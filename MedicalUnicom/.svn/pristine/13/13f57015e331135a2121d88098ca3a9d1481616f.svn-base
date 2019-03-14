$(function(){

	var link=window.location.href;
	var recordsId=link.substring(link.lastIndexOf("=")+1,link.length);


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
	}


	$.ajax({
		type:"get",
		url:"/MedicalUnicom/Records.do?doGetAppointDetailByDoctor",
		data:{
			recordsId:recordsId,
		},
		aync:false,
		dataType:"json",
		success:function(result){
			var date=new Date();
			var content=$(".contents").html();
			var Item=new Array();

			var patientId=result.patientId;
			var name=result.name;
			var sex=result.sex;
			if(sex == 0){
				sex ="img/icon_man.png";
			}else if(sex == 1){
				sex ="img/icon_girl.png";
			};
			var age=result.birth;
			if(age != null){
				var date=new Date();
				age=date.getFullYear()-parseInt(age.substring(0,4));
			}else{
				age="";
			};
			var patientPic=result.patientPic;

			var departmentName=result.departmentName;
			var recordsId=result.recordsId;
			var inquiryStatus=result.inquiryStatus;
			var symptomName=result.symptomName;
			var symptonDescribe=result.symptonDescribe;
			if(symptonDescribe != null){
				symptonDescribe=result.symptonDescribe;
			}else{
				symptonDescribe="暂无问诊票";
			}
			var recordsType=result.recordsType;
			if(recordsType==0){
				recordsType="视频"
			}else if(recordsType==1){
				recordsType="电话"
			}
			var createTime=result.createTime;
			var appointTime=result.appointTime;
			
			var time="";
			if(appointTime != null){
				time=result.appointTime;
			}else{
				time=result.createTime;
			}

			var time_Res="";
			var data_Res=new Date(time.replace(/-/g,"/")+":00");
			var dateR2=Math.abs(date.getTime()-data_Res.getTime());
			var Rday=Math.floor(dateR2/24/3600/1000);
			var Rleave1=dateR2%(24*3600*1000);
			var Rhours=Math.floor(Rleave1/(3600*1000));
			var RH=Math.floor(dateR2/3600/1000);
			var Rleave2=Rleave1%(3600*1000);       //计算小时数后剩余的毫秒数  
			var Rminutes=Math.floor(Rleave2/(60*1000));
			var RM=Math.floor(dateR2/60/1000);
			if(RH > 24 ){
				time_Res=Rday+"d";
			}else if(RH == 24 && RM > 60){
				time_Res=RH+"h";
			}else if(RH < 24 && RM > 60){
				time_Res=Rhours+"h";
			}else if(RM < 60 ){
				time_Res=Rminutes+"m";
			}
			
			
			var time_Inquiry="";
			var date_Inquiry=new Date(time.replace(/-/g,"/")+":00");
			var date2=Math.abs(date.getTime()-date_Inquiry.getTime());
			var day=Math.floor(date2/24/3600/1000);
			var leave1=date2%(24*3600*1000);
			var hours=Math.floor(leave1/(3600*1000));
			var H=Math.floor(date2/3600/1000);
			var leave2=leave1%(3600*1000);       //计算小时数后剩余的毫秒数  
			var minutes=Math.floor(leave2/(60*1000));
			var M=Math.floor(date2/60/1000);
			
			if(H > 24 ){
				time_Inquiry=day+"d";
			}else if(H == 24 && M > 60){
				time_Inquiry=H+"h";
			}else if(H < 24 && M > 60){
				time_Inquiry=hours+"h";
			}else if(M < 60 ){
				time_Inquiry=minutes+"m";
			}
			

			var time_Over="";
			var data_Over=new Date(time.replace(/-/g,"/")+":00");
			var dateO=Math.abs(date.getTime()-data_Over.getTime()-3600*1000);
			var Oday=Math.floor(dateO/24/3600/1000);
			var Oleave1=dateO%(24*3600*1000);
			var Ohours=Math.floor(Oleave1/(3600*1000));
			var OH=Math.floor(dateO/3600/1000);
			var Oleave2=Oleave1%(3600*1000);       //计算小时数后剩余的毫秒数  
			var Ominutes=Math.floor(Oleave2/(60*1000));
			var OM=Math.floor(dateO/60/1000);
			if(OH > 24 ){
				time_Over=Oday+"d";
			}else if(OH == 24 && OM > 60){
				time_Over=OH+"h";
			}else if(OH < 24 && OM > 60){
				time_Over=Ohours+"h";
			}else if(OH < 1 && OM < 60 ){
				time_Over=Ominutes+"m";
			}
			

			var telNum=result.telNum;
			if(telNum !=null){
				telNum=result.telNum;
			}else{
				telNum="";
			}
			var analysis=result.analysis;
			if(analysis != null){
				analysis=analysis;
			}else{
				analysis="暂无病状分析";
			}
			var guidance=result.guidance;
			if(guidance != null){
				guidance=guidance;
			}else{
				guidance="暂无病状分析";
			}
			var inquiryPic=result.inquiryPic;
			//var inquiryPic=["img/main_slide1.jpg","img/main_slide1.jpg","img/main_slide1.jpg"];
			var imageFile="";
			if(inquiryPic != null){
				for(m=0;m<inquiryPic.length;m++){
					imageFile+="<img src='"+inquiryPic[m]+"' />"
				}
			}else{
				imageFile="<p class='Empty'>暂无化验单</p>"
			}
			

			content=content.replace("{$name}",name);
			content=content.replace("{$sex}",sex);
			content=content.replace("{$age}",age);
			content=content.replace("{$patientPic}",patientPic);
			content=content.replace("{$telNum}",telNum);
			content=content.replace("{$analysis}",analysis);
			content=content.replace("{$guidance}",guidance);
			content=content.replace("{$symptonDescribe}",symptonDescribe.replace(/\n/g,"<br/><br/>"));
			content=content.replace("{$symptomName}",symptomName);
			content=content.replace("{$departmentName}",departmentName);
			content=content.replace("{$time}",time.substring(0,10));
			content=content.replace("{$ftime}",time.substring(0,10));
			content=content.replace("{$vtime}",time.substring(0,10));
			content=content.replace("{$time_Res}",time_Res);
			content=content.replace("{$time_Inquiry}",time_Inquiry);
			content=content.replace("{$time_Over}",time_Over);
			content=content.replace("{$imageFile}",imageFile);
			
			$(".contents").html(content);
			
			$(".UserImg img").on("error",function(){
				$(this).attr("src","img/defaultpatient.png")
			})
			
			$(".NaviCate li").click(function(){
				$(".NaviCate li").removeClass("On");
				$(".contentList .Menu").hide();
				$(this).addClass("On");
				
				if($(this).attr("cate") == 1){
					$(".history").show();
				}else if($(this).attr("cate") == 2){
					$(".historyInfo").show();
				}else if($(this).attr("cate") == 3){
					$(".ImageFile").show();
				}else if($(this).attr("cate") == 4){
					$(".Analsis").show();
				}
			})
			
			$(".info").delegate(".opener","click",function(){
				if($(this).attr("class") == "opener") {
					$(".opener span").animate({"left":$(this).width()-$(".opener span").width()-2},150,function(){
						$(".opener").addClass("On");
					});
				}else{
					$(".opener span").animate({"left":2},200,function(){
						$(".opener").removeClass("On");
					});
				}
			})
		}
	})
	
	
	
})