package com.project.appinterface.util.kuaidi;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.appinterface.util.kuaidi.pojo.pojo.KuaidiResult;
import com.project.common.utils.Md5Utils;

/**
 * 
 * @author rbf
 *
 */
public class Kuaidi100Util {

	private static final Logger log = LoggerFactory.getLogger(Kuaidi100Util.class);

	public static KuaidiResult getString(String com, String num) {
		try {
			log.info("查询快递公司编号：{};快递单号：{}",com,num);
			String param = "{\"com\":\"" + com + "\",\"num\":\"" + num
					+ "\",\"from\":\"\",\"phone\":\"\",\"to\":\"\",\"resultv2\":0}";
			String customer = "C79379DF4765050F9220333409868089";
			String key = "wWYQAYPe3232";
			String sign = Md5Utils.hash(param + key + customer);
			sign = sign.toUpperCase();
			Map<String, String> params = new HashMap();
			params.put("param", param);
			params.put("sign", sign);
			params.put("customer", customer);
			String resp = new HttpRequest().postData("http://poll.kuaidi100.com/poll/query.do", params, "utf-8")
					.toString();
			log.info(resp);
			if(!"".contentEquals(resp)) {
				KuaidiResult result = JacksonHelper.fromJSON(resp, KuaidiResult.class);
				return result;
			}else {
				return null;
			}
		} catch (Exception e) {
			log.error("快递100查询错误",e);
			return null;
		}
	}

	public static void main(String[] args) {
		getString("zhongtong", "75138476875974");
	}
}
