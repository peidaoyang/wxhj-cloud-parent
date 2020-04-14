/**
 * @fileName: SummaryAttendanceHandle.java
 * @author: pjf
 * @date: 2019年12月26日 下午3:19:22
 */

package com.wxhj.cloud.business.handle;

import com.wxhj.cloud.component.job.AbstractAsynJobHandle;
import org.springframework.stereotype.Component;

/**
 * @className SummaryAttendanceHandle.java
 * @author pjf
 * @date 2019年12月26日 下午3:19:22
 */

@Component
public class SummaryAttendanceHandle extends AbstractAsynJobHandle {

    //@Resource
    //NowAttendanceAccountService nowAttendanceAccountService;

    @Override
    public boolean execute() {
        //nowAttendanceAccountService.insertDef();


        return true;
    }

    @Override
    public void destroy() {
    }
}
