package com.project.appinterface.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.appinterface.mapper.WinningRecordMapper;
import com.project.appinterface.domain.WinningRecord;
import com.project.appinterface.service.IWinningRecordService;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
import org.apache.velocity.util.ArrayListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.common.support.Convert;

/**
 * 抽奖纪录 服务层实现
 * 
 * @author lws
 * @date 2019-03-04
 */
@Service
public class WinningRecordServiceImpl implements IWinningRecordService
{
	@Autowired
	private WinningRecordMapper winningRecordMapper;

	/**
     * 查询抽奖纪录信息
     * 
     * @param id 抽奖纪录ID
     * @return 抽奖纪录信息
     */
    @Override
	public WinningRecord selectWinningRecordById(String id)
	{
	    return winningRecordMapper.selectWinningRecordById(id);
	}
	
	/**
     * 查询抽奖纪录列表
     * 
     * @return 抽奖纪录集合
     */
	@Override
	public DataResult selectWinningRecordList()
	{
		DataResult result =new DataResult();
		List<WinningRecord> wlist=winningRecordMapper.getWinningRecord();
		List<Map<String,Object>> rlist=new ArrayList<>();
		for(WinningRecord wr:wlist){
			if(wr != null){
				Map<String,Object> map=new HashMap<>();
				map.put("message",wr.getMessage());
				rlist.add(map);
			}
		}
		result.setResult(rlist);
		result.setStatus(Result.SUCCESS);
		result.setMessage("查询成功");
	    return result;
	}
	
    /**
     * 新增抽奖纪录
     * 
     * @param winningRecord 抽奖纪录信息
     * @return 结果
     */
	@Override
	public int insertWinningRecord(WinningRecord winningRecord)
	{
	    return winningRecordMapper.insertWinningRecord(winningRecord);
	}
	
	/**
     * 修改抽奖纪录
     * 
     * @param winningRecord 抽奖纪录信息
     * @return 结果
     */
	@Override
	public int updateWinningRecord(WinningRecord winningRecord)
	{
	    return winningRecordMapper.updateWinningRecord(winningRecord);
	}

	/**
     * 删除抽奖纪录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWinningRecordByIds(String ids)
	{
		return winningRecordMapper.deleteWinningRecordByIds(Convert.toStrArray(ids));
	}
	
}
