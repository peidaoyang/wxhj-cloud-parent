/** 
 * @fileName: RideRecordListence.java  
 * @author: pjf
 * @date: 2020年2月10日 下午1:54:19 
 */

package com.wxhj.cloud.business.listener;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.rocketmq.common.message.MessageExt;
import org.dozer.DozerBeanMapper;

import com.alibaba.fastjson.JSON;
import com.wxhj.cloud.business.bo.RideInfoBO;
import com.wxhj.cloud.business.domain.RideInfoDO;
import com.wxhj.cloud.business.domain.view.ViewFlightRouteDO;
import com.wxhj.cloud.business.service.shuttleBus.RideInfoService;
import com.wxhj.cloud.business.service.shuttleBus.ViewFlightRouteService;
import com.wxhj.cloud.core.statics.RocketMqTopicStaticClass;
import com.wxhj.cloud.rocketmq.RocketMqListenDoWorkHandle;
import com.wxhj.cloud.rocketmq.annotation.RocketMqConsumerListenAnnotation;

/**
 * @className RideRecordListence.java
 * @author pjf
 * @date 2020年2月10日 下午1:54:19
 */

@RocketMqConsumerListenAnnotation(topic = RocketMqTopicStaticClass.RIDE_TOPIC, rocketGroup = "businessServerRideGroup")
public class RideRecordListence implements RocketMqListenDoWorkHandle {
	@Resource
	RideInfoService rideInfoService;
	@Resource
	ViewFlightRouteService viewFlightRouteService;
	@Resource
	DozerBeanMapper dozerBeanMapper;

	@Override
	public void dataHandle(MessageExt messageExt) {
		String bodyStr = new String(messageExt.getBody());
		RideInfoBO rideInfoTemp = JSON.parseObject(bodyStr, RideInfoBO.class);

		Calendar cal = Calendar.getInstance();
		cal.setTime(rideInfoTemp.getRideTime());
		//
		Integer minuteTime = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
		ViewFlightRouteDO viewFlightRoute = viewFlightRouteService.select(rideInfoTemp.getRouteNumber(),
				rideInfoTemp.getCarNumber(), rideInfoTemp.getOrganizeId(), minuteTime);
		RideInfoDO rideInfo = dozerBeanMapper.map(rideInfoTemp, RideInfoDO.class);
//		rideInfo.setCreatorTime(new Date());
		if (viewFlightRoute != null) {
			rideInfo.setFlightId(viewFlightRoute.getId());
			rideInfo.setRouteName(viewFlightRoute.getRouteName());
			rideInfo.setStartSite(viewFlightRoute.getStartSite());
			rideInfo.setEndSite(viewFlightRoute.getEndSite());
			rideInfo.setChannelSite(viewFlightRoute.getChannelSite());
		}
		rideInfoService.insert(rideInfo);
	}
}
