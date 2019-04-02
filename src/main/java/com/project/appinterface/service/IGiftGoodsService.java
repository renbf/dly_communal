package com.project.appinterface.service;

import com.project.appinterface.domain.GiftGoods;
import java.util.List;

/**
 * 礼物机-礼物中间 服务层
 * 
 * @author lws
 * @date 2019-03-04
 */
public interface IGiftGoodsService 
{
	/**
     * 查询礼物机-礼物中间信息
     * 
     * @param id 礼物机-礼物中间ID
     * @return 礼物机-礼物中间信息
     */
	public GiftGoods selectGiftGoodsById(String id);
	
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
     * 删除礼物机-礼物中间信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGiftGoodsByIds(String ids);
	
}
