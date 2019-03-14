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
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/checkbox.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/icomoon.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/box.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/common.css"/>
<script src="<%=strPath%>/js/checkbox.js"></script>
<script src="<%=strPath%>/js/checkbox1.js"></script>
<script src="<%=strPath%>/js/jquery-1.11.1.min.js"></script>
<script src="<%=strPath%>/js/date.js"></script>
<script src="<%=strPath%>/js/iscroll.js"></script>
<script src="<%=strPath%>/js/common/common.js"></script>
<title>车主</title>
<script>
 $(function(){
	//$('#beginTime').date();
	$('#begin_time').date({theme:"datetime"});
	$('#pend_time').date({theme:"datetime"});
});
</script>

  <style>
  #format { margin-top: 2em; }

  </style>
<script type="text/javascript">
$(document).ready(function(){
	 var errorValue=$("#error").html();
		if(errorValue!=''&& errorValue!=null&& errorValue!=undefined){
			$(".box1").css({
				left:($("body").width()-$(".box1").width())/2-20+"px",
				top:($(window).height()-$(".box1").height())/2+$(window).scrollTop()+"px",
				display:"block"
			});
			return;
			}
});
function  checkSelect(){
var selectStart = document.getElementById("start").value;
var selectEnd = document.getElementById("end").value;
var begin_time = document.getElementById("begin_time").value;
var pend_time = document.getElementById("pend_time").value;

if(selectStart=='起点'){
$("#tishi").html("请选择起点城市！");	
	$(".box").css({
		left:($("body").width()-$(".box").width())/2-20+"px",
		top:($(window).height()-$(".box").height())/2+$(window).scrollTop()+"px",
		display:"block"
	}).show(100).delay(1000).hide(100);
	return;
}else if(selectEnd=='终点'){
$("#tishi").html("请选择终点城市！");	
	$(".box").css({
		left:($("body").width()-$(".box").width())/2-20+"px",
		top:($(window).height()-$(".box").height())/2+$(window).scrollTop()+"px",
		display:"block"
	}).show(100).delay(1000).hide(100);
	return;
}
else if(selectEnd==selectStart){
	$("#tishi").html("请选择不同城市！");		
		$(".box").css({
			left:($("body").width()-$(".box").width())/2-20+"px",
			top:($(window).height()-$(".box").height())/2+$(window).scrollTop()+"px",
			display:"block"
		}).show(100).delay(1000).hide(100);
		return;
	}else if(begin_time!=''&& pend_time!=''){
		var initH1=parseInt((begin_time).substr(11,12));
		var begin_time1="";
		var pend_time1="";
		if(initH1<10){
			var h1="0"+initH1;
			begin_time1=begin_time.substr(0,11)+h1+begin_time.substr(12,14);
		}else{h1=initH1;		
	 begin_time1=begin_time.substr(0,11)+h1+begin_time.substr(13,15);}	
		var initH2=parseInt((pend_time).substr(11,12));		
		if(initH2<10){
			var h2="0"+initH2;
			 pend_time1=pend_time.substr(0,11)+h2+pend_time.substr(12,14);
		}else{
		h2=initH2;
		pend_time1=pend_time.substr(0,11)+h2+pend_time.substr(13,15);}		
		if(begin_time1 >pend_time1){
        $("#tishi").html("起点时间不能大于终点时间！");	
	$(".box").css({
		left:($("body").width()-$(".box").width())/2-20+"px",
		top:($(window).height()-$(".box").height())/2+$(window).scrollTop()+"px",
		display:"block"
	}).show(100).delay(1000).hide(100);
	return;}else{
		doButtonSubmit(document.forms[0], 'searchCarpoolList', 'submitField');	}     	
		}
	else{
	doButtonSubmit(document.forms[0], 'searchCarpoolList', 'submitField');	
}
}

</script>
 
</head>

<body>
            

  <div class="page1">
   <html:form action="/CarpoolPassenger.do" method="get">   
  <header>
   <a class="admin" href="javascript:void(0);" onClick="formSubmitByPath(document.forms[0],'<%=userLink %>');doButtonSubmit(document.forms[0], 'getCarpoolUserDetail', 'submitField'); return false;"></a><h3><a  class="switch" href="javascript:void(0);" onClick="formSubmitByPath(document.forms[0],'<%=passengerLink %>');doButtonSubmit(document.forms[0], 'changeToPassenger', 'submitField');"><span style="color:#000;position:relative;top:-1px">车主</span><i class="icon-switch"></i></a> </h3><!-- <a class="back" href="#"></a>  -->   
  </header>
  <div class="content1">
   
    <section class="select" >
      <section class="hr"><span class="one"></span><font>选择路线</font><span class="two"></span></section>
<!--选择路线-->      
 <div class="select1">
        
        <span class="qidian">
       <!--   <select class="qidian"  type="select" name="start_city" id="start"  >
           <option selected="selected">起点</option>
             <option value="沈阳">沈阳</option>
             <option value="大连">大连</option>
             <option>鞍山</option>
             <option>铁岭</option>
              
           </select> --> 
            <html:select  styleClass="qidian" property="start_city" styleId="start">  
           <html:option value="起点">起点</html:option>
           <html:option value="沈阳">沈阳</html:option>
           <html:option value="大连">大连</html:option>
           <html:option value="鞍山">鞍山</html:option>
          <html:option value="抚顺">抚顺</html:option>
           <html:option value="本溪">本溪</html:option>
           <html:option value="丹东">丹东</html:option>
           <html:option value="锦州">锦州</html:option>
           <html:option value="营口">营口</html:option>
            <html:option value="阜新">阜新</html:option>
           <html:option value="辽阳">辽阳</html:option>
           <html:option value="盘锦">盘锦</html:option>
           <html:option value="铁岭">铁岭</html:option>
           <html:option value="朝阳">朝阳</html:option>
           <html:option value="葫芦岛">葫芦岛</html:option>
		   </html:select>
          </span>
       <!--  <i></i>-->
   
      </div>
      <span class="icon-to"></span>
       <div class="select1"  style="float:right;overflow:hidden">
        
        <span class="qidian">
        <!--    <select class="qidian"  type="select" name="end_city" id="end"  >
           <option >终点</option>
             <option>沈阳</option>
             <option >大连</option>
             <option>鞍山</option>
             <option >铁岭</option>
              
           </select>--> 
           <html:select  styleClass="qidian" property="end_city" styleId="end">  
           <html:option value="终点">终点</html:option>
           <html:option value="沈阳">沈阳</html:option>
           <html:option value="大连">大连</html:option>
           <html:option value="鞍山">鞍山</html:option>
           <html:option value="抚顺">抚顺</html:option>
           <html:option value="本溪">本溪</html:option>
           <html:option value="丹东">丹东</html:option>
           <html:option value="锦州">锦州</html:option>
           <html:option value="营口">营口</html:option>
           <html:option value="阜新">阜新</html:option>
           <html:option value="辽阳">辽阳</html:option>
           <html:option value="盘锦">盘锦</html:option>
           <html:option value="铁岭">铁岭</html:option>
           <html:option value="朝阳">朝阳</html:option>
           <html:option value="葫芦岛">葫芦岛</html:option>
		   </html:select>
						</span>
       <!--  <i></i>-->
   
      </div>
<!--选择路线-->     
<!--选择出发时间-->           
            <section class="hr"><span class="one"></span><font>选择出发时间</font><span class="two"></span></section>
      
 <div class="select1" style="overflow:hidden; position:absolute;left:8px">
        
        <span class="qidian" style="text-align:center;display:block;">
        	
        	<!--  <input type="text" name="begin_time" value="请选择时间" class="datetimepicker" style="width:100%"/>-->
        	<input  name="begin_time" id="begin_time" class="kbtn" placeholder="请选择时间"  value="${CarpoolPassengerForm.begin_time}" style="width:96%;line-height:30px"/>
       <!--  	<html:text property="begin_time"  styleId="begin_time" styleClass="datetimepicker" style="width:100%;ime-mode:disabled" onkeypress="noPermitInput(event)"></html:text>-->
  
<!--
         <input type="datetime-local" style="color:#0086ff;font-size:14px; border:none;width:94%;padding:0 0 0 6px;display:block" value="请选择日期" data-date-format="yy-mm-dd"><!--<input type="time" step="1800" style="color:#0086ff;font-size:18px; border:none;width:98%;padding:0 0 0 6px;" value="时间">-->
        </span>
       <!--  <i></i>-->
      </div>
    
      <font class="xian">-</font>
       <div class="select1" style="overflow:hidden; position:absolute;right:8px">
        
        <span class="qidian" style="text-align:center;display:block;padding:padding:6px 0px 6px 0px;text-align:center">
     <!-- <input type="datetime" name="pend_time"  placeholder="请选择时间"/> -->
  <input name="pend_time" id="pend_time" class="kbtn" placeholder="请选择时间" value="${CarpoolPassengerForm.pend_time}" style="width:96%;line-height:30px"/>
    <!--  <html:text property="pend_time"  styleId="pend_time"  styleClass="datetimepicker" style="width:100%;ime-mode:disabled" onkeypress="noPermitInput(event)"></html:text> -->
 </span>
       <!--  <i></i>-->
   
      </div>
 <!--选择出发时间-->   
 <!--包车-->        
    <!-- 
     <section class="checkbox"> <input type="checkbox"  name="charter_flg"  value="1" id="check" class="ui-helper-hidden-accessible"  style="
position: absolute;left:8%;top:10px;display:block"><label for="check" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" aria-pressed="false"><span class="ui-button-text">我要包车</span></label></section> -->  
       
  <!--包车-->        
  <!--  <a href="#" class="button" style="margin-top:80px"  onClick="doButtonSubmit(document.forms[0], 'searchCarpoolList', 'submitField');">查找乘客</a>-->
  <!-- <a href="#" class="button" style="margin-top:80px"  onClick="checkSelect()">查找乘客</a>
    </section>  --> 
    
    
      <section class="checkbox" style="top:60px"> 
      <!--  <input type="checkbox" name="charter_flg"  value="1"  id="check" class="ui-helper-hidden-accessible"  style="float:left;position:relative;left:12%;top:10px;display:block">-->
      <html:checkbox property="charter_flg"   value="1" styleId="check" styleClass="ui-helper-hidden-accessible"  style="float:left;position:relative;left:12%;top:10px;display:block"></html:checkbox>
      
      <label for="check" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" aria-pressed="false"><span class="ui-button-text">我要包车</span></label></section>
       
  <!--包车-->        
  <a href="javascript:void(0);" class="button" style="margin-top:80px"  onClick="formSubmitByPath(document.forms[0],'<%=passengerLink %>');checkSelect()">查找乘客</a>
    
    </section>
    
	  <div class="box" style="display:none;">
	<img src="<%=strPath%>/images/notification.png"  width="20" height="20" style="position:absolute;left:10px;top:13px">
	<div class="txt" style="text-align:center" id="tishi"></div>
	</div>
   
    <div class="box1" style="display:none;" id="htmlerror">
	
	<img src="<%=strPath%>/images/notification.png"  width="20" height="20" style="position:absolute;left:10px;top:13px">
	 <div class="txt" id="error" style="text-align:center"><html:errors/></div>	
</div>

       <html:hidden property="requestBy" styleId="requestBy" value="h5" />
       <html:hidden property="cu_id" styleId="cu_id"  />
       <html:hidden property="submitField" styleId="submitField" value="" />
       <html:hidden property="dp_flag" styleId="dp_flag" value="0" />
      
 
 
   <div class="footer" style="float:left;font-size:12px;margin-top:50px">*注：出发时间在一个小时内的相同路线可以合并订单。</div>
  </div>
 
<div id="datePlugin"></div> 
<div id="datePlugin1"></div> 
</html:form> 
  
  </div>
</body>

</html>
