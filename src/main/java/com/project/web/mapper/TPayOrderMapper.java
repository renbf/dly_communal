package com.project.web.mapper;

import com.project.web.domain.TPayOrder;

import java.util.List;

/**
 * 支付 数据层
 * 
 * @author lws
 * @date 2019-03-15
 */
public interface TPayOrderMapper 
{
	/**
     * 查询支付信息
     * 
     * @param id 支付ID
     * @return 支付信息
     */
	public TPayOrder selectTPayOrderById(String id);

	/**
	 * 查询交易金额
	 *
	 * @param orderno 订单编号
	 * @return 订单信息
	 */
	public TPayOrder selectTPayOrderByOrderNo(String orderno);

	/**
     * 查询支付列表
     * 
     * @param tPayOrder 支付信息
     * @return 支付集合
     */
	public List<TPayOrder> selectTPayOrderList(TPayOrder tPayOrder);
	
	/**
     * 新增支付
     * 
     * @param tPayOrder 支付信息
     * @return 结果
     */
	public int insertTPayOrder(TPayOrder tPayOrder);
	
	/**
     * 修改支付
     * 
     * @param tPayOrder 支付信息
     * @return 结果
     */
	public int updateTPayOrder(TPayOrder tPayOrder);
	
	/**
     * 删除支付
     * 
     * @param id 支付ID
     * @return 结果
     */
	public int deleteTPayOrderById(String id);
	
	/**
     * 批量删除支付
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTPayOrderByIds(String[] ids);
	
}