<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%
    String strPath = (String) request.getContextPath();
    String passengerLink = (String) response.encodeURL(strPath + "/CarpoolPassenger.do");
    String driverLink = (String) response.encodeURL(strPath + "/CarpoolDriver.do");
    String userLink = (String) response.encodeURL(strPath + "/CarpoolUser.do");
%>
<html>
<head>
<meta charset=utf-8 />
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" name="viewport" />
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/css.css"/>
  <link rel="stylesheet" type="text/css" href="<%=strPath%>/css/box.css"/>
<script src="<%=strPath%>/js/jquery.min.js"></script>
<script src="<%=strPath%>/js/bootstrap-modal.js"></script>
<script src="<%=strPath%>/js/bootstrap-modalmanager.js"></script>
<script src="<%=strPath%>/js/common/common.js"></script>
<title>用户注册</title>
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

function registerCheck(){
    var cu_password =document.getElementById("cu_password").value;
    var cu_code=document.getElementById("cu_code").value;
    var confirm_password=document.getElementById("confirm_password").value;
    var cu_question=document.getElementById("cu_question").value;
    var cu_answer=document.getElementById("cu_answer").value;
    var verificationCode=document.getElementById("verificationCode").value;
    var cu_tel=document.getElementById("cu_tel").value;
    var agree=document.getElementById("agree").checked;
	 var vercode=$("#vercode").html();

    if(cu_code==''||cu_code==null){
  	  $("#tishi").html("请输入账号！"); 		
  	modifyCss();}else if(cu_password==''||cu_password==null){
  			 $("#tishi").html("请输入密码！");
  			modifyCss();}else if(cu_password.length<6){
  	  			//alert(cu_password.length);
  				 $("#tishi").html("密码长度不能少于6位！"); 		
   				modifyCss();
   				return;
  	  			}else if(confirm_password==''||confirm_password==null){
  				 $("#tishi").html("请输入确认密码！"); 		
  				modifyCss();
  				return;
  	  			}else if(confirm_password!=cu_password){
  	  	  		 $("#tishi").html("两次输入不一致！"); 		
  				modifyCss();	
  				return;}
  	  		else if(cu_tel==''||cu_tel==null){
 	  	  		 $("#tishi").html("请输入电话号码！"); 		
 				modifyCss();	
 				return;}
  	  	   else if(isNaN(cu_tel)){
  	  		   $("#tishi").html("请输入电话号码！"); 		
			  modifyCss();	
			return;} else if(cu_tel.length<11){
	  	  		   $("#tishi").html("电话号码不能少于11位！"); 		
	 			  modifyCss();	
	 			return;}
  	  		else if(cu_question==''||cu_question==null){
					 $("#tishi").html("请选择问题！"); 		
	  				modifyCss();
	  				}else if(cu_answer==''||cu_answer==null){
	  				$("#tishi").html("请输入答案！"); 		
	  				modifyCss();
	  				return;	}
  				else if(verificationCode==''||verificationCode==null){
  					 $("#tishi").html("请输入验证码！"); 		
  	  				modifyCss();
  	  			    return;
  	  				}else if(verificationCode!=vercode){
  	  				$("#tishi").html("验证码输入错误！"); 		
  	  				modifyCss();
  	  			     return;	}
  	  			else if(agree==false){
  	  				$("#tishi").html("请同意协议！"); 		
  	  				modifyCss();
  	  			     return;	}else{
  	    			 doButtonSubmit(document.forms[0], 'doCarpoolUserRegister', 'submitField');
      	    		}
		}
</script>
<script type="text/javascript">

function generateCode(){
	
	$.ajax({  
		type:"get", // 请求方式
		url:"<%=userLink%>?generateUserCode=",
       
		async: false, // 是否异步
		dataType:"text", // 设置返回数据的格式
		// ajax请求成功后的回调函数 data为json格式
		success:function(data){
			var usercode=$(data).find("usercode").text();
			if (usercode != null && usercode != undefined) {
			
			//$("#cu_code").val=usercode;
			document.getElementById("cu_code").value=usercode;
			}else{
				$("#tishi").html("生成账号失败！"); 		
	  				modifyCss();
	  			     return;	
				}

		},
		// ajax请求失败后的处理  
		error:function(){
			
		}
	 });
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

</head>

<body>
<div class="page1">
  <header>
   <h3 style="text-align:center;color:#000; ">用户注册</h3><a class="back" href="#" onClick="doButtonSubmit(document.forms[0], 'doBackToLogin', 'submitField');"></a>    
  </header>
  <div class="content1">
     <html:form action="/CarpoolUser.do" method="POST" styleId="register"> 
    <section class="main">

      <div class="main1" style="margin:4px 0 0 0">
        <p class="m-top">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：<input type="text"  id="cu_code" name="cu_code" value="${CarpoolUserForm.cu_code}" placeholder="6-16位字母/数字"  class="first"><a href="#" onClick="generateCode()">自动生成</a></p><p  class="m-top1">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password"  name="cu_password"  value="${CarpoolUserForm.cu_password}" id="cu_password" placeholder="6位以上字母/数字" style="width:65%"></p><p>确认密码：<input type="password"  id="confirm_password"  value="${CarpoolUserForm.confirm_password}" name="confirm_password" placeholder="请再次输入密码" style="width:65%"></p>
      </div>
      <div class="main1" style="margin:10px 0 0 0">
        <p>手&nbsp;&nbsp;机&nbsp;&nbsp;号：<input type="tel" name="cu_tel" id="cu_tel"  value="${CarpoolUserForm.cu_tel}"placeholder="请输入手机号" style="width:65%"></p>
        </div>
       <p style="font-size:16px;color:#999;line-height:36px;text-align:center">此问题用于找回密码，请牢记答案</p>
       
    <div class="main1">
        <p class="m-top">问&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：
        <html:select property="cu_question" styleId="cu_question">
        <html:option value=""> 请选择一个问题</html:option>
        <html:option value="1"> 我的生日？</html:option>
        <html:option value="2"> 我的家乡？</html:option>
        <html:option value="3"> 我的母校的名字？</html:option>
        <html:option value="4"> 我的爱好 ？</html:option>
        <html:option value="5"> 我的名字 ？</html:option>
        </html:select>
      <!--   <select >
        <option>请选择一个问题</option>
        <option>我的生日？</option>
        </select>--> 
        </p>
        <p>答&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案：<input type="text" value="${CarpoolUserForm.cu_answer}" id="cu_answer" name="cu_answer" placeholder="请输入您的答案"  required="required"></p>
      </div>
      <div class="main1" style="width:62%;float:left">
        <p ><input type="text" name="verificationCode" id="verificationCode" placeholder="请输入验证码" style="width:90%" required="required"></p>
      </div>
      <span  id="vercode" style="float:right;text-align:center;background:#aed3f4;width:30%;padding:5px 8px;line-height:40px">${CarpoolUserForm.verificationCode}</span>
      <p style="clear:both;margin-bottom:20px"><html:checkbox property="agree" styleId="agree" value="1"></html:checkbox>
      <!--  <input type="checkbox" value="1" name="agree" id="agree" >-->我同意<a data-toggle="modal" href="#myModal1"  class="btn btn-primary size-L" style="color:#0086ff" onClick=" saveValue('verificationCode','${CarpoolUserForm.verificationCode}');doButtonSubmit(document.forms[0], 'doChangeToAgreement', 'submitField');">相关协议</a> </p>
       <a href="#" class="button" onClick="registerCheck();">完成</a>
     
  
 
     
    </section>
    
 <div class="box" style="display:none;">
	<img src="<%=strPath%>/images/notification.png"  width="20" height="20" style="position:absolute;left:10px;top:13px">
	<div class="txt" style="text-align:center" id="tishi"></div>
	</div>
   
    <div class="box1" style="display:none;" id="htmlerror">
	
	<img src="<%=strPath%>/images/notification.png"  width="20" height="20" style="position:absolute;left:10px;top:13px">
	 <div class="txt" id="error" style="text-align:center"><html:errors/></div>	
</div>
    <html:hidden property="submitField" styleId="submitField" value="" />
    <html:hidden property="requestBy" styleId="requestBy" value="h5" /> 
    
  </html:form>
  </div>
 
  
  </div>
  

</body>
  
</html>
