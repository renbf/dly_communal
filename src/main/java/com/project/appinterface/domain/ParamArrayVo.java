package com.project.appinterface.domain;

import java.io.Serializable;
import java.util.List;

public class ParamArrayVo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4954078865502056609L;
	//礼物机id
    private String  giftId;
    //礼物机名称
    private String giftName;
    //礼物机类型
    private String giftModel;
    //格子单价
    private String univalent;
    //商品信息
    private List<GoodsVo> goodsList;
    //租机方式0天1月
    private String timeType;
    //数量
    private int number;
    // 支付方式0支付宝1微信
    private String payType;
    //支付金额
    private String payMoney;
    //申请人id
    private String userId;
    //0礼物机支付1抽奖支付
    private String type;
    //礼物机位置名称
    private String locationName;
    //商品说明
    private String introduce;
    //优惠券id
    private String couponId;
    //格子下标 多个以逗号拼接
    private  String indexs;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 纬度
     */
    private Double latitude;
    /**订单号*/
    private String orderNo;
    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getIndexs() {
        return indexs;
    }

    public void setIndexs(String indexs) {
        this.indexs = indexs;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
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

    public String getUnivalent() {
        return univalent;
    }

    public void setUnivalent(String univalent) {
        this.univalent = univalent;
    }

    public List<GoodsVo> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsVo> goodsList) {
        this.goodsList = goodsList;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
    
}
