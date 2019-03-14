//
$(function() {
	var urlstorage=localStorage.getItem("url");
	var userId = 0;
	//$(".save").click(function() {}
	////渲染表格
//	
	$("#table").bootstrapTable({ // 对应table标签的id
		url : urlstorage+"permission/userRoles", // 获取表格数据的url
		cache : false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
		striped : true, //表格显示条纹，默认为false
		sidePagination : 'server', // 设置为服务器端分页
		clickToSelect:true,
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
		onLoadSuccess : function() {
			// alert(data)//加载成功时执行
			console.info("加载成功");
		},
		onLoadError : function() { //加载失败时执行
			console.info("加载数据失败");
		}

	});
	
	$(".add").click(function(){
		//alert("userID"+userId)
		 var selects = $('#table').bootstrapTable('getSelections');
		var roleIds=new Array();
		if(selects.length>0){
		$.each(selects, function(index,item){
			if(item.roleId!=""){
				//alert(item.roleId);
				roleIds.push(parseInt(item.roleId));
		 console.log(roleIds+"ids1");
				}		
			　});}	
		 if(userId=='0'){
				$(".error").text("请选择员工");
				$(".error").css('display','block');
				return false;
			}
		if(roleIds.length<=0){
			$(".error").text("请选择角色");
			$(".error").css('display','block');
			return false;
		}
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
			success:function(result){
				
				if (result.statusCode=="200") {
					$(".ok").css('display','block'); 
					$(".error").css('display','none');
				}
				}
			})
	})
//查询按钮
	$(".searchBtn").click(function(){
		$(".error").css('display','none');
		 var userNumber=$("#userNumber").val();
		 if(userNumber==''){
				$(".error").text("请选择员工");
				$(".error").css('display','block');
				return false;
		 }
		$.ajax({
			type:"get",
			url:urlstorage+"userInfo/"+userNumber,
			dataType: "json", 
			contentType : "application/json",
			//data: {
			//	userNumber:userNumber
			//},
			success:function(result){
				//姓名：张三 &nbsp; 手机号：13888888888
				//alert(result.returnCode+"ss")
				if (result.statusCode=="200" && result.msg!='用户不存在') {
					$("#info").text("姓名："+result.data.realName+"  "+ " 手机号:"+result.data.mobile);
					userId=result.data.userId;
					
					$.ajax({
				type:"get",
				url:urlstorage+"permission/userRoles",
				dataType: "json", 
				contentType : "application/json",
				data: {
					userId:userId
				},
				success:function(result){
					$("#table").bootstrapTable('load', result);
				}
				})
					
					//alert("hhh");
				}else{
					$(".error").text("用户不存在");
					$(".error").css('display','block');
					return false;
				} 
			}
	})
		
	});
	
	function stateFormatter(value, row, index) {
	    if (row.roleFlag == '1')
	        return {
	            checked : true//设置选中
	        };
	  // return value;
	};

})
