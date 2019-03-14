
$(function(){
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	//alert("url")
	if(urlstorage==null){
		
		 window.location.href="login.html";	
	}
	//获取当前用户权限
	 var permission=localStorage.getItem("userNumber")+"permission";
	 //控制按钮显示
	var permissions= localStorage.getItem(permission);
	//客户查看权限
	var custom=0;
	//服务人员查看权限
	var service=0;
	//任务编辑权限
	var taskedit=0;
	//任务删除权限
	var taskdelete=0;
	 if(permissions.length>0){
		var data= permissions.split(",");
         for(var i=0;i<data.length;i++){
             if(data[i]=="customer:update"){
            	custom=1;
             }
             if(data[i]=="personal:update"){
            	 service=1;
             }
             if(data[i]=="order:update"){
            	 taskedit=1;
             }
             if(data[i]=="order:delete"){
            	 taskdelete=1;
             }
             //break;
         } 
	 }
	 //获取待处理url
	    var url = location.search; 
	    if(url!=null&& url!=""){
	    var params=$.getParam(url);
	    var pendingFlag=params['pendingFlag']; 
	    if(pendingFlag!=null&& pendingFlag!=""){
	    	sessionStorage.removeItem('task_Man_Cnds');
	    	sessionStorage.removeItem('task_pn');
	    	$("[name='serviceCategoryCodes']").attr("checked",'true');//全选 
            var orderStatus = document.getElementsByName("orderStatus");
            orderStatus[0].checked=true;
            orderStatus[1].checked=true;
            var abnormalOrder = document.getElementsByName("abnormalOrder");
            abnormalOrder[0].checked=true;
	    }
	    }else{	$("[name='serviceCategoryCodes']").attr("checked",'true');//全选 
        var orderStatus = document.getElementsByName("orderStatus");
        orderStatus[0].checked=true;
        orderStatus[1].checked=true;
        var abnormalOrder = document.getElementsByName("abnormalOrder");
        abnormalOrder[0].checked=true;
        abnormalOrder[1].checked=true;}
	    //日期格式化
	    jeDate("#customBirthday",{
	    	 format: "YYYY-MM-DD",
	    	 //minDate:mindate,
	    });
	    jeDate("#servicePersonBirthday",{
	    	 format: "YYYY-MM-DD",
	    	 //minDate:mindate,
	    });
	    jeDate("#orderDateFrom",{
	    	 format: "YYYY-MM-DD",
	    	 //minDate:mindate,
	    });
	    jeDate("#orderDateTo",{
	    	 format: "YYYY-MM-DD",
	    	 //minDate:mindate,
	    }); 
	    jeDate("#orderBeginTimeFrom",{
	    	format: "hh:mm",
	    	 language:{
	               name   : "cn",
	               month  : ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
	               weeks  : [ "日", "一", "二", "三", "四", "五", "六" ],
	               times  : ["小时","分钟","秒数"],
	               timetxt: ["时间选择","开始时间","结束时间"],
	               backtxt:"返回日期",
	               clear  : "清空",
	               today  : "现在",
	               yes    : "确定"
	           },
	    	 //minDate:mindate,
	    });
	    jeDate("#orderBeginTimeTo",{
	    	format: "hh:mm",
	    	 language:{
	               name   : "cn",
	               month  : ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
	               weeks  : [ "日", "一", "二", "三", "四", "五", "六" ],
	               times  : ["小时","分钟","秒数"],
	               timetxt: ["时间选择","开始时间","结束时间"],
	               backtxt:"返回日期",
	               clear  : "清空",
	               today  : "现在",
	               yes    : "确定"
	           },
	    });
	    
	//渲染表格
	initData();
	//初始化表单验证
	formValidate();
//渲染表格
function initData(){
	//liuli 2019/02/02 start
	//获取session查询条件
	if(sessionStorage.getItem('task_Man_Cnds')!=null){
		var Srn_Cnds_JsonStr = sessionStorage.getItem('task_Man_Cnds');
		var task_Man_Cnds = JSON.parse(Srn_Cnds_JsonStr);
		// 服务类别
		if(task_Man_Cnds.serviceCategoryCodes1){
			$("input[type='checkbox']:eq(0)").prop("checked", true);
		}else{
			$("input[type='checkbox']:eq(0)").prop("checked", false);
		}
		if(task_Man_Cnds.serviceCategoryCodes2){
			$("input[type='checkbox']:eq(1)").prop("checked", true);
		}else{
			$("input[type='checkbox']:eq(1)").prop("checked", false);
		}
		if(task_Man_Cnds.serviceCategoryCodes3){
			$("input[type='checkbox']:eq(2)").prop("checked", true);
		}else{
			$("input[type='checkbox']:eq(2)").prop("checked", false);
		}
		//任务状态
		if(task_Man_Cnds.orderStatus1){
			$("input[type='checkbox']:eq(3)").prop("checked", true);
		}else{
			$("input[type='checkbox']:eq(3)").prop("checked", false);
		}
		if(task_Man_Cnds.orderStatus2){
			$("input[type='checkbox']:eq(4)").prop("checked", true);
		}else{
			$("input[type='checkbox']:eq(4)").prop("checked", false);
		}
		if(task_Man_Cnds.orderStatus3){
			$("input[type='checkbox']:eq(5)").prop("checked", true);
		}else{
			$("input[type='checkbox']:eq(5)").prop("checked", false);
		}
		if(task_Man_Cnds.orderStatus4){
			$("input[type='checkbox']:eq(6)").prop("checked", true);
		}else{
			$("input[type='checkbox']:eq(6)").prop("checked", false);
		}
		//异常任务状态
		if(task_Man_Cnds.abnormalOrder1){
			$("input[type='checkbox']:eq(7)").prop("checked", true);
		}else{
			$("input[type='checkbox']:eq(7)").prop("checked", false);
		}
		if(task_Man_Cnds.abnormalOrder2){
			$("input[type='checkbox']:eq(8)").prop("checked", true);
		}else{
			$("input[type='checkbox']:eq(8)").prop("checked", false);
		}
		// 任务编号
		$("#orderNo").val(task_Man_Cnds.orderNo),		
	    // 客户编号
		$("#customUserNumber").val(task_Man_Cnds.customUserNumber),
		//服务人员工号
		$("#servicePersonUserNumber").val(task_Man_Cnds.servicePersonUserNumber),
		// 客户姓名
		$("#customRealName").val(task_Man_Cnds.customRealName),
		// 服务人员姓名
		$("#servicePersonRealName").val(task_Man_Cnds.servicePersonRealName),
		// 客户生日
		$("#customBirthday").val(task_Man_Cnds.customBirthday),
		// 服务人员生日
		$("#servicePersonBirthday").val(task_Man_Cnds.servicePersonBirthday),
		// 客户身份证号
		$("#customIdCard").val(task_Man_Cnds.customIdCard),
		// 服务人员身份证号
		$("#servicePersonIdCard").val(task_Man_Cnds.servicePersonIdCard),
		// 客户手机号
		$("#customMobile").val(task_Man_Cnds.customMobile),
		// 服务人员手机号
		$("#servicePersonMobile").val(task_Man_Cnds.servicePersonMobile),
		// 预约日期-From
		$("#orderDateFrom").val(task_Man_Cnds.orderDateFrom),
		// 预约日期-To
		$("#orderDateTo").val(task_Man_Cnds.orderDateTo),
		// 服务时长-开始
		$("#orderDurationFrom").val(task_Man_Cnds.orderDurationFrom),
		// 服务时长-结束
		$("#orderDurationTo").val(task_Man_Cnds.orderDurationTo),
		// 预约开始时间-开始
		$("#orderBeginTimeFrom").val(task_Man_Cnds.orderBeginTimeFrom),
		// 预约开始时间-结束
		$("#orderBeginTimeTo").val(task_Man_Cnds.orderBeginTimeTo)
	}
	//获取session保存当前页码
	if(sessionStorage.getItem('task_pn')==null){
		pn = 1
	}else{
		pn = parseInt(sessionStorage.getItem('task_pn'))
	}
	//liuli 2019/02/02 end
	$("#table").bootstrapTable('destroy');
	$("#table").bootstrapTable({ // 对应table标签的id
		method : "post",
		url: urlstorage+"task/list", // 获取表格数据的url
		cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
		striped: true,  //表格显示条纹，默认为false
		pagination: true, // 在表格底部显示分页组件，默认false
		pageSize: 10, // 页面数据条数
		//liuli 2019/02/02 start
		pageNumber: pn, // 首页页码
		//liuli 2019/02/02 end
		sidePagination: 'server', // 设置为服务器端分页
		showHeader : true,
		queryParams:queryParams,
	     //设置header
		ajaxOptions: {
			beforeSend: function(request) {
			  request.setRequestHeader("Authorization", token);
			}	
		},
		// 返回数据格式处理（如果后台返回数据格式不是rows）
//		 responseHandler: function(res) {
//			$("#totalOrderDuration").text("服务总时长： "+res.data.totalOrderDuration);
//			$("#totalServiceDuration").text("实际总时长："+res.data.totalServiceDuration);
//			return {
//			"total": res.data.total,//总页数
//			"rows": res.data.taskManageVOList ,//数据
//		 };
//		},
		columns : [
		
			 {
					field: 'orderNo',// 返回json数据中的name
					title: '任务编号', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle' // 上下居中
			 }, 
			 {
					field: 'serviceCategoryName',// 返回json数据中的name
					title: '服务类别',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
			
			 },
			 {
					field: 'orderDateTime',// 返回json数据中的name
					title: '预约开始时间',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
			
			 },
			 {
					field: 'orderDuration',// 返回json数据中的name
					title: '预约时长',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
			 },
			 {
					field: 'serviceBeginTime',// 返回json数据中的name
					title: '实际开始时间',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
			
			 },
			 {
					field: 'serviceDuration',// 返回json数据中的name
					title: '实际时长',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
			 },
			 {
					field: 'orderStatus',// 返回json数据中的name
					title: '任务状态',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter: function (value, row, index) {
						//debugger;
							if(row.orderStatus=='1'){
								return "待分配";
								}else if(row.orderStatus=='2'||row.orderStatus=='5'){
									return "待完成";
								}else if(row.orderStatus=='3'){
									return "已完成";
								}else if(row.orderStatus=='4'){
									return "取消";
								}
							
					}		   
				

			 },
			 {
					field: 'customRealName',// 返回json数据中的name
					title: '客户姓名',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter: function (value, row, index) {
						//不可点击
					if(custom==0){
					return row.customRealName;
					}else{
						//可点击
						return "<a class='linktext custom' >"+row.customRealName+"</a>";
					}
					
						},
					events: 'operateEvents'	
			 },
			 {
					field: 'servicePersonRealName',// 返回json数据中的name
					title: '服务人员姓名',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter: function (value, row, index) {
						//debugger;
							if(service==0){
								//不可点击
								if(row.servicePersonRealName!=undefined){
							return row.servicePersonRealName;}else{
								return "";	
							}
							}else{
								if(row.servicePersonRealName!=undefined){
								//可点击
								return "<a class='linktext service' >"+row.servicePersonRealName+"</a>";
								}else{
									return "";
								}
								}
						},
					events: 'operateEvents'	
				

			 },
			 {
					field: 'seeFlg',// 返回json数据中的name
					title: '查看',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter : function (value, row, index) {
						var text = '';
						if (row.seeFlg == 0){
						   	text = "未读";
					    } else if (row.seeFlg == 1){
					    	text = "已读";
			    		}else if (row.seeFlg == 2){
					    	text = "查看";
			    		}
						    return text;
						}
			 },
			{
				 field: 'id',// 返回json数据中的name
				title: "操作",// 表格表头显示文字
				align: 'center',// 左右居中
				valign: 'middle',// 上下居中
				formatter: function (value, row, index) {
					var html="";
					if(taskedit==1){
						html="<button type='button' class='btn btn-primary top0 edit' >编辑</button>";	
					}
					if(taskdelete==1){
						html+="<button type='button' class='btn btn-default btn-size btn-pos  delete' >删除</button>";
					}
					return html;
					
					//return "<button type='button' class='btn btn-primary top0 edit' >编辑</button>";
				},
				 events: 'operateEvents'
			}
			
		],
		//liuli 2019/02/02 start
		//页码跳转的时候保存当前页码
		onPageChange:function(number,size){
			sessionStorage.setItem('task_pn',number,size);
		},
		//liuli 2019/02/02 end
		onLoadSuccess: function(data){
			   // alert(data.totalOrderDuration+"totalOrderDuration");//加载成功时执行
			//whs 2019/02/15 begin
			//if(data.totalOrderDuration!=undefined && data.totalServiceDuration!=undefined ){
			//$("#totalOrderDuration").text("服务总时长： "+data.totalOrderDuration);
			//$("#totalServiceDuration").text("实际总时长："+data.totalServiceDuration);
			//}else{
			//	$("#totalOrderDuration").text("");
			//	$("#totalServiceDuration").text("");
			//}
			if(data.totalOrderDuration!=undefined){
				$("#OrderDuration").text("预约总时长");
				$("#totalOrderDuration").text(data.totalOrderDuration);
				}else{
					$("#OrderDuration").text("");
					$("#totalOrderDuration").text("");
				}
			if(data.totalServiceDuration!=undefined ){
				$("#ServiceDuration").text("实际总时长");
				$("#totalServiceDuration").text(data.totalServiceDuration);
				}else{
					$("#ServiceDuration").text("");
					$("#totalServiceDuration").text("");
				}
			//whs 2019/02/15 end
			if(taskedit==0&&taskdeletet==0){
				//没有权限编辑
				
			$('#table').bootstrapTable('hideColumn', 'id');
			}
		
			if(data.statusCode == "401"){
				 window.location.href="login.html";
			}else if(data.statusCode == "512"){
				//alert("错误");
				Swal.fire({
					  type: 'error',
					  title: data.msg
				})
			}
				console.info("加载成功");
				//alert("表格加载成功")
		},
		onLoadError: function(){  //加载失败时执行
			//alert("表格加载失败")
			    console.info("加载数据失败");
		}
		
	});
	//表格参数设置
queryParams: function queryParams(params) {// 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
	//服务类别
	var typeCategory = document.getElementsByName('serviceCategoryCodes');
    var serviceCategoryCodes = new Array();
    for(var i = 0; i < typeCategory.length; i++){
     if(typeCategory[i].checked)
    	 serviceCategoryCodes.push(typeCategory[i].value);
    }
   // alert(serviceCategoryCodes)
    //订单状态
    var typeOrder = document.getElementsByName('orderStatus');
    var orderStatus = new Array();
    for(var i = 0; i < typeOrder.length; i++){
     if(typeOrder[i].checked)
    	 orderStatus.push(typeOrder[i].value);
    }
    //异常状态任务
    var typeAbnormal = document.getElementsByName('abnormalOrder');
    var abnormalOrder = new Array();
    for(var i = 0; i < typeAbnormal.length; i++){
     if(typeAbnormal[i].checked)
    	 abnormalOrder.push(typeAbnormal[i].value);
    }
	   return {
				limit : params.limit,// 每页要显示的数据条数
				offset : params.offset,// 每页显示数据的开始行号
//				//订单编号
				orderNo : $("#orderNo").val(),
//		
//				//服务类别
				serviceCategoryCodes:serviceCategoryCodes,
//				
//			    //客户编号
				customUserNumber:$("#customUserNumber").val(),
//				
//			    //服务人员工号
				servicePersonUserNumber:$("#servicePersonUserNumber").val(),
//				
//			    //客户姓名
				customRealName:$("#customRealName").val(),  
//			
//			    //服务人员姓名
				servicePersonRealName:$("#servicePersonRealName").val(), 
//			    //客户生日
				customBirthday:$("#customBirthday").val(),
//				//服务人员生日
				servicePersonBirthday:$("#servicePersonBirthday").val(),
//				//  客户身份证号
				customIdCard:$("#customIdCard").val(),   
//			    //服务人员身份证号
				servicePersonIdCard:$("#servicePersonIdCard").val(), 
//			    //客户手机号
				customMobile:$("#customMobile").val(),
//				 //服务人员手机号
				servicePersonMobile:$("#servicePersonMobile").val(),   
//			    //任务状态
				orderStatus:orderStatus, 
//			    //预约日期From
				orderDateFrom:$("#orderDateFrom").val(),
//				// 预约日期To
				orderDateTo:$("#orderDateTo").val(),
//				//  服务时长From
				orderDurationFrom:$("#orderDurationFrom").val(),   
//			    //服务时长To
				orderDurationTo:$("#orderDurationTo").val(), 
//			    //预约时间From
				orderBeginTimeFrom:$("#orderBeginTimeFrom").val(),
//				 //服务时长To
			    orderBeginTimeTo:$("#orderBeginTimeTo").val(), 
//			    
//			    //预约时间From
			    abnormalOrder:abnormalOrder,
			}
	};
	
}
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
	  validateResult= $("#form").validate({
		  onkeyup : function(orderNo,customUserNumber,servicePersonUserNumber,
				  servicePersonRealName,customIdCard,servicePersonIdCard,
				  servicePersonIdCard,customMobile,servicePersonMobile,orderDateTo,orderBeginTimeTo,orderDurationTo,
				  servicePersonBirthday,customBirthday){
			  $("#orderNo").valid();
			  $("#customUserNumber").valid();
			  $("#servicePersonUserNumber").valid();
			  $("#servicePersonRealName").valid();
			  $("#customRealName").valid();
			  $("#customIdCard").valid();
			  $("#servicePersonIdCard").valid();
			  $("#customMobile").valid();
			  $("#servicePersonMobile").valid();
			  $("#orderDateTo").valid();
			  $("#orderBeginTimeTo").valid();
			  $("#orderDurationTo").valid();
			  $("#servicePersonBirthday").valid();
			  $("#customBirthday").valid();
			
		  },
	        rules: {
	        	orderNo:{
	                maxlength:12
	            },
	            customUserNumber:{
	            	maxlength:8
	            },
	            servicePersonUserNumber:{
	            	maxlength:16
	            },
	            customRealName:{
	            	maxlength:8
	            },
	            servicePersonRealName:{
	            	maxlength:8
	            },
	            customIdCard:{
	            	maxlength:18
	            },
	            servicePersonIdCard:{
	            	maxlength:18
	            },
	            customMobile:{
	            	digits:true,
	            	maxlength:11
	            },
	            servicePersonMobile:{
	            	digits:true,
	            	maxlength:11
	            },
	            orderDateFrom:{
	            	isDate:"#orderDateFrom"
	            },
	            orderDateTo: {
	            	isDate:"#orderDateTo",
	                compareDate: "#orderDateFrom"
	            },
	           
	            orderBeginTimeTo:{
	               
	            	compareTime: "#orderBeginTimeFrom"
	            },
	            orderDurationTo:{
	                gt:true,
	                digits:true,
	                min:0
	            },
	            orderDurationFrom:{
	               
	                digits:true,
	                min:0
	            },
                customBirthday:{
                	isDate:"#customBirthday",
	            },
	            servicePersonBirthday:{
	            	isDate:"#servicePersonBirthday",
	            },
	           
	            
	    },
	     messages: {
	    	 orderNo:{
	                maxlength:"任务编号长度不能大于12位"
	            },
	            customUserNumber:{
	            	maxlength:"客户编号长度不能大于8位"
	            },
	            servicePersonUserNumber:{
	            	maxlength:"服务人员工号长度不能大于16位"
	            },
	            customRealName:{
	            	maxlength:"客户姓名长度不能大于8字"
	            },
	            servicePersonRealName:{
	            	maxlength:"服务人员姓名长度不能大于8字"
	            },
	            customIdCard:{
	            	maxlength:"客户身份证号长度必须为18位"
	            },
	            servicePersonIdCard:{
	            	maxlength:"服务人员身份证号长度必须为18位"
	            },
	            customMobile:{
	            	digits:"客户手机号必须为数字",
	            	maxlength:"客户手机号长度必须为11位"
	            },
	            servicePersonMobile:{
	            	digits:"服务人员手机号必须为数字",
	            	maxlength:"服务人员手机号长度必须为11位"
	            },
	          
	            orderDateTo: {
	            
	                compareDate: "最小预约日期必须小于等于最大预约日期"
	            },
	           
	            orderBeginTimeTo:{
	               
	            	compareTime: "最小预约开始时间必须小于等于最大预约开始时间"
	            },
	            orderDurationFrom:{
	             
	                digits:"最小预约时长必须为非负整数",
	                min:"最小预约时长必须大于等于0"
	            },
	            orderDurationTo:{
	               
	                digits:"最大预约时长必须为非负整数",
	                min:"最大预约时长必须大于等于0"
	            }
	     },

	errorPlacement: function(error, element) {

	 error.appendTo(element.parent());
			},
	
	});
	  return validateResult;
	}
//开始时间结束时间比较
jQuery.validator.methods.compareTime = function(value, element, param) {
    var startDate ="2019-01-01"+' '+ jQuery(param).val() + ":00";//补全yyyy-MM-dd HH:mm:ss格式
    var valuedate = "2019-01-01"+' '+value + ":00";
    var tempParam=jQuery(param).val();
    var tempvalue=value;
if(tempParam==""&&tempvalue==""){
	return true;
}else if(tempParam!="" && tempvalue==""){
	return true;
}else if(tempParam==""&&tempvalue!=""){
	return true;
}
else{
	 var date1 = new Date(Date.parse(startDate.replace("-", "/")));
	    var date2 = new Date(Date.parse(valuedate.replace("-", "/")));
	    return date1 <= date2;
}
   
};
//日期check
jQuery.validator.methods.compareDate = function(value, element, param) {
	
	  var startDate = jQuery(param).val() ;
	    var value = value;
	    if(startDate==""&&value==""){
	    	return true;
	    }else if(startDate==""&&value!=""){
	    	return true;
	    }else if(startDate!=""&&value==""){
	    	return true;
	    }
	    else{
	    	   var date1 = new Date(Date.parse(startDate.replace("-", "/")));
	    	   // alert(date1)
	    	    var date2 = new Date(Date.parse(value.replace("-", "/")));
	    	    //alert(date2)
	    	    return date1 <=date2;
	    }
 
};
//最长时间比较
jQuery.validator.addMethod("gt", function (value, param) {

    return duration();
}, $.validator.format("最小预约时长必须小于等于最大预约时长"));
function duration() {
    var returnVal = true;
    var orderDurationFrom = parseFloat($("#orderDurationFrom").val());
    var orderDurationTo = parseFloat($("#orderDurationTo").val());
    if (orderDurationFrom > orderDurationTo) {
        returnVal = false;
    }
    return returnVal;

}
//点击查询
$(".search").click(function(){
		// 提交之前进行验证
	   if (formValidate().form()) { //判断校验是否符合规则
	        // 符合规则进入后台 ajax处理
		     //liuli 2019/02/02 start
	    	//验证成功将查询信息保存到session里
	    	var task_Manage_Conditions = {
	    		    // 任务编号
	    		orderNo:$("#orderNo").val(),
				    // 服务类别
	    		serviceCategoryCodes1:$('#inlineCheckbox1').prop('checked'),
	    		serviceCategoryCodes2:$('#inlineCheckbox2').prop('checked'),
	    		serviceCategoryCodes3:$('#inlineCheckbox3').prop('checked'),
				    // 客户编号
	    		customUserNumber:$("#customUserNumber").val(),
	    		    // 服务人员工号
	    		servicePersonUserNumber:$("#servicePersonUserNumber").val(),
	    		    // 客户姓名
	    		customRealName:$("#customRealName").val(), 
	    		    // 服务人员姓名
	    		servicePersonRealName:$("#servicePersonRealName").val(),
	    		    // 客户生日
	    		customBirthday:$("#customBirthday").val(),
	    		    // 服务人员生日
	    		servicePersonBirthday:$("#servicePersonBirthday").val(),
	    		    // 客户身份证号
	    		customIdCard:$("#customIdCard").val(),
	    		    // 服务人员身份证号
	    		servicePersonIdCard:$("#servicePersonIdCard").val(),
	    		    // 客户手机号
				customMobile:$("#customMobile").val(),
				    // 服务人员手机号
				servicePersonMobile:$("#servicePersonMobile").val(),
				    // 任务状态
				orderStatus1:$('#orderStatus1').prop('checked'),
				orderStatus2:$('#orderStatus2').prop('checked'),
				orderStatus3:$('#orderStatus3').prop('checked'),
				orderStatus4:$('#orderStatus4').prop('checked'),
				    // 预约日期-开始
				orderDateFrom:$("#orderDateFrom").val(),
	    		    // 预约日期-结束
				orderDateTo:$("#orderDateTo").val(), 
				    // 服务时长-开始
				orderDurationFrom:$("#orderDurationFrom").val(),
				    // 服务时长-结束
				orderDurationTo:$("#orderDurationTo").val(),
				    // 预约开始时间-开始
				orderBeginTimeFrom:$("#orderBeginTimeFrom").val(),
	    		    // 预约开始时间-结束
				orderBeginTimeTo:$("#orderBeginTimeTo").val(),
				    // 异常状态任务
				abnormalOrder1:$('#abnormalOrder1').prop('checked'),
				abnormalOrder2:$('#abnormalOrder2').prop('checked')
	    	};
	    	//以JSON格式保存
	    	sessionStorage.setItem('task_Man_Cnds', JSON.stringify(task_Manage_Conditions));
	    	sessionStorage.removeItem('task_pn');
			//liuli 2019/02/02 end
	    	initData();
	    }else{
	        // 不符合规则返回
	        return false;
	    } 

	});	
//点击日历
$(document).on("click",".setok",function(){
	if (formValidate().form()) { //判断校验是否符合规则
        
    }else{
        // 不符合规则返回
        return false;
    } 

});
window.operateEvents = {
		
		// 点击编辑
		'click .edit': function (e, value, row, index) {
		window.location.href = "service-task-edit.html?"+window.btoa(unescape(encodeURIComponent("id=" + row.id))) ;
		},
		// 点击客户姓名
		'click .custom': function (e, value, row, index) {
		window.location.href = "customer-edit.html?"+window.btoa(unescape(encodeURIComponent("userId=" + row.customId+"&customFlag=0")));
		},
		// 点击服务人员姓名
		'click .service': function (e, value, row, index) {
		window.location.href = "service-person-edit.html?"+window.btoa(unescape(encodeURIComponent("serviceFlag=1&userId=" + row.servicePersonId ))) ;
		},
		//删除行
		'click .delete': function (e, value, row, index) {
		//alert("点击删除按钮")
		$("#myModal").modal('show');
		$(".sure").on("click",function(){
			$.ajax({
				type:"delete",
				url:urlstorage+"task/"+row.id,
				dataType: "json", 
				async: false,
				contentType : "application/json",
				beforeSend: function(request) {
					request.setRequestHeader("Authorization", token);
					},
//					data:JSON.stringify( {
//					roleId:row.roleId,
//					status:0
//					}),
					success:function(result){
					//弹出框显示
						if(result.statusCode=="200"){
//						$('#table').bootstrapTable('remove', {
//						field: 'id',
//						values: [row.id]
//						});
						//弹出框隐藏
					$("#myModal").modal('hide');
					//if(result.statusCode=="200"){
					Swal.fire({
						title: result.msg,
						type: 'success',
						showCancelButton: false,
						confirmButtonColor: '#3085d6',
						confirmButtonText: 'OK'
					});
					
					//alert("删除刷新前");
					//initData();
					var refreshurl= urlstorage+"task/unassignedTask/list";
					$('#table').bootstrapTable('refresh',refreshurl);
					//alert("删除刷新后")
					}else if(result.statusCode=="512"){
						$("#myModal").modal('hide');
						Swal.fire({
							  type: 'error',
							  //title: '删除失败！',
							  title: result.msg
						})
					}else if(result.statusCode == "401"){
						//alert(">>>>>>>")
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
	}
	};
})





