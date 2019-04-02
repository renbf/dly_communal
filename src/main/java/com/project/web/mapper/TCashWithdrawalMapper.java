package com.project.web.mapper;

import java.util.List;

import com.project.web.domain.TCashWithdrawal;
import com.project.web.domain.vo.TCashWithdrawalVo;

/**
 * 提现申请 数据层
 * 
 * @author lws
 * @date 2019-03-12
 */
public interface TCashWithdrawalMapper 
{
	/**
     * 查询提现申请信息
     * 
     * @param id 提现申请ID
     * @return 提现申请信息
     */
	public TCashWithdrawal selectTCashWithdrawalById(String id);
	
	/**
     * 查询提现申请列表
     * 
     * @param tCashWithdrawal 提现申请信息
     * @return 提现申请集合
     */
	public List<TCashWithdrawalVo> selectTCashWithdrawalList(TCashWithdrawal tCashWithdrawal);
	
	/**
     * 新增提现申请
     * 
     * @param tCashWithdrawal 提现申请信息
     * @return 结果
     */
	public int insertTCashWithdrawal(TCashWithdrawal tCashWithdrawal);
	
	/**
     * 修改提现申请
     * 
     * @param tCashWithdrawal 提现申请信息
     * @return 结果
     */
	public int updateTCashWithdrawal(TCashWithdrawal tCashWithdrawal);
	
	/**
     * 删除提现申请
     * 
     * @param id 提现申请ID
     * @return 结果
     */
	public int deleteTCashWithdrawalById(String id);
	
	/**
     * 批量删除提现申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTCashWithdrawalByIds(String[] ids);
	
}