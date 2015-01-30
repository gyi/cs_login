package com.test;

import java.io.IOException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.login.db.model.server.Server;
import com.login.status.ServerPlayerStatus;
import com.login.util.GMCommandUtil;

import junit.framework.TestCase;

public class TestGMCommandUtil extends TestCase{
	
	private Server server;
	
	@Before
	public void setUp(){
		server = new Server();
		server.setConditionset("[10][20][30]");
		
	}
	
	@Ignore
	public void testGetServerOnlinePlayerCondition() throws IllegalAccessException, InstantiationException{
//		GMCommandUtil.getServerOnlinePlayerCondition(server, null);
	}

}
