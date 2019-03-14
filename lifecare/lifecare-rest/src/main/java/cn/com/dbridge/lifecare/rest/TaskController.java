package cn.com.dbridge.lifecare.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import cn.com.dbridge.jtraining.upload.picture.service.impl.StorageProperties;
import cn.com.dbridge.lifecare.framework.vo.web.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.lifecare.dao.po.DistrictPO;
import cn.com.dbridge.lifecare.dao.po.TaskPO;
import cn.com.dbridge.lifecare.dao.po.UserPO;
import cn.com.dbridge.lifecare.dao.po.UserTaskRealPO;
import cn.com.dbridge.lifecare.framework.base.PageListDTO;
import cn.com.dbridge.lifecare.framework.base.PageResult;
import cn.com.dbridge.lifecare.framework.base.Result;
import cn.com.dbridge.lifecare.framework.base.TaskWebPageResult;
import cn.com.dbridge.lifecare.framework.base.WebPageResult;
import cn.com.dbridge.lifecare.framework.dblog.annotation.OperatorLog;
import cn.com.dbridge.lifecare.framework.dto.TaskDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileMyTaskQueryDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.TaskDetailQueryDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.UserTaskPoolRealDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.UserTaskRealDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.WxCalendarTaskDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.WxCustomServicePersonTaskDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.WxShowMyTaskDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.WxShowTaskPoolDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.WxTaskQueryDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.WxTaskQueryDetailDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.WxUpdateTaskStatusDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.WxWaitingServiceDTO;
import cn.com.dbridge.lifecare.framework.dto.web.TaskManageDTO;
import cn.com.dbridge.lifecare.framework.dto.web.TaskPoolDTO;
import cn.com.dbridge.lifecare.framework.dto.web.TaskUpdateDTO;
import cn.com.dbridge.lifecare.framework.dto.web.UnassignedTaskAddDTO;
import cn.com.dbridge.lifecare.framework.dto.web.UnassignedTaskDTO;
import cn.com.dbridge.lifecare.framework.dto.web.UnassignedTaskUpdateDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebUserTaskRealDTO;
import cn.com.dbridge.lifecare.framework.enums.TaskShowEnum;
import cn.com.dbridge.lifecare.framework.enums.TaskTypeEnum;
import cn.com.dbridge.lifecare.framework.exception.CustomException;
import cn.com.dbridge.lifecare.framework.util.AgeUtils;
import cn.com.dbridge.lifecare.framework.util.TimeSwitch;
import cn.com.dbridge.lifecare.framework.vo.mobile.WxCalendarTaskVO;
import cn.com.dbridge.lifecare.framework.vo.mobile.WxCustomServicePersonTaskVO;
import cn.com.dbridge.lifecare.framework.vo.mobile.WxMyTaskQueryVO;
import cn.com.dbridge.lifecare.framework.vo.mobile.WxServicePersonInfo;
import cn.com.dbridge.lifecare.framework.vo.mobile.WxTaskPoolQueryVO;
import cn.com.dbridge.lifecare.framework.vo.mobile.WxTaskQueryDetailVO;
import cn.com.dbridge.lifecare.framework.vo.mobile.WxWaitingServiceVO;
import cn.com.dbridge.lifecare.service.DistrictService;
import cn.com.dbridge.lifecare.service.TaskService;
import cn.com.dbridge.lifecare.service.UserService;
import cn.com.dbridge.lifecare.service.UserTaskRealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * 
 * @ClassName:  TaskController
 * @Description:任务
 * @author: linh
 * @date:   2019年1月5日 下午2:56:27
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@RestController
@Api(tags = "任务管理")
@RequestMapping(value = "/api")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserTaskRealService userTaskRealService;
    @Autowired
    private UserService userService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private StorageProperties storageProperties;
    
    /**
     * wechat/task/getTask
     * @Title: selectPendingCount 
     * @author linh
     * @Description:获取待处理任务数
     * @param mobileTaskDTO
     * @return
     */
    @ApiOperation(value = "获取待处理任务数", notes = "获取待处理任务数")
    @GetMapping(value = "/task/pending")
    @RequiresAuthentication
    public Result<TaskPendingVO> selectPendingCount() {
        return new Result<TaskPendingVO>(HttpStatus.OK.value(), "操作成功",
                taskService.selectPendingCount());
    }
    
    /**
     * 
     * @Title: queryMyTask
     * @Description: 查看我的任务
     * @param mobileMyTaskQueryDTO
     * @author chenjianfei
     * @return
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "[微信]查看我的任务列表-任务主窗口", notes = "[微信]查看我的任务列表-任务主窗口")
    @PostMapping(value = "/wechat/task/myTask")
    @RequiresAuthentication
    public PageResult<List<WxMyTaskQueryVO>> queryMyTask(@RequestBody MobileMyTaskQueryDTO mobileMyTaskQueryDTO){
        Integer servicePersonId = mobileMyTaskQueryDTO.getServicePersonId();
        PageListDTO pageListDTO = taskService.wxQueryTask(mobileMyTaskQueryDTO);
        List<WxTaskQueryDTO> wxQueryTaskList = (List<WxTaskQueryDTO>)pageListDTO.getList();
        List<WxMyTaskQueryVO> WxMyTaskQueryVOList = new ArrayList<WxMyTaskQueryVO>();
        WxMyTaskQueryVO wxMyTaskQueryVO = null;
        for (WxTaskQueryDTO wxQueryTask : wxQueryTaskList) {
            wxMyTaskQueryVO = new WxMyTaskQueryVO();
            BeanUtils.copyProperties(wxQueryTask, wxMyTaskQueryVO);
            
            //设置查看信息阅读状态
            WebUserTaskRealDTO userTaskRealDTO = new WebUserTaskRealDTO();
            userTaskRealDTO.setOrderId(wxQueryTask.getTaskId());
            userTaskRealDTO.setUserId(servicePersonId);//服务人员ID
            userTaskRealDTO.setType(TaskTypeEnum.MY_TASK.value);
            UserTaskRealPO userTaskRealPO = userTaskRealService.getByOrderIdAndUserId(userTaskRealDTO);
            //查看用户任务查看信息表，如果表中有记录，证明已经查看过了
            if(userTaskRealPO != null) {
                //已经查看过
                wxMyTaskQueryVO.setSeeFlg(TaskShowEnum.ALREADY_SEEN.value);
            }else {
                //未查看过
                wxMyTaskQueryVO.setSeeFlg(TaskShowEnum.NOT_SEEN.value);
            }
            
            WxMyTaskQueryVOList.add(wxMyTaskQueryVO);
        }
        Integer offset = mobileMyTaskQueryDTO.getOffset();
        Integer limit = mobileMyTaskQueryDTO.getLimit();
        return new PageResult<List<WxMyTaskQueryVO>>(HttpStatus.OK.value(), "操作成功",offset,limit,
                pageListDTO.getCount(), WxMyTaskQueryVOList);
    }
    
    /**
     * 
     * @Title: taskPool
     * @Description: 查看任务池列表
     * @param mobileMyTaskQueryDTO
     * @return
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "[微信]查看任务池列表", notes = "[微信]查看任务池列表")
    @PostMapping(value = "/wechat/task/taskPool")
    @RequiresAuthentication
    public PageResult<List<WxTaskPoolQueryVO>> queryTaskPool(@RequestBody MobileMyTaskQueryDTO mobileMyTaskQueryDTO){
    	mobileMyTaskQueryDTO.setType("0");//0：任务池
        //获取服务人员ID用于查询 是否是新消息
        Integer userId = mobileMyTaskQueryDTO.getServicePersonId();
        //任务池中查询任务，不需要传递ServicePersonId，因为所有人看到的都是一样的，不需要根据服务人员去做筛选
        mobileMyTaskQueryDTO.setServicePersonId(null);
    	PageListDTO pageListDTO = taskService.wxQueryTask(mobileMyTaskQueryDTO);
        List<WxTaskQueryDTO> wxQueryTaskList = (List<WxTaskQueryDTO>)pageListDTO.getList();
        List<WxTaskPoolQueryVO> wxTaskPoolQueryVOList = new ArrayList<WxTaskPoolQueryVO>();
        WxTaskPoolQueryVO wxTaskPoolQueryVO = null;
        for(WxTaskQueryDTO wxQueryTask: wxQueryTaskList) {
            wxTaskPoolQueryVO = new WxTaskPoolQueryVO();
            BeanUtils.copyProperties(wxQueryTask, wxTaskPoolQueryVO);
            
            //设置查看信息阅读状态
            WebUserTaskRealDTO userTaskRealDTO = new WebUserTaskRealDTO();
            userTaskRealDTO.setOrderId(wxQueryTask.getTaskId());
            userTaskRealDTO.setUserId(userId);//服务人员ID
            userTaskRealDTO.setType(TaskTypeEnum.TASK_POOL.value);
            //查看用户任务查看信息表，如果表中有记录，证明已经查看过了(根据TaskId、服务人员ID和类别进行查询)
            UserTaskRealPO userTaskRealPO = userTaskRealService.getByOrderIdAndUserId(userTaskRealDTO);
            if(userTaskRealPO != null) {
                //已经查看过
                wxTaskPoolQueryVO.setSeeFlg(TaskShowEnum.ALREADY_SEEN.value);
            }else {
                //未查看过
                wxTaskPoolQueryVO.setSeeFlg(TaskShowEnum.NOT_SEEN.value);
            }
            //TODO 生日
            wxTaskPoolQueryVO.setAge(AgeUtils.getAgeByBirthday(wxQueryTask.getBirthday()));
            wxTaskPoolQueryVOList.add(wxTaskPoolQueryVO);
        }
        Integer offset = mobileMyTaskQueryDTO.getOffset();
        Integer limit = mobileMyTaskQueryDTO.getLimit();
        return new PageResult<List<WxTaskPoolQueryVO>>(HttpStatus.OK.value(), "操作成功",offset,limit,
                pageListDTO.getCount(), wxTaskPoolQueryVOList);
    }
    
    /**
     * 
     * @Title: getTask
     * @Description: 获取登录用户的任务信息
     * @param taskDetailQueryDTO
     * @return
     */
    @ApiOperation(value = "[微信]查询任务详情信息", notes = "[微信]查询任务详情信息")
    @PostMapping(value = "/wechat/task/getTaskDetail")
    @RequiresAuthentication
    public Result<WxTaskQueryDetailVO> getTaskDetail(@RequestBody TaskDetailQueryDTO taskDetailQueryDTO){
        WxTaskQueryDetailDTO paramDTO = new WxTaskQueryDetailDTO();
        BeanUtils.copyProperties(taskDetailQueryDTO, paramDTO);
        WxTaskQueryDetailDTO wxTaskQueryDetailDTO = taskService.wxSelectTaskDetail(paramDTO);
        if(null == wxTaskQueryDetailDTO) {
            return new Result<WxTaskQueryDetailVO>(HttpStatus.OK.value(), "[微信]根据任务ID查询任务详情信息",null);
        }
        WxTaskQueryDetailVO wxTaskQueryDetailVO = new WxTaskQueryDetailVO();
        BeanUtils.copyProperties(wxTaskQueryDetailDTO, wxTaskQueryDetailVO);
        String orderDuration = wxTaskQueryDetailVO.getOrderDuration();
        //格式化合计服务时长字段格式 3小时12分
        if(StringUtils.isNotEmpty(orderDuration) && NumberUtils.isDigits(orderDuration)) {
        	String duration = TimeSwitch.secondToTime(Integer.parseInt(orderDuration)*60);
        	wxTaskQueryDetailVO.setOrderDuration(duration);
        }
        return new Result<WxTaskQueryDetailVO>(HttpStatus.OK.value(), "操作成功",
                wxTaskQueryDetailVO);
    }
    
    /**
     * 
     * @Title: getTask
     * @Description: 获取登录用户的任务信息
     * @param wxWaitingServiceDTO
     * @return
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "[微信]获取登录用户的任务信息-待完成/已完成页面", notes = "[微信]获取登录用户的任务信息-待完成/已完成页面")
    @PostMapping(value = "/wechat/task/getTask")
    @RequiresAuthentication
    public PageResult<List<WxWaitingServiceVO>> getTask(@RequestBody WxWaitingServiceDTO wxWaitingServiceDTO){
        MobileMyTaskQueryDTO mobileMyTaskQueryDTO = new MobileMyTaskQueryDTO();
        BeanUtils.copyProperties(wxWaitingServiceDTO, mobileMyTaskQueryDTO);
        //查询登录用户所有任务信息
        PageListDTO pageListDTO = taskService.wxQueryTask(mobileMyTaskQueryDTO);
        List<WxTaskQueryDTO> wxQueryTaskList = (List<WxTaskQueryDTO>)pageListDTO.getList();

        Map<String,WxTaskQueryDTO> map = new HashMap<String,WxTaskQueryDTO>();
        //对全部单据进行分组，取最新的一条记录
        Map<String, List<WxTaskQueryDTO>> collectMap = wxQueryTaskList
        		.stream().collect(Collectors.groupingBy(WxTaskQueryDTO::getRealName));
        for (Map.Entry<String, List<WxTaskQueryDTO>> entry : collectMap.entrySet()) {
        	 map.put(entry.getKey(), entry.getValue().get(0));
        }
		Iterator<Entry<String, WxTaskQueryDTO>> it = map.entrySet().iterator();
		List<WxTaskQueryDTO> list = new ArrayList<WxTaskQueryDTO>();
		while (it.hasNext()) {
			Entry<String, WxTaskQueryDTO> entry = (Entry<String, WxTaskQueryDTO>) it.next();
			list.add((WxTaskQueryDTO)entry.getValue());
		}
        List<WxWaitingServiceVO> wxWaitingServiceVOList = new ArrayList<WxWaitingServiceVO>();
        WxWaitingServiceVO wxWaitingServiceVO = null;
        //获取记录是否查看处理
        for(WxTaskQueryDTO wxQueryTask: list) {
            wxWaitingServiceVO = new WxWaitingServiceVO();
            BeanUtils.copyProperties(wxQueryTask, wxWaitingServiceVO);
            //设置订单开始时间
            wxWaitingServiceVO.setStartTime(wxQueryTask.getOrderBeginTime());
            //订单结束时间
            wxWaitingServiceVO.setEndTime(wxQueryTask.getOrderEndTime());
            //设置查看信息阅读状态
            WebUserTaskRealDTO userTaskRealDTO = new WebUserTaskRealDTO();
            userTaskRealDTO.setOrderId(wxQueryTask.getTaskId());
            userTaskRealDTO.setUserId(wxQueryTask.getServicePersonId());
            userTaskRealDTO.setType(TaskTypeEnum.MY_TASK.value);
            //查看用户任务查看信息表，如果表中有记录，证明已经查看过了
            UserTaskRealPO userTaskRealPO = userTaskRealService.getByOrderIdAndUserId(userTaskRealDTO);
            if(userTaskRealPO != null) {
                //已经查看过
                wxWaitingServiceVO.setSeeFlg(TaskShowEnum.ALREADY_SEEN.value);
            }else {
                //未查看过
                wxWaitingServiceVO.setSeeFlg(TaskShowEnum.NOT_SEEN.value);
            }
            wxWaitingServiceVOList.add(wxWaitingServiceVO);
        }
        Integer offset = mobileMyTaskQueryDTO.getOffset();
        Integer limit = mobileMyTaskQueryDTO.getLimit();
        //先按照订单日期排序，再按照订单时间排序
        List<WxWaitingServiceVO> collect = wxWaitingServiceVOList.stream()
                .sorted(Comparator.comparing(WxWaitingServiceVO::getOrderDate)
                        .thenComparing(WxWaitingServiceVO::getStartTime))
                .collect(Collectors.toList());
        return new PageResult<List<WxWaitingServiceVO>>(HttpStatus.OK.value(), "操作成功",offset,limit,
                pageListDTO.getCount(), collect);
    }
    
    /**
     * 
     * @Title: queryCalendarTask
     * @Description:根据登录用户ID，预约日期(yyyyMM)获取登录用户的那些天有预约信息[手机端]
     * @param wxCalendarTaskDTO
     * @return
     */
    @ApiOperation(value = "获取任务日历信息", notes = "获取任务日历信息")
    @PostMapping("/wechat/task/calendarTask")
    @RequiresAuthentication
    public Result<List<WxCalendarTaskVO>> queryCalendarTask(@RequestBody WxCalendarTaskDTO wxCalendarTaskDTO) {
        return new Result<List<WxCalendarTaskVO>>(HttpStatus.OK.value(),
                "操作成功", taskService.queryCalendarTask(wxCalendarTaskDTO));
    }
    
    /**
     * 
     * @Title: updateShowMyTaskStatus
     * @Description:[微信]更新查看任务状态
     * @param wxShowMyTaskDTO
     * @return
     */
    @ApiOperation(value = "[微信]更新查看任务状态", notes = "[微信]更新查看任务状态")
    @PostMapping(value = "/wechat/task/updateShowTaskStatus")
    @OperatorLog(module = "任务管理", methods = "更新查看状态", description = "更新查看状态")
    @RequiresAuthentication
    public Result<Object> updateShowMyTaskStatus(@RequestBody WxShowMyTaskDTO wxShowMyTaskDTO){
        UserTaskRealDTO record = new UserTaskRealDTO();
        BeanUtils.copyProperties(wxShowMyTaskDTO, record);
        record.setShowTime(new Date());
        userTaskRealService.addUserTaskReal(record);
        return new Result<Object>(HttpStatus.OK.value(),"操作成功");
    }
    /**
     * @Title: updateShowTaskPoolStatus
     * @Description:[微信]更新查看任务状态
     * @param wxShowTaskPoolDTO
     * @return
     */
    @ApiOperation(value = "[微信]更新查看任务池状态", notes = "[微信]更新查看任务池状态")
    @PostMapping(value = "/wechat/task/updateShowTaskPoolStatus")
    @RequiresAuthentication
    public Result<Object> updateShowTaskPoolStatus(@RequestBody WxShowTaskPoolDTO wxShowTaskPoolDTO){
        UserTaskPoolRealDTO record = new UserTaskPoolRealDTO();
        BeanUtils.copyProperties(wxShowTaskPoolDTO, record);
        userTaskRealService.addTaskPoolReal(record);
        return new Result<Object>(HttpStatus.OK.value(),"更新查看任务状态");
    }
    
    /**
     * 
     * @Title: updateTask 
     * @Description:更新任务状态
     * @author 郭健
     * @param wxUpdateTaskStatusDTO
     * @return
     */
    @ApiOperation(value = "更新任务状态", notes = "更新任务状态")
    @PutMapping(value = "/wechat/task/updateTask")
    @OperatorLog(module = "任务管理", methods = "更新任务", description = "更新任务")
    @RequiresAuthentication
    public Result<Object> updateTask(
            @RequestBody WxUpdateTaskStatusDTO wxUpdateTaskStatusDTO) {
        TaskDTO taskDTO = new TaskDTO();
        BeanUtils.copyProperties(wxUpdateTaskStatusDTO, taskDTO);
        taskService.updateTaskById(taskDTO);
        return new Result<Object>(HttpStatus.OK.value(), "操作成功");
    }
    
    /**
     * 
     * @Title: taskPool
     * @Description: 获取客户与服务人员关联的订单列表
     * @param wxCustomServicePersonTaskDTO
     * @return
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取客户与服务人员关联的订单列表", notes = "获取客户与服务人员关联的订单列表")
    @PostMapping(value = "/wechat/task/customServicePersonTask")
    @RequiresAuthentication
    public PageResult<WxServicePersonInfo> customServicePersonTask(
            @RequestBody WxCustomServicePersonTaskDTO wxCustomServicePersonTaskDTO){
        MobileMyTaskQueryDTO MobileMyTaskQueryDTO = new MobileMyTaskQueryDTO();
        MobileMyTaskQueryDTO.setServicePersonId(wxCustomServicePersonTaskDTO.getServicePersonId());
        //每页显示记录数
        Integer limit = wxCustomServicePersonTaskDTO.getLimit();
        //开始记录数
        Integer offset = wxCustomServicePersonTaskDTO.getOffset();
        Integer customId = wxCustomServicePersonTaskDTO.getCustomId();
        MobileMyTaskQueryDTO.setLimit(limit);
        MobileMyTaskQueryDTO.setOffset(offset);
        MobileMyTaskQueryDTO.setCustomId(customId);
        //分页查询任务信息
        PageListDTO pageListDTO = taskService.wxQueryTask(MobileMyTaskQueryDTO);
        List<WxTaskQueryDTO> wxQueryTaskList = (List<WxTaskQueryDTO>)pageListDTO.getList();
        WxServicePersonInfo customInfo = new WxServicePersonInfo();
        List<WxCustomServicePersonTaskVO> wxCustomServicePersonTaskVOList = null;
        //编辑任务DTO转化为任务VO
        if(!CollectionUtils.isEmpty(wxQueryTaskList)) {
            wxCustomServicePersonTaskVOList = new ArrayList<WxCustomServicePersonTaskVO>();
           WxCustomServicePersonTaskVO wxCustomServicePersonTaskVO = null;
           for(WxTaskQueryDTO wxQueryTask: wxQueryTaskList) {
               wxCustomServicePersonTaskVO = new WxCustomServicePersonTaskVO();
               BeanUtils.copyProperties(wxQueryTask, wxCustomServicePersonTaskVO);
               wxCustomServicePersonTaskVOList.add(wxCustomServicePersonTaskVO);
           }
           customInfo.setImg(storageProperties.getShowPath() + wxQueryTaskList.get(0).getImg());
           customInfo.setRealName(wxQueryTaskList.get(0).getRealName());
           customInfo.setCustomId(wxQueryTaskList.get(0).getCustomId());
           customInfo.setTaskVoList(wxCustomServicePersonTaskVOList);
       }
       return new PageResult<WxServicePersonInfo>(HttpStatus.OK.value(), "操作成功",offset,limit,
                pageListDTO.getCount(), customInfo);
    }
    

    /**
     * 
     * @Title: queryUnassignedTaskManage 
     * @Description:获取未分配任务列表
     * @author 郭健
     * @param unassignedTaskDTO
     * @return
     * @throws ParseException 
     */
    @ApiOperation(value = "获取未分配任务管理页信息", notes = "获取未分配任务管理页信息")
    @PostMapping(value = "/task/unassignedTask/list")
    @RequiresAuthentication
    public TaskWebPageResult<List<UnassignedTaskVO>> queryUnassignedTaskManage(
            @RequestBody UnassignedTaskDTO unassignedTaskDTO)
            throws ParseException {
    	//页面处理结果
    	TaskWebPageResult<List<UnassignedTaskVO>> taskWebPageResult = taskService
                .queryUnassignedTaskManage(unassignedTaskDTO);
        //返回处理结果
    	TaskWebPageResult<List<UnassignedTaskVO>> rtnPageResult = new TaskWebPageResult<List<UnassignedTaskVO>>(
                HttpStatus.OK.value(), "操作成功",
                taskWebPageResult.getRows(), taskWebPageResult.getTotal(),taskWebPageResult.getTotalOrderDuration(),null);
        rtnPageResult.setLimit(taskWebPageResult.getLimit());
        rtnPageResult.setOffset(taskWebPageResult.getOffset());
        return rtnPageResult;

    }

    /**
     * 
     * @Title: queryTaskManage 
     * @Description:获取服务任务管理页信息
     * @author : 郭健
     * @param taskManageDTO
     * @return
     * @throws ParseException 
     */
    @ApiOperation(value = "获取服务任务管理页信息", notes = "获取服务任务管理页信息")
    @PostMapping(value = "/task/list")
    @RequiresAuthentication
    public TaskWebPageResult<List<TaskManageVO>> queryTaskManage(@RequestBody TaskManageDTO taskManageDTO) throws ParseException {
    	//页面处理结果
    	TaskWebPageResult<List<TaskManageVO>> taskWebPageResult = taskService
                .queryTaskManage(taskManageDTO);
        //返回处理结果
    	TaskWebPageResult<List<TaskManageVO>> rtnPageResult = new TaskWebPageResult<List<TaskManageVO>>(
                HttpStatus.OK.value(), "操作成功",
                taskWebPageResult.getRows(), taskWebPageResult.getTotal(), taskWebPageResult.getTotalOrderDuration(), taskWebPageResult.getTotalServiceDuration());
        rtnPageResult.setLimit(taskWebPageResult.getLimit());
        rtnPageResult.setOffset(taskWebPageResult.getOffset());
        return rtnPageResult;
    }

    /**
     * 
     * @Title: getUnassignedTaskById 
     * @Description:获取未分配编辑页回显数据
     * @author 郭健
     * @param id
     * @return
     */
    @ApiOperation(value = "获取未分配编辑页回显数据", notes = "获取未分配编辑页回显数据")
    @GetMapping("/task/unassignedTask/{id}")
    @RequiresAuthentication
    public Result<UnassignedTaskEditVO> getUnassignedTaskById(
            @PathVariable Integer id) {
        TaskPO taskPO = taskService.getTaskById(id);
        UnassignedTaskEditVO unassignedTaskEditVO = new UnassignedTaskEditVO();
        String orderAddr = null;
        if (null != taskPO) {
            UserPO customPO = null;
            Integer customId = taskPO.getCustomId();
            if(null != customId) {
            	customPO = userService.getUserById(customId);
            }
            BeanUtils.copyProperties(taskPO, unassignedTaskEditVO);
            String userNumber = customPO.getUserNumber();
            String realName = customPO.getRealName();
            String mobile = customPO.getMobile();
            String userLevel = customPO.getUserLevel();
            Date birthday = customPO.getBirthday();
            Integer districtId = customPO.getDistrictId();
        	String streetVillage = customPO.getStreetVillage();
        	String buildingNo = customPO.getBuildingNo();
        	DistrictPO districtPO = null;
        	if(null != districtId) {
        		districtPO = districtService.getDistrictById(districtId);
        	}
        	String districtName = null;
        	if(null != districtPO) {
        		districtName = districtPO.getDistrictName();
        	}
        	orderAddr = districtName + streetVillage + buildingNo;
            //年龄设定
            if (birthday != null) {
                //年龄计算
                Integer age = AgeUtils.getAgeByBirthday(
                		birthday);
                //年龄设定
                unassignedTaskEditVO.setAge(age);
            }
            Byte sex = customPO.getSex();
            unassignedTaskEditVO.setUserNumber(userNumber);
            unassignedTaskEditVO.setRealName(realName);
            unassignedTaskEditVO.setMobile(mobile);
            unassignedTaskEditVO.setUserLevel(userLevel);
            unassignedTaskEditVO.setSex(sex);
            unassignedTaskEditVO.setOrderAddr(orderAddr);
        }
        return new Result<UnassignedTaskEditVO>(HttpStatus.OK.value(),
                "操作成功",
                unassignedTaskEditVO);
    }
    
    /**
     * 
     * @Title: getTaskEditById 
     * @Description:获取服务任务编辑页面回显信息
     * @author 郭健
     * @param id
     * @return
     */
    @ApiOperation(value = "获取服务任务编辑页面回显信息", notes = "获取服务任务编辑页面回显信息")
    @GetMapping(value = "task/{id}")
    @RequiresAuthentication
    public Result<TaskEditVO> getTaskEditById(@PathVariable Integer id) {
        TaskPO taskPO = taskService.getTaskById(id);
        TaskEditVO taskEditVO = new TaskEditVO();
        if (null != taskPO) {
            Integer customId = taskPO.getCustomId();
            UserPO customPO = null;
            String customUserNumber = null;
            String customRealName = null;
            String customMobile = null;
            String customUserLevel = null;
            Byte customSex = null;
            Date customBirthday = null;
            String orderAddr = null;
            Integer servicePersonId = taskPO.getServicePersonId();
            UserPO servicePersonPO = null;
            String servicePersonUserNumber = null;
            String servicePersonRealName = null;
            String servicePersonMobile = null;
            String servicePersonUserLevel = null;
            Byte servicePersonSex = null;
            Date servicePersonBirthday = null;
            String serviceDuration = null;
            Integer intserviceDuration = null;
            String cSex = null;
            String sSex = null;
            int serviceHours = 0;
            int serviceMinute = 0;
            //String remark = null;
//            if(null != taskPO.getRemark()) {
//            	remark = taskPO.getRemark();
//            }
            if(null != taskPO.getServiceDuration()) {
            	intserviceDuration = taskPO.getServiceDuration();
            	serviceHours = (int) Math.floor(intserviceDuration / 60);
            	serviceMinute = intserviceDuration % 60;
            }
            if(serviceHours != 0) {
            	serviceDuration = serviceHours + "小时" + serviceMinute + "分钟";
            } else {
            	serviceDuration = serviceMinute + "分钟";
            }
            if(null != customId) {
            	customPO = userService.getUserById(customId);
            	Integer districtId = customPO.getDistrictId();
            	String streetVillage = customPO.getStreetVillage();
            	String buildingNo = customPO.getBuildingNo();
            	DistrictPO districtPO = null;
            	if(null != districtId) {
            		districtPO = districtService.getDistrictById(districtId);
            	}
            	String districtName = null;
            	if(null != districtPO) {
            		districtName = districtPO.getDistrictName();
            	}
            	orderAddr = districtName + streetVillage + buildingNo;
            	customUserNumber = customPO.getUserNumber();
                customRealName = customPO.getRealName();
                customMobile = customPO.getMobile();
                customUserLevel = customPO.getUserLevel();
                customSex = customPO.getSex();
                if(customSex == 0) {
                	cSex = "男";
                } else {
                	cSex = "女";
                }
                customBirthday = customPO.getBirthday();
            }
            if(null != servicePersonId) {
            	servicePersonPO = userService.getUserById(servicePersonId);
                servicePersonUserNumber = servicePersonPO.getUserNumber();
                servicePersonRealName = servicePersonPO.getRealName();
                servicePersonMobile = servicePersonPO.getMobile();
                servicePersonUserLevel = servicePersonPO.getUserLevel();
                servicePersonSex = servicePersonPO.getSex();
                if(servicePersonSex == 0) {
                	sSex = "男";
                } else {
                	sSex = "女";
                }
                servicePersonBirthday = servicePersonPO.getBirthday();
            }
            BeanUtils.copyProperties(taskPO, taskEditVO);
            taskEditVO.setCustomUserNumber(customUserNumber);
            taskEditVO.setCustomRealName(customRealName);
            taskEditVO.setCustomMobile(customMobile);
            taskEditVO.setCustomUserLevel(customUserLevel);
            taskEditVO.setCustomSex(cSex);
            taskEditVO.setOrderAddr(orderAddr);
            //年龄设定
            if (customBirthday != null) {
                //年龄计算
                Integer customAge = AgeUtils.getAgeByBirthday(
                		customBirthday);
                //年龄设定
                taskEditVO.setCustomAge(customAge);
            }
            taskEditVO.setServicePersonUserNumber(servicePersonUserNumber);
            taskEditVO.setServicePersonRealName(servicePersonRealName);
            taskEditVO.setServicePersonMobile(servicePersonMobile);
            taskEditVO.setServicePersonUserLevel(servicePersonUserLevel);
            taskEditVO.setServicePersonSex(sSex);
            taskEditVO.setServiceDuration(serviceDuration);
            //taskEditVO.setRemark(remark);
            //年龄设定
            if (servicePersonBirthday != null) {
                //年龄计算
                Integer servicePersonAge = AgeUtils.getAgeByBirthday(
                		servicePersonBirthday);
                //年龄设定
                taskEditVO.setServicePersonAge(servicePersonAge);
            }
        }
        return new Result<TaskEditVO>(HttpStatus.OK.value(), "操作成功",
                taskEditVO);
    }
    
    /**
     * 
     * @Title: updateUnassignedTask 
     * @Description:编辑未分配页面信息
     * @author 郭健
     * @param unassignedUpdateTaskDTO
     * @return
     * @throws ParseException 
     * @throws Exception 
     */
    @ApiOperation(value = "编辑未分配页面信息", notes = "编辑未分配页面信息")
    @PutMapping(value = "/task/unassignedTask")
    @OperatorLog(module = "任务管理", methods = "更新未分配任务", description = "更新未分配任务")
    @RequiresAuthentication
    public Result<Object> updateUnassignedTask(
            @RequestBody UnassignedTaskUpdateDTO unassignedUpdateTaskDTO) throws ParseException {
    	//判断当前任务是否为取消状态
//    	Integer id = null;
//    	if(null != unassignedUpdateTaskDTO.getId()) {
//    		id = unassignedUpdateTaskDTO.getId();
//    	}
//    	TaskPO currentTaksPO = taskService.getTaskById(id);
//    	Byte currentOrderStatus = null;
//    	if(null != currentTaksPO.getOrderStatus()) {
//    		currentOrderStatus = currentTaksPO.getOrderStatus();
//    	}
    	Byte updateOrderStatus = null;
    	if(null != unassignedUpdateTaskDTO.getOrderStatus()) {
    		updateOrderStatus = unassignedUpdateTaskDTO.getOrderStatus();
    	}
    	//若原先为取消状态，现在改为非取消状态，要将取消原因字段置空
//    	if(currentOrderStatus == 4) {
		if(null == updateOrderStatus) {
			unassignedUpdateTaskDTO.setCancelReson("");
			Byte orderStatus = 1;
			unassignedUpdateTaskDTO.setOrderStatus(orderStatus);
		}
//    	}
		if(null == unassignedUpdateTaskDTO.getRemark()) {
			unassignedUpdateTaskDTO.setRemark("");
		}
    	Date nowDate = new Date();
    	Date orderDate = null;
    	Date orderBeignTime = null;
    	Date orderEndTime = unassignedUpdateTaskDTO.getOrderEndTime();
    	SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
    	String sOrderDate = null;
    	String sOrderEndTime = null;
    	Date formatOrderDate = null;
    	Date formatOrderEndTime = null;
    	Date laterOrderBeginTime = null;
    	String sLaterOrderBeginTime = null;
    	Date formatLaterOrderBeginTime = null;
    	Calendar c = Calendar.getInstance();  
    	if(null != unassignedUpdateTaskDTO.getOrderDate()) {
    		orderDate = unassignedUpdateTaskDTO.getOrderDate();
    		sOrderDate = sdfd.format(orderDate);
    		formatOrderDate = sdfd.parse(sOrderDate);
    	}
    	if(null != unassignedUpdateTaskDTO.getOrderBeginTime()) {
    		orderBeignTime = unassignedUpdateTaskDTO.getOrderBeginTime();
    		c.setTime(orderBeignTime);   //设置时间
            c.add(Calendar.HOUR, 1); //预约开始时间一小时后
            laterOrderBeginTime = c.getTime(); //结果
            sLaterOrderBeginTime = sdft.format(laterOrderBeginTime);
        	formatLaterOrderBeginTime = sdft.parse(sLaterOrderBeginTime);
    	}
    	if(null != unassignedUpdateTaskDTO.getOrderEndTime()) {
    		orderEndTime = unassignedUpdateTaskDTO.getOrderEndTime();
    		sOrderEndTime = sdft.format(orderEndTime);
    		formatOrderEndTime = sdft.parse(sOrderEndTime);
    	}
    	
    	String sNowDate = sdfd.format(nowDate);
    	Date formatNowDate = sdfd.parse(sNowDate);
    	
    	if(null != formatOrderDate && null != formatOrderEndTime && null != formatLaterOrderBeginTime && formatOrderDate.compareTo(formatNowDate) == 0 && formatOrderEndTime.compareTo(formatLaterOrderBeginTime) != 0 && formatOrderEndTime.compareTo(formatLaterOrderBeginTime) == -1) {
    		throw new CustomException("预约结束时间必须大于等于预约开始时间+1小时");
    	}
        taskService.updateUnassignedTask(unassignedUpdateTaskDTO);
        return new Result<Object>(HttpStatus.OK.value(), "操作成功");
    }
    
    /**
     * 
     * @Title: updateTask 
     * @Description:编辑服务任务页面信息
     * @author 郭健
     * @param taskUpdateDTO
     * @return
     * @throws Exception 
     */
    @ApiOperation(value = "编辑服务任务页面信息", notes = "编辑服务任务页面信息")
    @PutMapping(value = "/task")
    @OperatorLog(module = "任务管理", methods = "更新任务", description = "更新任务")
    @RequiresAuthentication
    public Result<Object> updateTask(@RequestBody TaskUpdateDTO taskUpdateDTO) throws Exception {
    	Date nowDate = new Date();
    	Date orderDate = null;
    	Date orderBeignTime = null;
    	Date orderEndTime = null;
    	SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
    	String sOrderDate = null;
    	String sOrderEndTime = null;
    	Date formatOrderDate = null;
    	Date formatOrderEndTime = null;
    	Calendar c = Calendar.getInstance();  
    	Date laterOrderBeginTime = null;
    	String sLaterOrderBeginTime = null;
    	Date formatLaterOrderBeginTime = null;
    	if(null != taskUpdateDTO.getOrderDate()) {
    		orderDate = taskUpdateDTO.getOrderDate();
    		sOrderDate = sdfd.format(orderDate);
    		formatOrderDate = sdfd.parse(sOrderDate);
    	}
    	if(null != taskUpdateDTO.getOrderBeginTime()) {
    		orderBeignTime = taskUpdateDTO.getOrderBeginTime();
    		c.setTime(orderBeignTime);   //设置时间
            c.add(Calendar.HOUR, 1); //预约开始时间一小时后
            laterOrderBeginTime = c.getTime(); //结果  
            sLaterOrderBeginTime = sdft.format(laterOrderBeginTime);
            formatLaterOrderBeginTime = sdft.parse(sLaterOrderBeginTime);
    	}
    	if(null != taskUpdateDTO.getOrderEndTime()) {
    		orderEndTime = taskUpdateDTO.getOrderEndTime();
    		sOrderEndTime = sdft.format(orderEndTime);
    		formatOrderEndTime = sdft.parse(sOrderEndTime);
    	}
    	String sNowDate = sdfd.format(nowDate);
    	Date formatNowDate = sdfd.parse(sNowDate); 
    	
    	if(null != formatOrderDate && null != formatOrderEndTime && formatOrderDate.compareTo(formatNowDate) == 0 && formatOrderEndTime.compareTo(formatLaterOrderBeginTime) == -1 && formatOrderEndTime.compareTo(formatLaterOrderBeginTime) != 0) {
    		throw new CustomException("预约结束时间必须大于等于预约开始时间+1小时");
    	}
        taskService.updateTask(taskUpdateDTO);
        return new Result<Object>(HttpStatus.OK.value(), "操作成功");
    }

    /**
     * 
     * @Title: addUnassignedTask 
     * @Description:追加未分配页面信息
     * @author 郭健
     * @param unassignedTaskAddDTO
     * @return
     * @throws ParseException 
     * @throws Exception 
     */
    @ApiOperation(value = "追加未分配页面信息", notes = "追加未分配页面信息")
    @PostMapping(value = "/task/unassignedTask/insert")
    @OperatorLog(module = "任务管理", methods = "添加未分配任务", description = "添加未分配任务")
    @RequiresAuthentication
    public Result<UnassignedTaskAddVO> addUnassignedTask(@RequestBody UnassignedTaskAddDTO unassignedTaskAddDTO) throws CustomException, ParseException {
    	Date orderDate = null;
    	Date orderBeignTime = null;
    	Date orderEndTime = null;
    	SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
    	String sOrderDate = null;
    	String sOrderBeginTime = null;
    	String sOrderEndTime = null;
    	Date formatOrderDate = null;
    	Date formatOrderBeginTime = null;
    	Date formatOrderEndTime = null;
    	Calendar c = Calendar.getInstance();  
    	Date laterOrderBeginTime = null;
    	String sLaterOrderBeginTime = null;
    	Date formatLaterOrderBeginTime = null;
    	if(null != unassignedTaskAddDTO.getOrderDate()) {
    		orderDate = unassignedTaskAddDTO.getOrderDate();
    		sOrderDate = sdfd.format(orderDate);
    		formatOrderDate = sdfd.parse(sOrderDate);
    	}
    	if(null != unassignedTaskAddDTO.getOrderBeginTime()) {
    		orderBeignTime = unassignedTaskAddDTO.getOrderBeginTime();
    		c.setTime(orderBeignTime);   //设置时间 
            c.add(Calendar.HOUR, 1); //预约开始时间一小时后
            laterOrderBeginTime = c.getTime(); //结果  
            sOrderBeginTime = sdft.format(c.getTime());
            sLaterOrderBeginTime = sdft.format(laterOrderBeginTime);
            formatOrderBeginTime = sdft.parse(sOrderBeginTime);
        	formatLaterOrderBeginTime = sdft.parse(sLaterOrderBeginTime);
    	}
    	if(null != unassignedTaskAddDTO.getOrderEndTime()) {
    		orderEndTime = unassignedTaskAddDTO.getOrderEndTime();
    		sOrderEndTime = sdft.format(orderEndTime);
    		formatOrderEndTime = sdft.parse(sOrderEndTime);
    	}
    	Date nowDate = new Date();
    	String sNowDate = sdfd.format(nowDate);
    	Date formatNowDate = sdfd.parse(sNowDate);
    	if(null != formatOrderDate && formatOrderDate.compareTo(formatNowDate) != 0 && formatOrderDate.compareTo(formatNowDate) == -1) {
    		throw new CustomException("预约日期必须大于等于当日日期");
    	}
    	long currentTime = System.currentTimeMillis() ;
    	currentTime += 60 * 60 * 1000;
    	Date laterTime = new Date(currentTime); //当前时间一小时后
    	String sLaterTime = sdft.format(laterTime);
    	Date formatLaterTime = sdft.parse(sLaterTime);
    	
    	if(null != formatOrderDate && formatOrderDate.compareTo(formatNowDate) == 0 && formatOrderBeginTime.compareTo(formatLaterTime) != 0 && formatOrderBeginTime.compareTo(formatLaterTime) == -1) {
    		throw new CustomException("预约开始时间必须大于等于当前时间+1小时");
    	}
    	if(null != formatOrderDate && null != formatOrderEndTime && formatOrderDate.compareTo(formatNowDate) == 0 && formatOrderEndTime.compareTo(formatLaterOrderBeginTime) == -1 && formatOrderEndTime.compareTo(formatLaterOrderBeginTime) != 0) {
    		throw new CustomException("预约结束时间必须大于等于预约开始时间+1小时");
    	}
        List<String> taskIds = taskService.addUnassignedTask(unassignedTaskAddDTO);
        UnassignedTaskAddVO unassignedTaskAddVO = new UnassignedTaskAddVO();
        unassignedTaskAddVO.setTaskIds(taskIds);
        return new Result<UnassignedTaskAddVO>(HttpStatus.OK.value(), "操作成功",unassignedTaskAddVO);
    }

    /**
     * 
     * @Title: queryTaskPool 
     * @Description:获取任务池信息
     * @author 郭健
     * @param taskPoolDTO
     * @return
     * @throws ParseException 
     */
    @ApiOperation(value = "获取任务池信息", notes = "获取任务池信息")
    @PostMapping(value = "/task/taskPool")
    @RequiresAuthentication
    public WebPageResult<List<TaskPoolVO>> queryTaskPool(@RequestBody TaskPoolDTO taskPoolDTO) throws ParseException {
    	Date orderDate = null;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String sOrderDate = null;
    	Date formatOrderDate = null;
    	if(null != taskPoolDTO.getOrderDate()) {
    		orderDate = taskPoolDTO.getOrderDate();
    		sOrderDate = sdf.format(orderDate);
    		formatOrderDate = sdf.parse(sOrderDate);
    	}
    	Date nowDate = new Date();
    	String sNowDate = sdf.format(nowDate);
    	Date formatNowDate = sdf.parse(sNowDate);
    	if(null != formatOrderDate && formatOrderDate.compareTo(formatNowDate) != 0 && formatOrderDate.compareTo(formatNowDate) == -1) {
    		throw new CustomException("查询日期必须大于等于当日日期");
    	}
    	//页面处理结果
        WebPageResult<List<TaskPoolVO>> webPageResult = taskService.queryTaskPool(taskPoolDTO);
        //返回处理结果
        WebPageResult<List<TaskPoolVO>> rtnPageResult = new WebPageResult<List<TaskPoolVO>>(
                HttpStatus.OK.value(), "操作成功",
                webPageResult.getRows(), null);
        return rtnPageResult;
    }
    
    /**
     * 
     * @Title: deleteTaskById 
     * @Description: 根据主键删除任务
     * @param id 
     * author:郭健
     * @return
     */
    @DeleteMapping("/task/{id}")
    @ApiOperation(value = "根据主键删除任务", notes = "根据主键删除任务")
    @OperatorLog(module = "任务管理", methods = "删除任务", description = "删除任务")
    @RequiresAuthentication
    public Result<Object> deleteTaskById(
            @ApiParam(name = "id", value = "任务编号") @PathVariable(value = "id") Integer id) {
        return new Result<Object>(HttpStatus.OK.value(), "操作成功",
        		taskService.deleteTaskById(id));
    }
    
//  /**
  //     * 
  //     * @Title: getUnassignedTaskById 
  //     * @Description:查看未分配任务页信息
  //     * @author 郭健
  //     * @param id
  //     * @return
  //     */
  //    @ApiOperation(value = "查看未分配任务页信息", notes = "查看未分配任务页信息")
  //    @GetMapping("task/unassignedTask/see/{id}")
  //    public Result<UnassignedTaskSeeResultVO> getUnassignedTaskSee(
  //            @PathVariable Integer id) {
  //        UnassignedTaskResultPO unassignedTaskResultPO = taskService
  //                .getCustomById(id);
  //        String orderNo = unassignedTaskResultPO.getOrderNo();
  //        List<UnassignedTaskSeePO> webUnassignedTaskSeePOList = taskService
  //                .queryServicePersonByOrderNo(orderNo);
  //        UnassignedTaskSeeVO webUnassignedTaskSeeVO = null;
  //        UnassignedTaskSeeResultVO webUnassignedTaskSeeResultVO = new UnassignedTaskSeeResultVO();
  //        List<UnassignedTaskSeeVO> webUnassignedTaskSeeVOList = new ArrayList<UnassignedTaskSeeVO>();
  //        Integer total = 0;
  //        Integer totalOrderDuration = 0;
  //        if (!CollectionUtils.isEmpty(webUnassignedTaskSeePOList)) {
  //            for (UnassignedTaskSeePO webUnassignedTaskSeePO : webUnassignedTaskSeePOList) {
  //                String duration = webUnassignedTaskSeePO.getOrderDuration();
  //                Integer orderDuration = Integer.parseInt(duration);
  //                totalOrderDuration += orderDuration;
  //                Integer userId = webUnassignedTaskSeePO.getServicePersonId();
  //                WebUserTaskRealDTO userTaskRealDTO = new WebUserTaskRealDTO();
  //                userTaskRealDTO.setUserId(userId);
  //                userTaskRealDTO.setOrderId(Integer.parseInt(orderNo));
  //                UserTaskRealPO userTaskRealPO = userTaskRealService
  //                        .getByOrderIdAndUserId(userTaskRealDTO);
  //                Byte seeFlg = 0;
  //                if (null != userTaskRealPO) {
  //                    if (null == userTaskRealPO.getId()) {
  //                        seeFlg = 0;
  //                    } else {
  //                        seeFlg = 1;
  //                    }
  //                }
  //                String districtName = webUnassignedTaskSeePO.getDistrictName();
  //                String streetVillage = webUnassignedTaskSeePO
  //                        .getStreetVillage();
  //                String buildingNo = webUnassignedTaskSeePO.getBuildingNo();
  //                String userAddr = "";
  //                if(districtName != null && streetVillage != null && buildingNo != null) {
  //                    userAddr = districtName + streetVillage + buildingNo;
  //                } else {
  //                    userAddr = "";
  //                }
  //                webUnassignedTaskSeeVO = new UnassignedTaskSeeVO();
  //                BeanUtils.copyProperties(webUnassignedTaskSeePO, webUnassignedTaskSeeVO);
  //                webUnassignedTaskSeeVO.setUserAddr(userAddr);
  //                webUnassignedTaskSeeVO.setSeeFlg(seeFlg);
  //                webUnassignedTaskSeeVOList.add(webUnassignedTaskSeeVO);
  //                total++;
  //            }
  //        } else {
  //            return null;
  //        }
  //        BeanUtils.copyProperties(unassignedTaskPO,
  //                webUnassignedTaskSeeResultVO);
  //        String serviceCategoryName = unassignedTaskPO.getName();
  //        webUnassignedTaskSeeResultVO
  //                .setServiceCategoryName(serviceCategoryName);
  //        webUnassignedTaskSeeResultVO.setTotal(total);
  //        webUnassignedTaskSeeResultVO
  //                .setTotalOrderDuration(totalOrderDuration.toString());
  //        webUnassignedTaskSeeResultVO.setWebUnassignedTaskSeeVOList(webUnassignedTaskSeeVOList);
  //        return new Result<UnassignedTaskSeeResultVO>(HttpStatus.OK.value(),
  //                "获取未分配任务查看页信息成功", webUnassignedTaskSeeResultVO);
  //    }
    
    

}