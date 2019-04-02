package com.project.web.mapper;

import java.util.List;
import java.util.Map;

import com.project.web.domain.TUserInfo;

/**
 * 用户 数据层
 * 
 * @author lws
 * @date 2019-03-08
 */
public interface TUserInfoMapper 
{
	/**
     * 查询用户信息
     * 
     * @param id 用户ID
     * @return 用户信息
     */
	public TUserInfo selectTUserInfoById(String id);
	
	/**
     * 查询用户列表
     * 
     * @param tUserInfo 用户信息
     * @return 用户集合
     */
	public List<TUserInfo> selectTUserInfoList(TUserInfo tUserInfo);
	
	/**
     * 新增用户
     * 
     * @param tUserInfo 用户信息
     * @return 结果
     */
	public int insertTUserInfo(TUserInfo tUserInfo);
	
	/**
     * 修改用户
     * 
     * @param tUserInfo 用户信息
     * @return 结果
     */
	public int updateTUserInfo(TUserInfo tUserInfo);
	
	/**
     * 删除用户
     * 
     * @param id 用户ID
     * @return 结果
     */
	public int deleteTUserInfoById(String id);
	
	/**
     * 批量删除用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTUserInfoByIds(String[] ids);
	/**
	 * 注册用户统计
	 * @return
	 */
	public List<Map<String,Object>> reportRegisterCount();
	
}