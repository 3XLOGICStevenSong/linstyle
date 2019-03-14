<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%
    String strPath = (String) request.getContextPath();
    String driverLink = (String) response.encodeURL(strPath + "/CarpoolDriver.do");
    String passengerLink = (String) response.encodeURL(strPath + "/CarpoolPassenger.do");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport" />
<link rel="stylesheet" href="<%=strPath%>/css/css.css">
<link rel="stylesheet" type="text/css" href="<%=strPath%>/css/box.css"/>
<script src="<%=strPath%>/js/common/common.js"></script>
<script src="<%=strPath%>/js/jquery-1.11.1.min.js"></script>


<style type="text/css">
#preview_1 {width:125px;height:88px;overflow:hidden;}
#preview_2 {width:125px;height:88px;overflow:hidden;}
#preview_3 {width:280px;height:357px;overflow:hidden;}
#imghead_1 {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
#imghead_2 {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
#imghead_3 {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
</style>
  <script type="text/javascript">
 function back(){
  var start_city=document.getElementById("start_city").value;
  var end_city=document.getElementById("end_city").value;
  var begin_time=document.getElementById("begin_time").value;
  var pend_time=document.getElementById("pend_time").value;
  var charter_flg=document.getElementById("charter_flg").value;
  saveValue('start_city',start_city,'end_city',end_city,'begin_time',begin_time,'pend_time',pend_time,'charter_flg',charter_flg);
  formSubmitByPath(document.forms[0],'<%=passengerLink %>');
  doButtonSubmit(document.forms[0], 'backToVerfDriver', 'submitField');

	 }
 function modifyCss(){
		$(".box").css({
				left:($("body").width()-$(".box").width())/2-20+"px",
				top:($(window).height()-$(".box").height())/2+$(window).scrollTop()+"px",
				display:"block"
			}).show(100).delay(1000).hide(100);
	}

	function check() {
		
		var driving_license_pic = document.getElementById("driving_license_pic").value;
		 var car_license_pic =document.getElementById("car_license_pic").value;
		  var people_license =document.getElementById("people_license").value;		   
		    if(driving_license_pic==''||driving_license_pic==null){

				$("#tishi").html("请上传驾驶证照片！");
				modifyCss();
				return;
		  	}else if(car_license_pic==''||car_license_pic==null){
				$("#tishi").html("请上传行驶证照片！");
				modifyCss();
		   		return;	
		   	}
		   	else if(people_license==''||people_license==null){
				$("#tishi").html("请上传驾驶人手持行驶证照片！");
				modifyCss();
		   		return;	
		   	}
		   else{
		 		doButtonSubmit(document.forms[0], 'driverVerifInfo', 'submitField');
			}
		}
		
</script>

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
});
</script>
<script type="text/javascript">
 
	//图片上传预览    IE是用了滤镜。
	function previewImage(file, paramDiv, paramImg)
    {
      	var MAXWIDTH  = 125; 
     	var MAXHEIGHT = 88;
        var div = document.getElementById(paramDiv);
        var str = '<img id='+paramImg +'>';
        if (file.files && file.files[0])
        {
            div.innerHTML = str;
            var img = document.getElementById(paramImg);
            img.onload = function(){
	            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
	            img.width  =  rect.width;
	            img.height =  rect.height;
				//   img.style.marginLeft = rect.left+'px';
	            img.style.marginTop = rect.top+'px';
	        }
        var reader = new FileReader();
        reader.onload = function(evt){img.src = evt.target.result;}
        reader.readAsDataURL(file.files[0]);
	    }
	    else //兼容IE
	    {
		    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
		    file.select();
		    var src = document.selection.createRange().text;
		    div.innerHTML = '<img id=paramImg>';
		    var img = document.getElementById('paramImg');
		    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
		    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
		    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
		    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
	    }
  	}
    function clacImgZoomParam( maxWidth, maxHeight, width, height ){
        
        var param = {top:0, left:0, width:width, height:height};
        if( width>maxWidth || height>maxHeight )
        {
            rateWidth = width / maxWidth;
            rateHeight = height / maxHeight;
             
            if( rateWidth > rateHeight )
            {
                param.width =  maxWidth;
                param.height = Math.round(height / rateWidth);
            }else
            {
                param.width = Math.round(width / rateHeight);
                param.height = maxHeight;
            }
        }
         
        param.left = Math.round((maxWidth - param.width) / 2);
        param.top = Math.round((maxHeight - param.height) / 2);
        return param;
    }
</script>   

<title>车主认证</title>

</head>

<body>
			<div class="page1">
				<div data-role="header" data-position="fixed" id="update_p_user"
					data-tap-toggle="false">
					<header>
						<h3 style="text-align: center; color: #000;">车主认证</h3><a class="back" href="javascript:void(0);" onclick="doButtonSubmit(document.forms[0], 'backToVerfDriver', 'submitField');"></a>
					</header>
				</div>
					<div class="content1">
					<html:form action="/CarpoolDriver.do" method="POST" enctype="multipart/form-data" styleId="verifyForm">
						<section class="title"><span class="two">填写车主资料<font class="t-one">1</font></span><span class="one">上传证件照片<font class="t-two">2</font></span>
						</section>
	
						<section class="main">
							<h4 style="font-size: 16px">你的照片仅用于审核，我们将为你严格保密</h4>
							<div style="width: 50%; float: left">
								<section class="pic1">
									<p>上传驾驶证照片</p>
									<span class="btn-upload">
										<a href="javascript:void();" class="btn btn-primary radius" id="preview_1">
											<img id="imghead_1" src="images/jishizheng.jpg" width="125" height="88">
										</a>
										<input type="file" multiple name="driving_license_pic" id="driving_license_pic" class="input-file" onchange="previewImage(this, 'preview_1', 'imghead_1')">
									</span>
								</section>
							</div>
							<div style="width: 50%; float: right">
								<section class="pic2">
									<p>上传行驶证照片</p>
									<span class="btn-upload">
										<a href="javascript:void();" class="btn btn-primary radius" id="preview_2">
											<img id="imghead_2" width="125" height="88" border="0" src="images/xingshizheng.jpg">
										</a>
										<input type="file" multiple name="car_license_pic" id="car_license_pic"  class="input-file" onchange="previewImage(this, 'preview_2', 'imghead_2')">
									</span>
								</section>
								
							</div>
							<div>
							<section class="pic3">
								<p>上传驾驶人手持行驶证照片</p>
								<span class="btn-upload" style="height: 100%">
									<a href="javascript:void();" class="btn btn-primary radius" id="preview_3">
										<img id="imghead_3" src="images/pic3.png" width="280" height="357">
									</a> 
									<input type="file" multiple name="people_license" id="people_license" class="input-file" onchange="previewImage(this, 'preview_3', 'imghead_3')">
								</span>
							</section>
							</div>
							<a href="#" class="button1" style="display: block; margin-top: 20px" onclick="doButtonSubmit(document.forms[0], 'backToVerfDriver', 'submitField');"><span
								style="float: left;">上一步</span></a>
							<a href="#" class="button1" style="display: block; margin-top: 20px" onclick="check();"><span style="float: right;">完成</span></a>
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
			<html:hidden property="cu_id" styleId="cu_id" />
			<html:hidden property="cu_name" styleId="cu_name" />
			<html:hidden property="id_number" styleId="id_number" />
			<html:hidden property="car_num" styleId="car_num" />
			<html:hidden property="car_type" styleId="car_type" />
			<html:hidden property="car_seat_num" styleId="car_seat_num" />	
			<html:hidden property="pr_id" styleId="pr_id" />			
			<html:hidden property="requestBy" styleId="requestBy" value="h5"  />
		
		</html:form>
	</div>
</body>
</html>
