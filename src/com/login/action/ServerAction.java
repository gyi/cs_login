package com.login.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.login.db.manager.AreaManager;
import com.login.db.manager.ServerManager;
import com.login.db.model.area.Area;
import com.login.db.model.server.Server;
import com.login.status.ServerActivitiesStatus;
import com.login.status.ServerStatus;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
*
* @Description: TODO	服务器管理控制
* @author zhaowei 
* @Ceatetime 2014年8月13日
*
 */
public class ServerAction extends ActionSupport{

	private static final long serialVersionUID = -6994280882198273827L;
	private Logger logger = Logger.getLogger(ServerAction.class) ;
	
	/**
	 * id
	 */
	private int id;
	
	/**
	 * 服务器id
	 */
	private int serverId ;
	
	/**
	 * 区id
	 */
	private int areaId ; 
	
	/**
	 * 区集合
	 */
	private List<Area> areas ;
	
	/**
	 * 服务器集合
	 */
	private List<Server> servers ;
	
	/**
	 * 服务器集合
	 */
	private List<Map<String, Object>> serversContainer ;
	
	/**
	 * 区名
	 */
	private String areaName ;
	
	/**
	 * 区描述
	 */
	private String areaDesc ;
	
	/**
	 * 服务器名
	 */
	private String serverName ;
	
	/**
	 * ip
	 */
	private String ip ;
	
	/**
	 * gm命令
	 */
	private String msg;
	
	/**
	 * 发送消息后的返回结果
	 */
	private String feedback;
	
	/**
	 * 端口
	 */
	private int port ;
	
	/**
	 * 良好人数上限
	 */
	private int low ;
	
	/**
	 * 繁忙人数上限
	 */
	private int middle ;
	
	/**
	 * 爆满人数上限
	 */
	private int max ;
	
	private String result ;
	
	/**
	 * 跳转到服务器页面
	 */
	public String serverPage() {
		areas = AreaManager.instance().getAllArea() ;
		return SUCCESS ;
	}
	
	/**
	 * 查询服务器
	 */
	@SuppressWarnings({ "unchecked" })
	public String serverSelect() {
		result = "false" ;
		//String[] conditionArray = {getText("cs.server.low"), getText("cs.server.middle"), getText("cs.server.max"), getText("cs.server.maintain")};
		try {
			//-999表示全部服务器
			servers = areaId == -999 ?ServerManager.instance().getAllServers(): ServerManager.instance().getServersByAreaId(areaId) ;
			serversContainer = (List<Map<String, Object>>) JSON.parse(JSON.toJSONString(servers));
			for (int i = 0; i < serversContainer.size(); i++) {
				Server server = servers.get(i);
//				serversContainer.get(i).put("perSizecondition", GMCommandUtil.getServerOnlinePlayerCondition(server, conditionArray));
				serversContainer.get(i).put("perSizecondition", 0);
				serversContainer.get(i).put("onOffCondition", server.getStatus()==ServerStatus.OPEN.getValue()? getText("cs.server.open"): getText("cs.server.close"));
				serversContainer.get(i).put("isActive", server.getIsactive()==ServerActivitiesStatus.ACTIVE.getValue()? getText("cs.server.active"): getText("cs.server.inactive"));
			}
			result = "true" ;
		} catch (Exception e) {
			logger.error(e, e);
		}
		return SUCCESS ;
	}
	
	/**
	 * 区查询
	 */
	public String areaSelect(){
		result = "false" ;
		try {
			areas = AreaManager.instance().getAllArea() ;
			result="true" ;
		} catch (Exception e) {
			logger.error(e, e);
		}
		return SUCCESS ;
	}
	
	/**
	 * 添加区
	 */
	public String addArea(){
		result = "false" ;
		try {
			Area area = AreaManager.instance().createArea(areaName, areaDesc) ;
			int rs = AreaManager.instance().addArea(area) ;
			if(rs>0){
				result = "true" ;
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return SUCCESS ;
	}
	
	/**
	 * 添加服务器
	 */
	public String addServer(){
		result = "false" ;
		try {
			Area area = AreaManager.instance().getArea(areaId) ;
			logger.info("区:" + area.getDescrible());
			//创建服务器
			Server server = ServerManager.instance().createServer(areaId, serverName, ip, port, area.getAreaname(), "["+low+"]["+middle+"]["+max+"]") ;
			logger.info("服务器:" + server.getServername());
			//数据库中添加服务器
			int rs = ServerManager.instance().addServer(server) ;
			if(rs>0) {
				result = "true" ;
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return SUCCESS ;
	}
	
	/**
	 * 验证添加区
	 */
	public void validateAddArea() {
		if(StringUtils.isEmpty(areaName)||StringUtils.isEmpty(areaDesc)) {
			this.addFieldError("areaMessageError", getText("com.server.areaNameIsNull"));
			return ;
		}
		if(StringUtils.isEmpty(areaDesc)||StringUtils.isEmpty(areaDesc)) {
			this.addFieldError("areaMessageError", getText("com.server.areaDescIsNull"));
			return ;
		}
		if(AreaManager.instance().getAreaByAreaName(areaName)!=null) {
			this.addFieldError("serverMessageError", getText("cs.platform.serverNameIsExist"));
			return;
		}
	}
	
	/**
	 * 验证添加服务器
	 */
	public void validateAddServer(){
		if(StringUtils.isEmpty(serverName)||StringUtils.isEmpty(serverName)) {
			this.addFieldError("serverMessageError", getText("com.server.serverNameIsNull"));
			return;
		}
		if(StringUtils.isEmpty(ip)||StringUtils.isEmpty(ip)) {
			this.addFieldError("serverMessageError", getText("com.server.ipIsNull"));
			return;
		}
		if(AreaManager.instance().getArea(areaId)==null) {
			this.addFieldError("serverMessageError", getText("com.server.areaIsExist"));
			return;
		}
		if(low <= 0) {
			this.addFieldError("serverMessageError", getText("com.server.lowOffsize"));
			return;
		}
		if(middle <= low) {
			this.addFieldError("serverMessageError", getText("com.server.middleOffsize"));
			return;
		}
		if(max <= middle) {
			this.addFieldError("serverMessageError", getText("com.server.maxOffsize"));
			return;
		}
		if(ServerManager.instance().getServersByServerName(serverName)!=null) {
			this.addFieldError("serverMessageError", getText("cs.platform.serverNameIsExist"));
			return;
		}
	}
	
	/**
	 * 删除服务器
	 */
	public String deleteServer() {
		result = "false" ;
		try {
			Server server = ServerManager.instance().getServerById(id);
			server.setIsdeleted(1);
			int rs = ServerManager.instance().modifyServer(server) ;
			if(rs > 0) {
				result = "true" ;
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return SUCCESS ;
	}
	
	/**
	 * 删除区
	 */
	public String deleteArea() {
		result = "false" ;
		try {
			Area area = AreaManager.instance().getArea(id);
			area.setIsdeleted(1);
			int rs = AreaManager.instance().modifyArea(area) ;
			if(rs > 0) {
				result = "true" ;
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return SUCCESS ;
	}
	
	/**
	 * 开启关闭
	 * @return
	 */
	public String serverOnOff() {
		result = "false" ;
		try {
			Server server = ServerManager.instance().getServerById(serverId);
			server.setOpentime(System.currentTimeMillis());
			/*
			if(server.getIsactive()==ServerActivitiesStatus.INACTIVE.getValue()){
				this.addFieldError("serverConnectError", getText("com.server.serverNotActive"));
				return INPUT;
			}*/
//			feedback = GMCommandUtil.serverOnOff(server);
			feedback = "close";
			if (!StringUtils.isEmpty(feedback)) {
				server.setStatus(server.getStatus()==ServerStatus.OPEN.getValue()? ServerStatus.CLOSE.getValue(): ServerStatus.OPEN.getValue());
				ServerManager.instance().modifyServer(server);
				result = "true" ;
			}
			else {
				this.addFieldError("serverConnectError", getText("com.server.serverConnectError"));
				return INPUT;
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return SUCCESS ;
	}
	
	/**
	 * 激活冻结服务器
	 * @return
	 */
	public String serverToActive() {
		result = "false" ;
		try {
			Server server = ServerManager.instance().getServerById(serverId);
			server.setIsactive(server.getIsactive()==ServerActivitiesStatus.ACTIVE.getValue()? ServerActivitiesStatus.INACTIVE.getValue(): ServerActivitiesStatus.ACTIVE.getValue());
			if(ServerManager.instance().modifyServer(server)==1){
				result = "true" ;
			}else {
				this.addFieldError("serverConnectError", getText("com.server.serverConnectError"));
				return INPUT;
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return SUCCESS ;
	}
	
	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaDesc() {
		return areaDesc;
	}

	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getMiddle() {
		return middle;
	}

	public void setMiddle(int middle) {
		this.middle = middle;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Map<String, Object>> getServersContainer() {
		return serversContainer;
	}

	public void setServersContainer(List<Map<String, Object>> serversContainer) {
		this.serversContainer = serversContainer;
	}
	
}
