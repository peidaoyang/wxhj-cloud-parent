package com.wxhj.cloud.platform.listener;

import com.alibaba.fastjson.JSON;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.statics.RocketMqTopicStaticClass;
import com.wxhj.cloud.feignClient.dto.file.FileDownloadDTO;
import com.wxhj.cloud.platform.domain.FileDownloadDO;
import com.wxhj.cloud.platform.service.FileDownloadService;
import com.wxhj.cloud.rocketmq.RocketMqListenDoWorkHandle;
import com.wxhj.cloud.rocketmq.annotation.RocketMqConsumerListenAnnotation;
import org.apache.rocketmq.common.message.MessageExt;
import org.dozer.DozerBeanMapper;

import javax.annotation.Resource;

/**
 * 文件下载状态变化的监听器
 * @author daxiong
 * @date 2020-04-02 18:34
 */
@RocketMqConsumerListenAnnotation(topic = RocketMqTopicStaticClass.FILE_DOWNLOAD_TOPIC)
public class FileDownloadListener implements RocketMqListenDoWorkHandle {

    @Resource
    FileDownloadService fileDownloadService;
    @Resource
    DozerBeanMapper dozerBeanMapper;
    @Resource
    FileStorageService fileStorageService;

    @Override
    public void dataHandle(MessageExt messageExt) {
        String bodyStr = new String(messageExt.getBody());
        System.out.println("接收到来自RocketMQ的消息：bodyStr: " + bodyStr);

        FileDownloadDTO fileDownloadDTO = JSON.parseObject(bodyStr, FileDownloadDTO.class);
        FileDownloadDO fileDownloadDO = new FileDownloadDO();
        dozerBeanMapper.map(fileDownloadDTO, fileDownloadDO);

        // 文件默认20min后删除，这里设置为永远不删除
        fileStorageService.notDeleteFile(fileDownloadDO.getFileName());

        // 修改数据库，将文件下载的状态修改
        fileDownloadService.update(fileDownloadDO);
    }
}
