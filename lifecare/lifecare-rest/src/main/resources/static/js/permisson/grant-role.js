//
$(function() {
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	if(urlstorage==null){
		 window.location.href="login.html";	
	}
	var userId = 0;
	$("userId").val(0);
	initData();
	//获取当前用户权限
	 var permission=localStorage.getItem("userNumber")+"permission";
	 //控制按钮显示
	var permissions= localStorage.getItem(permission);
	console.info(permissions);
	 if(permissions.length>0){
		var data= permissions.split(",");
       for(var i=0;i<data.length;i++){
           if(data[i]=="role:set"){
           	 $(".add").css("display","inline-block");
              }
           //break;
       } 
	 }
//初始化
	function initData(){
	$("#table").bootstrapTable({ // 对应table标签的id
		url : urlstorage+"permission/userRoles", // 获取表格数据的url
		cache : false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
		striped : true, //表格显示条纹，默认为false
		sidePagination : 'server', // 设置为服务器端分页
		clickToSelect:true,
		ajaxOptions: {
			beforeSend: function(request) {
			  request.setRequestHeader("Authorization", token);
			}	
			},
		queryParams : function(params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求

			return {
				userId : userId
			}
		},

		columns : [ {
			checkbox : true, 
			align : 'center',
			title : '选择项', 
			field : 'roleFlag',
			formatter : stateFormatter

		}, {
			field : 'roleName', 
			title : '角色名称', 
			align : 'center', 
			valign : 'middle' 
		}, {
			field : 'memo',
			title : '角色描述',
			align : 'center',
			valign : 'middle'

		}

		],
		onLoadSuccess : function(result) {
			//$(".add").at
			$('.add').attr("disabled","disabled");
			if (result.statusCode=="512") {
				Swal.fire({
					  type: 'error',
					  title: result.msg
				})
			}else if(result.statusCode == "401"){
				 window.location.href="login.html";
			}
			console.info("加载成功");
		},
		onLoadError : function() { //加载失败时执行
			console.info("加载数据失败");
		}

	});
	}
	 jQuery.validator.addMethod("paramcheckUser", function () {
		var param= $("#userId").val();
		 if(param==0||param==""){
			 return false;
		 }else{
			 return true;
		 }
	    }, "请选择工号");
	 jQuery.validator.addMethod("paramcheckRoler", function () {
			var param= $("#roleIds").val();
			 if(param.length==0||param==""){
				 return false;
			 }else{
				 return true;
			 }
		    }, "请选择角色");
	 
	//验证方法
		function formValidate(){
			  validateResult= $("#form").validate({
//				  onkeyup : function(roleName,memo){
//					  $("#roleName").valid();
//					  $("#memo").valid();
//				  },
			        rules: {
			        	userNumber: {
			                //required: true,
			                paramcheckUser:true
			            },
			            roleIds:{
			            	// required: true,
			            	paramcheckRoler:true
			            }
	            
			    },
			     messages: {
			    	 
			     },

			errorPlacement: function(error, element) {
		
			 error.appendTo(element.parent());
					},
			
			});
			  return validateResult;
			}
		
		function numberValidate(){
			  validateResult= $("#form").validate({
				  onkeyup : function(userNumber){
					  $("#userNumber").valid();
				  },
			        rules: {
			        	userNumber: {
			                required: true,
			                maxlength:16
			            },	            
			    },
			     messages: {
			    		userNumber: {
			                required: "工号不能为空",
			                maxlength:"工号长度不能大于16位"
			            },
			     },

			errorPlacement: function(error, element) {
		
			 error.appendTo(element.parent());
					},
			
			});
			  return validateResult;
			}
	$(".add").click(function(){
		//alert("userID"+userId)
		 var selects = $('#table').bootstrapTable('getSelections');
		var roleIds=new Array();
		if(selects.length>0){
		$.each(selects, function(index,item){
			if(item.roleId!=""){
				roleIds.push(parseInt(item.roleId));	
		// console.log(roleIds+"ids1");
				}		
			　});
		
		}	
		$("#roleIds").val(roleIds);
//		 if(userId=='0'){
//				$(".error").text("请选择员工");
//				$(".error").css('display','block');
//				return false;
//			}
//		if(roleIds.length<=0){
//			$("#ids").text("请选择角色");
//			$("#ids").css('display','block');
//			return false;
//		}
	
		//alert
		$.ajax({
			type:"get",
			url:urlstorage+"permission/roleGrant",
			data: {
				userId:userId,
				roleIds:roleIds.toString()
			},
			dataType: "json", 
			contentType : "application/json",
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", token);
				},
			success:function(result,status,httpResponse){
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
					});
					//如果更改的角色是当前登陆者的刷新权限
					if(localStorage.getItem("userId")==userId){
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
									}
								}else if(result.statusCode=="512"){
									//alert(result.msg);
									Swal.fire({
										  type: 'error',
										  title: result.msg
									})
								}else if(result.statusCode=="401"){
									window.location.href="login.html";
								}
							}
						})
					}
						
				}
				else if(result.statusCode=="512"){
					//alert(result.msg);
					Swal.fire({
						  type: 'error',
						  title: result.msg
					})
				}else if(result.statusCode=="401"){
					window.location.href="login.html";
				}
				}
			});
	});
	
//查询按钮
	$(".searchBtn").click(function(){
		 var userNumber=$("#userNumber").val();
		 if (numberValidate().form()) {
		$.ajax({
			type:"get",
			url:urlstorage+"userInfo/"+userNumber,
			dataType: "json", 
			contentType : "application/json",
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", token);
				},
			success:function(result,status,httpResponse){
				if (result.statusCode=="200" && result.msg!='用户不存在') {
					$("#info").text("姓名："+result.data.realName+"  "+ " 手机号:"+result.data.mobile);
					userId=result.data.userId;
					$("#userId").val(userId);
					//var token=httpResponse.getResponseHeader('Authorization');
//					if(token!=""&&token!=null){
//						localStorage.setItem("token", token); 
//					};
					$.ajax({
				type:"get",
				url:urlstorage+"permission/userRoles",
				dataType: "json", 
				contentType : "application/json",
				beforeSend: function(request) {
					request.setRequestHeader("Authorization", token);
					},
				data: {
					userId:userId
				},
				success:function(result,status,httpResponse){
					if (result.statusCode=="200"){
						$("#table").bootstrapTable('load', result);
						//var token=httpResponse.getResponseHeader('Authorization');
						//if(token!=""&&token!=null){
						//	localStorage.setItem("token", token); 
						//};
					
						$('.add').removeAttr("disabled");
					}else if (result.statusCode=="512") {
						Swal.fire({
							  type: 'error',
							  title: result.msg
						})
					}else if(result.statusCode == "401"){
						 window.location.href="login.html";
					}
				}
				});
					
					//alert("hhh");
				}else if(result.statusCode=="200" && result.msg=='用户不存在'){
					$('.add').attr("disabled","disabled");
					$("#info").text("");
					$("#userId").val("");
					Swal.fire({
						  type: 'error',
						  title: result.msg
					});
					$(":checkbox").prop("checked", false);
				}else if(result.statusCode=="512"){
					//alert(result.msg);
					Swal.fire({
						  type: 'error',
						  title: result.msg
					})
				}else if(result.statusCode=="401"){
					window.location.href="login.html";
				}else{
					//$("#userNumberError").css('display','block');
					///$("#userNumberError").text("用户不存在");
					Swal.fire({
						  type: 'error',
						  //title: '获取员工信息失败！',
						  title: result.msg
					})
					return false;
				} 
			}
	});
		}
		 
	});
	
	function stateFormatter(value, row, index) {
	    if (row.roleFlag == '1')
	        return {
	            checked : true//设置选中
	        };
	  // return value;
	};
//输入框获取焦点事件
	$("#userNumber").focus(function(){
		$('.add').attr("disabled","disabled");
		$("#info").text("");
	})
})
