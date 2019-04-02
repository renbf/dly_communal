package com.project.appinterface.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.project.appinterface.domain.ParamArrayVo;
import com.project.appinterface.domain.UserInfo;
import com.project.appinterface.service.IUserInfoService;
import com.project.framework.util.FileUploadUtils;
import com.project.util.UploadUtil;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Map;


/**
 *
 * @author 张鹏浩
 * @ApiOperation(value = “接口说明”, httpMethod = “接口请求方式”, response = “接口返回参数类型”, notes = “接口发布说明”；produces=“media类型”；)
 * @ApiParam(required = “是否必须参数”, name = “参数名称”, value = “参数具体描述”)
 */

@Controller
@RequestMapping("gift/accountNumberController")
@Api(value="礼物机登录",description="登录")
public class AccountNumberController {
	@Autowired
	IUserInfoService userService;

	/**
	 * 用户注册
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ApiOperation(value="注册账号",httpMethod="POST",response=Result.class)
	public @ResponseBody Result register(HttpServletRequest request,
										 @ApiParam(name="phone",value="手机号",required=true) @RequestParam(value="phone",required=false)String phone,
										 @ApiParam(name="code",value="验证码",required=true) @RequestParam(value="code",required=false)String code,
										 @ApiParam(name="password",value="密码",required=true) @RequestParam(value="password",required=false)String password,
										 @ApiParam(name="invitationCode",value="邀请码",required=true) @RequestParam(value="invitationCode",required=false)String invitationCode
	){
		Result result=new DataResult();
		result=userService.register(phone,code,password,invitationCode) ;
		return result;
	}
	/**
	 * 忘记密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/forgotpassword",method=RequestMethod.POST)
	@ApiOperation(value="忘记密码",httpMethod="POST",response=Result.class)
	public @ResponseBody Result forgotpassword(HttpServletRequest request,
											   @ApiParam(name="phone",value="手机号",required=true) @RequestParam(value="phone",required=false)String phone,
											   @ApiParam(name="code",value="验证码",required=true) @RequestParam(value="code",required=false)String code,
											   @ApiParam(name="password",value="密码",required=true) @RequestParam(value="password",required=false)String password
	){
		Result result=new Result();
		result = userService.forgotpassword(phone,code,password);
		return result;
	}
	/**
	 * 更换手机号
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updatephone",method=RequestMethod.POST)
	@ApiOperation(value=" 更换手机号",httpMethod="POST",response=Result.class)
	public @ResponseBody Result updatephone(HttpServletRequest request,
											@ApiParam(name="code",value="验证码",required=true) @RequestParam(value="code",required=false)String code,
											@ApiParam(name="password",value="密码",required=true) @RequestParam(value="password",required=false)String password,
											@ApiParam(name="newPhone",value="新手机号",required=true) @RequestParam(value="newPhone",required=false)String newPhone,
											@ApiParam(name="userId",value="用户id",required=true) @RequestParam(value="userId",required=false)String userId
	){
		Result result=new Result();
		if(userId==null || userId=="") {
			result.setMessage("登陆超时，请重新登录");
			result.setStatus(Result.FAILED);
			return result;
		}
		result = userService.updatephone(code,password,newPhone,userId);
		return result;
	}
	/**
	 * 完善用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	@ApiOperation(value=" 完善用户信息",httpMethod="POST",response=Result.class)
	public @ResponseBody Result updatephone(HttpServletRequest request,
											@ApiParam(name="userId",value="用户id",required=true) @RequestParam(value="userId",required=false)String userId,
											@ApiParam(name="nickName",value="昵称",required=true) @RequestParam(value="nickName",required=false)String nickName,
											@ApiParam(name="sex",value="性别0女1男",required=true) @RequestParam(value="sex",required=false)String sex,
											@ApiParam(name="birthDay",value="生日",required=true) @RequestParam(value="birthDay",required=false)String birthDay,
											@ApiParam(name="headPortrait",value="头像图片",required=true) @RequestParam(value="headPortrait",required=false)MultipartFile headPortrait
	){
		Result result=new Result();
		if(userId==null || userId=="") {
			result.setMessage("登陆超时，请重新登录");
			result.setStatus(Result.FAILED);
			return result;
		}
		UserInfo user = new UserInfo();
		try {
			user.setId(userId);
			user.setNickname(nickName);
			user.setSex(sex);
			if(headPortrait != null && !"".equals(headPortrait.getOriginalFilename())){
				String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
				String str=UploadUtil.uploadHeadImg(request,userId).get(0);
				user.setHeadPortrait(str);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		result = userService.updatephone(user,headPortrait,birthDay);
		return result;
	}
	/**
	 * 修改密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateuserpassword",method=RequestMethod.POST)
	@ApiOperation(value="修改密码",httpMethod="POST",response=Result.class)
	public @ResponseBody Result updateuserpassword(HttpServletRequest request,
												   @ApiParam(name="password",value="密码",required=true) @RequestParam(value="password",required=false)String password,
												   @ApiParam(name="newPassword",value="新密码",required=true) @RequestParam(value="newPassword",required=false)String newPassword,
												   @ApiParam(name="userId",value="用户id",required=true) @RequestParam(value="userId",required=false)String userId
	){
		Result result=new Result();
		if(userId==null || userId=="") {
			result.setMessage("登陆超时，请重新登录");
			result.setStatus(Result.FAILED);
			return result;
		}
		result = userService.updateuserpassword(password,newPassword,userId);
		return result;
	}
	/**
	 * 发送短信
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "发送短信", httpMethod="GET",response=Result.class )
	@RequestMapping(value="/shortMessage",method=RequestMethod.GET)
	public @ResponseBody Result shortMessage(HttpServletRequest request,
											 @ApiParam(name="phone",value="电话",required=true)@RequestParam("phone")String phone,
											 @ApiParam(name="type",value="类型1查询没有这个手机号 2查询有这个手机号 3微信绑定已有的手机号",required=true)@RequestParam("type")String type){
		Result result =new Result();
		result = userService.shortMessage(phone,type);
		return result;
	}
	/**
	 * 用户登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	@ApiOperation(value="用户登录",httpMethod="GET",response=Result.class)
	public @ResponseBody DataResult login(HttpServletRequest request,
										  @ApiParam(name="phone",value="手机号",required=true) @RequestParam(value="phone",required=false)String phone,
										  @ApiParam(name="code",value="验证码",required=false) @RequestParam(value="code",required=false)String code,
										  @ApiParam(name="password",value="密码",required=false) @RequestParam(value="password",required=false)String password
	){
		DataResult result=new DataResult();
		UserInfo userinfo=new UserInfo();
		userinfo.setPhone(phone);
		userinfo.setPassword(password);
		result = userService.login(userinfo,code);
		return result;
	}
	/**
	 * 查询我的
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryMy",method=RequestMethod.GET)
	@ApiOperation(value="查询我的",httpMethod="GET",response=Result.class)
	public @ResponseBody DataResult queryMy(HttpServletRequest request,
											@ApiParam(name="userId",value="用户id",required=true) @RequestParam(value="userId",required=false)String userId){
		DataResult result=new DataResult();
		if(userId==null || userId=="") {
			result.setMessage("登陆超时，请重新登录");
			result.setStatus(Result.FAILED);
			return result;
		}
		result = userService.queryMy(userId);
		return result;
	}
	/**
	 * 查询我的中奖纪录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryMyPrize",method=RequestMethod.GET)
	@ApiOperation(value="查询我的中奖纪录",httpMethod="GET",response=Result.class)
	public @ResponseBody DataResult queryMyPrize(HttpServletRequest request,
											@ApiParam(name="userId",value="用户id",required=true) @RequestParam(value="userId",required=false)String userId){
		DataResult result=new DataResult();
		if(userId==null || userId=="") {
			result.setMessage("登陆超时，请重新登录");
			result.setStatus(Result.FAILED);
			return result;
		}
		result = userService.queryMyPrize(userId);
		return result;
	}
	/**
	 * 查询我的优惠券信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryMyCoupon",method=RequestMethod.GET)
	@ApiOperation(value="查询我的优惠券信息",httpMethod="GET",response=Result.class)
	public @ResponseBody DataResult queryMyCoupon(HttpServletRequest request,
												 @ApiParam(name="userId",value="用户id",required=true) @RequestParam(value="userId",required=false)String userId){
		DataResult result=new DataResult();
		if(userId==null || userId=="") {
			result.setMessage("登陆超时，请重新登录");
			result.setStatus(Result.FAILED);
			return result;
		}
		result = userService.queryMyCoupon(userId);
		return result;
	}
	/**
	 *  礼物机
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryMyGift",method=RequestMethod.GET)
	@ApiOperation(value="查询我的礼物机",httpMethod="GET",response=Result.class)
	public @ResponseBody DataResult queryMyGift(HttpServletRequest request,
												  @ApiParam(name="userId",value="用户id",required=true) @RequestParam(value="userId",required=false)String userId){
		DataResult result=new DataResult();
		if(userId==null || userId=="") {
			result.setMessage("登陆超时，请重新登录");
			result.setStatus(Result.FAILED);
			return result;
		}
		result = userService.queryMyGift(userId);
		return result;
	}
	
	/**
	 * 用户授权获取支付宝userid
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/openAuthSdkCodeGet",method=RequestMethod.POST)
	@ApiOperation(value="用户授权获取支付宝userid",httpMethod="POST",response=Result.class)
	public @ResponseBody DataResult openAuthSdkCodeGet(HttpServletRequest request,
												  @ApiParam(name="userId",value="用户id",required=true) @RequestParam(value="userId",required=true)String userId){
		DataResult result=new DataResult();
		try {
			result = userService.openAuthSdkCodeGet(userId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Result.FAILED);
			result.setMessage("开盒子支付失败");
			return result;
		}
	}
}

