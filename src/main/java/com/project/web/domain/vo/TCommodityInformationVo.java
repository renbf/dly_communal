package com.project.web.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.project.common.base.BaseEntity;

/**
 * 商品表 t_commodity_information
 * 
 * @author lws
 * @date 2019-03-12
 */
public class TCommodityInformationVo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String id;
	/** 商品名称 */
	private String name;
	/** 商品价格 */
	private Long price;
	/** 商品型号 */
	private String model;
	/** 商品介绍 */
	private String introduce;
	/** 商品图片 */
	private String picture;
	/** 创建时间 */
	private Date createDate;
	/** 创建人 */
	private String createUser;
	/** 商品价格总价格 */
	private BigDecimal totalPrice;
	/** 商品数 */
	private Integer number;
	/** 礼物机id */
	private String giftId;
	
	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setPrice(Long price) 
	{
		this.price = price;
	}

	public Long getPrice() 
	{
		return price;
	}
	public void setModel(String model) 
	{
		this.model = model;
	}

	public String getModel() 
	{
		return model;
	}
	public void setIntroduce(String introduce) 
	{
		this.introduce = introduce;
	}

	public String getIntroduce() 
	{
		return introduce;
	}
	public void setPicture(String picture) 
	{
		this.picture = picture;
	}

	public String getPicture() 
	{
		return picture;
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

    public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getGiftId() {
		return giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

}
