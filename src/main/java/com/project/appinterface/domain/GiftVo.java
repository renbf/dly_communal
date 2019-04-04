package com.project.appinterface.domain;

import com.project.common.base.BaseEntity;

public class GiftVo extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6899439158426433078L;
	/**礼物机id*/
	private String giftId;
	/**用户申请的礼物机名称*/
	private String introduce;
	/**格子单价*/
	private String latticePrice;
	/**格子数*/
	private Integer latticeNum;
	/**剩余格子数*/
	private Integer surplusPosition;
	/**申请的商品名称*/
	private String goodsNames;
	public String getGiftId() {
		return giftId;
	}
	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getLatticePrice() {
		return latticePrice;
	}
	public void setLatticePrice(String latticePrice) {
		this.latticePrice = latticePrice;
	}
	public Integer getLatticeNum() {
		return latticeNum;
	}
	public void setLatticeNum(Integer latticeNum) {
		this.latticeNum = latticeNum;
	}
	public Integer getSurplusPosition() {
		return surplusPosition;
	}
	public void setSurplusPosition(Integer surplusPosition) {
		this.surplusPosition = surplusPosition;
	}
	public String getGoodsNames() {
		return goodsNames;
	}
	public void setGoodsNames(String goodsNames) {
		this.goodsNames = goodsNames;
	}
	
	
}
