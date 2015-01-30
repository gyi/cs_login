package com.login.container;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.login.config.SystemConfig;

/**
 * 
*
* @Description: TODO	ip管理
* @author zhaowei 
* @Ceatetime 2014年9月10日
*
 */
public class IPContainer {
	/**
	 * ip次数
	 */
	private ConcurrentHashMap<String, Integer> ipCiShu = new ConcurrentHashMap<String, Integer>() ;
	/**
	 * ip时间
	 */
	private ConcurrentHashMap<String, Long> ipTime = new ConcurrentHashMap<String, Long>() ;
	
	private Object obj = new Object() ;
	
	private static IPContainer container ;
	
	private IPContainer() {
	}
	
	public static IPContainer instance(){
		if(container==null){
			container = new IPContainer() ;
		}
		return container ;
	}
	
	/**
	 * ip是否能够进入
	 */
	public boolean canIn(String ip) {
		synchronized (obj) {
			if(!ipCiShu.containsKey(ip)) {
				ipCiShu.put(ip, 1) ;
				ipTime.put(ip, System.currentTimeMillis()) ;
				return true ;
			}
			long time = ipTime.get(ip) ;
			long now = System.currentTimeMillis() ;
			if(now-time>SystemConfig.time) {
				ipCiShu.remove(ip) ;
				ipTime.remove(ip) ;
				return true ;
			}
			
			int cishu = ipCiShu.get(ip) ;
			if(cishu<=SystemConfig.cishu) {
				ipCiShu.put(ip, cishu + 1) ;
				ipTime.put(ip, System.currentTimeMillis()) ;
				return true ;
			}
			
			ipTime.put(ip, System.currentTimeMillis()) ;
			return false ;
		}
	}
	
	/**
	 * 定时清空ip
	 */
	public void removeIp() {
		synchronized (obj) {
			Iterator<Map.Entry<String, Long>> it = ipTime.entrySet().iterator() ;
			 while(it.hasNext()){
				 Map.Entry<String, Long> entry = it.next(); 
				 String ip = entry.getKey() ;
				 long time = entry.getValue() ;
				 long now = System.currentTimeMillis() ;
				 if(now - time > SystemConfig.timeLake) {
					 it.remove();
					 ipCiShu.remove(ip) ;
				 }
			 }
		}
	}
}
