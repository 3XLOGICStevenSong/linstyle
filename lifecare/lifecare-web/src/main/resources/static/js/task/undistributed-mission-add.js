
$(function(){
    function dateFormater(){
    	var datetime = new Date();
    	//时间格式化
    	var year=datetime.getFullYear();
    	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    	var d = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    	
    	var nowdate=year+'-'+month+"-"+d;
    	return nowdate;
    };
    function timeFormater(){
    	var datetime = new Date();
    	//时间格式化
    	datetime.setMinutes(datetime.getMinutes()+60);
//    	var year=datetime.getFullYear();
//    	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
//    	var d = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    	var h = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
		var m = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
		var nowTime=h+':'+m;
    	return nowTime;
    };
    var mindate=dateFormater();
    var minTime=timeFormater();
	
    jeDate("#orderDate",{
    	 format: "YYYY-MM-DD",
    	 minDate:mindate,
    });
    jeDate("#orderBeginTime",{
        //minDate:"01:02:08",              //最小日期
    	//isinitVal: true,           //最大日期
        format: "hh:mm",
        //minDate:minTime,
       // isinitVal:true,
       // initDate:[{hh:"+1"},true],
    });
    jeDate("#orderEndTime",{
        //minDate:"01:02:08",              //最小日期
    	//isinitVal: true,           //最大日期
        format: "hh:mm",
        //isinitVal:true,
       // initDate:[{hh:"+2"},true],
    });
    
	//获取token AND url
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	var storageUserId=localStorage.getItem("userId");
	$("#backendPersonId").val(storageUserId);
	//选择人员信息初始化
    var url = location.search; 
   // alert(url);
    //console.log(url)
    if(url!=null && url!=""){
    var params=$.getParam(url);
    var userNumber=params['userNumber']; 
    var realName=params['realName']; 
    var mobile=params['mobile']; 
    var userAddr=params['userAddr']; 
    var userLevel=params['userLevel']; 
    var sex=params['sex'];
    var age=params['age'];
    var customId=params['userId'];
    $("#userNumber").val(userNumber);
    $("#realName").val(realName);
    $("#mobile").val(mobile);
    $("#userAddr").val(userAddr);
    $("#userLevel").val(userLevel);
    $("#sex").val(sex);
    $("#age").val(age);
    $("#customId").val(customId);
  //获取缓存json
	var jsondata=localStorage.getItem("unassigndata");
	$("#form").initForm(jsondata);
    }	
    
	//开始时间结束时间比较
	jQuery.validator.methods.compareDate = function(value, element, param) {
        var startDate ="2019-01-01"+' '+ jQuery(param).val() + ":00";//补全yyyy-MM-dd HH:mm:ss格式
        var value = "2019-01-01"+' '+value + ":00";
  
        var date1 = new Date(Date.parse(startDate.replace("-", "/")));
        var date2 = new Date(Date.parse(value.replace("-", "/")));
        return date1 <= date2;
    };
    //时间check
	jQuery.validator.methods.compareTime = function(value, element, param) {
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
	//jQuery.validator.methods.compareTimeIntervel = function(value, element, param) {
		 jQuery.validator.addMethod("compareTimeIntervel", function (value, element, param) {
        var startDate ="2019-01-01"+' '+ jQuery(param).val() + ":00";//补全yyyy-MM-dd HH:mm:ss格式
        var value = "2019-01-01"+' '+value + ":00";
  
        var date1 = new Date(Date.parse(startDate.replace("-", "/")));
        var date2 = new Date(Date.parse(value.replace("-", "/")));
        date2.setHours(date2.getHours()-1);
        return date1 <= date2;
    }, "结束时间必须大于预约时间1小时");
	//验证方法
	function formValidate(){
		  validateResult= $("#form").validate({
			  onkeyup : function(orderDate,orderBeginTime,orderEndTime,content){
				  $("#orderDate").valid();
				  $("#orderBeginTime").valid();
				  $("#orderEndTime").valid();
				  $("#content").valid();
				 
			  },
		        rules: {
		        	userNumber: {
		                required: true
		            },
		           // sex: {
		           //     required: true
		                //isZipCode:true,
		          //  },
		            orderDate:{
		                required: true,
		                date:true
		            },
		            orderBeginTime: {
		                required:true,
		                compareTime: "#orderDate"
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
		                maxlength:4000
		            },
		            saveCnt:{
		                required:true,
		                digits:true
		            },
		            remark:{
		            	 maxlength:500
		            }
		       
		            
		    },
		     messages: {
		    	 userNumber: {
		                required: "客户编号不能为空",
		            },
		            //sex:{
		            //    required:"性别不能为空",
		            //},
		            orderDate: {
		               required:"预约日期不能为空",
		         
		            },
		            orderBeginTime: {
		               required:"开始时间不能为空",
		               compareTime: "只能预约1小时后时间"
		         
		            },
		            orderEndTime: {
		            required:"结束时间不能为空",
		             //  compareDate: "结束日期必须大于开始日期!"
		              
		           },
		          // serviceCategoryCode:{
		          //  required:"请选择服务类别",
		          // },
		           content:{
		                required :"服务内容不能为空",
		                maxlength:"最大长度不能超过4000"
		            },
		            saveCnt:{
		                required:"保存份数不能为空",
		                digits:"请输入整数"
		            },
		            remark:{
		            	 maxlength:"最大长度不能超过500"
		            }
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
		 var unassigndata = JSON.stringify($("form").serializeObject());
		  console.log(unassigndata);
		  alert(unassigndata);
		//存入缓存(未分配任务)
		 localStorage.setItem("unassigndata",unassigndata); 
		window.location.href="customer-choose.html?selectFlag=1"
	});
	//保存按钮
		$(".save").click(function(){
			// 提交之前进行验证
			      if (formValidate().form()) { //判断校验是否符合规则
			          // 符合规则进入后台 ajax处理
			    	  var formdata=$("form").serializeObject();
			    	  var dataJson = JSON.stringify($("form").serializeObject());
			    	 // alert(dataJson);
			    	 // console.log(dataJson);
			  		$.ajax({
						type:"post",
						url:urlstorage+"task/unassignedTask/insert",
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
								localStorage.removeItem('unassigndata');
								alert("成功");
								window.location.href="undistributed-mission-management.html";
//								$.messager.show({ 
//									title:'提示',msg:'保存成功', 
//									showType:'fade',
//									style:{right:'',bottom:''} ,
//								 timeout: 300000,
//									}); 
								//$(".ok").css('display','block'); 
								//$(".error").css('display','none');
							}else if(result.statusCode == "401"){
								 window.location.href="login.html";
							}
						}
					
				})
			    }else{
			        // 不符合规则返回
			        return false;
			    } 

	})
	
	
})