
$(document).ready(function() {
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	if(urlstorage==null){
		 window.location.href="login.html";	
	}
	//获取当前用户权限
	 var permission=localStorage.getItem("userNumber")+"permission";
	 //控制按钮显示
	var permissions= localStorage.getItem(permission);
	 if(permissions.length>0){
		var data= permissions.split(",");
         for(var i=0;i<data.length;i++){
             if(data[i]=="password:update"){
                 $("#password").css("display","block");
             }
             if(data[i]=="order:query"){
                 $("#pending").css("display","block");
                 $("#notice").css("display","block");
             }
             //break;
         } 
	 }
	
	//初始化数据(获取未处理订单数)
		$.ajax({

			type : "get",
			url : urlstorage+"task/pending",
			datatype : "json",
			contentType : "application/json;charset=utf-8",
			async : false,
			  beforeSend: function(request) {
  				request.setRequestHeader("Authorization", token);
  				},
			success : function(result,status,httpResponse) {
				if (result.statusCode == "200") {
					//token是否过期过期更换token
					var token=httpResponse.getResponseHeader('Authorization');
					if(token!=""&&token!=null){
						localStorage.setItem("token", token); 
					}
					if(result.data.pendingCnt>0){
					$("#notice").html("<font>"+result.data.pendingCnt+"</font>");}
				}else if(result.statusCode == "401"){
					 window.location.href="login.html";
				}else if(result.statusCode=="512"){
    				Swal.fire({
    					  type: 'error',
    					  title: result.msg
    				})
    			}
			},
			 error : function(xhr,textStatus,errorThrown){
					
				 if(xhr.status == 401){
					 window.location.href="login.html";
				 }
		}
		});
	//定时刷新方法
	function initData(){
		//alert("<<<<<<<<")
		$.ajax({
		type : "get",
		url :  urlstorage+"task/pending",
		datatype : "json",
		contentType : "application/json;charset=utf-8",
		async : false,
		beforeSend: function(request) {
				request.setRequestHeader("Authorization", token);
				},
		success : function(result) {
			if (result.statusCode == "200") {
				//token是否过期过期更换token
				var token=httpResponse.getResponseHeader('Authorization');
				if(token!=""&&token!=null){
					localStorage.setItem("token", token); 
				}
				if(result.data.pendingCnt>0){
				$("#notice").html("<font>"+result.data.pendingCnt+"</font>");}
			}else if(result.statusCode=="401"){
				window.location.href="login.html";
			}else if(result.statusCode=="512"){
				Swal.fire({
					  type: 'error',
					  title: result.msg
				})
			}
		}
	});
}
//定数刷新（待处理订单）
	setInterval(initData, 600000);	
	//修改密码连接
	$(document).on("click","#password",function(){
	
	window.location.href = "modify-password.html";
	});
	//退出登录连接
	$(document).on("click","#logout",function(){
		var permission=localStorage.getItem("userNumber")+"permissions"
		localStorage.removeItem(permission);
		window.location.href = "login.html";
		});
	//待处理
	$(document).on("click",".pending",function(){
		
		window.location.href = "service-task-management.html?"+window.btoa(unescape(encodeURIComponent("pendingFlag=0")));
		pendingFlag=0
		});
	
})
