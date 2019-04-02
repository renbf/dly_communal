package com.project.web.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 礼物机表 t_gift
 * 
 * @author lws
 * @date 2019-03-12
 */
public class TGift extends BaseEntity
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
	/** 来源 */
	private String source;


	/** 剩余天数 */
	private String timeType;
	/** 格子单价 */
	private String price;

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
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
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
            .append("locationName", getLocationName())
            .append("source", getSource())
            .append("timeType", getTimeType())
            .append("price", getPrice())
            .toString();
    }
}
