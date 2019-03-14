/**
 * 未发布服务方案页面
 * @returns
 */
$(function(){
    jeDate("#birthday",{
    	format: "YYYY-MM-DD",
//        isinitVal: true,
       // valiDate:["0[4-7]$,1[1-5]$,2[58]$",true],
    });

	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	if(urlstorage==null){
		 window.location.href="login.html";	
	}
	/**
	 * 获取地址栏传递的数据
	 * 从地址栏截取数据，判断数据来源后获取信息
	 */
    var url = location.search; 
    if(url!=null){
    	var params=$.getParam(url);
    	//客户信息
    	var userNumber=params['userNumber'];
    	$("#userNumber").val(userNumber);
    }
    //获取当前用户权限
	var permission=localStorage.getItem("userNumber")+"permission";
	//控制按钮显示
	var permissions= localStorage.getItem(permission);
	/**
	 * 通过用户权限控制用户操作：（0：无权限，1：有权限）
	 * 查询：searchBtn
	 * 添加：addBtn
	 * 编辑：updateBtn
	 * 发布：publishBtn
	 */
	var updateBtn=0;
	var publishBtn=0;
	var deleteBtn=0;
	if(permissions.length>0){
		var data= permissions.split(",");
        for(var i=0;i<data.length;i++){
        	//若有查询权限，则显示查询按钮
            if(data[i]=="unpublish:query"){
//            	searchBtn=1;
            	$(".searchCarePlan").css("display","inline-block");
            };
            //若有添加权限，则显示添加按钮
            if(data[i]=="unpublish:create"){
//            	addBtn=1;
            	$(".addCarePlan").css("display","inline-block");
            }
            //若有编辑权限，updateBtn为1
            if(data[i]=="unpublish:update"){
            	updateBtn=1;
            }
            //若有发布权限，publishBtn为1
            if(data[i]=="unpublish:publish"){
            	publishBtn=1;
            }
            //若有发布权限，publishBtn为1
            if(data[i]=="unpublish:delete"){
            	deleteBtn=1;
            }
        } 
	};
	 formValidate();
	//显示数据
	load();
	function load(){
		//liuli 2019/02/01 start
		//获取session查询条件
		if(sessionStorage.getItem('unpub_Plan_Cnds')!=null){
			var Srn_Cnds_JsonStr = sessionStorage.getItem('unpub_Plan_Cnds');
			var unpub_Plan_Cnds = JSON.parse(Srn_Cnds_JsonStr);
		    // 客户编号
			$("#userNumber").val(unpub_Plan_Cnds.userNumber),
		    // 客户身份证号
			$("#idCard").val(unpub_Plan_Cnds.idCard),
		    // 客户姓名
			$("#realName").val(unpub_Plan_Cnds.realName),
		    // 客户手机号
			$("#mobile").val(unpub_Plan_Cnds.mobile),   
		    // 客户生日
			$("#birthday").val(unpub_Plan_Cnds.birthday), 
		    // 星级
			$("#userLevel").val(unpub_Plan_Cnds.userLevel)
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
			url: urlstorage + "nursePlan/unassigned/list", // 获取表格数据的url
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
					field: 'nursePlanId', // 返回json数据中的nursePlanId
					title: '护理方案编号', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle', // 上下居中
					hidden:true,
					visible: false
				},{
					field: 'customId', // 返回json数据中的customId
					title: '客户主键', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle', // 上下居中
					hidden:true,
					visible: false
				},{
					field: 'backendPersonId', // 返回json数据中的servicePersonId
					title: '后台用户主键', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle', // 上下居中
					hidden:true,
					visible: false
				},{
					field: 'userNumber', // 返回json数据中的userNumber
					title: '客户编号', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle' // 上下居中
				},{
					field: 'customRealName', // 返回json数据中的customRealName
					title: '客户姓名', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle' // 上下居中
				},{
					field: 'nursePlanType',// 返回json数据中的nursePlanType
					title: '服务方案类型',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter : function (value, row, index) {
						var text = '';
						if (row.nursePlanType == 1){
						   	text = "生活助理";//服务方案类型转换
					    }
						if (row.nursePlanType == 2){
					    	text = "老龄照护";//服务方案类型转换
			    		}
						if (row.nursePlanType == 3){
					    	text = "临床护理";//服务方案类型转换
			    		}
						return text;
					}		
				},{
					field: 'nursePlanTitle', // 返回json数据中的nursePlanTitle
					title: '未发布服务方案', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle' // 上下居中
				},{
					field: 'nursePlanBeginDate',
					title: '方案开始日期',
					align: 'center',
					valign: 'middle'
				},{
					field: 'backendPersonRealName',
					title: '制定人员姓名',
					align: 'center',
					valign: 'middle'
				},{
					field: 'draftDate',
					title: '制定日期',
					align: 'center',
					valign: 'middle'
				},{
					field: 'useType',// 返回json数据中的name
					title: '状态',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter : function (value, row, index) {
						var text = '';
						if (row.useType == 0){
						   	text = "启用";//状态转换
					    } else if (row.useType == 1){
					    	text = "停用";//状态转换
			    		}
						return text;
					}		
				},{
					field: 'id',
					title: "操作",
					align: 'center',
					valign: 'middle',
					formatter: function (value, row, index) {
						return "<button tabIndex='9' type='button' class='btn btn-primary published'  data='"+row.nursePlanId+"' >发布</button><button tabIndex='9' type='button' class='btn btn-primary btn-size btn-pos edit'  data='"+row.nursePlanId+"' >编辑</button><button type='button' class='btn btn-default btn-size btn-pos  delete' >删除</button></td>";
					},
					 events: 'operateEvents'
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
				if(updateBtn==0){
					//无权限编辑
					$(".edit").css("display","none");
				}
				if(publishBtn==0){
					//无权限发布
					$(".published").css("display","none");
				}
				if(deleteBtn==0){
					//删除
					$(".delete").css("display","none");
				}
				if(updateBtn==0&&publishBtn==0&&deleteBtn==0){
					//没有权限编辑也没有权限发布
					$('#table').bootstrapTable('hideColumn', 'id');
				}
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
				nursePlanStatus:"0",
				//客户编号
				userNumber:$("#userNumber").val(),
				//客户身份证号
				idCard:$("#idCard").val(),
				//客户姓名
				realName:$("#realName").val(),
				//客户手机号
				mobile:$("#mobile").val(),   
				//客户生日
				birthday:$("#birthday").val(),
				//星级
				userLevel:$("#userLevel").val()
			}
		};
	}
	//编辑和发布事件
	window.operateEvents = {
		//编辑行
		'click .edit': function (e, value, row, index) {
			window.location.href = "unpublished-care-plan-edit.html?"+window.btoa(unescape(encodeURIComponent("nursePlanId="+row.nursePlanId)));
		},
		//发布时间
		'click .published': function (e, value, row, index) {
			$(".ok").css('display','none'); 
			$("#myModal").modal('show');
			$(".sure").on("click",function(){
				$.ajax({
					type:"PUT",
					url: urlstorage + "nursePlan/nursePlanStatus",
					dataType: "json", 
					contentType : "application/json",
					beforeSend: function(request) {
						request.setRequestHeader("Authorization", token);
					},
					data:JSON.stringify( {
						nursePlanId:row.nursePlanId,
						nursePlanStatus:1
					}),
					success:function(result,status,httpResponse){
						if (result.statusCode=="200") {
							//将token存入缓存	
							var token=httpResponse.getResponseHeader('Authorization');
							if(token!=null && token!="" && token!=undefined){
								localStorage.setItem("token", token); 
							}
							//弹出框显示
							$('#table').bootstrapTable('remove', {
							field: 'nursePlanId',
							values: [row.nursePlanId]
							});
							//弹出框隐藏
							$("#myModal").modal('hide');
							Swal.fire({
		  						  type: 'success',
		  						  title: result.msg
			        			})
							var refreshurl= urlstorage + "nursePlan/unassigned/list";
							$('#table').bootstrapTable('refresh',refreshurl);
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
				$(".sure").unbind();
			});
		},
		//发布时间
		'click .delete': function (e, value, row, index) {
			$(".ok").css('display','none'); 
			$("#myModal").modal('show');
			$(".sure").on("click",function(){
				$.ajax({
					type:"DELETE",
					url: urlstorage + "nursePlan/"+row.nursePlanId,
					dataType: "json", 
					contentType : "application/json",
					beforeSend: function(request) {
						request.setRequestHeader("Authorization", token);
					},
//					data:JSON.stringify( {
//						nursePlanId:row.nursePlanId,
//						nursePlanStatus:1
//					}),
					success:function(result,status,httpResponse){
						if (result.statusCode=="200") {
							//将token存入缓存	
							var token=httpResponse.getResponseHeader('Authorization');
							if(token!=null && token!="" && token!=undefined){
								localStorage.setItem("token", token); 
							}
							//弹出框显示
							$('#table').bootstrapTable('remove', {
							field: 'nursePlanId',
							values: [row.nursePlanId]
							});
							//弹出框隐藏
							$("#myModal").modal('hide');
							Swal.fire({
		  						  type: 'success',
		  						  title: result.msg
			        			})
							var refreshurl= urlstorage + "nursePlan/unassigned/list";
							$('#table').bootstrapTable('refresh',refreshurl);
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
			});
		}
	}
	//身份证号的验证(格式check)
//    jQuery.validator.addMethod("isCard", function(value,element) {
//	    var regExp=/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/   
//	    return this.optional(element) || (regExp.test(value));    
//	});
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
			onkeyup : function(userNumber,idCard,realName,mobile,birthday){
				$("#userNumber").valid();
				$("#idCard").valid();
				$("#realName").valid();
				$("#mobile").valid();
				$("#birthday").valid();
			},
		    rules: {
		    	userNumber:{
		    		maxlength:8
		        },
				idCard:{
					rangelength:[18,18]
//					isCard:true
		        },
				realName:{
					maxlength:8
		        },
				mobile:{
		    		digits : true,
		    		rangelength:[11,11]
		        },
		        birthday:{
		        	isDate:"#birthday",
		        }
		    },
		    messages: {
		    	userNumber:{
		    		maxlength:"客户编号长度不能大于8位"
		        },
				idCard:{
					rangelength:"客户身份证号长度必须为18位"
//					isCard:"客户身份证号长度必须为15位或18位"
		        },
				realName:{
					maxlength:"客户姓名长度不能大于8字"
		        },
				mobile:{
		    		digits : "客户手机号必须为数字",
		    		rangelength:"客户手机号长度必须为11位"
		        }
		    },
		    errorPlacement: function(error, element) {
		    	error.appendTo(element.parent());
			}
		});
		return validateResult;
	}
	//查询未发布服务方案
	$(".searchCarePlan").click(function(){
		// 提交之前进行验证
	    if (formValidate().form()) { //判断校验是否符合规则
	    	//liuli 2019/02/01 start
	    	//验证成功将查询信息保存到session里
	    	var unpublished_Plan_Conditions = {
	    		    // 客户编号
	    		userNumber:$("#userNumber").val(),
				    // 客户身份证号
				idCard:$("#idCard").val(),
				    // 客户姓名
				realName:$("#realName").val(),
				    // 客户手机号
				mobile:$("#mobile").val(),   
				    // 客户生日
				birthday:$("#birthday").val(), 
				    // 星级
				userLevel:$("#userLevel").val()
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
	//添加护理计划
	$(".addCarePlan").on("click",function(){
		//debugger;
		window.location.href="unpublished-care-plan-add.html";
	});
})
