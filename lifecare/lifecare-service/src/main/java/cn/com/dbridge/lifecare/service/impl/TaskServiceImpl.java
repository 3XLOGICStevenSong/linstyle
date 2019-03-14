package cn.com.dbridge.lifecare.service.impl;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.com.dbridge.lifecare.dao.po.MobileMyTaskCalendarParaPO;
import cn.com.dbridge.lifecare.dao.po.MobileMyTaskCalendarResPO;
import cn.com.dbridge.lifecare.dao.po.MobileMyTaskParaPO;
import cn.com.dbridge.lifecare.dao.po.MobileMyTaskResPO;
import cn.com.dbridge.lifecare.dao.po.MobileOrderPO;
import cn.com.dbridge.lifecare.dao.po.MobileTaskPO;
import cn.com.dbridge.lifecare.dao.po.MobileTaskPoolCalendarParaPO;
import cn.com.dbridge.lifecare.dao.po.SmsMsgPO;
import cn.com.dbridge.lifecare.dao.po.TaskManagePO;
import cn.com.dbridge.lifecare.dao.po.TaskManageResPO;
import cn.com.dbridge.lifecare.dao.po.TaskPO;
import cn.com.dbridge.lifecare.dao.po.TaskPoolPO;
import cn.com.dbridge.lifecare.dao.po.TaskPoolResultPO;
import cn.com.dbridge.lifecare.dao.po.UnassignedTaskPO;
import cn.com.dbridge.lifecare.dao.po.UnassignedTaskResultPO;
import cn.com.dbridge.lifecare.dao.po.UnassignedTaskSeePO;
import cn.com.dbridge.lifecare.dao.po.UserPO;
import cn.com.dbridge.lifecare.dao.po.UserTaskRealPO;
import cn.com.dbridge.lifecare.dao.po.WebUserSelectPO;
import cn.com.dbridge.lifecare.dao.po.WxQueryTaskCalenderPO;
import cn.com.dbridge.lifecare.dao.po.WxTaskQueryDetailPO;
import cn.com.dbridge.lifecare.dao.po.WxTaskQueryPO;
import cn.com.dbridge.lifecare.dao.respository.SmsMsgMapper;
import cn.com.dbridge.lifecare.dao.respository.TaskPOMapper;
import cn.com.dbridge.lifecare.dao.respository.UserPOMapper;
import cn.com.dbridge.lifecare.dao.respository.UserTaskRealMapper;
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
import cn.com.dbridge.lifecare.framework.dto.mobile.WxTaskQueryDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.WxTaskQueryDetailDTO;
import cn.com.dbridge.lifecare.framework.dto.web.TaskManageDTO;
import cn.com.dbridge.lifecare.framework.dto.web.TaskPoolDTO;
import cn.com.dbridge.lifecare.framework.dto.web.TaskUpdateDTO;
import cn.com.dbridge.lifecare.framework.dto.web.UnassignedTaskAddDTO;
import cn.com.dbridge.lifecare.framework.dto.web.UnassignedTaskDTO;
import cn.com.dbridge.lifecare.framework.dto.web.UnassignedTaskUpdateDTO;
import cn.com.dbridge.lifecare.framework.enums.MobileTaskStatusEnum;
import cn.com.dbridge.lifecare.framework.enums.OrderEnum;
import cn.com.dbridge.lifecare.framework.enums.SexEnum;
import cn.com.dbridge.lifecare.framework.exception.CustomException;
import cn.com.dbridge.lifecare.framework.util.AgeUtils;
import cn.com.dbridge.lifecare.framework.util.DateUtils;
import cn.com.dbridge.lifecare.framework.util.PageInitUtils;
import cn.com.dbridge.lifecare.framework.vo.mobile.WxCalendarTaskVO;
import cn.com.dbridge.lifecare.framework.vo.web.TaskManageVO;
import cn.com.dbridge.lifecare.framework.vo.web.TaskPendingVO;
import cn.com.dbridge.lifecare.framework.vo.web.TaskPoolVO;
import cn.com.dbridge.lifecare.framework.vo.web.UnassignedTaskVO;
import cn.com.dbridge.lifecare.service.TaskService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TaskPOMapper taskPOMapper;
    @Autowired
    private SmsMsgMapper smsMsgMapper;
    @Autowired
    private UserTaskRealMapper userTaskRealMapper;
    @Autowired
    private UserPOMapper userPOMapper;
    
    
    @Override
    public int add(MobileOrderDTO mobileOrderDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<TaskPO> queryAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TaskPO> queryOrder(
            MobileOrderQueryDTO mobileOrderQueryDTO) {
        TaskPO queryPO = new TaskPO();
        BeanUtils.copyProperties(mobileOrderQueryDTO, queryPO);
        List<TaskPO> orderPOList = taskPOMapper.selectOrder(queryPO);
        if (CollectionUtils.isEmpty(orderPOList)) {
            return null;
        }
        return orderPOList;
    }

    /**   
     * Title: queryOrderNotComplete  
     * Description:获取未完成服务列表
     * @author 郭健
     * @param orderPO
     * @return   
     * @see cn.com.dbridge.lifecare.service.TaskService#queryOrderByOrderStatus(cn.com.dbridge.lifecare.dao.po.TaskPO)   
     */
    @Override
    public List<MobileOrderPO> queryOrderNotComplete(
            MobileOrderQueryDTO mobileOrderQueryDTO) {
        TaskPO orderPO = new TaskPO();
        BeanUtils.copyProperties(mobileOrderQueryDTO, orderPO);
        List<MobileOrderPO> mobileOrderPOList = taskPOMapper
                .selectOrderNotComplete(orderPO);
        if (CollectionUtils.isEmpty(mobileOrderPOList)) {
            return null;
        }
        return mobileOrderPOList;
    }

    /**   
     * Title: queryOrderCompleted  
     * Description:获取已完成服务列表
     * @author 郭健
     * @param orderPO
     * @return   
     * @see cn.com.dbridge.lifecare.service.TaskService#queryOrderByOrderStatus(cn.com.dbridge.lifecare.dao.po.TaskPO)   
     */
    @Override
    public List<MobileOrderPO> queryOrderCompleted(
            MobileOrderQueryDTO mobileOrderQueryDTO) {
        TaskPO orderPO = new TaskPO();
        BeanUtils.copyProperties(mobileOrderQueryDTO, orderPO);
        List<MobileOrderPO> mobileOrderPOList = taskPOMapper
                .selectOrderCompleted(orderPO);
        if (CollectionUtils.isEmpty(mobileOrderPOList)) {
            return null;
        }
        return mobileOrderPOList;
    }

    /**   
     * Title: queryServiceList  
     * Description:获取服务列表信息  
     * @param mobileOrderQueryDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.TaskService#queryServiceList(cn.com.dbridge.lifecare.framework.dto.mobile.MobileOrderQueryDTO)   
     */
    @Override
    public List<MobileOrderPO> queryServiceList(
            MobileOrderQueryDTO mobileOrderQueryDTO) {
        TaskPO orderPO = new TaskPO();
        BeanUtils.copyProperties(mobileOrderQueryDTO, orderPO);
        List<MobileOrderPO> mobileOrderPOList = taskPOMapper
                .selectOrderByCustomIdAndServicePersonId(orderPO);
        if (CollectionUtils.isEmpty(mobileOrderPOList)) {
            return null;
        }
        return mobileOrderPOList;
    }

    /**   
     * Title: queryCalendarTask
     * @author 郭健
     * Description:获取本月中有预约的日期
     * @param mobileCalendarTaskDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.TaskService#queryCalendarTask(cn.com.dbridge.lifecare.framework.dto.mobile.MobileMyTaskCalendarDTO)   
     */
    @Override
    public List<MobileMyTaskCalendarResPO> queryCalendarTaskForMyTask(
            MobileMyTaskCalendarDTO mobileCalendarTaskDTO) {
        MobileMyTaskCalendarParaPO mobileMyTaskCalendarParaPO = new MobileMyTaskCalendarParaPO();
        BeanUtils.copyProperties(mobileCalendarTaskDTO,
                mobileMyTaskCalendarParaPO);
        List<MobileMyTaskCalendarResPO> mobileMyTaskCalendarResPOList = taskPOMapper
                .selectCalendarTaskForMyTask(mobileMyTaskCalendarParaPO);
        if (CollectionUtils.isEmpty(mobileMyTaskCalendarResPOList)) {
            return null;
        }
        return mobileMyTaskCalendarResPOList;
    }

    /**   
     * Title: queryOrderForMyTask  
     * @author 郭健
     * Description:获取我的任务页信息
     * @param mobileMyTaskPageDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.TaskService#queryOrderForMyTask(cn.com.dbridge.lifecare.framework.dto.mobile.MobileMyTaskPageDTO)   
     */
    @Override
    public List<MobileMyTaskResPO> queryOrderForMyTask(
            MobileMyTaskParaDTO mobileMyTaskPageDTO) {
        MobileMyTaskParaPO mobileMyTaskPagePO = new MobileMyTaskParaPO();
        BeanUtils.copyProperties(mobileMyTaskPageDTO, mobileMyTaskPagePO);
        List<MobileMyTaskResPO> mobileMyTaskPOList = taskPOMapper
                .selectOrderForMyTask(mobileMyTaskPagePO);
        if (CollectionUtils.isEmpty(mobileMyTaskPOList)) {
            return null;
        }
        return mobileMyTaskPOList;
    }

    /**   
     * Title: updateOrderStatus  
     * Description:修改订单状态
     * @author 郭健
     * @param MobileOrderDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.TaskService#updateOrderStatus(cn.com.dbridge.lifecare.framework.dto.mobile.MobileOrderDTO)   
     */
    @Override
    public int updateOrderStatus(
            MobileOrderStatusUpdateDTO mobileOrderStatusUpdateDTO) {
        TaskPO taskPO = new TaskPO();
        BeanUtils.copyProperties(mobileOrderStatusUpdateDTO, taskPO);
        int affectNum = taskPOMapper.updateByPrimaryKey(taskPO);
        return affectNum;
    }

    /**   
     * Title: selectDetailedOrder  
     * Description:获取任务详情页信息
     * @author 郭健
     * @param mobileTaskDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.TaskService#selectDetailedOrder(cn.com.dbridge.lifecare.framework.dto.mobile.MobileTaskDTO)   
     */
    @Override
    public MobileTaskPO selectDetailedOrder(MobileTaskDTO mobileTaskDTO) {
        TaskPO orderPO = new TaskPO();
        BeanUtils.copyProperties(mobileTaskDTO, orderPO);
        MobileTaskPO mobileTaskPO = taskPOMapper.selectDetailedOrder(orderPO);
        if (null == mobileTaskPO) {
            return null;
        }
        return mobileTaskPO;
    }

    /**   
     * Title: queryCalendarTaskForTaskPool  
     * Description:获取本月任务池中有预约的日期 
     * @author 郭健
     * @param mobileCalendarTaskForTaskPoolDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.TaskService#queryCalendarTaskForTaskPool(cn.com.dbridge.lifecare.framework.dto.mobile.MobileCalendarTaskForTaskPoolDTO)   
     */
    @Override
    public List<MobileMyTaskCalendarParaPO> queryCalendarTaskForTaskPool(
            MobileCalendarTaskForTaskPoolDTO mobileCalendarTaskForTaskPoolDTO) {
        MobileTaskPoolCalendarParaPO mobileCalendarTaskForTaskPoolPO = new MobileTaskPoolCalendarParaPO();
        BeanUtils.copyProperties(mobileCalendarTaskForTaskPoolDTO, mobileCalendarTaskForTaskPoolPO);
        List<MobileMyTaskCalendarParaPO> mobileCalendarTaskPOList = taskPOMapper
                .selectCalendarTaskForTaskPool(mobileCalendarTaskForTaskPoolPO);
        if(CollectionUtils.isEmpty(mobileCalendarTaskPOList)) {
            return null;
        }
        return mobileCalendarTaskPOList;
    }
    /**
     * 
     * Title: selectPendingCount
     * Description:获取待处理订单数
     * @return
     * @see cn.com.dbridge.lifecare.service.TaskService#selectPendingCount()
     */
    @Override
    public TaskPendingVO selectPendingCount() {
        TaskPendingVO result = new TaskPendingVO();
        //获取待处理订单数 
        result.setPendingCnt(taskPOMapper.selectPendingCount());
        return result;
    }
    /**
     * 
     * Title: wxQueryTask
     * Description:查询任务
     * @param mobileMyTaskQueryDTO
     * @return
     * @see cn.com.dbridge.lifecare.service.TaskService#wxQueryTask(cn.com.dbridge.lifecare.framework.dto.mobile.MobileMyTaskQueryDTO)
     */
    @Override
    public PageListDTO wxQueryTask(MobileMyTaskQueryDTO mobileMyTaskQueryDTO) {
        WxTaskQueryPO paramWxTaskQueryPO = new WxTaskQueryPO();
        BeanUtils.copyProperties(mobileMyTaskQueryDTO, paramWxTaskQueryPO);
        Integer offset = PageInitUtils.setPageOffset(mobileMyTaskQueryDTO.getOffset());
        Integer limit = PageInitUtils.setPageLimit(mobileMyTaskQueryDTO.getLimit());
        Page<Object> offsetPage = PageHelper.offsetPage(offset, limit);
        List<WxTaskQueryPO> taskQueryPOList = taskPOMapper.wxSelectTask(paramWxTaskQueryPO);
        List<WxTaskQueryDTO> wxTaskQueryDTOList = new ArrayList<WxTaskQueryDTO>();
        Long total = offsetPage.getTotal();
        WxTaskQueryDTO wxTaskQueryDTO = null;
        for(WxTaskQueryPO taskQueryPO: taskQueryPOList) {
            wxTaskQueryDTO = new WxTaskQueryDTO();
            BeanUtils.copyProperties(taskQueryPO, wxTaskQueryDTO);
            wxTaskQueryDTO.setSex(SexEnum.getDesc(wxTaskQueryDTO.getSex()));
            wxTaskQueryDTOList.add(wxTaskQueryDTO);
        }
        return new PageListDTO(total,wxTaskQueryDTOList);
    }
    /**
     * Title: wxSelectTaskDetail
     * Description:查看任务详情
     * @param paramDTO
     * @return
     * @see cn.com.dbridge.lifecare.service.TaskService#wxSelectTaskDetail(cn.com.dbridge.lifecare.framework.dto.mobile.WxTaskQueryDetailDTO)
     */
    @Override
    public WxTaskQueryDetailDTO wxSelectTaskDetail(WxTaskQueryDetailDTO paramDTO) {
        WxTaskQueryDetailPO paramWxTaskQueryDetailPO = new WxTaskQueryDetailPO();
        BeanUtils.copyProperties(paramDTO, paramWxTaskQueryDetailPO);
        WxTaskQueryDetailPO wxTaskQueryDetailPO = taskPOMapper.wxSelectTaskDetail(paramWxTaskQueryDetailPO);
        if(wxTaskQueryDetailPO == null) {
            return null;
        }
        WxTaskQueryDetailDTO wxTaskQueryDetailDTO = new WxTaskQueryDetailDTO();
        BeanUtils.copyProperties(wxTaskQueryDetailPO, wxTaskQueryDetailDTO);
        return wxTaskQueryDetailDTO;
    }
    
    /**   
     * Title: queryUnassignedTaskManage  
     * Description:获取未分配任务管理页信息
     * @author 郭健
     * @param unassignedTaskDTO
     * @return   
     * @throws ParseException 
     * @see cn.com.dbridge.lifecare.service.TaskService#queryUnassignedTask(cn.com.dbridge.lifecare.framework.dto.web.WebUnassignedTaskQueryDTO)   
     */
    @Override
    public TaskWebPageResult<List<UnassignedTaskVO>> queryUnassignedTaskManage(
            UnassignedTaskDTO unassignedTaskDTO) throws ParseException {
    	//页面处理结果
    	TaskWebPageResult<List<UnassignedTaskVO>> taskWebPageResult = new TaskWebPageResult<List<UnassignedTaskVO>>();
        //最小预约时长
    	Integer orderDurationFrom = null;
    	//最大预约时长
        Integer orderDurationTo = null;
        //最小预约日期
    	Date orderDateFrom = null;
    	//最大预约日期
    	Date orderDateTo = null;
    	//最小预约开始时间
    	Date orderBeginTimeFrom = null;
    	//最大预约开始时间
    	Date orderBeginTimeTo = null;
    	if(null != unassignedTaskDTO.getOrderDateFrom()) {
    		orderDateFrom = unassignedTaskDTO.getOrderDateFrom();
    	}
    	if(null != unassignedTaskDTO.getOrderDateTo()) {
    		orderDateTo = unassignedTaskDTO.getOrderDateTo();
    	}
    	if(null != unassignedTaskDTO.getOrderBeginTimeFrom()) {
    		orderBeginTimeFrom = unassignedTaskDTO.getOrderBeginTimeFrom();
    	}
    	if(null != unassignedTaskDTO.getOrderBeginTimeTo()) {
    		orderBeginTimeTo = unassignedTaskDTO.getOrderBeginTimeTo();
    	}
    	if(null != unassignedTaskDTO.getOrderDurationFrom()) {
    		orderDurationFrom = unassignedTaskDTO.getOrderDurationFrom() * 60;
    		if(orderDurationFrom < 0) {
    			throw new CustomException("最小预约时长必须大于等于0");
    		}
        }
    	if(null != unassignedTaskDTO.getOrderDurationTo()) {
    		orderDurationTo = unassignedTaskDTO.getOrderDurationTo() * 60;
    		if(orderDurationTo < 0) {
    			throw new CustomException("最大预约时长必须大于等于0");
    		}
    	}
    	if(null != orderDateFrom && null != orderDateTo && orderDateFrom.compareTo(orderDateTo) == 1) {
    		throw new CustomException("最小预约日期必须小于等于最大预约日期");
    	}
    	if(null != orderBeginTimeFrom && null != orderBeginTimeTo && orderBeginTimeFrom.compareTo(orderBeginTimeTo) == 1) {
    		throw new CustomException("最小预约开始时间必须小于等于最大预约开始时间");
    	}
    	if(null != orderDurationFrom && null != orderDurationTo && orderDurationFrom > orderDurationTo) {
    		throw new CustomException("最小预约时长必须小于等于最大预约时长");
    	}
     	UnassignedTaskVO unassignedTaskVO = null;
        List<UnassignedTaskVO> unassignedTaskVOList = new ArrayList<UnassignedTaskVO>();
    	//分页数据设定
        Integer offset = PageInitUtils.setPageOffset(unassignedTaskDTO.getOffset());
        Integer limit = PageInitUtils.setPageLimit(unassignedTaskDTO.getLimit());
        Page<Object> offsetPage = PageHelper.offsetPage(offset, limit);
        UnassignedTaskPO unassignedTaskPO = new UnassignedTaskPO();
        BeanUtils.copyProperties(unassignedTaskDTO, unassignedTaskPO);
        unassignedTaskPO.setOrderDurationFrom(orderDurationFrom);
        unassignedTaskPO.setOrderDurationTo(orderDurationTo);
        List<UnassignedTaskResultPO> unassignedTaskResultPOList = taskPOMapper
                .selectUnassignedTaskManage(unassignedTaskPO);
        Integer intTotalOrderDuration = taskPOMapper.selectUnassignedTaskTotalOrderDuration(unassignedTaskPO);
        int totalHours = 0;
        int totalMinute = 0;
        String totalOrderDuration = null;
        //将分钟格式的总预约时长转化为小时加分钟模式
        if(null != intTotalOrderDuration) {
        	totalHours = (int) Math.floor(intTotalOrderDuration / 60);
            totalMinute = intTotalOrderDuration % 60;
            if(totalHours != 0) {
            	totalOrderDuration = totalHours + "小时" + totalMinute + "分钟";
            } else {
            	totalOrderDuration = totalMinute + "分钟";
            }
            
        }
        Long total = offsetPage.getTotal();
        if (!CollectionUtils.isEmpty(unassignedTaskResultPOList)) {
        	for (UnassignedTaskResultPO unassignedTaskResultPO : unassignedTaskResultPOList) {
        		Date orderDate = null;
        		Date orderBeginTime = null;
        		Integer intOrderDuration = null;
        		String serviceCategoryName = null;
        		if(null != unassignedTaskResultPO.getOrderDate()) {
        			orderDate = unassignedTaskResultPO.getOrderDate();
        		}
        		if(null != unassignedTaskResultPO.getOrderBeginTime()) {
        			orderBeginTime = unassignedTaskResultPO.getOrderBeginTime();
        		}
        		int hours = 0;
                int minute = 0;
                String orderDuration = null;
        		if(null != unassignedTaskResultPO.getOrderDuration()) {
        			//将分钟格式的预约时常转化为小时加分钟模式
        			intOrderDuration = unassignedTaskResultPO.getOrderDuration();
        			hours = (int) Math.floor(intOrderDuration / 60);
                    minute = intOrderDuration % 60;
                    if(hours != 0) {
                    	orderDuration = hours + "小时" + minute + "分钟";
                    } else {
                    	orderDuration = minute + "分钟";
                    }
                    
        		}
        		if(null != unassignedTaskResultPO.getName()) {
        			serviceCategoryName = unassignedTaskResultPO.getName();
        		}
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd ");
                SimpleDateFormat dh = new SimpleDateFormat("HH:mm");
                Date orderDateTime = null;
                if(null != orderDate && null != orderBeginTime) {
                	orderDateTime = df
                            .parse(ds.format(orderDate)
                                    + dh.format(orderBeginTime).toString());
                } 
                unassignedTaskVO = new UnassignedTaskVO();
                BeanUtils.copyProperties(unassignedTaskResultPO,
                        unassignedTaskVO);
                unassignedTaskVO.setOrderDateTime(orderDateTime);
                unassignedTaskVO.setOrderDuration(orderDuration);
                unassignedTaskVO.setServiceCategoryName(serviceCategoryName);
                unassignedTaskVOList.add(unassignedTaskVO);
            }
        }
        //用户列表信息
        taskWebPageResult.setRows(unassignedTaskVOList);
        taskWebPageResult.setTotalOrderDuration(totalOrderDuration);
        //分页数据设定
        if (unassignedTaskDTO.getOffset() != null
                && unassignedTaskDTO.getLimit() != null) {
            //记录总数
        	taskWebPageResult.setTotal(total);
        	taskWebPageResult.setLimit(limit);
        	taskWebPageResult.setOffset(offset);
        }
        return taskWebPageResult;
    }

    /**   
     * Title: queryTaskManage  
     * Description:获取服务任务管理页信息
     * @param taskManageDTO
     * @return   
     * @throws ParseException 
     * @see cn.com.dbridge.lifecare.service.TaskService#queryTaskManage(cn.com.dbridge.lifecare.framework.dto.web.TaskManageDTO)   
     */
    @Override
    public TaskWebPageResult<List<TaskManageVO>> queryTaskManage(TaskManageDTO taskManageDTO) throws ParseException {
    	List<Integer> orderStatus = taskManageDTO.getOrderStatus();
    	List<Integer> abnormalOrder = taskManageDTO.getAbnormalOrder();
    	List<Byte> orderStatusList = new ArrayList<Byte>();
    	List<Byte> abnormalOrderList = new ArrayList<Byte>();
    	if(null != orderStatus) {
    		for(Integer currentOrderStatus : orderStatus) {
        		byte cOrderStatus = (byte)currentOrderStatus.intValue();
        		orderStatusList.add(cOrderStatus);
        	}
    	}
    	if(null != abnormalOrder) {
    		for(Integer currentAbnormalOrder : abnormalOrder) {
        		byte cAbnormalOrder = (byte)currentAbnormalOrder.intValue();
        		abnormalOrderList.add(cAbnormalOrder);
        	}
    	}
    	//页面处理结果
        TaskWebPageResult<List<TaskManageVO>> taskWebPageResult = new TaskWebPageResult<List<TaskManageVO>>();
    	Integer orderDurationFrom = null;
        Integer orderDurationTo = null;
    	Date orderDateFrom = null;
    	Date orderDateTo = null;
    	Date orderBeginTimeFrom = null;
    	Date orderBeginTimeTo = null;
    	if(null != taskManageDTO.getOrderDateFrom()) {
    		orderDateFrom = taskManageDTO.getOrderDateFrom();
    	}
    	if(null != taskManageDTO.getOrderDateTo()) {
    		orderDateTo = taskManageDTO.getOrderDateTo();
    	}
    	if(null != taskManageDTO.getOrderBeginTimeFrom()) {
    		orderBeginTimeFrom = taskManageDTO.getOrderBeginTimeFrom();
    	}
    	if(null != taskManageDTO.getOrderBeginTimeTo()) {
    		orderBeginTimeTo = taskManageDTO.getOrderBeginTimeTo();
    	}
    	if(null != taskManageDTO.getOrderDurationFrom()) {
    		orderDurationFrom = taskManageDTO.getOrderDurationFrom() * 60;
    		if(orderDurationFrom < 0) {
    			throw new CustomException("最小预约时长必须大于等于0");
    		}
        }
    	if(null != taskManageDTO.getOrderDurationTo()) {
    		orderDurationTo = taskManageDTO.getOrderDurationTo() * 60;
    		if(orderDurationTo < 0) {
    			throw new CustomException("最大预约时长必须大于等于0");
    		}
    	}
    	if(null != orderDateFrom && null != orderDateTo && orderDateFrom.compareTo(orderDateTo) == 1) {
    		throw new CustomException("最小预约日期必须小于等于最大预约日期");
    	}
    	if(null != orderBeginTimeFrom && null != orderBeginTimeTo && orderBeginTimeFrom.compareTo(orderBeginTimeTo) == 1) {
    		throw new CustomException("最小预约开始时间必须小于等于最大预约开始时间");
    	}
    	if(null != orderDurationFrom && null != orderDurationTo && orderDurationFrom > orderDurationTo) {
    		throw new CustomException("最小预约时长必须小于等于最大预约时长");
    	}
    	TaskManageVO taskManageVO = null;
        List<TaskManageVO> taskManageVOList = new ArrayList<TaskManageVO>();
        TaskManagePO taskManagePO = new TaskManagePO();
        Integer limit = PageInitUtils.setPageLimit(taskManageDTO.getLimit());
        Integer offset = PageInitUtils.setPageOffset(taskManageDTO.getOffset());
        Page<Object> offsetPage = PageHelper.offsetPage(offset, limit);
        BeanUtils.copyProperties(taskManageDTO, taskManagePO);
        taskManagePO.setOrderStatus(orderStatusList);
        taskManagePO.setOrderDurationFrom(orderDurationFrom);
        taskManagePO.setOrderDurationTo(orderDurationTo);
        List<TaskManageResPO> taskManageResPOList = taskPOMapper
                .selectTaskManage(taskManagePO);
        //获取预约总时长
        Integer intTotalOrderDuration = taskPOMapper.selectTaskTotalOrderDuration(taskManagePO);
        //获取服务总时长
        Integer intTotalServiceDuration = taskPOMapper.selectTaskTotalServiceDuration(taskManagePO);
        int totalOrderHours = 0;
        int totalOrderMinute = 0;
        int totalServiceHours = 0;
        int totalServiceMinute = 0;
        String totalOrderDuration = null;
        String totalServiceDuration = null;
        //将预约总时长转化为“时：分”格式
        if(null != intTotalOrderDuration) {
        	totalOrderHours = (int) Math.floor(intTotalOrderDuration / 60);
            totalOrderMinute = intTotalOrderDuration % 60;
            if(totalOrderHours != 0) {
            	totalOrderDuration = totalOrderHours + "小时" + totalOrderMinute + "分钟";
            } else {
            	totalOrderDuration = totalOrderMinute + "分钟";
            }
            
        }
        //将服务总时长转化为“时：分”格式
        if(null != intTotalServiceDuration) {
        	totalServiceHours = (int) Math.floor(intTotalServiceDuration / 60);
            totalServiceMinute = intTotalServiceDuration % 60;
            if(totalServiceHours != 0) {
            	totalServiceDuration = totalServiceHours + "小时" + totalServiceMinute + "分钟";
            } else {
            	totalServiceDuration = totalServiceMinute + "分钟";
            }
            
        }
        Long total = offsetPage.getTotal();
        if (!CollectionUtils.isEmpty(taskManageResPOList)) {
            for (TaskManageResPO taskManageResPO : taskManageResPOList) {
            	Integer taskId = null;
            	Date orderDate = null;
            	Date orderBeginTime = null;
            	Integer intOrderDuration = null;
            	Integer intServiceDuration = null;
            	if(null != taskManageResPO.getId()) {
            		taskId = taskManageResPO.getId();
            	}
            	Byte currentOrderStatus = null;
            	TaskPO taskPO = new TaskPO();
            	taskPO.setId(taskId);
            	List<TaskPO> taskPOList = taskPOMapper.selectTask(taskPO);
            	if(!CollectionUtils.isEmpty(taskPOList)) {
            		TaskPO currentTask = taskPOList.get(0);
            		currentOrderStatus = currentTask.getOrderStatus();
            	}
            	if(null != taskManageResPO.getOrderDate()) {
            		orderDate = taskManageResPO.getOrderDate();
            	}
            	if(null != taskManageResPO.getOrderBeginTime()) {
            		orderBeginTime = taskManageResPO.getOrderBeginTime();
            	}
            	String orderDuration = null;
            	if(null != taskManageResPO.getOrderDuration()) {
            		intOrderDuration = taskManageResPO.getOrderDuration();
            		int orderHours = (int) Math.floor(intOrderDuration / 60);
                    int orderMinute = intOrderDuration % 60;
                    if(orderHours != 0) {
                    	orderDuration = orderHours + "小时" + orderMinute + "分钟";
                    } else {
                    	orderDuration = orderMinute + "分钟";
                    }
            	}
            	String serviceDuration = null;
            	if(null != taskManageResPO.getServiceDuration()) {
            		intServiceDuration = taskManageResPO.getServiceDuration();
            		int serviceHours = (int) Math.floor(intServiceDuration / 60);
                    int serviceMinute = intServiceDuration % 60;
                    if(serviceHours != 0) {
                    	serviceDuration = serviceHours + "小时" + serviceMinute + "分钟";
                    } else {
                    	serviceDuration = serviceMinute + "分钟";
                    }
            	}
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd ");
                SimpleDateFormat dh = new SimpleDateFormat("HH:mm");
                Date orderDateTime = null;
                if(null != orderDate && null != orderBeginTime) {
                	orderDateTime = df
                            .parse(ds.format(orderDate)
                                    + dh.format(orderBeginTime).toString());
                }
                //添加订单实际开始时间
                Date serviceBeginTime_ = null;
                Date serviceBeginTime = taskManageResPO.getServiceBeginTime();
                if(null != orderDate && null != serviceBeginTime) {
                	serviceBeginTime_ = df
                            .parse(ds.format(orderDate)
                                    + dh.format(serviceBeginTime).toString());
                }
                taskManageVO = new TaskManageVO();
                BeanUtils.copyProperties(taskManageResPO, taskManageVO);
                taskManageVO.setOrderDateTime(orderDateTime);
                taskManageVO.setOrderDuration(orderDuration);
                taskManageVO.setServiceDuration(serviceDuration);
                taskManageVO.setServiceBeginTime(serviceBeginTime_);
//                UserTaskRealDTO userTaskRealDTO = new UserTaskRealDTO();
//                userTaskRealDTO.setTaskId(taskId);
                UserTaskRealPO userTaskRealPO = new UserTaskRealPO();
                userTaskRealPO.setTaskId(taskId);
                //判断当前任务是否已读
                List<UserTaskRealPO> userTaskRealPOList = userTaskRealMapper.selectReadOrNot(userTaskRealPO);
                //是否查看标志为未查看
                Byte seeFlg = 0;
                //若查询集合不为空，判定已读
                if(!CollectionUtils.isEmpty(userTaskRealPOList)) {
                	seeFlg = 1;
                } 
                if(currentOrderStatus == 1) {
                	seeFlg = null;
                }
                taskManageVO.setSeeFlg(seeFlg);
                taskManageVOList.add(taskManageVO);
            }
            taskWebPageResult.setTotalOrderDuration(totalOrderDuration);
            taskWebPageResult.setTotalServiceDuration(totalServiceDuration);
        }
        //用户列表信息
        taskWebPageResult.setRows(taskManageVOList);
        //分页数据设定
        if (taskManageDTO.getOffset() != null
                && taskManageDTO.getLimit() != null) {
            //记录总数
        	taskWebPageResult.setTotal(total);
        }
        return taskWebPageResult;
    }
    
    /**   
     * Title: updateTaskById  
     * Description:
     * @author 郭健
     * @param taskDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.TaskService#updateUnassignedTask(cn.com.dbridge.lifecare.framework.dto.web.WebUnassignedTaskUpdateDTO)   
     */
    @SuppressWarnings("deprecation")
	@Override
    public int updateTaskById(TaskDTO taskDTO) {
		TaskPO taskPO = new TaskPO();
        TaskPO resTaskPO = taskPOMapper.wxSelectTaskByPrimaryKey(taskDTO.getId());
        //获取当前时间
        Date now = new Date();
        //订单日期
        Date orderDate = resTaskPO.getOrderDate();
        Byte orderStatus = taskDTO.getOrderStatus();
        String msg = "该订单未在param时间范围内，不能param服务";
        if(MobileTaskStatusEnum.STARTED.value.equals(orderStatus)){
            msg = msg.replaceAll("param","开始");
        }else if(MobileTaskStatusEnum.FINISHED.value.equals(orderStatus)){
            msg = msg.replaceAll("param","完成");
        }
        if(DateUtils.dateCompare(orderDate,new Date()) != 0){
            throw new CustomException(msg);
        }
        if (MobileTaskStatusEnum.STARTED.value.equals(orderStatus)) {
            //订单的开始时间
            Calendar instance = Calendar.getInstance();
            Date orderBeginTime = resTaskPO.getOrderBeginTime();
            orderBeginTime.setYear(instance.get(Calendar.YEAR) - 1900);
            orderBeginTime.setMonth(instance.get(Calendar.MONTH));
            orderBeginTime.setDate(instance.get(Calendar.DAY_OF_MONTH));
            logger.debug("开始订单:{},订单开始时间{},当前时间{}",taskDTO.getId(),orderBeginTime,now);
			//如果现在时间大于订单预约开始时间10分钟或小于订单开始时间10分钟提示异常
            if(DateUtils.between(orderBeginTime,now)> OrderEnum.EXCEPTION_TIME.value
                    || DateUtils.between(orderBeginTime,now) < -OrderEnum.EXCEPTION_TIME.value){
                throw new CustomException(msg);
            }
            //开始
			taskDTO.setServiceBeginTime(new Date());
		} else if (MobileTaskStatusEnum.FINISHED.value.equals(taskDTO.getOrderStatus())) {
            //订单的结束时间
            Calendar instance = Calendar.getInstance();
            Date orderEndTime = resTaskPO.getOrderEndTime();
            orderEndTime.setYear(instance.get(Calendar.YEAR) - 1900);
            orderEndTime.setMonth(instance.get(Calendar.MONTH));
            orderEndTime.setDate(instance.get(Calendar.DAY_OF_MONTH));
            logger.debug("完成订单:{},订单结束时间{},当前时间{}",taskDTO.getId(),orderEndTime,now);
            //如果现在时间大于订单预约结束时间10分钟或小于订单结束时间10分钟提示异常
            if(DateUtils.between(orderEndTime,now) > OrderEnum.EXCEPTION_TIME.value
                    || DateUtils.between(orderEndTime,now) < -OrderEnum.EXCEPTION_TIME.value){
                throw new CustomException(msg);
            }
			//完成
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); 
			taskDTO.setServiceEndTime(now);
			Long finishedTime;
			try {
				finishedTime = df.parse(df.format(now)).getTime();
				//获取任务的实际开始时间
				Long beginTime = resTaskPO.getServiceBeginTime().getTime();
				//设置任务的实际时长
				taskDTO.setServiceDuration((int)(finishedTime - beginTime)/(1000 * 60));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		BeanUtils.copyProperties(taskDTO, taskPO);
		int affectNum = taskPOMapper.updateByPrimaryKey(taskPO);
		return affectNum;
    }

    /**
     * 
     * @Title: updateUnassignedTask 
     * @Description: 编辑未分配页面信息 
     * @author 郭健
     * @param unassignedTaskupdateDTO
     * @return
     * @throws ParseException 
     */
    @Override
    public int updateUnassignedTask(
            UnassignedTaskUpdateDTO unassignedTaskupdateDTO) throws ParseException {
    	Date orderBeginTime = unassignedTaskupdateDTO.getOrderBeginTime();
    	Date orderEndTime = unassignedTaskupdateDTO.getOrderEndTime();
    	String formatOrderBeginTime = null;
    	String formatOrderEndTime = null;
    	Long ofd = null;
    	Long otd = null;
    	int orderDuration = 0;
    	Integer id = null;
    	Integer backendPersonId = null;
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
    	if(null != orderBeginTime) {
    		formatOrderBeginTime = sdf.format(orderBeginTime);
    		ofd = sdf.parse(formatOrderBeginTime).getTime();
    	}
    	if(null != orderEndTime) {
    		formatOrderEndTime = sdf.format(orderEndTime);
    		otd = sdf.parse(formatOrderEndTime).getTime();
    	}
    	if(null != ofd && null != otd) {
    		orderDuration = (int) ((otd - ofd)/(1000 * 60));
    	}
        if(null != unassignedTaskupdateDTO.getId()) {
        	id = unassignedTaskupdateDTO.getId();
        }
        if(null != unassignedTaskupdateDTO.getBackendPersonId()) {
        	backendPersonId = unassignedTaskupdateDTO.getBackendPersonId();
        }
        TaskPO taskPOPara = new TaskPO();
        taskPOPara.setId(id);
        int affectNum = 0;
        List<TaskPO> taskPOResList = taskPOMapper.selectTask(taskPOPara);
        if(!CollectionUtils.isEmpty(taskPOResList)) {
//        	for(TaskPO taskPO : taskPOResList) {
//        		String orderNo = taskPO.getOrderNo();
//                String subOrderNo = orderNo.substring(8);
//                Date orderDate = unassignedTaskupdateDTO.getOrderDate();
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
//                        "yyyy-MM-dd");
//                String sOrderDate = simpleDateFormat.format(orderDate);
//                String subOrderDate = sOrderDate.substring(2, 4)
//                        + sOrderDate.substring(5, 7) + sOrderDate.substring(8);
//                String serviceCategoryCode = unassignedTaskupdateDTO
//                        .getServiceCategoryCode();
//                String orderNoForUpdate = serviceCategoryCode + subOrderDate + "-"
//                        + subOrderNo;
            TaskPO taskPOTemp = new TaskPO();
            BeanUtils.copyProperties(unassignedTaskupdateDTO, taskPOTemp);
//                taskPOTemp.setOrderNo(orderNoForUpdate);
            taskPOTemp.setOrderDuration(orderDuration);
            taskPOTemp.setUpdateBy(backendPersonId);
            taskPOTemp.setUpdateTime(new Date());
            affectNum = taskPOMapper.updateByPrimaryKey(taskPOTemp);
//                            smsMsgPO
//                            smsMsgMapper.selectSmsMsg(smsMsgPO);
//          未分配任务修改的情况
            if (affectNum > 0) {
                //订单编号
                Integer taskId = taskPOTemp.getId();
                TaskPO updatedCurrentTask = taskPOMapper.selectTaskByPrimaryKey(taskId);
                //删除既存的短信提醒
                smsMsgMapper.deleteByTaskId(taskId);

                //订单状态(3.已完成 4.取消)以外的情况
                if (updatedCurrentTask.getOrderStatus() != null
                        && updatedCurrentTask.getOrderStatus() != 3
                        && updatedCurrentTask.getOrderStatus() != 4) {
                    //添加新的客户短信提醒
                    //短信消息表PO
                    SmsMsgPO smsMsgPO = new SmsMsgPO();
                    //订单编号
                    smsMsgPO.setTaskId(taskId);
                    //创建人
                    smsMsgPO.setCreateBy(backendPersonId);
                    smsMsgPO.setUpdateBy(backendPersonId);
                    smsMsgPO.setUpdateTime(new Date());
                    //未分配任务创建的时候，为客户追加短信处理
                    smsMsgMapper.insertCustomSmsMsgByOrderId(smsMsgPO);
                }
                if(updatedCurrentTask.getOrderStatus() != null && updatedCurrentTask.getOrderStatus() == 4) {
                	smsMsgMapper.deleteByTaskId(taskId);
                }
            }
        }
        return affectNum;
    }
    
    /**   
     * Title: updateTask  
     * Description:服务任务编辑页面
     * @param taskUpdateDTO
     * @return   
     * @throws ParseException 
     * @throws Exception 
     * @see cn.com.dbridge.lifecare.service.TaskService#updateTask(cn.com.dbridge.lifecare.framework.dto.web.TaskUpdateDTO)   
     */
    @Override
    public int updateTask(TaskUpdateDTO taskUpdateDTO) throws CustomException, ParseException {
    	Integer affectNum = 0;
    	Integer id = taskUpdateDTO.getId();
    	Byte orderStatus = null;
    	TaskPO taskPO = new TaskPO();
    	taskPO.setId(id);
    	TaskPO currentTaskPO = null;
    	String currentOrderAddr = null;
    	Date orderDate = null;
    	Date orderBeginTime = null;
    	Date orderEndTime = null;
    	Date serviceBeginTime = null;
    	Date serviceEndTime = null;
    	String orderAddr = null;
    	String formatOrderBeginTime = null;
        String formatOrderEndTime = null;
        String formatServiceBeginTime = null;
        String formatServiceEndTime = null;
        Long ofd = null;
        Long sfd = null;
        Long otd = null;
        Long std = null;
        int orderDuration = 0;
        int serviceDuration = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	if(null != taskUpdateDTO.getOrderAddr()) {
    		orderAddr = taskUpdateDTO.getOrderAddr();
    	}
    	if(null != taskUpdateDTO.getOrderDate()) {
    		orderDate = taskUpdateDTO.getOrderDate();
    	}
    	if(null != taskUpdateDTO.getOrderBeginTime()) {
    		orderBeginTime = taskUpdateDTO.getOrderBeginTime();
    	}
    	if(null != taskUpdateDTO.getOrderEndTime()) {
    		orderEndTime = taskUpdateDTO.getOrderEndTime();
    	}
    	if(null != taskUpdateDTO.getServiceBeginTime()) {
    		serviceBeginTime = taskUpdateDTO.getServiceBeginTime();
    	}
    	if(null != taskUpdateDTO.getServiceEndTime()) {
    		serviceEndTime = taskUpdateDTO.getServiceEndTime();
    	}
    	List<TaskPO> taskPOList = taskPOMapper.selectTask(taskPO);
    	if(!CollectionUtils.isEmpty(taskPOList)) {
    		currentTaskPO = taskPOList.get(0);
    		currentOrderAddr = currentTaskPO.getOrderAddr();
    	}
    	if(null == currentOrderAddr) {
    		if(null == serviceEndTime) {
    			taskUpdateDTO.setOrderAddr("");
            } else {
            	taskUpdateDTO.setOrderAddr(orderAddr);
            }
    	} else {
    		if(null != serviceEndTime) {
    			taskUpdateDTO.setOrderAddr(orderAddr);
    		}
    	}
        if(null != orderBeginTime) {
        	formatOrderBeginTime = sdf.format(orderBeginTime);
        	ofd = sdf.parse(formatOrderBeginTime).getTime();
        }
        if(null != orderEndTime) {
        	formatOrderEndTime = sdf.format(orderEndTime);
        	otd = sdf.parse(formatOrderEndTime).getTime();  
        }
        if(null != serviceBeginTime) {
        	formatServiceBeginTime = sdf.format(serviceBeginTime);
        	sfd = sdf.parse(formatServiceBeginTime).getTime();
        }
        if(null != serviceEndTime) {
        	formatServiceEndTime = sdf.format(serviceEndTime);
        	std = sdf.parse(formatServiceEndTime).getTime();
        }
        if(null != otd && null != ofd) {
        	orderDuration = (int) ((otd - ofd)/(1000 * 60));
        }
        if(null != std && null != sfd) {
        	serviceDuration = (int) ((std - sfd)/(1000 * 60));
        }
        if(taskUpdateDTO.getOrderStatus() != null && taskUpdateDTO.getOrderStatus() == 4) {
        	//取消
        	orderStatus = taskUpdateDTO.getOrderStatus();
        } else if(taskUpdateDTO.getOrderStatus() != null && taskUpdateDTO.getOrderStatus() != 4) {
        	if(null != taskUpdateDTO.getServicePersonId() && null != taskUpdateDTO.getServiceBeginTime() && null == taskUpdateDTO.getServiceEndTime()) {
            	//待完成
            	orderStatus = 2;
            	taskUpdateDTO.setCancelReson("");
            } else if(null != taskUpdateDTO.getServicePersonId() && null != taskUpdateDTO.getServiceBeginTime() && null != taskUpdateDTO.getServiceEndTime()) {
            	//已完成
            	orderStatus = 3;
            	taskUpdateDTO.setCancelReson("");
            } else if(null == taskUpdateDTO.getServicePersonId() && null == taskUpdateDTO.getServiceBeginTime() && null == taskUpdateDTO.getServiceEndTime()) {
            	//待分配
            	orderStatus = 1;
            	taskUpdateDTO.setCancelReson("");
            } else if(null != taskUpdateDTO.getServicePersonId() && null == taskUpdateDTO.getServiceBeginTime() && null == taskUpdateDTO.getServiceEndTime()) {
            	//待开始
            	orderStatus = 5;
            	taskUpdateDTO.setCancelReson("");
            }
        } else if(taskUpdateDTO.getOrderStatus() == null) {
        	if(null != taskUpdateDTO.getServicePersonId() && null != taskUpdateDTO.getServiceBeginTime() && null == taskUpdateDTO.getServiceEndTime()) {
            	//待完成
            	orderStatus = 2;
            	taskUpdateDTO.setCancelReson("");
            } else if(null != taskUpdateDTO.getServicePersonId() && null != taskUpdateDTO.getServiceBeginTime() && null != taskUpdateDTO.getServiceEndTime()) {
            	//已完成
            	orderStatus = 3;
            	taskUpdateDTO.setCancelReson("");
            } else if(null == taskUpdateDTO.getServicePersonId() && null == taskUpdateDTO.getServiceBeginTime() && null == taskUpdateDTO.getServiceEndTime()) {
            	//待分配
            	orderStatus = 1;
            	taskUpdateDTO.setCancelReson("");
            } else if(null != taskUpdateDTO.getServicePersonId() && null == taskUpdateDTO.getServiceBeginTime() && null == taskUpdateDTO.getServiceEndTime()) {
            	//待开始
            	orderStatus = 5;
            	taskUpdateDTO.setCancelReson("");
            }
        }
    	Integer servicePersonId = null;
    	if(null != taskUpdateDTO.getServicePersonId()) {
    		servicePersonId = taskUpdateDTO.getServicePersonId();
    		UserPO servicePersonPO = userPOMapper.selectByPrimaryKey(servicePersonId);
    		// 0.男 1.女
        	Byte servicePersonSex = null;
        	if(null != servicePersonPO.getSex()) {
        		servicePersonSex = servicePersonPO.getSex();
        	}
        	// 0.不限 1.男 2.女
        	Byte sexRequire = null;
        	if(null != taskUpdateDTO.getSexRequire()) {
        		sexRequire = taskUpdateDTO.getSexRequire();
        	}
        	if(null != servicePersonSex && null != sexRequire && sexRequire != 0) {
        		if(servicePersonSex == 0 && sexRequire != 1) {
        			throw new CustomException("服务人员性别必须符合性别要求");
        		}
        		if(servicePersonSex == 1 && sexRequire != 2) {
        			throw new CustomException("服务人员性别必须符合性别要求");
        		}
        	}
        	String serviceCategoryLevel = null;
        	if(null != servicePersonPO.getServiceCategoryLevel()) {
        		serviceCategoryLevel = servicePersonPO.getServiceCategoryLevel();
        	}
        	String ServiceCategoryCode = null;
        	if(null != taskUpdateDTO.getServiceCategoryCode()) {
        		ServiceCategoryCode = taskUpdateDTO.getServiceCategoryCode();
        	}
        	if(null != serviceCategoryLevel && null != ServiceCategoryCode && ServiceCategoryCode.equals("A")) {
        		if(!(serviceCategoryLevel.equals("001") || serviceCategoryLevel.equals("011") || serviceCategoryLevel.equals("101") || serviceCategoryLevel.equals("111"))) {
        			throw new CustomException("服务人员的服务类别等级必须符合任务的服务类别");
        		}
        	}
        	if(null != serviceCategoryLevel && null != ServiceCategoryCode && ServiceCategoryCode.equals("B")) {
        		if(!(serviceCategoryLevel.equals("010") || serviceCategoryLevel.equals("011") || serviceCategoryLevel.equals("110") || serviceCategoryLevel.equals("111"))) {
        			throw new CustomException("服务人员的服务类别等级必须符合任务的服务类别");
        		}
        	}
        	if(null != serviceCategoryLevel && null != ServiceCategoryCode && ServiceCategoryCode.equals("C")) {
        		if(!(serviceCategoryLevel.equals("100") || serviceCategoryLevel.equals("101") || serviceCategoryLevel.equals("110") || serviceCategoryLevel.equals("111"))) {
        			throw new CustomException("服务人员的服务类别等级必须符合任务的服务类别");
        		}
        	}
        	Date servicePersonOrderDate = null;
        	if(null != taskUpdateDTO.getOrderDate()) {
        		servicePersonOrderDate = taskUpdateDTO.getOrderDate();
        	}
        	Date servicePersonOrderBeginTime = null;
        	if(null != taskUpdateDTO.getOrderBeginTime()) {
        		servicePersonOrderBeginTime = taskUpdateDTO.getOrderBeginTime();
        	}
        	Date servicePersonOrderEndTime = null;
        	if(null != taskUpdateDTO.getOrderEndTime()) {
        		servicePersonOrderEndTime = taskUpdateDTO.getOrderEndTime();
        	}
        	WebUserSelectPO checkUserSelectPO = new WebUserSelectPO();
        	checkUserSelectPO.setId(id);
        	checkUserSelectPO.setOrderDate(servicePersonOrderDate);
        	checkUserSelectPO.setOrderBeginTime(servicePersonOrderBeginTime);
        	checkUserSelectPO.setOrderEndTime(servicePersonOrderEndTime);
        	checkUserSelectPO.setUserId(servicePersonId);
        	//判定人员是否冲突
        	int num = taskPOMapper.selectTimeOverlapedTask(checkUserSelectPO);
        	if(num > 0) {
        		throw new CustomException("所选服务人员在任务的预约时间内有其他任务");
        	}
        	String serviceDesc = null;
        	if(null != taskUpdateDTO.getServiceDesc()) {
        		serviceDesc = taskUpdateDTO.getServiceDesc();
        	}
        	if((null != serviceBeginTime && null == servicePersonId) || (null != serviceEndTime && null == servicePersonId) || (null != serviceDesc && null == servicePersonId)) {
        		throw new CustomException("如果服务人员信息不为空，则服务人员工号不能为空");
        	}
        	if(null == serviceBeginTime && null != serviceEndTime) {
        		throw new CustomException("如果服务结束时间不为空，则服务开始时间不能为空");
        	}
    	}
    	WebUserSelectPO webUserSelectPO = new WebUserSelectPO();
    	webUserSelectPO.setId(taskUpdateDTO.getId());
    	webUserSelectPO.setOrderDate(orderDate);
    	webUserSelectPO.setOrderBeginTime(taskUpdateDTO.getOrderBeginTime());
    	webUserSelectPO.setOrderEndTime(taskUpdateDTO.getOrderEndTime());
    	webUserSelectPO.setUserId(servicePersonId);
    	int count = taskPOMapper.getServiceTimeConflictTaskCnt(webUserSelectPO);
    	if(count == 0) {
    		TaskPO taskTempPO = new TaskPO();
            BeanUtils.copyProperties(taskUpdateDTO, taskTempPO);
            taskTempPO.setDictTypeCode("SERVER_TYPE");
            taskTempPO.setOrderStatus(orderStatus);
            taskTempPO.setOrderDuration(orderDuration);
            taskTempPO.setServiceDuration(serviceDuration);
            taskTempPO.setUpdateTime(new Date());
            taskTempPO.setUpdateBy(taskUpdateDTO.getBackendPersonId());
            affectNum = taskPOMapper.updateByTaskId(taskTempPO);
            //任务编辑成功的情况
            if (affectNum > 0) {
                //订单编号
                Integer taskId = taskUpdateDTO.getId();
                TaskPO updatedCurrentTask = taskPOMapper.selectTaskByPrimaryKey(taskId);
                //删除既存的短信提醒
                smsMsgMapper.deleteByTaskId(taskId);
                //订单状态(3.已完成 4.取消)以外的情况
                if (updatedCurrentTask.getOrderStatus() != null && updatedCurrentTask.getOrderStatus() != 3 && updatedCurrentTask.getOrderStatus() != 4) {
                    //添加新的短信提醒
                    //短信消息表PO
                    SmsMsgPO smsMsgPO = new SmsMsgPO();
                    //订单编号
                    smsMsgPO.setTaskId(taskId);
                    //创建人
                    smsMsgPO.setCreateBy(taskUpdateDTO.getBackendPersonId());
                    smsMsgPO.setUpdateBy(taskUpdateDTO.getBackendPersonId());
                    smsMsgPO.setUpdateTime(new Date());
                    //为客户追加短信处理
                    smsMsgMapper.insertCustomSmsMsgByOrderId(smsMsgPO);
                    
                    smsMsgPO.setUserId(servicePersonId);
                    smsMsgPO.setUpdateBy(taskUpdateDTO.getBackendPersonId());
                    smsMsgPO.setUpdateTime(new Date());
                    //为服务人员追加短信处理
                    smsMsgMapper.insertServiceSmsMsgByOrderId(smsMsgPO);
                } 
                if(updatedCurrentTask.getOrderStatus() != null && updatedCurrentTask.getOrderStatus() == 4) {
                	smsMsgMapper.deleteByTaskId(taskId);
                }
            }
    	} else {
    		throw new CustomException("该服务人员已有任务！");
    	} 
		return affectNum;
    }


    /**   
     * Title: addUnassignedTask  
     * Description:添加未分配任务
     * @author 郭健
     * @param unassignedTaskAddDTO
     * @return   
     * @throws ParseException 
     * @see cn.com.dbridge.lifecare.service.TaskService#addUnassignedTask(cn.com.dbridge.lifecare.framework.dto.web.UnassignedTaskAddDTO)   
     */
    @Override
    @Transactional
    public List<String> addUnassignedTask(
            UnassignedTaskAddDTO unassignedTaskAddDTO) throws ParseException {
    	Date orderDate = null;
    	Date orderBeginTime = null;
    	Date orderEndTime = null;
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
    	String formatOrderBeginTime = null;
    	String formatOrderEndTime = null;
    	Long ofd = null;
    	Long otd = null;
    	int orderDuration = 0;
    	String sOrderDate = null;
    	String subOrderDate = null;
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
    	if(null != unassignedTaskAddDTO.getOrderDate()) {
    		orderDate = unassignedTaskAddDTO.getOrderDate();
    		sOrderDate = simpleDateFormat.format(orderDate);
    		//截取预约日期查询拼接订单编号
    		subOrderDate = sOrderDate.substring(2, 4) + sOrderDate.substring(5, 7)
            + sOrderDate.substring(8);
    	}
    	if(null != unassignedTaskAddDTO.getOrderBeginTime()) {
    		orderBeginTime = unassignedTaskAddDTO.getOrderBeginTime();
    		formatOrderBeginTime = sdf.format(orderBeginTime);
    		ofd = sdf.parse(formatOrderBeginTime).getTime();
    	}
    	if(null != unassignedTaskAddDTO.getOrderEndTime()) {
    		orderEndTime = unassignedTaskAddDTO.getOrderEndTime();
    		formatOrderEndTime = sdf.format(orderEndTime);
    		otd = sdf.parse(formatOrderEndTime).getTime();  
    	}
        if(null != ofd && null != otd) {
        	//预约时长
        	orderDuration = (int) ((otd - ofd)/(1000 * 60));
        }
        //保存份数
        Integer saveCnt = unassignedTaskAddDTO.getSaveCnt();
        String serviceCategoryCode = unassignedTaskAddDTO
                .getServiceCategoryCode();
        TaskPO taskPO = new TaskPO();
        taskPO.setOrderNo(subOrderDate);
        Integer maxOrderNo = 0;
        //查询包含截取日期的任务
    	List<TaskPO> taskPOList = taskPOMapper.selectTaskLikeOrderNo(taskPO);
        List<Integer> intOrderNoList = new ArrayList<Integer>();
        //取得最大订单号的数值
        if(!CollectionUtils.isEmpty(taskPOList)) {
        	for (TaskPO taskPOT : taskPOList) {
                String orderNo = taskPOT.getOrderNo();
                String subOrderNo = orderNo.substring(8);
                Integer intOrderNo = Integer.parseInt(subOrderNo);
                intOrderNoList.add(intOrderNo);
                maxOrderNo = Collections.max(intOrderNoList);
            }
        }
        List<String> taskIds = new ArrayList<>();
        //根据保存份数保存相应份数的订单，若之前存在当前日期的订单，则订单号继续从最大订单号的尾号数进行累加
        for (int i = 0; i < saveCnt; i++) {
          String orderNo = "";
          DecimalFormat decimalFormat = new DecimalFormat("0000");
          String numFormatStr = "";
          if (maxOrderNo != 0) {
        	  numFormatStr = decimalFormat.format(maxOrderNo + i + 1);
          } else {
        	  numFormatStr = decimalFormat.format(i + 1);
          }
          //拼接订单
          orderNo = serviceCategoryCode + subOrderDate + "-"
                  + numFormatStr;

          //存储订单编号，返回给调用者
          taskIds.add(orderNo);

          TaskPO taskPOTemp = new TaskPO();
          BeanUtils.copyProperties(unassignedTaskAddDTO, taskPOTemp);
          taskPOTemp.setOrderDuration(orderDuration);
          taskPOTemp.setDictTypeCode("SERVER_TYPE");
          taskPOTemp.setOrderNo(orderNo);
          Byte orderStatus = 1;
          taskPOTemp.setOrderStatus(orderStatus);
          taskPOTemp.setCreateTime(new Date());
          taskPOTemp.setCreateBy(unassignedTaskAddDTO.getBackendPersonId());
          taskPOTemp.setUpdateTime(new Date());
          taskPOTemp.setUpdateBy(unassignedTaskAddDTO.getBackendPersonId());
          taskPOTemp.setServiceDuration(0);
          //影响数据的条数
          int affectNum = 0;
          //任务表追加
          affectNum = taskPOMapper.insert(taskPOTemp);
          //追加成功的场合
          if (affectNum > 0) {
              //订单编号
              Integer taskId = taskPOTemp.getId();
              //短信消息表PO
              SmsMsgPO smsMsgPO = new SmsMsgPO();
              //订单编号
              smsMsgPO.setTaskId(taskId);
              //创建人
              smsMsgPO.setCreateBy(unassignedTaskAddDTO.getBackendPersonId());
              smsMsgPO.setUpdateBy(unassignedTaskAddDTO.getBackendPersonId());
              smsMsgPO.setUpdateTime(new Date());
              //未分配任务创建的时候，为客户追加短信处理
              smsMsgMapper.insertCustomSmsMsgByOrderId(smsMsgPO);
          }
      }
        return taskIds;
    }

    /**   
     * Title: getCustomById  
     * Description:根据主键获取客户信息
     * @author 郭健
     * @param id
     * @return   
     * @see cn.com.dbridge.lifecare.service.TaskService#selectCustomById(java.lang.Integer)   
     */
    @Override
    public UnassignedTaskResultPO getCustomById(Integer id) {
        UnassignedTaskResultPO unassignedTaskResultPO = taskPOMapper
                .selectCustomById(id);
        if (null == unassignedTaskResultPO) {
            return null;
        }
        return unassignedTaskResultPO;
    }

    /**   
     * Title: queryServicePersonByOrderNo  
     * Description:根据订单号获取服务人员信息
     * @author 郭健
     * @param orderNo
     * @return   
     * @see cn.com.dbridge.lifecare.service.TaskService#selectServicePersonByOrderNo(java.lang.String)   
     */
    @Override
    public List<UnassignedTaskSeePO> queryServicePersonByOrderNo(
            String orderNo) {
        List<UnassignedTaskSeePO> webUnassignedTaskSeePOList = taskPOMapper
                .selectServicePersonByOrderNo(orderNo);
        if (CollectionUtils.isEmpty(webUnassignedTaskSeePOList)) {
            return null;
        }
        return webUnassignedTaskSeePOList;
    }

    /**   
     * Title: queryCalendarTask
     * Description:
     * @author 
     * @param wxCalendarTaskDTO
     * @return   
     */
    @Override
    public List<WxCalendarTaskVO> queryCalendarTask(WxCalendarTaskDTO wxCalendarTaskDTO) {
        //处理结果VOList
        List<WxCalendarTaskVO> rtnVOList = null;
        //我的任务池或者任务池日历PO
        WxQueryTaskCalenderPO paramWxTaskQueryPO = new WxQueryTaskCalenderPO();
        //我的任务池或者任务池日历PO设定
        BeanUtils.copyProperties(wxCalendarTaskDTO, paramWxTaskQueryPO);
        //我的任务池或者任务池日历取得
        List<WxQueryTaskCalenderPO> wxQueryTaskCalenderPOList = taskPOMapper.selectCalendarTask(paramWxTaskQueryPO);
        if (!CollectionUtils.isEmpty(wxQueryTaskCalenderPOList)) {
            //处理结果VOList
            rtnVOList = new ArrayList<WxCalendarTaskVO>();
            for (WxQueryTaskCalenderPO wxQueryTaskCalenderPO : wxQueryTaskCalenderPOList) {
                //处理结果VO
                WxCalendarTaskVO rtnVO = new WxCalendarTaskVO();
                //订单日期
                rtnVO.setOrderDate(wxQueryTaskCalenderPO.getOrderDate());

                //查询当前日期的我的任务和任务池未查看的任务数
                //订单日期设定
                paramWxTaskQueryPO.setOrderDate(wxQueryTaskCalenderPO.getOrderDate());
                //获取当前日期的我的任务和任务池未查看的任务数
                int noSeeTaskCnt = taskPOMapper.getNoSeeTaskCnt(paramWxTaskQueryPO);
                //有未查看的任务场合
                //查看标志(0:未查看 1:查看)
                if (noSeeTaskCnt > 0) {
                    rtnVO.setSeeFlg("0");
                } else {
                    rtnVO.setSeeFlg("1");
                }
                rtnVOList.add(rtnVO);
            }
        }
        return rtnVOList;
    }

    /**   
     * Title: queryTaskPool  
     * Description:获取任务池信息
     * @param taskPoolDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.TaskService#queryTaskPool(cn.com.dbridge.lifecare.framework.dto.web.TaskPoolDTO)   
     */
    @Override
    public WebPageResult<List<TaskPoolVO>> queryTaskPool(TaskPoolDTO taskPoolDTO) {
    	//页面处理结果
        WebPageResult<List<TaskPoolVO>> webPageResult = new WebPageResult<List<TaskPoolVO>>();
        TaskPoolPO taskPoolPO = new TaskPoolPO();
        TaskPoolVO taskPoolVO = null;
        BeanUtils.copyProperties(taskPoolDTO, taskPoolPO);
        List<TaskPoolResultPO> taskPoolResultPOList = taskPOMapper
                .selectTaskPool(taskPoolPO);
        List<TaskPoolVO> taskPoolVOList = new ArrayList<TaskPoolVO>();
        if (!CollectionUtils.isEmpty(taskPoolResultPOList)) {
            for (TaskPoolResultPO taskPoolResultPO : taskPoolResultPOList) {
                String serviceCategoryColor = null;
                if(null != taskPoolResultPO.getDescription()) {
                	serviceCategoryColor = taskPoolResultPO.getDescription();
                }
                Byte sex = null;
                if(null != taskPoolResultPO.getSex()) {
                	sex = taskPoolResultPO.getSex();
                }
                Integer customId = null;
                if(null != taskPoolResultPO.getCustomId()) {
                	customId = taskPoolResultPO.getCustomId();
                }
                UserPO customPO = null;
                String orderAddr = null;
                if(null != customId) {
                	customPO = userPOMapper.selectByPrimaryKey(customId);
                	String streetVillage = null;
                	if(null != customPO) {
                		streetVillage = customPO.getStreetVillage();
                		if(null != streetVillage) {
                			orderAddr = streetVillage;
                		}
                	}
                }
                taskPoolVO = new TaskPoolVO();
                if(null != sex) {
                	if(sex == 0) {
                    	taskPoolVO.setSex("男");
                    } else {
                    	taskPoolVO.setSex("女");
                    }
                }
                //年龄设定
                if (taskPoolResultPO.getBirthday() != null) {
                    //年龄计算
                    Integer age = AgeUtils.getAgeByBirthday(
                    		taskPoolResultPO.getBirthday());
                    //年龄设定
                    taskPoolVO.setAge(age);
                }
                BeanUtils.copyProperties(taskPoolResultPO, taskPoolVO);
                taskPoolVO.setServiceCategoryColor(serviceCategoryColor);
                taskPoolVO.setOrderAddr(orderAddr);
                taskPoolVOList.add(taskPoolVO);
            }
        }
        //用户列表信息
        webPageResult.setRows(taskPoolVOList);
        return webPageResult;
    }

    /**   
     * Title: getTaskById  
     * Description:根据主键获取任务信息
     * @param id
     * @return   
     * @see cn.com.dbridge.lifecare.service.TaskService#getTaskById(java.lang.Integer)   
     */
    @Override
    public TaskPO getTaskById(Integer id) {
        TaskPO taskPO = new TaskPO();
        taskPO.setId(id);
        List<TaskPO> taskPOList = taskPOMapper.selectTask(taskPO);
        if(CollectionUtils.isEmpty(taskPOList)) {
            return null;
        }
        return taskPOList.get(0);
    }

	@Override
	public int deleteTaskById(Integer id) {
		int count = taskPOMapper.deleteByPrimaryKey(id);
		return count;
	}

    
    
}