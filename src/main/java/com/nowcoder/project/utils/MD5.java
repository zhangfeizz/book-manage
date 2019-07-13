package com.nowcoder.project.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	public static String next(String key) {
		char hexDigits[] = {
		        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
		    };
		byte[] btInput = key.getBytes();
		
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
		    // 使用指定的字节更新摘要
			mdInst.update(btInput);
			//获得密文
			byte[] md = mdInst.digest();
			//密文转换十六进制
			int j = md.length;
			char[] str = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
		        str[k++] = hexDigits[byte0 >>> 4 & 0xf];
		        str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
