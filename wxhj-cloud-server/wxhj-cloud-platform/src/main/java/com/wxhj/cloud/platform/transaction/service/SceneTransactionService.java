/** 
 * @fileName: SceneTransactionService.java  
 * @author: pjf
 * @date: 2019年11月13日 下午1:18:27 
 */

package com.wxhj.cloud.platform.transaction.service;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.platform.dto.request.SubmitSceneInfoRequestDTO;

/**
 * @className SceneTransactionService.java
 * @author pjf
 * @date 2019年11月13日 下午1:18:27   
*/
/** 
 * @className SceneTransactionService.java
 * @author pjf
 * @date 2019年11月13日 下午1:18:27 
*/

public interface SceneTransactionService {

	WebApiReturnResultModel deleteSceneInfoExecute(String sceneId);
	
	WebApiReturnResultModel submitSceneInfoExecute(SubmitSceneInfoRequestDTO submitSceneInfoRequest);
}
