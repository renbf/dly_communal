package com.project.web.service;

import java.util.List;

import com.project.web.domain.TPayOrder;
import com.project.web.mapper.TPayOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.common.support.Convert;

/**
 * 支付 服务层实现
 * 
 * @author lws
 * @date 2019-03-15
 */
@Service
public class TPayOrderServiceImpl implements ITPayOrderService 
{
	@Autowired
	private TPayOrderMapper tPayOrderMapper;

	/**
     * 查询支付信息
     * 
     * @param id 支付ID
     * @return 支付信息
     */
    @Override
	public TPayOrder selectTPayOrderById(String id)
	{
	    return tPayOrderMapper.selectTPayOrderById(id);
	}

	@Override
	public TPayOrder selectTPayOrderByOrderNo(String orderno) {
		return tPayOrderMapper.selectTPayOrderByOrderNo(orderno);
	}

	/**
     * 查询支付列表
     * 
     * @param tPayOrder 支付信息
     * @return 支付集合
     */
	@Override
	public List<TPayOrder> selectTPayOrderList(TPayOrder tPayOrder)
	{
	    return tPayOrderMapper.selectTPayOrderList(tPayOrder);
	}
	
    /**
     * 新增支付
     * 
     * @param tPayOrder 支付信息
     * @return 结果
     */
	@Override
	public int insertTPayOrder(TPayOrder tPayOrder)
	{
	    return tPayOrderMapper.insertTPayOrder(tPayOrder);
	}
	
	/**
     * 修改支付
     * 
     * @param tPayOrder 支付信息
     * @return 结果
     */
	@Override
	public int updateTPayOrder(TPayOrder tPayOrder)
	{
	    return tPayOrderMapper.updateTPayOrder(tPayOrder);
	}

	/**
     * 删除支付对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTPayOrderByIds(String ids)
	{
		return tPayOrderMapper.deleteTPayOrderByIds(Convert.toStrArray(ids));
	}
	
}
