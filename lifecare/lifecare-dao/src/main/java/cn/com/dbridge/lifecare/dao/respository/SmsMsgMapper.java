package cn.com.dbridge.lifecare.dao.respository;

import org.apache.ibatis.annotations.Param;

import cn.com.dbridge.lifecare.dao.po.SmsMsgPO;

public interface SmsMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsMsgPO record);

    int insertSelective(SmsMsgPO record);

    SmsMsgPO selectSmsMsg(SmsMsgPO smsMsgPO);

    SmsMsgPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsMsgPO record);

    int updateByPrimaryKey(SmsMsgPO record);

    /**
     * 
     * @Title: insertServiceSmsMsgByOrderId 
     * @author 王林江
     * @Description: 订单分配服务人员的时候，为服务人员添加短信处理
     * @param smsMsgPO
     * @return
     */
    int insertServiceSmsMsgByOrderId(SmsMsgPO smsMsgPO);

    /**
     * 
     * @Title: insertCustomSmsMsgByOrderId 
     * @Description: 订单分配服务人员的时候，为客户添加短信处理
     * @param smsMsgPO
     * @return
     */
    int insertCustomSmsMsgByOrderId(SmsMsgPO smsMsgPO);

    /**
     * 
     * @Title: deleteByTaskId 
     * @author 王林江
     * @Description: 根据订单编号，删除短息提醒记录
     * @param taskId
     * @return
     */
    int deleteByTaskId(@Param("taskId") Integer taskId);
    
    /**
     * 
     * @Title: deleteSmsMsg 
     * @author 郭健
     * @Description: 删除短信信息
     * @param smsMsgPO
     * @return
     */
    int deleteSmsMsg(SmsMsgPO smsMsgPO);
}