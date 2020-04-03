/** 
 * @fileName: AbstractBceResponse.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:24:40 
 */

package com.wxhj.cloud.baidu.model;

import java.io.Serializable;

import com.wxhj.cloud.baidu.BceResponseMetadata;

/**
 * @className AbstractBceResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:24:40   
*/
/** 
 * @className AbstractBceResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:24:40 
*/

public class AbstractBceResponse implements Serializable {

    protected BceResponseMetadata metadata = new BceResponseMetadata();

    public BceResponseMetadata getMetadata() {
        return this.metadata;
    }
}