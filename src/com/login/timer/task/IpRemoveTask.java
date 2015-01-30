package com.login.timer.task;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.login.container.IPContainer;

/**
 * 
*
* @Description: TODO	清空ip任务
* @author zhaowei 
* @Ceatetime 2014年9月11日
*
 */
public class IpRemoveTask extends TimerTask {
	private Logger logger = Logger.getLogger(IpRemoveTask.class) ;
	@Override
	public void run() {
		try {
			IPContainer.instance().removeIp();
			logger.info("定时清空ip成功");
		} catch (Exception e) {
			logger.error(e, e);
		}
	}

}
