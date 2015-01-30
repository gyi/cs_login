package com.login.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.login.convert.Converter;

public class ObjectConvertUtil {
	
	private static Logger logger = Logger.getLogger(ObjectConvertUtil.class) ;
	/**
	 * 将数据库对象转换成vo对象
	 */
	public static Map<String, String> convert2Map(Object obj, Map<String, Converter> convert) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>() ;
		Method[] methods = obj.getClass().getMethods() ;
		for(Method method : methods) {
			String methodName = method.getName() ;
			if(methodName.startsWith("set")) {
				String field = methodName.replaceFirst("set", "") ;
				String fieldName = String.valueOf(field.charAt(0)).toLowerCase()  + field.substring(1, field.length());
				Method mtd = obj.getClass().getMethod("get" + field) ;
				//获得值
				Object value = mtd.invoke(obj) ;
				if(convert.containsKey(fieldName)) {
					Converter converter = convert.get(fieldName) ;
					value = converter.convert(value) ;
				}
				resultMap.put(fieldName, String.valueOf(value)) ;
			}
		}
		logger.info("转换器转换结果：" + resultMap);
		return resultMap;
	}
	
	/**
	 * 将数据库对象转换成vo对象list
	 */
	public static List<Map<String, String>> convert2ListMap(List<?> objects, Map<String, Converter> convert) throws Exception {
		List<Map<String, String>> mapList = new ArrayList<Map<String,String>>() ;
		for(Object obj : objects) {
			Map<String, String> map = convert2Map(obj, convert) ;
			mapList.add(map) ;
		}
		return mapList;
	}
}
