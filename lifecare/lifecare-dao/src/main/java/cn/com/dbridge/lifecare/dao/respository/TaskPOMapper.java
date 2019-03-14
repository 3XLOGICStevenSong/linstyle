package cn.com.dbridge.lifecare.dao.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dbridge.lifecare.dao.po.MobileMyTaskCalendarParaPO;
import cn.com.dbridge.lifecare.dao.po.MobileMyTaskCalendarResPO;
import cn.com.dbridge.lifecare.dao.po.MobileMyTaskParaPO;
import cn.com.dbridge.lifecare.dao.po.MobileMyTaskResPO;
import cn.com.dbridge.lifecare.dao.po.MobileOrderPO;
import cn.com.dbridge.lifecare.dao.po.MobileSeeFlagPO;
import cn.com.dbridge.lifecare.dao.po.MobileTaskPO;
import cn.com.dbridge.lifecare.dao.po.MobileTaskPoolCalendarParaPO;
import cn.com.dbridge.lifecare.dao.po.TaskManagePO;
import cn.com.dbridge.lifecare.dao.po.TaskManageResPO;
import cn.com.dbridge.lifecare.dao.po.TaskPO;
import cn.com.dbridge.lifecare.dao.po.TaskPoolPO;
import cn.com.dbridge.lifecare.dao.po.TaskPoolResultPO;
import cn.com.dbridge.lifecare.dao.po.UnassignedTaskPO;
import cn.com.dbridge.lifecare.dao.po.UnassignedTaskResultPO;
import cn.com.dbridge.lifecare.dao.po.UnassignedTaskSeePO;
import cn.com.dbridge.lifecare.dao.po.WebUserSelectPO;
import cn.com.dbridge.lifecare.dao.po.WxQueryTaskCalenderPO;
import cn.com.dbridge.lifecare.dao.po.WxTaskQueryDetailPO;
import cn.com.dbridge.lifecare.dao.po.WxTaskQueryPO;

public interface TaskPOMapper {
    int insert(TaskPO record);

    List<TaskPO> selectAll();

    List<TaskPO> selectOrder(TaskPO taskPO);
    
    /**
     * 
     * @Title: selectCalendarTaskForMyTask 
     * @author 郭健
     * @Description:获取本月我的任务中有预约的日期
     * @param orderPO
     * @return
     */
    List<MobileMyTaskCalendarResPO> selectCalendarTaskForMyTask(
            MobileMyTaskCalendarParaPO mobileMyTaskCalendarParaPO);

    /**
     * 
     * @Title: selectOrderForMyTask
     * @author 郭健 
     * @Description:获取我的任务页信息
     * @param mobileMyTaskPagePO
     * @return
     */
    List<MobileMyTaskResPO> selectOrderForMyTask(
            MobileMyTaskParaPO mobileMyTaskPagePO);

    /**
     * 
     * @Title: selectSeeFlag 
     * @author 郭健
     * @Description:判断该条记录是否已读
     * @param orderPO
     */
    MobileSeeFlagPO selectSeeFlag(TaskPO orderPO);

    /**
     * 
     * @Title: updateOrderStatus
     * @author 郭健 
     * @Description:修改订单状态
     * @param orderPO
     * @return
     */
    int updateOrderStatus(TaskPO orderPO);

    /**
     * 
     * @Title: selectDetailedOrder 
     * @author 郭健
     * @Description: 获取订单详情信息
     * @param orderPO
     * @return
     */
    MobileTaskPO selectDetailedOrder(TaskPO orderPO);

    /**
     * 
     * @Title: selectCalendarTaskForTaskPool 
     * @author 郭健
     * @Description:获取任务池中本月中有预约的日期
     * @param mobileCalendarTaskForTaskPoolPO
     * @return
     */
    List<MobileMyTaskCalendarParaPO> selectCalendarTaskForTaskPool(
            MobileTaskPoolCalendarParaPO mobileCalendarTaskForTaskPoolPO);

    /**
     * 
     * @Title: selectOrderByDate 
     * @author 郭健
     * @Description: 获取某一天的订单信息
     * @param orderPO
     * @return
     */
    //List<Mobile> selectOrderByDate(OrderPO orderPO);

    /**
     * 
     * @Title: selectOrderNotComplete 
     * @author 郭健
     * @Description: 获取未完成服务列表
     * @param orderPO
     * @return
     */
    List<MobileOrderPO> selectOrderNotComplete(TaskPO orderPO);

    /**
     * 
     * @Title: selectOrderCompleted 
     * @author 郭健
     * @Description: 获取已完成服务列表
     * @param orderPO
     * @return
     */
    List<MobileOrderPO> selectOrderCompleted(TaskPO orderPO);



    /**
     * 
     * @Title: selectOrderByCustomIdAndServicePersonId
     * @author 郭健  
     * @Description: 获取订单
     * @param orderPO
     * @return
     */
    List<MobileOrderPO> selectOrderByCustomIdAndServicePersonId(
            TaskPO orderPO);
    /**
     * 
     * @Title: selectPendingCount
     * @Description:获取待处理任务数
     * @author linh
     * @return
     */
    int selectPendingCount();
    /**
     * 
     * @Title: wxSelectTask
     * @Description: 查询任务
     * @param List<WxTaskQueryPO> 
     * @return
     */
    List<WxTaskQueryPO> wxSelectTask(WxTaskQueryPO wxTaskQueryPO);
    /**
     * 
     * @Title: wxSelectTaskByPrimaryKey
     * @Description: 根据主键查询任务
     * @param TaskPO
     * @return
     */
    TaskPO wxSelectTaskByPrimaryKey(@Param(value="taskId") Integer taskId);
    
    /**
     * 
     * @Title: wxSelectTaskDetail
     * @Description: 查询任务详情
     * @param List<WxTaskQueryPO>
     * @return
     */
    WxTaskQueryDetailPO wxSelectTaskDetail(WxTaskQueryDetailPO wxTaskQueryDetailPO);

    /**
     * 
     * @Title: selectUnassignedTaskManage
     * @author 郭健
     * @Description:获取未分配任务管理页信息
     * @param unassignedTaskPO
     * @return
     */
    List<UnassignedTaskResultPO> selectUnassignedTaskManage(
            UnassignedTaskPO unassignedTaskPO);

    /**
     * 
     * @Title: selectTaskManage 
     * @author 郭健
     * @Description:获取服务任务管理页信息
     * @param taskManagePO
     * @return
     */
    List<TaskManageResPO> selectTaskManage(TaskManagePO taskManagePO);

    /**
     * 
     * @Title: updateByPrimaryKey 
     * @author 郭健
     * @Description:根据主键更新
     * @param taskPO
     * @return
     */
    int updateByPrimaryKey(TaskPO taskPO);
    
    /**
     * 
     * @Title: updateByTaskId 
     * @author 郭健
     * @Description:编辑未分配页面信息
     * @param taskPO
     * @return
     */
    int updateByTaskId(TaskPO taskPO);
    
    /**
     * 
     * @Title: selectCustomById 
     * @author 郭健
     * @Description:根据主键获取客户信息
     * @param id
     * @return
     */
    UnassignedTaskResultPO selectCustomById(Integer id);

    /**
     * 
     * @Title: selectServicePersonByOrderNo 
     * @author 郭健
     * @Description:根据订单号获取服务人员信息
     * @param orderNo
     * @return
     */
    List<UnassignedTaskSeePO> selectServicePersonByOrderNo(String orderNo);
    /**
     * 
     * @Title: selectCalendarTask
     * @Description: 查询任务日历
     * @param paramWxTaskQueryPO
     * @return
     */
    List<WxQueryTaskCalenderPO> selectCalendarTask(WxQueryTaskCalenderPO paramWxTaskQueryPO);

    /**
     * 
     * @Title: getNoSeeTaskCnt
     * @author 王林江
     * @Description: 获取当前日期的我的任务和任务池未查看的任务数
     * @param paramWxTaskQueryPO
     * @return
     */
    int getNoSeeTaskCnt(
            WxQueryTaskCalenderPO paramWxTaskQueryPO);

    /**
     * 
     * @Title: updateServicePersonId
     * @author 王林江 
     * @Description:订单服务人员更新实行
     * @param webUserSelectPO
     * @return
     */
    int updateServicePersonId(WebUserSelectPO webUserSelectPO);

    /**
     * 
     * @Title: getUsedTaskCnt 
     * @author 王林江
     * @Description: 获取用户待分配或待未完成任务数
     * @param userId
     * @return
     */
    int getUsedTaskCnt(@Param("userId") Integer userId);

    /**
     * 
     * @Title: getServiceTimeConflictTaskCnt 
     * @author 王林江
     * @Description:服务池选择服务人员时，查看服务人员服务任务发生冲突的任务数
     * @param webUserSelectPO 
     * @return
     */
    int getServiceTimeConflictTaskCnt(WebUserSelectPO webUserSelectPO);

    /**
     * 
     * @Title: selectTaskLikeOrderNo 
     * @author 郭健
     * @Description:获取包含制定订单号内容的订单数
     * @param taskPO
     */
    List<TaskPO> selectTaskLikeOrderNo(TaskPO taskPO);

    /**
     * 
     * @Title: selectTask 
     * @author 郭健
     * @Description:获取任务
     * @param taskPO
     * @return
     */
    List<TaskPO> selectTask(TaskPO taskPO);
    
    List<TaskPO> selectTaskCust(TaskPO taskPO);
    
    int selectTimeOverlapedTask(WebUserSelectPO webUserSelectPO);
    
    /**
     * 
     * @Title: selectTaskPool 
     * @Description:获取任务池信息
     * @param taskPoolPO
     * @return
     */
    List<TaskPoolResultPO> selectTaskPool(TaskPoolPO taskPoolPO);
    
    /**
     * 
     * @Title: selectTotalOrderDuration 
     * @Description:获取预约总时长
     * @param taskPoolPO
     * @return
     */
    Integer selectTaskTotalOrderDuration(TaskManagePO taskManagePO);
    
    /**
     * 
     * @Title: selectUnassignedTaskTotalOrderDuration 
     * @Description:获取预约总时长
     * @param taskPoolPO
     * @return
     */
    Integer selectUnassignedTaskTotalOrderDuration(UnassignedTaskPO unassignedTaskPO);
    
    /**
     * 
     * @Title: selectTotalServiceDuration 
     * @Description:获取服务总时长
     * @param taskPoolPO
     * @return
     */
    Integer selectTaskTotalServiceDuration(TaskManagePO taskManagePO);
    
    /**
     * @Title: selectTaskByPrimaryKey 
     * @Description:根据主键获取服务信息 
     * @param id
     * @return
     */
    TaskPO selectTaskByPrimaryKey(Integer id);
    
    /**
     * @Title: deleteTask 
     * @Description:删除任务
     * @param taskPO
     * @return
     */
    int deleteTask(TaskPO taskPO);
    
    /**
     * @Title: deleteByPrimaryKey
     * @Description:根据主键删除任务
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);
    
}