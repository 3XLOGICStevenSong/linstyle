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
	String carpoolDriverPath = (String) response.encodeURL(strPath + "/CarpoolDriver.do");
	String userLink = (String) response.encodeURL(strPath + "/CarpoolUser.do");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" />
<link rel="stylesheet" href="<%=strPath%>/css/css.css">
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/icomoon.css"/>

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

<title>个人资料</title>
</head>
<body>
	<div data-role="page">
		<html:form method="get" action="/CarpoolUser.do" styleId="passengerInfoForm">
			<div class="page1">
				<div data-role="header" data-position="fixed" data-tap-toggle="false">
					<header>
						<h3 style="text-align: center; color: #000;">个人资料</h3>
						<a class="back" href="javascript:void(0);return;" onclick="saveValue('dp_flag','0');formSubmitByPath(document.forms[0],'<%=userLink%>');doButtonSubmit(document.forms[0], 'doInitRole', 'submitField')"></a>
					</header>
				</div>
				<div data-role="content">
					<div class="content">
						<logic:present name="CarpoolUserForm">
							<section class="text">
								<p style="border-bottom:1px dashed #999;color:#999;font-weight:700">账号：${CarpoolUserForm.cu_code}</p>
								<section class="t">
									<p class="text1"><span class="icon-admin"></span>
										昵称：<c:out value="${CarpoolUserForm.cu_nick}" />
									</p>
									<p class="text2"><span class="icon-mobile"></span>
										手机：<c:out value="${CarpoolUserForm.cu_tel}" />
									</p>
								</section>
	
								<section class="button1">
	
									<a href="javascript:void(0);return;" onclick="formSubmitByPath(document.forms[0],'<%=userLink%>');doButtonSubmit(document.forms[0], 'doModifyDriver', 'submitField');">修改</a>
								</section>
							</section>
							<section class="list1" style="border-bottom:1px solid #d0d0d0">
								<ul>
								<!-- 
									<li class="one">
										<a href="#">&nbsp;我的行程</a>
										
										<i></i>
									</li>
									<li class="two">
										<a href="#" onclick="doButtonSubmit(document.forms[0], 'doVerifyDriver', 'submitField');">&nbsp;车主认证</a>
										<i></i>
									</li>
									 -->
									 
							       <a href="javascript:void(0);return;"  onclick="saveValue('dp_flag','0'); saveValue('requestBy','h5');formSubmitByPath(document.forms[0],'<%=carpoolDriverPath%>');doButtonSubmit(document.forms[0], 'getDriverRouteList', 'submitField');"><li><span class="icon-file-text2"></span>我的行程<i></i></li></a>
							       <a href="javascript:void(0);return;" onclick="formSubmitByPath(document.forms[0],'<%=userLink%>');doButtonSubmit(document.forms[0], 'doVerifyDriver', 'submitField');"><li><span class="icon-user-check"></span>车主认证<i></i></li></a>
								  <a href="javascript:void(0);return;" onclick="formSubmitByPath(document.forms[0],'<%=userLink%>');doButtonSubmit(document.forms[0], 'changeToPassword', 'submitField');"><li style="padding:2px 20px 5px 20px"><img src="images/cog.png" width="20" height="20" style="padding:7px 6px 0 0">修改密码<i></i></li></a>
								</ul>
							</section>
							<section class="list1">     
							    <ul>   
							       <a href="#" onclick="if(confirm('您确定要退出当前账号?')){doButtonSubmit(document.forms[0], 'doLogout', 'submitField');}"><li style="text-align:center;color:#C33">退出登录</li></a>
							    </ul>
							</section>
						</logic:present>
					</div>
				</div>
			</div>
			
			  <div class="box1" style="display:none;" id="htmlerror">
				<img src="<%=strPath%>/images/notification.png"  width="20" height="20" style="position:absolute;left:10px;top:13px">
			 	<div class="txt" id="error" style="text-align:center"><html:errors/></div>	
			</div>
			<html:hidden property="submitField" styleId="submitField" value="" />
			<html:hidden property="cu_id" styleId="cu_id" />
			<html:hidden property="cu_code" styleId="cu_code" />
			<html:hidden property="cu_nick" styleId="cu_nick" />
			<html:hidden property="cu_tel" styleId="cu_tel" />
			<html:hidden property="dp_flag" styleId="dp_flag" />
			<html:hidden property="requestBy" styleId="requestBy" value="h5"  />
		</html:form>
	</div>
	
</body>
</html>
