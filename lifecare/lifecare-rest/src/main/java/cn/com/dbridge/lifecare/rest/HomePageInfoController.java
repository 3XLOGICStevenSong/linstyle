package cn.com.dbridge.lifecare.rest;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.lifecare.dao.po.HomePageInfoPO;
import cn.com.dbridge.lifecare.framework.base.Result;
import cn.com.dbridge.lifecare.framework.dblog.annotation.OperatorLog;
import cn.com.dbridge.lifecare.framework.dto.web.WebHomePageInfoDTO;
import cn.com.dbridge.lifecare.framework.vo.mobile.MobileHomePageInfoVO;
import cn.com.dbridge.lifecare.service.HomePageInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @ClassName:HomePageInfoController
 * @Description:主页信息Controller
 * @author:陈健飞
 * @date:2018年12月27日 下午2:20:11
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 注意：本内容仅限于必捷必信息技术有限公司
 *             内部传阅，禁止外泄以及用于其他的商业目的
 */
@RestController
@Api(tags = "主页管理")
@RequestMapping(value = "/api")
public class HomePageInfoController {
	@Autowired
	private HomePageInfoService homePageInfoService;

	/**
	 * 
	 * @Title: getHomePageInfo
	 * @Description: 首页（信息页）页面[手机端]
	 * @return
	 */
	@ApiOperation(value = "查询主页信息", notes = "查询主页信息")
	@GetMapping(value = "/homePageInfo")
	@RequiresAuthentication
	public Result<MobileHomePageInfoVO> getHomePageInfo() {
		HomePageInfoPO homePageInfoPO = homePageInfoService.getHomePageInfo();
		MobileHomePageInfoVO mobileHomePageInfoVO = new MobileHomePageInfoVO();
		if (null != homePageInfoPO) {
			BeanUtils.copyProperties(homePageInfoPO, mobileHomePageInfoVO);
		}
		return new Result<MobileHomePageInfoVO>(HttpStatus.OK.value(), "操作成功", mobileHomePageInfoVO);
	}

	/**
	 * 
	 * @Title: saveHomePageInfo
	 * @Description: 更新主页信息
	 * @param webHomePageInfoDTO 主页信息DTO author:王林江
	 * @return
	 */
	@ApiOperation(value = "更新主页信息", notes = "更新主页信息")
	@PostMapping(value = "/homePageInfo")
	@OperatorLog(module = "主页管理", methods = "更新主页", description = "更新主页")
	public Result<Object> saveHomePageInfo(@ApiParam(name = "webHomePageInfoDTO", value = "主页信息DTO")
											   @RequestBody @Valid WebHomePageInfoDTO webHomePageInfoDTO) {
		// 更新主页信息
		homePageInfoService.saveHomePageInfo(webHomePageInfoDTO);
		return new Result<Object>(HttpStatus.OK.value(), "操作成功");
	}
}