package com.login.util;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @Create 2014-1-15
 * @author wangl
 * @Description OBJ-JSON, JSON-OBJ
 */
public class JsonHelper {
	private static ObjectMapper objectMapper = new ObjectMapper();

	static {
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.enableDefaultTyping();
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	}

	/**
	 * 将对象转换为json
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static <T> String obj2Json(T obj) throws IOException {
		if (obj == null) {
			return "";
		}
		// 转换字符串类型
		StringWriter stringWriter = new StringWriter();
		JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(stringWriter);
		jsonGenerator.writeObject(obj);
		return stringWriter.toString();
	}

	/**
	 * json转换为对象
	 * 
	 * @param json
	 * @param objClass
	 * @return 如果json字符串为空，则返回newInstance
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T> T json2Obj(String json, Class<T> objClass) throws IOException, InstantiationException,
			IllegalAccessException {
		if (StringUtils.isEmpty(json)) {
			return objClass.newInstance();
		}
		// 转换字符串类型
		return objectMapper.readValue(json, objClass);
	}

	/**
	 * 
	 * @param json
	 * @param javaType
	 * @return 如果json字符串为空，则返回对象为null
	 * @throws IOException
	 */
	public static <T> T json2Obj(String json, JavaType javaType) throws IOException {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		// 转换字符串类型
		return objectMapper.readValue(json, javaType);
	}
	
	public static String toFastJSONString(Object obj) {
		return JSON.toJSONString(obj) ;
	}
}
