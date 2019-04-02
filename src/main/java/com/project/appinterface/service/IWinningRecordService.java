package com.project.appinterface.service;

import com.project.appinterface.domain.WinningRecord;
import com.project.common.result.DataResult;

import java.util.List;

/**
 * 抽奖纪录 服务层
 * 
 * @author lws
 * @date 2019-03-04
 */
public interface IWinningRecordService 
{
	/**
     * 查询抽奖纪录信息
     * 
     * @param id 抽奖纪录ID
     * @return 抽奖纪录信息
     */
	public WinningRecord selectWinningRecordById(String id);
	
	/**
     * 查询抽奖纪录列表
     * 
     * @return 抽奖纪录集合
     */
	public DataResult selectWinningRecordList();
	
	/**
     * 新增抽奖纪录
     * 
     * @param winningRecord 抽奖纪录信息
     * @return 结果
     */
	public int insertWinningRecord(WinningRecord winningRecord);
	
	/**
     * 修改抽奖纪录
     * 
     * @param winningRecord 抽奖纪录信息
     * @return 结果
     */
	public int updateWinningRecord(WinningRecord winningRecord);
		
	/**
     * 删除抽奖纪录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWinningRecordByIds(String ids);
	
}
