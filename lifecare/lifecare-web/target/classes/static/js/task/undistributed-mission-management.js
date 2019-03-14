
$(function(){
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	//获取当前用户权限
	 var permission=localStorage.getItem("userNumber")+"permission";
	 //控制按钮显示
	var permissions= localStorage.getItem(permission);
	//客户查看权限标识
	var custom=0;
	var taskedit=0;

	 if(permissions.length>0){
		var data= permissions.split(",");
        for(var i=0;i<data.length;i++){
            if(data[i]=="customer:view"){
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
            
            //break;
        } 
	 };
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
	});
	jeDate("#orderBeginTimeTo",{
	format: "hh:mm",
	});
	jeDate("#birthday",{
		format: "YYYY-MM-DD"
	});
//渲染表格
function initData(){
	
	$("#table").bootstrapTable('destroy');
	$("#table").bootstrapTable({ // 对应table标签的id
		method : "post",
		url: urlstorage+"task/unassignedTask/list", // 获取表格数据的url
		cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
		striped: true,  //表格显示条纹，默认为false
		pagination: true, // 在表格底部显示分页组件，默认false
		pageSize: 10, // 页面数据条数
		pageNumber: 1, // 首页页码
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
					title: '服务类型',// 表格表头显示文字
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
					title: '服务时长',// 表格表头显示文字
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
							return "<a class='linktext'>"+row.realName+"</a>";
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
					return "<button type='button' class='btn btn-default top0 edit' >编辑</button>";
					//return "<button type='button' class=' btn btn-primary btn-default col-md-offset-1 btn-size edit'  onclick='edit("+row.userId+")'>编辑</button></td>"; 
				},
				 events: 'operateEvents'
			}
		
			
		],
		onLoadSuccess: function(data ){
			    //alert(data);//加载成功时执行
			//alert(taskedit)
			$("#totalOrderDuration").text(data.totalOrderDuration);
			if(taskedit==0){
				//没有权限编辑

			$('#table').bootstrapTable('hideColumn', 'id');
			}
				console.info("加载成功"+data.statusCode);
		},
		onLoadError: function(){ //加载失败时执行
			window.location.href="login.html";
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
    // alert(">>>>"+serviceCategoryCodes)
    }};
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
//验证方法
function formValidate(){
	  validateResult= $("#form").validate({
		  onkeyup : function(orderNo,userNumber,realName,mobile,idCard,orderDateTo,orderBeginTimeTo){
			  $("#orderNo").valid();
			  $("#userNumber").valid();
			  $("#realName").valid();
			  $("#mobile").valid();
			   $("#idCard").valid();
			   $("#orderDateTo").valid(); 
			   $("#orderBeginTimeTo").valid();
		  },
	        rules: {
	        	orderNo:{
	                maxlength:12
	            },
	            userNumber:{
	            	maxlength:8
	            },
	            realName:{
	            	maxlength:16
	            },
	            idCard:{
	            	maxlength:18
	            },
	            mobile:{
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
	            },
	            idCard:{
	            	maxlength:"客户身份证最长18位"
	            }
	          
	           
	            
	    },
	     messages: {
	    		orderNo:{
	                maxlength:"任务编号最长12位"
	            },
	            userNumber:{
	            	maxlength:"客户编号最大长度8位"
	            },
	            realName:{
	            	maxlength:"客户姓名最长16位"
	            },
	            mobile:{
	            	digits:"只能输入数字",
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
			window.location.href = "undistributed-mission-edit.html?id=" + row.id ;
		},
		// 点击客户姓名
		'click .linktext': function (e, value, row, index) {
			window.location.href = "customer-edit.html?userId="+row.customId+"&customFlag=1" ;
		},
	};
})





