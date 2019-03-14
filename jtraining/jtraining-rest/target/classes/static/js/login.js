$(function(){	
	$(".login").on("click",function(){
		var userName=$("#userName").val();
		var password=$("#password").val();
		if(userName != "Admin"){
			alert("許可がありません");
			return;
		}
		var data={
			"no":userName,
			"password":password
		}
		$.ajax({
			  type:"post",
              url:"http://49.4.109.191:8080/jtrain/v1/api/login",
              async: false,
              dataType: "json",
              contentType:"application/json",
              data:JSON.stringify(data),
              success:function(result,status,httpResponse){
            	if(result.statusCode=="200"){
            		localStorage.setItem("token",httpResponse.getResponseHeader('Authorization'));
            		window.location.href="http://49.4.109.191:8080/jtrain/html/main.html";
            	}else{
            		alert("ユーザー名またはパスワードが正しくありません");
            	}
            }
		});
	});
	$("body").keydown(function(event) {
		e = event ? event : (window.event ? window.event : null);
	    if (e.keyCode == "13") {//keyCode=13是回车键
	    	var userName=$("#userName").val();
			var password=$("#password").val();
			if(userName != "Admin"){
				alert("許可がありません");
				return;
			}
			var data={
				"no":userName,
				"password":password
			}
			$.ajax({
				  type:"post",
	              url:"http://49.4.109.191:8080/jtrain/v1/api/login",
	              async: false,
	              dataType: "json",
	              contentType:"application/json",
	              data:JSON.stringify(data),
	              success:function(result,status,httpResponse){
	            	  if(result.statusCode=="200"){
	            		  localStorage.setItem("token",httpResponse.getResponseHeader('Authorization'));
	            		  window.location.href="http://49.4.109.191:8080/jtrain/html/main.html";
	            	  }else{
	            		  alert("ユーザー名またはパスワードが正しくありません");
	            	  }
	              }
			});
	    }
	}); 
});