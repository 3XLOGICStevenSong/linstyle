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
	String carpoolPassengerPath = (String) response.encodeURL(strPath + "/CarpoolPassenger.do");
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

<title>个人资料</title>
</head>
<body>
	<div data-role="page">
		<html:form method="get" action="/CarpoolUser.do" styleId="passengerInfoForm">
			<div class="page1">
				<div data-role="header" data-position="fixed" data-tap-toggle="false">
					<header>
						<h3 style="text-align: center; color: #000;">个人资料</h3>
						<a class="back" href="javascript:void(0);return;" onclick="saveValue('dp_flag','1');formSubmitByPath(document.forms[0],'<%=userLink%>');doButtonSubmit(document.forms[0], 'doInitRole', 'submitField')"></a>
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
	
									<a href="javascript:void(0);return;" onclick="formSubmitByPath(document.forms[0],'<%=userLink%>');doButtonSubmit(document.forms[0], 'doModifyPassenger', 'submitField');">修改</a>
								</section>
							</section>
							<section class="list1" style="border-bottom:1px solid #d0d0d0">
								<ul>
									<a href="javascript:void(0);return;" onclick="saveValue('dp_flag','1'); saveValue('requestBy','h5');formSubmitByPath(document.forms[0],'<%=carpoolPassengerPath%>');doButtonSubmit(document.forms[0], 'getPassengerRouteList', 'submitField');"><li><span class="icon-file-text2"></span>我的行程<i></i></li></a>
       								<a href="javascript:void(0);return;" onclick="formSubmitByPath(document.forms[0],'<%=userLink%>');doButtonSubmit(document.forms[0], 'doVerifyPassenger', 'submitField');"><li><span class="icon-meter"></span>实名认证<i></i></li></a>	
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
			<html:hidden property="submitField" styleId="submitField" value="" />
			<html:hidden property="cu_id" styleId="cu_id" />
			<html:hidden property="cu_code" styleId="cu_code" />
			<html:hidden property="cu_nick" styleId="cu_nick" />
			<html:hidden property="cu_tel" styleId="cu_tel" />
			<html:hidden property="dp_flag" styleId="dp_flag" />
			<html:hidden property="requestBy" styleId="requestBy" value="h5"  />
			<html:hidden property="screenId" styleId="screenId" value="passengerInfo" />
		</html:form>
	</div>

</body>
</html>
