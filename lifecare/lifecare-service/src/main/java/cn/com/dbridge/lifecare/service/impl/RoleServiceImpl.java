package cn.com.dbridge.lifecare.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.com.dbridge.lifecare.dao.po.PermissionPO;
import cn.com.dbridge.lifecare.dao.po.ResponsibilityPO;
import cn.com.dbridge.lifecare.dao.po.RolePO;
import cn.com.dbridge.lifecare.dao.po.WebUserPO;
import cn.com.dbridge.lifecare.dao.respository.ResponsibilityPOMapper;
import cn.com.dbridge.lifecare.dao.respository.RolePOMapper;
import cn.com.dbridge.lifecare.dao.respository.UserPOMapper;
import cn.com.dbridge.lifecare.framework.dto.web.RoleDTO;
import cn.com.dbridge.lifecare.framework.enums.UserTypeEnum;
import cn.com.dbridge.lifecare.framework.exception.CustomException;
import cn.com.dbridge.lifecare.framework.util.PageInitUtils;
import cn.com.dbridge.lifecare.framework.vo.web.RoleResposeResultVO;
import cn.com.dbridge.lifecare.framework.vo.web.RoleResposibilityVO;
import cn.com.dbridge.lifecare.framework.vo.web.RoleResultVO;
import cn.com.dbridge.lifecare.framework.vo.web.RoleVO;
import cn.com.dbridge.lifecare.pingyin.PinyinTool;
import cn.com.dbridge.lifecare.pingyin.PinyinTool.Type;
import cn.com.dbridge.lifecare.service.RoleService;
//import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

@Service
//@Slf4j TODO
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RolePOMapper rolePOMapper;
    @Autowired
    private UserPOMapper userPOMapper;
    @Autowired
    private ResponsibilityPOMapper responsibilityPOMapper;

    // @Autowired
    // private UserPOMapper userPOMapper;

    @Override
    public Set<String> getRolesByUserLoginCode(String loginCode) {
        Set<String> ret = new HashSet<String>();
        List<String> tmp = rolePOMapper
                .selectStringRolesByUserLoginCode(loginCode);
        for (String item : tmp) {
            ret.add(item);
        }
        return ret;
    }

    @Override
    public Integer createRole(RoleDTO role) {
        RolePO rolePO = new RolePO();
        BeanUtils.copyProperties(role, rolePO);
        String roleNamePinYin = null;
        PinyinTool tool = new PinyinTool();
        try {
            roleNamePinYin = tool.toPinYin(role.getRoleName(), "",
                    Type.UPPERCASE);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw new CustomException("角色名称不正：角色名称转换失败");
        }
        rolePO.setRoleNamePinyin(roleNamePinYin);
        //添加角色

        RolePO roleName = rolePOMapper.getRoleByRoleName(role.getRoleName());
        if (roleName == null) {
            return rolePOMapper.insert(rolePO);
        } else {
            throw new CustomException("角色名称必须唯一");
        }

    }

    /**
     * 
     * Title: getRoles
     * Description:获取所有角色
     * @param offset
     * @param limit
     * @return
     * @see cn.com.dbridge.lifecare.service.RoleService#getRoles(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public RoleResultVO getRoles(Integer offset, Integer limit) {
        RoleResultVO result = new RoleResultVO();
        List<RolePO> resultList = null;
        //分页数据赋值
        Integer offsetParam = PageInitUtils.setPageOffset(offset);
        Integer limitParma = PageInitUtils.setPageLimit(limit);
        Page<Object> offsetPage = PageHelper.offsetPage(offsetParam,
                limitParma);
        resultList = rolePOMapper.getRoles();
        // @SuppressWarnings("resource")
        //  Page<RolePO> pages = (Page<RolePO>) resultList;

        List<RoleVO> roleVOList = new ArrayList<RoleVO>();
        if (resultList != null && resultList.size() > 0) {

            for (RolePO param : resultList) {
                //结果赋值
                RoleVO roleVO = new RoleVO();
                BeanUtils.copyProperties(param, roleVO);
                roleVOList.add(roleVO);
            }

        }
        result.setRows(roleVOList);
        result.setTotal(offsetPage.getTotal());
        result.setStatusCode(HttpStatus.OK.value());
        result.setName("获取所有角色");
        //TODO total 数没有返回
        return result;
    }

    @Override
    public Integer updateRole(RoleDTO role) {
        RolePO rolePO = new RolePO();
        BeanUtils.copyProperties(role, rolePO);

        List<RolePO> result = rolePOMapper.getRolesInfo(rolePO);
        if (result != null && result.size() > 0) {
            throw new CustomException("角色名称必须唯一");
        } else {
            String roleNamePinYin = null;
            PinyinTool tool = new PinyinTool();
            try {
                roleNamePinYin = tool.toPinYin(role.getRoleName(), "",
                        Type.UPPERCASE);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                throw new CustomException("角色名称不正：角色名称转换失败");
            }
            rolePO.setRoleNamePinyin(roleNamePinYin);
            return rolePOMapper.updateRole(rolePO);
        }

    }

    @Override
    @Transactional
    public Integer updateUserRoles(Integer userId, Integer[] roleIds) {

        // 删除之前角色
        responsibilityPOMapper.delete(userId);
        List<ResponsibilityPO> list = new ArrayList<ResponsibilityPO>();
        if (roleIds != null && roleIds.length > 0) {
            for (Integer i : roleIds) {
                ResponsibilityPO responsibility = new ResponsibilityPO();
                responsibility.setRoleId(i);
                responsibility.setUserId(userId);
                list.add(responsibility);
            }
            if (list.size() > 0) {
                //批量添加
                return responsibilityPOMapper.insertBatch(list);
            }
        }
        return null;
    }
    @Override
    @Transactional
    public Integer resourceRole(Integer roleId, Integer[] ids) {
        // 删除之前角色
        int count = rolePOMapper.deletePermission(roleId);
        List<PermissionPO> list = new ArrayList<PermissionPO>();
        if (ids != null && ids.length > 0) {
            for (Integer i : ids) {
                PermissionPO permission = new PermissionPO();
                permission.setRoleId(roleId);
                permission.setResourceId(i);
                list.add(permission);
            }
            if (list.size() > 0) {
                rolePOMapper.insertPermission(list);
            }
        }

        return count;
    }
    @Override
    public RoleVO getRoleDetail(Integer roleId) {
        //获取角色详情信息
        RolePO rolePO = rolePOMapper.getRoleByRoleId(roleId);
        RoleVO roleVO = new RoleVO();
        BeanUtils.copyProperties(rolePO, roleVO);
        return roleVO;

    }

    @Override
    public List<RoleVO> getAllRoles() {

        List<RolePO> resultList = new ArrayList<RolePO>();

        resultList = rolePOMapper.getAllRoles();
        List<RoleVO> roleVOList = new ArrayList<RoleVO>();
        if (resultList != null && resultList.size() > 0) {

            for (RolePO param : resultList) {
                //结果赋值
                RoleVO roleVO = new RoleVO();
                BeanUtils.copyProperties(param, roleVO);
                roleVOList.add(roleVO);
            }

        }
        return roleVOList;
    }
	@Override
    public RoleResposeResultVO getRolesByUserId(Integer userId) {
		//只有后台用户才能赋予角色
    	WebUserPO user = userPOMapper.getUserInfoByUserId(userId);
    	if(user != null && 
    			!UserTypeEnum.BACKGROUND_USER.value.equals(user.getUserType()+"")) {
    		throw new CustomException("只能对后台用户分配角色");
    	}
    	
        RoleResposeResultVO result = new RoleResposeResultVO();
        List<RolePO> resultList = new ArrayList<RolePO>();

        resultList = rolePOMapper.getRolesByUserId(userId);
        List<RoleResposibilityVO> roleVOList = new ArrayList<RoleResposibilityVO>();
        if (resultList != null && resultList.size() > 0) {

            for (RolePO param : resultList) {
                //结果赋值
                RoleResposibilityVO roleVO = new RoleResposibilityVO();
                BeanUtils.copyProperties(param, roleVO);
                if (param.getResponsibiltyList().size() > 0 && param
                        .getResponsibiltyList().get(0).getUserId() != null) {
                    //拥有该角色
                    roleVO.setRoleFlag("1");
                } else {
                    roleVO.setRoleFlag("0");
                }
                roleVOList.add(roleVO);
            }
            result.setRows(roleVOList);
            result.setStatusCode(200);
            result.setMsg("获取角色列表");
        }
        return result;
    }

    @Override
    public Integer deleteRole(Integer roleId) {
        // TODO Auto-generated method stub
        //获取该角色是否被使用
        List<ResponsibilityPO> list = new ArrayList<ResponsibilityPO>();
        list = responsibilityPOMapper.getResponseByRoleId(roleId);
        if (list != null && list.size() > 0) {
            throw new CustomException("该角色已经被用户使用，不可删除");
        } else {
            return rolePOMapper.deleteByRoleId(roleId);
        }
    }

}
