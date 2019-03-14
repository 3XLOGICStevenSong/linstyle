package com.djb.art.sct.model;

import java.io.Serializable;
import java.util.Date;

import com.djb.art.cms.serializer.CustomDateSerializer;
import com.djb.art.cms.deserializer.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class User implements Serializable {
	
	public interface WithoutPasswordView {};
	public interface WithPasswordView extends WithoutPasswordView {};
    
	private static final long serialVersionUID = -2236037914810967120L;
	
	private Integer backUserId;
	private String loginCode;
	private String userName;
	private String password;
	private String salt;
	private Boolean status;
	
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)  
	private Date createTime;
	
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)  
	private Date updateTime;
	
	private String lastLoginIp;
	
	public String getCredentialsSalt() {
		return this.loginCode + this.salt;
	}
	
	@JsonView(WithoutPasswordView.class)
	public Integer getBackUserId() {
		return backUserId;
	}
	public void setBackUserId(Integer backUserId) {
		this.backUserId = backUserId;
	}
	@JsonView(WithoutPasswordView.class)
	public String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	@JsonView(WithoutPasswordView.class)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@JsonView(WithPasswordView.class)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@JsonView(WithPasswordView.class)
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@JsonView(WithoutPasswordView.class)
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	@JsonView(WithoutPasswordView.class)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@JsonView(WithoutPasswordView.class)
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@JsonView(WithoutPasswordView.class)
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
   
}
