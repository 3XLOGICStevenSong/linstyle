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

%>
<html>
<head>
<meta charset=utf-8 />
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" name="viewport" />
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/css.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/icomoon.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/box.css"/>
<script src="<%=strPath%>/js/jquery.js"></script>
<script src="<%=strPath%>/js/modal.js"></script>
<script src="<%=strPath%>/js/common/common.js"></script>
<title>查找乘客</title>
<style>
     .btn{padding:0}
.btn-primary{color:#000;background:none;border:none;width: 100%;text-align: left;}
.btn-primary:hover{color:#000;background:none;border:none}
.btn-primary.focus, .btn-primary:focus{color:#000;background:none;border:none}
.btn-primary:active{background:none;border:none;}
.modal-dialog{width:auto;margin:150px 30px}
   </style>
   
     <script type="text/javascript">
     $(document).ready(function(){
    	 var errorValue=$("#error").html();
    	 if(errorValue!=''&& errorValue!=null&& errorValue!=undefined){
    			//$("#TB_overlayBG").css({display:"block",height:$(document).height()});
    			$(".box1").css({
    				left:($("body").width()-$(".box1").width())/2-20+"px",
    				top:($(window).height()-$(".box1").height())/2+$(window).scrollTop()+"px",
    				display:"block"
    			}).show(100).delay(1000).hide(100);
    			return;
    			}
    });
function orderRoute(pr_id){
	var pid=pr_id;	
    var uid=document.getElementById("cu_id").value;
    var requestBy=document.getElementById("requestBy").value; 
	$.ajax({  
		type:"get", // 请求方式
		url:"<%=driverLink%>?getValidRoute=",
        data:{pr_id:pid,cu_id:uid,requestBy:requestBy},
		async: false, // 是否异步
		dataType:"text", // 设置返回数据的格式
		// ajax请求成功后的回调函数 data为json格式
		success:function(data){
			var result=$(data).find("result").text();
			var rowIndex=$(data).find("rowIndex").text();
			var outerIndex=$(data).find("outerIndex").text();
			if(result=="1"){
				var li_id_row = document.all("li_id_row");		
				var li_id_row_index=null;
				if (li_id_row != null && li_id_row != undefined) {
					if (li_id_row.id == "li_id_row") {
						 li_id_row_index=li_id_row;
				} else {					
					 li_id_row_index=li_id_row[outerIndex];
				}
				}
				var state=li_id_row_index.querySelectorAll("#state");
				if (state != null && state != undefined) {
					if (state.id == "state") {
						$("#state[rowIndex]").html("司机取消路线");
						state.value="司机取消路线";
						$("#result").html("取消路线成功");		
					}else{
							//state[rowIndex].value="司机取消路线";
							state[rowIndex].innerHTML="司机取消路线";
							//$("#state[rowIndex]").html("司机取消路线");
						//	$("#state").html("司机取消路线");
							$("#result").html("取消路线成功");		
						}
				//state.innerHTML="司机取消路线";
				
						}else{
							$("#result").html("取消路线失败");
						}
				}else{
							$("#result").html("取消路线失败");
						}

		},
		// ajax请求失败后的处理  
		error:function(){
			alert("页面发生错误");
		}
	 });
}
</script>
</head>

<body>
	<div class="page1">
	<html:form action="/CarpoolPassenger.do" method="get" >
		<header style="height:50px">
			<h3 style="text-align: center; color: #000;">查找乘客</h3>
			<a class="back" href="javascript:void(0);" style="right:6px" onClick="doButtonSubmit(document.forms[0], 'backToSearch', 'submitField');"></a>
		</header>
		<%	
	    int upIndex = 0;

		%>
		
			<div class="content1">
				<logic:present name="CarpoolPassengerForm"	property="passengerRoutelist">
					<section class="top">
						<font class="start">起</font>${CarpoolPassengerForm.start_city}<span
							class="icon-to"></span><font class="end">终</font>${CarpoolPassengerForm.end_city}
					<p> <c:if test="${CarpoolPassengerForm.begin_time!=null&&CarpoolPassengerForm.begin_time!=''&&CarpoolPassengerForm.pend_time!=null&&CarpoolPassengerForm.pend_time!=''}">${CarpoolPassengerForm.begin_time} —
							${CarpoolPassengerForm.pend_time}</c:if><c:if test="${CarpoolPassengerForm.begin_time!=null&&CarpoolPassengerForm.pend_time==null}">${CarpoolPassengerForm.begin_time} 
							</c:if><c:if test="${CarpoolPassengerForm.begin_time!=null&&CarpoolPassengerForm.begin_time!=''&&CarpoolPassengerForm.pend_time==''}">${CarpoolPassengerForm.begin_time} 
							</c:if><c:if test="${CarpoolPassengerForm.pend_time!=null&&CarpoolPassengerForm.pend_time!=''&&CarpoolPassengerForm.begin_time==null}">
							${CarpoolPassengerForm.pend_time}</c:if><c:if test="${CarpoolPassengerForm.pend_time!=null&&CarpoolPassengerForm.pend_time!=''&&CarpoolPassengerForm.begin_time==''}">
							${CarpoolPassengerForm.pend_time}</c:if></p>
					</section>

					<section class="list">
						<ul style="margin-bottom:32px">
							<c:forEach items="${CarpoolPassengerForm.passengerRoutelist}"
								var="routeList">
								<li>
									 <logic:present name="routeList"
										property="carpoolUser">
										
										
								 <!--对话框start-->        
 <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal<%=upIndex %>"><p class="title">
												${routeList.carpoolUser.cu_nick}<font class="ck">乘客</font><font>搭乘人数：${routeList.people_count}</font>
											</p>
            </button>   
            <div class="modal fade" id="myModal<%=upIndex %>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"  style="color:#ff9c00">乘客</h4>
      </div>
      <div class="modal-body">
 <p style="font-size: 20px" class="modal-name">
													${routeList.carpoolUser.cu_nick}<font
														style="position: absolute; right: 10px; font-size: 14px;">成功搭车${routeList.carpoolUser.p_success_count}次</font>
												</p>
												<span style="font-size: 14px;">

													<p>
														联系电话：<font style="color: #999">${routeList.carpoolUser.cu_tel}</font>
													</p>
													<p>注册日期：${routeList.carpoolUser.create_time}</p>
												</span>
      </div>
                
    </div>
  </div>
</div>        
 
 <!--对话框end--> 		
										
										
										
										
				<!--  					
										
										<a data-toggle="modal" href="#myModal<%=upIndex %>"
											class="btn btn-primary size-L">
											<p class="title">
												${routeList.carpoolUser.cu_nick}<font class="ck">乘客</font><font>搭乘人数：${routeList.people_count}</font>
											</p>
										</a>
									
										<div id="myModal<%=upIndex %>" class="modal hide fade" tabindex="-1"
											role="dialog" aria-labelledby="myModalLabel"
											aria-hidden="true">
											<div class="modal-header">
												<h3 id="myModalLabel" style="color: #ff9c00">乘客</h3>
												<a class="close" data-dismiss="modal" aria-hidden="true"
													href="javascript:void();">×</a>
											</div>
											<div class="modal-body">
												<p style="font-size: 20px" class="modal-name">
													${routeList.carpoolUser.cu_nick}<font
														style="position: absolute; right: 10px; font-size: 14px;">成功搭车${routeList.carpoolUser.p_success_count}次</font>
												</p>
												<span style="font-size: 14px;">

													<p>
														联系电话：<font style="color: #999">${routeList.carpoolUser.cu_tel}</font>
													</p>
													<p>注册日期：${routeList.carpoolUser.create_time}</p>
												</span>
											</div>
											 <div class="modal-footer">
     <button class="btn btn-primary">确定</button> <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
     </div>
										</div>

										<div id="ajaxModal" class="modal hide fade" tabindex="-1"
											data-width="700"></div>-->	
										<!--对话框-->
										<p class="position">
											<font class="icon-qi1 qi"></font>${routeList.start_area}<span
												class="icon-to"></span><font class="icon-qi1 zhong"></font>${routeList.end_area}&nbsp;&nbsp;(${routeList.price}点)
										</p>
										<p class="main">
											出发时间：<font style="color: #0086ff;">${routeList.go_time}</font><br><c:if test="${routeList.pr_memo!=''}">备
											注：${routeList.pr_memo}</c:if><c:if test="${routeList.charter_flg==1}"> <font class="bc">包车</font></c:if>
											  <a href="javascript:void(0);" onClick="saveValue('pr_id','${routeList.pr_id}');formSubmitByPath(document.forms[0],'<%=driverLink %>');doButtonSubmit(document.forms[0], 'getValidRoute', 'submitField');">抢单</a>
											<!--<a href="#" onClick="orderRoute('${routeList.pr_id}');">抢单</a>-->
										</p>

									</logic:present>
								</li>
								<%
								upIndex++;
								%>
							</c:forEach>
						</ul>
					</section>
				</logic:present>
				<logic:notPresent  name="CarpoolPassengerForm"	property="passengerRoutelist">
			<div class="none" style="vertical-align:middle;line-height:30px;text-align:center;font-size:16px;color:#666;margin-top:150px"><img src="<%=strPath%>/images/search.png" width="32" height="32" style="margin:0 auto;display:block;margin-bottom:10px">抱歉，没有找到符合您的路线</div>
				</logic:notPresent>

			</div>
	 <div class="box" style="display:none;">
	<img src="<%=strPath%>/images/notification.png"  width="20" height="20" style="position:absolute;left:10px;top:13px">
	<div class="txt" style="text-align:center" id="tishi"></div>
	</div>
   
    <div class="box1" style="display:none;" id="htmlerror">
	
	<img src="<%=strPath%>/images/notification.png"  width="20" height="20" style="position:absolute;left:10px;top:13px">
	 <div class="txt" id="error" style="text-align:center"><html:errors/></div>	
</div>
			<html:hidden property="cu_id" styleId="cu_id" />
			<html:hidden property="pr_id" styleId="pr_id" />
			<html:hidden property="requestBy" styleId="requestBy" value="h5" />
			<html:hidden property="submitField" styleId="submitField" value="" />
			<html:hidden property="start_city" styleId="start_city" />
			<html:hidden property="end_city" styleId="end_city" />
			<html:hidden property="begin_time" styleId="begin_time" />
			<html:hidden property="pend_time" styleId="pend_time" />
			<html:hidden property="charter_flg" styleId="charter_flg" />

		</html:form>
	</div>

	<section class="bottom" style="">
 *注：出发时间在一个小时内的相同路线可以合并订单。
   </section>

</body>
</html>
