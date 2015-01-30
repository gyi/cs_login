package com.cs.global.server.data;

import java.util.Map;

/**
 * 
*
* @Description: TODO gm传输对象
* @author zhaowei 
* @Ceatetime 2014年9月16日
*
 */
public class Gmcmd {
	/**
	 * 角色id
	 */
	private long roleId ;
	/**
	 * gm命令信息
	 */
	private Map<String, String> map ; //map中可以加入
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}
