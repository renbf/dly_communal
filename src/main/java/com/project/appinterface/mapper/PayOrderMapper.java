package com.project.appinterface.mapper;

import com.project.appinterface.domain.PayOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 支付 数据层
 * 
 * @author lws
 * @date 2019-03-05
 */
public interface PayOrderMapper 
{
	/**
     * 查询支付信息
     * 
     * @param id 支付ID
     * @return 支付信息
     */
	public PayOrder selectPayOrderById(@Param("id")String id);
	
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
     * 删除支付
     * 
     * @param id 支付ID
     * @return 结果
     */
	public int deletePayOrderById(String id);
	
	/**
     * 批量删除支付
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePayOrderByIds(String[] ids);
	/**
	 * 根据订单号修改
	 * @param payOrder
	 * @return
	 */
	public int updatePayOrderByOrderNo(PayOrder payOrder);
	/**
	 * 根据订单号查询
	 * @param orderNo
	 * @return
	 */
	public PayOrder selectPayOrderByOrderNo(String orderNo);
	
}