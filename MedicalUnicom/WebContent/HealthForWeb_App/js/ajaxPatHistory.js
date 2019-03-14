$(function(){
	var pid="";
	
	var link=window.location.href;
	
	if(pid == "" ){
		pid=getCookie("pid");
		if(pid == ""){
			var CookieID=link.substring(link.lastIndexOf("="),+1,link.length);
			pid=CookieID;
		}
	}

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

	function getHistory(pid,_pageNum,_pageSize){
		
		$.ajax({
			type:"get",
			url:"/MedicalUnicom/Appoint.do?doGetRecordsInfo",
			data:{
				patientId:pid,
				pageNum:_pageNum,
				pageSize:_pageSize
			},
			aync:false,
			dataType:"json",
			success:function(result){
				var DTO=result.recordsInfo;
				
				if(DTO == null){
					$(".List").hide();
					var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>没有了</font></div></div>")
					$("body").append(Mess);
					setTimeout(function(){
						$(".Mess").remove();
					},1500)
					//alert("没有更多");
					
				}else{

					var content=$("#List").html();
		
					var Item=new Array();
					
					for(i=0;i<DTO.length;i++){	
						var dcName=DTO[i].dcName;
						var doctorId=DTO[i].doctorId;
						var recordsId=DTO[i].recordsId;
						var recordsType=DTO[i].recordsType;
						if(recordsType == 0){
							recordsType = "视频";
						}else if(recordsType == 1){
							recordsType = "电话";
						}
						var departmentName=DTO[i].departmentName;
						var hospitalName=DTO[i].hospitalName;
						
						var appointTime=DTO[i].appointTime;
						if(appointTime != null){
							appointTime = "问诊时间: "+appointTime;
						}else{
							appointTime = "";
						}
						var createTime=DTO[i].createTime;
	
						Item[i]=content;
	
						Item[i]=Item[i].replace("{$dcName}",dcName);
						Item[i]=Item[i].replace("{$doctorId}",doctorId);
						Item[i]=Item[i].replace("{$depName}",departmentName);
						Item[i]=Item[i].replace("{$hpName}",hospitalName);
						Item[i]=Item[i].replace("{$appointTime}",appointTime);
						Item[i]=Item[i].replace("{$createTime}",createTime);
						Item[i]=Item[i].replace("{$recordsId}",recordsId);
						Item[i]=Item[i].replace("{$recordsType}",recordsType);
					}
				}

				$(".List").append(Item);
				
				$(".List li").click(function(){
					//setCookie("payStatus",$(this).attr("class"));
					window.location.href="Pat_CaseHistory.html?recordsId="+$(this).attr("data");
				})
			}
		})
	}
	
	var _pageNum=0;
	var _pageSize=10;
	
	getHistory(pid,_pageNum,_pageSize);
	
	$("#MoreBtn").click(function(){
		_pageNum=_pageNum+1;
		getHistory(pid,_pageNum,_pageSize);
	})
	
})