/**
 * @className EntranceDayAspect.java
 * @author jwl
 * @date 2020年1月10日 下午12:01:51
 */
package com.wxhj.cloud.business.aspect;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.business.domain.EntranceDayRecDO;
import com.wxhj.cloud.business.service.EntranceDayRecService;

/**
 * @className EntranceDayAspect.java
 * @author jwl
 * @date 2020年1月10日 下午12:01:51
 */
@Aspect
@Component
public class EntranceDayAspect {

	@Resource
	EntranceDayRecService entranceDayRecService;

	@Pointcut("execution(public String com.wxhj.cloud.business.service.EntranceDayService.insertCascade(..))")
	public void entranceInsertCut() {

	}
	@Pointcut("execution(public void com.wxhj.cloud.business.service.EntranceDayService.updateCascade(..))")
	public void entranceUpdateCut() {

	}

	@AfterReturning(returning = "rObject", value = "entranceInsertCut()")
	public void entranceInsert(JoinPoint joinPoint, Object rObject) {
		Object[] args = joinPoint.getArgs();
		String id = (String) rObject;
		List<EntranceDayRecDO> entranceDayRecList = (List<EntranceDayRecDO>) args[1];
		entranceDayRecList = entranceDayRecList.stream().map(q -> {
			q.setEntranceId(id);
			return q;
		}).collect(Collectors.toList());
		entranceDayRecService.insert(entranceDayRecList);
	}
	
	
	@After("entranceUpdateCut()")
	public void entranceUpdate(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		List<EntranceDayRecDO> entranceDayRecList = (List<EntranceDayRecDO>) args[1];
		if(entranceDayRecList.size()>0)
		{
			entranceDayRecService.delete(entranceDayRecList.get(0).getEntranceId());
			entranceDayRecService.insert(entranceDayRecList);
		}
	}

}