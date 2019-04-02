package com.project.web.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.project.common.support.Convert;
import com.project.framework.util.ShiroUtils;
import com.project.util.UUIDUtil;
import com.project.web.domain.TGoodsType;
import com.project.web.mapper.TGoodsTypeMapper;

/**
 * 商品类型 服务层实现
 * 
 * @author lws
 * @date 2019-04-01
 */
@Service
public class TGoodsTypeServiceImpl implements ITGoodsTypeService 
{
	private static final Logger log = LoggerFactory.getLogger(TGoodsTypeServiceImpl.class);
	
	@Autowired
	private TGoodsTypeMapper tGoodsTypeMapper;

	/**
     * 查询商品类型信息
     * 
     * @param id 商品类型ID
     * @return 商品类型信息
     */
    @Override
	public TGoodsType selectTGoodsTypeById(String id)
	{
	    return tGoodsTypeMapper.selectTGoodsTypeById(id);
	}
	
	/**
     * 查询商品类型列表
     * 
     * @param tGoodsType 商品类型信息
     * @return 商品类型集合
     */
	@Override
	public List<TGoodsType> selectTGoodsTypeList(TGoodsType tGoodsType)
	{
	    return tGoodsTypeMapper.selectTGoodsTypeList(tGoodsType);
	}
	
    /**
     * 新增商品类型
     * 
     * @param tGoodsType 商品类型信息
     * @return 结果
     */
	@Override
	public int insertTGoodsType(TGoodsType tGoodsType)
	{
		tGoodsType.setId(UUIDUtil.getUUID());
		tGoodsType.setCreateUser(ShiroUtils.getUserId().toString());
		tGoodsType.setCreateDate(new Date());
	    return tGoodsTypeMapper.insertTGoodsType(tGoodsType);
	}
	
	/**
     * 修改商品类型
     * 
     * @param tGoodsType 商品类型信息
     * @return 结果
     */
	@Override
	public int updateTGoodsType(TGoodsType tGoodsType)
	{
		tGoodsType.setUpdateDate(new Date());
	    return tGoodsTypeMapper.updateTGoodsType(tGoodsType);
	}

	/**
     * 删除商品类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTGoodsTypeByIds(String ids)
	{
		return tGoodsTypeMapper.deleteTGoodsTypeByIds(Convert.toStrArray(ids));
	}

	@Override
	public DataResult getTGoodsTypes() {
		DataResult result=new DataResult();
		try {
			List<TGoodsType> tGoodsTypeList = tGoodsTypeMapper.selectTGoodsTypeList(null);
			result.setResult(tGoodsTypeList);
			result.setMessage("查询成功");
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			result.setMessage("查询失败");
			result.setStatus(Result.FAILED);
			log.error("查询失败", e);
		}
		return result;
	}
	
}
