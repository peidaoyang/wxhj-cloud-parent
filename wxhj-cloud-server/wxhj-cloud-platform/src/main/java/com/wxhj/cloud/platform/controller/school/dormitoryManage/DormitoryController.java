package com.wxhj.cloud.platform.controller.school.dormitoryManage;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.school.DormitoryClient;
import com.wxhj.cloud.feignClient.school.requestDTO.SubmitDormitoryRequestDTO;
import com.wxhj.cloud.feignClient.school.responseDTO.DormitoryInfoResponseDTO;
import com.wxhj.cloud.feignClient.school.vo.ListDormitoryByOrgIdVO;
import com.wxhj.cloud.feignClient.school.vo.ListDormitoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author cya
 * @Date 2020/5/20
 * @Version V1.0
 **/
@RestController
@RequestMapping("/school/dormitory")
@Api(tags="楼栋设置")
public class DormitoryController {
    @Resource
    DormitoryClient dormitoryClient;

    @ApiOperation(value = "分页查询楼栋设置",response = ListDormitoryVO.class)
    @PostMapping("/listDormitory")
    @LcnTransaction
    public WebApiReturnResultModel listDormitory(@RequestBody @Validated CommonListPageRequestDTO commonListPage){
        return dormitoryClient.listDormitory(commonListPage);
    }

    @ApiOperation(value = "根据id查询楼栋信息",response = DormitoryInfoResponseDTO.class)
    @PostMapping("/dormitoryInfo")
    @LcnTransaction
    public WebApiReturnResultModel dormitoryInfo(@RequestBody @Validated CommonIdRequestDTO commonId){
        return dormitoryClient.dormitoryInfo(commonId);
    }

    @ApiOperation(value = "根据组织id查询楼栋列表",response = ListDormitoryByOrgIdVO.class)
    @PostMapping("/listDormitoryByOrgId")
    @LcnTransaction
    public WebApiReturnResultModel listDormitoryByOrgId(@RequestBody @Validated CommonIdRequestDTO commonId){
        return dormitoryClient.listDormitoryByOrgId(commonId);
    }

    @ApiOperation("新增/修改 楼栋")
    @PostMapping("/submitDormitory")
    @LcnTransaction
    public WebApiReturnResultModel submitDormitory(@RequestBody @Validated SubmitDormitoryRequestDTO submitDormitory){
        return dormitoryClient.submitDormitory(submitDormitory);
    }

    @ApiOperation("删除楼栋")
    @PostMapping("/deleteDormitory")
    @LcnTransaction
    public WebApiReturnResultModel deleteDormitory(@RequestBody @Validated CommonIdRequestDTO commonId){
        return dormitoryClient.deleteDormitory(commonId);
    }
}
