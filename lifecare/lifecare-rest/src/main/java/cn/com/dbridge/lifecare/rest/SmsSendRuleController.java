package cn.com.dbridge.lifecare.rest;

import java.lang.reflect.InvocationTargetException;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.lifecare.framework.base.Result;
import cn.com.dbridge.lifecare.framework.dblog.annotation.OperatorLog;
import cn.com.dbridge.lifecare.framework.dto.web.WebSmsSendRuleDTO;
import cn.com.dbridge.lifecare.framework.vo.web.WebSmsSendRuleVO;
import cn.com.dbridge.lifecare.service.SmsSendRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * 
 * @ClassName:SmsSendRuleController
 * @Description:短息推送规则Controller
 * @author:陈健飞
 * @date:2018年12月27日 下午2:21:38
 * 
 * @Copyright:2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@RestController
@Api(tags = "短信推送规则")
@RequestMapping(value = "/api")
public class SmsSendRuleController {

    @Autowired
    private SmsSendRuleService smsSendRuleService;

    /**
     * 
     * @Title:getSmsSendRules
     * @Description:查询短信发送规则
     * author:王林江
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @ApiOperation(value = "查询短信发送规则", notes = "查询短信发送规则")
    @GetMapping(value = "/smsSendRule")
    @RequiresAuthentication
    public Result<WebSmsSendRuleVO> getSmsSendRules() {
        return new Result<WebSmsSendRuleVO>(HttpStatus.OK.value(), "操作成功",
                smsSendRuleService.getSmsSendRules());
    }

    /**
     * 
     * @Title: saveSmsSendRules
     * @Description: 保存短信发送规则
     * @param webSmsSendRuleDTO 短息推送规则DTO
     * author:王林江
     * @return
     */

    @ApiOperation(value = "保存短信发送规则", notes = "保存短信发送规则")
    @PostMapping(value = "/smsSendRule")
    @OperatorLog(module = "短信发送规则", methods = "保存短信发送规则", description = "保存短信发送规则")
    @RequiresAuthentication
    public Result<Object> saveSmsSendRules(
            @ApiParam(name = "webSmsSendRuleDTO", value = "短息推送规则DTO") @RequestBody @Valid WebSmsSendRuleDTO webSmsSendRuleDTO) {
        //保存短信发送规则
        smsSendRuleService.saveSmsSendRules(webSmsSendRuleDTO);
        return new Result<Object>(HttpStatus.OK.value(), "操作成功");
    }

}