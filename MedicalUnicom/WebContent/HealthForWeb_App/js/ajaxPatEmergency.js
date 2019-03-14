$(function(){
	var pid=getCookie("pid");
	
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
		url:"/MedicalUnicom/GradePrice.do?doGetEmergencyPrice",
		data:{},
		aync:false,
		datatype:"json",
		success:function(result){
			//var price=$(".price").text();
			var sym=$("title").html();
			var price=result.price;
			$(".price").text(price);
			
			$(".subMit_btn").click(function(){
				$.ajax({
					type:"post",
					url:"/MedicalUnicom/EmergencyDoctor.do?doAppointEmergency",
					data:{
						patientId:pid,
						totalFee:price,
						payType:0
					},
					aync:false,
					dataType:"json",
					success:function(result){
						var orderId=result.orderNumber;
						var signedPayString=result.aliPaySignInfo;
						if(result.returnCode==0){
							window.location.href="payment.html?orderId="+orderId+"&ToFee="+price+"&signedPayString="+signedPayString+"&doctorId=Emergency";
						}
					}
				})
				
			})
			
		}
	})
	
	
	
})