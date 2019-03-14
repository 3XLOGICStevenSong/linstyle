<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<jsp:useBean id="CarpoolUserForm"
	class="com.djb.highway.carpool.dto.CarpoolUserDTO" scope="request" />
<%
	String strPath = (String) request.getContextPath();
%>
<%
	String carpoolDriverPath = (String) response.encodeURL(strPath
		+ "/CarpoolDriver.do");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" />
<link rel="stylesheet" href="<%=strPath%>/css/css.css">
<script src="<%=strPath%>/js/common/common.js"></script>
<script src="<%=strPath%>/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	 var errorValue=$("#error").html();
	 if(errorValue!=''&& errorValue!=null&& errorValue!=undefined){
			
			$(".box1").css({
				left:($("body").width()-$(".box1").width())/2-20+"px",
				top:($(window).height()-$(".box1").height())/2+$(window).scrollTop()+"px",
				display:"block"
			}).show(100).delay(1000).hide(100);
			return;
			}
});
</script>

<title>协议</title>
</head>
<body>
	<div data-role="page">
		<html:form method="get" action="/CarpoolUser.do" styleId="passengerInfoForm">
			<div class="page1">
				<div data-role="header" data-position="fixed" data-tap-toggle="false">
					<header>
							<h3 style="text-align: center; color: #000;">协议</h3>
						<a class="back" href="#" onclick="doButtonSubmit(document.forms[0], 'doChangeToRegister', 'submitField')"></a>
					</header>
				</div>
				<div data-role="content">
					<div class="content">
						<logic:present name="CarpoolUserForm">
					     <p style="text-align:center;font-size:18px;margin:10px 20px;display:block">相关协议</p>
<p style="margin:10px 20px;display:block">高速顺风车是一款智能匹配车主与乘客需求的手机app软件。<br>
此软件弥补了高速公路拼车的闲置资源，缓解了交通压力。提倡绿色出行，为拥有车辆的车主和需要搭乘顺风车的乘客提供服务平台。 <br>
使用规范：<br>
在高速顺风车服务过程中实施的所有行为严格遵守国家法律、法规等规范性文件及高速顺风车的各项规定要求，不违背社会公共利益或公共道德，不损害他人合法权益。您如果违反上述承诺，产生的任何法律后果，您应独立承担其法律责任，并确保高速顺风车免于产生任何损失。
在交易过程中，遵守诚实守信原则，不采取不正当行为，不扰乱网上交易的正常秩序。<br>
不发布国家禁止销售或限制销售的商品或服务信息，不发布涉嫌侵犯他人知识产权或其他合法权益商品或服务信息，不发布违背社会公共利益或公共道德的服务信息。
本服务条款的最终解释权归“高速顺丰车”应用所有。
</p>
					     
						</logic:present>
					</div>
				</div>
			</div>
			
			  <div class="box1" style="display:none;" id="htmlerror">
				<img src="<%=strPath%>/images/notification.png"  width="20" height="20" style="position:absolute;left:10px;top:13px">
			 	<div class="txt" id="error" style="text-align:center"><html:errors/></div>	
			</div>
			<html:hidden property="submitField" styleId="submitField" value="" />
			
			<html:hidden property="requestBy" styleId="requestBy" value="h5"  />
		    <html:hidden property="cu_code" styleId="cu_code"   />
		    <html:hidden property="cu_tel" styleId="cu_tel"   />
		    <html:hidden property="cu_question" styleId="cu_question"   />
		    <html:hidden property="cu_answer" styleId="cu_answer"   />
		    <html:hidden property="cu_password" styleId="cu_password"   />
		    <html:hidden property="confirm_password" styleId="confirm_password"   />
		     <html:hidden property="verificationCode" styleId="verificationCode"   />
		     <html:hidden property="agree" styleId="agree"   />
		</html:form>
	</div>
	
</body>
</html>
