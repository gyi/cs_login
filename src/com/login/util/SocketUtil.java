package com.login.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cs.global.server.data.Gmcmd;
import com.login.db.manager.ServerManager;
import com.login.db.model.server.Server;

/**
 * 
*
* @Description: TODO	SOCKET连接
* @author zhaowei 
* @Ceatetime 2014年8月20日
*
 */
public class SocketUtil {
	
	private static Logger logger = Logger.getLogger(SocketUtil.class);
	
//	public static String getResult(Server server, String msg){
//		Map<String, String> resultMap = new HashMap<String, String>() ;
//		String result = "";
//		Socket socket = null;
//		socket = new Socket();
//		InputStreamReader in = null;
//		OutputStreamWriter out = null;
//		char[] buffer = new char[1024];
//		try {
//			String ip = server.getIp();
//			int port = server.getPort(); 
//			socket.connect(new InetSocketAddress(ip, port), 5000);
//			socket.setSoTimeout(5000);
//			in = new InputStreamReader(socket.getInputStream(),"UTF-8");
//			out = new OutputStreamWriter(socket.getOutputStream(),"UTF-8");
//			out.write(msg);
//			out.flush();
//			in.read(buffer);
//			result += new String(buffer);
//			resultMap.put("result", result) ;
//			resultMap.put("isConnect", "1") ;
//		} catch (Exception e) {
//			logger.error("socket连接(通信)错误：",e);
//			resultMap.put("isConnect", "0") ; //是否连接上,1表示连接上了,0表示没有连接上
//		} finally{
//			try {
//				if(in != null){
//					in.close();
//				}
//				if(out != null){
//					out.close();
//				}
//				if(socket != null){
//					socket.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		String rs = null;
//		try {
//			rs = JsonHelper.obj2Json(resultMap);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return rs;
//	}
	
	public static String getHttpGmResult(Server server, String msg, long roleId) {
		String url = "http://" + server.getIp() + ":" + server.getPort() + "/gm.action?cmd=" ;
		try {
			Gmcmd gmcmd = new Gmcmd() ;
			gmcmd.setRoleId(roleId); 
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("orderType", msg.split(" ")[0]) ;
			map.put("orderContent", msg.split(" ")[1]) ;
			gmcmd.setMap(map);
			String urlres = URLEncoder.encode(JsonHelper.obj2Json(gmcmd), "utf-8") ;
			url += urlres ;
		} catch (Exception e) {
			logger.error("gm error", e);
		}
		
		return Http.submit(url) ;
	}
}
