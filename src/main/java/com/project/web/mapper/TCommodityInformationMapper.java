package com.project.web.mapper;

import java.util.List;

import com.project.web.domain.TCommodityInformation;
import com.project.web.domain.vo.TCommodityInformationVo;

/**
 * 商品 数据层
 * 
 * @author lws
 * @date 2019-03-12
 */
public interface TCommodityInformationMapper 
{
	/**
     * 查询商品信息
     * 
     * @param id 商品ID
     * @return 商品信息
     */
	public TCommodityInformation selectTCommodityInformationById(String id);
	
	/**
     * 查询商品列表
     * 
     * @param tCommodityInformation 商品信息
     * @return 商品集合
     */
	public List<TCommodityInformation> selectTCommodityInformationList(TCommodityInformation tCommodityInformation);
	
	/**
     * 新增商品
     * 
     * @param tCommodityInformation 商品信息
     * @return 结果
     */
	public int insertTCommodityInformation(TCommodityInformation tCommodityInformation);
	
	/**
     * 修改商品
     * 
     * @param tCommodityInformation 商品信息
     * @return 结果
     */
	public int updateTCommodityInformation(TCommodityInformation tCommodityInformation);
	
	/**
     * 删除商品
     * 
     * @param id 商品ID
     * @return 结果
     */
	public int deleteTCommodityInformationById(String id);
	
	/**
     * 批量删除商品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTCommodityInformationByIds(String[] ids);
	/**
	 * 礼物机存在的商品信息
	 * @param giftId
	 * @return
	 */
	public List<TCommodityInformationVo> getTCommodityInformationVosByGiftId(String giftId);
	
}