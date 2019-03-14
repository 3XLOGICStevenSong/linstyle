$(function(){
	var link=window.location.href;
	var param=link.split("&");
	var patientId="";
	var doctorId="";
	
	for(i=0;i<param.length;i++){
		if(param[i].indexOf("doctorId") != -1 ){
			doctorId=param[i].substring(param[i].lastIndexOf("=")+1,param[i].length);
		}else if(param[i].indexOf("patientId") != -1 ){
			patientId=param[i].substring(param[i].lastIndexOf("=")+1,param[i].length);
		}else{
			patientId=getCookie("pid");
			doctorId=getCookie("pid");
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
	
	function getDocList(listPage,listSize,Emp){
		$.ajax({
			type:"get",
			url:"/MedicalUnicom/FollowInterest.do?doGetFollowList",
			data: {
				patientId:patientId,
				pageNum:listPage,
				pageSize:10
			},
			async: false,
			dataType: "json", // json or text or xml
			success:function(result){
				var Doclist=result.doctorList;
				var List="";

				if(Doclist == null){
					var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>没有更多</font></div></div>")
					$("body").append(Mess);
					setTimeout(function(){
						$(".Mess").remove();
					},1500)
					//alert("没有更多");
				}else{
					for(i=0;i<Doclist.length;i++){
					/*
					{
					1.医生ID:doctorId
					2.医生头像:headPic
					3.医生姓名:name
					4.医生科室:departmentName
					5.医生职称名:positional
					6.医生简介:healDisease
					7.套餐价格:total
					8.套餐剩余有效天数:effectTime
					9.套餐剩余视频问诊次数vio_num
					10.套餐剩余电话问诊次数tel_num 
					11预约的时间 ：createTime
					12:预约appointId
					13.手机号（环信留言使用）：tel
					14.医生科室:departmentId
					}
					*/	
						var doctorId=Doclist[i].doctorId;
						var name=Doclist[i].name;
						var headPic=Doclist[i].headPic;
						if (headPic == null){
							headPic = "img/defaultpatient.png";
						}
						var healDisease=Doclist[i].healDisease;
						var hospitalName=Doclist[i].hospitalName;
						var positional=Doclist[i].positional;
						if(positional != null){
							positional=Doclist[i].positional;
						}else{
							positional="";
						}
						var dcName=Doclist[i].dcName;
						//var departmentId=Doclist[i].departmentId;
						var departmentName=Doclist[i].departmentName;

						var minTotal=Doclist[i].minTotal;
						if(minTotal != null){
							minTotal=Doclist[i].minTotal;
						}else{
							minTotal="0";
						}
						var commentGrade=parseFloat(Doclist[i].commentGrade).toFixed(1);
						if(commentGrade == null || commentGrade == "NaN"){
							commentGrade="暂无评分";
						}
						
						
						/*
						var appointId=Doclist[i].appointId;
						var total=Doclist[i].total;
						var vio_num=Doclist[i].vio_num;
						var tel_num=Doclist[i].tel_num;
						var tel=Doclist[i].tel;
						var efft=Doclist[i].effectTime
						var effectTime="剩余 "+efft+" 天";
						if(efft < 1 || efft == null ){
							effectTime="已经过期";
						}
						if(vio_num < 1) {
							vio_num = 0;
						}
						if(tel_num < 1){
							tel_num = 0;
						};
						<!--span class='Vprice'><img src='/MedicalUnicom/HealthForWeb_App/img/cam_icon.png'>"+vio_num+"次</span><span class='Cprice'><img src='/MedicalUnicom/HealthForWeb_App/img/call_icon.png'>"+tel_num+"次</spa><span class='Effect'>"+effectTime+"</spann-->
						*/
						
						
						
						List+="<li data='"+doctorId+"'><div class='info clearfix'><strong class='UserImg'><img src='"+headPic+"'/><span class='commentGrade'>"+commentGrade+"</span></strong><ul><li class='frt'><strong class='name'>"+name+"</strong><span>"+positional+"</span></li><li><strong class='docTitle'><span class='daName'>"+dcName+"</span> <span class='depId'>"+departmentName+"</span></strong><strong class='docTitle'><span>"+hospitalName+"</span></strong><strong class='docTitle'><span>"+healDisease+"</span></strong></li></ul><div class='Price'><p><span class='Vprice'>"+minTotal+"元 /次  起</span><span class='follow' data='1'><img id='followImg' src='img/icon_unfollow.png'></span></p></div></div></li>";

					}
					
					if(Emp === 0 ){
						$("#List").html("");
						$("#List").append(List);
					}else{
						$("#List").append(List);
					}

					$(".follow").click(function(){
						var $that=$(this);
						var DocId=$that.parent().parent().parent().parent().attr("data");
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

					})
					
					$(".UserImg img").on("error",function(){
						$(this).attr("src","img/defaultpatient.png")
					})
					
					$(".info ul").click(function(){
						//setCookie("appointId",$(this).parent().attr("class"))
						//setCookie("TelNum",$(this).attr("name"))
						window.location.href="Pat_ResDetail.html?&doctorId="+$(this).parent().attr("data")+"&patientId="+patientId;
					})
				}
			}
		})
		
	};
	
	var listPage=0;
	var listSize=10;
	var Emp=0;
	
	getDocList(listPage,listSize,Emp);

	$("#MoreBtn").click(function(){

		listPage=listPage+10;
		getDocList(listPage,listSize);
	})
	
	mui.init({
		pullRefresh: {
			container: '#pullrefresh',
			down: {
				height:120,
				callback: pulldownRefresh
				
			},
			up: {
				contentrefresh: '正在加载...', 
				callback: pullupRefresh
			}
		}
	});


	mui("#List").on("tap",".info ul",function(){
		var depName=$(this).find(".daName").text();
		var id=$(this).parent().parent().attr("data");
		mui.openWindow({
		    url:'Pat_ResDetail.html?depName='+depName+'&doctorId='+id+"&patientId="+patientId+"&followFlag=1",
		});
	})

	mui("#List").on("tap",".follow",function(){
		var depName=$(this).find(".daName").text();
		var id=$(this).parent().parent().attr("data");

		var $that=$(this);
		var DocId=$that.parent().parent().parent().parent().attr("data");
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
					$that.parent().parent().parent().parent().remove();
				});

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
			listPage=0;
			Emp=0;
			getDocList( listPage , listSize , Emp)
		}, 1500);

	}
	var count = 0;
	/**
	 * 上拉加载具体业务实现
	 */
	function pullupRefresh() {
		setTimeout(function() {
			mui('#pullrefresh').pullRefresh().endPullupToRefresh((++count > 2)); //参数为true代表没有更多数据了。
			listPage=listPage+10;
			Emp = 1;
			getDocList( listPage , listSize , Emp)
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