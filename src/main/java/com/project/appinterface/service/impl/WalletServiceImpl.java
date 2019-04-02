package com.project.appinterface.service.impl;

import java.util.List;

import com.project.appinterface.domain.Wallet;
import com.project.appinterface.mapper.WalletMapper;
import com.project.appinterface.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.common.support.Convert;

/**
 * 用户钱包 服务层实现
 * 
 * @author lws
 * @date 2019-03-05
 */
@Service
public class WalletServiceImpl implements IWalletService
{
	@Autowired
	private WalletMapper walletMapper;

	/**
     * 查询用户钱包信息
     * 
     * @param id 用户钱包ID
     * @return 用户钱包信息
     */
    @Override
	public Wallet selectWalletById(String id)
	{
	    return walletMapper.selectWalletById(id);
	}
	
	/**
     * 查询用户钱包列表
     * 
     * @param wallet 用户钱包信息
     * @return 用户钱包集合
     */
	@Override
	public List<Wallet> selectWalletList(Wallet wallet)
	{
	    return walletMapper.selectWalletList(wallet);
	}
	
    /**
     * 新增用户钱包
     * 
     * @param wallet 用户钱包信息
     * @return 结果
     */
	@Override
	public int insertWallet(Wallet wallet)
	{
	    return walletMapper.insertWallet(wallet);
	}
	
	/**
     * 修改用户钱包
     * 
     * @param wallet 用户钱包信息
     * @return 结果
     */
	@Override
	public int updateWallet(Wallet wallet)
	{
	    return walletMapper.updateWallet(wallet);
	}

	/**
     * 删除用户钱包对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWalletByIds(String ids)
	{
		return walletMapper.deleteWalletByIds(Convert.toStrArray(ids));
	}
	
}
