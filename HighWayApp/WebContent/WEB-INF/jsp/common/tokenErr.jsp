<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%
	String strPath = (String) request.getContextPath();
%>
<%
	String backLink = (String) response.encodeURL(strPath + "/CarpoolUser.do");
	String carpoolPassengerPath = (String) response.encodeURL(strPath + "/CarpoolPassenger.do");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" />
<link rel="stylesheet" href="<%=strPath%>/css/css.css">
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/jquery-ui.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/bootstrap.min.css"/>

<script src="<%=strPath%>/js/common/common.js"></script>
<script src="<%=strPath%>/js/jquery-1.11.1.min.js"></script>
<script src="<%=strPath%>/js/jquery.validate.js"></script>

<title>高速顺风车</title>

</head>

<body>
	<div data-role="page">
		<html:form method="post" action="/CarpoolUser.do">
			<div class="page1">
				<div data-role="header" data-position="fixed" data-tap-toggle="false">
					<header>
						<h3 style="text-align:center;color:#000; ">高速搭车</h3>
					</header>
				</div>
				<div data-role="content">
					<div class="index"  style="margin-top:120px">
						<!-- 
						<div class="logo">
							<i></i>
						</div>
						 -->
						<h2 align="center">您的路线已创建成功！</h2>
						<div class="button" style="margin-top:50px">
							<a href="javascript:void(0);return;" class="in1" onclick="saveValue('dp_flag','0');doButtonSubmit(document.forms[0], 'doChangeRole', 'submitField')"><p>继续创建路线</p></a>
						</div>
					</div>
				</div>
			</div>
			<div class="box1" style="display:none;" id="htmlerror">
				<img src="<%=strPath%>/images/notification.png"  width="20" height="20" style="position:absolute;left:10px;top:13px">
				<div class="txt" id="error" style="text-align:center"><html:errors/></div>	
			</div>
			<html:hidden property="submitField" styleId="submitField" value="" />
			<html:hidden property="cu_id" styleId="cu_id" />
			<html:hidden property="dp_flag" styleId="dp_flag" />
			<html:hidden property="requestBy" styleId="requestBy" value="h5"  />
		</html:form>
	</div>
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
</body>
</html>

