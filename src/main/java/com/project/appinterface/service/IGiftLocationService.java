package com.project.appinterface.service;

import com.project.appinterface.domain.GiftLocation;

import java.util.List;

/**
 * 礼物位置 服务层
 * 
 * @author lws
 * @date 2019-03-04
 */
public interface IGiftLocationService 
{
	/**
     * 查询礼物位置信息
     * 
     * @param id 礼物位置ID
     * @return 礼物位置信息
     */
	public GiftLocation selectGiftLocationById(String id);
	
	/**
     * 查询礼物位置列表
     * 
     * @param giftLocation 礼物位置信息
     * @return 礼物位置集合
     */
	public List<GiftLocation> selectGiftLocationList(GiftLocation giftLocation);
	
	/**
     * 新增礼物位置
     * 
     * @param giftLocation 礼物位置信息
     * @return 结果
     */
	public int insertGiftLocation(GiftLocation giftLocation);
	
	/**
     * 修改礼物位置
     * 
     * @param giftLocation 礼物位置信息
     * @return 结果
     */
	public int updateGiftLocation(GiftLocation giftLocation);
		
	/**
     * 删除礼物位置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGiftLocationByIds(String ids);
	
}
