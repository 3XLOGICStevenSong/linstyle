package cn.com.dbridge.lifecare.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.lifecare.dao.po.ProvincePO;
import cn.com.dbridge.lifecare.framework.base.Result;
import cn.com.dbridge.lifecare.framework.vo.mobile.MobileProvinceVO;
import cn.com.dbridge.lifecare.service.ProvinceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 
 * @ClassName:ProvinceController
 * @Description:省Controller
 * @author:陈健飞
 * @date:2018年12月27日 下午2:21:11
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@RestController
@Api(tags = "省")
@RequestMapping(value = "/api")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;
    /**
     * 
     * @Title: queryProvince
     * @Description: 查询所有省信息
     * @return
     */
    @ApiOperation(value = "查询省信息", notes = "查询省信息")
    @GetMapping("/mobile/province/query")
    @RequiresAuthentication
    public Result<List<MobileProvinceVO>> queryProvince() {
        List<ProvincePO> provincePOList = provinceService.queryAll();
        MobileProvinceVO mobileProvinceVO = null;
        List<MobileProvinceVO> mobileProvinceVOList = new ArrayList<MobileProvinceVO>();
        if (!CollectionUtils.isEmpty(provincePOList)) {
            for (ProvincePO provincePO : provincePOList) {
                mobileProvinceVO = new MobileProvinceVO();
                BeanUtils.copyProperties(provincePO, mobileProvinceVO);
                mobileProvinceVOList.add(mobileProvinceVO);
            }
        }
        return new Result<List<MobileProvinceVO>>(HttpStatus.OK.value(),
                "操作成功", mobileProvinceVOList);
    }
    
}