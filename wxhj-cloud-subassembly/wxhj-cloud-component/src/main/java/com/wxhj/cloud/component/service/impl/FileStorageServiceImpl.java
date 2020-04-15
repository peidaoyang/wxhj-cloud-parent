package com.wxhj.cloud.component.service.impl;

import com.google.common.base.Strings;
import com.google.common.io.ByteStreams;
import com.wxhj.cloud.baidu.services.bos.BosClient;
import com.wxhj.cloud.baidu.services.bos.model.BosObject;
import com.wxhj.cloud.baidu.services.bos.model.ObjectMetadata;
import com.wxhj.cloud.baidu.services.bos.model.PutObjectResponse;
import com.wxhj.cloud.component.config.BaiduConfig;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.core.statics.RocketMqStaticClass;
import com.wxhj.cloud.core.statics.RocketMqTopicStaticClass;
import com.wxhj.cloud.rocketmq.RocketMqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {
    @Resource
    BosClient bosClient;
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    RocketMqProducer rocketMqProducer;
    @Resource
    BaiduConfig baiduConfig;

    private void timedDelete(String fileUuid) {
        redisTemplate.opsForSet().add(RedisKeyStaticClass.FILR_TIMED_DELETE, fileUuid);
        rocketMqProducer.sendDelayTimeMessage(RocketMqStaticClass.DELAY_TIME, RocketMqTopicStaticClass.FILEUPLOAD_TOPIC,
                fileUuid);
    }

    @Override
    public boolean saveFile(byte[] file, String fileUuid) {
        timedDelete(fileUuid);
        PutObjectResponse putObjectResponseFromByte = bosClient.putObject(baiduConfig.getFileBucketName(), fileUuid,
                file);

        return true;
    }

    @Override
    public boolean saveFileInputStream(InputStream inputStream, String fileUuid) {
        timedDelete(fileUuid);
        PutObjectResponse putObjectResponseFromInputStream = bosClient.putObject(baiduConfig.getFileBucketName(),
                fileUuid, inputStream);

        return true;
    }

    @Override
    public byte[] getFile(String fileUuid) {
        // 获取Object，返回结果为BosObject对象
        // BosObject object = bosClient.getObject(baiduConfig.getFileBucketName(),
        // fileUuid);
        // 获取ObjectMeta
        // ObjectMetadata meta = object.getObjectMetadata();
        // 获取Object的输入流
        InputStream objectContent = getFileInputStream(fileUuid);
        //byte[] fileByte = CommUtil.readFileBytes(objectContent);
        byte[] fileByte = null;
        try {
            fileByte = ByteStreams.toByteArray(objectContent);
            objectContent.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return fileByte;
    }

    @Override
    public boolean deleteFile(String fileUuid) {
        // 删除地址表
        redisTemplate.opsForHash().delete(baiduConfig.getFileBucketName(), fileUuid);
        redisTemplate.opsForSet().remove(RedisKeyStaticClass.FILR_TIMED_DELETE, fileUuid);

        if (existFile(fileUuid)) {
            bosClient.deleteObject(baiduConfig.getFileBucketName(), fileUuid);
        }
        return true;
    }

    @Override
    public boolean existFile(String fileUuid) {
        return bosClient.doesObjectExist(baiduConfig.getFileBucketName(), fileUuid);
    }

    @Override
    public String generateFileUrl(String fileUuid) {

        if (Strings.isNullOrEmpty(fileUuid)) {
            return null;
        }

        Boolean hasKey = redisTemplate.opsForHash().hasKey(RedisKeyStaticClass.FILR_URL_TABLE, fileUuid);
        if (hasKey) {
            return redisTemplate.opsForHash().get(RedisKeyStaticClass.FILR_URL_TABLE, fileUuid).toString();
        } else {
            if (!existFile(fileUuid)) {
                return null;
            }
            URL url = bosClient.generatePresignedUrl(baiduConfig.getFileBucketName(), fileUuid, -1);
            String urlString = url.toString();
            redisTemplate.opsForHash().put(RedisKeyStaticClass.FILR_URL_TABLE, fileUuid, urlString);
            return urlString;
        }
    }

    @Override
    public boolean timedDeleteFile(String fileUuid) {
        if (redisTemplate.opsForSet().isMember(RedisKeyStaticClass.FILR_TIMED_DELETE, fileUuid)) {
            deleteFile(fileUuid);
        }
        return true;
    }

    @Override
    public boolean notDeleteFile(String fileUuid) {
        redisTemplate.opsForSet().remove(RedisKeyStaticClass.FILR_TIMED_DELETE, fileUuid);
        return true;
    }

    @Override
    public InputStream getFileInputStream(String fileUuid) {
        // TODO Auto-generated method stub
        // 获取Object，返回结果为BosObject对象
        BosObject object = bosClient.getObject(baiduConfig.getFileBucketName(), fileUuid);

        // 获取ObjectMeta
        ObjectMetadata meta = object.getObjectMetadata();
        // 获取Object的输入流
        InputStream objectContent = object.getObjectContent();
        return objectContent;
    }
}
