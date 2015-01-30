package com.login.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.login.convert.Converter;
import com.login.convert.PlatformIdConvert;
import com.login.convert.TimeConvert;
import com.login.db.manager.PlayerManager;
import com.login.db.model.player.Player;
import com.login.util.ObjectConvertUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
*
* @Description: TODO	玩家管理控制
* @author zhaowei 
* @Ceatetime 2014年8月13日
*
 */
public class PlayerAction extends ActionSupport {

	private static final long serialVersionUID = -4741314376600009022L;
	
	private Logger logger = Logger.getLogger(PlayerAction.class) ;
	
	/**
	 * 结果
	 */
	private String result ;
	
	/**
	 * 查询类型
	 */
	private int type ;
	
	/**
	 * 玩家信息
	 */
	private String playerMessage ;
	
	/**
	 * 玩家
	 */
	private List<Map<String, String>> players ;
	
	/**
	 * 玩家
	 */
	private List<Map<String, Object>> playersContainer ;
	
	public String playerPage(){
		return SUCCESS ;
	}
	
	public String playerSelect() {
		result = "false" ;
		List<Player> playerList = null ;
		try {
			switch (type) {
			case 1:
				playerList = PlayerManager.instance().getPlayerByPlayerId(Long.valueOf(playerMessage)) ;
				//将数据库中的时间转成可理解格式
				break;
			case 2:
				playerList = PlayerManager.instance().getPlayerByUserId(playerMessage) ;
				break;
			default:
				break;
			}
			Map<String, Converter> mapConvert = new HashMap<String, Converter>() ;
			mapConvert.put("lastlogintime", new TimeConvert()) ;
			mapConvert.put("platformid", new PlatformIdConvert()) ;
			players = ObjectConvertUtil.convert2ListMap(playerList,mapConvert) ;
			result = "true" ;
		} catch (Exception e) {
			logger.error(e, e);
			return INPUT ;
		}
		
		return SUCCESS ;
	}
	
	public void validatePlayerSelect() {
		if(StringUtils.isEmpty(playerMessage)){
			this.addFieldError("playerMessageError", getText("cs.player.playerMessageIsNull"));
			return ;
		}
		
		if(type==1) {
			if(!StringUtils.isNumeric(playerMessage)) {
				this.addFieldError("playerMessageError", getText("cs.player.playerIdMustBeNumber"));
			}
		}
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPlayerMessage() {
		return playerMessage;
	}

	public void setPlayerMessage(String playerMessage) {
		this.playerMessage = playerMessage;
	}

	public List<Map<String, String>> getPlayers() {
		return players;
	}

	public void setPlayers(List<Map<String, String>> players) {
		this.players = players;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Map<String, Object>> getPlayersContainer() {
		return playersContainer;
	}

	public void setPlayersContainer(List<Map<String, Object>> playersContainer) {
		this.playersContainer = playersContainer;
	}
	
}
