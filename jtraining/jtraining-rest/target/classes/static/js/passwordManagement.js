$(function(){
	/*document.onkeydown = function(e){
	    if(e.keyCode == 116){
	        e.preventDefault(); //组织默认刷新
	        location=location;
	    }
	}*/
	var no = "";
	var noOrName = "";
	var password = "";
	$(".password-border").hide();
	$(".search").click(function(){
		$("#message").html("");
		$("#no").val("");
		$("#name").val("");
		noOrName = $.trim($(".noOrName").val());
		$("#password").val("");
		if(noOrName == "%" || noOrName == ""){
			$("#message").show();
			$("#message").html("正しいユーザー名を入力してください");
			$("#message").removeClass("ok");
			$("#message").addClass("error");
			$(".password-border").hide();
		}
		getPersonInfo(noOrName);
	});
	$(".cancel").click(function(){
		$("#message").html("");
		$("#no").val("");
		$("#name").val("");
		$("#password").val("");
		$(".password-border").hide();
	});
	$(".update").click(function(){
		$("#message").html("");
		password = $("#password").val();
		if(password != "") {
			updatePassword(no);
		} else {
			$("#message").html("パスワードは空にできません");
			$("#message").removeClass("ok");
			$("#message").addClass("error");
		}
	});
	
	//查询事件方法
	function getPersonInfo(noOrName) {
		$.ajax({
			type : "get",
			url : "http://49.4.109.191:8080/jtrain/v1/api/password/get/" + noOrName,
			dataType : "json",
			contentType : "application/json",
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
			success : function(result) {
				if(result.statusCode == "200") {
					var personInfo = result.data;
					if(personInfo != null) {
						$("#message").hide();
						no = personInfo.no;
						var name = personInfo.name;
						if(null == no){
							$(".password-border").hide();
							$("#message").html("管理者のパスワードをリセットできません");
							$("#message").removeClass("ok");
							$("#message").addClass("error");
						}else{
							$(".password-border").show();
							$("#no").val(no);
							$("#name").val(name);
						}
					} else {
						$(".password-border").hide();
						$("#message").show();
						$("#message").html("照会ユーザーが存在しません");
						$("#message").removeClass("ok");
						$("#message").addClass("error");
					}
				}
				if(result.statusCode=="401"){
					alert("あなたのアカウント接続は異常です,もう一度ログインしてください");
					window.location.href="http://49.4.109.191:8080/jtrain/html/login.html";
				}
			}
		});
	}
	
	//修改事件方法
	function updatePassword(no) {
		$.ajax({
			type : "put",
			url : "http://49.4.109.191:8080/jtrain/v1/api/password/update",
			dataType : "json",
			contentType :"application/json",
			data:JSON.stringify({"no":no ,"password":password}),
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
			success : function(result) {
				if(result.statusCode == "200") {
					$("#message").show();
					$("#message").html("正常に更新されました");
					$("#message").removeClass("error");
					$("#message").addClass("ok");
				}
				if(result.statusCode=="401"){
					alert("あなたのアカウント接続は異常です,もう一度ログインしてください");
					window.location.href="http://49.4.109.191:8080/jtrain/html/login.html";
				}
			}
		});
	}
});