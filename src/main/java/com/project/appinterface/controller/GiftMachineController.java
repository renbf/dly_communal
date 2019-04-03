package com.project.appinterface.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.project.appinterface.domain.ParamArrayVo;
import com.project.appinterface.domain.UserAddress;
import com.project.appinterface.service.GiftMachineService;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.project.util.UUIDUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiParam;


/**
 *
 * @author 张鹏浩
 * @ApiOperation(value = “接口说明”, httpMethod = “接口请求方式”, response = “接口返回参数类型”, notes = “接口发布说明”；produces=“media类型”；)
 * @ApiParam(required = “是否必须参数”, name = “参数名称”, value = “参数具体描述”)
 */

@Controller
@RequestMapping("gift/giftMachine")
@Api(value="礼物机",description="礼物机")
public class GiftMachineController {
	@Autowired
	GiftMachineService giftMachineService;
    /********************************      礼物机查询      **********************************/
    /**
     *  查询礼物机id
     * @param request
     * @return
     */
    @RequestMapping(value="/queryGiftId",method=RequestMethod.GET)
    @ApiOperation(value="查询礼物机id",httpMethod="GET",response=Result.class)
    public @ResponseBody DataResult queryGiftId(HttpServletRequest request,
                                              @ApiParam(name="longitude",value="0可申请1已申请",required=true) @RequestParam(value="longitude",required=false)String longitude,
											  @ApiParam(name="latitude",value="0可申请1已申请",required=true) @RequestParam(value="latitude",required=false)String latitude
    ){
        DataResult result=new DataResult();
        try {
			result = giftMachineService.queryGiftId(longitude, latitude);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("查询失败");
			result.setStatus(Result.FAILED);
			return result;
		}
    }
	/********************************      领礼物      **********************************/
	/**
	 * 查询礼物机
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryGift",method=RequestMethod.GET)
	@ApiOperation(value="查询礼物机",httpMethod="GET",response=Result.class)
	public @ResponseBody DataResult queryGift(HttpServletRequest request,
										 @ApiParam(name="state",value="0可申请1已申请",required=true) @RequestParam(value="state",required=false)String state,
										 @ApiParam(name="user_id",value="用户id",required=false) @RequestParam(value="user_id",required=false)String user_id
	){
		DataResult result=new DataResult();
		result=giftMachineService.queryGift(state,user_id) ;
		return result;
	}
    /**
     * 查询礼物机类型
     * @param request
     * @return
     */
    @RequestMapping(value="/queryGiftModel",method=RequestMethod.GET)
    @ApiOperation(value="查询礼物机类型",httpMethod="GET",response=Result.class)
    public @ResponseBody DataResult queryGiftModel(HttpServletRequest request
    ){
        DataResult result=new DataResult();
        result=giftMachineService.queryGiftModel() ;
        return result;
    }
	/**
	 * 查询礼物机详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryById",method=RequestMethod.GET)
	@ApiOperation(value="查询礼物机详情",httpMethod="GET",response=Result.class)
	public @ResponseBody DataResult queryById(HttpServletRequest request,
											  @ApiParam(name="giftId",value="礼物机id",required=true) @RequestParam(value="giftId",required=false)String giftId,
											  @ApiParam(name="userId",value="用户id",required=true) @RequestParam(value="userId",required=false)String userId
	){
		DataResult result=new DataResult();
		result=giftMachineService.queryById(giftId,userId);
		return result;
	}
	/**
	 * 查询是否中奖
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/isPrize",method=RequestMethod.GET)
	@ApiOperation(value="查询是否中奖",httpMethod="GET",response=Result.class)
	public @ResponseBody DataResult isPrize(HttpServletRequest request,
											  @ApiParam(name="orderNo",value="支付订单号",required=true) @RequestParam(value="orderNo",required=false)String orderNo,
											  @ApiParam(name="userId",value="用户id",required=true) @RequestParam(value="userId",required=false)String userId,
										@ApiParam(name="giftId",value="礼物机id",required=true) @RequestParam(value="giftId",required=false)String giftId
	){
		DataResult result=new DataResult();
		result=giftMachineService.isPrize(orderNo,userId,giftId);
		return result;
	}
	/**
	 * 查询订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryOrder",method=RequestMethod.GET)
	@ApiOperation(value="查询订单",httpMethod="GET",response=Result.class)
	public @ResponseBody DataResult queryOrder(HttpServletRequest request,
											@ApiParam(name="orderId",value="订单id",required=true) @RequestParam(value="orderId",required=false)String orderId
	){
		DataResult result=new DataResult();
		result=giftMachineService.queryOrder(orderId);
		return result;
	}
	/**
	 * 完善订单信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/createOrder",method=RequestMethod.POST)
	@ApiOperation(value="完善订单",httpMethod="POST",response=Result.class)
	public @ResponseBody Result updateOrder(HttpServletRequest request,
											@ApiParam(name="orderId",value="订单id",required=true) @RequestParam(value="orderId",required=false)String orderId,
											@ApiParam(name="userId",value="用户id",required=true) @RequestParam(value="userId",required=false)String userId,
											@ApiParam(name="addressId",value="收货地址id",required=true) @RequestParam(value="addressId",required=false)String addressId
	){
		Result result=new Result ();
		result=giftMachineService.updateOrder(orderId,userId,addressId);
		return result;
	}
	/**
	 * 查询所有商品
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryGoodsAll",method=RequestMethod.GET)
	@ApiOperation(value="查询所有商品",httpMethod="GET",response=Result.class)
	public @ResponseBody DataResult queryGoodsAll(HttpServletRequest request){
		DataResult result=new DataResult();
		result=giftMachineService.queryGoodsAll(null);
		return result;
	}
	/**
	 *  新增收货地址
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "新增收货地址", httpMethod="POST",response=Result.class )
	@RequestMapping(value="/insertCollectAddress",method=RequestMethod.POST)
	public @ResponseBody Result insertCollectAddress(HttpServletRequest request,
													 @ApiParam(name="name",value="收货人姓名",required=true)@RequestParam("name")String name,
													 @ApiParam(name="phone",value="收货人电话",required=true)@RequestParam("phone")String phone,
													 @ApiParam(name="consigneeaddress",value="收货地址",required=true)@RequestParam("consigneeaddress")String consigneeaddress,
													 @ApiParam(name="defaultaddress",value="是否是默认地址0是1不是",required=true)@RequestParam("defaultaddress")String defaultaddress,
													 @ApiParam(name="consigneeaddressdetail",value="收货详细地址",required=true)@RequestParam("consigneeaddressdetail")String consigneeaddressdetail,
													 @ApiParam(name="userid",value="用户id",required=true)@RequestParam("userid")String userid){
		Result result =new Result();
		UserAddress userAddress=new UserAddress();
		userAddress.setId(UUIDUtil.getUUID());
		userAddress.setReceiver(name);
		userAddress.setAddress(consigneeaddress);
		userAddress.setTelephone(phone);
		userAddress.setAddress_detailed(consigneeaddressdetail);
		userAddress.setDefault_address(defaultaddress);
		userAddress.setUser_id(userid);
		result = giftMachineService.insertCollectAddress(userAddress);
		return result;
	}
	/**
	 *  修改收货地址
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "修改收货地址", httpMethod="POST",response=Result.class )
	@RequestMapping(value="/updateCollectAddress",method=RequestMethod.POST)
	public @ResponseBody Result updateCollectAddress(HttpServletRequest request,
													@ApiParam(name="addressid",value="收货地址id",required=true)@RequestParam("addressid")String addressid,
													 @ApiParam(name="name",value="收货人姓名",required=true)@RequestParam("name")String name,
													 @ApiParam(name="phone",value="收货人电话",required=true)@RequestParam("phone")String phone,
													 @ApiParam(name="consigneeaddress",value="收货地址",required=true)@RequestParam("consigneeaddress")String consigneeaddress,
													 @ApiParam(name="userid",value="用户id",required=true)@RequestParam("userid")String userid,
													 @ApiParam(name="defaultaddress",value="是否是默认地址0是1不是",required=true)@RequestParam("defaultaddress")String defaultaddress,
													 @ApiParam(name="consigneeaddressdetail",value="收货详细地址",required=true)@RequestParam("consigneeaddressdetail")String consigneeaddressdetail){
		Result result =new Result();
		Map<String,String> map=new HashMap<String,String>();
		map.put("id", addressid);
		map.put("user_id",userid);
		map.put("receiver", name);
		map.put("address", consigneeaddress);
		map.put("telephone", phone);
		map.put("address_detailed", consigneeaddressdetail);
		map.put("default_address", defaultaddress);
		result = giftMachineService.updateCollectAddress(map);
		return result;
	}
	/**
	 *  删除收货地址
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "删除收货地址", httpMethod="GET",response=Result.class )
	@RequestMapping(value="/deleteCollectAddress",method=RequestMethod.GET)
	public @ResponseBody Result deleteCollectAddress(HttpServletRequest request,
													 @ApiParam(name="addressid",value="收货地址id",required=true)@RequestParam("addressid")String addressid){
		Result result =new Result();
		Map<String,String> map=new HashMap<String,String>();
		map.put("id", addressid);
		result = giftMachineService.deleteCollectAddress(map);
		return result;
	}
	/**
	 *  设置为默认收货地址
	 * @param request
	 * @return
	 */
	@ApiOperation(value = " 设置为默认收货地址", httpMethod="POST",response=Result.class )
	@RequestMapping(value="/updateDefault",method=RequestMethod.POST)
	public @ResponseBody Result updateDefault(HttpServletRequest request,
											  @ApiParam(name="addressid",value="收货地址id",required=true)@RequestParam("addressid")String addressid,
											  @ApiParam(name="userid",value="用户id",required=true)@RequestParam("userid")String userid){
		Result result =new Result();
		Map<String,String> map=new HashMap<String,String>();
		map.put("id", addressid);
		map.put("default_address", "0");
		result = giftMachineService.updateDefault(map,userid);
		return result;
	}
	/**
	 *  查询默认收货地址
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "查询默认收货地址", httpMethod="GET",response=Result.class )
	@RequestMapping(value="/queryDefault",method=RequestMethod.GET)
	public @ResponseBody DataResult queryDefault(HttpServletRequest request,
												 @ApiParam(name="userid",value="用户id",required=true)@RequestParam("userid")String userid){
		DataResult result =new DataResult();
		result = giftMachineService.queryDefault(userid);
		return result;
	}
	/**
	 *  查询收货地址
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "查询收货地址", httpMethod="GET",response=Result.class )
	@RequestMapping(value="/getCollectgoodsAddress",method=RequestMethod.GET)
	public @ResponseBody DataResult getCollectgoodsAddress(HttpServletRequest request,
														   @ApiParam(name="userid",value="用户id",required=true)@RequestParam("userid")String userid){
		DataResult result =new DataResult();
		result = giftMachineService.getCollectgoodsAddress(userid);
		return result;
	}
	/********************************      查询支付状态      **********************************/
	/**
	 *  查询支付状态
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "查询支付状态", httpMethod="GET",response=Result.class )
	@RequestMapping(value="/queryPayState",method=RequestMethod.GET)
	public @ResponseBody Result queryPayState(HttpServletRequest request,
												 @ApiParam(name="orderNo",value="订单号",required=true)@RequestParam("orderNo")String orderNo){
		Result result =new Result();
		result = giftMachineService.queryPayState(orderNo);
		return result;
	}
	/**
	 *  查询消费记录
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "查询消费记录", httpMethod="GET",response=Result.class )
	@RequestMapping(value="/queryRecordsConsumption",method=RequestMethod.GET)
	public @ResponseBody DataResult queryRecordsConsumption(HttpServletRequest request,
															@ApiParam(name="userId",value="用户id",required=true)@RequestParam("userId")String userId){
		DataResult result =new DataResult();
		result = giftMachineService.queryRecordsConsumption(userId);
		return result;
	}
	/********************************      抽奖记录      **********************************/
	/**
	 *  查询未中奖
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "查询未中奖", httpMethod="GET",response=Result.class )
	@RequestMapping(value="/queryNoPrize",method=RequestMethod.GET)
	public @ResponseBody DataResult queryNoPrize(HttpServletRequest request,
												   @ApiParam(name="userId",value="用户id",required=true)@RequestParam("userId")String userId){
		DataResult result =new DataResult();
		result = giftMachineService.queryNoPrize(userId);
		return result;
	}
	/**
	 *  查询中奖订单
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "查询中奖订单", httpMethod="GET",response=Result.class )
	@RequestMapping(value="/queryPrizeOrder",method=RequestMethod.GET)
	public @ResponseBody DataResult queryPrizeOrder(HttpServletRequest request,
												 @ApiParam(name="userId",value="用户id",required=true)@RequestParam("userId")String userId,
													@ApiParam(name="state",value="状态0待发货1待收货2已完成3已失效4待领取5查询全部",required=true)@RequestParam("state")String state){
		DataResult result =new DataResult();
		result = giftMachineService.queryPrizeOrder(userId,state);
		return result;
	}
	/**
	 *  查询订单详情
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "查询订单详情", httpMethod="GET",response=Result.class )
	@RequestMapping(value="/queryOrderByUserId",method=RequestMethod.GET)
	public @ResponseBody DataResult queryWinningPrize(HttpServletRequest request,
													  @ApiParam(name="orderId",value="订单id",required=true)@RequestParam("orderId")String orderId){
		DataResult result =new DataResult();
		result = giftMachineService.queryOrderByUserId(orderId);
		return result;
	}
	/**
	 *  确认收货
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "确认收货", httpMethod="GET",response=Result.class )
	@RequestMapping(value="/getRconfirmCollect",method=RequestMethod.GET)
	public @ResponseBody Result getRconfirmCollect(HttpServletRequest request,
															@ApiParam(name="userId",value="用户id",required=true)@RequestParam("userId")String userId,
															@ApiParam(name="orderNo",value="订单号",required=true)@RequestParam("orderNo")String orderNo){
		Result result =new Result();
		result = giftMachineService.getRconfirmCollect(userId,orderNo);
		return result;
	}
	/********************************      查询参数      **********************************/
	/**
	 *  查询消费记录
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "查询消费记录", httpMethod="GET",response=Result.class )
	@RequestMapping(value="/queryConsumptionRecords",method=RequestMethod.GET)
	public @ResponseBody DataResult queryConsumptionRecords(HttpServletRequest request,
															@ApiParam(name="userId",value="用户id",required=true)@RequestParam("userId")String userId){
		DataResult result =new DataResult();
		result = giftMachineService.queryConsumptionRecords(userId);
		return result;
	}
	/********************************      查询参数      **********************************/
	/**
	 *  查询参数
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "查询参数", httpMethod="GET",response=Result.class )
	@RequestMapping(value="/queryRentType",method=RequestMethod.GET)
	public @ResponseBody DataResult queryRentType(HttpServletRequest request){
		DataResult result =new DataResult();
		result = giftMachineService.queryRentType();
		return result;
	}
	/********************************      收藏      **********************************/
	/**
	 *  添加收藏
	 * @param request
	 * @return
	 */
	@ApiOperation(value = " 添加收藏", httpMethod="POST",response=Result.class )
	@RequestMapping(value="/addCollection",method=RequestMethod.POST)
	public @ResponseBody Result addCollection(HttpServletRequest request,
												  @ApiParam(name="giftId",value="礼物机id",required=true)@RequestParam String giftId,
												  @ApiParam(name="userId",value="用户id",required=true)@RequestParam String userId){
		Result result =new Result();
		result = giftMachineService.addCollection(giftId,userId);
		return result;
	}
	/**
	 *  添加收藏
	 * @param request
	 * @return
	 */
	@ApiOperation(value = " 删除收藏", httpMethod="GET",response=Result.class )
	@RequestMapping(value="/deleteCollection",method=RequestMethod.GET)
	public @ResponseBody Result deleteCollection(HttpServletRequest request,
											  @ApiParam(name="collectionId",value="收藏id",required=true)@RequestParam String collectionId){
		Result result =new Result();
		result = giftMachineService.deleteCollection(collectionId);
		return result;
	}
	/**
	 *  查询收藏
	 * @param request
	 * @return
	 */
	@ApiOperation(value = " 查询收藏", httpMethod="GET",response=Result.class )
	@RequestMapping(value="/queryCollection",method=RequestMethod.GET)
	public @ResponseBody DataResult queryCollection(HttpServletRequest request,
												 @ApiParam(name="userId",value="用户id",required=true)@RequestParam String userId){
		DataResult result =new DataResult();
		result = giftMachineService.queryCollection(userId);
		return result;
	}
	/********************************      退款申请      **********************************/
	/**
	 *  退款
	 * @param request
	 * @return
	 */
	@ApiOperation(value = " 退款", httpMethod="POST",response=Result.class )
	@RequestMapping(value="/refundApplication",method=RequestMethod.POST)
	public @ResponseBody Result refundApplication(HttpServletRequest request,
											  @ApiParam(name="userId",value="用户id",required=true)@RequestParam String userId,
												  @ApiParam(name="money",value="金额",required=true)@RequestParam String money){
		Result result =new Result();
		result = giftMachineService.refundApplication(userId,money);
		return result;
	}
	/********************************     押金提现至余额      **********************************/
	/**
	 *  退押金
	 * @param request
	 * @return
	 */
	@ApiOperation(value = " 退押金", httpMethod="POST",response=Result.class )
	@RequestMapping(value="/returnDepositMoney",method=RequestMethod.POST)
	public @ResponseBody DataResult returnDepositMoney(HttpServletRequest request,
												  @ApiParam(name="userId",value="用户id",required=true)@RequestParam String userId,
												  @ApiParam(name="giftId",value="礼物机id",required=true)@RequestParam String giftId){
		DataResult result =new DataResult();
		result = giftMachineService.returnDepositMoney(userId,giftId);
		return result;
	}
	/********************************      申请礼物机      **********************************/
	/**
	 *  支付
	 * @param request
	 * @return
	 */
//	@ApiOperation(value = " 支付", httpMethod="POST",response=Result.class)
//	@RequestMapping(value="/payGift",method=RequestMethod.POST)
//	public @ResponseBody Result updateDefault(HttpServletRequest request,
//											  @RequestBody ParamArrayVo paramArrayVo){
//		Result result =new Result();
//		String ip = getIP(request);
//		result = giftMachineService.payGift(paramArrayVo,ip);
//		return result;
//	}
	/**
	 *  IOS支付
	 * @param request
	 * @return
	 */
	@ApiOperation(value = " IOS支付", httpMethod="POST",response=Result.class)
	@RequestMapping(value="/payGift",method=RequestMethod.POST)
	public @ResponseBody Result updateDefault(HttpServletRequest request,
											  @ApiParam(name="paramArrayVoStr",value="数据",required=true)@RequestParam String paramArrayVoStr){
		Result result =new Result();
		try {
			String ip = getIP(request);
			JSONObject jsStr = JSONObject.parseObject(paramArrayVoStr); //将字符串{“id”：1}
			ParamArrayVo paramArrayVo = (ParamArrayVo) JSONObject.toJavaObject(jsStr, ParamArrayVo.class);
			result = giftMachineService.payGift(paramArrayVo, ip);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Result.FAILED);
			result.setMessage("开盒子支付失败");
			return result;
		}
	}
	
	/**
	 *  开盒子支付
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "开盒子支付", httpMethod="POST",response=Result.class)
	@RequestMapping(value="/tryPayGift",method=RequestMethod.POST)
	public @ResponseBody Result tryPayGift(HttpServletRequest request,
			@ApiParam(name="paramArrayVoStr",value="数据",required=true)@RequestParam String paramArrayVoStr){
		Result result =new Result();
		try {
			String ip = getIP(request);
			JSONObject jsStr = JSONObject.parseObject(paramArrayVoStr); //将字符串{“id”：1}
			ParamArrayVo paramArrayVo = (ParamArrayVo) JSONObject.toJavaObject(jsStr, ParamArrayVo.class);
			result = giftMachineService.tryPayGift(paramArrayVo, ip);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Result.FAILED);
			result.setMessage("开盒子支付失败");
			return result;
		}
	}
	
	/**
	 * 查询支付宝接口
	 * @param orderNo
	 * @return
	 */
	@ApiOperation(value = "查询支付宝接口", httpMethod="POST",response=Result.class)
	@RequestMapping(value="/queryPayInfoByOrderNo",method=RequestMethod.POST)
	public @ResponseBody Result queryPayInfoByOrderNo(HttpServletRequest request,
			@ApiParam(name="paramArrayVoStr",value="数据",required=true)@RequestParam String paramArrayVoStr){
		DataResult result=new DataResult();
		try {
			String ip = getIP(request);
			JSONObject jsStr = JSONObject.parseObject(paramArrayVoStr); //将字符串{“id”：1}
			ParamArrayVo paramArrayVo = (ParamArrayVo) JSONObject.toJavaObject(jsStr,ParamArrayVo.class);
			result = giftMachineService.queryPayInfoByOrderNo(paramArrayVo, ip);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Result.FAILED);
			result.setMessage("查询支付宝接口失败");
			return result;
		}
	}
	
	/**
	 * 退押金接口
	 * @param orderNo
	 * @return
	 */
	@ApiOperation(value = "退押金", httpMethod="POST",response=Result.class)
	@RequestMapping(value="/refundDeposit",method=RequestMethod.POST)
	public @ResponseBody Result refundDeposit(HttpServletRequest request,
			@ApiParam(name="giftId",value="礼物机id",required=true)@RequestParam String giftId,
			@ApiParam(name="userId",value="用户id",required=true)@RequestParam String userId){
		DataResult result=new DataResult();
		try {
			result = giftMachineService.refundDeposit(giftId, userId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Result.FAILED);
			result.setMessage("退押金失败");
			return result;
		}
	}
	
	/**
	 * 申请提现接口
	 * @param orderNo
	 * @return
	 */
	@ApiOperation(value = "申请提现", httpMethod="POST",response=Result.class)
	@RequestMapping(value="/cashWithdrawal",method=RequestMethod.POST)
	public @ResponseBody Result cashWithdrawal(HttpServletRequest request,
			@ApiParam(name="money",value="提现金额",required=true)@RequestParam String money,
			@ApiParam(name="cashType",value="提现方式",required=true)@RequestParam String cashType,
			@ApiParam(name="userId",value="用户id",required=true)@RequestParam String userId){
		DataResult result=new DataResult();
		try {
			result = giftMachineService.cashWithdrawal(money,cashType,userId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Result.FAILED);
			result.setMessage("申请提现失败");
			return result;
		}
	}
	/**
	 * 查询快递接口
	 * @param orderNo
	 * @return
	 */
	@ApiOperation(value = "查询快递接口", httpMethod="POST",response=Result.class)
	@RequestMapping(value="/queryKuaidiInfo",method=RequestMethod.POST)
	public @ResponseBody Result queryKuaidiInfo(HttpServletRequest request,
			@ApiParam(name="orderId",value="订单编号",required=true)@RequestParam String orderId){
		DataResult result=new DataResult();
		try {
			result = giftMachineService.queryKuaidiInfo(orderId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Result.FAILED);
			result.setMessage("查询快递失败");
			return result;
		}
	}
	/**
	 * 处理支付宝的回调
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/aliNotice",method=RequestMethod.POST)
	public void notice(HttpServletRequest request, HttpServletResponse response) {
		giftMachineService.notice(request,response);
	}
	/**
	 * 处理微信的支付支付结果
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="weiPayNotice",method=RequestMethod.POST)
	public void weiPayNotice(HttpServletRequest request,HttpServletResponse response) {
		giftMachineService.weiPayNotice(request,response);
	}
	
	/**
	 * 获取IP地址
	 * @param request
	 * @return
	 */
	public String getIP(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}

