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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" />
<link rel="stylesheet" href="<%=strPath%>/css/css.css">
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/icomoon.css"/>

<script src="<%=strPath%>/js/common/common.js"></script>
<script src="<%=strPath%>/js/jquery-1.11.1.min.js"></script>

<title>车主认证</title>

</head>

<body>
	<div data-role="page">
		<html:form method="get" action="/CarpoolUser.do">
			<div class="page1">
				<div data-role="header" data-position="fixed" data-tap-toggle="false">
					<header>
		  					<h3 style="text-align:center;color:#000; ">车主认证</h3>
						<a class="back" href="javascript:void(0);return" onclick="doButtonSubmit(document.forms[0], 'getCarpoolUserDetail', 'submitField')"></a>
					</header>
				</div>
				<div class="content1">
				    <section class="main">
					    <logic:present name="CarpoolUserForm">
						    <c:set var="id_number" value="${CarpoolUserForm.id_number}" scope = "request"/>
			      				<%
			      					String id_number = (String)request.getAttribute("id_number");
			      					String echo_id_number = "";
			      					if(id_number != null && !("".equals(id_number)) && (id_number.length() >=3)){
			      						String begin = id_number.substring(0, 3);
					      				String end = id_number.substring(id_number.length()-3,id_number.length());
					      				echo_id_number = begin + "************" + end;
			      					}
			      				%>
							<h4 style="font-size:16px"><p class="icon-right"></p><p>恭喜您车主认证成功，现在可以接单了</p></h4>
							<div class="main1">
								<p class="m-top">
									真实姓名：<input type="text" value="${CarpoolUserForm.cu_name}">
								</p>
								<p>
									身份证号：<input type="text" value="<%=echo_id_number %>">
								</p>
							</div>
							<div class="main1">
								<p class="m-top">
									车&nbsp;&nbsp;牌&nbsp;&nbsp;号：
										<!-- 
										<font style="margin-right: 5px; color: #0086ff">&or;</font>
										 -->
										<input style="width: 54%" type="text" value="${CarpoolUserForm.car_num}">
								</p>
								<p class="m-top">
									车辆类型：<font><c:out value="${CarpoolUserForm.car_type}" escapeXml="false" /></font>
								</p>
								<p class="m-top">
									车&nbsp;&nbsp;架&nbsp;&nbsp;号：<font><c:out value="${CarpoolUserForm.car_license_pic}" escapeXml="false" /></font>
								</p>
								<p>
									核载人数：<font><c:out value="${CarpoolUserForm.car_seat_num}" escapeXml="false" /></font>
								</p>
							</div>
							<!-- 
							<div style="width: 50%; float: left">
								<section class="pic1">
									<p>驾驶证照片</p>
									
									<img src="${CarpoolUserForm.driving_license_pic}">
								</section>
							</div>
							<div style="width: 50%; float: right">
								<section class="pic2">
									<p>行驶证照片</p>
									<img src="${CarpoolUserForm.car_license_pic}">
								</section>
							</div>
							<section class="pic3">
								<p>驾驶人手持行驶证照片</p>
								<img src="${CarpoolUserForm.people_license}">
							</section>
							 -->
						</logic:present>
					</section>
				</div>
	  		</div>
	  		<html:hidden property="submitField" styleId="submitField" value="" />
			<html:hidden property="cu_id" styleId="cu_id" />
			<html:hidden property="dp_flag" styleId="dp_flag" />
			<html:hidden property="requestBy" styleId="requestBy" value="h5"  />
		
  		</html:form>
	</div>
</body>
</html>
