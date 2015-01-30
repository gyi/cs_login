package com.login.convert;

import com.login.util.DateUtil;

/**
 * 
*
* @Description: TODO	时间转换器
* @author zhaowei 
* @Ceatetime 2014年9月24日
*
 */
public class TimeConvert extends Converter {
	private static final long serialVersionUID = 8191001858142196351L;
	
	public TimeConvert() {
	}
	
	public TimeConvert(Object[] params) {
		super(params);
	}

	/**
	 * @param	obj
	 * 		要转换的时间long型
	 * @param params
	 * 		要转换的时间格式(例如:yyyy-MM-dd)
	 */
	@Override
	public Object convert(Object obj, Object ...params) {
		if(!(obj instanceof Long)) {
			throw new RuntimeException(obj + " is not Long type") ;
		}
		if(params!=null&&params.length > 1) {
			throw new RuntimeException("params is length must <= 1") ;
		}
		
		String result = "" ;
		if(params==null||params.length==0) {
			result = DateUtil.convertLong2StringInModel(obj, "yyyy-MM-dd HH:mm:ss") ;
		}else if(params.length==1){
			result = DateUtil.convertLong2StringInModel(obj, (String)params[0]) ;
		}
		
		return result;
	}

}
