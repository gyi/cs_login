package com.login.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.login.db.model.server.Server;
import com.login.status.ServerStatus;

public class GMCommandUtil {

	private static Logger logger = LoggerFactory.getLogger(GMCommandUtil.class);

	/**
	 * 返回服务器人数
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings("unchecked")
//	public static Integer getServerUserNum(Server server) throws InstantiationException, IllegalAccessException, IOException {
//		String result = SocketUtil.getResult(server, "#cmd getRoleNum");
//		Map<String, String> resultMap = new HashMap<String, String>();
//		resultMap = JsonHelper.json2Obj(result, resultMap.getClass());
//		
//		if (StringUtils.isEmpty(result)||StringUtils.isEmpty(resultMap.get("isConnect"))||resultMap.get("isConnect").equals("0")) {
//			logger.error("gm命令有误或者链接有误");
//			return null;
//		}
//		// 人数规则
//		logger.info("服务器人数为：{}", result);
//		return Integer.valueOf(result);
//	}

	/**
	 * 返回服务器状态
	 */
	/*public static String getServerOnOffStatue(Server server) {
		String result = SocketUtil.getResult(server, "#cmd getStatue");
		if (StringUtils.isEmpty(result)) {
			logger.error("gm命令有误或者链接有误");
		} else if (result.equals("open")) {
			logger.info("服务器开关状态为：{}", result);
			return "open";
		} else if (result.equals("close")) {
			logger.info("服务器开关状态为：{}", result);
		} else {
			logger.error("gm命令有误或者链接有误");
		}
		return "close";
	}*/

	/**
	 * 打开或关闭服务器
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
//	@SuppressWarnings("unchecked")
//	public static String serverOnOff(Server server) throws InstantiationException, IllegalAccessException, IOException {
//		int onOffStatue = server.getStatus();
//		String result = null;
//		Map<String, String> resultMap = new HashMap<String, String>();
//		if (onOffStatue==ServerStatus.CLOSE.getValue()) {
//			logger.info("服务器已关");
//			result = SocketUtil.getResult(server, "open");
//			resultMap = JsonHelper.json2Obj(result, resultMap.getClass());
//			if (StringUtils.isEmpty(result)||resultMap.get("isConnect").equals("0")) {
//				logger.error("gm命令有误或者链接有误");
//				return null;
//			}
//			
//		} else if (onOffStatue==ServerStatus.OPEN.getValue()) {
//			logger.info("服务器已开");
//			result = SocketUtil.getResult(server, "close");
//			resultMap = JsonHelper.json2Obj(result, resultMap.getClass());
//			if (StringUtils.isEmpty(result)||resultMap.get("isConnect").equals("0")) {
//				logger.error("gm命令有误或者链接有误");
//				return null;
//			}
//		}
//		return resultMap.get("result");
//	}

	/**
	 * 服务器人数状态
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
//	public static String getServerOnlinePlayerCondition(Server server,
//			String[] serverPlayerStatus){
//		if(server.getStatus()==ServerStatus.CLOSE.getValue()){
//			return serverPlayerStatus[3];
//		}
//		try {
//			Integer onlineMan = getServerUserNum(server);
//			if(onlineMan==null){
//				return serverPlayerStatus[3];
//			}
//			Pattern p = Pattern.compile(".*?\\[(.*?)(?<!\\[)\\]");
//			Matcher m = p.matcher(String.valueOf(server.getConditionset()));
//			
//			for (int j = 0; m.find(); j++) {
//				if (onlineMan < Integer.valueOf(m.group(1))) {
//					return serverPlayerStatus[j];
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return serverPlayerStatus[3];
//		}
//
//		return serverPlayerStatus[3];
//	}
	
	public static void main(String[] args) {
		String str = "[111][3213][322]" ;
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(str);
		List<String> list = new ArrayList<String>();
		while (m.find()) {
			String strs = m.group() ;
			list.add(strs) ;
		}
		System.out.println(list);
	}
}
