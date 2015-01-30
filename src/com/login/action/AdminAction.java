package com.login.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.login.db.manager.AdminManager;
import com.login.db.model.admin.Admin;
import com.login.status.UserSecrurityStatus;
import com.login.util.CodeUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 
 * @Description: TODO 管理员管理控制
 * @author GYI
 * @Ceatetime 2014年8月13日
 * 
 */
public class AdminAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(AdminAction.class) ;
	private static final long serialVersionUID = -4741314376600009022L;

	/**
	 * 结果
	 */
	private String result;

	/**
	 * 管理员Id
	 */
	private int id;

	/**
	 * 管理员名
	 */
	private String adminName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 重复密码
	 */
	private String confirmpassword;

	/**
	 * 旧密码
	 */
	private String oldpassword;

	/**
	 * 权限
	 */
	private int security;

	/**
	 * 玩家
	 */
	private List<Map<String, Object>> adminsContainer;

	/**
	 * 玩家
	 */
	private Map<String, Object> adminContainer;

	public String AdminPage() {
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String adminSelect() {
		result = "false";
		String[] securitys = { getText("cs.admin.ordinary"),
				getText("cs.admin.privilege") };
		try {
			List<Admin> admins = AdminManager.instance().getAllAdmins();
			adminsContainer = (List<Map<String, Object>>) JSON.parse(JSON
					.toJSONString(admins));
			for (int i = 0; i < adminsContainer.size(); i++) {
				Admin admin = admins.get(i);
				adminsContainer.get(i).put("security",
						securitys[admin.getSecurity()]);
			}
			result = "true";
		} catch (Exception e) {
			logger.error(e, e);
			return INPUT;
		}
		return SUCCESS;
	}

	public String adminAdd() {
		result = "false";
		try {
			Admin admin = AdminManager.instance().createAdmin(adminName,
					password, security);
			if (AdminManager.instance().addAdmin(admin) == 1) {
				result = "true";
			}
		} catch (Exception e) {
			logger.error(e, e);
			return INPUT;
		}
		return SUCCESS;
	}

	public void validateAdminAdd() {
		if (StringUtils.isEmpty(adminName)) {
			this.addFieldError("adminMessageError",
					getText("cs.admin.adminNameMessageIsNull"));
			return;
		}

		if (StringUtils.isEmpty(password)) {
			this.addFieldError("adminMessageError",
					getText("cs.admin.passwordMessageIsNull"));
			return;
		}
		if (!password.equals(confirmpassword)) {
			this.addFieldError("adminMessageError",
					getText("cs.admin.passwordMessageIsNotEqual"));
			return;
		} else if (AdminManager.instance().getAdminByName(adminName) != null
				&& AdminManager.instance().getAdminByName(adminName).getId() != id) {
			this.addFieldError("adminMessageError",
					getText("cs.admin.adminNameMessageIsExist"));
			return;
		}
		if (AdminManager.instance().getAdminByName(adminName) != null) {
			this.addFieldError("adminMessageError",
					getText("cs.admin.adminNameMessageIsExist"));
			return;
		}
	}

	@SuppressWarnings("unchecked")
	public String adminModifyPage() {
		result = "false";
		String[] securitys = { getText("cs.admin.ordinary"),
				getText("cs.admin.privilege") };
		try {
			Admin admin = AdminManager.instance().getAdminById(id);
			adminContainer = (Map<String, Object>) JSON.parse(JSON
					.toJSONString(admin));
			adminContainer.put("securityDesc", securitys[admin.getSecurity()]);
			result = "true";
		} catch (Exception e) {
			logger.error(e, e);
			return INPUT;
		}
		return SUCCESS;
	}

	public String adminModify() {
		result = "false";
		try {
			Admin admin = AdminManager.instance().getAdminById(id);
			admin.setAdminname(adminName);
			// admin.setPassword(CodeUtil.Md5(password));
			admin.setSecurity(security);
			if (AdminManager.instance().modifyAdmin(admin) == 1) {
				result = "true";
			}
		} catch (Exception e) {
			logger.error(e, e);
			return INPUT;
		}
		return SUCCESS;
	}

	public String adminPasswordModify() {
		result = "false";
		try {
			Admin admin = AdminManager.instance().getAdminById(id);
			admin.setPassword(CodeUtil.Md5(password));
			if (AdminManager.instance().modifyAdmin(admin) == 1) {
				result = "true";
			}
		} catch (Exception e) {
			logger.error(e, e);
			return INPUT;
		}
		return SUCCESS;
	}

	public void validateAdminModify() {
		if (StringUtils.isEmpty(adminName)) {
			this.addFieldError("adminMessageError",
					getText("cs.admin.adminNameMessageIsNull"));
			return;
		} else if (AdminManager.instance().getAdminByName(adminName) != null
				&& AdminManager.instance().getAdminByName(adminName).getId() != id) {
			this.addFieldError("adminMessageError",
					getText("cs.admin.adminNameMessageIsExist"));
			return;
		}
		if (!String.valueOf(security).equals(
				UserSecrurityStatus.ORDINARY.getValue())
				&& !String.valueOf(security).equals(
						UserSecrurityStatus.PRIVILEGE.getValue())) {
			this.addFieldError("adminMessageError",
					getText("cs.admin.adminNameMessageIsNotLegal"));
			return;
		}
	}

	public void validateAdminPasswordModify() {

		if (!AdminManager.instance().getAdminById(id).getPassword()
				.equals(CodeUtil.Md5(oldpassword))) {
			this.addFieldError("adminMessageError",
					getText("cs.admin.passwordMessageIsError"));
			return;
		}
		if (StringUtils.isEmpty(password)) {
			this.addFieldError("adminMessageError",
					getText("cs.admin.passwordMessageIsNull"));
			return;
		}
		if (!password.equals(confirmpassword)) {
			this.addFieldError("adminMessageError",
					getText("cs.admin.passwordMessageIsNotEqual"));
			return;
		}
	}

	public String adminDelete() {
		result = "false";
		try {
			Admin admin = AdminManager.instance().getAdminById(id);
			admin.setIsdeleted(1);
			if (AdminManager.instance().modifyAdmin(admin) == 1) {
				result = "true";
			}
		} catch (Exception e) {
			logger.error(e, e);
			return INPUT;
		}
		return SUCCESS;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSecurity() {
		return security;
	}

	public void setSecurity(int security) {
		this.security = security;
	}

	public List<Map<String, Object>> getAdminsContainer() {
		return adminsContainer;
	}

	public void setAdminsContainer(List<Map<String, Object>> adminsContainer) {
		this.adminsContainer = adminsContainer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, Object> getAdminContainer() {
		return adminContainer;
	}

	public void setAdminContainer(Map<String, Object> adminContainer) {
		this.adminContainer = adminContainer;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

}
