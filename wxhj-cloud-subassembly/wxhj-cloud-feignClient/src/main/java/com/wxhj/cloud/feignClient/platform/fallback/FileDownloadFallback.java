package com.wxhj.cloud.feignClient.platform.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.file.FileDownloadDTO;
import com.wxhj.cloud.feignClient.platform.FileDownloadClient;
import com.wxhj.cloud.feignClient.platform.request.ListFileDownloadRequestDTO;
import org.springframework.stereotype.Component;

/**
 * @author daxiong
 * @date 2020-04-03 11:34
 */
@Component
public class FileDownloadFallback implements FileDownloadClient {
    @Override
    public WebApiReturnResultModel insertFileDownload(FileDownloadDTO fileDownload) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel listFileDownload(ListFileDownloadRequestDTO fileDownloadRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }
}
