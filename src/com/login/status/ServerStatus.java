package com.login.status;

/**
 * 
*
* @Description: TODO
* @author zhaowei 
* @Ceatetime 2014年8月20日	区服状态
*
 */
public enum ServerStatus {
	/**
	 * 开启
	 */
	OPEN(0) ,
	/**
	 * 关闭
	 */
	CLOSE(1);
	
	private int value ;
	
	ServerStatus(int value){
		this.value = value ;
	}
	
	public int getValue() {
		return value ;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
