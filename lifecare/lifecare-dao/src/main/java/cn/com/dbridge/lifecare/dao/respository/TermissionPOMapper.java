package cn.com.dbridge.lifecare.dao.respository;

import cn.com.dbridge.lifecare.dao.po.TermissionPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TermissionPOMapper {
    int deleteByPrimaryKey(@Param("roleId") Integer roleId, @Param("resourceId") Integer resourceId);

    int insert(TermissionPO record);

    List<TermissionPO> selectAll();
}