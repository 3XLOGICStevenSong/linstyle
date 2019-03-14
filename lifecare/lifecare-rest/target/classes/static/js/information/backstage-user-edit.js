
$(function(){
//    jeDate("#birthday",{
//   	 format: "YYYY-MM-DD"
//        //isinitVal: true,
//       // valiDate:["0[4-7]$,1[1-5]$,2[58]$",true],
//   });
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	if(urlstorage==null){
		 window.location.href="login.html";	
	}
	var loginUserId=localStorage.getItem("userId");
	
	$("#loginUserId").val(loginUserId);
	//获取URL中参数
	var userId="";
	 var url = location.search; 
	 
    if(url!=null && url!=""){
    var params=jQuery.getParam(url);
    userId=params['userId']; 
   
	//获取URL中参数
    }
	//var userId=window.location.href.substring(window.location.href.lastIndexOf("=")+1,window.location.href.length);
	$("#userId").val(userId);
	
	formValidate();
	//区ID(235成都市)
	var cityId=235;
	//区一览取得
	$.ajax({
		type:"get",
		url:urlstorage+"district/"+cityId,
		beforeSend: function(request) {
			request.setRequestHeader("Authorization", token);
			},
		async: false,
		dataType: "json", 
		contentType : "application/json",
		success:function(result){
			if(result.statusCode=="200"){
				var list = "";
				var districtList = result.data;
				
				if(districtList == null) {
					$(".error").text("异常：区一览取得失败");
					$(".error").css('display','block');
					return false;
				} else {
					for(i = 0; i < districtList.length; i++){
						list += "<option  value='"+districtList[i].districtId+"'>"+districtList[i].districtName+"</option>";
					}
					$("#districtId").append(list);
				}				
			}else if(result.statusCode=="401"){
				window.location.href = "login.html"
			}else if (result.statusCode=="512") {
				Swal.fire({
					  type: 'error',
					  title: result.msg
				})
			}
		}
	});
	
	//初始化画面
	$.ajax({
		type:"get",
		url:urlstorage+"user/"+userId,
		beforeSend: function(request) {
			request.setRequestHeader("Authorization", token);
			},
		async: false,
		dataType: "json", 
		contentType : "application/json",
		success:function(result,status,httpResponse){
			if(result.statusCode=="200"){
				//alert(result.data.userLevel);
				var formdata=$("#userEdit").initFormPlus(result.data);
				$("#preview").attr("src",result.data.img);
				if (result.data.draftNursePlan == "1"){
				    $("[name = draftNursePlan]:checkbox").attr("checked", true);
				}
			}else if(result.statusCode=="401"){
				window.location.href = "login.html"
			}else if(result.statusCode=="512"){
				Swal.fire({
					  type: 'error',
					  title: result.msg
				})
			}
		}
});
	//图片上传
	var img = "";
	var imgName = "";
	$(".myFileUpload").on("change",function(){
	    var file = $(this)[0].files[0];//获取指定ID的文件信息
	    //在此处我们限制图片大小为400K
	    if(file.size<=400*1024){
	    var formdat=file.name.substring(file.name.lastIndexOf("."));
	    if((formdat.toLowerCase() == ".png")||(formdat.toLowerCase() == ".jpg")||(formdat.toLowerCase() == ".jpeg")){
	        var formData = new FormData();//创建FormData对象，将所需的信息封装到内部，以键值对的方式
	        formData.append("file",file);//参数封装格式,可以是文件，亦可以是普通的字符串 
	        $.ajax({
	        	type:"post",
	        	url:urlstorage + "file/upload",
	        	dataType:"json",
	        	async: false,
	        	cache:false,//不需要缓存操作
	            data:formData,//传递的参数就是FormData
	            contentType:false,//由于提交的对象是FormData,所以要在这里更改上传参数的类型
	            processData:false,//提交对象是FormData,不需要对数据做任何处理
	        	success:function(result){
	        		if(result.statusCode=="200"){
	        			img=result.data.url;
	        			imgName=result.data.imgName;
	        			Swal.fire({
	  						  type: 'success',
	  						  title: result.msg
		        		})
	        		}else if(result.statusCode=="401"){
	    				window.location.href = "login.html"
	    			}else if (result.statusCode=="512") {
	    				Swal.fire({
	    					  type: 'error',
	    					  title: result.msg
	    				})
	    			}
	        	}
	        }); 
	    }else{
	    	//alert("请上传正确的图片格式");
	    	Swal.fire({
				  type: 'error',
				  title: "请上传正确的图片格式"
	    	})
	    }}else{
	    	Swal.fire({
				  type: 'error',
				  title: "上传文件过大,请上传小于400KB的文件!"
	    	})	
	    }
	});
	//手机号验证
	jQuery.validator.addMethod("isPhone", function(value,element) {
		var regExp=/^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\d{8}$/  
		return this.optional(element) || (regExp.test(value));    
 	});
	
	//身份证中的日期不大于今天
	jQuery.validator.methods.compareIdDate = function(value, element, param) {
		//获取当前日期
		var today = new Date().Format("yyyy-MM-dd h:m:s")
		var date=today.substring(0, 10);
	    var birthday1 = $("#idCard").val();
	    var birthday = birthday1.substring(6,14);
	    return birthday  <= date;
	};
	//身份证验证
	jQuery.validator.addMethod("isCard", function(value,element) {
		var regExp=/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/   
		return this.optional(element) || (regExp.test(value));    
	});
    //工号的验证
    jQuery.validator.addMethod("beforeThree", function(value,element) {
	      	 var regExp=/^ZQ[M,F]+/
	      	 return this.optional(element) || (!regExp.test(value));    
	});
    //工号只能是大小字母或数字
    jQuery.validator.addMethod("userNumberCheck", function(value,element) {
    	var regExp= /^[a-zA-Z\d]+$/
     	 return this.optional(element) || (regExp.test(value));    
});
    //数值不全为空的验证
    jQuery.validator.addMethod("trim", function(value,element) {
    	     //debugger;
	      	 return $.trim(value)!="";
	});
    
    //身份证号日期验证
//    jQuery.validator.addMethod("idDate", function(value,element) {
//        var year, month, day;
//        year = value.substring(6, 10);
//        month = value.substring(10, 12);
//        day = value.substring(12, 14);
//        var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
//        if (year < 1919 || year > 2500)
//            return false
//        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
//            iaMonthDays[1] = 29;
//        if (month < 1 || month > 12)
//            return false
//        if (day < 1 || day > iaMonthDays[month - 1])
//            return false
//        return true
//    });
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
	
		$("#idCard").blur(function(){
			//获取身份证号
			var idCard=$("#idCard").val();
			var birthday=idCard.substring(6, 10) + "-" + idCard.substring(10, 12) + "-" + idCard.substring(12, 14);
			if(isDateFormat(birthday)){		
					$("#birthday").val(birthday);
				}else{
					$("#birthday").val("");
				} 
			});
jQuery.validator.methods.compareDate = function(value, element, param) {
	//获取当前日期
	var today = new Date().Format("yyyy-MM-dd h:m:s")
	var date=today.substring(0, 10);
    var birthday = $("#birthday").val();
    return birthday  <= date;
};
//出生日期规则的验证 by liuli 2019/2/19
jQuery.validator.methods.brDate = function(value, element, param) {
	//获取生日
    var birthday = $("#birthday").val();
    //截取年月日进行check
    var year, month, day;
    year = value.substring(0, 4);
    month = value.substring(5, 7);
    day = value.substring(8, 10);
    var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if (year < 1919 || year > 2500)
        return false
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
        iaMonthDays[1] = 29;
    if (month < 1 || month > 12)
        return false
    if (day < 1 || day > iaMonthDays[month - 1])
        return false
    return true
};
Date.prototype.Format = function (fmt) {
	var o = {
		    "M+": this.getMonth() + 1, //月份 
		    "d+": this.getDate(), //日 
		    "h+": this.getHours(), //小时 
		    "m+": this.getMinutes(), //分 
		    "s+": this.getSeconds(), //秒 
		  };
		  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		  for (var k in o)
		    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		  return fmt;
};

function formValidate(){
	 validateResult= $("#userEdit").validate({
//		 onkeyup : function(){
//			  $("#birthday").valid();
//		  },
 		rules: {
 			//员工号
 			userNumber : {
 				required: true,
 				maxlength:16,
 				beforeThree :"#userNumber",
 				userNumberCheck:"#userNumber"
 			},
 			//姓名
			realName : {
				required: true,
				trim : "#realName",
				maxlength:20
			},
			//手机
			mobile : {
				required:true,
				digits:true,
				rangelength:[11,11]
			},
			//性别
			sex : {
				required:true
			},
			//地址（街道小区）
			streetVillage : {
				required:true,
				trim : "#streetVillage",
				maxlength:32
			},
			//地址（楼号单元门牌号）
			buildingNo : {
				required:true,
				trim : "#buildingNo",
				maxlength:32
			},
			//身份证号码
			idCard : {
				required:true,
				rangelength:[18,18],
				compareIdDate:true,
				isCard:"#idCard"
			//	idDate:"#idCard"
			},
			//出生日期
			birthday : {
				required:true,
				isDate: "#birthday",//liuli 2019/2/19
				compareDate: true
			},
			//紧急联系人1
			emergencyContactA : {
				required:true,
				trim : "#emergencyContactA",
				maxlength:32
			},
			relationWithA:{
				required:true,
				trim : "#relationWithA",
				maxlength:32
			},
			//紧急联系人2
			emergencyContactB : {
				maxlength:32
			},			
			relationWithB : {
				maxlength:32
			},
			//备注
			remarks : {
				maxlength:500
			}
		},
		messages: {
 			//员工号
 			userNumber : {
 				required: "工号不能为空",
 				maxlength:"工号长度不能大于16位",
 				beforeThree :"工号不能以ZQM或ZQF开头",
 				userNumberCheck : "工号必须为大小字母或数字"
 			},
 			//姓名
			realName : {
				required: "姓名不能为空",
				trim : "姓名不能为全空格",
				maxlength:"姓名长度不能大于20字"
			},
			//手机
			mobile : {
				required:"手机号不能为空",
				digits:"手机号必须为数字",
				rangelength:"手机号长度必须为11位"
			},
			//性别
			sex : {
				required:"性别不能为空"
			},
			//地址（街道小区）
			streetVillage : {
				required:"街道小区不能为空",
				trim : "街道小区不能为全空格",
				maxlength:"街道小区长度不能大于32字"
			},
			//地址（楼号单元门牌号）
			buildingNo : {
				required:"楼号单元门牌号不能为空",
				trim : "楼号单元门牌号不能为全空格",
				maxlength:"楼号单元门牌号长度不能大于32字"
			},
			//身份证号码
			idCard : {
				required:"身份证号不能为空",
				rangelength:"身份证号码长度必须为18位",
				compareIdDate:"出生日期必须小于等于当日日期",
				//idDate:"身份证号码日期不正",	
				isCard:"身份证号码格式不正"
			},
			//出生日期
			birthday : {
				required:"出生日期不能为空",
				//brDate:"出生日期无效",//liuli 2019/2/19
				compareDate: "出生日期必须小于等于当日日期"
			},
			//紧急联系人1
			emergencyContactA : {
				required: "紧急联系人1姓名和电话不能为空",
				trim :    "紧急联系人1姓名和电话不能为全空格",
				maxlength:"紧急联系人1姓名和电话长度不能大于32字"
			},
			relationWithA:{
				required: "紧急联系人1与后台用户的关系不能为空",
				trim :    "紧急联系人1与后台用户的关系不能为全空格",
				maxlength:"紧急联系人1与后台用户的关系长度不能大于32字"
			},
			//紧急联系人2
			emergencyContactB : {
				maxlength:"紧急联系人2姓名和电话长度不能大于32字"
			},			
			relationWithB : {
				maxlength: "紧急联系人2与后台用户的关系长度不能大于32字"
			},	
			//备注
			remarks : {
				maxlength: "备注长度不能大于500字"
			}
		}
	});
		  return validateResult;
}	

//返回按钮点击事件
$(".back").click(function(){
	window.location.href="backstage-user-management.html"
});	
//保存按钮
$(".save").click(function() {
	$("#img").val(img);
	$("#imgName").val(imgName);
    if (formValidate().form()){
        $("#provinceId").removeAttr("disabled");
        $("#cityId").removeAttr("disabled");
        var formdata=$("#userEdit").serializeObject();
  	    //获取制定照护方案可不可状态
  	    var draftNursePlan1=$("#draftNursePlan").val();
  	    formdata.draftNursePlan=draftNursePlan1;
  	    var dataJson = JSON.stringify(formdata);
  	    //console.log(dataJson);
		//更新服务器 
		$.ajax({
			type : "put",
			url : urlstorage+"user",
			async: false,
			dataType: "json", 
			contentType : "application/json",
			 beforeSend: function(request) {
	 			request.setRequestHeader("Authorization", token);
	 			},
			data : dataJson,
			success:function(result,status,httpResponse){
				if (result.statusCode=="200") {
					//将token存入缓存	
					var token=httpResponse.getResponseHeader('Authorization');
					if(token!=null && token!="" && token!=undefined){
						localStorage.setItem("token", token); 
				}
					$("#provinceId").attr("disabled","disabled");
				    $("#cityId").attr("disabled","disabled");
				    
				    Swal.fire({
						title: result.msg,
						type: 'success',
						showCancelButton: false,
						confirmButtonColor: '#3085d6',
						confirmButtonText: 'OK'
				    }).then(function(result){
							window.location.href="backstage-user-management.html";
					})
					/*$.messager.show({ 
						title:'提示',msg:'保存成功', 
						showType:'fade',
						style:{right:'',bottom:''} ,
					 timeout: 300000,
						});*/
					//$(".ok").css('display','block'); 
					//$(".error").css('display','none');
				}else if(result.statusCode=="401"){
					window.location.href="login.html"
				}else if (result.statusCode=="512") {
					Swal.fire({
						  type: 'error',
						  title: result.msg
					})
				}else{
					Swal.fire({
						title: result.msg,
						showCancelButton: false,
						confirmButtonColor: '#3085d6',
						confirmButtonText: 'OK'
					})
				
				}
			}
		})
	}else{
    // 不符合规则返回
	  return false;
	  } 
    })
    
})