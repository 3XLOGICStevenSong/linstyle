
$(function(){
	jeDate("#birthday",{
   	 format: "YYYY-MM-DD",
        //isinitVal: true,
       // valiDate:["0[4-7]$,1[1-5]$,2[58]$",true],
   });
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");	
	//获取loginUserId
	var loginUserId=localStorage.getItem("userId");

	/**
	 * 获取地址栏传递的数据
	 * selectFlag : 1(服务任务池选择)
	 * selectFlag : 2(服务任务编辑选择)
	 * 从地址栏截取数据，判断数据来源后获取信息
	 */
    var url = location.search; 
    if(url != null && url != ""){
    	var params=$.getParam(url);
    	var selectFlag=params['selectFlag'];
    	//服务任务编辑传递的信息
    	if(selectFlag==2){
    		//性别要求
    		var sexRequire=params['sexRequire'];	
    		//预约日期
    		var orderDate=params['orderDate'];
    		//预约时间Begin
    		var orderBeginTime=params['orderBeginTime'];
    		//预约时间End
    		var orderEndTime=params['orderEndTime'];
    		//服务类别
    		var serviceCategoryCode=params['serviceCategoryCode'];
    	//服务池传递的信息
    	}else if(selectFlag==1){	
     		//订单主键
    		var id=params['id'];
     	}
    };
  //用户类型固定值(2:服务人员)
   var userType="2";
 //状态固定值(0:启用 1:停用)   	
   var userStatus="0"; 
   //显示数据
	load();
	function load(){
	//清除查询出的数据
	$("#table").bootstrapTable('destroy');
	//渲染表格
	$("#table").bootstrapTable({ // 对应table标签的id
		method : "post",
		url: urlstorage+"user/list", // 获取表格数据的url
		cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
		striped: true,  //表格显示条纹，默认为false
		pagination: true, // 在表格底部显示分页组件，默认false
		// : [10, 20], // 设置页面可以显示的数据条数
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
//			 {
//					field: 'userId', // 返回json数据中的name
//					title: '用户主键', // 表格表头显示文字
//					align: 'center', // 左右居中
//					valign: 'middle', // 上下居中
//					hidden:true,//隐藏
//					visible: false//不可显示
//			 },
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
				    field: 'userId', // 返回json数据中的name
					title: "操作",
					align: 'center',
					valign: 'middle',
					formatter: function (value, row, index) {
						return "<button type='button' class=' btn btn-primary btn-default col-md-offset-1 btn-size choice'>选择</button></td>"; 
				},
				 	events: 'operateEvents'
			 }	
		],
		onLoadSuccess: function(data ){
			console.info("加载成功");
		},
		onLoadError : function() { //加载失败时执行
			console.info("加载数据失败");
			window.location.href="login.html";
		}
	});
	//表格参数设置
	queryParams: function queryParams(params) {// 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求	
		return {
					limit : params.limit,// 每页要显示的数据条数
					offset : params.offset,// 每页显示数据的开始行号
					id : id,//订单id
					userType : userType, //用户类型(2:服务人员)
					userStatus : userStatus,//状态(0:启用 1:停用)   				
					sexRequire : sexRequire,//性别要求
      	    		orderDate : orderDate,//预约日期
  		    		orderBeginTime : orderBeginTime,//预约时间Begin
     	    	    orderEndTime : orderEndTime,//预约时间End
 		    		serviceCategoryCode : serviceCategoryCode,//服务类别
				    userNumber:$("#userNumber").val(),//服务人员工号
				    idCard:$("#idCard").val(), //服务人员身份证号
				    realName:$("#realName").val(),//服务人员姓名
				    mobile:$("#mobile").val(), //服务人员手机号
				    birthday:$("#birthday").val(), //服务人员生日  
				    userLevel:$("#userLevel").val()//等级
				}
	}
}
window.operateEvents = {
		//编辑按钮点击事件
			'click .choice': function (e, value, row, index) {
				if (selectFlag==2){
				var urlParam=
				window.location.href = "service-task-edit.html?servicePersonId="+row.userId+"&servicePersonUserNumber="+row.userNumber+"&servicePersonRealName="+row.realName+"&servicePersonMobile="+row.mobile+"&servicePersonSex="+row.sex;
				}else if(selectFlag==1){
					//用户主键
					var userId =	row.userId;
					$.ajax({
						type:"post",
						url:urlstorage+"user/select",
						beforeSend: function(request) {
							request.setRequestHeader("Authorization", token);
							},
						async: false,
						dataType: "json", 
						contentType : "application/json",
						data : JSON.stringify({
							userId: userId,  //用户主键
							id :  id,  //订单主键
							loginUserId : loginUserId//登录用户ID
						}),
						success:function(result,status,httpResponse){
							if(result.statusCode=="200"){
								//将token存入缓存	
		    				       token=httpResponse.getResponseHeader('Authorization');
			    				   if(token!=null && token!="" && token!=undefined){
			    						localStorage.setItem("token", token); 
			    				   }
			    				   Swal.fire({
										title: "服务人员选择成功",
										type: 'success',
										showCancelButton: false,
										confirmButtonColor: '#3085d6',
										confirmButtonText: 'OK'
									}).then(function(result){
										if (result.value) {
					    				    //跳转到服务人员管理页面
											window.location.href = "service-task-pool.html?taskflag=1"		
										}
									})
															
							}else if(result.statusCode=="401"){
	    						window.location.href="login.html";		
		    				}
						}
				});
				}
			}
};
	
//身份证号的验证
jQuery.validator.addMethod("isCard", function(value,element) {
      	 var regExp=/^\d{15}$|^\d{17}[0-9Xx]$/   
      	 return this.optional(element) || (regExp.test(value));                   	
});
//手机号的验证
//jQuery.validator.addMethod("iphone", function(value,element) {
//      	 var regExp=/^\d{11}$/   
//      	 return this.optional(element) || (regExp.test(value));                   	
//});
	//验证方法
	function formValidate(){
		validateResult =  $("#servicePersionForm").validate({
			rules: {
				userNumber : {
	        		maxlength:16
	        	},
	        	idCard : {
	        		isCard : true
	        	},
	        	realName : {
	        		maxlength:8
	        	},
	            mobile: {
	                digits:true,
	                rangelength:[11,11]
	            }
	         },
	     messages: {
	    	 userNumber : {
	    		   maxlength:"服务人员工号长度不能大于16位"
	        	},
	        	idCard : {
	        		isCard : "服务人员身份证号长度必须为15位或18位"
	        	},
	        	realName : {
	        		maxlength:"服务人员姓名长度不能大于8字"
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
	$(".query").click(function(){
		// 提交之前进行验证
	    if (formValidate().form()) { //判断校验是否符合规则
	    load();
	  }else{
	      // 不符合规则返回
	      return false;
	  } 
	})
})