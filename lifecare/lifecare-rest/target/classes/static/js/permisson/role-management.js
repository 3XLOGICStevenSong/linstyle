
$(function(){
	//获取缓存token
	var token=localStorage.getItem("token");
	var urlstorage=localStorage.getItem("url");
	if(urlstorage==null){
		 window.location.href="login.html";	
	}
	//获取当前用户权限
	 var permission=localStorage.getItem("userNumber")+"permission";
	 //控制按钮显示
	var permissions= localStorage.getItem(permission);
	console.info(permissions);
	//设置权限
	var settingFlag=0;
	//编辑权限
	var editFlag=0;
	//删除权限
	var deleteFlag=0;
	 if(permissions.length>0){
		var data= permissions.split(",");
        for(var i=0;i<data.length;i++){
            if(data[i]=="role:update"){
            	editFlag=1;
            }
            if(data[i]=="role:delete"){
            	deleteFlag=1;
            }
            if(data[i]=="role:set"){
            	settingFlag=1;
            }
            if(data[i]=="role:create"){
            	 $(".addRole").css("display","inline-block");
               }
            //break;
        } 
	 }
		//渲染表格
		initData();

	//渲染表格
	function initData(){
	//liuli 2019/02/01 start
	//获取session保存当前页码
	if(sessionStorage.getItem('role_pn')==null){
		pn = 1
	}else{
		pn = parseInt(sessionStorage.getItem('role_pn'))
	}
	//liuli 2019/02/01 end
	$("#table").bootstrapTable({ // 对应table标签的id

		url: urlstorage+"permission/getRoles", // 获取表格数据的url
		cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
		striped: true,  //表格显示条纹，默认为false
		pagination: true, // 在表格底部显示分页组件，默认false
		//pageList: [10, 20], // 设置页面可以显示的数据条数
		pageSize: 10, // 页面数据条数
		//liuli 2019/02/01 start
		pageNumber: pn, // 首页页码
		//liuli 2019/02/01 end
		sidePagination: 'server', // 设置为服务器端分页
		showHeader : true,
		queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求

			return {
				limit: params.limit, // 每页要显示的数据条数
				offset: params.offset, // 每页显示数据的开始行号
				}
		},
//设置header
		ajaxOptions: {
			beforeSend: function(request) {
			  request.setRequestHeader("Authorization", token);
			}	
			},

		columns: [
			
//			 {
//					field: 'roleId', // 返回json数据中的name
//					title: '角色Id', // 表格表头显示文字
//					align: 'center', // 左右居中
//					valign: 'middle', // 上下居中
//					hidden:true,
//					visible: false,
//				},
			{
				field: 'roleName', // 返回json数据中的name
				title: '角色名称', // 表格表头显示文字
				align: 'center', // 左右居中
				valign: 'middle' // 上下居中
			}, 
			{
				field: 'memo',
				title: '角色描述',
				align: 'center',
				valign: 'middle'
			
			},
			  {
				field: 'roleId',
				title: "操作",
				align: 'center',
				valign: 'middle',
				formatter: function (value, row, index) {
					var html="";
					if(settingFlag==1){
						html="<button type='button' class='btn btn-primary btn-size' onClick='settingPermission("+ row.roleId + ")'>设置</button>";	
					}
					if(editFlag==1){
						html+="<button type='button' class='btn btn-primary btn-size btn-pos edit'  data='"+row.roleId+"' onclick='edit("+ row.roleId + ")' >编辑</button>";
					}
					if(deleteFlag==1){
						html+="<button type='button' class='btn btn-default btn-size btn-pos  deleteTr'    data='"+row.roleId+"'>删除</button>";
					}
					return html;
                   
				},
				 events: 'operateEvents'
			}
			
		],
		//liuli 2019/02/01 start
		//页码跳转的时候保存当前页码
		onPageChange:function(number,size){
			sessionStorage.setItem('role_pn',number,size);
		},
		//liuli 2019/02/01 end
		onLoadSuccess: function(data ){
			     // alert(data)//加载成功时执行
			if(settingFlag==0&&editFlag==0&&deleteFlag==0 ){
				//没有权限编辑

				$('#table').bootstrapTable('hideColumn', 'roleId');
			}else if(data.statusCode=="512"){
				Swal.fire({
					  type: 'error',
					  //title: '删除失败！',
					  title: data.msg
				})
			}else if(data.statusCode == "401"){
				 window.location.href="login.html";
			}
			
				console.info("加载成功");
		},
		onLoadError: function(data){  //加载失败时执行
				
			//		window.location.href="login.html";
				
		}

	})	}

window.operateEvents = {
		//删除行
		'click .deleteTr': function (e, value, row, index) {
		
		$("#myModal").modal('show');
		$(".sure").on("click",function(){
			$.ajax({
				type:"delete",
				url:urlstorage+"permission/roleDelete/"+row.roleId,
				dataType: "json", 
				contentType : "application/json",
				beforeSend: function(request) {
					request.setRequestHeader("Authorization", token);
					},

					success:function(result){
					//弹出框显示
						if(result.statusCode=="200"){
//						$('#table').bootstrapTable('remove', {
//						field: 'roleId',
//						values: [row.roleId]
//						});
						//弹出框隐藏
					$("#myModal").modal('hide');
		
					Swal.fire({
						title: result.msg,
						type: 'success',
						showCancelButton: false,
						confirmButtonColor: '#3085d6',
						confirmButtonText: 'OK'
					});
					//initData();
					var refreshurl= urlstorage+"lifecare/api/permission/getRoles";
					$('#table').bootstrapTable('refresh',refreshurl);
					}else if(result.statusCode=="512"){
						$("#myModal").modal('hide');
						Swal.fire({
							  type: 'error',
							  //title: '删除失败！',
							  title: result.msg
						})
					}else if(result.statusCode == "401"){
						 window.location.href="login.html";
					}
					},
					 error : function(xhr,textStatus,errorThrown){
							
						 if(xhr.status == 401){
							 window.location.href="login.html";
						 }
				}
			});
			$(".sure").unbind();
		});
	}}
	


//添加角色
$(".addRole").on("click",function(){
	window.location.href="role-add.html";
	});
	})
// 点击编辑
 function edit(roleId) {
	window.location.href = "role-edit.html?"+window.btoa(unescape(encodeURIComponent("roleId=" + roleId)));
}
//设置角色
function settingPermission(roleId){
	window.location.href = "permissions-settings.html?"+window.btoa(unescape(encodeURIComponent("roleId=" + roleId)));
}

