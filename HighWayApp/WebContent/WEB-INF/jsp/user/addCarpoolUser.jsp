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
	String backLink = (String) response.encodeURL(strPath
		+ "/CarpoolUser.do");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" />
<link rel="stylesheet" href="<%=strPath%>/css/css.css">
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/box.css"/>


<script src="<%=strPath%>/js/jquery-1.11.1.min.js"></script>
<script src="<%=strPath%>/js/jquery.validate.js"></script>
<script src="<%=strPath%>/js/common/common.js"></script>

<title>添加手机号</title>
</head>
<body>
	<div data-role="page">
		<html:form method="post" action="/CarpoolUser.do" styleId="addCarpoolUserForm">
			<div class="page1">
				<div data-role="header" data-position="fixed" id="update_p_user"
					data-tap-toggle="false">
					<header>
						<h3 style="text-align:center;color:#000; ">添加手机号</h3>
						<a class="back" href="#" onclick="doButtonSubmit(document.forms[0], 'init', 'submitField');"></a>
					</header>
				</div>
				<div data-role="content">
					<div class="content1">
						    <section class="main">
						      <div class="main1">
						      <p>手机号：<input type="tel" id="cu_tel" name="cu_tel" placeholder="11位手机号"></p>
						      </div>
						      <a href="#"  class="button" onclick="submitForm()">完成</a>
						    </section>
					</div>
				</div>
			</div>
			<div class="box" style="display:none;">
				<img src="<%=strPath%>/images/notification.png"  width="20" height="20" style="position:absolute;left:10px;top:13px">
				<div class="txt" style="text-align:center" id="tishi"></div>
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
function modifyCss(){
	$(".box").css({
			left:($("body").width()-$(".box").width())/2-20+"px",
			top:($(window).height()-$(".box").height())/2+$(window).scrollTop()+"px",
			display:"block"
		}).show(100).delay(1000).hide(100);
}
</script>	
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
<script type="text/javascript">
function submitForm(){
    var cu_tel =document.getElementById("cu_tel").value;
    if(cu_tel==''||cu_tel==null){

		$("#tishi").html("请输入手机号！");
		//$("#TB_overlayBG").css({display:"block",height:$(document).height()});
		$(".box").css({
			left:($("body").width()-$(".box").width())/2-20+"px",
			top:($(window).height()-$(".box").height())/2+$(window).scrollTop()+"px",
			display:"block"
		}).show(100).delay(1000).hide(100);
		return;
  	} else if(cu_tel.length<8 || cu_tel.length>12){
		alert(cu_tel.length);
		$("#tishi").html("请输入8-12位电话号"); 		
		modifyCss();
		return;
 	} else {
		doButtonSubmit(document.forms[0], 'addCarpoolUser', 'submitField');
	}
}
</script>	
</body>
</html>

