package com.login.db.manager;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.login.db.model.admin.Admin;
import com.login.db.model.admin.AdminExample;
import com.login.db.model.admin.AdminMapper;
import com.login.util.CodeUtil;
import com.login.util.DBUtil;

/**
 * 
*
* @Description: TODO	玩家管理类
* @author GYI 
* @Ceatetime 2014年8月12日
*
 */
public class AdminManager {
	private static AdminManager manager ;
	
	public static AdminManager instance(){
		if(manager==null) {
			manager = new AdminManager() ;
		}
		return manager ;
	}
	
	/**
	 * 通过用户名获得用户信息
	 */
	public Admin getAdminByName(String adminName) {
		Admin admin = null ;
		SqlSession session = DBUtil.instance().getSqlSession() ;
		try {
			AdminMapper mapper = session.getMapper(AdminMapper.class) ;
			AdminExample example = new AdminExample() ;
			example.createCriteria().andAdminnameEqualTo(adminName).andIsdeletedEqualTo(0) ;
			List<Admin> admins = mapper.selectByExample(example) ;
			admin = admins==null||admins.size()==0?null:admins.get(0) ;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return admin ;
	}
	
	/**
	 * 通过管理员Id获得用户信息
	 */
	public Admin getAdminById(int Id) {
		Admin admin = null ;
		SqlSession session = DBUtil.instance().getSqlSession() ;
		try {
			AdminMapper mapper = session.getMapper(AdminMapper.class) ;
			AdminExample example = new AdminExample() ;
			example.createCriteria().andIdEqualTo(Id).andIsdeletedEqualTo(0) ;
			List<Admin> admins = mapper.selectByExample(example) ;
			admin = admins==null||admins.size()==0?null:admins.get(0) ;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return admin ;
	}
	
	/**
	 * 获得所有管理员
	 */
	public List<Admin> getAllAdmins(){
		List<Admin> admins = null;
		SqlSession session = DBUtil.instance().getSqlSession();
		try {
			AdminMapper mapper = session.getMapper(AdminMapper.class);
			AdminExample example = new AdminExample();
			example.createCriteria().andIsdeletedEqualTo(0);
			admins = mapper.selectByExample(example);
		} catch (Exception e) {
			throw e ;
		}
		finally{
			session.close();
		}
		return admins;
	}
	
	public Admin createAdmin(String adminname, String password, int security){
		Admin admin = new Admin();
		admin.setId(0);
		admin.setAdminname(adminname);
		admin.setPassword(CodeUtil.Md5(password));
		admin.setSecurity(security);
		admin.setIsdeleted(0);
		return admin;
	}
	
	/**
	 * 添加管理员
	 */
	public int addAdmin(Admin admin){
		SqlSession session = DBUtil.instance().getSqlSession();
		int status = 0;
		try {
			AdminMapper mapper = session.getMapper(AdminMapper.class);
			status = mapper.insert(admin);
			session.commit();
		} catch (Exception e) {
			throw e ;
		}
		finally{
			session.close();
		}
		return status;
	}
	
	/**
	 * 修改管理员
	 */
	public int modifyAdmin(Admin admin){
		SqlSession session = DBUtil.instance().getSqlSession();
		int status = 0;
		try {
			AdminMapper mapper = session.getMapper(AdminMapper.class);
			status = mapper.updateByPrimaryKeySelective(admin);
			session.commit();
		} catch (Exception e) {
			throw e ;
		}
		finally{
			session.close();
		}
		return status;
	}

}
