/**
 * 
 */
package com.wxhj.cloud.business;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.business.controller.attenance.AttendanceDayController;

/**
 * @ClassName: AttendanceDayControllerPublicTest.java
 * @author: cya
 * @Date: 2020年3月5日 下午1:06:38 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AttendanceDayControllerPublicTest {
	@Resource
	AttendanceDayController attendanceDayController;
	
	@Test
	public void search() throws Exception{
		
	}
}
