/** 
 * @fileName: AttendanceDataAcpet.java  
 * @author: pjf
 * @date: 2019年12月23日 上午9:14:51 
 */

package com.wxhj.cloud.business.aspect;

import com.github.dozermapper.core.Mapper;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.OrganizeYearScheduleDO;
import com.wxhj.cloud.business.domain.OrganizeYearScheduleRecDO;
import com.wxhj.cloud.business.service.OrganizeYearScheduleRecService;
import com.wxhj.cloud.feignClient.business.vo.OrganizeYearScheduleRecVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className AttendanceDataAcpet.java
 * @author pjf
 * @date 2019年12月23日 上午9:14:51   
*/

/**
 * @className AttendanceDataAcpet.java
 * @author pjf
 * @date 2019年12月23日 上午9:14:51
 */
@Aspect
@Component
public class OrganizeYearScheduleAspect {

	@Resource
	Mapper dozerBeanMapper;
	@Resource
	OrganizeYearScheduleRecService organizeYearScheduleRecService;

	@Pointcut("execution(* com.wxhj.cloud.business.service.impl.OrganizeYearScheduleServiceImpl.submitOrganizeYearScheduleCascade(..))")
	public void attendanceInsertCut() {
	}

	@After("attendanceInsertCut()")
	public void update(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		OrganizeYearScheduleDO organizeYearScheduleDO = (OrganizeYearScheduleDO) args[0];
		String id = organizeYearScheduleDO.getId();
		List<OrganizeYearScheduleRecVO> list = (List<OrganizeYearScheduleRecVO>) args[1];
		List<OrganizeYearScheduleRecDO> collect = list.stream().map(q -> {
			OrganizeYearScheduleRecDO yearScheduleRecDO = dozerBeanMapper.map(q, OrganizeYearScheduleRecDO.class);
			yearScheduleRecDO.setOrganizeYearScheduleId(id);
			return yearScheduleRecDO;
		}).collect(Collectors.toList());
		if (!Strings.isNullOrEmpty(id)) {
			organizeYearScheduleRecService.delete(id);
		}
		organizeYearScheduleRecService.insertBatch(collect);
	}
}
