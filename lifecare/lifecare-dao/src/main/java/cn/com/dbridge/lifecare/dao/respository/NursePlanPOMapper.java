package cn.com.dbridge.lifecare.dao.respository;

import java.util.List;

import cn.com.dbridge.lifecare.dao.po.MobileNursePlanPO;
import cn.com.dbridge.lifecare.dao.po.NursePlanManagePO;
import cn.com.dbridge.lifecare.dao.po.NursePlanManageResPO;
import cn.com.dbridge.lifecare.dao.po.NursePlanPO;

public interface NursePlanPOMapper {
    int deleteByPrimaryKey(Integer nursePlanId);

    int insert(NursePlanPO nursePlanPO);

    NursePlanPO selectByPrimaryKey(Integer nursePlanId);

    List<NursePlanPO> selectAll();
    
    List<NursePlanPO> selectNursePlan(NursePlanPO record);

    int updateByPrimaryKey(NursePlanPO nursePlanPO);
    
    /**
     * 
     * @Title: deleteNursePlan 
     * @Description:删除照护方案
     * @param nursePlanPO
     */
    int deleteNursePlan(NursePlanPO nursePlanPO);

    /**
     * 
     * @Title: selectNurserPlanForCustom 
     * @Description:获取小于今天的照护方案页信息
     * @param nursePlanPO
     */
    MobileNursePlanPO selectNurserPlanl(NursePlanPO nursePlanPO);
    
    /**
     * 
     * @Title: selectNurserPlanForCustom 
     * @Description:获取小于今天的照护方案页信息
     * @param nursePlanPO
     */
    MobileNursePlanPO selectNurserPlanR(NursePlanPO nursePlanPO);
    
    /**
     * 
     * @Title: selectNursePlanManage 
     * @Description:获取照护方案管理页信息
     * @param userPO
     * @return
     */
    List<NursePlanManageResPO> selectNursePlanManage(
            NursePlanManagePO nursePlanManagePO);
    
    int selectNursePlanCount(NursePlanPO record);
}