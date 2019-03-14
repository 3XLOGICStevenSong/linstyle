$(document).ready(function(){

	$("#User").on("click",function(){
		$("#aside").css("display","block");
		$("body").css("overflow-y","hidden");
		$("html").css("overflow-y","hidden");
		$(".sideMenu").css({"-webkit-transition":"left 0.25s linear"});
		$(".sideMenu").css({"left":"0"});
		//$(".sideMenu").animate({"left":"0"},300,false);
	});
	
	$("#aside").on("click",function(){
		$(".sideMenu").css({"-webkit-transition":"left 0.25s linear"});
		$(".sideMenu").css({"left":"-50%"});
		$("#aside").css("display","none");
		$("body").css("overflow-y","");
		$("html").css("overflow-y","");
		/*
		$(".sideMenu").animate({"left":"-50%"},300,function(){
			$("#aside").css("display","none");
			$("body").css("overflow-y","");
			$("html").css("overflow-y","");
		});
		*/
	});
	
});



