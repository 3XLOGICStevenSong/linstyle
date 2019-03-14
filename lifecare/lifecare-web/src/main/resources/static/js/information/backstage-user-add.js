
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
	var storePassword=localStorage.getItem("password");
	$("#loginUserId").val(loginUserId);
	//区ID(235成都市)
	var cityId=235;
	//区初始取得
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
				//666
			}
		}
		});
	//输入身份证号自动获取生日
	getBirthday();
	function getBirthday(){
		$("#idCard").blur(function(){
		   //获取身份证号
		   var idCard=$("#idCard").val();		
		   //出生日期
		   
		   var birthday = $("#birthday").val();
		   if(birthday==""||birthday==null){
			   var birthday=idCard.substring(6, 10) + "-" + idCard.substring(10, 12) + "-" + idCard.substring(12, 14);
			   $("#birthday").val(birthday);
		   }

		})
	};
	//生日选择要不大于今天
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
    //数值不全为空的验证
    jQuery.validator.addMethod("trim", function(value,element) {
    	     //debugger;
	      	 return $.trim(value)!="";
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
	//验证方法
	  function formValidate(){
			 validateResult= $("#userAdd").validate({
				  onkeyup : function(userNumber,realName,mobile,streetVillage,buildingNo,idCard,birthday,emergencyContactA,emergencyContactB,remarks){
				   //工号
				   $("userNumber").valid();	  
				   //姓名
     			   $("#realName").valid();
				   //手机
     			   $("#mobile").valid();
				   //地址（街道小区）
     			   $("#streetVillage").valid();
     			   //地址（楼号单元门牌号）
     			   $("#streetVillage").valid();
				   //身份证号码
     			   $("#idCard").valid();
				   //出生日期
				   $("#birthday").valid();
				   //紧急联系人1
				   $("#emergencyContactA").valid();
				   //紧急联系人2
				   $("#emergencyContactB").valid();
				   //备注
				   $("#remarks").valid();
			  },

		 		rules: {
		 			//员工号
		 			userNumber : {
		 				required: true,
		 				maxlength:16,
		 				beforeThree :"#userNumber"
		 			},
		 			//姓名
					realName : {
						required: true,
						trim : "#realName",
						maxlength:16
					},
					//手机
					mobile : {
						required:true,
						digits:true,
						rangelength:[11,11]
					},
					//地址（街道小区）
					streetVillage : {
						required:true,
						trim : "#streetVillage",
						maxlength:64
					},
					//地址（楼号单元门牌号）
					buildingNo : {
						required:true,
						trim : "#buildingNo",
						maxlength:64
					},
					//身份证号码
					idCard : {
						required:true,
						maxlength:18,
						isCard:true
					},
					//出生日期
					birthday : {
						required:true,
						compareDate: true
					},
					//紧急联系人1
					emergencyContactA : {
						required:true,
						trim : "#emergencyContactA",
						maxlength:64
					},
					//紧急联系人2
					emergencyContactB : {
						maxlength:64
					},
					//备注
					remarks : {
						maxlength:1000
					}
				},
				messages: {
		 			//员工号
		 			userNumber : {
		 				required: "员工号不能为空",
		 				maxlength:"员工号最大长度是16位",
		 				beforeThree :"工号不能以ZQM或ZQF开头"
		 			},
		 			//姓名
					realName : {
						required: "姓名不能为空",
						trim : "姓名不能为全空格",
						maxlength:"姓名长度不能大于8字"
					},
					//手机
					mobile : {
						required:"手机号不能为空",
						digits:"手机号必须为数字",
						rangelength:"手机号长度必须为11位"
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
						maxlength:"身份证号码长度不能大于18位",
						isCard:"身份证号码格式不正"
					},
					//出生日期
					birthday : {
						required:"出生日期不能为空",
						compareDate: "出生日期必须小于等于当日日期"
					},
					//紧急联系人1
					emergencyContactA : {
						required:"紧急联系人1不能为空",
						trim :  "紧急联系人1不能为全空格",
						maxlength:"紧急联系人1长度不能大于32字"
					},
					//紧急联系人2
					emergencyContactB : {
						maxlength: "紧急联系人2长度不能大于32字"
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
	$(".save").click(function(){
		$("#img").val(img);
		//var realName = $("#realName").val();
	    if (formValidate().form()){
	      $("#provinceId").removeAttr("disabled");
	      $("#cityId").removeAttr("disabled");
	      var formdata=$("#userAdd").serializeObject();
	  	    //获取制定照护方案可不可状态
	  	    var draftNursePlan1=$("#draftNursePlan").val();
	  	    formdata.draftNursePlan=draftNursePlan1;	      
		    var dataJson = JSON.stringify(formdata);
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
				success:function(result,status,httpResponse){
					if (result.statusCode=="200") {
						//将token存入缓存	
						var token=httpResponse.getResponseHeader('Authorization');
						if(token!=null && token!="" && token!=undefined){
							localStorage.setItem("token", token); 
						}
						var password=result.data.password;
						alert("成功,密码："+password);
						$("#provinceId").attr("disabled","disabled");
					    $("#cityId").attr("disabled","disabled");
						Swal.fire({
							title: result.msg,
							type: 'success',
							showCancelButton: false,
							confirmButtonColor: '#3085d6',
							confirmButtonText: 'OK'
						}).then((result) => {
							if (result.value) {
								window.location.href="backstage-user-management.html";
							}
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
						window.location.href="login.html";
					}
				}
		})
	}
	    else{
	        // 不符合规则返回
	        return false;
	    } 
		})
})