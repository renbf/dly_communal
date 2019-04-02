package com.project.appinterface.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class ConfirmationNumberUtil {
	//生成随机数字和字母,  
          
    	/** 自定义进制(0,1没有加入,容易与o,l混淆) */
        private static final char[] r=new char[]{'Q', 'W', 'E', '8', 'A', 'S', '2', 'D', 'Z', 'X', '9', 'C', '7', 'P', '5', 'I', 'K', '3', 'M', 'J', 'U', 'F', 'R', '4', 'V', 'Y', 'L', 'T', 'N', '6', 'B', 'G', 'H','1','b'};
     
        /** (不能与自定义进制有重复) */
        private static final char b='O';
     
        /** 进制长度 */
        private static final int binLen=r.length;
     
        /** 序列最小长度 */
        private static final int s=6;
        public static String  getRandomFileName() {

      	  SimpleDateFormat simpleDateFormat;

      	  simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

      	  Date date = new Date();

      	  String str = simpleDateFormat.format(date);

      	  Random random = new Random();

      	  int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

      	  return rannum + str;// 当前时间  }

      	}
        /**
         * 根据ID生成六位随机码
         * @param id ID
         * @return 随机码
         */
        public static String toSerialCode(long id) {
            char[] buf=new char[32];
            int charPos=32;
     
            while((id / binLen) > 0) {
                int ind=(int)(id % binLen);
                // System.out.println(num + "-->" + ind);
                buf[--charPos]=r[ind];
                id /= binLen;
            }
            buf[--charPos]=r[(int)(id % binLen)];
            // System.out.println(num + "-->" + num % binLen);
            String str=new String(buf, charPos, (32 - charPos));
            // 不够长度的自动随机补全
            if(str.length() < s) {
                StringBuilder sb=new StringBuilder();
                sb.append(b);
                Random rnd=new Random();
                for(int i=1; i < s - str.length(); i++) {
                sb.append(r[rnd.nextInt(binLen)]);
                }
                str+=sb.toString();
            }
            return str;
        }
     
        public static long codeToId(String code) {
            char chs[]=code.toCharArray();
            long res=0L;
            for(int i=0; i < chs.length; i++) {
                int ind=0;
                for(int j=0; j < binLen; j++) {
                    if(chs[i] == r[j]) {
                        ind=j;
                        break;
                    }
                }
                if(chs[i] == b) {
                    break;
                }
                if(i > 0) {
                    res=res * binLen + ind;
                } else {
                    res=ind;
                }
                // System.out.println(ind + "-->" + res);
            }
            return res;
        } 
      
    public static void  main(String[] args) {  
    	ConfirmationNumberUtil test = new ConfirmationNumberUtil();  
        //测试  
        System.out.println(test.toSerialCode(6));  
    }  

}
