package com.project.appinterface.util;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayUserInfoAuthModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayUserInfoAuthRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayUserInfoAuthResponse;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.project.util.PayParameter;

/**
 * 支付宝支付工具类
 * 2018-10-16
 * @author 张鹏浩
 *
 */
public class AliPayUtil {
	
	/**
	 * 获取支付宝支付串
	 * @param model 支付宝实体参数
	 * @param notifyUrl 回调方法
	 * @return 支付串
	 */
	public static String aliPay(AlipayTradeAppPayModel model,String notifyUrl) {
//		model.setBody("我是测试数据");
//		model.setSubject("App支付测试Java");
//		model.setOutTradeNo(outtradeno);
//		model.setTimeoutExpress("30m");
//		model.setTotalAmount("0.01");
//		model.setProductCode("QUICK_MSECURITY_PAY");
		String orderStr="";
		try {
			// 实例化客户端
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
					PayParameter.ALI_APP_ID, PayParameter.ALI_APP_PRIVATE_KEY, "json", "UTF-8", PayParameter.Ali_PUBLIC_KEY,
					"RSA2");
			// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
			AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
			// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
			request.setBizModel(model);
			request.setNotifyUrl(notifyUrl);
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			System.out.println(response.getBody());// 就是orderString 可以直接给客户端请求，无需再做处理。
			if(response.isSuccess()) {
				System.err.println(response.getMsg());
				return response.getBody();
			}else {
				System.out.println(response.getSubMsg());
				return null;
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 获取支付宝支付串
	 * @param model 支付宝实体参数
	 * @param notifyUrl 回调方法
	 * @return 支付串
	 * @throws AlipayApiException 
	 */
	public static AlipayFundTransToaccountTransferResponse aliTransfer(AlipayFundTransToaccountTransferModel model) throws AlipayApiException {
			// 实例化客户端
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",PayParameter.ALI_APP_ID,PayParameter.ALI_APP_PRIVATE_KEY,"json","UTF-8",PayParameter.ALI_ALIPAY_PUBLIC_KEY,"RSA2");
			// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
			AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
			// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
			request.setBizModel(model);
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayFundTransToaccountTransferResponse  response = alipayClient.execute(request);
			return response;
	}
	
	
	/**
	 * 处理支付宝回调
	 * @param request
	 * @param response
	 * @return 支付成功还是失败
	 */
	public static Map<String,Object> aliNotice(HttpServletRequest request,HttpServletResponse response) {
		 Map<String,Object> map=new HashMap<String,Object>();
		try {
			//获取支付宝POST过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			    String name = (String) iter.next();
			    String[] values = (String[]) requestParams.get(name);
			    String valueStr = "";
			    for (int i = 0; i < values.length; i++) {
			        valueStr = (i == values.length - 1) ? valueStr + values[i]
			                    : valueStr + values[i] + ",";
			  	}
			    //乱码解决，这段代码在出现乱码时使用。
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
			//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
			 boolean flag = AlipaySignature.rsaCheckV1(params, PayParameter.ALI_ALIPAY_PUBLIC_KEY, "UTF-8","RSA2");
			 map.put("flag", flag);
			 map.put("data", params);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static void getUserInfoAuth(){
		try {
			// 实例化客户端
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
					PayParameter.ALI_APP_ID, PayParameter.ALI_APP_PRIVATE_KEY, "json", "UTF-8",
					PayParameter.Ali_PUBLIC_KEY, "RSA2");
			AlipayUserInfoAuthRequest alipayRequest = new AlipayUserInfoAuthRequest();
			AlipayUserInfoAuthModel model = new AlipayUserInfoAuthModel();
			List<String> scopes = new ArrayList<String>();
			scopes.add("auth_base");
			model.setScopes(scopes);
			model.setState("init");
			alipayRequest.setBizModel(model);
			AlipayUserInfoAuthResponse response = alipayClient.execute(alipayRequest);
			if(response.isSuccess()){
				System.out.println(response.getBody());
				} else {
				System.out.println("调用失败");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 将服务处理的结果返回给支付平台 
	 * 
	 * @param response
	 * @param str
	 * @throws IOException 
	 */
	public static void responseService(HttpServletResponse response,String str) throws IOException{
		System.out.println("response:"+response==null);
		response.getContentType();
		response.setHeader("contentType", "text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(str);
		pw.close();
	}
	/**
	 * 根据订单号查询是否支付成功以及成功的回调
	 * @param orderNo
	 * @return
	 */
	public static DataResult queryPayInfoByOrderNo(String orderNo){
		DataResult result=new DataResult();
		try {
			// 实例化客户端
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
					PayParameter.ALI_APP_ID, PayParameter.ALI_APP_PRIVATE_KEY, "json", "UTF-8",
					PayParameter.ALI_ALIPAY_PUBLIC_KEY, "RSA2");
			//设置请求参数
			AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
			AlipayTradeQueryModel model = new AlipayTradeQueryModel();
			model.setOutTradeNo(orderNo);
			alipayRequest.setBizModel(model);
			//请求
			AlipayTradeQueryResponse response = alipayClient.execute(alipayRequest);
			if(response.isSuccess()){
				result.setMessage(response.getMsg());
				result.setStatus(Result.SUCCESS);
			}else{
				result.setStatus(response.getCode());
				result.setMessage(response.getMsg());
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("查询支付宝失败");
			result.setStatus(Result.FAILED);
			return result;
		}
	}
	/**
	 * 单笔转账到支付宝账户
	 * @param outBizNo
	 * @param payeeAccount
	 * @param amount
	 * @param remark
	 * @return
	 */
	public static DataResult fundTransToaccountTransfer(String outBizNo,String payeeAccount,String amount,String remark){
		DataResult result=new DataResult();
		try {
			if(StringUtils.isEmpty(outBizNo) ||StringUtils.isEmpty(payeeAccount) || StringUtils.isEmpty(amount)){
				result.setMessage("单笔转账参数为空");
				result.setStatus(Result.FAILED);
				return result;
			}
			// 实例化客户端
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
					PayParameter.ALI_APP_ID, PayParameter.ALI_APP_PRIVATE_KEY, "json", "UTF-8",
					PayParameter.ALI_ALIPAY_PUBLIC_KEY, "RSA2");
			//设置请求参数
			AlipayFundTransToaccountTransferRequest alipayRequest = new AlipayFundTransToaccountTransferRequest();
			AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
			model.setOutBizNo(outBizNo);
			model.setPayeeType("ALIPAY_LOGONID");
			model.setPayeeAccount(payeeAccount);
			model.setAmount(amount);
			model.setPayerShowName("大来也");
			model.setRemark(remark);
			alipayRequest.setBizModel(model);
			//请求
			AlipayFundTransToaccountTransferResponse response = alipayClient.execute(alipayRequest);
			if(response.isSuccess()){
				result.setMessage(response.getMsg());
				result.setStatus(Result.SUCCESS);
			}else{
				result.setStatus(response.getCode());
				result.setMessage(response.getMsg());
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("查询支付宝失败");
			result.setStatus(Result.FAILED);
			return result;
		}
	}
	
	public static String openAuthSdkCodeGet(String userId){
		try {
			SortedMap<String,String > map = new TreeMap<>();
			map.put("apiname","com.alipay.account.auth");
			map.put("method","alipay.open.auth.sdk.code.get");
			map.put("app_id",PayParameter.ALI_APP_ID);
			map.put("app_name","mc");
			map.put("biz_type","openservice");
			map.put("pid","2088921869615805");
			map.put("product_id","APP_FAST_LOGIN");
			map.put("scope","kuaijie");
			map.put("target_id",userId);
			map.put("auth_type","AUTHACCOUNT");
			map.put("sign_type",AlipayConstants.SIGN_TYPE_RSA2);
			String signStr = AlipaySignature.getSignContent(map);
			String sign = AlipaySignature.rsaSign(signStr, PayParameter.ALI_APP_PRIVATE_KEY, "utf-8",AlipayConstants.SIGN_TYPE_RSA2);
			String urlParam = getEncodeSignContent(map) + "&sign=" + URLEncoder.encode(sign, "utf-8");
			return urlParam;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	private static String getEncodeSignContent(SortedMap<String,String > sortedParams) throws Exception{
		StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + URLEncoder.encode(value, "utf-8"));
                index++;
            }
        }
        return content.toString();
	}
}
