$(function(){
	$("#navy span").click(function(){
		
		$("patient_form").hide();
		if($(this).attr("id") == "ImUser"){
			$("#navy span").attr("class","");
			$(this).attr("class","btnSelect");
			$("#doctor_form").hide();
			$("#patient_form").show();
		}
		
		if($(this).attr("id") == "ImDoctor"){
			$("#navy span").attr("class","");
			$(this).attr("class","btnSelect");
			$("#patient_form").hide();
			$("#doctor_form").css("display","block");
		}
	})
	
})

	/*
	var btn=document.getElementById("navy").getElementsByTagName("span");
	var patient=document.getElementById("patient_form");
	var doctor=document.getElementById("doctor_form");
	var detail=document.getElementById("detailForm");

	for(i=0;i<=btn.length;i++){
		btn[i].onclick=function(){addSelect(this);};
	};
	
	function addSelect(obj){
		for(i=0;i<btn.length;i++){

			if(btn[i] == obj){
				btn[i].className="btnSelect";
				patient.style.display="none";
				doctor.style.display="block";
				//detail.href="doctor_join_detail.html";
			}else{
				btn[i].className="";
				patient.style.display="block";
				doctor.style.display="none";
				//detail.href="patient_join_detail.html";
			}
		}
	}
	*/
	
