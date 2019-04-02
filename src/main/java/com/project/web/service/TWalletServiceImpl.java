package com.project.web.service;

import java.util.List;

import com.project.web.domain.TWallet;
import com.project.web.mapper.TWalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.common.support.Convert;

/**
 * 用户钱包 服务层实现
 * 
 * @author lws
 * @date 2019-03-15
 */
@Service
public class TWalletServiceImpl implements ITWalletService 
{
	@Autowired
	private TWalletMapper tWalletMapper;

	/**
     * 查询用户钱包信息
     * 
     * @param id 用户钱包ID
     * @return 用户钱包信息
     */
    @Override
	public TWallet selectTWalletById(String id)
	{
	    return tWalletMapper.selectTWalletById(id);
	}

	/**
	 * 查询用户钱包信息
	 *
	 * @param userId 用户ID
	 * @return 用户押金信息
	 */
	@Override
	public TWallet selectTWalletByUserId(String userId) {
		return tWalletMapper.selectTWalletByUserId(userId);
	}

	/**
     * 查询用户钱包列表
     * 
     * @param tWallet 用户钱包信息
     * @return 用户钱包集合
     */
	@Override
	public List<TWallet> selectTWalletList(TWallet tWallet)
	{
	    return tWalletMapper.selectTWalletList(tWallet);
	}
	
    /**
     * 新增用户钱包
     * 
     * @param tWallet 用户钱包信息
     * @return 结果
     */
	@Override
	public int insertTWallet(TWallet tWallet)
	{
	    return tWalletMapper.insertTWallet(tWallet);
	}
	
	/**
     * 修改用户钱包
     * 
     * @param tWallet 用户钱包信息
     * @return 结果
     */
	@Override
	public int updateTWallet(TWallet tWallet)
	{
	    return tWalletMapper.updateTWallet(tWallet);
	}

	/**
     * 删除用户钱包对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTWalletByIds(String ids)
	{
		return tWalletMapper.deleteTWalletByIds(Convert.toStrArray(ids));
	}
	
}
