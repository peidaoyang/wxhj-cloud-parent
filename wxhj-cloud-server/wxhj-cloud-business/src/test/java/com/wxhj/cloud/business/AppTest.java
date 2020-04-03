package com.wxhj.cloud.business;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wxhj.cloud.business.listener.AttendanceRecordListener;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AppTest {

	@Resource
	AttendanceRecordListener attendanceRecordListener;

	@Test
	public void test6() {
		String str="{\"recordDatetime\":\"2020-03-03 14:04:01\",\"orderNumber\":\"82012345_5\",\"serialNumber\":5,\"accountId\":\"0000000084\",\"sceneId\":\"2ad65997-0279-49f3-aec8-b425f97e6cab\",\"deviceId\":\"82012345\",\"organizeId\":\"29eec925-a8cd-4e58-af61-5b2347186df8\"}";
		MessageExt messageExt=new MessageExt();
		try {
			messageExt.setBody(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		attendanceRecordListener.dataHandle(messageExt);
	}

}
