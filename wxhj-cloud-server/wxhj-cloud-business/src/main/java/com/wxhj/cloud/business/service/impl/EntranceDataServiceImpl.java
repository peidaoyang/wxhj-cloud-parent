/**
 * @className EntranceDataServiceImpl.java
 * @author jwl
 * @date 2020年1月17日 下午1:17:03
 */
package com.wxhj.cloud.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.EntranceDataDO;
import com.wxhj.cloud.business.mapper.EntranceDataMapper;
import com.wxhj.cloud.business.service.EntranceDataService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @className EntranceDataServiceImpl.java
 * @author jwl
 * @date 2020年1月17日 下午1:17:03
 */
@Service
public class EntranceDataServiceImpl implements EntranceDataService {

	@Resource
	EntranceDataMapper entranceDataMapper;

	@Override
	public void insert(EntranceDataDO entranceData) {
		entranceDataMapper.insert(entranceData);
	}

	

}
