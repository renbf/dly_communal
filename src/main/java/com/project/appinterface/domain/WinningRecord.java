package com.project.appinterface.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 抽奖纪录表 t_winning_record
 * 
 * @author lws
 * @date 2019-03-04
 */
public class WinningRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private String id;
	/** 抽奖人 */
	private String userId;
	/** 礼物机id */
	private String giftId;
	/** 中奖商品 */
	private String goodsId;
	/** 中奖时间 */
	private Date createDate;
	/** 状态0中奖1未中奖 */
	private String state;
	/** 消息 **/
	private String message;
	/** 礼物机名称 **/
	private String modelName;
	/** 支付订单号 **/
	private String payOrderNo;
	/** 订单id **/
	private String orderId;
	/** 下标位置 **/
	private Integer index;
	
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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
	public void setGiftId(String giftId) 
	{
		this.giftId = giftId;
	}

	public String getGiftId() 
	{
		return giftId;
	}
	public void setGoodsId(String goodsId) 
	{
		this.goodsId = goodsId;
	}

	public String getGoodsId() 
	{
		return goodsId;
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

    public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("giftId", getGiftId())
            .append("goodsId", getGoodsId())
            .append("createDate", getCreateDate())
            .append("state", getState())
            .append("index", getIndex())
            .append("orderId", getOrderId())
            .append("payOrderNo", getPayOrderNo())
            .toString();
    }
}
