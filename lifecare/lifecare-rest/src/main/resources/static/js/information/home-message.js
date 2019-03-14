
$(function(){
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	if(urlstorage==null){
		 window.location.href="login.html";	
	}
	var loginUserId=localStorage.getItem("userId");
	$("#loginUserId").val(loginUserId);
	//获取当前用户权限
	 var permission=localStorage.getItem("userNumber")+"permission";
	 //控制按钮显示
	var permissions= localStorage.getItem(permission);
	console.info(permissions);
	 if(permissions.length>0){
		var data= permissions.split(",");
      for(var i=0;i<data.length;i++){
          if(data[i]=="home:update"){
          	 $(".save").css("display","inline-block");
             }
          //break;
      } 
	 }
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
				$("#title1").val(result.data.title1);
				//驿站信息
				$("#content1").val(result.data.content1);
				//巡回车信息
				$("#title2").val(result.data.title2);	
				//其他信息
				$("#content2").val(result.data.content2);
				//培训信息
				$("#title3").val(result.data.title3);
				//驿站信息
				$("#content3").val(result.data.content3);
				//巡回车信息
				$("#title4").val(result.data.title4);	
				//其他信息
				$("#content4").val(result.data.content4);	
			}else if(result.statusCode=="401"){
				window.location.href="login.html";		
			}else if (result.statusCode=="512") {
				Swal.fire({
					  type: 'error',
					  title: result.msg
				})
			}
		}
	});
	//保存按钮
	$(".save").click(function() {
		// 提交之前进行验证
		if (formValidate().form()) { //判断校验是否符合规则
			//培训信息
			var title1 =  $("#title1").val();
			//驿站信息
			var content1 =  $("#content1").val();
			//巡回车信息
			var title2 =  $("#title2").val();
			//其他信息
			var content2 =  $("#content2").val();
			//登录用户ID
			var title3 =  $("#title3").val();
			//培训信息
			var content3 =  $("#content3").val();
			//驿站信息
			var title4 =  $("#title4").val();
			//巡回车信息
			var content4 =  $("#content4").val();
			//登录用户ID
			var loginUserId =  $("#loginUserId").val();
			var data = {
					"title1" : title1,
					"content1" : content1,
					"title2" : title2,
					"content2" : content2,
					"title3" : title3,
					"content3" : content3,
					"title4" : title4,
					"content4" : content4,
					"loginUserId":loginUserId
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
	 					Swal.fire({
							title: result.msg,
							type: 'success',
							showCancelButton: false,
							confirmButtonColor: '#3085d6',
							confirmButtonText: 'OK'
						}); 
	 				}else if(result.statusCode=="401"){
	 					window.location.href="login.html";		
	 				}else if (result.statusCode=="512") {
	 					Swal.fire({
	 						  type: 'error',
	 						  title: result.msg
	 					})
	 				}else{
	 					return false;
	 				}
	 			}
			})
		}
	});
	//liuli 2019/02/02 start
	//数值不全为空的验证
    jQuery.validator.addMethod("trim", function(value,element) {
    	     //debugger;
	      	 return $.trim(value)!="";
	});
	//验证方法
	function formValidate(){
		validateResult= $("#missionForm").validate({
		    rules:{
		    	title1:{
		    		required: true,
		    		trim:"#title1",
		    		maxlength:8
		        },
		    	title2:{
		    		required: true,
		    		trim: "#title2",
		    		maxlength:8
		        },
		    	title3:{
		    		required: true,
		    		trim: "#title3",
		    		maxlength:8
		        },
		    	title4:{
		    		required: true,
		    		trim: "#title4",
		    		maxlength:8
		        },
		        content1:{
		    		maxlength:4000
		        },
		        content2:{
		    		maxlength:4000
		        },
		        content3:{
		    		maxlength:4000
		        },
		        content4:{
		    		maxlength:4000
		        }
		    },
		    messages: {
		    	title1:{
		    		required: "标题1不能为空",
				    trim: "标题1不能为全空格",
		    		maxlength: "标题1长度不能大于8字"
		        },
		        title2:{
		        	required: "标题2不能为空",
				    trim: "标题2不能为全空格",
		        	maxlength: "标题2长度不能大于8字"
		        },
		        title3:{
		        	required: "标题3不能为空",
				    trim: "标题3不能为全空格",
					maxlength: "标题3长度不能大于8字"
		        },
		        title4:{
		        	required: "标题4不能为空",
				    trim: "标题4不能为全空格",
		        	maxlength: "标题4长度不能大于8字"
		        },
		        content1:{
		    		maxlength: "内容1长度不能大于4000字"
		        },
		        content2:{
		        	maxlength: "内容2长度不能大于4000字"
		        },
		        content3:{
					maxlength: "内容3长度不能大于4000字"
		        },
		        content4:{
		        	maxlength: "内容4长度不能大于4000字"
		        }
		    },
		    errorPlacement: function(error, element) {
		    	error.appendTo(element.parent());
			}
		});
		return validateResult;
	}
	//liuli 2019/02/02 end
})