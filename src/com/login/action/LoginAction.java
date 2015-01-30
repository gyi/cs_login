package com.login.action;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.login.config.SystemConfigManager;
import com.login.container.IPContainer;
import com.login.db.manager.ClientVersionManager;
import com.login.db.manager.PlatformManager;
import com.login.db.manager.PlayerManager;
import com.login.db.manager.ServerManager;
import com.login.db.model.clientversion.ClientVersion;
import com.login.db.model.platform.Platform;
import com.login.db.model.player.Player;
import com.login.db.model.server.Server;
import com.login.status.LoginResultStatus;
import com.login.status.ServerActiveIsSuccessStatus;
import com.login.status.ServerActivitiesStatus;
import com.login.util.IPUtil;
import com.login.util.JsonHelper;
import com.login.util.MD5;
import com.login.util.StringUtil;
import com.login.vo.LoginResult;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 
 * @Description: TODO 登陆接口
 * @author zhaowei
 * 
 */
public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = -2760619549668208790L;

	private Logger logger = Logger.getLogger(LoginAction.class);
	/**
	 * 平台id
	 */
	private String platformId;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 验证key
	 */
	private String key;

	/**
	 * 端口
	 */
	private String port;

	/**
	 * http端口
	 */
	private String httpport;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 结果
	 */
	private String result;

	/**
	 * 服务器id
	 */
	private String serverId;
	
	/**
	 * ip
	 */
	private String ip ;
	
	/**
	 * 版本
	 */
	private String version ;

	/**
	 * 客户端请求获得服务器列表
	 */
	public String logincheck() {
		try {
			LoginResult loginResult = new LoginResult();
			long now = System.currentTimeMillis();
			HttpServletRequest request = ServletActionContext.getRequest() ;
			String ip = IPUtil.getRealIp(request) ;
			logger.info("客户端请求获得服务器列表, ip:" + ip);
			if(IPContainer.instance().canIn(ip.trim())) {
				if (StringUtils.isEmpty(platformId) || !StringUtil.isNumric(platformId) || StringUtils.isEmpty(userId)
						|| StringUtils.isEmpty(key)) {
					loginResult.setResult(LoginResultStatus.FAIL);
					logger.info("客户端请求获得服务器数据失败, 平台id或用户id为空或部位数字,或者key值为空");
				} else {
					Platform platform = PlatformManager.instance().getPlatformById(Integer.valueOf(platformId));
					if (platform == null || !key.trim().equals(platform.getPlatformkey())) {
						loginResult.setResult(LoginResultStatus.FAIL);
						logger.info("客户端请求获得服务器数据失败,不存在指定id=" + platformId + "的区服") ;
					} else {
						Player player = PlayerManager.instance().getPlayerByUserIdAndPlatformId(userId, platform.getId());
						int rs = 0;
						if (player == null) {
							logger.info("客户端请求获得服务器数据,正在创建玩家id") ;
							player = PlayerManager.instance().createPlayer(platform.getId(), userId, now);
							rs = PlayerManager.instance().addPlayer(player);

						} else {
							logger.info("客户端请求获得服务器数据,更新最后登陆时间") ;
							rs = PlayerManager.instance().updateLoginTime(player.getPlayerid(), now);
						}

						if (rs < 0) {
							logger.info("客户端请求获得服务器数据失败，连接时间超时") ;
							loginResult.setResult(LoginResultStatus.FAIL);
						} else {
							// 获得服务器列表
							List<Server> servers = ServerManager.instance().getServersByIsActive(
									ServerActivitiesStatus.ACTIVE);
							loginResult.setResult(LoginResultStatus.SUCCESS);
							loginResult.setServers(ServerManager.instance().covertToServerInfos(servers));
							HttpSession session = ServletActionContext.getRequest().getSession();// 保存session信息
							session.setAttribute("USERID", userId);
							session.setAttribute("PLATFORMID", platformId);
							session.setAttribute("PLAYERID", String.valueOf(player.getPlayerid()));
							logger.info("客户端请求获得服务器数据成功,userId=" + userId + ", platformId=" + platformId + ", playerId=" + player.getPlayerid()) ;
						}
					}
				}
			}else {
				logger.info("登陆频率过高");
				loginResult.setResult(LoginResultStatus.FAIL);
			}
			
			result = JsonHelper.toFastJSONString(loginResult);
			logger.info("客户端请求获得服务器数据: " + result);
			result = URLEncoder.encode(result, "UTF-8") ;
		} catch (Exception e) {
			logger.error(e, e);
		}
		return SUCCESS;
	}

	// 2表示失败，1表示成功
	public String findServerInfo() {
		try {
			logger.info("请求进入游戏服");
			HttpServletRequest request = ServletActionContext.getRequest() ;
			String ip = IPUtil.getRealIp(request) ;
			Map<String, String> resultMap = new HashMap<String, String>();
			if(IPContainer.instance().canIn(ip.trim())) {
				HttpSession session = ServletActionContext.getRequest().getSession();// 保存session信息
				String userId = (String) session.getAttribute("USERID");
				String platformId = (String) session.getAttribute("PLATFORMID");
				String playerId = (String) session.getAttribute("PLAYERID");
				if (userId == null || platformId == null || playerId == null) {
					resultMap.put("result", "2");
					resultMap.put("desc", "you have no session, failed!!!");
					logger.info("没有session, 请求进入游戏服失败");
				} else {
					if (StringUtil.isBlank(serverId) || !StringUtil.isNumric(serverId)) {
						resultMap.put("result", "2");
						resultMap.put("desc", "serverId must be number, failed!!!");
						logger.info("服务器Id必须为数字, 请求进入游戏服失败");
					} else {
						Server server = ServerManager.instance().getServerById(Integer.valueOf(serverId));
						if (server == null) {
							resultMap.put("result", "2");
							resultMap.put("desc", "server is not exsit, failed!!!");
							logger.info("服务器不存在, 请求进入游戏服失败");
						} else {
							long time = System.currentTimeMillis() ;
							resultMap.put("result", "1");
							resultMap.put("desc", "successfully");
							resultMap.put("ip", server.getIp());
							resultMap.put("port", String.valueOf(server.getPort()));
							resultMap.put("playerId", playerId);
							resultMap.put("globalKey", MD5.md5_16(server.getSecritkey(), playerId, time));
							resultMap.put("logintime", String.valueOf(time));
							logger.info("进入游戏服验证成功");
						}
					}
				}
			}else {
				resultMap.put("result", "2");
				resultMap.put("desc", "http request too many, failed!!!");
				logger.info("请求命令频率过高");
			}
			

			result = JsonHelper.toFastJSONString(resultMap);
			result = URLEncoder.encode(result, "UTF-8") ;
		} catch (Exception e) {
			logger.error(e, e);
		}
	
		return SUCCESS;
	}

	/**
	 * 激活服务器 1：是激活状态
	 */
	public String sensitizeServer() {
		Map<String, String> resultMap = new HashMap<String, String>();// 结果map
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = IPUtil.getRealIp(request);
		try {
			// 端口必须存在并且必须为数字
			if (StringUtil.isExsitBlank(port, httpport) || !StringUtil.isNumric(port, httpport)) {
				resultMap.put("result", ServerActiveIsSuccessStatus.FAIL.getValue());
				resultMap.put("desc", "port must not be null or be number");
			} else {
				Server server = ServerManager.instance().getServerByIpAndPort(ip, Integer.valueOf(port));// 通过ip和端口获得区服
				// 判断数据库中是否存在区服
				if (server == null) {
					resultMap.put("result", ServerActiveIsSuccessStatus.FAIL.getValue());
					resultMap.put("desc", "server is not exsit");
				} else {
					server.setIsactive(1);
					server.setHttpport(Integer.valueOf(httpport));
					int rs = ServerManager.instance().modifyServer(server);
					// 判断是否修改成功
					if (rs < 0) {
						resultMap.put("result", ServerActiveIsSuccessStatus.FAIL.getValue());
						resultMap.put("desc", "Activation failed!!!");
					} else {
						resultMap.put("result", ServerActiveIsSuccessStatus.SUCCESS.getValue());
						resultMap.put("desc", "Activation successful!!!");
						resultMap.put("SECRET_KEY", server.getSecritkey());
						resultMap.put("GAME_ID", String.valueOf(server.getId()));
					}
				}
			}
		} catch (Exception e) {
			resultMap.put("result", ServerActiveIsSuccessStatus.FAIL.getValue());
			logger.error(e, e);
		}

		try {
			result = JsonHelper.obj2Json(resultMap);
			result = URLEncoder.encode(result, "UTF-8") ;
		} catch (Exception e) {
			logger.error(e, e);
		}

		return SUCCESS;
	}

	/**
	 * 改变服务器状态（开启/关闭）
	 */
	public String changeServerState() {
		Map<String, String> resultMap = new HashMap<String, String>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String ipstr = IPUtil.getRealIp(request);
		logger.info("请求改变服务器状态");
		try {
			if(IPContainer.instance().canIn(ipstr.trim())) {
				// 判断是否空或者为数字
				if (StringUtil.isExsitBlank(status, port, ip) || !StringUtil.isNumric(status, port)) {
					resultMap.put("result", "false");
					resultMap.put("desc", "status or port or ip must not be null or be number");
					logger.info("服务器状态，端口以及ip必须存在并且相应参数应为数字, 请求改变服务器状态失败");
				} else {
//								HttpServletRequest request = ServletActionContext.getRequest();
//								String ip = IPUtil.getRealIp(request);
					Server server = ServerManager.instance().getServerByIpAndPort(ip, Integer.valueOf(port));// 通过ip和端口获得区服
					if (server == null) {
						resultMap.put("result", "false");
						resultMap.put("desc", "server is not exsit!!!");
						logger.info("服务器不存在, 请求改变服务器状态失败");
					} else {
						logger.info("修改状态服务器:" + server.getServername() + ", ip:" + ip + ", port:" + port + ", status:" + status);
						int rs = -1;
						// 0表示开启,1表示关闭
						switch (Integer.valueOf(status)) {
						case 0:
							if (StringUtil.isBlank(httpport) || !StringUtil.isNumric(httpport)) {
								resultMap.put("result", "false");
								resultMap.put("desc", "httpport must not be null or be number");
								logger.info("服务器不存在, 请求改变服务器状态失败");
							} else {
								server.setHttpport(Integer.valueOf(httpport));
								server.setStatus(0);
								rs = ServerManager.instance().modifyServer(server);
								// 判断是否修改成功
								if (rs > 0) {
									resultMap.put("SECRET_KEY", server.getSecritkey());
									resultMap.put("GAME_ID", String.valueOf(server.getId()));
									logger.info("请求改变服务器状态成功, SECRET_KEY:" + server.getSecritkey() + ", GAME_ID:" + server.getId());
								}
							}

							break;
						case 1:
							server.setStatus(1);
							rs = ServerManager.instance().modifyServer(server);
							if (rs < 0) {
								resultMap.put("desc", "modify database failed!!");
								logger.info("数据库修改失败, 请求改变服务器状态失败");
							}
							break;
						default:
							resultMap.put("result", "false");
							break;
						}
						if (rs > 0) {
							resultMap.put("result", "true");
							resultMap.put("desc", "successfully");
						} else {
							resultMap.put("result", "false");
						}
					}
				}
			}else {
				resultMap.put("result", "false");
				resultMap.put("desc", "http request too many, failed!!!!!!");
				logger.info("请求命令频率过高");
			}
		} catch (Exception e) {
			resultMap.put("result", "false");
			logger.error(e, e);
		}
		try {
			result = JsonHelper.obj2Json(resultMap);
			result = URLEncoder.encode(result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getAllServerInfo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = IPUtil.getRealIp(request);
		String ips = SystemConfigManager.instance().getPropertiesValue("login.servers") ;
		Map<String, String> resultMap = new HashMap<String, String>();
		logger.info("请求所有区服信息,来之ip:" + ip);
		try {
			boolean has = false ;
			for(String ipone : ips.split(",")) {
				if(ipone.trim().equals(ip)) {
					has = true ;
					break ;
				}
			}
			if(has) {
				resultMap.put("result", "0") ;//0表示成功
				String res = JsonHelper.toFastJSONString(ServerManager.instance().getAllServersContainDeleted()) ;
				resultMap.put("servers", res) ;
				logger.info("请求所有区服信息成功, servers结果:" + res);
			}else {
				resultMap.put("result", "1") ;//1表示失败
				logger.info("请求所有区服信息失败, ip:" + ip);
			}
		} catch (Exception e) {
			resultMap.put("result", "1") ;//1表示失败
			logger.error(e, e);
		}
		try {
			result = JsonHelper.toFastJSONString(resultMap);
			result = URLEncoder.encode(result, "UTF-8");
			logger.info("请求所有区服信息成功, ip:" + ip + ", 结果:" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 获得以后更新版本
	 */
	public String getVersionResult(){
		logger.info("请求版本号,来之ip:" + ip);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(StringUtil.isBlank(version)){
			resultMap.put("result", "1") ;//失败
			logger.info("请求版本失败，版本不能为空");
		}else {
			ClientVersion cversion = ClientVersionManager.instance().getVersion(version) ;
			if(cversion==null){
				resultMap.put("result", "1") ;//失败
				logger.info("请求版本失败，版本不存在");
			}else {
				resultMap.put("result", "0") ;//成功
				List<ClientVersion> clientVersions = ClientVersionManager.instance().getNextVersions(cversion) ;
				if(clientVersions==null)
					resultMap.put("data", clientVersions) ;
				else 
					resultMap.put("data", ClientVersionManager.instance().packageClientVersionVo(clientVersions)) ;
			}
		}
		
		result = JsonHelper.toFastJSONString(resultMap) ;
		logger.info("请求版本号, ip:" + ip + ", 结果:" + result);
		return SUCCESS;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHttpport() {
		return httpport;
	}

	public void setHttpport(String httpport) {
		this.httpport = httpport;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
