package com.djb.ylt.wechat.entity;

/**
 * 微信退款结果返回的参数
 * @author linh

 */
public class WXRefundResult {


	private String result_code;//SUCCESS/FAIL
	private String err_code;//错误返回的信息描述
	private String err_code_des;//错误返回的信息描述
	private String return_code;//SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	private String return_msg;//返回信息，如非空，为错误原因
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

}
