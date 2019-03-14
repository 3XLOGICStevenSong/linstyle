package cn.com.dbridge.lifecare.service;

import java.util.List;
import java.util.Set;

import cn.com.dbridge.lifecare.framework.dto.web.RoleDTO;
import cn.com.dbridge.lifecare.framework.vo.web.RoleResposeResultVO;
import cn.com.dbridge.lifecare.framework.vo.web.RoleResultVO;
import cn.com.dbridge.lifecare.framework.vo.web.RoleVO;

public interface RoleService {

    public Set<String> getRolesByUserLoginCode(String userLoginCode);

    public Integer createRole(RoleDTO role);

    public RoleResultVO getRoles(Integer offset, Integer limit);

    public Integer updateRole(RoleDTO role);

    public Integer updateUserRoles(Integer userId, Integer[] roleIds);

    public RoleVO getRoleDetail(Integer roleId);

    public List<RoleVO> getAllRoles();

    public Integer resourceRole(Integer roleId, Integer[] ids);

    public RoleResposeResultVO getRolesByUserId(Integer userId);

    public Integer deleteRole(Integer roleId);


}
