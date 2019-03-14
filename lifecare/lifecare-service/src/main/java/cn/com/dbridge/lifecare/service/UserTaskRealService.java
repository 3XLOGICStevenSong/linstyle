package cn.com.dbridge.lifecare.service;

import cn.com.dbridge.lifecare.dao.po.UserTaskRealPO;
import cn.com.dbridge.lifecare.framework.dto.mobile.UserTaskPoolRealDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.UserTaskRealDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebUserTaskRealDTO;

/**
 * @ClassName:UserTaskRealService
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月8日 下午1:48:46
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
public interface UserTaskRealService {
    /**
     * 
     * @Title: getByOrderIdAndUserId 
     * @Description:确认订单是否已读
     * @param userTaskRealDTO
     * @return
     */
    UserTaskRealPO getByOrderIdAndUserId(WebUserTaskRealDTO userTaskRealDTO);
    /**
     * 
     * @Title: getByOrderIdAndUserId 
     * @Description:确认订单是否已读
     * @param userTaskRealDTO
     * @return
     */
    UserTaskRealPO getByOrderIdAndUserId(UserTaskRealDTO userTaskRealDTO);
//    /**
//     * 
//     * @Title: getByOrderIdAndUserId 
//     * @Description:确认订单是否已读
//     * @param userTaskRealDTO
//     * @return
//     */
//    UserTaskRealPO getByOrderIdAndUserId(UserTaskRealDTO userTaskRealDTO);
    

    int addUserTaskReal(UserTaskRealDTO record);
    
    int deleteUserTaskRealById(Integer id);
    
    UserTaskRealDTO getUserTaskRealById(Integer id);

    Boolean queryReadOrNot(UserTaskRealDTO userTaskRealDTO);
	void addTaskPoolReal(UserTaskPoolRealDTO record);
}
