package com.wxhj.cloud.account;

import com.google.common.base.CaseFormat;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.component.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AppTest {

//    @Resource
//    FileStorageService fileStorageService;
//    @Resource
//    PaymentService paymentService;

    // @Test
    public void test3() {
//        File file = new File("C:\\Users\\wxpjf\\Desktop\\pic_吕佳俊000000000191.jpg");
//        try {
//            byte[] fileByte = Files.readAllBytes(file.toPath());
//            fileStorageService.saveFile(fileByte, "pic_吕佳俊000000000191.jpg");
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }

    @Test
    public void test4() {
        String abcA = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE).convert("abcA");
        System.out.println(abcA);
        String user_name = CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL).convert("user_name");
        System.out.println(user_name);
    }

}
