package com.djb.art.sct.model;

import java.io.Serializable;
import java.util.Date;

import com.djb.art.cms.deserializer.CustomDateDeserializer;
import com.djb.art.cms.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Role implements Serializable {

	private static final long serialVersionUID = -8530710278034239646L;
	
	private Integer roleId;
	private String roleName;
	private Boolean statu;
	
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)  
	private Date createTime;
	
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)  
	private Date updateTime;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Boolean getStatu() {
		return statu;
	}
	public void setStatu(Boolean statu) {
		this.statu = statu;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
