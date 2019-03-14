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

%>
<html>
<head>
<meta charset=utf-8 />
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" name="viewport" />
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/css.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/box.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/icomoon.css"/>
<script src="<%=strPath%>/js/jquery.min.js"></script>
<script src="<%=strPath%>/js/common/common.js"></script>
<script type="text/javascript">
$(function(){
	$(".box1").click(function(){
		$("#TB_overlayBG").css("display","none");
		$(".box1").css("display","none");
	});
	$("#TB_overlayBG").click(function(){
		$("#TB_overlayBG").css("display","none");
		$(".box").css("display","none");
		$(".box1").css("display","none");
	});
	
});

</script>
<script>

$(document).ready(function(){
	 var errorValue=$("#error").html();
	 if(errorValue!=''&& errorValue!=null&& errorValue!=undefined){
			$("#TB_overlayBG").css({display:"block",height:$(document).height()});
			$(".box1").css({
				left:($("body").width()-$(".box1").width())/2-20+"px",
				top:($(window).height()-$(".box1").height())/2+$(window).scrollTop()+"px",
				display:"block"
			}).show(100).delay(1000).hide(100);
			return;
			}
});
	function joinRoute(driver_id,sup_num) {
		var pr_id = document.getElementById("pr_id").value;	
		var dr_id = driver_id;
		//乘客路线人数
		var pass_num = document.getElementById("pass_num").value;
		//剩余人数
		//var num = document.getElementById("num").innerHTML;
		var num = sup_num;
    
		
		if (pass_num <= num) {
			saveValue('dr_id', dr_id, 'pr_id', pr_id);
			doButtonSubmit(document.forms[0], 'joinDriverRoute', 'submitField');
		} else {
			$("#tishi").html("搭乘人数超过该路线剩余！");
			$(".box").css({
				left:($("body").width()-$(".box").width())/2-20+"px",
				top:($(window).height()-$(".box").height())/2+$(window).scrollTop()+"px",
				display:"block"
			}).show(100).delay(1000).hide(100);
			return;
		
		}
	}

	function createRoute(driver_id,seat_num) {
		var pr_id = document.getElementById("pr_id").value;	
		var dr_id = driver_id;
		//乘客路线人数
		var pass_num = document.getElementById("pass_num").value;
		//剩余人数
		//var num = document.getElementById("num").innerHTML;
		var num = seat_num;
    
		
		if (pass_num <= num) {
			saveValue('dr_id', dr_id, 'pr_id', pr_id);
			doButtonSubmit(document.forms[0], 'createDriverRoute', 'submitField');
		} else {
			$("#tishi").html("搭乘人数超过核载人数！");
			$(".box").css({
				left:($("body").width()-$(".box").width())/2-20+"px",
				top:($(window).height()-$(".box").height())/2+$(window).scrollTop()+"px",
				display:"block"
			}).show(100).delay(1000).hide(100);
			return;
		
		}
	}
</script>

  <script type="text/javascript">
 function back(){
  var start_city=document.getElementById("start_city").value;
  var end_city=document.getElementById("end_city").value;
  var begin_time=document.getElementById("begin_time").value;
  var pend_time=document.getElementById("pend_time").value;
  var charter_flg=document.getElementById("charter_flg").value;
  saveValue('start_city',start_city,'end_city',end_city,'begin_time',begin_time,'pend_time',pend_time,'charter_flg',charter_flg);
  formSubmitByPath(document.forms[0],'<%=passengerLink %>');
  doButtonSubmit(document.forms[0], 'searchCarpoolList', 'submitField');

	 }
</script>
<title>生成订单</title>
</head>

<body>
<div class="page1">
 <html:form action="/CarpoolDriver.do" method="get">
  <header>
 
   <h3 style="text-align:center;color:#000; ">生成订单</h3><a class="back" href="javascript:void(0);" onClick="back();"></a>    
  </header>
    
  <div class="content1" style="padding:0">    
   
    <section class="list" style="margin:50px 0 0 0">
        <logic:present name="CarpoolDriverForm" property="list"> 
          <span style="font-size:16px;color:#999;line-height:30px;margin-left:10px;margin-top:10px;display:block">合并到已有的路线</span>
    
      <ul style="border-bottom:1px dashed #999">
    <c:forEach items="${CarpoolDriverForm.list}" var="driverRouteList">
     <li>
           <section class="top1" style="float:left;border:none;font-size:14px">            <p style="font-size:14px;font-weight:700"><font style="color:#404040">出发时间：</font>${driverRouteList.driverGo_time}  <font style="color:#404040;text-align:right">（剩余车位：</font><font style="color:#404040;text-align:right" id="num">${driverRouteList.sup_num}</font><font style="color:#404040;text-align:right">）</font></p>
            <font class="start">起</font>${driverRouteList.start_city}<span class="icon-to"></span><font class="end">终</font>${driverRouteList.end_city}<font style="color:#0086ff;"> </font>
          
             
            </section> 
            <!--   <a href="#" style="position:absolute;right:7px;padding:2px 10px;background:#0086ff;border-radius:5px;color:#fff;margin-top:30px" onClick=" saveValue('dr_id','${driverRouteList.dr_id}','pr_id','${driverRouteList.pr_id}');doButtonSubmit(document.forms[0], 'joinDriverRoute', 'submitField');">加入</a>-->
         <a href="javascript:void(0);" style="position:absolute;right:7px;padding:2px 10px;background:#0086ff;border-radius:5px;color:#fff;margin-top:30px"  onClick="joinRoute('${driverRouteList.dr_id}','${driverRouteList.sup_num}');">加入</a>
         <!--   <a href="#" style="position:absolute;right:7px;padding:2px 10px;background:#0086ff;border-radius:5px;color:#fff;margin-top:30px">加入</a>-->
        </li> </c:forEach>                
      </ul></logic:present>
      <logic:notPresent name="CarpoolDriverForm" property="list" >
       <span style="font-size:16px;color:#999;line-height:30px;margin-left:10px;margin-top:10px;display:block">新建路线</span>
       <ul style="border-bottom:1px dashed #999">
     <li>
           <section class="top1" style="float:left;border:none;font-size:14px">            <p style="font-size:14px;font-weight:700"><font style="color:#404040">出发时间：</font>${CarpoolDriverForm.go_time} <font style="color:#404040;text-align:right">（剩余车位：${CarpoolDriverForm.seat_num}）</font></p>
            <font class="start">起</font>${CarpoolDriverForm.start_city}<span class="icon-to"></span><font class="end">终</font>${CarpoolDriverForm.end_city}<font style="color:#0086ff;"> </font>
          
             
            </section> 
          <a href="javascript:void(0);" style="position:absolute;right:7px;padding:2px 10px;background:#0086ff;border-radius:5px;color:#fff;margin-top:30px"onClick="createRoute('${driverRouteList.dr_id}','${CarpoolDriverForm.seat_num}');">新建</a>
        </li>                
  
      </ul>
      </logic:notPresent>
    </section>
    
  </div>
 
 <div class="box" style="display:none;">
	<img src="<%=strPath%>/images/notification.png"  width="20" height="20" style="position:absolute;left:10px;top:13px">
	<div class="txt" style="text-align:center" id="tishi"></div>
	</div>
   
    <div class="box1" style="display:none;" id="htmlerror">
	
	<img src="<%=strPath%>/images/notification.png"  width="20" height="20" style="position:absolute;left:10px;top:13px">
	 <div class="txt" id="error" style="text-align:center"><html:errors/></div>	
</div>
    <html:hidden property="requestBy" styleId="requestBy" value="h5" />
    <html:hidden property="dr_id" styleId="dr_id" />
    <html:hidden property="pr_id" styleId="pr_id" />
    <html:hidden property="cu_id" styleId="cu_id"  />
    <html:hidden property="submitField" styleId="submitField" value="" />
    <html:hidden property="pass_num" styleId="pass_num" />
     <html:hidden property="start_city" styleId="start_city" />
     <html:hidden property="end_city" styleId="end_city" />
    <html:hidden property="begin_time" styleId="begin_time" />
	<html:hidden property="pend_time" styleId="pend_time" />
	<html:hidden property="charter_flg" styleId="charter_flg" />
	<html:hidden property="screenId" styleId="screenId" value="validRoute"/>
  </html:form>
 
  
  </div>
  
   <section class="bottom" style="">
 *注：出发时间在一个小时内的相同路线可以合并订单。
   </section>
  
</body>
</html>
