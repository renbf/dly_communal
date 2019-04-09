package com.project.appinterface.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.project.appinterface.domain.CommodityInformation;
import com.project.appinterface.domain.ConsumptionInformation;
import com.project.appinterface.domain.CouponReceive;
import com.project.appinterface.domain.Gift;
import com.project.appinterface.domain.GiftApply;
import com.project.appinterface.domain.GiftGoods;
import com.project.appinterface.domain.GiftLocation;
import com.project.appinterface.domain.GiftModel;
import com.project.appinterface.domain.GiftVo;
import com.project.appinterface.domain.GoodsVo;
import com.project.appinterface.domain.Order;
import com.project.appinterface.domain.ParamArrayVo;
import com.project.appinterface.domain.Parameter;
import com.project.appinterface.domain.PayOrder;
import com.project.appinterface.domain.RefundApplication;
import com.project.appinterface.domain.TCollection;
import com.project.appinterface.domain.UserAddress;
import com.project.appinterface.domain.Wallet;
import com.project.appinterface.domain.WinningRecord;
import com.project.appinterface.mapper.CollectionMapper;
import com.project.appinterface.mapper.CommodityInformationMapper;
import com.project.appinterface.mapper.CommonInterfaceMapper;
import com.project.appinterface.mapper.ConsumptionInformationMapper;
import com.project.appinterface.mapper.CouponReceiveMapper;
import com.project.appinterface.mapper.GiftApplyMapper;
import com.project.appinterface.mapper.GiftGoodsMapper;
import com.project.appinterface.mapper.GiftLocationMapper;
import com.project.appinterface.mapper.GiftMachineMapper;
import com.project.appinterface.mapper.OrderMapper;
import com.project.appinterface.mapper.ParameterMapper;
import com.project.appinterface.mapper.PayOrderMapper;
import com.project.appinterface.mapper.RefundApplicationMapper;
import com.project.appinterface.mapper.WalletMapper;
import com.project.appinterface.mapper.WinningRecordMapper;
import com.project.appinterface.service.GiftMachineService;
import com.project.appinterface.util.AliPayUtil;
import com.project.appinterface.util.AlipayNotifyParam;
import com.project.appinterface.util.JedisUtil;
import com.project.appinterface.util.OrderNo;
import com.project.appinterface.util.WXPayExample;
import com.project.appinterface.util.kuaidi.Kuaidi100Util;
import com.project.appinterface.util.kuaidi.pojo.pojo.KuaidiResult;
import com.project.appinterface.util.wxpayutil.WXPayUtil;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.project.util.MoneyUtil;
import com.project.util.PayParameter;
import com.project.util.UUIDUtil;
import com.project.web.domain.TCashWithdrawal;
import com.project.web.domain.TExpressCompany;
import com.project.web.mapper.TCashWithdrawalMapper;
import com.project.web.mapper.TExpressCompanyMapper;

/**
 * 用户 服务层实现
 *
 * @author lws
 * @date 2019-03-04
 */
@Service
public class GiftMachineServiceImpl implements GiftMachineService {
	
	private static Logger log = LoggerFactory.getLogger(GiftMachineServiceImpl.class);
	
	@Autowired
	private GiftMachineMapper giftMachineMapper;
	@Autowired
	private CommodityInformationMapper commodityInformationMapper;
	@Autowired
	private GiftLocationMapper giftLocationMapper;
	@Autowired
	private WinningRecordMapper winningRecordMapper;
	@Autowired
	private GiftGoodsMapper giftGoodsMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private GiftApplyMapper giftApplyMapper;
	@Autowired
	private ParameterMapper parameterMapper;
	@Autowired
	private WalletMapper walletMapper;
	@Autowired
	private ConsumptionInformationMapper consumptionInformationMapper;
	@Autowired
	private RefundApplicationMapper refundApplicationMapper;
	@Autowired
	private CollectionMapper collectionMapper;
	@Autowired
	private CouponReceiveMapper couponReceiveMapper;

	@Autowired
	private CommonInterfaceMapper commonInterfaceMapper;
	@Autowired
	private PayOrderMapper payOrderMapper;
	@Autowired
	private TCashWithdrawalMapper tCashWithdrawalMapper;
	@Autowired
	private TExpressCompanyMapper tExpressCompanyMapper;
	
	@Override
	public DataResult queryGift(String state, String user_id) {
		DataResult result = new DataResult();
		Gift g = new Gift();
		g.setState(state);
		g.setUser_id(user_id);
		List<Gift> list = giftMachineMapper.selectGiftList(g);
		List<Map<String, Object>> rlist = new ArrayList<>();
		for (Gift gift : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("lng", gift.getLng());
			map.put("lat", gift.getLat());
			map.put("giftId", gift.getId());
			map.put("state", gift.getState());
			if (gift.getIsApply() == null) {
				gift.setIsApply(0);
			}
			map.put("isApply", gift.getIsApply());
			map.put("giftId", gift.getId());
			map.put("locationName", gift.getLocationName());
			if("1".equals(gift.getState())) {
				GiftVo giftVo = giftMachineMapper.selectGiftByGiftId(gift.getId());
				map.put("giftName", giftVo.getIntroduce());
				map.put("latticePrice", MoneyUtil.toYuan(giftVo.getLatticePrice()));
				map.put("surplusPosition", giftVo.getSurplusPosition());
				map.put("totalPosition", giftVo.getLatticeNum());
				map.put("goodsName", giftVo.getGoodsNames());
			}
			rlist.add(map);
		}
		result.setResult(rlist);
		result.setMessage("查询成功");
		result.setStatus(Result.SUCCESS);
		return result;
	}

	@Override
	public DataResult queryGiftId(String longitude, String latitude) {
		DataResult result = new DataResult();
		log.info("queryGiftId:longitude="+longitude+",latitude="+latitude);
		Map<String, String> rmap = new HashMap<>();
		Gift gift = giftMachineMapper.queryGiftId(longitude, latitude);
		log.info("gift"+gift);
		if(gift == null){
			result.setMessage("查询失败");
			result.setStatus(Result.FAILED);
			return result;
		}
		rmap.put("giftId", gift.getId());
		result.setResult(rmap);
		result.setMessage("查询成功");
		result.setStatus(Result.SUCCESS);
		return result;
	}

	@Override
	public DataResult queryGiftModel() {
		DataResult result = new DataResult();
		List<GiftModel> mlist = giftMachineMapper.queryGiftModel();
		List<Map<String, Object>> rlist = new ArrayList<>();
		for (GiftModel gm : mlist) {
			Map<String, Object> map = new HashMap<>();
			map.put("giftModel", gm.getLattice_num());
			map.put("giftPicture", gm.getGift_picture());
			map.put("modelId", gm.getId());
			rlist.add(map);
		}
		result.setResult(rlist);
		result.setStatus(Result.SUCCESS);
		result.setMessage("查询成功");
		return result;
	}

	@Override
	public DataResult queryById(String giftId, String userId) {
		DataResult result = new DataResult();
		Map<String, Object> rmap = new HashMap<>();
		// 查询收藏
		if (!"".equals(userId) && userId != null) {
			TCollection collection = new TCollection();
			collection.setUserId(userId);
			collection.setGiftId(giftId);
			TCollection coll = collectionMapper.selectCollectionById(collection);
			if (coll == null) {
				rmap.put("collectionId", null);
			} else {
				rmap.put("collectionId", coll.getId());
			}
		}
		// 查询礼物机信息
		GiftLocation giftLocation = giftLocationMapper.selectGiftLocationById(giftId);
		// 查询优惠券信息
		List<CommodityInformation> clist = commodityInformationMapper.selectCommodityInformationList(giftId);
		String goodsname = "";
		for (CommodityInformation c : clist) {
			goodsname += c.getName();
		}
		String[] array = giftLocation.getContent().split(",");
		rmap.put("goodsName", goodsname);
		rmap.put("giftName", giftLocation.getGiftName());
		rmap.put("position", array);
		if (userId != null && !"".equals(userId)) {
			List<CouponReceive> crlist = giftMachineMapper.queryCoupon(userId);
			rmap.put("isCoupon", crlist.size() >= 1 ? "0" : "1");
		} else {
			rmap.put("isCoupon", "1");
		}
		rmap.put("price", MoneyUtil.toYuan(giftLocation.getLatticePrice()));
		result.setResult(rmap);
		result.setMessage("查询成功");
		result.setStatus(Result.SUCCESS);
		return result;
	}

	@Override
	public DataResult queryGoodsAll(String goodsName) {
		DataResult result = new DataResult();
		List<CommodityInformation> clist = commodityInformationMapper.queryGoodsAll(null);
		List<Map<String, Object>> rlist = new ArrayList<>();
		Set<String> distinctSet = new HashSet<String>();
		for (CommodityInformation c : clist) {
			if(!distinctSet.contains(c.getGoodsTypeId())) {
				distinctSet.add(c.getGoodsTypeId());
				Map<String, Object> goodsTypeMap = new HashMap<String, Object>();
				List<Map<String, Object>> goodsList = new ArrayList<Map<String,Object>>();
				goodsTypeMap.put("goodsTypeId", c.getGoodsTypeId());
				goodsTypeMap.put("goodsTypeName", c.getGoodsTypeName());
				Map<String, Object> map = new HashMap<>();
				map.put("goodsId", c.getId());
				map.put("goodsName", c.getName());
				map.put("picture", c.getPicture());
				map.put("introduce", c.getIntroduce());
				map.put("price", MoneyUtil.toYuan(c.getPrice()));
				goodsList.add(map);
				goodsTypeMap.put("goods", goodsList);
				rlist.add(goodsTypeMap);
			}else {
				Map<String, Object> goodsTypeMap = rlist.get(rlist.size()-1);
				List<Map<String, Object>> goodsList = (List<Map<String, Object>>)goodsTypeMap.get("goods");
				Map<String, Object> map = new HashMap<>();
				map.put("goodsId", c.getId());
				map.put("goodsName", c.getName());
				map.put("picture", c.getPicture());
				map.put("introduce", c.getIntroduce());
				map.put("price", MoneyUtil.toYuan(c.getPrice()));
				goodsList.add(map);
			}
//			Map<String, Object> map = new HashMap<>();
//			map.put("goodsId", c.getId());
//			map.put("goodsName", c.getName());
//			map.put("picture", c.getPicture());
//			map.put("introduce", c.getIntroduce());
//			map.put("price", MoneyUtil.toYuan(c.getPrice()));
//			rlist.add(map);
		}
		result.setResult(rlist);
		result.setMessage("查询成功");
		result.setStatus(Result.SUCCESS);
		return result;
	}

	@Override
	public DataResult isPrize(String orderNo, String userId, String giftId) {
		DataResult result = new DataResult();
		WinningRecord winningRecord = new WinningRecord();
		winningRecord.setPayOrderNo(orderNo);
		winningRecord.setGiftId(giftId);
		winningRecord.setUserId(userId);
		List<WinningRecord> winningRecordList = winningRecordMapper.selectWinningRecordList(winningRecord);
		if(CollectionUtils.isNotEmpty(winningRecordList)){
			List<Map<String, Object>> rlist = new ArrayList<>();
			for(WinningRecord twinningRecord:winningRecordList){
				Map<String, Object> map = new HashMap<>();
				if("1".equals(twinningRecord.getState())){
					//中奖
					map.put("orderId", twinningRecord.getOrderId());
					map.put("state", "0");
					CommodityInformation ci = commodityInformationMapper
							.selectCommodityInformationById(twinningRecord.getGoodsId());
					map.put("goodsUrl", ci.getPicture());
					map.put("goodsName", ci.getName());
				}else{
					//未中奖
					map.put("state", "3");
				}
				rlist.add(map);
			}
			result.setMessage("查询成功");
			result.setStatus(Result.SUCCESS);
			result.setResult(rlist);
		}
		return result;
	}

	public static String getOrderIdByTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String newDate = sdf.format(new Date());
		String result = "";
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			result += random.nextInt(10);
		}
		return newDate + result;
	}

	@Override
	public Result updateOrder(String orderId, String userId, String addressId) {
		Result result = new Result();
		String[] orderid = orderId.split(",");
		for (String str : orderid) {
			Order order = new Order();
			order.setId(str);
			order.setAddressId(addressId);
			order.setState("0");
			orderMapper.updateOrder(order);
		}
		result.setStatus(Result.SUCCESS);
		result.setMessage("领取成功！");
		return result;
	}

	@Override
	public DataResult queryOrder(String orderId) {
		String[] arra = orderId.split(",");
		DataResult result = new DataResult();
		List<Map<String, Object>> rlist = new ArrayList<>();
		for (String str : arra) {
			Map<String, Object> map = new HashMap<>();
			Order order = orderMapper.selectOrderById(str);
			CommodityInformation ommodityInformation = commodityInformationMapper
					.selectCommodityInformationById(order.getGoodsId());
			map.put("createDate", ommodityInformation.getCreateDate());
			map.put("goodsName", ommodityInformation.getName());
			map.put("picture", ommodityInformation.getPicture());
			map.put("introduce", ommodityInformation.getIntroduce());
			map.put("price", MoneyUtil.toYuan(ommodityInformation.getPrice()));
			rlist.add(map);
		}
		result.setResult(rlist);
		result.setMessage("查询成功");
		result.setStatus(Result.SUCCESS);
		return result;
	}

	@Override
	public Result insertCollectAddress(UserAddress userAddress) {

		Result result = new Result();
		try {
			if (userAddress.getDefault_address().equals("0")) {
				Map<String, String> map = new HashMap<>();
				map.put("user_id", userAddress.getUser_id());
				map.put("default_address", "1");
				giftMachineMapper.updateCollectAddress(map);
			}
			giftMachineMapper.insertCollectAddress(userAddress);
			result.setMessage("新增成功");
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			result.setStatus(Result.FAILED);
			result.setMessage("新增失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result updateCollectAddress(Map<String, String> map) {
		Result result = new Result();
		try {
			if (map.get("default_address").equals("0")) {
				Map<String, String> rmap = new HashMap<>();
				rmap.put("user_id", map.get("user_id"));
				rmap.put("default_address", "1");
				giftMachineMapper.updateCollectAddress(rmap);
			}
			map.remove("user_id");
			giftMachineMapper.updateCollectAddress(map);
			result.setMessage("修改成功");
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			result.setStatus(Result.FAILED);
			result.setMessage("修改失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result deleteCollectAddress(Map<String, String> map) {
		Result result = new Result();
		try {
			giftMachineMapper.deleteCollectAddress(map);
			result.setMessage("删除成功");
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			result.setStatus(Result.FAILED);
			result.setMessage("删除失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result updateDefault(Map<String, String> map, String userid) {
		Result result = new Result();
		try {
			Map<String, String> maps = new HashMap<String, String>();
			maps.put("user_id", userid);
			maps.put("default_address", "1");
			giftMachineMapper.updateCollectAddress(maps);
			giftMachineMapper.updateCollectAddress(map);
			result.setMessage("设置成功");
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			result.setStatus(Result.FAILED);
			result.setMessage("设置失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DataResult queryDefault(String userid) {
		DataResult result = new DataResult();
		try {
			Map<String, String> addressmap = new HashMap<String, String>();
			Map<String, String> returnmap = new HashMap<String, String>();
			addressmap.put("user_id", userid);
			addressmap.put("default_address", "0");
			UserAddress us = giftMachineMapper.queryDefault(addressmap);
			if (us != null) {
				returnmap.put("name", us.getReceiver());
				returnmap.put("phone", us.getTelephone());
				returnmap.put("address", us.getAddress());
				returnmap.put("addressdetailed", us.getAddress_detailed());
				returnmap.put("addressid", us.getId());
				returnmap.put("defaultaddress", us.getDefault_address());
			}
			result.setResult(returnmap);
			result.setMessage("查询成功");
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			result.setStatus(Result.FAILED);
			result.setMessage("查询失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DataResult getCollectgoodsAddress(String userid) {
		DataResult result = new DataResult();
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("user_id", userid);
			List<UserAddress> useraddresslist = giftMachineMapper.getCollectgoodsAddress(map);
			List<Map<String, String>> returnmap = new ArrayList<Map<String, String>>();
			for (UserAddress us : useraddresslist) {
				Map<String, String> umap = new HashMap<String, String>();
				umap.put("name", us.getReceiver());
				umap.put("phone", us.getTelephone());
				umap.put("address", us.getAddress());
				umap.put("addressdetailed", us.getAddress_detailed());
				umap.put("addressid", us.getId());
				umap.put("defaultaddress", us.getDefault_address());
				returnmap.add(umap);
			}
			result.setResult(returnmap);
			result.setMessage("查询成功");
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			result.setStatus(Result.FAILED);
			result.setMessage("查询失败");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	@Transactional
	public DataResult payGift(ParamArrayVo paramArrayVo, String ip) {
		log.info("申请礼物机接口=============");
		DataResult result = new DataResult();
		if ("0".equals(paramArrayVo.getType())) {
			Integer countGift = giftApplyMapper.checkIsApplyByGiftId(paramArrayVo.getGiftId());
			if(countGift != null && countGift.intValue() > 0) {
				result.setMessage("您申请的该位置的礼物机，已被申请");
				result.setStatus(Result.FAILED);
				return result;
			}
			Integer count = giftApplyMapper.checkIsApply(paramArrayVo.getGiftId(), paramArrayVo.getUserId());
			if (count != null && count.intValue() > 0) {
				result.setMessage("您已经申请过该位置的礼物机，请勿重复申请！");
				result.setStatus(Result.FAILED);
				return result;
			}
		}
		// 支付方式0支付宝1微信
		String orderno = OrderNo.getOrderNo() + "";
		Map<String, Object> returnMap = new HashMap<String, Object>();
		ConsumptionInformation gi = new ConsumptionInformation();
		gi.setId(UUIDUtil.getUUID());
		gi.setMoney(MoneyUtil.toFen(paramArrayVo.getPayMoney()));
		gi.setConsumptionUser(paramArrayVo.getUserId());
		gi.setConsumptionDate(new Date());
		gi.setState("0");
		gi.setExtend(orderno);
		returnMap.put("indexs", paramArrayVo.getIndexs());
		if (paramArrayVo.getPayType().equals("0")) {
			gi.setPayType("0");
			try {
				AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
				model.setBody("大来也");
				if (paramArrayVo.getType().equals("0")) {
					model.setSubject("礼物机申请");
				} else {
					model.setSubject("抽奖支付");
				}
				model.setOutTradeNo(orderno.trim());
				model.setTimeoutExpress("30m");
				model.setTotalAmount(paramArrayVo.getPayMoney().trim());
				model.setProductCode("QUICK_MSECURITY_PAY");
				String paramArrayVoJson = JSON.toJSONString(paramArrayVo);
				String notifyUrl = "http://60.205.187.254:80/gift/giftMachine/aliNotice";
				String returnstr = AliPayUtil.aliPay(model, notifyUrl).trim();

				if (returnstr != null) {
					// 生成支付信息
					PayOrder po = new PayOrder();
					po.setId(UUIDUtil.getUUID());
					po.setUserid(paramArrayVo.getUserId());
					po.setOrderno(orderno);
					po.setState("0");
					po.setMoney(MoneyUtil.toFen(paramArrayVo.getPayMoney()));
					po.setCreateDate(new Date());
					po.setType("1");
					po.setOrderid(paramArrayVo.getGiftId());
					po.setContent(returnstr);
					po.setPayParams(paramArrayVoJson);
					// 申请
					if (paramArrayVo.getType().equals("0")) {
						po.setPayType("0");
						po.setOrderno(orderno);
						gi.setConsumptionType("0");
//						gi.setState("购买礼物机");
					} else {
						po.setPayType("1");
						gi.setConsumptionType("1");
//						gi.setState("抽奖");
					}
					giftMachineMapper.addPayOrder(po);
					returnMap.put("returnStr", returnstr);
					returnMap.put("orderno", orderno);
					result.setMessage("支付成功");
					result.setResult(returnMap);
					result.setStatus(Result.SUCCESS);
				} else {
					result.setMessage("支付失败");
					result.setStatus(Result.FAILED);
				}

			} catch (Exception e) {
				result.setMessage("支付失败");
				result.setStatus(Result.FAILED);
				e.printStackTrace();
			}
		} else if (paramArrayVo.getPayType().equals("1")) {
			gi.setPayType("1");
			try {
				Map<String, String> data = new HashMap<String, String>();
				data.put("body", "大来也");
				data.put("out_trade_no", orderno);
				data.put("total_fee", MoneyUtil.toFen(paramArrayVo.getPayMoney()).toString());
				String notifyUrl = "http://http://60.205.187.254:80/gift/giftMachine/weiPayNotice";
				data.put("notify_url", notifyUrl);
				data.put("trade_type", "APP"); // 此处指定为扫码支付
				data.put("spbill_create_ip", ip);
				data.put("nonce_str", getRandomString(32));
				Map<String, String> map = WXPayExample.unifiedOrder(data);
				Map<String, String> map1 = new HashMap<>();
				if ("SUCCESS".equals(map.get("return_code"))) {
					String timestamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);
					String prepay_id = (String) map.get("prepay_id"); // 付款的订单号
					map1.put("appid", PayParameter.WEI_APPID + "");// appID
					map1.put("partnerid", PayParameter.WEI_MCHID + "");//
					map1.put("prepayid", prepay_id + "");
					map1.put("package", "Sign=WXPay");
					map1.put("noncestr", WXPayUtil.generateNonceStr() + "");
					map1.put("timestamp", timestamp);
					String sign = WXPayUtil.generateSignature(map1, PayParameter.WEI_KEY);
					map1.put("sign", sign);
					map1.put("errorCode", "1");
					returnMap.put("returnStr", map1);
					returnMap.put("orderno", orderno);
					PayOrder po = new PayOrder();
					po.setMoney(MoneyUtil.toFen(paramArrayVo.getPayMoney()));
					po.setOrderid(paramArrayVo.getGiftId());
					po.setId(UUIDUtil.getUUID());
					po.setUserid(paramArrayVo.getUserId());
					po.setOrderno(orderno);
					po.setState("0");
					po.setCreateDate(new Date());
					po.setType("2");
					po.setContent(sign);
					if (paramArrayVo.getType().equals("0")) {
						po.setPayType("0");
						GiftApply ga = new GiftApply();
						ga.setId(UUIDUtil.getUUID());
						ga.setGiftId(paramArrayVo.getGiftId());
						ga.setUserId(paramArrayVo.getUserId());
						ga.setTimeType(paramArrayVo.getTimeType());
						ga.setNumber(paramArrayVo.getNumber());
						ga.setCreateDate(new Date());
						ga.setState("0");
						ga.setOrderno(orderno);
						ga.setLatticePrice(MoneyUtil.toFen(paramArrayVo.getPayMoney()));
						ga.setIntroduce(paramArrayVo.getGiftName());
						giftApplyMapper.insertGiftApply(ga);
						gi.setConsumptionType("0");
					} else {
						po.setPayType("1");
						gi.setConsumptionType("1");
//						gi.setState("抽奖");
					}
					po.setOrderno(orderno);
					giftMachineMapper.addPayOrder(po);
					result.setMessage("正在调起微信支付");
					result.setResult(returnMap);

					result.setStatus(Result.SUCCESS);
				} else {
					System.out.println(map);
					System.out.println("获取微信支付订单号失败");
					result.setMessage("获取微信支付订单号失败");
					result.setStatus(Result.FAILED);
				}
			} catch (Exception e) {
				result.setMessage("支付失败");
				result.setStatus(Result.FAILED);
				e.printStackTrace();
			}
		}
		consumptionInformationMapper.insertConsumptionInformation(gi);
		return result;
	}

	@Override
	@Transactional
	public DataResult tryPayGift(ParamArrayVo paramArrayVo, String ip) {
		log.info("开盒子接口开始=============");
		String requestId = UUIDUtil.getUUID();
		DataResult result = new DataResult();
		String giftId = paramArrayVo.getGiftId();
		String indexs = paramArrayVo.getIndexs();
		String[] indexArr = null;
		if (StringUtils.isNotEmpty(indexs)) {
			indexArr = indexs.split(",");
		} else {
			result.setMessage("没选盒子");
			result.setStatus(Result.FAILED);
			return result;
		}
		try {
			for (String index : indexArr) {
				boolean getLock = JedisUtil.tryGetDistributedLock("tryPayGift_"+giftId + "_" + index, requestId, 60000);
				if (getLock == false) {
					result.setMessage("第" + (Integer.valueOf(index).intValue() + 1) + "个盒子已被占用了");
					result.setStatus(Result.FAILED);
					return result;
				}
			}
			// 支付方式0支付宝1微信
			String orderno = OrderNo.getOrderNo() + "";
			Map<String, Object> returnMap = new HashMap<String, Object>();
			ConsumptionInformation gi = new ConsumptionInformation();
			gi.setId(UUIDUtil.getUUID());
			gi.setMoney(MoneyUtil.toFen(paramArrayVo.getPayMoney()));
			gi.setConsumptionUser(paramArrayVo.getUserId());
			gi.setConsumptionDate(new Date());
			gi.setState("0");
			gi.setExtend(orderno);
			returnMap.put("indexs", paramArrayVo.getIndexs());
			if (paramArrayVo.getPayType().equals("0")) {
				gi.setPayType("0");
				AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
				model.setBody("大来也");
				if (paramArrayVo.getType().equals("0")) {
					model.setSubject("礼物机申请");
				} else {
					model.setSubject("抽奖支付");
				}
				model.setOutTradeNo(orderno.trim());
				model.setTimeoutExpress("30m");
				model.setTotalAmount(paramArrayVo.getPayMoney().trim());
				model.setProductCode("QUICK_MSECURITY_PAY");
				String paramArrayVoJson = JSON.toJSONString(paramArrayVo);
				String notifyUrl = " http://http://60.205.187.254:80/gift/giftMachine/aliNotice";
				String returnstr = AliPayUtil.aliPay(model, notifyUrl).trim();
				if (returnstr != null) {
					// 生成支付信息
					PayOrder po = new PayOrder();
					po.setId(UUIDUtil.getUUID());
					po.setUserid(paramArrayVo.getUserId());
					po.setOrderno(orderno);
					po.setState("0");
					po.setMoney(MoneyUtil.toFen(paramArrayVo.getPayMoney()));
					po.setCreateDate(new Date());
					po.setType("1");
					po.setOrderid(paramArrayVo.getGiftId() + "_" + indexs);
					po.setContent(returnstr);
					po.setPayParams(paramArrayVoJson);
					po.setPayType("1");
					gi.setConsumptionType("1");
//					gi.setState("抽奖");

					giftMachineMapper.addPayOrder(po);
					consumptionInformationMapper.insertConsumptionInformation(gi);
					returnMap.put("returnStr", returnstr);
					returnMap.put("orderno", orderno);
					result.setMessage("支付成功");
					result.setResult(returnMap);
					result.setStatus(Result.SUCCESS);
				} else {
					result.setMessage("支付失败");
					result.setStatus(Result.FAILED);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			for (String index : indexArr) {
				JedisUtil.releaseDistributedLock(giftId + "_" + index, requestId);
			}
		}
		return result;
	}

	public static String getRandomString(int length) {
		// 1. 定义一个字符串（A-Z，a-z，0-9）即62个数字字母；
		String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
		// 2. 由Random生成随机数
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		// 3. 长度为几就循环几次
		for (int i = 0; i < length; ++i) {
			// 从62个的数字或字母中选择
			int number = random.nextInt(62);
			// 将产生的数字通过length次承载到sb中
			sb.append(str.charAt(number));
		}
		// 将承载的字符转换成字符串
		return sb.toString();
	}

	@Override
	@Transactional
	public void notice(HttpServletRequest request, HttpServletResponse response) {
		try {
			log.info("支付宝回调开始=============");
			Map<String, Object> map = AliPayUtil.aliNotice(request, response);
			boolean flag = (Boolean) map.get("flag");
			if (flag) {
				Map<String, String> params = (Map<String, String>) map.get("data");
				String out_trade_no = params.get("out_trade_no");
				String app_id = params.get("app_id");
				if (out_trade_no == null) {
					AliPayUtil.responseService(response, "failure");
					return;
				}
				if (app_id == null || !PayParameter.ALI_APP_ID.equals(app_id)) {
					AliPayUtil.responseService(response, "failure");
					return;
				}
				// 获取订单信息
				Map<String, String> ordermap = new HashMap<String, String>();
				ordermap.put("orderno", out_trade_no);
				PayOrder orders = giftMachineMapper.queryPayOrder(ordermap);
				if (orders == null) {
					AliPayUtil.responseService(response, "failure");
					return;
				}
//				AliPayUtil.responseService(response, "success");
//				Map<String, String> rmap = new HashMap<String, String>();
//				rmap.put("id", orders.getId());
//				rmap.put("state", "1");
//				if (orders.getPayType().equals("0")) {
//					GiftApply ga = new GiftApply();
//					ga.setState("0");
//					ga.setOrderno(orders.getOrderno());
//					giftApplyMapper.updateGiftApply(ga);
//				} else {
//					// 给商家添加收入
//					GiftLocation giftLocation = giftLocationMapper.selectGiftLocationById(orders.getOrderid());
//					Wallet wallet = walletMapper.selectWalletByUserId(giftLocation.getCreateUser());
//					Wallet walletu = new Wallet();
//					walletu.setId(wallet.getId());
//					walletu.setProfit(wallet.getProfit() + giftLocation.getLatticePrice());
//					walletMapper.updateWallet(walletu);
//				}
//				giftMachineMapper.updatePayOrder(rmap);
				
				//回调
				
				String orderNo = out_trade_no;
				DataResult res = aliPayNotice(orderNo);
				if(Result.SUCCESS.equals(res.getStatus())) {
					AliPayUtil.responseService(response, "success");
					return ;
				}else {
					AliPayUtil.responseService(response, "failure");
					return;
				}
			} else {
				AliPayUtil.responseService(response, "failure");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public synchronized void weiPayNotice(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			StringBuffer str = new StringBuffer();
			InputStreamReader in = new InputStreamReader(request.getInputStream(), "utf-8");
			BufferedReader br = new BufferedReader(in);
			String read = null;
			while ((read = br.readLine()) != null) {
				str.append(read);
			}
			// WeiPayUtil.readStringXmlOut
			Map<String, String> para = WXPayExample.queryOrder(str.toString());
			System.out.println(para);
			String result = "";
			String out_trade_no = (String) para.get("out_trade_no");
			System.out.println("out_trade_no:" + out_trade_no);
			if (!"SUCCESS".equals(para.get("return_code"))) {
				System.err.println("微信返回的交易状态不正确（result_code=" + "SUCCESS" + "）");
				result = WXPayExample.setXml("fail", "微信返回的交易状态不正确（result_code=" + "SUCCESS" + "）");
				AliPayUtil.responseService(response, result);
				return;
			} else {
				Map<String, String> ordermap = new HashMap<String, String>();
				ordermap.put("orderno", out_trade_no);
				PayOrder orders = giftMachineMapper.queryPayOrder(ordermap);
				if (orders == null) {
					result = WXPayExample.setXml("fail", "订单不存在！");
					AliPayUtil.responseService(response, result);
					return;
				} else {
					Map<String, String> rmap = new HashMap<String, String>();
					rmap.put("id", orders.getId());
					rmap.put("state", "1");
					giftMachineMapper.updatePayOrder(rmap);
					result = WXPayExample.setXml("SUCCESS", "处理成功！");
					AliPayUtil.responseService(response, result);
					if (orders.getPayType().equals("0")) {
						GiftApply ga = new GiftApply();
						ga.setState("0");
						ga.setOrderno(orders.getOrderno());
						giftApplyMapper.updateGiftApply(ga);
					} else {
						// 给商家添加收入
						GiftLocation giftLocation = giftLocationMapper.selectGiftLocationById(orders.getOrderid());
						Wallet wallet = walletMapper.selectWalletByUserId(giftLocation.getCreateUser());
						Wallet walletu = new Wallet();
						walletu.setId(wallet.getId());
						walletu.setProfit(wallet.getProfit() + giftLocation.getLatticePrice());
						walletMapper.updateWallet(walletu);
					}
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Result refundApplication(String userId, String money) {
		Result result = new Result();
		RefundApplication refundApplication = new RefundApplication();
		refundApplication.setId(UUIDUtil.getUUID());
		refundApplication.setUserId(userId);
		refundApplication.setCreateDate(new Date());
		refundApplication.setMoney(MoneyUtil.toFen(money));
		refundApplication.setState("0");
		refundApplicationMapper.insertRefundApplication(refundApplication);
		result.setMessage("查询成功");
		result.setStatus(Result.SUCCESS);
		return result;
	}

	@Override
	public DataResult returnDepositMoney(String userId, String giftId) {
		DataResult result = new DataResult();
		// 计算还可以退多少押金
		CommodityInformation CommodityInformation = commodityInformationMapper.querySumMoney(giftId);
		// 把可以退的押金返还到我的钱包中
		Wallet wallet = walletMapper.selectWalletByUserId(userId);
		Wallet uwallet = new Wallet();
		uwallet.setId(wallet.getId());
		uwallet.setBalance(wallet.getBalance() + CommodityInformation.getGoodsSumMoney());
		walletMapper.updateWallet(uwallet);
		// 生成退还押金记录
		ConsumptionInformation ci = new ConsumptionInformation();
		ci.setId(UUIDUtil.getUUID());
		ci.setConsumptionType("2");
		ci.setMoney(CommodityInformation.getGoodsSumMoney());
		ci.setConsumptionUser(userId);
		ci.setConsumptionDate(new Date());
		ci.setState("3");
		ci.setPayType("2");
		consumptionInformationMapper.insertConsumptionInformation(ci);
		// 清空礼物机位置 修改礼物机
		Gift gift = new Gift();
		gift.setId(giftId);
		gift.setState("0");
		giftMachineMapper.updateTGift(gift);
		giftGoodsMapper.deleteGiftGoodsById(giftId);
		giftLocationMapper.deleteGiftLocationById(giftId);
		giftApplyMapper.deleteGiftApplyById(giftId);
		result.setStatus(Result.SUCCESS);
		result.setMessage("查询成功");
		return result;
	}

	@Override
	public DataResult queryRentType() {
		DataResult result = new DataResult();
		Parameter parameter = new Parameter();
		parameter.setState("0");
		List<Parameter> plist = parameterMapper.selectParameterList(parameter);
		Map<String, Object> map = new HashMap<>();
		for (Parameter p : plist) {
			if (p.getType().equals("2")) {
				map.put("zjl", p.getParameterContent());
			} else if (p.getType().equals("1")) {
				map.put("syl", p.getParameterContent());
			} else if (p.getType().equals("0")) {
				map.put("month", MoneyUtil.toYuan(p.getParameterContent()));
			} else if (p.getType().equals("3")) {
				map.put("day", MoneyUtil.toYuan(p.getParameterContent()));
			}

		}
		// String jsonStr=JSONObject.toJSONString(plist);
		result.setStatus(Result.SUCCESS);
		result.setMessage("查询成功");

		result.setResult(map);
		return result;
	}

	@Override
	public DataResult addCollection(String giftId, String userId) {
		DataResult result = new DataResult();
		// 查询收藏
		Map<String, String> rmap = new HashMap<>();
		if (!"".equals(userId) && userId != null) {
			TCollection collection = new TCollection();
			collection.setUserId(userId);
			collection.setGiftId(giftId);
			TCollection coll = collectionMapper.selectCollectionById(collection);
			if (coll == null) {
				TCollection tcoll = new TCollection();
				tcoll.setId(UUIDUtil.getUUID());
				tcoll.setCreateDate(new Date());
				tcoll.setGiftId(giftId);
				tcoll.setUserId(userId);
				collectionMapper.insertCollection(tcoll);
				rmap.put("collectionId", tcoll.getId());
			}
		}
		result.setResult(rmap);
		result.setStatus(Result.SUCCESS);
		result.setMessage("查询成功");
		return result;
	}

	@Override
	public Result deleteCollection(String collectionId) {
		Result result = new Result();
		collectionMapper.deleteCollectionById(collectionId);
		result.setStatus(Result.SUCCESS);
		result.setMessage("删除成功");
		return result;
	}

	@Override
	public DataResult queryCollection(String userId) {
		DataResult result = new DataResult();
		TCollection collection = new TCollection();
		collection.setUserId(userId);
		List<TCollection> clist = collectionMapper.selectCollectionList(collection);
		List<Map<String, Object>> rlist = new ArrayList<>();
		for (TCollection c : clist) {
			Map<String, Object> map = new HashMap<>();
			map.put("collectionId", c.getId());
			Gift gift = giftMachineMapper.selectGiftByIdw(c.getGiftId());
			List<CommodityInformation> cilist = commodityInformationMapper.selectCommodityInformationList(gift.getId());
			String goodsname = "";
			for (CommodityInformation co : cilist) {
				goodsname += co.getName();
			}
			map.put("giftId", gift.getId());
			map.put("giftName", gift.getModelName());
			map.put("goodsName", goodsname);
			map.put("pictureUrl", gift.getPictureUrl());
			rlist.add(map);
		}
		result.setResult(rlist);
		result.setStatus(Result.SUCCESS);
		result.setMessage("查询成功");
		return result;
	}

	@Override
	public Result queryPayState(String orderNo) {
		Result result = new Result();
		Map<String, String> ordermap = new HashMap<String, String>();
		ordermap.put("orderno", orderNo);
		PayOrder orders = giftMachineMapper.queryPayOrder(ordermap);
		if (orders.getState().equals("0")) {
			result.setMessage("查询成功");
			result.setStatus("2");
		} else if (orders.getState().equals("2")) {
			result.setMessage("查询成功");
			result.setStatus("1");
		} else {
			result.setMessage("查询成功");
			result.setStatus("0");
		}
		return result;
	}

	@Override
	public DataResult queryRecordsConsumption(String userId) {
		DataResult result = new DataResult();
		ConsumptionInformation ci = new ConsumptionInformation();
		ci.setConsumptionUser(userId);
		List<ConsumptionInformation> clist = consumptionInformationMapper.selectConsumptionInformationList(ci);
		List<Map<String, Object>> rlist = new ArrayList<>();
		for (ConsumptionInformation c : clist) {
			if("1".equals(c.getState()) || "3".equals(c.getState())) {
				Map<String, Object> map = new HashMap<>();
				map.put("consumptionType", c.getConsumptionType());
				map.put("money", MoneyUtil.toYuan(c.getMoney()));
				map.put("state", c.getState());
				rlist.add(map);
			}
		}
		result.setResult(rlist);
		result.setMessage("查询成功");
		result.setStatus(Result.SUCCESS);
		return result;
	}

	@Override
	public DataResult queryNoPrize(String userId) {
		DataResult result = new DataResult();
		WinningRecord wr = new WinningRecord();
		wr.setUserId(userId);
		wr.setState("0");
		List<WinningRecord> wlist = winningRecordMapper.queryNoPrize(wr);
		List<Map<String, Object>> rlist = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (WinningRecord w : wlist) {
			Map<String, Object> map = new HashMap<>();
			map.put("giftName", w.getModelName());
			map.put("drawTime", sdf.format(w.getCreateDate()));
			rlist.add(map);
		}
		result.setResult(rlist);
		result.setStatus(Result.SUCCESS);
		result.setMessage("查询成功");
		return result;
	}

	@Override
	public DataResult queryOrderByUserId(String orderId) {
		DataResult result = new DataResult();
		Order or = new Order();
		or.setId(orderId);
		List<Order> olist = orderMapper.selectOrderByUserId(or);
		List<Map<String, Object>> rlist = new ArrayList<>();
		for (Order o : olist) {
			Map<String, Object> map = new HashMap<>();
			map.put("receiver", o.getReceiver());
			map.put("address", o.getAddress());
			map.put("telephone", o.getTelephone());
			map.put("goodsName", o.getGoodsName());
			map.put("picture", o.getPicture());
			map.put("orderNo", o.getOrderNo());
			map.put("createDate", o.getCreatDate());
			map.put("deliveryDate", o.getDeliveryDate());
			map.put("completionDate", o.getCompletionDate());
			map.put("price", o.getPrice());
			map.put("state", o.getState());
			rlist.add(map);
		}
		result.setResult(rlist);
		result.setStatus(Result.SUCCESS);
		result.setMessage("查询成功");
		return result;
	}

	@Override
	public Result getRconfirmCollect(String userId, String orderNo) {
		Result result = new Result();
		Order order = new Order();
		order.setOrderNo(orderNo);
		order.setUserId(userId);
		order.setState("2");
		order.setCompletionDate(new Date());
		orderMapper.updateOrder(order);
		result.setStatus(Result.SUCCESS);
		result.setMessage("确认收货成功");
		return result;
	}

	@Override
	public DataResult queryConsumptionRecords(String userId) {
		DataResult result = new DataResult();
		ConsumptionInformation cr = new ConsumptionInformation();
		cr.setConsumptionUser(userId);
		List<ConsumptionInformation> clist = consumptionInformationMapper.selectConsumptionInformationList(cr);
		List<Map<String, String>> rlist = new ArrayList<>();
		for (ConsumptionInformation c : clist) {
			Map<String, String> map = new HashMap<>();
			map.put("consumptionType", c.getConsumptionType());
			map.put("money", MoneyUtil.toYuan(c.getMoney()));
			map.put("state", c.getState());
			rlist.add(map);
		}
		result.setResult(rlist);
		result.setMessage("查询成功");
		result.setStatus(Result.SUCCESS);
		return result;
	}

	@Override
	public DataResult queryPrizeOrder(String userId, String state) {
		DataResult result = new DataResult();
		Order order = new Order();
		order.setUserId(userId);
		order.setState(state);
		List<Order> olist = orderMapper.selectOrderGoodsList(order);
		List<Map<String, Object>> rlist = new ArrayList<>();
		for (Order o : olist) {
			Map<String, Object> map = new HashMap<>();
			map.put("goodsName", o.getGoodsName());
			map.put("picture", o.getPicture());
			map.put("state", o.getState());
			map.put("orderId", o.getId());
			map.put("orderNo", o.getOrderNo());
			rlist.add(map);
		}
		result.setResult(rlist);
		result.setStatus(Result.SUCCESS);
		result.setMessage("查询成功");
		return result;
	}

	@Override
	@Transactional
	public DataResult queryPayInfoByOrderNo(ParamArrayVo paramArrayVo, String ip) {
		DataResult result = new DataResult();
		try {
			log.info("支付宝查询接口开始=============");
			String orderNo = paramArrayVo.getOrderNo();
			if (StringUtils.isEmpty(orderNo)) {
				result.setStatus(Result.FAILED);
				result.setMessage("订单号不能为空");
				return result;
			}
			result = AliPayUtil.queryPayInfoByOrderNo(orderNo);
			if (Result.SUCCESS.equals(result.getStatus())) {
				DataResult res = aliPayNotice(orderNo);
				if(Result.SUCCESS.equals(res.getStatus())) {
					res.setMessage(result.getMessage());
				}
				result = res;
			}
			return result;
		} catch (Exception e) {
			log.error("查询支付宝接口失败",e);
			throw new RuntimeException();
		}
	}
	/**
	 * 支付成功回调
	 * @param orderNo
	 * @return
	 */
	public DataResult aliPayNotice(String orderNo) {
		String requestId = UUIDUtil.getUUID();
		DataResult result = new DataResult();
		try {
			boolean getLock = JedisUtil.tryGetDistributedLock("notice_" + orderNo, requestId, 60000);
			if (getLock == false) {
				result.setMessage("系统正忙");
				result.setStatus(Result.FAILED);
				return result;
			}
			PayOrder payOrderIsCheck = payOrderMapper.selectPayOrderByOrderNo(orderNo);
			//支付已成功
			if ("1".equals(payOrderIsCheck.getState())) {
				result.setStatus(Result.SUCCESS);
				return result;
			}
			ParamArrayVo paramArrayVo = JSON.parseObject(payOrderIsCheck.getPayParams(), ParamArrayVo.class);
			if (paramArrayVo.getCouponId() != null && !"".equals(paramArrayVo.getCouponId())) {
				CouponReceive couponReceive = new CouponReceive();
				couponReceive.setCouponId(paramArrayVo.getCouponId());
				couponReceive.setState("1");
				couponReceiveMapper.updateCouponReceive(couponReceive);
			}
			String giftId = paramArrayVo.getGiftId();
			String userId = paramArrayVo.getUserId();
			// 申请
			if (paramArrayVo.getType().equals("0")) {
				GiftApply ga = new GiftApply();
				String giftApplyId = UUIDUtil.getUUID();
				ga.setId(giftApplyId);
				ga.setGiftId(giftId);
				ga.setUserId(userId);
				ga.setTimeType(paramArrayVo.getTimeType());
				ga.setNumber(paramArrayVo.getNumber());
				ga.setCreateDate(new Date());
				ga.setState("0");
				ga.setOrderno(orderNo);
				ga.setGiftModelId(paramArrayVo.getGiftModel());
				ga.setLatticePrice(MoneyUtil.toFen(paramArrayVo.getUnivalent()));
				ga.setIntroduce(paramArrayVo.getGiftName());
				giftApplyMapper.insertGiftApply(ga);
				List<GoodsVo> gvlist = paramArrayVo.getGoodsList();
				//计算押金
				BigDecimal deposit = BigDecimal.ZERO;
				for (GoodsVo g : gvlist) {
					int goodsNumber = g.getGoodsNumber();
					for (int i = 0; i < goodsNumber; i++) {
						GiftGoods gg = new GiftGoods();
						gg.setId(UUIDUtil.getUUID());
						gg.setGoodsId(g.getGoodsId());
						gg.setGiftId(giftId);
						gg.setState("0");
						gg.setGiftApplyId(giftApplyId);
						gg.setPrice(MoneyUtil.toFen(g.getPrice()));
						giftGoodsMapper.insertGiftGoods(gg);
					}
					BigDecimal price = new BigDecimal(g.getPrice());
					deposit = deposit.add(price.multiply(new BigDecimal(goodsNumber)));
				}
				deposit = deposit.multiply(new BigDecimal(100));
				//增加押金
				Wallet wallet = walletMapper.selectWalletByUserId(userId);
				Long depositOld = wallet.getDeposit();
				Wallet walletu = new Wallet();
				walletu.setId(wallet.getId());
				walletu.setDeposit(depositOld+deposit.longValue());
				walletMapper.updateWallet(wallet);
				//消费记录
				ConsumptionInformation consumptionInformation = consumptionInformationMapper.selectConsumptionInformationByOrderNo(orderNo);
				consumptionInformation.setState("1");
				consumptionInformationMapper.updateConsumptionInformation(consumptionInformation);
			} else {
				//开盒子
				String indexs = paramArrayVo.getIndexs();
				// 1.查询礼物机礼物格子信息
				GiftLocation giftLocation = giftLocationMapper.selectGiftLocationById(giftId);
				//机主的 giftUserId
				String giftUserId = giftLocation.getCreateUser();
				Integer surplusPosition = giftLocation.getSurplusPosition();
				String[] contentArr = giftLocation.getContent().split(",");
				String[] indexArr = indexs.split(",");
				Long shouyi = 0l;
				for (String index : indexArr) {
					if (surplusPosition >= 1) {
						// 1 未中奖2已中奖
						String prize = "1";
						if (contentArr[Integer.valueOf(index)].equals("2")) {
							prize = "2";
						}
						// 3.记录抽奖信息
						WinningRecord winningRecord = new WinningRecord();
						winningRecord.setId(UUIDUtil.getUUID());
						winningRecord.setCreateDate(new Date());
						winningRecord.setUserId(userId);
						winningRecord.setGiftId(giftId);
						winningRecord.setIndex(Integer.valueOf(index));
						winningRecord.setPayOrderNo(orderNo);
						if (prize.equals("2")) {
							// 查询中奖商品
							GiftGoods giftGoods = new GiftGoods();
							giftGoods.setGiftId(giftId);
							giftGoods.setState("0");
							List<GiftGoods> glist = giftGoodsMapper.selectGiftGoodsList(giftGoods);
							int n = (int) (Math.random() * glist.size());
							GiftGoods gg = glist.get(n);
							winningRecord.setState("1");
							winningRecord.setGoodsId(gg.getGoodsId());
							String orderId = UUIDUtil.getUUID();
							winningRecord.setOrderId(orderId);
							// 修改中奖物品中间表\
							GiftGoods ugiftGoods = new GiftGoods();
							ugiftGoods.setId(gg.getId());
							ugiftGoods.setState("1");
							giftGoodsMapper.updateGiftGoods(ugiftGoods);
							Order order = new Order();
							order.setId(orderId);
							order.setState("4");
							order.setOrderNo(getOrderIdByTime());
							order.setCreatDate(new Date());
							order.setUserId(userId);
							order.setGoodsId(gg.getGoodsId());
							order.setCreateUser(userId);
							orderMapper.insertOrder(order);
						} else {
							winningRecord.setState("0");
						}
						winningRecordMapper.insertWinningRecord(winningRecord);
						// 4.修改礼物机中奖格子

						contentArr[Integer.valueOf(index)] = "3";
						String context = "";
						for (String a : contentArr) {
							context += a + ",";
						}
						context = context.substring(0, context.length() - 1);
						GiftLocation giftLocationTemp = giftLocationMapper.selectGiftLocationById(giftId);
						
						GiftLocation ugiftLocation = new GiftLocation();
						ugiftLocation.setContent(context);
						ugiftLocation.setId(giftLocationTemp.getId());
						ugiftLocation.setSurplusPosition(giftLocationTemp.getSurplusPosition() - 1);
						giftLocationMapper.updateGiftLocation(ugiftLocation);
						//增加收益和余额
						Wallet wallet = walletMapper.selectWalletByUserId(giftUserId);
						Long profitOld = wallet.getProfit();
						Long balanceOld = wallet.getBalance();
						Wallet walletu = new Wallet();
						walletu.setId(wallet.getId());
						walletu.setProfit(profitOld + giftLocation.getLatticePrice());
						walletu.setBalance(balanceOld +giftLocation.getLatticePrice());
						walletMapper.updateWallet(walletu);
						shouyi += giftLocation.getLatticePrice();
					}
				}
				//消费记录
				ConsumptionInformation consumptionInformation = consumptionInformationMapper.selectConsumptionInformationByOrderNo(orderNo);
				consumptionInformation.setState("1");
				consumptionInformationMapper.updateConsumptionInformation(consumptionInformation);
				ConsumptionInformation gi = consumptionInformationMapper.selectConsumptionInformationByOrderNo(orderNo);
				gi.setId(UUIDUtil.getUUID());
				gi.setMoney(shouyi);
				gi.setConsumptionUser(giftUserId);
				gi.setConsumptionDate(new Date());
				gi.setState("1");
				gi.setConsumptionType("4");
				consumptionInformationMapper.insertConsumptionInformation(gi);
			}
			PayOrder payOrder = new PayOrder();
			payOrder.setState("1");
			payOrder.setOrderno(orderNo);
			int l = payOrderMapper.updatePayOrderByOrderNo(payOrder);
			
			if (l != 1) {
				result.setStatus(Result.FAILED);
				result.setMessage("更新订单状态失败");
			} 
			result.setMessage("支付回调成功");
			result.setStatus(Result.SUCCESS);
			return result;
		} catch (Exception e) {
			log.error("查询支付宝接口异常",e);
			throw new RuntimeException();
		}finally{
			JedisUtil.releaseDistributedLock("notice_"+orderNo, requestId);
		}
	}
	
	@Override
	@Transactional
	public DataResult refundDeposit(String giftId, String userId) {
		DataResult result = new DataResult();
		try {
			GiftApply giftApply=giftApplyMapper.selectGiftApplyByGiftId(giftId,userId);
			if(giftApply != null) {
				GiftGoods giftGoods = new GiftGoods();
				giftGoods.setGiftId(giftId);
				giftGoods.setGiftApplyId(giftApply.getId());
				List<GiftGoods> giftGoodsList = giftGoodsMapper.selectGiftGoodsInfoList(giftGoods);
				if(CollectionUtils.isNotEmpty(giftGoodsList)) {
					Long deposit = 0l;//退到余额里的钱
					Long depositTotal = 0l;//押金总共退的钱
					for(GiftGoods tGiftGoods:giftGoodsList) {
						if("0".equals(tGiftGoods.getState())) {
							deposit+=tGiftGoods.getPrice();
						}
						depositTotal +=tGiftGoods.getPrice();
					}
					// 把可以退的押金返还到我的钱包中
					Wallet wallet = walletMapper.selectWalletByUserId(userId);
					Wallet uwallet = new Wallet();
					uwallet.setId(wallet.getId());
					uwallet.setBalance(wallet.getBalance() + deposit);
					uwallet.setDeposit(wallet.getDeposit() - depositTotal);
					walletMapper.updateWallet(uwallet);
					// 生成退还押金记录
					ConsumptionInformation ci = new ConsumptionInformation();
					ci.setId(UUIDUtil.getUUID());
					ci.setConsumptionType("2");
					ci.setMoney(deposit);
					ci.setConsumptionUser(userId);
					ci.setConsumptionDate(new Date());
					ci.setState("3");
					ci.setPayType("2");
					consumptionInformationMapper.insertConsumptionInformation(ci);
					// 清空礼物机位置 修改礼物机
					giftMachineMapper.updateTGiftReload(giftId);
					giftGoodsMapper.deleteGiftGoodsById(giftId);
					giftLocationMapper.deleteGiftLocationById(giftId);
					giftApplyMapper.deleteGiftApplyById(giftId);
					result.setStatus(Result.SUCCESS);
					result.setMessage("退押金成功");
					return result;
				}
			}
			result.setStatus(Result.FAILED);
			result.setMessage("退押金失败");
			return result;
		} catch (Exception e) {
			log.error("退押金失败",e);
			throw new RuntimeException();
		}
	}

	@Override
	public DataResult cashWithdrawal(String money, String cashType, String userId) {
		DataResult result = new DataResult();
		try {
			if(money.indexOf(".") <= 0) {
				result.setStatus(Result.FAILED);
				result.setMessage("金额参数错误失败");
				return result;
			}
			Long moneyL = Long.valueOf(money.replace(".", ""));
			Wallet wallet = new Wallet();
			wallet.setUserId(userId);
			List<Wallet> wallets = walletMapper.selectWalletList(wallet);
			if(CollectionUtils.isEmpty(wallets)) {
				result.setStatus(Result.FAILED);
				result.setMessage("用户已没有余额");
				return result;
			}else {
				Long balance = null;
				if(wallets.size() == 1) {
					balance = wallets.get(0).getBalance();
				}
				if(balance == null || balance.longValue() == 0) {
					result.setStatus(Result.FAILED);
					result.setMessage("用户已没有余额");
					return result;
				}else {
					if(balance.longValue() < moneyL.longValue()) {
						result.setStatus(Result.FAILED);
						result.setMessage("用户余额不足");
						return result;
					}
				}
			}
			TCashWithdrawal tCashWithdrawal = new TCashWithdrawal();
			tCashWithdrawal.setId(UUIDUtil.getUUID());
			tCashWithdrawal.setMoney(moneyL);
			tCashWithdrawal.setCashType(cashType);
			tCashWithdrawal.setApplicantUser(userId);
			tCashWithdrawal.setApplicantDate(new Date());
			tCashWithdrawal.setState("0");
			tCashWithdrawalMapper.insertTCashWithdrawal(tCashWithdrawal);
			result.setStatus(Result.SUCCESS);
			result.setMessage("提现申请成功");
			return result;
		} catch (Exception e) {
			log.error("提现申请失败",e);
			result.setStatus(Result.FAILED);
			result.setMessage("提现申请失败");
			return result;
		}
	}

	@Override
	public DataResult queryKuaidiInfo(String orderId) {
		DataResult result = new DataResult();
		try {
			Order order = orderMapper.selectOrderById(orderId);
			if(order == null) {
				result.setStatus(Result.FAILED);
				result.setMessage("查询订单信息失败");
				return result;
			}
			String companyCode = order.getCompanyCode();
			String expressNo = order.getExpressNo();
			if(StringUtils.isEmpty(companyCode) || StringUtils.isEmpty(expressNo)) {
				result.setStatus(Result.FAILED);
				result.setMessage("查询订单信息异常");
				return result;
			}
			KuaidiResult kuaidiResult = Kuaidi100Util.getString(companyCode, expressNo);
			TExpressCompany tExpressCompany = tExpressCompanyMapper.selectTExpressCompanyByCode(companyCode);
			if(tExpressCompany != null) {
				kuaidiResult.setNuName(tExpressCompany.getCompanyName());
			}
			result.setResult(kuaidiResult);
			result.setStatus(Result.SUCCESS);
			result.setMessage("提现成功");
			return result;
		} catch (Exception e) {
			log.error("查询快递信息失败",e);
			result.setStatus(Result.FAILED);
			result.setMessage("查询快递信息失败");
			return result;
		}
	}

	@Override
	public List<GiftApply> selectGiftIdOverdue() {
		return giftApplyMapper.selectGiftIdOverdue();
	}

}
