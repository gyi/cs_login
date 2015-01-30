package com.login.vo;

import java.util.List;
import java.util.Map;

import com.login.status.LoginResultStatus;

/**
 * 
*
* @Description: TODO	登陆结果
* @author zhaowei 
* @Ceatetime 2014年8月12日
*
 */
public class LoginResult {
	/**
	 * 状态 	0:表示成功， 1：表示失败
	 */
	private int result ; 
	
	/**
	 * 区服列表
	 */
	private Map<String,List<ServerInfo>> servers ;

	public Map<String,List<ServerInfo>> getServers() {
		return servers;
	}

	public void setServers(Map<String,List<ServerInfo>> servers) {
		this.servers = servers;
	}

	public int getResult() {
		return result;
	}

	public void setResult(LoginResultStatus result) {
		this.result = result.getValue();
	}
}
