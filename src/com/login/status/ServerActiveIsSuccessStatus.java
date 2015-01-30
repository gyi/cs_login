package com.login.status;

/**
 * 
*
* @Description: TODO	服务器激活/开启是否成功状态
* @author zhaowei 
* @Ceatetime 2014年8月22日
*
 */
public enum ServerActiveIsSuccessStatus {
	/**
	 * 成功
	 */
	SUCCESS("1") ,
	/**
	 * 失败
	 */
	FAIL("2");
	
	private String value ;
	private ServerActiveIsSuccessStatus(String value) {
		this.value = value ;
	}
	
	public String getValue() {
		return value ;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
