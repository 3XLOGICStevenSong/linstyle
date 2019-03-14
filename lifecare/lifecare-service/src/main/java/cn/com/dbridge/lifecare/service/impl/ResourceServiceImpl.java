package cn.com.dbridge.lifecare.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.dbridge.lifecare.dao.po.MenuPO;
import cn.com.dbridge.lifecare.dao.po.ResourcePO;
import cn.com.dbridge.lifecare.dao.respository.ResourcePOMapper;
import cn.com.dbridge.lifecare.framework.vo.web.MenuResultVO;
import cn.com.dbridge.lifecare.framework.vo.web.MenuVO;
import cn.com.dbridge.lifecare.framework.vo.web.ResourceResultVO;
import cn.com.dbridge.lifecare.service.ResourceService;
//import lombok.extern.slf4j.Slf4j;

@Service
//@Slf4j TODO
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourcePOMapper resourcePOMapper;

    @Override
    public Set<String> getPermissionsByUserLoginCode(String loginCode) {
        Set<String> ret = new HashSet<String>();
        List<String> tmp = resourcePOMapper
                .selectStringPermissionsByUserLoginCode(loginCode);
        for (String item : tmp) {
            ret.add(item);
        }
        return ret;
    }
    /**
     * 
     * Title: getMenusByUserNumber
     * Description:通过账号获取菜单
     * @return
     * @see cn.com.dbridge.lifecare.service.ResourceService#getMenusByUserNumber()
     */
    @Override
    public MenuResultVO getMenusByUserNumber(String userNumber) {

        //结果
        List<MenuVO> menuVO = new ArrayList<MenuVO>();
        List<MenuPO> menuPO = new ArrayList<MenuPO>();
        MenuResultVO result = new MenuResultVO();
        //获取菜单列表
        menuPO = resourcePOMapper.selectMenusByUserNumber(userNumber);
        if (menuPO != null && menuPO.size() > 0) {
            for (MenuPO param : menuPO) {
                MenuVO menu = new MenuVO();
                // 结果赋值
                BeanUtils.copyProperties(param, menu);
                menuVO.add(menu);

            }
        }
        JSONArray array = buildMenuTree(menuVO, 0);
        result.setMenuList(array);
        result.setUserNumber(userNumber);
        return result;
    }

    /**
     * 
     * @Title: buildMenuTree
     * @Description: 创建菜单树
     * @param menus
     * @param root
     * @return
     * @author linh
     */
    private JSONArray buildMenuTree(List<MenuVO> menus, Integer root) {
        JSONArray ret = new JSONArray();
        List<MenuVO> list = menus.stream()
                .filter(item -> item.getParentId() == root)
                .collect(Collectors.toList());
        for (MenuVO item : list) {
            ret.add(buildMenuItem(item, buildMenuTree(menus, item.getId())));
        }
        return ret;

    }
    /**
    * 
    * @Title: buildMenuItem
    * @Description: 创建菜单项
    * @param menu
    * @param childern
    * @return
    * @author linh
    */
    private JSONObject buildMenuItem(MenuVO menu, JSONArray childern) {
        if (null == menu)
            return null;
        JSONObject ret = new JSONObject();
        ret.put("id", menu.getId());
        ret.put("name", menu.getName());
        ret.put("url", menu.getUrl());
        if (null != childern && childern.size() > 0)
            ret.put("children", childern);
        return ret;
    }
    @Override
    public Integer createResource(ResourcePO resource) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer updateResource(ResourcePO resource) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer disableResource(Integer resourceId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResourcePO getResourceByResourceId(Integer resourceId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResourceResultVO getResources(Integer roleId) {

        // TODO Auto-generated method stub
        //结果
        // List<ResourceVO> resourceVO = new ArrayList<ResourceVO>();
        List<ResourcePO> resourcePO = new ArrayList<ResourcePO>();
        ResourceResultVO result = new ResourceResultVO();
        resourcePOMapper.selectResource(roleId);
        //获取菜单列表
        resourcePO = resourcePOMapper.selectResource(roleId);
        if (resourcePO != null && resourcePO.size() > 0) {
            //            for (ResourcePO param : resourcePO) {
            //                ResourceVO resource = new ResourceVO();
            //                // 结果赋值
            //                BeanUtils.copyProperties(param, resource);
            //                resourceVO.add(resource);
            //
            //            }

            JSONArray array = buildResourceTree(resourcePO, 0);
            result.setResourceList(array);

        }
        return result;
    }
    private JSONArray buildResourceTree(List<ResourcePO> resources,
            Integer root) {
        JSONArray ret = new JSONArray();

        List<ResourcePO> list = resources.stream()
                .filter(item -> item.getParentId() == root)
                .collect(Collectors.toList());
        for (ResourcePO item : list) {
            ret.add(buildResourceItem(item,
                    buildResourceTree(resources, item.getResourceId())));
        }

        return ret;
    }

    private JSONObject buildResourceItem(ResourcePO resource,
            JSONArray childern) {
        if (null == resource)
            return null;
        JSONObject ret = new JSONObject();
        ret.put("id", resource.getResourceId());
        ret.put("name", resource.getResourceName());
        // ret.put("url", resource.getUrl());
        ret.put("permission", resource.getPermission());
        ret.put("type", resource.getResourceType());
        ret.put("pId", resource.getParentId());
        if (resource.getPermissions() != null
                && resource.getPermissions().getRoleId() != null) {
            ret.put("checked", true);
            ret.put("open", true);
        } else {
            ret.put("checked", false);
            ret.put("open", false);
        }

        if (null != childern && childern.size() > 0)
            ret.put("children", childern);
        return ret;
    }

}
