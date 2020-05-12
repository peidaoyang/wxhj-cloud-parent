package com.wxhj.cloud.core.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author wxpjf
 * @date 2020/5/6 11:09
 */

@Slf4j
public class DistributedLock implements Lock {
    private static final String ROOT_LOCK = "/locks";
    private static final String SUFFIX_LOCK = "/locktemp";

    private Object lockObj = new Object();

    private CuratorFramework curatorFramework;
    // 竞争的资源
    private String lockName;
    private String nodePath;
    private String nodePathSuffix;
    private PathChildrenCache pathChildrenCache;

    private void createPermanentNode(String path) throws Exception {
        if (curatorFramework.checkExists().forPath(path) == null) {
            curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(path);
        }
    }

    public DistributedLock(CuratorFramework curatorFramework, String lockName) throws Exception {
        this.curatorFramework = curatorFramework;
        this.lockName = lockName;
        this.nodePath = ROOT_LOCK.concat("/").concat(this.lockName);
        this.nodePathSuffix = nodePath.concat(SUFFIX_LOCK);
        createPermanentNode(ROOT_LOCK);
        createPermanentNode(nodePath);
    }

    @Override
    public void lock() {
        try {
            lockInterruptibly();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

    private void addWatcher() throws Exception {
        this.pathChildrenCache = new PathChildrenCache(curatorFramework, nodePath, false);
        pathChildrenCache.getListenable().addListener((client, event) -> {
            if (Objects.equals(event.getType(), PathChildrenCacheEvent.Type.CHILD_REMOVED)
                    && Objects.equals(event.getData().getPath(), nodePathSuffix)) {
                System.out.println("触发监听，重新争夺" + Thread.currentThread().getName());
                synchronized (lockObj) {
                    lockObj.notifyAll();
                }
            }
        });
        this.pathChildrenCache.start();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        if (!tryLock(0, TimeUnit.MILLISECONDS)) {
            throw new RuntimeException("获取锁失败");
        }
    }


    @Override
    public boolean tryLock() {
        try {
            curatorFramework.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(nodePathSuffix);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        long milliseconds = 0;
        if (time > 0) {
            milliseconds = unit.toMillis(time);
        }
        long endTime = System.currentTimeMillis() + milliseconds;
        //
        while (true) {
            if (milliseconds < 0 && time != 0) {
                return false;
            }
            //
            if (tryLock()) {
                Optional.ofNullable(this.pathChildrenCache).ifPresent(q -> {
                    try {
                        q.close();
                        this.pathChildrenCache = null;
                        System.out.println("释放监听" + Thread.currentThread().getName());
                    } catch (IOException e) {
                        log.error("zk监听关闭失败" + Thread.currentThread().getName());
                    }
                });
                break;
            } else {
                System.out.println("争夺失败，添加监听" + Thread.currentThread().getName());
                synchronized (lockObj) {
                    try {
                        if (this.pathChildrenCache == null) {
                            addWatcher();
                        }
                        if (time == 0) {
                            lockObj.wait();
                        } else {
                            lockObj.wait(milliseconds);
                        }
                        milliseconds = endTime - System.currentTimeMillis();
                    } catch (Exception e) {
                        log.error("创建监听失败等待重试" + e.getMessage());
                        Thread.sleep(10);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void unlock() {
        try {
            System.out.println("释放锁 " + Thread.currentThread().getName());
            curatorFramework.delete().forPath(nodePathSuffix);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
