package com.login.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.login.timer.task.IpRemoveTask;

/**
 * 
*
* @Description: TODO	定时器工厂类
* @author zhaowei 
* @Ceatetime 2014年9月10日
*
 */
public class TimerFactory {
	private Logger logger = Logger.getLogger(Logger.class) ;
	private static TimerFactory timerFactory ;//定时器工厂类
	private Timer timerIpRemove ;//清空ip
	private TimerTask timerTaskIpRemove ;//清空ip任务
	
	private TimerFactory(){
		timerIpRemove = new Timer("清空ip") ;
		timerTaskIpRemove = new IpRemoveTask() ;
	}
	
	public static TimerFactory instance() {
		if(timerFactory==null){
			timerFactory = new TimerFactory() ;
		}
		return timerFactory ;
	}
	
	public void startIpRemoveTimer() {
		logger.info("清空ip定时器");
		timerIpRemove.schedule(timerTaskIpRemove, new Date(System.currentTimeMillis() + 30000L), 1000L*3600);
	}
	
	public void start() {
		startIpRemoveTimer();
	}
}
