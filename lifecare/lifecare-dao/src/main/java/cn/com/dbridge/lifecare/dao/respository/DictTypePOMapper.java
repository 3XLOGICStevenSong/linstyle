package cn.com.dbridge.lifecare.dao.respository;

import cn.com.dbridge.lifecare.dao.po.DictTypePO;
import java.util.List;

public interface DictTypePOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DictTypePO record);

    DictTypePO selectByPrimaryKey(Integer id);

    List<DictTypePO> selectAll();

    int updateByPrimaryKey(DictTypePO record);
}