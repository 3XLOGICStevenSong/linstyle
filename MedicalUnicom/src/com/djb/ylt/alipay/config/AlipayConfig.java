package com.djb.ylt.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088421582419600";
	// appID
	public static String app_id = "2016081001730297";

	// 商户的私钥
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMFLYi8mgUX+RLRQQe3NeP4TmVMCwagAHh2/GNnBAcxZE2vJ0DoE9J142kcByWt++lRHKDVMYWTIHZhNWKLbkthzhrtAbIkSf/xjS18kS6PoIokUEo4A/8SRTK8T8virflaIg/c4vNCtsHdESF4FyDn4ffrKrU1gdJP47ycY/SFpAgMBAAECgYEAvtu3ZAUqKjmKD10b7DoxmPczMrMLEt6i3g5d9E1OgD8IHStVrqRMAN1e8jQCdWEYvnTQb+guVrv99O+ScKRMljOGHTAQZsTRsCXQO0YFT27lH/x508Vg/HFy7IN9fTf9JgZCmN3s1T4axqg2y0J7I9vWkNz1SDSBm1CsPGJ6ADECQQDqFVaetX7Il+r6e4ZDaglsnyWNtZqxBBa9FqRqJx+5KbEuu9pYyzdtIkCOv4W1wMNaRw8EogrzaxAhEpt+cDi1AkEA02RkBI79YxvUQDelTGeHoIsqtaqUPGC9segfmNg3GEKC5VY6vatyASLQ10ISxCE3RRZE2JHQ5F5EJU6TBb76ZQJAZWq9TqG/vlTjf4aJEygb0S3abV0jGlJ1L5NhxIQS4HxJwb+tyA6zgtr2MWKVIbvUZ4al4RH7wJ7ALDQNnmgsiQJAUjjjGk7PygPiC9RZDB3cyBo5U430uINcZV2HJLk8vMfqB/ABSxLrdfunxYJisDzHNa0SvDYDTUBJO0WTtBFzbQJAL/l8Zv9ge9laxy8Uppd1VZvqfS7aavZd5KG1HM4NjlRVQ/8FbnA577FhfP1uzQCdpjq1SIG9dp3uSRmuYOyJkw==";

	// 支付宝的公钥，无需修改该值
	// public static String ali_public_key =
	// "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	// public static String ali_public_key =
	// "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBS2IvJoFF/kS0UEHtzXj+E5lTAsGoAB4dvxjZwQHMWRNrydA6BPSdeNpHAclrfvpURyg1TGFkyB2YTVii25LYc4a7QGyJEn/8Y0tfJEuj6CKJFBKOAP/EkUyvE/L4q35WiIP3OLzQrbB3REheBcg5+H36yq1NYHST+O8nGP0haQIDAQAB";
	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑
	// 支付宝的人给的KEY
	// public static String ali_public_key
	// ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	// 合作伙伴的公钥
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	// 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
	public static String seller_id = "privatedoctor@dalianhuaying.cn";

	// MD5密钥
	public static String key = "96mtxtvabtwzslkysoencz2tt135buqt";

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// format
	public static String input_format = "JSON";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 签名方式 不需修改
	public static String sign_type = "RSA";
	
	// 签名方式 不需修改
	public static String sign_type_MD5 = "MD5";

}
