package com.wxhj.cloud.account.controller.face;

import com.google.common.io.Files;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.domain.MapAccountAuthorityDO;
import com.wxhj.cloud.account.domain.view.ViewAutoSynchroAuthorityDO;
import com.wxhj.cloud.account.service.AccountInfoService;
import com.wxhj.cloud.account.service.MapAccountAuthorityService;
import com.wxhj.cloud.account.service.ViewAutoSynchroAuthorityService;
import com.wxhj.cloud.component.service.FaceImageService;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.DateError;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.face.FaceAccountClient;
import com.wxhj.cloud.feignClient.face.request.FaceRegisterBatchRequestDTO;
import com.wxhj.cloud.feignClient.face.request.FaceRegisterRequestDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/faceAccount")
public class FaceAccountController implements FaceAccountClient {
    @Resource
    AccountInfoService accountInfoService;
    @Resource
    FileStorageService fileStorageService;
    @Resource
    FaceImageService faceImageService;
    @Resource
    MapAccountAuthorityService mapAccountAuthorityService;
    @Resource
    ViewAutoSynchroAuthorityService viewAutoSynchroAuthorityService;


    @PostMapping("/faceRegister")
    @ApiOperation("人脸注册")
    @Override
    public WebApiReturnResultModel faceRegister(@Validated @RequestBody FaceRegisterRequestDTO faceRegisterRequest) {
        AccountInfoDO accountInfo = accountInfoService.selectByAccountId(faceRegisterRequest.getAccountId());
        try {
            faceImageCheck(faceRegisterRequest.getImageName());
            commondFaceRegister(accountInfo, faceRegisterRequest.getImageName());
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        if(accountInfo.getIsFace() == 0){
            return synchroAuthority(accountInfo);
        }
        return WebApiReturnResultModel.ofSuccess();
    }

    @ApiOperation("人脸批量注册")
    @PostMapping("/faceRegisterBatch")
    @Override
    public WebApiReturnResultModel faceRegisterBatch(
            @Validated @RequestBody FaceRegisterBatchRequestDTO faceRegisterBatchRequest) {
        String originalImageName = Files.getNameWithoutExtension(faceRegisterBatchRequest.getOriginalImageName());

        try {
            faceImageCheck(faceRegisterBatchRequest.getImageName());
            AccountInfoDO selectByNoAndOrganizeId = accountInfoService.selectByNoAndOrganizeId(
                    originalImageName,
                    faceRegisterBatchRequest.getImageNameType(),
                    faceRegisterBatchRequest.getOrganizeId());
            if(selectByNoAndOrganizeId.getIsFace() == 0){
                commondFaceRegister(selectByNoAndOrganizeId, faceRegisterBatchRequest.getImageName());
                return synchroAuthority(selectByNoAndOrganizeId);
            }
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        return WebApiReturnResultModel.ofSuccess();
    }

    // 人脸校验
    private void faceImageCheck(String imageName) throws WuXiHuaJieFeignError {
        byte[] imageFile = fileStorageService.getFile(imageName);
        boolean faceMonitor = faceImageService.faceMonitor(imageFile);
        if (!faceMonitor) {
            throw new WuXiHuaJieFeignError(WebResponseState.FACE_ERROR);
        }
    }

    @Transactional
    public void commondFaceRegister(AccountInfoDO accountInfo, String imageName) throws WuXiHuaJieFeignError{
        if (accountInfo == null) {
            throw new WuXiHuaJieFeignError(WebResponseState.ACCOUNT_NO_EXIST);
        }

        String oldImageName = accountInfo.getImageName();
        accountInfo.setImageName(imageName);

        if (accountInfo.getIsFace() == 1) {
            Optional.ofNullable(oldImageName).ifPresent(q -> fileStorageService.deleteFile(q));
            accountInfoService.updateCascade(accountInfo);
            fileStorageService.notDeleteFile(imageName);
            return;
        }

        accountInfoService.insertFaceImage(accountInfo.getAccountId(), imageName);
        fileStorageService.notDeleteFile(imageName);
    }

    private WebApiReturnResultModel synchroAuthority(AccountInfoDO accountInfo){
        //只有人脸注册过才有同步权限组的资格
        List<ViewAutoSynchroAuthorityDO> list = viewAutoSynchroAuthorityService.listByOrgId(accountInfo.getChildOrganizeId());
        List<MapAccountAuthorityDO> mapAccountAuthorityList = list.stream().map(q -> new MapAccountAuthorityDO(null, q.getId(), accountInfo.getAccountId())).collect(Collectors.toList());
        try {
            mapAccountAuthorityList.forEach(q -> {mapAccountAuthorityService.insertCascade(q);});
        }catch (DateError e) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.ACCOUNT_ATTENDANCE_ERROR);
        }
        return WebApiReturnResultModel.ofSuccess();
    }
}
