$(function(){
	//获取缓存token
	var token=localStorage.getItem("token");
	var url=localStorage.getItem("url");
	var userId=localStorage.getItem("userId");
	var userNumber=localStorage.getItem("userNumber");
	/*var regExp=/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,21}$/;*/
	
	
	
	//格式验证
	jQuery.validator.addMethod("isFormatCorrect", function(value,element) {
		var regExp=/^[a-zA-Z0-9]{6,20}$/
		return this.optional(element) || (regExp.test(value));    
	});
	//原密码和新密码相同验证
	jQuery.validator.methods.compareNewAndOldPsw = function(value, element, param) {
		//获取当前日期
		var oldPassword = $("#oldPassword").val();
	    var newPassword = $("#newPassword").val();
	    return newPassword != oldPassword;
	};

	function formValidate(){
		validateResult= $("#form").validate({
			rules: {
				oldPassword : {
					required: true,
					isFormatCorrect:true
				},
				newPassword : {
					required:true,
					isFormatCorrect:true,
					compareNewAndOldPsw:true
				},		 		 
				confirmPassword : {
					required:true,
					equalTo:"#newPassword"
				}
			},
			messages: {
				oldPassword : {
					required: "请输入原密码",
					isFormatCorrect: "密码必须为6-20位的大小字母或数字：请输入原密码"
				},
				newPassword : {
					required:"请输入新密码",
					isFormatCorrect: "密码必须为6-20位的大小字母或数字：请输入新密码",
					compareNewAndOldPsw:"新密码和旧密码相同：请输入新密码"
				},	 		 
				confirmPassword : {
					required:"请输入确认密码",
					equalTo:"确认密码必须和新密码相同"	
				}
			}
		});
		return validateResult;
	};		
	
	
	
//	$(".save").on("click",function(){
//		var oldPassword=$("#oldPassword").val();
//		if(oldPassword==""){
//			$(".login-error").text("请输入旧密码!");
//			$(".login-error").css('display','block');
//			$("#oldPassword").focus();
//			return;
//		}else{
//			 if(!regExp.test(oldPassword)){
//				$(".login-error").text("旧密码格式不正确，字数为6-20位数字和字母!");
//				$(".login-error").css('display','block');
//				$("#oldPassword").focus();
//				return;
//			}else{
//				$(".login-error").text("");
//				$(".login-error").css('display','none');
//			}
//		})
//	   }
//		var newPassword=$("#newPassword").val();
//		if(newPassword==""){
//			$(".login-error").text("请输入新密码!");
//			$(".login-error").css('display','block');
//			$("#newPassword").focus();
//			return;
//		}else{
//			if(!regExp.test(newPassword)){
//				$(".login-error").text("新密码格式不正确，字数为6-20位数字和字母!");
//				$(".login-error").css('display','block');
//				$("#newPassword").focus();
//				return;
//			}else{
//				$(".login-error").text("");
//			    $(".login-error").css('display','none');
//			}
//		}
//		var confirmPassword=$("#confirmPassword").val();
//		if(confirmPassword==""){
//			$(".login-error").text("请输入确认密码!");
//			$(".login-error").css('display','block');
//			$("#confirmPassword").focus();
//			return;
//		}else{
//			if(!regExp.test(confirmPassword)){
//				$(".login-error").text("新密码格式不正确，字数为6-20位数字和字母!");
//				$(".login-error").css('display','block');
//				$("#confirmPassword").focus();
//				return;
//			}else{
//				$(".login-error").text("");
//			    $(".login-error").css('display','none');
//			}	
//		}
//		if(newPassword==oldPassword){
//			$(".login-error").text("新旧密码不可以一样!");
//			$(".login-error").css('display','block');
//			$("#newPassword").focus();
//			return;
//		}
//		if(newPassword!=confirmPassword){
//			$(".login-error").text("两次密码不一样");
//			$(".login-error").css('display','block');
//			$("#confirmPassword").focus();
//			return;
//		}
//}
	//保存按钮
	$(".save").click(function() {
		if (formValidate().form()){ //判断校验是否符合规则
//		if (true) {
			//获取要发送的数据值
			//旧密码
			var oldPassword=$("#oldPassword").val();
			//新密码
			var newPassword=$("#newPassword").val();
			var data ={
				"userNumber":userNumber,
				"passwordOld":oldPassword,
				"passwordNew":newPassword,
				"loginUserId":userId
			};
			$.ajax({
				type:"post",
				url:url+"user/pwdModify",
				dataType: "json", 
				contentType : "application/json",
				beforeSend: function(request) {
			 		request.setRequestHeader("Authorization", token);
			 	},
			 	data:JSON.stringify(data),
				success:function(result){
					if (result.statusCode=="200") {
						alert(result.msg);
						localStorage.clear();
						window.location.href="login.html";
					}
					if (result.statusCode=="512") {
						alert(result.msg);
					}
				}
			})
		}else{
			// 不符合规则返回
			return false;
		} 
	})
	
//		$.ajax({
//			type:"post",
//			url:url+"user/pwdModify",
//			beforeSend: function(request) {
//				request.setRequestHeader("Authorization", token);
//			},
//			async: false,
//			dataType: "json", 
//			contentType : "application/json",
//			data:JSON.stringify({
//				"userNumber":userNumber,
//				"passwordOld":oldPassword,
//				"passwordNew":newPassword,
//				"loginUserId":userId
//			}),
//			success:function(result){
//				if(result.statusCode=="200"){
//					$(".ok").css("display","block");
//					$(".ok").html("密码修改成功");
//					$(".login-error").css('display','none');
//					/*localStorage.removeItem("userId");
//					localStorage.removeItem("userNumber");
//					localStorage.removeItem("password");*/
//					localStorage.clear();
//					window.location.href="login.html";
//				   /* setTimeout(function(){
//						$(".ok").css("display","none");
//						$(".ok").html("");
//					},2000)	*/
//				}else{
//					$(".login-error").text("密码修改失败!");
//					$(".login-error").css('display','block');
//				}
//			}
//	   })
})
	