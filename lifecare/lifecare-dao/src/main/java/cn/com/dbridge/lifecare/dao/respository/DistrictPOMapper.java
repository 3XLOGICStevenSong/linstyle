package cn.com.dbridge.lifecare.dao.respository;

import java.util.List;

import cn.com.dbridge.lifecare.dao.po.DistrictPO;

public interface DistrictPOMapper {
    int deleteByPrimaryKey(Integer districtId);

    int insert(DistrictPO record);

    DistrictPO selectByPrimaryKey(Integer districtId);

    List<DistrictPO> selectAll();

    int updateByPrimaryKey(DistrictPO record);

    /**
     * 
     * @Title: selectDistrictByCityId 
     * @Description:根据市编号获取区信息
     * @author 郭健
     * @param cityId
     * @return
     */
    List<DistrictPO> selectDistrictByCityId(DistrictPO record);
}