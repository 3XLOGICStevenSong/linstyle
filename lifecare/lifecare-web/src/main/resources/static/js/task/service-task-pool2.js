
$(function(){
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	//重缓存中去查询日期
	//选择人员信息初始化
    var url = location.search; 
    if(url!=null&&url!=""){
    	  var params=jQuery.getParam(url);
    	  if(params['taskflag']!=undefined ){
    		  $("#orderDate").val(localStorage.getItem("orderDate")); 
    	  }
    }
    
	// 获取当前用户权限
	// var permission=localStorage.getItem("userNumber")+"permission";
	 // 控制按钮显示
	// var permissions= localStorage.getItem(permission);
	// 客户查看权限
	// var custom=0;
	// 服务人员查看权限
	// var service=0;
	// if(permissions.length>0){
	// var data= permissions.split(",");
	// for(var i=0;i<data.length;i++){
	// if(data[i]=="customer:update"){
	// custom=1;
	// }
	// if(data[i]=="personal:update"){
	// service=1;
	// }
	// //break;
	// }
	// }
	//初始化查询日期
	var searchDay="";
	//获取当前日期
	var datetime = new Date();
	//时间格式化
	function timeFormatter(datetime){
	var year=datetime.getFullYear();
	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	var d = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	var now=year+'-'+month+"-"+d;
	return now;}
	searchDay=timeFormatter(datetime) ;
	$("#orderDate").val(searchDay);
	localStorage.setItem("orderDate",searchDay);
	//日期表头
    var dateArray = new Array();
	 dateArray.push(timeFormatter(datetime));
    for(var i = 0; i < 6; i++){
    	
    	datetime.setDate(datetime.getDate() +1);
    	 dateArray.push(timeFormatter(datetime));
    	// alert(dateArray[i])
    };
    //日期选择
    jeDate("#orderDate",{
   	 format: "YYYY-MM-DD",
        isinitVal: true,
       // valiDate:["0[4-7]$,1[1-5]$,2[58]$",true],
   });
	
	//渲染表格
	initData();
//渲染表格
function initData(){
	$("#table").bootstrapTable('destroy');
	$("#table").bootstrapTable({ // 对应table标签的id
		method : "post",
		url: urlstorage+"task/taskPool", // 获取表格数据的url
		cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
		striped: true,  //表格显示条纹，默认为false
		//pagination: true, // 在表格底部显示分页组件，默认false
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
				field: 'id', // 返回json数据中的name
				title: 'id', // 表格表头显示文字
				align: 'center', // 左右居中
				valign: 'middle', // 上下居中
				hidden:true,
				visible: false,
			},
			{
				field: 'orderNo', // 返回json数据中的name
				title: 'orderNo', // 表格表头显示文字
				align: 'center', // 左右居中
				valign: 'middle', // 上下居中
				hidden:true,
				visible: false,
			},
			{
				field: 'orderAddr', // 返回json数据中的name
				title: '地点', // 表格表头显示文字
				align: 'center', // 左右居中
				valign: 'middle', // 上下居中
				//hidden:true,
				//visible: false,
			},
			{
				field: 'serviceCategoryColor',// 返回json数据中的name
				title: dateArray[0], // 表格表头显示文字
				align: 'center', // 左右居中
				valign: 'middle', // 上下居中
				cellStyle:function(value,row,index){
					//alert(row.serviceCategoryColor);
					var dateth= new Date(dateArray[0]);
					var daterow = new Date(row.orderDate);
						if (row.serviceCategoryColor!=undefined && dateth.getTime() ==daterow.getTime()){
							//alert(row.serviceCategoryColor+">>>>>>");
							return {css:{"background-color":row.serviceCategoryColor}};
						}
						else{
							return {css:{"background-color":""}};
						}
						},
					
						formatter:function( value,row,index ){
							var dateth= new Date(dateArray[0]);
							var daterow = new Date(row.orderDate);
							if(dateth.getTime() ==daterow.getTime()){
								if(row.serviceCategoryColor!=undefined){
									return "<button type='button'class='clicktd'>"+row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo+"</button>";
								
								}else{
									return row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo;
								
								}
								
							
							}else{
								return "";
							}
						},
						 events: 'operateEvents'
		
		 },
			{
				field: 'serviceCategoryColor',// 返回json数据中的name
				title: dateArray[1], // 表格表头显示文字
				align: 'center', // 左右居中
				valign: 'middle', // 上下居中
				cellStyle:function(value,row,index){
					//alert(row.serviceCategoryColor);
					var dateth= new Date(dateArray[1]);
					var daterow = new Date(row.orderDate);
						if (row.serviceCategoryColor!=undefined && dateth.getTime() ==daterow.getTime()){
							//alert(row.serviceCategoryColor+">>>>>>");
							return {css:{"background-color":row.serviceCategoryColor}};
						}
						else{
							return {css:{"background-color":""}};
						}
						},
						formatter:function( value,row,index ){
							var dateth= new Date(dateArray[3]);
							var daterow = new Date(row.orderDate);
							if(dateth.getTime() ==daterow.getTime()){
								if(row.serviceCategoryColor!=undefined){
									return "<button type='button'class='clicktd'>"+row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo+"</button>";
								
								}else{
									return row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo;
								
								}
								
							
							}
						},
						 events: 'operateEvents'
		
		 },
			{
				field: 'serviceCategoryColor',// 返回json数据中的name
				title: dateArray[2], // 表格表头显示文字
				align: 'center', // 左右居中
				valign: 'middle', // 上下居中
				cellStyle:function(value,row,index){
					//alert(row.serviceCategoryColor);
					var dateth= new Date(dateArray[2]);
					var daterow = new Date(row.orderDate);
						if (row.serviceCategoryColor!=undefined && dateth.getTime() ==daterow.getTime()){
							//alert(row.serviceCategoryColor+">>>>>>");
							return {css:{"background-color":row.serviceCategoryColor}};
						}
						else{
							return {css:{"background-color":""}};
						}
						},
					
						formatter:function( value,row,index ){
							var dateth= new Date(dateArray[3]);
							var daterow = new Date(row.orderDate);
							if(dateth.getTime() ==daterow.getTime()){
								if(row.serviceCategoryColor!=undefined){
									return "<button type='button'class='clicktd'>"+row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo+"</button>";
								
								}else{
									return row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo;
									
								}
								
							
							}
						},
						 events: 'operateEvents'
		
		 },
			{
				field: 'serviceCategoryColor',// 返回json数据中的name
				title: dateArray[3], // 表格表头显示文字
				align: 'center', // 左右居中
				valign: 'middle', // 上下居中
				cellStyle:function(value,row,index){
					//alert(row.serviceCategoryColor);
					var dateth= new Date(dateArray[3]);
					var daterow = new Date(row.orderDate);
						if (row.serviceCategoryColor!=undefined && dateth.getTime() ==daterow.getTime()){
							//alert(row.serviceCategoryColor+">>>>>>");
							return {css:{"background-color":row.serviceCategoryColor}};
						}
						else{
							return {css:{"background-color":""}};
						}
						},
					
						formatter:function( value,row,index ){
							var dateth= new Date(dateArray[3]);
							var daterow = new Date(row.orderDate);
							if(dateth.getTime() ==daterow.getTime()){
								if(row.serviceCategoryColor!=undefined){
									return "<button type='button'class='clicktd'>"+row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo+"</button>";
								
								}else{
									return row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo;
								
								}
								
							
							}
						},
						 events: 'operateEvents'
		 },
			{
				field: 'serviceCategoryColor',// 返回json数据中的name
				title: dateArray[4], // 表格表头显示文字
				align: 'center', // 左右居中
				valign: 'middle', // 上下居中
				cellStyle:function(value,row,index){
					//alert(row.serviceCategoryColor);
					var dateth= new Date(dateArray[4]);
					var daterow = new Date(row.orderDate);
						if (row.serviceCategoryColor!=undefined && dateth.getTime() ==daterow.getTime()){
							
							return {css:{"background-color":row.serviceCategoryColor}};
						}
						else{
							return {css:{"background-color":""}};
						}
						},
					
						formatter:function( value,row,index ){
							var dateth= new Date(dateArray[4]);
							var daterow = new Date(row.orderDate);
							if(dateth.getTime() ==daterow.getTime()){
								if(row.serviceCategoryColor!=undefined){
									return "<button type='button'class='clicktd'>"+row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo+"</button>";
							
								}else{
									return row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo;
								
								}
								
							
							}
						},
						 events: 'operateEvents'
		
		 },
			{
				field: 'serviceCategoryColor',// 返回json数据中的name
				title: dateArray[5], // 表格表头显示文字
				align: 'center', // 左右居中
				valign: 'middle', // 上下居中
				cellStyle:function(value,row,index){
					//alert(row.serviceCategoryColor);
					var dateth= new Date(dateArray[5]);
					var daterow = new Date(row.orderDate);
						if (row.serviceCategoryColor!=undefined && dateth.getTime() ==daterow.getTime()){
							return {css:{"background-color":row.serviceCategoryColor}};
							
						}
						else{
							return {css:{"background-color":""}};
						}
						},
						formatter:function( value,row,index ){
							//var dateth= new Date(dateArray[5]);
							//var daterow = new Date(row.orderDate);
							//if(dateth.getTime() ==daterow.getTime()){
							//	if(row.serviceCategoryColor!=undefined){
									return "<button type='button'class='fff'>cccc</button>";
								
							//	}else{
							//		return row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo;
								
							//	}
								
							
							//}
						},
						events: 'operateEvents'
		
		 },
			{
				field: 'serviceCategoryColor',// 返回json数据中的name
				title: dateArray[6], // 表格表头显示文字
				align: 'center', // 左右居中
				valign: 'middle', // 上下居中
				cellStyle:function(value,row,index){
					//alert(row.serviceCategoryColor);
					var dateth= new Date(dateArray[6]);
					var daterow = new Date(row.orderDate);
						if (row.serviceCategoryColor!=undefined && dateth.getTime() ==daterow.getTime()){
						
							return {css:{"background-color":row.serviceCategoryColor}};
						}
						else{
							return {css:{"background-color":""}};
						}
						},
					
						formatter:function( value,row,index ){
							var dateth= new Date(dateArray[6]);
							var daterow = new Date(row.orderDate);
							if(dateth.getTime() ==daterow.getTime()){
								if(row.serviceCategoryColor!=undefined){
									return "<button type='button'class='clicktd'>"+row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo+"</button>";
								
								}else{
									return row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo;
									
								}
								
							
							}
						},
						 events: 'operateEvents'
		
		 },
			 
			 ] ,
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
				
					// 预约日期
					orderDate:$("#orderDate").val(),
				
				}
		};
//	$("#table").bootstrapTable('destroy');
//	$("#table").bootstrapTable({ // 对应table标签的id
//		method : "post",
//		url: urlstorage+"task/taskPool", // 获取表格数据的url
//		cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
//		striped: true,  //表格显示条纹，默认为false
//		pagination: false, // 在表格底部显示分页组件，默认false
//		//pageSize: 10, // 页面数据条数
//		//pageNumber: 1, // 首页页码
//		sidePagination: 'server', // 设置为服务器端分页
//		//showHeader : true,
//		queryParams:queryParams,
//	     //设置header
//		ajaxOptions: {
//			beforeSend: function(request) {
//			  request.setRequestHeader("Authorization", token);
//			}	
//		},
//		// 返回数据格式处理（如果后台返回数据格式不是rows）
////		 responseHandler: function(res) {
////			return {
////			"rows": res.data,//数据
////		 };
////		},
//		columns : [
//			{
//				field: 'id', // 返回json数据中的name
//				title: 'id', // 表格表头显示文字
//				align: 'center', // 左右居中
//				valign: 'middle', // 上下居中
//				hidden:true,
//				visible: false,
//			},
//			{
//				field: 'orderNo', // 返回json数据中的name
//				title: 'orderNo', // 表格表头显示文字
//				align: 'center', // 左右居中
//				valign: 'middle', // 上下居中
//				hidden:true,
//				visible: false,
//			},
//			{
//				field: 'orderAddr', // 返回json数据中的name
//				title: '地点', // 表格表头显示文字
//				align: 'center', // 左右居中
//				valign: 'middle', // 上下居中
//				//hidden:true,
//				//visible: false,
//			},
//			{
//				field: 'serviceCategoryColor',// 返回json数据中的name
//				title: dateArray[0], // 表格表头显示文字
//				align: 'center', // 左右居中
//				valign: 'middle', // 上下居中
//				cellStyle:function(value,row,index){
//					//alert(row.serviceCategoryColor);
//					var dateth= new Date(dateArray[0]);
//					var daterow = new Date(row.orderDate);
//						if (row.serviceCategoryColor!=undefined && dateth.getTime() ==daterow.getTime()){
//							//alert(row.serviceCategoryColor+">>>>>>");
//							return {css:{"background-color":row.serviceCategoryColor}};
//						}
//						else{
//							return {css:{"background-color":""}};
//						}
//						},
//					
//						formatter:function( value,row,index ){
//							var dateth= new Date(dateArray[0]);
//							var daterow = new Date(row.orderDate);
//							if(dateth.getTime() ==daterow.getTime()){
//								if(row.serviceCategoryColor!=undefined){
//									return "<button type='button'class='clicktd'>"+row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo+"</button>";
//								
//								}else{
//									return row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo;
//								
//								}
//								
//							
//							}else{
//								return "";
//							}
//						},
//						 events: 'operateEvents'
//		
//		 },
//			{
//				field: 'serviceCategoryColor',// 返回json数据中的name
//				title: dateArray[1], // 表格表头显示文字
//				align: 'center', // 左右居中
//				valign: 'middle', // 上下居中
//				cellStyle:function(value,row,index){
//					//alert(row.serviceCategoryColor);
//					var dateth= new Date(dateArray[1]);
//					var daterow = new Date(row.orderDate);
//						if (row.serviceCategoryColor!=undefined && dateth.getTime() ==daterow.getTime()){
//							//alert(row.serviceCategoryColor+">>>>>>");
//							return {css:{"background-color":row.serviceCategoryColor}};
//						}
//						else{
//							return {css:{"background-color":""}};
//						}
//						},
//						formatter:function( value,row,index ){
//							var dateth= new Date(dateArray[3]);
//							var daterow = new Date(row.orderDate);
//							if(dateth.getTime() ==daterow.getTime()){
//								if(row.serviceCategoryColor!=undefined){
//									return "<button type='button'class='clicktd'>"+row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo+"</button>";
//								
//								}else{
//									return row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo;
//								
//								}
//								
//							
//							}
//						},
//						 events: 'operateEvents'
//		
//		 },
//			{
//				field: 'serviceCategoryColor',// 返回json数据中的name
//				title: dateArray[2], // 表格表头显示文字
//				align: 'center', // 左右居中
//				valign: 'middle', // 上下居中
//				cellStyle:function(value,row,index){
//					//alert(row.serviceCategoryColor);
//					var dateth= new Date(dateArray[2]);
//					var daterow = new Date(row.orderDate);
//						if (row.serviceCategoryColor!=undefined && dateth.getTime() ==daterow.getTime()){
//							//alert(row.serviceCategoryColor+">>>>>>");
//							return {css:{"background-color":row.serviceCategoryColor}};
//						}
//						else{
//							return {css:{"background-color":""}};
//						}
//						},
//					
//						formatter:function( value,row,index ){
//							var dateth= new Date(dateArray[3]);
//							var daterow = new Date(row.orderDate);
//							if(dateth.getTime() ==daterow.getTime()){
//								if(row.serviceCategoryColor!=undefined){
//									return "<button type='button'class='clicktd'>"+row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo+"</button>";
//								
//								}else{
//									return row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo;
//									
//								}
//								
//							
//							}
//						},
//						 events: 'operateEvents'
//		
//		 },
//			{
//				field: 'serviceCategoryColor',// 返回json数据中的name
//				title: dateArray[3], // 表格表头显示文字
//				align: 'center', // 左右居中
//				valign: 'middle', // 上下居中
//				cellStyle:function(value,row,index){
//					//alert(row.serviceCategoryColor);
//					var dateth= new Date(dateArray[3]);
//					var daterow = new Date(row.orderDate);
//						if (row.serviceCategoryColor!=undefined && dateth.getTime() ==daterow.getTime()){
//							//alert(row.serviceCategoryColor+">>>>>>");
//							return {css:{"background-color":row.serviceCategoryColor}};
//						}
//						else{
//							return {css:{"background-color":""}};
//						}
//						},
//					
//						formatter:function( value,row,index ){
//							var dateth= new Date(dateArray[3]);
//							var daterow = new Date(row.orderDate);
//							if(dateth.getTime() ==daterow.getTime()){
//								if(row.serviceCategoryColor!=undefined){
//									return "<button type='button'class='clicktd'>"+row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo+"</button>";
//								
//								}else{
//									return row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo;
//								
//								}
//								
//							
//							}
//						},
//						 events: 'operateEvents'
//		 },
//			{
//				field: 'serviceCategoryColor',// 返回json数据中的name
//				title: dateArray[4], // 表格表头显示文字
//				align: 'center', // 左右居中
//				valign: 'middle', // 上下居中
//				cellStyle:function(value,row,index){
//					//alert(row.serviceCategoryColor);
//					var dateth= new Date(dateArray[4]);
//					var daterow = new Date(row.orderDate);
//						if (row.serviceCategoryColor!=undefined && dateth.getTime() ==daterow.getTime()){
//							
//							return {css:{"background-color":row.serviceCategoryColor}};
//						}
//						else{
//							return {css:{"background-color":""}};
//						}
//						},
//					
//						formatter:function( value,row,index ){
//							var dateth= new Date(dateArray[4]);
//							var daterow = new Date(row.orderDate);
//							if(dateth.getTime() ==daterow.getTime()){
//								if(row.serviceCategoryColor!=undefined){
//									return "<button type='button'class='clicktd'>"+row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo+"</button>";
//							
//								}else{
//									return row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo;
//								
//								}
//								
//							
//							}
//						},
//						 events: 'operateEvents'
//		
//		 },
//			{
//				field: 'serviceCategoryColor',// 返回json数据中的name
//				title: dateArray[5], // 表格表头显示文字
//				align: 'center', // 左右居中
//				valign: 'middle', // 上下居中
//				cellStyle:function(value,row,index){
//					//alert(row.serviceCategoryColor);
//					var dateth= new Date(dateArray[5]);
//					var daterow = new Date(row.orderDate);
//						if (row.serviceCategoryColor!=undefined && dateth.getTime() ==daterow.getTime()){
//							return {css:{"background-color":row.serviceCategoryColor}};
//							
//						}
//						else{
//							return {css:{"background-color":""}};
//						}
//						},
//						formatter:function( value,row,index ){
//							//var dateth= new Date(dateArray[5]);
//							//var daterow = new Date(row.orderDate);
//							//if(dateth.getTime() ==daterow.getTime()){
//							//	if(row.serviceCategoryColor!=undefined){
//									return "<button type='button'class='fff'>cccc</button>";
//								
//							//	}else{
//							//		return row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo;
//								
//							//	}
//								
//							
//							//}
//						},
//						events: 'operateEvents'
//		
//		 },
//			{
//				field: 'serviceCategoryColor',// 返回json数据中的name
//				title: dateArray[6], // 表格表头显示文字
//				align: 'center', // 左右居中
//				valign: 'middle', // 上下居中
//				cellStyle:function(value,row,index){
//					//alert(row.serviceCategoryColor);
//					var dateth= new Date(dateArray[6]);
//					var daterow = new Date(row.orderDate);
//						if (row.serviceCategoryColor!=undefined && dateth.getTime() ==daterow.getTime()){
//						
//							return {css:{"background-color":row.serviceCategoryColor}};
//						}
//						else{
//							return {css:{"background-color":""}};
//						}
//						},
//					
//						formatter:function( value,row,index ){
//							var dateth= new Date(dateArray[6]);
//							var daterow = new Date(row.orderDate);
//							if(dateth.getTime() ==daterow.getTime()){
//								if(row.serviceCategoryColor!=undefined){
//									return "<button type='button'class='clicktd'>"+row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo+"</button>";
//								
//								}else{
//									return row.orderBeginTime+"-"+row.orderEndTime+"<br>"+row.age+"岁"+row.sex+"<br>"+row.orderNo;
//									
//								}
//								
//							
//							}
//						},
//						 events: 'operateEvents'
//		
//		 },
//			 
//			 ] ,
//
//				onLoadSuccess: function(data ){
//				    //加载成功时执行
//					console.info("加载成功");
//				},
//				onLoadError: function(){  
//					//加载失败时执行
//				    console.info("加载数据失败");
//				}
//			
//		
//	});
//	//表格参数设置
//	queryParams: function queryParams(params) {// 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
//
//		   return {
//				
//					// 预约日期
//					orderDate:$("#orderDate").val(),
//				
//				}
//		};
//	}
//	
	window.operateEvents = {
			//
			'click .fff': function (e, value, row, index) {
				window.location.href = "service-person-choose.html?orderNo=" + row.orderNo ;
			
			},
		};
	$(document).on("click",".setok",function(){
		var olddate=localStorage.getItem("orderDate");
	var newdate=$("#orderDate").val();
	var newdatefam=new Date(Date.parse(newdate.replace("-", "/")));
	if(olddate!=newdate){
		dateArray.splice(0,dateArray.length);//清空数组
		newdatefam.setDate(newdatefam.getDate());
		dateArray.push(timeFormatter(newdatefam));
		 for(var i = 0; i < 6; i++){
			 newdatefam.setDate(newdatefam.getDate() +1);
			 dateArray.push(timeFormatter(newdatefam));
			// alert(dateArray[i])
		};
		// alert(">>>>>>>>>>")
		initData();
	}
	
	});
	
//	}
//	$("input.search").focus(function(){
//        $(this).attr("data-date",$(this).val()); //将当前值存入自定义属性
//    }).blur(function(){
//        var oldVal=($(this).attr("data-date")); //获取原值
//        alert(oldVal+"oldVal");
//        var newVal=($(this).val()); //获取当前值
//        //var newVal=($(this).text()); //获取当前值
//        alert(newVal+"newVal");
//        if (oldVal!=newVal)
//        {
//        	  initData();
//        	  localStorage.setItem("orderDate",$("#orderDate").val());
//        }
//    });
//选择日期
//$(".search").change(function(){
//	  initData();
//	  localStorage.setItem("orderDate",$("#orderDate").val());
//	});
//window.operateEvents = {
//		
//		// 点击编辑
//		'click .edit': function (e, value, row, index) {
//		window.location.href = "service-task-edit.html?id=" + row.id ;
//		},
//	
//		
//	};
})




