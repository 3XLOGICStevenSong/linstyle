package cn.com.dbridge.lifecare.rest;

import java.lang.reflect.InvocationTargetException;
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

import cn.com.dbridge.lifecare.dao.po.CityPO;
import cn.com.dbridge.lifecare.framework.base.Result;
import cn.com.dbridge.lifecare.framework.vo.mobile.MobileCityVO;
import cn.com.dbridge.lifecare.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 
 * @ClassName:CityController
 * @Description:城市信息Controller
 * @author:陈健飞
 * @date:2018年12月27日 下午2:19:37
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@RestController
@Api(tags = "城市管理")
@RequestMapping(value = "/api")
public class CityController {
    @Autowired
    private CityService cityService;
    /**
     * 
     * @Title: queryCity
     * @Description: 根据省查询市信息
     * @param provinceId 省ID
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @ApiOperation(value = "查询市信息", notes = "查询市信息")
    @GetMapping("/mobile/city/query/{provinceId}")
    @RequiresAuthentication
    public Result<List<MobileCityVO>> queryCity(@PathVariable String provinceId) {
        List<CityPO> cityPOList = cityService.queryCityByProvinceId(provinceId);
        List<MobileCityVO> mobileCityVOList = new ArrayList<MobileCityVO>();
        MobileCityVO mobileCityVO = null;
        if (!CollectionUtils.isEmpty(cityPOList)) {
            for (CityPO cityPO : cityPOList) {
                mobileCityVO = new MobileCityVO();
                BeanUtils.copyProperties(cityPO, mobileCityVO);
                mobileCityVOList.add(mobileCityVO);
            }
        }
        return new Result<List<MobileCityVO>>(HttpStatus.OK.value(), "操作成功",mobileCityVOList);
    }
}