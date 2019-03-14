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
	    document.cookie = name + "="+ escape (value); /*+ ";expires=" + exp.toGMTString()*/
	}
	
	function getCookie(name){

	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	 
	    if(arr=document.cookie.match(reg))
	 
	        return (arr[2]);
	    else
	        return null;
	}

	function getDocList1(listPage,listSize,Emp){
		$.ajax({
			type:"get",
			url:"/MedicalUnicom/FollowInterest.do?doGetFollowList",
			data: {
				patientId:pid,
				pageNum:listPage,
				pageSize:10
			},
			async: false,
			dataType: "json", // json or text or xml
			success:function(result){
				var Doclist=result.doctorList;
				var List="";

				if(Doclist == null){
					alert("没有更多");
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
						var appointId=Doclist[i].appointId;
						var headPic=Doclist[i].headPic;
						var departmentName=Doclist[i].departmentName;
						var positional=Doclist[i].positional;
						var healDisease=Doclist[i].healDisease;
						var hospitalName=Doclist[i].hospitalName;
						var total=Doclist[i].total;
						var vio_num=Doclist[i].vio_num;
						var tel_num=Doclist[i].tel_num;
						var tel=Doclist[i].tel;
						var efft=Doclist[i].effectTime
						var dcName=Doclist[i].dcName;
						var departmentId=Doclist[i].departmentId;
						var effectTime="剩余 "+efft+" 天";
						
						if (headPic == null){
							headPic = "/MedicalUnicom/HealthForWeb_App/img/id_icon.jpg";
						}
						if(efft < 1 || efft == null ){
							effectTime="已经过期";
						}
						if(vio_num < 1) {
							vio_num = 0;
						}
						if(tel_num < 1){
							tel_num = 0;
						};
						
						List+="<li data='"+doctorId+"'><div class='info clearfix' name='"+tel+"'><strong class='UserImg'><img src='"+headPic+"'/></strong><ul><li class='frt'><strong class='name'>"+name+"</strong><span>"+positional+"</span></li><li><strong class='docTitle'><span>"+dcName+"</span> <span>"+departmentName+"</span></strong><strong class='docTitle'><span>"+hospitalName+"</span></strong><strong class='docTitle'><span>"+healDisease+"</span></strong></li></ul><div class='Price'><p><!--span class='Vprice'><img src='/MedicalUnicom/HealthForWeb_App/img/cam_icon.png'>"+vio_num+"次</span--><!--span class='Cprice'><img src='/MedicalUnicom/HealthForWeb_App/img/call_icon.png'>"+tel_num+"次</span--><span class='Effect'>"+effectTime+"</span></p></div></div></li>";
					
					/*	
					<li>
						<div class="info">
							<strong class="UserImg"><img src="img/photo.jpg"></strong>
							<ul>
								<li class="frt">
									<strong class="name">陈淑娟 <span class="level">主治医师</span></strong>
								</li>
								<li>
									<strong class="docTitle">评分: <span class="grad">4.6</span></strong>
									<strong class="docProfile">简介: <span>胃肠道间质肿瘤的诊治。</span></strong>
								</li>
							</ul>
							<div class="Price">
								<p><span class="Vprice">30</span>元/次</p>
								<p class="clearfix">问诊:<span class="count">20</span>人</p>
							</div>
						</div>
					</li>
					*/
					}
					
					if(Emp === 0 ){
						$("#List1").html("");
						$("#List1").append(List);
					}else{
						$("#List1").append(List);
					}


					$(".UserImg img").on("error",function(){
						$(this).attr("src","img/defaultpatient.png")
					})

					$(".info").click(function(){
						setCookie("appointId",$(this).parent().attr("class"))
						setCookie("TelNum",$(this).attr("name"))
						window.location.href="Pat_BudedDocDetail.html?appointId="+$(this).parent().attr("class")+"&doctorId="+$(this).parent().attr("data");
					})
				}
			}
		})

	};

	var listPage=0;
	var listSize=10;
	var Emp=0;

	getDocList1(listPage,listSize,Emp);

	$("#MoreBtn").click(function(){

		listPage=listPage+1;
		getDocList1(listPage,listSize);
	})

	mui.init({
		pullRefresh: {
			container: '#pullrefresh1',
			down: {
				height:150,
				callback: pulldownRefresh1
				
			},
			up: {
				contentrefresh: '正在加载...', 
				callback: pullupRefresh1
			}
		}
	});


	mui("#List1").on("tap",".info",function(){
		var id = this.parentNode.getAttribute("data");
		var appointId = this.parentNode.getAttribute("class");
		var depId=this.parentNode.getAttribute("data");
		mui.openWindow({
		    url:'Pat_BudedDocDetail.html?appointId='+appointId+'&depId='+depId+'&doctorId='+id
		});
	})
	
	/**
	 * 下拉刷新具体业务实现
	 */
	function pulldownRefresh1() {
		setTimeout(function() {
			mui('#pullrefresh1').pullRefresh().endPulldownToRefresh(); //refresh completed
			listPage=0;
			Emp=0;
			getDocList1( listPage , listSize , Emp)
		}, 1500);

	}
	var count = 0;
	/**
	 * 上拉加载具体业务实现
	 */
	function pullupRefresh1() {
		setTimeout(function() {
			mui('#pullrefresh1').pullRefresh().endPullupToRefresh((++count > 2)); //参数为true代表没有更多数据了。
			listPage=listPage+1;
			Emp = 1;
			getDocList1( listPage , listSize , Emp)
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