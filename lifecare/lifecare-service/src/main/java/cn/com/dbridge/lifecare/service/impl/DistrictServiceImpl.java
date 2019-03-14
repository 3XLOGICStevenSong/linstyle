package cn.com.dbridge.lifecare.service.impl;

import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dbridge.lifecare.dao.po.DistrictPO;
import cn.com.dbridge.lifecare.dao.respository.DistrictPOMapper;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileDistrictDTO;
import cn.com.dbridge.lifecare.framework.dto.web.DistrictDTO;
import cn.com.dbridge.lifecare.service.DistrictService;
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictPOMapper districtPOMapper;
    @Override
    public int deleteDistrictById(Integer districtId) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int add(MobileDistrictDTO mobileDistrictDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public DistrictPO getDistrictById(Integer districtId) {
    	DistrictPO districtPO = districtPOMapper.selectByPrimaryKey(districtId);
    	if(null == districtPO) {
    		return null;
    	}
    	return districtPO;
    }

    @Override
    public List<DistrictPO> queryAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int updateDistrict(MobileDistrictDTO mobileDistrictDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * 
     * Title: queryDistrictByCityId  
     * Description:根据市编号获取区信息
     * @param cityId
     * @return   
     * @see cn.com.dbridge.lifecare.service.DistrictService#queryDistrictByCityId(java.lang.String)
     */
    @Override
    public List<DistrictPO> queryDistrictByCityId(
            DistrictDTO districtDTO) {
        DistrictPO districtPO = new DistrictPO();
        BeanUtils.copyProperties(districtDTO, districtPO);
        List<DistrictPO> districtPOList = districtPOMapper
                .selectDistrictByCityId(districtPO);
        if (CollectionUtils.isEmpty(districtPOList)) {
            return null;
        }
        return districtPOList;
    }
}