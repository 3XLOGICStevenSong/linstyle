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
	
	$("#login").on("click", function(){
		var tel=$("#username").val();
		var myreg = /(1[3-9]\d{9}$)/;

		if( !tel ){
			alert("请输入手机号");
			$("#username").focus();
			return false;
		}
		
		if(myreg.test(tel) == false){
			alert("请输入正确的手机号");
			$("#username").focus();
			return false;
		}else if($("#psw").val() == ""){
			alert("请输入密码");
			$("#psw").focus();
			return false;
		};
		
		$.ajax({
			type:"get",
			url:"/MedicalUnicom/UserLogin.do?doLogin=",
			data: {
				//doLogin:"",
				
				userTel: $("#username").val(),
				password: $("#psw").val()
			},
			async: false,
			dataType: "json", // json or text or xml
			success:function(result){
				// returnCode=0的时候说明登录成功
				if(result.returnCode == "0") {
					//alert("登录成功");
					// 患者id
					var patientId = result.patientId;
					// 医生id
					var doctorId = result.doctorId;
					// userId
					var userId = result.userId;
					// headPic
					var headPic = result.headPic;
					// name
					var name = result.name;
					// role 0:医生 1:患者
					var role = result.role;
					
					var Tel=$("#username").val();
					if(role == 1){
						window.location.href="Pat_Index.html?role=1"
						setCookie("pid",patientId);
						setCookie("Tel",Tel);
					}else if(role == 0){
						window.location.href="Doc_Index.html?role=0"
						setCookie("Tel",Tel);
						setCookie("pid",doctorId);
					}
				}else {
					alert("您的账号或密码错误");
				}
			},
			error:function(res) {
				console.error("出错了" + res);
			}
		});
	})		
});