package cn.com.dbridge.lifecare.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dbridge.lifecare.dao.po.UserTaskRealPO;
import cn.com.dbridge.lifecare.dao.respository.UserTaskRealMapper;
import cn.com.dbridge.lifecare.framework.dto.mobile.UserTaskPoolRealDTO;
import cn.com.dbridge.lifecare.framework.dto.mobile.UserTaskRealDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebUserTaskRealDTO;
import cn.com.dbridge.lifecare.service.UserTaskRealService;

/**
 * @ClassName:UserTaskRealServiceImpl
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月8日 下午1:53:25
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Service
public class UserTaskRealServiceImpl implements UserTaskRealService {
    @Autowired
    private UserTaskRealMapper userTaskRealMapper;

//    @Override
//    public UserTaskRealPO getByOrderIdAndUserId(UserTaskRealDTO userTaskRealDTO) {
//        UserTaskRealPO userTaskRealPO = new UserTaskRealPO();
//        BeanUtils.copyProperties(userTaskRealDTO, userTaskRealPO);
//        UserTaskRealPO UserTaskRealPOQuery = userTaskRealMapper
//                .selectByOrderIdAndUserId(userTaskRealPO);
//        if (null == UserTaskRealPOQuery) {
//            return null;
//        }
//        return UserTaskRealPOQuery;
//    }
    
    @Override
    public int addUserTaskReal(UserTaskRealDTO record) {
        UserTaskRealPO userTaskRealPO = new UserTaskRealPO();
        BeanUtils.copyProperties(record, userTaskRealPO);
        return userTaskRealMapper.insertSelective(userTaskRealPO);
    }

    @Override
    public int deleteUserTaskRealById(Integer id) {
        return userTaskRealMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserTaskRealDTO getUserTaskRealById(Integer id) {
        UserTaskRealPO userTaskRealPO = userTaskRealMapper.selectByPrimaryKey(id);
        if(userTaskRealPO == null) {
            return null;
        }
        UserTaskRealDTO userTaskRealDTO = new UserTaskRealDTO();
        BeanUtils.copyProperties(userTaskRealPO, userTaskRealDTO);
        return userTaskRealDTO;
    }

    /**   
     * Title: getByOrderIdAndUserId  
     * Description:  
     * @param userTaskRealDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.UserTaskRealService#getByOrderIdAndUserId(cn.com.dbridge.lifecare.framework.dto.web.WebUserTaskRealDTO)   
     */
    @Override
    public UserTaskRealPO getByOrderIdAndUserId(WebUserTaskRealDTO userTaskRealDTO) {
        UserTaskRealPO record = new UserTaskRealPO();
        record.setUserId(userTaskRealDTO.getUserId());
        record.setType(userTaskRealDTO.getType());
        String taskId = userTaskRealDTO.getOrderId();
        if(StringUtils.isNotEmpty(taskId)) {
        	record.setTaskId(Integer.parseInt(taskId));
        }
        List<UserTaskRealPO> selectReadOrNot = userTaskRealMapper.selectReadOrNot(record);
        if(CollectionUtils.isEmpty(selectReadOrNot)) {
        	return null;
        }
//        if(selectReadOrNot.size()>1) {
//        	throw new CustomException("查询记录多余一条");
//        }
        return selectReadOrNot.get(0);
    }

    /**   
     * Title: getByOrderIdAndUserId  
     * Description:  
     * @param userTaskRealDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.UserTaskRealService#getByOrderIdAndUserId(cn.com.dbridge.lifecare.framework.dto.mobile.UserTaskRealDTO)   
     */
    @Override
    public UserTaskRealPO getByOrderIdAndUserId(
            UserTaskRealDTO userTaskRealDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    /**   
     * Title: queryReadOrNot  
     * Description:  
     * @param userTaskRealDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.UserTaskRealService#queryReadOrNot(cn.com.dbridge.lifecare.framework.dto.mobile.UserTaskRealDTO)   
     */
    @Override
    public Boolean queryReadOrNot(
            UserTaskRealDTO userTaskRealDTO) {
        UserTaskRealPO userTaskRealPO = new UserTaskRealPO();
        BeanUtils.copyProperties(userTaskRealDTO, userTaskRealPO);
        List<UserTaskRealPO> userTaskRealPOList = userTaskRealMapper.selectReadOrNot(userTaskRealPO);
        if(CollectionUtils.isEmpty(userTaskRealPOList)) {
            return false;
        } else {
            return true;
        }
    }

	@Override
	public void addTaskPoolReal(UserTaskPoolRealDTO record) {
		List<Integer> taskIds = record.getTaskIds();
		for(Integer taskId : taskIds) {
			UserTaskRealPO userTaskRealPO = new UserTaskRealPO();
			userTaskRealPO.setShowTime(new Date());
			userTaskRealPO.setTaskId(taskId);
			userTaskRealPO.setType(record.getType());
			userTaskRealPO.setUserId(record.getUserId());
	        userTaskRealMapper.insertSelective(userTaskRealPO);
		}
	}

}
