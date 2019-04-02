package com.project.web.domain;

import java.io.Serializable;

public class GiftApplyVo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6833783316506486364L;
	//id
    private String id;
    /***礼物机id**/
    private String giftId;
    //礼物机名称
    private String giftName;
    //礼物机类型
    private String giftModel;
    //位置名称
    private String locationName;
    //商品名称
    private String goodsName;
    //租赁方式
    private String timeType;
    //数量
    private String number;
    //押金
    private long deposit;
    //状态
    private String state;
    //申请人
    private  String userName;
    /** 平台的人员名称 */
    private String sysUserName;
    /** 来源 */
    private String source;
    /** 存在的商品*/
	private String goodsNames;
	/** 存在的商品id*/
	private String goodsIds;
	
    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftModel() {
        return giftModel;
    }

    public void setGiftModel(String giftModel) {
        this.giftModel = giftModel;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getDeposit() {
        return deposit;
    }

    public void setDeposit(long deposit) {
        this.deposit = deposit;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getGiftId() {
		return giftId;
	}

	public String getGoodsNames() {
		return goodsNames;
	}

	public void setGoodsNames(String goodsNames) {
		this.goodsNames = goodsNames;
	}

	public String getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(String goodsIds) {
		this.goodsIds = goodsIds;
	}
    
}
