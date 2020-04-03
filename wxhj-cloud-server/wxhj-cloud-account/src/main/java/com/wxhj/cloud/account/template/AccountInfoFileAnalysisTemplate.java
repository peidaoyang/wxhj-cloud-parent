/**
 * 
 */
package com.wxhj.cloud.account.template;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.core.file.AbstractFileAnalysisTemplate;
import com.wxhj.cloud.core.file.IFileAnalysis;

import lombok.Data;

/**
 * @ClassName: AccountInfoBO.java
 * @author: cya
 * @Date: 2019年12月6日 下午2:57:59
 */
@Data
@Component("accountInfoFileAnalysis")
public class AccountInfoFileAnalysisTemplate extends AbstractFileAnalysisTemplate<AccountInfoDO>
		implements IFileAnalysis<AccountInfoDO> {

	private static Class<AccountInfoDO> accountInfoClass = AccountInfoDO.class;
	private static List<String> columeList = Arrays.asList("phoneNumber", "name", "idNumber", "sex", "accountType","otherCode");

	@Override
	protected List<String> getColumeNameList() {

		return columeList;
	}

	@Override
	protected Class<AccountInfoDO> getTclass() {
		return accountInfoClass;
	}
}
