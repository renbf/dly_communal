package com.project.appinterface.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 礼物机申请表 t_gift_apply
 * 
 * @author lws
 * @date 2019-03-05
 */
public class GiftApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private String id;
	/** 礼物机id */
	private String giftId;
	/** 申请人id */
	private String userId;
	/** 租机时间类型0天1月 */
	private String timeType;
	/** 数量 */
	private Integer number;
	/** 申请时间 */
	private Date createDate;
	/** 申请状态0申请中1已申请2已拒绝3待支付 */
	private String state;
	/** 格子单价 */
	private Long latticePrice;
	/** 礼物机名称 */
	private String introduce;
	//订单号
	private String orderno;
	/** 礼物机类型id */
	private String giftModelId;
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setGiftId(String giftId) 
	{
		this.giftId = giftId;
	}

	public String getGiftId() 
	{
		return giftId;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getUserId() 
	{
		return userId;
	}
	public void setTimeType(String timeType) 
	{
		this.timeType = timeType;
	}

	public String getTimeType() 
	{
		return timeType;
	}
	public void setNumber(Integer number) 
	{
		this.number = number;
	}

	public Integer getNumber() 
	{
		return number;
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
	public void setLatticePrice(Long latticePrice) 
	{
		this.latticePrice = latticePrice;
	}

	public Long getLatticePrice() 
	{
		return latticePrice;
	}
	public void setIntroduce(String introduce) 
	{
		this.introduce = introduce;
	}

	public String getIntroduce() 
	{
		return introduce;
	}

    public String getGiftModelId() {
		return giftModelId;
	}

	public void setGiftModelId(String giftModelId) {
		this.giftModelId = giftModelId;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("giftId", getGiftId())
            .append("userId", getUserId())
            .append("timeType", getTimeType())
            .append("number", getNumber())
            .append("createDate", getCreateDate())
            .append("state", getState())
            .append("latticePrice", getLatticePrice())
            .append("introduce", getIntroduce())
            .toString();
    }
}
