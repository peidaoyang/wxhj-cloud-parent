package com.wxhj.cloud.account.controller.face;

import com.google.common.io.Files;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.domain.MapAccountAuthorityDO;
import com.wxhj.cloud.account.service.AccountInfoService;
import com.wxhj.cloud.account.service.MapAccountAuthorityService;
import com.wxhj.cloud.component.service.FaceImageService;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;

import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
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

    @PostMapping("/faceRegister")
    @ApiOperation("人脸注册")
    @Transactional
    @Override
    public WebApiReturnResultModel faceRegister(@Validated @RequestBody FaceRegisterRequestDTO faceRegisterRequest) {
        AccountInfoDO accountInfo = accountInfoService.selectByAccountId(faceRegisterRequest.getAccountId());
        try {
            faceImageCheck(faceRegisterRequest.getImageName());
            commondFaceRegister(accountInfo, faceRegisterRequest.getImageName());
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
            throw new WuXiHuaJieFeignError(WebApiReturnResultModel.ofStatus(WebResponseState.FACE_ERROR));
        }
    }

    private void commondFaceRegister(AccountInfoDO accountInfo, String imageName) throws WuXiHuaJieFeignError {
        if (accountInfo == null || accountInfo.getIsFace() != 0) {
            throw new WuXiHuaJieFeignError(WebResponseState.ACCOUNT_NO_EXIST);
        }
//        if (accountInfo.getIsFace() != 0) {
//            throw new WuXiHuaJieFeignError(WebResponseState.ACCOUNT_NO_EXIST);
//        }
        accountInfo.setImageName(imageName);

        if (accountInfo.getIsFace() == 0) {
            accountInfoService.update(new AccountInfoDO(accountInfo.getAccountId(),1,imageName));
        } else {
            //人脸替换需要重新下发人脸
            List<MapAccountAuthorityDO> deleteByAccountId = mapAccountAuthorityService.deleteByAccountId(accountInfo.getAccountId());
            for (MapAccountAuthorityDO mapAccountAuthority: deleteByAccountId) {
                mapAccountAuthorityService.insertCascade(mapAccountAuthority);
            }
        }
        fileStorageService.notDeleteFile(imageName);
    }

//    @ApiOperation("人脸注册信息删除")
//    @PostMapping("/faceDelete")
//    @Override
//    public WebApiReturnResultModel faceDelete(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
//        AccountInfoDO selectByAccount = accountInfoService.selectByAccountId(commonIdRequest.getId());
//        if (selectByAccount == null) {
//            return WebApiReturnResultModel.ofStatus(WebResponseState.ACCOUNT_NO_EXIST);
//        }
//        selectByAccount.setIsFace(0);
//        accountInfoService.update(selectByAccount);
//        fileStorageService.deleteFile(selectByAccount.getImageName());
//        return WebApiReturnResultModel.ofSuccess();
//    }

    @ApiOperation("人脸批量注册")
    @PostMapping("/faceRegisterBatch")
    @Override
    public WebApiReturnResultModel faceRegisterBatch(
            @Validated @RequestBody FaceRegisterBatchRequestDTO faceRegisterBatchRequest) {
        String originalImageName = Files.getNameWithoutExtension(faceRegisterBatchRequest.getOriginalImageName());

        try {
            faceImageCheck(faceRegisterBatchRequest.getImageName());
            AccountInfoDO selectByNoAndOrganizeId = accountInfoService.selectByNoAndOrganizeId(
                    originalImageName, faceRegisterBatchRequest.getImageNameType(), faceRegisterBatchRequest.getOrganizeId());
            commondFaceRegister(selectByNoAndOrganizeId, faceRegisterBatchRequest.getImageName());
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        return WebApiReturnResultModel.ofSuccess();
    }
}
