/**
 * @fileName: AliFlatMessageFactory.java
 * @author: pjf
 * @date: 2019年12月3日 上午8:54:58
 */

package com.wxhj.cloud.core.factory;


import com.wxhj.cloud.core.model.AliFlatMessage;
import com.wxhj.cloud.core.statics.AliFlatTypeStaticClass;
import com.wxhj.cloud.core.statics.LocalDateTimeStaticClass;

/**
 * @className AliFlatMessageFactory.java
 * @author pjf
 * @date 2019年12月3日 上午8:54:58   
 */

/**
 * @className AliFlatMessageFactory.java
 * @author pjf
 * @date 2019年12月3日 上午8:54:58
 */

public class AliFlatMessageFactory {

    public static AliFlatMessage AliFlatMessageRebuild(String dataSourceName, String tableName) {
        AliFlatMessage aliFlatMessage = new AliFlatMessage();
        aliFlatMessage.setDatabase(dataSourceName);
        aliFlatMessage.setTable(tableName);
        Long es =LocalDateTimeStaticClass.getTimestamp();
           //     LocalDateTime.now().toEpochSecond(LocalDateTimeStaticClass.LOCAL_ZONE_OFF);
        aliFlatMessage.setEs(es);
        aliFlatMessage.setTs(es);
        aliFlatMessage.setType(AliFlatTypeStaticClass.DB_REBUILD);
        return aliFlatMessage;
    }

//    public static void main(String[] args) {
//        long l = LocalDateTime.now().toEpochSecond(LocalDateTimeStaticClass.LOCAL_ZONE_OFF);
//        System.out.println(l);
//    }
}
