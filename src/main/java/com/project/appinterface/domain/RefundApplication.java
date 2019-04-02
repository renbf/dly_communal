package com.project.appinterface.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 退款申请表 t_refund_application
 * 
 * @author lws
 * @date 2019-03-07
 */
public class RefundApplication extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private String id;
	/**  */
	private String userId;
	/** 退款金额 */
	private Long money;
	/** 申请时间 */
	private Date createDate;
	/** 状态0申请中1申请通过2申请拒绝 */
	private String state;

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getUserId() 
	{
		return userId;
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
	public void setState(String state) 
	{
		this.state = state;
	}

	public String getState() 
	{
		return state;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("money", getMoney())
            .append("createDate", getCreateDate())
            .append("state", getState())
            .toString();
    }
}
