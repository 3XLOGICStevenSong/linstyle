package cn.com.dbridge.lifecare.service;

import java.text.ParseException;
import java.util.List;

import cn.com.dbridge.lifecare.dao.po.MobileMyTaskCalendarParaPO;
import cn.com.dbridge.lifecare.dao.po.MobileMyTaskCalendarResPO;
import cn.com.dbridge.lifecare.dao.po.MobileMyTaskResPO;
import cn.com.dbridge.lifecare.dao.po.MobileOrderPO;
import cn.com.dbridge.lifecare.dao.po.MobileTaskPO;
import cn.com.dbridge.lifecare.dao.po.TaskPO;
import cn.com.dbridge.lifecare.dao.po.UnassignedTaskResultPO;
import cn.com.dbridge.lifecare.dao.po.UnassignedTaskSeePO;
import cn.com.dbridge.lifecare.framework.base.PageListDTO;
import cn.com.dbridge.lifecare.framework.base.TaskWebPageResult;
import cn.com.dbridge.lifecare.framework.base.WebPageResult;
import cn.com.dbridge.lifecare.framework.dto.TaskDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileCalendarTaskForTaskPoolDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileMyTaskCalendarDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileMyTaskParaDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileMyTaskQueryDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileOrderDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileOrderQueryDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileOrderStatusUpdateDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileTaskDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.WxCalendarTaskDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.WxTaskQueryDetailDTO;
import cn.com.dbridge.lifecare.framework.dto.web.TaskManageDTO;
import cn.com.dbridge.lifecare.framework.dto.web.TaskPoolDTO;
import cn.com.dbridge.lifecare.framework.dto.web.TaskUpdateDTO;
import cn.com.dbridge.lifecare.framework.dto.web.UnassignedTaskAddDTO;
import cn.com.dbridge.lifecare.framework.dto.web.UnassignedTaskDTO;
import cn.com.dbridge.lifecare.framework.dto.web.UnassignedTaskUpdateDTO;
import cn.com.dbridge.lifecare.framework.vo.mobile.WxCalendarTaskVO;
import cn.com.dbridge.lifecare.framework.vo.web.TaskManageVO;
import cn.com.dbridge.lifecare.framework.vo.web.TaskPendingVO;
import cn.com.dbridge.lifecare.framework.vo.web.TaskPoolVO;
import cn.com.dbridge.lifecare.framework.vo.web.UnassignedTaskVO;

public interface TaskService {
    int add(MobileOrderDTO mobileOrderDTO);

    List<TaskPO> queryAll();
    
    List<TaskPO> queryOrder(MobileOrderQueryDTO mobileOrderQueryDTO);

    TaskPO getTaskById(Integer id);

    /**
     * 
     * @Title: queryCalendarTask 
     * @author 郭健
     * @Description:获取本月中有预约的日期 
     * @param mobileCalendarTaskDTO
     * @return
     */
    List<MobileMyTaskCalendarResPO> queryCalendarTaskForMyTask(
            MobileMyTaskCalendarDTO mobileCalendarTaskDTO);

    /**
     * 
     * @Title: queryOrderForMyTask 
     * @author 郭健
     * @Description:获取我的任务页信息
     * @param mobileMyTaskPageDTO
     * @return
     */
    List<MobileMyTaskResPO> queryOrderForMyTask(
            MobileMyTaskParaDTO mobileMyTaskPageDTO);

    /**
     * 
     * @Title: updateOrderStatus
     * @author 郭健 
     * @Description: 修改订单状态
     * @param MobileOrderDTO
     * @return
     */
    int updateOrderStatus(
            MobileOrderStatusUpdateDTO mobileOrderStatusUpdateDTO);

    /**
     * 
     * @Title: selectDetailedOrder
     * @author 郭健 
     * @Description:获取任务详情页信息
     * @param mobileTaskDTO
     * @return
     */
    MobileTaskPO selectDetailedOrder(MobileTaskDTO mobileTaskDTO);

    /**
     * 
     * @Title: queryCalendarTaskForTaskPool 
     * @author 郭健
     * @Description:获取本月任务池中有预约的日期 
     * @param mobileCalendarTaskForTaskPoolDTO
     * @return
     */
    List<MobileMyTaskCalendarParaPO> queryCalendarTaskForTaskPool(
            MobileCalendarTaskForTaskPoolDTO mobileCalendarTaskForTaskPoolDTO);


    /**
     * 
     * @Title: queryOrderNotComplete 
     * @Description: 获取未完成服务列表
     * @param mobileOrderQueryDTO
     * @return
     */
    List<MobileOrderPO> queryOrderNotComplete(
            MobileOrderQueryDTO mobileOrderQueryDTO);

    /**
     * 
     * @Title: queryOrderCompleted 
     * @Description: 获取已完成服务列表
     * @param mobileOrderQueryDTO
     * @return
     */
    List<MobileOrderPO> queryOrderCompleted(
            MobileOrderQueryDTO mobileOrderQueryDTO);


    /**
     * 
     * @Title: queryServiceList 
     * @Description: 获取服务列表信息
     * @param mobileOrderQueryDTO
     * @return
     */
    List<MobileOrderPO> queryServiceList(
            MobileOrderQueryDTO mobileOrderQueryDTO);

    /**
     * 
     * @Title: selectPendingCount
     * @Description:获取待处理订单数
     * @return
     */
    TaskPendingVO selectPendingCount();
    /**
     * 
     * @Title: queryTask
     * @Description: 查询任务
     * @param mobileMyTaskQueryDTO
     * @author chenjianfei
     * @return
     */
    PageListDTO wxQueryTask(MobileMyTaskQueryDTO mobileMyTaskQueryDTO);
    /**
     * 
     * @Title: wxSelectTaskDetail
     * @Description: 查询任务详情
     * @param wxTaskQueryDetailDTO
     * @author chenjianfei
     * @return
     */
    public WxTaskQueryDetailDTO wxSelectTaskDetail(WxTaskQueryDetailDTO wxTaskQueryDetailDTO);
    /**
     * 
     * @Title: queryUnassignedTask 
     * @Description:获取未分配任务管理页信息
     * @author 郭健
     * @param unassignedTaskDTO
     * @return
     * @throws ParseException 
     */
    TaskWebPageResult<List<UnassignedTaskVO>> queryUnassignedTaskManage(
            UnassignedTaskDTO unassignedTaskDTO) throws ParseException;

    /**
     * 
     * @Title: queryTaskManage 
     * @Description:获取服务任务管理页信息
     * @author 郭健
     * @param taskManageDTO
     * @return
     * @throws ParseException 
     */
    TaskWebPageResult<List<TaskManageVO>> queryTaskManage(TaskManageDTO taskManageDTO) throws ParseException;
    /**
     * 
     * @Title: updateUnassignedTask 
     * @Description:编辑未分配页面信息
     * @author 郭健
     * @param webUnassignedUpdateDTO
     * @return
     */
    int updateTaskById(TaskDTO taskDTO);
    
    /**
     * 
     * @Title: addUnassignedTask 
     * @Description:添加未分配任务
     * @author 郭健
     * @param unassignedTaskAddDTO
     * @return
     * @throws ParseException 
     */
    List<String> addUnassignedTask(UnassignedTaskAddDTO unassignedTaskAddDTO) throws ParseException;

    /**
     * 
     * @Title: getCustomById 
     * @author 郭健
     * @Description:根据主键获取客户信息
     * @param id
     * @return
     */
    UnassignedTaskResultPO getCustomById(Integer id);

    /**
     * 
     * @Title: queryServicePersonByOrderNo 
     * @author 郭健
     * @Description:根据订单号获取服务人员信息
     * @param orderNo
     * @return
     */
    List<UnassignedTaskSeePO> queryServicePersonByOrderNo(String orderNo);
    /**
     * 
     * @Title: queryCalendarTask
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param wxCalendarTaskDTO
     */
    List<WxCalendarTaskVO> queryCalendarTask(
            WxCalendarTaskDTO wxCalendarTaskDTO);

    /**
     * 
     * @Title: updateUnassignedTask 
     * @Description: 编辑未分配页面信息 
     * @author 郭健
     * @param unassignedTaskupdateDTO
     * @return
     * @throws ParseException 
     */
    public int updateUnassignedTask(
            UnassignedTaskUpdateDTO unassignedTaskupdateDTO) throws ParseException;
    
    /**
     * 
     * @Title: queryTaskPool 
     * @Description:获取任务池信息
     * @author 郭健
     * @param taskPoolDTO
     * @return
     */
    public WebPageResult<List<TaskPoolVO>> queryTaskPool(TaskPoolDTO taskPoolDTO);

    /**
     * 
     * @Title: updateTask 
     * @Description:修改服务任务
     * @author 郭健
     * @param taskUpdateDTO
     * @return
     * @throws Exception 
     */
    public int updateTask(TaskUpdateDTO taskUpdateDTO) throws Exception;
    
    /**
     * 
     * @Title: deleteTaskById 
     * @Description:根据主键删除任务
     * @author 郭健
     * @param id
     */
    public int deleteTaskById(Integer id);
}
