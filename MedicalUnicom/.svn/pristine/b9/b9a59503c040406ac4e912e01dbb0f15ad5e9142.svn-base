$(function(){
	var Sun=document.getElementById("week");
	var SunTime=document.getElementById("Tm");
	var tm=0;
	function week(){

		function GetDateStr(AddDayCount) { 
			var dd = new Date(); 
			
			dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期 
			
			var y = dd.getFullYear(); 
			
			//获取当前月份的日期，不足10补0
			var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);
			
			//获取当前几号，不足10补0
			var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate(); 
			
			return y+"年"+m+"月"+d+"日"; 
		}
		
		function weekday(day) { 
			var dd = new Date(); 
			
			dd.setDate(dd.getDate()+day);//获取day天后的日期 
			
			var w=dd.getDay();
			if(w==0){w="一"}
			else if(w==1){w="二"}
			else if(w==2){w="三"}
			else if(w==3){w="四"}
			else if(w==4){w="五"}
			else if(w==5){w="六"}
			else if(w==6){w="日"};
			return "星期"+w; 
		}

		SunTime.innerHTML=GetDateStr(tm)+" ";
		Sun.innerHTML=weekday(tm-1);

	};
			
	$(".left").click(function(){
		tm=tm-1;
		week();
		$("#TmList li").removeClass("checkOn");
	});
	$(".right").click(function(){
		tm=tm+1;
		week();
		$("#TmList li").removeClass("checkOn");
	});

	week();
	
	//时间表格js//
	var selTime=$("#selTime");
	
	function CreatLi(){
		var ul=$("<ul id='TmList' class='clearfix'></ul>");
		var h=7;
		var m=45;

		for(i=0;i<35;i++){
			
			var li=$("<li></li>");
			
			m=parseInt(m)+15;
			
			if(m == 60){
				h=parseInt(h)+1;
				m="00";
			}
			
			ul.append( li.text( (h<10? "0":"") + h + ":" + m ) );
		};
		
		selTime.append(ul);
		
		$("#TmList li").click(function(){
			$(this).toggleClass("checkOn");
		})
	}
	CreatLi();
});