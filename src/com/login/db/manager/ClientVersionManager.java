package com.login.db.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.login.db.model.clientversion.ClientVersion;
import com.login.db.model.clientversion.ClientVersionExample;
import com.login.db.model.clientversion.ClientVersionMapper;
import com.login.util.DBUtil;
import com.login.util.DateUtil;
import com.login.vo.ClientVersionVo;

/**
 * 
*
* @Description: TODO	客户端版本管理
* @author zhaowei 
* @Ceatetime 2014年9月22日
*
 */
public class ClientVersionManager {
	private Logger logger = Logger.getLogger(ClientVersionManager.class) ;
	
	private static ClientVersionManager manager ;
	
	private ClientVersionManager() {
	}
	
	public static ClientVersionManager instance(){
		if(manager==null) {
			manager = new ClientVersionManager() ;
		}
		return manager;
	}
	
	/**
	 * 获取所有客户端版本号
	 */
	public List<ClientVersion> getAllClientVersions() {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		List<ClientVersion> clientVersions = null ;
		try {
			ClientVersionMapper mapper = session.getMapper(ClientVersionMapper.class) ;
			ClientVersionExample example = new ClientVersionExample() ;
			example.setOrderByClause("addtime desc");
			example.createCriteria().andIsdeletedEqualTo(0) ;
			clientVersions = mapper.selectByExample(example) ;
		} catch (Exception e) {
			throw e ;
		}finally {
			session.close(); 
		}
		return clientVersions ;
	}
	
	/**
	 * 插入客户端版本
	 */
	public int insert(ClientVersion version) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		int result = -1 ;
		try {
			ClientVersionMapper mapper = session.getMapper(ClientVersionMapper.class) ;
			result = mapper.insert(version) ;
			session.commit();
		} catch (Exception e) {
			throw e ;
		}finally {
			session.close(); 
		}
		return result;
	}
	
	/**
	 * 根据版本号获得版本信息
	 */
	public ClientVersion getVersion(String version) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		ClientVersion clientVersion = null ;
		try {
			ClientVersionMapper mapper = session.getMapper(ClientVersionMapper.class) ;
			ClientVersionExample example = new ClientVersionExample() ;
			example.createCriteria().andIsdeletedEqualTo(0).andVersionEqualTo(version) ;
			List<ClientVersion> clientVersions = mapper.selectByExample(example) ;
			clientVersion = clientVersions==null||clientVersions.size()==0 ? null:clientVersions.get(0) ;
		} catch (Exception e) {
			throw e ;
		}finally {
			session.close();
		}
		return clientVersion;
	}
	
	/**
	 * 根据版本找到后来版本的列表
	 */
	public List<ClientVersion> getNextVersions(ClientVersion version) {
		List<ClientVersion> clientVersions = new ArrayList<ClientVersion>() ;
		SqlSession session = DBUtil.instance().getSqlSession() ;
		try {
			ClientVersionMapper mapper = session.getMapper(ClientVersionMapper.class) ;
			ClientVersionExample example = new ClientVersionExample() ;
			example.setOrderByClause("addtime desc");
			example.createCriteria().andIsdeletedEqualTo(0).andAddtimeGreaterThan(version.getAddtime()) ;
			clientVersions = mapper.selectByExample(example) ;
		} catch (Exception e) {
			throw e ;
		}finally {
			session.close();
		}
		return clientVersions;
	}
	
	/**
	 * 删除版本号
	 */
	public int deleteVersion(String version) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		int rs = 0 ;
		try {
			ClientVersion record = new ClientVersion() ;
			record.setIsdeleted(1);
			ClientVersionMapper mapper = session.getMapper(ClientVersionMapper.class) ;
			ClientVersionExample example = new ClientVersionExample() ;
			example.createCriteria().andVersionEqualTo(version) ;
			rs = mapper.updateByExampleSelective(record, example) ;
			session.commit();
		} catch (Exception e) {
			throw e ;
		}finally {
			session.close();
		}
		return rs;
	}
	
	/**
	 * 创建版本
	 */
	public ClientVersion createVersion(String version, int isMust) {
		ClientVersion clientVersion = new ClientVersion() ;
		clientVersion.setAddtime(System.currentTimeMillis());
		clientVersion.setIsdeleted(0);
		clientVersion.setIsmust(isMust);
		clientVersion.setVersion(version);
		logger.info("创建版本");
		return clientVersion;
	}
	
	/**
	 * 构建vo
	 */
	public List<ClientVersionVo> packageClientVersionVo(List<ClientVersion> versions){
		List<ClientVersionVo> clientVersionVos = new ArrayList<ClientVersionVo>() ;
		for(ClientVersion version : versions){
			ClientVersionVo clientVersionVo = new ClientVersionVo() ;
			clientVersionVo.setMust(version.getIsmust()==0?true:false);
			clientVersionVo.setTime(DateUtil.convertLong2StringInModel(version.getAddtime(), "yyyy-MM-dd HH:mm:ss"));
			clientVersionVo.setVersion(version.getVersion());
			clientVersionVos.add(clientVersionVo) ;
		}
		return clientVersionVos;
	}
}
