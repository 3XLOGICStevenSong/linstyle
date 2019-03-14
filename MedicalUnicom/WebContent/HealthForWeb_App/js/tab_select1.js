$(document).ready(function(){

	$(".menu").each(function(){
		
		$(this).on("click",function(){
			
			$(this).children("span").toggleClass("minus");
	
			if($(this).siblings("div.sub_menu").css("display") != "block"  ){
				
				$(this).siblings("div.sub_menu").css("display","block");
			}else{
				
				$(this).siblings("div.sub_menu").css("display","none");
			}

		})
		
	})
	
	$("label").each(function(){

		$(this).on("click",function(){
			
			$(this).parent().toggleClass("li"+$(this).parent().parent().attr('class')+" check");
			
		})
		
	})
	
});