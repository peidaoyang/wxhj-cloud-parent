package com.wxhj.cloud.feignClient.bo;

/**
 * @ClassName: IUserModle.java
 * @author: cya
 * @Date: 2020年3月18日 下午1:18:00 
 */
public interface IUserModel {
	/**
	 * @return the creatorUserId
	 */
	public String getCreatorUserId();
	/**
	 * @param creatorUserId the creatorUserId to set
	 */
	public void setCreatorUserId(String creatorUserId);
	/**
	 * @return the creatorUserName
	 */
	public String getCreatorUserName();
	/**
	 * @param creatorUserName the creatorUserName to set
	 */
	public void setCreatorUserName(String creatorUserName);
}
