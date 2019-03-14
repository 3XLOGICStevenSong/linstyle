package cn.com.dbridge.lifecare.rest;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.lifecare.framework.base.Result;
import cn.com.dbridge.lifecare.framework.constant.Constant;
import cn.com.dbridge.lifecare.framework.dblog.annotation.OperatorLog;
import cn.com.dbridge.lifecare.framework.dto.RoleResourceDTO;
import cn.com.dbridge.lifecare.framework.dto.web.RoleDTO;
import cn.com.dbridge.lifecare.framework.exception.CustomException;
import cn.com.dbridge.lifecare.framework.util.JwtUtil;
import cn.com.dbridge.lifecare.framework.vo.web.MenuResultVO;
import cn.com.dbridge.lifecare.framework.vo.web.ResourceResultVO;
import cn.com.dbridge.lifecare.framework.vo.web.RoleResposeResultVO;
import cn.com.dbridge.lifecare.framework.vo.web.RoleResultVO;
import cn.com.dbridge.lifecare.framework.vo.web.RoleVO;
import cn.com.dbridge.lifecare.service.ResourceService;
import cn.com.dbridge.lifecare.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @ClassName:  PermissionController
 * @Description:权限资源管理
 * @author: linh
 * @date:   2018年12月25日 下午5:19:34
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@RestController
@Api(tags = "权限管理")
@RequestMapping(value = "/api")
public class PermissionController {
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleService roleService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletRequest response;

    @ApiOperation(value = "获取当前用户菜单", notes = "获取当前用户菜单")
    @PostMapping(value = "/user/menus")
    @RequiresAuthentication
    public Result<MenuResultVO> getCurrentUserMenus( @RequestHeader ("Authorization") String token ) {

        String userNumber= JwtUtil.getClaim(token, Constant.ACCOUNT);
        if (userNumber == null) {
            throw new CustomException("请登录");
        }
        return new Result<MenuResultVO>(HttpStatus.OK.value(), "操作成功",
                resourceService.getMenusByUserNumber(userNumber));

    }

    @ApiOperation(value = "获取角色列表", notes = "获取角色列表")
    @GetMapping(value = "/permission/getRoles")
    //@RequiresPermissions("role:view") //TODO
    @RequiresAuthentication
    public RoleResultVO getRoles(
            @ApiParam(name = "offset", value = "起始页码") @RequestParam("offset") Integer offset,
            @ApiParam(name = "limit", value = "总条数") @RequestParam("limit") Integer limit,
            @ApiParam(name = "token", value = "token") HttpServletRequest request) {
        return roleService.getRoles(offset, limit);
    }
    /**
     * 
     * @Title: createRole
     * @Description: 添加角色
     * @param role
     * @return
     */
    @ApiOperation(value = "添加角色", notes = "添加角色")
    @PostMapping("/permission/roleCreate")
    // @RequiresPermissions("role:create")
    @OperatorLog(module="权限管理",methods="添加角色",description="添加角色")
    @RequiresAuthentication
    public Result<Object> createRole(@RequestBody RoleDTO role) {

        return new Result<Object>(HttpStatus.OK.value(), "操作成功",
                roleService.createRole(role));

    }

    /**
    * 
    * @Title: updateRole
    * @Description: 角色更新
    * @param role
    * @return
    */
    @ApiOperation(value = "更新角色", notes = "更新角色")
    @PutMapping("/permission/roleUpdate")
    @OperatorLog(module="权限管理",methods="更新角色",description="更新角色")
    //@RequiresPermissions("role:update")
    @RequiresAuthentication
    public Result<Object> updateRole(
            @ApiParam(name = "role", value = "角色DTO") @RequestBody RoleDTO role) {

        return new Result<Object>(HttpStatus.OK.value(), "操作成功",
                roleService.updateRole(role));

    }
    /**
     * 
     * @Title: deleteRole
     * @Description: 角色删除
     * @param roleId
     * @return
     */
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @DeleteMapping("/permission/roleDelete/{roleId}")
    @OperatorLog(module="权限管理",methods="删除角色",description="删除角色")
    //@RequiresPermissions("role:update")
    @RequiresAuthentication
    public Result<Object> deleteRole(
            @ApiParam(name = "roleId", value = "角色Id") @PathVariable(value = "roleId") Integer roleId) {

        return new Result<Object>(HttpStatus.OK.value(), "操作成功",
                roleService.deleteRole(roleId));

    }
    /**
     * 角色详情
     */
    //@RequiresPermissions("role:view")
    @ApiOperation(value = "获取角色信息", notes = "获取角色信息")
    @GetMapping("/permission/roleDetail/{roleId}")
    @RequiresAuthentication
    public Result<RoleVO> getRoleDetail(
            @ApiParam(name = "roleId", value = "角色ID") @PathVariable(value = "roleId") Integer roleId) {

        return new Result<RoleVO>(HttpStatus.OK.value(), "操作成功",
                roleService.getRoleDetail(roleId));
    }

    /**
     * 角色分配
     * 
     * @param loginCode
     * @param roleIds
     * @return
     */
    @ApiOperation(value = "角色分配", notes = "角色分配")
    @GetMapping("/permission/roleGrant")
    @OperatorLog(module="权限管理",methods="角色分配",description="角色分配")
    // @RequiresPermissions("role:update")
    @RequiresAuthentication
    public Result<Object> roleGrant(
            @ApiParam(name = "userId", value = "用户ID") @RequestParam("userId") Integer userId,
            @ApiParam(name = "roleIds", value = "roleIds") @RequestParam("roleIds") String roleIds) {
        if (roleIds != null && !"".equals(roleIds)) {
            String[] id = roleIds.split(",");
            Integer[] idList = new Integer[id.length];
            if (id.length > 0) {
                for (int i = 0; i < id.length; i++) {
                    idList[i] = Integer.parseInt(id[i]);
                }
            }
            return new Result<Object>(HttpStatus.OK.value(), "操作成功",
                    roleService.updateUserRoles(userId, idList));
        } else if ("".equals(roleIds)) {
            return new Result<Object>(HttpStatus.OK.value(), "操作成功",
                    roleService.updateUserRoles(userId, null));
        }
        return null;

    }
    @ApiOperation(value = "获取资源", notes = "获取资源")
    @PostMapping("/permission/roleResource")
    // @RequiresPermissions("role:update")
    @RequiresAuthentication
    public Result<Object> roleResource(@RequestBody RoleResourceDTO roleResourceDTO) {
        String ids = roleResourceDTO.getIds();
        Integer roleId = roleResourceDTO.getRoleId();
        if (ids != null && !"".equals(ids)) {
            String[] id = ids.split(",");
            Integer[] idList = new Integer[id.length];
            if (id.length > 0) {
                for (int i = 0; i < id.length; i++) {
                    idList[i] = Integer.parseInt(id[i]);
                }
                return new Result<Object>(HttpStatus.OK.value(), "操作成功",
                        roleService.resourceRole(roleId, idList));
            }
        } else {
            return new Result<Object>(HttpStatus.OK.value(), "操作成功",
                    roleService.resourceRole(roleId, null));
        }
        return null;
    }

    /**
     * 获取所有角色
     * 
     * @return
     */
    @ApiOperation(value = "获取角色", notes = "获取角色")
    //@RequiresPermissions("role:view")
    @GetMapping("/permission/allRole")
    @RequiresAuthentication
    public Result<List<RoleVO>> getAllRoleList() {

        return new Result<List<RoleVO>>(HttpStatus.OK.value(), "操作成功",
                roleService.getAllRoles());
    }

    /**
     * 获取所有角色（角色分配）
     * 
     * @return
     */
    //@RequiresPermissions("role:view")
    @GetMapping("/permission/userRoles")
    @ApiOperation(value = "获取用户角色", notes = "获取角色")
    @RequiresAuthentication
    public RoleResposeResultVO getRoleInfoByUserId(
            @ApiParam(name = "userId", value = "用户ID") @RequestParam(required = false) Integer userId) {
        return roleService.getRolesByUserId(userId);
    }
    /**
     * 
     * @Title: PerssionsResource
     * @Description: 获取资源树
     * @param roleId
     * @return
     */
    @GetMapping("/permission/resourceTree")
    @RequiresAuthentication
    public Result<ResourceResultVO> PerssionsResource(
            @RequestParam(name = "roleId", required = false) Integer roleId) {
        //if (-1==roleId) {
        if (0 == roleId) {
            roleId = null;
        }
        return new Result<ResourceResultVO>(HttpStatus.OK.value(), "操作成功",
                resourceService.getResources(roleId));

    }

    @GetMapping("current/user/permissions")
    @RequiresAuthentication
    public Result<Set<String>> getCurrentUserPermissions(
            @RequestHeader("Authorization") String token) {
        String userNumber = JwtUtil.getClaim(token, Constant.ACCOUNT);
        if (userNumber == null) {
            throw new CustomException("请登录");
        }
        return new Result<Set<String>>(HttpStatus.OK.value(), "操作成功",
                resourceService.getPermissionsByUserLoginCode(userNumber));

    }
}
