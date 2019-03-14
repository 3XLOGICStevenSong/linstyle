package cn.com.dbridge.lifecare.service;

import java.text.ParseException;
import java.util.List;

import cn.com.dbridge.lifecare.dao.po.MobileNursePlanPO;
import cn.com.dbridge.lifecare.dao.po.NursePlanPO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileNursePlanDTO;
import cn.com.dbridge.lifecare.framework.dto.web.NursePlanAddDTO;
import cn.com.dbridge.lifecare.framework.dto.web.NursePlanDTO;
import cn.com.dbridge.lifecare.framework.dto.web.NursePlanManageDTO;
import cn.com.dbridge.lifecare.framework.dto.web.NursePlanStatusDTO;
import cn.com.dbridge.lifecare.framework.dto.web.NursePlanUpdateDTO;
import cn.com.dbridge.lifecare.framework.vo.web.NursePlanManageResultVO;

public interface NursePlanService {
    int deleteNursePlanById(Integer nursePlanId);

    int add(MobileNursePlanDTO mobileNursePlanDTO);

    NursePlanPO getNursePlanById(NursePlanDTO nursePlanDTO);

    List<NursePlanPO> queryAll();

    int updateNursePlan(NursePlanUpdateDTO nursePlanUpdateDTO) throws ParseException, Exception;

    int addNursePlan(NursePlanAddDTO nursePlanAddDTO) throws ParseException, Exception;
    
    int updateNursePlanStatus(NursePlanStatusDTO nursePlanStatusDTO);

    /**
     * 
     * @Title: queryNursePlan
     * @Description:获取照护方案页信息
     * @author 郭健
     * @param mobileNursePlanDTO
     * @return
     */
    MobileNursePlanPO queryNursePlan(
            MobileNursePlanDTO mobileNursePlanDTO);
    
    /**
     * 
     * @Title: queryNursePlanManage 
     * @Description:获取照护方案管理页信息
     * @author 郭健
     * @param nursePlanManageDTO
     * @return
     */
    NursePlanManageResultVO queryNursePlanManage(
            NursePlanManageDTO nursePlanManageDTO);
}