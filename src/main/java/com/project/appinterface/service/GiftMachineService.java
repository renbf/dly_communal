package com.project.appinterface.service;


import com.project.appinterface.domain.GiftApply;
import com.project.appinterface.domain.ParamArrayVo;
import com.project.appinterface.domain.UserAddress;
import com.project.appinterface.domain.UserInfo;
import com.project.common.result.DataResult;
import com.project.common.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DataTruncation;
import java.util.List;
import java.util.Map;

/**
 * 用户 服务层
 * 
 * @author lws
 * @date 2019-03-04
 */
public interface GiftMachineService
{
	/**
	 * 查询礼物机
	 * @param state
	 * @return
	 */
	public DataResult queryGift(String state,String user_id);
	public DataResult  queryGiftId(String longitude,String latitude);
	/**
	 * 查询礼物机类型
	 * @return
	 */
	public DataResult queryGiftModel();
	/**
	 * 查询礼物机详情
	 * @param giftId
	 * @return
	 */
	public DataResult queryById(String giftId,String userId);

	/**
	 * 查询所有商品
	 * @return
	 */
	public DataResult queryGoodsAll(String goodsName);

	/**
	 * 查询是否中奖
	 * @param position
	 * @param userId
	 * @param giftId
	 * @return
	 */
	public DataResult isPrize(String position, String userId,String  giftId);

	/**
	 * 生成订单
	 * @return
	 */
	public Result updateOrder(String orderId,String userId,String addressId);

	/**
	 * 查询订单
	 * @param orderId
	 * @return
	 */
	public DataResult queryOrder(String orderId);

	/**
	 * 添加收货地址
	 * @param userAddress
	 * @return
	 */
	public Result insertCollectAddress(UserAddress userAddress);
	//修改收货地址
	public Result updateCollectAddress(Map<String,String> map);
	//删除收货地址
	public Result deleteCollectAddress(Map<String,String> map) ;
	//设置默认收货地址
	public Result updateDefault(Map<String,String>map,String userid);
	//查询默认收货地址
	public DataResult queryDefault(String userid);
	//查询我的收货地址
	public DataResult getCollectgoodsAddress(String userid);
	/**
	 * 支付礼物机
	 * @param paramArrayVo
	 * @return
	 */
	public DataResult payGift(ParamArrayVo paramArrayVo,String ip);
	
	public DataResult tryPayGift(ParamArrayVo paramArrayVo,String ip);
	//支付回调
	public void notice(HttpServletRequest request, HttpServletResponse response);
	//微信回调
	public void weiPayNotice(HttpServletRequest request,HttpServletResponse response);

	/**
	 * 退款
	 * @param userId
	 * @param money
	 * @return
	 */
	public Result refundApplication(String userId,String money);

	/**
	 * 退押金
	 * @param userId
	 * @param giftId
	 * @return
	 */
	public DataResult returnDepositMoney(String userId,String giftId);
	/**
	 * 查询参数
	 * @return
	 */
	public DataResult queryRentType();

	/**
	 * 添加收藏
	 * @return
	 */
	public DataResult addCollection(String giftId,String userId);

	/**
	 * 删除收藏
	 * @param collectionId
	 * @return
	 */
	public Result deleteCollection(String collectionId);

	/**
	 * c查询收藏
	 * @param userId
	 * @return
	 */
	public DataResult queryCollection(String userId);
	/**
	 * 查询订单状态
	 * @param orderNo
	 * @return
	 */
	public Result queryPayState(String orderNo);

	/**
	 *  查询消费记录s
	 * @param userId
	 * @return
	 */
	public DataResult queryRecordsConsumption(String userId);


	/**
	 * 查询抽奖记录
	 * @param userId
	 * @return
	 */
	public DataResult queryNoPrize(String userId);
	/**
	 * 查询中奖记录
	 * @param userId
	 * @return
	 */
	public DataResult queryOrderByUserId(String orderId);

	/**
	 * 确认收货。
	 * @param userId
	 * @param orderNo
	 * @return
	 */
	public Result getRconfirmCollect(String userId,String orderNo);
	public DataResult queryConsumptionRecords(String userId);
	/**
	 * 查询订单信息
	 * @param userId
	 * @param state
	 * @return
	 */
	public DataResult queryPrizeOrder(String userId,String state);

	public DataResult queryPayInfoByOrderNo(ParamArrayVo paramArrayVo,String ip);
	/**
	 * 退押金
	 * @param giftId
	 * @param userId
	 * @return
	 */
	public DataResult refundDeposit(String giftId,String userId);
	/**
	 * 提现申请
	 * @param money
	 * @param cashType
	 * @param userId
	 * @return
	 */
	public DataResult cashWithdrawal(String money,String cashType,String userId);
	/**
	 * 查询快递信息
	 * @param orderId
	 * @return
	 */
	public DataResult queryKuaidiInfo(String orderId);
	
	/**
	 * 查询过期的礼物机
	 * @return
	 */
	public List<GiftApply> selectGiftIdOverdue();
}
