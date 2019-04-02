package com.project.web.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 *  礼物机类型表 t_gift_model
 * 
 * @author lws
 * @date 2019-03-12
 */
public class TGiftModel extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String id;
	/** 格子数 */
	private Integer latticeNum;
	/** 礼物机图片 */
	private String giftPicture;
	/** 创建时间 */
	private Date createDate;
	/** 状态0使用中1未使用2已作废 */
	private String state;

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setLatticeNum(Integer latticeNum) 
	{
		this.latticeNum = latticeNum;
	}

	public Integer getLatticeNum() 
	{
		return latticeNum;
	}
	public void setGiftPicture(String giftPicture) 
	{
		this.giftPicture = giftPicture;
	}

	public String getGiftPicture() 
	{
		return giftPicture;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setState(String state) 
	{
		this.state = state;
	}

	public String getState() 
	{
		return state;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("latticeNum", getLatticeNum())
            .append("giftPicture", getGiftPicture())
            .append("createDate", getCreateDate())
            .append("state", getState())
            .toString();
    }
}
