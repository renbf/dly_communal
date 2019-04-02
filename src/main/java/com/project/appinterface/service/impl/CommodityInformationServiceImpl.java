package com.project.appinterface.service.impl;

import java.util.List;

import com.project.appinterface.mapper.CommodityInformationMapper;
import com.project.appinterface.domain.CommodityInformation;
import com.project.appinterface.service.ICommodityInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.common.support.Convert;

/**
 * 商品 服务层实现
 * 
 * @author lws
 * @date 2019-03-04
 */
@Service
public class CommodityInformationServiceImpl implements ICommodityInformationService
{
	@Autowired
	private CommodityInformationMapper commodityInformationMapper;

	/**
     * 查询商品信息
     * 
     * @param id 商品ID
     * @return 商品信息
     */
    @Override
	public CommodityInformation selectCommodityInformationById(String id)
	{
	    return commodityInformationMapper.selectCommodityInformationById(id);
	}
	

    /**
     * 新增商品
     * 
     * @param commodityInformation 商品信息
     * @return 结果
     */
	@Override
	public int insertCommodityInformation(CommodityInformation commodityInformation)
	{
	    return commodityInformationMapper.insertCommodityInformation(commodityInformation);
	}
	
	/**
     * 修改商品
     * 
     * @param commodityInformation 商品信息
     * @return 结果
     */
	@Override
	public int updateCommodityInformation(CommodityInformation commodityInformation)
	{
	    return commodityInformationMapper.updateCommodityInformation(commodityInformation);
	}

	/**
     * 删除商品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCommodityInformationByIds(String ids)
	{
		return commodityInformationMapper.deleteCommodityInformationByIds(Convert.toStrArray(ids));
	}
	
}
