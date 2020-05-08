package com.wxhj.cloud.core.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author wxpjf
 * @date 2020/5/6 11:09
 */


//@Component

//@NoArgsConstructor
@Slf4j
public class DistributedLock implements Lock {
    private static final String ROOT_LOCK = "/locks";

    // private static final String LOCK_SPLIT_STR = "_lock_";
    // 等待的前一个锁
    //   private String WAIT_LOCK;
    // 当前锁
    //   private String CURRENT_LOCK;

    private CountDownLatch countDownLatch = new CountDownLatch(1);
    //
    private CuratorFramework curatorFramework;
    // 竞争的资源
    private String lockName;
    // private int sessionTimeout = 30000;

    private String nodePath() {

        return ROOT_LOCK.concat("/").concat(lockName);
    }


    public DistributedLock(CuratorFramework curatorFramework, String lockName) throws Exception {
        this.curatorFramework = curatorFramework;
        this.lockName = lockName;
        if (curatorFramework.checkExists().forPath(ROOT_LOCK) == null) {
            curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(ROOT_LOCK);
        }
    }

    //争夺锁
    private void createNode() throws Exception {
        curatorFramework.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath(this.nodePath());
    }

    //private void addWatcher()
    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {


        while (true) {
            try {
                createNode();
                break;
            } catch (Exception ex) {

            }
        }
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public void unlock() {
        try {
            // System.out.println("释放锁 " + CURRENT_LOCK);
            curatorFramework.delete().forPath(this.nodePath());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
