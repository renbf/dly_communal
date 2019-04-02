package com.project.appinterface.service.impl;

import java.util.List;

import com.project.appinterface.domain.TCollection;
import com.project.appinterface.mapper.CollectionMapper;
import com.project.appinterface.service.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.common.support.Convert;

/**
 * 收藏 服务层实现
 * 
 * @author lws
 * @date 2019-03-07
 */
@Service
public class CollectionServiceImpl implements ICollectionService
{
	@Autowired
	private CollectionMapper collectionMapper;

	/**
     * 查询收藏信息
     * 
     * @param id 收藏ID
     * @return 收藏信息
     */
    @Override
	public TCollection selectCollectionById(String id)
	{
	    return collectionMapper.selectCollectionById(null);
	}
	
	/**
     * 查询收藏列表
     * 
     * @param collection 收藏信息
     * @return 收藏集合
     */
	@Override
	public List<TCollection> selectCollectionList(TCollection collection)
	{
	    return collectionMapper.selectCollectionList(collection);
	}
	
    /**
     * 新增收藏
     * 
     * @param collection 收藏信息
     * @return 结果
     */
	@Override
	public int insertCollection(TCollection collection)
	{
	    return collectionMapper.insertCollection(collection);
	}
	
	/**
     * 修改收藏
     * 
     * @param collection 收藏信息
     * @return 结果
     */
	@Override
	public int updateCollection(TCollection collection)
	{
	    return collectionMapper.updateCollection(collection);
	}

	/**
     * 删除收藏对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCollectionByIds(String ids)
	{
		return collectionMapper.deleteCollectionByIds(Convert.toStrArray(ids));
	}
	
}
