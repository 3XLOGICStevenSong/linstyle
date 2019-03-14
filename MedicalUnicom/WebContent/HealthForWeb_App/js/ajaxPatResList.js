$(function(){
	var link=window.location.href;
	var param=link.split("&");
	var _symptomId="";
	var _departmentId="";
	var patientId="";
	/*
	1.科室ID：departmentId
	2.症状ID：symptomId
	3.排序方式： methodFlg:0职称 1价格 2评价
	4.升序或降序:sortType：0升序 1降序
	*/
	var _departmentId="";
	var _symptomId="";
	var _methodFlg=0;
	var _sortType=1;
	var _pageNum=0;
	var _pageSize=10;
	var AddDoc="";
	var Symp="";
	
	for(i=0;i<param.length;i++){
		if(param[i].indexOf("patientId") != -1 ){
			patientId=param[i].substring(param[i].lastIndexOf("=")+1,param[i].length);
		}
	}
	
	for(i=0;i<param.length;i++){
		if(param[i].indexOf("_symptomId") != -1 ){
			_symptomId=param[i].substring(param[i].lastIndexOf("=")+1,param[i].length);
			Symp="sym";
			getDocList(_symptomId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc);
		}else if(param[i].indexOf("_departmentId") != -1 ){
			_departmentId=param[i].substring(param[i].lastIndexOf("=")+1,param[i].length);
			Symp="Dep";
			getDocListDep(_departmentId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc);
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
	};

	
	function getDocList(_symptomId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc){
		$.ajax({
			type:"get",
			url:"/MedicalUnicom/InqueryView.do?doGetInqueryList",
			data: {
				patientId:patientId,
				symptomId: _symptomId,
				methodFlg: _methodFlg,
				sortType: _sortType,
				pageNum: _pageNum,
				pageSize: _pageSize
			},
			async: false,
			dataType: "json", // json or text or xml
			success:function(result){
				
				var Doclist=result.doctorList;
				var List="";
				
				if(result.returnCode == "0" && Doclist != null){
					/*
					$(".MenuChangeTap").addClass("displaynone");
					$(".ChangeTap").addClass("displaynone");
					$(".SelFm").addClass("displaynone");
					$("#container").removeClass("displaynone");
					*/
					
					for(i=0;i<Doclist.length;i++){
						/*
						1.医生ID:doctorId
						2.医生头像headPic
						3.医生姓名name
						4.医生职称名positional
						5.医生简介:healDisease
						6.医生评分:commentGrade
						7.医生的视频问诊价格total
						8.预订过该医生的人数:serviceCount
						*/
						var doctorId=Doclist[i].doctorId;
						var headPic=Doclist[i].headPic;
						var followFlag=Doclist[i].followFlag;
						if(followFlag == "0"){
							followFlag="img/icon_follow.png";
						}else if(followFlag == "1"){
							followFlag="img/icon_unfollow.png";
						}
						
						var name=Doclist[i].name;
						if(Doclist[i].name != null){
							name=Doclist[i].name;
						}else{
							name="";
						}
						var healDisease=Doclist[i].healDisease;
						if(Doclist[i].healDisease != null){
							healDisease=Doclist[i].healDisease;
						}else{
							healDisease="";
						}
						var hospitalName=Doclist[i].hospitalName;
						if(Doclist[i].hospitalName != null){
							hospitalName=Doclist[i].hospitalName;
						}else{
							hospitalName="";
						}
						var dcName=Doclist[i].dcName;
						if(Doclist[i].dcName != null){
							dcName=Doclist[i].dcName;
						}else{
							dcName="";
						}
						var departmentName=Doclist[i].departmentName;
						if(Doclist[i].departmentName != null){
							departmentName=Doclist[i].departmentName;
						}else{
							departmentName="";
						}
						
						var departmentId=Doclist[i].departmentId;
						
						if(Doclist[i].departmentId != null){
							departmentId=Doclist[i].departmentId;
						}else{
							departmentId="";
						}
						
						var positional=Doclist[i].positional;
						if(Doclist[i].positional != null){
							positional=Doclist[i].positional;
						}else{
							positional="";
						}
						var symptomId=Doclist[i].symptomId;
						if(Doclist[i].symptomId != null){
							symptomId=Doclist[i].symptomId;
						}else{
							symptomId="";
						}

						var commentGrade=parseFloat(Doclist[i].commentGrade).toFixed(1);
						if(commentGrade == null || commentGrade == "NaN"){
							commentGrade="暂无评分";
						}
						var total=Doclist[i].total;
						var serviceCount=Doclist[i].serviceCount;
						if(serviceCount == null){
							serviceCount="0";
						}
						if (Doclist[i].headPic == null){
							Doclist[i].headPic = "/MedicalUnicom/HealthWeb/img/id_icon.jpg";
						};
						
						List+="<li data='"+doctorId+"'><div class='info clearfix'><strong class='UserImg'><img src='"+headPic+"'/><span class='commentGrade'>"+commentGrade+"</span></strong><ul><li class='frt'><strong class='name'>"+name+"</strong> <span>"+positional+"</span></li><li><strong class='docTitle'><span class='dcName'>"+dcName+"</span> <span class='depId' data='"+departmentId+"'>"+departmentName+"</span></strong><strong class='docTitle'>"+hospitalName+"</strong><strong class='docTitle'>"+healDisease+"</strong></li></ul><div class='Price'><p><span class='Vprice'>"+total+"元 /次  起</span><span class='follow' data='"+Doclist[i].followFlag+"'><img src='"+followFlag+"'></span><!--span class='Cprice'>问诊: "+serviceCount+" 人次</span--></p></div></div></li>";
					
					}
					
					if( AddDoc == "0"){
						$(".List").html("");
					}

					$(".List").append(List);
					
					$(".follow").click(function(){
						var $that=$(this);
						var DocId=$that.parent().parent().parent().parent().attr("data");
						alert(patientId)
						if(patientId != null){
							if($(this).attr("data") == 0){
								$(this).attr("data","1");
								$(this).find("img").attr("src","img/icon_unfollow.png");
								$.ajax({
									type:"get",
									url:"/MedicalUnicom/FollowInterest.do?doInsertFollow",
									data:{
										patientId:patientId,
										doctorId:DocId
									},
									aync:false,
									dataType:"json",
									success:function(result){
										if(result.returnCode == 0){
											var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_complate.png'></span><font color='white'>添加关注</font></div></div>")
											$("body").append(Mess);
											setTimeout(function(){
												$(".Mess").remove();
											},1500)
											//alert("添加关注");
										}
									}
								})
							}else if($(this).attr("data") == 1){
								
								var Mess2=$("<div class='Mess2'><div class='MessWrap'><div class='confrim'><p>确定取消关注？</p><span class='confrim'><font color='red'>确定</font></span></div><span class='cancel'>取消</span></div></div>")
								var blind=$("<div class='blind'></div>")

								$("body").append(blind);
								$("body").append(Mess2);
								$(".Mess2").animate({"bottom":"0px"},100);

								$(".MessWrap span").click(function(){

									if($(this).attr("class") == "confrim"){
										$(".Mess2").animate({"bottom":"-6rem"},100,function(){
											$(".Mess2").remove();
											$(".blind").remove();
										});
										
										$that.attr("data","0");
										$that.find("img").attr("src","img/icon_follow.png")
										$.ajax({
											type:"get",
											url:"/MedicalUnicom/FollowInterest.do?doCancelFollow",
											data:{
												patientId:patientId,
												doctorId:DocId
											},
											aync:false,
											dataType:"json",
											success:function(result){
												if(result.returnCode == 0){
													var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_complate.png'></span><font color='white'>取消关注</font></div></div>")
													$("body").append(Mess);
													setTimeout(function(){
														$(".Mess").remove();
													},1500)
												}
											}
										})
									}else if($(this).attr("class") == "cancel"){
										$(".Mess2").animate({"bottom":"-6rem"},100,function(){
											$(".Mess2").remove();
											$(".blind").remove();
										});
									}
									
								})
								
							}
							
						}else{
							//var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_complate.png'></span><font color='white'>请登录</font></div></div>")
							var Mess1=$("<div class='Mess1'><div class='MessWrap'><p class='tip'>您还没有登录，请登录</p><span class='cancel'>取消</span><span class='Gologin'>立即登录</span></div></div>")
							var blind=$("<div class='blind'></div>")
							
							$("body").append(blind);
							$("body").append(Mess1);
							
							$(".MessWrap span").click(function(){
								if($(this).attr("class")=="cancel"){
									$(".Mess1").remove();
									$(".blind").remove();
									return false
								}else if($(this).attr("class")=="Gologin"){
									window.location.href="login.html"
								}
							})
							
							return false
							
						}
						
					})
					
					$(".UserImg img").on("error",function(){
						$(this).attr("src","img/defaultpatient.png")
					})

					$(".info ul").click(function(){
						window.location.href="Pat_ResDetail.html?depName="+$(this).find(".dcName").text()+"&patientId="+patientId+"&depId="+$(this).find(".depId").attr("data")+"&doctorId="+$(this).parent().parent().attr("data")+"&followFlag="+$(this).parent().find(".follow").attr("data");
					})
				}else{
					var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>该科室缺少医生，请谅解</font></div></div>")
					$("body").append(Mess);
					setTimeout(function(){
						$(".Mess").remove();
						return false;
					},1500)
				}
			}
		})
		
	};

	function getDocListDep(_departmentId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc){
		$.ajax({
			type:"get",
			url:"/MedicalUnicom/Doctor.do?doGetDoctorListByDepId",
			data: {
				patientId:patientId,
				departmentId:_departmentId,
				methodFlg: _methodFlg,
				sortType: _sortType,
				pageNum: _pageNum,
				pageSize: _pageSize
			},
			async: false,
			dataType: "json", // json or text or xml
			success:function(result){
				
				var Doclist=result.doctorList;
				var List="";
				
				if(result.returnCode == "0" && Doclist != null){
					/*
					$(".MenuChangeTap").addClass("displaynone");
					$(".ChangeTap").addClass("displaynone");
					$(".SelFm").addClass("displaynone");
					$("#container").removeClass("displaynone");
					*/
					for(i=0;i<Doclist.length;i++){
						/*
						1.医生ID:doctorId
						2.医生头像headPic
						3.医生姓名name
						4.医生职称名positional
						5.医生简介:healDisease
						6.医生评分:commentGrade
						7.医生的视频问诊价格total
						8.预订过该医生的人数:serviceCount
						*/
						var doctorId=Doclist[i].doctorId;
						var headPic=Doclist[i].headPic;
						var followFlag=Doclist[i].followFlag;
						if(followFlag == "0"){
							followFlag="img/icon_follow.png";
						}else if(followFlag == "1"){
							followFlag="img/icon_unfollow.png";
						}
						
						var name=Doclist[i].name;
						if(Doclist[i].name != null){
							name=Doclist[i].name;
						}else{
							name="";
						}
						var healDisease=Doclist[i].healDisease;
						if(Doclist[i].healDisease != null){
							healDisease=Doclist[i].healDisease;
						}else{
							healDisease="";
						}
						var hospitalName=Doclist[i].hospitalName;
						if(Doclist[i].hospitalName != null){
							hospitalName=Doclist[i].hospitalName;
						}else{
							hospitalName="";
						}
						var dcName=Doclist[i].dcName;
						if(Doclist[i].dcName != null){
							dcName=Doclist[i].dcName;
						}else{
							dcName="";
						}
						var departmentName=Doclist[i].departmentName;
						if(Doclist[i].departmentName != null){
							departmentName=Doclist[i].departmentName;
						}else{
							departmentName="";
						}
						var departmentId=Doclist[i].departmentId;
						if(Doclist[i].departmentId != null){
							departmentId=Doclist[i].departmentId;
						}else{
							departmentId="";
						}
						
						var positional=Doclist[i].positional;
						if(Doclist[i].positional != null){
							positional=Doclist[i].positional;
						}else{
							positional="";
						}

						var commentGrade=parseFloat(Doclist[i].commentGrade).toFixed(1);
						if(commentGrade == null || commentGrade == "NaN"){
							commentGrade="暂无评分";
						}
						var total=Doclist[i].total;
						var serviceCount=Doclist[i].serviceCount;
						if(serviceCount == null){
							serviceCount="0";
						}
						if (Doclist[i].headPic == null){
							Doclist[i].headPic = "/MedicalUnicom/HealthWeb/img/id_icon.jpg";
						};
						List+="<li data='"+doctorId+"'><div class='info clearfix'><strong class='UserImg'><img src='"+headPic+"'/><span class='commentGrade'>"+commentGrade+"</span></strong><ul><li class='frt'><strong class='name'>"+name+"</strong> <span>"+positional+"</span></li><li><strong class='docTitle'><span class='dcName'>"+dcName+"</span> <span class='depId' data='"+departmentId+"'>"+departmentName+"</span></strong><strong class='docTitle'>"+hospitalName+"</strong><strong class='docTitle'>"+healDisease+"</strong></li></ul><div class='Price'><p><span class='Vprice'>"+total+"元 /次  起</span><span class='follow' data='"+Doclist[i].followFlag+"'><img src='"+followFlag+"'></span><!--span class='Cprice'>问诊: "+serviceCount+" 人次</span--></p></div></div></li>";
					
					}
					
					if( AddDoc == "0"){
						$(".List").html("");
					}

					$(".List").append(List);
					
					/*
					$(".follow").click(function(){
						var $that=$(this);
						var DocId=$that.parent().parent().parent().parent().attr("data");
						if(patientId == null){
							//var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_complate.png'></span><font color='white'>请登录</font></div></div>")
							var Mess1=$("<div class='Mess1'><div class='MessWrap'><p class='tip'>您还没有登录，请登录</p><span class='cancel'>取消</span><span class='Gologin'>立即登录</span></div></div>")
							var blind=$("<div class='blind'></div>")
							
							$("body").append(blind);
							$("body").append(Mess1);
							
							$(".MessWrap span").click(function(){
								if($(this).attr("class")=="cancel"){
									$(".Mess1").remove();
									$(".blind").remove();
									return false
								}else if($(this).attr("class")=="Gologin"){
									window.location.href="login.html"
								}
							})
							
							return false
						}else{
						
							if($(this).attr("data") == 0){
								$(this).attr("data","1");
								$(this).find("img").attr("src","img/icon_unfollow.png");
								$.ajax({
									type:"get",
									url:"/MedicalUnicom/FollowInterest.do?doInsertFollow",
									data:{
										patientId:patientId,
										doctorId:DocId
									},
									aync:false,
									dataType:"json",
									success:function(result){
										if(result.returnCode == 0){
											var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_complate.png'></span><font color='white'>添加关注</font></div></div>")
											$("body").append(Mess);
											setTimeout(function(){
												$(".Mess").remove();
											},1500)
											//alert("添加关注");
										}
									}
								})
							}else if($(this).attr("data") == 1){
								
								var Mess2=$("<div class='Mess2'><div class='MessWrap'><div class='confrim'><p>确定取消关注？</p><span class='confrim'><font color='red'>确定</font></span></div><span class='cancel'>取消</span></div></div>")
								var blind=$("<div class='blind'></div>")

								$("body").append(blind);
								$("body").append(Mess2);
								$(".Mess2").animate({"bottom":"0px"},100);

								$(".MessWrap span").click(function(){

									if($(this).attr("class") == "confrim"){
										$(".Mess2").animate({"bottom":"-6rem"},100,function(){
											$(".Mess2").remove();
											$(".blind").remove();
										});
										
										$that.attr("data","0");
										$that.find("img").attr("src","img/icon_follow.png")
										$.ajax({
											type:"get",
											url:"/MedicalUnicom/FollowInterest.do?doCancelFollow",
											data:{
												patientId:patientId,
												doctorId:DocId
											},
											aync:false,
											dataType:"json",
											success:function(result){
												if(result.returnCode == 0){
													var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_complate.png'></span><font color='white'>取消关注</font></div></div>")
													$("body").append(Mess);
													setTimeout(function(){
														$(".Mess").remove();
													},1500)
												}
											}
										})
									}else if($(this).attr("class") == "cancel"){
										$(".Mess2").animate({"bottom":"-6rem"},100,function(){
											$(".Mess2").remove();
											$(".blind").remove();
										});
									}
									
								})
								
							}
						}
						
					})*/
					
					$(".UserImg img").on("error",function(){
						$(this).attr("src","img/defaultpatient.png")
					})

					$(".info ul").click(function(){
						window.location.href="Pat_ResDetail.html?depName="+$(this).find(".dcName").text()+"&patientId="+patientId+"&depId="+$(this).find(".depId").attr("data")+"&doctorId="+$(this).parent().parent().attr("data")+"&followFlag="+$(this).parent().find(".follow").attr("data");
					})
				}else{
					var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>该科室缺少医生，请谅解</font></div></div>")
					$("body").append(Mess);
					setTimeout(function(){
						$(".Mess").remove();
						return false;
					},1500)
				}
			}
		})
		
	};

	
	$("#MoreBtn").click(function(){
		AddDoc="1";
		_pageNum=_pageNum+10;
		
		if(Symp=="sym"){
			getDocList(_symptomId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc);
			return;
		}else if(Symp=="Dep"){
			getDocListDep(_departmentId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc);
			return;
		}
		
	})

	$(".sortForm li").click(function(){

		if(Symp=="sym" ){
			
			if($(this).attr("class") == undefined || $(this).attr("class") =="") {
				
				$(".sortForm li").removeClass("down");
				$(".sortForm li").removeClass("up");
				_methodFlg=$(this).attr("id");
				_sortType="1";
				$(this).addClass("down");
				AddDoc="0";
				_pageNum=0;
				_pageSize=10;
				count=0;
				mui('#pullrefresh').pullRefresh().refresh(true);
				mui('#pullrefresh').pullRefresh().scrollTo(0,0,100);
				//$(".mui-scroll").css({"transform":"translate3d(0px, 0px, 0px)"}) 
				getDocList(_symptomId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc);
				return;
			}
			
			if($(this).attr("class") == "down"){
				
				$(".sortForm li").removeClass("down");
				$(".sortForm li").removeClass("up");
				_methodFlg=$(this).attr("id");
				_sortType="0";
				$(this).addClass("up");
				AddDoc="0";
				_pageNum=0;
				_pageSize=10;
				count=0;
				mui('#pullrefresh').pullRefresh().refresh(true);
				mui('#pullrefresh').pullRefresh().scrollTo(0,0,100);
				//$(".mui-scroll").css({"transform":" translate3d(0px, 0px, 0px)"}) 
				getDocList(_symptomId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc);
				return;
			}
			
			if($(this).attr("class") == "up"){
				
				$(".sortForm li").removeClass("down");
				$(".sortForm li").removeClass("up");
				_methodFlg=$(this).attr("id");
				_sortType="1";
				$(this).addClass("down");
				AddDoc="0";
				_pageNum=0;
				_pageSize=10;
				count=0;
				mui('#pullrefresh').pullRefresh().refresh(true);
				mui('#pullrefresh').pullRefresh().scrollTo(0,0,100);
				//$(".mui-scroll").css({"transform":"translate3d(0px, 0px, 0px)"}) 
				getDocList(_symptomId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc);
				return;
			}
		}else if(Symp=="Dep"){
			if($(this).attr("class") == undefined || $(this).attr("class") =="") {
				
				$(".sortForm li").removeClass("down");
				$(".sortForm li").removeClass("up");
				$(this).addClass("down");
				_methodFlg=$(this).attr("id");
				_sortType="1";
				_pageNum=0;
				_pageSize=10;
				AddDoc="0";
				count=0;
				mui('#pullrefresh').pullRefresh().refresh(true);
				mui('#pullrefresh').pullRefresh().scrollTo(0,0,100);
				//$(".mui-scroll").css({"transform":"translate3d(0px, 0px, 0px)"}) 
				getDocListDep(_departmentId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc);
				return;
			}
			if($(this).attr("class") == "down"){
				
				$(".sortForm li").removeClass("down");
				$(".sortForm li").removeClass("up");
				_methodFlg=$(this).attr("id");
				_sortType="0";
				$(this).addClass("up");
				AddDoc="0";
				_pageNum=0;
				_pageSize=10;
				count=0;
				mui('#pullrefresh').pullRefresh().refresh(true);
				mui('#pullrefresh').pullRefresh().scrollTo(0,0,100);
				//$(".mui-scroll").css({"transform":"translate3d(0px, 0px, 0px)"}) 
				getDocListDep(_departmentId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc);
				return;
			}
			if($(this).attr("class") == "up"){
				
				$(".sortForm li").removeClass("down");
				$(".sortForm li").removeClass("up");
				$(this).addClass("down");
				_methodFlg=$(this).attr("id");
				_sortType="1";
				AddDoc="0";
				_pageNum=0;
				_pageSize=10;
				count=0;
				mui('#pullrefresh').pullRefresh().refresh(true);
				mui('#pullrefresh').pullRefresh().scrollTo(0,0,100);
				//$(".mui-scroll").css({"transform":"translate3d(0px, 0px, 0px)"}) 
				getDocListDep(_departmentId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc);
				return;
			}

		}

	})

	mui.init({
		pullRefresh: {
			container: '#pullrefresh',
			down: {
				height:120,
				callback: pulldownRefresh
				
			},
			up: {
				height:120,
				contentrefresh: '正在加载...', 
				callback: pullupRefresh
			}
		}
	});

	mui(".List").on("tap",".info ul",function(){
		var depName=$(this).find(".dcName").text();
		var id=$(this).parent().parent().attr("data");
		var followFlag=$(this).parent().find(".follow").attr("data");
		mui.openWindow({
		    url:'Pat_ResDetail.html?depName='+depName+'&doctorId='+id+"&patientId="+patientId+"&followFlag="+followFlag,
		});
	})

	mui(".List").on("tap",".follow",function(){
		
		var $that=$(this);
		var DocId=$that.parent().parent().parent().parent().attr("data");
		

		if(patientId != "null" && patientId != ""){
			
			if($(this).attr("data") == 0){
				$(this).attr("data","1");
				$(this).find("img").attr("src","img/icon_unfollow.png");
				$.ajax({
					type:"get",
					url:"/MedicalUnicom/FollowInterest.do?doInsertFollow",
					data:{
						patientId:patientId,
						doctorId:DocId
					},
					aync:false,
					dataType:"json",
					success:function(result){
						if(result.returnCode == 0){
							var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_complate.png'></span><font color='white'>添加关注</font></div></div>")
							$("body").append(Mess);
							setTimeout(function(){
								$(".Mess").remove();
							},1500)
							//alert("添加关注");
						}
					}
				})
			}else if($(this).attr("data") == 1){
				
				var Mess2=$("<div class='Mess2'><div class='MessWrap'><div class='confrim'><p>确定取消关注？</p><span class='confrim'><font color='red'>确定</font></span></div><span class='cancel'>取消</span></div></div>")
				var blind=$("<div class='blind'></div>")

				$("body").append(blind);
				$("body").append(Mess2);
				$(".Mess2").animate({"bottom":"0px"},100);

				$(".MessWrap span").click(function(){

					if($(this).attr("class") == "confrim"){
						$(".Mess2").animate({"bottom":"-6rem"},100,function(){
							$(".Mess2").remove();
							$(".blind").remove();
						});
						
						$that.attr("data","0");
						$that.find("img").attr("src","img/icon_follow.png")
						$.ajax({
							type:"get",
							url:"/MedicalUnicom/FollowInterest.do?doCancelFollow",
							data:{
								patientId:patientId,
								doctorId:DocId
							},
							aync:false,
							dataType:"json",
							success:function(result){
								if(result.returnCode == 0){
									var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_complate.png'></span><font color='white'>取消关注</font></div></div>")
									$("body").append(Mess);
									setTimeout(function(){
										$(".Mess").remove();
									},1500)
								}
							}
						})
					}else if($(this).attr("class") == "cancel"){
						$(".Mess2").animate({"bottom":"-6rem"},100,function(){
							$(".Mess2").remove();
							$(".blind").remove();
						});
					}
					
				})
				
			}
			
		}else{
			var Mess1=$("<div class='Mess1'><div class='MessWrap'><p class='tip'>请您登录</p><span class='cancel'>取消</span><span class='Gologin'>立即登录</span></div></div>")
			var blind=$("<div class='blind'></div>")
			
			$("body").append(blind);
			$("body").append(Mess1);
			
			$(".MessWrap span").click(function(){
				if($(this).attr("class")=="cancel"){
					$(".Mess1").remove();
					$(".blind").remove();
					return false
				}else if($(this).attr("class")=="Gologin"){
					window.location.href="login.html"
				}
			})
			
			return false
			
		}

		/*
		mui.openWindow({
		    url:'Pat_ResDetail.html?depName='+depName+'&doctorId='+id+"&patientId="+patientId+"&followFlag=1",
		});*/
	})
	
	/**
	 * 下拉刷新具体业务实现
	 */
	function pulldownRefresh() {
		setTimeout(function() {
			mui('#pullrefresh').pullRefresh().endPulldownToRefresh(); //refresh completed
			_pageNum=0;
			AddDoc=0;
			if(_symptomId != ""){
				getDocList(_symptomId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc);
			}else{
				getDocListDep(_departmentId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc)
			}
		}, 1500);

	}
	var count = 0;
	/**
	 * 上拉加载具体业务实现
	 */
	function pullupRefresh() {
		setTimeout(function() {
			mui('#pullrefresh').pullRefresh().endPullupToRefresh((++count > 2)); //参数为true代表没有更多数据了。
			_pageNum=_pageNum+10;
			AddDoc = 1;
			if(_symptomId != ""){
				getDocList(_symptomId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc);
			}else{
				getDocListDep(_departmentId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc)
			}
			//getDocList( listPage , listSize , AddDoc)
		}, 1500);
	}
	/*
	if (mui.os.plus) {
		mui.plusReady(function() {
			setTimeout(function() {
				mui('#pullrefresh').pullRefresh().pullupLoading();
			}, 1000);

		});
	} else {
		mui.ready(function() {
			mui('#pullrefresh').pullRefresh().pullupLoading();
		});
	}*/

})