<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<jsp:useBean id="CarpoolPassengerForm" class="com.djb.highway.carpool.dto.PassengerRouteDTO" scope="request" />
<jsp:useBean id="CarpoolUserForm" class="com.djb.highway.carpool.dto.CarpoolUserDTO" scope="request" />
<%
	String strPath = (String) request.getContextPath();
	String userLink = (String) response.encodeURL(strPath + "/CarpoolUser.do");
	String passengerLink = (String) response.encodeURL(strPath + "/CarpoolPassenger.do");
%>
<%
	//tabs_1的style样式
	String tabs_1_show = "";
	//tabs_2的style样式
	String tabs_2_show = "";
	//每条路线的id
	int strProceeding = 0;
	int strComplete = 0;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" />

<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/jquery-ui.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/bootstrap.css"/>
<link rel="stylesheet" href="<%=strPath%>/css/css.css">
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/box.css"/>
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/icomoon.css" />


<script src="<%=strPath%>/js/common/common.js"></script>
<script src="<%=strPath%>/js/jquery.min.js"></script>
<script src="<%=strPath%>/js/jquery-ui.min.js"></script>
<script src="<%=strPath%>/js/modal.js"></script>


<script>
  $(function() {
    $( "#tabs" ).tabs();
  });
</script>
<script type="text/javascript">
	function checkUpdate() {
		obj = document.getElementById("returnCode");
		//if(text == passenger_publish){
		//	alert("更新成功");
		//}
		if(obj.value == '10001'){
			$("#tishi").html("路线创建成功！");
			modifyCss();
			return null;
		}
		if(obj.value == '10002'){
			$("#tishi").html("路线删除成功！");
			modifyCss();
			return null;
		}
		if(obj.value == '10003'){
			$("#tishi").html("路线取消成功！");
			modifyCss();
			return null;
		}
		return null;
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

<title>我的行程</title>
<style>
.btn{padding:0;width:100%;text-align:left}
.btn-primary{color:#000;background:none;border:none;width: 100%;text-align: left;}
.btn-primary:hover{color:#000;background:none;border:none}
.btn-primary.focus, .btn-primary:focus{color:#000;background:none;border:none}
.btn-primary:active{background:none;border:none;}
.modal-dialog{width:auto;margin:150px 30px}
</style>
</head>

<body onload="checkUpdate()">
	<div class="page1">
		<html:form method="get" action="/CarpoolPassenger.do"
			styleId="passengerForm">
			<header style="height:50px;">
				<h3 style="text-align: center; color: #000;">我的行程</h3>
				<a class="back" href="javascript:void(0);return;" style="right:6px" onclick="formSubmitByPath(document.forms[0],'<%=userLink %>');doButtonSubmit(document.forms[0], 'backToPage', 'submitField')"></a>
			</header>
			<div class="content1">
			<%
				if ("2".equals(CarpoolPassengerForm.getTab_show())) {
					    tabs_1_show = "class='ui-state-default ui-corner-top'";
						tabs_2_show = "class='ui-state-default ui-corner-top ui-tabs-active ui-state-active'";
					} else {
						tabs_1_show = "class='ui-state-default ui-corner-top ui-tabs-active ui-state-active'";
						tabs_2_show = "class='ui-state-default ui-corner-top'";
					}
			%>
				<div id="tabs"
					class="ui-tabs ui-widget ui-widget-content ui-corner-all">
					<ul
						class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all"
						role="tablist">
						<li
							<%=tabs_1_show%>
							role="tab" tabindex="0" aria-controls="tabs-1"
							aria-labelledby="ui-id-1" aria-selected="true">
							<a href="#tabs-1" 
								class="ui-tabs-anchor" role="presentation"
								tabindex="-1" id="ui-id-1">进行中
							</a>
						</li>
						<li <%=tabs_2_show%> 
							role="tab" tabindex="-1" aria-controls="tabs-2" 
							aria-labelledby="ui-id-2" aria-selected="false">
							<a href="#tabs-2"
								class="ui-tabs-anchor" role="presentation" tabindex="-1"
								id="ui-id-2">已完成
							</a>
						</li>

					</ul>
					<div id="tabs-1" aria-labelledby="ui-id-1"
						class="ui-tabs-panel ui-widget-content ui-corner-bottom"
						role="tabpanel" aria-expanded="true" aria-hidden="false">
						<section class="list" style="margin: 0 auto; display: block;">
							<ul>
								<logic:present name="CarpoolPassengerForm"
									property="passengerRoutelist">
									<c:forEach items="${CarpoolPassengerForm.passengerRoutelist}"
										var="passengerRouteDetail" varStatus="status">
										<%strProceeding++; %>
										
										<c:if test="${passengerRouteDetail.state != '12' && passengerRouteDetail.state != '21' && passengerRouteDetail.state != '22' && passengerRouteDetail.state != '23'}">
										
										<li style="padding: 0; background: #fff">

											<p class="position">
												<font class="icon-qi1 qi"></font>${passengerRouteDetail.start_city
													}${passengerRouteDetail.start_area
												}<span class="icon-to"></span><font class="icon-qi1 zhong"></font>${passengerRouteDetail.end_city
													}${passengerRouteDetail.end_area
												}&nbsp;&nbsp;(${passengerRouteDetail.price }点)
											</p>
											<p class="main">
												出发时间：<font style="color: #0086ff;">${passengerRouteDetail.go_time}
													</font><br>搭乘人数：${passengerRouteDetail.people_count }
												<c:if test="${passengerRouteDetail.pr_memo != null && !(''。equals(passengerRouteDetail.pr_memo))}">
													<br>备注：${passengerRouteDetail.pr_memo }
												</c:if>

												<c:if test="${passengerRouteDetail.state == '0'}">
												<a href="javascript:void(0);return;"
														style="font-size: 14px; margin-left: 5px;background-color: #8d8d8d"
														onClick="if(confirm('是否要删除?')){saveValue('returnCode','10002');saveValue('pr_id',${passengerRouteDetail.pr_id});
														formSubmitByPath(document.forms[0],'<%=passengerLink%>'); doButtonSubmit(document.forms[0], 'deletePassengerRoute','submitField');}">删除</a>
													<font style="float: right; color: #0086ff;margin-right:5px">等待车主接单</font>
												</c:if>

												<c:if test="${passengerRouteDetail.state == '10'}">
													<em></em>
													<a href="javascript:void(0);return;"
														style="font-size: 14px; margin-left: 5px;background-color: #8d8d8d"
														onClick="saveValue('returnCode','10003');saveValue('pr_id',${passengerRouteDetail.pr_id});
														formSubmitByPath(document.forms[0],'<%=passengerLink%>'); doButtonSubmit(document.forms[0], 'cancelPassengerRoute','submitField');">取消</a>
													<a href="javascript:void(0);return;" style="font-size: 14px"
														onClick="saveValue('returnCode','0');saveValue('dr_id',${passengerRouteDetail.carpoolUser.dr_id});saveValue('pr_id',${passengerRouteDetail.pr_id});saveValue('state','11');
														formSubmitByPath(document.forms[0],'<%=passengerLink%>');doButtonSubmit(document.forms[0], 'updateRouteStatus','submitField');">我已上车</a>
												</c:if>
												<c:if test="${passengerRouteDetail.state == '11'}">
													<a href="javascript:void(0);return;" style="font-size: 14px"
														onClick="saveValue('returnCode','0');saveValue('dr_id',${passengerRouteDetail.carpoolUser.dr_id});saveValue('pr_id',${passengerRouteDetail.pr_id});saveValue('state','12');
														formSubmitByPath(document.forms[0],'<%=passengerLink%>');doButtonSubmit(document.forms[0], 'updateRouteStatus','submitField');">行程结束</a>
												</c:if>

											</p> 
											<c:if test="${passengerRouteDetail.state != '0'}">
											<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal_<%=strProceeding %>">
													<p class="qiangdan">
														<span
															style="background-color: #66ca6b; padding: 8px 5px; color: #fff;">抢单人</span><span
															style="padding: 0px 6px; line-height: 36px">${passengerRouteDetail.carpoolUser.cu_nick}</span><font
															class="ck">车主</font><font style="line-height: 36px">${passengerRouteDetail.carpoolUser.car_type}</font>
													</p>
											</button>
											</c:if> 
											<!-- 对话框新 -->					
								            <div class="modal fade" id="myModal_<%=strProceeding %>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
												<div class="modal-dialog" role="document">
												    <div class="modal-content">
												      <div class="modal-header">
												        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
												        <h4 class="modal-title" id="myModalLabel"  style="color:#ff9c00">车主</h4>
												      </div>
												      <div class="modal-body">
													<p style="font-size: 20px" class="modal-name">
														${passengerRouteDetail.carpoolUser.cu_nick}<font
															style="position: absolute; right: 10px; font-size: 14px;">成功接单${passengerRouteDetail.carpoolUser.d_success_count}次</font>
													</p>
													<span style="font-size: 14px;">

														<p>车辆类型：${passengerRouteDetail.carpoolUser.car_type}</p>
														<p>车 牌 号：${passengerRouteDetail.carpoolUser.car_num}</p>
														<p>
															联系电话：<a href="tel:${passengerRouteDetail.carpoolUser.cu_tel}"
																style="color: #0086ff">${passengerRouteDetail.carpoolUser.cu_tel}</a>
														</p>
														<p>注册日期：${passengerRouteDetail.carpoolUser.insert_time}
														</p>
													</span>
											      </div>
											                
											    </div>
											  </div>
										</div>        
										<!--对话框endnew--> 		
										</li>
										</c:if>
									</c:forEach>
								</logic:present>
								<logic:notPresent  name="CarpoolPassengerForm" property="passengerRoutelist">
									<div class="none" style="vertical-align:middle;line-height:30px;text-align:center;font-size:16px;color:#666;margin-top:150px"><img src="<%=strPath%>/images/search.png" width="32" height="32" style="margin:0 auto;display:block;margin-bottom:10px">您没有进行中的行程</div>
								</logic:notPresent>
							</ul>
						</section>
					</div>
					<div id="tabs-2" aria-labelledby="ui-id-2"
						class="ui-tabs-panel ui-widget-content ui-corner-bottom"
						role="tabpanel" aria-expanded="false" aria-hidden="true">
						<section class="list" style="margin: 0 auto; display: block">
							<ul>
								<logic:present name="CarpoolPassengerForm"
									property="passengerRoutelist">
									<c:forEach items="${CarpoolPassengerForm.passengerRoutelist}"
										var="passengerRouteDetail" varStatus="status">
										<%strComplete++; %>
										<c:if test="${passengerRouteDetail.state == '12' || passengerRouteDetail.state == '21' || passengerRouteDetail.state == '22' || passengerRouteDetail.state == '23'}">
											<li style="padding: 0; background: #fff">
	
												<p class="position">
													<font class="icon-qi1 qi"></font>${passengerRouteDetail.start_city
													}${passengerRouteDetail.start_area
													}<span class="icon-to"></span><font class="icon-qi1 zhong"></font>${passengerRouteDetail.end_city
													}${passengerRouteDetail.end_area}&nbsp;&nbsp;(${passengerRouteDetail.price }点)
												</p>
												<p class="main">
													出发时间：<font style="color: #0086ff;">${passengerRouteDetail.go_time
														}</font><br>搭乘人数：${passengerRouteDetail.people_count }
														
													<c:if test="${passengerRouteDetail.pr_memo != null && !(''。equals(passengerRouteDetail.pr_memo))}">
														<br>备注：${passengerRouteDetail.pr_memo }
													</c:if>
													<c:if test="${passengerRouteDetail.state == '12'}">
														<font style="float: right; color: #b7b7b7">已完成</font>
													</c:if>
													<c:if test="${passengerRouteDetail.state == '21' || passengerRouteDetail.state == '22'}">
														<font style="float: right; color: #b7b7b7">已取消</font>
													</c:if>
													<c:if test="${passengerRouteDetail.state == '23'}">
														<font style="float: right; color: #b7b7b7">已过期</font>
													</c:if>
												</p>
												<c:if test="${passengerRouteDetail.state != '23'}">
													<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal<%=strComplete %>">
														<p class="qiangdan">
															<span
																style="background-color: #cdcdcd; padding: 8px 5px; color: #fff;">抢单人</span><span
																style="padding: 0px 6px; line-height: 36px">${passengerRouteDetail.carpoolUser.cu_nick}</span><font
																class="ck">车主</font><font style="line-height: 36px">${passengerRouteDetail.carpoolUser.car_type}</font>
														</p>
													</button>
												</c:if>	
												<!-- 对话框新 -->	
									            <div class="modal fade" id="myModal<%=strComplete %>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
												  <div class="modal-dialog" role="document">
												    <div class="modal-content">
												      <div class="modal-header">
												        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
												        <h4 class="modal-title" id="myModalLabel"  style="color:#ff9c00">车主</h4>
												      </div>
												      <div class="modal-body">
														<p style="font-size: 20px" class="modal-name">
														${passengerRouteDetail.carpoolUser.cu_nick}<font
															style="position: absolute; right: 10px; font-size: 14px;">成功接单${passengerRouteDetail.carpoolUser.d_success_count}次</font>
													</p>
													<span style="font-size: 14px;">

														<p>车辆类型：${passengerRouteDetail.carpoolUser.car_type}</p>
														<p>车 牌 号：${passengerRouteDetail.carpoolUser.car_num}</p>
														<p>
															联系电话：<a href="tel:${passengerRouteDetail.carpoolUser.cu_tel}"
																style="color: #0086ff">${passengerRouteDetail.carpoolUser.cu_tel}</a>
														</p>
														<p>注册日期：${passengerRouteDetail.carpoolUser.insert_time}
														</p>
													</span>
											      </div>
											                
											    </div>
											  </div>
											</div>        
												 
											<!--对话框endnew--> 		
											</li>
										
										</c:if>
									</c:forEach>
								</logic:present>
								<logic:notPresent  name="CarpoolPassengerForm" property="passengerRoutelist">
									<div class="none" style="vertical-align:middle;line-height:30px;text-align:center;font-size:16px;color:#666;margin-top:150px"><img src="<%=strPath%>/images/search.png" width="32" height="32" style="margin:0 auto;display:block;margin-bottom:10px">您还没有已完成的行程</div>
								</logic:notPresent>
							</ul>
						</section>
					</div>
				</div>
			</div>
			<div class="box" style="display:none;">
				<img src="<%=strPath%>/images/notification.png"  width="20" height="20" style="position:absolute;left:10px;top:13px">
				<div class="txt" style="text-align:center;font-size:14px" id="tishi"></div>
			</div>
			<html:hidden property="submitField" styleId="submitField" value="" />
			<html:hidden property="cu_id" styleId="cu_id" />
			<html:hidden property="dr_id" styleId="dr_id" />
			<html:hidden property="pr_id" styleId="pr_id" />
			<html:hidden property="state" styleId="state" />
			<html:hidden property="p_longitude" styleId="p_longitude" />
			<html:hidden property="p_latitude" styleId="p_latitude" />
			<html:hidden property="dp_flag" styleId="dp_flag" />
			<html:hidden property="requestBy" styleId="requestBy" value="h5" />
			<html:hidden property="screenId" styleId="screenId" />
			<html:hidden property="returnCode" styleId="returnCode" />
			
		</html:form>
	</div>

</body>
</html>
