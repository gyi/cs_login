package com.login.config;

/**
 * 
*
* @Description: TODO	系统配置
* @author zhaowei 
* @Ceatetime 2014年8月11日
*
 */
public class SystemConfig {
	/**
	 * mybatis配置
	 */
	public final static String DBCONFIG = "configuration.xml" ;
	
	/**
	 * 系统配置
	 */
	public final static String SYSCONFIG = "config.properties" ;
	
	/**
	 * 请求时间间隔
	 */
	public final static long time = 1000L ;
	
	/**
	 * 时间间隔内请求的最大次数
	 */
	public final static int cishu = 4 ;
	
	/**
	 * 上次请求与当前销毁时间间隔
	 */
	public final static long timeLake = 500L  ;
}
