package com.login.convert;

import java.util.Map;

/**
 * 
*
* @Description: TODO	是否转换器
* @author zhaowei 
* @Ceatetime 2014年9月24日
*
 */
public class IsNotConvert extends Converter {
	
	public IsNotConvert() {
	}
	
	public IsNotConvert(Object[] params){
		super(params) ;
	}

	private static final long serialVersionUID = 1L;

	@Override
	@SuppressWarnings("unchecked")
	public Object convert(Object obj, Object... params) {
		//参数长度必须为1
		if(params==null||params.length!=1) {
			throw new RuntimeException("params length must = 1") ;
		}
		//参数必须为Map的实例
		if(!(params[0] instanceof Map)) {
			throw new RuntimeException("params must be instance of Map") ;
		}
		
		Map<Object, Object> map = (Map<Object, Object>) params[0] ;
		
		return map.get(obj);
	}

}
