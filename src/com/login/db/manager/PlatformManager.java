package com.login.db.manager;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.login.db.model.platform.Platform;
import com.login.db.model.platform.PlatformExample;
import com.login.db.model.platform.PlatformMapper;
import com.login.util.DBUtil;

/**
 * 
*
* @Description: TODO	平台管理类
* @author zhaowei 
* @Ceatetime 2014年8月12日
*
 */
public class PlatformManager {
	private static PlatformManager manager ;
	
	
	private PlatformManager() {
	}
	
	public static PlatformManager instance(){
		if(manager==null){
			manager = new PlatformManager() ;
		}
		return manager ;
	}
	
	/**
	 * 	获得所有平台
	 */
	public List<Platform> getAllPlatforms() {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		List<Platform> platforms = null ;
		try {
			PlatformMapper mapper = session.getMapper(PlatformMapper.class) ;
			PlatformExample example = new PlatformExample() ;
			example.createCriteria().andIsdeletedEqualTo(0) ;
			platforms = mapper.selectByExample(example) ;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return platforms ;
	}
	
	/**
	 * 通过平台id获得平台信息
	 */
	public Platform getPlatformById(int platformId) {
		Platform platform = null ;
		SqlSession session = DBUtil.instance().getSqlSession() ;
		try {
			PlatformMapper mapper = session.getMapper(PlatformMapper.class) ;
			platform = mapper.selectByPrimaryKey(platformId) ;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return platform ;
	}
	
	/**
	 * 通过平台名获得平台信息
	 */
	public Platform getPlatformByName(String platformName) {
		Platform platform = null ;
		SqlSession session = DBUtil.instance().getSqlSession() ;
		try {
			PlatformMapper mapper = session.getMapper(PlatformMapper.class) ;
			PlatformExample example = new PlatformExample() ;
			example.createCriteria().andPlatformnameEqualTo(platformName).andIsdeletedEqualTo(0) ;
			List<Platform> platforms = mapper.selectByExample(example) ;
			platform = platforms==null||platforms.size()==0?null:platforms.get(0) ;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return platform ;
	}
	
	public Platform createPlatform(String platformName, String platformKey, String platformDesc) {
		Platform platform = new Platform() ;
		platform.setDescrible(platformDesc);
		platform.setId(0);
		platform.setIsdeleted(0);
		platform.setPlatformkey(platformKey);
		platform.setPlatformname(platformName);
		return platform ;
	}
	
	public int addPlatform(Platform platform) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		int result = 0 ;
		try {
			PlatformMapper mapper = session.getMapper(PlatformMapper.class) ;
			result = mapper.insert(platform) ;
			session.commit();
		} catch (Exception e) {
			result = -1 ;
			throw e ;
		}finally{
			session.close();
		}
		return result ;
	}
	
	public int modifyPlatform(Platform platform) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		int result = 0 ;
		try {
			PlatformMapper platformMapper = session.getMapper(PlatformMapper.class) ;
			result = platformMapper.updateByPrimaryKeySelective(platform) ;
			session.commit();
		} catch (Exception e) {
			result = -1 ;
			throw e ;
		}finally{
			session.close();
		}
		return result ;
	}
	
	public static void main(String[] args) {
		System.out.println(PlatformManager.instance().getPlatformByName("uc"));
	}
}
