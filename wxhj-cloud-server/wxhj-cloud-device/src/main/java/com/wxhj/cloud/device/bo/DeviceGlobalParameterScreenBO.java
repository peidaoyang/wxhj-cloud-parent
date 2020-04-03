/** 
 * @fileName: DeviceGlobalParameterScreenBO.java  
 * @author: pjf
 * @date: 2019年12月16日 下午3:32:47 
 */

package com.wxhj.cloud.device.bo;

import java.util.ArrayList;
import java.util.List;

import com.wxhj.cloud.device.domain.DeviceGlobalParameterDO;

import lombok.Data;
import lombok.ToString;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @className DeviceGlobalParameterScreenBO.java
 * @author pjf
 * @date 2019年12月16日 下午3:32:47   
*/
/**
 * @className DeviceGlobalParameterScreenBO.java
 * @author pjf
 * @date 2019年12月16日 下午3:32:47
 */
@Data
@ToString
public class DeviceGlobalParameterScreenBO {

	
	private String organizeId;
	private Integer deviceType;
	private String deviceId;
	private String sceneId;

	private Criteria buildExample() {
		return new Example(DeviceGlobalParameterDO.class).createCriteria();
	}

	private Criteria getOrganizeCriteria() {
		Criteria criteria = buildExample();
		criteria.andEqualTo("organizeId", "*").orEqualTo("organizeId", organizeId);
		//criteria.and
		return criteria;
	}

	private Criteria getDeviceTypeCriteria() {
		Criteria criteria = buildExample();
		criteria.andEqualTo("deviceType", -1).orEqualTo("deviceType", deviceType);
		return criteria;
	}

	private Criteria getDeviceIdCriteria() {
		Criteria criteria = buildExample();
		criteria.andIsNull("deviceIdList").orLike("deviceIdList", "%" + deviceId + "%");
		return criteria;
	}

	private Criteria getSceneIdCriteria() {
		Criteria criteria = buildExample();
		criteria.andEqualTo("sceneId", "*").orLike("sceneId", "%" +sceneId+ "%");
		return criteria;
	}

	public List<Criteria> getCriteriaList() {
		List<Criteria> criteriaList = new ArrayList<>();
		criteriaList.add(getOrganizeCriteria());
		criteriaList.add(getDeviceTypeCriteria());
		criteriaList.add(getDeviceIdCriteria());
		criteriaList.add(getSceneIdCriteria());
		return criteriaList;
	}
}
