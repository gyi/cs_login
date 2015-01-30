package com.login.status;

/**
 *	登陆结果状态
 */
public enum LoginResultStatus {
	//状态 	0:表示成功， 1：表示失败
	/**
	 * 成功
	 */
	SUCCESS(1),
	
	/**
	 * 失败
	 */
	FAIL(2);
	private int value ;
	
	LoginResultStatus(int value){
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
