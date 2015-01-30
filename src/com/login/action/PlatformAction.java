package com.login.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.login.db.manager.PlatformManager;
import com.login.db.model.platform.Platform;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Description: TODO 平台管理控制
 * @author zhaowei
 * @Ceatetime 2014年8月14日
 * 
 */
public class PlatformAction extends ActionSupport {

	private static final long serialVersionUID = 9095223639094306225L;
	
	private static Logger logger = Logger.getLogger(PlatformAction.class) ;
	
	/**
	 * id
	 */
	private int id ;
	
	/**
	 * 平台集合
	 */
	private List<Platform> platforms ;
	
	/**
	 * 结果
	 */
	private String result ;
	
	/**
	 * 平台id
	 */
	private int platformId ;
	
	/**
	 * 平台名
	 */
	private String platformName ;
	
	/**
	 * 平台key
	 */
	private String platformKey ;
	
	/**
	 * 平台描述
	 */
	private String platformDesc ;
	
	public String platformPage(){
		platforms = PlatformManager.instance().getAllPlatforms();
		return SUCCESS ;
	}
	
	public String platformSelect(){
		result = "false" ;
		try {
			switch (platformId) {
			case -999:
				platforms = PlatformManager.instance().getAllPlatforms() ;
				break;
			default:
				platforms = new ArrayList<Platform>() ; 
				Platform platform = PlatformManager.instance().getPlatformById(platformId) ;
				platforms.add(platform) ;
				break;
			}
			result = "true" ;
		} catch (Exception e) {
			logger.error(e, e);
			return INPUT ;
		}
		return SUCCESS ;
	}
	
	//添加平台
	public String addPlatform() {
		result = "false" ;
		try {
			Platform platform = PlatformManager.instance().createPlatform(platformName, platformKey, platformDesc) ;
			int rs = PlatformManager.instance().addPlatform(platform) ;
			if(rs>0) {
				result = "true" ;
			}
		} catch (Exception e) {
			logger.error(e, e);
			return INPUT ;
		}
		return SUCCESS ;
	}
	
	public void validateAddPlatform(){
		if(StringUtils.isEmpty(platformName)) {
			this.addFieldError("platformMessageError", getText("cs.platform.platformNameIsNull"));
			return;
		}
		if(StringUtils.isEmpty(platformKey)) {
			this.addFieldError("platformMessageError", getText("cs.platform.platformKeyIsNull"));
			return;
		}
		if(StringUtils.isEmpty(platformDesc)) {
			this.addFieldError("platformMessageError", getText("cs.platform.platformDescIsNull"));
			return;
		}
		if(PlatformManager.instance().getPlatformByName(platformName)!=null) {
			this.addFieldError("platformMessageError", getText("cs.platform.platformNameIsExist"));
			return;
		}
	}
	
	public String deletePlatform() {
		result = "false" ;
		try {
			Platform platform = PlatformManager.instance().getPlatformById(id);
			platform.setIsdeleted(1);
			int rs = PlatformManager.instance().modifyPlatform(platform) ;
			if(rs>0) {
				result = "true" ;
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return SUCCESS ;
	}
	
	public List<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getPlatformId() {
		return platformId;
	}

	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getPlatformKey() {
		return platformKey;
	}

	public void setPlatformKey(String platformKey) {
		this.platformKey = platformKey;
	}

	public String getPlatformDesc() {
		return platformDesc;
	}

	public void setPlatformDesc(String platformDesc) {
		this.platformDesc = platformDesc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
