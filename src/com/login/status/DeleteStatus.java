package com.login.status;

/**
 * 用户权限状态
 */
public enum DeleteStatus {
	/**
	 * 未删除
	 */
	UNDELETE("0"),
	/**
	 * 删除
	 */
	DELETE("1");
	
	private String value ;
	
	private DeleteStatus(String value) {
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
