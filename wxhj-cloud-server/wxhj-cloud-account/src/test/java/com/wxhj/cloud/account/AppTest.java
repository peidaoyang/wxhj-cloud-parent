package com.wxhj.cloud.account;

import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.component.service.PaymentService;
import com.wxhj.cloud.core.utils.BeanMapUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AppTest {

    @Resource
    FileStorageService fileStorageService;
    @Resource
    PaymentService paymentService;

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
    public void test4() throws UnknownHostException {
        Map map = BeanMapUtil.beanToMap(new a(), false);

        log.error(map.toString());
//        log.error(
//        InetAddress.getLocalHost().getHostAddress());

        // qrCodePaymentService.wechatQrCodePayment();
    }

    @Data
    class a extends b {
        public a() {
            str = "1";
            str1 = "2";
        }

        private String str;
        private String str1;


    }

    @Data
    class b {
        private String c = "str3";

    }


}
