package com.wxhj.cloud.account;

import com.wxhj.cloud.component.service.FaceImageService;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AppTest {

    @Resource
    FileStorageService fileStorageService;
    @Resource
    FaceImageService faceImageService;

    @Test
    public void test3() {


        File file = new File("C:\\Users\\wxpjf\\Desktop\\13115217.jpg");
        try {
            byte[] fileByte = Files.readAllBytes(file.toPath());
            //fileStorageService.saveFile(fileByte, "pic_吕佳俊000000000191.jpg");

            boolean monitor = faceImageService.faceMonitor(fileByte);
            // log.error(monitor.t);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //@Test
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
