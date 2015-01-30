package com.login.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.login.util.StringUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
  
public class LoginInterceptor extends MethodFilterInterceptor {  
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
		request.setAttribute("LANGUAGE", request.getLocale().toString());
		String resultCode = "resultLogin";
		try {
			if(!StringUtil.isBlank(userId)){
				resultCode = invocation.invoke();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultCode;
	}  
}