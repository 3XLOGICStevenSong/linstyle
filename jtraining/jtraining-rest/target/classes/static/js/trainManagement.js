$.ajaxSetup({cache:false});
$(function(){
	document.onkeydown = function(e){
	    if(e.keyCode == 116){
	        e.preventDefault(); //组织默认刷新
	        location.reload();
	    }
	}
	var pageTotal="";//总页数 
	var currentPage=1;//当前页数 
	var pageSize=4;//每页行数
	var endNumber="";//最后页的条数
	var trainType="";
  	var startTime="";
  	var endTime="";
	getload(currentPage,pageSize);
	function getload(currentPage,pageSize){
		var data={
			"offset":currentPage,
			"limit":pageSize
		};
	    $.ajax({
	        type:"post",
	        url:"http://49.4.109.191:8080/jtrain/v1/api/trainManage/load",
	        async: false,
	        dataType: "json",
	        contentType:"application/json",
	        data:JSON.stringify(data),
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
	        success:function(result){
	            if(result.statusCode=="200"){
	            	pageTotal=Math.ceil(result.data.count/pageSize);
	            if(result.data.count%pageSize==0){
	            	 endNumber=pageSize;
	            }else{
	            	endNumber=result.data.count%pageSize;
	            }
	            var categoryItemVOList=result.data; 
	            var list="";
	            var page="";
	            if(categoryItemVOList == ""){
	            	return;
	            }else{
	            	$("#select").empty();
	    			$("#select").append("<option value=''>すべて</option>");
	            	$.each(categoryItemVOList.trainCategoryInformationVOList,function(i,item){
	        			$("#select").append("<option value='"+item.trainType+"'>"+item.typeName+"</option>");
	            	})
	            	$.each(categoryItemVOList.trainInformationVOList,function(i,item){
	            		list+="<div class='media border-bt' style='margin-top:15px'><div class='media-left media-middle'><a href='#'><img class='media-object' width='150px' height='100px' src='"+item.trainDraw+"'></a></div><div class='media-body form-group'><div class='col-md-9'><h4>"+item.trainTitle+"</h4><div class='train-detail overshow' style='max-width:630px;word-wrap:break-word' >"+item.trainDesc+"</div><div class='train-date'>"+item.updateDate+"</div></div><div class='col-md-3 paddingtop30'><button type='button' class='btn btn-training btn-size upDateBtn'data='"+item.trainId+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal1' data='"+item.trainId+"'>削除</button></div></div></div>"
	            	})
	            	if(pageTotal > 5){
	            		if(pageTotal - currentPage < 6){
		            		for(var i=pageTotal-5;i<=pageTotal;i++){
					            if(i==currentPage){
					            	page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
					            }else{
					            	page+="<li id='pointpoint'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
					            }
				            }
		            	}else{
		            		for(var i=currentPage;i<=currentPage+4;i++){
					            if(i==currentPage){
					            	page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
					            }else{
					            	page+="<li><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
					            }
				            }
			            	page+="<span id='liSpan'>···</span><li><a href='#' class='pageNumber'>"+pageTotal+"<span class='sr-only'>(current)</span></a></li>"
		            	}
	            	}else{
	            		for(var i=1;i<=pageTotal;i++){
		            		if(i==currentPage){
		            			page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
		            		}else{
		            			page+="<li><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
		            		}
	            		}
	            	}
	            }
	            $(".categoryItemVOList").html("");
	            $(".categoryItemVOList").append(list);
	            if(pageTotal==0){
	            	$(".pagination").html("");
	            }else{
	            	$(".pagination").html("");
	            	$(".pagination").append("<li><a href='#' aria-label='Previous' class='beforePage'><span aria-hidden='true'>&laquo;</span></a></li>"+page+"<li><a href='#' aria-label='Previous' class='nextPage'><span aria-hidden='true'>&raquo;</span></a></li><li><input type='text' class='form-control col-1 btn-pos no' placeholder='頁'></li><li><button type='button' class='btn btn-default btn-pos move'>ジャンプ</button></li>");
	            }
	           }  
	        }
	    });
	}
   $(".search").on("click",function(){
	    currentPage=1;
    	trainType=$("#select").val();
    	startTime=$("#startTime").val();
    	endTime=$("#endTime").val();
    	if(endTime!=""){
	    	if(startTime>endTime){
	    		alert("正しい日付を入力してください");
	    		return;
	    	}
    	}
    	var data={
    		"trainType":trainType,
    		"stratTime":startTime,
    		"endTime":endTime,
    		"offset":currentPage,
			"limit":pageSize
    	};
    	$.ajax({
    		type:"post",
    		url:"http://49.4.109.191:8080/jtrain/v1/api/trainManage/query",
    		async:false,
    		dataType: "json",
    		contentType:"application/json",
    		data:JSON.stringify(data),
    		beforeSend: function(request) {
    			request.setRequestHeader("Authorization", localStorage.getItem("token"));
    		},
    		success:function(result){
    			if(result.statusCode=="200"){
    				var trainItemVOList=result.data;
    				$.each(trainItemVOList,function(i,item){
    					pageTotal=Math.ceil(item.count/pageSize);
    					if(item.count%pageSize==0){
    		            	 endNumber=pageSize;
    		            }else{
    		            	endNumber=item.count%pageSize;
    		            }
    					return false; 
    				})
    				var list="";
    				var page="";
    				if(trainItemVOList==""){
    					$(".categoryItemVOList").html("");
    	            	$(".pagination").html("");
    					return;
    				}else{
    				$.each(trainItemVOList,function(i,item){
    					list+="<div class='media border-bt' style='margin-top:15px'><div class='media-left media-middle'><a href='#'><img class='media-object' width='150px' height='100px' src='"+item.trainDraw+"'></a></div><div class='media-body form-group'><div class='col-md-9'><h4>"+item.trainTitle+"</h4><div class='train-detail overshow' style='max-width:630px;word-wrap:break-word' >"+item.trainDesc+"</div><div class='train-date'>"+item.updateDate+"</div></div><div class='col-md-3 paddingtop30'><button type='button' class='btn btn-training btn-size upDateBtn'data='"+item.trainId+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal1' data='"+item.trainId+"'>削除</button></div></div></div>"
    				})
    				if(pageTotal > 5){
	            		if(pageTotal - currentPage < 6){
		            		for(var i=pageTotal-5;i<=pageTotal;i++){
					            if(i==currentPage){
					            	page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
					            }else{
					            	page+="<li id='pointpoint'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
					            }
				            }
		            	}else{
		            		for(var i=currentPage;i<=currentPage+4;i++){
					            if(i==currentPage){
					            	page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
					            }else{
					            	page+="<li><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
					            }
				            }
			            	page+="<span id='liSpan'>···</span><li><a href='#' class='pageNumber'>"+pageTotal+"<span class='sr-only'>(current)</span></a></li>"
		            	}
	            	}else{
	            		for(var i=1;i<=pageTotal;i++){
		            		if(i==currentPage){
		            			page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
		            		}else{
		            			page+="<li><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
		            		}
	            		}
	            	}
    			}
    				$(".categoryItemVOList").html("");
    				$(".categoryItemVOList").append(list);
    				if(pageTotal==0){
    					$(".pagination").html("");
    				}else{
    					$(".pagination").html("");
    					$(".pagination").append("<li><a href='#' aria-label='Previous' class='beforePage'><span aria-hidden='true'>&laquo;</span></a></li>"+page+"<li><a href='#' aria-label='Previous' class='nextPage'><span aria-hidden='true'>&raquo;</span></a></li><li><input type='text' class='form-control col-1 btn-pos no' placeholder='頁'></li><li><button type='button' class='btn btn-default btn-pos move'>ジャンプ</button></li>");
    				}
    			}
    		}
    	});
    });
   function getInfromation(trainType,startTime,endTime,currentPage,pageSize){
		var data={
	    		"trainType":trainType,
	    		"stratTime":startTime,
	    		"endTime":endTime,
	    		"offset":currentPage,
				"limit":pageSize
	    	};
	    	$.ajax({
	    		type:"post",
	    		url:"http://49.4.109.191:8080/jtrain/v1/api/trainManage/query",
	    		async:false,
	    		dataType: "json",
	    		contentType:"application/json",
	    		data:JSON.stringify(data),
	    		beforeSend: function(request) {
	    			request.setRequestHeader("Authorization", localStorage.getItem("token"));
	    		},
	    		success:function(result){
	    			if(result.statusCode=="200"){
	    				var trainItemVOList=result.data;
	    				$.each(trainItemVOList,function(i,item){
	    					pageTotal=Math.ceil(item.count/pageSize);
	    					if(item.count%pageSize==0){
	    		            	 endNumber=pageSize;
	    		            }else{
	    		            	 endNumber=item.count%pageSize;
	    		            }
	    					return false;
	    				})
	    				var list="";
	    				var page="";
	    				if(trainItemVOList==""){
	    					return;
	    				}else{
	    				$.each(trainItemVOList,function(i,item){
	    					list+="<div class='media border-bt' style='margin-top:15px'><div class='media-left media-middle'><a href='#'><img class='media-object' width='150px' height='100px' src='"+item.trainDraw+"'></a></div><div class='media-body form-group'><div class='col-md-9'><h4>"+item.trainTitle+"</h4><div class='train-detail overshow' style='max-width:630px;word-wrap:break-word' >"+item.trainDesc+"</div><div class='train-date'>"+item.updateDate+"</div></div><div class='col-md-3 paddingtop30'><button type='button' class='btn btn-training btn-size upDateBtn'data='"+item.trainId+"'>編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal1' data='"+item.trainId+"'>削除</button></div></div></div>"
	    				})
	    				if(pageTotal > 5){
		            		if(pageTotal - currentPage < 6){
			            		for(var i=pageTotal-5;i<=pageTotal;i++){
						            if(i==currentPage){
						            	page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
						            }else{
						            	page+="<li id='pointpoint'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
						            }
					            }
			            	}else{
			            		for(var i=currentPage;i<=currentPage+4;i++){
						            if(i==currentPage){
						            	page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
						            }else{
						            	page+="<li><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
						            }
					            }
				            	page+="<span id='liSpan'>···</span><li><a href='#' class='pageNumber'>"+pageTotal+"<span class='sr-only'>(current)</span></a></li>"
			            	}
		            	}else{
		            		for(var i=1;i<=pageTotal;i++){
			            		if(i==currentPage){
			            			page+="<li class='active'><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
			            		}else{
			            			page+="<li><a href='#' class='pageNumber'>"+i+"<span class='sr-only'>(current)</span></a></li>"
			            		}
		            		}
		            	}
	    			    }
	    				$(".categoryItemVOList").html("");
	    				$(".categoryItemVOList").append(list);
	    				if(pageTotal==""){
	    					$(".pagination").html("");
	    				}else{
	    					$(".pagination").html("");
	    					$(".pagination").append("<li><a href='#' aria-label='Previous' class='beforePage'><span aria-hidden='true'>&laquo;</span></a></li>"+page+"<li><a href='#' aria-label='Previous' class='nextPage'><span aria-hidden='true'>&raquo;</span></a></li><li><input type='text' class='form-control col-1 btn-pos no' placeholder='頁' ></li><li><button type='button' class='btn btn-default btn-pos move'>ジャンプ</button></li>");
	    				}
	    			}
	    		}
	    	});
	    }
   $(document).on("click",".pageNumber",function(){
		  currentPage=null;
		   $(this).contents().each(function(){ 
			   if(this.nodeType == 3){ 
				   currentPage=parseInt(this.wholeText); 
			   }
		   });
		  if((trainType=="")&&(startTime=="")&&(endTime=="")){
			   getload(currentPage,pageSize);  
		  }else{
			  getInfromation(trainType,startTime,endTime,currentPage,pageSize);
		  }   
	   });
	  $(document).on("click",".beforePage",function(){
		  if(currentPage==1){
			  return;
		  }
		  if((trainType=="")&&(startTime=="")&&(endTime=="")){  
			  getload(currentPage-1,pageSize);
		  }else{
			  getInfromation(trainType,startTime,endTime,currentPage-1,pageSize);
		  }   
		  currentPage=currentPage-1;
	  });
	  $(document).on("click",".nextPage",function(){
		  if(currentPage==pageTotal){
			  return;
		  }
		  if((trainType=="")&&(startTime=="")&&(endTime=="")){  
			  getload(currentPage+1,pageSize);
		  }else{
			  getInfromation(trainType,startTime,endTime,currentPage+1,pageSize);
		  }
		   currentPage=currentPage+1;
	  });
	   $(document).on("input propertychange",".no",function(){
		   this.value=this.value.replace(/[^\d]/g,'');
	   });
	  $(document).on("click",".move",function(){
		  if($(".no").val()==""){
			  return;
		  }
		  if(parseInt($(".no").val())>pageTotal){
			  $(".no").val("");
			  return;
		  }
		  currentPage=parseInt($(".no").val());
		  if((trainType=="")&&(startTime=="")&&(endTime=="")){  
			  getload(currentPage,pageSize);
		  }else{
			  getInfromation(trainType,startTime,endTime,currentPage,pageSize);
		  }
	  });
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
    $(".deleteTr1").click(function(){
        $("#myModal1").modal('hide');
        $.ajax({
            type:"delete",
            url:"http://49.4.109.191:8080/jtrain/v1/api/trainManage/delete/"+trainId,
             dataType : 'json',   
     		beforeSend: function(request) {
    			request.setRequestHeader("Authorization", localStorage.getItem("token"));
    		},
            success:function(result){
                if(result.statusCode=="200"){
                    if(currentPage==pageTotal){
                        if(endNumber-1<1){
                        	if((trainType=="")&&(startTime=="")&&(endTime=="")){
                        		getload(currentPage-1,pageSize);
                            	currentPage=currentPage-1;
                        	}else{
                        		getInfromation(trainType,startTime,endTime,currentPage-1,pageSize);
                        		currentPage=currentPage-1;
                        	}	
                        }else{
                        	if((trainType=="")&&(startTime=="")&&(endTime=="")){
                        		getload(currentPage,pageSize);
                        	}else{
                        		getInfromation(trainType,startTime,endTime,currentPage,pageSize);
                        	}
                        }
                      }else{
                            if((trainType=="")&&(startTime=="")&&(endTime=="")){
                        	    getload(currentPage,pageSize);
                        	}else{
                        		getInfromation(trainType,startTime,endTime,currentPage,pageSize);
                        	}
                        }
                    media.remove();
                }else{
                	$("#message").html("最初にトレーニング教材を削除してください");
                	$("#message").removeClass("ok");
                	$("#message").addClass("error");
                	if((trainType=="")&&(startTime=="")&&(endTime=="")){
                		getload(currentPage,pageSize);
                    	currentPage=currentPage;
                	}else{
                		getInfromation(trainType,startTime,endTime,currentPage,pageSize);
                		currentPage=currentPage;
                	}
                }
           }
      });
   });
});
