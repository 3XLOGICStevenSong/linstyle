package cn.com.dbridge.lifecare.dao.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dbridge.lifecare.dao.po.PermissionPO;
import cn.com.dbridge.lifecare.dao.po.RolePO;


public interface RolePOMapper {

    List<String> selectStringRolesByUserLoginCode(String loginCode);

    Integer insert(RolePO role);

    Integer updateRole(RolePO role);

    Integer disableRoleByRoleId(Integer roleId);

    RolePO getRoleByRoleId(Integer roleId);

    RolePO getRoleByRoleName(@Param("roleName") String roleName);

    List<RolePO> getRoles();

    int selectCount();

    List<RolePO> getAllRoles();

    List<RolePO> getRolesInfo(RolePO role);

    Integer deletePermission(Integer roleId);

    Integer insertPermission(List<PermissionPO> permission);

    List<RolePO> getRolesByUserId(@Param("userId") Integer userId);

    Integer deleteByRoleId(Integer roleId);

}
