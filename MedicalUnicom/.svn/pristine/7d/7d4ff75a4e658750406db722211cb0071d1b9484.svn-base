$(function(){
	var link=window.location.href;
	var param=link.split("&");
	var doctorId="";
	for(i=0;i<param.length;i++){
		if(param[i].indexOf("doctorId") != -1){
			doctorId=param[i].substring(param[i].lastIndexOf("=")+1,param[i].length)
		}else{
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


	$.ajax({
		type:"get",
		url:"/MedicalUnicom/Doctor.do?doGetDoctorDetail",
		data: {
			doctorId: doctorId,
		},
		async: false,
		dataType: "json", // json or text or xml
		success:function(result){
			// 头像
			var headPic = result.headPic;
			// 账户
			var userId = result.userId;
			// 名字
			var name = result.name;
			// 出生日期
			var age=result.age;
			// 性别
			var sex=result.sex;
			// 身份证号
			var cardNum=result.cardNum;
			// 行医证号
			var certificateNum=result.certificateNum;
			// 行医证照片
			var certificatePic=result.certificatePic;
			// 医院
			var hospitalName=result.hospitalName;
			// 主治
			var healDisease=result.healDisease;
			// 科室
			var dcName=result.dcName;
			// 专业
			var departmentName=result.departmentName;
			// 症状
			var symptomName=result.symptomName;
			// 持卡人
			var bankOwner=result.bankOwner;
			// 卡号
			var bankNum=result.bankNum;
			// 银行
			var bankName=result.bankName;
			
			var followCount=result.followCount;
			
			$(".followCount").text(followCount);
			
			$("#UserImg").attr('src',headPic);
			$("#UserImg").bind("error",function(){
				this.src="img/defaultpatient.png"
			})
			//$("#account").val(account);
			$("#name").val(name);
			
			// 设置年龄\
			if(age != null){
				var date=new Date();
				var age_=date.getFullYear()-parseInt(age.substring(0,4));
				$("#birth").val(age_);

			};

			// 设置性别
			if(sex == 0) {
				$("#sex input").val("男");
			}else if(sex == 1){
				$("#sex input").val("女");
			};
			
			$("#cardNum").val(cardNum);
			$("#certificateNum").val(certificateNum);
			$("#certificatePic").attr("src",certificatePic);
			$("#hospitalName").val(hospitalName);
			$("#healDisease").val(healDisease);
			$("#dcName").val(dcName);
			$("#departmentId").val(departmentName);
			$("#symptomId").val(symptomName);
			$("#bankOwner").val(bankOwner);
			$("#bankNum").val(bankNum);
			$("#bankName").val(bankName);
			
			$(".information input").attr("readonly",true);
			//$(".list1 label").removeAttr("for");
		},
		error:function(res) {
			console.error("出错了" + res);
		}
	});
	
	$(".next").click(function(){	
		$(this).hide();
		$(".Edit").show();
		$.ajax({
			type:"post",
			url:"/MedicalUnicom/Doctor.do?doUpdateDoctorInfo",
			data: {
				doctorId:$("#account").val(),
				url:$("#UserImg").attr('src'),
				name:$("#name").val(),
			},
			async: false,
			success:function(result){
				if(result.returnCode == 0){
					var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_complate.png'></span><font color='white'>保存成功</font></div></div>")
					$("body").append(Mess);
					setTimeout(function(){
						$(".Mess").remove();
					},1500)
					//alert("保存成功");
					$("#name").attr("readonly",true);
					$("#name").removeClass("edit");
					//$(".list1 label").removeAttr("for");
				}else {
					var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_complate.png'></span><font color='white'>保存失败</font></div></div>")
					$("body").append(Mess);
					setTimeout(function(){
						$(".Mess").remove();
					},1500)
					return false
				}
			}
		})
 	});	
	
	$(".Edit").click(function(){
		$(this).hide();
		$(".next").show();
		$("#name").attr("readonly",false);
		$("#name").addClass("edit");
		//$(".list1 label").attr("for","Photofile");
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
		$("#buttonArea").animate({"bottom":"-224px"},100);
		$("#buttonArea").hide();
		$("#aside").hide();
	});

});