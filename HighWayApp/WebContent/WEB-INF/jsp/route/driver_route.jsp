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
    String driverLink = (String) response.encodeURL(strPath + "/CarpoolDriver.do");
    String passengerLink = (String) response.encodeURL(strPath + "/CarpoolPassenger.do");
    String userLink = (String) response.encodeURL(strPath + "/CarpoolUser.do");
%>
<html>
<head>
<meta charset=utf-8 />
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" name="viewport" />
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/jquery-ui.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/css.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/box.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/icomoon.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/style.css"/>
<script src="<%=strPath%>/js/jquery-1.11.1.min.js"></script>
<script src="<%=strPath%>/js/index.js"></script>
<script src="<%=strPath%>/js/jquery.min.js"></script>
<script src="<%=strPath%>/js/jquery-ui.min.js"></script>
<script src="<%=strPath%>/js/modal.js"></script>
<script src="<%=strPath%>/js/common/common.js"></script>
<script>
 $(function() {
 $( "#tabs" ).tabs();
 });
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
		var  returnCode=document.getElementById("returnCode").value;
		if(returnCode=='0'){
			$("#tishi").html("抢单成功！");	
			$(".box").css({
				left:($("body").width()-$(".box").width())/2-20+"px",
				top:($(window).height()-$(".box").height())/2+$(window).scrollTop()+"px",
				display:"block"
			}).show(100).delay(1000).hide(100);
			return;
			}
		if(returnCode=='1'){
			$("#tishi").html("取消路线成功！");	
			$(".box").css({
				left:($("body").width()-$(".box").width())/2-20+"px",
				top:($(window).height()-$(".box").height())/2+$(window).scrollTop()+"px",
				display:"block"
			}).show(100).delay(1000).hide(100);
			return;
			}
});
 function back(){
  var start_city=document.getElementById("start_city").value;
  var end_city=document.getElementById("end_city").value;
  var begin_time=document.getElementById("begin_time").value;
  var pend_time=document.getElementById("pend_time").value;
  var charter_flg=document.getElementById("charter_flg").value;
  var screenId=document.getElementById("screenId").value;
  if(screenId!=''&&screenId=='validRoute'){
  saveValue('start_city',start_city,'end_city',end_city,'begin_time',begin_time,'pend_time',pend_time,'charter_flg',charter_flg);
  formSubmitByPath(document.forms[0],'<%=passengerLink %>');
  doButtonSubmit(document.forms[0], 'searchCarpoolList', 'submitField');}
  else if(screenId!=''&&screenId=='routeList'){
	  saveValue('start_city',start_city,'end_city',end_city,'begin_time',begin_time,'pend_time',pend_time,'charter_flg',charter_flg);
	  formSubmitByPath(document.forms[0],'<%=passengerLink %>');
	  doButtonSubmit(document.forms[0], 'searchCarpoolList', 'submitField');}
 else{
	 
	  formSubmitByPath(document.forms[0],'<%=userLink %>');
	  doButtonSubmit(document.forms[0], 'getCarpoolUserDetail', 'submitField');}}
</script>
<title>我的行程</title>
<style>
.btn{padding:0}
.btn-primary{color:#000;background:none;border:none;width: 100%;text-align: left;}
.btn-primary:hover{color:#000;background:none;border:none}
.btn-primary.focus, .btn-primary:focus{color:#000;background:none;border:none}
.btn-primary:active{background:none;border:none;}
.modal-dialog{width:auto;margin:150px 30px}
</style>
</head>
<%
    int rowIndex = 0;
     int outerIndex=0;
%>
<body>
<div class="page1">
  <header style="height:50px">
    <h3 style="text-align:center;color:#000; ">我的行程</h3>
    <a class="back" href="javascript:void(0);" style="right:6px" onClick="back();"></a> </header>
    <html:form action="/CarpoolDriver.do" method="post">
    
    <%
    int upIndex=0;
    int downIndex=0;
    int upOut=0;
    int downOut=0;
    %>
  <div class="content1">
    <div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
      <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" role="tablist">
        <li onClick="document.getElementById('tabs-1').disabled=false;document.getElementById('tabs-2').disabled=true;" class="ui-state-default ui-corner-top ui-tabs-active ui-state-active" role="tab" tabindex="0" aria-controls="tabs-1" aria-labelledby="ui-id-1" aria-selected="true"><a href="#tabs-1" class="ui-tabs-anchor" role="presentation" tabindex="-1" id="ui-id-1">进行中</a></li>
        <li onClick="document.getElementById('tabs-1').disabled=true;document.getElementById('tabs-2').disabled=false;" class="ui-state-default ui-corner-top" role="tab" tabindex="-1" aria-controls="tabs-2" aria-labelledby="ui-id-2" aria-selected="false"> <a id="ui-id-2" class="ui-tabs-anchor" href="#tabs-2" role="presentation" tabindex="-1">已完成</a></li>
      </ul>

      <ul id="accordion" style="width:100%">
        <li>

    <div id="tabs-1" aria-labelledby="ui-id-1" class="ui-tabs-panel ui-widget-content ui-corner-bottom" role="tabpanel" aria-expanded="true" aria-hidden="false">
      <section class="list" style="margin:0 auto;display:block;overflow:hidden">
      <logic:present name="CarpoolDriverForm" property="list">      
<ul id="accordion1" class="accordion" style="width:100%">
<c:forEach items="${CarpoolDriverForm.list}" var="driverRouteList"  varStatus="outstatus">
 <c:if test="${driverRouteList.state_flg=='0'}">
        <li id="li_id_row" style="margin:0 auto;">
          <div class="link">
            <section class="top1">           
            <font class="start">起</font>${driverRouteList.start_city}<span class="icon-to"></span><font class="end">终</font>${driverRouteList.end_city}<font style="color:#0086ff;"></font>
              <p><font style="color:#404040">出发时间：</font>${driverRouteList.driverGo_time}</p>
            </section>
            <i class="fa fa-chevron-down"></i></div>
          <ul class="submenu" >
           <c:forEach items="${driverRouteList.passengerRouteDTOs}" var="passengerDetail" varStatus="status">
            <c:if test="${passengerDetail.state=='10'|| passengerDetail.state=='11'}">
            <li style="margin:0 auto;">
              <section style="border-left:6px solid #66ca6b;display:block;background-color:#efefef;overflow:hidden;margin:0">
                <p class="qiangdan" style="width:100%;"> 
                
                <!--对话框start1-->        
 <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal<%=upOut+99 %><%=upIndex +99%>"><p class="title">
											 ${passengerDetail.carpoolUser.cu_nick}<font class="ck">乘客</font>
											</p>
            </button>   
            <div class="modal fade" id="myModal<%=upOut+99 %><%=upIndex+99 %>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"  style="color:#ff9c00">乘客</h4>
      </div>
      <div class="modal-body">
  <p style="font-size:20px" class="modal-name">${passengerDetail.carpoolUser.cu_nick}<font style="position: absolute;right:10px;font-size:14px;">成功搭车${passengerDetail.carpoolUser.p_success_count}次</font></p>
                    <span style="font-size:14px;">
                    <p>联系电话：<a href="tel:${passengerDetail.carpoolUser.cu_tel}" style="color:#0086ff;padding:0;display:in-block">${passengerDetail.carpoolUser.cu_tel}</a></p>
                    <p>注册日期：${passengerDetail.carpoolUser.create_time} </p>
                    </span> 
      </div>
                
    </div>
  </div>
</div>        
 
 <!--对话框end--> 		
					
 
                <%
																		    rowIndex++;
																		%>
                <font id="state" style="color:#0086ff;font-size:14px;line-height:38px;position:absolute;right:3%"><c:if test="${passengerDetail.state=='0'}">路线已发布</c:if><c:if test="${passengerDetail.state=='10'}">等待上车</c:if><c:if test="${passengerDetail.state=='11'}">已上车</c:if></font>
                </p>
                <p class="position" style="clear:both;width:100%"><font class="icon-qi1 qi"></font>${passengerDetail.start_area}<span class="icon-to"></span><font class="icon-qi1 zhong"></font>${passengerDetail.end_area}
                <p style="font-size:14px;font-weight:100"></p>
                </p>
                <p class="main" style="clear:both;width:100%;padding:0 0 0 12px">参考点数：${passengerDetail.price}点<br>
                  出发时间：<font style="color:#0086ff;">${passengerDetail.go_time}</font><br>
                  搭乘人数：${passengerDetail.people_count}<br>
   <c:if test="${passengerDetail.pr_memo!=''}">备注：${passengerDetail.pr_memo}</c:if></p>
   <c:if test="${passengerDetail.state=='10'}">
             <!--  <a href="#" style="font-size:14px;background-color:#8d8d8d;border-radius:5px;padding:3px 10px;color:#fff;float:left;margin:6px 0 8px 16px;"onClick="cancelRoute(${(outstatus.index)},${(status.index)},'${driverRouteList.dr_id}','${passengerDetail.pr_id}');">撤消</a> </span> -->  
             <a href="javascript:void(0);" style="font-size:14px;background-color:#8d8d8d;border-radius:5px;padding:3px 10px;color:#fff;float:left;margin:6px 0 8px 16px;" onClick="saveValue('dr_id','${driverRouteList.dr_id}');saveValue('screenId','routeList');saveValue('pr_id','${passengerDetail.pr_id}');doButtonSubmit(document.forms[0], 'cancelDriverRoute', 'submitField');">撤消</a> </c:if></span>
                </p>
              </section>
            
            </li>    <%
            
            upIndex++;%></c:if>
           
            </c:forEach>
          </ul></li>
           <%
            
            upOut++;upIndex=0; %>
          
          </c:if>   
           </c:forEach></ul></logic:present>
             <logic:notPresent  name="CarpoolDriverForm" property="list">
			<div class="none" style="vertical-align:middle;line-height:30px;text-align:center;font-size:16px;color:#666;margin-top:150px"><img src="<%=strPath%>/images/search.png" width="32" height="32" style="margin:0 auto;display:block;margin-bottom:10px">您还没有行程</div>
				</logic:notPresent></section></div>
    
    
<div id="tabs-2" aria-labelledby="ui-id-2" class="ui-tabs-panel ui-widget-content ui-corner-bottom" role="tabpanel" aria-expanded="true" aria-hidden="false">
<section class="list" style="margin:0 auto;display:block;overflow:hidden">
  <logic:present name="CarpoolDriverForm" property="list">   
<ul id="accordion2" class="accordion" style="width:100%">
<c:forEach items="${CarpoolDriverForm.list}" var="driverRouteList" >
  <c:if test="${driverRouteList.state_flg!='0'}">		
		 <li style="margin:0 auto;">
          <div class="link">
            <section class="top1">           
            <font class="start">起</font>${driverRouteList.start_city}<span class="icon-to"></span><font class="end">终</font>${driverRouteList.end_city}<font style="color:#b7b7b7;"><c:if test="${driverRouteList.state_flg=='1'}">（已完成）</c:if><c:if test="${driverRouteList.state_flg=='2'}">（已取消）</c:if><c:if test="${driverRouteList.state_flg=='3'}">（已过期）</c:if></font>
              <p><font style="color:#404040">出发时间：</font>${driverRouteList.driverGo_time}</p>
            </section>
            <i class="fa fa-chevron-down"></i></div>
          <ul class="submenu" >
           <c:forEach items="${driverRouteList.passengerRouteDTOs}" var="passengerDetail" varStatus="status">
            <li style="margin:0 auto;">
             <section style="border-left:6px solid #66ca6b;display:block;background-color:#efefef;overflow:hidden;margin:0">
                <p class="qiangdan" style="width:100%;"> 
                    <!--对话框start1-->        
 <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal<%=downOut %><%=downIndex %>"><p class="title">
											 ${passengerDetail.carpoolUser.cu_nick}<font class="ck">乘客</font>
											</p>
            </button>   
            <div class="modal fade" id="myModal<%=downOut %><%=downIndex %>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"  style="color:#ff9c00">乘客</h4>
      </div>
      <div class="modal-body">
  <p style="font-size:20px" class="modal-name">${passengerDetail.carpoolUser.cu_nick}<font style="position: absolute;right:10px;font-size:14px;">成功搭车${passengerDetail.carpoolUser.p_success_count}次</font></p>
                    <span style="font-size:14px;">
                    <p>联系电话：<a href="tel:${passengerDetail.carpoolUser.cu_tel}" style="color:#0086ff;padding:0;display:in-block">${passengerDetail.carpoolUser.cu_tel}</a></p>
                    <p>注册日期：${passengerDetail.carpoolUser.create_time} </p>
                    </span> 
      </div>
                
    </div>
  </div>
</div>        
 
 <!--对话框end--> 	
                
                
                
                
                
                
                
                
                
                
                
                
                  <!--对话框--> 
               <!--    <a data-toggle="modal" href="#myModal<%=downOut %><%=downIndex %>" class="btn btn-primary size-L" style="padding:10px 10px 0 15px;line-height:24px;font-size:20px;color:#000;float:left;background-color:#efefef">${passengerDetail.carpoolUser.cu_nick}<font class="ck">乘客</font></a>
                <div id="myModal<%=downOut %><%=downIndex %>" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                  <div class="modal-header">
                    <h3 id="myModalLabel" style="color:#ff9c00">乘客</h3>
                    <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a> </div>
                  <div class="modal-body">
                    <p style="font-size:20px" class="modal-name">${passengerDetail.carpoolUser.cu_nick}<font style="position: absolute;right:10px;font-size:14px;">成功搭车${passengerDetail.carpoolUser.p_success_count}次</font></p>
                    <span style="font-size:14px;">
                    <p>联系电话：<a href="#" style="color:#0086ff">${passengerDetail.carpoolUser.cu_tel}</a></p>
                    <p>注册日期：${passengerDetail.carpoolUser.create_time} </p>
                    </span> </div>
                  <div class="modal-footer">
					 <button class="btn btn-primary">确定</button> <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
					 </div>
                </div>
                <div id="ajaxModal" class="modal hide fade" tabindex="-1" data-width="700"></div>--> 
                <!--对话框--> 
                
               <p class="position" style="clear:both;width:100%"><font class="icon-qi1 qi"></font>${passengerDetail.start_area}<span class="icon-to"></span><font class="icon-qi1 zhong"></font>${passengerDetail.end_area}
                <p style="font-size:14px;font-weight:100"></p>
                </p>
                <p class="main" style="clear:both;width:100%;padding:0 0 0 12px">参考点数：${passengerDetail.price}点<br>
                  出发时间：<font style="color:#0086ff;">${passengerDetail.go_time}</font><br>
                  搭乘人数：${passengerDetail.people_count}<br>
        <c:if test="${passengerDetail.pr_memo!=''}">备注：${passengerDetail.pr_memo}</c:if></p>
                 </span>
                </p>
              </section>
            </li>
         <%downIndex++; %>  
       </c:forEach></ul></li></c:if>
         <%downOut++;downIndex=0; %>
       </c:forEach></ul></logic:present>
       <logic:notPresent  name="CarpoolDriverForm" property="list">
			<div class="none" style="vertical-align:middle;line-height:30px;text-align:center;font-size:16px;color:#666;margin-top:150px"><img src="<%=strPath%>/images/search.png" width="32" height="32" style="margin:0 auto;display:block;margin-bottom:10px">您还没有行程</div>
	   </logic:notPresent>
       </section></div>
       </li></ul>  
  </div>
</div>
	
     <div class="box" style="display:none;">
	<img src="<%=strPath%>/images/notification.png"  width="20" height="20" style="position:absolute;left:10px;top:13px">
	<div class="txt" style="text-align:center; font-size:14px" id="tishi"></div>
	</div>
    <div class="box1" style="display:none;" id="htmlerror">
	
	<img src="<%=strPath%>/images/notification.png"  width="20" height="20" style="position:absolute;left:10px;top:13px">
	 <div class="txt" id="error" font-size:14px" style="text-align:center"><html:errors/></div>	
</div>
<html:hidden property="submitField" styleId="submitField" value="" />
  <html:hidden property="requestBy" styleId="requestBy" value="h5" />
   <html:hidden property="cu_id" styleId="cu_id" />
   <html:hidden property="pr_id" styleId="pr_id" />
   <html:hidden property="dr_id" styleId="dr_id" />
   <html:hidden property="start_city" styleId="start_city" />
   <html:hidden property="end_city" styleId="end_city" />
   <html:hidden property="begin_time" styleId="begin_time" />
   <html:hidden property="pend_time" styleId="pend_time" />
   <html:hidden property="charter_flg" styleId="charter_flg" />
   <html:hidden property="screenId" styleId="screenId" />
   <html:hidden property="dp_flag" styleId="dp_flag" value="0" />
   <html:hidden property="returnCode" styleId="returnCode"  />
</html:form>
</div>
</body>
</html>
