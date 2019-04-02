package com.project.web.mapper;

import java.util.List;

import com.project.web.domain.TGoodsType;	

/**
 * 商品类型 数据层
 * 
 * @author lws
 * @date 2019-04-01
 */
public interface TGoodsTypeMapper 
{
	/**
     * 查询商品类型信息
     * 
     * @param id 商品类型ID
     * @return 商品类型信息
     */
	public TGoodsType selectTGoodsTypeById(String id);
	
	/**
     * 查询商品类型列表
     * 
     * @param tGoodsType 商品类型信息
     * @return 商品类型集合
     */
	public List<TGoodsType> selectTGoodsTypeList(TGoodsType tGoodsType);
	
	/**
     * 新增商品类型
     * 
     * @param tGoodsType 商品类型信息
     * @return 结果
     */
	public int insertTGoodsType(TGoodsType tGoodsType);
	
	/**
     * 修改商品类型
     * 
     * @param tGoodsType 商品类型信息
     * @return 结果
     */
	public int updateTGoodsType(TGoodsType tGoodsType);
	
	/**
     * 删除商品类型
     * 
     * @param id 商品类型ID
     * @return 结果
     */
	public int deleteTGoodsTypeById(String id);
	
	/**
     * 批量删除商品类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTGoodsTypeByIds(String[] ids);
	
}