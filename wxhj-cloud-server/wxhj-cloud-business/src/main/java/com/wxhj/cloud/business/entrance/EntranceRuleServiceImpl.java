package com.wxhj.cloud.business.entrance;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.wxhj.cloud.business.bo.EntranceDayBO;
import com.wxhj.cloud.business.bo.EntranceDayRecBO;
import com.wxhj.cloud.business.bo.EntranceGroupBO;
import com.wxhj.cloud.business.bo.EntranceGroupRecBO;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.wxhj.cloud.business.domain.EntranceDayDO;
import com.wxhj.cloud.business.domain.EntranceGroupDO;
import com.wxhj.cloud.business.domain.EntranceGroupRecDO;
import com.wxhj.cloud.business.service.EntranceDayRecService;
import com.wxhj.cloud.business.service.EntranceDayService;
import com.wxhj.cloud.business.service.EntranceGroupRecService;

@Service
public class EntranceRuleServiceImpl implements EntranceRuleService {
	@Resource
	EntranceGroupRecService entranceGroupRecService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	EntranceDayService entranceDayService;
	@Resource
	EntranceDayRecService entranceDayRecService;

	@Override
	public EntranceGroupBO createMapEntranceAuthorize(EntranceGroupDO entranceGroupTemp) {
		List<EntranceGroupRecDO> entranceGroupRecListTemp = entranceGroupRecService.listById(entranceGroupTemp.getId());
		EntranceGroupBO entranceGroup = dozerBeanMapper.map(entranceGroupTemp, EntranceGroupBO.class);
		entranceGroup.setEntranceGroupRecList(entranceGroupRecListTemp.stream()
				.map(q -> dozerBeanMapper.map(q, EntranceGroupRecBO.class)).collect(Collectors.toList()));

		//
		List<String> entranceDayIdList = entranceGroupRecListTemp.stream().map(q -> q.getEntranceDayId()).distinct()
				.collect(Collectors.toList());
		List<EntranceDayDO> entranceDayListTemp = entranceDayService.listByIdList(entranceDayIdList);

		List<EntranceDayBO> entranceDayList = entranceDayListTemp.stream().map(q -> {
			EntranceDayBO entranceDayTemp = dozerBeanMapper.map(q, EntranceDayBO.class);
			entranceDayTemp.setEntranceDayRecList(entranceDayRecService.listById(entranceDayTemp.getId()).stream()
					.map(p -> dozerBeanMapper.map(p, EntranceDayRecBO.class)).collect(Collectors.toList()));
			return entranceDayTemp;
		}).collect(Collectors.toList());

		entranceGroup.setEntranceDayList(entranceDayList);
		return entranceGroup;
	}

}
