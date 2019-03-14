$(function(){

	for(i=0;i<$(".UserName").length;i++){
			
		var txt=$(".UserName");

		if(txt[i].innerText.length == 3){
			txt[i].innerText=txt[i].innerText.replace(txt[i].innerText.substring(1,txt[i].innerText.length-1),"*");
		}else if(txt[i].innerText.length < 3){
			txt[i].innerText=txt[i].innerText.replace(txt[i].innerText.substring(0,txt[i].innerText.length-1),"*");
		}else if(txt[i].innerText.length > 3){
			txt[i].innerText=txt[i].innerText.replace(txt[i].innerText.substring(1,txt[i].innerText.length-1),"**");
		}

	}
	
})