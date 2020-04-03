package com.wxhj.cloud.platform.controller.file;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.MapSceneAccountClient;
import com.wxhj.cloud.feignClient.dto.file.FileDownloadDTO;
import com.wxhj.cloud.feignClient.platform.FileDownloadClient;
import com.wxhj.cloud.feignClient.platform.request.FileDownloadRequestDTO;
import com.wxhj.cloud.platform.domain.FileDownloadDO;
import com.wxhj.cloud.platform.service.FileDownloadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author daxiong
 * @date 2020-04-02 18:59
 */
@RestController
@RequestMapping("/file/download")
@Api(tags = "文件下载管理")
public class FileDownloadController implements FileDownloadClient {

    @Resource
    FileDownloadService fileDownloadService;
    @Resource
    DozerBeanMapper dozerBeanMapper;
    @Resource
    MapSceneAccountClient mapSceneAccountClient;

    @PostMapping("/insert")
    @ApiOperation("插入文件下载管理记录")
    @Override
    public WebApiReturnResultModel insertFileDownload(@Validated @RequestBody FileDownloadDTO fileDownloadDTO) {
        FileDownloadDO fileDownloadDO = new FileDownloadDO();
        dozerBeanMapper.map(fileDownloadDTO, fileDownloadDO);
        String insertId = fileDownloadService.insert(fileDownloadDO);
        return WebApiReturnResultModel.ofSuccess(insertId);
    }

    @PostMapping("/list")
    @ApiOperation("获取文件下载记录列表")
    @Override
    public WebApiReturnResultModel listFileDownload(@Validated @RequestBody FileDownloadRequestDTO fileDownloadRequest) {
        String organizeId = fileDownloadRequest.getOrganizeId();
        String taskId = fileDownloadRequest.getSceneId();
        Date startTime = fileDownloadRequest.getStartTime();
        Date endTime = fileDownloadRequest.getEndTime();
        List<FileDownloadDO> fileDownloads = fileDownloadService.listByOrganizeIdAndTaskIdAndTime(organizeId, taskId, startTime, endTime);
        return WebApiReturnResultModel.ofSuccess(fileDownloads);
    }

    /**
     * 调用account服务，下载账户zip包
     * @param fileDownloadRequestDTO
     * @return
     */
    @PostMapping("/packFaceBySceneId")
    @ApiOperation("下载账户zip包")
    public WebApiReturnResultModel packFaceBySceneId(@RequestBody @Validated FileDownloadRequestDTO fileDownloadRequestDTO) {
        return mapSceneAccountClient.packFaceBySceneId(fileDownloadRequestDTO);
    }

}
