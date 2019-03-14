package cn.com.dbridge.lifecare.dao.respository;

import java.util.List;

import cn.com.dbridge.lifecare.dao.po.ResponsibilityPO;

public interface ResponsibilityPOMapper {

    Integer delete(Integer userId);

    Integer insertBatch(List<ResponsibilityPO> responsibilitys);
    
    List<ResponsibilityPO> getResponseByRoleId(Integer roleId);

}
