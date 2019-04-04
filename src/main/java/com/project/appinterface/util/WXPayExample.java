package com.project.appinterface.util;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.github.wxpay.sdk.WXPayUtil;
import com.github.wxpay.sdk.WXPayConstants.SignType;
import com.project.appinterface.util.wxpayutil.MyConfig;
import com.project.util.HttpsClientUtil;
import com.project.util.PayParameter;

/**
 *  微信支付
 *  2018-10-16
 * @author 张鹏浩
 *
 */
public class WXPayExample {
	/**
	 * 统一下单
	 * @return
	 */
	public static Map<String,String> unifiedOrder(Map<String, String> data ) {
			Map<String,String> resp = new HashMap<>();
			Map<String,String> map = new HashMap<>();
	        try {
	        	MyConfig config = new MyConfig();
	        	data.put("appid", config.getAppID());
	        	data.put("mch_id", config.getMchID());
	 	        data.put("nonce_str", WXPayUtil.generateNonceStr());
	 	        
//	 	       String sign = WXPayUtil.generateSignature(data, PayParameter.WEI_KEY);
//	 	       data.put("sign", sign);
//	 	       data.put("sign_type", "MD5");
	 	       String xml = WXPayUtil.generateSignedXml(data, config.getKey());
	 	       String str = HttpsClientUtil.httpRequest("https://api.mch.weixin.qq.com/pay/unifiedorder",
	    				"POST", xml);
//	 	      HttpClient client=new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
//	 	     client.setHttps(true);//是否是https协议
//	 	     client.setXmlParam(xmlParam);//发送的xml数据
//	 	     client.post();//执行post请求
//	 	     String result = client.getContent(); //获取结果
	 	      map = WXPayUtil.xmlToMap(str);
	 	       System.out.println("支付成功后:"+str);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return map;
	    }
	/**
	 * 微信回调
	 * @return
	 */
	public static Map<String, String>  queryOrder(String notifyData) {
		Map<String, String> map = new TreeMap<String,String>();
		try {
	        map = WXPayUtil.xmlToMap(notifyData);  // 转换成map
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	  /**
     * 给微信发送xml消息
     * @param return_code
     * @param return_msg
     * @return
     */
    public static String setXml(String return_code, String return_msg) {  
        SortedMap<String, String> parameters = new TreeMap<String, String>();  
        parameters.put("return_code", return_code);  
        parameters.put("return_msg", return_msg);  
        return "<xml><return_code><![CDATA[" + return_code + "]]>" +   
                "</return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";  
    }  
}
