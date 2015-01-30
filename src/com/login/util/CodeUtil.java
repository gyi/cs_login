package com.login.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * 编码工具类
*
* @author zhaowei 
* @Ceatetime 2014年6月5日
*
 */
public class CodeUtil {
	public static Logger logger = Logger.getLogger(CodeUtil.class);

	private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	/**
	 * 获得base64解码后的字符串
	 * 
	* @throws
	 */
	public static String decodeBase64(String b64string) throws Exception {
		return new String(Base64.decodeBase64(b64string.getBytes()), "utf-8");
	}
	/**
	 * 获得base64编码后的字符串
	 * 
	* @throws
	 */
	public static String encodeBase64(String stringsrc) {
		try {
			Base64 base64encode = new Base64();
			return new String(base64encode.encode(stringsrc.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * MD5编码 
	* @throws
	 */
	public static String Md5(String s) {
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] str = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
				str[(k++)] = hexDigits[(byte0 & 0xF)];
			}
			return new String(str);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}