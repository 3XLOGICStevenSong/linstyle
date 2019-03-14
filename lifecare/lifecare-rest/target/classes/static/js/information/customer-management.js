
$(function(){
    jeDate("#birthday",{
   	 format: "YYYY-MM-DD"
        //isinitVal: true,
       // valiDate:["0[4-7]$,1[1-5]$,2[58]$",true],
   });
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
	//客户查看权限标识
	var permissionSearch =0;//0:无权限 1:有权限
	var permissionAdd=0;//0:无权限 1:有权限
	var permissionView=0;//0:无权限 1:有权限
	var permissionEdit=0;//0:无权限 1:有权限
	var permissionPublished=0;//0:无权限 1:有权限
	var permissionUnpublished=0;//0:无权限 1:有权限
	var permissionDelete=0;//0:无权限 1:有权限
	if(permissions.length>0){
		var data= permissions.split(",");
		for(var i=0;i<data.length;i++){
           if(data[i]=="customer:query"){
        	   permissionSearch=1;
           }
           if(data[i]=="customer:create"){
        	   permissionAdd=1;
           }
           if(data[i]=="customer:update"){
        	   permissionEdit=1;
           }
           if(data[i]=="planend:query"){
        	   permissionPublished=1;
           }
           if(data[i]=="unpublish:query"){
        	   permissionUnpublished=1;
           }
           if(data[i]=="customer:delete"){
        	   permissionDelete=1;
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
	if (permissionDelete==0){
		//渲染表格
		$(".delete").hide();
	};
/*	//有一览权限
	if (permissionView==1){

	};*/
	formValidate();
	//渲染表格
	load();	
	//渲染表格
	function load(){
		//liuli 2019/02/01 start
		//获取session查询条件
		if(sessionStorage.getItem('cus_Manage_Cnds')!=null){
			var Srn_Cnds_JsonStr = sessionStorage.getItem('cus_Manage_Cnds');
			var cus_Manage_Cnds = JSON.parse(Srn_Cnds_JsonStr);
		    // 客户编号
			$("#userNumber").val(cus_Manage_Cnds.userNumber),
		    // 客户身份证号
			$("#idCard").val(cus_Manage_Cnds.idCard),
		    // 客户姓名
			$("#realName").val(cus_Manage_Cnds.realName),
		    // 客户手机号
			$("#mobile").val(cus_Manage_Cnds.mobile),   
		    // 客户生日
			$("#birthday").val(cus_Manage_Cnds.birthday), 
		    // 星级
			$("#userLevel").val(cus_Manage_Cnds.userLevel)
		}
		//获取session保存当前页码
		if(sessionStorage.getItem('customer_pn')==null){
			pn = 1
		}else{
			pn = parseInt(sessionStorage.getItem('customer_pn'))
		}
		//liuli 2019/02/01 end
		$("#table").bootstrapTable('destroy');
		$("#table").bootstrapTable({ // 对应table标签的id
			method : "post",
			url: urlstorage+"user/list", // 获取表格数据的url
			cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
			striped: true,  //表格显示条纹，默认为false
			pagination: true, // 在表格底部显示分页组件，默认false
			pageSize: 10, // 页面数据条数
			//liuli 2019/02/01 start
			pageNumber: pn, // 首页页码
			//liuli 2019/02/01 end
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
					valign: 'middle',// 上下居中
					formatter: function (value, row, index) {
							if(permissionEdit==0){
								//不可点击
							//return "不可点击";
							return row.realName;
							}else{
								//可点击
								return "<a class='linktext custom' tabIndex='18'>"+row.realName+"</a>";
							}
						},
						events: 'operateEvents'	
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
						return "<button type='button' tabIndex='9' class='btn btn-primary published' >已发布</button><button type='button' tabIndex='9' class='btn btn-primary btn-size btn-pos unpublished '>未发布</button><button type='button' tabIndex='9' class='btn btn-primary btn-size btn-pos edit '>编辑</button><button type='button' class='btn btn-default btn-size btn-pos  delete' >删除</button>";
					},
					events: 'operateEvents'
				}
			],
			//liuli 2019/02/01 start
			//页码跳转的时候保存当前页码
			onPageChange:function(number,size){
				sessionStorage.setItem('customer_pn',number,size);
			},
			//liuli 2019/02/01 end
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
					//没有权限编辑
					if(permissionDelete==0){
						$(".delete").hide();
					}
				}
				if(data.statusCode=="401"){
					window.location.href="login.html";		
				}else if (data.statusCode=="512") {
					Swal.fire({
						  type: 'error',
						  title: data.msg
					})
				}
				console.info("加载成功"+data.statusCode);
			},
			onLoadError: function(){
				//加载失败时执行
				//window.location.href="login.html";
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
//  //身份证号的验证(18位验证)
//  jQuery.validator.addMethod("isCard", function(value,element) {
//	    var regExp=/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/   
//	    return this.optional(element) || (regExp.test(value));    
//	});
	jQuery.validator.addMethod("isDate", function(value, element,param){
		if(jQuery(param).val()==""){return true;}
	    var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/ 
	    if(value.length==8 && re.test(value)){
		value=value.substring(0,4)+"-"+value.substring(4,6)+"-"+value.substring(6,8);
	      }else if(value.length==10){
		value=value.replace(/\//g,"-");
	       }
	    	var ereg = /^(\d{1,4})(-|\/)(\d{1,2})(-|\/)(\d{1,2})$/;
	    	var r = value.match(ereg);
	    	if (r == null) {
	    		return false;
	    	}
	    	var d = new Date(r[1], r[3] - 1, r[5]);
	    	var result = (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[5]);
	    	jQuery(param).val(value);
	    	return this.optional(element) || (result);
	    }, "日期格式不正确");
	//入力项验证方法
	function formValidate(){
		 validateResult= $("#customerForm").validate({
			onkeyup : function(userNumber,idCard,realName,mobile,birthday){
				$("#userNumber").valid();
				$("#idCard").valid();
				$("#realName").valid();
				$("#mobile").valid();
				$("#birthday").valid();
			},
		     rules: {
		    	userNumber : {
		    		maxlength:8
			    },
			    idCard:{
			    	rangelength:[18,18]
			    },
			    realName : {
			    	maxlength:20
		        },
		        mobile : {
		        	digits:true,
		        	rangelength:[11,11]
		        },
		        birthday:{
		        	isDate:"#birthday"
		        }
		        
		     },
		     messages: {
		    	userNumber : {
		    		maxlength:"客户编号长度不能大于8位"
			    },
			    idCard:{
			    	rangelength:"客户身份证号长度必须为18位"
			    },
			    realName : {
			    	maxlength:"客户姓名长度不能大于20字"
		        },
		        mobile : {
		        	digits:"客户手机号必须为数字",
		        	rangelength:"客户手机号长度必须为11位"
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
	    	//liuli 2019/02/01 start
	    	//验证成功将查询信息保存到session里
	    	var customer_Manage_Conditions = {
	    		    // 客户编号
	    		userNumber:$("#userNumber").val(),
				    // 客户身份证号
				idCard:$("#idCard").val(),
				    // 客户姓名
				realName:$("#realName").val(),
				    // 客户手机号
				mobile:$("#mobile").val(),   
				    // 客户生日
				birthday:$("#birthday").val(), 
				    // 星级
				userLevel:$("#userLevel").val()
	    	};
	    	//以JSON格式保存
	    	sessionStorage.setItem('cus_Manage_Cnds', JSON.stringify(customer_Manage_Conditions));
	    	sessionStorage.removeItem('customer_pn');
			//liuli 2019/02/01 end
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
			window.location.href = "published-care-plan-management.html?"+window.btoa(unescape(encodeURIComponent("userNumber=" + row.userNumber )));
		},
		//点击未发布
		'click .unpublished': function (e, value, row, index) {
			window.location.href = "unpublished-care-plan-management.html?"+window.btoa(unescape(encodeURIComponent("userNumber=" + row.userNumber )));
		},
		//编辑按钮点击事件
		'click .edit': function (e, value, row, index) {
			window.location.href = "customer-edit.html?"+window.btoa(unescape(encodeURIComponent("userId=" + row.userId))) ;
		},
		// 点击客户姓名
		'click .custom': function (e, value, row, index) {
			
			window.location.href = "customer-edit.html?"+window.btoa(unescape(encodeURIComponent("userId="+row.userId+"&customFlag=2"))) ;
		},
		//删除
		'click .delete': function (e, value, row, index) {
			
			$("#myModal").modal('show');
			$(".sure").on("click",function(){
				$.ajax({
					type:"DELETE",
					url: urlstorage + "user/"+row.userId,
					dataType: "json", 
					contentType : "application/json",
					beforeSend: function(request) {
						request.setRequestHeader("Authorization", token);
					},
//					data:JSON.stringify( {
//						nursePlanId:row.nursePlanId,
//						nursePlanStatus:1
//					}),
					success:function(result,status,httpResponse){
						if (result.statusCode=="200") {
							//将token存入缓存	
							var token=httpResponse.getResponseHeader('Authorization');
							if(token!=null && token!="" && token!=undefined){
								localStorage.setItem("token", token); 
							}
							//弹出框显示
							$('#table').bootstrapTable('remove', {
								field: 'userId',
								values: [row.userId]
							});
							//弹出框隐藏
							$("#myModal").modal('hide');
							Swal.fire({
		  						  type: 'success',
		  						  title: result.msg
			        			})
			        			var refreshurl=  urlstorage+"user/list";
							$('#table').bootstrapTable('refresh',refreshurl);
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
				});
				$(".sure").unbind();
			});
		}
	};
});