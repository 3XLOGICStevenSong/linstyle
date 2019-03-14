//角色添加>定义角色功能
var setting = {
	check : {
		enable : true,
		chkDisabledInherit : true,
		url: "javascript:void(0)",
		 showLine : false, 
	},
	
	//树型结构设置
	data : {
		simpleData : {
			enable : true,
			idKey : "id", // 修改默认的ID为自己的id
			pIdKey : "pId", // 修改默认父级ID为自己数据的父级id
			rootPId : 0,
			url: "javascript:void(0)",
		// 根节点的父节点id
		}
	},
};

$(document).ready(function(){
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	if(urlstorage==null){
		 window.location.href="login.html";	
	}
	//初始化表单验证
	formValidate();  
	//获取参数
	var roleId="";
	 var url = location.search; 
	 if(url!=null && url!=""){
		    var params=jQuery.getParam(url);
		    roleId=params['roleId']; 
			//获取URL中参数
		    }
	//var roleId=window.location.href.substring(window.location.href.lastIndexOf("=")+1,window.location.href.length);
	var selectList="";
	//初始化角色列表
	$.ajax({
		type:"get",
		url:urlstorage+"permission/allRole",
		dataType: "json", 
		contentType : "application/json",
		 beforeSend: function(request) {
				request.setRequestHeader("Authorization", token);
				},
		success:function(result){
			if (result.statusCode=="200") {
				selectList+="<option value='0'>请选择</option>"
				$.each(result.data, function(index,item){
					selectList+="<option  value="+item.roleId+">"+item.roleName+"</option>"
				});
				$("#selectRole").html("");
				$("#selectRole").append(selectList);
				//下拉列表设置默认选中值
				$("#selectRole option[value='"+roleId+"']").attr("selected", true);
		
			}else if(result.statusCode=="512"){
				Swal.fire({
					  type: 'error',
					  title: result.msg
				})
			}else if(result.statusCode == "401"){
				 window.location.href="login.html";
			}
			},
	error:function(res) {
	console.error("出错了" + res);
}
		});
	//获取资源树形结构
	$.ajax({
		type:"get",
		url:urlstorage+"permission/resourceTree",
		dataType: "json", 
		contentType : "application/json",
		data:{
			roleId:roleId
		},
		 beforeSend: function(request) {
				request.setRequestHeader("Authorization", token);
				},
		success:function(result){
			$.fn.zTree.init($("#treeDemo"), setting, result.data.resourceList);
			}
		});
//选择角色
		$("#selectRole").on("change",function(){
	var roleId=$("#selectRole option:selected").val();
	  if (formValidate().form()) { 
	$.ajax({
		type:"get",
		url:urlstorage+"permission/resourceTree",
		dataType: "json", 
		contentType : "application/json",
		 beforeSend: function(request) {
				request.setRequestHeader("Authorization", token);
				},
		data:{
			roleId:roleId
		},
		success:function(result){
			$.fn.zTree.init($("#treeDemo"), setting, result.data.resourceList);
			if(result.statusCode=="512"){
				Swal.fire({
					  type: 'error',
					  title: result.msg
				})
			}else if(result.statusCode == "401"){
				 window.location.href="login.html";
			}
		}
		});}else{
			return false;
		}
	})
	//保存按钮
	$(".add").on("click",function(){
		if (formValidate().form()) { 
		// var zTreeOjb = $.fn.zTree.getZTreeObj("treeDemo"); //ztree的Id zTreeId
		// var checkedNodes = zTreeObj.getCheckedNodes();
//		var roleId=$("#selectRole option:selected").val();
//		 if(roleId==""||roleId==0){
//				$(".error").text("请选择角色");
//				$(".error").css('display','block');
//				return false;
//			 }else{
//				 $(".error").text("");
//					$(".error").css('display','none');
//			 }
		 var zTreeObj=$.fn.zTree.getZTreeObj("treeDemo");
		 //获取选择的checkbox
		var checkedNodes=zTreeObj.getCheckedNodes();
		var ids=new Array();
		var idlist="";
		console.info(checkedNodes.length+"checkedNodes.length")
		if(checkedNodes.length>0){
		$.each(checkedNodes, function(index,item){
			if(item.id!=""){ids.push(parseInt(item.id));
		 console.log(ids+"ids1");
				}
			　})
			 var idlist=ids.toString();
		}
			//alert(idlist+"idlist")
			 $.ajax({
				type:"post",
				url:urlstorage+"permission/roleResource",
				data:JSON.stringify( {
					roleId:roleId,
					ids:idlist
				}),
				 beforeSend: function(request) {
		 				request.setRequestHeader("Authorization", token);
		 				},
				dataType: "json", 
				contentType : "application/json",
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
						}).then(function(){
							//返回管理页面
							window.location.href="role-management.html";
					})
					
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
			 console.log(ids+"ids");
		}else{
			return false;
		}
		
		});
		//点击取消按钮
//		$(".cancel").on("click",function(){
//			 var zTreeObj=$.fn.zTree.getZTreeObj("treeDemo");
//			 zTreeObj.checkAllNodes(false);
//			 console.info(localStorage.getItem("token")+"token")
//		})
		//点击取消按钮
		$(".cancel").on("click",function(){
			window.location.href = "role-management.html";
		})
	//角色选择
		$("#selectRole").change(function(){
			if (formValidate().form()) { 
				return true;
			}else{
				return false;
			}
//			var roleId=$("#selectRole option:selected").val();
//			 if(roleId==""||roleId==0){
//					$(".error").text("请选择角色");
//					$(".error").css('display','block');
//					return false;
//				 }else{
//					 $(".error").text("");
//						$(".error").css('display','none');
//				 }
			});
		//验证方法
		function formValidate(){
			  validateResult= $("#form").validate({
//				  onkeyup : function(roleName,memo){
//					  $("#roleName").valid();
//					  $("#memo").valid();
//				  },
			        rules: {
			        	selectRole: {
			                //required: true,
			                paramcheckRole:true
			            },
			           
	            
			    },
			     messages: {
			    	 
			     },

			errorPlacement: function(error, element) {
		
			 error.appendTo(element.parent());
					},
			
			});
			  return validateResult;
			}
		 jQuery.validator.addMethod("paramcheckRole", function () {
			 var roleId=$("#selectRole option:selected").val();
			 if(roleId==""||roleId==0){
				
					return false;
				 }else{
					 return true;
				 }
			    }, "请选择角色");
});