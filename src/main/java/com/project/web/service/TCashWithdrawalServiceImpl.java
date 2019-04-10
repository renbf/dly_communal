package com.project.web.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.appinterface.domain.ConsumptionInformation;
import com.project.appinterface.domain.Wallet;
import com.project.appinterface.mapper.ConsumptionInformationMapper;
import com.project.appinterface.mapper.WalletMapper;
import com.project.appinterface.util.AliPayUtil;
import com.project.appinterface.util.OrderNo;
import com.project.common.constant.Constants;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.project.common.support.Convert;
import com.project.util.UUIDUtil;
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

	@Autowired
	private ConsumptionInformationMapper consumptionInformationMapper;
	
	@Autowired
	private WalletMapper walletMapper;
	
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
	@Transactional
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
				Wallet wallet = walletMapper.selectWalletByUserId(cashWithdrawal.getApplicantUser());
				String account = null;
				//支付宝
				if("0".equals(cashWithdrawal.getCashType())){
					account = wallet.getAlipayAccount();
					if(StringUtils.isEmpty(account)){
						result.setStatus(Result.FAILED);
						result.setMessage("支付宝账号为空");
						return result;
					}
				}
				Long balance = wallet.getBalance();
				if(balance == null || balance.longValue() == 0) {
					result.setStatus(Result.FAILED);
					result.setMessage("用户已没有余额");
					return result;
				}else {
					if(balance.longValue() < cashWithdrawal.getMoney().longValue()) {
						result.setStatus(Result.FAILED);
						result.setMessage("用户余额不足");
						return result;
					}
				}
				//平台加钱
				Wallet adminWallet = walletMapper.selectWalletById(Constants.ADMIN);
				Long adminBalance = adminWallet.getBalance();
				if(adminBalance.longValue() < cashWithdrawal.getMoney().longValue()) {
					result.setStatus(Result.FAILED);
					result.setMessage("平台没钱了");
					return result;
				}
				adminWallet.setBalance(adminBalance.longValue()-cashWithdrawal.getMoney().longValue());
				walletMapper.updateWallet(adminWallet);
				Wallet walletu = new Wallet();
				walletu.setId(wallet.getId());
				walletu.setBalance(balance.longValue() - cashWithdrawal.getMoney().longValue());
				walletMapper.updateWallet(walletu);
				// 生成退还押金记录
				ConsumptionInformation ci = new ConsumptionInformation();
				ci.setId(UUIDUtil.getUUID());
				ci.setConsumptionType("3");
				ci.setMoney(cashWithdrawal.getMoney());
				ci.setConsumptionUser(cashWithdrawal.getApplicantUser());
				ci.setConsumptionDate(new Date());
				ci.setState("1");
				ci.setPayType(cashWithdrawal.getCashType());
				consumptionInformationMapper.insertConsumptionInformation(ci);
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
