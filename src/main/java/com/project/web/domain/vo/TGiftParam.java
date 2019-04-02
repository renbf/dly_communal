package com.project.web.domain.vo;

import com.project.common.base.BaseEntity;

public class TGiftParam extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3026979420878628537L;

	/***礼物机id**/
    private String giftId;
	/** 机型0 */
	private String model;
	/** 机器名称 */
	private String modelName;
	/** 位置名称 */
	private String locationName;
	/** 租赁方式*/
    private String timeType;
    /** 数量*/
    private Integer number;
    /** 格子单价*/
    private Long latticePrice;
    /** 商品id*/
    private String[] goodsId;
    /** 商品数量*/
    private int[] goodsNumber;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public String[] getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String[] goodsId) {
		this.goodsId = goodsId;
	}
	public String getGiftId() {
		return giftId;
	}
	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public int[] getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(int[] goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	public Long getLatticePrice() {
		return latticePrice;
	}
	public void setLatticePrice(Long latticePrice) {
		this.latticePrice = latticePrice;
	}
	
}
