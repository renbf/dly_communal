package com.project.web.domain.vo;

import java.util.Date;

import com.project.common.base.BaseEntity;

/**
 * 提现申请表 t_cash_withdrawal
 * 
 * @author lws
 * @date 2019-03-12
 */
public class TCashWithdrawalVo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String id;
	/** 提现金额 */
	private Long money;
	/** 提现方式0支付宝1微信 */
	private String cashType;
	/** 申请人id */
	private String applicantUser;
	/** 申请人名称 */
	private String applicantUserName;
	/** 申请时间 */
	private Date applicantDate;
	/** 状态0申请中1已同意2已拒绝 */
	private String state;
	/** 账户 */
	private String account;
	/** 扩展 */
	private String extend;
	
	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setMoney(Long money) 
	{
		this.money = money;
	}

	public Long getMoney() 
	{
		return money;
	}
	public void setCashType(String cashType) 
	{
		this.cashType = cashType;
	}

	public String getCashType() 
	{
		return cashType;
	}
	
	public String getApplicantUser() {
		return applicantUser;
	}

	public void setApplicantUser(String applicantUser) {
		this.applicantUser = applicantUser;
	}

	public String getApplicantUserName() {
		return applicantUserName;
	}

	public void setApplicantUserName(String applicantUserName) {
		this.applicantUserName = applicantUserName;
	}

	public void setApplicantDate(Date applicantDate) 
	{
		this.applicantDate = applicantDate;
	}

	public Date getApplicantDate() 
	{
		return applicantDate;
	}
	public void setState(String state) 
	{
		this.state = state;
	}

	public String getState() 
	{
		return state;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	@Override
	public String toString() {
		return "TCashWithdrawalVo [id=" + id + ", money=" + money + ", cashType=" + cashType + ", applicantUser="
				+ applicantUser + ", applicantUserName=" + applicantUserName + ", applicantDate=" + applicantDate
				+ ", state=" + state + ", account=" + account + ", extend=" + extend + "]";
	}

}
