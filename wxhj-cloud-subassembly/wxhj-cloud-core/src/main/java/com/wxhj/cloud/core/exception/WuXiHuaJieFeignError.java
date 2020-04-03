/** 
 * @fileName: WuXiHuaJieFeginError.java  
 * @author: pjf
 * @date: 2020年3月2日 下午3:44:01 
 */

package com.wxhj.cloud.core.exception;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;

import lombok.Getter;

/**
 * @className WuXiHuaJieFeginError.java
 * @author pjf
 * @date 2020年3月2日 下午3:44:01
 */

@Getter
public class WuXiHuaJieFeignError extends Exception {
//RuntimeException
	WebApiReturnResultModel webApiReturnResultModel;

	private static final long serialVersionUID = 6029257377054777937L;

	public WuXiHuaJieFeignError(WebApiReturnResultModel webApiReturnResultModel) {
		this.webApiReturnResultModel = webApiReturnResultModel;
	}

	public WuXiHuaJieFeignError(WebResponseState webResponseState) {
		this.webApiReturnResultModel = WebApiReturnResultModel.ofStatus(webResponseState);
	}

}
