$(function(){
	var pid=getCookie("pid");
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
	var doctorId=window.location.href.substring(window.location.href.lastIndexOf("=")+1,window.location.href.length);
	var recId=window.location.href.substring(window.location.href.lastIndexOf("?")+1,window.location.href.lastIndexOf("&"));
	var recordsId=recId.substring(recId.lastIndexOf("=")+1,recId.length);
	var grade="";
	var gradInfo=$(".gradInfo").val();
	
	var point=document.getElementById("point");
	for(i=0;i<point.children.length;i++){
		
		(function(e){
			point.children[e].onclick=function(){
				
				grade=e+1;
				
				if(point.children[e].className == "on"){
					for(j=1;j<point.children.length;j++){
						point.children[j].className="";
					};
				};
				
				if( e < point.children.length){
					for(j=0;j<=e;j++){
						point.children[j].className="on";
					};
					point.children[e].className+=" pOn";
				};
				
			};
			
		})(i);

	};
	$(".SubmitBtn").click(function(){
		$.ajax({
			type:"post",
			url:"/MedicalUnicom/DoctorComment.do?doInsertDoctorComment",
			data:{
				/*
				1.预约问诊的ID:recordsId
				2.医生评分:grade
				3.评价内容:content 
				4:预约者ID：patientId
				5:评论的医生ID：doctorId
				*/
				doctorId:doctorId,
				patientId:pid,
				recordsId:recordsId,
				grade:grade,
				content:gradInfo
			},
			aync:false,
			dataType:"json",
			success:function(result){
				if(result.returnCode == 0){
					alert("提交成功");
				}
			}
		})
	})
})