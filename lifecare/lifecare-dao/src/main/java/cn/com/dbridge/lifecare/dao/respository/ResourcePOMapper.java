package cn.com.dbridge.lifecare.dao.respository;

import java.util.List;

import cn.com.dbridge.lifecare.dao.po.MenuPO;
import cn.com.dbridge.lifecare.dao.po.ResourcePO;
import cn.com.dbridge.lifecare.dao.po.RolePO;


public interface ResourcePOMapper {

    public Integer insertRole(RolePO user);

	public List<String> selectStringPermissionsByUserLoginCode(String loginCode);

    public List<MenuPO> selectMenusByUserNumber(String userNumber);

    public List<ResourcePO> selectResource(Integer roleId);

}
