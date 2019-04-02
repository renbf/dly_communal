package com.project.appinterface.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNo {
	 /** 
     * 生成订单编号 
     * @return 
     */  
	 private static long orderNum = 0l;  
	    private static String date ; 
    public static synchronized long getOrderNo() {  
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());  
        if(date==null||!date.equals(str)){  
            date = str;  
            orderNum  = 0l;  
        }  
        orderNum ++;  
        long orderNo = Long.parseLong((date)) * 10000;  
        orderNo += orderNum;;  
        return orderNo;  
    } 
}
