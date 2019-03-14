package cn.com.dbridge.lifecare.service;

import java.util.List;

import cn.com.dbridge.lifecare.dao.po.DistrictPO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileDistrictDTO;
import cn.com.dbridge.lifecare.framework.dto.web.DistrictDTO;

public interface DistrictService {
    int deleteDistrictById(Integer districtId);

    int add(MobileDistrictDTO mobileDistrictDTO);

    DistrictPO getDistrictById(Integer districtId);

    List<DistrictPO> queryAll();

    int updateDistrict(MobileDistrictDTO mobileDistrictDTO);

    /**
     * 
     * @Title: queryDistrictByCityId
     * @author 郭健
     * @Description:根据登录市编号获取区信息
     * @param cityId
     * @return
     */
    public List<DistrictPO> queryDistrictByCityId(DistrictDTO districtDTO);
}