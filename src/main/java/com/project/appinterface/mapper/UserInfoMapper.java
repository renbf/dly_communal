package com.project.appinterface.mapper;

import com.project.appinterface.domain.UserInfo;

import java.util.List;

/**
 * 用户 数据层
 * 
 * @author lws
 * @date 2019-03-04
 */
public interface UserInfoMapper 
{
	/**
     * 查询用户信息
     *
     * @param userInfo 用户ID
     * @return 用户信息
     */
	public UserInfo selectUserInfoById(UserInfo userInfo);
	/**
	 * 修改用户
	 *
	 * @param userInfo 用户信息
	 * @return 结果
	 */
	public int updateUserInfo(UserInfo userInfo);
	/**
	 * 查询用户列表
	 *
	 * @param userInfo 用户信息
	 * @return 用户集合
	 */
	public List<UserInfo> selectUserInfoList(UserInfo userInfo);

	/**
	 * 新增用户
	 *
	 * @param userInfo 用户信息
	 * @return 结果
	 */
	public int insertUserInfo(UserInfo userInfo);
	/**
	 * 查询用户信息
	 *
	 * @param id 用户ID
	 * @return 用户信息
	 */
	public UserInfo selectUserInfoByUserId(String id);
	/**
	 * 删除用户
	 *
	 * @param id 用户ID
	 * @return 结果
	 */
	public int deleteUserInfoById(String id);

	/**
	 * 批量删除用户
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteUserInfoByIds(String[] ids);
}