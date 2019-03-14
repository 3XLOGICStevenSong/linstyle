/**
 * 
 */
/*window.onload = function(){
    var studentLoad = document.getElementById('studentLoad');
    var teacherLoad = document.getElementById('teacherLoad');
    studentLoad.onclick=function(){  
        window.location.href="../html/studentManagement.html";
     }
    teacherLoad.onclick=function(){  
        window.location.href="../html/teacherManagement.html";
     }
 }*/
$.ajaxSetup({cache:false});
var pageTotal="";//总页数 
var offset=1;//当前页数 
var limit=8;//每页行数
var endNumber="";//最后页的条数
var noOrName="";
$(function(){
	document.onkeydown = function(e){
	    if(e.keyCode == 116){
	        e.preventDefault(); //组织默认刷新
	        location.reload();
	    }
	}
	var type = 1;
	var addEdit = 0;
	getTrainType();
	getload(offset,limit);
	function getload(offset,limit){
		$.ajax({
			type:"post",
			url:"http://49.4.109.191:8080/jtrain/v1/api/user/load",
			async: false,
			dataType: "json",
			contentType:"application/json",
			data:JSON.stringify({"type":type,"offset":offset,"limit":limit}),
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
			success:function(result){
				if(result.statusCode=="200"){
					var page = "";
					if(result.data == ""){
						$("#count").text("0");
						return;
					}else{
						$.each(result.data,function(i,item){
							if(item.sex == 1){
								if(item.remarks != null && item.vocational != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td class='linetd'>"+item.remarks+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
								}else if(item.remarks != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td class='linetd'>"+item.remarks+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
								}else if(item.vocational != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
								}else{
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
								}
							}else{
								if(item.remarks != null && item.vocational != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td class='linetd'>"+item.remarks+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
								}else if(item.remarks != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td class='linetd'>"+item.remarks+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
								}else if(item.vocational != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
								}else{
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' data='"+item.no+"'>禁用</button></td></tr>");
								}
								
							}
							if(item.useStatus==0){
		                   		   $("table tr:last .statusOnTr2").show();
		                   	    }
		                     if(item.useStatus==1){
		                   	       $("table tr:last .statusOffTr2").show();
			                   	}
						})
						$("#count").text(result.data[0].count);
						pageTotal=Math.ceil(result.data[0].count/limit);
			            if(result.data[0].count%limit==0){
			            	 endNumber=limit;
			            }else{
			            	endNumber=result.data[0].count%limit;
			            }
			            if(pageTotal > 5){
		            		if(pageTotal - offset < 6){
			            		for(var i=pageTotal-5;i<=pageTotal;i++){
						            if(i==offset){
						            	page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
						            }else{
						            	page+="<li id='pointpoint'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
						            }
					            }
			            	}else{
			            		for(var i=offset;i<=offset+4;i++){
						            if(i==offset){
						            	page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
						            }else{
						            	page+="<li><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
						            }
					            }
				            	page+="<span id='liSpan'>···</span><li><a href='#' class='pageNumber'>"+pageTotal+"<span class='sr-only'>(current)</span></a></li>"
			            	}
		            	}else{
		            		for(var i=1;i<=pageTotal;i++){
					            if(i==offset){
					            	page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
					            }else{
					            	page+="<li id='pointpoint'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
					            }
				            }
		            	}
			            /*for(var i=1;i<=pageTotal;i++){
			            	if(i==offset){
			            		page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
			            	}else{
			            		page+="<li><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
			            	}
			            }*/
			            $(".pagination").html("");
			            $(".pagination").append("<li><a href='#' aria-label='Previous' class='beforePage'><span aria-hidden='true'>&laquo;</span></a></li>"+page+"<li><a href='#' aria-label='Previous' class='nextPage'><span aria-hidden='true'>&raquo;</span></a></li><li><input type='text' class='form-control col-1 btn-pos no' placeholder='頁'></li><li><button type='button' class='btn btn-default btn-pos move'>ジャンプ</button></li>");
					}
				}
			}
		})
	}
	$("#teacherSearch").click(function(){
		offset = 1;
		var type = 1;
		noOrName = $.trim($("#querySearch").val());
		if(noOrName.indexOf("%") != -1){
			$("#count").text("0");
			$("table tr").eq(0).nextAll().remove();
			$(".pagination").html("");
			return;
		}
		$.ajax({
			type:"post",
			url:"http://49.4.109.191:8080/jtrain/v1/api/user/get",
			async: false,
			dataType: "json",
			contentType:"application/json",
			data:JSON.stringify({"type":type,"noOrName":noOrName,"offset":offset,"limit":limit}),
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
			success:function(result){
				if(result.statusCode=="200"){
					var page = "";
					$("table tr td").remove();
					if(result.data == ""){
						$("#count").text("0");
						$(".pagination").html("");
						return;
					}else{
						$.each(result.data,function(i,item){
							if(item.sex == 1){
								if(item.remarks != null && item.vocational != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td class='linetd'>"+item.remarks+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
								}else if(item.remarks != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td class='linetd'>"+item.remarks+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
								}else if(item.vocational != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2 style='display:none'' data='"+item.no+"'>禁用</button></td></tr>");
								}else{
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
								}
								
							}else{
								if(item.remarks != null && item.vocational != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td class='linetd'>"+item.remarks+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
								}else if(item.remarks != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td class='linetd'>"+item.remarks+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
								}else if(item.vocational != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
								}else{
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
								}
							}
		                     if(item.useStatus==0){
		                   		   $("table tr:last .statusOnTr2").show();
		                   	    }
		                     if(item.useStatus==1){
		                   	       $("table tr:last .statusOffTr2").show();
			                   	}
						})
						$("#count").text(result.data[0].count);
						pageTotal=Math.ceil(result.data[0].count/limit);
			            if(result.data[0].count%limit==0){
			            	 endNumber=limit;
			            }else{
			            	endNumber=result.data[0].count%limit;
			            }
			            if(pageTotal > 5){
		            		if(pageTotal - offset < 6){
			            		for(var i=pageTotal-5;i<=pageTotal;i++){
						            if(i==offset){
						            	page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
						            }else{
						            	page+="<li id='pointpoint'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
						            }
					            }
			            	}else{
			            		for(var i=offset;i<=offset+4;i++){
						            if(i==offset){
						            	page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
						            }else{
						            	page+="<li><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
						            }
					            }
				            	page+="<span id='liSpan'>···</span><li><a href='#' class='pageNumber'>"+pageTotal+"<span class='sr-only'>(current)</span></a></li>"
			            	}
		            	}else{
		            		for(var i=1;i<=pageTotal;i++){
					            if(i==offset){
					            	page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
					            }else{
					            	page+="<li id='pointpoint'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
					            }
				            }
		            	}
			            /*for(var i=1;i<=pageTotal;i++){
			            	if(i==offset){
			            		page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
			            	}else{
			            		page+="<li><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
			            	}
			            }*/
			            $(".pagination").html("");
			            $(".pagination").append("<li><a href='#' aria-label='Previous' class='beforePage'><span aria-hidden='true'>&laquo;</span></a></li>"+page+"<li><a href='#' aria-label='Previous' class='nextPage'><span aria-hidden='true'>&raquo;</span></a></li><li><input type='text' class='form-control col-1 btn-pos no' placeholder='頁'></li><li><button type='button' class='btn btn-default btn-pos move'>ジャンプ</button></li>");
					}	
				}else{
					alert("クエリ失敗，正しい文字を入力してください");
					window.location.reload();
				}
			}
		})
	})
	//编辑
	var no ="";
	  $(document).on("click",".editMemberBtn",function(){
	  $("#message").hide();
	  $("#nameHide").hide();
	  $("#applyDateHide").hide();
	  $("#birthdayHide").hide();
	  $("#MemberModal").modal('show');
	  var no=$(this).attr('data');
	  var flag = 1;
	  var type = 1;
	  addEdit = 0;
	  $.ajax({
		  type:"get",
		  url:"http://49.4.109.191:8080/jtrain/v1/api/user/select/"+no,
		  async: false,
		  dataType: "json",
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
		  success:function(result){
			  if(result.statusCode=="200"){
				  $("#no").val(result.data.no);
				  $("#name").val(result.data.name);
				  $("#applyDate").val(result.data.applyDate);
				  $("#birthday").val(result.data.birthday);
				  $("#pro option[value='"+result.data.sex+"']").prop("selected",true);
				  $("#vocational").val(result.data.vocational);
				  $("#remarks").val(result.data.remarks);
				  $("#trainType option[value='']").remove();
				  $("#trainType option[value='"+result.data.trainType+"']").prop("selected",true);
				  $("#preview").attr("src",result.data.personDraw);
			  }
		  }
	  })
	});
	$("#MemberModal input").val("");
	  $(document).on("click",".pageNumber",function(){
		  $("table tr").eq(0).nextAll().remove();
		  offset=null;
		   $(this).contents().each(function(){ 
			   if(this.nodeType == 3){ 
				   offset=parseInt(this.wholeText); 
			   }
		   });
		  if(noOrName==""){
			   getload(offset,limit);  
		  }else{
			  getInfromation(noOrName,offset,limit);
		  }   
	 });
	  function getInfromation(noOrName,offset,limit){
		  if(noOrName.indexOf("%") != -1){
			  $("#count").text("0");
				$("table tr").eq(0).nextAll().remove();
				return;
			}
		  $.ajax({
				type:"post",
				url:"http://49.4.109.191:8080/jtrain/v1/api/user/get",
				async: false,
				dataType: "json",
				contentType:"application/json",
				data:JSON.stringify({"type":type,"noOrName":noOrName,"offset":offset,"limit":limit}),
				beforeSend: function(request) {
					request.setRequestHeader("Authorization", localStorage.getItem("token"));
				},
				success:function(result){
					if(result.statusCode=="200"){
						$("#count").text("0");
						var page = "";
						$("table tr td").remove();
						if(result.data == ""){
							return;
						}else{
							$.each(result.data,function(i,item){
								if(item.sex == 1){
									if(item.remarks != null && item.vocational != null){
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td class='linetd'>"+item.remarks+"</td><td><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
									}else if(item.remarks != null){
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td class='linetd'>"+item.remarks+"</td><td><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
									}else if(item.vocational != null){
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td></td><td><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
									}else{
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td></td><td><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
									}
									
								}else{
									if(item.remarks != null && item.vocational != null){
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td class='linetd'>"+item.remarks+"</td><td><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
									}else if(item.remarks != null){
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td class='linetd'>"+item.remarks+"</td><td><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
									}else if(item.vocational != null){
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td></td><td><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
									}else{
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td class='nametd'>"+item.trainName+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td></td><td><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-success col-md-offset-1 btn-size statusOffTr2' style='display:none' data='"+item.no+"'>開く</button><button type='button' class='btn btn-danger col-md-offset-1 btn-size statusOnTr2' style='display:none' data='"+item.no+"'>禁用</button></td></tr>");
									}
								}
								
								if(item.useStatus==0){
			                   		   $("table tr:last .statusOnTr2").show();
			                   	    }
			                     if(item.useStatus==1){
			                   	       $("table tr:last .statusOffTr2").show();
				                   	}
							})
							$("#count").text(result.data[0].count);
							pageTotal=Math.ceil(result.data[0].count/limit);
				            if(result.data[0].count%limit==0){
				            	 endNumber=limit;
				            }else{
				            	endNumber=result.data[0].count%limit;
				            }
				            if(pageTotal > 5){
			            		if(pageTotal - offset < 6){
				            		for(var i=pageTotal-5;i<=pageTotal;i++){
							            if(i==offset){
							            	page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
							            }else{
							            	page+="<li id='pointpoint'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
							            }
						            }
				            	}else{
				            		for(var i=offset;i<=offset+4;i++){
							            if(i==offset){
							            	page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
							            }else{
							            	page+="<li><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
							            }
						            }
					            	page+="<span id='liSpan'>···</span><li><a href='#' class='pageNumber'>"+pageTotal+"<span class='sr-only'>(current)</span></a></li>"
				            	}
			            	}else{
			            		for(var i=1;i<=pageTotal;i++){
						            if(i==offset){
						            	page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
						            }else{
						            	page+="<li id='pointpoint'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
						            }
					            }
			            	}
				            /*for(var i=1;i<=pageTotal;i++){
				            	if(i==offset){
				            		page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
				            	}else{
				            		page+="<li><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
				            	}
				            }*/
				            $(".pagination").html("");
				            $(".pagination").append("<li><a href='#' aria-label='Previous' class='beforePage'><span aria-hidden='true'>&laquo;</span></a></li>"+page+"<li><a href='#' aria-label='Previous' class='nextPage'><span aria-hidden='true'>&raquo;</span></a></li><li><input type='text' class='form-control col-1 btn-pos no' placeholder='頁'></li><li><button type='button' class='btn btn-default btn-pos move'>ジャンプ</button></li>");
						}	
					}else{
						alert("クエリ失敗，正しい文字を入力してください");
						window.location.reload();
					}
				}
			})
	  }
	  $(document).on("click",".beforePage",function(){
		  if(offset==1){
			  return;
		  }
		  if((noOrName=="")){  
			  $("table tr").eq(0).nextAll().remove();
			  getload(offset-1,limit);
		  }else{
			  $("table tr").eq(0).nextAll().remove();
			  getInfromation(noOrName,offset-1,limit);
		  }   
		  offset=offset-1;
	});
	$(document).on("click",".nextPage",function(){
		
		  if(offset==pageTotal){
			  return;
		  }
		  if((noOrName=="")){ 
			  $("table tr").eq(0).nextAll().remove();
			  getload(offset+1,limit);
		  }else{
			  $("table tr").eq(0).nextAll().remove();
			  getInfromation(noOrName,offset+1,limit);
		  }
		   offset=offset+1;
	});
	$(document).on("click",".move",function(){
		 if($(".no").val()==""){
			  return;
		  }
		 if(parseInt($(".no").val())>pageTotal){
			 $(".no").val("");
			 return;
		 }
		  offset=parseInt($(".no").val());
		  if((noOrName=="")){  
			  $("table tr").eq(0).nextAll().remove();
			  getload(offset,limit);
		  }else{
			  $("table tr").eq(0).nextAll().remove();
			  getInfromation(noOrName,offset,limit);
		  }
	});
	$(document).on("input propertychange",".no",function(){
		   this.value=this.value.replace(/[^\d]/g,'');
	   });
	$(document).on("input propertychange","#vocational",function(){
		   this.value=this.value.replace(/[^\d]/g,'');
	   });
	$("#MemberModal input").val("");
	var personDraw = "";
	$(".myFileUpload").on("change",function(){
		
	    var file = $(this)[0].files[0];//获取指定ID的文件信息
	    var formdat=file.name.substring(file.name.lastIndexOf("."));
	    if((formdat == ".png")||(formdat == ".jpg")||(formdat == ".jpeg")){
	        var formData = new FormData();//创建FormData对象，将所需的信息封装到内部，以键值对的方式
	        formData.append("file",file);//参数封装格式,可以是文件，亦可以是普通的字符串 
	        $.ajax({
	        	type:"post",
	        	url:"http://49.4.109.191:8080/jtrain/file/upload",
	        	dataType:"json",
	        	async: false,
	        	cache:false,//不需要缓存操作
	            data:formData,//传递的参数就是FormData
	            contentType:false,//由于提交的对象是FormData,所以要在这里更改上传参数的类型
	            processData:false,//提交对象是FormData,不需要对数据做任何处理
	    		beforeSend: function(request) {
	    			request.setRequestHeader("Authorization", localStorage.getItem("token"));
	    		},
	        	success:function(result){
	        		if(result.statusCode=="200"){
	        			$("#message").show();
	        			$("#message").html("アップロード成功");
	    			    $("#message").removeClass("error");
	    			    $("#message").addClass("ok");
	        			personDraw=result.data.url;
	        		}
	        	}
	        });
	    }else{
	    	alert("正しい画像フォーマットをアップロードしてください");
	    }
   });
	//添加
	$(".addMemberBtn").click(function(){
	  $("#message").hide();
	  $("#nameHide").hide();
	  $("#applyDateHide").hide();
	  $("#birthdayHide").hide();
	  $("#MemberModal").modal('show');
	  $("#no").val("");
	  $("#name").val("");
	  $("#applyDate").val("");
	  $("#birthday").val("");
	  $("#pro option[value='0']").prop("selected",true);
	  $("#vocational").val("");
	  $("#remarks").val("");
	  $("#trainType option[value='0']").prop("selected",true);
	  $("#personDraw").val("");
	  $("#preview").attr("src","http://49.4.109.191:8080/jtrain/images/userheader_default.png");
	  var flag = 0;
	  var type = 1;
	  $.ajax({
		  type:"get",
		  url:"http://49.4.109.191:8080/jtrain/v1/api/user/getno/"+type,
		  async: false,
		  dataType: "json",
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
		  success:function(result){
			  if(result.statusCode=="200"){
				  $("#no").val(result.data);
			  }
		  }
	  })
	  addEdit = 1;
	});
	//确定按钮功能
	$("#checkBtn").click(function(){
		$("#message").show();
		$("#message").html("");
		var type = 1;
		if(addEdit == 1){
			var flag = 0;
			var type = 1;
			var no = $("#no").val();
			var name = $.trim($("#name").val());
			var applyDate = $.trim($("#applyDate").val());
			var birthday = $.trim($("#birthday").val());
			var pro = $("#pro").val();
			var vocational = $.trim($("#vocational").val());
			var remarks = $.trim($("#remarks").val());
			var trainType = $("#trainType").val();
			if(name == ""){
				$("#message").html("名前は空にできません");
		    	$("#message").removeClass("ok");
		    	$("#message").addClass("error");
				$("#name").focus();
				return;
			}
			if(applyDate == ""){
				$("#message").html("エントリー日は空にできません");
		    	$("#message").removeClass("ok");
		    	$("#message").addClass("error");
				return;
			}
			if(birthday == ""){
				$("#message").html("誕生日は空にできません");
		    	$("#message").removeClass("ok");
		    	$("#message").addClass("error");
				return;
			}
			if(vocational == ""){
				$("#message").html("職歴は空にできません");
		    	$("#message").removeClass("ok");
		    	$("#message").addClass("error");
				return;
			}
			if(remarks == ""){
				$("#message").html("備考は空にできません");
		    	$("#message").removeClass("ok");
		    	$("#message").addClass("error");
				return;
			}
			if(personDraw == ""){
				//默认图片路径
	    		personDraw=$("#preview").attr("src");
			}
			var apply =new Date(applyDate);
			var birth =new Date(birthday);
			var applyDateYear = apply.getYear();
			var applyDateMonth = apply.getMonth();
			var applyDateDay = apply.getDate();
			var birthdayYear = birth.getYear();
			var birthdayMonth = birth.getMonth();
			var birthdayDay = birth.getDate();
			if(applyDateYear < birthdayYear){
				$("#message").html("誕生日またはエントリーの日付が間違っています");
			    $("#message").removeClass("ok");
			    $("#message").addClass("error");
			    return;
			}
			if(applyDateYear == birthdayYear){
				if(applyDateMonth < birthdayMonth){
					$("#message").html("誕生日またはエントリーの日付が間違っています");
				    $("#message").removeClass("ok");
				    $("#message").addClass("error");
				    return;
				}
			}
			if(applyDateYear == birthdayYear){
				if(applyDateMonth == birthdayMonth){
					if(applyDateDay < birthdayDay){
						$("#message").html("誕生日またはエントリーの日付が間違っています");
					    $("#message").removeClass("ok");
					    $("#message").addClass("error");
					    return;
					}
				}
			}
			if(trainType == ""){
				$("#message").html("トレーニングカテゴリを選択してください");
			    $("#message").removeClass("ok");
			    $("#message").addClass("error");
			    return;
			}
			$.ajax({
				type:"post",
				url:"http://49.4.109.191:8080/jtrain/v1/api/user/backendAdd",
				async: false,
				dataType: "json",
				contentType:"application/json",
				data:JSON.stringify({"flag":flag,"type":type,"no":no,"name":name,"applyDate":applyDate,"birthday":birthday,"sex":pro,"vocational":vocational,"remarks":remarks,"trainType":trainType,"personDraw":personDraw}),
				beforeSend: function(request) {
					request.setRequestHeader("Authorization", localStorage.getItem("token"));
				},
				success:function(result){
					if(result.statusCode=="200"){
						window.location.reload();
						$("#MemberModal").modal('hide');
					}else{
						$("#message").html("追加に失敗，ページを更新してください");
					    $("#message").removeClass("ok");
					    $("#message").addClass("error");
					}
				}
			})
		}else{
			$("#message").html("");
			var no = $("#no").val();
			var name = $.trim($("#name").val());
			var applyDate = $.trim($("#applyDate").val());
			var birthday = $.trim($("#birthday").val());
			var pro = $("#pro").val();
			var vocational = $.trim($("#vocational").val());
			var remarks = $.trim($("#remarks").val());
			var trainType = $("#trainType").val();
			if(name == ""){
				$("#message").html("名前は空にできません");
		    	$("#message").removeClass("ok");
		    	$("#message").addClass("error");
				$("#name").focus();
				return;
			}
			if(applyDate == ""){
				$("#message").html("エントリー日は空にできません");
		    	$("#message").removeClass("ok");
		    	$("#message").addClass("error");
				return;
			}
			if(birthday == ""){
				$("#message").html("誕生日は空にできません");
		    	$("#message").removeClass("ok");
		    	$("#message").addClass("error");
				return;
			}
			if(vocational == ""){
				$("#message").html("職歴は空にできません");
		    	$("#message").removeClass("ok");
		    	$("#message").addClass("error");
				return;
			}
			if(remarks == ""){
				$("#message").html("備考は空にできません");
		    	$("#message").removeClass("ok");
		    	$("#message").addClass("error");
				return;
			}
			var apply =new Date(applyDate);
			var birth =new Date(birthday);
			var applyDateYear = apply.getYear();
			var applyDateMonth = apply.getMonth();
			var applyDateDay = apply.getDate();
			var birthdayYear = birth.getYear();
			var birthdayMonth = birth.getMonth();
			var birthdayDay = birth.getDate();
			if(applyDateYear < birthdayYear){
				$("#message").html("誕生日またはエントリーの日付が間違っています");
			    $("#message").removeClass("ok");
			    $("#message").addClass("error");
			    return;
			}
			if(applyDateYear == birthdayYear){
				if(applyDateMonth < birthdayMonth){
					$("#message").html("誕生日またはエントリーの日付が間違っています");
				    $("#message").removeClass("ok");
				    $("#message").addClass("error");
				    return;
				}
			}
			if(applyDateYear == birthdayYear){
				if(applyDateMonth == birthdayMonth){
					if(applyDateDay < birthdayDay){
						$("#message").html("誕生日またはエントリーの日付が間違っています");
					    $("#message").removeClass("ok");
					    $("#message").addClass("error");
					    return;
					}
				}
			}
			$.ajax({
				type:"post",
				url:"http://49.4.109.191:8080/jtrain/v1/api/user/backendUpdate",
				async: false,
				dataType: "json",
				contentType:"application/json",
				data:JSON.stringify({"flag":flag,"type":type,"no":no,"name":name,"applyDate":applyDate,"birthday":birthday,"sex":pro,"vocational":vocational,"remarks":remarks,"trainType":trainType,"personDraw":personDraw}),
				beforeSend: function(request) {
					request.setRequestHeader("Authorization", localStorage.getItem("token"));
				},
				success:function(result){
					if(result.statusCode=="200"){
						getInfromation(noOrName,offset,limit);
						$("#MemberModal").modal('hide');
					}else{
						$("#message").html("更新失敗");
					    $("#message").removeClass("ok");
					    $("#message").addClass("error");
					}
				}
			})
		}
		
		})
		$(".dlTrainBtn").click(function(){
			$("#MemberModal").modal('hide');
		})
	//删除
	var memberTr = "";
	$(document).on("click",".statusOffTr2",function(){
		no = null;
		no = $(this).attr("data");
		$(this).hide();
		$(this).parents("td").find(".statusOnTr2").show();
		$.ajax({
			type:"delete",
			url:"http://49.4.109.191:8080/jtrain/v1/api/user/delete/"+no,
			async:false,
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
		})
		memberTr=$(this).parents("tr");
		no=$(this).attr('data');
	});
	$(document).on("click",".statusOnTr2",function(){
		no = null;
		no = $(this).attr("data");
		$(this).hide();
		$(this).parents("td").find(".statusOffTr2").show();
		$.ajax({
			type:"delete",
			url:"http://49.4.109.191:8080/jtrain/v1/api/user/delete/"+no,
			async:false,
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
		})
		memberTr=$(this).parents("tr");
		no=$(this).attr('data');
	});
}); 
 function getTrainType(){
	$("#trainType").empty();
	$("#trainType").append("<option value=''>選択してください</option>");
	$.ajax({
		type:"get",
		url:"http://49.4.109.191:8080/jtrain/v1/api/trainType/load",
		async: false,
		dataType:"json",
		beforeSend: function(request) {
			request.setRequestHeader("Authorization", localStorage.getItem("token"));
		},
		success:function(result){
			if(result.statusCode=="200"){
				$.each(result.data.trainCategoryInformationVOList,function(i,item){
				$("#trainType").append("<option value="+item.trainType +">"+item.typeName+"</option>");
				})
			}
		}
	})
}


$(document).on("click",".upDateBtn",function(){
	   window.location.href="trainDetail.html?trainId="+$(this).attr("data");
});
 var media = "";
 var trainId ="";
 $(document).on("click",".launchModal1",function(){
 	$("#myModal1").modal('show');
     media=$(this).parents(".media");	  
     trainId=$(this).attr("data");
 });
