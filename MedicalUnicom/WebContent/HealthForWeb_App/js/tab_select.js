$(function(){

	var menu=$(".menu");
	var op_icon=$(".op_icon");
	var sub_menu=$(".sub_menu");

	
	for(i=0;i<menu.length;i++){
		
		menu[i].onclick=function(){tab_select(this);}
	}
	
	function tab_select(obj){
		
		for(i=0;i<menu.length;i++){
			
			if(menu[i] == obj){

				if(sub_menu[i].style.display != "block"){
					
					sub_menu[i].style.display="block";
					menu[i].getElementsByTagName("p")[0].className+=" sbl";

					if(op_icon[i].className.indexOf("minus") == -1){
						
						op_icon[i].className+=" minus";
					}
				}else{
					menu[i].getElementsByTagName("p")[0].className="clearfix";
					sub_menu[i].style.display="none";
					op_icon[i].className="op_icon";
				}

			}else{
				menu[i].getElementsByTagName("p")[0].className="clearfix";
				sub_menu[i].style.display="none";
				op_icon[i].className="op_icon";
			}
			
		}
		
	};
	
	for(j=0;j<sub_menu.length;j++){

		var Item=sub_menu[j].getElementsByTagName("li");

		for(k=0;k<Item.length;k++){
			
			Item[k].onmousedown=function(){
			
				if(this.className.indexOf("check") == -1){
				
					this.className="li"+this.parentNode.className+" "+"check";
					
				 }else{
				
					this.className="li";
				 }
			}
		}
	}

})
