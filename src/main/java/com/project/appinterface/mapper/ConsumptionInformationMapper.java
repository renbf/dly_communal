package com.project.appinterface.mapper;

import com.project.appinterface.domain.ConsumptionInformation;

import java.util.List;

/**
 * 消费 数据层
 * 
 * @author lws
 * @date 2019-03-08
 */
public interface ConsumptionInformationMapper 
{
	/**
     * 查询消费信息
     * 
     * @param id 消费ID
     * @return 消费信息
     */
	public ConsumptionInformation selectConsumptionInformationById(String id);
	
	/**
     * 查询消费列表
     * 
     * @param consumptionInformation 消费信息
     * @return 消费集合
     */
	public List<ConsumptionInformation> selectConsumptionInformationList(ConsumptionInformation consumptionInformation);
	/**
     * 新增消费
     * 
     * @param consumptionInformation 消费信息
     * @return 结果
     */
	public int insertConsumptionInformation(ConsumptionInformation consumptionInformation);
	
	/**
     * 修改消费
     * 
     * @param consumptionInformation 消费信息
     * @return 结果
     */
	public int updateConsumptionInformation(ConsumptionInformation consumptionInformation);
	
	/**
     * 删除消费
     * 
     * @param id 消费ID
     * @return 结果
     */
	public int deleteConsumptionInformationById(String id);
	
	/**
     * 批量删除消费
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteConsumptionInformationByIds(String[] ids);
	/**
	 * 根据订单号查询
	 * @param orderNo
	 * @return
	 */
	public ConsumptionInformation selectConsumptionInformationByOrderNo(String orderNo);
	
}