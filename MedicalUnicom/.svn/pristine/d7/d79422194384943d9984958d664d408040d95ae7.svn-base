$(document).ready(function(){ 

	$(".slide").width($(window).width()+"px");
	$(".slide").height($(".slide img").height()+"px");
	$(".slide ul").width($(window).width()*$(".slide li").length+"px");
	$(".slide li").width($(window).width()+"px");

	$(window).resize(function(){
		$(".slide").width($(window).width()+"px");
		$(".slide").height($(".slide img").height()+"px");
		$(".slide ul").width($(window).width()*$(".slide li").length+"px");
		$(".slide li").width($(window).width()+"px");
	});
	
	$(".slide").append("<div id='curent'></div>");
	for(i=0;i<$(".slide li").length;i++){
		$("#curent").append("<span class='dot'></span>")
	}
	
	$(".dot").first().addClass("select");
	
	var a=0; 
	var t=0; // btn 
	function showTime(a){
		$(".slide ul").animate({"left":a+"px"},500);
	};
   
	setInterval(function(){
		if( $(".slide ul").position().left > -$(".slide li").width()*($(".slide li").length-1)){
			a=$(".slide ul").position().left;
			a=a-$(".slide li").width();
			showTime(a);
			t=t+1;
			$(".dot").eq(t-1).removeClass("select");
			$(".dot").eq(t).addClass("select");
		}else{
			$(".slide ul").animate({"left":$(".slide ul").position().left+"px"},500);
			a=0;
			$(".dot").eq(t).removeClass("select");
			$(".dot").eq(0).addClass("select");
			t=0;
			showTime(a);
		};
	},2000);

});