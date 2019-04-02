package com.project.appinterface.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 参数表 t_parameter
 * 
 * @author lws
 * @date 2019-03-05
 */
public class Parameter extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private String id;
	/** 参数名称 */
	private String parameterName;
	/** 参数内容 */
	private String parameterContent;
	/** 参数类型0收费1收益2中奖 */
	private String type;
	/** 状态0有效1无效 */
	private String state;

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setParameterName(String parameterName) 
	{
		this.parameterName = parameterName;
	}

	public String getParameterName() 
	{
		return parameterName;
	}
	public void setParameterContent(String parameterContent) 
	{
		this.parameterContent = parameterContent;
	}

	public String getParameterContent() 
	{
		return parameterContent;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
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
            .append("parameterName", getParameterName())
            .append("parameterContent", getParameterContent())
            .append("type", getType())
            .append("state", getState())
            .toString();
    }
}
