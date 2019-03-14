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
<title>乘客</title>
  <style>
  #format { margin-top: 2em; }

  </style>
<script>
 $(function(){
	$('#go_time').date({theme:"datetime"});

});


 function modifyCss(){
		$(".box").css({
				left:($("body").width()-$(".box").width())/2-20+"px",
				top:($(window).height()-$(".box").height())/2+$(window).scrollTop()+"px",
				display:"block"
			}).show(100).delay(1000).hide(100);
	}
function add(count){ 
   var numq=document.getElementById("count").innerHTML;
   if(parseInt(numq)+1<=count){
    document.getElementById("count").innerHTML=parseInt(numq) + 1;}
   else{
		//alert('超过车辆正常搭载人数了');
		$("#tishi").html("超过车辆正常搭载人数了！");
		modifyCss();
		return;}

}
function sub(count){
  
   var numq=document.getElementById("count").innerHTML;
   if(numq>1){
    document.getElementById("count").innerHTML=parseInt(numq) - 1;}
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

	$(".city").change(function() {
					var city = $(this);
					$(".city").val(city.val());
					document.getElementById("start_area").value = city.val();
				});
	$(".city1").change(function() {
					var city1 = $(this);
					//alert(city1.val());
					$(".city1").val(city1.val());
					document.getElementById("end_area").value = city1.val();
				});

	$(".province1").change(function() {
					var province = document.getElementById("start_city").value;
					var province1 = document.getElementById("end_city").value;
					calPrice(province, province1);
				});
			});
	function calPrice(city, city1) {
		var province = city;
		var province1 = city1;
		if (province == "起点城市") {
			$("#tishi").html("请选择起点城市！");
			modifyCss();
			return;
		} else if (province1 == "终点城市") {
		$("#tishi").html("请选择终点城市！");
			modifyCss();
			return;
		}
		var price = 0.0;
		// alert("prov"+province);
		if (province == "沈阳" && province1 == "大连") {
			price = 100;
			// alert(price);
		} else if(province == "沈阳" && province1 == "鞍山") {
			price = 20;
		}
		else if (province == "沈阳" && province == "抚顺"){
			price = 20;
			// alert(price);
		}  else if (province == "大连" && province1 == "辽阳") {
			price = 80;
		} 
		else {
			price = 50;
		}
		document.getElementById("cal_price").innerHTML = price;
		document.getElementById("price").value = price;
	}

	function dateToStr(datetime){

		var year = datetime.getFullYear();
		var month = datetime.getMonth()+1;//js从0开始取 
		var date = datetime.getDate(); 
		var hour = datetime.getHours(); 
		var minutes = datetime.getMinutes(); 
		var second = datetime.getSeconds();

		if(month<10){
		 month = "0" + month;
		}
		if(date<10){
		 date = "0" + date;
		}
		if(hour <10){
		 hour = "0" + hour;
		}
		if(minutes <10){
		 minutes = "0" + minutes;
		}
		if(second <10){
		 second = "0" + second ;
		}


		var time = year+"-"+month+"-"+date+" "+hour+":"+minutes; //2009-06-12 17:18:05

		return time;}
</script>

<script type="text/javascript">
	function checkSelect() {
		var selectStartCity = document.getElementById("start_city").value;
		var selectEndCity = document.getElementById("end_city").value;
		var selectTime = document.getElementById("go_time").value;
        var date1=new Date();
		var date=dateToStr(date1);
		if (selectStartCity == '起点城市') {
			$("#tishi").html("请选择起点城市！");
			modifyCss();
			return;

		} else if (selectEndCity == '终点城市') {
			$("#tishi").html("请选择终点城市！");
			modifyCss();
			return;
		} 
		else if (selectEndCity ==selectStartCity) {
			$("#tishi").html("请选择不同城市！");
			modifyCss();
			return;
		}else if (selectTime == '') {
			$("#tishi").html("请选择出发时间！");
			modifyCss();
			return;
		} else if (selectTime != ''&&selectTime<date) {
			$("#tishi").html("不能发布过去时间的路线！");
			modifyCss();
			return;
		} else {
			document.getElementById("people_count").value = document.getElementById("count").innerHTML;
			doButtonSubmit(document.forms[0], 'publishPassengerRoute','submitField');

		}

	}
</script>

</head>

<body>
<div class="page1">
 <html:form action="/CarpoolPassenger.do" method="get">   
  <header> 
   <a class="admin" href="javascript:void(0);" onClick="formSubmitByPath(document.forms[0],'<%=userLink %>');doButtonSubmit(document.forms[0], 'getCarpoolUserDetail', 'submitField');"></a><h3><a class="switch" href="javascript:void(0);" onClick="formSubmitByPath(document.forms[0],'<%=passengerLink %>');doButtonSubmit(document.forms[0], 'changeToDriver', 'submitField');"><span style="color:#000;position:relative;top:-1px">乘客</span><i class="icon-switch"></i></a>  </h3><!--  <a class="back" href="#"></a> -->   
  </header>
  
  <div class="content1" style="padding:50px 0 0 0">
    
    <section class="select" >
      <section class="hr"><span class="one"></span><font>选择路线</font><span class="two"></span></section>
<!--选择路线-->      
 <div class="select1">
        
        <span class="qidian">

<select name="start_city"  id="start_city"  value="${CarpoolPassengerForm.start_city}" class="province" onChange="redirec(document.forms[0].start_city.options.selectedIndex)" style="border-bottom:1px dashed #666">
<option value="起点城市" >起点城市</option>
        <option value="沈阳">沈阳</option>
           <option value="大连">大连</option>
           <option value="鞍山">鞍山</option>
          <option value="抚顺">抚顺</option>
           <option value="本溪">本溪</option>
           <option value="丹东">丹东</option>
           <option value="锦州">锦州</option>
           <option value="营口">营口</option>
            <option value="阜新">阜新</option>
           <option value="辽阳">辽阳</option>
           <option value="盘锦">盘锦</option>
           <option value="铁岭">铁岭</option>
           <option value="朝阳">朝阳</option>
           <option value="葫芦岛">葫芦岛</option>
</select>

<select name="start_area"  id="start_area" class="city" >
<option value="市区" selected>市区</option>
</select>

          </span>
       <!--  <i></i>-->
   
      </div>
      <span class="icon-to" style="line-height:66px"></span>
       <div class="select1" style="float:right;overflow:hidden">
        
        <span class="qidian">
  
 <select  name="end_city" id="end_city"  class="province1" value="${CarpoolPassengerForm.end_city}"  onChange="redirec1(document.forms[0].end_city.options.selectedIndex)" style="border-bottom:1px dashed #666">
       <option value="终点城市" selected>终点城市</option>
           <option value="沈阳">沈阳</option>
           <option value="大连">大连</option>
           <option value="鞍山">鞍山</option>
          <option value="抚顺">抚顺</option>
           <option value="本溪">本溪</option>
           <option value="丹东">丹东</option>
           <option value="锦州">锦州</option>
           <option value="营口">营口</option>
            <option value="阜新">阜新</option>
           <option value="辽阳">辽阳</option>
           <option value="盘锦">盘锦</option>
           <option value="铁岭">铁岭</option>
           <option value="朝阳">朝阳</option>
           <option value="葫芦岛">葫芦岛</option>
</select>

<select name="end_area" id="end_area" class="city1" >
<option value="市区" selected>市区</option>
</select>
           </span>
       <!--  <i></i>-->
   
      </div>
<!--选择路线-->
<p style="float:left;font-size:12px;text-align:center;display:block;width:100%">参考点数：<font style="color:#ff0000" id="cal_price">0.0</font><font style="color:#ff0000">点</font></p>
     
<!--填写详情-->           
            <section class="hr"><span class="one"></span><font>填写详情</font><span class="two"></span></section>    
      <div class="detail">
         <p class="m-top" ><font>出发时间：</font>
          <input  id="go_time"  name="go_time"  value="${CarpoolPassengerForm.go_time}"class="kbtn" placeholder="请选择日期" style="width:60%;line-height:30px;color:#0086ff"/>
           <!-- <html:text property="go_time" styleId="go_time" style="position:absolute; width:64%;ime-mode:disabled" onkeypress="noPermitInput(event)"  styleClass="datetimepicker" ></html:text>
       -->       
        </p>
       <p class="jj"> <font style="float:left;margin:0 0 4px 0">搭乘人数：</font><a href="#" style="margin:4px 10px 0 0;line-height:16px;text-indent:8px;float:left" onClick="sub(1) "><img src="<%=strPath%>/images/-.png" width="20" height="20"></a>
<span id="count" style="float:left" value="${CarpoolPassengerForm.people_count}">1</span>
<a href="javascript:void(0);" onClick="add(10)"  style="border-radius:50px;margin:4px 0 0  10px;line-height:18px;text-indent:5px;float:left"><img src="<%=strPath%>/images/+.png" width="20" height="20"></a></p>
     <p style="clear:both;border-top:1px solid #8a8a8a">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：<html:text property="pr_memo"></html:text>
        </p>
      </div>

 <!--填写详情-->   
 <!--包车-->        
      
     <section class="checkbox" style="top:20px"> 
 
   <html:checkbox property="charter_flg"   value="1" styleId="check" styleClass="ui-helper-hidden-accessible"  style="float:left;position:relative;left:12%;top:10px;display:block"></html:checkbox><label for="check" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false" aria-pressed="false"><span class="ui-button-text">我要包车</span></label></section>
       
  <!--包车-->        
 <!--   <a href="#" class="button" style="margin-top:40px" onClick="doButtonSubmit(document.forms[0], 'publishPassengerRoute', 'submitField');">发布路线</a>-->
   
   <a href="javascript:void(0);return" class="button" style="margin-top:40px;" onClick="saveValue('returnCode','10001');formSubmitByPath(document.forms[0],'<%=passengerLink %>');checkSelect()">发布路线</a> 
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
      <html:hidden property="cu_id" styleId="cu_id" />
      <html:hidden property="submitField" styleId="submitField" value="" />
      <html:hidden property="dp_flag" styleId="dp_flag" value="1" />
      <html:hidden property="people_count" styleId="people_count" />
      <html:hidden property="price" styleId="price" />
      <html:hidden property="screenId" styleId="screenId" value="passenger_publish" />
      <html:hidden property="returnCode" styleId="returnCode" />
     <!--   <html:hidden property="end_area" styleId="end_area" />
       <html:hidden property="start_area" styleId="start_area" />-->
     
   <div class="footer" style="float:left;font-size:12px;">*注：多个乘客搭乘时，出发时间可能有小幅度偏差</div>
  </div>
  <div id="datePlugin"></div>
    </html:form>
  
  </div>
</body>

<script language="javascript">
//获取一级菜单长度
var select1_len = document.forms[0].start_city.length;
var select2 = new Array(select1_len);
//把一级菜单都设为数组
for (i=0; i<select1_len; i++)
{ select2[i] = new Array();}
//定义基本选项
select2[0][0] = new Option("市区", "市区 ");

select2[1][0] = new Option("皇姑区", "皇姑区 ");
select2[1][1] = new Option("大东区", "大东区 ");
select2[1][2] = new Option("铁西区", "铁西区 ");
select2[1][3] = new Option("和平区", "和平区 ");
select2[1][4] = new Option("沈河区", "沈河区 ");
select2[1][5] = new Option("于洪区", "于洪区 ");
select2[1][6] = new Option("浑南新区", "浑南新区 ");


select2[2][0] = new Option("西岗区", "西岗区");
select2[2][1] = new Option("中山区", " 中山区");
select2[2][2] = new Option("沙河口区", "沙河口区 ");
select2[2][3] = new Option("甘井子区", "甘井子区 ");

select2[3][0] = new Option("铁东区", "铁东区");
select2[3][1] = new Option("铁西区", "铁西区");
select2[3][2] = new Option("立山区", "立山区 ");
select2[3][3] = new Option("千山区", "千山区 ");

select2[4][0] = new Option("新抚区", "铁东区");
select2[4][1] = new Option("望花区", "望花区");
select2[4][2] = new Option("东洲区", "东洲区 ");
select2[4][3] = new Option("顺城区", "顺城区 ");

select2[5][0] = new Option("新抚区", "铁东区");
select2[5][1] = new Option("望花区", "望花区");
select2[5][2] = new Option("东洲区", "东洲区 ");
select2[5][3] = new Option("顺城区", "顺城区 ");

select2[6][0] = new Option("振兴区", "振兴区");
select2[6][1] = new Option("元宝区", "元宝区");
select2[6][2] = new Option("振安区", "振安区 ");

select2[7][0] = new Option("古塔区 ", "古塔区 ");
select2[7][1] = new Option("凌河区", "凌河区");
select2[7][2] = new Option("太和区", "太和区 ");


select2[8][0] = new Option("站前区", "站前区");
select2[8][1] = new Option("西市区", "西市区");
select2[8][2] = new Option("老边区 ", "老边区  ");
select2[8][3] = new Option("鲅鱼圈区", "鲅鱼圈区 ");


select2[9][0] = new Option("海州区", "海州区");
select2[9][1] = new Option("细河区", "细河区");
select2[9][2] = new Option("新邱区", "新邱区   ");
select2[9][3] = new Option("清河门区", "鲅鱼圈区 ");

select2[10][0] = new Option("白塔区", "白塔区");
select2[10][1] = new Option("文圣区", "文圣区");
select2[10][2] = new Option("宏伟区", "宏伟区  ");
select2[10][3] = new Option("弓长岭区", "弓长岭区 ");
select2[10][4] = new Option("太子河区", "太子河区");
    
select2[11][0] = new Option("兴隆台区", "兴隆台区");
select2[11][1] = new Option("双台子区", "双台子区");


select2[12][0] = new Option("银州区 ", "银州区 ");
select2[12][1] = new Option("清河区", "清河区");
select2[12][2] = new Option("铁岭新城区", "铁岭新城区");



select2[13][0] = new Option("双塔区", "双塔区");
select2[13][1] = new Option("龙城区", "龙城区");


select2[14][0] = new Option("连山区 ", "连山区 ");
select2[14][1] = new Option("龙港区", "龙港区");
select2[14][2] = new Option("南票区", "南票区");
  
//联动函数
function redirec(x)
{
	//var  x=document.getElementById("start_city").selectedIndex;
var temp= document.forms[0].start_area;
for (i=0;i<select2[x].length;i++)
{ temp.options[i]=new Option(select2[x][i].text,select2[x][i].value);
}
temp.options[0].selected=true;

var province=document.getElementById("start_city").value;
var province1=document.getElementById("end_city").value;
if(province!='起点城市'&&province1!='终点城市'){
calPrice(province,province1);
}}
</script>

<script language="javascript">
//获取一级菜单长度
var select3_len = document.forms[0].end_city.options.length;
var select4 = new Array(select3_len);
//把一级菜单都设为数组
for (i=0; i<select3_len; i++)
{ select4[i] = new Array();}
//定义基本选项
select4[0][0] = new Option("市区", "市区 ");

select4[1][0] = new Option("皇姑区", "皇姑区 ");
select4[1][1] = new Option("大东区", "大东区 ");
select4[1][2] = new Option("铁西区", "铁西区 ");
select4[1][3] = new Option("和平区", "和平区 ");
select4[1][4] = new Option("沈河区", "沈河区 ");
select4[1][5] = new Option("于洪区", "于洪区 ");
select4[1][6] = new Option("浑南新区", "浑南新区 ");


select4[2][0] = new Option("西岗区", "西岗区");
select4[2][1] = new Option("中山区", " 中山区");
select4[2][2] = new Option("沙河口区", "沙河口区 ");
select4[2][3] = new Option("甘井子区", "甘井子区 ");

select4[3][0] = new Option("铁东区", "铁东区");
select4[3][1] = new Option("铁西区", "铁西区");
select4[3][2] = new Option("立山区", "立山区 ");
select4[3][3] = new Option("千山区", "千山区 ");

select4[4][0] = new Option("新抚区", "铁东区");
select4[4][1] = new Option("望花区", "望花区");
select4[4][2] = new Option("东洲区", "东洲区 ");
select4[4][3] = new Option("顺城区", "顺城区 ");

select4[5][0] = new Option("新抚区", "铁东区");
select4[5][1] = new Option("望花区", "望花区");
select4[5][2] = new Option("东洲区", "东洲区 ");
select4[5][3] = new Option("顺城区", "顺城区 ");

select4[6][0] = new Option("振兴区", "振兴区");
select4[6][1] = new Option("元宝区", "元宝区");
select4[6][2] = new Option("振安区", "振安区 ");

select4[7][0] = new Option("古塔区 ", "古塔区 ");
select4[7][1] = new Option("凌河区", "凌河区");
select4[7][2] = new Option("太和区", "太和区 ");


select4[8][0] = new Option("站前区", "站前区");
select4[8][1] = new Option("西市区", "西市区");
select4[8][2] = new Option("老边区 ", "老边区  ");
select4[8][3] = new Option("鲅鱼圈区", "鲅鱼圈区 ");


select4[9][0] = new Option("海州区", "海州区");
select4[9][1] = new Option("细河区", "细河区");
select4[9][2] = new Option("新邱区", "新邱区   ");
select4[9][3] = new Option("清河门区", "鲅鱼圈区 ");

select4[10][0] = new Option("白塔区", "白塔区");
select4[10][1] = new Option("文圣区", "文圣区");
select4[10][2] = new Option("宏伟区", "宏伟区  ");
select4[10][3] = new Option("弓长岭区", "弓长岭区 ");
select4[10][4] = new Option("太子河区", "太子河区");
    
select4[11][0] = new Option("兴隆台区", "兴隆台区");
select4[11][1] = new Option("双台子区", "双台子区");


select4[12][0] = new Option("银州区 ", "银州区 ");
select4[12][1] = new Option("清河区", "清河区");
select4[12][2] = new Option("铁岭新城区", "铁岭新城区");



select2[13][0] = new Option("双塔区", "双塔区");
select2[13][1] = new Option("龙城区", "龙城区");


select2[14][0] = new Option("连山区 ", "连山区 ");
select2[14][1] = new Option("龙港区", "龙港区");
select2[14][2] = new Option("南票区", "南票区");
  

//联动函数
function redirec1(x)
{
	//var  x=document.getElementById("end_city").selectedIndex;
	var temp = document.forms[0].end_area;
for (i=0;i<select4[x].length;i++)
{ temp.options[i]=new Option(select4[x][i].text,select4[x][i].value);}
var index=document.forms[0].end_area.options.selectedIndex;
 //var  area=select4[x][index].value;
// document.forms[0].end_area.value=area;
temp.options[0].selected=true;
var province=document.getElementById("start_city").value;
var province1=document.getElementById("end_city").value;
if(province!='起点城市'&&province1!='终点城市'){
	calPrice(province,province1);
	}}
</script>
</html>
