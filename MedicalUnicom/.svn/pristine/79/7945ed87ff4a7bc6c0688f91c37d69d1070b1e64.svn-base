$(function(){
	var pid=getCookie("pid");
	var recordsId=window.location.href.substring(window.location.href.lastIndexOf("=")+1,window.location.href.length)
	
	function getCookie(name){
		
	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	 
	    if(arr=document.cookie.match(reg))
	 
	        return (arr[2]);
	    else
	        return null;
	};
	
	function setCookie(name,value){
	    /*
	    var Days = 30;
	    var exp = new Date();
	    exp.setTime(exp.getTime() + Days*24*60*60*1000);*/
	    document.cookie = name + "="+ escape (value) ;/*+ ";expires=" + exp.toGMTString()*/
	}
	
	$.ajax({
		type:"get",
		url:"/MedicalUnicom/Records.do?doGetAppointDetail",
		data:{
			patientId:pid,
			recordsId:recordsId
		},
		aync:false,
		dataType:"json",
		success:function(result){
			var date=new Date();
			var name=result.name;
			var doctorId=result.doctorId;
			if(name !=null){
				name=result.name;
			}else{
				name="Name";
			}
			var positional=result.positional;
			var headPic=result.headPic;
			
			var healDisease=result.healDisease;
			var dcName=result.dcName;
			var departmentName=result.departmentName;
			var hospitalName=result.hospitalName;
			var recordsId=result.recordsId;
			var inquiryStatus=result.inquiryStatus;
			
			var appointTime=result.appointTime;
			var symptomName=result.symptomName;
			var symptonDescribe=result.symptonDescribe;
			if(symptonDescribe == null){
				symptonDescribe="";
			}
			var createTime=result.createTime;
			var recordsType=result.recordsType;
			var payStatus=getCookie("payStatus");
			
			var commentGrade="";
			if(result.commentGrade != null){
				commentGrade="评分: <span>"+result.commentGrade.toFixed(1)+"</span>";
			}else{
				commentGrade="暂无评分";
			}
			
			var analysis="";
			if(result.analysis == null || result.analysis == ""){
				analysis="<p name='Analysis' class='DetailInfoTXT'><span>病情分析:</span> <strong>暂未填写</strong></p>";
			}else{
				analysis="<p name='Analysis' class='DetailInfoTXT'><span>病情分析:</span> <br/>"+result.analysis+"</p>";
			}
			
			var guidance="";
			if(result.guidance == null || result.guidance == ""){
				guidance="<p name='analysis' class='DetailInfoTXT'><span>指导意见:</span> <strong>暂未填写</strong></p>";
			}else{
				guidance="<p name='analysis' class='DetailInfoTXT'><span>指导意见:</span> <br/>"+result.guidance+"</p>";
			}
			
			var upFlag=result.upFlag;
			var scheduleId=result.scheduleId;
			var commentFlag=result.commentFlag;
			var tel=getCookie("TelNum");
			var Time=createTime;

			var date1="";
			if(appointTime != null){
				Time=appointTime+" ~ "+appointTime.substring(11,16).replace("00","59");
				date1=appointTime.replace(/-/g,"/")+":00";  //开始时间  
			}else{
				Time=createTime;
				date1=createTime.replace(/-/g,"/")+":00";  //开始时间  
			}

		    var date2= new Date(date1);  //设置日期时间
		    
		    var date3="";   //时间差的毫秒数
			var delay="";  // 未来过去判断 0=未来 ， -1=过去
			
			if(new Date().getTime() > date2.getTime() ){
				delay=-1;
				date3=new Date().getTime()-date2.getTime(); 
				
			}else if(new Date().getTime() < date2.getTime() ){
				delay=0;
				date3=date2.getTime()-new Date().getTime();

			}

			//计算出天数  
			var days=Math.floor(date3/(24*3600*1000)); // 距离问诊日期到就诊日期间的  天数

			//计算出小时数  
			var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数  
			var hours=Math.floor(leave1/(3600*1000));// 距离问诊时间小时
			var H=Math.floor(date3/3600/1000); // 距离问诊日期到就诊日期间的 小时

			//计算相差分钟数  
			var leave2=leave1%(3600*1000);       //计算小时数后剩余的毫秒数  
			var minutes=Math.floor(leave2/(60*1000)); // 距离问诊时间分钟
			var M=Math.floor(date3/60/1000); // 距离问诊日期到就诊日期间的 分钟
			
			var InqDiv="";

			if( payStatus == 0){
				InqDiv="<p class='Notpaid'>未支付</p>";
				
			}else if(payStatus == 2){
				InqDiv="<p class='PaidOver'>支付超时</p>";
				
			}else if(payStatus == 3){
				InqDiv="<p class='Refund'>已退款</p>";
				
			}else if(payStatus == 1 && inquiryStatus == 0 && recordsType == 0){
				InqDiv="<p class='Wait'>等待医生确认</p>";

			}else if(payStatus == 1 && inquiryStatus == 0 && recordsType == 1){
				InqDiv="<p class='Wait'>等待医生确认</p>";
				
			}else if(payStatus == 1 && inquiryStatus == 1 && recordsType == 0){
				
				if(delay == 0 && H > 24){
					InqDiv="<p class='Count'><span class='state_icon'></span>还有"+days+"天"+hours+"时</p>";
				}else if(delay==0 && H < 24 && H >= 1){
					InqDiv="<p class='Count'><span class='state_icon'></span>还有"+hours+"时"+minutes+"分钟</p>";
				}else if(delay==0 && M < 60){
					
					InqDiv="<p class='Cam'><span class='state_icon'></span><span class='Start'><a href='callVideo.html'>开始就诊</a></span></p>";
				}else if(delay==-1 && M > 60){
					InqDiv="<p class='NotResult'>未出就诊结果</p>";
				};
			}else if(payStatus == 1 && inquiryStatus == 1 && recordsType == 1){
			 		InqDiv="<p class='Success'>预约成功</p>"
				 	
			}else if(payStatus == 1 && inquiryStatus == 2 ){
				InqDiv="<p class='NotResult'>未出就诊结果</p>";
			}else if(payStatus == 1 && inquiryStatus == 3 ){
				InqDiv="<p class='Over'>问诊结束</p>";
			}else if(payStatus == 1 && inquiryStatus == 4 ){
				InqDiv="<p class='DocReject'>医生拒绝</p>";
			}else if(payStatus == 1 && inquiryStatus == 5 ){
				InqDiv="<p class='WaitCancel'>等待医生同意取消</p>";
			}else if(payStatus == 1 && inquiryStatus == 6 ){
				InqDiv="<p class='AgreeCancel'>医生同意取消</p>";
			}else if(payStatus == 1 && inquiryStatus == 7 ){
				InqDiv="<p class='Disagree'>医生拒绝取消</p>";
			}else if(payStatus == 1 && inquiryStatus == 8 ){
				InqDiv="<p class='Cancel'>医生取消</p>";
			}
			
			if(payStatus ==0 && payStatus == 2 && payStatus == 3){
				$(".subMit_btn").hide()
			}else{
				if( payStatus == 1 && inquiryStatus == 0 ){
					$(".subMit_btn").show();
					$(".subMit_btn .btnGrad").hide();
					$(".subMit_btn .btnCancel").show();
				}else if(payStatus == 1 && inquiryStatus == 1 && recordsType == 0 && hours > 1){
					$(".subMit_btn").show();
					$(".subMit_btn .btnGrad").hide();
					$(".subMit_btn .btnCancel").show();
				}else if(payStatus == 1 && inquiryStatus == 1 && recordsType == 1 && hours > 1){
					$(".subMit_btn").show();
					$(".subMit_btn .btnGrad").hide();
					$(".subMit_btn .btnCancel").show();
				}else if(payStatus == 1 && inquiryStatus == 3){
					$(".subMit_btn").show();
					$(".subMit_btn .btnCancel").hide();
					$(".subMit_btn .btnGrad").show();
				}else if(payStatus == 1 && inquiryStatus == 7){
					$(".subMit_btn").show();
					$(".subMit_btn .btnGrad").hide();
					$(".subMit_btn .btnCancel").show();
				}
			}
			
			var Grad="";
			
			Grad="<ul id='point'><li><span></span></li><li><span></span></li><li><span></span></li><li><span></span></li><li><span></span></li></ul><p>"+commentGrade+"</p>"
			
			var content=$(".InquiryDetail").html();
			
			var Item=new Array();
		
			Item[0]=content;
			
			Item[0]=Item[0].replace("{$name}",name);
			Item[0]=Item[0].replace("{$positional}",positional);
			Item[0]=Item[0].replace("{$headPic}",headPic);
			Item[0]=Item[0].replace("{$healDisease}",healDisease);
			Item[0]=Item[0].replace("{$dcName}",dcName);
			Item[0]=Item[0].replace("{$departmentName}",departmentName);
			Item[0]=Item[0].replace("{$Time}",Time);
			Item[0]=Item[0].replace("{$symptomName}",symptomName);
			Item[0]=Item[0].replace("{$analysis}",analysis);
			Item[0]=Item[0].replace("{$guidance}",guidance);
			Item[0]=Item[0].replace("{$symptonDescribe}",symptonDescribe.replace(/\n/g,"<br/>"));
			Item[0]=Item[0].replace("{$commentGrade}",commentGrade);
			Item[0]=Item[0].replace("{$InqDiv}",InqDiv)
			Item[0]=Item[0].replace("{$Grad}",Grad)
			Item[0]=Item[0].replace("{$doctorId}",doctorId)
			Item[0]=Item[0].replace("{$hospitalName}",hospitalName)

			$(".InquiryDetail").html("");
			$(".InquiryDetail").html(Item);
			
			$(".UserImg img").on("error",function(){
				$(this).attr("src","img/defaultpatient.png")
			})
			
			for(i=0;i<parseInt(result.commentGrade);i++){
				$("#point li")[i].className="on";
			}

			$(".subMit_btn .btnCancel").click(function(){
				$("#aside").show();
				$("#buttonArea").show();
				$("#buttonArea").animate({"bottom":"0"},100);
			})
			
			$("#confirm").click(function(){
				window.location.href="Pat_CancelForm.html?recordsId="+recordsId;
			})
			
			$("#cancel").click(function(){
				$("#buttonArea").animate({"bottom":"-224px"});
				$("#buttonArea").hide();
				$("#aside").hide();
			});
			
			$(".subMit_btn .btnGrad").click(function(){
				window.location.href="gradForm.html?"+"recordsId="+recordsId+"&doctorId="+doctorId
			})

		}
	})

})