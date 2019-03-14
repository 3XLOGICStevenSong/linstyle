$(function(){
	var pid=getCookie("pid");
	var doctorId=window.location.href.substring(window.location.href.lastIndexOf("=")+1,window.location.href.length);
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
	};
	
	$.ajax({
		type:"get",
		url:"/MedicalUnicom/DoctorComment.do?doGetDoctorCommentList",
		data:{
			doctorId:doctorId
		},
		aync:false,
		dataType:"json",
		success:function(result){
			/*
			"name":"张******",
			"grade":"4.0",
			"createTime":"2016-10-14 11:52",
			"content":"",
			*/
			var CmtList=result.commentList;
			if(CmtList != null){
				var name="";
				var grade="";
				var createTime="";
				var content="";

				var List=$(".List").html();
				
				var Item=new Array();
				
				for(i=0;i<CmtList.length;i++){
					name=CmtList[i].name;
					grade=CmtList[i].grade;
					createTime=CmtList[i].createTime;
					content=CmtList[i].content;

					Item[i]=List;
					
					for(Val=0;Val<Item.length;Val++){
						Item[Val]=Item[Val].replace("{$name}",name);
						Item[Val]=Item[Val].replace("{$grade}",grade);
						Item[Val]=Item[Val].replace("{$createTime}",createTime);
						Item[Val]=Item[Val].replace("{$content}",content);
					}
					

					$("#List").html(Item);
					$(".List").remove();
					
				}
			}else{
				$("#List").html("<li style='text-align:center;padding:0.5rem 0;'>暂无评价</li>");
			}

		}
	})
})