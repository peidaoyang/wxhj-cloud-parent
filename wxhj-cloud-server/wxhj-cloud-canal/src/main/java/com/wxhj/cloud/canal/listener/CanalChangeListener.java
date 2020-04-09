/**
 * @fileName: CanalChangeListener.java
 * @author: pjf
 * @date: 2019年11月29日 下午3:37:31
 */

package com.wxhj.cloud.canal.listener;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.wxhj.cloud.canal.config.CanalServerConfig;
import com.wxhj.cloud.canal.config.SqlFileConfig;
import com.wxhj.cloud.canal.service.CurrencyService;
import com.wxhj.cloud.core.model.AliFlatMessage;
import com.wxhj.cloud.core.statics.AliFlatTypeStaticClass;
import com.wxhj.cloud.core.statics.FileStaticClass;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.core.statics.RocketMqTopicStaticClass;
import com.wxhj.cloud.core.utils.FileUtil;
import com.wxhj.cloud.rocketmq.RocketMqListenDoWorkHandle;
import com.wxhj.cloud.rocketmq.annotation.RocketMqConsumerListenAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author pjf
 * @className CanalChangeListener.java
 * @date 2019年11月29日 下午3:37:31
 */

@RocketMqConsumerListenAnnotation(topic = RocketMqTopicStaticClass.CANAL_TOPIC)
@Slf4j
public class CanalChangeListener implements RocketMqListenDoWorkHandle {
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    CurrencyService currencyService;

    @Resource
    CanalServerConfig canalServerConfig;
    @Resource
    SqlFileConfig sqlFileConfig;

    @Override
    public void dataHandle(MessageExt messageExt) {
        String bodyStr = "";
        try {
            bodyStr = new String(messageExt.getBody(), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            log.error(ex.getMessage());
        }
        // System.out.println(bodyStr);
        AliFlatMessage flatMessage = JSON.parseObject(bodyStr, AliFlatMessage.class);
        // String tableName =
        // flatMessage.getDatabase().concat(".").concat(flatMessage.getTable());
        //
//		if (!canalServerConfig.getTableName().contains(tableName)) {
//			return;
//		}
        flatMessage.filterLine();
        switch (flatMessage.getType()) {
            case AliFlatTypeStaticClass.DB_DELETE:
                // delete(flatMessage);
                break;
            case AliFlatTypeStaticClass.DB_INSERT:
                // insert(flatMessage);
                break;
            case AliFlatTypeStaticClass.DB_UPDATE:
                // update(flatMessage);
                break;
            default:
                recordSql(flatMessage);
                break;
        }
    }

    private void recordSql(AliFlatMessage aliFlatMessage) {
        // 获取ddl的sql语句
        String sqlString = getSqlString(aliFlatMessage);

        String filePath = sqlFileConfig.getPath() + File.separator + sqlFileConfig.getFileName();
        try {
            FileUtil.writeToFile(filePath, sqlString, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取ddl的sql语句
     *
     * @param aliFlatMessage
     * @return
     */
    private String getSqlString(AliFlatMessage aliFlatMessage) {
        String result = "";
        if (aliFlatMessage.getIsDdl()) {
            String sql = aliFlatMessage.getSql();
            // 去掉字符串中的换行符
            sql = FileUtil.replaceLineFeed(sql);
            // 在语句末尾增加封号和换行符
            result = sql + ";" + System.getProperty(FileStaticClass.LINE_SEPARATOR);
        }
        return result;
    }

    private void rebuild(AliFlatMessage flatMessage) {
        String redisKey = redisKey(flatMessage);
        if (redisTemplate.hasKey(redisKey)) {
            redisTemplate.delete(redisKey);
        }
        //
        List<String> keyList = currencyService.listTableKey(flatMessage.getDatabase(), flatMessage.getTable());
        List<Map<String, String>> tableContent = currencyService.listTableContent(flatMessage.getDatabase(),
                flatMessage.getTable());
        tableContent.forEach(q -> {
            String databaseKey = databaseNewKey(keyList, q);
            redisTemplate.opsForHash().put(redisKey, databaseKey, JSON.toJSONString(q));
        });
    }

    private void update(AliFlatMessage flatMessage) {
        String key = redisKey(flatMessage);
        for (int i = 0; i < flatMessage.getData().size(); i++) {
            Map<String, String> dataMap = flatMessage.getData().get(i);
            Map<String, String> oldDataMap = flatMessage.getOld().get(i);
            String oldPk = databaseOldKey(flatMessage.getPkNames(), dataMap, oldDataMap);
            String newPk = databaseNewKey(flatMessage.getPkNames(), dataMap);
            if (!oldPk.equals(newPk)) {
                redisTemplate.opsForHash().delete(key, oldPk);
            }
            redisTemplate.opsForHash().put(key, newPk, JSON.toJSONString(dataMap));
        }
    }

    private void delete(AliFlatMessage flatMessage) {
        String key = redisKey(flatMessage);
        flatMessage.getData().forEach(q -> {
            String databaseKey = databaseNewKey(flatMessage.getPkNames(), q);
            redisTemplate.opsForHash().delete(key, databaseKey);
        });
    }

    private void insert(AliFlatMessage flatMessage) {
        String key = redisKey(flatMessage);
        flatMessage.getData().forEach(q -> {
            String databaseKey = databaseNewKey(flatMessage.getPkNames(), q);
            redisTemplate.opsForHash().put(key, databaseKey, JSON.toJSONString(q));
        });
    }

    private String redisKey(AliFlatMessage flatMessage) {
        return RedisKeyStaticClass.TABLE_REDIS_KEY.concat(flatMessage.getDatabase()).concat("#")
                .concat(flatMessage.getTable());
    }

    private String databaseNewKey(List<String> pkNames, Map<String, String> nowData) {
        List<String> dKeyList = new ArrayList<String>();
        pkNames.forEach(q -> {
            String keyValue = nowData.get(q);
            dKeyList.add(q.concat(":").concat(keyValue));
        });
        return Joiner.on(",").join(dKeyList);
    }

    private String databaseOldKey(List<String> pkNames, Map<String, String> nowData, Map<String, String> oldData) {
        List<String> dKeyList = new ArrayList<String>();
        pkNames.forEach(q -> {
            String keyValue = null;
            if (oldData != null) {
                keyValue = oldData.get(q);
            }
            if (Strings.isNullOrEmpty(keyValue)) {
                keyValue = nowData.get(q);
            }
            dKeyList.add(q.concat(":").concat(keyValue));
        });
        return Joiner.on(",").join(dKeyList);
    }
}
