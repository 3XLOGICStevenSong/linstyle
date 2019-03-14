//头部下拉菜单
$(function(){
	$(".down_menu").hide();
	$(".top .drop_down").click(function(e){
		$(".down_menu").toggle();
		e.stopPropagation();
	});
	$(document).click(function(){ 
    $(".down_menu").hide(); 
  }) 
})

//通知一览删除//类型删除（通知和活动)
var tr="";
function launchModal(nowTr){
	$("#myModal").modal('show');
	tr=$(nowTr).parents("tr");	
 }
function deleteTr(){
	var table=tr.parents("table");
	var num=tr.parents("table").find("tbody").find("tr").length;
	tr.remove();
	$("#myModal").modal('hide');	
	for(i=0;i<num;i++){
		table.find("tbody").find("tr").eq(i).find("td").eq(0).text(i+1);
	}
}
 
//删除角色
var tr="";
function launchRoles(nowTr){
	$("#myModal").modal('show');
	tr=$(nowTr).parents("tr");	
 }
function deleteRoles(){
	$("#myModal").modal('hide');
	tr.remove();
 }

//报名人员删除  
var tr="";
function launchDelete(nowTr){
	$("#myModal").modal('show');
	tr=$(nowTr).parents(".fill_main");
  }
 function deletePerson(){
	$("#myModal").modal('hide');
	tr.remove();
 }

//上传文件 
$(function(){
	$("input[type=file]").change(function(){$(this).parents(".uploader").find(".filename").val($(this).val());});
	$("input[type=file]").each(function(){
	if($(this).val()==""){$(this).parents(".uploader").find(".filename").val("未选择文件");}
	});
});

//类型追加（通知和活动）
$(document).ready(function(){
	$("#btnAddRow").click(function(){
		$("table tbody").append("<tr>"+$("#module").html()+"</tr>");
		var num=$("tbody tr").length;
		for(i=0;i<num;i++){
			$("tbody tr").eq(i).find("td").eq(0).text(i+1);			
		}
});	
//活动报名人员追加
	var count=0;
	$("#btnAddRow").click(function(){
		count=count+1;
		$(".fill").append("<div class='fill_main line"+count+"'>"+$("#module .fill_main").html()+"</div>");
		var name=$(".line"+count).find('input[name=inlineRadioOptions]').attr("name");
		var newName=name+"_"+count;
		$(".line"+count).find('input[name=inlineRadioOptions]').attr("name",newName);
	});	
});

//人员信息>个人信息一览更新保存
$(document).ready(function(){
  $(".personInput").hide();
  $(".personDetail").hide();
  $(".updateBtn").click(function(){
  	$(this).hide();  
	$(this).parents(".info").find(".personPrompt").hide();
	$(this).parents(".info").find(".personDetail").hide();
	$(this).parents(".info").find(".personInput").show();
	$(".updateBtn").attr("disabled","disabled");
  });
  
  $(".saveBtn").click(function(){
	$(this).parents(".info").find(".personDetail").show();
	$(this).parents(".info").find(".updateBtn").show();
	$(this).parents(".info").find(".personInput").hide();
	$(".updateBtn").removeAttr("disabled");
  });
  $(".cancelBtn").click(function(){
	$(this).parents(".info").find(".personPrompt").show();
	$(this).parents(".info").find(".updateBtn").show();
	$(this).parents(".info").find(".personInput").hide();
	$(".updateBtn").removeAttr("disabled");
  });

})

//人员信息>个人信息一览>入职信息出差离职select事件
$(document).ready(function(){
	var s=$('#mySelect').val();
	if(s=="0"){
	    $(".quit").hide();
		$(".travel").hide();	
		}
    if(s=="1"){
	    $(".quit").hide();
		$(".travel").show();		
		}
	if(s=="2"){
	    $(".quit").show();
		$(".travel").hide();
	}	
	$('#mySelect').change(function(){ 
	var p1=$(this).children('option:selected').val();//这就是selected的值 
    if(p1=="0"){
	    $(".quit").hide();
		$(".travel").hide();
		}
    if(p1=="1"){
	    $(".quit").hide();
		$(".travel").show();		
		}
	if(p1=="2"){
	    $(".quit").show();
		$(".travel").hide();
	}	
  }) 
})

//人员信息>自定义检索条件>select事件
$(document).ready(function(){
	var c=$('.myChoose').val();
	$('.myChoose').next().show();
	$('.myChoose').next().next().hide();
	if(c=="0"){
	    $(this).next().show();
		$(this).next().next().hide();	
		}
    if(c=="1"){
	    $(this).next().show();
		$(this).next().next().hide();		
		}
	if(c=="2"){
	    $(this).next().hide();
		$(this).next().next().show();
	}	
	$('.myChoose').change(function(){ 
	var c1=$(this).children('option:selected').val();//这就是selected的值 
    if(c1=="0"){
	    $(this).next().show();
		$(this).next().next().hide();	
		}
    if(c1=="1"){
	    $(this).next().show();
		$(this).next().next().hide();	
		}
	if(c1=="2"){
	    $(this).next().hide();
		$(this).next().next().show();
	}	
  }) 
})

//人员信息>个人信息一览>入职信息追加合同删除合同事件
$(document).ready(function(){
	$(document).delegate(".plusBtn","click",function(){
		var num = $(".contractbox .contract").length + 1;
		$(".contractbox").append('<div class="space clearfix contract"><div class="col-4 more_text"><label>合同<span>'+num+'</span>期限</label>&nbsp<input class="form-control laydate-icon"  onclick="laydate()">&nbsp;至&nbsp;<input class="form-control laydate-icon"  onclick="laydate()"><span class="remove_btn minusBtn" style="margin-left:10px"><i class="glyphicon glyphicon-minus"></i></span></div></div>');		
	});
	$(document).delegate(".minusBtn","click",function(){
		var num = $(".contractbox .contract").length;
		$(this).parents(".contract").remove();   
		for(i=0;i<=num;i++){
			$(".contractbox .contract").eq(i).find("label").find("span").text(i+1);
			}
	})
});	


//人员信息>员工信息>自定义检索添加删除条件
function definedProject(){
	$("#myDefined").modal('show');
  }  
$(document).ready(function(){
	$(".checkall").click(
	  function(){
		$("input[name='checkname']").prop("checked",this.checked);
		if(this.checked){
			$("input[name='checkname']").attr('checked', true)
		}else{
			$("input[name='checkname']").attr('checked', false)
		}
	  }
	);
})
$(document).delegate(".addDefined","click",function(){
	var chks=$(".modal_search").find("input[type=checkbox]:checked");
	var list=new Array();
	for(i=0;i<chks.length;i++){
		list[i]=chks.eq(i).attr("class");
	}
	$("#myDefined").modal('hide');
	showDefined(list);
})
function showDefined(arr){
	$("#definedSearch .addp").hide();
	for(j=0;j<arr.length;j++){
		for(i=0;i<$("#definedSearch .addp").length;i++){
			if($("#definedSearch .addp").eq(i).attr("date") == arr[j]){
				$("#definedSearch .addp").eq(i).show();
			}
		}
	}
}

//人员信息>员工信息>自定义检索>删除检索条件
$(document).ready(function(){
	$(".remove_btn").click(function(){
		var chk=$(this).parents(".addp").attr("date");
		$(this).parents(".addp").hide();
		$(".modal_search").find("."+chk).removeAttr("checked");
		//$(".modal_search").find("."+chk).removeAttr("checked");
  	});
})

//人员信息>员工信息>添加览更多项目
function addProject(){
	$("#myModal").modal('show');
  }
$(document).ready(function(){
	$(".checkall").click(
	  function(){
		$("input[name='checkname']").prop("checked",this.checked);
		if(this.checked){
			$("input[name='checkname']").attr('checked', true)
		}else{
			$("input[name='checkname']").attr('checked', false)
		}
	  }
	);
}) 
$(document).delegate(".addchk","click",function(){
	var chks=$(".modal_list").find("input[type=checkbox]:checked");
	var list=new Array();
	for(i=0;i<chks.length;i++){
		list[i]=chks.eq(i).attr("class");
	}
	$("#myModal").modal('hide');
	showTh(list);
})
function showTh(arr){
	$(".table th").hide();
	$(".table td").hide();
	for(i=0;i<arr.length;i++){
		$(".table").find("."+arr[i]).show();
	}
}
//人员信息>员工信息>搜索展开收起
$(function(){
	$(".openBtn").hide();
	$(".retractBtn").click(function(){
	  $(".search_info").slideUp("slow");	
	  $(".retractBtn").hide();
	  $(".openBtn").show();
	});
	  $(".openBtn").click(function(){
	  $(".search_info").slideDown("slow");	
	  $(".openBtn").hide();
	  $(".retractBtn").show();
	});
})
//人员信息>员工信息>升序降序
$(function(){	
	$(".sort_info th").click(function(){
		if($(this).attr("date") == null){
			$(this).attr("date","asc");
			$(".sort_info th").find("button").hide();
			$(this).find(".asc").show();
		}else{
			if( $(this).attr("date") == "asc" ){
				$(".sort_info th").attr("date","desc");
				$(this).attr("date","desc");
				$(".sort_info th").find("button").hide();
				$(this).find(".desc").show();
				return false;	
			}
			
			if( $(this).attr("date") == "desc" ){
				$(".sort_info th").attr("date","desc");
				$(this).attr("date","asc");
				$(".sort_info th").find("button").hide();
				$(this).find(".asc").show();
				return false;	
			}
		}
		
		
    });
	
})

//角色授予>描述查看显示
$(document).ready(function(){
  $(".describe").hide();
  $(".see").hide();
  $(".describeBtn").click(function(){
  	$(".describe").show();
	$(".see").hide();  
  });
  $(".seeBtn").click(function(){
  	$(".see").show();
	$(".describe").hide();  
  });
  $(".closeBtn").click(function(){
	$(".describe").hide(); 
	$(".see").hide();   
  });
}) 

//角色授予>重置
$(document).ready(function(){
  $(".reset").click(function(){
  	$(".selectRole").show();
	$(".see").hide();  
  });   
}) 


//站内消息>消息设置修改
$(document).delegate(".modifyBtn","click",function(){
	$(".modifyBtn").hide();
	$(".stationModify").find("input").removeClass("clearstyle");
	$(".stationModify").find("input").removeAttr("readonly");
	$(this).parents().find(".stationSave").show();
})
//站内消息>消息设置保存
$(document).delegate(".stationSave","click",function(){
	$(".stationSave").hide();
	$(".stationModify").find("input").addClass("clearstyle");
	$(".stationModify").find("input").attr("readonly","readonly");
	$(this).parents().find(".modifyBtn").show();
})

$(function(){
	//成功消息提醒
	//共同部分
	$.MsgAlert={
		AlertTure:function (title,msg){
			GenerationTrue(title,msg);
		}
	}
	var GenerationTrue=function(title,msg){
		var html="";
		html+="<div id='msgPop'><h3>"+title+"<span id='clickOff'>X</span></h3>";
		html+="<p class='msg'><i class='glyphicon glyphicon-ok-sign'></i>"+msg+"</p></div>";	
		$("body").append(html);	
		setTimeout(function(){
			$("#msgPop").animate({"opacity":"0"},200/*设置消失速度*/,function(){
				$("#msgPop").remove();
			});
		},2000)//几秒后关闭提示 
	}
	//共同部分
	$(function(){
		//点击X 关闭提示 -- 可以删除 <span id='clickOff'>X</span> 不使用该功能
		$(document).delegate("#clickOff","click",function(){
			$(this).parents("#msgPop").remove();
		});
	//点击X 关闭提示
		$(".TipAlert").click(function(){
		// 设置 提示的 title 与 contents  MsgAlert.AlertTure(Title,Contents);
		MsgAlert.AlertTure("修改密码提交","提交成功");
		})
	})
})