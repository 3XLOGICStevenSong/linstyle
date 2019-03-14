$(function(){
	
	var userTel=window.location.href.substring(window.location.href.lastIndexOf("=")+1,window.location.href.length)
	//alert(userTel);
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
	
	$("#new_pw").on("change", function(){

		var newPw=$("#new_pw").val();
		var confirmPw = $("#confirm_pw").val();
		
		if( !newPw ){
			alert("请输入新密码");
			$("#new_pw").focus();
			return false;
		}
		if( !confirmPw ){
			alert("请输入确认密码");
			$("#confirm_pw").focus();
			return false;
		}
		if(newPw != confirmPw){
			alert("新密码与确认密码不一致");
			$("#confirm_pw").focus();
			return false;
		}
		$.ajax({
			type:"get",
			url:"/MedicalUnicom/UserLogin.do?doResetPassword",
			data: {
				//doLogin:"",
				userTel: userTel,
				password: $("#new_pw").val()
			},
			async: false,
			dataType: "json", // json or text or xml
			success:function(result){
				// returnCode=0的时候说明登录成功
				if(result.returnCode == "0") {
					alert("密码重置成功");
					window.location.href="login.html"
				}else {
					alert("密码重置失败");
				}
			},
			error:function(res) {
				console.error("出错了" + res);
			}
		});
	})		
	$("#confirm_pw").on("change", function(){

		var newPw=$("#new_pw").val();
		var confirmPw = $("#confirm_pw").val();
		
		if( !newPw ){
			alert("请输入新密码");
			$("#new_pw").focus();
			return false;
		}
		if( !confirmPw ){
			alert("请输入确认密码");
			$("#confirm_pw").focus();
			return false;
		}
		if(newPw != confirmPw){
			alert("新密码与确认密码不一致");
			$("#confirm_pw").focus();
			return false;
		}
		$.ajax({
			type:"get",
			url:"/MedicalUnicom/UserLogin.do?doResetPassword",
			data: {
				//doLogin:"",
				userTel: userTel,
				password: $("#new_pw").val()
			},
			async: false,
			dataType: "json", // json or text or xml
			success:function(result){
				// returnCode=0的时候说明登录成功
				if(result.returnCode == "0") {
					alert("密码重置成功");
					window.location.href="login.html"
				}else {
					alert("密码重置失败");
				}
			},
			error:function(res) {
				console.error("出错了" + res);
			}
		});
	})		
});