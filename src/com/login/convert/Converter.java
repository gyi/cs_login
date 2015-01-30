package com.login.convert;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
*
* @Description: TODO	转化器
* @author zhaowei 
* @Ceatetime 2014年9月24日
*
 */
public abstract class Converter extends ActionSupport{
	private static final long serialVersionUID = -2702818687370922519L;
	private Object[] params ;
	
	public Converter() {
	}
	
	public Converter(Object[] params) {
		this.params = params ;
	}
	
	public Object convert(Object obj) {
		return convert(obj, params) ;
	}
	
	public abstract Object convert(Object obj, Object ...params) ;
}
