
$(function(){
	//获取token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	//返回按钮
	$(".back").click(function(){
		window.location.href="role-management.html"
	});
	//保存按钮
		$(".save").click(function(){
		//角色名称	
		var roleName=$("#roleName").val();
		//角色名称	check
		if(roleName==''){
			$(".error").text("角色名称不能为空");
			$(".error").css('display','block');
			return false;
		}
		//角色描述	check
		var memo=$("#memo").val();
		if(memo==''){
			$(".error").text("角色描述不能为空");
			$(".error").css('display','block');
			return false;
			
		}
		//请求Server
		$.ajax({
			type:"post",
			url:urlstorage+"permission/roleCreate",
			dataType: "json", 
			contentType : "application/json",
			 beforeSend: function(request) {
 				request.setRequestHeader("Authorization", token);
 				},
			data : JSON.stringify({
				roleName:roleName,
				memo:memo
			}),
	
			success:function(result){
				if (result.statusCode=="200") {
					$(".ok").css('display','block'); 
					$(".error").css('display','none');
				}
			}

		
	})
	})
})