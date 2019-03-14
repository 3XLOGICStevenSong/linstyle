
$(function(){
    jeDate("#nursePlanBeginDate",{
    	format: "YYYY-MM-DD",
//        isinitVal: true,
       // valiDate:["0[4-7]$,1[1-5]$,2[58]$",true],
    });
    jeDate("#draftDate",{
	    format: "YYYY-MM-DD",
//        isinitVal: true,
       // valiDate:["0[4-7]$,1[1-5]$,2[58]$",true],
    });

	//获取token AND url
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	/**
	 * 获取当前登录用户Id
	 */
	var loginUserId=localStorage.getItem("userId");
	$("#enteringPersonId").val(loginUserId);
	//初始化服务方案id
	var nursePlanId=0;
	var unassigndata="";
	//获取缓存json
	var jsondata="";
	/**
	 * 获取地址栏传递的数据
	 * 1.select没有值则为管理页面点击编辑按钮迁移到此画面
	 * 2.selectFlag : 6(后台用户选择)
	 * 从地址栏截取数据，判断数据来源后获取信息
	 */
	//获取地址栏传递来的值
    var url = location.search; 
    //解码（解决IE地址栏传递中文乱码情况）
//	var url = decodeURI(url1);
    if(url!=null && url!=""){
    	var params=$.getParam(url);
    	//获取nursePlanId回显
    	nursePlanId=params['nursePlanId'];
    	var selectFlag=params['selectFlag'];
    	//制定人员信息
    	if(selectFlag=="6"){
    		//获取缓存json
    		jsondata=localStorage.getItem("unassigndata");
    		//显示之前输入信息
        	$("#missionForm").initFormPlus(jsondata);
    		var backatageUserId=params['userId'];
    		var backatageUserNumber=params['userNumber'];
    		var backatageUserName=params['realName'];
    	    $("#backatageUserId").val(backatageUserId);
    	    $("#backendPersonUserNumber").val(backatageUserNumber);
    	    $("#backendPersonRealName").val(backatageUserName);
    	    window.scrollTo($("#userChoose").offset().left,$("#userChoose").offset().top);
    	}
    }	
    formValidate();
    $.ajax({
        url: urlstorage + "nursePlan/assigned/"+nursePlanId,
        type: "GET",
//        async: false,
        dataType: 'json',
    	beforeSend: function(request) {
			request.setRequestHeader("Authorization", token);
			},
        success: function (result) {
            if ("200" == result.statusCode) {
            	$("#nursePlanId").val(result.data.nursePlanId);
            	$("#customUserNumber").val(result.data.customUserNumber);
				$("#customRealName").val(result.data.customRealName);
				if(result.data.customSex==0){
	    	    	$("#customSex").val("男");
	    	    }
	    	    if(result.data.customSex==1){
	    	    	$("#customSex").val("女");
	    	    }
				$("#customAge").val(result.data.customAge);
				$("#customUserLevel").val(result.data.customUserLevel);
				//服务方案类别
				$("#nursePlanType").val(result.data.nursePlanType);
				$("#nursePlanTitle").val(result.data.nursePlanTitle);
				$("#nursePlanBeginDate").val(result.data.nursePlanBeginDate);
				$("#nursePlanContent").val(result.data.nursePlanContent);
				$("#backendPersonUserNumber").val(result.data.backendPersonUserNumber);
				$("#backendPersonRealName").val(result.data.backendPersonRealName);
//				$("#nursePlanStatus").val(result.data.nursePlanStatus);
				$("#draftDate").val(result.data.draftDate);
				//状态
				$(":radio[name='useType'][value='" + result.data.useType + "']").prop("checked", "checked");
				$("#enteringPersonUserNumber").val(result.data.enteringPersonUserNumber);
				$("#enteringPersonRealName").val(result.data.enteringPersonRealName);
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

//	//方案开始日期与当前日期的比较
//	jQuery.validator.methods.compareNursePlanBeginDate = function(value, element, param) {
//		//获取当前日期
//		var today = new Date().Format("yyyy-MM-dd h:m:s")
//		var date=today.substring(0, 10);
//        return value >= date;
//    };
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
    //数值不全为空的验证
    jQuery.validator.addMethod("trim", function(value,element) {
	    return $.trim(value)!="";
	});
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
	//验证方法
	function formValidate(){
		validateResult= $("#missionForm").validate({
			onkeyup : function(nursePlanTitle,nursePlanContent){
				$("#nursePlanTitle").valid();
				$("#nursePlanContent").valid();
				$("#nursePlanBeginDate").valid();
				$("#draftDate").valid();
			},
		    rules: {
				nursePlanTitle:{
				    required: true,//必须输入的字段
				    trim: true,
				    maxlength:50
				},
				nursePlanBeginDate: {
				    required:true,
				    isDate:"#nursePlanBeginDate"
//				    compareNursePlanBeginDate:true
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
			    nursePlanTitle:{
				    required: "服务方案标题不能为空",
				    trim: "服务方案标题不能为全空格",
				    maxlength:"服务方案标题长度不能大于50字"
				},
				nursePlanBeginDate: {
				    required:"方案开始日期不能为空"
//				    compareNursePlanBeginDate:"方案开始日期必须大于等于当日日期"
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
	//制定人员选择按钮
	$("#userChoose").click(function(){
		unassigndata = JSON.stringify($("#missionForm").serializeObject());
		//存入缓存(未分配任务)
		localStorage.setItem("unassigndata",unassigndata); 
		window.location.href="backstage-user-choose.html?selectFlag=6";
	});
	//返回按钮
	$(".back").click(function(){
		window.location.href="published-care-plan-management.html";
	});
	
	//保存按钮
	$(".save").click(function(){
		// 提交之前进行验证
		if (formValidate().form()) { //判断校验是否符合规则
			//获取要更新的值
			var data={
					"nursePlanId" : $("#nursePlanId").val(),
					"backendPersonId" : $("#backatageUserId").val(),
					"nursePlanType" : $("#nursePlanType").val(),
					"nursePlanTitle" : $("#nursePlanTitle").val(),
					"nursePlanBeginDate" : $("#nursePlanBeginDate").val(),
					"nursePlanContent" : $("#nursePlanContent").val(),
//					"nursePlanStatus" : $("#nursePlanStatus").val(),
					"draftDate" : $("#draftDate").val(),
					"useType" : $('input[name="useType"]:checked').val(),
					"enteringPersonId" : $("#enteringPersonId").val()
			}
			//发送要更新的数据
		  	$.ajax({
				type:"PUT",
				url:urlstorage+"nursePlan",
				dataType: "json", 
				contentType : "application/json",
				beforeSend : function(request) {
		 			request.setRequestHeader("Authorization", token);
		 		},
		 		data : JSON.stringify(data),
				success:function(result,status,httpResponse){
					if (result.statusCode=="200") {
						//token是否过期过期更换token
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
								//返回管理页面
								window.location.href="published-care-plan-management.html";
							}
						})
					}
					if(result.statusCode=="401"){
						window.location.href="login.html";	
					}
					if (result.statusCode == "512" ) {
						Swal.fire({
							  type: 'error',
							  //title: '保存失败！',
							  title: result.msg
						})
		            }
				}
			})
		}else{
		    //不符合规则返回
		    return false;
		} 
	})
})