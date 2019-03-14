package cn.com.dbridge.lifecare.dao.respository;

import cn.com.dbridge.lifecare.dao.po.CityPO;
import java.util.List;

import org.apache.ibatis.annotations.Param;
/**
 * 
 * @ClassName:  CityPOMapper
 * @Description:城市PO
 * @author: 陈健飞
 * @date:   2019年1月15日 下午7:01:09
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public interface CityPOMapper {
    int deleteByPrimaryKey(Integer cityId);

    int insert(CityPO record);

    CityPO selectByPrimaryKey(Integer cityId);

    List<CityPO> selectAll();

    int updateByPrimaryKey(CityPO record);

    List<CityPO> selectCityByProvinceId(@Param("provinceId") String provinceId);
}