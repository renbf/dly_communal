package com.project.appinterface.service.impl;


import com.project.appinterface.mapper.GiftLocationMapper;
import com.project.appinterface.domain.GiftLocation;
import com.project.appinterface.service.IGiftLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.common.support.Convert;

import java.util.List;

/**
 * 礼物位置 服务层实现
 * 
 * @author lws
 * @date 2019-03-04
 */
@Service
public class GiftLocationServiceImpl implements IGiftLocationService
{
	@Autowired
	private GiftLocationMapper giftLocationMapper;

	/**
     * 查询礼物位置信息
     * 
     * @param id 礼物位置ID
     * @return 礼物位置信息
     */
    @Override
	public GiftLocation selectGiftLocationById(String id)
	{
	    return giftLocationMapper.selectGiftLocationById(id);
	}
	
	/**
     * 查询礼物位置列表
     *
     * @param giftLocation 礼物位置信息
     * @return 礼物位置集合
     */
	@Override
	public List<GiftLocation> selectGiftLocationList(GiftLocation  giftLocation)
	{
	    return giftLocationMapper.selectGiftLocationList(giftLocation);
	}
	
    /**
     * 新增礼物位置
     * 
     * @param giftLocation 礼物位置信息
     * @return 结果
     */
	@Override
	public int insertGiftLocation(GiftLocation giftLocation)
	{
	    return giftLocationMapper.insertGiftLocation(giftLocation);
	}
	
	/**
     * 修改礼物位置
     * 
     * @param giftLocation 礼物位置信息
     * @return 结果
     */
	@Override
	public int updateGiftLocation(GiftLocation giftLocation)
	{
	    return giftLocationMapper.updateGiftLocation(giftLocation);
	}

	/**
     * 删除礼物位置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGiftLocationByIds(String ids)
	{
		return giftLocationMapper.deleteGiftLocationByIds(Convert.toStrArray(ids));
	}
	
}
