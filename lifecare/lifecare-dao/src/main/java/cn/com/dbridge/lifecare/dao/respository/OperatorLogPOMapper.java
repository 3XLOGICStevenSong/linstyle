package cn.com.dbridge.lifecare.dao.respository;

import cn.com.dbridge.lifecare.dao.po.OperatorLogPO;
import java.util.List;

public interface OperatorLogPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OperatorLogPO record);

    OperatorLogPO selectByPrimaryKey(Long id);

    List<OperatorLogPO> selectAll();

    int updateByPrimaryKey(OperatorLogPO record);

    List<OperatorLogPO> selectLogInfo(OperatorLogPO record);
}