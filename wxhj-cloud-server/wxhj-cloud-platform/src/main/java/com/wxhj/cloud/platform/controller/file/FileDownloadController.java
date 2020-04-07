package com.wxhj.cloud.platform.controller.file;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.account.MapSceneAccountClient;
import com.wxhj.cloud.feignClient.dto.file.FileDownloadDTO;
import com.wxhj.cloud.feignClient.platform.FileDownloadClient;
import com.wxhj.cloud.feignClient.platform.request.FileDownloadRequestDTO;
import com.wxhj.cloud.feignClient.platform.request.ListFileDownloadRequestDTO;
import com.wxhj.cloud.platform.domain.FileDownloadDO;
import com.wxhj.cloud.platform.service.FileDownloadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author daxiong
 * @date 2020-04-02 18:59
 */
@RestController
@RequestMapping("/fileDownload")
@Api(tags = "文件下载管理")
public class FileDownloadController implements FileDownloadClient {

    @Resource
    FileDownloadService fileDownloadService;
    @Resource
    DozerBeanMapper dozerBeanMapper;
    @Resource
    MapSceneAccountClient mapSceneAccountClient;

    @PostMapping("/insertFileDownload")
    @ApiOperation("插入文件下载管理记录")
    @Override
    public WebApiReturnResultModel insertFileDownload(@Validated @RequestBody FileDownloadDTO fileDownloadDTO) {
        FileDownloadDO fileDownloadDO = new FileDownloadDO();
        dozerBeanMapper.map(fileDownloadDTO, fileDownloadDO);
        String insertId = fileDownloadService.insert(fileDownloadDO);
        return WebApiReturnResultModel.ofSuccess(insertId);
    }

    @PostMapping("/listFileDownload")
    @ApiOperation(value = "获取文件下载记录列表", response = FileDownloadDTO.class)
    @ApiResponse(code = 200, message = "请求成功", response = FileDownloadDTO.class)
    @Override
    public WebApiReturnResultModel listFileDownload(@Validated @RequestBody ListFileDownloadRequestDTO fileDownloadRequest) {
        String organizeId = fileDownloadRequest.getOrganizeId();
        String taskId = fileDownloadRequest.getSceneId();
        Date startTime = fileDownloadRequest.getStartTime();
        Date endTime = fileDownloadRequest.getEndTime();
        // 获取分页查询信息
        PageInfo<FileDownloadDO> fileDownloadsPage = fileDownloadService.listPageByOrganizeIdAndTaskIdAndTime(fileDownloadRequest,
                organizeId, taskId, startTime, endTime);

        // 将分页信息中的data转成要返回的类型
        List<FileDownloadDTO> fileDownloadDTOList = fileDownloadsPage.getList().stream()
                .map(q ->  dozerBeanMapper.map(q, FileDownloadDTO.class)).collect(Collectors.toList());
        // 构建分页信息返回实体
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(fileDownloadsPage,
                fileDownloadDTOList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    /**
     * 调用account服务，下载账户zip包
     * @param fileDownloadRequestDTO
     * @return
     */
    @PostMapping("/packFaceBySceneId")
    @ApiOperation("打包账户zip包")
    public WebApiReturnResultModel packFaceBySceneId(@RequestBody @Validated FileDownloadRequestDTO fileDownloadRequestDTO) {
        return mapSceneAccountClient.packFaceBySceneId(fileDownloadRequestDTO);
    }

}
