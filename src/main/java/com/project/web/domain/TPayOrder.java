package com.project.web.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 支付表 t_pay_order
 * 
 * @author lws
 * @date 2019-03-15
 */
public class TPayOrder extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String id;
	/** 商品id */
	private String orderid;
	/** 用户id */
	private String userid;
	/** 订单号 */
	private String orderno;
	/** 支付状态0待支付1支付成功2支付失败 */
	private String state;
	/** 生成订单时间 */
	private Date createDate;
	/** 1支付宝2微信 */
	private String type;
	/**  */
	private String content;
	/** 0礼物机支付1抽奖支付 */
	private String payType;
	/** 第三方交易号 */
	private String tradeno;
	/** 交易金额 */
	private Long money;

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setOrderid(String orderid) 
	{
		this.orderid = orderid;
	}

	public String getOrderid() 
	{
		return orderid;
	}
	public void setUserid(String userid) 
	{
		this.userid = userid;
	}

	public String getUserid() 
	{
		return userid;
	}
	public void setOrderno(String orderno) 
	{
		this.orderno = orderno;
	}

	public String getOrderno() 
	{
		return orderno;
	}
	public void setState(String state) 
	{
		this.state = state;
	}

	public String getState() 
	{
		return state;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setPayType(String payType) 
	{
		this.payType = payType;
	}

	public String getPayType() 
	{
		return payType;
	}
	public void setTradeno(String tradeno) 
	{
		this.tradeno = tradeno;
	}

	public String getTradeno() 
	{
		return tradeno;
	}
	public void setMoney(Long money) 
	{
		this.money = money;
	}

	public Long getMoney() 
	{
		return money;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderid", getOrderid())
            .append("userid", getUserid())
            .append("orderno", getOrderno())
            .append("state", getState())
            .append("createDate", getCreateDate())
            .append("type", getType())
            .append("content", getContent())
            .append("payType", getPayType())
            .append("tradeno", getTradeno())
            .append("money", getMoney())
            .toString();
    }
}
