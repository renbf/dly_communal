package com.project.web.service;

import com.project.web.domain.TWallet;

import java.util.List;

/**
 * 用户钱包 服务层
 * 
 * @author lws
 * @date 2019-03-15
 */
public interface ITWalletService 
{
	/**
     * 查询用户钱包信息
     * 
     * @param id 用户钱包ID
     * @return 用户钱包信息
     */
	public TWallet selectTWalletById(String id);

	/**
     * 查询用户钱包信息
     *
     * @param userId 用户ID
     * @return 用户押金信息
     */
	public TWallet selectTWalletByUserId(String userId);

	/**
     * 查询用户钱包列表
     * 
     * @param tWallet 用户钱包信息
     * @return 用户钱包集合
     */
	public List<TWallet> selectTWalletList(TWallet tWallet);
	
	/**
     * 新增用户钱包
     * 
     * @param tWallet 用户钱包信息
     * @return 结果
     */
	public int insertTWallet(TWallet tWallet);
	
	/**
     * 修改用户钱包
     * 
     * @param tWallet 用户钱包信息
     * @return 结果
     */
	public int updateTWallet(TWallet tWallet);
		
	/**
     * 删除用户钱包信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTWalletByIds(String ids);
	
}
