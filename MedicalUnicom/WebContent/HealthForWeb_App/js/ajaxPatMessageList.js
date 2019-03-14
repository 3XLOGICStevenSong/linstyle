$(function(){
	var pid=getCookie("pid");
	
	function getCookie(name){

	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	 
	    if(arr=document.cookie.match(reg))
	 
	        return (arr[2]);
	    else
	        return null;
	}
	
	function getDocList(listPage,listSize){
		$.ajax({
			type:"get",
			url:"/MedicalUnicom/Appoint.do?doGetPrivateDoctor",
			data: {
				patientId:pid,
				pageNum:listPage,
				pageSize:10
			},
			async: false,
			dataType: "json", // json or text or xml
			success:function(result){
				var Doclist=result.doctorList;
				if(Doclist == null ){
					alert("没有更多");
					return false ;
				}
				var Item=new Array();
				
				var content=$("#List").html();
				
				for(i=0;i<Doclist.length;i++){

					var doctorId=Doclist[i].doctorId;
					var headPic=Doclist[i].headPic;
					var name=Doclist[i].name;
					var departmentName=Doclist[i].departmentName;
					var positional=Doclist[i].positional;
					var healDisease=Doclist[i].healDisease;
					var hospitalName=Doclist[i].hospitalName

					Item[i]=content;
					
					for(Val=0;Val<Item.length;Val++){
						Item[Val]=Item[Val].replace("{$headPic}",headPic);
						Item[Val]=Item[Val].replace("{$name}",name);
						Item[Val]=Item[Val].replace("{$departmentName}",departmentName);
						Item[Val]=Item[Val].replace("{$positional}",positional);
						Item[Val]=Item[Val].replace("{$healDisease}",healDisease);
						Item[Val]=Item[Val].replace("{$hospitalName}",hospitalName);
					}

				}

				$(".List").html(Item);
				
				$(".UserImg img").on("error",function(){
					$(this).attr("src","img/defaultpatient.png")
				})
			}
		})
		
	};
	
	var listPage=0;
	var listSize=10;
	
	getDocList(listPage,listSize);

	$("#MoreBtn").click(function(){

		listPage=listPage+10;
		getDocList(listPage,listSize);
	})

})