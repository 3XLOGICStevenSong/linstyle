
$(document).ready(function(){
	//获取token AND url
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	if(urlstorage==null){
		 window.location.href="login.html";	
	}
	//var sideFlag=0;
//设置左菜单展开项 1 服务任务2基本信息3权限管理4系统设置	
	//alert(window.sidebar_menu_id+"init")
if(window.sidebar_menu_id == undefined){
	sidebar_menu_id=1;
	//sideFlag=1;
}
//初始化菜单
		$.ajax({
			type : "post",
			url : urlstorage+"user/menus",
			datatype : "json",
			contentType : "application/json;charset=utf-8",
			async : false,
			 beforeSend: function(request) {
 				request.setRequestHeader("Authorization", token);
 				},
			success : function(result,status,httpResponse) {
				if (result.statusCode == "200") {
					var token=httpResponse.getResponseHeader('Authorization');
					if(token!=""&&token!=null){
						localStorage.setItem("token", token); 
					}
					//当前用户
					$("#currentUser").text("当前用户："+result.data.userNumber);
					//菜单列表
					var menuList=result.data.menuList;
					var list="";
					if(menuList.length>0){
						for(i=0;i<menuList.length;i++){
//							alert(window.sidebar_menu_id+">>>>"+sideFlag);
//							if(sidebar_menu_id==1&&sideFlag==1 ){
//								
//								window.sidebar_menu_id=menuList[0].id;
//								alert(window.sidebar_menu_id);
//							}
							//服务任务列表
							if(window.sidebar_menu_id==menuList[i].id){
								//alert("menu_id+menuList[i].id"+window.sidebar_menu_id+"...."+menuList[i].id)
								list+="<div class='subNav currentDd currentDt'>";
							}else{
								list+="<div class='subNav'>";
								//alert("menu id"+window.sidebar_menu_id+"...."+menuList[i].id)
							}
							//服务任务图标
							if(menuList[i].id==1){
								list+="<i class='glyphicon glyphicon-list-alt'>&nbsp;"+menuList[i].name+"</i>  <i class='right_icon'></i></div>"
							//服务任务列表 子菜单
								if(window.sidebar_menu_id==menuList[i].id){
								list+="<ul class='navContent ' style='display:block'>";}
								else{
									list+="<ul class='navContent ' >";	
								}
								var children=menuList[i].children;
								for(j=0;j<children.length;j++){
									list+="<li class='linkPage' data='"+children[j].url+"'><span ><i class='sub_left_icon'></i>"+children[j].name+"</span></li>";
								}
								list+="</ul>";
							}else if(menuList[i].id==2){//基本信息管理
								list+="<i class='glyphicon glyphicon-th-large'>&nbsp;"+menuList[i].name+"</i>  <i class='right_icon'></i></div>"
							//基本信息子菜单
								if(window.sidebar_menu_id==menuList[i].id){
									list+="<ul class='navContent ' style='display:block'>";}
									else{
										list+="<ul class='navContent ' >";	
									}
								var children=menuList[i].children;
								for(j=0;j<children.length;j++){
									list+="<li  class='linkPage' data='"+children[j].url+"'><span  ><i class='sub_left_icon'></i>"+children[j].name+"</span></li>"	
								}
								list+="</ul>"
							}else if(menuList[i].id==3){//权限管理
								
								list+="<i class='glyphicon glyphicon-lock'>&nbsp;"+menuList[i].name+"</i>  <i class='right_icon'></i></div>"
								//权限管理子菜单
								if(window.sidebar_menu_id==menuList[i].id){
									list+="<ul class='navContent ' style='display:block'>";}
									else{
										list+="<ul class='navContent ' >";	
									}
									var children=menuList[i].children;
									for(j=0;j<children.length;j++){
										list+="<li class='linkPage' data='"+children[j].url+"'><span ><i class='sub_left_icon'></i>"+children[j].name+"</span></li>"	
									}
									list+="</ul>"
							}else if(menuList[i].id==4){//系统设置
								
								list+="<i class='glyphicon glyphicon-cog'>&nbsp;"+menuList[i].name+"</i>  <i class='right_icon'></i></div>"
								//系统设置子菜单
								if(window.sidebar_menu_id==menuList[i].id){
									list+="<ul class='navContent ' style='display:block'>";}
									else{
										list+="<ul class='navContent ' >";	
									}
									var children=menuList[i].children;
									for(j=0;j<children.length;j++){
										list+="<li class='linkPage' data='"+children[j].url+"' ><span ><i class='sub_left_icon'></i>"+children[j].name+"</span></li>"	
									}
									list+="</ul>"
							}
						}	
						//拼接菜单列表
						$("#subNavBox").empty();
						$("#subNavBox").append(list);
					}else{
						$("#subNavBox").empty();
						$("#subNavBox").append("您还没有任何权限请联系管理员！");
					}
					
				}else if(result.statusCode == "401"){
					 window.location.href="login.html";
				}
			},
			
			 error : function(xhr,textStatus,errorThrown){
				
				 if(xhr.status == 401){
					 window.location.href="login.html";
				 }
		}
		})
		//点解菜单项目
		$(".linkPage").on("click",function(){
			//wangbo 2019/01/31 start
			//当从左菜单进入的时候，清掉session保存的页码信息和查询条件
			//后台用户信息管理
			sessionStorage.removeItem('back_pn');
			sessionStorage.removeItem('bk_Srh_Cnds');
			//客户信息管理
			sessionStorage.removeItem('customer_pn');
			sessionStorage.removeItem('cus_Manage_Cnds');
			//已发布服务方案管理
			sessionStorage.removeItem('publish_pn');
			sessionStorage.removeItem('pub_Plan_Cnds');
			//服务人员信息管理
			sessionStorage.removeItem('server_pn');
			sessionStorage.removeItem('ser_Per_Cnds');
			//未发布服务方案管理
			sessionStorage.removeItem('unpublish_pn');
			sessionStorage.removeItem('unpub_Plan_Cnds');
			//服务任务管理
	    	sessionStorage.removeItem('task_Man_Cnds');
	    	sessionStorage.removeItem('task_pn');
	    	//未分配任务管理
	    	sessionStorage.removeItem('undis_Manage_Cnds');
	    	sessionStorage.removeItem('undistribute_pn');
	    	//权限角色管理
	    	sessionStorage.removeItem('role_pn');
			//wangbo 2019/01/31 end
			var contentPage=$(this).attr("data");
			//alert(contentPage);
			window.location.href=contentPage;
			 return false;
	})	

})