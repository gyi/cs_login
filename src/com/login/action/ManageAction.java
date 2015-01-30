package com.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.login.container.IPContainer;
import com.login.db.manager.AdminManager;
import com.login.db.model.admin.Admin;
import com.login.status.UserSecrurityStatus;
import com.login.util.CodeUtil;
import com.login.util.IPUtil;
import com.login.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 
 * @Description: TODO 系统管理控制
 * @author zhaowei
 * @Ceatetime 2014年8月13日
 * 
 */
public class ManageAction extends ActionSupport {

	private static final long serialVersionUID = 5584777843621924110L;
	
	private static Logger logger = Logger.getLogger(ManageAction.class) ;

	private String adminname ;
	
	private String password ;
	
	public String index() {
		return SUCCESS;
	}

	public String login() {
		String loginResult = INPUT;
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String ip = IPUtil.getRealIp(request) ;
			if(!IPContainer.instance().canIn(ip)){
				logger.info("请求太频繁");
				return INPUT ;
			}
			
			Admin admin = AdminManager.instance().getAdminByName(adminname);
			if(admin!=null&&admin.getPassword().equals(CodeUtil.Md5(password))){
				HttpSession session = request.getSession() ;
				session.setAttribute("USERID", String.valueOf(admin.getId()));
				session.setAttribute("SECRURITY", CodeUtil.Md5(String.valueOf(admin.getSecurity())));
				session.setAttribute("SECRURITYSTYLE", String.valueOf(admin.getSecurity()).equals(UserSecrurityStatus.PRIVILEGE.toString())? UserSecrurityStatus.PRIVILEGESHOW.toString(): UserSecrurityStatus.ORDINARYSHOW.toString());
				loginResult = SUCCESS;
			}
		} catch (Exception e) {
			logger.error(e, e);
			return INPUT;
		}
		return loginResult;
	}
	
	public void validateLogin() {
		if(StringUtil.isBlank(adminname, password)){
			this.addFieldError("nameorpass", "username or password is null");
		}
	}
	
	public String loginSuccess() {
		return SUCCESS;
	}

	public String loginOut() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}
	
	public String error() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("LANGUAGE", request.getLocale().toString());
		return SUCCESS;
	}

	public String manage() {
		return SUCCESS;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
