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

<title>实名认证</title>
</head>
<body>
	<div data-role="page">
		<html:form method="get" action="/CarpoolUser.do" styleId="verifyForm">
			<div class="page1">
				<div data-role="header" data-position="fixed" data-tap-toggle="false">
					<header>
	   					<h3 style="text-align:center;color:#000; ">实名认证</h3>
						<a class="back" href="javascript:void(0);return;" onclick="doButtonSubmit(document.forms[0], 'getCarpoolUserDetail', 'submitField')"></a>
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
						    <h4 style="font-size:16px"><p class="icon-right"></p><p>恭喜您实名认证成功</p></h4>
		      				<h3 style="font-size1:24px;color:#363636">${CarpoolUserForm.cu_name}&nbsp;&nbsp;&nbsp;<%=echo_id_number %></h3>
		      				
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

