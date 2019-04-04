package com.project.appinterface.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 消费表 t_consumption_information
 * 
 * @author lws
 * @date 2019-03-08
 */
public class ConsumptionInformation extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private String id;
	/** 消费类型0抽奖1买礼物机2退款 */
	private String consumptionType;
	/** 金额 */
	private Long money;
	/** 0支付宝1微信 */
	private String payType;
	/** 消费人 */
	private String consumptionUser;
	/** 消费时间 */
	private Date consumptionDate;
	/** 状态0支付中1支付成功2支付失败 */
	private String state;
	/** 账户 */
	private String account;
	/** 支付单号 */
	private String extend;
	public void setId(String id)
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setConsumptionType(String consumptionType) 
	{
		this.consumptionType = consumptionType;
	}

	public String getConsumptionType() 
	{
		return consumptionType;
	}
	public void setMoney(Long money) 
	{
		this.money = money;
	}

	public Long getMoney() 
	{
		return money;
	}
	public void setPayType(String payType) 
	{
		this.payType = payType;
	}

	public String getPayType() 
	{
		return payType;
	}
	public void setConsumptionUser(String consumptionUser) 
	{
		this.consumptionUser = consumptionUser;
	}

	public String getConsumptionUser() 
	{
		return consumptionUser;
	}
	public void setConsumptionDate(Date consumptionDate) 
	{
		this.consumptionDate = consumptionDate;
	}

	public Date getConsumptionDate() 
	{
		return consumptionDate;
	}
	public void setState(String state) 
	{
		this.state = state;
	}

	public String getState() 
	{
		return state;
	}

    public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("consumptionType", getConsumptionType())
            .append("money", getMoney())
            .append("payType", getPayType())
            .append("consumptionUser", getConsumptionUser())
            .append("consumptionDate", getConsumptionDate())
            .append("state", getState())
            .toString();
    }
}
