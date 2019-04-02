package com.project.appinterface.mapper;

import com.project.appinterface.domain.WinningRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 抽奖纪录 数据层
 * 
 * @author lws
 * @date 2019-03-04
 */
public interface WinningRecordMapper 
{
	/**
     * 查询抽奖纪录信息
     * 
     * @param id 抽奖纪录ID
     * @return 抽奖纪录信息
     */
	public WinningRecord selectWinningRecordById(@Param("userId")String id);

	/**
	 * 查询中奖纪录
	 * @return
	 */
	public List<WinningRecord> getWinningRecord();
	/**
     * 查询抽奖纪录列表
     * 
     * @param winningRecord 抽奖纪录信息
     * @return 抽奖纪录集合
     */
	public List<WinningRecord> queryNoPrize(WinningRecord winningRecord);
	
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
     * 删除抽奖纪录
     * 
     * @param id 抽奖纪录ID
     * @return 结果
     */
	public int deleteWinningRecordById(String id);
	
	/**
     * 批量删除抽奖纪录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWinningRecordByIds(String[] ids);
	/**
	 * 查询根据条件
	 * @param winningRecord
	 * @return
	 */
	public List<WinningRecord> selectWinningRecordList(WinningRecord winningRecord);
}