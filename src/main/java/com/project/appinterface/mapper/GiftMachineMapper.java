package com.project.appinterface.mapper;

import com.project.appinterface.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户 数据层
 * 
 * @author lws
 * @date 2019-03-04
 */
public interface GiftMachineMapper
{
	/**
	 * 根据状态查询礼物机
	 * @param gift
	 * @return
	 */
	public List<Gift> selectGiftList(Gift gift);
	public Gift selectGiftByIdw(@Param("id")String id);
	/**
	 * 查询优惠券信息
	 * @param userId
	 * @return
	 */
	public List<CouponReceive> queryCoupon(@Param("userId")String userId);

	/**
	 * 查询礼物机id
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public Gift queryGiftId(@Param("longitude")String longitude, @Param("latitude")String latitude);
	/**
	 * 修改礼物机
	 * @param gift
	 * @return
	 */
	public int updateTGift(Gift gift);
	/**
	 * 查询礼物类型
	 * @return
	 */
	public List<GiftModel> queryGiftModel();
	/**
	 * 查询支付订单
	 * @param grifId
	 * @return
	 */
	public PayOrder queryPayGift(@Param("giftApplyId")String giftApplyId);
	/**
	 * 添加收货地址
	 * @param userAddress
	 */
	public void insertCollectAddress(UserAddress userAddress);

	/**
	 * 查询收货地址
	 * @param addressmap
	 * @return
	 */
	public UserAddress queryaddress(Map<String, String> addressmap);

	/**
	 * 修改收货地址
	 * @param map
	 */
	public void updateCollectAddress(Map<String, String> map);

	/**
	 * 删除收货地址
	 * @param map
	 */
	public void deleteCollectAddress(Map<String, String> map);

	/**
	 *  查询默认收货地址
 	 * @param map
	 * @return
	 */
	public UserAddress queryDefault(Map<String, String> map);

	/**
	 * 查询收货地址
	 * @param map
	 * @return
	 */
	List<UserAddress>getCollectgoodsAddress(Map<String,String>map);
    /**
     * 礼物机支付
	 * @param po
	 */
	public void addPayOrder(PayOrder po);

	/**
	 * 查询订单
	 * @param map
	 * @return
	 */
	public PayOrder queryPayOrder(Map<String,String> map);

	/**
	 * 修改支付订单
	 * @param map
	 */
	public void updatePayOrder(Map<String,String> map);

	/**
	 * 查询机器基本信息
	 * @param userId
	 * @return
	 */
	public List<Gift> queryGift(@Param("userId") String userId);

	/**
	 * 重置礼物机
	 * @param gift
	 * @return
	 */
	public int updateTGiftReload(String id);
	/**
	 * 审核通过的信息
	 * @param giftId
	 * @return
	 */
	public GiftVo selectGiftByGiftId(@Param("giftId")String giftId);
	
}