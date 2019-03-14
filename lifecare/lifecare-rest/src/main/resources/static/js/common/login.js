
$(function(){
	//获取本地缓存 完成记住密码功能
	var url="http://localhost:8084/lifecare/api/";
	localStorage.setItem("url",url);
	var storeUserNumber=localStorage.getItem("userNumber");
	var storePassword=localStorage.getItem("password");
	if(localStorage.getItem("userNumber")!=null&&storePassword!=null){
	$("#userNumber").val(storeUserNumber);
	$("#password").val(storePassword);
	}
	//点击登录
	$(".add").click(function(){
		login();
	})

	$("body").keydown(function(event) {
		var e = event ? event : (window.event ? window.event : null);
		if (e.keyCode == "13") {//keyCode=13是回车键
			login();
		}
	})
	function login(){
		var userNumber=$("#userNumber").val();
		var password=$("#password").val();

		//验证员工号
		if(userNumber==''&& password=='' ){
			$(".login-error").html("登录名不能为空!"+"</br>"+"密码不能为空!");
			$(".login-error").css('display','block');
			return false;
		}
		if(userNumber==''){
			$(".login-error").html("登录名不能为空!");
			$(".login-error").css('display','block');
			return false;
		}else if(userNumber.length>16){
			$(".login-error").html("登录名长度不能大于16位!");
			$(".login-error").css('display','block');
			return false;
		}
		//验证密码
		if(password==''){
			$(".login-error").html("密码不能为空!");
			$(".login-error").css('display','block');
			return false;
		}else if(password.length>20){
			$(".login-error").html("密码长度不能大于20位!");
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
						//alert("浏览器不支持localstorage");
						Swal.fire({
							type: 'error',
							title: "浏览器不支持localstorage"
						})
						return false;
					}else{
						localStorage.clear();
						localStorage.setItem("url",url);
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

									var task=result.data.indexOf("task:*");

									if(task!=-1){
										window.location.href="service-task-pool.html";
									}else{
										window.location.href="blank.html";
									}

								}else{
									$(".login-error").html("您还没有任何权限请联系管理员！");
									$(".login-error").css('display','block');
									return false;
								}
							}else if(result.statusCode=="401"){
								window.location.href="login.html";
							}else if(result.statusCode=="512"){
								Swal.fire({
									type: 'error',
									title: result.msg
								})
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
	}
})