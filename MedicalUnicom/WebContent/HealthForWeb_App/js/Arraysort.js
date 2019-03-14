$(function(){
	
	$("#grad").click(function(){
		var li=$(".List").children();
		var DocArr=[];
		for(i=0;i<li.length;i++){
			li[i].setAttribute("data_id",parseFloat($(".grad")[i].innerText));
			DocArr.push(li[i]);
		};
		DocArr.sort(function(a,b){
			return b.getAttribute("data_id") - a.getAttribute("data_id");
		});
		for(i=0;i<DocArr.length;i++){
			$(".List")[0].appendChild(DocArr[i]);
		};
	});
	
	$("#price").click(function(){
		var li=$(".List").children();
		var DocArr=[];
		for(i=0;i<li.length;i++){
			li[i].setAttribute("data_id",parseFloat($(".Vprice")[i].innerText));
			DocArr.push(li[i]);
		};
		DocArr.sort(function(a,b){
			return a.getAttribute("data_id") - b.getAttribute("data_id");
		});
		for(i=0;i<DocArr.length;i++){
			$(".List")[0].appendChild(DocArr[i]);
		};
	});
	
	$("#level").click(function(){
		var li=$(".List").children();
		var DocArr=[];
		for(i=0;i<li.length;i++){
			var Value;
			if($(".level")[i].innerText  == "主治医师"){
				Value=0;
			}else if($(".level")[i].innerText  == "副主治医师"){
				Value=1;
			}else if($(".level")[i].innerText  == "医师"){
				Value=2;
			};
			li[i].setAttribute("data_id",Value);
			DocArr.push(li[i]);
		};
		
		DocArr.sort(function(a,b){
			return a.getAttribute("data_id") - b.getAttribute("data_id");
		});
		
		for(i=0;i<DocArr.length;i++){
			$(".List")[0].appendChild(DocArr[i]);
		};
	});

})