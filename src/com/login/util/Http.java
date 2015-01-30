package com.login.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cs.global.server.data.Gmcmd;

/**
 * http请求
*
* @Description: TODO
* @author zhaowei 
* @Ceatetime 2014年6月12日
*
 */
public class Http {
	private static Logger logger = LoggerFactory.getLogger(Http.class) ;
	
	public static String submit(String url) {
		HttpClient httpclient = new DefaultHttpClient(); 
		String result = "" ;
		try {
			HttpPost httpPost = new HttpPost(url) ;
//			logger.info("executing request {}", httpPost.getURI());
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
//			logger.info( "status : " , response.getStatusLine().toString());
			if (entity != null) {
//				logger.info("Response content length: {}", entity.getContentLength());
		    }
			InputStream inSm = entity.getContent() ;
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(inSm, "UTF-8")) ;
			String line = "" ;
			while((line=reader.readLine())!=null) {
				result += line ;
			}
			httpPost.abort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result ;
	}
	
	 public static String post(String url, Map<String, String> params) {  
	        DefaultHttpClient httpclient = new DefaultHttpClient();  
	        String body = null;  
	          
	        logger.info("create httppost:" + url);  
	        HttpPost post = postForm(url, params);  
	          
	        body = invoke(httpclient, post);  
	          
	        httpclient.getConnectionManager().shutdown();  
	          
	        return body;  
    }
	 
	 private static HttpPost postForm(String url, Map<String, String> params){  
	        HttpPost httpost = new HttpPost(url);  
	        List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
	          
	        Set<String> keySet = params.keySet();  
	        for(String key : keySet) {  
	            nvps.add(new BasicNameValuePair(key, params.get(key)));  
	        }  
	          
	        try {  
	        	logger.info("set utf-8 form entity to httppost");  
	            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }  
	          
	        return httpost;  
    }
	 
    private static String invoke(DefaultHttpClient httpclient,  
            HttpUriRequest httpost) {  
          
        HttpResponse response = sendRequest(httpclient, httpost);  
        String body = paseResponse(response);  
          
        return body;  
    }
    
    private static String paseResponse(HttpResponse response) {  
    	logger.info("get response from http server..");  
        HttpEntity entity = response.getEntity();  
          
        logger.info("response status: " + response.getStatusLine());  
        String charset = EntityUtils.getContentCharSet(entity);  
        logger.info(charset);  
          
        String body = null;  
        try {  
            body = EntityUtils.toString(entity);  
            logger.info(body);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return body;  
    }
    
    private static HttpResponse sendRequest(DefaultHttpClient httpclient,  
            HttpUriRequest httpost) {  
    	logger.info("execute post...");  
        HttpResponse response = null;  
          
        try {  
            response = httpclient.execute(httpost);  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return response;  
    }  
	
	public static void main(String[] args) throws IOException {
			Gmcmd gmcmd = new Gmcmd() ;
			gmcmd.setRoleId(1001000300096L); 
			Map<String, String> map = new HashMap<String, String>() ;
			map.put("orderType", "a1ttr") ;
			map.put("orderContent", "2,11111;7,21212121") ;
			gmcmd.setMap(map);
			String urlres = URLEncoder.encode(JsonHelper.obj2Json(gmcmd), "utf-8") ;
			System.out.println(urlres);
			String url = Http.submit("http://192.168.1.66:8103/gm1.action?cmd=" + urlres) ;
			String decoder = URLDecoder.decode(url, "utf-8") ;
			System.out.println(decoder);
	}
}
