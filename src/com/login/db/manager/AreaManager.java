package com.login.db.manager;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.login.db.model.area.Area;
import com.login.db.model.area.AreaExample;
import com.login.db.model.area.AreaMapper;
import com.login.util.DBUtil;

/**
 * 
*
* @Description: TODO	区管理
* @author zhaowei 
* @Ceatetime 2014年8月13日
*
 */
public class AreaManager {
	private static AreaManager manager ;
	private AreaManager() {
	}
	
	public static AreaManager instance(){
		if(manager==null) {
			manager = new AreaManager() ;
		}
		return manager ;
	}
	
	/**
	 * 获得所有区域
	 */
	public List<Area> getAllArea() {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		List<Area> areas = null ;
		try {
			AreaMapper mapper = session.getMapper(AreaMapper.class) ;
			AreaExample example = new AreaExample() ;
			example.createCriteria().andIsdeletedEqualTo(0) ;
			areas = mapper.selectByExample(example) ;
		} catch (Exception e) {
			throw e ;
		}finally {
			session.close(); 
		}
		return areas ;
	}
	
	/**
	 * 根据区域id获得区域
	 */
	public Area getArea(int areaId) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		Area area = null ;
		try {
			AreaMapper mapper = session.getMapper(AreaMapper.class) ;
			area = mapper.selectByPrimaryKey(areaId) ;
		} catch (Exception e) {
			throw e ;
		}finally {
			session.close(); 
		}
		return area ;
	}
	
	/**
	 * 根据区域名获得区域
	 */
	public Area getAreaByAreaName(String areaName) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		Area area = null ;
		try {
			AreaMapper mapper = session.getMapper(AreaMapper.class) ;
			AreaExample example = new AreaExample() ;
			example.createCriteria().andAreanameEqualTo(areaName).andIsdeletedEqualTo(0) ;
			List<Area> areas = mapper.selectByExample(example) ;
			area = areas==null||areas.size()==0?null:areas.get(0) ;
		} catch (Exception e) {
			throw e ;
		}finally {
			session.close(); 
		}
		return area ;
	}
	
	public int addArea(Area area) {
		int result = 0 ;
		SqlSession session = DBUtil.instance().getSqlSession() ;
		try {
			AreaMapper mapper = session.getMapper(AreaMapper.class) ;
			result = mapper.insert(area) ;
			session.commit();
		} catch (Exception e) {
			result = -1 ;
			throw e ;
		}finally {
			session.close(); 
		}
		return result;
	}
	
	/**
	 * 添加区域
	 */
	public Area createArea(String areaName, String areaDesc) {
		Area area = new Area() ;
		area.setAreaname(areaName);
		area.setDescrible(areaDesc);
		area.setId(0);
		area.setIsdeleted(0);
		return area ;
	}
	
	public int modifyArea(Area area) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		int result = 0 ;
		try {
			AreaMapper areaMapper = session.getMapper(AreaMapper.class) ;
			result = areaMapper.updateByPrimaryKeySelective(area) ;
			session.commit();
		} catch (Exception e) {
			result = -1 ;
			throw e ;
		}finally{
			session.close();
		}
		return result ;
	}
}
