package com.wxhj.cloud.feignClient.platform;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.file.FileDownloadDTO;
import com.wxhj.cloud.feignClient.platform.request.ListFileDownloadRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author daxiong
 * @date 2020-04-02 19:09
 */
@Component
@FeignClient(name = "platformServer")
public interface FileDownloadClient {
    /**
     * 插入文件下载记录
     * @param fileDownload
     * @return
     */
    @PostMapping("/fileDownload/insertFileDownload")
    WebApiReturnResultModel insertFileDownload(FileDownloadDTO fileDownload);

    /**
     * 根据taskId获取下载记录列表
     * @param fileDownloadRequest
     * @return
     */
    @PostMapping("/fileDownload/listFileDownload")
    WebApiReturnResultModel listFileDownload(@Validated @RequestBody ListFileDownloadRequestDTO fileDownloadRequest);

}
