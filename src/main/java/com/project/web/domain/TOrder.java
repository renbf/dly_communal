package com.project.web.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 订单表 t_order
 * 
 * @author lws
 * @date 2019-03-12
 */
public class TOrder extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String id;
	/** 中奖人 */
	private String userId;
	/** 地址id */
	private String addressId;
	/** 快递编号 */
	private String expressNo;
	/** 订单创建时间 */
	private Date creatDate;
	/** 创建人 */
	private String createUser;
	/** 0待发货1待收货2已完成3已失效4待领取 */
	private String state;
	/** 商品id */
	private String goodsId;
	/** 发货时间 */
	private Date deliveryDate;
	/** 确认收货 */
	private Date completionDate;
	/** 商品名称 **/
	private String goodsName;
	/** 收货人 **/
	private String receiver;
	/** 快递公司编码 **/
	private String companyCode;
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getUserId() 
	{
		return userId;
	}
	public void setAddressId(String addressId) 
	{
		this.addressId = addressId;
	}

	public String getAddressId() 
	{
		return addressId;
	}
	public void setExpressNo(String expressNo) 
	{
		this.expressNo = expressNo;
	}

	public String getExpressNo() 
	{
		return expressNo;
	}
	public void setCreatDate(Date creatDate) 
	{
		this.creatDate = creatDate;
	}

	public Date getCreatDate() 
	{
		return creatDate;
	}
	public void setCreateUser(String createUser) 
	{
		this.createUser = createUser;
	}

	public String getCreateUser() 
	{
		return createUser;
	}
	public void setState(String state) 
	{
		this.state = state;
	}

	public String getState() 
	{
		return state;
	}
	public void setGoodsId(String goodsId) 
	{
		this.goodsId = goodsId;
	}

	public String getGoodsId() 
	{
		return goodsId;
	}
	public void setDeliveryDate(Date deliveryDate) 
	{
		this.deliveryDate = deliveryDate;
	}

	public Date getDeliveryDate() 
	{
		return deliveryDate;
	}
	public void setCompletionDate(Date completionDate) 
	{
		this.completionDate = completionDate;
	}

	public Date getCompletionDate() 
	{
		return completionDate;
	}

    public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("addressId", getAddressId())
            .append("expressNo", getExpressNo())
            .append("creatDate", getCreatDate())
            .append("createUser", getCreateUser())
            .append("state", getState())
            .append("goodsId", getGoodsId())
            .append("deliveryDate", getDeliveryDate())
            .append("completionDate", getCompletionDate())
            .append("companyCode", getCompanyCode())
            .toString();
    }
}
