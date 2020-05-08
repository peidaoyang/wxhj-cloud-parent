package com.wxhj.cloud.account;

import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import com.wxhj.cloud.account.domain.FaceChangeDO;
import com.wxhj.cloud.account.domain.FaceChangeRecDO;
import com.wxhj.cloud.account.domain.es.TestDO;
import com.wxhj.cloud.account.domain.es.TestMapper;
import com.wxhj.cloud.account.service.FaceChangeRecService;
import com.wxhj.cloud.account.service.FaceChangeService;
import com.wxhj.cloud.component.service.FaceImageService;
import com.wxhj.cloud.core.lock.DistributedLock;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.locks.Lock;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AppTest {


    @Resource
    FaceImageService faceImageService;
    @Resource
    CuratorFramework curatorFramework;


    private static int threadTest = 0;

    @Test
    public void test2() throws Exception {
        //System.out.println(curatorFramework);

        for (int i = 0; i < 5; i++) {
            int iTemp = i;
            Thread t = new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    Lock lock = new DistributedLock(curatorFramework, "testLock");
                    for (int j = 0; j < 10; j++) {
                        try {
                            lock.lock();
                            Thread.sleep(1000);
                            System.out.print(Thread.currentThread().getName() + " ");
                            System.out.println(threadTest++);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } finally {
                            lock.unlock();
                        }
                    }

                }
            });
            t.start();
        }
        Thread.currentThread().join();
    }

    //@Test
    public void test3() throws IOException, InterruptedException {

        // String path = "C:\\Users\\wxpjf\\Desktop\\17115127.jpg";
        String path = "C:\\Users\\wxpjf\\Desktop\\电商171";
        //directory
        File file = new File(path);
        for (int i = 0; i < 10; i++) {

            Files.fileTraverser().breadthFirst(file)
                    .forEach(
                            q -> {
                                if (!q.isFile()) {
                                    return;
                                }
                                ByteSource byteSource = Files.asByteSource(q);
                                try {
                                    boolean isTrue = faceImageService.faceMonitor(byteSource.read());
                                    if (!isTrue) {
                                        System.out.println(isTrue);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    Thread.sleep(500L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
        }


//        byte[] a=new byte[100000];
//        for (int i=0;i<100000;i++)
//        {
//            a[i]= (byte) 0x41;
//
//        }
//        Stopwatch stopwatch=Stopwatch.createStarted();
//        System.out.println(Arrays.toString(a));
//        stopwatch.stop();
//        System.out.println(stopwatch.toString());
    }

    @Resource
    TestMapper testMapper;

    @Test
    public void test4() throws IOException {
        TestDO testdo = new TestDO();
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

    @Resource
    FaceChangeService faceChangeService;
    @Resource
    FaceChangeRecService faceChangeRecService;

    @Test
    public void test5() {
        List<FaceChangeDO> faceChangeDOS = faceChangeService.listAll();
        for (FaceChangeDO faceChangeTemp : faceChangeDOS) {
            List<FaceChangeRecDO> faceChangeRecDOS = faceChangeRecService.listGreaterThanIndexAndId(faceChangeTemp.getId(), -1L);
            // String redisKey = RedisKeyStaticClass.FACE_CHANGE_REDIS_KEY.concat(faceChangeTemp.getId());
            //redisKey
        }
    }
}