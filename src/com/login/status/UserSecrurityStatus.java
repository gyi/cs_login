package com.login.status;

/**
 * 用户权限状态
 */
public enum UserSecrurityStatus {
	/**
	 * 普通
	 */
	ORDINARY("0"),
	/**
	 * 特权
	 */
	PRIVILEGE("1"),
	
	/**
	 * 普通
	 */
	ORDINARYSHOW("00"),
	/**
	 * 特权
	 */
	PRIVILEGESHOW("01");
	
	
	private String value ;
	
	private UserSecrurityStatus(String value) {
		this.value = value ;
	}
	
	public String getValue(){
		return value ;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
