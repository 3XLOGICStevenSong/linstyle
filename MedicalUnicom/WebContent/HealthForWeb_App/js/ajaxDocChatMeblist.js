$(function(){
	function setCookie(name,value){
			/*
	    var Days = 30;
	    var exp = new Date();
	    exp.setTime(exp.getTime() + Days*24*60*60*1000);*/
	    document.cookie = name + "="+ escape (value) ;/*+ ";expires=" + exp.toGMTString()*/
	}
	function getCookie(name){

	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

	    if(arr=document.cookie.match(reg))
	 
	        return (arr[2]);
	    else
	        return null;
	}
	
	var pid=getCookie("pid");
	
	function getdoGetMembers( _pageNum , _pageSize){
		$.ajax({
			type:"get",
			url:"/MedicalUnicom/Appoint.do?doGetMembers",
			data:{
				doctorId:pid,
				pageNum:_pageNum,
				pageSize:_pageSize
			},
			aync: false,
			dataType:"json",
			success:function(result){
				var date=new Date();
				var PatList=result.patientClientDTO;
				if(PatList == null){
					alert("没有更多");
					return false;
				}
				var List="";
	
				for(i=0;i<PatList.length;i++){
					/*
					"patientId"
					"name"
					"birth"
					"sex"
					"patientPic"
					"tel"
					*/
					
					var patientId=PatList[i].patientId;
					var tel=PatList[i].tel;
					var patientPic=PatList[i].patientPic;
					var name=PatList[i].name;					
					var birth=PatList[i].birth;
					if(birth != null){
						birth=date.getFullYear() - parseInt(birth.substring(0,4))
					}else{
						birth="";
					};
					var sex=PatList[i].sex;
					if(sex != null){
						if(sex == 0){
							sex= "男";
						}else if(sex == 1){
							sex= "女";
						};
					}else{
						sex ="";
					};
	
					List+="<li data='"+patientId+"'><div class='info'><strong class='UserImg'><img src='"+patientPic+"'/></strong><ul><li class='frt'><strong clas='name'>"+name+"</strong></li><li><strong class='Sex'>性别: "+sex+"</strong><strong class='Age'>年龄: "+birth+"</strong></li></ul></div></li>";
				}
				$("#List").html("");
				$("#List").append(List);
				
				$(".UserImg img").on("error",function(){
					$(this).attr("src","img/defaultpatient.png")
				})
				
				$("#List").children().click(function(){
					window.location.href="#";
				})
			},
		})

	}
	
	var _pageNum=0;
	var _pageSize=10;
	
	getdoGetMembers( _pageNum , _pageSize);
	
	$("#MoreBtn").click(function(){
		_pageNum=_pageNum+10;
		getdoGetMembers( _pageNum , _pageSize)
	})
	
})