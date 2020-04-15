package com.wxhj.cloud.platform.controller.file;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.account.MapSceneAccountClient;
import com.wxhj.cloud.feignClient.account.bo.FileDownloadBO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.platform.FileDownloadClient;
import com.wxhj.cloud.feignClient.platform.request.FileDownloadRequestDTO;
import com.wxhj.cloud.feignClient.platform.request.ListFileDownloadRequestDTO;
import com.wxhj.cloud.platform.domain.FileDownloadDO;
import com.wxhj.cloud.platform.service.FileDownloadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.dozer.DozerBeanMapper;
import org.springframework.transaction.annotation.Transactional;
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
    @Resource
    AccessedRemotelyService accessedRemotelyService;
    @Resource
    FileStorageService fileStorageService;

    @PostMapping("/insertFileDownload")
    @ApiOperation("插入文件下载管理记录")
    @Override
    public WebApiReturnResultModel insertFileDownload(@Validated @RequestBody FileDownloadBO fileDownloadBO) {
        FileDownloadDO fileDownloadDO = new FileDownloadDO();
        dozerBeanMapper.map(fileDownloadBO, fileDownloadDO);
        String insertId = fileDownloadService.insert(fileDownloadDO);
        return WebApiReturnResultModel.ofSuccess(insertId);
    }

    @PostMapping("/listFileDownload")
    @ApiOperation(value = "获取文件下载记录列表", response = FileDownloadBO.class)
    @ApiResponse(code = 200, message = "请求成功", response = FileDownloadBO.class)
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
        List<FileDownloadBO> fileDownloadBOList = fileDownloadsPage.getList().stream()
                .map(q ->  dozerBeanMapper.map(q, FileDownloadBO.class)).collect(Collectors.toList());
        // 增加组织名称信息
        try {
            fileDownloadBOList = (List<FileDownloadBO>) accessedRemotelyService.accessedOrganizeList(fileDownloadBOList);
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            return wuXiHuaJieFeignError.getWebApiReturnResultModel();
        }

        // 构建分页信息返回实体
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(fileDownloadsPage,
                fileDownloadBOList, new PageDefResponseModel());
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

    /**
     * 批量删除文件下载记录
     * @param commonIdListRequestDTO
     * @return
     */
    @PostMapping("/deleteFileDownload")
    @ApiOperation("批量删除文件下载记录")
    @Transactional(rollbackFor = Exception.class)
    public WebApiReturnResultModel deleteFileDownload(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequestDTO) {
        List<String> idList = commonIdListRequestDTO.getIdList();
        if (idList == null || idList.size() == 0) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.PARAMETER_MUST_NOT_NULL);
        }
        // 获取到要删除的数据
        List<FileDownloadDO> fileDownloads = fileDownloadService.listFileDownload(idList);
        // 删除文件
        fileDownloads.stream().filter(item -> !Strings.isNullOrEmpty(item.getFileName()))
                .map(item -> fileStorageService.deleteFile(item.getFileName()));

        fileDownloadService.delete(idList);
        return WebApiReturnResultModel.ofSuccess();
    }

}
