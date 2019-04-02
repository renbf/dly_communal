package com.project.appinterface.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 收藏表 t_collection
 * 
 * @author lws
 * @date 2019-03-07
 */
public class TCollection extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private String id;
	/**  */
	private String giftId;
	/**  */
	private String userId;
	/** 收藏时间 */
	private Date createDate;

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setGiftId(String giftId) 
	{
		this.giftId = giftId;
	}

	public String getGiftId() 
	{
		return giftId;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getUserId() 
	{
		return userId;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("giftId", getGiftId())
            .append("userId", getUserId())
            .append("createDate", getCreateDate())
            .toString();
    }
}
