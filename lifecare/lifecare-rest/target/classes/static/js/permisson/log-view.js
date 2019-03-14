/**
 * 日志信息页面
 * @returns
 */
$(function(){
//    jeDate("#birthday",{
//    	format: "YYYY-MM-DD",
////        isinitVal: true,
//       // valiDate:["0[4-7]$,1[1-5]$,2[58]$",true],
//    });
    jeDate("#startTime",{
    	format: "YYYY-MM-DD hh:mm:ss",
    	 language:{
               name   : "cn",
               month  : ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
               weeks  : [ "日", "一", "二", "三", "四", "五", "六" ],
               times  : ["小时","分钟","秒数"],
               timetxt: ["时间选择","开始时间","结束时间"],
               backtxt:"返回日期",
               clear  : "清空",
               today  : "现在",
               yes    : "确定"
           },
    	 //minDate:mindate,
    });
    jeDate("#endTime",{
    	format: "YYYY-MM-DD hh:mm:ss",
    	 language:{
               name   : "cn",
               month  : ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
               weeks  : [ "日", "一", "二", "三", "四", "五", "六" ],
               times  : ["小时","分钟","秒数"],
               timetxt: ["时间选择","开始时间","结束时间"],
               backtxt:"返回日期",
               clear  : "清空",
               today  : "现在",
               yes    : "确定"
           },
    });

	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	if(urlstorage==null){
		 window.location.href="login.html";	
	}
    //获取当前用户权限
	var permission=localStorage.getItem("userNumber")+"permission";
	//控制按钮显示
	var permissions= localStorage.getItem(permission);
	/**
	 * 通过用户权限控制用户操作：（0：无权限，1：有权限）
	 * 查询：searchBtn
	 */
	if(permissions.length>0){
		var data= permissions.split(",");
        for(var i=0;i<data.length;i++){
        	//若有查询权限，则显示查询按钮
            if(data[i]=="log:query"){
            	$(".searchCarePlan").css("display","inline-block");
            };
        } 
	};
//	 formValidate();
	//显示数据
	load();
	function load(){
		//liuli 2019/02/01 start
		//获取session查询条件
		if(sessionStorage.getItem('unpub_Plan_Cnds')!=null){
			var Srn_Cnds_JsonStr = sessionStorage.getItem('unpub_Plan_Cnds');
			var unpub_Plan_Cnds = JSON.parse(Srn_Cnds_JsonStr);
		    // 操作人ip
			$("#ip").val(unpub_Plan_Cnds.ip),
		    // 操作人姓名
			$("#userCode").val(unpub_Plan_Cnds.userCode),
		    // 开始时间
			$("#startTime").val(unpub_Plan_Cnds.startTime),
		    // 结束时间
			$("#endTime").val(unpub_Plan_Cnds.endTime),   
		    // 请求状态
			$("#code").val(unpub_Plan_Cnds.code)
		}
		//获取session保存当前页码
		if(sessionStorage.getItem('unpublish_pn')==null){
			pn = 1
		}else{
			pn = parseInt(sessionStorage.getItem('unpublish_pn'))
		}
		//liuli 2019/02/01 end
		//清除查询出的数据
		$("#table").bootstrapTable('destroy');
		//渲染表格
		$("#table").bootstrapTable({// 对应table标签的id
			url: urlstorage + "queryOperatorLog", // 获取表格数据的url
			method: 'post',
			cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
			striped: true,  //表格显示条纹，默认为false
			pagination: true, // 在表格底部显示分页组件，默认false
			//pageList: [10, 20], // 设置页面可以显示的数据条数
			pageSize: 10, // 页面数据条数
			//liuli 2019/02/01 start
			pageNumber: pn, // 首页页码
			//liuli 2019/02/01 end
			sidePagination: 'server', // 设置为服务器端分页
			showHeader : true,
			queryParams: queryParams,
			//设置header
			ajaxOptions: {
				beforeSend: function(request) {
				  request.setRequestHeader("Authorization", token);
				}},
			columns: [
				 {
					field: 'id', // 返回json数据中的nursePlanId
					title: '编号', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle', // 上下居中
					hidden:true,
					visible: false
				},{
					field: 'module', // 返回json数据中的customId
					title: '模块名称', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle'
				},{
					field: 'method', // 返回json数据中的servicePersonId
					title: '方法名称', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle'
				},{
					field: 'statusDesc', // 返回json数据中的userNumber
					title: '状态描述', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle', // 上下居中
					formatter: function(value, row, index) {
						if(value !=null && value.length >15){
							return '<a title="'+value+'"" >'+value.substr(0,10)+'...</a>';
						}
						return value;
					}		
				},{
					field: 'exceptionInfo', // 返回json数据中的customRealName
					title: '异常信息', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle', // 上下居中
					formatter: function(value, row, index) {
						if(value !=null && value.length >15){
							return '<a title="'+value+'"" >'+value.substr(0,10)+'...</a>';
						}
						return value;
					}
				},{
					field: 'code',// 返回json数据中的nursePlanType
					title: '请求状态',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter : function (value, row, index) {
						var text = '';
						if (row.code == 0){
						   	text = "成功";//服务方案类型转换
					    }
						if (row.code == 1){
					    	text = "失败";//服务方案类型转换
			    		}
						return text;
					}		
				},{
					field: 'args', // 返回json数据中的nursePlanTitle
					title: '参数', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle', // 上下居中
//					class: 'colStyle',
//					width: 100,
//
//					formatter: function(value, row, index) {
//						var values = row.args;
//						var span=document.createElement('span');
//						span.setAttribute('title',values);
//						span.innerHTML = row.args;
//						return span.outerHTML;
//				    }
					formatter: function(value, row, index) {
						if(value !=null && value.length >15){
							return '<a title="'+value+'"" >'+value.substr(0,10)+'...</a>';
						}
						return value;
					}
				},{
					field: 'userCode',
					title: '操作人',
					align: 'center',
					valign: 'middle'
				},{
					field: 'ip',
					title: '操作人IP',
					align: 'center',
					valign: 'middle'
				},{
					field: 'createTime',
					title: '操作时间',
					align: 'center',
					valign: 'middle'
				}

			],
			//liuli 2019/02/01 start
			//页码跳转的时候保存当前页码
			onPageChange:function(number,size){
				sessionStorage.setItem('unpublish_pn',number,size);
			},
			//liuli 2019/02/01 end
			onLoadSuccess: function(data){
				console.info(data);//加载成功时执行
				if (data.statusCode=="512") {
					Swal.fire({
						  type: 'error',
						  title: data.msg
					})
				}else if(data.statusCode == "401"){
					 window.location.href="login.html";
				}
				console.info("加载成功"+data.statusCode);//加载成功时执行
			},
			onLoadError: function(data){
				if(data.statusCode=="401"){
					window.location.href = "login.html";
				}
				console.info("加载数据失败"+data.statusCode);//加载失败时执行
			}
		});
		//表格参数设置
		queryParams: function queryParams(params) {// 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
			return {
				limit : params.limit,// 每页要显示的数据条数
				offset : params.offset,// 每页显示数据的开始行号
				// 操作人ip
				ip:$("#ip").val(),
				// 操作人姓名
				userCode:$("#userCode").val(),
				// 开始时间
				startTime:$("#startTime").val(),
				// 结束时间
				endTime:$("#endTime").val(),   
				// 请求状态
				code:$("#code").val()
			}
		};
	}
	//验证日期格式
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
//	function formValidate(){
//		validateResult= $("#missionForm").validate({
//			onkeyup : function(ip,userCode,code){
//				$("#ip").valid();
//				$("#userCode").valid();
//				$("#code").valid()
//			},
//		    rules: {
//		    	userNumber:{
//		    		maxlength:8
//		        },
//				idCard:{
//					rangelength:[18,18]
////					isCard:true
//		        },
//				realName:{
//					maxlength:8
//		        },
//				mobile:{
//		    		digits : true,
//		    		rangelength:[11,11]
//		        },
//		        birthday:{
//		        	isDate:"#birthday",
//		        }
//		    },
//		    messages: {
//		    	userNumber:{
//		    		maxlength:"客户编号长度不能大于8位"
//		        },
//				idCard:{
//					rangelength:"客户身份证号长度必须为18位"
////					isCard:"客户身份证号长度必须为15位或18位"
//		        },
//				realName:{
//					maxlength:"客户姓名长度不能大于8字"
//		        },
//				mobile:{
//		    		digits : "客户手机号必须为数字",
//		    		rangelength:"客户手机号长度必须为11位"
//		        }
//		    },
//		    errorPlacement: function(error, element) {
//		    	error.appendTo(element.parent());
//			}
//		});
//		return validateResult;
//	}
	//查询日志信息
	$(".searchCarePlan").click(function(){
		// 提交之前进行验证
//		if (formValidate().form()) { //判断校验是否符合规则
	    if (true) { //判断校验是否符合规则
	    	//liuli 2019/02/01 start
	    	//验证成功将查询信息保存到session里
	    	var unpublished_Plan_Conditions = {
    			// 操作人ip
				ip:$("#ip").val(),
				// 操作人姓名
				userCode:$("#userCode").val(),
				// 开始时间
				startTime:$("#startTime").val(),
				// 结束时间
				endTime:$("#endTime").val(),   
				// 请求状态
				code:$("#code").val()
	    	};
	    	//以JSON格式保存
	    	sessionStorage.setItem('unpub_Plan_Cnds', JSON.stringify(unpublished_Plan_Conditions));
	    	sessionStorage.removeItem('unpublish_pn');
			//liuli 2019/02/01 end
	    	load();
//	    	$('#table').bootstrapTable('refresh');
	    }else{
	    	//不符合规则返回
	    	return false;
	    } 
	});
})
