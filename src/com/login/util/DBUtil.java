package com.login.util;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.login.config.SystemConfig;
import com.login.db.manager.ServerManager;

/**
 * 
*
* @Description: TODO	DB工具
* @author zhaowei 
*
 */
public class DBUtil {
	private SqlSessionFactory sessionFactory = null;
	
	private static DBUtil dbUtil ;
	
	private DBUtil() {
		if(sessionFactory==null) {
			try {  
				//加载mybatis配置文件
	            sessionFactory = new SqlSessionFactoryBuilder().build(Resources  
	                    .getResourceAsReader(SystemConfig.DBCONFIG));  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
		}
	}
	
	public static DBUtil instance(){
		if(dbUtil==null) {
			dbUtil = new DBUtil() ;
		}
		return dbUtil ;
	}
	
	//获得连接
	public SqlSession getSqlSession() {
		return sessionFactory.openSession() ;
	}
	
	
	public static void main(String[] args) {
		System.out.println(JsonHelper.toFastJSONString(ServerManager.instance().getServerById(16))) ;
	}
}
