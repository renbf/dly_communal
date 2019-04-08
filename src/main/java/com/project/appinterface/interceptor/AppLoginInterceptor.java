package com.project.appinterface.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.project.appinterface.domain.UserInfo;
import com.project.appinterface.service.IUserInfoService;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
/**
 * 拦截器
 * @author rbf
 *
 */
@Component
public class AppLoginInterceptor implements HandlerInterceptor {
	
	private static final Logger log = LoggerFactory.getLogger(AppLoginInterceptor.class);
	@Autowired
	IUserInfoService userService;
	
    long start = System.currentTimeMillis();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        start = System.currentTimeMillis();
        DataResult result=new DataResult();
        String userId = request.getParameter("userId");
        if(StringUtils.isNotEmpty(userId)) {
        	UserInfo userInfo = userService.selectUserInfoByUserId(userId);
            if(userInfo == null) {
            	result.setMessage("用户不存在");
        		result.setStatus(Result.USERNO);
        		String jsonObjectStr = JSON.toJSONString(result);
                returnJson(response,jsonObjectStr);
                return false;
            }else {
            	if("1".equals(userInfo.getState())) {
            		result.setMessage("用户已冻结");
            		result.setStatus(Result.USERNO);
            		String jsonObjectStr = JSON.toJSONString(result);
                    returnJson(response,jsonObjectStr);
                    return false;
            	}
            }
        }
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor cost="+(System.currentTimeMillis()-start));
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
    }
    
    private void returnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
 
        } catch (IOException e) {
        	log.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

}
