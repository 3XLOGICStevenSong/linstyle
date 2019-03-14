package cn.com.dbridge.lifecare.rest;

import java.text.ParseException;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.lifecare.framework.base.WebPageResult;
import cn.com.dbridge.lifecare.framework.dto.web.OperatorLogQueryDTO;
import cn.com.dbridge.lifecare.framework.vo.web.OperatorLogVO;
import cn.com.dbridge.lifecare.service.OperatorLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName:OperatorLogController
 * @Description:操作日志Controller
 * @author:陈健飞
 * @date:2018年12月27日 下午2:20:41
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@RestController
@Api(tags = "操作日志")
@RequestMapping(value = "/api")
public class OperatorLogController {
    @Autowired
    private OperatorLogService operatorLogService;
    /**
     *
     * @Title: queryOperatorLog
     * @Description: 查询操作日志
     * @return
     * @throws ParseException 
     */
    @ApiOperation(value = "日志管理", notes = "查询操作日志")
    @PostMapping("/queryOperatorLog")
    @RequiresAuthentication
    public WebPageResult<List<OperatorLogVO>> queryOperatorLog(@RequestBody OperatorLogQueryDTO operatorLogQueryDTO) throws ParseException {
        //页面处理结果
        WebPageResult<List<OperatorLogVO>> webPageResult = operatorLogService.queryLogInfo(operatorLogQueryDTO);
        //返回处理结果
        WebPageResult<List<OperatorLogVO>> rtnPageResult = new WebPageResult<List<OperatorLogVO>>(
                HttpStatus.OK.value(), "操作成功",
                webPageResult.getRows(), webPageResult.getTotal());
        return rtnPageResult;
    }
}