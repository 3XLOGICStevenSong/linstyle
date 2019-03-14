$(function(){
	var link=window.location.href;
	var param=link.split("&");
	var patientId="";
	var doctorId="";
	
	for(i=0;i<param.length;i++){
		if(param[i].indexOf("doctorId") != -1 ){
			doctorId=param[i].substring(param[i].lastIndexOf("=")+1,param[i].length);
		}else if(param[i].indexOf("patientId") != -1 ){
			patientId=param[i].substring(param[i].lastIndexOf("=")+1,param[i].length);
		}else{
			patientId=getCookie("pid");
			doctorId=getCookie("pid");
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
	$("#Photofile").on("change",function(){
		var img=document.getElementById("Photofile");
		var oFReader= new FileReader();
		oFReader.readAsDataURL(img.files[0]);
		oFReader.onload= function (oFREvent) {
			src=oFREvent.target.result;
			$("#UserImg").attr("src",src);
			$.ajax({
				type:"post",
				url:"/MedicalUnicom/Doctor.do?doUpdateDoctorInfo",
				data: {
					doctorId:doctorId,
					url:$("#UserImg").attr('src'),
					name:$("#name").val()
				},
				aync: false,
				success:function(result){
					if(result.returnCode == "0"){
						var imgSrc="";
						$.ajax({
							type:"get",
							url:"/MedicalUnicom/Doctor.do?doGetDoctorDetail",
							data:{
								doctorId: doctorId
							},
							async:false,
							dataType:"json",
							success:function(result){
								var headPic=result.headPic;
								imgSrc=headPic;
							}
						})
						
						var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_complate.png'></span><font color='white'>头像更新成功</font></div></div>")
						$("body").append(Mess);
						setTimeout(function(){
							$(".Mess").remove();
							window.location.href="Doc_Information.html?imgSrc="+imgSrc;//$("#UserImg").attr('src')
						},1500)
					}else {
						var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>更新失败</font></div></div>")
						$("body").append(Mess);
						setTimeout(function(){
							$(".Mess").remove();
						},1500)
					}
				}
			})
		}; 
	})
	
	$("#Photofile1").on("change",function(){
		var img=document.getElementById("Photofile1");
		var oFReader= new FileReader();
		oFReader.readAsDataURL(img.files[0]);
		oFReader.onload= function (oFREvent) {
			src=oFREvent.target.result;
			$("#UserImg").attr("src",src);
			/*
			var _birth=$("#birth").val();
			var Birth_reg=/^[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}$/;

			if(Birth_reg.test(_birth)==false){
				alert("请按照正确时间格式输入");
				$("#birth").focus();
				return false;
			};
			
			if($("#man").attr("key") == "0"){
				var _Sex=0;
			}else if($("#femal").attr("key") == "1"){
				var _Sex=1;
			}
			
			$.ajax({
				type:"post",
				url:"/MedicalUnicom/Patient.do?doUpdatePatientInfo",
				data: {
					patientId:patientId,
					url:$("#UserImg").attr('src'),
					name:$("#name").val(),
					birth:$("#birth").val(),
					sex:_Sex
				},
				async: false,
				success:function(result){
					if(result.returnCode == "0"){
						var imgSrc="";
						$.ajax({
							type:"get",
							url:"/MedicalUnicom/Patient.do?doGetPatientInfo",
							data:{
								patientId: patientId
							},
							async:false,
							dataType:"json",
							success:function(result){
								var patientPic=result.patientPic;
								imgSrc=patientPic;
							}
						})
						var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_complate.png'></span><font color='white'>头像更新成功</font></div></div>")
						$("body").append(Mess);
						setTimeout(function(){
							$(".Mess").remove();
							window.location.href="Pat_Information.html?imgSrc="+imgSrc;//$("#UserImg").attr('src')
						},1500)
					}else {
						var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>更新失败</font></div></div>")
						$("body").append(Mess);
						setTimeout(function(){
							$(".Mess").remove();
						},1500)
					}
				}
			})*/
		}; 
	})
})
