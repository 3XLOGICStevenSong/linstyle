
$(function(){
    jeDate("#birthday",{
      	 format: "YYYY-MM-DD"
      });
	// 获取缓存token
	var token=localStorage.getItem("token");
	var urlstorage=localStorage.getItem("url");
	if(urlstorage==null){
		 window.location.href="login.html";	
	}
	//获取当前用户权限
	var permission=localStorage.getItem("userNumber")+"permission";
	 //控制按钮显示
	var permissions= localStorage.getItem(permission);
	//查看权限标识
	
	//一览画面标识
	var userView=0;
	//编辑权限标识
	var userEdit=0;
	//查询权限标识
	var userqQuery=0;
	//添加权限标识
	var userAdd=0;
	var userDelete=0;
	 if(permissions.length>0){
			var data= permissions.split(",");
	        for(var i=0;i<data.length;i++){
	            //编辑权限
	            if(data[i]=="user:update"){
	            	userEdit=1;
	            };
	            //查询权限
	            if(data[i]=="user:query"){
	            	userqQuery=1;
	            };
	            //添加权限
	            if(data[i]=="user:create"){
	            	userAdd=1;
	            }
	            //删除权限
	            if(data[i]=="user:delete"){
	            	userDelete=1;
	            }
	        } 
		 };
		//查询 
		if (userqQuery==0){
			$(".searchBtn").hide();
		};
		//添加
		if (userAdd==0){
			$(".btn-pos").hide();
		};
		if (userDelete==0){
			$(".delete").hide();
		};
		formValidate();
		//渲染表格
		load();
	//初始化表单验证
	//formValidate();
function load(){
	//wangbo 2019/01/31 start
	//获取session查询条件
	if(sessionStorage.getItem('bk_Srh_Cnds')!=null){
		var Srn_Cnds_JsonStr = sessionStorage.getItem('bk_Srh_Cnds');
		var bk_Srh_Cnds = JSON.parse(Srn_Cnds_JsonStr);
		$("#userNumber").val(bk_Srh_Cnds.userNumber),
	    // 用户身份证号
		$("#idCard").val(bk_Srh_Cnds.idCard),
	    // 用户姓名
		$("#realName").val(bk_Srh_Cnds.realName),
	    // 用户手机号
		$("#mobile").val(bk_Srh_Cnds.mobile),   
	    // 用户生日
		$("#birthday").val(bk_Srh_Cnds.birthday), 
	    // 等级
		$("#userLevel").val(bk_Srh_Cnds.userLevel)
	}
	//获取session保存当前页码
	if(sessionStorage.getItem('back_pn')==null){
		pn = 1
	}else{
		pn = parseInt(sessionStorage.getItem('back_pn'))
	}
	//wangbo 2019/01/31 end
	$("#table").bootstrapTable('destroy');
	$("#table").bootstrapTable({ // 对应table标签的id
		method : "post",
		url: urlstorage+"user/list", // 获取表格数据的url
		cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
		striped: true,  // 表格显示条纹，默认为false
		pagination: true, // 在表格底部显示分页组件，默认false
		// pageList: [10, 20], // 设置页面可以显示的数据条数
		pageSize: 10, // 页面数据条数
		//wangbo 2019/01/31 start
		pageNumber: pn, // 首页页码
		//wangbo 2019/01/31 end
		sidePagination: 'server', // 设置为服务器端分页
		showHeader : true,	
		queryParams:queryParams,
     // 设置header
		ajaxOptions: {
			beforeSend: function(request) {
			  request.setRequestHeader("Authorization", token);
			}	
		},
		columns : [
			 {
					field: 'userNumber',// 返回json数据中的userNumber
					title: '用户工号', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle' // 上下居中
			 }, 
			 {
					field: 'realName',// 返回json数据中的realName
					title: '用户姓名',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
			
			 },
			 {
					field: 'mobile',// 返回json数据中的mobile
					title: '手机',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
			
			 },
			 {
					field: 'userAddr',// 返回json数据中的userAddr
					title: '地址',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
			 },
			 {
					field: 'birthday',// 返回json数据中的birthday
					title: '生日',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
			 },
			 {
					field: 'sex',// 返回json数据中的sex
					title: '性别',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter : function (value, row, index) {
						var text = '';
						if (row.sex == 1){
						   	text = "女";// 性别转换
					    } else if (row.sex == 0){
					    	text = "男";// 性别转换
			    		}
						    return text;
						}

			 },
			 {
					field: 'userLevel',// 返回json数据中的name
					title: '等级',// 表格表头显示文字
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
						if (row.userStatus == 1){
						   	text = "停用";// 用户状态
					    } else if (row.userStatus == 0){
					    	text = "启用";// 用户状态
			    		}
						    return text;
						}		
			 },
			 {		
				 	field: 'userId', // 返回json数据中的userId
					title: "操作",
					align: 'center',
					valign: 'middle',
					formatter: function (value, row, index) {
						return "<button type='button'tabIndex='9' class=' btn btn-primary btn-default edit'  >编辑</button><button type='button' class='btn btn-default btn-size btn-pos  delete' >删除</button>"; 
				},
				events: 'operateEvents'
			 }
			
		],
		//wangbo 2019/01/31 start
		//页码跳转的时候保存当前页码
		onPageChange:function(number,size){
			sessionStorage.setItem('back_pn',number,size);
		},
		//wangbo 2019/01/31 end
		onLoadSuccess: function(data ){
				//console.info("加载成功");
			if(userEdit==0){
				//无权限编辑
				$(".edit").css("display","none");
			}
			if(userDelete==0){
				//无权限删除
				$(".delete").css("display","none");
			}
			if(userEdit==0&& userDelete==0){
				//没有权限编辑
			$('#table').bootstrapTable('hideColumn', 'userId');
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
		onLoadError : function() { // 加载失败时执行
			console.info("加载数据失败");
		}
	});
  //身份证号的验证(18位验证)
  jQuery.validator.addMethod("isCard", function(value,element) {
	    var regExp=/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/   
	    return this.optional(element) || (regExp.test(value));    
	});
	// 表格参数设置
	queryParams: function queryParams(params) {// 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
			return {
				limit : params.limit,// 每页要显示的数据条数
				offset : params.offset,// 每页显示数据的开始行号
				userType : "3",
				// 用户工号
			    userNumber:$("#userNumber").val(),
			    // 用户身份证号
			    idCard:$("#idCard").val(),
			    // 用户姓名
			    realName:$("#realName").val(),
			    // 用户手机号
			    mobile:$("#mobile").val(),   
			    // 用户生日
			    birthday:$("#birthday").val(), 
			    // 等级
			    userLevel:$("#userLevel").val()
			}
    }
};
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
// 数据check
function formValidate(){
	  validateResult= $("#missionForm").validate({
		  onkeyup : function(birthday){
			  $("#birthday").valid();
		  },
	     rules: {
	        	userNumber:{
	        		maxlength:16
			    },
			    idCard:{
			    	isCard:true
			    },
			    realName : {
			    	maxlength:20
		        },
	            // 用户手机号
		        mobile : {
		        	digits:true,
		        	rangelength:[11,11]
		        } ,
		        birthday:{
		        	isDate:"#birthday"
		        }
	    },
	     messages: {
	    	 userNumber :{
	    		 maxlength:"用户工号长度不能大于16位"
			    },
			    idCard:{
			    	isCard:"用户身份证号长度必须为18位"
			    },
			    realName : {
			    	maxlength:"用户姓名长度不能大于20字"
		        },
		        mobile : {
		        	digits:"用户手机号必须为数字",
		        	rangelength:"用户手机号长度必须为11位"
		        }
		     },
	     errorPlacement: function(error, element) {
	    	 error.appendTo(element.parent());
			}
	    });
	  return validateResult;
	};
	
// 查询后台人员
$(".searchBtn").click(function(){
	// 查询之前进行数据check
	// 提交之前进行验证
    if (formValidate().form()) {
		//wangbo 2019/01/31 start
    	//验证成功将查询信息保存到session里
    	var back_Search_Conditions = {
    		userNumber:$("#userNumber").val(),
			    // 用户身份证号
			idCard:$("#idCard").val(),
			    // 用户姓名
			realName:$("#realName").val(),
			    // 用户手机号
			mobile:$("#mobile").val(),   
			    // 用户生日
			birthday:$("#birthday").val(), 
			    // 等级
			userLevel:$("#userLevel").val()
    	};
    	//以JSON格式保存
    	sessionStorage.setItem('bk_Srh_Cnds', JSON.stringify(back_Search_Conditions));
    	sessionStorage.removeItem('back_pn');
		//wangbo 2019/01/31 end
    	load();
  	 //$('#table').bootstrapTable('refresh');
    	
    }else{
      // 不符合规则返回
      return false;
  } 
});

// 添加后台人员
$(".btn-pos").click(function(){
	window.location.href="backstage-user-add.html";
});

// 点击编辑
window.operateEvents = {
		// 监听事件
		'click .edit': function (e, value, row, index) {
			
			window.location.href = "backstage-user-edit.html?"+window.btoa(unescape(encodeURIComponent("userId="+row.userId)));
			
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
//				data:JSON.stringify( {
//					nursePlanId:row.nursePlanId,
//					nursePlanStatus:1
//				}),
				success:function(result,status,httpResponse){
					if (result.statusCode=="200") {
						//将token存入缓存	
						var token=httpResponse.getResponseHeader('Authorization');
						if(token!=null && token!="" && token!=undefined){
							localStorage.setItem("token", token); 
						}
						//弹出框显示
//						$('#table').bootstrapTable('remove', {
//							field: 'userId',
//							values: [row.userId]
//						});
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
}
})