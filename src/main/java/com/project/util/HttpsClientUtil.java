package com.project.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 发送Https请求
 * @author Administrator
 *
 */
public class HttpsClientUtil {
	private static Logger log = LoggerFactory.getLogger(HttpsClientUtil.class);
	public static String httpRequest(String requestUrl,String requestMethod,String outputStr){
		try {  
			  
            URL url = new URL(requestUrl);  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setUseCaches(false);  
            // 设置请求方式（GET/POST）  
            conn.setRequestMethod(requestMethod);  
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("content-type", "text/xml");
            // 开始连接请求
            conn.connect();
            // 当outputStr不为null时向输出流写数据  
            if (null != outputStr) {  
                OutputStream outputStream = conn.getOutputStream();  
                // 注意编码格式  
                outputStream.write(outputStr.getBytes("UTF-8")); 
                outputStream.flush();
                outputStream.close();  
            }  
            
            if (conn.getResponseCode() == 200) {
                log.info("连接成功");
                // 请求返回的数据
                InputStream in = conn.getInputStream();
                String a = null;
                try {
                    byte[] data1 = new byte[in.available()];
                    in.read(data1);
                    // 转成字符串
                    a = new String(data1);
                    log.info(a);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    log.error("响应失败",e1);
                }
                in.close();  
                in = null;  
                conn.disconnect();
                return a;
            } else {
            	log.info("no++");
            }
        } catch (ConnectException ce) {  
        	System.out.println(("连接超时：{}" + ce));  
        } catch (Exception e) {  
        	System.out.println(("https请求异常：{}" + e));  
        }  
		return null;
	}

}
