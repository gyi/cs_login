package com.login.db.manager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.login.db.model.server.Server;
import com.login.db.model.server.ServerExample;
import com.login.db.model.server.ServerMapper;
import com.login.status.ServerActivitiesStatus;
/*import com.login.status.ServerPlayerStatus;*/
import com.login.util.DBUtil;
import com.login.util.StringUtil;
import com.login.vo.ServerInfo;

/**
 * 
*
* @Description: TODO	服务器管理
* @author zhaowei 
* @Ceatetime 2014年8月12日
*
 */
public class ServerManager {
	private static ServerManager manager ;
	private ServerManager() {
	}
	
	public static ServerManager instance(){
		if(manager==null){
			manager = new ServerManager() ;
		}
		return manager ;
	}
	
	/**
	 * 	获得所有区服
	 */
	public List<Server> getAllServers() {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		List<Server> servers = null ;
		try {
			ServerMapper mapper = session.getMapper(ServerMapper.class) ;
			ServerExample example = new ServerExample() ;
			example.setOrderByClause("addtime desc"); 
			example.createCriteria().andIsdeletedEqualTo(0) ;
			servers = mapper.selectByExample(example) ;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return servers ;
	}
	
	public List<Server> getAllServersContainDeleted() {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		List<Server> servers = null ;
		try {
			ServerMapper mapper = session.getMapper(ServerMapper.class) ;
			ServerExample example = new ServerExample() ;
			example.setOrderByClause("addtime desc"); 
			example.createCriteria() ;
			servers = mapper.selectByExample(example) ;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return servers ;
	}
	
	/**
	 * 通过区获得区服
	 */
	public List<Server> getServersByAreaId(int areaId) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		List<Server> servers = null ;
		try {
			ServerMapper mapper = session.getMapper(ServerMapper.class) ;
			ServerExample example = new ServerExample() ;
			example.setOrderByClause("addtime desc"); 
			example.createCriteria().andAreaidEqualTo(areaId).andIsdeletedEqualTo(0) ;
			servers = mapper.selectByExample(example) ;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return servers;
	}
	
	/**
	 * 通过区名获得区服
	 */
	public Server getServersByServerName(String serverName) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		Server server = null ;
		try {
			ServerMapper mapper = session.getMapper(ServerMapper.class) ;
			ServerExample example = new ServerExample() ;
			example.setOrderByClause("addtime desc");
			example.createCriteria().andServernameEqualTo(serverName).andIsdeletedEqualTo(0) ;
			List<Server> servers = mapper.selectByExample(example) ;
			server = servers==null||servers.size()==0?null:servers.get(0) ;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return server;
	}
	
	/**
	 * 获得指定状态的服务器(激活)
	 */
	public List<Server> getServersByIsActive(ServerActivitiesStatus active) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		List<Server> servers = null ;
		try {
			ServerMapper mapper = session.getMapper(ServerMapper.class) ;
			ServerExample example = new ServerExample() ;
			example.setOrderByClause("addtime desc");
			example.createCriteria().andIsactiveEqualTo(active.getValue()).andIsdeletedEqualTo(0) ;
			servers = mapper.selectByExample(example) ;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return servers;
	}
	
	/**
	 * 根据服务器id找到服务器
	 */
	public Server getServerById(int serverId) {
		Server server = null ;
		SqlSession session = DBUtil.instance().getSqlSession() ;
		try {
			ServerMapper serverMapper = session.getMapper(ServerMapper.class) ;
			server = serverMapper.selectByPrimaryKey(serverId) ;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return server ;
	}
	
	/**
	 * 添加区服
	 */
	public int addServer(Server server) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		int result = 0 ;
		try {
			ServerMapper serverMapper = session.getMapper(ServerMapper.class) ;
			result = serverMapper.insert(server) ;
			session.commit();
		} catch (Exception e) {
			result = -1 ;
			throw e ;
		}finally{
			session.close();
		}
		return result ;
	}
	
	/**
	 * 创建服务器
	 * @param string 
	 */
	public Server createServer(int areaId, String serverName, String ip, int port, String areaName, String conditionset) {
		Server server = new Server() ;
		server.setAreaid(areaId);
		server.setAreaname(areaName);
		server.setId(0);
		server.setIp(ip);
		server.setPort(port);
		server.setServername(serverName);
		server.setConditionset(conditionset);
		server.setIsactive(0);//默认为未激活状态
		server.setIsdeleted(0);//默认为未删除状态
		server.setStatus(1);//默认状态为关闭状态
		server.setSecritkey(StringUtil.randUUID());
		server.setAddtime(System.currentTimeMillis());
		return server ;
	}
	
	/**
	 * 修改区服
	 */
	public int modifyServer(Server server) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		int result = 0 ;
		try {
			ServerMapper serverMapper = session.getMapper(ServerMapper.class) ;
			result = serverMapper.updateByPrimaryKeySelective(server) ;
			session.commit();
		} catch (Exception e) {
			result = -1 ;
			throw e ;
		}finally{
			session.close();
		}
		return result ;
	}
	
	/**
	 * 将区服转换成区服信息vo
	 */
	public Map<String, List<ServerInfo>> covertToServerInfos(List<Server> servers) throws NumberFormatException, InstantiationException, IllegalAccessException, IOException {
		Map<String, List<ServerInfo>> loginResultMap = new HashMap<String, List<ServerInfo>>() ;
		for(Server server : servers) {
			ServerInfo serverInfo = new ServerInfo() ;
			serverInfo.setId(server.getId());
			serverInfo.setServername(server.getServername());
			//发送GM命令获取人数
			//String[] conditionArray = ServerPlayerStatus.toStrings();
//			int roleNum = Integer.valueOf(GMCommandUtil.getServerOnlinePlayerCondition(server, conditionArray)) ;
			int roleNum = Integer.valueOf(3) ;
			serverInfo.setState(roleNum);
			if(loginResultMap.containsKey(server.getAreaname())) {
				List<ServerInfo> serversList = loginResultMap.get(server.getAreaname()) ;
				serversList.add(serverInfo) ;
			}else {
				List<ServerInfo> serversList = new ArrayList<ServerInfo>() ;
				serversList.add(serverInfo) ;
				loginResultMap.put(server.getAreaname(), serversList) ;
			}
		}
		return loginResultMap ;
	}
	
	/**
	 * 根据ip和端口找到指定服务器
	 */
	public Server getServerByIpAndPort(String ip, int port){
		SqlSession session = DBUtil.instance().getSqlSession() ;
		Server server = null ;
		try {
			ServerMapper serverMapper = session.getMapper(ServerMapper.class) ;
			ServerExample example = new ServerExample() ;
			example.createCriteria().andIpEqualTo(ip).andPortEqualTo(port) ;
			List<Server> servers = serverMapper.selectByExample(example) ;
			server = servers!=null&&servers.size()!=0?servers.get(0):null ;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return server ;
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
	}
}
