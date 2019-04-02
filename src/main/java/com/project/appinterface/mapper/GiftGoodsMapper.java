package com.project.appinterface.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.appinterface.domain.GiftGoods;

/**
 * 礼物机-礼物中间 数据层
 * 
 * @author lws
 * @date 2019-03-04
 */
public interface GiftGoodsMapper 
{
	/**
     * 查询礼物机-礼物中间信息
     * 
     * @param id 礼物机-礼物中间ID
     * @return 礼物机-礼物中间信息
     */
	public GiftGoods selectGiftGoodsById(@Param("id")String id);


	/**
     * 查询礼物机-礼物中间列表
     * 
     * @param giftGoods 礼物机-礼物中间信息
     * @return 礼物机-礼物中间集合
     */
	public List<GiftGoods> selectGiftGoodsList(GiftGoods giftGoods);
	
	/**
     * 新增礼物机-礼物中间
     * 
     * @param giftGoods 礼物机-礼物中间信息
     * @return 结果
     */
	public int insertGiftGoods(GiftGoods giftGoods);
	
	/**
     * 修改礼物机-礼物中间
     * 
     * @param giftGoods 礼物机-礼物中间信息
     * @return 结果
     */
	public int updateGiftGoods(GiftGoods giftGoods);
	
	/**
     * 删除礼物机-礼物中间
     * 
     * @param id 礼物机-礼物中间ID
     * @return 结果
     */
	public int deleteGiftGoodsById(@Param("giftId")String giftId);
	
	/**
     * 批量删除礼物机-礼物中间
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGiftGoodsByIds(String[] ids);
	/**
	 * 批量新增
	 * @param list
	 * @return
	 */
	public int insertGiftGoodsBatch(List<GiftGoods> list);
	
	/**
	 * 礼物机商品信息列表
	 * @param giftGoods
	 * @return
	 */
	public List<GiftGoods> selectGiftGoodsInfoList(GiftGoods giftGoods);
	
}