
$(function(){
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	if(urlstorage==null){
		 window.location.href="login.html";	
	}
	//选择人员信息初始化
    var url = location.search; 
	var roleId="";
    if(url!=null && url!=""){
    var params=jQuery.getParam(url);
    roleId=params['roleId']; 
	//获取URL中参数
    }
	//alert(roleId);
	//var params=window.location.href.indexOf("=")+1,window.location.href.lastIndexOf("&");

	$.ajax({
		type:"get",
		url:urlstorage+"permission/roleDetail/"+roleId,
		//data: {
		//	roleId:roleId
		//},
		beforeSend: function(request) {
			request.setRequestHeader("Authorization", token);
			},
		async: false,
		dataType: "json", 
		contentType : "application/json",
		success:function(result,status,httpResponse){
			if(result.statusCode=="200"){
				//token是否过期过期更换token
				var token=httpResponse.getResponseHeader('Authorization');
				if(token!=""&&token!=null){
					localStorage.setItem("token", token); 
				};
				$("#roleName").val(result.data.roleName);
				
				$("#memo").val(result.data.memo);
			}else if(result.statusCode=="512"){
				Swal.fire({
					  type: 'error',
					  //title: '保存失败！',
					  title: result.msg
				})
			}else if(result.statusCode == "401"){
				 window.location.href="login.html";
			}
		}
});
   
	 jQuery.validator.addMethod("checkEnter", function (value, element) {
    	 var paramValue=value.replace(/\s+/g, ""); 
    	 if(paramValue==""||paramValue.length==0){
    		 return false;
    	 }else{
    		 return true
    	 }   
    	}, "角色名称不能为全空格!");
	//验证方法
	function formValidate(){
		  validateResult= $("#form").validate({
			  onkeyup : function(roleName,memo){
				  $("#roleName").valid();
				  $("#memo").valid();
			  },
		        rules: {
		        	roleName: {
		                required: true,
		                maxlength:100,
		                checkEnter:true
		            },
		            memo:{
		            	//checkEnter:true,
		            	maxlength:500
		            }
		            
		    },
		     messages: {
		    	 roleName: {
		                required: "角色名称不能为空",
		                maxlength:"角色名称长度不能大于100字"
		            },
		           
		            memo: {
		            	  maxlength:"角色描述长度不能大于500字"
		         
		            }
		     },

		errorPlacement: function(error, element) {
	
		 error.appendTo(element.parent());
				},
		
		});
		  return validateResult;
		}
	//初始化画面

//返回按钮
$(".back").click(function() {
	window.location.href = "role-management.html"
	});
	// 保存
$(".save").click(function() {
	
		 if (formValidate().form()) {
			 var roleName = $("#roleName").val();
			 var memo = $("#memo").val();
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
			success : function(result,status,httpResponse){
				if(result.statusCode=="200"){
					//token是否过期过期更换token
					var token=httpResponse.getResponseHeader('Authorization');
					if(token!=""&&token!=null){
						localStorage.setItem("token", token); 
					};
					Swal.fire({
						title: result.msg,
						type: 'success',
						showCancelButton: false,
						confirmButtonColor: '#3085d6',
						confirmButtonText: 'OK'
					}).then(function(result){
							//返回管理页面
							window.location.href="role-management.html";
					})
				}else if(result.statusCode=="512"){
					Swal.fire({
						  type: 'error',
						  //title: '添加失败！',
						  title: result.msg
					})
				}else if(result.statusCode == "401"){
					 window.location.href="login.html";
				}
			}
	})
		 }
})
})