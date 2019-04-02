package com.project.appinterface.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 礼物机表 t_gift
 *
 * @author lws
 * @date 2019-03-04
 */
public class Gift extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 机型0
	 */
	private String model;
	/**
	 * 礼物机图片
	 */
	private String pictureUrl;
	/**
	 * 0可申请1已申请2失效
	 */
	private String state;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 修改时间
	 */
	private Date updateDate;
	/**
	 * 经度
	 */
	private String lng;
	/**
	 * 纬度
	 */
	private String lat;
	/**
	 * 收费类型id
	 */
	private String chargeId;
	/**
	 * 所有人id
	 */
	private String userId;
	/**
	 * 机器名称
	 */
	private String modelName;
	/**
	 *  格子价格
	 */
	private Long lattice_price;
	/**   位置名称**/
	private String locationName;
	/**   总位置**/
	private int totalPosition;
	/**   剩余位置**/
	private int surplusPosition;
	/** 起始时间**/
	private Date lcreateDate;
	/** 总天数 */
	private Integer dayTotalNumber;
	/**是否可申请*/
	private Integer isApply;
	/**
	 * 用户id
	 */
	private String user_id;
	/**礼物机类型图片*/
	private String modelPicture;
	
	public int getTotalPosition() {
		return totalPosition;
	}

	public void setTotalPosition(int totalPosition) {
		this.totalPosition = totalPosition;
	}

	public int getSurplusPosition() {
		return surplusPosition;
	}

	public void setSurplusPosition(int surplusPosition) {
		this.surplusPosition = surplusPosition;
	}

	public Date getLcreateDate() {
		return lcreateDate;
	}

	public void setLcreateDate(Date lcreateDate) {
		this.lcreateDate = lcreateDate;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Long getLattice_price() {
		return lattice_price;
	}

	public void setLattice_price(Long lattice_price) {
		this.lattice_price = lattice_price;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModel() {
		return model;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLng() {
		return lng;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLat() {
		return lat;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelName() {
		return modelName;
	}

	public Integer getDayTotalNumber() {
		return dayTotalNumber;
	}

	public void setDayTotalNumber(Integer dayTotalNumber) {
		this.dayTotalNumber = dayTotalNumber;
	}

	public Integer getIsApply() {
		return isApply;
	}

	public void setIsApply(Integer isApply) {
		this.isApply = isApply;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getModelPicture() {
		return modelPicture;
	}

	public void setModelPicture(String modelPicture) {
		this.modelPicture = modelPicture;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("model", getModel())
				.append("pictureUrl", getPictureUrl())
				.append("state", getState())
				.append("createDate", getCreateDate())
				.append("createUser", getCreateUser())
				.append("updateDate", getUpdateDate())
				.append("lng", getLng())
				.append("lat", getLat())
				.append("chargeId", getChargeId())
				.append("userId", getUserId())
				.append("modelName", getModelName())
				.append("dayTotalNumber", getDayTotalNumber())
				.toString();
	}
}