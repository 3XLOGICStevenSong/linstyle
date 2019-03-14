/**
 * 未发布服务方案添加
 * @returns
 */
$(function(){
    jeDate("#nursePlanBeginDate",{
    	format: "YYYY-MM-DD",
        isinitVal: true,//获取当前日期
       // valiDate:["0[4-7]$,1[1-5]$,2[58]$",true],
    });
    jeDate("#draftDate",{
	    format: "YYYY-MM-DD",
        isinitVal: true,
       // valiDate:["0[4-7]$,1[1-5]$,2[58]$",true],
    });
    formValidate();
	//获取token AND url
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	if(urlstorage==null){
		 window.location.href="login.html";	
	}
	/**
	 * 获取当前登录用户Id、工号、姓名
	 */
	var userId=localStorage.getItem("userId");
	var userNumber=localStorage.getItem("userNumber");
	
	var unassigndata="";
	//获取缓存json
	var jsondata="";
	/**
	 * 获取地址栏传递的数据
	 * selectFlag : 3(客户选择)
	 * selectFlag : 5(后台用户选择)
	 * 从地址栏截取数据，判断数据来源后获取信息
	 */
	//获取地址栏传递来的值
    var url = location.search; 
    //解码（解决IE地址栏传递中文乱码情况）
//	var url = decodeURI(url1);
    if(url != null && url != ""){
    	var params=$.getParam(url);
    	var selectFlag=params['selectFlag'];
    	//客户信息
    	if(selectFlag=="3"){
    		//获取缓存json
        	jsondata=localStorage.getItem("unassigndata");
        	//显示之前输入信息
        	$("#missionForm").initFormPlus(jsondata);
    		var customId=params['userId'];
    		var customNumber=params['userNumber'];
    		var customRealName=params['realName'];
    		var customSex=params['sex'];
    		var customAge=params['age'];
    		var userLevel=params['userLevel'];
    	    $("#customId").val(customId);
    	    $("#customNumber").val(customNumber);
    	    $("#customRealName").val(customRealName);
    	    if(customSex==0){
    	    	$("#customSex").val("男");
    	    }
    	    if(customSex==1){
    	    	$("#customSex").val("女");
    	    }
    	    $("#customAge").val(customAge);
    	    $("#userLevel").val(userLevel);
    	}
    	//制定人员信息
    	if(selectFlag=="5"){
    		//获取缓存json
        	jsondata=localStorage.getItem("unassigndata");
        	//显示之前输入信息
        	$("#missionForm").initFormPlus(jsondata);
    		var backatageUserId=params['userId'];
    		var backatageUserNumber=params['userNumber'];
    		var backatageUserName=params['realName'];
    	    $("#backatageUserId").val(backatageUserId);
    	    $("#backatageUserNumber").val(backatageUserNumber);
    	    $("#backatageUserName").val(backatageUserName);
    	    window.scrollTo($("#userChoose").offset().left,$("#userChoose").offset().top);
    	    
    	}
    }
	//方案开始日期与当前日期的比较
	jQuery.validator.methods.compareNursePlanBeginDate = function(value, element, param) {
		//获取当前日期
		var today = new Date().Format("yyyy-MM-dd h:m:s")
		var date=today.substring(0, 10);
        return value >= date;
    };
    //制定日期与当前日期的比较
	jQuery.validator.methods.compareDraftDate = function(value, element, param) {
		//获取当前日期
		var today = new Date().Format("yyyy-MM-dd h:m:s")
		var date=today.substring(0, 10);
        return value <= date;
    };
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
    //数值不全为空的验证
    jQuery.validator.addMethod("trim", function(value,element) {
	    return $.trim(value)!="";
	});
    
	//表单校验方法
	function formValidate(){
		validateResult= $("#missionForm").validate({
            onkeyup : function(draftDate,nursePlanBeginDate){
				
				$("#draftDate").valid();
				$("#nursePlanBeginDate").valid();
				
			},
		    rules: {
		        customNumber:{
		        	required: true,//必须输入的字段
		        },
		        nursePlanTitle:{
		            required: true,//必须输入的字段
		            trim: true,
		            maxlength:50
		        },
		        nursePlanBeginDate: {
		            required:true,
		            isDate:"#nursePlanBeginDate",
		            compareNursePlanBeginDate:true
		        },
		        nursePlanContent:{
		            required:true,
		            trim: true,
		            maxlength:4000
		        },
		        backatageUserNumber:{
		            required: true,//必须输入的字段
		        },
		        draftDate: {
		            required:true,
		            isDate:"#draftDate",
		            compareDraftDate:true
		        }
		    },
		    messages: {
		    	customNumber: {
		            required: "客户编号不能为空"
		        },
		        nursePlanTitle:{
			        required: "服务方案标题不能为空",
			        trim: "服务方案标题不能为全空格",
			        maxlength:"服务方案标题长度不能大于50字"
			    },
			    nursePlanBeginDate: {
			    	required:"方案开始日期不能为空",
			    	compareNursePlanBeginDate:"方案开始日期必须大于等于当日日期"
		        },
		        nursePlanContent:{
		            required :"服务方案内容不能为空",
		            trim: "服务方案内容不能全为空格",
		            maxlength:"服务方案内容长度不能大于4000字"
		        },
		        backatageUserNumber:{
		            required: "制定人员工号不能为空"//必须输入的字段
		        },
		        draftDate: {
		            required:"制定日期不能为空",
		            compareDraftDate:"制定日期必须小于等于当日日期"
		        }
		    },
		    errorPlacement: function(error, element) {
		    	error.appendTo(element.parent());
			},
		});
		return validateResult;
	}
	//返回按钮
	$(".back").click(function(){
		window.location.href="unpublished-care-plan-management.html"
	});
	//未发布服务方案添加Flag
//	var selectFlag = 1;
	//客户选择按钮
	$("#customChoose").click(function(){
		unassigndata = JSON.stringify($("#missionForm").serializeObject());
			//存入缓存
		localStorage.setItem("unassigndata",unassigndata); 
		window.location.href="customer-choose.html?selectFlag=3"
	});
	//制定人员选择按钮
	$("#userChoose").click(function(){
		unassigndata = JSON.stringify($("#missionForm").serializeObject());
		//存入缓存
		localStorage.setItem("unassigndata",unassigndata); 
		window.location.href="backstage-user-choose.html?selectFlag=5"
	});

	/**
	 * 获取当前用户（录入员）工号和姓名
	 */
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
				$("#enteringPersonId").val(result.data.userId);
				$("#enteringPersonName").val(result.data.realName);
				$("#enteringPersonNumber").val(userNumber);
			}else if(result.statusCode=="512"){
				//alert(result.msg);
				Swal.fire({
					  type: 'error',
					  title: result.msg
				})
			}else if(result.statusCode=="401"){
				window.location.href="login.html";
			}
		}
	});

	//保存按钮
	$(".save").click(function(){
		// 提交之前进行验证
		if (formValidate().form()) { //判断校验是否符合规则
			//获取要发送的数据值
			//客户ID
			var customId=$("#customId").val();
			//服务方案类型
			var nursePlanType=$("#nursePlanType").val();
			//服务方案标题
			var nursePlanTitle=$("#nursePlanTitle").val();
			//方案开始日期
			var nursePlanBeginDate=$("#nursePlanBeginDate").val();
			//服务方案内容
			var nursePlanContent=$("#nursePlanContent").val();
			//制定人员ID
			var backatageUserId=$("#backatageUserId").val();
			//制定日期
			var draftDate=$("#draftDate").val();
//			//发布
//			var nursePlanStatus =$("#nursePlanStatus").val();
			//状态
			var useType=$('input[name="useType"]:checked').val(); 
			//录入员
			var enteringPersonId=$("#enteringPersonId").val();
			var data ={
			  	"customId":customId,
			  	"nursePlanType":nursePlanType,
			  	"backendPersonId":backatageUserId,
			  	"nursePlanTitle":nursePlanTitle,
			  	"nursePlanBeginDate":nursePlanBeginDate,
			  	"nursePlanContent":nursePlanContent,
			  	"draftDate":draftDate,
//			  	"nursePlanStatus":nursePlanStatus,
			  	"useType":useType,
			  	"enteringPersonId":enteringPersonId
			};
			$.ajax({
				type:"post",
				url:urlstorage+"nursePlan",
				dataType: "json", 
				contentType : "application/json",
				beforeSend: function(request) {
			 		request.setRequestHeader("Authorization", token);
			 	},
			 	data:JSON.stringify(data),
				success:function(result,status,httpResponse){
					if (result.statusCode=="200") {
						//将token存入缓存	
						var token=httpResponse.getResponseHeader('Authorization');
						if(token!=null && token!="" && token!=undefined){
							localStorage.setItem("token", token); 
						}
						Swal.fire({
							title: result.msg,
							type: 'success',
							showCancelButton: false,
							confirmButtonColor: '#3085d6',
							confirmButtonText: 'OK'
						}).then(function(result){
							if (result.value) {
								window.location.href="unpublished-care-plan-management.html";
							}
						})
					}
					if(result.statusCode=="401"){
						window.location.href="login.html";	
					}
					if (result.statusCode=="512") {
//						alert(result.msg);
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
	})
})