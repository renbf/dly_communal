package com.project.web.service;

import java.util.List;

import com.project.common.result.DataResult;
import com.project.web.domain.TCashWithdrawal;
import com.project.web.domain.vo.TCashWithdrawalVo;

/**
 * 提现申请 服务层
 * 
 * @author lws
 * @date 2019-03-12
 */
public interface ITCashWithdrawalService 
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
	public DataResult updateTCashWithdrawal(TCashWithdrawal tCashWithdrawal);
		
	/**
     * 删除提现申请信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTCashWithdrawalByIds(String ids);
	
}
