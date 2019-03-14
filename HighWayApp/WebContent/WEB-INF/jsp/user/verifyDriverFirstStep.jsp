<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<jsp:useBean id="CarpoolUserForm" class="com.djb.highway.carpool.dto.CarpoolUserDTO"
	scope="request" />
<%
	String strPath = (String) request.getContextPath();
	String backLink = (String) response.encodeURL(strPath + "/CarpoolUser.do");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" />
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/box.css"/>
<link rel="stylesheet" href="<%=strPath%>/css/css.css">

<script src="<%=strPath%>/js/common/common.js"></script>
<script src="<%=strPath%>/js/jquery-1.11.1.min.js"></script>

<title>车主认证</title>
</head>
<body>
	<div data-role="page">
		<html:form method="get" action="/CarpoolUser.do" styleId="verifyFirstForm">
			<div class="page1">
				<div data-role="header" data-position="fixed" data-tap-toggle="false">
					<header>
						<h3 style="text-align:center;color:#000; ">车主认证</h3>
						<a class="back" href="javascript:void(0);return;" onclick="doButtonSubmit(document.forms[0], 'getCarpoolUserDetail', 'submitField')"></a>
					</header>
				</div>
				<div data-role="content">
					<div class="content1">
					<!-- 
					    <section class="title"><span class="one">填写车主资料<font class="t-one">1</font></span><span class="two">上传证件照片<font class="t-two">2</font></span></section>
					-->
					    <section class="main">
					      <h4>请提交真实的身份信息</h4>
					      <logic:present name="CarpoolUserForm" >
						      <div class="main1">
						       <p class="m-top">真实姓名：
						       
						       <input type="text"  name="cu_name" id="cu_name" value="${CarpoolUserForm.cu_name }" placeholder="你的身份证姓名" style="width:65%">
						      <!--   
						      <html:text property="cu_name"></html:text>
						       -->
						       </p>
						        <p>身份证号：<input type="text" name="id_number" id="id_number" value="${CarpoolUserForm.id_number }" placeholder="18位身份证号" style="width:65%"></p>
						      </div>
					      </logic:present>
					      <div class="main1">
					        <p class="m-top">车&nbsp;&nbsp;牌&nbsp;&nbsp;号：
					        	<font style="margin-right:5px;color:#0086ff">
							        <select name="province" id="province" style="color:#0086ff;font-size:16px;width:50px">
								        <option>辽</option>
								        <option>吉</option>
								        <option>黑</option>
								        <option>京</option>
								        <option>津</option>
								        <option>冀</option>
								        <option>晋</option>
								        <option>蒙</option>
								        <option>沪</option>
								        <option>苏</option>
								        <option>浙</option>
								        <option>皖</option>
								        <option>闽</option>
								        <option>赣</option>
								        <option>鲁</option>
								        <option>豫</option>
								        <option>鄂</option>
								        <option>湘</option>
								        <option>粤</option>
								        <option>桂</option>
								        <option>琼</option>
								        <option>渝</option>
								        <option>川</option>
								        <option>贵</option>
								        <option>云</option>
								        <option>藏</option>
								        <option>陕</option>
								        <option>甘</option>
								        <option>青</option>
								        <option>宁</option>
								        <option>新</option>
							        </select>
					        	</font><input name="carNumber" id="carNumber" type="text" style="width:37%" placeholder="6位车牌号">
					        </p>
					        <!-- 
					        <a href="#">
					        	<p>车辆类型：<font>请选择车型</font><i></i></p>
					        </a>
					         
					         <p>车辆类型：<input type="text" name="car_type" id="car_type" placeholder="奥迪 A4 白色"></p>
					         -->
					         <p class="m-top">车辆类型：<input type="text"  name="car_type" id="car_type" style="width:68%" placeholder="奥迪 A4 白色"><!--<font>请选择车型</font><i></i>--></p>
					         <p class="m-top">车&nbsp;&nbsp;架&nbsp;&nbsp;号：<input type="text" style="width:68%" name="car_license_pic" id="car_license_pic" placeholder="车辆识别代码">
					         <p>核载人数：<input type="tel" style="width:68%" name="car_seat_num" id="car_seat_num" placeholder="请输入人数（包括司机）"></p> 
					      </div>
					      <a href="javascript:void(0);return;"  class="button" onclick="checkChange(); submitForm()">完成</a>
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
			<html:hidden property="cu_nick" styleId="cu_nick" />
			<html:hidden property="cu_tel" styleId="cu_tel" />
			<html:hidden property="cu_name" styleId="cu_name" />
			<html:hidden property="id_number" styleId="id_number" />
			<html:hidden property="car_num" styleId="car_num" />
			<html:hidden property="car_type" styleId="car_type" />
			<html:hidden property="car_license_pic" styleId="car_license_pic" />
			
			<html:hidden property="dp_flag" styleId="dp_flag" />
			<html:hidden property="requestBy" styleId="requestBy" value="h5"  />
		</html:form>
	</div>
<script type="text/javascript">
	function checkChange() {
		//拼接车牌号
		var obj = document.getElementById("province");
		var province = obj.options[obj.selectedIndex].text;
		var carNumber = document.getElementById("carNumber").value;
		var text = province + carNumber;
		var car_num = document.getElementById("car_num");
		car_num.value = text;
	}
</script>
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
    var cu_name =document.getElementById("cu_name").value;
    var id_number =document.getElementById("id_number").value;
    var carNumber =document.getElementById("carNumber").value;
    var car_type =document.getElementById("car_type").value;
    var car_license_pic =document.getElementById("car_license_pic").value;
    var car_seat_num =document.getElementById("car_seat_num").value;
    if(cu_name==''||cu_name==null){

		$("#tishi").html("请输入姓名！");
		modifyCss();
		return;
  	}else if(id_number==''||id_number==null){
		$("#tishi").html("请输入身份证号！");
    	modifyCss();
   		return;	
   	}else if(id_number.length != 18){
		$("#tishi").html("请输入18位身份证号！");
		modifyCss();
   		return;	
   	}else if(carNumber==''||carNumber==null){
		$("#tishi").html("请输入车牌号！");
		modifyCss();
   		return;	
   	}else if(carNumber.length != 6 ){
		$("#tishi").html("请输入6位车牌号！");
		modifyCss();
   		return;	
   	}else if(car_type==''||car_type==null){
		$("#tishi").html("请输入车辆类型！");
		modifyCss();
   		return;	
   	}else if(car_license_pic==''||car_license_pic==null){
		$("#tishi").html("请输入车架号！");
		modifyCss();
   		return;	
   	}else if(car_seat_num==''|| car_seat_num==null){
		$("#tishi").html("请输入核载人数！");
		modifyCss();
   		return;	
   	}else if(isNaN(car_seat_num)){
		$("#tishi").html("请输入数字！");
		modifyCss();
   		return;	
   	} else{
   	   	//司机认证的照片认证
 		//doButtonSubmit(document.forms[0], 'verifyDriverSecondStep', 'submitField');
 		doButtonSubmit(document.forms[0], 'verifyDriver', 'submitField');
	}
}
</script>	
</body>
</html>

