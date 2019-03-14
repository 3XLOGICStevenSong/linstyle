
$(function(){
    jeDate("#birthday",{
     	 format: "YYYY-MM-DD"
          //isinitVal: true,
         // valiDate:["0[4-7]$,1[1-5]$,2[58]$",true],
     });
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	
	//渲染表格
	load();
	var loginUserId=localStorage.getItem("userId");
	$("#loginUserId").val(loginUserId);

	//获取URL中参数
	var selectFlag=window.location.href.substring(window.location.href.lastIndexOf("=")+1,window.location.href.length);
	//渲染表格
	function load(){
		$("#table").bootstrapTable('destroy');
		$("#table").bootstrapTable({ // 对应table标签的id
			method : "post",
			url: urlstorage+"user/list", // 获取表格数据的url
			cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
			striped: true,  //表格显示条纹，默认为false
			pagination: true, // 在表格底部显示分页组件，默认false
			pageSize: 10, // 页面数据条数
			pageNumber: 1, // 首页页码
			sidePagination: 'server', // 设置为服务器端分页
			showHeader : true,
			queryParams:queryParams,
		    //设置header
			ajaxOptions: {
				beforeSend: function(request) {
				  request.setRequestHeader("Authorization", token);
				}	
			},
			columns : [
				 {
					field: 'userId', // 返回json数据中的name
					title: '客户主键', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle', // 上下居中
					hidden:true,
					visible: false
				 },
				 {
					field: 'userNumber',// 返回json数据中的name
					title: '客户编号', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle' // 上下居中
				 }, 
				 {
					field: 'realName',// 返回json数据中的name
					title: '客户姓名',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
				
				 },
				 {
					field: 'mobile',// 返回json数据中的name
					title: '手机',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
				
				 },
				 {
					field: 'userAddr',// 返回json数据中的name
					title: '地址',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
				 },
				 {
					field: 'birthday',// 返回json数据中的name
					title: '生日',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
				 },
				 {
					field: 'sex',// 返回json数据中的name
					title: '性别',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter : function (value, row, index) {
						var text = '';
						if (row.sex == 1){
						   	text = "女";//性别转换
					    } else if (row.sex == 0){
					    	text = "男";//性别转换
			    		}
						    return text;
						}
				 },
				{
					field: 'userLevel',// 返回json数据中的name
					title: '星级',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
				
				},
				{
					title: "操作",// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter: function (value, row, index) {
						return "<button type='button' class='btn btn-primary btn-size btn-pos select data='" + row.userId + "'>选择</button></td>";
					},
					events: 'operateEvents'
				}
			],
			onLoadSuccess: function(data ){
			    //加载成功时执行
				console.info("加载成功");
			},
			onLoadError: function(){  
				//加载失败时执行
			    console.info("加载数据失败");
			}
		});
		//表格参数设置
		queryParams: function queryParams(params) {// 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
			return {
				limit : params.limit,// 每页要显示的数据条数
				offset : params.offset,// 每页显示数据的开始行号
				//状态(0:启用 1:停用)
				userStatus : "0", //状态(0:启用)
				//服务类别
				userType : "1", //用户类型(1:客户)
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
	//入力项验证方法
	function formValidate(){
		 validateResult= $("#customerForm").validate({
		     rules: {
		         mobile : {digits:true,}
		     },
		     messages: {
		         mobile : {digits:"客户手机号设定不正：客户手机号必须为数字"}
		     },
		    errorPlacement: function(error, element) {
		    	 error.appendTo(element.parent());
		    },
		 });
		 return validateResult;
	};	
			
	//点击查询
	$("#search").click(function(){
		// 提交之前进行验证，判断校验是否符合规则
	    if (formValidate().form()) { 
	    	//符合规则返回
	    	load();
	    }else{
	        // 不符合规则返回
	        return false;
	    } 
	});	

	
window.operateEvents = {
		
		//点击选择
		'click .select': function (e, value, row, index) {
			//"未分配任务添加画面"/"未分配任务添加画面"参数汉字转码
			var paraUndistributed = "?userId=" + row.userId + "&userNumber=" + row.userNumber +"&realName=" + row.realName + "&mobile=" + row.mobile +"&userAddr=" + row.userAddr +"&sex=" + row.sex +"&userLevel=" + row.userLevel  +"&age=" + row.age +"&selectFlag=" + selectFlag;
			var passUndistributed = encodeURI(paraUndistributed);
			//"未发布任务添加画面"参数汉字转码
			var paraUnpublished = "?userId=" + row.userId + "&userNumber=" + row.userNumber +"&realName=" + row.realName +"&selectFlag=" + selectFlag;
			var passUnpublished = encodeURI(paraUnpublished);
			//返回"未分配任务添加画面"
			if (selectFlag == "1"){
			window.location.href = "undistributed-mission-add.html" + passUndistributed ;
			}
			//返回"未分配任务编辑画面"			
			else if(selectFlag == "2"){
			window.location.href = "undistributed-mission-edit.html" + passUndistributed ;
			
			}
			//返回"未发布任务添加画面"				
			else if(selectFlag == "3"){
			window.location.href = "unpublished-care-plan-add.html" + passUnpublished ;
			}
		}
	};
});