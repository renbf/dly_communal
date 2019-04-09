package com.project.appinterface.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 礼物位置表 t_gift_location
 * 
 * @author lws
 * @date 2019-03-04
 */
public class GiftLocation extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private String id;
	/** 礼物位置 */
	private String content;
	/** 礼物机id */
	private String giftId;
	/** 创建时间 */
	private Date createDate;
	/** 创建人 */
	private String createUser;
	/** 状态0有效1无效 */
	private String state;
	/** 剩余位置 */
	private Integer surplusPosition;
	/** 总位置 */
	private Integer totalPosition;
	/** 格子单价 */
	private Long latticePrice;
	/** 礼物机名称 **/
	private String giftName;
	/** 申请礼物机id **/
	private String giftApplyId;

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public void setId(String id)
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setGiftId(String giftId) 
	{
		this.giftId = giftId;
	}

	public String getGiftId() 
	{
		return giftId;
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
	public void setState(String state) 
	{
		this.state = state;
	}

	public String getState() 
	{
		return state;
	}
	public void setSurplusPosition(Integer surplusPosition) 
	{
		this.surplusPosition = surplusPosition;
	}

	public Integer getSurplusPosition() 
	{
		return surplusPosition;
	}
	public void setTotalPosition(Integer totalPosition) 
	{
		this.totalPosition = totalPosition;
	}

	public Integer getTotalPosition() 
	{
		return totalPosition;
	}
	public void setLatticePrice(Long latticePrice) 
	{
		this.latticePrice = latticePrice;
	}

	public Long getLatticePrice() 
	{
		return latticePrice;
	}

    public String getGiftApplyId() {
		return giftApplyId;
	}

	public void setGiftApplyId(String giftApplyId) {
		this.giftApplyId = giftApplyId;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("content", getContent())
            .append("giftId", getGiftId())
            .append("createDate", getCreateDate())
            .append("createUser", getCreateUser())
            .append("state", getState())
            .append("surplusPosition", getSurplusPosition())
            .append("totalPosition", getTotalPosition())
            .append("latticePrice", getLatticePrice())
            .toString();
    }
}
