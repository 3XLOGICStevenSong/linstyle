/**
 * 
 */
$.ajaxSetup({cache:false});
var pageTotal="";//总页数 
var offset=1;//当前页数 
var limit=8;//每页行数
var endNumber="";//最后页的条数
var noOrName="";
/*var token = localStorage.getItem("token");
if(token == null){
	alert("ログインしてください");
	window.parent.location.href="http://49.4.109.191:8080/jtrain/html/login.html";
}*/
$(function(){
	document.onkeydown = function(e){
	    if(e.keyCode == 116){
	        e.preventDefault(); //组织默认刷新
	        location.reload();
	    }
	}
	var type = 2;
	var addEdit = 0;
	getTrainType();
	getload(offset,limit);
	function getload(offset,limit){
		/*compareToken();*/
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
									if(item.vocational != null && item.introduction != null){
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td class='linetd'>"+item.introduction+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
									}else if(item.vocational != null){
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
									}else if(item.introduction != null){
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td class='linetd'>"+item.introduction+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
									}else{
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
									}
									
								}else{
									if(item.vocational != null && item.introduction != null){
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td class='linetd'>"+item.introduction+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
									}else if(item.vocational != null){
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
									}else if(item.introduction != null){
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td class='linetd'>"+item.introduction+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
									}else{
										$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
									}
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
				            if(pageTotal==0){
		    					$(".pagination").html("");
		    				}else{
		    					$(".pagination").html("");
		    					$(".pagination").append("<li><a href='#' aria-label='Previous' class='beforePage'><span aria-hidden='true'>&laquo;</span></a></li>"+page+"<li><a href='#' aria-label='Previous' class='nextPage'><span aria-hidden='true'>&raquo;</span></a></li><li><input type='text' class='form-control col-1 btn-pos no' placeholder='頁'></li><li><button type='button' class='btn btn-default btn-pos move'>ジャンプ</button></li>");
		    				}
						}
				}
			}
		})
	}
	$("#studentSearch").click(function(){
		/*compareToken();*/
		offset = 1;
		var type = 2;
		noOrName = $.trim($("#querySearch").val());
		if(noOrName.indexOf("%") != -1){
			$(".pagination").html("");
			$("table tr").eq(0).nextAll().remove();
			$("#count").text("0");
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
								if(item.vocational != null && item.introduction != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td class='linetd'>"+item.introduction+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}else if(item.vocational != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}else if(item.introduction != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td class='linetd'>"+item.introduction+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}else{
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}
								
							}else{
								if(item.vocational != null && item.introduction != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td class='linetd'>"+item.introduction+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}else if(item.vocational != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}else if(item.introduction != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td class='linetd'>"+item.introduction+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}else{
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}
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
			            if(pageTotal==0){
	    					$(".pagination").html("");
	    				}else{
	    					$(".pagination").html("");
	    					$(".pagination").append("<li><a href='#' aria-label='Previous' class='beforePage'><span aria-hidden='true'>&laquo;</span></a></li>"+page+"<li><a href='#' aria-label='Previous' class='nextPage'><span aria-hidden='true'>&raquo;</span></a></li><li><input type='text' class='form-control col-1 btn-pos no' placeholder='頁'></li><li><button type='button' class='btn btn-default btn-pos move'>ジャンプ</button></li>");
	    				}
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
	var previewEdit = "";
	$(document).on("click",".editMemberBtn",function(){
		/*compareToken();*/
		$("#message").hide();
		$("#nameHide").hide();
		$("#applyDateHide").hide();
		$("#birthdayHide").hide();
		$("#MemberModal").modal('show');
		var no=$(this).attr('data');
		var flag = 1;
		var type = 2;
		addEdit = 0;
		/*compareToken();*/
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
					$("#introduction").val(result.data.introduction);
					$("#preview").attr("src",result.data.personDraw);
				}
			}
		})
		});
		$("#MemberModal input").val("");
	$(document).on("click",".pageNumber",function(){
		/*compareToken();*/
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
		/*compareToken();*/
		if(noOrName.indexOf("%") != -1){
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
					var page = "";
					$("table tr td").remove();
					if(result.data == ""){
						$("#count").text("0");
						return;
					}else{
						$.each(result.data,function(i,item){
							if(item.sex == 1){
								if(item.vocational != null && item.introduction != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td class='linetd'>"+item.introduction+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}else if(item.vocational != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}else if(item.introduction != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td class='linetd'>"+item.introduction+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}else{
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>女</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}
								
							}else{
								if(item.vocational != null && item.introduction != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td class='linetd'>"+item.introduction+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}else if(item.vocational != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td class='linetd2'>"+item.vocational+"</td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}else if(item.introduction != null){
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td class='linetd'>"+item.introduction+"</td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}else{
									$("table").append("<tr><td>"+item.no+"</td><td class='nametd'>"+item.name+"</td><td>"+item.age+"</td><td>男</td><td class='applyDatetd'>"+item.applyDate+"</td><td></td><td></td><td class='operatetd'><button type='button' class='btn btn-training btn-size editMemberBtn' data='"+item.no+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal2' data='"+item.no+"'>削除</button></td><td><button type='button' class='btn btn-training btn-size evaluateBtn' data='"+item.no+"'>レビュー </button></td></tr>");
								}
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
			            if(pageTotal==0){
	    					$(".pagination").html("");
	    				}else{
	    					$(".pagination").html("");
	    					$(".pagination").append("<li><a href='#' aria-label='Previous' class='beforePage'><span aria-hidden='true'>&laquo;</span></a></li>"+page+"<li><a href='#' aria-label='Previous' class='nextPage'><span aria-hidden='true'>&raquo;</span></a></li><li><input type='text' class='form-control col-1 btn-pos no' placeholder='頁'></li><li><button type='button' class='btn btn-default btn-pos move'>ジャンプ</button></li>");
	    				}
					}	
				}else{
					alert("クエリ失敗，正しい文字を入力してください");
					window.location.reload();
				}
			}
		})
	}
	$(document).on("click",".beforePage",function(){
		/*compareToken();*/
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
		/*compareToken();*/
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
		/*compareToken();*/
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
		/*compareToken();*/
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
	  /*compareToken();*/
	  $("#message").hide();
	  $("#nameHide").hide();
	  $("#applyDateHide").hide();
	  $("#birthdayHide").hide();
	  $("#MemberModal").modal('show');
	  $("#name").val("");
	  $("#applyDate").val("");
	  $("#birthday").val("");
	  $("#pro option[value='0']").prop("selected",true);
	  $("#vocational").val("");
	  $("#introduction").val("");
	  $("#preview").attr("src","http://49.4.109.191:8080/jtrain/images/userheader_default.png");
	  var flag = 0;
	  var type = 2;
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
		/*compareToken();*/
		$("#message").show();
		if(addEdit == 1){
			var flag = 0;
			var type = 2;
			var no = $("#no").val();
			var name = $.trim($("#name").val());
			var applyDate = $.trim($("#applyDate").val());
			var birthday = $.trim($("#birthday").val());
			var pro = $("#pro").val();
			var vocational = $.trim($("#vocational").val());
			var introduction = $.trim($("#introduction").val());
			if(personDraw==""){
	        	//默认图片路径
	    		personDraw=$("#preview").attr("src");
    		}
		    if(name == ""){
				$("#message").html("名前は空にできません");
			    $("#message").removeClass("ok");
			    $("#message").addClass("error");
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
			if(introduction == ""){
				$("#message").html("紹介は空にできません");
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
				url:"http://49.4.109.191:8080/jtrain/v1/api/user/backendAdd",
				async: false,
				dataType: "json",
				contentType:"application/json",
				data:JSON.stringify({"type":type,"no":no,"name":name,"applyDate":applyDate,"birthday":birthday,"sex":pro,"vocational":vocational,"introduction":introduction,"personDraw":personDraw}),
				beforeSend: function(request) {
					request.setRequestHeader("Authorization", localStorage.getItem("token"));
				},
				success:function(result){
					if(result.statusCode=="200"){
						window.location.reload();
						$("#MemberModal").modal('hide');
						return;
					}else{
						$("#message").html("追加に失敗，追加に失敗，ページを更新してください");
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
			var introduction = $.trim($("#introduction").val());
		    if(name == ""){
				$("#message").html("名前は空にできません");
			    $("#message").removeClass("ok");
			    $("#message").addClass("error");
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
			if(introduction == ""){
				$("#message").html("紹介は空にできません");
			    $("#message").removeClass("ok");
			    $("#message").addClass("error");
				return;
			}
			var flag = 1;
			var type = 2;
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
				data:JSON.stringify({"type":type,"no":no,"name":name,"applyDate":applyDate,"birthday":birthday,"sex":pro,"vocational":vocational,"introduction":introduction,"personDraw":personDraw}),
				beforeSend: function(request) {
					request.setRequestHeader("Authorization", localStorage.getItem("token"));
				},
				success:function(result){
					if(result.statusCode=="200"){
						getInfromation(noOrName,offset,limit);
						$("#MemberModal").modal('hide');
						return;
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
	$(document).on("click",".launchModal2",function(){
	/*compareToken();*/
	$("#myModal2").modal('show');
	memberTr=$(this).parents("tr");
	no=$(this).attr('data');
	});
	$(".deleteTr2").on("click",function(){
		/*compareToken();*/
		$.ajax({
			type:"delete",
			url:"http://49.4.109.191:8080/jtrain/v1/api/user/delete/"+no,
			async:false,
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
			success:function(result){
				if(result.statusCode=="200"){
					$("table tr").eq(0).nextAll().remove();
					if(offset==pageTotal){
                        if(endNumber-1<1){
                        	if((noOrName=="")){
                        		getload(offset-1,limit);
                            	offset=offset-1;
                        	}else{
                        		getInfromation(noOrName,offset-1,limit);
                        		offset=offset-1;
                        	}
                        }else{
                        	if((noOrName=="")){
                        		getload(offset,limit);
                        	}else{
                        		getInfromation(noOrName,offset,limit);
                        	}
                        }
                      }else{
                            if((noOrName=="")){
                        	    getload(offset,limit);
                        	}else{
                        		getInfromation(noOrName,offset,limit);
                        	}
                    }
				}
			}
		})
	   $("#myModal2").modal('hide');
	   memberTr.remove();
	})
	//评论对话框
	 var tr = "";
	$(document).on("click",".evaluateBtn",function(){
		/*compareToken();*/
		var studentId=$(this).attr('data');
		$.ajax({
			type:"get",
			url:"http://49.4.109.191:8080/jtrain/v1/api/password/get/"+studentId,
			async: false,
			dataType: "json",
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
			success:function(result){
				if(result.statusCode=="200"){
					$("#previewStudentName").html(result.data.name);
				}
			}
		})
		$.ajax({
			type:"get",
			url:"http://49.4.109.191:8080/jtrain/v1/api/student/preview/"+studentId,
			async: false,
			dataType: "json",
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
			success:function(result){
				if(result.statusCode=="200"){
					$("#selectPreview").html("");
					if(result.data == ""){
						alert("評価はありません");
					}else{
						$.each(result.data,function(i,item){
							$("#evaluateModal").modal('show');
							$("#selectPreview").append("<div class='media border-bt'><div class='media-body form-group'><div class='col-md-10'><h4><span>"+item.trainTypeName+"</span>----<span>"+item.teacherName+"</span>の新教師のコメント</h4><div class='col-md-12 train-detail'>"+item.evaluate+"</div><div class='col-md-10 train-date'>"+item.evaluateTime+"</div></div></div></div>");
						})
					}
				}
			}
		})
	});
	$(".dlTrainBtn").click(function(){
		/*compareToken();*/
	   $("#evaluateModal").modal('hide');
	})
	function getTrainType(){
		$("#trainType").empty();
		$("#trainType").append("<option value=''>请选择种别</option>");
		/*compareToken();*/
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
	/*2019.1.29*/
	/*function compareToken(){
		var tokenTemp = "";
		$.ajax({
			type:"post",
			url:"http://49.4.109.191:8080/jtrain/v1/api/login/compare",
			async: false,
			dataType:"json",
			success:function(result){
				if(result.statusCode=="200"){
					tokenTemp = result.data;
				}
			}
		})
		if(tokenTemp != localStorage.getItem("token")){
			alert('リモートログイン');
	        window.parent.location.href="http://49.4.109.191:8080/jtrain/html/login.html";
	        return;
		}
	}*/
})
/*$(document).on("click",".upDateBtn",function(){
	   window.location.href="trainDetail.html?trainId="+$(this).attr("data");
});
 var media = "";
 var trainId ="";
 $(document).on("click",".launchModal1",function(){
 	$("#myModal1").modal('show');
     media=$(this).parents(".media");	  
     trainId=$(this).attr("data");
 });
 */
