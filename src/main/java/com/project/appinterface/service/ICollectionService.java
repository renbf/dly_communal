package com.project.appinterface.service;


import com.project.appinterface.domain.TCollection;

import java.util.List;

/**
 * 收藏 服务层
 * 
 * @author lws
 * @date 2019-03-07
 */
public interface ICollectionService 
{
	/**
     * 查询收藏信息
     * 
     * @param id 收藏ID
     * @return 收藏信息
     */
	public TCollection selectCollectionById(String id);
	
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
     * 删除收藏信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCollectionByIds(String ids);
	
}
