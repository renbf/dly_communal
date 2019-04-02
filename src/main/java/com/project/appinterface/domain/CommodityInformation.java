package com.project.appinterface.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 商品表 t_commodity_information
 * 
 * @author lws
 * @date 2019-03-04
 */
public class CommodityInformation extends BaseEntity
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
	/**
	 * 礼物机id
	 */
	private  String gift_id;
	/** 商品数量 **/
	private int num;
	/** 查询所有未抽走商品总数 **/
	private long goodsSumMoney;
	/** 商品类别id **/
	private String goodsTypeId;
	/** 商品类别名称 **/
	private String goodsTypeName;
	
	public long getGoodsSumMoney() {
		return goodsSumMoney;
	}

	public void setGoodsSumMoney(long goodsSumMoney) {
		this.goodsSumMoney = goodsSumMoney;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getGift_id() {
		return gift_id;
	}

	public void setGift_id(String gift_id) {
		this.gift_id = gift_id;
	}

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

    public String getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("price", getPrice())
            .append("model", getModel())
            .append("introduce", getIntroduce())
            .append("picture", getPicture())
            .append("createDate", getCreateDate())
            .append("createUser", getCreateUser())
            .append("goodsTypeId", getGoodsTypeId())
            .append("goodsTypeName", getGoodsTypeName())
            .toString();
    }
}
