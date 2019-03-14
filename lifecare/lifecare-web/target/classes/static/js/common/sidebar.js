
$(document).ready(function(){
	//获取token AND url
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
//设置左菜单展开项 1 服务任务2基本信息3权限管理4系统设置	
if(window.sidebar_menu_id == undefined){
	sidebar_menu_id=1;
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
							}else if(menuList[i].id==3){//基本信息管理
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
							}else if(menuList[i].id==4){//权限管理
								
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
							}else if(menuList[i].id==5){//系统设置
								
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
			var contentPage=$(this).attr("data");
			//alert(contentPage);
			window.location.href=contentPage;
			 return false;
	})	

})