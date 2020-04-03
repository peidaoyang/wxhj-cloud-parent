/**
 * 
 */
package com.wxhj.cloud.account;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.account.controller.AuthorityGroupController;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.account.request.OptionalAuthorityGroupListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.vo.DropDownListControlVO;

/**
 * @ClassName: AuthorityGroupControllerTest.java
 * @author: cya
 * @Date: 2020年3月3日 下午3:03:01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AuthorityGroupControllerPublicTest {
	@Resource
	AuthorityGroupController authorityGroupController;

	@Test
	public void search() throws Exception {
//		System.out.println("测试查询接口=====>");
//		String orgId = "f8b89131-de13-4dc2-b5bb-b117e12c23bc";
//		WebApiReturnResultModel submitModel = authorityGroupController
//				.optionalAuthorityGroupList(new OptionalAuthorityGroupListRequestDTO(orgId));
//		assertThat(true, is(submitModel.resultSuccess()));
//		List<DropDownListControlVO> dropDownListControlList = FeignUtil.formatArrayClass(submitModel,
//				DropDownListControlVO.class);
//		assertThat(true, is(dropDownListControlList.size() > 0));
	}
}
