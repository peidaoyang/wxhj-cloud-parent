/** 
 * @fileName: IOrganizeModel.java  
 * @author: pjf
 * @date: 2020年2月12日 上午10:38:58 
 */

package com.wxhj.cloud.feignClient.bo;


/**
 * 根据accountId获取根组织和子组织的id，子类实现该接口
 * @author daxiong
 * @date 2020/4/17 11:10 上午
 * @return
 */
public interface IAccountOrganizeModel {
	String getAccountId();

	void setAccountId(String accountId);

	String getOrganizeId();

	void setOrganizeId(String organizeId);

	String getChildOrganizeId();

	void setChildOrganizeId(String childOrganizeId);
}
