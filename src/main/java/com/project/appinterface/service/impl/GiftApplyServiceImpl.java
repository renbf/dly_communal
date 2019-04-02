package com.project.appinterface.service.impl;

import java.util.List;

import com.project.appinterface.domain.GiftApply;
import com.project.appinterface.mapper.GiftApplyMapper;
import com.project.appinterface.service.IGiftApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.support.Convert;

/**
 * 礼物机申请 服务层实现
 * 
 * @author lws
 * @date 2019-03-05
 */
@Service
public class GiftApplyServiceImpl implements IGiftApplyService
{
	@Autowired
	private GiftApplyMapper giftApplyMapper;

	/**
     * 查询礼物机申请信息
     * 
     * @param id 礼物机申请ID
     * @return 礼物机申请信息
     */
    @Override
	public GiftApply selectGiftApplyById(String id)
	{
	    return giftApplyMapper.selectGiftApplyById(id);
	}
	
	/**
     * 查询礼物机申请列表
     * 
     * @param giftApply 礼物机申请信息
     * @return 礼物机申请集合
     */
	@Override
	public List<GiftApply> selectGiftApplyList(GiftApply giftApply)
	{
	    return giftApplyMapper.selectGiftApplyList(giftApply);
	}
	
    /**
     * 新增礼物机申请
     * 
     * @param giftApply 礼物机申请信息
     * @return 结果
     */
	@Override
	public int insertGiftApply(GiftApply giftApply)
	{
	    return giftApplyMapper.insertGiftApply(giftApply);
	}
	
	/**
     * 修改礼物机申请
     * 
     * @param giftApply 礼物机申请信息
     * @return 结果
     */
	@Override
	public int updateGiftApply(GiftApply giftApply)
	{
	    return giftApplyMapper.updateGiftApply(giftApply);
	}

	/**
     * 删除礼物机申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGiftApplyByIds(String ids)
	{
		return giftApplyMapper.deleteGiftApplyByIds(Convert.toStrArray(ids));
	}
	
}
