package com.wxhj.cloud.account;

import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import com.google.common.io.MoreFiles;
import com.google.common.io.RecursiveDeleteOption;
import com.wxhj.cloud.component.service.FaceImageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AppTest {
    @Resource
    FaceImageService faceImageService;

    @Test
    public void test3() throws IOException, IllegalAccessException {

        String path = "C:\\Users\\wxpjf\\Desktop\\3ef27e45-ab44-4b81-8bd5-32cfdc67a849.jpg";

        ByteSource byteSource = Files.asByteSource(new File(path));

        faceImageService.faceMonitor(byteSource.read());
    }

    // @Test
    public void test4() throws IOException {
//        String abcA = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE).convert("abcA");
//        System.out.println(abcA);
//        String user_name = CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL).convert("user_name");
//        System.out.println(user_name);


        File file = new File("C:\\Users\\wxpjf\\Desktop\\ac");
        MoreFiles.deleteRecursively(Paths.get(file.toURI()), RecursiveDeleteOption.ALLOW_INSECURE);


    }


}
