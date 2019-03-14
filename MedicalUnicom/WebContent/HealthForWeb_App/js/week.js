$(function(){
	var Sun=document.getElementById("Week");
	
	function week(){
		function GetDateStr(AddDayCount) { 
			var dd = new Date(); 
			
			dd.setDate(dd.getDate()+AddDayCount);//设定AddDayCount天后的日期 
			
			var y = dd.getYear(); 
			
			//获取当前月份的日期，不足10补0
			var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);
			
			//获取当前几号，不足10补0
			var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate(); 
			
			return d+"/"+m; 
		}
		
		function weekday(day) { 
			var dd = new Date(); 
			
			dd.setDate(dd.getDate()+day);//获取AddDayCount天后的日期 
			
			var w=dd.getDay();
			if(w==0){w="日"}
			else if(w==1){w="一"}
			else if(w==2){w="二"}
			else if(w==3){w="三"}
			else if(w==4){w="四"}
			else if(w==5){w="五"}
			else if(w==6){w="六"};
			return w; 
		}

		for(i=1;i<Sun.children.length;i++){
			Sun.children[i].innerHTML="<span>"+GetDateStr(i-1)+"</span>"+"<span class='displaynone'>"+GetDateStr(i-1)+"</span>"+"<br/>"+weekday(i-1);
		};
	};
	week();


	function sunDay(){
		var date = new Date();
		var days=date.getDate();
		var month=date.getMonth();
		var today=String(days+"/"+(month+1));
		var tomorow=String((days+1)+"/"+(month+1));

		for(i=0;i<Sun.children.length;i++){
			 if(String(Sun.children[i].innerText).indexOf(today) != -1){
				 Sun.children[i].getElementsByTagName("span")[0].innerText="今日";
				 Sun.children[i].getElementsByTagName("span")[0].style.cssText="display:inline-block;width:1rem;height:1rem;line-height:1rem;background:#f4c4c6;border:1px solid #d1d1d1;border-radius:100%;color:#fff";
			 }else if(String(Sun.children[i].innerText).indexOf(tomorow) != -1){
				 Sun.children[i].getElementsByTagName("span")[0].innerText="明日";
				 Sun.children[i].getElementsByTagName("span")[0].style.cssText="display:inline-block;width:1rem;height:1rem;line-height:1rem;background:#c4d4ed;border:1px solid #d1d1d1;border-radius:100%;color:#fff";
			 }
		}
	}
	sunDay();
});