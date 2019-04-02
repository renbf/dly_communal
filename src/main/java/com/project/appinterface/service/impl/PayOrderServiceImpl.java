package com.project.appinterface.service.impl;

import java.util.List;

import com.project.appinterface.domain.PayOrder;
import com.project.appinterface.mapper.PayOrderMapper;
import com.project.appinterface.service.IPayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.support.Convert;

/**
 * 支付 服务层实现
 * 
 * @author lws
 * @date 2019-03-05
 */
@Service
public class PayOrderServiceImpl implements IPayOrderService
{
	@Autowired
	private PayOrderMapper payOrderMapper;

	/**
     * 查询支付信息
     * 
     * @param id 支付ID
     * @return 支付信息
     */
    @Override
	public PayOrder selectPayOrderById(String id)
	{
	    return payOrderMapper.selectPayOrderById(id);
	}
	
	/**
     * 查询支付列表
     * 
     * @param payOrder 支付信息
     * @return 支付集合
     */
	@Override
	public List<PayOrder> selectPayOrderList(PayOrder payOrder)
	{
	    return payOrderMapper.selectPayOrderList(payOrder);
	}
	
    /**
     * 新增支付
     * 
     * @param payOrder 支付信息
     * @return 结果
     */
	@Override
	public int insertPayOrder(PayOrder payOrder)
	{
	    return payOrderMapper.insertPayOrder(payOrder);
	}
	
	/**
     * 修改支付
     * 
     * @param payOrder 支付信息
     * @return 结果
     */
	@Override
	public int updatePayOrder(PayOrder payOrder)
	{
	    return payOrderMapper.updatePayOrder(payOrder);
	}

	/**
     * 删除支付对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePayOrderByIds(String ids)
	{
		return payOrderMapper.deletePayOrderByIds(Convert.toStrArray(ids));
	}
	
}
