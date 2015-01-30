package com.login.util;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

/**
 * 
*
* @Description: TODO	字符串工具类
* @author zhaowei 
* @Ceatetime 2014年8月20日
*
 */
public class StringUtil {
	/**
	 * 	判断传入字符串是否全部为空
	 */
	public static boolean isBlank(String ...strs) {
		if(strs.length==0) {
			throw new RuntimeException("传入字符串长度必须大于0") ;
		}
		boolean isEmpty = true ;
		for(String str : strs) {
			if(!StringUtils.isEmpty(str)){
				isEmpty = false ;
				break ;
			}
		}
		return isEmpty;
	}
	
	/**
	 * 判断是否存在空字符串
	 */
	public static boolean isExsitBlank(String ...strs) {
		if(strs.length==0) {
			throw new RuntimeException("传入字符串长度必须大于0") ;
		}
		boolean isEmpty = false ;
		for(String str : strs) {
			if(StringUtils.isEmpty(str)){
				isEmpty = true ;
				break ;
			}
		}
		return isEmpty;
	}
	
	/**
	 * 判断传入字符串是否全是数字
	 */
	public static boolean isNumric(String ...strs) {
		if(strs.length==0) {
			throw new RuntimeException("传入字符串长度必须大于0") ;
		}
		boolean isNumeric = true ;
		for(String str : strs) {
			if(!StringUtils.isNumeric(str)){
				isNumeric = false ;
				break ;
			}
		}
		return isNumeric ;
	}
	
	/**
	 * 随机生成一个唯一uuid
	 */
	public static String randUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
