package com.project.appinterface.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.project.appinterface.domain.*;
import com.project.appinterface.mapper.*;
import com.project.appinterface.service.IUserInfoService;
import com.project.appinterface.util.AliPayUtil;
import com.project.appinterface.util.ConfirmationNumberUtil;
import com.project.appinterface.util.SMSsend;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.project.util.MoneyUtil;
import com.project.util.RedisUtil;
import com.project.util.UUIDUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户 服务层实现
 * 
 * @author lws
 * @date 2019-03-04
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService
{
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private WalletMapper walletMapper;
	@Autowired
	private GiftMachineMapper giftMachineMapper;
	@Autowired
	private GiftApplyMapper giftApplyMapper;
	@Autowired
	private CommodityInformationMapper commodityInformationMapper;
	@Autowired
	private CouponReceiveMapper couponReceiveMapper;
	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	private ParameterMapper parameterMapper;
	@Autowired
	private CommonInterfaceMapper commonInterfaceMapper;
	/**
     *  用户登录
     *
	 * */

	@Override
	public DataResult login(UserInfo userInfo, String code) {
		DataResult result = new DataResult();
		UserInfo user=userInfoMapper.selectUserInfoById(userInfo);
		if (user == null) {
			result.setMessage("该手机号未注册");
			result.setStatus(Result.FAILED);
			return result;
		}
		if ("1".equals(user.getState())) {
			result.setMessage("该账户已被冻结请联系客服");
			result.setStatus(Result.FAILED);
			return result;
		}
		if (!userInfo.getPassword().equals(user.getPassword())) {
			result.setMessage("账号或密码错误");
			result.setStatus(Result.FAILED);
			return result;
		}
		Map<String, String> mapResult = new HashMap<>();
		mapResult.put("userId", user.getId());
		result.setResult(mapResult);
		result.setMessage("登陆成功");
		result.setStatus(Result.SUCCESS);
		return result;
	}

	@Override
	public DataResult queryMy(String userId) {
		DataResult result=new DataResult();
		try{
		UserInfo userInfo=new UserInfo();
		userInfo.setId(userId);
		UserInfo user=userInfoMapper.selectUserInfoById(userInfo);
		Wallet wallet=walletMapper.selectWalletByUserId(userId);
		Map<String,Object> map=new HashMap<>();
		if(user != null){
			map.put("headPortrait", user.getHeadPortrait());
			map.put("name",user.getNickname());
			map.put("sex",user.getSex());
			map.put("birthDay",user.getBirthDay());
			map.put("invitationCode",user.getInvitationCode());
			map.put("phone",user.getPhone());
		}
		Map<String,String> wmap=new HashMap<>();
		if(wallet!=null){
			if(wallet.getProfit()==null){
				wmap.put("profit", "0.00");
			}else{
				wmap.put("profit", MoneyUtil.toYuan(wallet.getProfit()));
			}
			if(wallet.getBalance()==null){
				wmap.put("balance", "0.00");
			}else{
				wmap.put("balance", MoneyUtil.toYuan(wallet.getBalance()));
			}
			if(wallet.getDeposit()==null){
				wmap.put("deposit", "0.00");
			}else{
				wmap.put("deposit", MoneyUtil.toYuan(wallet.getDeposit()));
			}
		}else{
				wmap.put("profit", "0.00");
				wmap.put("balance", "0.00");
				wmap.put("deposit", "0.00");
		}

		map.put("wallet" ,wmap);
		result.setResult(map);
		result.setMessage("查询成功");
		result.setStatus(Result.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result register(String phone, String code, String password, String invitationCode) {
		Result result = new Result();
		String strcode = redisUtil.get(phone).toString();
		UserInfo userInfo=new UserInfo();
		userInfo.setPhone(phone);
		UserInfo isRegister = this.userInfoMapper.selectUserInfoById(userInfo);
		if (isRegister != null) {
			result.setMessage("该手机号已经注册!");
			result.setStatus(Result.FAILED);
			return result;
		}
		if (strcode != null && !"".equals(strcode)) {
			if (code.equals(strcode)) {
				UserInfo user = new UserInfo();
				user.setId(UUIDUtil.getUUID());
				user.setPassword(password);
				user.setPhone(phone);
				user.setHeadPortrait("");
				user.setCreateDate(new Date());
				user.setUpdateDate(new Date());
				user.setInvitationCode(ConfirmationNumberUtil.toSerialCode(6));
				user.setState("0");
				user.setNickname("用户_"+phone);
				user.setInviterCode(invitationCode);
				userInfoMapper.insertUserInfo(user);
				Wallet wallet = walletMapper.selectWalletByUserId(user.getId());
				if(wallet == null) {
					Wallet walletu = new Wallet();
					walletu.setId(UUIDUtil.getUUID());
					walletu.setUserId(user.getId());
					walletu.setBalance(0l);
					walletu.setProfit(0l);
					walletu.setDeposit(0l);
					walletMapper.insertWallet(walletu);
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", user.getId());
				result.setMessage("注册成功");
				result.setStatus(Result.SUCCESS);
				redisUtil.del(phone);
				if(invitationCode!=null && !"".equals(invitationCode)){
					UserInfo userInfoi=new UserInfo();
					userInfoi.setInvitationCode(invitationCode);
					UserInfo u = this.userInfoMapper.selectUserInfoById(userInfoi);
					if(u!=null){
						//没加优惠券id
						CouponReceive couponReceivecrm=new CouponReceive();
						couponReceivecrm.setId(UUIDUtil.getUUID());
						couponReceivecrm.setUserId(u.getId());
						couponReceivecrm.setState("0");
						couponReceivecrm.setCreateDate(new Date());
						couponReceivecrm.setUpdateDate(new Date());
						Coupon cou=couponMapper.selectCouponById();
						couponReceivecrm.setCouponId(cou.getId());
						couponReceiveMapper.insertCouponReceive(couponReceivecrm);
					}
				}
			} else {
				result.setMessage("请输入正确的验证码");
				result.setStatus(Result.FAILED);
			}
		} else {
			result.setMessage("验证码失效请重新发送！");
			result.setStatus(Result.FAILED);
		}
		return result;
	}

	@Override
	public Result forgotpassword(String phone, String code, String password) {
		Result result = new Result();
		UserInfo userInfo=new UserInfo();
		userInfo.setPhone(phone);
		UserInfo user = this.userInfoMapper.selectUserInfoById(userInfo);
		String strcode = redisUtil.get(phone).toString();
		if (strcode != null) {
			if (strcode.equals(code)) {
				if (user != null) {
					user.setPassword(password);
					userInfoMapper.updateUserInfo(user);
					result.setMessage("修改成功");
					result.setStatus(Result.SUCCESS);
				} else {
					result.setMessage("没有该账号");
					result.setStatus(Result.FAILED);
				}
			} else {
				result.setMessage("验证码错误请重新输入！");
				result.setStatus(Result.FAILED);
			}
		} else {
			result.setMessage("验证码失效请重新发送！");
			result.setStatus(Result.FAILED);
		}
		return result;
	}

	@Override
	public Result updatephone(String code, String password, String newPhone, String userId) {
		Result result = new Result();
		UserInfo userInfo=new UserInfo();
		userInfo.setId(userId);
		UserInfo user = this.userInfoMapper.selectUserInfoById(userInfo);
		if (user == null) {
			result.setStatus("10086");
			result.setMessage("登录已失效!");
			return result;
		}
		if (!user.getPassword().equals(password)) {
			result.setStatus(Result.FAILED);
			result.setMessage("密码错误");
			return result;
		}

		String strcode = redisUtil.get(newPhone).toString();
		if (strcode != null) {
			if (strcode.equals(code)) {
				UserInfo users = new UserInfo();
				users.setPhone(newPhone);
				users.setId(userId);
				userInfoMapper.updateUserInfo(users);
				result.setMessage("修改成功");
				result.setStatus(Result.SUCCESS);
			} else {
				result.setMessage("验证码错误请重新输入！");
				result.setStatus(Result.FAILED);
			}
		} else {
			result.setMessage("验证码失效请重新发送！");
			result.setStatus(Result.FAILED);
		}
		return result;
	}

	@Override
	public Result updatephone(UserInfo user, MultipartFile headPortrait, String birthDay) {
		Result result=new Result();
		try {
		    if(headPortrait!=null){
//                String headPortraitUrl= UploadUtil.upload(request,user.getId()).get(0);
//                user.setHeadPortrait(headPortraitUrl);
        }
    SimpleDateFormat formatterdate = new SimpleDateFormat("yyyy-MM-dd");
			if(birthDay!=null){
                    user.setBirthDay(formatterdate.parse(birthDay));
                    }
                    userInfoMapper.updateUserInfo(user);
                    }catch (Exception e){
                    result.setMessage("修改失败！");
                    result.setStatus(Result.FAILED);
                    e.printStackTrace();
                    }
                    result.setMessage("修改成功！");
                    result.setStatus(Result.SUCCESS);
                    return result;
                    }

	@Override
	public Result updateuserpassword(String password, String newPassword, String userId) {
		Result result = new Result();
		UserInfo userInfo=new UserInfo();
		userInfo.setId(userId);
		UserInfo user = this.userInfoMapper.selectUserInfoById(userInfo);
		if (user == null) {
			result.setMessage("登录已失效，请重新登陆！");
			result.setStatus("10086");
			return result;
		}
		if (!user.getPassword().equals(password)) {
			result.setMessage("原密码不正确");
			result.setStatus(Result.FAILED);
			return result;
		}
		user.setPassword(newPassword);
		userInfoMapper.updateUserInfo(user);
		result.setMessage("修改密码成功");
		result.setStatus(Result.SUCCESS);
		return result;
	}

	@Override
	public Result shortMessage(String phone, String type) {
		Result result = new Result();
		try {
			System.out.println(type);
			UserInfo userInfo=new UserInfo();
			userInfo.setPhone(phone);
			UserInfo isRegister = this.userInfoMapper.selectUserInfoById(userInfo);
			if ("1".equals(type)) {
				if (isRegister != null) {
					result.setMessage("该手机号已经注册!");
					result.setStatus(Result.FAILED);
					return result;
				}
			} else if ("2".equals(type)) {
				if (isRegister == null) {
					result.setMessage("该手机号未注册!");
					result.setStatus(Result.FAILED);
					return result;
				}
			} else if ("3".equals(type)) {
				if (isRegister != null) {
					result.setMessage("该手机号已经注册,如需绑定微信，请登录后在个人信息进行绑定");
					result.setStatus("3");
					return result;
				}
			}
			int code = (int) ((Math.random() * 9 + 1) * 100000);
			if (SMSsend.SMSSend(phone, code + "")) {
				boolean tstr= redisUtil.set(phone, code + "", 60 * 5);
				if (tstr) {
					result.setMessage("发送成功！");
					result.setStatus(Result.SUCCESS);
				} else {
					result.setMessage("发送失败！");
					result.setStatus(Result.FAILED);
				}
			} else {
				result.setMessage("操作繁忙请稍后重试");
				result.setStatus(Result.FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DataResult queryMyPrize(String userId) {
		return null;
	}

	@Override
	public DataResult queryMyCoupon(String userId) {
		DataResult result=new DataResult();
		List<CouponReceive> crlist=giftMachineMapper.queryCoupon( userId);
		List<Map<String,Object>> rlist=new ArrayList<Map<String, Object>>();
		SimpleDateFormat formatterdate = new SimpleDateFormat("yyyy.MM.dd");
		for (CouponReceive couponReceive:crlist){
			Map<String,Object> map=new HashMap<>();
			map.put("money",MoneyUtil.toYuan(couponReceive.getMoney()));
            map.put("couponId",couponReceive.getId());
			map.put("state",couponReceive.getState());
			map.put("effectiveStart",formatterdate.format(couponReceive.getEffectiveStart()));
			map.put("effectiveEnd",formatterdate.format(couponReceive.getEffectiveEnd()));
			rlist.add(map);
		}
		result.setResult(rlist);
		result.setMessage("查询成功");
		result.setStatus(Result.SUCCESS);
		return result;
	}

	@Override
	public DataResult queryMyGift(String userId) {
		DataResult result=new DataResult();
		//查询机器基本信息
		List<Gift> glist=giftMachineMapper.queryGift(userId);
		List<Map<String,Object>> rlist=new ArrayList<>();
		for (Gift gift:glist){
			Map<String,Object> rmap=new HashMap<>();
			GiftApply giftApply=giftApplyMapper.selectGiftApplyById(gift.getGiftApplyId());
			//失效
			if("2".equals(gift.getApplyState())) {
				rmap.put("day",0);
			}else {
				if(gift.getLcreateDate() != null){
					//0按天1按月
					Calendar c = Calendar.getInstance();
					c.setTime(gift.getLcreateDate());   //设置时间
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
					//剩余多少天
					int num=0;
					if(giftApply.getTimeType().equals("0")){
						c.add(Calendar.DATE, giftApply.getNumber()); //日期分钟加1,Calendar.DATE(天),Calendar.HOUR(小时)
						Date newDate = c.getTime(); //结果
						num=daysBetween(dateFormat.format(new Date()),dateFormat.format(newDate));
					}else{
						c.add(Calendar.MINUTE, giftApply.getNumber()); //日期分钟加1,Calendar.DATE(天),Calendar.HOUR(小时)
						Date newDate = c.getTime(); //结果
						num=daysBetween(dateFormat.format(new Date()),dateFormat.format(newDate));
					}
					if(num < 0) {
						num = 0;
					}
					rmap.put("day",num);
				}else{
					rmap.put("day",gift.getDayTotalNumber());
				}
			}
			if(gift.getModelName()==null){
				rmap.put("giftName","");
			}else{
				rmap.put("giftName",gift.getModelName());
			}
			rmap.put("giftId", gift.getId());
			rmap.put("state", gift.getApplyState());
			rmap.put("modelPicture",gift.getModelPicture());
			rmap.put("model",gift.getModel());
			rmap.put("latticePrice",MoneyUtil.toYuan(gift.getLattice_price()));
			rmap.put("surplusPosition",gift.getSurplusPosition());
			rmap.put("totalPosition",gift.getTotalPosition());
			SysAreas sysAress = commonInterfaceMapper.getAddressById(gift.getLocationName());
			if(sysAress != null){
				rmap.put("locationName",sysAress.getShort_name());
			}
			//查询机器绑定商品信息
			List<CommodityInformation> cglist=commodityInformationMapper.selectGiftGoods( giftApply.getId());
			List<Map<String,Object>> goodsList=new ArrayList<>();
			for(CommodityInformation ci:cglist){
				Map<String,Object> map=new HashMap<>();
				map.put("goodsName",ci.getName());
				map.put("goodsNumber",ci.getNum());
				map.put("picture",ci.getPicture());
				map.put("price",MoneyUtil.toYuan(ci.getPrice()));
				goodsList.add(map);
			}
			rmap.put("goodsList",goodsList);
			//查询机器支付信息
			Parameter parameter=new Parameter();
			parameter.setState("0");
			List<Parameter> plist=parameterMapper.selectParameterList(parameter);
			String moneyStr="";
			if(giftApply.getTimeType().equals("0")){
				for(Parameter p:plist){
						if(p.getType().equals("3")){
							moneyStr=p.getParameterContent();
						}
					}

				}else{
					for(Parameter p:plist){
						if(p.getType().equals("0")){
							moneyStr=p.getParameterContent();
						}
					}
			}
			PayOrder po=giftMachineMapper.queryPayGift(gift.getId(),userId);
			if(po != null){
				if(po.getType().equals("1")){
					rmap.put("payType","支付宝");
				}else{
					rmap.put("payType","微信");
				}
				rmap.put("moneyPay",MoneyUtil.toYuan(po.getMoney()-giftApply.getNumber() * MoneyUtil.toFen(moneyStr)));
			}
			rlist.add(rmap);
		}

		result.setResult(rlist);
		result.setMessage("查询成功");
		result.setStatus(Result.SUCCESS);
		return result;
	}

	@Override
	public DataResult openAuthSdkCodeGet(String userId) {
		DataResult result = new DataResult();
		String urlParam = AliPayUtil.openAuthSdkCodeGet(userId);
		if(StringUtils.isNotEmpty(urlParam)){
			result.setMessage("授权成功");
			result.setResult(urlParam);
			result.setStatus(Result.SUCCESS);
		}else{
			result.setMessage("授权失败");
			result.setStatus(Result.FAILED);
		}
		return result;
	}

	public static void main(String[] args) {
		int a=daysBetween("2019-03-02","2019-03-06");
		System.out.println(a);
	}
	/**
	 *
	 * @param startDay 开始日期
	 * @param endDay 截止日期
	 * @return
	 */
	public static int daysBetween(String startDay, String endDay) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = sdf.parse(startDay);
			date2 = sdf.parse(endDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	@Override
	public UserInfo selectUserInfoByUserId(String id) {
		return userInfoMapper.selectUserInfoByUserId(id);
	}

}
