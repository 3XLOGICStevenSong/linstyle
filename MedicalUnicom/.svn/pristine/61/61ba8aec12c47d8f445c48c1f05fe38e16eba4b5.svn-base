$(function(){
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
	
	$("#detailForm").on("click", function(){
		if(patient_form.style.display == "block"){
			var tel=$("#P_userTel").val();
			var myreg = /(1[3-9]\d{9}$)/;

			if( !tel ){
				alert("请输入手机号");
				$("#P_userTel").focus();
				return false;
			}
			
			if(myreg.test(tel) == false){
				alert("请输入正确的手机号");
				$("#P_userTel").focus();
				return false;
			}else if($("#P_password").val() == ""){
				alert("请输入密码");
				$("#P_password").focus();
				return false;
			};
			
			var P_tel=$("#P_userTel").val();
			var P_password=$("#P_password").val();
			
			$.ajax({
				type:"post",
				url:"/MedicalUnicom/UserLogin.do?doRegisterPatient",
				data: {
					//doLogin:"",
					userTel: $("#P_userTel").val(),
					password: $("#P_password").val()
				},
				async: false,
				dataType: "json", // json or text or xml
				success:function(result){
					// returnCode=0的时候说明登录成功
					if(result.returnCode == "0") {
						alert("注册成功");
						setCookie("P_tel",P_tel);
						window.location.href="login.html";
					}else if(result.returnCode == "-3011"){
						alert("注册账号已存在");
						return false;
					}
					else if(result.returnCode == "-3010"){
						alert("注册失败");
						window.location.href="join.html";
					}
				},
				error:function(res) {
					console.error("出错了" + res);
				}
			});
		}/*else if(doctor_form.style.display == "block"){
			var D_tel=$("#D_userTel").val();
			var D_password=$("#D_password").val();
			setCookie("D_tel",D_tel);
			setCookie("D_password",D_password);
			window.location.href="/MedicalUnicom/HealthWeb/doctor_join_detail.html";
		}*/
		else if(doctor_form.style.display == "block"){
			var tel=$("#D_userTel").val();
			var myreg = /(1[3-9]\d{9}$)/;

			if( !tel ){
				alert("请输入手机号");
				$("#D_userTel").focus();
				return false;
			}
			
			if(myreg.test(tel) == false){
				alert("请输入正确的手机号");
				$("#D_userTel").focus();
				return false;
			}else if($("#D_password").val() == ""){
				alert("请输入密码");
				$("#D_password").focus();
				return false;
			};
			
			var D_tel=$("#D_userTel").val();
			var D_password=$("#D_password").val();
			setCookie("D_tel",D_tel);
			setCookie("D_password",D_password);
			window.location.href="Doc_join.html";
			/*
			$.ajax({
				type:"post",
				url:"/MedicalUnicom/UserLogin.do?doRegisterDoctor",
				data: {
					//doLogin:"",
					userTel: $("#D_userTel").val(),
					password: $("#D_password").val()
				},
				async: false,
				dataType: "json", // json or text or xml
				success:function(result){
					// returnCode=0的时候说明登录成功
					if(result.returnCode == "0") {
						alert("注册成功");
						setCookie("D_tel",D_tel);
						setCookie("D_password",D_password);
						window.location.href="/MedicalUnicom/HealthWeb/doctor_join_detail.html";
					}else if(result.returnCode == "-3021"){
						alert("注册账号已存在");
						$("#D_userTel").focus();
						return false;
					}else if(result.returnCode == "-3022"){
						alert("请输入账号");
						$("#D_userTel").focus();
						return false;
					}else if(result.returnCode == "-3020"){
						alert("注册失败");
						window.location.href="/MedicalUnicom/HealthWeb/join.html";
					}
				,
				error:function(res) {
					console.error("出错了" + res);
				}

			});
			*/
		}
	})
});