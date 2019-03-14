package cn.com.dbridge.lifecare.framework.dto;

import java.io.Serializable;

public class RoleResourceDTO implements Serializable {
    private static final long serialVersionUID = -7660811555137286061L;
    private Integer roleId;
    private String ids;
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public String getIds() {
        return ids;
    }
    public void setIds(String ids) {
        this.ids = ids;
    }
}
