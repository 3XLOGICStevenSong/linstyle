/**
 * Project Name:MedicalUnicomAPP
 * File Name:PatientAction.java
 * Package Name:com.djb.ylt.user.action
 * Copyright © 大连必捷必信息技术有限公司  All Rights Reserved.
*/

package com.djb.ylt.user.action;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.djb.ylt.alipay.action.AsynAlipayNotifyAction;
import com.djb.ylt.alipay.config.AlipayConfig;
import com.djb.ylt.alipay.util.SignUtils;

import com.djb.ylt.common.util.Constants;
import com.djb.ylt.common.util.GsonUtil;
import com.djb.ylt.common.util.OrderNumberUtil;
import com.djb.ylt.common.util.ResourceLocator;

import com.djb.ylt.framework.action.BaseAction;

import com.djb.ylt.user.dto.EmergencyDoctorDTO;
import com.djb.ylt.user.dtoclient.AppointInsertClientDTO;
import com.djb.ylt.user.entity.AppointInquiryEntity;
import com.djb.ylt.user.entity.EmergencyDoctorEntity;
import com.djb.ylt.user.entity.RecordsEntity;
import com.djb.ylt.user.service.IAppointInquiryService;
import com.djb.ylt.user.service.IEmergencyDoctorService;
import com.djb.ylt.user.service.IRecordsService;
import com.djb.ylt.wechat.Utils.HttpXmlUtils;
import com.djb.ylt.wechat.Utils.JdomParseXmlUtils;
import com.djb.ylt.wechat.Utils.RandCharsUtils;
import com.djb.ylt.wechat.Utils.WXSignUtils;
import com.djb.ylt.wechat.Utils.WeixinConfigUtils;
import com.djb.ylt.wechat.entity.Unifiedorder;
import com.djb.ylt.wechat.entity.UnifiedorderResult;

/**
 * <p>
 * <strong>类名: </strong>
 * </p>
 * PatientAction <br/>
 * <p>
 * <strong>功能说明: </strong>
 * </p>
 * 这个类是封装那个哪个模块，起什么用的，需要手动填写. <br/>
 * <p>
 * <strong>创建日期: </strong><br/>
 * </p>
 * 2016年7月29日下午1:19:01 <br/>
 * 
 * @author 林行
 * @version 1.0
 * @since JDK 1.8
 */

@Controller("/EmergencyDoctor")
public class EmergencyDoctorAction extends BaseAction {

	@Autowired
	@Qualifier("iEmergencyDoctorService")
	private IEmergencyDoctorService iEmergencyDoctorService;

	@Autowired
	@Qualifier("iAppointInquiryService")
	private IAppointInquiryService iAppointInquiryService;

	@Autowired
	@Qualifier("iRecordsService")
	private IRecordsService iRecordsService;

	public EmergencyDoctorAction() {
		super();
	}

	/**
	 * 预约问诊
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance
	 * @param form
	 *            The optional ActionForm bean for this request
	 * @param request
	 *            The servlet request we are processing
	 * @param response
	 *            The servlet response we are creating
	 * 
	 * @exception Exception
	 *                if business logic throws an exception
	 */
	public ActionForward doAppointEmergency(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		EmergencyDoctorDTO paramDTO = (EmergencyDoctorDTO) form;
		// 参数Entity
		EmergencyDoctorEntity paramEntity = new EmergencyDoctorEntity();

		// 结果ClientDTO
		AppointInsertClientDTO resultClientDTO = new AppointInsertClientDTO();
		StringBuffer stringBuffer = new StringBuffer();
		// DB查询
		try {

			paramEntity = iEmergencyDoctorService.getObject();
			AppointInquiryEntity appointInquiryEntity = new AppointInquiryEntity();
			if (paramEntity != null) {
				// 参数Entity
				// 日期时间戳

				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String str = sdf.format(date);
				stringBuffer.append(str);
				// 订单号（程序自动生成）
				OrderNumberUtil orderNumberUtil = new OrderNumberUtil();
				// 采番订单编号
				stringBuffer.append(orderNumberUtil.getOrderNumber(request));
				appointInquiryEntity.setOrderNumber(stringBuffer.toString());
				appointInquiryEntity.setPatientId(paramDTO.getPatientId());
				appointInquiryEntity.setDoctorId(paramEntity.getEdDocid());
				appointInquiryEntity.setAppointType("3");
				if ("0".equals(paramDTO.getTotalFee()) || "0.0".equals(paramDTO.getTotalFee())
						|| "0.00".equals(paramDTO.getTotalFee())) {
					appointInquiryEntity.setPayStatus("1");
				}
				if (paramDTO.getTotalFee() != null) {
					appointInquiryEntity.setBuyTotal(Double.parseDouble(paramDTO.getTotalFee()));
					BigDecimal price = new BigDecimal(paramDTO.getTotalFee());
					BigDecimal mul = new BigDecimal("0.7");// 医生获取0.7的比例
					BigDecimal result = price.multiply(mul);// 相乘结果
					// System.out.println(result);
					BigDecimal one = new BigDecimal("1");
					double doctorTotal = result.divide(one, 1, BigDecimal.ROUND_HALF_UP).doubleValue();// 保留1位数
					appointInquiryEntity.setDoctorTotal(doctorTotal);
				}
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
				Date appointDate = new Date();
				appointInquiryEntity.setAppointDate(sdf1.format(appointDate));
				appointInquiryEntity.setWorkType("2");
				iAppointInquiryService.addAppointInquiry(appointInquiryEntity);
				if (appointInquiryEntity.getAppointId() != null) {
					RecordsEntity recordsEntity = new RecordsEntity();
					recordsEntity.setAppointId(appointInquiryEntity.getAppointId());
					List<RecordsEntity> recordsList = new ArrayList<RecordsEntity>();
					recordsList = iRecordsService.getRecordsList(recordsEntity);
					if (recordsList != null && recordsList.size() > 0) {
						resultClientDTO.setRecordsId(recordsList.get(0).getRecordsId());
					}
					resultClientDTO.setAppointId(appointInquiryEntity.getAppointId());
					resultClientDTO.setOrderNumber(stringBuffer.toString());

				} else {
					paramDTO.setErrFlg(true);
					resultClientDTO.setReturnCode("-1013");
					toJson(response, resultClientDTO);
					return null;
				}
			} else {
				paramDTO.setErrFlg(true);
				resultClientDTO.setReturnCode("-1013");
				toJson(response, resultClientDTO);
				return null;
			}

			// return 处理
			if (!paramDTO.isErrFlg()) {
				resultClientDTO.setReturnCode("0");
				resultClientDTO.setUserTel(paramEntity.getEdUsertel());
				if (!"0".equals(paramDTO.getTotalFee()) && !"0.0".equals(paramDTO.getTotalFee())
						&& !"0.00".equals(paramDTO.getTotalFee())) {
					// 支付宝支付
					String hostIp = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS);
					if ("0".equals(paramDTO.getPayType())) {
						StringBuffer url = new StringBuffer();
						url.append(hostIp);
						url.append("Appoint.do?" + "receiveNotify" + "=");
						// url.append("&orderNumber=");
						// url.append(stringBuffer.toString());
						String service_url = url.toString();

						String orderContent = AsynAlipayNotifyAction.getOrderInfo(stringBuffer.toString(), "购买急诊视频",
								"支付宝支付", paramDTO.getTotalFee(), service_url);
						String sign = sign(orderContent);
						try {
							// 签名
							sign = URLEncoder.encode(sign, "UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						StringBuffer sb = new StringBuffer();
						sb.append(orderContent);
						sb.append("&sign=\"" + sign + "\"&");
						sb.append("sign_type=\"RSA\"");
						resultClientDTO.setAliPaySignInfo(sb.toString());
						AppointInquiryEntity payInfoEntity = new AppointInquiryEntity();
						payInfoEntity.setAppointId(appointInquiryEntity.getAppointId());
						payInfoEntity.setPayInfo(sb.toString());
						payInfoEntity.setPayType("0");
						iAppointInquiryService.updateAppointInquiry(payInfoEntity);
					} else if ("1".equals(paramDTO.getPayType())) {// 微信支付
						// 构造xml参数
						Unifiedorder unifiedorder = this.weChatParam(stringBuffer.toString(), "购买急诊视频问诊",
								paramDTO.getTotalFee(), request);

						String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);

						String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

						String method = "POST";

						String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();

						UnifiedorderResult unifiedorderResult = JdomParseXmlUtils.getUnifiedorderResult(weixinPost);

						if ("SUCCESS".equals(unifiedorderResult.getReturn_code())) {
							// 再次验签
							UnifiedorderResult result = this.weChatParamAgain(unifiedorderResult);
							String reslutxml = GsonUtil.getJsonStringFormBean(result);
							String payInfo = HttpXmlUtils.xmlInfoJson(result);
							resultClientDTO.setAliPaySignInfo(reslutxml);
							AppointInquiryEntity payInfoEntity = new AppointInquiryEntity();
							payInfoEntity.setAppointId(appointInquiryEntity.getAppointId());
							// payInfoEntity.setOrderNumber(stringBuffer.toString());
							payInfoEntity.setPayInfo(payInfo);
							payInfoEntity.setPayType("1");
							iAppointInquiryService.updateAppointInquiry(payInfoEntity);
						} else {
							paramDTO.setErrFlg(true);
							resultClientDTO.setReturnCode("-1031");
						}

						// System.out.println(xmlInfo);
						// ParseXMLUtils.jdomParseXml(weixinPost);
					}
				} else {
					paramDTO.setErrFlg(true);
					resultClientDTO.setReturnCode("-1031");
				}

			}

		} catch (Exception e) {
			paramDTO.setErrFlg(true);
			resultClientDTO.setReturnCode("-1013");
		}
		// 返回结果
		toJson(response, resultClientDTO);

		return null;
	}

	private String sign(String content) {
		return SignUtils.sign(content, AlipayConfig.private_key);
	}

	/**
	 * 微信支付参数拼接
	 */
	public Unifiedorder weChatParam(String orderNum, String subject, String price, HttpServletRequest request) {

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
		System.out.println(time_start);
		String time_expire = RandCharsUtils.timeExpire();
		System.out.println(time_expire);
		String hostIp = ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS);
		StringBuffer url = new StringBuffer();
		url.append(hostIp);
		// url.append("Appoint.do?" + "receiveWechatNotify" + "=");
		url.append("Appoint.do");
		String notify_url = url.toString();
		System.out.println("notify_url是：" + notify_url);
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

		return unifiedorder;
	}

	/**
	 * 微信支付参数拼接
	 */
	public UnifiedorderResult weChatParamAgain(UnifiedorderResult unifiedorderResult) {

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
		System.out.println("shijianchuo：" + seconds);
		String sign = WXSignUtils.createSign("UTF-8", signParam);// 再次生成签名
		System.out.println("erci签名是：" + sign);
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
