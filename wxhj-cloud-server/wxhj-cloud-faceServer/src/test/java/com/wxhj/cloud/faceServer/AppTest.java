package com.wxhj.cloud.faceServer;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wxhj.cloud.faceServer.handle.CacheSyncFaceChangeHandle;
import com.wxhj.cloud.faceServer.handle.FaceChangeSynchHandle;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

	@Resource
	FaceChangeSynchHandle faceChangeSynchHandle;
	@Resource
	CacheSyncFaceChangeHandle cacheSyncFaceChangeHandle;

	@Test
	public void test1() {
		faceChangeSynchHandle.execute();
	}

	 //@Test
	public void test2() {
		cacheSyncFaceChangeHandle.execute();
	}

}
