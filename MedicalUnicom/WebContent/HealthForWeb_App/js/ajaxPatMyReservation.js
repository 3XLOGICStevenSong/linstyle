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

	function getCookie(name){
		
	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	 
	    if(arr=document.cookie.match(reg))

	        return (arr[2]);
	    else
	        return null;
	};

	function setCookie(name,value){
	    /*
	    var Days = 30;
	    var exp = new Date();
	    exp.setTime(exp.getTime() + Days*24*60*60*1000);*/
	    document.cookie = name + "="+ escape (value) ;/*+ ";expires=" + exp.toGMTString()*/
	}
	
	function getMyReservation( pid,_pageNum , _pageSize ,_statusFlag , Emp){
		$.ajax({
			type:"get",
			url:"/MedicalUnicom/Appoint.do?doGetAppointListByStatus",//doGetAppointListByPatient
			data:{
				patientId:pid,
				statusFlag:_statusFlag,
				pageNum:_pageNum,
				pageSize:_pageSize
			},
			aync:false,
			dataType:"json",
			success:function(result){
				var DTO=result.patientAppointList;

				if(DTO == null){

					//$(".List").hide();
					var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>没有了</font></div></div>")
					$("body").append(Mess);
					setTimeout(function(){
						$(".Mess").remove();
					},1000)
					//alert("没有更多");
					return false
					
				}else{
					/*
					if(DTO == null){
						alert("没有更多")
						return false
					}*/
					var List="";
					
					for(i=0;i<DTO.length;i++){
						var date=new Date();
						
						var name=DTO[i].name;
						if(name !=null){
							name=DTO[i].name;
						}else{
							name="Name";
						}
						var positional=DTO[i].positional;
						var headPic=DTO[i].headPic;
						var healDisease=DTO[i].healDisease;
						var dcName=DTO[i].dcName;
						var departmentName=DTO[i].departmentName;
						var recordsId=DTO[i].recordsId;
						var inquiryStatus=DTO[i].inquiryStatus;
						var payStatus=DTO[i].payStatus
						var appointTime=DTO[i].appointTime;
						var createTime=DTO[i].createTime;
						var recordsType=DTO[i].recordsType;
						var hospitalName=DTO[i].hospitalName;
						var tel=DTO[i].tel;
						
						var Time=createTime;
		
						var date1="";
						if(appointTime != null){
							Time=appointTime+" ~ "+appointTime.substring(11,16).replace("00","59");
							date1=appointTime.replace(/-/g,"/")+":00";  //开始时间  
						}else{
							Time=createTime;
							date1=createTime.replace(/-/g,"/")+":00";  //开始时间  
						}
		
					    var date2= new Date(date1);    //结束时间  
					    var date3="";   //时间差的毫秒数
						var delay="";
					    
						if(new Date().getTime()> date2.getTime() ){
							delay=-1;
							date3=new Date().getTime() - date2.getTime(); 
						}else if(new Date().getTime() < date2.getTime() ){
							delay=0;
							date3=date2.getTime() - new Date().getTime(); 
						}
		
						//计算出天数  
						var days=Math.floor(date3/(24*3600*1000));
						
						//计算出小时数  
						var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数  
						var hours=Math.floor(leave1/(3600*1000));
						var H=Math.floor(date3/3600/1000);
		
						//计算相差分钟数  
						var leave2=leave1%(3600*1000);       //计算小时数后剩余的毫秒数  
						var minutes=Math.floor(leave2/(60*1000));
						var M=Math.floor(date3/60/1000);
		
						var InqDiv="";
						
						if( payStatus == 0){
							InqDiv="<p class='Notpaid'>未支付</p>";
	
						}else if(payStatus == 2){
							InqDiv="<p class='PaidOver'>支付超时</p>";
							
						}else if(payStatus == 3){
							InqDiv="<p class='Refund'>已退款</p>";
							
						}else if(payStatus == 1 && inquiryStatus == 0 && recordsType == 0){
							InqDiv="<p class='Wait'>等待医生确认</p>";
	
						}else if(payStatus == 1 && inquiryStatus == 0 && recordsType == 1){
							InqDiv="<p class='Wait'>等待医生确认</p>";
							
						}else if(payStatus == 1 && inquiryStatus == 1 && recordsType == 0){
							
							if(delay == 0 && H > 24){
								InqDiv="<p class='Count'><span class='state_icon'></span>还有"+days+"天"+hours+"时</p>";
							}else if(delay==0 && H < 24 && H >= 1){
								InqDiv="<p class='Count'><span class='state_icon'></span>还有"+hours+"时"+minutes+"分钟</p>";
							}else if(delay==0 && M < 60){
								InqDiv="<p class='Cam'><span class='state_icon'></span><span class='Start'><a href='callVideo.html'>开始就诊</a></span></p>";
							}else if(delay==-1 && M > 60){
								InqDiv="<p class='NotResult'>未出就诊结果</p>";
							};
						}else if(payStatus == 1 && inquiryStatus == 1 && recordsType == 1){
						 		InqDiv="<p class='Success'>预约成功</p>"
							 	
						}else if(payStatus == 1 && inquiryStatus == 2 ){
							InqDiv="<p class='NotResult'>未出就诊结果</p>";
						}else if(payStatus == 1 && inquiryStatus == 3 ){
							InqDiv="<p class='Over'>问诊结束</p>";
						}else if(payStatus == 1 && inquiryStatus == 4 ){
							InqDiv="<p class='DocReject'>医生拒绝</p>";
						}else if(payStatus == 1 && inquiryStatus == 5 ){
							InqDiv="<p class='WaitCancel'>等待医生同意取消</p>";
						}else if(payStatus == 1 && inquiryStatus == 6 ){
							InqDiv="<p class='AgreeCancel'>医生同意取消</p>";
						}else if(payStatus == 1 && inquiryStatus == 7 ){
							InqDiv="<p class='Disagree'>医生拒绝取消</p>";
						}else if(payStatus == 1 && inquiryStatus == 8 ){
							InqDiv="<p class='Cancel'>医生取消</p>";
						}
						var tel=DTO[i].tel;
						var createTime=DTO[i].createTime;
						List+="<li data='"+recordsId+"' name='"+tel+"'><div class='info' data='"+payStatus+"'><strong class='UserImg'><img src='"+headPic+"' /></strong><ul><li class='frt'><strong class='name'>"+name+"</strong><span>"+positional+"</span></li><li><strong class='docTitle'><span>"+dcName+"</span> <span>"+departmentName+"</span></strong><strong class='docTitle'><span>"+hospitalName+"</span></strong><strong class='docTitle'><span>"+healDisease+"</span></strong></li></ul></div><div class='TimeList'><div class='state clearfix'><strong class='timer'>预约时间: <span class='datetime'>"+Time+"</span></strong>"+InqDiv+"</div></div></li>";
					}
						
					if(Emp === 0 ){
						$("#List").html("");
						$("#List").append(List);
					}else{
						$("#List").append(List);
					}
	
					$(".UserImg img").on("error",function(){
						$(this).attr("src","img/defaultpatient.png")
					})
					
					$(document).scroll(function(){
						setCookie("scroll",$(document).scrollTop());
					})
					
					$(document).scrollTop(getCookie("scroll"));
					
					$(".info").click(function(){
						setCookie("TelNum",$(this).parent().attr("name"));
						setCookie("payStatus",$(this).attr("data"));
						window.location.href="Pat_MyReservationDetail.html?date="+$(this).parent("li").attr("data");
					})
					
				}
			}
		})

	}
	
	var _pageNum=0;
	var _pageSize=10;
	var _statusFlag=1;
	var Emp = 0;
	
	getMyReservation( pid, _pageNum , _pageSize , _statusFlag , Emp)
	
	$(".listNavy li").click(function(){
		$(".listNavy li").removeClass("On");
		$(this).addClass("On");
		_pageNum=0;
		_pageSize=10;
		Emp = 0;
		_statusFlag=$(this).attr("data");
		getMyReservation( pid, _pageNum , _pageSize , _statusFlag , Emp);
	})

	$("#MoreBtn").click(function(){
		_pageNum=_pageNum+10;
		Emp = 1;
		getMyReservation( pid , _pageNum , _pageSize , _statusFlag , Emp)
	})
	
	mui.init({
		pullRefresh: {
			container: '#pullrefresh',
			down: {
				height:"100",
				callback: pulldownRefresh
				
			},
			up: {
				contentrefresh: '正在加载...', 
				callback: pullupRefresh
			}
		}
	});

	
	mui("#List").on("tap",".info",function(){
		var id = this.parentNode.getAttribute("data");
		setCookie("TelNum",$(this).parent().attr("name"));
		setCookie("payStatus",$(this).attr("data"));
		mui.openWindow({
		    url:'Pat_MyReservationDetail.html?date='+id
		});
	})
	
	/**
	 * 下拉刷新具体业务实现
	 */
	function pulldownRefresh() {
		setTimeout(function() {
			mui('#pullrefresh').pullRefresh().endPulldownToRefresh(); //refresh completed
			_pageNum=0;
			Emp=0;
			getMyReservation( pid , _pageNum , _pageSize ,_statusFlag , Emp)
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
			Emp = 1;
			getMyReservation( pid , _pageNum , _pageSize , Emp)
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

