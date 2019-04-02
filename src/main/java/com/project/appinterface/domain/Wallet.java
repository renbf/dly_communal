package com.project.appinterface.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户钱包表 t_wallet
 * 
 * @author lws
 * @date 2019-03-05
 */
public class Wallet extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private String id;
	/** 收益 */
	private Long profit;
	/** 余额 */
	private Long balance;
	/** 押金 */
	private Long deposit;
	/** 用户id */
	private String userId;

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setProfit(Long profit) 
	{
		this.profit = profit;
	}

	public Long getProfit() 
	{
		return profit;
	}
	public void setBalance(Long balance) 
	{
		this.balance = balance;
	}

	public Long getBalance() 
	{
		return balance;
	}
	public void setDeposit(Long deposit) 
	{
		this.deposit = deposit;
	}

	public Long getDeposit() 
	{
		return deposit;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getUserId() 
	{
		return userId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("profit", getProfit())
            .append("balance", getBalance())
            .append("deposit", getDeposit())
            .append("userId", getUserId())
            .toString();
    }
}
