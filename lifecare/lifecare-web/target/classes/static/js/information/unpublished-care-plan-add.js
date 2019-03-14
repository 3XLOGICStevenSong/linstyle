/**
 * 未发布照护方案添加
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
	
	//获取token AND url
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
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
    var url1 = location.search; 
    //解码（解决IE地址栏传递中文乱码情况）
	var url = decodeURI(url1);
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
    	    $("#customId").val(customId);
    	    $("#customNumber").val(customNumber);
    	    $("#customRealName").val(customRealName);
    	}
    	//制定人员信息
    	if(selectFlag=="5"){
    		//获取缓存json
        	jsondata=localStorage.getItem("unassigndata");
        	//显示之前输入信息
        	$("#missionForm").initFormPlus(jsondata);
    		var userId=params['userId'];
    		var userNumber=params['userNumber'];
    		var userRealName=params['realName'];
    	    $("#backatageUserId").val(userId);
    	    $("#backatageUserNumber").val(userNumber);
    	    $("#backatageUserName").val(userRealName);
    	}
    }
	//输入的日期与当前日期的比较
	jQuery.validator.methods.compareDate = function(value, element, param) {
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
	//表单校验方法
	function formValidate(){
		validateResult= $("#missionForm").validate({
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
		            compareDate:true
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
		            compareDate:true
		        }
		    },
		    messages: {
		    	customNumber: {
		            required: "客户编号不能为空"
		        },
		        nursePlanTitle:{
			        required: "照护方案标题不能为空",
			        trim: "照护方案标题不能为全空格",
			        maxlength:"照护方案标题长度不能大于50字"
			    },
			    nursePlanBeginDate: {
			    	required:"方案开始日期不能为空",
		            compareDate:"方案开始日期必须大于等于当日日期"
		        },
		        nursePlanContent:{
		            required :"照护方案内容不能为空",
		            trim: "照护方案内容不能全为空格",
		            maxlength:"照护方案内容长度不能大于4000字"
		        },
		        backatageUserNumber:{
		            required: "制定人员工号不能为空"//必须输入的字段
		        },
		        draftDate: {
		            required:"制定日期不能为空",
		            compareDate:"制定日期必须小于等于当日日期"
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
	//未发布照护方案添加Flag
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
	//保存按钮
	$(".save").click(function(){
		// 提交之前进行验证
		if (formValidate().form()) { //判断校验是否符合规则
			//获取要发送的数据值
			//客户ID
			var customId=$("#customId").val();
			//照护方案标题
			var nursePlanTitle=$("#nursePlanTitle").val();
			//方案开始日期
			var nursePlanBeginDate=$("#nursePlanBeginDate").val();
			//照护方案内容
			var nursePlanContent=$("#nursePlanContent").val();
			//制定人员ID
			var backatageUserId=$("#backatageUserId").val();
			//制定日期
			var draftDate=$("#draftDate").val();
			//发布状态
			var nursePlanStatus =$("#nursePlanStatus").val();
			var data ={
			  	"customId":customId,
			  	"backendPersonId":backatageUserId,
			  	"nursePlanTitle":nursePlanTitle,
			  	"nursePlanBeginDate":nursePlanBeginDate,
			  	"nursePlanContent":nursePlanContent,
			  	"draftDate":draftDate,
			  	"nursePlanStatus":nursePlanStatus
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
							title: "保存成功",
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
							  title: '保存失败！',
							  text: result.msg
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