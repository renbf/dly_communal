package com.project.appinterface.service;


import com.project.appinterface.domain.UserInfo;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户 服务层
 * 
 * @author lws
 * @date 2019-03-04
 */
public interface IUserInfoService 
{
	

	
	/**
     * 用户登录
     * 
     * @param userInfo 用户信息
     * @return 结果
     */
	public DataResult login(UserInfo userInfo, String code);

	/**
	 * 查询我的
	 * @param userId
	 * @return
	 */
	public DataResult queryMy(String userId);

	/**
	 * 账号注册
	 * @param phone
	 * @param code
	 * @param password
	 * @param invitationCode
	 * @return
	 */
	Result register(String phone,String code,String password,String invitationCode);

	/**
	 * 忘记密码
	 * @param phone
	 * @param code
	 * @param password
	 * @return
	 */
	Result forgotpassword(String phone,String code,String password);

	/**
	 * 修改手机号
	 * @param code
	 * @param password
	 * @param newPhone
	 * @param userId
	 * @return
	 */
	Result updatephone(String code,String password,String newPhone,String userId);

	/**
	 * 完善个人信息
	 * @param user
	 * @param headPortrait
	 * @param birthDay
	 * @return
	 */
	Result updatephone(UserInfo user, MultipartFile headPortrait, String birthDay);
	/**
	 * 修改密码
	 * @param password
	 * @param newPassword
	 * @param userId
	 * @return
	 */
	Result updateuserpassword(String password, String newPassword, String userId);
	/**
	 * 发送验证码
	 * @param phone
	 * @param type
	 * @return
	 */
	Result shortMessage(String phone,String type);
	/**
	 * 查询中奖纪录
	 * @param userId
	 * @return
	 */
	public DataResult queryMyPrize(String userId);

	/**
	 * 查询优惠券信息
	 * @param userId
	 * @return
	 */
	public DataResult queryMyCoupon(String userId);

	/**
	 * 查询我的礼物机
	 * @param userId
	 * @return
	 */
	public DataResult queryMyGift(String userId);
	/**
	 * 用户授权获取支付宝userid
	 * @param userId
	 * @return
	 */
	public DataResult openAuthSdkCodeGet(String userId);
	
}
