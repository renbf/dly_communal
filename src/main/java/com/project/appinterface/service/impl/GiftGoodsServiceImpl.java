package com.project.appinterface.service.impl;

import java.util.List;

import com.project.appinterface.mapper.GiftGoodsMapper;
import com.project.appinterface.domain.GiftGoods;
import com.project.appinterface.service.IGiftGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.common.support.Convert;

/**
 * 礼物机-礼物中间 服务层实现
 * 
 * @author lws
 * @date 2019-03-04
 */
@Service
public class GiftGoodsServiceImpl implements IGiftGoodsService
{
	@Autowired
	private GiftGoodsMapper giftGoodsMapper;

	/**
     * 查询礼物机-礼物中间信息
     * 
     * @param id 礼物机-礼物中间ID
     * @return 礼物机-礼物中间信息
     */
    @Override
	public GiftGoods selectGiftGoodsById(String id)
	{
	    return giftGoodsMapper.selectGiftGoodsById(id);
	}
	
	/**
     * 查询礼物机-礼物中间列表
     * 
     * @param giftGoods 礼物机-礼物中间信息
     * @return 礼物机-礼物中间集合
     */
	@Override
	public List<GiftGoods> selectGiftGoodsList(GiftGoods giftGoods)
	{
	    return giftGoodsMapper.selectGiftGoodsList(giftGoods);
	}
	
    /**
     * 新增礼物机-礼物中间
     * 
     * @param giftGoods 礼物机-礼物中间信息
     * @return 结果
     */
	@Override
	public int insertGiftGoods(GiftGoods giftGoods)
	{
	    return giftGoodsMapper.insertGiftGoods(giftGoods);
	}
	
	/**
     * 修改礼物机-礼物中间
     * 
     * @param giftGoods 礼物机-礼物中间信息
     * @return 结果
     */
	@Override
	public int updateGiftGoods(GiftGoods giftGoods)
	{
	    return giftGoodsMapper.updateGiftGoods(giftGoods);
	}

	/**
     * 删除礼物机-礼物中间对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGiftGoodsByIds(String ids)
	{
		return giftGoodsMapper.deleteGiftGoodsByIds(Convert.toStrArray(ids));
	}
	
}
