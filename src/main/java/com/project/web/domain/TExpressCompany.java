package com.project.web.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.project.common.base.BaseEntity;

/**
 * 快递公司表 t_express_company
 * 
 * @author rbf
 * @date 2019-04-02
 */
public class TExpressCompany extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String id;
	/** 公司编码 */
	private String companyCode;
	/** 公司名称 */
	private String companyName;

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setCompanyCode(String companyCode) 
	{
		this.companyCode = companyCode;
	}

	public String getCompanyCode() 
	{
		return companyCode;
	}
	public void setCompanyName(String companyName) 
	{
		this.companyName = companyName;
	}

	public String getCompanyName() 
	{
		return companyName;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyCode", getCompanyCode())
            .append("companyName", getCompanyName())
            .toString();
    }
}
