package com.project.appinterface.mapper;

import com.project.appinterface.domain.CommodityInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品 数据层
 * 
 * @author lws
 * @date 2019-03-04
 */
public interface CommodityInformationMapper 
{
	/**
     * 查询商品信息
     * 
     * @param id 商品ID
     * @return 商品信息
     */
	public CommodityInformation selectCommodityInformationById(@Param("id")String id);
	/**
	 * 查询所有绑定商品
	 * @param giftId
	 * @return
	 */
	public List<CommodityInformation> selectGiftGoods(@Param("giftApplyId")String giftApplyId);

	/**
	 * 查询所有未抽走商品的总额
	 * @param giftId
	 * @return
	 */
	public CommodityInformation querySumMoney(@Param("giftId") String giftId);
	/**
	 * 查询商品列表
	 *
	 * @param name
	 * @return 商品集合
	 */
	public List<CommodityInformation> queryGoodsAll(@Param("name")String name);
	/**
     * 查询商品列表
     * 
     * @param id 商品信息
     * @return 商品集合
     */
	public List<CommodityInformation> selectCommodityInformationList(@Param("giftId") String giftId);

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
     * 删除商品
     *
     * @param id 商品ID
     * @return 结果
     */
	public int deleteCommodityInformationById(String id);

	/**
     * 批量删除商品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCommodityInformationByIds(String[] ids);
	
}