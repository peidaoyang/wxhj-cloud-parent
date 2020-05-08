/** 
 * @fileName: MapListenListServiceImpl.java  
 * @author: pjf
 * @date: 2019年10月31日 下午1:53:32 
 */

package com.wxhj.cloud.account.service.impl;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.MapListenListDO;
import com.wxhj.cloud.account.mapper.MapListenListMapper;
import com.wxhj.cloud.account.service.MapListenListService;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefRequestModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

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
	public PageInfo<MapListenListDO> selectByNoSync(int selectCount) {
		PageDefRequestModel paginationRequestModel = new PageDefRequestModel();
		paginationRequestModel.setOrderBy("id asc");
		paginationRequestModel.setPage(1);
		paginationRequestModel.setRows(selectCount);
		Example example = new Example(MapListenListDO.class);

		example.createCriteria().andEqualTo("syncMark", 0);

		PageInfo<MapListenListDO> selectPageList = PageUtil.selectPageList(paginationRequestModel,
				() -> mapListenListMapper.selectByExample(example));
//		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
//		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(selectPageList,
//				pageDefResponseModel);
		return selectPageList;
	}

	@Override
	public int updateByIdSetSync(List<Long> idList) {
		MapListenListDO mapListenListDO = new MapListenListDO();
		mapListenListDO.setSyncMark(1);
		Example example = new Example(MapListenListDO.class);
		example.createCriteria().andIn("id", idList);
		return mapListenListMapper.updateByExampleSelective(mapListenListDO, example);
	}

	@Override
	public int selectBySceneIdList(String sceneId) {
		Example example = new Example(MapListenListDO.class);
		example.createCriteria().andEqualTo("sceneId",sceneId);
		return mapListenListMapper.selectCountByExample(example);
	}


	@Override
	@Transactional
	public void insertListCascade(List<MapListenListDO> mapListenListList) {
		if (mapListenListList.size() > 0) {
			mapListenListMapper.insertList(mapListenListList);
		}
	}
}
