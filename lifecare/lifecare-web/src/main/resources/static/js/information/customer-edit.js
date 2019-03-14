
$(function(){
    jeDate("#birthday",{
      	 format: "YYYY-MM-DD"
           //isinitVal: true,
          // valiDate:["0[4-7]$,1[1-5]$,2[58]$",true],
      });
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	var loginUserId=localStorage.getItem("userId");
	$("#loginUserId").val(loginUserId);
	//获取当前用户权限
	var permission=localStorage.getItem("userNumber")+"permission";
	 //控制按钮显示
	var permissions= localStorage.getItem(permission);
//	//获取URL中参数
//	var userId=window.location.href.substring(window.location.href.lastIndexOf("=")+1,window.location.href.length);
//		
	/**
	 * 获取地址栏传递的数据
	 * selectFlag : 1(服务任务池选择)
	 * selectFlag : 2(服务任务编辑选择)
	 * 从地址栏截取数据，判断数据来源后获取信息
	 */
    var url = location.search; 
    if(url != null && url != ""){
    	var params=$.getParam(url);
    	var customFlag=params['customFlag'];
    	var userId=params['userId'];
    }	
	//客户编辑权限
	var custom=0;
	 if(permissions.length>0){
		var data= permissions.split(",");
         for(var i=0;i<data.length;i++){
             if(data[i]=="customer:update"){
            	custom=1;
             }
         } 
	 }    	

	//区一览取得(区ID=235:成都市)
	var cityId=235;
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
		success:function(result){
			if(result.statusCode=="200"){
				var formdata=$("#customerEdit").initForm(result.data);
				$("#preview").attr("src",result.data.img);
				console.log(formdata);
				/*$("#userLevel option[value='"+result.data.userLevel+"']").prop("selected",true);
				//用户编号
				$("#userId").val(result.data.userId);
				//用户编号
				$("#userNumber").val(result.data.userNumber);
				//相片地址
				$("#img").val(result.data.img);
				//姓名
				$("#realName").val(result.data.realName);
				//手机
				$("#mobile").val(result.data.mobile);
				//性别
				$("#sex").find("input:radio:checked").val();
				//省编号
				$("#provinceId option[value='"+result.data.provinceId+"']").attr("selected",true);
				//市编号
				$("#cityId option[value='"+result.data.cityId+"']").attr("selected",true);				
				//区编号
				$("#districtId option[value='"+result.data.districtId+"']").attr("selected",true);
				//街道小区
				$("#streetVillage").val(result.data.streetVillage);
				//楼号单元号
				$("#buildingNo").val(result.data.buildingNo);
				//身份证号码
				$("#idCard").val(result.data.idCard);
				//出生日期
				$("#birthday").val(result.data.birthday);
				//紧急联系人A
				$("#emergencyContactA").val(result.data.emergencyContactA);
				//紧急联系人B
				$("#emergencyContactB").val(result.data.emergencyContactB);
				//备注
				$("#remarks").val(result.data.remarks);
				//背景
				$("#backInfo").val(result.data.backInfo);
//				//服务类别
//				$("#serviceCategoryLevel").val(result.data.serviceCategoryLevel);
//				//制定照护方案(0:不可 1:可)
//				$("#draftNursePlan").val(result.data.draftNursePlan);
				//星级或者等级
				$("#userLevel option[value='"+result.data.userLevel+"']").attr("selected",true);
				//状态
				$("#userStatus").find("input:radio:checked").val();*/
			}
		}
	});
	
	//图片上传
	var img = "";
	$(".myFileUpload").on("change",function(){
	    var file = $(this)[0].files[0];//获取指定ID的文件信息
	    var formdat=file.name.substring(file.name.lastIndexOf("."));
	    if((formdat == ".png")||(formdat == ".jpg")||(formdat == ".jpeg")){
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
	        		}
	        	}
	        }); 
	    }else{
	    	alert("请上传正确的图片格式");
	    }
	});
	
	//手机号验证
	jQuery.validator.addMethod("isPhone", function(value,element) {
		var regExp=/^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\d{8}$/  
		return this.optional(element) || (regExp.test(value));    
 	});
 
	//身份证验证
	jQuery.validator.addMethod("isCard", function(value,element) {
		var regExp=/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/   
		return this.optional(element) || (regExp.test(value));    
	});
	
	//输入身份证号自动获取生日
	getBirthday();
	function getBirthday(){
		$("#idCard").blur(function(){
			//获取身份证号
			var idCard=$("#idCard").val();	
			var birthday=$("#birthday").val();	
			if (birthday==""||birthday==null){
			//出生日期
			var birthday=idCard.substring(6, 10) + "-" + idCard.substring(10, 12) + "-" + idCard.substring(12, 14);
			$("#birthday").val(birthday);
			}
		})
	};	
	
	//出生日期验证
	jQuery.validator.methods.compareDate = function(value, element, param) {
		//获取当前日期
		var today = new Date().Format("yyyy-MM-dd h:m:s")
		var date=today.substring(0, 10);
		var birthday = $("#birthday").val();
		return birthday  <= date;
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
		validateResult= $("#customerEdit").validate({
			rules: {
				realName : {
				required: true,
				maxlength:8
				},
			mobile : {
				required:true,
				isPhone:true
				},		 		 
			sex : {
				required:true
				},
			streetVillage : {
				required:true
				},
			buildingNo : {
				required:true
				},
			idCard : {
				required:true,
				isCard:true
				},
			birthday : {
				required:true,
				compareDate: true
				},
			emergencyContactA : {
				required:true
				}
			},
		messages: {
			realName : {
				required: "姓名不能为空"
				},
			mobile : {
				required:"手机号不能为空",
				isPhone:"请输入正确的手机号"
				},	 		 
			sex : {
				required:"性别不能为空"
				},
			streetVillage : {
				required:"地址（街道小区）不能为空"
				},
			buildingNo : {
				required:"地址（楼号单元门牌号）不能为空"
				},
			idCard : {
				required:"身份证号不能为空",
				isCard:"请输入正确的身份证号"
				},
			birthday : {
				required:"出生日期不能为空",
				compareDate: "出生日期必须小于今日"
				},
			emergencyContactA : {
				required:"紧急联系人A不能为空"
				}
			}
		});
		return validateResult;
	};	

//返回按钮
$(".back").click(function() {
	if(customFlag == 1){
		window.location.href = "service-task-management.html"			
	}else{
		window.location.href = "customer-management.html"
	}

})

if(custom == 0){
	$(".save").hide();
	}
else{
	//保存按钮
	$(".save").click(function() {
		$("#img").val(img);
		if (formValidate().form()){
			$("#provinceId").removeAttr("disabled");
			$("#cityId").removeAttr("disabled");
			var formdata=$("#customerEdit").serializeObject();
			console.log(formdata)
			var dataJson = JSON.stringify($("#customerEdit").serializeObject());
			console.log(dataJson);
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
				success : function(result,status,httpResponse){
				if (result.statusCode=="200") {
					$("#provinceId").attr("disabled","disabled");
				    $("#cityId").attr("disabled","disabled");
					//将token存入缓存	
					var token=httpResponse.getResponseHeader('Authorization');
					
					if(token!=null && token!="" && token!=undefined){
						localStorage.setItem("token", token); 
					}
					Swal.fire({
						title: '保存成功',
						type: 'success',
						showCancelButton: false,
						confirmButtonColor: '#3085d6',
						confirmButtonText: 'OK'
					}).then(function(result){
						if (result.value) {
							if(customFlag == 1){
								window.location.href = "service-task-management.html"			
							}else{
								window.location.href = "customer-management.html"
							}
						}
					})
					}else if(result.statusCode=="401"){
						//异常发生时返回登录画面
						window.location.href = "login.html"
					}
	 			}
			})
		}else{
			// 不符合规则返回
			return false;
		} 
	})
	}
})