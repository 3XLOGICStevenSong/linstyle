package cn.com.dbridge.lifecare.dao.respository;

import cn.com.dbridge.lifecare.dao.po.DictPO;
import java.util.List;

public interface DictPOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DictPO record);

    DictPO selectByPrimaryKey(Integer id);

    List<DictPO> selectAll();

    int updateByPrimaryKey(DictPO record);
}