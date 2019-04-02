package com.project.appinterface.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 礼物机-礼物中间表 t_gift_goods
 * 
 * @author lws
 * @date 2019-03-04
 */
public class GiftGoods extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private String id;
	/** 机器id */
	private String giftId;
	/** 商品id */
	private String goodsId;
	/** 状态0未抽走1一抽走 */
	private String state;
	/** 申请表id */
	private String giftApplyId;
	/** 商品价格 */
	private Long price;
	
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
	public void setGoodsId(String goodsId) 
	{
		this.goodsId = goodsId;
	}

	public String getGoodsId() 
	{
		return goodsId;
	}
	public void setState(String state) 
	{
		this.state = state;
	}

	public String getState() 
	{
		return state;
	}

    public String getGiftApplyId() {
		return giftApplyId;
	}

	public void setGiftApplyId(String giftApplyId) {
		this.giftApplyId = giftApplyId;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("giftId", getGiftId())
            .append("goodsId", getGoodsId())
            .append("state", getState())
            .append("giftApplyId", getGiftApplyId())
            .toString();
    }
}
