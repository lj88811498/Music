package cn.owntt.util;

 
/**
 * <p>Title: HttpClientUtil.java
 * <p>Description: 使用httpclient发送get,post请求
 * <p>Copyright: (C) 2016 youedata Tech. Co. Ltd. All Rights Reserved <duanbing@youedata.com>
 * <p>Company: www.youedata.com
 * @author Monkey
 * @date 2016年4月12日
 * @version 1.0
 */

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

public class HttpClientUtil {

	/**
	 * 
	 * <p>
	 * Title: doPost
	 * <p>
	 * Description:发送post请求
	 * 
	 * @author duanbing duanbing@youedata.com
	 * @date 2016年4月14日
	 * @param url
	 *            : 请求地址
	 * @param params
	 *            :参数
	 * @return body:返回请求得到的值。
	 */
	public static String doPost(String url, Map<String, String> params) {

		DefaultHttpClient httpclient = new DefaultHttpClient();

		String body = null;

		HttpPost post = postForm(url, params);

		body = invoke(httpclient, post);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	/**
	 * 
	 * <p>
	 * Title: doGet
	 * <p>
	 * Description:发送get请求
	 * 
	 * @author duanbing duanbing@youedata.com
	 * @date 2016年4月14日
	 * @param url
	 *            : 请求地址
	 * @return body:返回请求得到的值。
	 */
	public static String doGet(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		HttpGet get = new HttpGet(url);
		//设置请求的报文头部的编码
		get.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));

		//设置期望服务端返回的编码
		get.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
		body = invoke(httpclient, get);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	/**
	 * 
	 * <p>
	 * Title: invoke
	 * <p>
	 * Description:调用httpclient发送请求，并获取返回值
	 * 
	 * @author duanbing duanbing@youedata.com
	 * @date 2016年4月14日
	 * @param httpclient
	 *            : httpclient对象
	 * @param httpost
	 *            : HttpGet,HttpPost.
	 * @return body:返回请求得到的值。
	 */
	private static String invoke(DefaultHttpClient httpclient, HttpUriRequest httpost) {

		HttpResponse response = sendRequest(httpclient, httpost);
		HttpEntity entity = response.getEntity();
		String body = "";
		try {
			body = EntityUtils.toString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return body;
	}

	/**
	 * 
	 * <p>
	 * Title: sendRequest
	 * <p>
	 * Description:发送请求
	 * 
	 * @author duanbing duanbing@youedata.com
	 * @date 2016年4月14日
	 * @param httpclient
	 *            : httpclient对象
	 * @param httpost
	 *            : HttpGet,HttpPost.
	 * @return response:HttpResponse对象。
	 */
	private static HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpost) {
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

	/**
	 * 
	 * <p>
	 * Title: postForm
	 * <p>
	 * Description:封装post请求参数
	 * 
	 * @author duanbing duanbing@youedata.com
	 * @date 2016年4月14日
	 * @param url
	 *            : url地址
	 * @param params
	 *            : 参数.
	 * @return httpost:HttpPost对象。
	 */
	private static HttpPost postForm(String url, Map<String, String> params) {

		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}

		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return httpost;
	}

	public static void main(String[] args) throws Exception {
		 
//		String url = "http://47.105.52.119:8082/music/list.do?page=1";
//		String name = "爱囚.mp3";//  54ix5ZuaLm1wMw==
//		String uri = "D:\\mp3\\" + name;//RDpcbXAzXOeIseWbmi5tcDM= 
//		byte[] n1 = name.getBytes();
//		byte[] u1 = uri.getBytes();
//	         //对字节数组Base64编码
//	    String t_name = Base64.encode(n1) ;
//	    String t_url = Base64.encode(u1) ;
//	    System.out.println(t_name);
//	    System.out.println(t_url);
//	    
//	    byte[] url1 = Base64.decode(t_url); 
//    	byte[] name1 = Base64.decode(t_name); 
//    	String url11 = new String(url1);
//    	String name11 = new String(name1);
//    	
//	    System.out.println(url11);
//	    System.out.println(name11);
    	String path = "d:\\mp4\\";
		String url = "http://localhost:8080/AnnotationMVC/uploadFile_local.do?author=Monkey&path="+ path ;
//		String url = "http://localhost:8080/AnnotationMVC/uploadFile_local.do?author=Monkey&t_name="+ t_name + "&t_url="+t_url;

//		URL u1 = new URL(url);
//		URI uri = new URI(u1.getProtocol(), u1.getHost() + ":" + u1.getPort(), u1.getPath(), u1.getQuery(), null);
		
		System.out.println(url);
		
		String doGet = doGet(url);
		System.out.println(doGet);
		 
//		String url = "http://localhost:8080/AnnotationMVC/uploadFile_local.do";
//		String path = "D:\\mp3\\1.mp3";
//		Map map = new HashMap();
//		map.put("author", "Monkey");
//		map.put("url", url);
//		map.put("name", "1.mp3");
//		String result = doPost(url, map);
//		System.out.println(result);
//		byte[] b = t_url.getBytes();
//		System.out.println(b);
//		String s = new String(b);
//		System.out.println(s);
	}
}
