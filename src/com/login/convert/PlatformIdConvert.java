package com.login.convert;

import com.login.db.manager.PlatformManager;
import com.login.db.model.platform.Platform;

/**
 * 
*
* @Description: TODO	平台转换器
* @author zhaowei 
* @Ceatetime 2014年9月24日
*
 */
public class PlatformIdConvert extends Converter {

	private static final long serialVersionUID = 9035249228612579241L;
	
	public PlatformIdConvert() {
	}
	
	public PlatformIdConvert(Object[] params) {
		super(params) ;
	}

	@Override
	public Object convert(Object obj, Object... params) {
		if(!(obj instanceof Integer)) {
			throw new RuntimeException("平台id必须为整形") ;
		}
		Platform platform = PlatformManager.instance().getPlatformById((int)obj) ;
		return platform==null?"":platform.getPlatformname();
	}

}
