package com.wxhj.cloud.account;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.utils.ExcelUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

	@Resource
	FileStorageService fileStorageService;

	// @Test
	public void test3() {
		File file = new File("C:\\Users\\wxpjf\\Desktop\\pic_吕佳俊000000000191.jpg");
		try {
			byte[] fileByte = Files.readAllBytes(file.toPath());
			fileStorageService.saveFile(fileByte, "pic_吕佳俊000000000191.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test4() {
		File file = new File("C:\\Users\\wxpjf\\Desktop\\account.xlsx");
		try {
			byte[] fileByte = Files.readAllBytes(file.toPath());
			List<String[]> readExcel = ExcelUtil.readExcel(fileByte);
			System.out.println(readExcel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
