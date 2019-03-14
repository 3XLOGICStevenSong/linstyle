package com.djb.ylt.alipay.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.djb.ylt.alipay.config.AlipayConfig;
import com.djb.ylt.alipay.dto.AlipayNotification;
import com.djb.ylt.alipay.util.AlipayCore;
import com.djb.ylt.alipay.util.AlipayNotify;
import com.djb.ylt.alipay.util.RequestUtils;






@Controller
@RequestMapping("store/thirdparty/pay/alipay")
public class AsynAlipayNotifyAction {

	
	private static Logger logger = LoggerFactory.getLogger(AsynAlipayNotifyAction.class);
		
	/**
	 * 异步接受支付宝支付结果 
	 * 支付宝服务器调用
	 * 
	 * @param request
	 * @param response
	 */

	@RequestMapping(value = "notify", method = RequestMethod.POST)
	public void receiveNotify(HttpServletRequest request, HttpServletResponse response) {
		
		
		Map<String, String> underScoreKeyMap = RequestUtils.getStringParams(request);
		Map<String, String> camelCaseKeyMap = RequestUtils.convertKeyToCamelCase(underScoreKeyMap);
		
		//首先验证调用是否来自支付宝
		boolean verifyResult = AlipayNotify.verify(underScoreKeyMap);
		
		try {
			
			String jsonString = JSON.toJSONString(camelCaseKeyMap);
			AlipayNotification notice = JSON.parseObject(jsonString, AlipayNotification.class);
			notice.setVerifyResult(verifyResult);
			
			String resultResponse = "success";
			PrintWriter printWriter = null;
			try {
				printWriter = response.getWriter();
				
				//do business
				if(verifyResult){
					
				}
				//fail due to verification error
				else{
					resultResponse = "fail";
				}
				
			} catch (Exception e) {
				logger.error("alipay notify error :", e);
				resultResponse = "fail";
				printWriter.close();
			}
			
			
			if (printWriter != null) {
				printWriter.print(resultResponse);
			}
			
		} catch (Exception e1) {
		
			e1.printStackTrace();
		} 
	}
	
	/**
	 * create the order info. 创建订单信息
	 * @param out_trade_no// 商户网站唯一订单号
	 * @param subject// 商品名称
	 * @param body// 商品详情
	 * @param price // 商品金额
	 * @param service_url// 服务器异步通知页面路径
	 * @param over_time// 超时时间
	 * @return
	 */
	public  static String getOrderInfo2(String out_trade_no,String subject, String body, String price,String service_url) {
		
		//StringBuffer orderContent=new StringBuffer();
		Map<String,String>params= new HashMap<String, String>();
		params.put("_input_charset", "\"utf-8\"");
		
		params.put("body=" ,"\"" + body + "\"");// 商品详情
		params.put("it_b_pay","\"30m\"");
		
		params.put("notify_url", "\"" + service_url+ "\"");// 服务器异步通知页面路径	
		params.put("out_trade_no" ,"\"" + out_trade_no + "\"" );//// 商户网站唯一订单号	
		params.put("partner" ,"\"" + AlipayConfig.partner + "\"");// 签约合作者身份ID
		params.put("payment_type","\"1\"");// 支付类型， 固定值
		params.put("seller_id" , "\"" + AlipayConfig.seller_id + "\"" );//商户ID
		params.put("service","mobile.securitypay.pay");// 服务接口名称， 固定值
		params.put("show_url", "\"mobile.securitypay.pay\"");// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		params.put("subject" ,  "\"" + subject + "\"");// 商品名称		
		params.put("total_fee" ,  "\"" + price + "\"");// 商品金额
		String orderContent=AlipayCore.createLinkString(params);
	//String sign=	StringUtils.buildRequestMysign(params);
	
		return orderContent;
	}
	public  static String getOrderInfo1(String out_trade_no,String subject, String body, String price,String service_url) {
		
		StringBuffer orderContent=new StringBuffer();
		orderContent.append("_input_charset=\"utf-8\"");// 参数编码， 固定值
		orderContent.append("&body=" + "\"" + body + "\"");// 商品详情
		orderContent.append("&it_b_pay=\"30m\"");
		orderContent.append("&partner=" + "\"" + AlipayConfig.partner + "\"");// 签约合作者身份ID
		orderContent.append("&notify_url="+ "\"" + service_url+ "\"");// 服务器异步通知页面路径	
		orderContent.append("&out_trade_no=" + "\"" + out_trade_no + "\"");//// 商户网站唯一订单号	
		orderContent.append("&payment_type=\"1\"");// 支付类型， 固定值
		orderContent.append("&seller_id=" + "\"" + AlipayConfig.seller_id + "\"");//商户ID
		orderContent.append("&service=\"mobile.securitypay.pay\"");// 服务接口名称， 固定值
		orderContent.append("&show_url=\"m.alipay.com\"");// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderContent.append("&subject=" + "\"" + subject + "\"");// 商品名称		
		orderContent.append("&total_fee=" + "\"" + price + "\"");// 商品金额
		
		
		
		
		
		
		return orderContent.toString();
	}
	
	public static String getOrderInfo(String out_trade_no,String subject, String body, String price,String notifyurl) {

		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + AlipayConfig.partner + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + AlipayConfig.seller_id + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + out_trade_no + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + notifyurl + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		return orderInfo;
	}
	
	
}
