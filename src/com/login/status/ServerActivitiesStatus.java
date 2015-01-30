package com.login.status;

/**
 *服务器注册状态
 */
public enum ServerActivitiesStatus {
	/**
	 * 激活
	 */
	ACTIVE(1),
	/**
	 * 未激活
	 */
	INACTIVE(0);
	
	private int value ;
	
	private ServerActivitiesStatus(int value) {
		this.value = value ;
	}
	
	public int getValue(){
		return value ;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
