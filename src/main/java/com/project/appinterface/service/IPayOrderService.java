package com.project.appinterface.service;

import com.project.appinterface.domain.PayOrder;
import java.util.List;

/**
 * 支付 服务层
 * 
 * @author lws
 * @date 2019-03-05
 */
public interface IPayOrderService 
{
	/**
     * 查询支付信息
     * 
     * @param id 支付ID
     * @return 支付信息
     */
	public PayOrder selectPayOrderById(String id);
	
	/**
     * 查询支付列表
     * 
     * @param payOrder 支付信息
     * @return 支付集合
     */
	public List<PayOrder> selectPayOrderList(PayOrder payOrder);
	
	/**
     * 新增支付
     * 
     * @param payOrder 支付信息
     * @return 结果
     */
	public int insertPayOrder(PayOrder payOrder);
	
	/**
     * 修改支付
     * 
     * @param payOrder 支付信息
     * @return 结果
     */
	public int updatePayOrder(PayOrder payOrder);
		
	/**
     * 删除支付信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePayOrderByIds(String ids);
	
}
