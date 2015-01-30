package com.login.interceptors;

import javax.servlet.http.HttpServletRequest;     
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;     

import com.login.status.UserSecrurityStatus;
import com.login.util.CodeUtil;
import com.login.util.StringUtil;
import com.opensymphony.xwork2.Action;     
import com.opensymphony.xwork2.ActionInvocation;     
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
  
public class RightInterceptor extends MethodFilterInterceptor {  
	private static final long serialVersionUID = 1L;
 
	public void destroy() {  
    }  
    public void init() {  
    	
    }     
	@Override
	protected String doIntercept(ActionInvocation invocation) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession() ;
		String userId = (String) session.getAttribute("USERID");
		String secrurity = (String) session.getAttribute("SECRURITY");
		String resultCode = Action.ERROR;
		try {
			if(!StringUtil.isBlank(userId)&&secrurity.equals(CodeUtil.Md5((UserSecrurityStatus.PRIVILEGE).getValue()))){
				resultCode = invocation.invoke();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultCode;
	}  
}