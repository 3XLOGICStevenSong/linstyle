
$(function(){
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	var loginUserId=localStorage.getItem("userId");
	$("#loginUserId").val(loginUserId);

	//初始化画面
	$.ajax({
		type:"get",
		url:urlstorage+"homePageInfo",
		beforeSend: function(request) {
			request.setRequestHeader("Authorization", token);
			},
		async: false,
		dataType: "json", 
		contentType : "application/json",
		success:function(result){
			if(result.statusCode=="200"){
				//培训信息
				$("#trainInfo").val(result.data.trainInfo);
				//驿站信息
				$("#dakInfo").val(result.data.dakInfo);
				//巡回车信息
				$("#tourBusInfo").val(result.data.tourBusInfo);	
				//其他信息
				$("#dutyInfo").val(result.data.dutyInfo);					
			}
		}
	});
	//保存按钮
	$(".save").click(function() {
		//培训信息
		var trainInfo =  $("#trainInfo").val();
		//驿站信息
		var dakInfo =  $("#dakInfo").val();
		//巡回车信息
		var tourBusInfo =  $("#tourBusInfo").val();
		//其他信息
		var dutyInfo =  $("#dutyInfo").val();
		//登录用户ID
		var loginUserId =  $("#loginUserId").val();
		var data = {
				"trainInfo" : trainInfo,
				"dakInfo" : dakInfo,
				"tourBusInfo" : tourBusInfo,
				"dutyInfo" : dutyInfo,
				"loginUserId" : loginUserId
			}
		//更新服务器 
		$.ajax({
			type : "post",
			url : urlstorage+"homePageInfo",
			async: false,
			dataType: "json", 
			contentType : "application/json",
			beforeSend: function(request) {
 			request.setRequestHeader("Authorization", token);
 				},
 			data : JSON.stringify(data),
 			success : function(result) {
 				if (result.statusCode=="200") {
 					alert("成功");
 					/*$.messager.show({ 
					title:'提示',msg:'保存成功', 
					showType:'fade',
					style:{right:'',bottom:''} ,
				 	timeout: 300000,
					});*/ 
 				}else{
 					return false;
 				}
 			}
		})
	});
})