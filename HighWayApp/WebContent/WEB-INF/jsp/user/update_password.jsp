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
<script src="<%=strPath%>/js/jquery-1.11.1.min.js"></script>
<script src="<%=strPath%>/js/common/common.js"></script>
<title>设置新密码</title>
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

function check(){
    var cu_password =document.getElementById("cu_password").value;
   
    var confirm_password=document.getElementById("confirm_password").value;
    if(cu_password==''||cu_password==null){
  			 $("#tishi").html("请输入密码！");
  			modifyCss();}else if(cu_password.length<6){
  	  			alert(cu_password.length);
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
  	  		else{
  	    			 doButtonSubmit(document.forms[0], 'doUpdateUserPassword', 'submitField');
      	    		}
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
 <html:form action="/CarpoolUser.do" method="get" styleId="register"> 
  <header>
   <h3 style="text-align:center;color:#000; ">设置新密码</h3><a class="back" href="javascript:void(0);"  onClick="doButtonSubmit(document.forms[0], 'backToRetrivePassword', 'submitField');"></a>    
  </header>
  <div class="content1">
    
    <section class="main">

      <div class="main1" style="margin:4px 0 20px 0">
        <p  class="m-top1">新&nbsp;&nbsp;密&nbsp;&nbsp;码：<input type="password"  name="cu_password" id="cu_password" placeholder="6位以上字母/数字" style="width:65%"></p><p>确认密码：<input type="password"  id="confirm_password" name="confirm_password" placeholder="请再次输入密码" style="width:65%"></p>
      </div>
      
      
     
       <a href="javascript:void(0);" class="button" style="clear:both"onClick="check();">完成</a>

     
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
     <html:hidden property="cu_id" styleId="cu_id" />
      <html:hidden property="cu_code" styleId="cu_code" />
 
  </div>
 
   </html:form>
  </div>
  

  
</body>
</html>
