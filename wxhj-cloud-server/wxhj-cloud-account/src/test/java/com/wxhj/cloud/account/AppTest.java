package com.wxhj.cloud.account;

import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import com.wxhj.cloud.account.domain.es.TestDO;
import com.wxhj.cloud.account.domain.es.TestMapper;
import com.wxhj.cloud.component.service.FaceImageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AppTest {
    @Resource
    FaceImageService faceImageService;

     @Test
    public void test3() throws IOException, IllegalAccessException {

        String path = "C:\\Users\\wxpjf\\Desktop\\70691b36-b912-4a33-a5d5-780eb74d8bf1.jpg";

        ByteSource byteSource = Files.asByteSource(new File(path));

        faceImageService.faceMonitor(byteSource.read());
    }

    @Resource
    TestMapper testMapper;

    @Test
    public void test4() throws IOException {
        TestDO testdo=new TestDO();
        testdo.putId("3");
        testdo.setA("2");
        //testdo.setB("6");
        testMapper.upsert(testdo);
//        String abcA = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE).convert("abcA");
//        System.out.println(abcA);
//        String user_name = CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL).convert("user_name");
//        System.out.println(user_name);


        // File file = new File("C:\\Users\\wxpjf\\Desktop\\ac");
        //  MoreFiles.deleteRecursively(Paths.get(file.toURI()), RecursiveDeleteOption.ALLOW_INSECURE);


    }


}