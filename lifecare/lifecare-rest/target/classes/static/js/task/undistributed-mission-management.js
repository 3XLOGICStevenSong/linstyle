
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
	//客户查看权限标识
	var custom=0;
	var taskedit=0;
	var taskdelete=0;
	 if(permissions.length>0){
		var data= permissions.split(",");
        for(var i=0;i<data.length;i++){
            if(data[i]=="customer:update"){
           	custom=1;
           //	alert(custom)
            };
            if(data[i]=="task:update"){
            	taskedit=1;
            }
            if(data[i]=="task:create"){
                $(".add").css("display","inline-block");
            }
            if(data[i]=="task:query"){
                $(".search").css("display","inline-block");
            }
            if(data[i]=="task:query"){
                $(".search").css("display","inline-block");
            }
            if(data[i]=="task:delete"){
            	 taskdelete=1;
            }
           
            //break;
        } 
	 };
	 $("[name='serviceCategoryCodes']").attr("checked",'true');//全选 
	//渲染表格
	initData();
	//初始化表单验证
	formValidate();
	
	//日期初始化
	jeDate("#orderDateFrom",{
	format: "YYYY-MM-DD",
	});
	jeDate("#orderDateTo",{
	format: "YYYY-MM-DD",
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
	jeDate("#birthday",{
		format: "YYYY-MM-DD"
	});
//渲染表格
function initData(){
	//liuli 2019/02/01 start
	//获取session查询条件
	if(sessionStorage.getItem('undis_Manage_Cnds')!=null){
		var Srn_Cnds_JsonStr = sessionStorage.getItem('undis_Manage_Cnds');
		var undis_Manage_Cnds = JSON.parse(Srn_Cnds_JsonStr);
		// 服务类别
		if(undis_Manage_Cnds.serviceCategoryCodes1){
			$("input[type='checkbox']:eq(0)").prop("checked", true);
		}else{
			$("input[type='checkbox']:eq(0)").prop("checked", false);
		}
		if(undis_Manage_Cnds.serviceCategoryCodes2){
			$("input[type='checkbox']:eq(1)").prop("checked", true);
		}else{
			$("input[type='checkbox']:eq(1)").prop("checked", false);
		}
		if(undis_Manage_Cnds.serviceCategoryCodes3){
			$("input[type='checkbox']:eq(2)").prop("checked", true);
		}else{
			$("input[type='checkbox']:eq(2)").prop("checked", false);
		}
		// 任务编号
		$("#orderNo").val(undis_Manage_Cnds.orderNo),		
	    // 客户编号
		$("#userNumber").val(undis_Manage_Cnds.userNumber),
		// 预约日期-From
		$("#orderDateFrom").val(undis_Manage_Cnds.orderDateFrom),
		// 预约日期-To
		$("#orderDateTo").val(undis_Manage_Cnds.orderDateTo),
	    // 客户姓名
		$("#realName").val(undis_Manage_Cnds.realName),
		// 预约开始时间-开始
		$("#orderBeginTimeFrom").val(undis_Manage_Cnds.orderBeginTimeFrom),
		// 预约开始时间-结束
		$("#orderBeginTimeTo").val(undis_Manage_Cnds.orderBeginTimeTo),
	    // 客户生日
		$("#birthday").val(undis_Manage_Cnds.birthday), 
		//服务时长-开始
		$("#orderDurationFrom").val(undis_Manage_Cnds.orderDurationFrom),
		//服务时长-结束
		$("#orderDurationTo").val(undis_Manage_Cnds.orderDurationTo),
		// 客户手机号
		$("#mobile").val(undis_Manage_Cnds.mobile),   
		// 客户身份证号
		$("#idCard").val(undis_Manage_Cnds.idCard)
	}
	//获取session保存当前页码
	if(sessionStorage.getItem('undistribute_pn')==null){
		pn = 1
	}else{
		pn = parseInt(sessionStorage.getItem('undistribute_pn'))
	}
	//liuli 2019/02/01 end
	$("#table").bootstrapTable('destroy');
	$("#table").bootstrapTable({ // 对应table标签的id
		method : "post",
		url: urlstorage+"task/unassignedTask/list", // 获取表格数据的url
		cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
		striped: true,  //表格显示条纹，默认为false
		pagination: true, // 在表格底部显示分页组件，默认false
		pageSize: 10, // 页面数据条数
		//liuli 2019/02/01 start
		pageNumber: pn, // 首页页码
		//liuli 2019/02/01 end
		sidePagination: 'server', // 设置为服务器端分页
	    // showColumns: true,          
		showHeader : true,
		queryParams:queryParams,
	
	     //设置header
		ajaxOptions: {
			beforeSend: function(request) {
			  request.setRequestHeader("Authorization", token);
			}	
		},
			
		columns : [
//			{
//				field: 'id', // 返回json数据中的name
//				title: 'id', // 表格表头显示文字
//				align: 'center', // 左右居中
//				valign: 'middle', // 上下居中
//				hidden:true,
//				visible: false,
//			},
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
					valign: 'middle',// 上下居中
//					formatter : function (value, row, index) {
//						var text = '';
//						if (row.sex == 1){
//						   	text = "女";//性别转换
//					    } else if (row.sex == 0){
//					    	text = "男";//性别转换
//			    		}
//						    return text;
//						}
			
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
					field: 'realName',// 返回json数据中的name
					title: '客户姓名',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter: function (value, row, index) {
						if(custom==0){
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
					title: '客户手机',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
				

			 },
	
			{
				 field: 'id', // 返回json数据中的name
				title: "操作",// 表格表头显示文字
				align: 'center',// 左右居中
				valign: 'middle',// 上下居中
				formatter: function (value, row, index) {
					var html="";
					if(taskedit==1){
						html="<button type='button' class='btn btn-primary top0 edit' tabIndex='18'>编辑</button>";	
					}
					if(taskdelete==1){
						html+="<button type='button' class='btn btn-default btn-size btn-pos  delete' >删除</button>";
					}
					return html;
				},
				 events: 'operateEvents'
			}
		
			
		],
		//liuli 2019/02/01 start
		//页码跳转的时候保存当前页码
		onPageChange:function(number,size){
			sessionStorage.setItem('undistribute_pn',number,size);
		},
		//liuli 2019/02/01 end
		onLoadSuccess: function(data ){
			    //alert(data);//加载成功时执行
			//alert(taskedit)
			if(data.totalOrderDuration!=undefined){
				
				$("#totalLable").text("预约总时长");
				$("#totalOrderDuration").text(data.totalOrderDuration);
				}else{
					$("#totalLable").text("");
					$("#totalOrderDuration").text("");
				}
			//$("#totalOrderDuration").text(data.totalOrderDuration);
			if(taskedit==0&&taskdelete==0){
				//没有权限编辑
			$('#table').bootstrapTable('hideColumn', 'id');
			}
			if (data.statusCode == "401") {
				window.location.href = "login.html";
			}else if(data.statusCode=="512"){
				Swal.fire({
					  type: 'error',
					  title: data.msg
				})
			}
				console.info("加载成功"+data.statusCode);
		},
		onLoadError: function(){ //加载失败时执行
			//window.location.href="login.html";
			    console.info("加载数据失败");
		}
		
	});
	//表格参数设置
queryParams: function queryParams(params) {// 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
	var type = document.getElementsByName('serviceCategoryCodes');
    var serviceCategoryCodes = new Array();
    for(var i = 0; i < type.length; i++){
     if(type[i].checked){
    	 serviceCategoryCodes.push(type[i].value);
    // 
    }};
   // alert(">>>>"+serviceCategoryCodes)
			return {
				limit : params.limit,// 每页要显示的数据条数
				offset : params.offset,// 每页显示数据的开始行号
				//订单编号
				orderNo : $("#orderNo").val(), //用户类型(2:服务人员)
				//服务人员工号
				serviceCategoryCodes:serviceCategoryCodes,
			    //客户编号
				userNumber:$("#userNumber").val(),
			    //预约日期From
				orderDateFrom:$("#orderDateFrom").val(),
			    //预约日期To
				orderDateTo:$("#orderDateTo").val(),   
			    //客户姓名
				realName:$("#realName").val(), 
			    //预约时间From
				orderBeginTimeFrom:$("#orderBeginTimeFrom").val(),
				//预约时间To
				orderBeginTimeTo:$("#orderBeginTimeTo").val(),
				// 客户生日
				birthday:$("#birthday").val(),   
			    //服务时长From
				orderDurationFrom:$("#orderDurationFrom").val(), 
			    //服务时长To
				orderDurationTo:$("#orderDurationTo").val(),
				//客户手机号
				mobile:$("#mobile").val(),
				//客户身份证号
				idCard:$("#idCard").val()
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
		  onkeyup : function(orderNo,userNumber,realName,mobile,idCard,orderDateTo,orderBeginTimeTo,birthday){
			  $("#orderNo").valid();
			  $("#userNumber").valid();
			  $("#realName").valid();
			  $("#mobile").valid();
			   $("#idCard").valid();
			   $("#orderDateTo").valid(); 
			   $("#orderBeginTimeTo").valid();
			   $("#birthday").valid(); 
		  },
	        rules: {
	        	orderNo:{
	                maxlength:12
	            },
	            userNumber:{
	            	maxlength:8
	            },
	            realName:{
	            	maxlength:8
	            },
	            idCard:{
	            	maxlength:18
	            },
	            mobile:{
	            	digits:true,
	            	maxlength:11
	            },
	            orderDateFrom: {
	    	        isDate:"#orderDateFrom",
	    	            },
	            orderDateTo: {
	                isDate:"#orderDateTo",
	                compareDate: "#orderDateFrom"
	            },
	           
	            orderBeginTimeTo:{
	               
	            	compareTime: "#orderBeginTimeFrom"
	            },
	           
	            idCard:{
	            	rangelength:[18,18]
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
	            birthday:{
	            	isDate:"#birthday"
	            }
	    },
	     messages: {
	    		orderNo:{
	                maxlength:"任务编号长度不能大于12位"
	            },
	            userNumber:{
	            	maxlength:"客户编号长度不能大于8位"
	            },
	            realName:{
	            	maxlength:"客户姓名长度不能大于8字"
	            },
	            mobile:{
	            	digits:"客户手机号必须为数字",
	            	maxlength:"客户手机号长度必须为11位"
	            	
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
	            },
	            idCard:{
	            	rangelength:"客户身份证号长度必须为18位"
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
    var valueDate = "2019-01-01"+' '+value + ":00";
    var tempParam=jQuery(param).val();
    var tempvalue=value;
if(tempParam==""&&tempvalue==""){
	return true;
}else if(tempParam!=""&&tempvalue==""){
	return true;
}else if(tempParam==""&&tempvalue!=""){
	return true;
}
else{
	 var date1 = new Date(Date.parse(startDate.replace("-", "/")));
	    var date2 = new Date(Date.parse(valueDate.replace("-", "/")));
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
		    //liuli 2019/02/01 start
	    	//验证成功将查询信息保存到session里
	    	var undistribute_Manage_Conditions = {
	    		    // 任务编号
	    		orderNo:$("#orderNo").val(),
				    // 服务类别
	    		serviceCategoryCodes1:$('#inlineCheckbox1').prop('checked'),
	    		serviceCategoryCodes2:$('#inlineCheckbox2').prop('checked'),
	    		serviceCategoryCodes3:$('#inlineCheckbox3').prop('checked'),
				    // 客户编号
	    		userNumber:$("#userNumber").val(),
				    // 预约日期-开始
	    		orderDateFrom:$("#orderDateFrom").val(),
	    		    // 预约日期-结束
	    		orderDateTo:$("#orderDateTo").val(), 
				    // 客户姓名
	    		realName:$("#realName").val(), 
				    // 预约开始时间-开始
	    		orderBeginTimeFrom:$("#orderBeginTimeFrom").val(),
	    		    // 预约开始时间-结束
	    		orderBeginTimeTo:$("#orderBeginTimeTo").val(),
				    //客户生日
	    		birthday:$("#birthday").val(),
				    //服务时长-开始
				orderDurationFrom:$("#orderDurationFrom").val(),
				    //服务时长-结束
				orderDurationTo:$("#orderDurationTo").val(),
				    //客户手机号
				mobile:$("#mobile").val(),
				    //客户身份证号
				idCard:$("#idCard").val()
	    	};
	    	//以JSON格式保存
	    	sessionStorage.setItem('undis_Manage_Cnds', JSON.stringify(undistribute_Manage_Conditions));
	    	sessionStorage.removeItem('undistribute_pn');
			//liuli 2019/02/01 end
	    	initData();
	    }else{
	        // 不符合规则返回
	        return false;
	    } 

	});	

//点击追加
$(".add").on("click",function(){
	window.location.href="undistributed-mission-add.html";
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
			//alert(row.id)
			window.location.href = "undistributed-mission-edit.html?"+window.btoa(unescape(encodeURIComponent("id=" + row.id))) ;
		},
		// 点击客户姓名
		'click .custom': function (e, value, row, index) {
			window.location.href = "customer-edit.html?"+window.btoa(unescape(encodeURIComponent("userId="+row.customId+"&customFlag=1"))) ;
		},
		//删除行
		'click .delete': function (e, value, row, index) {
			
		$("#myModal").modal('show');
		$(".sure").on("click",function(){
			$.ajax({
				type:"delete",
				url:urlstorage+"task/"+row.id,
				dataType: "json", 
				contentType : "application/json",
				beforeSend: function(request) {
					request.setRequestHeader("Authorization", token);
					},
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
					//initData();
					
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
						var refreshurl= urlstorage+"task/unassignedTask/list";
						$('#table').bootstrapTable('refresh',refreshurl);
					},
				
			});
			$(".sure").unbind();
		});
	}
		}
		
	
})





