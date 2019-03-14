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
	
	var link=window.location.href;
	var keyVal=link.split("&");
	
	var doctorId="";
	
	var fee="";
	for(i=0;i<keyVal.length;i++){
		if(keyVal[i].indexOf("doctorId") != -1 ){
			doctorId=keyVal[i].substring(keyVal[i].lastIndexOf("=")+1,keyVal[i].length);
		}else if(keyVal[i].indexOf("ToFee") != -1 ){
			fee=keyVal[i].substring(keyVal[i].lastIndexOf("=")+1,keyVal[i].length);
		}
	}
	
	if(doctorId == "Emergency"){
		var TotalPrice=fee;
		var content=$(".info").html();
		var Item=new Array();
		for(i=0;i<$(".info").length;i++){
			Item[i]=content;
			for(val=0;val<Item.length;val++){
				Item[val]=Item[val].replace("{$total_price}",TotalPrice+"元")
			}
		}
		$(".infoBox").html("");
		$(".infoBox").html(Item);
	}else{
		var pack=window.location.href.substring(window.location.href.indexOf("=")+1,window.location.href.indexOf("&"));
		$.ajax({
			type:"get",
			url:"/MedicalUnicom/Doctor.do?doGetPackageByDoctor",
			data:{
				doctorId:doctorId,
			},
			aync:false,
			dataType:"json",
			success:function(result){
				var typ=pack;
				var Package=result.packagrInfoList
				
				var packageId=Package[typ].packageId;
				var count=Package[typ].count;
				var telCount=Package[typ].telCount;
				var total=Package[typ].total;
				var effectTime=Package[typ].effectTime;
				var type=Package[typ].type;
				
				var content=$(".info"+pack).html();
				var Item=new Array();
				
				for(i=0;i<$(".info"+pack).length;i++){
					Item[i]=content;
					for(val=0;val<Item.length;val++){
						Item[val]=Item[val].replace("{$packageId}",packageId);
						Item[val]=Item[val].replace("{$count}",count);
						Item[val]=Item[val].replace("{$telCount}",telCount);
						Item[val]=Item[val].replace("{$total}",total+"元");
						Item[val]=Item[val].replace("{$effectTime}",effectTime);
						Item[val]=Item[val].replace("{$type}",type);
					}
				}
				$(".infoBox").html("");
				$(".infoBox").html(Item);
			}
		})
		/*
		if(pack == 0){
			$.ajax({
				type:"get",
				url:"/MedicalUnicom/Doctor.do?doGetPackageByDoctor",
				data:{
					doctorId:doctorId,
				},
				aync:false,
				dataType:"json",
				success:function(result){
					var typ=window.location.href.substring(window.location.href.indexOf("=")+1,window.location.href.lastIndexOf("&"));
					var Package=result.packagrInfoList
					
					var packageId=Package[typ].packageId;
					var count=Package[typ].count;
					var telCount=Package[typ].telCount;
					var total=Package[typ].total;
					var effectTime=Package[typ].effectTime;
					var type=Package[typ].type;
					
					var content=$(".info0").html();
					var Item0=new Array();
					
					for(i=0;i<$(".info0").length;i++){
						Item0[i]=content;
						for(val=0;val<Item0.length;val++){
							Item0[val]=Item0[val].replace("{$packageId}",packageId);
							Item0[val]=Item0[val].replace("{$count}",count);
							Item0[val]=Item0[val].replace("{$telCount}",telCount);
							Item0[val]=Item0[val].replace("{$total}",total+".0"+"元");
							Item0[val]=Item0[val].replace("{$effectTime}",effectTime);
							Item0[val]=Item0[val].replace("{$type}",type);
						}
					}
					$(".infoBox").html("");
					$(".infoBox").html(Item0);
				}
			})
		}
		if(pack == 1){
			$.ajax({
				type:"get",
				url:"/MedicalUnicom/Doctor.do?doGetPackageByDoctor",
				data:{
					doctorId:doctorId,
				},
				aync:false,
				dataType:"json",
				success:function(result){
					var typ=window.location.href.substring(window.location.href.indexOf("=")+1,window.location.href.lastIndexOf("&"));
					var Package=result.packagrInfoList
					
					var packageId=Package[typ].packageId;
					var count=Package[typ].count;
					var telCount=Package[typ].telCount;
					var total=Package[typ].total;
					var effectTime=Package[typ].effectTime;
					var type=Package[typ].type;
					
					var content=$(".info1").html();
					var Item1=new Array();
					
					for(i=0;i<$(".info1").length;i++){
						Item1[i]=content;
						for(val=0;val<Item1.length;val++){
							Item1[val]=Item1[val].replace("{$packageId}",packageId);
							Item1[val]=Item1[val].replace("{$count}",count);
							Item1[val]=Item1[val].replace("{$telCount}",telCount);
							Item1[val]=Item1[val].replace("{$total}",total+".0"+"元");
							Item1[val]=Item1[val].replace("{$effectTime}",effectTime);
							Item1[val]=Item1[val].replace("{$type}",type);
						}
					}
					$(".infoBox").html("");
					$(".infoBox").html(Item1);
				}
			})
		}
		if(pack == 2){
			$.ajax({
				type:"get",
				url:"/MedicalUnicom/Doctor.do?doGetPackageByDoctor",
				data:{
					doctorId:doctorId,
				},
				aync:false,
				dataType:"json",
				success:function(result){
					var typ=window.location.href.substring(window.location.href.indexOf("=")+1,window.location.href.lastIndexOf("&"));
					var Package=result.packagrInfoList
					
					var packageId=Package[typ].packageId;
					var count=Package[typ].count;
					var telCount=Package[typ].telCount;
					var total=Package[typ].total+".0";
					var effectTime=Package[typ].effectTime;
					var type=Package[typ].type;
					
					var content=$(".info2").html();
					var Item2=new Array();
					
					for(i=0;i<$(".info2").length;i++){
						Item2[i]=content;
						for(val=0;val<Item2.length;val++){
							Item2[val]=Item2[val].replace("{$packageId}",packageId);
							Item2[val]=Item2[val].replace("{$count}",count);
							Item2[val]=Item2[val].replace("{$telCount}",telCount);
							Item2[val]=Item2[val].replace("{$total}",total+".0"+"元");
							Item2[val]=Item2[val].replace("{$effectTime}",effectTime);
							Item2[val]=Item2[val].replace("{$type}",type);
						}
					}
					$(".infoBox").html("");
					$(".infoBox").html(Item2);
				}
			})
		}*/
	}

	$(".icon_check").click(function(){
		$(this).toggleClass("on");
	})
	
})