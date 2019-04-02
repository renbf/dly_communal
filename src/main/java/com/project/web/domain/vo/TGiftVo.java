package com.project.web.domain.vo;

import java.util.Date;

import com.project.common.base.BaseEntity;

/**
 * 礼物机表 t_gift
 * 
 * @author lws
 * @date 2019-03-12
 */
public class TGiftVo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private String id;
	/** 机型0 */
	private String model;
	/** 礼物机图片 */
	private String pictureUrl;
	/** 0可申请1已申请2失效 */
	private String state;
	/** 创建时间 */
	private Date createDate;
	/** 创建人 */
	private String createUser;
	/** 修改时间 */
	private Date updateDate;
	/** 经度 */
	private String lng;
	/** 纬度 */
	private String lat;
	/** 收费类型id */
	private String chargeId;
	/** 所有人id */
	private String userId;
	/** 机器名称 */
	private String modelName;
	/** 位置名称 */
	private String locationName;
	/** 位置名称code */
	private String locationCode;
	/** 来源 */
	private String source;


	/** 租赁类型 */
	private String timeType;
	/** 数量 */
	private Integer number;
	/** 格子单价 */
	private String price;
	/** 使用了多少天 */
	private Integer dayUsed;
	/** 总共多少天 */
	private Integer dayTotalNumber;
	/** 剩余多少天 */
	private Integer dayNumber;
	/** 存在的商品*/
	private String goodsNames;
	/** 存在的商品id*/
	private String goodsIds;
	/** 所有人名称 */
	private String nickname;
	/** 平台userid */
	private String sysUserId;
	/** 平台username */
	private String sysUserName;
	/** 格子单价 */
	private Long latticePrice;
	/** 格子数 */
	private Integer latticeNum;
	/** 押金 */
    private long deposit;
    /** 剩余格子数 */
    private Integer surplusPosition;
    
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}


	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setModel(String model) 
	{
		this.model = model;
	}

	public String getModel() 
	{
		return model;
	}
	public void setPictureUrl(String pictureUrl) 
	{
		this.pictureUrl = pictureUrl;
	}

	public String getPictureUrl() 
	{
		return pictureUrl;
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
	public void setCreateUser(String createUser) 
	{
		this.createUser = createUser;
	}

	public String getCreateUser() 
	{
		return createUser;
	}
	public void setUpdateDate(Date updateDate) 
	{
		this.updateDate = updateDate;
	}

	public Date getUpdateDate() 
	{
		return updateDate;
	}
	public void setLng(String lng) 
	{
		this.lng = lng;
	}

	public String getLng() 
	{
		return lng;
	}
	public void setLat(String lat) 
	{
		this.lat = lat;
	}

	public String getLat() 
	{
		return lat;
	}
	public void setChargeId(String chargeId) 
	{
		this.chargeId = chargeId;
	}

	public String getChargeId() 
	{
		return chargeId;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getUserId() 
	{
		return userId;
	}
	public void setModelName(String modelName) 
	{
		this.modelName = modelName;
	}

	public String getModelName() 
	{
		return modelName;
	}
	public void setLocationName(String locationName) 
	{
		this.locationName = locationName;
	}

	public String getLocationName() 
	{
		return locationName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getDayUsed() {
		return dayUsed;
	}

	public void setDayUsed(Integer dayUsed) {
		this.dayUsed = dayUsed;
	}

	public Integer getDayTotalNumber() {
		return dayTotalNumber;
	}

	public void setDayTotalNumber(Integer dayTotalNumber) {
		this.dayTotalNumber = dayTotalNumber;
	}

	public String getGoodsNames() {
		return goodsNames;
	}

	public void setGoodsNames(String goodsNames) {
		this.goodsNames = goodsNames;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(Integer dayNumber) {
		this.dayNumber = dayNumber;
	}

	public String getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(String goodsIds) {
		this.goodsIds = goodsIds;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public Long getLatticePrice() {
		return latticePrice;
	}

	public void setLatticePrice(Long latticePrice) {
		this.latticePrice = latticePrice;
	}

	public Integer getLatticeNum() {
		return latticeNum;
	}

	public void setLatticeNum(Integer latticeNum) {
		this.latticeNum = latticeNum;
	}

	public long getDeposit() {
		return deposit;
	}

	public void setDeposit(long deposit) {
		this.deposit = deposit;
	}

	public Integer getSurplusPosition() {
		return surplusPosition;
	}

	public void setSurplusPosition(Integer surplusPosition) {
		this.surplusPosition = surplusPosition;
	}
	
}
