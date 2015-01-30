package com.login.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

public class Log4jInitServlet extends HttpServlet {

	private static final long serialVersionUID = 3954229572889403414L;
	private final String WEB_APP_ROOT_DEFAULT = "webapp.root";
	@Override
	public void init() throws ServletException {
		String prefix = getServletContext().getRealPath("/");
		  String webAppRootKey = getServletConfig().getInitParameter(
		    "webAppRootKey");
		  if(webAppRootKey == null || webAppRootKey.length() < 1){
		   webAppRootKey = WEB_APP_ROOT_DEFAULT;
		  }
		  System.setProperty(webAppRootKey, prefix);
		  
		  String propfile = getServletConfig().getInitParameter(
		    "log4j-config-file");
		  if (propfile != null) {
		   PropertyConfigurator.configure(prefix + propfile);
		   System.out.println("Log4J Configured.");
		  }
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
