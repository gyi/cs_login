package com.login.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

/**
 * 
*
* @Description: TODO	系统配置管理者
* @author zhaowei 
* @Ceatetime 2014年8月13日
*
 */
public class SystemConfigManager {
	private static SystemConfigManager configHandler ;
	private Properties properties ;
	
	private SystemConfigManager() {
		try {
			properties = Resources.getResourceAsProperties(SystemConfig.SYSCONFIG) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SystemConfigManager instance(){
		if(configHandler==null){
			configHandler = new SystemConfigManager() ;
		}
		return configHandler ;
	}
	
	/**
	 * 获得系统属性
	 */
	public String getPropertiesValue(String key) {
		return properties.getProperty(key) ;
	}
}
