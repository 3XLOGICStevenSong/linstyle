$(function(){

	$("body").delegate(".opener","click",function(){
		if($(this).attr("class") == "opener") {
			$(".opener span").animate({"left":$(this).width()-$(".opener span").width()-2},150,function(){
				$(".opener").addClass("On");
			});
		}else{
			$(".opener span").animate({"left":2},200,function(){
				$(".opener").removeClass("On");
			});
		}
	})

})