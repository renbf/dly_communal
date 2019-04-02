package com.project.appinterface.service;

import com.project.appinterface.domain.CommodityInformation;
import java.util.List;

/**
 * 商品 服务层
 * 
 * @author lws
 * @date 2019-03-04
 */
public interface ICommodityInformationService 
{
	/**
     * 查询商品信息
     * 
     * @param id 商品ID
     * @return 商品信息
     */
	public CommodityInformation selectCommodityInformationById(String id);
	

	
	/**
     * 新增商品
     * 
     * @param commodityInformation 商品信息
     * @return 结果
     */
	public int insertCommodityInformation(CommodityInformation commodityInformation);
	
	/**
     * 修改商品
     * 
     * @param commodityInformation 商品信息
     * @return 结果
     */
	public int updateCommodityInformation(CommodityInformation commodityInformation);
		
	/**
     * 删除商品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCommodityInformationByIds(String ids);
	
}
