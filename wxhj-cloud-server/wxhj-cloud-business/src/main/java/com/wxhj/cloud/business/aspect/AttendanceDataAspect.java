/** 
 * @fileName: AttendanceDataAcpet.java  
 * @author: pjf
 * @date: 2019年12月23日 上午9:14:51 
 */

package com.wxhj.cloud.business.aspect;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.business.config.AttendanceExtentTypeEnum;
import com.wxhj.cloud.business.domain.AttendanceDataDO;
import com.wxhj.cloud.business.domain.AttendanceDataMatchingDO;
import com.wxhj.cloud.business.service.AttendanceDataMatchingService;

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
public class AttendanceDataAspect {

	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	AttendanceDataMatchingService attendanceDataMatchingService;

	@Pointcut("execution(public void com.wxhj.cloud.business.service.AttendanceDataService.insertCascade(..))")
	public void attendanceInsertCut() {

	}

	@After("attendanceInsertCut()")
	public void update(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		AttendanceDataDO attendanceData = (AttendanceDataDO) args[0];
		AttendanceDataMatchingDO attendanceDataMatching = dozerBeanMapper.map(attendanceData,
				AttendanceDataMatchingDO.class);
		// attendanceDataMatching.setMatchingDate(new
		// java.sql.Date(attendanceData.getMatchingDate().getTime()));
		AttendanceDataMatchingDO attendanceDataMatchingCondition = new AttendanceDataMatchingDO();
		attendanceDataMatchingCondition.setMatchingDate(attendanceDataMatching.getMatchingDate());
		attendanceDataMatchingCondition.setAccountId(attendanceDataMatching.getAccountId());
		attendanceDataMatchingCondition.setAttendanceId(attendanceDataMatching.getAttendanceId());
		attendanceDataMatchingCondition.setAttendanceSequence(attendanceDataMatching.getAttendanceSequence());
		// attendanceDataMatchingCondition.set
		List<AttendanceDataMatchingDO> attendanceDataMatchingList = attendanceDataMatchingService
				.list(attendanceDataMatchingCondition);
		if (attendanceDataMatching.getExtentType() > AttendanceExtentTypeEnum.NO_MATCH.getExtentType()
				&& attendanceDataMatching.getExtentType() <= AttendanceExtentTypeEnum.MATCH_PART3.getExtentType()) {
			Optional<AttendanceDataMatchingDO> attendanceDataMatchingOptional = attendanceDataMatchingList.stream()
					.filter(q -> q.getUpDownMark() == 0).findFirst();
			if (attendanceDataMatchingOptional.isPresent()) {
				if (attendanceDataMatchingOptional.get().getRecordTimeStamp() > attendanceDataMatching
						.getRecordTimeStamp()) {
					attendanceDataMatchingService
							.deleteByOrderNumber(attendanceDataMatchingOptional.get().getOrderNumber());
					attendanceDataMatching.setUpDownMark(0);
					attendanceDataMatchingService.insert(attendanceDataMatching);
					return;
				}
			} else {
				attendanceDataMatching.setUpDownMark(0);
				attendanceDataMatchingService.insert(attendanceDataMatching);
				return;
			}
		}
		if (attendanceDataMatching.getExtentType() >= AttendanceExtentTypeEnum.MATCH_PART3.getExtentType()
				&& attendanceDataMatching.getExtentType() <= AttendanceExtentTypeEnum.MATCH_PART5.getExtentType()) {
			Optional<AttendanceDataMatchingDO> attendanceDataMatchingOptional = attendanceDataMatchingList.stream()
					.filter(q -> q.getUpDownMark() == 1).findFirst();
			if (attendanceDataMatchingOptional.isPresent()) {
				if (attendanceDataMatchingOptional.get().getRecordTimeStamp() < attendanceDataMatching
						.getRecordTimeStamp()) {
					//
					attendanceDataMatchingService
							.deleteByOrderNumber(attendanceDataMatchingOptional.get().getOrderNumber());
					//
					attendanceDataMatching.setUpDownMark(1);
					attendanceDataMatchingService.insert(attendanceDataMatching);
					return;
				}
			} else {
				attendanceDataMatching.setUpDownMark(1);
				attendanceDataMatchingService.insert(attendanceDataMatching);
				return;
			}
		}
	}
}
