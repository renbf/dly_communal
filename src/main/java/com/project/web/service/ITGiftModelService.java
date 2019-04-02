package com.project.web.service;

import com.project.common.result.DataResult;
import com.project.web.domain.TGiftModel;
import java.util.List;

/**
 *  礼物机类型 服务层
 * 
 * @author lws
 * @date 2019-03-12
 */
public interface ITGiftModelService 
{
	/**
     * 查询 礼物机类型信息
     * 
     * @param id  礼物机类型ID
     * @return  礼物机类型信息
     */
	public TGiftModel selectTGiftModelById(String id);
	
	/**
     * 查询 礼物机类型列表
     * 
     * @param tGiftModel  礼物机类型信息
     * @return  礼物机类型集合
     */
	public List<TGiftModel> selectTGiftModelList(TGiftModel tGiftModel);
	
	/**
     * 新增 礼物机类型
     * 
     * @param tGiftModel  礼物机类型信息
     * @return 结果
     */
	public int insertTGiftModel(TGiftModel tGiftModel);
	
	/**
     * 修改 礼物机类型
     * 
     * @param tGiftModel  礼物机类型信息
     * @return 结果
     */
	public int updateTGiftModel(TGiftModel tGiftModel);
		
	/**
     * 删除 礼物机类型信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTGiftModelByIds(String ids);
	
	public DataResult getTGiftModel(TGiftModel tGiftModel);
	
}
