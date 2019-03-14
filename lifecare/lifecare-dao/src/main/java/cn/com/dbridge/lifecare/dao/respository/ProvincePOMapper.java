package cn.com.dbridge.lifecare.dao.respository;

import cn.com.dbridge.lifecare.dao.po.ProvincePO;
import java.util.List;

public interface ProvincePOMapper {
    int deleteByPrimaryKey(Integer provinceId);

    int insert(ProvincePO record);

    ProvincePO selectByPrimaryKey(Integer provinceId);

    List<ProvincePO> selectAll();

    int updateByPrimaryKey(ProvincePO record);
}