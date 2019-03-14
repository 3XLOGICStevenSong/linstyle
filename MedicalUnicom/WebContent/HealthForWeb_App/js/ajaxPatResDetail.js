$(function(){
	//var pid=getCookie("pid");
	var doctorId="";
	var patientId="";
	var depId="";
	var depName="";

	var link=window.location.href;
	var param=link.split("&");
	for(i=0;i<param.length;i++){
		if(param[i].indexOf("doctorId") != -1 ){
			doctorId=param[i].substring(param[i].lastIndexOf("=")+1,param[i].length);
		}else if(param[i].indexOf("depId") != -1 ){
			depId=param[i].substring(param[i].lastIndexOf("=")+1,param[i].length);
		}else if(param[i].indexOf("depName") != -1 ){
			depName=decodeURI(param[i].substring(param[i].lastIndexOf("=")+1,param[i].length));
		}else if(param[i].indexOf("patientId") != -1 ){
			patientId=decodeURI(param[i].substring(param[i].lastIndexOf("=")+1,param[i].length));
		}else{
			patientId=getCookie("pid");
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
		url:"/MedicalUnicom/Doctor.do?doGetPackageByDoctorNew", //"/MedicalUnicom/Doctor.do?doGetPackageByDoctorNew"
		data:{
			patientId:patientId,
			doctorId:doctorId
		},
		aync:false,
		dataType:"json",
		success:function(result){
			var doctorId=result.doctorId;
			var name=result.name;
			var positional=result.positional;
			if(result.positional != null){
				positional=result.positional;
			}else{
				positional="";
			}
			var healDisease=result.healDisease;
			if(result.healDisease != null){
				healDisease=result.healDisease;
			}else{
				healDisease="";
			}
			var headPic=result.headPic;
			var serviceCount=result.serviceCount;
			var grade=result.grade;
			var commentNum=result.commentNum;
			if(result.commentNum != null){
				commentNum=result.commentNum;
			}else{
				commentNum="0";
			}
			var commentGrade="";
			if(result.commentGrade != null){
				commentGrade="评分: <span>"+result.commentGrade.toFixed(1)+"</span>";
			}else{
				commentGrade="暂无评分";
			}
			var dcName=result.dcName;
			depName=result.dcName;
			var departmentName=result.departmentName;
			var departmentId=result.departmentId;
			var hospitalName=result.hospitalName;
			if(result.hospitalName != null){
				hospitalName=result.hospitalName;
			}else{
				hospitalName="";
			}
			var Tel=getCookie("Tel");
			
			var dayTotal="";
			var nightTotal="";
			
			if(result.packageInfoList !=null){
				for(i=0;i<result.packageInfoList.length;i++){
					if(result.packageInfoList[i].workType != null){
						dayTotal=result.packageInfoList[i].total;
					}else{
						nightTotal=result.packageInfoList[i].total;
					}
				}
			}

			/*
			var packageId0="";
			var count0="";
			var total0="0";

			var packageId1="";
			var telCount1="";
			var total1="0";

			var packageId2="";
			var count2="";
			var telCount2="";
			var total2="0";
			var effectTime2="";

			if(result.packagrInfoList !=null){
				for(i=0;i<result.packagrInfoList.length;i++){
					
					if(result.packagrInfoList[i].type == 0){
						packageId0=result.packagrInfoList[i].packageId;
						count0=result.packagrInfoList[i].count;
						total0=result.packagrInfoList[i].total;
					}else if(result.packagrInfoList[i].type == 1){
						packageId1=result.packagrInfoList[i].packageId;
						telCount1=result.packagrInfoList[i].telCount;
						total1=result.packagrInfoList[i].total;
					}else if(result.packagrInfoList[i].type == 2){
						packageId2=result.packagrInfoList[i].packageId;
						count2=result.packagrInfoList[i].count;
						telCount2=result.packagrInfoList[i].telCount;
						effectTime2=result.packagrInfoList[i].effectTime;
						total2=result.packagrInfoList[i].total;
					}
	
				}
			}
			*/
			var Grad="";
			
			Grad="<ul id='point'><li><span></span></li><li><span></span></li><li><span></span></li><li><span></span></li><li><span></span></li></ul><p>"+commentGrade+"</p>"
			
			var content=$(".doctorMode").html();
			var Item=new Array();
			
			Item[0]=content;
			Item[0]=Item[0].replace("{$doctorId}",doctorId);
			Item[0]=Item[0].replace("{$name}",name);
			Item[0]=Item[0].replace("{$positional}",positional);
			Item[0]=Item[0].replace("{$headPic}",headPic);
			Item[0]=Item[0].replace("{$hospitalName}",hospitalName);
			Item[0]=Item[0].replace("{$healDisease}",healDisease);
			Item[0]=Item[0].replace("{$dcName}",dcName);
			Item[0]=Item[0].replace("{$departmentName}",departmentName);
			Item[0]=Item[0].replace("{$departmentId}",departmentId);
			Item[0]=Item[0].replace("{$commentNum}",commentNum);
			Item[0]=Item[0].replace("{$commentGrade}",commentGrade);
			Item[0]=Item[0].replace("{$departmentId}",departmentId);
			Item[0]=Item[0].replace("{$Grad}",Grad)
			Item[0]=Item[0].replace("{$Tel}",Tel);
			Item[0]=Item[0].replace("{$dayTotal}",dayTotal);
			Item[0]=Item[0].replace("{$nightTotal}",nightTotal);
			
			/*
			Item[0]=Item[0].replace("{$packageId0}",packageId0);
			Item[0]=Item[0].replace("{$count0}",count0);
			Item[0]=Item[0].replace("{$total0}",total0);
			
			Item[0]=Item[0].replace("{$packageId1}",packageId1);
			Item[0]=Item[0].replace("{$telCount1}",telCount1);
			Item[0]=Item[0].replace("{$total1}",total1);
			
			Item[0]=Item[0].replace("{$packageId2}",packageId2);
			Item[0]=Item[0].replace("{$count2}",count2);
			Item[0]=Item[0].replace("{$telCount2}",telCount2);
			Item[0]=Item[0].replace("{$total2}",total2);
			Item[0]=Item[0].replace("{$effectTime2}",effectTime2);
			*/
			
			$(".doctorMode").html("");
			$(".doctorMode").html(Item);
			
			
			for(i=0;i<parseInt(result.commentGrade);i++){
				$("#point li")[i].className="on";
			}
			
			
			$(".UserImg img").on("error",function(){
				$(this).attr("src","img/defaultpatient.png")
			})
			
			$(".Grad").click(function(){
				if($(this).find("p").text() != "暂无评分"){
					window.location.href="gradList.html?doctorId="+doctorId+"&commentNum="+commentNum;
				}else{
					return false
				}
				
			});
			
			$("#DatInp").change(function(){
				$(".DatInp").text($(this).val());
			});
			
			var date=new Date();
			var Year=date.getFullYear();
			var Month=date.getMonth()+1;
			var Days=date.getDate();
			
			$(".DatInp").text(Year+"-"+Month+"-"+Days);
			
			$(".Next").click(function(){
				setCookie("DocID",doctorId);
				
				if($(this).attr("id") == "0"){
					setCookie("pack",$(this).attr("id"));
					setCookie("packageId",packageId0);
					setCookie("appointType",$(this).attr("id"));
					setCookie("doctorId",doctorId);
					
					//window.location.href="select_time.html?depId="+depId+"&ToFee="+total0+"&depName="+depName+"&doctorId="+doctorId;
					window.location.href="Pat_Tiket.html?depId="+depId+"&ToFee="+total0+"&depName="+depName+"&doctorId="+doctorId;
				}else if($(this).attr("id") == "1"){
					setCookie("pack",$(this).attr("id"));
					setCookie("packageId",packageId1);
					setCookie("appointType",$(this).attr("id"));
					setCookie("doctorId",doctorId);
					window.location.href="Pat_Tiket.html?depId="+depId+"&depName="+depName;
					
				}else if($(this).attr("id") == "2"){
					var date=new Date;
					var Year=date.getFullYear();
					var Month=date.getMonth()+1;
					var day=date.getDate();
					if($(".DatInp").text() == "请选择套餐开始日期"){
						$(".DatInp").text(Year+"-"+Month+"-"+day);
					};
					
					setCookie("SetTime",$(".DatInp").val());
					setCookie("packageId",packageId2);
					setCookie("doctorId",doctorId);
					
					window.location.href="payment.html?id="+$(this).attr("id")+"&doctorId="+doctorId;
				}
			})
		}
	})
})