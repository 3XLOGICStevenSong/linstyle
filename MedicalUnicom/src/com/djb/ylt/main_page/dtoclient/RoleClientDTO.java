package com.djb.ylt.main_page.dtoclient;

import java.util.Date;

import com.djb.ylt.framework.dto.BaseClientDTO;

/**
 * @date 2016年12月13日 下午6:07:52
 * @author chengming
 * 内容：
 * 修改履历：2016年12月13日 下午6:07:52 chengming 
 */
public class RoleClientDTO extends BaseClientDTO {
	private Integer roleId;

    private String roleName;

    private String statu;

    private Date createTime;

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
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu == null ? null : statu.trim();
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
}
