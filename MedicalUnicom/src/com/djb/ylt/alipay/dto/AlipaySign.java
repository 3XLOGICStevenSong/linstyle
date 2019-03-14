package com.djb.ylt.alipay.dto;

import java.io.Serializable;
import java.util.Date;



public class AlipaySign implements Serializable{
	


    /**
	 * serialVersionUID:用一句话描述这个变量表示什么。
	 */
	private static final long serialVersionUID = -4141116767850555273L;

	private Integer alipay_notice_id;

	private String notify_id;

    private String notify_type;

    private Integer appointId;
    
    private Date notify_time;

    private String sign_type;

    private String sign;

    private String out_trade_no;

    private String subject;

    private String payment_ype;

    private String trade_no;

    private String trade_status;

    private String seller_id;

    private String seller_email;

    private String buyer_id;

    private String buyer_email;

    private Double total_fee;

    private Integer quantity;

    private String notify_url;

    private String body;

   // @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private String service;

   // @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private String payment_type;

    private String input_charset;

    private String it_b_pay;

    private String show_url;

	/**
	 * 返回alipay_notice_id的值
	 * @return Integer alipay_notice_id的值
	 */
	public Integer getAlipay_notice_id() {
		return alipay_notice_id;
	}

	/**
	 * 设置alipay_notice_id的值
	 * @param  alipay_notice_id alipay_notice_id的值
	 */
	public void setAlipay_notice_id(Integer alipay_notice_id) {
		this.alipay_notice_id = alipay_notice_id;
	}

	/**
	 * 返回notify_id的值
	 * @return String notify_id的值
	 */
	public String getNotify_id() {
		return notify_id;
	}

	/**
	 * 设置notify_id的值
	 * @param  notify_id notify_id的值
	 */
	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

	/**
	 * 返回notify_type的值
	 * @return String notify_type的值
	 */
	public String getNotify_type() {
		return notify_type;
	}

	/**
	 * 设置notify_type的值
	 * @param  notify_type notify_type的值
	 */
	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	/**
	 * 返回appointId的值
	 * @return Integer appointId的值
	 */
	public Integer getAppointId() {
		return appointId;
	}

	/**
	 * 设置appointId的值
	 * @param  appointId appointId的值
	 */
	public void setAppointId(Integer appointId) {
		this.appointId = appointId;
	}

	/**
	 * 返回notify_time的值
	 * @return Date notify_time的值
	 */
	public Date getNotify_time() {
		return notify_time;
	}

	/**
	 * 设置notify_time的值
	 * @param  notify_time notify_time的值
	 */
	public void setNotify_time(Date notify_time) {
		this.notify_time = notify_time;
	}

	/**
	 * 返回sign_type的值
	 * @return String sign_type的值
	 */
	public String getSign_type() {
		return sign_type;
	}

	/**
	 * 设置sign_type的值
	 * @param  sign_type sign_type的值
	 */
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	/**
	 * 返回sign的值
	 * @return String sign的值
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * 设置sign的值
	 * @param  sign sign的值
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * 返回out_trade_no的值
	 * @return String out_trade_no的值
	 */
	public String getOut_trade_no() {
		return out_trade_no;
	}

	/**
	 * 设置out_trade_no的值
	 * @param  out_trade_no out_trade_no的值
	 */
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	/**
	 * 返回subject的值
	 * @return String subject的值
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 设置subject的值
	 * @param  subject subject的值
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 返回payment_ype的值
	 * @return String payment_ype的值
	 */
	public String getPayment_ype() {
		return payment_ype;
	}

	/**
	 * 设置payment_ype的值
	 * @param  payment_ype payment_ype的值
	 */
	public void setPayment_ype(String payment_ype) {
		this.payment_ype = payment_ype;
	}

	/**
	 * 返回trade_no的值
	 * @return String trade_no的值
	 */
	public String getTrade_no() {
		return trade_no;
	}

	/**
	 * 设置trade_no的值
	 * @param  trade_no trade_no的值
	 */
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	/**
	 * 返回trade_status的值
	 * @return String trade_status的值
	 */
	public String getTrade_status() {
		return trade_status;
	}

	/**
	 * 设置trade_status的值
	 * @param  trade_status trade_status的值
	 */
	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	/**
	 * 返回seller_id的值
	 * @return String seller_id的值
	 */
	public String getSeller_id() {
		return seller_id;
	}

	/**
	 * 设置seller_id的值
	 * @param  seller_id seller_id的值
	 */
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	/**
	 * 返回seller_email的值
	 * @return String seller_email的值
	 */
	public String getSeller_email() {
		return seller_email;
	}

	/**
	 * 设置seller_email的值
	 * @param  seller_email seller_email的值
	 */
	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	/**
	 * 返回buyer_id的值
	 * @return String buyer_id的值
	 */
	public String getBuyer_id() {
		return buyer_id;
	}

	/**
	 * 设置buyer_id的值
	 * @param  buyer_id buyer_id的值
	 */
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	/**
	 * 返回buyer_email的值
	 * @return String buyer_email的值
	 */
	public String getBuyer_email() {
		return buyer_email;
	}

	/**
	 * 设置buyer_email的值
	 * @param  buyer_email buyer_email的值
	 */
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}

	/**
	 * 返回total_fee的值
	 * @return Double total_fee的值
	 */
	public Double getTotal_fee() {
		return total_fee;
	}

	/**
	 * 设置total_fee的值
	 * @param  total_fee total_fee的值
	 */
	public void setTotal_fee(Double total_fee) {
		this.total_fee = total_fee;
	}

	/**
	 * 返回quantity的值
	 * @return Integer quantity的值
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * 设置quantity的值
	 * @param  quantity quantity的值
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * 返回notify_url的值
	 * @return String notify_url的值
	 */
	public String getNotify_url() {
		return notify_url;
	}

	/**
	 * 设置notify_url的值
	 * @param  notify_url notify_url的值
	 */
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	/**
	 * 返回body的值
	 * @return String body的值
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 设置body的值
	 * @param  body body的值
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 返回service的值
	 * @return String service的值
	 */
	public String getService() {
		return service;
	}

	/**
	 * 设置service的值
	 * @param  service service的值
	 */
	public void setService(String service) {
		this.service = service;
	}

	/**
	 * 返回payment_type的值
	 * @return String payment_type的值
	 */
	public String getPayment_type() {
		return payment_type;
	}

	/**
	 * 设置payment_type的值
	 * @param  payment_type payment_type的值
	 */
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	/**
	 * 返回input_charset的值
	 * @return String input_charset的值
	 */
	public String getInput_charset() {
		return input_charset;
	}

	/**
	 * 设置input_charset的值
	 * @param  input_charset input_charset的值
	 */
	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}

	/**
	 * 返回it_b_pay的值
	 * @return String it_b_pay的值
	 */
	public String getIt_b_pay() {
		return it_b_pay;
	}

	/**
	 * 设置it_b_pay的值
	 * @param  it_b_pay it_b_pay的值
	 */
	public void setIt_b_pay(String it_b_pay) {
		this.it_b_pay = it_b_pay;
	}

	/**
	 * 返回show_url的值
	 * @return String show_url的值
	 */
	public String getShow_url() {
		return show_url;
	}

	/**
	 * 设置show_url的值
	 * @param  show_url show_url的值
	 */
	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}
}