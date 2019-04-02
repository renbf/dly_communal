package com.project.appinterface.service.impl;

import java.util.List;

import com.project.appinterface.domain.ConsumptionInformation;
import com.project.appinterface.mapper.ConsumptionInformationMapper;
import com.project.appinterface.service.IConsumptionInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.common.support.Convert;

/**
 * 消费 服务层实现
 * 
 * @author lws
 * @date 2019-03-08
 */
@Service
public class ConsumptionInformationServiceImpl implements IConsumptionInformationService
{
	@Autowired
	private ConsumptionInformationMapper consumptionInformationMapper;

	/**
     * 查询消费信息
     * 
     * @param id 消费ID
     * @return 消费信息
     */
    @Override
	public ConsumptionInformation selectConsumptionInformationById(String id)
	{
	    return consumptionInformationMapper.selectConsumptionInformationById(id);
	}
	
	/**
     * 查询消费列表
     * 
     * @param consumptionInformation 消费信息
     * @return 消费集合
     */
	@Override
	public List<ConsumptionInformation> selectConsumptionInformationList(ConsumptionInformation consumptionInformation)
	{
	    return consumptionInformationMapper.selectConsumptionInformationList(consumptionInformation);
	}
	
    /**
     * 新增消费
     * 
     * @param consumptionInformation 消费信息
     * @return 结果
     */
	@Override
	public int insertConsumptionInformation(ConsumptionInformation consumptionInformation)
	{
	    return consumptionInformationMapper.insertConsumptionInformation(consumptionInformation);
	}
	
	/**
     * 修改消费
     * 
     * @param consumptionInformation 消费信息
     * @return 结果
     */
	@Override
	public int updateConsumptionInformation(ConsumptionInformation consumptionInformation)
	{
	    return consumptionInformationMapper.updateConsumptionInformation(consumptionInformation);
	}

	/**
     * 删除消费对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteConsumptionInformationByIds(String ids)
	{
		return consumptionInformationMapper.deleteConsumptionInformationByIds(Convert.toStrArray(ids));
	}
	
}
