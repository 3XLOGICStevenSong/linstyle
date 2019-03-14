
$(function(){
    jeDate("#birthday",{
      	 format: "YYYY-MM-DD"
           //isinitVal: true,
          // valiDate:["0[4-7]$,1[1-5]$,2[58]$",true],
      });
	// 获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	//获取URL中参数
	var selectFlag=window.location.href.substring(window.location.href.lastIndexOf("=")+1,window.location.href.length);
	// 渲染表格
	load();
function load(){
	$("#table").bootstrapTable('destroy');
	$("#table").bootstrapTable({ // 对应table标签的id
		method : "post",
		url: urlstorage+"user/list", // 获取表格数据的url
		cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
		striped: true,  // 表格显示条纹，默认为false
		pagination: true, // 在表格底部显示分页组件，默认false
		// pageList: [10, 20], // 设置页面可以显示的数据条数
		pageSize: 10, // 页面数据条数
		pageNumber: 1, // 首页页码
		sidePagination: 'server', // 设置为服务器端分页
		showHeader : true,
		queryParams:queryParams,
     // 设置header
		ajaxOptions: {
			beforeSend: function(request) {
			  request.setRequestHeader("Authorization", token);
			}	
		},
		columns : [
			 {
					field: 'userId', // 返回json数据中的userId
					title: '用户主键', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle', // 上下居中
					hidden:true,
					visible: false
			 },
			 {
					field: 'userNumber',// 返回json数据中的userNumber
					title: '用户工号', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle' // 上下居中
			 }, 
			 {
					field: 'realName',// 返回json数据中的realName
					title: '用户姓名',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
			
			 },
			 {
					field: 'mobile',// 返回json数据中的mobile
					title: '手机',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
			
			 },
			 {
					field: 'userAddr',// 返回json数据中的userAddr
					title: '地址',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
			 },
			 {
					field: 'birthday',// 返回json数据中的birthday
					title: '生日',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
			 },
			 {
					field: 'sex',// 返回json数据中的sex
					title: '性别',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle',// 上下居中
					formatter : function (value, row, index) {
						var text = '';
						if (row.sex == 1){
						   	text = "女";// 性别转换
					    } else if (row.sex == 0){
					    	text = "男";// 性别转换
			    		}
						    return text;
						}

			 },
			 {
					field: 'userLevel',// 返回json数据中的name
					title: '等级',// 表格表头显示文字
					align: 'center',// 左右居中
					valign: 'middle'// 上下居中
			 },
			 {
					title: "操作",
					align: 'center',
					valign: 'middle',
					formatter: function (value, row, index) {
						return "<button type='button' class=' btn btn-primary btn-default col-md-offset-1 btn-size edit'  data='"+row.userId+"'>选择</button></td>"; 
					},
				events: 'operateEvents'
			 }
			
		],
		onLoadSuccess: function(data ){
				console.info("加载成功");
		},
		onLoadError : function() { 
			// 加载失败时执行
			console.info("加载数据失败");
		}
	});
	
	// 表格参数设置
	queryParams: function queryParams(params) {// 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
			return {
				limit : params.limit,// 每页要显示的数据条数
				offset : params.offset,// 每页显示数据的开始行号
				//用户类型(3:用户)
				userType : "3",
				//制定照护方案(1:可)
				draftNursePlan:"1",
				//状态
				userStatus:"0",
				// 用户工号
			    userNumber:$("#userNumber").val(),
			    // 用户身份证号
			    idCard:$("#idCard").val(),
			    // 用户姓名
			    realName:$("#realName").val(),
			    // 用户手机号
			    mobile:$("#mobile").val(),   
			    // 用户生日
			    birthday:$("#birthday").val(), 
			    // 等级
			    userLevel:$("#userLevel").val()
			}
    }
};
// 数据check
function formValidate(){
	  validateResult= $("#form").validate({
	        rules: {
	            // 用户手机号
	            mobile:{
	                digits:true
	            }
	    },
	     messages: {
	    	 // 用户手机号
	    	 mobile: {
	    		 digits: "用户手机号设定不正：用户手机号必须为数字"
	            }
	     },
	     errorPlacement: function(error, element) {
	    	 error.appendTo(element.parent());
			}
	    });
	  return validateResult;
	};
	
// 查询后台人员
$(".searchBtn").click(function(){
	// 查询之前进行数据check
	// 提交之前进行验证
    if (formValidate().form()) { // 判断校验是否符合规则
    	load();
    }else{
      // 不符合规则返回
      return false;
  } 
});
// 点击选择
window.operateEvents = {
		
		// 监听事件
		'click .edit': function (e, value, row, index) {
			//地址栏传递的参数
			var urlParam1 = "userId="+row.userId+"&userNumber="+row.userNumber+"&realName="+row.realName+"&selectFlag="+selectFlag;
			//转码（解决IE地址栏传递中文乱码问题）
			var urlParam = encodeURI(encodeURI(urlParam1));
			//未发布编辑
			if(selectFlag=="4"){
				window.location.href = "unpublished-care-plan-edit.html?"+urlParam;
			}
			//未发布添加
			if(selectFlag=="5"){
				window.location.href = "unpublished-care-plan-add.html?"+urlParam;
			}
			//已发布编辑
			if(selectFlag=="6"){
				window.location.href = "published-care-plan-edit.html?"+urlParam;
			}
	}}
})