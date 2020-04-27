package com.wxhj.cloud.account;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.io.MoreFiles;
import com.google.common.io.RecursiveDeleteOption;
import com.wxhj.cloud.account.domain.es.TestMapper;
import com.wxhj.cloud.account.domain.es.testDO;
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

    //    @Resource
//    FileStorageService fileStorageService;
//    @Resource
//    PaymentService paymentService;

    @Resource
    TestMapper testMapper;
    //BaseElasticService baseElasticService;

    @Test
    public void test3() throws IOException, IllegalAccessException {
        //String str = "////";
        testDO test = new testDO("a", "c");
        test.putId("2");


        String string = JSON.toJSONString(test);
        testMapper.replace(test);
//        testDO testDO = testMapper.selectByid("1");
//
//        List<testDO> testDOS = testMapper.listByQuery(testDO);
//
//        System.out.println(testDOS);
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
