package com.project.appinterface.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 优惠券表 t_coupon
 * 
 * @author lws
 * @date 2019-03-07
 */
public class Coupon extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String id;
	/** 优惠券名称 */
	private String name;
	/** 优惠券价值 */
	private Long money;
	/** 发布时间 */
	private Date createDate;
	/** 创建人 */
	private String createUser;
	/** 状态0有效1失效 */
	private String state;
	/** 有效起始时间 */
	private Date effectiveStart;
	/** 有效截止时间 */
	private Date effectiveEnd;

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setMoney(Long money) 
	{
		this.money = money;
	}

	public Long getMoney() 
	{
		return money;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setCreateUser(String createUser) 
	{
		this.createUser = createUser;
	}

	public String getCreateUser() 
	{
		return createUser;
	}
	public void setState(String state) 
	{
		this.state = state;
	}

	public String getState() 
	{
		return state;
	}
	public void setEffectiveStart(Date effectiveStart) 
	{
		this.effectiveStart = effectiveStart;
	}

	public Date getEffectiveStart() 
	{
		return effectiveStart;
	}
	public void setEffectiveEnd(Date effectiveEnd) 
	{
		this.effectiveEnd = effectiveEnd;
	}

	public Date getEffectiveEnd() 
	{
		return effectiveEnd;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("money", getMoney())
            .append("createDate", getCreateDate())
            .append("createUser", getCreateUser())
            .append("state", getState())
            .append("effectiveStart", getEffectiveStart())
            .append("effectiveEnd", getEffectiveEnd())
            .toString();
    }
}
