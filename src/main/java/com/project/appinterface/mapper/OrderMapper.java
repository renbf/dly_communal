package com.project.appinterface.mapper;

import com.project.appinterface.domain.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单 数据层
 * 
 * @author lws
 * @date 2019-03-05
 */
public interface OrderMapper 
{
	/**
     * 查询订单信息
     * 
     * @param id 订单ID
     * @return 订单信息
     */
	public Order selectOrderById(@Param("id") String id);
	/**
	 * 查询订单信息
	 *
	 * @param id 订单ID
	 * @return 订单信息
	 */
	public List<Order>  selectOrderByUserId(Order order);

	/**
	 * 查询订单信息
	 * @param order
	 * @return
	 */
	public List<Order> selectOrderGoodsList(Order order);
	/**
     * 查询订单列表
     * 
     * @param order 订单信息
     * @return 订单集合
     */
	public List<Order> selectOrderList(Order order);
	
	/**
     * 新增订单
     * 
     * @param order 订单信息
     * @return 结果
     */
	public int insertOrder(Order order);
	
	/**
     * 修改订单
     * 
     * @param order 订单信息
     * @return 结果
     */
	public int updateOrder(Order order);
	
	/**
     * 删除订单
     * 
     * @param id 订单ID
     * @return 结果
     */
	public int deleteOrderById(String id);
	
	/**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrderByIds(String[] ids);
	
}