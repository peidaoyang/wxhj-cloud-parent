/**
 * @fileName: AttendanceDayRecService.java
 * @author: pjf
 * @date: 2019年12月12日 上午11:01:28
 */

package com.wxhj.cloud.business.service;

import com.wxhj.cloud.business.domain.AttendanceDayRecDO;

import java.util.List;

/**
 * @author pjf
 * @className AttendanceDayRecService.java
 * @date 2019年12月12日 上午11:01:28
 */

public interface AttendanceDayRecService {
    void insertList(List<AttendanceDayRecDO> attendanceDayRecList);

    void insert(AttendanceDayRecDO attendanceDayRec);

    //void updateList(List<AttendanceDayRecBO> attendanceDayRecList);

    void update(AttendanceDayRecDO attendanceDayRec);

    void delete(String attendanceId);

    //IPageResponseModel listAttendanceDayRec(IPageRequestModel pageRequestModel, String attendanceId);

    List<AttendanceDayRecDO> listById(String id);


    List<AttendanceDayRecDO> listByIdList(List<String> idList);

    //List<AttendanceDayRecDO> listAttendanceDayRecById(String Id);
}
