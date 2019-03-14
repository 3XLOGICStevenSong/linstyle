$(function(){
	var pid=getCookie("pid");
	var recordsId_=window.location.href.substring(window.location.href.lastIndexOf("=")+1,window.location.href.length);
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
		type: "get",
		url: "/MedicalUnicom/Records.do?doGetAppointDetailByDoctor",
		data: {
			doctorId: pid,
			recordsId: recordsId_
		},
		aync: false,
		dataType:"json",
		success:function(result){
			$(".UserImg img").attr("src",result.patientPic);
			if(result.name != null){
				$(".name").text(result.name)
			}else{
				$(".name").text("Name")
			}
			var dep="<span>"+result.departmentName+"</span>";
			$(".frt").append(dep);
			
			var Sex="";
			if(result.sex != null){
				if(result.sex == "0"){
					Sex="男";
				}else if(result.sex == "1"){
					Sex="女";
				}
			}else{
				Sex="";
			};
			$(".Sex span").text(Sex);
			
			var date=new Date();
			var birth=result.birth;
			if(birth != null){
				birth=date.getFullYear()-parseInt(birth.substring(0,4))
			}else{
				birth="";
			};
			$(".Age span").text(birth);
			$(".Symptom span").text(result.symptomName);
			$(".DetailInfo textarea").text(result.symptonDescribe);
			/*
			1.患者头像 patientPic
			2.患者姓名name
			3.科室departmentName
			4.性别sex
			5.年龄birth yyyy-MM-dd
			6.症状:symptomName
			7.预约时间:appointTime yyyy-MM-dd HH:mm:ss
			8.预约就诊的状态:inquiryStatus:0医生未确认、1预约成功、2未出出诊结果、3问诊结束
			9.就诊结果-病情分析analysis（就诊结束后，医生填写了就诊记过，才会有这个数据）
			10.就诊结果-意见指导 guidance（显示条件同上）
			11:创建时间 createTime
			预约类型 ：recordsType：0：视频1：电话
			12:拨打电话:telNum}
			13.用户填写的备注：symptonDescribe
			*/
			var Time=result.createTime;

			var date1="";
			if(result.appointTime != null){
				Time=result.appointTime;
				date1=result.appointTime.replace(/-/g,"/")+":00";  //开始时间  
			}else{
				Time=result.createTime;
				date1=result.createTime.replace(/-/g,"/")+":00";  //开始时间  
			}

		    var date2= new Date(date1);    //结束时间  
		    var date3="";   //时间差的毫秒数
			var delay="";
		    
			if(new Date().getTime()> date2.getTime() ){
				delay=-1;
				date3=new Date().getTime() - date2.getTime(); 
			}else if(new Date().getTime() < date2.getTime() ){
				delay=0;
				date3=date2.getTime() - new Date().getTime(); 
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

			var appointId=result.appointId;
			var inquiryStatus=result.inquiryStatus;
			var recordsType=result.recordsType;
			var recordsId=result.recordsId;
			var payStatus=getCookie("payStatus");
			var tel=result.tel;
			
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
			
			$(".datetime").text(Time);
			$(".state").append(InqDiv);
			$(".analysis textarea").text(result.analysis);
			$(".guidance textarea").text(result.guidance);
			
			$(".UserImg img").on("error",function(){
				$(this).attr("src","img/defaultpatient.png")
			})
		}
	})

	$(".subMit_btn").click(function(){
		var inquiryStatus_="";
		if($(".analysis textarea").val() !="" && $(".guidance textarea").val() !=""){
			inquiryStatus_=3;
		}else{
			inquiryStatus_=1;
		}
		
		$.ajax({
			type:"post",
			url: "/MedicalUnicom/Records.do?doUpdateRecordsInfo",
			data: {
				doctorId: pid,
				recordsId: recordsId_,
				analysis: $(".analysis textarea").val(),
				guidance: $(".guidance textarea").val(),
				inquiryStatus: inquiryStatus_
			},
			aync:false,
			dataType:"json",
			success:function(result){
				if(result.returnCode == 0){
					alert("提交成功")
					window.location.reload();
				};
			}
		})
		
	})
});