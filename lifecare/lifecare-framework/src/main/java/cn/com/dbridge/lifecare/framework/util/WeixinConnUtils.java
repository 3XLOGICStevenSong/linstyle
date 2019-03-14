package cn.com.dbridge.lifecare.framework.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;


public class WeixinConnUtils {
	private static final Logger logger = LoggerFactory.getLogger(WeixinConnUtils.class);
	// 微信小程序取得openid等登录信息的URL
	private static final String LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
	// 微信取得access_token的URL
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
	//
	private static final String WXACODEUNLIMIT_URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";

	/**
	 * 小程序登录信息取得
	 * 
	 * @param weixinSrvParam
	 * @return
	 */
	public static JSONObject getLoginJson(String weixinSrvParam) {
		String weixinRs = HttpRequest.sendGet(LOGIN_URL, weixinSrvParam);
		return JSONObject.parseObject(weixinRs);
	}

	/**
	 * 取得access_token
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 */
	public static String getAccessToken(String appid, String secret) {
		String param = "grant_type=client_credential&appid=" + appid + "&secret=" + secret;
		String weixinRs = HttpRequest.sendGet(ACCESS_TOKEN_URL, param);
		JSONObject accessTokenJson = JSONObject.parseObject(weixinRs);
		return accessTokenJson.getString("access_token");
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public static Map<String, Object> getWxacodeunlimit(Map<String, Object> param) {
		String appid = String.valueOf(param.get("appid"));
		String appsecret = String.valueOf(param.get("appsecret"));
		
		String scene = String.valueOf(param.get("scene"));
		String page = String.valueOf(param.get("page"));
		
		String accessToken = WeixinConnUtils.getAccessToken(appid, appsecret);
		logger.info("access_token:" + accessToken);
		
		param = new HashMap<>();
		param.put("scene", scene);
		param.put("page", page);
		param.put("width", 150);
		param.put("auto_color", false);

		Map<String, Object> line_color = new HashMap<>();
		line_color.put("r", 0);
		line_color.put("g", 0);
		line_color.put("b", 0);
		param.put("line_color", line_color);

		return HttpRequest.sendPost(WXACODEUNLIMIT_URL + accessToken , param);
	}
}
