/** 
 * @fileName: RestartableInputStream.java  
 * @author: pjf
 * @date: 2020年2月28日 上午9:12:36 
 */

package com.wxhj.cloud.baidu.internal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @className RestartableInputStream.java
 * @author pjf
 * @date 2020年2月28日 上午9:12:36   
*/
/** 
 * @className RestartableInputStream.java
 * @author pjf
 * @date 2020年2月28日 上午9:12:36 
*/


public abstract class RestartableInputStream extends InputStream {
   public abstract void restart();

   public static RestartableInputStream wrap(byte[] b) {
       ByteArrayInputStream input = new ByteArrayInputStream(b);
       input.mark(b.length);
       return new RestartableResettableInputStream(input);
   }
}
