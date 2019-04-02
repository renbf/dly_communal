package com.project.web.service;

import com.project.web.domain.TUserInfo;
import java.util.List;
import java.util.Map;

/**
 * 用户 服务层
 * 
 * @author lws
 * @date 2019-03-08
 */
public interface ITUserInfoService 
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
     * 删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTUserInfoByIds(String ids);
	/**
	 * 注册用户统计
	 * @return
	 */
	public Map<String,List> reportRegisterCount();
}
