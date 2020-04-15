package com.wxhj.cloud.account.thread;

import com.alibaba.fastjson.JSON;
import com.google.common.io.ByteStreams;
import com.wxhj.cloud.account.dto.account.FileFaceAccountDTO;
import com.wxhj.cloud.account.dto.account.FileFaceAccountInfoDTO;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.enums.FileDownloadStatusEnum;
import com.wxhj.cloud.core.statics.FileStaticClass;
import com.wxhj.cloud.core.statics.RocketMqTopicStaticClass;
import com.wxhj.cloud.core.statics.SystemStaticClass;
import com.wxhj.cloud.core.utils.FileUtil;
import com.wxhj.cloud.feignClient.account.bo.FileDownloadBO;
import com.wxhj.cloud.feignClient.face.bo.FaceAccountInfoBO;
import com.wxhj.cloud.feignClient.face.bo.FaceChangeBO;
import com.wxhj.cloud.rocketmq.RocketMqProducer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author daxiong
 * @date 2020-04-01 15:17
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Data
@Slf4j
public class AccountFileDownloadThread implements Callable<String> {

    @Resource
    FileStorageService fileStorageService;
    @Resource
    DozerBeanMapper dozerBeanMapper;
    @Resource
    private RocketMqProducer rocketMqProducer;

    private FaceChangeBO faceChange;
    private List<FaceAccountInfoBO> faceAccountInfos;
    private String downloadId;

    @Override
    public String call() throws Exception {
        String zipFileUrl = null;
        FileDownloadBO fileDownload = new FileDownloadBO();
        fileDownload.setId(downloadId);
        try {
            // 将账户信息和人脸图片打包成zip包
            String zipFilePath = accountInfo2zip(faceChange, faceAccountInfos);

            // 返回zip的服务器地址
            InputStream inputStream = new FileInputStream(new File(zipFilePath));
            fileStorageService.saveFileInputStream(inputStream, zipFilePath);
            zipFileUrl = fileStorageService.generateFileUrl(zipFilePath);
            // 删除zip包
            FileUtil.deleteFile(new File(zipFilePath));

            // 获取文件的名称
            String zipFileName = zipFilePath.substring(zipFilePath.lastIndexOf(File.separator) + 1);

            // 将下载信息放入rocketMQ
            fileDownload.setStatus(FileDownloadStatusEnum.SUCCEED.getCode());
            fileDownload.setFileName(zipFileName);
            fileDownload.setDownloadUrl(zipFileUrl);
            rocketMqProducer.sendMessage(RocketMqTopicStaticClass.FILE_DOWNLOAD_TOPIC, JSON.toJSONString(fileDownload));

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            fileDownload.setStatus(FileDownloadStatusEnum.FAILED.getCode());
            rocketMqProducer.sendMessage(RocketMqTopicStaticClass.FILE_DOWNLOAD_TOPIC, JSON.toJSONString(fileDownload));
        }
        return zipFileUrl;
    }

    /**
     * 将账户信息和人脸图片打包成zip包
     *
     * @param faceChange       场景信息
     * @param faceAccountInfos 账号信息
     * @return  zip包的本地文件路径
     * @throws IOException
     */
    private String accountInfo2zip(FaceChangeBO faceChange, List<FaceAccountInfoBO> faceAccountInfos)
            throws IOException {
        // FileFaceAccountDTO用于与写文件交互
        FileFaceAccountDTO fileFaceAccountDTO = new FileFaceAccountDTO();
        fileFaceAccountDTO.setFaceChange(faceChange);

        // 根路径，兼容带"/"和不带"/"
        String rootPath = System.getProperty(FileStaticClass.TMP_DIR);
        if (!rootPath.substring(rootPath.length() - 1).equals(File.separator)) {
            rootPath += File.separator;
        }
        // 路径前缀
        String prefix = UUID.randomUUID().toString();
        // 完整路径
        String wholePath = rootPath + prefix;

        // 账号信息返回
        List<FileFaceAccountInfoDTO> fileFaceInfoList = new ArrayList<>(SystemStaticClass.INIT_CAPACITY);
        // 从fileServer获取图片
        for (FaceAccountInfoBO faceAccountInfoTemp : faceAccountInfos) {
            // 拼接账户信息
            FileFaceAccountInfoDTO fileFaceAccountInfoDTO =
                    dozerBeanMapper.map(faceAccountInfoTemp, FileFaceAccountInfoDTO.class);
            fileFaceInfoList.add(fileFaceAccountInfoDTO);

            String imageName = faceAccountInfoTemp.getImageName();
            InputStream imageInputStream;
            try {
                imageInputStream = fileStorageService.getFileInputStream(imageName);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            // 将face信息写入文件夹
            String fileName = wholePath + File.separator + "face" + File.separator + imageName;
            // FileUtil.byte2image(imageByte, fileName);
            File file = FileUtil.createFile(fileName);
            ByteStreams.copy(imageInputStream, new FileOutputStream(file));
        }

        fileFaceAccountDTO.setAccountInfoList(fileFaceInfoList);
        // 账号信息写入文件
        String sysUserString = JSON.toJSONString(fileFaceAccountDTO);

        String filename = wholePath + File.separator + FileStaticClass.ACCOUNT_FILE_NAME;
        FileUtil.writeToFile(filename, sysUserString);

        // 打成zip包
        String zipFilePath = wholePath + FileStaticClass.ZIP_SUFFIX;
        FileUtil.toZip(wholePath, zipFilePath, true);

        // 删除临时文件
        FileUtil.deleteFile(new File(wholePath));
        return zipFilePath;
    }

}
