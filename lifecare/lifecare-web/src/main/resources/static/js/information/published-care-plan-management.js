/**
 * 已发布照护方案页面
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
	 * 编辑：updateBtn
	 * 删除：deleteBtn
	 */
//	var searchBtn=0;
	var updateBtn=0;
	var deleteBtn=0;
//	if(searchBtn==0){
//		$(".searchCarePlan").css("display","none");
//	}
	if(permissions.length>0){
		var data= permissions.split(",");
        for(var i=0;i<data.length;i++){
        	//若有查询权限，则显示查询按钮
            if(data[i]=="planend:query"){
//            	searchBtn=1;
            	$(".searchCarePlan").css("display","inline-block");
            };
            //若有编辑权限，updateBtn为1
            if(data[i]=="planend:update"){
            	updateBtn=1;
            }
            //若有删除权限，deleteBtn为1
            if(data[i]=="planend:delete"){
            	deleteBtn=1;
            }
        } 
	};
    
	//显示数据
	load();
	function load(){
		//清除查询出的数据
		$("#table").bootstrapTable('destroy');
		//渲染表格
		$("#table").bootstrapTable({// 对应table标签的id
			url: urlstorage + "nursePlan/assigned/list", // 获取表格数据的url
			method: 'post',
			cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
			striped: true,  //表格显示条纹，默认为false
			pagination: true, // 在表格底部显示分页组件，默认false
			//pageList: [10, 20], // 设置页面可以显示的数据条数
			pageSize: 10, // 页面数据条数
			pageNumber: 1, // 首页页码
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
					title: '客户主键', // 表格表头显示文字
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
					field: 'nursePlanTitle', // 返回json数据中的nursePlanTitle
					title: '已发布照护方案', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle' // 上下居中
				}, {
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
					field: 'id',
					title: "操作",
					align: 'center',
					valign: 'middle',
					formatter: function (value, row, index) {
						return "<button type='button' class='btn btn-primary btn-size edit'  data='"+row.nursePlanId+"' >编辑</button><button type='button' class='btn btn-default btn-size col-md-offset-1  deleteTr'  data='"+row.nursePlanId+"'>删除</button></td>";
					},
					 events: 'operateEvents'
				}
			],
			onLoadSuccess: function(data){
				if(updateBtn==0){
					//有权限编辑
					$(".edit").css("display","none");
				}
				if(deleteBtn==0){
					//有权限删除
					$(".deleteTr").css("display","none");
				}
				if(updateBtn==0&&deleteBtn==0){
					//没有权限编辑
					$('#table').bootstrapTable('hideColumn', 'id');
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
				nursePlanStatus:"1",
				//客户工号
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
	//编辑和删除事件
	window.operateEvents = {
		//编辑行
		'click .edit': function (e, value, row, index) {
			window.location.href = "published-care-plan-edit.html?nursePlanId="+row.nursePlanId;
		},
		//删除行
		'click .deleteTr': function (e, value, row, index) {
			$(".ok").css('display','none'); 
			$("#myModal").modal('show');
			$(".sure").on("click",function(){
				$.ajax({
					type:"DELETE",
					url: urlstorage + "nursePlan/" + row.nursePlanId,
					dataType: "json", 
					contentType : "application/json",
					beforeSend: function(request) {
						request.setRequestHeader("Authorization", token);
					},
					data:JSON.stringify( {
						roleId:row.nursePlanId
					}),
					success:function(result){
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
							$(".ok").css('display','block'); 
							$(".ok").text("删除成功");
							$(".ok").show().delay(1000).fadeOut();
							var refreshurl= urlstorage + "nursePlan/unassigned/list";
							$('#table').bootstrapTable('refresh',refreshurl);
						}
						if(result.statusCode=="512"){
							alert(result.msg);
						}
						if(result.statusCode=="401"){
							window.location.href="login.html";
						}
					}
				});
			});
		}
	}
	//身份证号的验证(18位验证)
	jQuery.validator.addMethod("isCard", function(value,element) {
		    var regExp=/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/   
		    return this.optional(element) || (regExp.test(value));    
	});
	//验证方法
	function formValidate(){
		validateResult= $("#missionForm").validate({
			onkeyup : function(userNumber,idCard,realName,mobile){
				$("#userNumber").valid();
				$("#idCard").valid();
				$("#realName").valid();
				$("#mobile").valid();
			},
		    rules: {
		    	userNumber:{
		    		maxlength:8
		        },
				idCard:{
					isCard:true
		        },
				realName:{
					maxlength:8
		        },
				mobile:{
		    		digits : true,
		    		rangelength:[11,11]
		        }
		    },
		    messages: {
		    	userNumber:{
		    		maxlength:"客户编号长度不能大于8位"
		        },
				idCard:{
					isCard:"客户身份证号长度必须为15位或18位"
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
			},
		});
		return validateResult;
	}
	//查询已发布照护方案
	$(".searchCarePlan").click(function(){
		// 提交之前进行验证
	    if (formValidate().form()) { //判断校验是否符合规则
	    	load();
//	    	$('#table').bootstrapTable('refresh');
	    }else{
	    	//不符合规则返回
	    	return false;
	    } 
	});
})
