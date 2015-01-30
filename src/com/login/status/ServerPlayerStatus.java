package com.login.status;

/**
 * 
*
* @Description: TODO	服务器人员状态
* @author zhaowei 
* @Ceatetime 2014年8月20日
*
 */
public enum ServerPlayerStatus {
	/**
	 * 爆满
	 */
	FULL_HOUSE("1") ,
	/**
	 * 火爆
	 */
	HOT("2"),
	/**
	 * 流畅
	 */
	LIQUIDITY("3"),
	/**
	 * 维护
	 */
	MAINTENANCE("4");
	private String value ;
	
	ServerPlayerStatus(String value){
		this.value = value ;
	}
	
	public String getValue() {
		return value ;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
	public static String[] toStrings() {
		ServerPlayerStatus[] conditionArray = ServerPlayerStatus.values();
		String[] results = new String[conditionArray.length];
		for(int i = 0; i < conditionArray.length; i++){
			results[i] = conditionArray[i].getValue();
		}
		return results;
	}
	
}
