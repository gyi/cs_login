package com.login.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MD5 {

	public static String md5_32(Object... parameters) {
		StringBuilder sBuilder = new StringBuilder();
		for (Object parameter : parameters) {
			sBuilder.append(parameter.toString());
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sBuilder.toString().getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String md5_16(Object... parameters) {
		return md5_32(parameters).substring(8, 24);
	}

	/**
	 * 对http参数进行加密，key值进行排序key1=valuekey2=value的方式
	 * 
	 * @param map
	 * @return
	 */
	public static String markSign(Map<String, String> map, String secretKey) {
		List<String> list = new ArrayList<>(map.size());
		for (Entry<String, String> entry : map.entrySet()) {
			list.add(entry.getKey() + "=" + entry.getValue());
		}
		Collections.sort(list);
		return MD5.md5_16(list.toArray(), secretKey);
	}

	/**
	 * 计算md5值，按值顺序排序
	 * 
	 * @param map
	 * @param secretKey
	 * @return
	 */
	public static String markSign(Object... params) {
		List<String> list = new ArrayList<>(params.length);
		for (Object param : params) {
			list.add(param.toString());
		}
		Collections.sort(list);
		return MD5.md5_16(list.toArray());
	}

}
