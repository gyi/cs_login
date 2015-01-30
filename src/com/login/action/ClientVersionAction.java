package com.login.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.login.convert.Converter;
import com.login.convert.IsNotConvert;
import com.login.convert.TimeConvert;
import com.login.db.manager.ClientVersionManager;
import com.login.db.model.clientversion.ClientVersion;
import com.login.util.ObjectConvertUtil;
import com.login.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
*
* @Description: TODO	客户端版本管理
* @author zhaowei 
* @Ceatetime 2014年9月23日
*
 */
public class ClientVersionAction extends ActionSupport {

	private static final long serialVersionUID = -5140268295350627699L;
	
	private Logger logger = Logger.getLogger(ClientVersionAction.class) ;
	
	/**
	 * 所有版本号
	 */
	private List<Map<String, String>> clientVersions ;
	
	/**
	 * 版本号
	 */
	private String version ;
	
	/**
	 * 是否为必须(0:是， 1：否)
	 */
	private int isMust ;
	
	/**
	 * 返回结果
	 */
	private String result ;
	
	public String clientVersion() {
		return SUCCESS;
	}
	
	/**
	 * 版本查询
	 */
	public String versionSelect() {
		result = "false" ;
		try {
			List<ClientVersion> clientVs = ClientVersionManager.instance().getAllClientVersions() ;
			Map<Integer, String> map = new HashMap<Integer, String>() ;
			map.put(0, getText("com.comm.yes")) ;
			map.put(1, getText("com.comm.no")) ;
			//转换器转换为可读模式
			Map<String, Converter> convert = new HashMap<String, Converter>() ;
			convert.put("ismust", new IsNotConvert(new Object[]{map})) ;
			convert.put("addtime", new TimeConvert()) ;
			clientVersions = ObjectConvertUtil.convert2ListMap(clientVs, convert) ;
			result = "success" ;
		} catch (Exception e) {
			logger.error("版本查询出错", e);
		}
		return SUCCESS;
	}
	
	/**
	 *添加版本 
	 */
	public String addVersion() {
		result = "false" ;
		try {
			ClientVersion clientVersion = ClientVersionManager.instance().createVersion(version, isMust) ;
			int rs = ClientVersionManager.instance().insert(clientVersion) ;//插入版本
			if(rs > 0) {
				result = "success" ;
			}
		} catch (Exception e) {
			logger.error("版本添加出错", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 验证添加版本 
	 */
	public void validateAddVersion(){
		if(StringUtil.isBlank(version)) {
			this.addFieldError("versionIsNull", getText("cs.clientvesion.versionIsNull"));
			return ;
		}
		ClientVersion rs = ClientVersionManager.instance().getVersion(version) ;
		if(rs!=null) {
			this.addFieldError("versionIsExsist", getText("cs.clientvesion.versionIsExist"));
			return ;
		}
			
	}
	
	/**
	 * 删除版本号
	 */
	public String deleteVersion(){
		result = "false" ;
		try {
			int rs = ClientVersionManager.instance().deleteVersion(version) ;
			if(rs>0){
				result = "success" ;
			}
		} catch (Exception e) {
			logger.error("版本删除出错", e);
		}
		return SUCCESS ;
	}
	
	/**
	 * 验证删除版本
	 */
	public void validateDeleteVersion(){
		if(StringUtil.isBlank(version)){
			this.addFieldError("versionIsNull", getText("cs.clientvesion.versionIsNull"));
			return ;
		}
		ClientVersion rs = ClientVersionManager.instance().getVersion(version) ;
		if(rs==null) {
			this.addFieldError("versionIsNotExsist", getText("cs.clientvesion.versionIsNotExist"));
			return ;
		}
	}
	

	public List<Map<String, String>> getClientVersions() {
		return clientVersions;
	}

	public void setClientVersions(List<Map<String, String>> clientVersions) {
		this.clientVersions = clientVersions;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getIsMust() {
		return isMust;
	}

	public void setIsMust(int isMust) {
		this.isMust = isMust;
	}
}
