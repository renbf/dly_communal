package com.project.appinterface.mapper;

import com.project.appinterface.domain.TCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收藏 数据层
 * 
 * @author lws
 * @date 2019-03-07
 */
public interface CollectionMapper 
{
	/**
     * 查询收藏信息
     * 
     * @param id 收藏ID
     * @return 收藏信息
     */
	public TCollection selectCollectionById(TCollection collection);
	
	/**
     * 查询收藏列表
     * 
     * @param collection 收藏信息
     * @return 收藏集合
     */
	public List<TCollection> selectCollectionList(TCollection collection);
	
	/**
     * 新增收藏
     * 
     * @param collection 收藏信息
     * @return 结果
     */
	public int insertCollection(TCollection collection);
	
	/**
     * 修改收藏
     * 
     * @param collection 收藏信息
     * @return 结果
     */
	public int updateCollection(TCollection collection);
	
	/**
     * 删除收藏
     * 
     * @param id 收藏ID
     * @return 结果
     */
	public int deleteCollectionById(@Param("id") String id);
	
	/**
     * 批量删除收藏
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCollectionByIds(String[] ids);
	
}