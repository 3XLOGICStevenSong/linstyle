$(function(){
	jeDate("#birthday",{
   	 format: "YYYY-MM-DD",
   });
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	//获取loginUserId
	var loginUserId=localStorage.getItem("userId");
	$("#loginUserId").val(loginUserId);
	/**
	 * 获取地址栏传递的数据
	 * serviceFlag : 1(返回服务任务管理页面)
	 * 从地址栏截取数据，判断数据来源后获取信息
	 */
    //获取url
	 var url = location.search; 
	    if(url != null && url != ""){
	    	var params=$.getParam(url);
	    	var serviceFlag=params['serviceFlag'];
	        var userId=params['userId'];
	    };
	//获取当前用户权限
	 var permission=localStorage.getItem("userNumber")+"permission";
	 //控制按钮显示
	 var permissions= localStorage.getItem(permission);
	 //判断当前用户能否对服务人员进行编辑（servicePersion:0(不能)，1（能））
	 var serverPersion=0;
		 if(permissions.length>0){
			var data= permissions.split(",");
	         for(var i=0;i<data.length;i++){
	             if(data[i]=="personal:update"){
	            	 serverPersion=1;
	             }
	         } 
		 };
	//返回按钮点击事件
    		$(".back").click(function(){
    			if(serviceFlag==1){
    			     window.location.href="service-task-management.html"
    			}else{
    		    	 window.location.href="service-person-management.html"
    			}
    		});

	//成都市cityId固定值
	var cityId=235;
	//请求Server
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
	        			alert("上传图片成功");
	        		}else{
	        			alert(result.msg);
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
  //身份证号的验证
    jQuery.validator.addMethod("isCard", function(value,element) {
	      	 var regExp=/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/   
	      	 return this.optional(element) || (regExp.test(value));    
	});

	//验证方法
	function formValidate(){
		  validateResult= $("#servicePersionEditFrom").validate({
		        rules: {
		        	userNumber : {
		        		required: true
		        	},
		        	realName : {
						required: true
					},
					mobile : {
						required: true,
						isPhone : true
					},
		            birthday:{
		            	required:true,
		            	compareDate: true
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
					emergencyContactA : {
						required:true
					}
		    },
		     messages: {
		    	 userNumber : {
		        		required: "工号不能为空"
		        	},
		    	 realName : {
						required: "姓名不能为空"
					},
				 mobile : {
						required: "手机号不能为空",
						isPhone: "请输入正确的手机号"
					},
		         birthday:{
		        	 required:"出生日期不能为空",
		            	compareDate: "出生日期不能大于今日"
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
					emergencyContactA : {
						required:"紧急联系人A不能为空"
					}
		     },
		     errorPlacement: function(error, element) {
		    	 error.appendTo(element.parent());
				},
		    });
		  return validateResult;
		};
	
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
				$("#preview").attr("src",result.data.img);
				//对From表单反序列化
				var formdata=$("#servicePersionEditFrom").initForm(result.data);		
			}
		}
});
	//输入身份证号自动获取生日
	getBirthday();
	function getBirthday(){
		$("#idCard").blur(function(){
		   //获取身份证号
		   var idCard=$("#idCard").val();	
		   var birthday=$("#birthday").val();
		   if (birthday==""||birthday==null){
		   //根据身份证号获取出生日期
		   var birthday=idCard.substring(6, 10) + "-" + idCard.substring(10, 12) + "-" + idCard.substring(12, 14);
		   $("#birthday").val(birthday);
		   }
		})
	};	
	//输入的出生日期与现在日期的比较
	jQuery.validator.methods.compareDate = function(value, element, param) {
		//获取当前日期并转换固定格式
		var today = new Date().Format("yyyy-MM-dd h:m:s")
		var date=today.substring(0, 10);
        var birthday = $("#birthday").val();
        return birthday  <=date;
    };
    //转换日期时间格式方法
    Date.prototype.Format = function (fmt){
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
	//判断有无权限并将保存按钮隐藏
    	if(serverPersion==0){
    		  $(".save").hide();
    	};
    	//保存按钮点击事件
	$(".save").click(function() {
		//将图片路径绑定到页面上
		$("#img").val(img);
		// 提交之前进行验证并判断校验是否符合规则
		if (formValidate().form()){
			//移除省市的disabled属性
	        $("#provinceId").removeAttr("disabled");
	        $("#cityId").removeAttr("disabled");
	        //序列化From表单
	  	    var dataJson = JSON.stringify($("#servicePersionEditFrom").serializeObject());
			//更服务人员信息
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
	    				       token=httpResponse.getResponseHeader('Authorization');
		    				   if(token!=null && token!="" && token!=undefined){
		    						localStorage.setItem("token", token); 
		    				   }Swal.fire({
									title: "服务人员编辑成功",
									type: 'success',
									showCancelButton: false,
									confirmButtonColor: '#3085d6',
									confirmButtonText: 'OK'
								}).then(function(result){
									if (result.value) {
										//添加省市的disabled属性
										$("#provinceId").attr("disabled","disabled");
									    $("#cityId").attr("disabled","disabled");
									    //跳转到服务人员管理页面
									    window.location.href="service-person-management.html";
									}
								})
							
						}else if(result.statusCode=="401"){
	    						window.location.href="login.html";		
	    				}else{
	    					Swal.fire({
								title: result.msg,
								type: 'error',
								showCancelButton: false,
								confirmButtonColor: '#3085d6',
								confirmButtonText: '请重新填写'
							}).then(function(result){
								if (result.value) {
			    					$("#provinceId").attr("disabled","disabled");
			    				    $("#cityId").attr("disabled","disabled");		
								}
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