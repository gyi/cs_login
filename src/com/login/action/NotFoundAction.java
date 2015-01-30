package com.login.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
*
* @Description: TODO 默认访问action
* @author zhaowei 
* @Ceatetime 2014年8月22日
*
 */
public class NotFoundAction extends ActionSupport {

	private static final long serialVersionUID = 2394120128066830076L;
	private Logger logger = Logger.getLogger(Logger.class) ;
	@Override
	public String execute() throws Exception {
		logger.info("不存在action");
		return super.execute();
	}
}
