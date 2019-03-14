
$(function(){
	//获取缓存token
	var token=localStorage.getItem("token");
	var urlstorage=localStorage.getItem("url");
	
//渲染表格
	$("#table").bootstrapTable({ // 对应table标签的id

		url: urlstorage+"permission/getRoles", // 获取表格数据的url
		cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
		striped: true,  //表格显示条纹，默认为false
		pagination: true, // 在表格底部显示分页组件，默认false
		//pageList: [10, 20], // 设置页面可以显示的数据条数
		pageSize: 10, // 页面数据条数
		pageNumber: 1, // 首页页码
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
		// 返回数据格式处理（如果后台返回数据格式不是rows）
		// responseHandler: function(res) {
		// return {
		// "total": res.data.total,//总页数
		// "rows": res.data.itemsList //数据
		// };
		//},

		columns: [
			
			 {
					field: 'roleId', // 返回json数据中的name
					title: '角色Id', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle', // 上下居中
					hidden:true,
					visible: false,
				},
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
				title: "操作",
				align: 'center',
				valign: 'middle',
				formatter: function (value, row, index) {
					return "<button type='button' class='btn btn-primary btn-size' onClick='settingPermission("+ row.roleId + ")'>设置</button><button type='button' class='btn btn-primary btn-size btn-pos edit'  data='"+row.roleId+"' onclick='edit("+ row.roleId + ")' >编辑</button><button type='button' class='btn btn-default btn-size btn-pos  deleteTr'    data='"+row.roleId+"'>删除</button></td>";
                   
				},
				 events: 'operateEvents'
			}
			
		],
		onLoadSuccess: function(data ){
			     // alert(data)//加载成功时执行
			
				console.info("加载成功");
		},
		onLoadError: function(data){  //加载失败时执行
				
			//		window.location.href="login.html";
				
		}

	})	

window.operateEvents = {
		//删除行
		'click .deleteTr': function (e, value, row, index) {
		$(".ok").css('display','none'); 
		$("#myModal").modal('show');
		$(".sure").on("click",function(){
			$.ajax({
				type:"put",
				url:urlstorage+"permission/roleUpdate",
				dataType: "json", 
				contentType : "application/json",
				beforeSend: function(request) {
					request.setRequestHeader("Authorization", token);
					},
					data:JSON.stringify( {
					roleId:row.roleId,
					status:0
					}),
					success:function(result){
					//弹出框显示
						$('#table').bootstrapTable('remove', {
						field: 'roleId',
						values: [row.roleId]
						});
						//弹出框隐藏
					$("#myModal").modal('hide');
					$(".ok").css('display','block'); 
					$(".ok").text("删除成功");
					var refreshurl= urlstorage+"lifecare/api/permission/getRoles";
					$('#table').bootstrapTable('refresh',refreshurl);
					},
					 error : function(xhr,textStatus,errorThrown){
							
						 if(xhr.status == 401){
							 window.location.href="login.html";
						 }
				}
			});
		});
	}}
	


//添加角色
$(".addRole").on("click",function(){
	window.location.href="role-add.html";
	});
	})
// 点击编辑
 function edit(roleId) {
	window.location.href = "role-edit.html?roleId=" + roleId;
}
//设置角色
function settingPermission(roleId){
	window.location.href = "permissions-settings.html?roleId=" + roleId;
}

