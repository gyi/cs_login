package com.login.vo;

/**
 * 
*
* @Description: TODO	客户端版本vo
* @author zhaowei 
* @Ceatetime 2014年9月24日
*
 */
public class ClientVersionVo {
	/**
	 * 版本号
	 */
	private String version ;
	
	/**
	 * 时间
	 */
	private String time ;
	
	/**
	 * 是否必须,0表示是，1表示否
	 */
	private boolean isMust ;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public boolean isMust() {
		return isMust;
	}

	public void setMust(boolean isMust) {
		this.isMust = isMust;
	}
}
