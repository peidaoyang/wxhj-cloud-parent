///**
// * 
// */
//package com.wxhj.cloud.platform.common;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.wxhj.cloud.core.model.WebApiReturnResultModel;
//import com.wxhj.cloud.core.utils.FeignUtil;
//import com.wxhj.cloud.platform.bo.SysUserOrgRoleBO;
//import com.wxhj.cloud.platform.controller.system.UserController;
//import com.wxhj.cloud.platform.dto.request.UserOrgRoleChooseListRequestDTO;
//
///**
// * @ClassName: UserControllerPublicTest.java
// * @author: cya
// * @Date: 2020年3月5日 上午10:19:11 
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class UserControllerPublicTest {
//	@Resource
//	UserController userController;
//	
//	@Test
//	public void submit() throws Exception{
//		UserOrgRoleChooseListRequestDTO userOrgRoleChooseList = new UserOrgRoleChooseListRequestDTO();
//		userOrgRoleChooseList.setUserId("9661ef43-82c0-cf27-4472-08e5a42c24cb");
//		WebApiReturnResultModel webApiReturnResultModel = userController.userOrgRoleChooseList(userOrgRoleChooseList);
//		if(webApiReturnResultModel.resultSuccess()) {
//			List<SysUserOrgRoleBO> userOrgRoleBOList = FeignUtil.formatArrayClass(webApiReturnResultModel, SysUserOrgRoleBO.class);
//			assertThat(true, is(userOrgRoleBOList.size()>0));
//		}
//		
//		
////		UserOrgRoleListRequestDTO 
//	}
//}
