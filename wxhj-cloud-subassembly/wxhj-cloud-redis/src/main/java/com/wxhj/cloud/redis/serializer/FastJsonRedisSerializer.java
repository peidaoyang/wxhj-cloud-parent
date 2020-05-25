/**
 * @fileName: FastJsonRedisSerializer.java
 * @author: pjf
 * @date: 2019年10月9日 下午4:39:57
 */

package com.wxhj.cloud.redis.serializer;

import com.google.common.base.Charsets;
import com.wxhj.cloud.core.utils.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author pjf
 * @className FastJsonRedisSerializer.java
 * @date 2019年10月9日 下午4:39:57
 */

public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
    //public static final Charset DEFAULT_CHARSET = Charsets.UTF_8;
    private Class<T> clazz;

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
        //this.clazz = getTClass();
        //System.out.println(this.clazz);
    }

//	private Class<T> getTClass() {
//		// 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
//		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
//		// 返回表示此类型实际类型参数的 Type 对象的数组()，想要获取第二个泛型的Class，所以索引写1
//		return (Class<T>) type.getActualTypeArguments()[0];// <T>
//	}

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (null == t) {
            return new byte[0];
        }
        //  return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
        return JSON.toJSONString(t).getBytes(Charsets.UTF_8);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, Charsets.UTF_8);
        return (T) JSON.parseObject(str, clazz);
    }
}
