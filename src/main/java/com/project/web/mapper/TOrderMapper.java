package com.project.web.mapper;

import com.project.web.domain.OrderVo;
import com.project.web.domain.TOrder;
import java.util.List;

/**
 * 订单 数据层
 * 
 * @author lws
 * @date 2019-03-12
 */
public interface TOrderMapper 
{
	/**
     * 查询订单信息
     * 
     * @param id 订单ID
     * @return 订单信息
     */
	public TOrder selectTOrderById(String id);
	
	/**
     * 查询订单列表
     * 
     * @param tOrder 订单信息
     * @return 订单集合
     */
	public List<TOrder> selectTOrderList(TOrder tOrder);

	/**
	 * 查询订单
	 * @param tOrder
	 * @return
	 */
	public List<OrderVo> selectTOrderAllList(TOrder tOrder);
	/**
     * 新增订单
     * 
     * @param tOrder 订单信息
     * @return 结果
     */
	public int insertTOrder(TOrder tOrder);
	
	/**
     * 修改订单
     * 
     * @param tOrder 订单信息
     * @return 结果
     */
	public int updateTOrder(TOrder tOrder);
	
	/**
     * 删除订单
     * 
     * @param id 订单ID
     * @return 结果
     */
	public int deleteTOrderById(String id);
	
	/**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTOrderByIds(String[] ids);
	/**
	 * 订单详情
	 * @param id
	 * @return
	 */
	public OrderVo selectTOrderVoDetail(String id);
}