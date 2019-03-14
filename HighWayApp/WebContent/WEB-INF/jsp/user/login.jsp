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
    String userLink = (String) response.encodeURL(strPath + "/CarpoolUser.do");

%>
<html>
<head>
<meta charset=utf-8 />
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" name="viewport" />
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/css.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/box.css"/>
<script src="<%=strPath%>/js/jquery-1.11.1.min.js"></script>
<script src="<%=strPath%>/js/common/common.js"></script>
<title>登录</title>
 
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

function loginCheck(){
    var cu_password =document.getElementById("cu_password").value;
    var cu_code=document.getElementById("cu_code").value;
    if(cu_code==''||cu_code==null){

  	  $("#tishi").html("请输入账号！");
  		
  		$(".box").css({
  			left:($("body").width()-$(".box").width())/2-20+"px",
  			top:($(window).height()-$(".box").height())/2+$(window).scrollTop()+"px",
  			display:"block"
  		}).show(100).delay(1000).hide(100);
  		return;}else if(cu_password==''||cu_password==null){
  			 $("#tishi").html("请输入密码！");	    		
  	    		$(".box").css({
  	    			left:($("body").width()-$(".box").width())/2-20+"px",
  	    			top:($(window).height()-$(".box").height())/2+$(window).scrollTop()+"px",
  	    			display:"block"
  	    		}).show(100).delay(1000).hide(100);
  	    		return;	}else{
  	    			 doButtonSubmit(document.forms[0], 'doLogin', 'submitField');
      	    		}
		}
</script>
</head>

<body>
<div class="page1">
  <header>
   <h3 style="text-align:center;color:#000; ">登录</h3><!-- <a class="back" href="#"></a>   -->  
  </header>
  <div class="content1">
   <html:form action="/CarpoolUser.do" method="POST" > 
    <section class="main">
<div class="logo" style="margin:20px 0">
   
  <i></i>
    
 </div>
      <div class="main1">
     
        <p class="m-top">账号：
        <input type="text" placeholder="请输入您的账号" id="cu_code" name="cu_code" style="width:70%" value="${CarpoolUserForm.cu_code}"></p><p>密码：<input type="password" id="cu_password"  name="cu_password"  style="width:70%" value="${CarpoolUserForm.cu_password}" placeholder="请输入密码"></p>
      </div>
      
      <!--   <a href="#" class="button" onClick='$("#login-form").submit()'>用户登录</a>-->
         <a href="#" class="button" onClick="loginCheck();">用户登录</a>
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
  <div class="footer"><a href="#" onClick="doButtonSubmit(document.forms[0], 'doCreateVerificationCode', 'submitField');">注册账号</a>|<a href="#" onClick="doButtonSubmit(document.forms[0], 'doVerifGenerate', 'submitField');">忘记密码</a></div>
  
  </div>


  
</body>
</html>
