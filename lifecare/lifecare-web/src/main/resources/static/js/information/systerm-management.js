
$(function(){
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	var loginUserId=localStorage.getItem("userId");
	$("#loginUserId").val(loginUserId);
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
						//两次提示都有
					}else{
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
				}
			}
	});

//提醒开始服务时间不能同时为null
jQuery.validator.methods.compareTime1 = function(value, element, param) {
	var minutes111 = $("#minutes1").val()*1;	
	var minutes222 = $("#minutes2").val()*1;
	if(minutes111==""&&minutes222==""){
		return false;
	}else{
		return true;
	}
};

//输入所有项都不为空时 提醒时间2要大于提醒时间1
jQuery.validator.methods.compareTime2 = function(value, element, param) {
	var minutes111 = $("#minutes1").val()*1;
	var minutes222 = $("#minutes2").val()*1;
	var flag1 = $("input:checkbox[name='sendFlag1']:checked").length > 0;
	var flag2 = $("input:checkbox[name='sendFlag2']:checked").length > 0
	if(minutes111!=0&&minutes222!=0&&flag1==true&&flag2==true){
		return minutes111 < minutes222;
	}else if(minutes111!=0&&minutes222==0){
		return true;
	}else if(minutes111==0&&minutes222!=0){
		return true;
	}
};

//
jQuery.validator.methods.compareTime3 = function(value, element, param) {
	var minutes111 = $("#minutes1").val()*1;	
	var minutes222 = $("#minutes2").val()*1;
	var flag1 = $("input:checkbox[name='sendFlag1']:checked").length ;
	var flag2 = $("input:checkbox[name='sendFlag2']:checked").length ;
	if((minutes111==0&&flag1>0)||(minutes111!=0&&flag1==0)||(minutes222==0&&flag2>0)||(minutes222!=0&&flag2==0)){
		return false;
	}else{
		return true;
	}
};
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
 			 	//checkTimeAndPerson1:true
			},
			minutes2 : {
				range:[1,99],
			 	digits:true,
			 	compareTime1:true,
			 	compareTime3:true,
			 	compareTime2:true
			 	//checkTimeAndPerson2:true
			 	//notNull:true
			}
		},
		messages: {
 			minutes1 : {
 			 	range:"输入数值介于1-9",
 			 	digits:"输入内容只能为数字"
 			 	//checkTimeAndPerson1:"服务开始时间和人员选择不能同时为空"
 			 	
			},
			minutes2 : {
				range:"输入数值介于1-99",
				digits:"输入内容只能为数字",
				//checkTimeAndPerson2:"同一组服务开始时间和人员选择必须同时输入",
				compareTime1:"请输入服务提醒时间",
				compareTime3:"请设置短信提醒时间及发送对象",
				compareTime2:"时间设定不正：提醒时间2必须大于提醒时间1"
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
    alert("sendFlag1:"+sendFlag1);
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
    alert("sendFlag2:"+sendFlag2);
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
    	}else{
    		var data ={
    			  	"minutes1":minutes1,
    			  	"minutes2":minutes2,
    			  	"sendFlag1":sendFlag1,
    			  	"sendFlag2":sendFlag2,
    			  	"loginUserId":loginUserId
    			}
    	}

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
					alert("成功");
				    //window.location.href="backstage-user-management.html"
					/*$.messager.show({ 
						title:'提示',msg:'保存成功', 
						showType:'fade',
						style:{right:'',bottom:''} ,
					 timeout: 300000,
						});*/ 
					//$(".ok").css('display','block'); 
					//$(".error").css('display','none');
				}
			}
		})
	}else{
    // 不符合规则返回
	  return false;
	  } 
    });
})