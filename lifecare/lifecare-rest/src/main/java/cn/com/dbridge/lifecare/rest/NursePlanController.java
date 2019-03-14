package cn.com.dbridge.lifecare.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import cn.com.dbridge.jtraining.upload.picture.service.impl.StorageProperties;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
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

import cn.com.dbridge.lifecare.dao.po.MobileNursePlanPO;
import cn.com.dbridge.lifecare.dao.po.NursePlanPO;
import cn.com.dbridge.lifecare.dao.po.UserPO;
import cn.com.dbridge.lifecare.framework.base.Result;
import cn.com.dbridge.lifecare.framework.base.WebPageResult;
import cn.com.dbridge.lifecare.framework.dblog.annotation.OperatorLog;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileNursePlanDTO;
import cn.com.dbridge.lifecare.framework.dto.web.NursePlanAddDTO;
import cn.com.dbridge.lifecare.framework.dto.web.NursePlanDTO;
import cn.com.dbridge.lifecare.framework.dto.web.NursePlanManageDTO;
import cn.com.dbridge.lifecare.framework.dto.web.NursePlanStatusDTO;
import cn.com.dbridge.lifecare.framework.dto.web.NursePlanUpdateDTO;
import cn.com.dbridge.lifecare.framework.util.AgeUtils;
import cn.com.dbridge.lifecare.framework.vo.mobile.MobileNursePlanWechatVO;
import cn.com.dbridge.lifecare.framework.vo.web.NursePlanEditVO;
import cn.com.dbridge.lifecare.framework.vo.web.NursePlanManageResultVO;
import cn.com.dbridge.lifecare.framework.vo.web.NursePlanManageVO;
import cn.com.dbridge.lifecare.service.NursePlanService;
import cn.com.dbridge.lifecare.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * 
 * @ClassName:NursePlanController
 * @Description:服务方案Controller
 * @author:陈健飞
 * @date:2018年12月27日 下午2:20:24
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@RestController
@Api(tags = "服务方案")
@RequestMapping(value = "/api")
public class NursePlanController {
    @Autowired
    private NursePlanService nursePlanService;
    @Autowired
    private UserService userService;
    @Autowired
    private StorageProperties storageProperties;

    /**
     * 
     * @Title:getNursePlan
     * @Description:获取服务方案页信息
     * @param customId 用户编号
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @ApiOperation(value = "获取服务方案页信息", notes = "获取服务方案页信息")
    @GetMapping(value = "/wechat/nursePlan/{customId}/{nursePlanType}")
    @RequiresAuthentication
    public Result<MobileNursePlanWechatVO> getNursePlanByUserId(@PathVariable(value="customId") Integer customId,
            @PathVariable(value="nursePlanType") Integer nursePlanType){
        MobileNursePlanDTO mobileNursePlanDTO = new MobileNursePlanDTO() ;
        mobileNursePlanDTO.setNursePlanType(Byte.parseByte(nursePlanType.toString()));
        mobileNursePlanDTO.setCustomId(customId);
        MobileNursePlanPO mobileNursePlanPO = nursePlanService
                .queryNursePlan(mobileNursePlanDTO);
        MobileNursePlanWechatVO mobileNursePlanWechatVO = null;
        if (null != mobileNursePlanPO) {
            mobileNursePlanWechatVO = new MobileNursePlanWechatVO();
            BeanUtils.copyProperties(mobileNursePlanPO, mobileNursePlanWechatVO);
            mobileNursePlanWechatVO.setImg(storageProperties.getShowPath() + mobileNursePlanPO.getImg());
        }
        return new Result<MobileNursePlanWechatVO>(HttpStatus.OK.value(),
                "操作成功", mobileNursePlanWechatVO);
    }

    /**
     * 
     * @Title: queryUnassignedNursePlanManage 
     * @author 郭健
     * @Description: 获取未发布服务方案管理页信息
     * @param nursePlanManageDTO
     * @return
     */
    @ApiOperation(value = "获取未发布服务方案管理页信息", notes = "获取未发布服务方案管理页信息")
    @PostMapping(value = "/nursePlan/unassigned/list")
    @RequiresAuthentication
    public WebPageResult<List<NursePlanManageVO>> queryUnassignedNursePlanManage( @RequestBody NursePlanManageDTO nursePlanManageDTO) {
    	//页面处理结果
        WebPageResult<List<NursePlanManageVO>> webPageResult = new WebPageResult<List<NursePlanManageVO>>();
        Byte nursePlanStatus = 0;
        nursePlanManageDTO.setNursePlanStatus(nursePlanStatus);
        NursePlanManageResultVO nursePlanManageResultVO = nursePlanService.queryNursePlanManage(nursePlanManageDTO);
        webPageResult.setRows(nursePlanManageResultVO.getRows());
        webPageResult.setTotal(nursePlanManageResultVO.getTotal());
        webPageResult.setStatusCode(HttpStatus.OK.value());
        webPageResult.setMsg("操作成功");
        return webPageResult;
    }

    /**
     * 
     * @Title: queryAssignedNursePlanManage 
     * @author 郭健
     * @Description: 获取已发布服务方案管理页信息
     * @param nursePlanManageDTO
     * @return
     */
    @ApiOperation(value = "获取已发布服务方案管理页信息", notes = "获取已发布服务方案管理页信息")
    @PostMapping(value = "/nursePlan/assigned/list")
    @RequiresAuthentication
    public WebPageResult<List<NursePlanManageVO>> queryAssignedNursePlanManage(@RequestBody NursePlanManageDTO nursePlanManageDTO) {
    	//页面处理结果
        WebPageResult<List<NursePlanManageVO>> webPageResult = new WebPageResult<List<NursePlanManageVO>>();
        Byte nursePlanStatus = 1;
        nursePlanManageDTO.setNursePlanStatus(nursePlanStatus);
        NursePlanManageResultVO nursePlanManageResultVO = nursePlanService.queryNursePlanManage(nursePlanManageDTO);
        webPageResult.setRows(nursePlanManageResultVO.getRows());
        webPageResult.setTotal(nursePlanManageResultVO.getTotal());
        webPageResult.setStatusCode(HttpStatus.OK.value());
        webPageResult.setMsg("操作成功");
        return webPageResult;
    }
    
    /**
     * 
     * @Title: updateNursePlanStatus
     * @author 郭健
     * @Description: 修改方案状态
     * @param nursePlanStatusDTO
     * @return
     */
    @ApiOperation(value = "修改方案状态", notes = "修改方案状态")
    @PutMapping(value = "/nursePlan/nursePlanStatus")
    @OperatorLog(module = "服务方案", methods = "修改方案状态", description = "修改方案状态")
    @RequiresAuthentication
    public Result<Object> updateNursePlanStatus(@RequestBody NursePlanStatusDTO nursePlanStatusDTO){
        nursePlanService.updateNursePlanStatus(nursePlanStatusDTO);
        return new Result<Object>(HttpStatus.OK.value(),"操作成功");
    }

    /**
     * 
     * @Title: getUnassignedNursePlanEdit 
     * @author 郭健
     * @Description: 获取未发布服务方案编辑页回显信息
     * @param nursePlanId
     * @return
     */
    @ApiOperation(value = "获取未发布服务方案编辑页回显信息", notes = "获取未发布服务方案编辑页回显信息")
    @GetMapping(value = "/nursePlan/unassigned/{nursePlanId}")
    @RequiresAuthentication
    public Result<NursePlanEditVO> getUnassignedNursePlanEdit(@PathVariable(value = "nursePlanId", required = false) Integer nursePlanId) {
        NursePlanDTO nursePlanDTO = new NursePlanDTO();
        nursePlanDTO.setNursePlanId(nursePlanId);
        Byte nursePlanStatus = 0;//0:草稿 1:发布
        nursePlanDTO.setNursePlanStatus(nursePlanStatus);
        NursePlanEditVO nursePlanEditVO = new NursePlanEditVO();
        NursePlanPO nursePlanPO = nursePlanService.getNursePlanById(nursePlanDTO);
        if(null == nursePlanPO) {
            return new Result<NursePlanEditVO>(HttpStatus.OK.value(),
                    "操作成功",null);
        }
        Integer customId = null;
        if(null != nursePlanPO.getCustomId()) {
        	customId = nursePlanPO.getCustomId();
        }
        Integer backendPersonId = null;
        if(null != nursePlanPO.getBackendPersonId()) {
        	backendPersonId = nursePlanPO.getBackendPersonId();
        }
        Integer enterPersonId = null;
        if(null != nursePlanPO.getCreateBy()) {
        	enterPersonId = nursePlanPO.getCreateBy();
        }
        UserPO customPO = userService.getUserById(customId);
        String customUserNumber = null;
        String customRealName = null;
        Byte customSex = null;
        Date birthday = null;
        String customUserLevel = null;
        if(null != customPO) {
        	customUserNumber = customPO.getUserNumber();
        	customRealName = customPO.getRealName();
        	customSex = customPO.getSex();
        	birthday = customPO.getBirthday();
        	customUserLevel = customPO.getUserLevel();
        }
        
        if (null != birthday) {
            //年龄计算
            Integer customAge = AgeUtils.getAgeByBirthday(
            		birthday);
            //年龄设定
            nursePlanEditVO.setCustomAge(customAge);
        }
        
        UserPO backendPersonPO = userService.getUserById(backendPersonId);
        String backendPersonUserNumber = null;
        String backendPersonRealName = null;
        if(null != backendPersonPO) {
        	backendPersonUserNumber = backendPersonPO.getUserNumber();
        	backendPersonRealName = backendPersonPO.getRealName();
        }
        UserPO enteringPersonPO = userService.getUserById(enterPersonId);
        String enteringPersonRealName = null;
        String enteringPersonUserNumber = null;
        if(null != enteringPersonPO) {
        	enteringPersonRealName = enteringPersonPO.getRealName();
        	enteringPersonUserNumber = enteringPersonPO.getUserNumber();
        }
        userService.getUserById(backendPersonId);
        BeanUtils.copyProperties(nursePlanPO, nursePlanEditVO);
        nursePlanEditVO.setCustomRealName(customRealName);
        nursePlanEditVO.setCustomUserNumber(customUserNumber);
        nursePlanEditVO.setCustomSex(customSex);
        nursePlanEditVO.setCustomUserLevel(customUserLevel);
        nursePlanEditVO.setBackendPersonRealName(backendPersonRealName);
        nursePlanEditVO.setBackendPersonUserNumber(backendPersonUserNumber);
        nursePlanEditVO.setEnteringPersonRealName(enteringPersonRealName);
        nursePlanEditVO.setEnteringPersonUserNumber(enteringPersonUserNumber);
        return new Result<NursePlanEditVO>(HttpStatus.OK.value(),
                "操作成功", nursePlanEditVO);
    }
    
    /**
     * 
     * @Title: getAssignedNursePlanEdit 
     * @author 郭健
     * @Description: 获取已分配照护方案编辑页回显信息
     * @param nursePlanId
     * @return
     */
    @ApiOperation(value = "获取已发布服务方案编辑页回显信息", notes = "获取已发布服务方案编辑页回显信息")
    @GetMapping(value = "/nursePlan/assigned/{nursePlanId}")
    @RequiresAuthentication
    public Result<NursePlanEditVO> getAssignedNursePlanEdit(@PathVariable(value = "nursePlanId", required = false) Integer nursePlanId) {
        NursePlanDTO nursePlanDTO = new NursePlanDTO();
        nursePlanDTO.setNursePlanId(nursePlanId);
        Byte nursePlanStatus = 1;//0:草稿 1:发布
        nursePlanDTO.setNursePlanStatus(nursePlanStatus);
        NursePlanEditVO nursePlanEditVO = new NursePlanEditVO();
        NursePlanPO nursePlanPO = nursePlanService
                .getNursePlanById(nursePlanDTO);
        if(null == nursePlanPO) {
            return new Result<NursePlanEditVO>(HttpStatus.OK.value(),
                    "操作成功",null);
        }
        Integer customId = null;
        Integer backendPersonId = null;
        Integer enterPersonId = null;
        if(null != nursePlanPO) {
        	customId = nursePlanPO.getCustomId();
        	backendPersonId = nursePlanPO.getBackendPersonId();
        	enterPersonId = nursePlanPO.getCreateBy();
        }
        UserPO customPO = userService.getUserById(customId);
        String customUserNumber = null;
        String customRealName = null;
        Byte customSex = null;
        Date birthday = null;
        if(null != customPO) {
        	customUserNumber = customPO.getUserNumber();
        	customRealName = customPO.getRealName();
        	customSex = customPO.getSex();
        	birthday = customPO.getBirthday();
        }
        
        if (null != birthday) {
            //年龄计算
            Integer customAge = AgeUtils.getAgeByBirthday(
            		birthday);
            //年龄设定
            nursePlanEditVO.setCustomAge(customAge);
        }
        String customUserLevel = customPO.getUserLevel();
        UserPO backendPersonPO = userService.getUserById(backendPersonId);
        String backendPersonUserNumber = null;
        String backendPersonRealName = null;
        if(null != backendPersonPO) {
        	backendPersonUserNumber = backendPersonPO.getUserNumber();
        	backendPersonRealName = backendPersonPO.getRealName();
        } 
        UserPO enteringPersonPO = userService.getUserById(enterPersonId);
        String enteringPersonRealName = null;
        String enteringPersonUserNumber = null;
        if(null != enteringPersonPO) {
        	enteringPersonRealName = enteringPersonPO.getRealName();
        	enteringPersonUserNumber = enteringPersonPO.getUserNumber();
        }
        userService.getUserById(backendPersonId);
        BeanUtils.copyProperties(nursePlanPO, nursePlanEditVO);
        nursePlanEditVO.setCustomRealName(customRealName);
        nursePlanEditVO.setCustomUserNumber(customUserNumber);
        nursePlanEditVO.setCustomSex(customSex);
        nursePlanEditVO.setCustomUserLevel(customUserLevel);
        nursePlanEditVO.setBackendPersonRealName(backendPersonRealName);
        nursePlanEditVO.setBackendPersonUserNumber(backendPersonUserNumber);
        nursePlanEditVO.setEnteringPersonRealName(enteringPersonRealName);
        nursePlanEditVO.setEnteringPersonUserNumber(enteringPersonUserNumber);
        return new Result<NursePlanEditVO>(HttpStatus.OK.value(),
                "操作成功", nursePlanEditVO);
    }
    
    /**
     * 
     * @Title: updateNursePlan
     * @Description: 编辑服务方案
     * @param nursePlanUpdateDTO
     * @return
     * @throws Exception 
     */
    @ApiOperation(value = "编辑服务方案", notes = "编辑服务方案")
    @PutMapping(value = "/nursePlan")
    @OperatorLog(module = "服务方案", methods = "更新服务方案", description = "更新服务方案")
    @RequiresAuthentication
    public Result<Object> updateNursePlan(@RequestBody NursePlanUpdateDTO nursePlanUpdateDTO) throws Exception {
        nursePlanService.updateNursePlan(nursePlanUpdateDTO);
        return new Result<Object>(HttpStatus.OK.value(), "操作成功");
    }
    
    /**
     * 
     * @Title: addNursePlan
     * @Description: 添加服务方案
     * @param nursePlanAddDTO
     * @return
     * @throws Exception 
     */
    @ApiOperation(value = "添加服务方案", notes = "添加服务方案")
    @PostMapping(value = "/nursePlan")
    @OperatorLog(module = "服务方案", methods = "添加服务方案", description = "添加服务方案")
    @RequiresAuthentication
    public Result<Object> addNursePlan(@RequestBody NursePlanAddDTO nursePlanAddDTO) throws Exception {
        nursePlanService.addNursePlan(nursePlanAddDTO);
        return new Result<Object>(HttpStatus.OK.value(), "操作成功");
    }
    
    /**
     * 
     * @Title: deleteNursePlanById 
     * @Description: 获取用户管理信息处理
     * @param nursePlanId 护理计划ID
     * author:郭健
     * @return
     */
    @DeleteMapping("/nursePlan/{nursePlanId}")
    @ApiOperation(value = "根据方案ID删除服务方案信息", notes = "根据方案ID删除服务方案信息")
    @OperatorLog(module = "服务方案", methods = "删除服务方案", description = "删除服务方案")
    @RequiresAuthentication
    public Result<Object> deleteNursePlanById(@ApiParam(name = "nursePlanId", value = "方案ID")
                                                  @PathVariable(value = "nursePlanId") Integer nursePlanId) {
        return new Result<Object>(HttpStatus.OK.value(), "操作成功",
        		nursePlanService.deleteNursePlanById(nursePlanId));
    }
}