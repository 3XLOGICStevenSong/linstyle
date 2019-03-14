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

<title>找回密码</title>
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

function nextCheck(){
    
    var cu_code=document.getElementById("cu_code").value;
   
    var cu_question=document.getElementById("cu_question").value;
    var cu_answer=document.getElementById("cu_answer").value;
    var verificationCode=document.getElementById("verificationCode").value;
	 var vercode=$("#vercode").html();

    if(cu_code==''||cu_code==null){
  	  $("#tishi").html("请输入账号！"); 		
  	modifyCss();}	  		
  				else if(verificationCode==''||verificationCode==null){
  					 $("#tishi").html("请输入验证码！"); 		
  	  				modifyCss();
  	  			    return;
  	  				}else if(verificationCode!=vercode){
  	  				$("#tishi").html("验证码输入错误！"); 		
  	  				modifyCss();
  	  			     return;	}else if(cu_question==''||cu_question==null){
  						 $("#tishi").html("请选择问题！"); 		
  		  				modifyCss();
  		  				}else if(cu_answer==''||cu_answer==null){
  		  				$("#tishi").html("请输入答案！"); 		
  		  				modifyCss();
  		  				return;	}
  	  			else{
  	    			 doButtonSubmit(document.forms[0], 'findCarpoolPassword', 'submitField');
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
   <html:form action="/CarpoolUser.do" method="get" >   
  <header>
   <h3 style="text-align:center;color:#000; ">找回密码</h3><a class="back" href="javascript:void(0);" onClick="doButtonSubmit(document.forms[0], 'doBackToLogin', 'submitField');"></a>    
  </header>
  <div class="content1">
 
    <section class="main">

      <div class="main1" style="margin:4px 0 20px 0">
    <p>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：<input type="text"  id="cu_code" name="cu_code" placeholder="请输入您的账号"   class="first"></p>
      </div>
      
       
       <div class="main1" style="width:62%;float:left">
              <p ><input type="text" name="verificationCode" id="verificationCode" placeholder="请输入验证码" style="width:90%" required="required" ></p>
      </div>
      <span  id="vercode" style="float:right;text-align:center;background:#aed3f4;width:30%;padding:5px 8px;line-height:40px">${CarpoolUserForm.verificationCode}</span>
    <div class="main1" style="clear:both">
       <p class="m-top">问&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：
        <html:select property="cu_question" styleId="cu_question">
        <html:option value=""> 请选择一个问题</html:option>
        <html:option value="1"> 我的生日？</html:option>
        <html:option value="2"> 我的家乡？</html:option>
        <html:option value="3"> 我的母校的名字？</html:option>
        <html:option value="4"> 我的爱好 ？</html:option>
        <html:option value="5"> 我的名字 ？</html:option>
        </html:select></p>
        <p>答&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案：<input type="text"  id="cu_answer" name="cu_answer" placeholder="请输入您的答案" required="required"></p>
       
      </div>
      
     
       <a href="javascript:void(0);" class="button" style="clear:both" onClick="nextCheck();">下一步</a>

     
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

  </div>
 
    </html:form>
  </div>
  

  
</body>
</html>
