package com.project.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.support.Convert;
import com.project.web.domain.TUserInfo;
import com.project.web.mapper.TUserInfoMapper;

/**
 * 用户 服务层实现
 * 
 * @author lws
 * @date 2019-03-08
 */
@Service
public class TUserInfoServiceImpl implements ITUserInfoService
{
	@Autowired
	private TUserInfoMapper tUserInfoMapper;

	/**
     * 查询用户信息
     * 
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
	public TUserInfo selectTUserInfoById(String id)
	{
	    return tUserInfoMapper.selectTUserInfoById(id);
	}
	
	/**
     * 查询用户列表
     * 
     * @param tUserInfo 用户信息
     * @return 用户集合
     */
	@Override
	public List<TUserInfo> selectTUserInfoList(TUserInfo tUserInfo)
	{
	    return tUserInfoMapper.selectTUserInfoList(tUserInfo);
	}
	
    /**
     * 新增用户
     * 
     * @param tUserInfo 用户信息
     * @return 结果
     */
	@Override
	public int insertTUserInfo(TUserInfo tUserInfo)
	{
	    return tUserInfoMapper.insertTUserInfo(tUserInfo);
	}
	
	/**
     * 修改用户
     * 
     * @param tUserInfo 用户信息
     * @return 结果
     */
	@Override
	public int updateTUserInfo(TUserInfo tUserInfo)
	{
	    return tUserInfoMapper.updateTUserInfo(tUserInfo);
	}

	/**
     * 删除用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTUserInfoByIds(String ids)
	{
		return tUserInfoMapper.deleteTUserInfoByIds(Convert.toStrArray(ids));
	}

	@Override
	public Map<String,List> reportRegisterCount() {
		Map<String,List> resultMap = new HashMap<>();
		List<Map<String,Object>> list = tUserInfoMapper.reportRegisterCount();
		List<String> xList = new ArrayList<>();
		List<Integer> yList = new ArrayList<>();
		for(Map<String,Object> map:list){
			xList.add(map.get("date").toString());
			String num = map.get("num").toString();
			yList.add(Integer.valueOf(num));
		}
		resultMap.put("xList", xList);
		resultMap.put("yList", yList);
		return resultMap;
	}
	
}
