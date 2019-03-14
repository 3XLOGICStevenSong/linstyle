$(function(){
	function setCookie(name,value){
	    /*
	    var Days = 30;
	    var exp = new Date();
	    exp.setTime(exp.getTime() + Days*24*60*60*1000);*/
	    document.cookie = name + "="+ escape (value) ;
		/*+ ";expires=" + exp.toGMTString()*/
	};
	
	function getCookie(name){

	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	 
	    if(arr=document.cookie.match(reg))
	 
	        return (arr[2]);
	    else
	        return null;
	};
	
	var pid=getCookie("pid");
	var recordsId_=window.location.href.substring(window.location.href.lastIndexOf("=")+1,window.location.href.length);
	
	$.ajax({
		type:"get",
		url:"/MedicalUnicom/RefundReason.do?doGetReasonList",
		data:{},
		aync:false,
		dataType:"json",
		success:function(result){
			var RefList=result.refundReasonList;
			
			var option="<option>请选择退款原因</option>";
			
			for(i=0;i<RefList.length;i++){
				option+="<option value='"+RefList[i].reasonId+"'>"+RefList[i].reasonContent+"</option>"
			}
			
			$("#Causes").html(option);

			$(".subMit_btn").click(function(){
				var refundId=$("#Causes option:selected").val();
				var refundReason=$("#Causes option:selected").text();
				var reasonMemo=$(".refundCauses textarea").val();
				
				$("#Causes").change(function(){
					refundId=$("#Causes option:selected").val();
				})
				
				if(refundId == null || refundId == "请选择退款原因"){
					alert("请选择退款原因");
					return false;
				}else if(reasonMemo == ""){
					alert("请输入退款原因");
					return false;
				}
				$.ajax({
					type:"get",
					url:"/MedicalUnicom/Records.do?doCancelRecordsInfo",
					data:{
						inquiryStatus:"5",
						recordsId:recordsId_,
						refundId:refundId,
						refundReason:refundReason,
						reasonMemo:reasonMemo
					},
					aync:false,
					dataType:"json",
					success:function(result){
						if(result.returnCode==0){
							window.location.href="Pat_MyReservation.html"
						}
					}
				})
			})
		}
	})
});