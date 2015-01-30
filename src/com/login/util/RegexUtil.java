package com.login.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
*
* @Description: TODO	正则通用类
* @author zhaowei 
* @Ceatetime 2014年8月20日
*
 */
public class RegexUtil {
	/**
	 * 通过正则返回指定字符串中的匹配
	 */
	public static List<String> getRegixMatchList(String regix, String input) {
		if(StringUtil.isBlank(regix, input)){
			throw new RuntimeException("传入字符串不允许为空") ;
		}
		Pattern pattern = Pattern.compile(regix) ;
		Matcher matcher = pattern.matcher(input) ;
		List<String> resultList = new ArrayList<String>() ;
		while(matcher.find()) {
			String result = matcher.group() ;
			resultList.add(result) ;
		}
		return resultList ;
	}
}
