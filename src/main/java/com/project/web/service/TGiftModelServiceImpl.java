package com.project.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.web.domain.TGiftModel;
import com.project.web.mapper.TGiftModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.appinterface.domain.SysAreas;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.project.common.support.Convert;

/**
 *  礼物机类型 服务层实现
 * 
 * @author lws
 * @date 2019-03-12
 */
@Service
public class TGiftModelServiceImpl implements ITGiftModelService 
{
	@Autowired
	private TGiftModelMapper tGiftModelMapper;

	/**
     * 查询 礼物机类型信息
     * 
     * @param id  礼物机类型ID
     * @return  礼物机类型信息
     */
    @Override
	public TGiftModel selectTGiftModelById(String id)
	{
	    return tGiftModelMapper.selectTGiftModelById(id);
	}
	
	/**
     * 查询 礼物机类型列表
     * 
     * @param tGiftModel  礼物机类型信息
     * @return  礼物机类型集合
     */
	@Override
	public List<TGiftModel> selectTGiftModelList(TGiftModel tGiftModel)
	{
	    return tGiftModelMapper.selectTGiftModelList(tGiftModel);
	}
	
    /**
     * 新增 礼物机类型
     * 
     * @param tGiftModel  礼物机类型信息
     * @return 结果
     */
	@Override
	public int insertTGiftModel(TGiftModel tGiftModel)
	{
	    return tGiftModelMapper.insertTGiftModel(tGiftModel);
	}
	
	/**
     * 修改 礼物机类型
     * 
     * @param tGiftModel  礼物机类型信息
     * @return 结果
     */
	@Override
	public int updateTGiftModel(TGiftModel tGiftModel)
	{
	    return tGiftModelMapper.updateTGiftModel(tGiftModel);
	}

	/**
     * 删除 礼物机类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTGiftModelByIds(String ids)
	{
		return tGiftModelMapper.deleteTGiftModelByIds(Convert.toStrArray(ids));
	}

	@Override
	public DataResult getTGiftModel(TGiftModel tGiftModel) {
		DataResult result=new DataResult();
		try {
			List<TGiftModel> tGiftModels=tGiftModelMapper.selectTGiftModelList(tGiftModel);
			result.setResult(tGiftModels);
			result.setMessage("查询成功");
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			result.setMessage("查询失败");
			result.setStatus(Result.SUCCESS);
			e.printStackTrace();
		}
		return result;
	}
	
}
