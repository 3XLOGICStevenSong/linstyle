$(function(){
	var link=window.location.href;
	var param=link.split("&");
	var doctorId="";
	for(i=0;i<param.length;i++){
		if(param[i].indexOf("doctorId") != -1 ){
			doctorId=param[i].substring(param[i].lastIndexOf("=")+1,param[i].length);
		}else{
			doctorId=getCookie("pid")
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

	var Intro="";
	
	$.ajax({
		type:"get",
		url:"/MedicalUnicom/Doctor.do?doGetDoctorInfo",
		data:{
			doctorId:doctorId
		},
		aync:false,
		dataType:"json",
		success:function(result){
			Intro=result.healDisease;
			$("#intro").val(Intro);
		}
	})

	
	$(".btnSave").click(function(){
		var txt=Intro;
		txt=$("#intro").val();
		var myReg = /\uD83C[\uDF00-\uDFFF]|\uD83D[\uDC00-\uDE4F]/;
		if(myReg.test(txt)){
			//alert("aa");
			var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>内容不能包含Emoji</font></div></div>")
			$("body").append(Mess);
			
			//txt=txt.replace(/\uD83C[\uDF00-\uDFFF]|\uD83D[\uDC00-\uDE4F]/g,"");

			setTimeout(function(){
				$(".Mess").remove();
				$("#intro").val(Intro);
			},1500)
			
			
			return false;
		}
		/*
		$.ajax({
			type:"post",
			url:"/MedicalUnicom/Doctor.do?doUpdateDoctorInfo",
			data:{
				doctorId:doctorId,
				healDisease:Intro
			},
			aync:false,
			dataType:"json",
			success:function(result){
				if(result.returnCode == 0){
					var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_complate.png'></span><font color='white'>保存成功</font></div></div>")
					$("body").append(Mess);
					setTimeout(function(){
						$(".Mess").remove();
					},1500)
				}else{
					var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>保存失败</font></div></div>")
					$("body").append(Mess);
					setTimeout(function(){
						$(".Mess").remove();
					},1500)
				};
			}
		})
		*/
	})
})