package com.project.appinterface.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 用户表 t_user_info
 * 
 * @author lws
 * @date 2019-03-05
 */
public class UserInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	private String id;
	/**  */
	private String phone;
	/** 密码 */
	private String password;
	/** 性别0女1男 */
	private String sex;
	/** 昵称 */
	private String nickname;
	/** 生日 */
	private Date birthDay;
	/** 邀请码 */
	private String invitationCode;
	/** 头像地址 */
	private String headPortrait;
	/** 微信openid */
	private String openid;
	/** 登录状态0未登录1已登录 */
	private String loginState;
	/** 0正常1冻结 */
	private String state;
	/**  */
	private Date createDate;
	/** 修改时间 */
	private Date updateDate;
	/** 邀请人 **/
	private String  inviterCode;

	public String getInviterCode() {
		return inviterCode;
	}

	public void setInviterCode(String inviterCode) {
		this.inviterCode = inviterCode;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getPhone() 
	{
		return phone;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getPassword() 
	{
		return password;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}

	public String getSex() 
	{
		return sex;
	}
	public void setNickname(String nickname) 
	{
		this.nickname = nickname;
	}

	public String getNickname() 
	{
		return nickname;
	}
	public void setBirthDay(Date birthDay) 
	{
		this.birthDay = birthDay;
	}

	public Date getBirthDay() 
	{
		return birthDay;
	}
	public void setInvitationCode(String invitationCode) 
	{
		this.invitationCode = invitationCode;
	}

	public String getInvitationCode() 
	{
		return invitationCode;
	}
	public void setHeadPortrait(String headPortrait) 
	{
		this.headPortrait = headPortrait;
	}

	public String getHeadPortrait() 
	{
		return headPortrait;
	}
	public void setOpenid(String openid) 
	{
		this.openid = openid;
	}

	public String getOpenid() 
	{
		return openid;
	}
	public void setLoginState(String loginState) 
	{
		this.loginState = loginState;
	}

	public String getLoginState() 
	{
		return loginState;
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
	public void setUpdateDate(Date updateDate) 
	{
		this.updateDate = updateDate;
	}

	public Date getUpdateDate() 
	{
		return updateDate;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("phone", getPhone())
            .append("password", getPassword())
            .append("sex", getSex())
            .append("nickname", getNickname())
            .append("birthDay", getBirthDay())
            .append("invitationCode", getInvitationCode())
            .append("headPortrait", getHeadPortrait())
            .append("openid", getOpenid())
            .append("loginState", getLoginState())
            .append("state", getState())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .toString();
    }
}
