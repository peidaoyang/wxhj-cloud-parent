/** 
 * @fileName: SM2KeyPairUtil.java  
 * @author: pjf
 * @date: 2019年10月30日 下午4:51:38 
 */

package com.wxhj.cloud.core.utils;

import java.math.BigInteger;

import org.bouncycastle.math.ec.ECPoint;

/**
 * @className SM2KeyPairUtil.java
 * @author pjf
 * @date 2019年10月30日 下午4:51:38   
*/
/**
 * @className SM2KeyPairUtil.java
 * @author pjf
 * @date 2019年10月30日 下午4:51:38
 */

public class Sm2KeyPairUtil {
	private final ECPoint publicKey;
	private final BigInteger privateKey;

	public Sm2KeyPairUtil(ECPoint publicKey, BigInteger privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	public ECPoint getPublicKey() {
		return publicKey;
	}

	public BigInteger getPrivateKey() {
		return privateKey;
	}
}
