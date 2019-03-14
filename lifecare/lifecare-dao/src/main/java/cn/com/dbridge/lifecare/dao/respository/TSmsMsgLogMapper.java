package cn.com.dbridge.lifecare.dao.respository;

import cn.com.dbridge.lifecare.dao.po.SmsMsgLogPO;

public interface TSmsMsgLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsMsgLogPO record);

    int insertSelective(SmsMsgLogPO record);

    SmsMsgLogPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsMsgLogPO record);

    int updateByPrimaryKey(SmsMsgLogPO record);
}