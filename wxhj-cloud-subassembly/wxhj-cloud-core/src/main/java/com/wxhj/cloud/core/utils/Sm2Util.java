/** 
 * @fileName: SM2Util.java  
 * @author: pjf
 * @date: 2019年10月30日 下午4:50:19 
 */

package com.wxhj.cloud.core.utils;

/**
 * @className SM2Util.java
 * @author pjf
 * @date 2019年10月30日 下午4:50:19   
*/
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Base64;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Sm2Util {
	private static BigInteger N = new BigInteger(
			"FFFFFFFE" + "FFFFFFFF" + "FFFFFFFF" + "FFFFFFFF" + "7203DF6B" + "21C6052B" + "53BBF409" + "39D54123", 16);
	private static BigInteger P = new BigInteger(
			"FFFFFFFE" + "FFFFFFFF" + "FFFFFFFF" + "FFFFFFFF" + "FFFFFFFF" + "00000000" + "FFFFFFFF" + "FFFFFFFF", 16);
	private static BigInteger A = new BigInteger(
			"FFFFFFFE" + "FFFFFFFF" + "FFFFFFFF" + "FFFFFFFF" + "FFFFFFFF" + "00000000" + "FFFFFFFF" + "FFFFFFFC", 16);
	private static BigInteger B = new BigInteger(
			"28E9FA9E" + "9D9F5E34" + "4D5A9E4B" + "CF6509A7" + "F39789F5" + "15AB8F92" + "DDBCBD41" + "4D940E93", 16);
	private static BigInteger GX = new BigInteger(
			"32C4AE2C" + "1F198119" + "5F990446" + "6A39C994" + "8FE30BBF" + "F2660BE1" + "715A4589" + "334C74C7", 16);
	private static BigInteger GY = new BigInteger(
			"BC3736A2" + "F4F6779C" + "59BDCEE3" + "6B692153" + "D0A9877C" + "C62A4740" + "02DF32E5" + "2139F0A0", 16);
	private static ECDomainParameters ECC_BC_SPEC;
	private static int W = (int) Math.ceil(N.bitLength() * 1.0 / 2) - 1;
	private static BigInteger W_2 = new BigInteger("2").pow(W);
	private static final int DIGEST_LENGTH = 32;

	private static SecureRandom RANDOM = new SecureRandom();
	private static ECCurve.Fp CURVE;
	private static ECPoint G;
	private boolean debug = false;

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	/**
	 * 以16进制打印字节数组
	 * 
	 * @param b
	 */
	public static void printHexString(byte[] b) {
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			System.out.print(hex.toUpperCase());
		}
		System.out.println();
	}

	/**
	 * 随机数生成器
	 * 
	 * @param max
	 * @return
	 */
	private static BigInteger random(BigInteger max) {

		BigInteger r = new BigInteger(256, RANDOM);
		// int count = 1;

		while (r.compareTo(max) >= 0) {
			r = new BigInteger(128, RANDOM);
			// count++;
		}

		// System.out.println("count: " + count);
		return r;
	}

	/**
	 * 判断字节数组是否全0
	 * 
	 * @param buffer
	 * @return
	 */
	private boolean allZero(byte[] buffer) {
		for (int i = 0; i < buffer.length; i++) {
			if (buffer[i] != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 公钥加密
	 * 
	 * @param input     加密原文
	 * @param publicKey 公钥
	 * @return
	 */
	public byte[] encrypt(String input, ECPoint publicKey) {

		byte[] inputBuffer = input.getBytes();
		if (debug) {
			printHexString(inputBuffer);
		}
		byte[] c1Buffer;
		ECPoint kpb;
		byte[] t;
		do {
			/* 1 产生随机数k，k属于[1, n-1] */
			BigInteger k = random(N);
			if (debug) {
				System.out.print("k: ");
				printHexString(k.toByteArray());
			}

			/* 2 计算椭圆曲线点c1 = [k]G = (x1, y1) */
			ECPoint c1 = G.multiply(k);
			c1Buffer = c1.getEncoded(false);
			if (debug) {
				System.out.print("c1: ");
				printHexString(c1Buffer);
			}

			/*
			 * 3 计算椭圆曲线点 S = [h]Pb
			 */
			BigInteger h = ECC_BC_SPEC.getH();
			if (h != null) {
				ECPoint s = publicKey.multiply(h);
				if (s.isInfinity()) {
					throw new IllegalStateException();
				}
			}

			/* 4 计算 [k]PB = (x2, y2) */
			kpb = publicKey.multiply(k).normalize();

			/* 5 计算 t = KDF(x2||y2, klen) */
			byte[] kpbBytes = kpb.getEncoded(false);
			t = kdf(kpbBytes, inputBuffer.length);

		} while (allZero(t));

		/* 6 计算C2=M^t */
		byte[] c2 = new byte[inputBuffer.length];
		for (int i = 0; i < inputBuffer.length; i++) {
			c2[i] = (byte) (inputBuffer[i] ^ t[i]);
		}

		/* 7 计算C3 = Hash(x2 || M || y2) */
		byte[] c3 = sm3hash(kpb.getXCoord().toBigInteger().toByteArray(), inputBuffer,
				kpb.getYCoord().toBigInteger().toByteArray());

		/* 8 输出密文 C=c1 || C2 || C3 */

		byte[] encryptResult = new byte[c1Buffer.length + c2.length + c3.length];

		System.arraycopy(c1Buffer, 0, encryptResult, 0, c1Buffer.length);
		System.arraycopy(c2, 0, encryptResult, c1Buffer.length, c2.length);
		System.arraycopy(c3, 0, encryptResult, c1Buffer.length + c2.length, c3.length);

		if (debug) {
			System.out.print("密文: ");
			printHexString(encryptResult);
		}

		return encryptResult;
	}

	/**
	 * 私钥解密
	 * 
	 * @param encryptData 密文数据字节数组
	 * @param privateKey  解密私钥
	 * @return
	 */
	public String decrypt(byte[] encryptData, BigInteger privateKey) {

		if (debug) {
			System.out.println("encryptData length: " + encryptData.length);
		}
		byte[] c1Byte = new byte[65];
		System.arraycopy(encryptData, 0, c1Byte, 0, c1Byte.length);

		ECPoint c1 = CURVE.decodePoint(c1Byte).normalize();

		/*
		 * 计算椭圆曲线点 S = [h]c1 是否为无穷点
		 */
		BigInteger h = ECC_BC_SPEC.getH();
		if (h != null) {
			ECPoint s = c1.multiply(h);
			if (s.isInfinity()) {
				throw new IllegalStateException();
			}
		}
		/* 计算[dB]c1 = (x2, y2) */
		ECPoint dbc1 = c1.multiply(privateKey).normalize();

		/* 计算t = KDF(x2 || y2, klen) */
		byte[] dbc1Bytes = dbc1.getEncoded(false);
		int klen = encryptData.length - 65 - DIGEST_LENGTH;
		byte[] t = kdf(dbc1Bytes, klen);

		if (allZero(t)) {
			System.err.println("all zero");
			throw new IllegalStateException();
		}

		/* 5 计算M'=C2^t */
		byte[] m = new byte[klen];
		for (int i = 0; i < m.length; i++) {
			m[i] = (byte) (encryptData[c1Byte.length + i] ^ t[i]);
		}
		if (debug) {
			printHexString(m);
		}
		/* 6 计算 u = Hash(x2 || M' || y2) 判断 u == C3是否成立 */
		byte[] c3 = new byte[DIGEST_LENGTH];

		if (debug) {
			try {
				System.out.println("M = " + new String(m, "UTF8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				log.error(e1.getMessage());
			}
		}
		System.arraycopy(encryptData, encryptData.length - DIGEST_LENGTH, c3, 0, DIGEST_LENGTH);
		byte[] u = sm3hash(dbc1.getXCoord().toBigInteger().toByteArray(), m,
				dbc1.getYCoord().toBigInteger().toByteArray());
		if (Arrays.equals(u, c3)) {
			if (debug) {
				System.out.println("解密成功");
			}
			try {
				return new String(m, "UTF8");
			} catch (UnsupportedEncodingException e) {
				log.error(e.getMessage());
			}
			return null;
		} else {
			if (debug) {
				System.out.print("u = ");
				printHexString(u);
				System.out.print("c3 = ");
				printHexString(c3);
				System.err.println("解密验证失败");
			}
			return null;
		}

	}

	/**
	 * 判断是否在范围内
	 * 
	 * @param param
	 * @param min
	 * @param max
	 * @return
	 */
	private boolean between(BigInteger param, BigInteger min, BigInteger max) {
		if (param.compareTo(min) >= 0 && param.compareTo(max) < 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断生成的公钥是否合法
	 * 
	 * @param publicKey
	 * @return
	 */
	private boolean checkPublicKey(ECPoint publicKey) {

		if (!publicKey.isInfinity()) {

			BigInteger x = publicKey.getXCoord().toBigInteger();
			BigInteger y = publicKey.getYCoord().toBigInteger();

			if (between(x, new BigInteger("0"), P) && between(y, new BigInteger("0"), P)) {

				BigInteger xResult = x.pow(3).add(A.multiply(x)).add(B).mod(P);

				if (debug) {
					System.out.println("xResult: " + xResult.toString());
				}
				BigInteger yResult = y.pow(2).mod(P);

				if (debug) {
					System.out.println("yResult: " + yResult.toString());
				}
				if (yResult.equals(xResult) && publicKey.multiply(N).isInfinity()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 生成密钥对
	 * 
	 * @return
	 */
	public Sm2KeyPairUtil generateKeyPair() {

		BigInteger d = random(N.subtract(new BigInteger("1")));

		Sm2KeyPairUtil keyPair = new Sm2KeyPairUtil(G.multiply(d).normalize(), d);

		if (checkPublicKey(keyPair.getPublicKey())) {
			if (debug) {
				System.out.println("generate key successfully");
			}
			return keyPair;
		} else {
			if (debug) {
				System.err.println("generate key failed");
			}
			return null;
		}
	}

	public Sm2Util() {
		CURVE = new ECCurve.Fp(P, 
				A, 
				B, null, null);
		G = CURVE.createPoint(GX, GY);
		ECC_BC_SPEC = new ECDomainParameters(CURVE, G, N);
	}

	public Sm2Util(boolean debug) {
		this();
		this.debug = debug;
	}

	/**
	 * 导出公钥到本地
	 * 
	 * @param publicKey
	 * @param path
	 */
	public void exportPublicKey(ECPoint publicKey, String path) {
		File file = new File(path);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			byte buffer[] = publicKey.getEncoded(false);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(buffer);
			fos.close();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * 公钥转hex
	 * 
	 * @param publicKey
	 * @param path
	 */
	public String publicKey2String(ECPoint publicKey) {

		byte buffer[] = publicKey.getEncoded(false);
		return CommUtil.bytesToHexString(buffer);
	}

	public ECPoint string2PublicKey(String hexString) {
		byte buffer[] = CommUtil.hexStringToBytes(hexString);
		return CURVE.decodePoint(buffer);
	}

	/*
	 * hex转私钥
	 */
	public BigInteger string2PrivateKey(String hexString) {
		String priks = new String(Base64.encode(hexStringToBytes(hexString)));
		return new BigInteger(Base64.decode(priks.getBytes()));
	}

	/**
	 * hexString 转btye[]
	 * 
	 * @param hexString
	 * @return btye[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));

		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * 从本地导入公钥
	 * 
	 * @param path
	 * @return
	 */
	public ECPoint importPublicKey(String path) {
		File file = new File(path);
		try {
			if (!file.exists()) {
				return null;
			}
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			byte buffer[] = new byte[16];
			int size;
			while ((size = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, size);
			}
			fis.close();
			return CURVE.decodePoint(baos.toByteArray());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 导出私钥到本地
	 * 
	 * @param privateKey
	 * @param path
	 */
	public void exportPrivateKey(BigInteger privateKey, String path) {
		File file = new File(path);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(privateKey);
			oos.close();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * 从本地导入私钥
	 * 
	 * @param path
	 * @return
	 */
	public BigInteger importPrivateKey(String path) {
		File file = new File(path);
		try {
			if (!file.exists()) {
				return null;
			}
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			BigInteger res = (BigInteger) (ois.readObject());
			ois.close();
			fis.close();
			return res;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 字节数组拼接
	 * 
	 * @param params
	 * @return
	 */
	private static byte[] join(byte[]... params) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] res = null;
		try {
			for (int i = 0; i < params.length; i++) {
				baos.write(params[i]);
			}
			res = baos.toByteArray();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return res;
	}

	/**
	 * sm3摘要
	 * 
	 * @param params
	 * @return
	 */
	private static byte[] sm3hash(byte[]... params) {
		byte[] res = null;
		try {
			res = Sm3Util.hash(join(params));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return res;
	}

	/**
	 * 取得用户标识字节数组
	 * 
	 * @param IDA
	 * @param aPublicKey
	 * @return
	 */
	private static byte[] za(String ida, ECPoint aPublicKey) {
		byte[] idaBytes = ida.getBytes();
		int entlena = idaBytes.length * 8;
		byte[] entla = new byte[] { (byte) (entlena & 0xFF00), (byte) (entlena & 0x00FF) };
		byte[] za = sm3hash(entla, idaBytes, A.toByteArray(), B.toByteArray(), GX.toByteArray(), GY.toByteArray(),
				aPublicKey.getXCoord().toBigInteger().toByteArray(),
				aPublicKey.getYCoord().toBigInteger().toByteArray());
		return za;
	}

	/**
	 * 签名
	 * 
	 * @param m       签名信息
	 * @param IDA     签名方唯一标识
	 * @param keyPair 签名方密钥对
	 * @return 签名
	 */
	public Signature sign(String m, String ida, Sm2KeyPairUtil keyPair) {
		byte[] za = za(ida, keyPair.getPublicKey());
		byte[] mByte = join(za, m.getBytes());
		BigInteger e = new BigInteger(1, sm3hash(mByte));
		BigInteger k;
		BigInteger r;
		do {
			k = random(N);
			ECPoint p1 = G.multiply(k).normalize();
			BigInteger x1 = p1.getXCoord().toBigInteger();
			r = e.add(x1);
			r = r.mod(N);
		} while (r.equals(BigInteger.ZERO) || r.add(k).equals(N));

		BigInteger s = ((keyPair.getPrivateKey().add(BigInteger.ONE).modInverse(N))
				.multiply((k.subtract(r.multiply(keyPair.getPrivateKey()))).mod(N))).mod(N);

		return new Signature(r, s);
	}

	/**
	 * 验签
	 * 
	 * @param m          签名信息
	 * @param signature  签名
	 * @param ida        签名方唯一标识
	 * @param aPublicKey 签名方公钥
	 * @return true or false
	 */
	public boolean verify(String m, Signature signature, String ida, ECPoint aPublicKey) {
		if (!between(signature.r, BigInteger.ONE, N)) {
			return false;
		}
		if (!between(signature.s, BigInteger.ONE, N)) {
			return false;
		}
		byte[] mByte = null;
		try {
			mByte = join(za(ida, aPublicKey), m.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			log.error(e1.getMessage());
		}
		BigInteger e = new BigInteger(1, sm3hash(mByte));
		BigInteger t = signature.r.add(signature.s).mod(N);

		if (t.equals(BigInteger.ZERO)) {
			return false;
		}
		ECPoint p1 = G.multiply(signature.s).normalize();
		ECPoint p2 = aPublicKey.multiply(t).normalize();
		BigInteger x1 = p1.add(p2).normalize().getXCoord().toBigInteger();
		BigInteger r = e.add(x1).mod(N);
		if (r.equals(signature.r)) {
			return true;
		}
		return false;
	}

	/**
	 * 密钥派生函数
	 * 
	 * @param z
	 * @param klen 生成klen字节数长度的密钥
	 * @return
	 */
	private static byte[] kdf(byte[] z, int klen) {
		int ct = 1;
		int end = (int) Math.ceil(klen * 1.0 / 32);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			for (int i = 1; i < end; i++) {
				baos.write(sm3hash(z, Sm3Util.toByteArray(ct)));
				ct++;
			}
			byte[] last = sm3hash(z, Sm3Util.toByteArray(ct));
			if (klen % 32 == 0) {
				baos.write(last);
			} else {
				baos.write(last, 0, klen % 32);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 传输实体类
	 * 
	 * @author Potato
	 *
	 */
	private static class TransportEntity implements Serializable {
		final byte[] finalR; // R点
		final byte[] finalS; // 验证S
		final byte[] finalZ; // 用户标识
		final byte[] finalK; // 公钥

		public TransportEntity(byte[] r, byte[] s, byte[] z, ECPoint pKey) {
			finalR = r;
			finalS = s;
			finalZ = z;
			finalK = pKey.getEncoded(false);
		}
	}

	/**
	 * 密钥协商辅助类
	 * 
	 * @author Potato
	 *
	 */
	public static class KeyExchange {
		BigInteger ra1;
		ECPoint ra2;
		ECPoint v;
		byte[] z;
		byte[] key;

		String id;
		Sm2KeyPairUtil keyPair;

		public KeyExchange(String id, Sm2KeyPairUtil keyPair) {
			this.id = id;
			this.keyPair = keyPair;
			this.z = za(id, keyPair.getPublicKey());
		}

		/**
		 * 密钥协商发起第一步
		 * 
		 * @return
		 */
		public TransportEntity keyExchange1() {
			ra1 = random(N);
			ra2 = G.multiply(ra1).normalize();
			return new TransportEntity(ra2.getEncoded(false), null, z, keyPair.getPublicKey());
		}

		/**
		 * 密钥协商响应方
		 * 
		 * @param entity 传输实体
		 * @return
		 */
		public TransportEntity keyExchange2(TransportEntity entity) {
			BigInteger rb1 = random(N);

			ECPoint rb2 = G.multiply(rb1).normalize();

			this.ra1 = rb1;
			this.ra2 = rb2;

			BigInteger x2 = rb2.getXCoord().toBigInteger();
			x2 = W_2.add(x2.and(W_2.subtract(BigInteger.ONE)));

			BigInteger tb = keyPair.getPrivateKey().add(x2.multiply(rb1)).mod(N);
			ECPoint ra = CURVE.decodePoint(entity.finalR).normalize();

			BigInteger x1 = ra2.getXCoord().toBigInteger();
			x1 = W_2.add(x1.and(W_2.subtract(BigInteger.ONE)));

			ECPoint aPublicKey = CURVE.decodePoint(entity.finalK).normalize();
			ECPoint temp = aPublicKey.add(ra2.multiply(x1).normalize()).normalize();
			ECPoint v = temp.multiply(ECC_BC_SPEC.getH().multiply(tb)).normalize();
			if (v.isInfinity()) {
				throw new IllegalStateException();
			}
			this.v = v;

			byte[] xv = v.getXCoord().toBigInteger().toByteArray();
			byte[] yv = v.getYCoord().toBigInteger().toByteArray();
			byte[] kb = kdf(join(xv, yv, entity.finalZ, this.z), 16);
			key = kb;
			System.out.print("协商得B密钥:");
			printHexString(kb);
			byte[] sb = sm3hash(new byte[] { 0x02 }, yv,
					sm3hash(xv, entity.finalZ, this.z, ra2.getXCoord().toBigInteger().toByteArray(),
							ra2.getYCoord().toBigInteger().toByteArray(), rb2.getXCoord().toBigInteger().toByteArray(),
							rb2.getYCoord().toBigInteger().toByteArray()));
			return new TransportEntity(rb2.getEncoded(false), sb, this.z, keyPair.getPublicKey());
		}

		/**
		 * 密钥协商发起方第二步
		 * 
		 * @param entity 传输实体
		 */
		public TransportEntity keyExchange3(TransportEntity entity) {
			BigInteger x1 = ra2.getXCoord().toBigInteger();
			x1 = W_2.add(x1.and(W_2.subtract(BigInteger.ONE)));

			BigInteger ta = keyPair.getPrivateKey().add(x1.multiply(ra1)).mod(N);
			ECPoint rb2 = CURVE.decodePoint(entity.finalR).normalize();

			BigInteger x2 = rb2.getXCoord().toBigInteger();
			x2 = W_2.add(x2.and(W_2.subtract(BigInteger.ONE)));

			ECPoint bPublicKey = CURVE.decodePoint(entity.finalK).normalize();
			ECPoint temp = bPublicKey.add(rb2.multiply(x2).normalize()).normalize();
			ECPoint u = temp.multiply(ECC_BC_SPEC.getH().multiply(ta)).normalize();
			if (u.isInfinity()) {
				throw new IllegalStateException();
			}
			this.v = u;

			byte[] xu = u.getXCoord().toBigInteger().toByteArray();
			byte[] yu = u.getYCoord().toBigInteger().toByteArray();
			byte[] ka = kdf(join(xu, yu, this.z, entity.finalZ), 16);
			key = ka;
			System.out.print("协商得A密钥:");
			printHexString(ka);
			byte[] s1 = sm3hash(new byte[] { 0x02 }, yu,
					sm3hash(xu, this.z, entity.finalZ, ra2.getXCoord().toBigInteger().toByteArray(),
							ra2.getYCoord().toBigInteger().toByteArray(), rb2.getXCoord().toBigInteger().toByteArray(),
							rb2.getYCoord().toBigInteger().toByteArray()));
			if (Arrays.equals(entity.finalS, s1)) {
				System.out.println("B->A 密钥确认成功");
			} else {
				System.out.println("B->A 密钥确认失败");
			}
			byte[] sa = sm3hash(new byte[] { 0x03 }, yu,
					sm3hash(xu, this.z, entity.finalZ, ra2.getXCoord().toBigInteger().toByteArray(),
							ra2.getYCoord().toBigInteger().toByteArray(), rb2.getXCoord().toBigInteger().toByteArray(),
							rb2.getYCoord().toBigInteger().toByteArray()));

			return new TransportEntity(ra2.getEncoded(false), sa, this.z, keyPair.getPublicKey());
		}

		/**
		 * 密钥确认最后一步
		 * 
		 * @param entity 传输实体
		 */
		public void keyExchange4(TransportEntity entity) {
			byte[] xv = v.getXCoord().toBigInteger().toByteArray();
			byte[] yv = v.getYCoord().toBigInteger().toByteArray();
			ECPoint ra = CURVE.decodePoint(entity.finalR).normalize();
			byte[] s2 = sm3hash(new byte[] { 0x03 }, yv,
					sm3hash(xv, entity.finalZ, this.z, ra2.getXCoord().toBigInteger().toByteArray(),
							ra2.getYCoord().toBigInteger().toByteArray(),
							this.ra2.getXCoord().toBigInteger().toByteArray(),
							this.ra2.getYCoord().toBigInteger().toByteArray()));
			if (Arrays.equals(entity.finalS, s2)) {
				System.out.println("A->B 密钥确认成功");
			} else {
				System.out.println("A->B 密钥确认失败");
			}
		}
	}

	public static class Signature {
		BigInteger r;
		BigInteger s;

		public Signature(BigInteger r, BigInteger s) {
			this.r = r;
			this.s = s;
		}

		@Override
		public String toString() {
			return r.toString(16) + s.toString(16);
		}
	}

}
