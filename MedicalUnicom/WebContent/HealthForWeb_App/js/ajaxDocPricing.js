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
	
	$.ajax({
		type:"get",
		url:"/MedicalUnicom/Interrogation.do?doGetDoctorPackage",
		data:{
			doctorId:pid
		},
		aync:false,
		dataType:"json",
		success:function(result){
			/*
			定价List{interrogationClientDTO
			价格：total
			视频问诊次数 count
			电话问诊次数：telCount
			类型：type 0：视频1：电话2：私人
			私人医生套餐有效期：effectTime}
			*/
			var totel=result.interrogationClientDTO
			var Ttoal="";
			var TDay="";
			var PriVcount="";
			var PriTcount="";
			var Vprice="";
			var Cprice="";
			for(i=0;i<totel.length;i++){
				if(totel[i].type == 0){
					Vprice=totel[i].total
				}else if(totel[i].type == 1){
					Cprice=totel[i].total
				}else if(totel[i].type == 2){
					Ttoal=totel[i].total;
					TDay=totel[i].effectTime;
					PriVcount=totel[i].count;
					PriTcount=totel[i].telCount;
				}
			};

			$(".Vmoney").val(Vprice);
			$(".Cmoney").val(Cprice);
			$(".quantity_vid").val(PriVcount);
			$(".quantity_call").val(PriTcount);
			$(".total").val(Ttoal);
			$(".day").val(TDay);
		}
	});
})