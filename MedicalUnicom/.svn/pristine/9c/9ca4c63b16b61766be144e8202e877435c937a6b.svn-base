$(function(){
	var pid=getCookie("pid");
	var patientId=window.location.href.substring(window.location.href.lastIndexOf("=")+1,window.location.href.length)

	function setCookie(name,value){
	    /*
	    var Days = 30;
	    var exp = new Date();
	    exp.setTime(exp.getTime() + Days*24*60*60*1000);*/
	    document.cookie = name + "="+ escape (value) ;/*+ ";expires=" + exp.toGMTString()*/
	}
	function getCookie(name){
		
	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	 
	    if(arr=document.cookie.match(reg))
	 
	        return (arr[2]);
	    else
	        return null;
	};
	
	
	$.ajax({
		type:"get",
		url:"/MedicalUnicom/Appoint.do?doGetPatientInfoByDoctor",
		data:{
			doctorId:pid,
			patientId:patientId,
		},
		aync:false,
		dataType:"json",
		success:function(result){
			var date=new Date();
			
			$(".UserImg img").attr("arc",result.patientPic);
			$(".name").text(result.name);
			
			var birth=result.birth;
			if(birth != null){
				birth=date.getFullYear()-parseInt(birth.substring(0,4))
			}else{
				birth="";
			};
			$(".Age span").text(birth);
			
			var sex=result.sex;
			if(sex != null){
				if(sex == 0){
					sex= "男";
				}else if(sex == 1){
					sex= "女";
				};
			}else{
				sex ="";
			};
			$(".Sex span").text(sex);
			
			
			var recordsList=result.recordsList;
			var List="";
			for(i=0;i<result.recordsList.length;i++){
				/*
				"recordsId":135,
				"inquiryStatus":"0",
				"appointTime":"2016-09-24 06:00",
				"createTime":"2016-09-21 20:03",
				"recordsType":"0"
				*/
				var recordsId=recordsList[i].recordsId;
				var inquiryStatus=recordsList[i].inquiryStatus;
				var recordsType=recordsList[i].recordsType;
				var appointTime=recordsList[i].appointTime;
				var createTime=recordsList[i].createTime;
				var Time="";
				if(appointTime != null){
					Time=appointTime
				}else{
					Time=createTime
				}
		
				var InqDiv="";

				if(inquiryStatus == 0){
					InqDiv="<p class='delay'>医生未确认</p>";
				}else if(inquiryStatus == 1){
					InqDiv="<p class='Complate'>预约成功</p>";
				}else if(inquiryStatus == 2){
					InqDiv="<p class='Emp'>未填写诊断结果</p>";
				}else if(inquiryStatus == 3){
					InqDiv="<p class='Over'>问诊结束</p>";
				}else if(inquiryStatus == 4){
					InqDiv="<p class='DocReject'>医生拒绝</p>";
				}else if(inquiryStatus == 6){
					InqDiv="<p class='PatCancelAgree'>患者取消</p>";
				}else if(inquiryStatus == 7){
					InqDiv="<p class='DocCancelDisagree'>拒绝取消</p>";
				}else if(inquiryStatus == 8){
					InqDiv="<p class='DocResCancel'>医生取消</p>";
				}

				List+="<li data='"+recordsId+"'><div class='TimeList'><div class='Detail clearfix'><span class='chart_icon'></span>就诊详情 </div><div class='state clearfix'><strong class='timer'>预约时间: <span class='datetime'>"+Time+"</span></strong>"+InqDiv+"</div></div></li>";
			};
			
			$("#List").html("");
			$("#List").html(List);
			
			$(".UserImg img").on("error",function(){
				$(this).attr("src","img/defaultpatient.png")
			})
			
			$("#List li").click(function(){
				window.location.href="Doc_Inquirydetail.html?date="+$(this).attr("data");
			});
			
			$(document).scroll(function(){
				setCookie("scroll",$(document).scrollTop());
			})
			$(document).scrollTop(getCookie("scroll"));
		}
	})
	
})