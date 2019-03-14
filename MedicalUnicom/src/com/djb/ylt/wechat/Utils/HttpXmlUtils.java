package com.djb.ylt.wechat.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import javax.net.ssl.HttpsURLConnection;

import com.djb.ylt.wechat.entity.Unifiedorder;
import com.djb.ylt.wechat.entity.UnifiedorderResult;






/**
 * post提交xml格式的参数
 * @author linh

 */
public class HttpXmlUtils {

	/**
	 * 开始post提交参数到接口
	 * 并接受返回
	 * @param url
	 * @param xml
	 * @param method
	 * @param contentType
	 * @return
	 */
	public static String xmlHttpProxy(String url,String xml,String method,String contentType){
		InputStream is = null;
		OutputStreamWriter os = null;

		try {
			URL _url = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
			conn.setDoInput(true);   
			conn.setDoOutput(true);   
			conn.setRequestProperty("Content-type", "text/xml");
			conn.setRequestProperty("Pragma:", "no-cache");  
			conn.setRequestProperty("Cache-Control", "no-cache");  
			conn.setRequestMethod("POST");
			os = new OutputStreamWriter(conn.getOutputStream());
			os.write(new String(xml.getBytes(contentType)));
			os.flush();

			//返回值
			is = conn.getInputStream();
			return getContent(is, "utf-8");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(os!=null){os.close();}
				if(is!=null){is.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 解析返回的值
	 * @param is
	 * @param charset
	 * @return
	 */
	public static String getContent(InputStream is, String charset) {
		String pageString = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		try {
			isr = new InputStreamReader(is, charset);
			br = new BufferedReader(isr);
			sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			pageString = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null){
					is.close();
				}
				if(isr!=null){
					isr.close();
				}
				if(br!=null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			sb = null;
		}
		return pageString;
	}

	/**
	 * 构造xml参数
	 * @param xml
	 * @return
	 */
	public static String xmlInfo(Unifiedorder unifiedorder){
		//构造xml参数的时候，至少又是个必传参数
		/*
		 * <xml>
			   <appid>wx2421b1c4370ec43b</appid>
			   <attach>支付测试</attach>
			   <body>JSAPI支付测试</body>
			   <mch_id>10000100</mch_id>
			   <nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>
			   <notify_url>http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php</notify_url>
			   <openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid>
			   <out_trade_no>1415659990</out_trade_no>
			   <spbill_create_ip>14.23.150.211</spbill_create_ip>
			   <total_fee>1</total_fee>
			   <trade_type>JSAPI</trade_type>
			   <sign>0CB01533B8C1EF103065174F50BCA001</sign>
			</xml>
		 */

		if(unifiedorder!=null){
			StringBuffer bf = new StringBuffer();
			bf.append("<xml>");

			bf.append("<appid><![CDATA[");
			bf.append(unifiedorder.getAppid());
			bf.append("]]></appid>");

			bf.append("<mch_id><![CDATA[");
			bf.append(unifiedorder.getMch_id());
			bf.append("]]></mch_id>");

			bf.append("<nonce_str><![CDATA[");
			bf.append(unifiedorder.getNonce_str());
			bf.append("]]></nonce_str>");

			bf.append("<sign><![CDATA[");
			bf.append(unifiedorder.getSign());
			bf.append("]]></sign>");

			bf.append("<body><![CDATA[");
			bf.append(unifiedorder.getBody());
			bf.append("]]></body>");

			bf.append("<detail><![CDATA[");
			bf.append(unifiedorder.getDetail());
			bf.append("]]></detail>");

			bf.append("<attach><![CDATA[");
			bf.append(unifiedorder.getAttach());
			bf.append("]]></attach>");

			bf.append("<out_trade_no><![CDATA[");
			bf.append(unifiedorder.getOut_trade_no());
			bf.append("]]></out_trade_no>");

			bf.append("<total_fee><![CDATA[");
			bf.append(unifiedorder.getTotal_fee());
			bf.append("]]></total_fee>");

			bf.append("<spbill_create_ip><![CDATA[");
			bf.append(unifiedorder.getSpbill_create_ip());
			bf.append("]]></spbill_create_ip>");

			bf.append("<time_start><![CDATA[");
			bf.append(unifiedorder.getTime_start());
			bf.append("]]></time_start>");

			bf.append("<time_expire><![CDATA[");
			bf.append(unifiedorder.getTime_expire());
			bf.append("]]></time_expire>");

			bf.append("<notify_url><![CDATA[");
			bf.append(unifiedorder.getNotify_url());
			bf.append("]]></notify_url>");

			bf.append("<trade_type><![CDATA[");
			bf.append(unifiedorder.getTrade_type());
			bf.append("]]></trade_type>");


			bf.append("</xml>");
			return bf.toString();
		}

		return "";
	}

	/**
	 * 构造xml参数
	 * @param xml
	 * @return
	 */
	public static String xmlInfoJson(UnifiedorderResult unifiedorder){
		//构造xml参数的时候，至少又是个必传参数
		/*
		 * <xml>
			   <appid>wx2421b1c4370ec43b</appid>
			   <attach>支付测试</attach>
			   <body>JSAPI支付测试</body>
			   <mch_id>10000100</mch_id>
			   <nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>
			   <notify_url>http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php</notify_url>
			   <openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid>
			   <out_trade_no>1415659990</out_trade_no>
			   <spbill_create_ip>14.23.150.211</spbill_create_ip>
			   <total_fee>1</total_fee>
			   <trade_type>JSAPI</trade_type>
			   <sign>0CB01533B8C1EF103065174F50BCA001</sign>
			</xml>
		 */
		if(unifiedorder!=null){
			StringBuffer bf = new StringBuffer();
			bf.append("<xml>");

			bf.append("<appid><![CDATA[");
			bf.append(unifiedorder.getAppid());
			bf.append("]]></appid>");

			bf.append("<mch_id><![CDATA[");
			bf.append(unifiedorder.getMch_id());
			bf.append("]]></mch_id>");

			bf.append("<nonce_str><![CDATA[");
			bf.append(unifiedorder.getNonce_str());
			bf.append("]]></nonce_str>");

			bf.append("<sign><![CDATA[");
			bf.append(unifiedorder.getSign());
			bf.append("]]></sign>");

			bf.append("<device_info><![CDATA[");
			bf.append(unifiedorder.getDevice_info());
			bf.append("]]></device_info>");

			bf.append("<result_code><![CDATA[");
			bf.append(unifiedorder.getResult_code());
			bf.append("]]></result_code>");

			bf.append("<err_code_des><![CDATA[");
			bf.append(unifiedorder.getErr_code_des());
			bf.append("]]></err_code_des>");

			bf.append("<err_code><![CDATA[");
			bf.append(unifiedorder.getErr_code());
			bf.append("]]></err_code>");

			bf.append("<prepay_id><![CDATA[");
			bf.append(unifiedorder.getPrepay_id());
			bf.append("]]></prepay_id>");

			bf.append("<code_url><![CDATA[");
			bf.append(unifiedorder.getCode_url());
			bf.append("]]></code_url>");

			bf.append("<timestamp><![CDATA[");
			bf.append(unifiedorder.getTimestamp());
			bf.append("]]></timestamp>");

			bf.append("<return_code><![CDATA[");
			bf.append(unifiedorder.getReturn_code());
			bf.append("]]></return_code>");

			bf.append("<return_msg><![CDATA[");
			bf.append(unifiedorder.getReturn_msg());
			bf.append("]]></return_msg>");
			
			bf.append("<prepayid><![CDATA[");
			bf.append(unifiedorder.getPrepayid());
			bf.append("]]></prepayid>");
			
			bf.append("<partnerid><![CDATA[");
			bf.append(unifiedorder.getPartnerid());
			bf.append("]]></partnerid>");
			
			bf.append("<noncestr><![CDATA[");
			bf.append(unifiedorder.getNoncestr());
			bf.append("]]></noncestr>");
			
			bf.append("<packagesign><![CDATA[");
			bf.append(unifiedorder.getPackagesign());
			bf.append("]]></packagesign>");
			
			bf.append("<trade_type><![CDATA[");
			bf.append(unifiedorder.getTrade_type());
			bf.append("]]></trade_type>");


			bf.append("</xml>");
			return bf.toString();
		}

		return "";
	}
	public static String getRequestXml(SortedMap<Object,Object> parameters){
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v=null;
            int v1=0;
            if("refund_fee".equalsIgnoreCase(k)||"total_fee".equalsIgnoreCase(k)){
            
             v1 = (Integer)entry.getValue();}else{  v = (String)entry.getValue();}
            if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
                sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
            }else if("refund_fee".equalsIgnoreCase(k)||"total_fee".equalsIgnoreCase(k)){
            	// v1=Integer.parseInt(v);
            	 sb.append("<"+k+">"+v1+"</"+k+">");
            }else {
                sb.append("<"+k+">"+v+"</"+k+">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
}
	
	
	/**
	 * post请求并得到返回结果
	 * @param requestUrl
	 * @param requestMethod
	 * @param output
	 * @return
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String output) {
		try{
			URL url = new URL(requestUrl);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod(requestMethod);
			if (null != output) {
				OutputStream outputStream = connection.getOutputStream();
				outputStream.write(output.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			connection.disconnect();
			return buffer.toString();
		}catch(Exception ex){
			ex.printStackTrace();
		}

		return "";
	}
	/**返回给微信的消息
     * 
     * @param return_code
     * @param return_msg
     * @param output
     * @return
     */
    /*<xml> 
    <return_code><![CDATA[SUCCESS]]></return_code>
    <return_msg><![CDATA[OK]]></return_msg>
    </xml>*/
    public static String backWeixin(String return_code, String return_msg) {
        StringBuffer bf = new StringBuffer();
        bf.append("<xml>");

        bf.append("<return_code><![CDATA[");
        bf.append(return_code);
        bf.append("]]></return_code>");

        bf.append("<return_msg><![CDATA[");
        bf.append(return_msg);
        bf.append("]]></return_msg>");

        bf.append("</xml>");
        return bf.toString();
    }
}
