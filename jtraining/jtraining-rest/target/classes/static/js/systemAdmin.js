
$(function(){
	document.onkeydown = function(e){
	    if(e.keyCode == 116){
	        e.preventDefault(); //组织默认刷新
	        location.reload();
	    }
	}
	$(".update").on("click",function(){
		var noOrName = $(".noOrName").val();
	    var oldPassword = $("#oldPassword").val();
	    if(oldPassword==""){
	    	$("#message").html("古いパスワードは空にできません");
	    	$("#message").removeClass("ok");
	    	$("#message").addClass("error");
	    	return;
	    }
	    var newPassword = $("#newPassword").val();
	    if(newPassword==""){
	    	$("#message").html("新しいパスワードを空にすることはできません");
	    	$("#message").removeClass("ok");
	    	$("#message").addClass("error");
	    	return;
	    }
	    var confirmPassword = $("#confirmPassword").val();
	    if(confirmPassword==""){
	    	$("#message").html("パスワードを空にすることはできません");
	    	$("#message").removeClass("ok");
	    	$("#message").addClass("error");
	    	return;
	    }
	    if(newPassword != confirmPassword){
	    	$("#message").html("入力パスワードの不一致");
	    	$("#message").removeClass("ok");
	    	$("#message").addClass("error");
	    	return;
	    }
	    var data={
	    	"no":noOrName,
	    	"password":oldPassword,
	    	"newPassword":newPassword
	    };
	    $.ajax({
			type : "put",
			url : "http://49.4.109.191:8080/jtrain/v1/api/password/change",
			dataType : "json",
			contentType : "application/json",
			data:JSON.stringify(data),
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
			success : function(result) {
				if(result.statusCode == "200") {
					$("#message").html("パスワードを更新しました");
			    	$("#message").removeClass("error");
			    	$("#message").addClass("ok");
				}
				if(result.statusCode=="401"){
					alert("あなたのアカウント接続は異常です,もう一度ログインしてください");
					window.location.href="http://49.4.109.191:8080/jtrain/html/login.html";
				}
				if(result.statusCode == "512") {
					$("#message").html("旧パスワードエラー");
			    	$("#message").removeClass("ok");
			    	$("#message").addClass("error");
			    	$("#oldPassword").val("");
			    	$("#oldPassword").focus();
				}
			}
		});
	});	
	$("#oldPassword").on("input propertychange",function(){
		this.value=this.value.replace(/[^0-9a-zA-Z]/,'');
    });
	$("#newPassword").on("input propertychange",function(){
		this.value=this.value.replace(/[^0-9a-zA-Z]/,'');
	});
	$("#confirmPassword").on("input propertychange",function(){
		this.value=this.value.replace(/[^0-9a-zA-Z]/,'');
	});
	$(".cancel").on("click",function(){
		$("#oldPassword").val("");
		$("#newPassword").val("");
		$("#confirmPassword").val("");
	});
});
