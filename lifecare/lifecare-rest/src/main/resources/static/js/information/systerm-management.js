
$(function(){
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	if(urlstorage==null){
		 window.location.href="login.html";	
	}
	var loginUserId=localStorage.getItem("userId");
	$("#loginUserId").val(loginUserId);
	//获取当前用户权限
	 var permission=localStorage.getItem("userNumber")+"permission";
	 //控制按钮显示
	var permissions= localStorage.getItem(permission);
	console.info(permissions);
	 if(permissions.length>0){
		var data= permissions.split(",");
     for(var i=0;i<data.length;i++){
         if(data[i]=="system:update"){
         	 $(".save").css("display","inline-block");
            }
         //break;
     } 
	 }
	//初始化画面
	$.ajax({
		type:"get",
		url:urlstorage+"smsSendRule",
		beforeSend: function(request) {
			request.setRequestHeader("Authorization", token);
			},
		async: false,
		dataType: "json",
		contentType : "application/json",
		success:function(result){
			if(result.statusCode=="200"){
				//反序列化
				//var formdata=$("#systermForm").initForm(result.data);
				var minutes11 =result.data.minutes1;
				var minutes22 =result.data.minutes2;
				var sendFlag11=result.data.sendFlag1;
				var sendFlag22=result.data.sendFlag2;
				//第一次提示无 第二次提醒有
				if (minutes11==null&&minutes22!=null){
					$("#minutes2").val((result.data.minutes2)/60);
					//客户回显
					if(sendFlag22==1){
						 $("[value = 3]:checkbox").attr("checked", true);
					}else if(sendFlag22==2){
						//服务人员回显	
						 $("[value = 4]:checkbox").attr("checked", true);
					}else if(sendFlag22==3){
						//客户和服务人员都回显
						$("[value = 3]:checkbox").attr("checked", true);
					    $("[value = 4]:checkbox").attr("checked", true);
						}
			    //第一次提示有 第二次提醒无	
				}else if(minutes11!=null&&minutes22==null){
					$("#minutes1").val((result.data.minutes1)/60);
					
					if(sendFlag11==1){
						//客户回显
						$("[value =1]:checkbox").attr("checked", true);
					}else if(sendFlag11==2){
						//服务人员回显	
						$("[value =2]:checkbox").attr("checked", true);
					}else if(sendFlag11==3){
						//客户和服务人员都回显
						$("[value =1]:checkbox").attr("checked", true);
						$("[value =2]:checkbox").attr("checked", true);
					}
					//两次提示都没有
				}else if(minutes11==null&&minutes22==null){
					
				}else{
					//两次提示都有
	            	$("#minutes1").val((result.data.minutes1)/60);
	            	$("#minutes2").val((result.data.minutes2)/60);
					//第一次提示
	            	if(sendFlag11==1){
						//客户回显
						$("[value =1]:checkbox").attr("checked", true);
					}else if(sendFlag11==2){
						//服务人员回显	
						$("[value =2]:checkbox").attr("checked", true);
					}else if(sendFlag11==3){
						//客户和服务人员都回显
						$("[value =1]:checkbox").attr("checked", true);
						$("[value =2]:checkbox").attr("checked", true);
					}
					//第二次提示
					if(sendFlag22==1){
						//客户回显
						$("[value =3]:checkbox").attr("checked", true);
					}else if(sendFlag22==2){
						//服务人员回显	
						$("[value =4]:checkbox").attr("checked", true);
					}else if(sendFlag22==3){
						//客户和服务人员都回显
						$("[value =3]:checkbox").attr("checked", true);
						$("[value =4]:checkbox").attr("checked", true);
					}
				}	            
			}else if(result.statusCode=="401"){
				window.location.href="login.html";	
			}else if (result.statusCode=="512") {
				Swal.fire({
					  type: 'error',
					  title: result.msg
				})
			}
		}
	});

/*//提醒开始服务时间不能同时为null
jQuery.validator.methods.compareTime1 = function(value, element, param) {
	var minutes111 = $("#minutes1").val()*1;	
	var minutes222 = $("#minutes2").val()*1;
	if(minutes111==""&&minutes222==""){
		return false;
	}else{
		return true;
	}
};*/
	//判断1：客户1或服务人员1设定不正：提醒时间1设定时，客户1或服务人员1至少选择其中一个 
	jQuery.validator.methods.compareTime9 = function(value, element, param) {
		var minutes111 = $("#minutes1").val()*1;
		var flag1 = $("input:checkbox[name='sendFlag1']:checked").length > 0;
		if(minutes111==""&&flag1!=""){
			return false;
		}else{
			return true;
		}
	};		

	//判断2：提醒时间1设定不正：客户1或服务人员1选择时，提醒时间1必须输入有效时间(1-9) 
	jQuery.validator.methods.compareTime8 = function(value, element, param) {
		var minutes111 = $("#minutes1").val()*1;
		var flag1 = $("input:checkbox[name='sendFlag1']:checked").length > 0;
		if(minutes111!=""&&flag1==""){
			return false;
		}else{
			return true;
		}
	};
	
	//判断3：提醒时间2设定不正：客户2或服务人员2选择时，提醒时间2必须输入有效时间(1-99)
	jQuery.validator.methods.compareTime7 = function(value, element, param) {
		var minutes222 = $("#minutes2").val()*1;
		var flag2 = $("input:checkbox[name='sendFlag2']:checked").length > 0;
		if(minutes222==""&&flag2!=""){
			return false;
		}else{
			return true;
		}
	};
	//判断4：客户2或服务人员2设定不正：提醒时间2设定时，客户2或服务人员2至少选择其中一个
	jQuery.validator.methods.compareTime6 = function(value, element, param) {
		var minutes222 = $("#minutes2").val()*1;
		var flag2 = $("input:checkbox[name='sendFlag2']:checked").length > 0;
		if(minutes222!=""&&flag2==""){
			return false;
		}else{
			return true;
		}
	};
	
	//提醒时间设定不正：提醒时间2必须大于提醒时间1
	jQuery.validator.methods.compareTime2 = function(value, element, param) {
		var minutes111 = $("#minutes1").val()*1;
		var minutes222 = $("#minutes2").val()*1;
		var flag1 = $("input:checkbox[name='sendFlag1']:checked").length > 0;
		var flag2 = $("input:checkbox[name='sendFlag2']:checked").length > 0;
		if(minutes111!=0&&minutes222!=0&&flag1==true&&flag2==true){
			return minutes111 < minutes222;
		}else if(minutes111!=0&&minutes222==0){
			return true;
		}else if(minutes111==0&&minutes222!=0){
			return true;
		}else if(minutes111==0&&minutes222==0&&flag1!=true&&flag2!=true){
			return true;
		}
	};

/*jQuery.validator.methods.compareTime3 = function(value, element, param) {
	var minutes111 = $("#minutes1").val()*1;	
	var minutes222 = $("#minutes2").val()*1;
	var flag1 = $("input:checkbox[name='sendFlag1']:checked").length ;
	var flag2 = $("input:checkbox[name='sendFlag2']:checked").length ;
	if((minutes111==0&&flag1>0)||(minutes111!=0&&flag1==0)||(minutes222==0&&flag2>0)||(minutes222!=0&&flag2==0)){
		return false;
	}else{
		return true;
	}
};*/
/*//开始服务时间1,客户/服务人员的入力验证
jQuery.validator.methods.checkTimeAndPerson1 = function(value, element, param) {
    var minutes11 = $("#minutes1").val();
	//开始服务时间2:有   客户/服务人员:checkbox无
	if(minutes11 == null||minutes11==""){
		return ($("input:checkbox[name='sendFlag1']:checked").length == 0);
	}
	//开始服务时间2:无   客户/服务人员:checkbox有
	else{
		return ($("input:checkbox[name='sendFlag1']:checked").length != 0);
	}
};*/

/*//开始服务时间2,客户/服务人员的入力验证
jQuery.validator.methods.checkTimeAndPerson2 = function(value, element, param) {
    var minutes22 = $("#minutes2").val();
	//开始服务时间2:有   客户/服务人员:checkbox无
	if(minutes22 == null||minutes22==""){
		return ($("input:checkbox[name='sendFlag2']:checked").length == 0);
	}
	//开始服务时间2:无   客户/服务人员:checkbox有
	else{
		return ($("input:checkbox[name='sendFlag2']:checked").length != 0);
	}
};*/
	
	//check 
	function formValidate(){
		 validateResult= $("#systermForm").validate({
	 		rules: {
	 			minutes1 : {
	 			 	range:[1,9],
	 			 	digits:true,
	 			 	maxlength : 1,
	 			 	//checkTimeAndPerson1:true
	 			 	compareTime9:true,
	 			 	compareTime8:true
				},
				minutes2 : {
					range:[1,99],
				 	digits:true,
				 	compareTime7:true,
				 	compareTime6:true,
				 	//compareTime1:true,
				 	//compareTime3:true,
				 	compareTime2:true,
				 	//checkTimeAndPerson2:true
				 	//notNull:true
				 	maxlength: 2
	
				}
			},
			messages: {
	 			minutes1 : {
	 			 	range:"请输入正确的提醒时间",
	 			 	compareTime8:"请选择发送对象",
	 			 	compareTime9:"请输入正确的提醒时间"
	 			 		 			 	
				},
				minutes2 : {
					range:"请输入正确的提醒时间",
					compareTime2:"提醒时间必须大于一次提醒时间",
	 			 	compareTime6:"请选择发送对象",
	 			 	compareTime7:"请输入正确的提醒时间"

				}
			}
		});
		return validateResult;
	}
	
	//推送标识1转换
	function getCheckBoxValueOne() {
	    //获取checkBox的元素
	    var ids = $('input[name="sendFlag1"]');
	    var data = [];
	    ids.each(function () {
	        //获取当前元素的勾选状态
	        if ($(this).prop("checked")) {
	             data.push($(this).val());
	        }
	    });
	    //客户和服务人员都选
	    if(data[0]==1&&data[1]==2){
			//复选框都选则赋值3
			sendFlag1=3;
		//只选客户	
		}else if(data[0]==1){
			sendFlag1=1;
		//只选服务人员	
		}else if(data[0]==2){
			sendFlag1=2;
		//都不选	
		}else{
			sendFlag1=0;
		}
	    //alert("sendFlag1:"+sendFlag1);
	};
	//推送标识2转换
	function getCheckBoxValueTwo() {
	    //获取checkBox的元素
	    var ids = $('input[name="sendFlag2"]');
	    var data = [];
	    ids.each(function () {
	        //获取当前元素的勾选状态
	        if ($(this).prop("checked")) {
	             data.push($(this).val());
	        }
	    });
	    //客户和服务人员都选
	    if(data[0]==3&&data[1]==4){
			//复选框都选则赋值3
			sendFlag2=3;
		//只选客户	
		}else if(data[0]==3){
			sendFlag2=1;
		//只选服务人员	
		}else if(data[0]==4){
			sendFlag2=2;
		//都不选	
		}else{
			sendFlag2=0;
		}
	    //alert("sendFlag2:"+sendFlag2);
	};
	//保存按钮
	$(".save").click(function() {
	    if (formValidate().form()){
	    	//推送标识1转换
	    	getCheckBoxValueOne();
	    	//推送标识2转换
	    	getCheckBoxValueTwo();
	    	//开始服务前?小时提醒
	    	var minutes1=$("#minutes1").val()*60;
	    	//开始服务前?小时提醒
	    	var minutes2=$("#minutes2").val()*60;
	    	if(sendFlag1==0&&sendFlag2!=0){
	    		var data ={
	    			  	"minutes2":minutes2,
	    			  	"sendFlag2":sendFlag2,
	    			  	"loginUserId":loginUserId
	    			}
	    	}else if(sendFlag1!=0&&sendFlag2==0){
	    		var data ={
	    			  	"minutes1":minutes1,
	    			  	"sendFlag1":sendFlag1,
	    			  	"loginUserId":loginUserId
	    			}
	    	}else if(sendFlag1!=0&&sendFlag2!=0){
	    		var data ={
	    			  	"minutes1":minutes1,
	    			  	"minutes2":minutes2,
	    			  	"sendFlag1":sendFlag1,
	    			  	"sendFlag2":sendFlag2,
	    			  	"loginUserId":loginUserId
	    			}
	    	}else{
	    		var data ={
	    			  	"loginUserId":loginUserId
	    			}
	    	};
	
			//更新服务器 
			$.ajax({
				type : "post",
				url : urlstorage+"smsSendRule",
				async: false,
				dataType: "json", 
				contentType : "application/json",
				beforeSend: function(request) {
		 			request.setRequestHeader("Authorization", token);
		 			},
		 		data:JSON.stringify(data),
				success : function(result) {
					if (result.statusCode=="200") {
						//alert("成功");
					    Swal.fire({
							title: result.msg,
							type: 'success',
							showCancelButton: false,
							confirmButtonColor: '#3085d6',
							confirmButtonText: 'OK'
						})
					}else if(result.statusCode=="401"){
						window.location.href="login.html";	
					}else if (result.statusCode=="512") {
						Swal.fire({
							  type: 'error',
							  title: result.msg
						})
					}else{
	 					return false;
	 				}
				}
			})
		}else{
		  // 不符合规则返回
		  return false;
		} 
    });
})