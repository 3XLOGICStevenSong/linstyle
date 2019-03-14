
$(function(){
    jeDate("#birthday",{
   	 format: "YYYY-MM-DD"
        //isinitVal: true,
       // valiDate:["0[4-7]$,1[1-5]$,2[58]$",true],
   });
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	
	//获取当前用户权限
	 var permission=localStorage.getItem("userNumber")+"permission";
	 //控制按钮显示
	var permissions= localStorage.getItem(permission);
	//客户查看权限标识
	var permissionSearch =0;//0:无权限 1:有权限
	var permissionAdd=0;//0:无权限 1:有权限
	var permissionView=0;//0:无权限 1:有权限
	var permissionEdit=0;//0:无权限 1:有权限
	var permissionPublished=0;//0:无权限 1:有权限
	var permissionUnpublished=0;//0:无权限 1:有权限

	if(permissions.length>0){
		var data= permissions.split(",");
		for(var i=0;i<data.length;i++){
           if(data[i]=="customer:query"){
        	   permissionSearch=1;
           }
           if(data[i]=="customer:create"){
        	   permissionAdd=1;
           }
           if(data[i]=="customer:view"||data[i]=="customer:update"||data[i]=="planend:query"||data[i]=="unpublish:search"){
        	   permissionView=1;
           };
           if(data[i]=="customer:update"){
        	   permissionEdit=1;
           }
           if(data[i]=="planend:query"){
        	   permissionPublished=1;
           }
           if(data[i]=="unpublish:search"){
        	   permissionUnpublished=1;
           }
		} 
	};
	
	if (permissionSearch==0){
		//渲染表格
		$("#search").hide();
	};
	if (permissionAdd==0){
		//渲染表格
		$("#add").hide();
	};
	//有一览权限
	if (permissionView==1){
		//渲染表格
		load();	
	};

	//渲染表格
	function load(){
		$("#table").bootstrapTable('destroy');
		$("#table").bootstrapTable({ // 对应table标签的id
			method : "post",
			url: urlstorage+"user/list", // 获取表格数据的url
			cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
			striped: true,  //表格显示条纹，默认为false
			pagination: true, // 在表格底部显示分页组件，默认false
			pageSize: 10, // 页面数据条数
			pageNumber: 1, // 首页页码
			sidePagination: 'server', // 设置为服务器端分页
			showHeader : true,
			queryParams:queryParams,
		    //设置header
			ajaxOptions: {
				beforeSend: function(request) {
				  request.setRequestHeader("Authorization", token);
				}	
			},
			columns : [
//				 {
//					field: 'userId', // 返回json数据中的name
//					title: '客户主键', // 表格表头显示文字
//					align: 'center', // 左右居中
//					valign: 'middle', // 上下居中
//					hidden:true,
//					visible: false
//				 },
				 {
					field: 'userNumber',// 返回json数据中的name
					title: '客户编号', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle' // 上下居中
				 }, 
				 {
					field: 'realName',// 返回json数据中的name
					title: '客户姓名',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
				
				 },
				 {
					field: 'mobile',// 返回json数据中的name
					title: '手机',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
				
				 },
				 {
					field: 'userAddr',// 返回json数据中的name
					title: '地址',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
				 },
				 {
					field: 'birthday',// 返回json数据中的name
					title: '生日',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
				 },
				 {
					field: 'sex',// 返回json数据中的name
					title: '性别',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter : function (value, row, index) {
						var text = '';
						if (row.sex == 1){
						   	text = "女";//性别转换
					    } else if (row.sex == 0){
					    	text = "男";//性别转换
			    		}
						    return text;
						}
				 },
				{
					field: 'userLevel',// 返回json数据中的name
					title: '星级',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
				
				},
				{
					field: 'userStatus',// 返回json数据中的name
					title: '状态',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter : function (value, row, index) {
						var text = '';
						if (row.userStatus == 0){
						   	text = "启用";//状态转换
					    } else if (row.userStatus == 1){
					    	text = "停用";//状态转换
			    		}
						    return text;
						}		
				},
				{
					field: 'userId', // 返回json数据中的name
					title: "操作",// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter: function (value, row, index) {
						return "<button type='button' class='btn btn-primary btn-size btn-pos published' >已发布</button><button type='button' class='btn btn-primary btn-size btn-pos unpublished '>未发布</button><button type='button' class='btn btn-primary btn-size btn-pos edit '>编辑</button></td>";
					},
					events: 'operateEvents'
				}
			],
			onLoadSuccess: function(data ){
				//没有发布，未发布，编辑权限
				if(permissionPublished==0&&permissionUnpublished==0&&permissionEdit==0){
					$('#table').bootstrapTable('hideColumn', 'userId');
				}else {
					//没有发布权限
					if(permissionPublished==0){
						$(".published").hide();
					} 
					//没有未发布编辑
					if(permissionUnpublished==0){
						$(".unpublished").hide();
					} 
					//没有权限编辑
					if(permissionEdit==0){
						$(".edit").hide();
					}
				}
				console.info("加载成功"+data.statusCode);
			},
			onLoadError: function(){
				//加载失败时执行
				window.location.href="login.html";
			    console.info("加载数据失败");
			}
		});
		//表格参数设置
		queryParams: function queryParams(params) {// 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
			return {
				limit : params.limit,// 每页要显示的数据条数
				offset : params.offset,// 每页显示数据的开始行号
				//服务类别
				userType : "1", //用户类型(1:客户)
				//服务工号
			    userNumber:$("#userNumber").val(),
			    //服务身份证号
			    idCard:$("#idCard").val(),
			    //服务姓名
			    realName:$("#realName").val(),
			    //服务手机号
			    mobile:$("#mobile").val(),   
			    //服务生日
			    birthday:$("#birthday").val(), 
			    //星级
			    userLevel:$("#userLevel").val()
			}
		};
		
	}
  //身份证号的验证(18位验证)
  jQuery.validator.addMethod("isCard", function(value,element) {
	    var regExp=/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/   
	    return this.optional(element) || (regExp.test(value));    
	});
	//入力项验证方法
	function formValidate(){
		 validateResult= $("#customerForm").validate({
			onkeyup : function(userNumber,idCard,realName,mobile){
				$("#userNumber").valid();
				$("#idCard").valid();
				$("#realName").valid();
				$("#mobile").valid();
			},
		     rules: {
		    	userNumber : {
		    		maxlength:8
			    },
			    idCard:{
			    	isCard:true
			    },
			    realName : {
			    	maxlength:8
		        },
		        mobile : {
		        	digits:true,
		        	rangelength:[11,11]
		        }
		        
		     },
		     messages: {
		    	userNumber : {
		    		maxlength:"客户编号长度不能大于8位"
			    },
			    idCard:{
			    	isCard:"客户身份证号长度必须为15位或18位"
			    },
			    realName : {
			    	maxlength:"客户姓名长度不能大于8字"
		        },
		        mobile : {
		        	digits:"客户手机号必须为数字",
		        	maxlength:"客户手机号长度必须为11位"
		        }
		     },
		    errorPlacement: function(error, element) {
		    	 error.appendTo(element.parent());
		    },
		 });
		 return validateResult;
	};	
			
	//点击查询
	$("#search").click(function(){
		// 提交之前进行验证，判断校验是否符合规则
	    if (formValidate().form()) { 
	    	//符合规则返回
	    	load();
	    }else{
	        // 不符合规则返回
	        return false;
	    } 
	});	

	//点击追加
	$("#add").on("click",function(){
		window.location.href="customer-add.html";
	});	

	window.operateEvents = {
		//点击已发布
		'click .published': function (e, value, row, index) {
			window.location.href = "published-care-plan-management.html?userNumber=" + row.userNumber ;
		},
		//点击未发布
		'click .unpublished': function (e, value, row, index) {
			window.location.href = "unpublished-care-plan-management.html?userNumber=" + row.userNumber ;
		},
		//编辑按钮点击事件
		'click .edit': function (e, value, row, index) {
			window.location.href = "customer-edit.html?userId=" + row.userId ;
		},
	};
});