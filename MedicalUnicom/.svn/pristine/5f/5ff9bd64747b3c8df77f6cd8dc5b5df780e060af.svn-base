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
	
	var Li="";
	for(i=0;i<24;i++){
		Li+="<li>"+(i<10? "0"+i:i)+":00"+"</li>"
	}
	$("#time ul").html(Li);
	
	for(t=0;t<$("#time li").length;t++){
		var Tr=$("<tr></tr>");
		for(d=0;d<7;d++){
			var Td=$("<td></td>")
			Tr.append(Td)
		}
		$("tbody").append(Tr);
	}

	
	var date=new Date();
	var week=date.getDay();
	date.setDate(date.getDate()-week-1);
	var list="";
	$("#calen td").text("");
	function Setday(){
		
		for(i=0;i<7;i++){
			date.setDate(date.getDate()+1);
			
			$("#week .days")[i].innerText=date.getDate()+"/"+(date.getMonth()+1);
			$("#week .data")[i].innerText=date.getDate();
			$("#week .data1")[i].innerText=date.getMonth()+1;
			$("#week strong")[i].innerText=date.getDate()+"/"+(date.getMonth()+1);
			
			list+=(date.getFullYear()+"-"+(date.getMonth()+1))+"-"+date.getDate()+"|";
		}

		for(k=0;k<7;k++){
			var dat=new Date();
			var M=dat.getDate();
			var P=dat.getDate()+1;
			var D=dat.getMonth()+1;
			
			if($("#week .data")[k].innerText == M && $("#week .data1")[k].innerText == D) {
				$("#week .days")[k].innerText="今日";
				$("#week .days")[k].className+=" today";
			}else if($("#week .data")[k].innerText == P && $("#week .data1")[k].innerText == D){
				$("#week .days")[k].innerText="明日";
				$("#week .days")[k].className+=" nextday";
			}else{
				$("#week .days")[k].className="days";
			}
		}

		NextDate(list);
		
	}
	
	Setday();
	
	
	$("#next").click(function(){
		
		var t=$("#week .data")[0].innerText;
		var m=$("#week .data1")[0].innerText;
		list="";
		$("#calen td").text("");
		function Setday1(t){
			var date1=new Date();
			date1.setMonth(m-1);
			date1.setDate(t);
			date1.setDate(date1.getDate()+7-1);

			for(i=0;i<7;i++){
				date1.setDate(date1.getDate()+1);
				
				$("#week .days")[i].innerText=((date1.getDate()<10?"0":"")+date1.getDate())+"/"+((date1.getMonth()+1)<10?"0":"")+(date1.getMonth()+1);
				$("#week .data")[i].innerText=date1.getDate();
				$("#week .data1")[i].innerText=date1.getMonth()+1;
				$("#week strong")[i].innerText=((date1.getDate()<10?"0":"")+date1.getDate())+"/"+((date1.getMonth()+1)<10?"0":"")+(date1.getMonth()+1);
				
				list+=(date1.getFullYear()+"-"+((date1.getMonth()+1)<10?"0":"")+(date1.getMonth()+1)+"-"+((date1.getDate()<10?"0":"")+date1.getDate())+"|");
				
				var dat=new Date();
				var M=dat.getDate();
				var P=dat.getDate()+1;
				var D=dat.getMonth()+1;
				if($("#week .data")[i].innerText == M && $("#week .data1")[i].innerText == D) {
					$("#week .days")[i].innerText="今日";
					$("#week .days")[i].className+=" today";
				}else if($("#week .data")[i].innerText == P && $("#week .data1")[i].innerText == D){
					$("#week .days")[i].innerText="明日";
					$("#week .days")[i].className+=" nextday";
				}else{
					$("#week .days")[i].className="days";
				}
			}
			
			NextDate(list);
		};
		
		Setday1(t);
		
	})
	
	$("#prev").click(function(){
	
		var t=$("#week .data")[0].innerText;
		var m=$("#week .data1")[0].innerText;
		list="";
		$("#calen td").text("");
		function Setday2(t){
			var date1=new Date();
			date1.setMonth(m-1);
			date1.setDate(t);
			date1.setDate(date1.getDate()-7-1);

			for(i=0;i<7;i++){
				date1.setDate(date1.getDate()+1);
				
				$("#week .days")[i].innerText=((date1.getDate()<10?"0":"")+date1.getDate())+"/"+((date1.getMonth()+1)<10?"0":"")+(date1.getMonth()+1);
				$("#week .data")[i].innerText=date1.getDate();
				$("#week .data1")[i].innerText=date1.getMonth()+1;
				$("#week strong")[i].innerText=((date1.getDate()<10?"0":"")+date1.getDate())+"/"+((date1.getMonth()+1)<10?"0":"")+(date1.getMonth()+1);
				
				list+=(date1.getFullYear()+"-"+((date1.getMonth()+1)<10?"0":"")+(date1.getMonth()+1)+"-"+((date1.getDate()<10?"0":"")+date1.getDate())+"|");
				
				var dat=new Date();
				var M=dat.getDate();
				var P=dat.getDate()+1;
				var D=dat.getMonth()+1;
				if($("#week .data")[i].innerText == M && $("#week .data1")[i].innerText == D) {
					$("#week .days")[i].innerText="今日";
					$("#week .days")[i].className+=" today";
				}else if($("#week .data")[i].innerText == P && $("#week .data1")[i].innerText == D){
					$("#week .days")[i].innerText="明日";
					$("#week .days")[i].className+=" nextday";
				}else{
					$("#week .days")[i].className="days";
				}
			}
			
			NextDate(list);
		};
		
		Setday2(t);

	})
	
	function NextDate(list){

		var tList=list;

		$.ajax({
			type:"get",
			url:"/MedicalUnicom/DoctorSchedule.do?doGetScheduleList",
			data:{
				doctorId:pid,
				startDateList:tList
			},
			aync:false,
			dataType:"json",
			success:function(result){

				var Met=result.appointTimeList;

				/*
				"startDate":"2016-10-11",
				"timeList":[{
				"startTime":"04:00",
				"endTime":"04:59",
				"patientNum":6,
				"scheduleId":281,
				"appointNum":4
				*/
				if(Met != null){

					for(i=0;i<Met.length;i++){
						
						var startDate=Met[i].startDate;
						
						var Day=String(startDate.substring(8,startDate.length)+"/"+startDate.substring(5,7));
						
						var timeList=Met[i].timeList;
				
						var Days=$("#week li");
						var col="";
						
						for(c=0;c<Days.length;c++){

							if(Days[c].getElementsByTagName("strong")[0].innerText.indexOf(Day) != -1 ){
								col=c;
							}
							
						}
						
						var Time=$("#time li");
						var row="";
						
						for(k=0;k<timeList.length;k++){
				
							var startTime=timeList[k].startTime;
							var patientNum=timeList[k].patientNum;
							
							for(r=0;r<Time.length;r++){
								if(Time[r].innerText.indexOf(startTime) != -1 ){
									row=r;
								}
							}
							
							$("#calen tr")[row].children[col].innerText=patientNum+"人";
							$("#calen tr")[row].children[col].className="On";
							
						}
					}
					
				}else{
					$("#calen td").text("");
				}
			}
		})
	}
})