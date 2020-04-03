/** 
 * @fileName: MapListenListServiceImpl.java  
 * @author: pjf
 * @date: 2019年10月31日 下午1:53:32 
 */

package com.wxhj.cloud.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.MapListenListDO;
import com.wxhj.cloud.account.mapper.MapListenListMapper;
import com.wxhj.cloud.account.service.MapListenListService;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefRequestModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.account.vo.MapListenListVO;

import tk.mybatis.mapper.entity.Example;

/**
 * @className MapListenListServiceImpl.java
 * @author pjf
 * @date 2019年10月31日 下午1:53:32
 */

@Service
public class MapListenListServiceImpl implements MapListenListService {
	@Resource
	MapListenListMapper mapListenListMapper;

	@Override
	public IPageResponseModel selectByNoSync(int selectCount) {
		PageDefRequestModel paginationRequestModel = new PageDefRequestModel();
		paginationRequestModel.setOrderBy("id asc");
		paginationRequestModel.setPage(1);
		paginationRequestModel.setRows(selectCount);
		Example example = new Example(MapListenListDO.class);

		example.createCriteria().andEqualTo("syncMark", 0);

		PageInfo<MapListenListDO> selectPageList = PageUtil.selectPageList(paginationRequestModel,
				() -> mapListenListMapper.selectByExample(example));
		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(selectPageList,
				pageDefResponseModel, MapListenListVO.class);
		return pageDefResponseModel;
	}

	@Override
	public int updateByIdSetSync(List<Long> idList) {
		MapListenListDO mapListenListDO = new MapListenListDO();
		mapListenListDO.setSyncMark(1);
		Example example = new Example(MapListenListDO.class);
		example.createCriteria().andIn("id", idList);
		return mapListenListMapper.updateByExampleSelective(mapListenListDO, example);
	}

//	@Override
//	@Transactional
//	public int insertForMapAccountAuthority(int operateType, String sceneId, String authorityId) {
//
//		if (Strings.isNullOrEmpty(sceneId) || Strings.isNullOrEmpty(authorityId)) {
//			return 0;
//		}
//		return mapListenListMapper.insertForMapAccountAuthority(operateType, sceneId, authorityId);
//	}

	@Transactional
	public void insertListCascade(List<MapListenListDO> mapListenListList) {
		if (mapListenListList.size() > 0) {
			mapListenListMapper.insertList(mapListenListList);
		}
	}

//	@Override
//	@Transactional
//	public int insertForMapAuthorityScene(int operateType, String accountId, String authorityId) {
//		if (Strings.isNullOrEmpty(accountId) || Strings.isNullOrEmpty(authorityId)) {
//			return 0;
//		}
//		return mapListenListMapper.insertForMapAuthorityScene(operateType, accountId, authorityId);
//	}

}
