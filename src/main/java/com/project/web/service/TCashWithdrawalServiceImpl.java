package com.project.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.appinterface.util.AliPayUtil;
import com.project.appinterface.util.OrderNo;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.project.common.support.Convert;
import com.project.web.domain.TCashWithdrawal;
import com.project.web.domain.vo.TCashWithdrawalVo;
import com.project.web.mapper.TCashWithdrawalMapper;

/**
 * 提现申请 服务层实现
 * 
 * @author lws
 * @date 2019-03-12
 */
@Service
public class TCashWithdrawalServiceImpl implements ITCashWithdrawalService 
{
	@Autowired
	private TCashWithdrawalMapper tCashWithdrawalMapper;

	/**
     * 查询提现申请信息
     * 
     * @param id 提现申请ID
     * @return 提现申请信息
     */
    @Override
	public TCashWithdrawal selectTCashWithdrawalById(String id)
	{
	    return tCashWithdrawalMapper.selectTCashWithdrawalById(id);
	}
	
	/**
     * 查询提现申请列表
     * 
     * @param tCashWithdrawal 提现申请信息
     * @return 提现申请集合
     */
	@Override
	public List<TCashWithdrawalVo> selectTCashWithdrawalList(TCashWithdrawal tCashWithdrawal)
	{
	    return tCashWithdrawalMapper.selectTCashWithdrawalList(tCashWithdrawal);
	}
	
    /**
     * 新增提现申请
     * 
     * @param tCashWithdrawal 提现申请信息
     * @return 结果
     */
	@Override
	public int insertTCashWithdrawal(TCashWithdrawal tCashWithdrawal)
	{
	    return tCashWithdrawalMapper.insertTCashWithdrawal(tCashWithdrawal);
	}
	
	/**
     * 修改提现申请
     * 
     * @param tCashWithdrawal 提现申请信息
     * @return 结果
     */
	@Override
	public DataResult updateTCashWithdrawal(TCashWithdrawal tCashWithdrawal)
	{
		DataResult result=new DataResult();
		try {
			TCashWithdrawal cashWithdrawal = tCashWithdrawalMapper.selectTCashWithdrawalById(tCashWithdrawal.getId());
			//同意
			if ("1".equals(tCashWithdrawal.getState())) {
//				String outBizNo = String.valueOf(OrderNo.getOrderNo());
//				String amount = String.valueOf(cashWithdrawal.getMoney());
//				result = AliPayUtil.fundTransToaccountTransfer(outBizNo, cashWithdrawal.getAccount(), amount, "备注");
//				if (Result.SUCCESS.equals(result.getStatus())) {
//					tCashWithdrawalMapper.updateTCashWithdrawal(tCashWithdrawal);
//					result.setMessage("提现申请成功");
//					result.setStatus(Result.SUCCESS);
//					return result;
//				} else {
//					return result;
//				}
				tCashWithdrawalMapper.updateTCashWithdrawal(tCashWithdrawal);
				result.setMessage("提现申请成功");
				result.setStatus(Result.SUCCESS);
				return result;
			} else {
				tCashWithdrawalMapper.updateTCashWithdrawal(tCashWithdrawal);
				result.setMessage("提现申请拒绝");
				result.setStatus(Result.SUCCESS);
				return result;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("提现申请异常");
		}
	}

	/**
     * 删除提现申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTCashWithdrawalByIds(String ids)
	{
		return tCashWithdrawalMapper.deleteTCashWithdrawalByIds(Convert.toStrArray(ids));
	}
	
}
