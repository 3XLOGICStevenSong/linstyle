
$(function(){

	//获取token AND url
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	if(urlstorage==null){
		 window.location.href="login.html";	
	}
	var storageUserId=localStorage.getItem("userId");
	$("#backendPersonId").val(storageUserId);
	formValidate();
	//选择人员信息初始化
    var url = location.search; 
    var id="";
    if(url!=null){
    var params=$.getParam(url);
    var userNumber=params['userNumber']; 
    var realName=params['realName']; 
    var mobile=params['mobile']; 
    var userAddr=params['userAddr']; 
    var userLevel=params['userLevel']; 
    var sex=params['sex'];
    var age=params['age'];
    var customId=params['userId'];
    if(customId==undefined){
        //获取管理页面传入的订单ID
        id=params['id'];
        $("#id").val(id);
        initData();
    }
    if(customId!=undefined){
    	   
        //获取缓存json
    	var jsondata=localStorage.getItem("editdata");
    	$("#form").initForm(jsondata);
    	 $("#customId").val(customId);
        }
    if(userNumber!=undefined){
    $("#userNumber").val(userNumber);}
    if(realName!=undefined){
    $("#realName").val(realName);}
    if(mobile!=undefined){
    $("#mobile").val(mobile);}
    if(userAddr!=undefined){
    $("#userAddr").val(userAddr);}
    if(userLevel!=undefined){
    $("#userLevel").val(userLevel);}
    if(sex!=undefined){
    	if(sex==0){
	    	$("#sex").val("男");
	    }
	    if(sex==1){
	    	$("#sex").val("女");
	    }
    }
    if(age!=undefined){
    $("#age").val(age);}


    };	
    function dateFormater(){
    	var datetime = new Date();
    	//时间格式化
    	var year=datetime.getFullYear();
    	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    	var d = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    	
    	var nowdate=year+'-'+month+"-"+d;
    	return nowdate;
    };
    var mindate=dateFormater();
 //日期格式化
	jeDate("#orderDate",{
		format: "YYYY-MM-DD",
		 //onClose:true,
	        minDate:mindate,
		});
		jeDate("#orderBeginTime",{
		format: "hh:mm",
		 isToday:false, 
		 language:{
             name   : "cn",
             month  : ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
             weeks  : [ "日", "一", "二", "三", "四", "五", "六" ],
             times  : ["小时","分钟","秒数"],
             timetxt: ["时间选择","开始时间","结束时间"],
             backtxt:"返回日期",
             clear  : "清空",
            
             yes    : "确定"
         },
		});
		jeDate("#orderEndTime",{
		format: "hh:mm",
		 isToday:false, 
		 language:{
             name   : "cn",
             month  : ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
             weeks  : [ "日", "一", "二", "三", "四", "五", "六" ],
             times  : ["小时","分钟","秒数"],
             timetxt: ["时间选择","开始时间","结束时间"],
             backtxt:"返回日期",
             clear  : "清空",
            
             yes    : "确定"
         },
		});
		
  //页面初始化
		function initData(){
	$.ajax({
		type:"GET",
		url:urlstorage+"task/unassignedTask/"+id,
		//url:urlstorage+"task/unassignedTask/"+1,
		dataType: "json", 
		contentType : "application/json",
		beforeSend: function(request) {
			request.setRequestHeader("Authorization", token);
			},
			//data:JSON.stringify( {
			//id:1
			//}),
			success:function(result,status,httpResponse){
				if(result.statusCode=="200"){
					//token是否过期过期更换token
					var token=httpResponse.getResponseHeader('Authorization');
					if(token!=""&&token!=null){
						localStorage.setItem("token", token); 
					}
					//反序列化json 数据			
					$("#form").initForm(result.data);
					if(result.data.sex!=undefined){
				    	if(result.data.sex==0){
					    	$("#sex").val("男");
					    }
					    if(result.data.sex==1){
					    	$("#sex").val("女");
					    }
				    }
					if(result.data.orderStatus==4){
						$('#other').attr("checked",'true');
						$("#stoptxt").css('display','block'); 
					}
				}else if(result.statusCode == "401"){
					 window.location.href="login.html";
				}else if(result.statusCode=="512"){
					Swal.fire({
						  type: 'error',
						  title: result.msg
					})
				}
			}
	});
		}
   // });
	//开始时间结束时间比较
	jQuery.validator.methods.compareTime = function(value, element, param) {
        var startDate ="2019-01-01"+' '+ jQuery(param).val() + ":00";//补全yyyy-MM-dd HH:mm:ss格式
        var value = "2019-01-01"+' '+value + ":00";
  
        var date1 = new Date(Date.parse(startDate.replace("-", "/")));
        var date2 = new Date(Date.parse(value.replace("-", "/")));
        return date1 <= date2;
    };
	//开始时间结束时间比较
	//jQuery.validator.methods.compareTimeIntervel = function(value, element, param) {
		 jQuery.validator.addMethod("compareTimeIntervel", function (value, element, param) {
       if(jQuery(param).val()==""){return true;}
		var startDate ="2019-01-01"+' '+ jQuery(param).val() + ":00";//补全yyyy-MM-dd HH:mm:ss格式
        var value = "2019-01-01"+' '+value + ":00";
  
        var date1 = new Date(Date.parse(startDate.replace("-", "/")));
        var date2 = new Date(Date.parse(value.replace("-", "/")));
        date2.setHours(date2.getHours()-1);
        return date1 <= date2;
    }, "预约结束时间必须大于等于预约开始时间1小时");
		  //日期格式验证
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
    //时间check
	jQuery.validator.methods.compareDate = function(value, element, param) {
		 var appointDate =jQuery(param).val()+ " "+value + ":00";//补全yyyy-MM-dd HH:mm:ss格式())
		//获取当前日期
		var datetime = new Date();
		//获取设定的时间间隔
		var  intevalTime=localStorage.getItem("appointTime");
		//alert(date.setMinutes)
		if(intevalTime!=null){
			datetime.setMinutes(datetime.getMinutes()+parseInt(intevalTime));
			//alert(datetime.getMinutes()+"date.getMinutes()")
		}else{
			datetime.setMinutes(datetime.getMinutes()+60);
		}
		//时间格式化
		var year=datetime.getFullYear();
		var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
		var d = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
		var h = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
		var m = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
		var s = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
		var now=year+'-'+month+"-"+d+" "+h+':'+m+":"+s;
        var date1 = new Date(Date.parse(appointDate.replace("-", "/")));
       // alert(date1)
        var date2 = new Date(Date.parse(now.replace("-", "/")));
        //alert(date2)
        return date1 >= date2;
    };
    
    
  //开始时间结束时间比较
	jQuery.validator.methods.cancelReson = function(value, element, param) {
		//判断取消按钮是否选中 如果选中 必须填写内容，如果不选中返回true；
		
		if ($(param)[0].checked) {
			if(value.trim()==""){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
    };   
    jQuery.validator.addMethod("checkEnter", function (value, element) {
	   	 var paramValue=value.replace(/\s+/g, ""); 
	   	 if(paramValue==""){
	   		 return false;
	   	 }else{
	   		 return true
	   	 }   
   	});
	//验证方法
	function formValidate(){
		  validateResult= $("#form").validate({
			  onkeyup : function(orderDate,orderBeginTime,orderEndTime){
				  $("#orderDate").valid();
				 // $("#orderBeginTime").valid();
				 // $("#orderEndTime").valid();
				 
			  },
		        rules: {
		            customName: {
		                required: true
		            },
		           // sex: {
		           //     required: true
		                //isZipCode:true,
		          //  },
		            orderDate:{
		                required: true,
		                isDate:"#orderDate"
		            },
		            orderBeginTime: {
		                required:true,
		               // compareDate:true
		            },
		            orderEndTime:{
		                required:true,
		                compareTimeIntervel: "#orderBeginTime"
		            },
		           // serviceCategoryCode:{
		            //    required:true,
		           // },
		            content:{
		                required:true,
		                maxlength:4000,
		                checkEnter:true
		            },
//		            saveCnt:{
//		                required:true,
//		                digits:true,
//		                max:3
//		            },
		            remark:{
		            	 maxlength:500,
		            	 //checkEnter:true
		            },
		            cancelReson:{
//		            	cancelReson:"#other",
		            	required:true,
		            	checkEnter:true,
		            	 maxlength:500
		            },
		            
		       
		            
		    },
		     messages: {
		    	 customName: {
		                required: "客户编号不能为空",
		            },
		            //sex:{
		            //    required:"性别不能为空",
		            //},
		            orderDate: {
		               required:"预约日期不能为空",
		         
		            },
		            orderBeginTime: {
		               required:"预约开始时间不能为空"
		             //  compareDate: "只能预约1小时后时间"
		         
		            },
		            orderEndTime: {
		            required:"预约结束时间不能为空",
		              // compareTime: "预约结束时间必须大于等于预约开始时间+1小时!"
		              
		           },
		          // serviceCategoryCode:{
		          //  required:"请选择服务类别",
		          // },
		           content:{
		                required :"服务内容不能为空",
		                checkEnter:"服务内容不能全为空格",
		                maxlength:"服务内容长度不能大于4000字"
		            },
//		            saveCnt:{
//		                required:"保存份数不能为空",
//		                digits:"请输入整数",
//		                max:"最多保存3份"
//		            },
		            remark:{
		            	 maxlength:"备注长度不能大于500字"
		            },
		            cancelReson:{
//		            	cancelReson:"取消服务原因不能为空",
		            	required:"取消服务原因不能为空",
		            	checkEnter:"取消服务原因不能全为空格",
		            	maxlength:"取消服务原因长度不能大于500字"
		            },
		     },

		errorPlacement: function(error, element) {
	
		 error.appendTo(element.parent());
				},
		
		});
		  return validateResult;
		}
	
	//选择按钮
	$(".select").click(function(){
		//跳转到 客户选择页面
		 var editdata = JSON.stringify($("form").serializeObject());
		//存入缓存(未分配任务)
		 localStorage.setItem("editdata",editdata); 
		//跳转到 客户选择页面
		window.location.href="customer-choose.html?selectFlag=2"
	});
	
	//取消按钮
	$("#other").click(function(){
		if ($("#other")[0].checked) {
			//$('input,select,textarea',$('form[name="form"]')).prop('disabled',true);
			$("#stoptxt").css('display','block');
			//$('#cancelReson').removeAttr("disabled");
			//$('#other').removeAttr("disabled");
			
		}else{
			$("#stoptxt").css('display','none'); 
			$("#cancelReson").val("");
			
			//$('input,select,textarea',$('form[name="form"]')).prop('disabled',false);
			//$('#other').removeAttr("disabled");
		}
	});
	//返回按钮
	$(".back").click(function(){
		//跳转到 客户选择页面
		window.location.href="undistributed-mission-management.html"
		//window.location.history.go(-1);
	});
	//保存按钮
		$(".save").click(function(){
			// 提交之前进行验证
			      if (formValidate().form()) { //判断校验是否符合规则
			          // 符合规则进入后台 ajax处理
			    	  var formdata=$("form").serializeObject();
			    	  var dataJson = JSON.stringify($("form").serializeObject());
			    	 // alert(dataJson);
			    	  //console.log(dataJson);
			  		$.ajax({
						type:"PUT",
						url:urlstorage+"task/unassignedTask",
						dataType: "json", 
						contentType : "application/json",
						 beforeSend: function(request) {
			 				request.setRequestHeader("Authorization", token);
			 				},
						data : dataJson,
				
						success:function(result,status,httpResponse){
							if(result.statusCode=="200"){
								//token是否过期过期更换token
								var token=httpResponse.getResponseHeader('Authorization');
								if(token!=""&&token!=null){
									localStorage.setItem("token", token); 
								};
								localStorage.removeItem('editdata');
								Swal.fire({
									title: result.msg,
									type: 'success',
									showCancelButton: false,
									confirmButtonColor: '#3085d6',
									confirmButtonText: 'OK'
								}).then(function(result){
										//返回管理页面
										window.location.href="undistributed-mission-management.html";
								})
							}else if(result.statusCode == "401"){
								 window.location.href="login.html";
							}else if(result.statusCode=="512"){
								Swal.fire({
									  type: 'error',
									  //title: '保存失败！',
									  title: result.msg
								})
							}
						}
			
					
				})
			    }else{
			        // 不符合规则返回
			        return false;
			    } 

	});
	
})
