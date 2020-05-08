package com.wxhj.cloud.core.lock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

/**
 * @author wxpjf
 * @date 2020/5/6 11:09
 */


//@Component

//@NoArgsConstructor
@Slf4j
public class DistributedLock implements Lock {
    private static final String ROOT_LOCK = "/locks";

    private static final String LOCK_SPLIT_STR = "_lock_";
    // 等待的前一个锁
    private String WAIT_LOCK;
    // 当前锁
    private String CURRENT_LOCK;

    private CountDownLatch countDownLatch;
    //
    private CuratorFramework curatorFramework;
    // 竞争的资源
    private String lockName;
    // private int sessionTimeout = 30000;

    private String nodePath() {

        return ROOT_LOCK.concat("/").concat(lockName).concat(LOCK_SPLIT_STR);
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


    private void createNode() throws Exception {
        CURRENT_LOCK = curatorFramework.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath(this.nodePath());
    }

    private boolean waitForLock(String prev) throws Exception {

        curatorFramework.getData().usingWatcher(new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        });
        //
        if (curatorFramework.checkExists().forPath(ROOT_LOCK + "/" + prev) != null) {
            System.out.println(Thread.currentThread().getName() + "等待锁 " + ROOT_LOCK + "/" + prev);
            //  this.countDownLatch = new CountDownLatch(1);
            // 计数等待，若等到前一个节点消失，则precess中进行countDown，停止等待，获取锁
            //   this.countDownLatch.await(waitTime, timeUnit);
            //  this.countDownLatch = null;
        }
        return true;
    }

    @SneakyThrows
    @Override
    public void lock() {
        createNode();
        if (this.tryLock()) {
            System.out.println(Thread.currentThread().getName() + " " + lockName + "获得了锁");
            return;
        } else {
            // 等待锁
            waitForLock(WAIT_LOCK);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        this.lock();
    }

    @Override
    public boolean tryLock() {
        try {
            // 取所有子节点
            List<String> subNodes =
                    curatorFramework.
                            getChildren().forPath(ROOT_LOCK); // 避免羊群效应
            // 取出所有lockName的锁
            List<String> lockObjects = subNodes.stream().filter(q ->
                    q.split(LOCK_SPLIT_STR)[0].equals(lockName)
            ).sorted().collect(Collectors.toList());

            System.out.println("===" + lockObjects);
            System.out.println(Thread.currentThread().getName() + " 的锁是 " + CURRENT_LOCK);
            // 若当前节点为最小节点，则获取锁成功
            if (CURRENT_LOCK.equals(ROOT_LOCK + "/" + lockObjects.get(0))) {
                return true;
            }
            // 若不是最小节点，则找到自己的前一个节点
            String prevNode = CURRENT_LOCK.substring(CURRENT_LOCK.lastIndexOf("/") + 1);
            WAIT_LOCK = lockObjects.get(Collections.binarySearch(lockObjects, prevNode) - 1);
            //添加节点监听
            PathChildrenCache cache = new PathChildrenCache(curatorFramework, WAIT_LOCK, false);
            cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            cache.getListenable().addListener((client, event) -> {
                if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                    String oldPath = event.getData().getPath();
                    log.info("success to release lock for path:{}", oldPath);
                    //if (oldPath.contains(path)) {
                        //释放计数器，让当前的请求获取锁
                        countDownLatch.countDown();
                   // }
                }
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        try {
            if (this.tryLock()) {
                return true;
            }
            return waitForLock(WAIT_LOCK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void unlock() {
        try {
            System.out.println("释放锁 " + CURRENT_LOCK);
            curatorFramework.delete().forPath(CURRENT_LOCK);
            CURRENT_LOCK = null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
