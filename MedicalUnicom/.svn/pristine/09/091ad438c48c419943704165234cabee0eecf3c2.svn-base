package com.djb.ylt.common.util;


import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;

import com.djb.ylt.wechat.Utils.HttpXmlUtils;
import com.djb.ylt.wechat.Utils.RandCharsUtils;
import com.djb.ylt.wechat.Utils.WXSignUtils;
import com.djb.ylt.wechat.Utils.WeixinConfigUtils;
import com.djb.ylt.wechat.entity.Unifiedorder;
import com.djb.ylt.wechat.entity.UnifiedorderResult;





public  class WechatPayUtil {

	/**
	 * 微信支付参数拼接
	 */
	public   static String weChatParam(String orderNum, String subject, String price,String hostIp,String actionName, HttpServletRequest request) {

		// 参数组
		String appid = WeixinConfigUtils.appid;
		System.out.println("appid是：" + appid);
		String mch_id = WeixinConfigUtils.mch_id;
		System.out.println("mch_id是：" + mch_id);
		String nonce_str = RandCharsUtils.getRandomString(16);
		System.out.println("随机字符串是：" + nonce_str);
		// String body = subject;
		// String detail = "谢彬0.01_元测试开始";
		String attach = "备用参数，先留着，后面会有用的";
		// String out_trade_no = "2015112500001000811017342394";

		float temPrice = Float.parseFloat(price);
		String fee = String.format("%.2f", temPrice);
		fee = fee.replace(".", "");
		int pay = Integer.parseInt(fee);
		int total_fee = pay;// 单位是分，即是0.01元

		String spbill_create_ip = request.getRemoteAddr();
		String time_start = RandCharsUtils.timeStart();
		//System.out.println(time_start);
		String time_expire = RandCharsUtils.timeExpire();
	//	System.out.println(time_expire);
		//String hostIp = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS);
		StringBuffer url = new StringBuffer();
		url.append(hostIp);
		// url.append("Appoint.do?" + "receiveWechatNotify" + "=");
		url.append(actionName);
		String notify_url = url.toString();
		//System.out.println("notify_url是：" + notify_url);
		String trade_type = "APP";

		// 参数：开始生成签名
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", appid);
		parameters.put("mch_id", mch_id);
		parameters.put("nonce_str", nonce_str);
		parameters.put("body", subject);
		// parameters.put("nonce_str", nonce_str);
		parameters.put("detail", subject);
		parameters.put("attach", attach);
		parameters.put("out_trade_no", orderNum);
		parameters.put("total_fee", total_fee);
		parameters.put("time_start", time_start);
		parameters.put("time_expire", time_expire);
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", trade_type);
		parameters.put("spbill_create_ip", spbill_create_ip);

		String sign = WXSignUtils.createSign("UTF-8", parameters);
		System.out.println("签名是：" + sign);

		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appid);
		unifiedorder.setMch_id(mch_id);
		unifiedorder.setNonce_str(nonce_str);
		unifiedorder.setSign(sign);
		unifiedorder.setBody(subject);
		unifiedorder.setDetail(subject);
		unifiedorder.setAttach(attach);
		unifiedorder.setOut_trade_no(orderNum);
		unifiedorder.setTotal_fee(total_fee);
		unifiedorder.setSpbill_create_ip(spbill_create_ip);
		unifiedorder.setTime_start(time_start);
		unifiedorder.setTime_expire(time_expire);
		unifiedorder.setNotify_url(notify_url);
		unifiedorder.setTrade_type(trade_type);
		String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);

		String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

		String method = "POST";

		String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
		return weixinPost;
	}

	/**
	 * 微信支付参数拼接
	 */
	public static  UnifiedorderResult weChatParamAgain(UnifiedorderResult unifiedorderResult) {

		long currentTimeMillis = System.currentTimeMillis();// 生成时间戳
		long second = currentTimeMillis / 1000L;// （转换成秒）
		String seconds = String.valueOf(second).substring(0, 10);// （截取前10位）
		// SortedMap<String, String> signParam = new TreeMap<String, String>();
		SortedMap<Object, Object> signParam = new TreeMap<Object, Object>();
		String appid = WeixinConfigUtils.appid;
		// System.out.println("appid是：" + appid);
		String mch_id = WeixinConfigUtils.mch_id;
		signParam.put("appid", WeixinConfigUtils.appid);// app_id
		String prepay_id = null;
		prepay_id = unifiedorderResult.getPrepay_id();
		String noce = RandCharsUtils.getRandomString(16);
		signParam.put("partnerid", WeixinConfigUtils.mch_id);// 微信商户账号
		signParam.put("prepayid", prepay_id);// 预付订单id
		signParam.put("package", "Sign=WXPay");// 默认sign=WXPay
		signParam.put("noncestr", noce);// 自定义不重复的长度不长于32位
		signParam.put("timestamp", seconds);// 北京时间时间戳
		//System.out.println("shijianchuo：" + seconds);
		String sign = WXSignUtils.createSign("UTF-8", signParam);// 再次生成签名
		//System.out.println("erci签名是：" + sign);
		UnifiedorderResult result = new UnifiedorderResult();
		result.setAppid(appid);
		// result.setMch_id(mch_id);
		result.setNoncestr(noce);
		result.setPrepayid(prepay_id);
		result.setSign(sign);
		result.setTrade_type("APP");
		result.setResult_code(unifiedorderResult.getResult_code());
		result.setReturn_code(unifiedorderResult.getReturn_code());
		result.setReturn_msg(unifiedorderResult.getReturn_msg());
		result.setPartnerid(mch_id);
		result.setTimestamp(seconds);
		result.setPackagesign("Sign=WXPay");
		return result;
	}
}
