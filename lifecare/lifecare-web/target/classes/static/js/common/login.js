
$(function(){
	//获取本地缓存 完成记住密码功能
	var url="http://192.168.11.128/lifecare/api/";
	localStorage.setItem("url",url)
	var storeUserNumber=localStorage.getItem("userNumber");
	var storePassword=localStorage.getItem("password");
	if(localStorage.getItem("userNumber")!=null&&storePassword!=null){
	$("#userNumber").val(storeUserNumber);
	$("#password").val(storePassword);
	}
	//点击登录
	$(".add").click(function(){
	var userNumber=$("#userNumber").val();
	var password=$("#password").val();
	
	//验证员工号
	if(userNumber==''){
		$(".login-error").text("请输入员工号!");
		$(".login-error").css('display','block');
		return false;
	}
	//验证密码
	if(password==''){
		$(".login-error").text("请输入密码!");
		$(".login-error").css('display','block');
		return false;
	}
	//请求登录
		$.ajax({
			type:"post",
			url:url+"login",
			dataType: "json", 
			contentType : "application/json",
			data :JSON.stringify( {
				userNumber:userNumber,
				password:password
			}),
	
			success:function(result,status,httpResponse){
				if (result.msg=='登陆成功' && result.statusCode=='200') {
				//将token存入缓存	
					var token=httpResponse.getResponseHeader('Authorization');
					
					if(!window.localStorage){
						alert("浏览器不支持localstorage");
						return false;
				}else{
					localStorage.setItem("token", token); 
					
					localStorage.setItem("userId", result.data.userId); 
					}
					//设置预约时间提前量 1小时
					
					localStorage.setItem("appointTime",60);
					//将账号信息存入本地缓存
					if($("#check_box").prop("checked")){
						
						localStorage.setItem("userNumber",userNumber);
						localStorage.setItem("password",password)	
					}
				
					//获取权限
					$.ajax({
						type:"get",
						url:url+"current/user/permissions",
						dataType: "json", 
						contentType : "application/json",
						beforeSend: function(request) {
		    				request.setRequestHeader("Authorization", token);
		    				},
					
						success:function(result,status,httpResponse){
							//将权限存储到本地
							if (result.statusCode=='200') {
								var permissions=localStorage.getItem("userNumber")+"permission";
							
								if( result.data.length>0){
								
								localStorage.setItem(permissions, result.data); 
								window.location.href="service-task-pool.html";
								}else{
									$(".login-error").text("您还没有任何权限请联系管理员！");
									$(".login-error").css('display','block');
									return false;
								}
						}
						}
				})
				//登录失败	
				}else {
					$(".login-error").text(result.msg);
					$(".login-error").css('display','block');
					return false;
				}
			}
	})
	})
})