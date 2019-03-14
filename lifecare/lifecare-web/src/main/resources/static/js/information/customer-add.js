
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
	
    //数值不全为空的验证
    jQuery.validator.addMethod("trim", function(value,element) {
    	     //debugger;
	      	 return $.trim(value)!="";
	});
	function formValidate(){
		validateResult= $("#customerAdd").validate({
			rules: {
				realName : {
					required: true,
					trim : "#realName",
					maxlength:8
				},
				mobile : {
					required:true,
					digits:true,
					rangelength:[11,11]
				},		 		 
				sex : {
					required:true
				},
				streetVillage : {
					required: true,
					trim : "#streetVillage",
					maxlength:32
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
					required:true,
					trim : "#emergencyContactA",
					maxlength:32
				},
				emergencyContactB : {
					maxlength:32
				},
				remarks : {
					maxlength:500
				},
				backInfo : {
					maxlength:500
				}
			},
			messages: {
				realName : {
					required: "姓名不能为空",
					trim : "姓名不能为全空格",
					maxlength:"姓名长度不能大于8字",
				},
				mobile : {
					required:"手机号不能为空",
					digits:"手机号必须为数字",
					rangelength:"手机号长度必须为11位"
				},	 		 
				sex : {
					required:"性别不能为空"
				},
				streetVillage : {
					required:"街道小区不能为空",
					trim:"街道小区不能为全空格",
					rangelength:"街道小区长度不能大于32字"
				},
				buildingNo : {
					required:"楼号单元门牌号不能为空",
					trim:"楼号单元门牌号不能为全空格",
					maxlength:"楼号单元门牌号长度不能大于32字"
				},
				idCard : {
					required:"身份证号不能为空",
					isCard:"请输入正确的身份证号"
				},
				birthday : {
					required:"出生日期不能为空",
					compareDate: "出生日期必须小于等于当日日期"
				},
				emergencyContactA : {
					required:"紧急联系人1不能为空",
					trim:"紧急联系人1不能为全空格",
					maxlength:"紧急联系人1长度不能大于32字"
				},
				emergencyContactB : {
					maxlength:"紧急联系人2长度不能大于32字"
				},	
				remarks : {
					maxlength:"备注长度不能大于500字"
				},	
				backInfo : {
					maxlength:"背景长度不能大于500字"
				}					
			}
		});
		return validateResult;
	};	
			
	//返回按钮
	$(".back").click(function() {
		window.location.href = "customer-management.html"
	})
	
	//保存按钮
	$(".save").click(function() {
		$("#img").val(img);
		if (formValidate().form()){
			$("#provinceId").removeAttr("disabled");
			$("#cityId").removeAttr("disabled");
			var formdata=$("#customerAdd").serializeObject();
			var dataJson = JSON.stringify($("#customerAdd").serializeObject());
			console.log(dataJson);
			//更新服务器 
			$.ajax({
				type : "post",
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
								window.location.href = "customer-management.html";
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

})