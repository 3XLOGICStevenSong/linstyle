$(function(){
	var link=window.location.href;
	var param=link.split("&");
	var patientId="";
	var TelNumber="";
	for(i=0;i<param.length;i++){
		if(param[i].indexOf("patientId") != -1 ){
			patientId=param[i].substring(param[i].lastIndexOf("=")+1,param[i].length);
		}else if(param[i].indexOf("account") != -1 ){
			TelNumber=param[i].substring(param[i].lastIndexOf("=")+1,param[i].length);
		}else{
			patientId=getCookie("pid");
			TelNumber=getCookie("Tel");
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
		data: {
			patientId: patientId,
		},
		async: false,
		dataType: "json", // json or text or xml
		success:function(result){
			// role 0:医生 1:患者
			var role = result.role;
			// 患者id
			var patientId = result.patientId;
			// userId
			var userId = result.userId;
			// name
			var name = result.name;
			// headPic
			var url = result.patientPic;
			// birth
			var birth=result.birth;
			// sex
			var sex=result.sex;

			$(".TelNumber").text(TelNumber);
			
			$("#UserImg").attr('src',url);
			
			$("#UserImg").bind("error",function(){
				this.src="img/defaultpatient.png"
			})
			//$("#account").val(account);
			$("#name").val(name);
			$("#birth").val(birth);
			
			if(sex == 0) {
				$("#sex").val("男");
				//$("#man").attr("checked","checked");
				//$("#man").attr("key","0")
			}else if(sex == 1){
				$("#sex").val("女");
				//$("#femal").attr("checked","checked");
				//$("#femal").attr("key","1")
			}
			
			$(".information input[type=text]").attr("readonly",true);
			$(".list1 label").removeAttr("for");

		},
		error:function(res) {
			console.error("出错了" + res);
		}
	});
	
	$(".next").click(function(){
		
		var _birth=$("#birth").val();
		var Birth_reg=/^[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}$/;

		if(Birth_reg.test(_birth)==false){
			alert("请按照正确时间格式输入");
			$("#birth").focus();
			return false;
		};
		
		if($("#sex").val() == "男"){
			var _Sex=0;
		}else if($("#sex").val() == "女"){
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
				if(result.returnCode == 0){
					$(".information input[type=text]").attr("readonly",true);
					$(".information input[type=text]").removeClass("edit");
					$(".list1 label").removeAttr("for");
					$(".next").hide();
					$(".Edit").show();
					
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
							var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_complate.png'></span><font color='white'>更新成功</font></div></div>")
							$("body").append(Mess);
							setTimeout(function(){
								$(".Mess").remove();
								//window.location.href="Pat_Information.html?imgSrc="+imgSrc;//$("#UserImg").attr('src')
							},1500)
						}
					})
				}else {
					var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>更新失败</font></div></div>")
					$("body").append(Mess);
					setTimeout(function(){
						$(".Mess").remove();
					},1500)
					//alert("请重新输入");
				}
			}
		})

 	});
	
	$(".Edit").click(function(){
		$(this).hide();
		$(".next").show();
		$(".information input[type=text]").attr("readonly",false);
		$(".information input[type=text]").addClass("edit");
		$(".list1 label").attr("for","Photofile1");
		$("#account").attr("readonly",true);
		$("#account").removeClass("edit");
 	});
	
	$("#logout_btn").click(function(){
		$("#aside").show();
		$("#buttonArea").show();
		$("#buttonArea").animate({"bottom":"0"},100);
	})
	
	$("#logout").click(function(){
		var keys=document.cookie.match(/[^ =;]+(?=\=)/g); 
		if (keys) { 
			for (var i = keys.length; i--;) 
			document.cookie=keys[i]+'=0;expires=' + new Date( 0).toUTCString() 
		} 
		window.location.href="login.html";
	})
	
	$("#cancel").click(function(){
		$("#buttonArea").animate({"bottom":"-224px"});
		$("#buttonArea").hide();
		$("#aside").hide();
	});

});