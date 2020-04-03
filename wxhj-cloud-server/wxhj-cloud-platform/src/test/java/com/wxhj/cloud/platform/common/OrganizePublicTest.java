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
//
//import com.wxhj.cloud.core.model.WebApiReturnResultModel;
//import com.wxhj.cloud.core.utils.FeignUtil;
//import com.wxhj.cloud.feignClient.vo.DropDownListControlVO;
//import com.wxhj.cloud.feignClient.vo.TreeListControlVO;
//import com.wxhj.cloud.platform.controller.system.OrganizeController;
//import com.wxhj.cloud.platform.dto.request.SysOrganizeMainRequestDTO;
//import com.wxhj.cloud.platform.dto.request.SysOrganizeRequestDTO;
//import com.wxhj.cloud.platform.dto.request.TreeListRequestDTO;
//import com.wxhj.cloud.platform.vo.SysOrganizeTreeVO;
//
///**
// * @ClassName: OrganizeController.java
// * @author: cya
// * @Date: 2020年3月2日 下午1:50:56 
// */
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class OrganizePublicTest {
//	@Resource
//	OrganizeController organizeController;
//	
//	@Test
//	public void sysOrganizeTreeList() throws Exception {
//		System.out.println("测试 =======> 获取组织树级显示列表 接口");
//		SysOrganizeRequestDTO sysOrganizeRequest = new SysOrganizeRequestDTO();
//		sysOrganizeRequest.setCurrentOrganizeId("dfaea5be-8273-4bdd-bd6f-4f66eaadd509");
//		WebApiReturnResultModel searchModel = organizeController.sysOrganizeTreeList(sysOrganizeRequest);
//		List<SysOrganizeTreeVO> searchResult =FeignUtil.formatArrayClass(searchModel, SysOrganizeTreeVO.class);
//		assertThat(true, is(searchResult.size()>0));
//	}
//	
//	@Test
//	public void sysOrganizeMainList() throws Exception {
//		System.out.println("测试 =======> 获取主要组织列表(层级<=1)");
//		SysOrganizeMainRequestDTO sysOrganizeRequest = new SysOrganizeMainRequestDTO();
//		sysOrganizeRequest.setIsSystem(true);
//		sysOrganizeRequest.setOrganizeId("f8b89131-de13-4dc2-b5bb-b117e12c23bc");
//		WebApiReturnResultModel searchModel = organizeController.sysOrganizeMainList(sysOrganizeRequest);
//		List<DropDownListControlVO> searchResult =FeignUtil.formatArrayClass(searchModel, DropDownListControlVO.class);
//		assertThat(true, is(searchResult.size()>0));
//	}
//	
//	@Test
//	public void treeListControl() throws Exception {
//		System.out.println("测试 =======> 获取组织树形菜单控件");
//		TreeListRequestDTO sysOrganizeRequest = new TreeListRequestDTO();
//		sysOrganizeRequest.setCurrentOrganizeId("f8b89131-de13-4dc2-b5bb-b117e12c23bc");
//		WebApiReturnResultModel searchModel = organizeController.treeListControl(sysOrganizeRequest);
//		List<TreeListControlVO> searchResult =FeignUtil.formatArrayClass(searchModel, TreeListControlVO.class);
//		assertThat(true, is(searchResult.size()>0));
//	}
//}
