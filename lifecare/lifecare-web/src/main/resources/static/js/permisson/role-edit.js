
$(function(){
	//获取缓存token
	var token=localStorage.getItem("token"); 
	//获取URL中参数
	var roleId=window.location.href.substring(window.location.href.lastIndexOf("=")+1,window.location.href.length);
	//alert(roleId);
	//var params=window.location.href.indexOf("=")+1,window.location.href.lastIndexOf("&");
	//初始化画面
	$.ajax({
		type:"get",
		url:"http://localhost:8081/lifecare/api/permission/roleDetail/"+roleId,
		//data: {
		//	roleId:roleId
		//},
		beforeSend: function(request) {
			request.setRequestHeader("Authorization", token);
			},
		async: false,
		dataType: "json", 
		contentType : "application/json",
		success:function(result){
			if(result.statusCode=="200"){
				$("#roleName").val(result.data.roleName);
				
				$("#memo").val(result.data.memo);
			}
		}
})
//返回按钮
$(".back").click(function() {
	window.location.href = "role-management.html"
	})
	// 保存
$(".save").click(function() {
	var roleName = $("#roleName").val();
	//角色Check
	if(roleName==''){
		$(".error").text("角色名称不能为空");
		$(".error").css('display','block');
		return false;
		}
	//角色描述
		var memo = $("#memo").val();
		if(memo==''){
			$(".error").text("角色描述不能为空");
			$(".error").css('display','block');
			return false;
		}
		//更新服务器 
		$.ajax({
			type : "put",
			url : urlstorage+"permission/roleUpdate",
			async: false,
			dataType: "json", 
			contentType : "application/json",
			 beforeSend: function(request) {
	 			request.setRequestHeader("Authorization", token);
	 			},
			data : JSON.stringify({
				roleId : roleId,
				roleName : roleName,
				memo : memo
			}),
			success : function(result) {
				if (result.statusCode=="200") {
					//显示成功信息
					$(".ok").css('display','block'); 
					$(".error").css('display','none');
				}
			}
	})
	})
})