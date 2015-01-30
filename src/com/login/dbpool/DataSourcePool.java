package com.login.dbpool;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
*
* @Description: TODO	连接池
* @author zhaowei 
* @Ceatetime 2014年8月11日
*
 */
public class DataSourcePool extends UnpooledDataSourceFactory {
	
	public DataSourcePool () {
	       this.dataSource = new ComboPooledDataSource () ;
    }
}
