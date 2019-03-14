package cn.com.dbridge.jtraining.dao.respository;

import cn.com.dbridge.jtraining.dao.po.OperatorLogPO;
import java.util.List;

public interface OperatorLogPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OperatorLogPO record);

    OperatorLogPO selectByPrimaryKey(Long id);

    List<OperatorLogPO> selectAll();

    int updateByPrimaryKey(OperatorLogPO record);
}