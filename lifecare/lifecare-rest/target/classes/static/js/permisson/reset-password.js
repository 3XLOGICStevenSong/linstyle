$(function(){
	//获取缓存token
	var token=localStorage.getItem("token");
	var url=localStorage.getItem("url");
	if(url==null){
		 window.location.href="login.html";	
	}
    var userId=localStorage.getItem("userId");
	formValidate();
    jQuery.validator.addMethod("isUserNumber", function(value,element) {   
    	 var regExp=/^[a-zA-Z0-9]{1,16}$/   
    	 return this.optional(element) || (regExp.test(value));    
    });
   function formValidate(){
	   validateResult = $("#resetForm").validate({
//		   onkeyup:function(userNumber){
//			   $("#userNumber").valid();
//		   },
	        rules: {
	        	userNumber:{
	        		required:true,
	        		isUserNumber:true
	        	}
	        },
	        messages: {
	        	userNumber:{
	        		required:"登录名不能为空",
	        		isUserNumber:"登录名必须为1-16位的字母或数字"
	        	}
	         },
	      errorPlacement: function(error, element) {
	          error.appendTo(element.parent());
	       },
	    })
	    return validateResult;
   }
    $(".reset").on("click",function(){
    	if(formValidate().form()){
    		var userNumber=$("#userNumber").val();
    		$.ajax({
				type:"post",
				url:url+"user/pwdReset",
				dataType: "json", 
				contentType : "application/json",
				 beforeSend: function(request) {
	 				request.setRequestHeader("Authorization", token);
	 			 },
				data:JSON.stringify({
					"userNumber":userNumber,
					"loginUserId":userId
				}),
				success:function(result){
					if(result.statusCode=="200"){
						$("#show").html("密码被重置为"+result.data.password);
						//alert("重置成功");
						Swal.fire({
							  type: 'success',
							  title: result.msg,
							  //text: "密码被重置为"+result.data.password
							  //html: '<span style="font-size:18px">密码被重置为：'+result.data.password+'</span>'
						})
						//$("#show").html("密码被重置为"+result.data.password);
						
						//重置密码
						localStorage.setItem("password",result.data.password);
					}else if(result.statusCode=="512"){
						//$("#show").html("");
						//alert("重置失败");
						Swal.fire({
							  type: 'error',
							  title: result.msg
						})
					}else if(result.statusCode == "401"){
						 window.location.href="login.html";
					}
				}
		   })
    	}
    });
    
    $("#userNumber").on("focus",function(){
    	$("#show").html("");})
})