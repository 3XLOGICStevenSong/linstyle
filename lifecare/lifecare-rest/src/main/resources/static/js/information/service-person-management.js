
$(function(){
	 jeDate("#birthday",{
    	 format: "YYYY-MM-DD",
         //isinitVal: true,
        // valiDate:["0[4-7]$,1[1-5]$,2[58]$",true],
    });
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	if(urlstorage==null){
		 window.location.href="login.html";	
	}
	//获取loginUserId
	var loginUserId=localStorage.getItem("userId");
	$("#loginUserId").val(loginUserId);
	formValidate();
	//获取当前用户权限
	 var permission=localStorage.getItem("userNumber")+"permission";
	//控制按钮显示
	var permissions= localStorage.getItem(permission);
	//登录用户权限查询标识（0：不能 ，1 ： 能）
	var servicePersionSelect=0;
	//登录用户权限追加标识（0：不能 ，1 ： 能）
	var servicePersionAdd=0;
	//登录用户权限编辑标识（0：不能 ，1 ： 能）
	var servicePersionEdit=0;	
	//删除
	var servicePersionDelete=0;
	//登录权限判断
	 if(permissions.length>0){
			var data= permissions.split(",");
	        for(var i=0;i<data.length;i++){
	        	//服务人员查询权限判断
	            if(data[i]=="personal:query"){
	            	servicePersionSelect=1;
	            	$("#query").css("display","inline-block");
	            };
	            //服务人员信息添加权限判断
	            if(data[i]=="personal:create"){
	            	servicePersionAdd=1;
	            	$("#add").css("display","inline-block");
	            }
	            //服务人员信息编辑权限判断
	            if(data[i]=="personal:update"){
	            	servicePersionEdit=1;
	                $(".edit").css("display","inline-block");
	            }
	            if(data[i]=="personal:delete"){
	            	servicePersionDelete=1;
	                $(".delete").css("display","inline-block");
	            }
	        } 
		 };
		//显示数据
		load();

	//没有服务人员查询权限执行的操作
	if(servicePersionSelect==0){
		$("#query").hide();
		//$("#query").css("display","block");
	}
	//没有服务人员添加权限执行的操作
	if(servicePersionAdd==0){
		$("#add").hide();
		//$("#add").css("display","none");
	}
	
	function load(){
	//liuli 2019/02/01 start
	//获取session查询条件
	if(sessionStorage.getItem('ser_Per_Cnds')!=null){
		var Srn_Cnds_JsonStr = sessionStorage.getItem('ser_Per_Cnds');
		var ser_Per_Cnds = JSON.parse(Srn_Cnds_JsonStr);
	    // 服务人员工号
		$("#userNumber").val(ser_Per_Cnds.userNumber),
	    // 服务人员身份证号
		$("#idCard").val(ser_Per_Cnds.idCard),
	    // 服务人员姓名
		$("#realName").val(ser_Per_Cnds.realName),
	    // 服务人员手机号
		$("#mobile").val(ser_Per_Cnds.mobile),   
	    // 服务人员生日
		$("#birthday").val(ser_Per_Cnds.birthday), 
	    // 等级
		$("#userLevel").val(ser_Per_Cnds.userLevel)
	}
	//获取session保存当前页码
	if(sessionStorage.getItem('server_pn')==null){
		pn = 1
	}else{
		pn = parseInt(sessionStorage.getItem('server_pn'))
	}
	//liuli 2019/02/01 end
	//清除查询出的数据
	$("#table").bootstrapTable('destroy');
	//渲染表格
	$("#table").bootstrapTable({ // 对应table标签的id
		method : "post",
		url: urlstorage+"user/list", // 获取表格数据的url
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
		queryParams: queryParams,
     //设置header
		ajaxOptions: {
			beforeSend: function(request) {
			  request.setRequestHeader("Authorization", token);
			}	
		},
		columns : [

			 {
					field: 'userNumber',// 返回json数据中的name
					title: '服务人员工号', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle' // 上下居中
			 }, 
			 {
					field: 'realName',// 返回json数据中的name
					title: '服务人员姓名',// 表格表头显示文字
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
					field: 'serviceCategoryLevel',// 返回json数据中的name
					title: '服务类别',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter : function (value, row, index) {
						var text = '';
						if(row.serviceCategoryLevel == '000'){
							text = "未设定";
						}else if (row.serviceCategoryLevel == '001'){
					    	text = "生活助理";
			    		}else if (row.serviceCategoryLevel == '010'){
					    	text = "老龄照护";
			    		}else if (row.serviceCategoryLevel == '100'){
					    	text = "临床护理";
			    		}else if (row.serviceCategoryLevel == '011'){
					    	text = "生活助理,老龄照护";
			    		}else if (row.serviceCategoryLevel == '101'){
					    	text = "生活助理,临床护理";
			    		}else if (row.serviceCategoryLevel == '110'){
					    	text = "老龄照护,临床护理";
			    		}else if (row.serviceCategoryLevel == '111'){
					    	text = "生活助理,老龄照护,临床护理";
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
						if (row.userStatus == 0){
						   	text = "启用";//性别转换
					    } else if (row.userStatus == 1){
					    	text = "停用";//性别转换
			    		}
						    return text;
						}		
			 },
			 {
				    field: 'userId', // 返回json数据中的name
					title: "操作",
					align: 'center',
					valign: 'middle',
					formatter: function (value, row, index) {
						return "<button type='button' class=' btn btn-primary btn-default edit'  >编辑</button><button type='button' class='btn btn-default btn-size btn-pos  delete' >删除</button>"; 
				},
				 	events: 'operateEvents'
			 }
			
		],
		//liuli 2019/02/01 start
		//页码跳转的时候保存当前页码
		onPageChange:function(number,size){
			sessionStorage.setItem('server_pn',number,size);
		},
		//liuli 2019/02/01 end
		onLoadSuccess: function(data ){
			//console.info("加载成功");
			//没有权限编辑
			if(servicePersionEdit==0){
				//无权限编辑
				$(".edit").css("display","none");
			}
			if(servicePersionDelete==0){
				//无权限删除
				$(".delete").css("display","none");
			}
			if(servicePersionEdit==0&&servicePersionDelete==0){
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
		onLoadError : function() { 
			//加载失败时执行
			console.info("加载数据失败");
			//window.location.href="login.html";
		}
	});

	//表格参数设置
	queryParams: function queryParams(params) {// 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
				return {
					limit : params.limit,// 每页要显示的数据条数
					offset : params.offset,// 每页显示数据的开始行号
					userType : "2", //用户类型(2:服务人员)
					//服务人员工号
				    userNumber:$("#userNumber").val(),
				    //服务人员身份证号
				    idCard:$("#idCard").val(),
				    //服务人员姓名
				    realName:$("#realName").val(),
				    //服务人员手机号
				    mobile:$("#mobile").val(),   
				    //服务人员生日
				    birthday:$("#birthday").val(), 
				    //等级
				    userLevel:$("#userLevel").val()
				}
	};
	}
window.operateEvents = {
		//编辑按钮点击事件
		'click .edit': function (e, value, row, index) {
			window.location.href = "service-person-edit.html?"+window.btoa(unescape(encodeURIComponent("userId="+row.userId)));	
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
}

//身份证号的验证
//jQuery.validator.addMethod("isCard", function(value,element) {
//      	 var regExp=/^\d{15}$|^\d{17}[0-9Xx]$/   
//      	 return this.optional(element) || (regExp.test(value));                   	
//});
//手机号的验证
//jQuery.validator.addMethod("iphone", function(value,element) {
//      	 var regExp=/^\d{11}$/   
//      	 return this.optional(element) || (regExp.test(value));                   	
//});
//日期格式验证
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
	//验证方法
	function formValidate(){
		validateResult =  $("#servicePersionForm").validate({
			  onkeyup : function(birthday){
				  $("#birthday").valid();
			  },
		        rules: {
		        	userNumber : {
		        		maxlength:16
		        	},
		        	idCard : {
		        		rangelength:[18,18]
		        	},
		        	realName : {
		        		maxlength:20
		        	},
		            mobile: {
		               	digits:true,
		               	rangelength:[11,11]
		            },
			        birthday:{
			        	isDate:"#birthday"
			        }
		         },
		     messages: {
		    	 userNumber : {
		    		   maxlength:"服务人员工号长度不能大于16位"
		        	},
		        	idCard : {
		        	   rangelength : "服务人员身份证号长度必须为18位"
		        	},
		        	realName : {
		        	   maxlength:"服务人员姓名长度不能大于20字"
		        	},
		            mobile: {
		               digits : "服务人员手机号必须为数字",
		               rangelength:"服务人员手机号长度必须为11位"
			        }
		     },
		     errorPlacement: function(error, element) {
		    	 error.appendTo(element.parent());
				}
		    });
		  return validateResult;
		};
	
//查询服务人员
$("#query").click(function(){
	// 提交之前进行验证
    if (formValidate().form()) { //判断校验是否符合规则
        // 符合规则进入后台 ajax处理
    	//liuli 2019/02/01 start
    	//验证成功将查询信息保存到session里
    	var server_Person_Conditions = {
    		    // 服务人员工号
    		userNumber:$("#userNumber").val(),
			    // 服务人员身份证号
			idCard:$("#idCard").val(),
			    // 服务人员姓名
			realName:$("#realName").val(),
			    // 服务人员手机号
			mobile:$("#mobile").val(),   
			    // 服务人员生日
			birthday:$("#birthday").val(), 
			    // 等级
			userLevel:$("#userLevel").val()
    	};
    	//以JSON格式保存
    	sessionStorage.setItem('ser_Per_Cnds', JSON.stringify(server_Person_Conditions));
    	sessionStorage.removeItem('server_pn');
		//liuli 2019/02/01 end
    load();
  }else{
      // 不符合规则返回
      return false;
  } 
})
//添加服务人员点击事件
$("#add").click(function(){
	window.location.href="service-person-add.html";
});

})