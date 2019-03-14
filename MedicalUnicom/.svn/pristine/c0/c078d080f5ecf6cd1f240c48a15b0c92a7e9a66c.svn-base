$(function(){
	var pid="";
	var link=window.location.href;
	
	if(pid == "" ){
		pid=getCookie("pid");
		if(pid == ""){
			var CookieID=link.substring(link.lastIndexOf("="),+1,link.length);
			pid=CookieID;
		}
	}
	
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
		url:"/MedicalUnicom/Doctor.do?doGetDoctorInfoById",
		data:{
			doctorId:pid,
		},
		aync:false,
		dataType:"json",
		success:function(result){
			$(".usrName").text(result.name);
			$(".usrImg").attr("src",result.certificatePic)
		}
	})
	
	$.ajax({
		type: "get",
		url: "/MedicalUnicom/Appoint.do?doGetAppointListByDoctor",
		data: {
			doctorId:pid
		},
		aync: false,
		dataType:"json",
		success:function(result){
			var DTO=result.patientClientDTO;
			if(DTO == null){
				return false
			}
			var List="";
			for(i=0;i<DTO.length;i++){
				var date=new Date();
				
				var name="";
				if(!DTO[i].name ){
					name=DTO[i].name;
				}else{
					name="Name";
				}
				var birth=DTO[i].birth;
				if(birth != null){
					birth=date.getFullYear()-parseInt(birth.substring(0,4))
				}else{
					birth="";
				};
				var sex=DTO[i].sex;
				if(sex != null){
					if(sex == 0){
						sex= "男";
					}else if(sex == 1){
						sex= "女";
					};
				}else{
					sex ="";
				};
				var patientPic=DTO[i].patientPic;
				var symptonName=DTO[i].symptonName;

				var appointTime=DTO[i].appointTime;
				var createTime=DTO[i].createTime;
				var Time=createTime;

				var date1="";
				if(appointTime != null){
					Time=appointTime;
					date1=appointTime.replace(/-/g,"/")+":00";  //开始时间  
				}else{
					Time=createTime;
					date1=createTime.replace(/-/g,"/")+":00";  //开始时间  
				}

			    var date2= new Date(date1);    //结束时间  
			    var date3="";   //时间差的毫秒数
				var delay="";
			    
				if(new Date().getTime()> date2.getTime() ){
					delay=-1;
					date3=new Date().getTime() - date2.getTime(); 
				}else if(new Date().getTime() < date2.getTime() ){
					delay=0;
					date3=date2.getTime() - new Date(date1).getTime(); 
				}

				//计算出天数  
				var days=Math.floor(date3/(24*3600*1000));
				
				//计算出小时数  
				var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数  
				var hours=Math.floor(leave1/(3600*1000));
				var H=Math.floor(date3/3600/1000);

				//计算相差分钟数  
				var leave2=leave1%(3600*1000);       //计算小时数后剩余的毫秒数  
				var minutes=Math.floor(leave2/(60*1000));
				var M=Math.floor(date3/60/1000);

				
				var appointId=DTO[i].appointId;
				var inquiryStatus=parseInt(DTO[i].inquiryStatus);
				var recordsType=parseInt(DTO[i].recordsType);
				var recordsId=DTO[i].recordsId;
				var depName=DTO[i].depName;
				var payStatus=DTO[i].payStatus;
				var tel=DTO[i].tel;

				var InqDiv="";
				
				if(payStatus == 0){
					InqDiv="<p class='Start'><span class='accept'><a href='#none'>确认就诊</a></span><span class='sorry'><a href='#none'>残忍拒绝</a></span></p>";
					
				}else if(payStatus == 2 ){
					InqDiv="<p class='PaidOver'>支付超时</p>";
					
				}else if(payStatus == 3){
					InqDiv="<p class='Refund'>已退款</p>";
					
				}else if(payStatus == 1 && inquiryStatus == 1 && recordsType == 0){
					
					if(delay == 0 && hours > 24){
						InqDiv="<p class='Count'><span class='state_icon'></span>还有"+days+"天"+H+"时</p>";
					}else if(delay==0 && hours < 24 && hours > 0){
						InqDiv="<p class='Count'><span class='state_icon'></span>还有"+H+"时"+M+"时</p>";
					}else if(delay==0 && M < 60){
						InqDiv="<p class='Cam'><span class='state_icon'></span><span class='Start'><a href=tel:'"+tel+"'>开始就诊</a></span></p>";
					}else if(delay==-1 && M > 60){
						InqDiv="<p class='NotResult'>未出就诊结果</p>";
					};
					
				}else if(payStatus == 1 && inquiryStatus == 1 && recordsType == 1){
					InqDiv="<p class='Tel'><span class='callPhone'><a href=tel:'"+tel+"'>拨打电话</a></span><span class='callSorry'><a href='#none'>取消</a></span></p>";
				}else if(payStatus == 1 && inquiryStatus == 2 && recordsType == 0){
					if(delay==0 && M < 60){
						InqDiv="<p class='Cam'><span class='state_icon'></span><span class='Start'><a href=tel:'"+tel+"'>开始就诊</a></span></p>";
					}else if(delay== -1 && M > 60){
						InqDiv="<p class='NotResult'>未出就诊结果</p>";
					}
				}else if(payStatus == 1 && inquiryStatus == 2 && recordsType == 1){
					InqDiv="<p class='NotResult'>未出就诊结果</p>";
				}else if(payStatus == 1 && inquiryStatus == 3 ){
					InqDiv="<p class='Over'>问诊结束</p>";
				}else if(payStatus == 1 && inquiryStatus == 4 ){
					InqDiv="<p class='DocReject'>医生拒绝</p>";
				}else if(payStatus == 1 && inquiryStatus == 5 ){
					InqDiv="<p class='Cancel'><span class='Pat_yes'><a href='#none'>同意取消</a></span><span class='Doc_no'><a href='#none'>拒绝取消</a></span></p>";
				}else if(payStatus == 1 && inquiryStatus == 6 ){
					InqDiv="<p class='AgreeCancel'>医生同意取消</p>";
				}else if(payStatus == 1 && inquiryStatus == 7 ){
					InqDiv="<p class='Disagree'>医生拒绝取消</p>";
				}else if(payStatus == 1 && inquiryStatus == 8 ){
					InqDiv="<p class='Cancel'>医生取消</p>";
				}
				
				var tel=DTO[i].tel;
				var createTime=DTO[i].createTime;
				List+="<li data='"+recordsId+"'><div class='info' data='"+payStatus+"'><strong class='UserImg'><img src='"+patientPic+"' /></strong><ul><li class='frt'><strong class='name'>"+name+"</strong> <span>"+depName+"</span></li><li><strong class='Sex'>性别: <span>"+sex+"</span></strong><strong class='Age'>年龄: <span>"+birth+"</span></strong></li><li>症状:"+symptonName+"</li></div><div class='TimeList'><div class='state clearfix'><strong class='timer'>预约时间: <span class='datetime'>"+Time+"</span></strong>"+InqDiv+"</div></div></li>";
			}
			
			$("#List").html("");
			$("#List").append(List);
			
			$(".usrImg").on("error",function(){
				$(this).attr("src","img/defaultpatient.png")
			})
			
			$(".UserImg img").on("error",function(){
				$(this).attr("src","img/defaultpatient.png")
			})
			
			$(document).scroll(function(){
				setCookie("scroll",$(document).scrollTop());
			})
			$(document).scrollTop(getCookie("scroll"));

			/*
			{"patientClientDTO":
			
			"patientId":1,   //ID
			"name":"张三",  //名字
			"birth":"2016-08-30", 	 //年龄
			"sex":"1",   //性别
			"patientPic":"http://192.168.11.120:8080/MedicalUnicom/imag_head/1p/1475032256816.jpg",  
			"symptonName":"发烧",   //就诊症状
			"appointTime":"2016-10-01 03:00"
			"appointId":123456837, //就诊ID
			"recordsType":"1",   //就诊方式 
			"recordsId":217, //就诊方式 ID
			"depName":"儿科", //科室
			"inquiryStatus":"1", //就诊状态
			"tel":"15111111111", //手机
			"createTime":"2016-09-29 10:15" //创建日期
			}
			*/
			$(".info").click(function(){
				setCookie("payStatus",$(this).attr("data"));
				window.location.href="Doc_Inquirydetail.html?date="+$(this).parent("li").attr("data");
			});
			
			var recordsId_="";

			$(".accept").click(function(){
				recordsId_=$(this).parent().parent().parent().parent("li").attr("data");
				$.ajax({
					type:"post",
					url:"/MedicalUnicom/Records.do?doUpdateRecordsInfo",
					data:{
						recordsId:recordsId_,
						inquiryStatus:1
					},
					aync:false,
					dataType:"json",
					success:function(result){
						if(result.returnCode == 0){
							alert("确认就诊");
							window.location.reload();
						}
					},
				})
				
			});
			$(".sorry").click(function(){
				recordsId_=$(this).parent().parent().parent().parent("li").attr("data");
				$.ajax({
					type:"post",
					url:"/MedicalUnicom/Records.do?doUpdateRecordsInfo",
					data:{
						recordsId:recordsId_,
						inquiryStatus:4
					},
					aync:false,
					dataType:"json",
					success:function(result){
						if(result.returnCode == 0){
							alert("取消就诊");
							window.location.reload();
						}
					},
				})
				
			});
			$(".callSorry").click(function(){
				recordsId_=$(this).parent().parent().parent().parent("li").attr("data");
				$.ajax({
					type:"post",
					url:"/MedicalUnicom/Records.do?doUpdateRecordsInfo",
					data:{
						recordsId:recordsId_,
						inquiryStatus:8
					},
					aync:false,
					dataType:"json",
					success:function(result){
						if(result.returnCode == 0){
							alert("取消就诊");
							window.location.reload();
						}
					},
				})
				
			});
			$(".callPhone").click(function(){
				recordsId_=$(this).parent().parent().parent().parent("li").attr("data");
				$.ajax({
					type:"post",
					url:"/MedicalUnicom/Records.do?doUpdateRecordsInfo",
					data:{
						recordsId:recordsId_,
						inquiryStatus:2
					},
					aync:false,
					dataType:"json",
					success:function(result){
						if(result.returnCode == 0){
							window.location.reload();
						}
					},
				})
				
			});
			$(".Pat_yes").click(function(){
				recordsId_=$(this).parent().parent().parent().parent("li").attr("data");
				$.ajax({
					type:"post",
					url:"/MedicalUnicom/Records.do?doUpdateRecordsInfo",
					data:{
						recordsId:recordsId_,
						inquiryStatus:6
					},
					aync:false,
					dataType:"json",
					success:function(result){
						if(result.returnCode == 0){
							window.location.reload();
						}
					},
				})
				
			});
			$(".Doc_no").click(function(){
				recordsId_=$(this).parent().parent().parent().parent("li").attr("data");
				$.ajax({
					type:"post",
					url:"/MedicalUnicom/Records.do?doUpdateRecordsInfo",
					data:{
						recordsId:recordsId_,
						inquiryStatus:7
					},
					aync:false,
					dataType:"json",
					success:function(result){
						if(result.returnCode == 0){
							window.location.reload();
						}
					},
				})
				
			});
		},
	})
	
	$(window).on("scroll",function(){
		if($(window).scrollTop() > 0){
			$(".NavMenu").addClass("Fix");
		}else if($(window).scrollTop() == 0){
			$(".NavMenu").removeClass("Fix");
		}
		
	})
})