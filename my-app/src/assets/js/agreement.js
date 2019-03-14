function agreementModal(){
	$("#agreementModal").modal("show");
}

function agrAbroadModal(){
	$("#agrAbroadModal").modal("show");
}

$(function(){
	// 协议查询 显示信息
	$(".searchAgreement").click(function(){
	    $(".agreement_info").show();
	})

	// 协议查询 报名 跳转
	$(".signUp").click(function(){ 
	    window.location.href="agreement_add.html";
	})

	// 添加其他协议报名人员 添加人员信息
    $(".agreeChk").click(function(){
		var $agreeChk=$(".UserList tr").find("input[type=checkbox]:checked");
		if($agreeChk.length == 0){
			$("#agreementModal").modal("hide");
			$(".addUserList").hide();
			return;
		};
		var list="";
		for(i=0;i<$agreeChk.length;i++){
			var name=$agreeChk.eq(i).parents("tr").find(".name").text();
			var code=$agreeChk.eq(i).parents("tr").find(".code").text();
			list+="<li><p class='userName'>"+name+"</p><p class='usercode'>(<span>"+code+"</span>)</p><span class='del_icon'>X</span></li>";
		}
		$("#agreementModal").modal("hide");
		$(".addUserList").show();
		$(".addUserList .total").find("span").text($agreeChk.length);
		$(".addUserList .users").html("");
		$(".addUserList .users").html(list);
	})

	// 添加赴日协议报名人员 添加人员信息
    $(".agrAbroadChk").click(function(){
		var $agreeChk=$(".UserList tr").find("input[type=radio]:checked");
		if($agreeChk.length == 0){
			$("#agrAbroadModal").modal("hide");
			return;
		};

		var name=$agreeChk.parents("tr").find(".name").text();
		var code=$agreeChk.parents("tr").find(".code").text();

		$(".abroadName").val(code+" "+name);
		$(".abroadHistory").show();
		$(".empty").show();
		$("#agrAbroadModal").modal("hide");
	})

    // 添加报名人员 选择报名类型时 隐藏显示 赴日条件
	$(".abroadSelect").change(function(){
		$(".addUserList").hide();
		$(".UserList li").find("input:checked").removeAttr("checked");
		$(".abroadName").val("");
		$(".abroadHistory").hide();
		$(".empty").hide();

		if($(this).val() < 2){
			$(".abroad").show();
			$(".abroadSubmit .btn-success").show();
			$(".abroadSubmit .btn-primary").hide();
		}else{
			$(".abroad").hide();
			$(".abroadSubmit .btn-success").hide();
			$(".abroadSubmit .btn-primary").show();
		}
	})

	// 添加报名人员 删除人员时 同步弹窗内checked
	$(document).delegate(".del_icon","click",function(){
		$(this).parents("li").remove();
		var count=$(".addUserList .users").find("li").length;
		if(count == 0){
			$(".addUserList").hide();
		}
		$(".addUserList .total").find("span").text(count);

		var code=$(this).parents("li").find(".usercode").find("span").text();

		$(".UserList tr").find("input[value='"+code+"']").removeAttr("checked");
	})

	// 添加其他协议报名人员 提交
	$(".addUserList .otherAdd").click(function(){
		window.location.href="agreement_search.html";
	})

	// 添加赴日协议报名人员 提交
	$(".abroadSubmit .abrBtn").click(function(){
		window.location.href="agreement_detail.html";
	})

	// 添加报名人员 弹窗内全选
	var chk=true;
	$("#chkAll").click(function(){

        if(chk){
            $(".UserList tr").find("input[type=checkbox]").removeAttr("checked");
            $(".UserList tr").find("input[type=checkbox]").trigger("click");
            chk=false;
        }else{
            $(".UserList tr").find("input[type=checkbox]").trigger("click");
            $(".UserList tr").find("input[type=checkbox]").removeAttr("checked");
            chk=true;
        }
    })

	// 个人协议详情 编辑按钮
    $(document).delegate(".agreeEdit","click",function(){
    	$(this).hide();
    	$(this).parents("tr").find("input").removeClass("clearStyle");
    	$(this).parents("tr").find("input").removeAttr("readonly");
    	$(this).parents("tr").find(".laydate-icon").attr("onclick","laydate()")
    	$(this).parents("td").find(".agreeSave").show();
    })
    // 个人协议详情 保存按钮
    $(document).delegate(".agreeSave","click",function(){
    	$(this).hide();
    	$(this).parents("tr").find("input").addClass("clearStyle");
    	$(this).parents("tr").find("input").attr("readonly","readonly");
    	$(this).parents("tr").find(".laydate-icon").removeAttr("onclick")
    	$(this).parents("td").find(".agreeEdit").show();
    })
    // 个人协议详情 添加协议按钮
    $(".addNewagree").click(function(){
    	window.location.href="agreement_add.html"
    })
    // 个人协议详情 结束全部协议按钮
    $(".endAllagree").click(function(){
    	if($(".clearStyle").length < 22){
    		$("#myModal").modal("show");
    	}else{
    		$(".finishTime").show();
	    	$(".EditArea").hide();
	    	$(".agreeStatus").text("已结束");
	    	$(this).parents(".btnArea").hide();
    	}
    })
    // 个人协议详情 结束全部 提示框 确认按钮
    $(".okSuccess").click(function(){
		$("#myModal").modal("hide");
    })

	// 协议一览 追加
	$(".newAdd").click(function(){
		var addAgree=$(this).parents("form").find(".module").html();
		$(this).parents("form").find("tbody").append("<tr>"+addAgree+"</tr>");
	})

	// 协议一览 取消追加
	$(document).delegate(".cancelBtn","click",function(){
		$(this).parents("tr").remove();
	})
	

    $(document).delegate(".delBtn","click",function(){
    	$("#myModal").modal("show");

    	$that=$(this).parents("tr");

    	$(".delSuccess").click(function(){
    		$("#myModal").modal("hide");
    		$that.remove();
    	})
    	//$(this).parents("tr").remove();
    })

    // 协议一览 添加协议
    $(document).delegate(".addSuccess","click",function(){

		var name=$(this).parents("tr").find(".name").find("input").val();
		var year=$(this).parents("tr").find(".year").find("input").val();
		var price="";
		var func=$(this).parents("tr").find(".function").find("input").val();

		if($(this).parents("tr").find(".price").length < 1){
			price="0";
		}else{
			price=$(this).parents("tr").find(".price").find("input").val();
		};

		if(name!= null && year!= null && price!= null && func!= null && name!= "" && year!= "" && price!= "" && func!= ""){
			$(this).hide();
			$(this).parents("tr").find("input").addClass("clearStyle");
			$(this).parents("tr").find("input").attr("readonly","readonly");
			$(this).parents("tr").find(".status").text("使用中");
			$(this).parents("tr").find(".status").removeClass("stoped")
			$(this).parents("td").find(".stopUse").show();
			$(this).parents("td").find(".delBtn").show();
			$(this).parents("td").find(".cancelBtn").hide();
		}
		
    })

    // 协议一览 开始使用按钮
    $(document).delegate(".startUse","click",function(){
    	$(this).hide();
    	$(this).parents("tr").find(".status").text("使用中");
    	$(this).parents("tr").find(".status").removeClass("stoped")
		$(this).parents("td").find(".stopUse").show();
    })
    // 协议一览 暂停使用按钮
    $(document).delegate(".stopUse","click",function(){
    	$(this).hide();
    	$(this).parents("tr").find(".status").text("暂停使用");
    	$(this).parents("tr").find(".status").addClass("stoped")
		$(this).parents("td").find(".startUse").show();
    })
})