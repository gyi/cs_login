package com.login.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.login.config.SystemConfigManager;
import com.login.container.IPContainer;
import com.login.db.manager.AdminManager;
import com.login.db.manager.AreaManager;
import com.login.db.manager.PlayerManager;
import com.login.db.manager.ServerManager;
import com.login.timer.TimerFactory;

/**
 * 
*
* @Description: TODO	系统监听
* @author zhaowei 
* @Ceatetime 2014年8月13日
*
 */
public class SystemListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent contextEvent) {
	}

	public void contextInitialized(ServletContextEvent contextEvent) {
		//初始化系统属性
		SystemConfigManager manager = SystemConfigManager.instance() ;
		//获得全局上下文
		ServletContext context = contextEvent.getServletContext() ;
		//设置工程名
		context.setAttribute("context", manager.getPropertiesValue("login.system"));
		//启动加载
		AdminManager.instance() ;
		AreaManager.instance() ;
		PlayerManager.instance() ;
		PlayerManager.instance() ;
		ServerManager.instance() ;
		IPContainer.instance() ;//ip控制容器
		//初始化定时器
		TimerFactory.instance().start();
	}

}
