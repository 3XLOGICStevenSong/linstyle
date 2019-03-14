package cn.com.dbridge.lifecare.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.dbridge.lifecare.config.WechatAccountConfig;
import cn.com.dbridge.lifecare.framework.base.Result;
import cn.com.dbridge.lifecare.framework.exception.CustomException;
import cn.com.dbridge.lifecare.framework.util.WeixinConnUtils;
import lombok.Data;
/**
 * 
 * @ClassName:  WechatController
 * @Description:微信相关接口
 * @author: 陈健飞
 * @date:   2019年1月29日 下午1:01:03
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Controller
@RequestMapping("/api/wechat")
@Data
public class WechatController {
	
	@Autowired
	private WechatAccountConfig wechatAccountConfig;
	
	/**
	 * 获得当前登录微信用户openid
	 * 
	 * @param params
	 * @return
	 */
	@GetMapping(value = "/getOpenId/{code}",  produces = { "application/json" })
	@ResponseBody
	public Result<String> getOpenid(@PathVariable("code") String code) {
		// 微信公众号或小程序appid
		String appId = wechatAccountConfig.getMpAppId();
		// 微信公众号或小程序密钥
		String appsecret = wechatAccountConfig.getMpAppSecret();
		
		String weixinSrvParam = "appid=" + appId + "&secret=" + appsecret + "&js_code=" + code + "&grant_type=authorization_code";
		JSONObject wxRsjson = WeixinConnUtils.getLoginJson(weixinSrvParam);
		if (wxRsjson == null) {
			throw new CustomException("微信接口调用失败");
		} 
		return new Result<>(HttpStatus.OK.value(),"操作成功",String.valueOf(wxRsjson.get("openid")));
	}
}
