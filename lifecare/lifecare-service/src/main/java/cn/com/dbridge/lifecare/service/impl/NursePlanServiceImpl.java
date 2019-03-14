package cn.com.dbridge.lifecare.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.dbridge.lifecare.dao.po.*;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.com.dbridge.lifecare.dao.respository.NursePlanPOMapper;
import cn.com.dbridge.lifecare.dao.respository.UserPOMapper;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileNursePlanDTO;
import cn.com.dbridge.lifecare.framework.dto.web.NursePlanAddDTO;
import cn.com.dbridge.lifecare.framework.dto.web.NursePlanDTO;
import cn.com.dbridge.lifecare.framework.dto.web.NursePlanManageDTO;
import cn.com.dbridge.lifecare.framework.dto.web.NursePlanStatusDTO;
import cn.com.dbridge.lifecare.framework.dto.web.NursePlanUpdateDTO;
import cn.com.dbridge.lifecare.framework.exception.CustomException;
import cn.com.dbridge.lifecare.framework.util.PageInitUtils;
import cn.com.dbridge.lifecare.framework.vo.web.NursePlanManageResultVO;
import cn.com.dbridge.lifecare.framework.vo.web.NursePlanManageVO;
import cn.com.dbridge.lifecare.service.NursePlanService;
@Service
public class NursePlanServiceImpl implements NursePlanService {
    @Autowired
    private NursePlanPOMapper nursePlanPOMapper;
    @Autowired
    private UserPOMapper userPOMapper;
    
    /**
     * 
     * Title: deleteNursePlanById  
     * Description:删除照护方案页信息
     * @author 郭健
     * @param nursePlanId
     * @return
     */
    @Override
    public int deleteNursePlanById(Integer nursePlanId) {
        return nursePlanPOMapper.deleteByPrimaryKey(nursePlanId);
    }

    @Override
    public int add(MobileNursePlanDTO mobileNursePlanDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public NursePlanPO getNursePlanById(NursePlanDTO nursePlanDTO) {
        NursePlanPO nursePlanPO = new NursePlanPO();
        BeanUtils.copyProperties(nursePlanDTO, nursePlanPO);
        NursePlanPO nursePlanResPO = null;
        List<NursePlanPO> nursePlanPOList = nursePlanPOMapper
                .selectNursePlan(nursePlanPO);
        if (CollectionUtils.isEmpty(nursePlanPOList)) {
            return null;
        } else {
            nursePlanResPO = nursePlanPOList.get(0);
        }
        return nursePlanResPO;
    }

    @Override
    public List<NursePlanPO> queryAll() {
    	List<NursePlanPO> nursePlanPOList = nursePlanPOMapper.selectAll();
    	if(CollectionUtils.isEmpty(nursePlanPOList)) {
    		return null;
    	}
    	return nursePlanPOList;
    }

    /**
     * 
     * Title: updateNursePlan  
     * Description:服务方案编辑
     * @author 郭健
     * @param nursePlanUpdateDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.NursePlanService#updateNursePlan(cn.com.dbridge.lifecare.framework.dto.mobile.NursePlanUpdateDTO)
     */
    @Override
    public int updateNursePlan(NursePlanUpdateDTO nursePlanUpdateDTO) throws Exception {
    	Date nowDate = new Date();
    	Integer currentNursePlanId = null;
    	if(null != nursePlanUpdateDTO.getNursePlanId()) {
    		currentNursePlanId = nursePlanUpdateDTO.getNursePlanId();
    	}
    	NursePlanPO nursePlanPO = nursePlanPOMapper.selectByPrimaryKey(currentNursePlanId);
    	Integer currentCustomId = null;
    	if(null != nursePlanPO.getCustomId()) {
    		currentCustomId = nursePlanPO.getCustomId();
    	}
    	Byte currentNursePlanType = null;
    	if(null != nursePlanUpdateDTO.getNursePlanType()) {
    		currentNursePlanType = nursePlanUpdateDTO.getNursePlanType();
    	}
    	String currentNursePlanTitle = null;
    	if(null != nursePlanUpdateDTO.getNursePlanTitle()) {
    		currentNursePlanTitle = nursePlanUpdateDTO.getNursePlanTitle();
    	}
    	Date currentNurseBeginDate = null;
    	if(null != nursePlanUpdateDTO.getNursePlanBeginDate()) {
    		currentNurseBeginDate = nursePlanUpdateDTO.getNursePlanBeginDate();
    	}
    		
    	Integer enteringPersonId = null;
    	if(null != nursePlanUpdateDTO.getEnteringPersonId()) {
    		enteringPersonId = nursePlanUpdateDTO.getEnteringPersonId();
    	}
    	
    	NursePlanPO nursePlanSelPO = new NursePlanPO();

    	nursePlanSelPO.setCustomId(currentCustomId);
		List<NursePlanPO> nursePlanPOList = nursePlanPOMapper
				.selectNursePlan(nursePlanSelPO);
		if (!CollectionUtils.isEmpty(nursePlanPOList)) {
			for (NursePlanPO nursePlantempPO : nursePlanPOList) {
				String tempNursePlanTitle = nursePlantempPO.getNursePlanTitle();
				Integer tempNursePlanId = nursePlantempPO.getNursePlanId();
//				 System.out.println(
//				 tempNursePlanTitle.equals(currentNursePlanTitle));
//				 System.out.println(!tempNursePlanId.equals(currentNursePlanId));
				if (tempNursePlanTitle
						.equals(currentNursePlanTitle)
						&& !tempNursePlanId.equals(currentNursePlanId)) {
					throw new CustomException("相同客户的照护方案标题必须唯一");
				}
			}
		}

    	nursePlanSelPO.setNursePlanType(currentNursePlanType);
		List<NursePlanPO> nursePlanTPOList = nursePlanPOMapper
				.selectNursePlan(nursePlanSelPO);
		if (!CollectionUtils.isEmpty(nursePlanTPOList)) {
			for (NursePlanPO nursePlantempPO : nursePlanTPOList) {
				Date tempNursePlanBeginDate = nursePlantempPO
						.getNursePlanBeginDate();
				Integer tempNursePlanId = nursePlantempPO.getNursePlanId();
				System.out.println(tempNursePlanBeginDate.equals(currentNurseBeginDate));
				System.out.println(!tempNursePlanId.equals(currentNursePlanId));
				if (tempNursePlanBeginDate.compareTo(currentNurseBeginDate) == 0
						&& !tempNursePlanId.equals(currentNursePlanId)) {
					throw new CustomException("相同客户相同类型的方案开始日期必须唯一");
        		}
        	}
    	}
//    	Date nursePlanBeginDate = null;
    	Date draftDate = null;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    	String sNursePlanBeginDate = null;
    	String sDraftDate = null;
//    	Date formatNursePlanBeginDate = null;
    	Date formatDraftDate = null;
//    	if(null != nursePlanUpdateDTO.getNursePlanBeginDate()) {
//    		nursePlanBeginDate = nursePlanUpdateDTO.getNursePlanBeginDate();
//    		sNursePlanBeginDate = sdf.format(nursePlanBeginDate);
//    		formatNursePlanBeginDate = sdf.parse(sNursePlanBeginDate);
//    	}
    	if(null != nursePlanUpdateDTO.getDraftDate()) {
    		draftDate = nursePlanUpdateDTO.getDraftDate();
    		sDraftDate = sdf.format(draftDate);
    		formatDraftDate = sdf.parse(sDraftDate);
    	}

    	String sNowDate = sdf.format(nowDate);
    	Date formatNowDate  = sdf.parse(sNowDate);
    	//modify by chenjianfei 更新的时候不需要校验 方案开始日期必须大于等于当日日期
//    	if(null != formatNursePlanBeginDate && formatNursePlanBeginDate.compareTo(formatNowDate) != 0 && formatNursePlanBeginDate.compareTo(formatNowDate) == -1) {
//    		throw new CustomException("方案开始日期必须大于等于当日日期");
//    	} 
    	if(null != formatDraftDate && formatDraftDate.compareTo(formatNowDate) == 1 && formatDraftDate.compareTo(formatNowDate) != 0) {
    		throw new CustomException("制定日期必须小于等于当日日期");
    	}
//    	UserPO backendPersonPO = userPOMapper.selectByPrimaryKey(backendPersonId);
//    	Byte draftNursePlan = backendPersonPO.getDraftNursePlan();
    	NursePlanPO nursePlanUpdatePO = new NursePlanPO();
    	int affectNum = 0;
//    	if(draftNursePlan == 1) {
            BeanUtils.copyProperties(nursePlanUpdateDTO, nursePlanUpdatePO);
            nursePlanUpdatePO.setUpdateBy(enteringPersonId);
            nursePlanUpdatePO.setUpdateTime(new Date());
            affectNum = nursePlanPOMapper.updateByPrimaryKey(nursePlanUpdatePO);
//    	} else {
//    		throw new CustomException("指定人员无制定权限");
//    	}
    	return affectNum;
    }

    /**
     * 
     * Title: addNursePlan  
     * Description:添加服务方案
     * @author 郭健
     * @param nursePlanAddDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.NursePlanService#addNursePlan(cn.com.dbridge.lifecare.framework.dto.mobile.NursePlanAddDTO)
     */
    @Override
    public int addNursePlan(NursePlanAddDTO nursePlanAddDTO) throws Exception {
    	Date nowDate = new Date();
    	
    	Integer currentCustomId = null;
    	if(null != nursePlanAddDTO.getCustomId()) {
    		currentCustomId = nursePlanAddDTO.getCustomId();
    	}
    	Integer backendPersonId = null;
    	if(null != nursePlanAddDTO.getBackendPersonId()) {
    		backendPersonId = nursePlanAddDTO.getBackendPersonId();
    	}
    	Integer enteringPersonId = null;
    	if(null != nursePlanAddDTO.getEnteringPersonId()) {
    		enteringPersonId = nursePlanAddDTO.getEnteringPersonId();
    	}
    	Byte nursePlanType = null;
    	if(null != nursePlanAddDTO.getNursePlanType()) {
    		nursePlanType = nursePlanAddDTO.getNursePlanType();
    	}
    	NursePlanPO nursePlanSelPO = new NursePlanPO();
    	nursePlanSelPO.setCustomId(currentCustomId);

		List<NursePlanPO> nursePlanPOList = nursePlanPOMapper
				.selectNursePlan(nursePlanSelPO);
		if (!CollectionUtils.isEmpty(nursePlanPOList)) {
			for (NursePlanPO nursePlanPOtemp : nursePlanPOList) {
				String tempNursePlanTitle = nursePlanPOtemp.getNursePlanTitle();
				if (tempNursePlanTitle
						.equals(nursePlanAddDTO.getNursePlanTitle())) {
					throw new CustomException("相同客户的服务方案标题必须唯一");
				}
			}
		}

    	nursePlanSelPO.setNursePlanType(nursePlanType);
		List<NursePlanPO> nursePlanTPOList = nursePlanPOMapper
				.selectNursePlan(nursePlanSelPO);
		if (!CollectionUtils.isEmpty(nursePlanTPOList)) {
			for (NursePlanPO nursePlanPOtemp : nursePlanTPOList) {
        		Date nursePlanBeginDate = nursePlanPOtemp.getNursePlanBeginDate();
        		if(nursePlanBeginDate.compareTo(nursePlanAddDTO.getNursePlanBeginDate()) == 0) {
					throw new CustomException("相同客户相同类型的方案开始日期必须唯一");
        		}
        	}
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String sNursePlanBeginDate = sdf.format(nursePlanAddDTO.getNursePlanBeginDate());
    	String sDraftDate = sdf.format(nursePlanAddDTO.getDraftDate());
    	String sNowDate = sdf.format(nowDate);

    	Date formatNursePlanBeginDate = sdf.parse(sNursePlanBeginDate);
    	Date formatDraftDate = sdf.parse(sDraftDate);
    	Date formatNowDate  = sdf.parse(sNowDate);
    	if(formatNursePlanBeginDate.compareTo(formatNowDate) != 0 && formatNursePlanBeginDate.compareTo(formatNowDate) == -1) {
    		throw new CustomException("方案开始日期必须大于等于当日日期");
    	} 
    	if(formatDraftDate.compareTo(formatNowDate) == 1 && formatDraftDate.compareTo(formatNowDate) != 0) {
    		throw new CustomException("制定日期必须小于等于当日日期");
    	}
    	UserPO backendPersonPO = userPOMapper.selectByPrimaryKey(backendPersonId);
    	Byte draftNursePlan = backendPersonPO.getDraftNursePlan();
    	NursePlanPO nursePlanPO = new NursePlanPO();
    	int affectNum = 0;
    	if(draftNursePlan == 1) {
            BeanUtils.copyProperties(nursePlanAddDTO, nursePlanPO);
            nursePlanPO.setCreateBy(enteringPersonId);
            nursePlanPO.setCreateTime(new Date());
            Byte nursePlanStatus = 0;
            nursePlanPO.setNursePlanStatus(nursePlanStatus);
            affectNum = nursePlanPOMapper.insert(nursePlanPO);
    	} else {
    		throw new CustomException("指定人员无制定权限");
    	}
    	return affectNum;
    }

    /**
     * 
     * Title: queryNursePlan  
     * Description:获取照护方案页信息
     * @author 郭健
     * @param mobileNursePlanDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.NursePlanService#queryNursePlan(cn.com.dbridge.lifecare.framework.dto.mobile.MobileNursePlanDTO)
     */
    @Override
    public MobileNursePlanPO queryNursePlan(MobileNursePlanDTO mobileNursePlanDTO) {
        NursePlanPO queryNursePlanPO = new NursePlanPO();
        BeanUtils.copyProperties(mobileNursePlanDTO, queryNursePlanPO);
        MobileNursePlanPO mobileNursePlanPO = nursePlanPOMapper.selectNurserPlanl(queryNursePlanPO);
        if(mobileNursePlanPO != null) {
        	return mobileNursePlanPO;
        } else {
        	mobileNursePlanPO = nursePlanPOMapper.selectNurserPlanR(queryNursePlanPO);
        	if(mobileNursePlanPO != null) {
            	return mobileNursePlanPO;
            }
        }
        //没有护理计划的时候仍然需要返回姓名和头像
		MobileNursePlanPO po = new MobileNursePlanPO();
		WebUserPO userInfoByUserId = userPOMapper.getUserInfoByUserId(queryNursePlanPO.getCustomId());
		po.setRealName(userInfoByUserId.getRealName());
		po.setImg(userInfoByUserId.getImg());
		return po;
    }

    /**   
     * Title: queryNursePlanManage
     * @author 郭健
     * Description:获取照护方案管理页信息
     * @param nursePlanManageDTO
     * @return   
     * @see cn.com.dbridge.lifecare.service.NursePlanService#queryNursePlanManage(cn.com.dbridge.lifecare.framework.dto.web.NursePlanManageDTO)   
     */
    @Override
    public NursePlanManageResultVO queryNursePlanManage(
            NursePlanManageDTO nursePlanManageDTO) {
        NursePlanManagePO nursePlanManagePO = new NursePlanManagePO();
        BeanUtils.copyProperties(nursePlanManageDTO, nursePlanManagePO);
        NursePlanManageVO nursePlanManageVO = null;
        List<NursePlanManageVO> nursePlanManageVOList = new ArrayList<NursePlanManageVO>();
        NursePlanManageResultVO nursePlanManageResultVO = new NursePlanManageResultVO();
        //分页数据设定
        Integer offset = PageInitUtils.setPageOffset(nursePlanManageDTO.getOffset());
        Integer limit = PageInitUtils.setPageLimit(nursePlanManageDTO.getLimit());
        Page<Object> offsetPage = PageHelper.offsetPage(offset, limit);
        List<NursePlanManageResPO> nursePlanManagePOResList = nursePlanPOMapper
                .selectNursePlanManage(nursePlanManagePO);
        Long total = offsetPage.getTotal();
        if (!CollectionUtils.isEmpty(nursePlanManagePOResList)) {
            for (NursePlanManageResPO nursePlanManageResPO : nursePlanManagePOResList) {
                Integer customId = nursePlanManageResPO.getCustomId();
                Integer backendPersonId = nursePlanManageResPO
                        .getBackendPersonId();
                UserPO customPO = null;
                UserPO backendPersonPO = null;
                if(null != customId) {
                	customPO = userPOMapper.selectByPrimaryKey(customId);
                }
                if(null != backendPersonId) {
                	backendPersonPO = userPOMapper
                            .selectByPrimaryKey(backendPersonId);
                }
                String customRealName = "";
                String backendPersonRealName = "";
                if (null != customPO) {
                    customRealName = customPO.getRealName();
                }
                if (null != backendPersonPO) {
                	backendPersonRealName = backendPersonPO.getRealName();
                }
                nursePlanManageVO = new NursePlanManageVO();
                BeanUtils.copyProperties(nursePlanManageResPO,
                        nursePlanManageVO);
                nursePlanManageVO.setCustomRealName(customRealName);
                nursePlanManageVO.setBackendPersonRealName(backendPersonRealName);
                nursePlanManageVOList.add(nursePlanManageVO);
            }
        }
        nursePlanManageResultVO.setRows(nursePlanManageVOList);
        nursePlanManageResultVO.setTotal(total);
        return nursePlanManageResultVO;
    }

	@Override
	public int updateNursePlanStatus(NursePlanStatusDTO nursePlanStatusDTO) {
		NursePlanPO nursePlanPO = new NursePlanPO();
		BeanUtils.copyProperties(nursePlanStatusDTO, nursePlanPO);
		int affectNum = nursePlanPOMapper.updateByPrimaryKey(nursePlanPO);
		return affectNum;
	}
}