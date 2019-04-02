package com.project.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
/*
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClient {
	//方法
	public static String abcd9(Map<String,String> params) throws Exception {
	        //访问准备
	        URL url = new URL("http://121.201.46.250:8088/sms.aspx");
	//post参数
//	        Map<String,Object> params = new LinkedHashMap<>();
//	        params.put("geomInfo", "");
//	        params.put("f", "json");

	        //开始访问
	        StringBuilder postData = new StringBuilder();
	        for (Map.Entry<String,String> param : params.entrySet()) {
	            if (postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        }
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postDataBytes);

	        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

	        StringBuilder sb = new StringBuilder();
	        for (int c; (c = in.read()) >= 0;)
	            sb.append((char)c);
	        String response = sb.toString().trim();
	        return response;
	    }
	 /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param.getBytes("UTF-8"));
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    

	
//	public static String getWeiChatCode() {
//		
//	}
	/**
	 * post请求
	 * @param url
	 * @param json
	 * @return
	 */
	  public static JSONObject doPost1(String url,JSONObject json){
	        DefaultHttpClient client = new DefaultHttpClient();
	        HttpPost post = new HttpPost(url);
	        JSONObject response = null;
	        try {
	            StringEntity s = new StringEntity(json.toString());
	            s.setContentEncoding("UTF-8");
	            s.setContentType("application/json");//发送json数据需要设置contentType
	            post.setEntity(s);
	            HttpResponse res = client.execute(post);
	            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	                HttpEntity entity = res.getEntity();
	                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
	                response = JSONObject.fromObject(result);
	            }
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	        return response;
	    }
	  
		
	  /**
	   * 获取阿里云物流信息
	   * @param urlSend
	   * @return
	 * @throws IOException 
	   */
	  public static String doGetAliYunKdi(String urlSend, Map<String, String> headers,Map<String, String> params) throws Exception {
		  String json="";
		  try {
			    StringBuilder param=new StringBuilder();
		        params.forEach((k,v)->{
		        	param.append(k+"="+v+"&");
		        });
		        if(param.length()>0) {
		        	urlSend+="?"+param.toString().substring(0,param.length()-1);
		        }
		        System.out.println("url==="+urlSend);
			  URL url = new URL(urlSend);
		        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		        headers.forEach((k,v)->{
		        	httpURLConnection.setRequestProperty(k, v);
		        });
		        
		        int httpCode = httpURLConnection.getResponseCode();
		        if(httpCode==400) {
					  throw new RuntimeException("阿里云物流接口权限错误");
				  }else if(httpCode==403) {
					  throw new RuntimeException("阿里云物流使用次数已用完");
				  } 
		        json = read(httpURLConnection.getInputStream());
		        System.out.println("/* 获取服务器响应状态码 200 正常；400 权限错误 ； 403 次数用完； */ ");
		        System.out.println(httpCode);
		        System.out.println("/* 获取返回的json   */ ");
		        System.out.print(json);
			  
			  
		  }catch (Exception e) {
			throw e;
		}
		  return json;
	  }
	    /*
      读取返回结果
   */
  private static String read(InputStream is) throws IOException {
      StringBuffer sb = new StringBuffer();
      BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
      String line = null;
      while ((line = br.readLine()) != null) {
         // line = new String(line.getBytes(), "utf-8");
          sb.append(line);
      }
      br.close();
      return sb.toString();
  }
	
}