package com.project.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoneyUtil {
	static Logger logger = LoggerFactory.getLogger(MoneyUtil.class);
	/**
	 * Long分转换为元
	 * @param v 分
	 * @return String
	 */
	public static String toYuan(Long v){
		if(v==null){
			return "0.00";
		}
		String s = v>=0?"":"-";
		Long a = Math.abs(v)/100L;
		Long f = Math.abs(v)%100L;
		return s+a+"."+(f<10?("0"+f):f);
	}
	
	/**
	 * String分转换为元
	 * @param v
	 * @return String
	 */
	public static String toYuan(String v){
		return toYuan(Long.parseLong(v));			
	}
	
	/**
	 * String元转换为分
	 * @param v
	 * @return Long
	 */
	public static Long toFen(String v){
		if(v==null||"".equals(v.trim())){
			return 0L;
		}
		int _i = v.indexOf("-");
		String s = _i>0?"-":"";
		v = _i>0?v.substring(1):v;
		int i = v.indexOf(".");
		if(i>0){
			String a = v.substring(0,i);
			String b = v.substring(i+1);
			if(b.length()==0){
				b="00";
			}else if(b.length()==1){
				b=b+"0";
			}else if(b.length()>2){
				b = b.substring(0, 2);
			}
			return Long.parseLong(s+a+b);
		}else{
			return Long.parseLong(s+v)*100L;
		}
	}
}
