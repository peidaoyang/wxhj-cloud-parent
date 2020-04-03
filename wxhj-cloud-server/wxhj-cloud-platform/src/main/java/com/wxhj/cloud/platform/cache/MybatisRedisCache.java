package com.wxhj.cloud.platform.cache;
import com.wxhj.cloud.platform.util.ApplicationContextHolder;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Mybatis 二级缓存
 * @author 李博
 *
 * 使用二级缓存需要开启 mybatis 二级缓存需要开启设置设置 mybatis.configuration.cache-enabled: true
 * 二级缓存适用于单表的Mapper使用,存在多表操作存在风险
 * 如果只有 Mapper 接口文件可以使用 @CacheNamespace(implementation = MybatisRedisCache.class) 注解配置
 * 如果有 Mapper xml 文件可以使用<Cache></Cache> 标签配置
 * 注解配置和标签配置不能同时使用
 */
public class MybatisRedisCache implements Cache {

    // 日志logger
    private static final Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);
    // 读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 缓存实例ID
    private final String id;
    // RedisTemplate
    private RedisTemplate redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
    // redis 缓存过期时间(分钟)
    private static final long EXPIRE_TIME_IN_MINUTES = 30;

    /**
     * 构造函数
     * @param id 缓存ID
     */
    public MybatisRedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("缓存实例 ID 不能为空");
        }
        this.id = id;
    }

    /**
     * 获取缓存ID
     * @return 缓存ID
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * 保存查询结果到 redis 中
     *
     * @param key 键
     * @param value 值
     */
    @Override
    @SuppressWarnings("unchecked")
    public void putObject(Object key, Object value) {
        // 查询数据为空不需要缓存
        if (value != null) {
            redisTemplate.opsForValue().set(key, value, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
            logger.debug("完成添加二级缓存数据, KEY: "+key.toString());
        }
    }

    /**
     * 获取redis 中查询结果
     *
     * @param key key
     * @return 查询结果
     */
    @Override
    public Object getObject(Object key) {
        try {
            if (key != null) {
                Object obj = redisTemplate.opsForValue().get(key.toString());
                logger.debug("完成获取二级缓存数据, KEY:"+key.toString());
                return obj;
            }
        } catch (Exception e) {
            logger.error("获取二级缓存数据错误:" + e.getMessage());
        }
        return null;
    }

    /**
     * 删除redis 中查询结果
     * @param key 键
     * @return 删除结果
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object removeObject(Object key) {
        try {
            if (key != null) {
                redisTemplate.delete(key.toString());
                logger.debug("完成删除二级缓存数据, KEY:"+key.toString());
            }
        } catch (Exception e) {
            logger.error("删除二级缓存数据错误:" + e.getMessage());
        }
        return null;
    }
    /**
     * 清空缓存实例
     */
    @Override
    public void clear() {
        try {
            Set<String> keys = redisTemplate.keys("*:" + this.id + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                redisTemplate.delete(keys);
            }
            logger.debug("完成清空二级缓存数据, ID:"+id);
        } catch (Exception e) {
            logger.error("清空二级缓存数据错误:" + e.getMessage());
        }
    }

    /**
     * 获取缓存数据数量
     * @return
     */
    @Override
    public int getSize() {
        Long size = (Long) redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
        return size.intValue();
    }


    /**
     * 获取读写锁
     * @return 读写锁
     */
    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

}
