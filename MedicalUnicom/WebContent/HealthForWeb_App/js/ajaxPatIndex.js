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
	}

	$.ajax({
		type:"get",
		url:"/MedicalUnicom/Patient.do?doGetPatientInfo",
		data:{
			patientId:pid,
		},
		aync:false,
		dataType:"json",
		success:function(result){
			$(".usrName").text(result.name);
			$(".usrImg").attr("src",result.patientPic)
			$(".usrImg").on("error",function(){
				$(this).attr("src","img/defaultpatient.png")
			})
		}
	})

});