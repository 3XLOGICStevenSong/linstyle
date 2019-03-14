package cn.com.dbridge.lifecare.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.lifecare.dao.po.DistrictPO;
import cn.com.dbridge.lifecare.framework.base.Result;
import cn.com.dbridge.lifecare.framework.dto.web.DistrictDTO;
import cn.com.dbridge.lifecare.framework.vo.mobile.MobileDistrictVO;
import cn.com.dbridge.lifecare.service.DistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 
 * @ClassName:DistrictController
 * @Description:区信息Controller
 * @author:陈健飞
 * @date:2018年12月27日 下午2:19:56
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@RestController
@Api(tags = "区管理")
@RequestMapping(value = "/api")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    /**
     * 
     * @Title: queryDistrictByCityId
     * @Description: 根据市ID查询区信息
     * @param cityId 城市ID
     * @return
     */
    @ApiOperation(value = "区信息管理", notes = "区信息管理")
    @GetMapping("/district/{cityId}")
    @RequiresAuthentication
    public Result<List<MobileDistrictVO>> queryDistrictByCityId(@PathVariable(value="cityId") Integer cityId){
        DistrictDTO districtDTO = new DistrictDTO();
        districtDTO.setCityId(cityId);
        List<DistrictPO> districtPOList = districtService.queryDistrictByCityId(districtDTO);
        List<MobileDistrictVO> mobileDistrictVOList = new ArrayList<MobileDistrictVO>();
        if (!CollectionUtils.isEmpty(districtPOList)) {
            MobileDistrictVO mobileDistrictVO = null;
            for (DistrictPO districtPO : districtPOList) {
                mobileDistrictVO = new MobileDistrictVO();
                BeanUtils.copyProperties(districtPO, mobileDistrictVO);
                mobileDistrictVOList.add(mobileDistrictVO);
            }
        }
        return new Result<List<MobileDistrictVO>>(HttpStatus.OK.value(),"操作成功", mobileDistrictVOList);
    }
}