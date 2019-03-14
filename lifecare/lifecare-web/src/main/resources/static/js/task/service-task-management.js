
$(function(){
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
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
             
             //break;
         } 
	 }
	 //获取待处理url
	    var url = location.search; 
	    if(url!=null&& url!=""){
	    var params=$.getParam(url);
	    var pendingFlag=params['pendingFlag']; 
	    if(pendingFlag!=null&& pendingFlag!=""){
	    	$("[name='serviceCategoryCodes']").attr("checked",'true');//全选 
            var orderStatus = document.getElementsByName("orderStatus");
            orderStatus[0].checked=true;
            orderStatus[1].checked=true;
            var abnormalOrder = document.getElementsByName("abnormalOrder");
            abnormalOrder[0].checked=true;
	    }
	    };	
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
	    	 //minDate:mindate,
	    });
	    jeDate("#orderBeginTimeTo",{
	    	format: "hh:mm",
	    });
	    
	//渲染表格
	initData();
	//初始化表单验证
	formValidate();
//渲染表格
function initData(){
	
	$("#table").bootstrapTable('destroy');
	$("#table").bootstrapTable({ // 对应table标签的id
		method : "post",
		url: urlstorage+"task/list", // 获取表格数据的url
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
					title: '服务类型',// 表格表头显示文字
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
					title: '服务时长',// 表格表头显示文字
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
					title: '服务人员名称',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter: function (value, row, index) {
							if(service==0){
								//不可点击
							return row.servicePersonRealName;
							}else{
								//可点击
								return "<a class='linktext service' >"+row.servicePersonRealName+"</a>";
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
					return "<button type='button' class='btn btn-default top0 edit' >编辑</button>";
				},
				 events: 'operateEvents'
			}
			
		],
		onLoadSuccess: function(data){
			    //alert(data);//加载成功时执行
			if(data.totalOrderDuration!=undefined && data.totalServiceDuration!=undefined ){
			$("#totalOrderDuration").text("服务总时长： "+data.totalOrderDuration);
			$("#totalServiceDuration").text("实际总时长："+data.totalServiceDuration);
			}
			if(taskedit==0){
				//没有权限编辑
				
			$('#table').bootstrapTable('hideColumn', 'id');
			}
		
			if(data.statusCode == "401"){
				 window.location.href="login.html";
			}if(data.statusCode == "404"){
				alert("错误");
			}
				console.info("加载成功");
		},
		onLoadError: function(){  //加载失败时执行
			
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
				//orderStatus:orderStatus, 
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
//			    abnormalOrder:abnormalOrder,
			}
	};
	
}
//验证方法
function formValidate(){
	  validateResult= $("#form").validate({
		  onkeyup : function(orderNo,customUserNumber,servicePersonUserNumber,
				  servicePersonRealName,customIdCard,servicePersonIdCard,
				  servicePersonIdCard,customMobile,servicePersonMobile,orderDateTo,orderBeginTimeTo,orderDurationTo){
			  $("#orderNo").valid();
			  $("#userNumber").valid()
		  },
	        rules: {
	        	orderNo:{
	                maxlength:12
	            },
	            customUserNumber:{
	            	maxlength:8
	            },
	            servicePersonUserNumber:{
	            	maxlength:12
	            },
	            customRealName:{
	            	maxlength:16
	            },
	            servicePersonRealName:{
	            	maxlength:16
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
	            orderDateTo: {
	        
	                compareDate: "#orderDateFrom"
	            },
	           
	            orderBeginTimeTo:{
	               
	            	compareTime: "#orderBeginTimeFrom"
	            },
	            orderDurationTo:{
	                gt:true
	            }
	          
	           
	            
	    },
	     messages: {
	    	 orderNo:{
	                maxlength:"任务编号最长12位"
	            },
	            customUserNumber:{
	            	maxlength:"客户编号最长8位"
	            },
	            servicePersonUserNumber:{
	            	maxlength:"服务人员编号最长16位"
	            },
	            customRealName:{
	            	maxlength:"客户姓名最长16位"
	            },
	            servicePersonRealName:{
	            	maxlength:"服务人员姓名最长16位"
	            },
	            customIdCard:{
	            	maxlength:"客户身份证号最长18位"
	            },
	            servicePersonIdCard:{
	            	maxlength:"服务人员身份证号最长18位"
	            },
	            customMobile:{
	            	digits:"电话号码只能是数字",
	            	maxlength:"电话号码最大长度11位"
	            },
	            servicePersonMobile:{
	            	digits:"电话号码只能是数字",
	            	maxlength:"电话号码最大长度11位"
	            },
	         
	            orderDateTo: {
	            
	                compareDate: "预约日期结束日期必须大于等于预约日期开始日期"
	            },
	           
	            orderBeginTimeTo:{
	               
	            	compareTime: "预约时间结束时间必须大于等于预约时间开始时间"
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
	    return date1 < date2;
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
	    	    return date1 <date2;
	    }
 
};
//最长时间比较
jQuery.validator.addMethod("gt", function (value, param) {

    return duration();
}, $.validator.format("服务时长结束时间必须大于等于服务时长开始时间"));
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
		window.location.href = "service-task-edit.html?id=" + row.id ;
		},
		// 点击客户姓名
		'click .custom': function (e, value, row, index) {
		window.location.href = "customer-edit.html?userId=" + row.id ;
		},
		// 点击服务人员姓名
		'click .service': function (e, value, row, index) {
		window.location.href = "service-person-edit.html?userId=" + row.id ;
		},
		
	};
})





