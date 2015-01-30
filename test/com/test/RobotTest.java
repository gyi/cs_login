package com.test;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.login.util.Http;

public class RobotTest {
	public static void main(String[] args) throws IOException {
		int i = 4 ;
		while(i>0) {
			try {
				Thread.sleep(1000);
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						Test.test();
					}
				}).start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			i-- ;
		}
	}
	
}

class Test{
	public static void test(){
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(500) ;
		final CountDownLatch latch = new CountDownLatch(500) ;
		final Set<String> set = new HashSet<String>() ;
		for (int i = 0; i < 500; i++) {
			executor.execute(new Runnable() {
				
				@SuppressWarnings("static-access")
				public void run() {
					Random random = new Random() ;
					try {
						long j = random.nextInt(2147483000) ;
						URLDecoder decoder = new URLDecoder() ;
						System.out.println(decoder.decode(Http.submit("http://localhost:8080/cs_login/login/logincheck.action?platformId=8&userId=" + j + "&key=1234567890"), "utf-8")) ;
						set.add(j+"") ;
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						latch.countDown();
						System.out.println("当前数：" + latch.getCount());
					}
				}
			});
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
