package cn.com.dbridge.lifecare.dao.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dbridge.lifecare.dao.po.UserTaskRealPO;

public interface UserTaskRealMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTaskRealPO record);

    int insertSelective(UserTaskRealPO record);

    UserTaskRealPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTaskRealPO record);

    int updateByPrimaryKey(UserTaskRealPO record);

    /**
     * 
     * @Title: selectByOrderIdAndUserId 
     * @Description: 确认订单是否已读
     * @author 郭健
     * @param record
     */
    List<UserTaskRealPO> selectReadOrNot(UserTaskRealPO record);

    /**
     * 
     * @Title: deleteByTaskId 
     * @author 王林江
     * @Description: 根据订单编号删除任务池的订单查看信息
     * @param orderId
     * @return
     */
    int deleteByTaskId(@Param("taskId") Integer taskId);
    
    /**
     * 
     * @Title: deleteUserTaskReal 
     * @author 郭健
     * @Description: 删除订单查看信息
     * @param userTaskRealPO
     * @return
     */
    int deleteUserTaskReal(UserTaskRealPO userTaskRealPO);
}