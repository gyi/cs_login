package com.login.db.manager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.ibatis.session.SqlSession;

import com.login.db.model.platform.Platform;
import com.login.db.model.player.Player;
import com.login.db.model.player.PlayerExample;
import com.login.db.model.player.PlayerMapper;
import com.login.util.DBUtil;



/**
 * 
*
* @Description: TODO	玩家管理类
* @author zhaowei 
* @Ceatetime 2014年8月12日
*
 */
public class PlayerManager {
	private static PlayerManager manager ;
	private Map<Integer, AtomicLong> nextPlayerIdMap ; 
	private Object obj = new Object() ;
	
	private PlayerManager() {
		nextPlayerIdMap = new HashMap<Integer, AtomicLong>() ;
		List<Platform> platforms = PlatformManager.instance().getAllPlatforms() ;
		//初始化将玩家id
		for(Platform platform : platforms) {
			long maxPlayerId = maxPlayerId(platform.getId()) ;
			AtomicLong atomicLong = new AtomicLong(maxPlayerId) ;
			nextPlayerIdMap.put(platform.getId(), atomicLong) ;
		}
	}
	
	public static PlayerManager instance(){
		if(manager==null) {
			manager = new PlayerManager() ;
		}
		return manager ;
	}
	
	/**
	 * 获得最大玩家id
	 */
	public long maxPlayerId(int platformId) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		Long maxPlayerId = null ;
		try {
			PlayerMapper mapper = session.getMapper(PlayerMapper.class) ;
			Long playerId = mapper.maxPlayerId(platformId) ;
			maxPlayerId = playerId==null? 0L : playerId;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return maxPlayerId ;
	}
	
	public long nextPlayerId(int platformId) {
		synchronized (obj) {
			AtomicLong nextPlayerId = nextPlayerIdMap.get(platformId) ;
			if(nextPlayerId.get()==0L){
				nextPlayerId.set(Long.valueOf(platformId + "" +System.nanoTime()));
			}
			return  nextPlayerId.incrementAndGet();
		}
	}
	
	public Player getPlayerByUserIdAndPlatformId(String userId, int platformId) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		Player player = null ;
		try {
			PlayerMapper mapper = session.getMapper(PlayerMapper.class) ;
			PlayerExample example = new PlayerExample() ;
			example.createCriteria().andUseridEqualTo(userId).andPlatformidEqualTo(platformId) ;
			List<Player> players = mapper.selectByExample(example) ;
			player = players==null||players.size()==0?null:players.get(0) ;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return player;
	}
	
	/**
	 * 更新玩家最新登陆时间
	 */
	public int updateLoginTime(long playerId, long time) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		int result = 0 ;
		try {
			PlayerMapper mapper = session.getMapper(PlayerMapper.class) ;
			Player player = new Player() ;
			player.setLastlogintime(time);
			PlayerExample example = new PlayerExample() ;
			example.createCriteria().andPlayeridEqualTo(playerId) ;
			result = mapper.updateByExampleSelective(player, example) ;
			session.commit();
		} catch (Exception e) {
			result = -1 ;
			throw e ;
		}finally{
			session.close();
		}
		return result ;
	}
	
	public List<Player> getPlayerByPlayerId(long playerId) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		List<Player> players = null ;
		try {
			PlayerMapper mapper = session.getMapper(PlayerMapper.class) ;
			PlayerExample example = new PlayerExample() ;
			example.createCriteria().andPlayeridEqualTo(playerId) ;
			players = mapper.selectByExample(example) ;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return players;
	}
	
	public List<Player> getPlayerByUserId(String userId) {
		SqlSession session = DBUtil.instance().getSqlSession() ;
		List<Player> players = null ;
		try {
			PlayerMapper mapper = session.getMapper(PlayerMapper.class) ;
			PlayerExample example = new PlayerExample() ;
			example.createCriteria().andUseridEqualTo(userId) ;
			players = mapper.selectByExample(example) ;
		} catch (Exception e) {
			throw e ;
		}finally{
			session.close();
		}
		return players;
	}
	
	/**
	 * 数据库添加玩家
	 */
	public int addPlayer(Player player){
		SqlSession session = DBUtil.instance().getSqlSession() ;
		int result = 0 ;
		try {
			PlayerMapper mapper = session.getMapper(PlayerMapper.class) ;
			result = mapper.insert(player) ;
			session.commit();
		} catch (Exception e) {
			result = -1 ;
			throw e ;
		}finally{
			session.close();
		}
		return result ;
	}
	
	/**
	 * 创建玩家
	 */
	public Player createPlayer(int platformId, String userId, long time) {
		Player player = new Player() ;
		player.setPlatformid(platformId);
		long playerId = nextPlayerId(platformId) ;
		player.setPlayerid(playerId);
		player.setUserid(userId);
		player.setLastlogintime(time);
		return player ;
	}
	
	public static void main(String[] args) {
		 PlayerManager.instance();
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(500) ;
		final CountDownLatch latch = new CountDownLatch(500) ;
		final Set<String> set = new HashSet<String>() ;
		for (int i = 0; i < 500; i++) {
			executor.execute(new Runnable() {
				
				public void run() {
					try {
						long test = PlayerManager.instance().nextPlayerId(8) ;
						set.add(test + "") ;
						System.out.println(test);
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						latch.countDown(); 
					}
				}
			});
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		System.out.println("最终数量: "+ set.size());
	}
}
