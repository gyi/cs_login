package com.login.vo;

public class ServerInfo {
	/**
	 * 区服id
	 */
	private int id ;
	/**
	 * 服务器名
	 */
	private String servername;
	/**
	 * 状态
	 */
	private int state ;
	public String getServername() {
		return servername;
	}
	public void setServername(String servername) {
		this.servername = servername;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
