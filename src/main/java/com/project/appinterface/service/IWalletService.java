package com.project.appinterface.service;

import com.project.appinterface.domain.Wallet;

import java.util.List;

/**
 * 用户钱包 服务层
 * 
 * @author lws
 * @date 2019-03-05
 */
public interface IWalletService 
{
	/**
     * 查询用户钱包信息
     * 
     * @param id 用户钱包ID
     * @return 用户钱包信息
     */
	public Wallet selectWalletById(String id);
	
	/**
     * 查询用户钱包列表
     * 
     * @param wallet 用户钱包信息
     * @return 用户钱包集合
     */
	public List<Wallet> selectWalletList(Wallet wallet);
	
	/**
     * 新增用户钱包
     * 
     * @param wallet 用户钱包信息
     * @return 结果
     */
	public int insertWallet(Wallet wallet);
	
	/**
     * 修改用户钱包
     * 
     * @param wallet 用户钱包信息
     * @return 结果
     */
	public int updateWallet(Wallet wallet);
		
	/**
     * 删除用户钱包信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWalletByIds(String ids);
	
}
