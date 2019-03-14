$.ajaxSetup({cache:false});
$(function(){
	document.onkeydown = function(e){
	    if(e.keyCode == 116){
	        e.preventDefault();
	        location.reload();
	    }
	}
	load();
	function load(){
		var data={};
        $.ajax({
            type:"post",
            url:"http://49.4.109.191:8080/jtrain/v1/api/trainType/query",
            async: false,
            dataType: "json",
            contentType:"application/json",
            data:JSON.stringify(data),
    		beforeSend: function(request) {
    			request.setRequestHeader("Authorization", localStorage.getItem("token"));
    		},
            success:function(result){
                if(result.statusCode=="200"){
                $("table tr").eq(1).nextAll().remove();
                var trainCategoryVOList=result.data; 
                var list="";
                if(trainCategoryVOList == ""){
                    return;
                }else{
                    $.each(trainCategoryVOList,function(i,item){
                    	$("table").append("<tr><td><input type='text' class='form-control clearStyle name' maxlength='10' style='background-color:white' readonly='readonly' placeholder='10ワード以内'   value='"+item.typeName+"' /></td><td><input class='form-control laydate-icon clearStyle'  name='time' style='background-color:white' readonly='readonly' value='"+item.insertTime+"' /></td><td><button type='button' class='btn btn-success categoryStop' style='display:none' data='"+item.trainType+"' >使用</button><button type='button' class='btn btn-danger categoryStart' style='display:none' data='"+item.trainType+"'>休止</button></td><td width='15%'><button type='button' class='btn btn-training categoryEdit' data='"+item.trainType+"'>編集</button><button type='button' class='btn btn-training category' style='display:none'>保存</button><button type='button' class='btn btn-training col-md-offset-1 cancel' style='display:none'>戻る</button></td></tr>");
                     if(item.useStatus==0){
                   		   $("table tr:last .categoryStart").show();
                   	    }
                     if(item.useStatus==1){
                   	       $("table tr:last .categoryStop").show();
	                   	}
                   })
                 }
               }
            }
        });
	}
        //点击添加出现新的一行
        $(".addBtn").click(function(){
        	$("table").append("<tr><td><input type='text' class='form-control type' maxlength='10' placeholder='10ワード以内'/></td><td><input class='form-control laydate-icon clearStyle'  name='time' style='background-color:white' readonly='readonly' value=''/></td><td><button type='button' class='btn btn-success deleteStop'>戻る</button></td><td><button type='button' class='btn btn-training categorySave'>保存</button></td></tr>");
        	$(".addBtn").hide();
        });
        //取消按钮
        $(document).on("click",".deleteStop",function(){
        	$(this).parents('tr').remove();
        	$(".addBtn").show();
        })
        //点击保存添加成功
        $(document).on("click",".categorySave",function(){
        	var typeName=$.trim($("table tr:last").find(".type").val());
        	if(typeName==""){
        		$("#message").html("タイプ情報を入力してください");
  			    $("#message").removeClass("ok");
  		        $("#message").addClass("error");
  		        $("table tr:last").find(".type").val("");
  		        $("table tr:last").find(".type").focus();
        	}else{
        	var data={
        		"typeName":typeName	
        	};
        	$.ajax({
                type:"post",
                url:"http://49.4.109.191:8080/jtrain/v1/api/trainType/insert",
                async: false,
                dataType: "json",
                contentType:"application/json",
                data:JSON.stringify(data),
        		beforeSend: function(request) {
        			request.setRequestHeader("Authorization", localStorage.getItem("token"));
        		},
                success:function(result){
                    if(result.statusCode=="200"){
                    	load();
                    	$(".addBtn").show();
                    }else{
                    	$("#message").html("追加に失敗しました");
          			    $("#message").removeClass("ok");
          		        $("#message").addClass("error");
                    	$("table tr:last").find(".type").val("");
                    	$("table tr:last").find(".type").focus();
                    }
               }
    		});
          }
        });
        //点击编辑
        $(document).on("click",".categoryEdit",function(){
        	$(".addBtn").hide();
        	var trainType=$(this).attr("data");
        	$(this).hide();
        	$(this).parents("tr").find("input[type='text']").removeClass("clearStyle");
        	$(this).parents("tr").find("input[type='text']").removeAttr("readonly");
        	$(this).parents("td").find(".category").show();
        	$(this).parents("td").find(".cancel").show();
        	//编辑保存
        	$(this).parents("td").find(".category").on("click", function(){
        		var fn=$(this);
        		var typeName=$.trim(fn.parents("tr").find("input[type='text']").val());
            	if(typeName==""){
            		$("#message").html("タイプ情報を入力してください");
      			    $("#message").removeClass("ok");
      		        $("#message").addClass("error");
      		        fn.parents("tr").find("input[type='text']").val("");
      		        fn.parents("tr").find("input[type='text']").focus();
            	}else{
            	var data={
            		"trainType":trainType,
            		"typeName":typeName	
            	};
            	$.ajax({
                    type:"put",
                    url:"http://49.4.109.191:8080/jtrain/v1/api/trainType/update",
                    async: false,
                    dataType: "json",
                    contentType:"application/json",
                    data:JSON.stringify(data),
            		beforeSend: function(request) {
            			request.setRequestHeader("Authorization", localStorage.getItem("token"));
            		},
                    success:function(result){
                        if(result.statusCode=="200"){
                        	load();
                        	$(".addBtn").show();
                        }else{
                        	$("#message").html("更新に失敗しました");
              			    $("#message").removeClass("ok");
              		        $("#message").addClass("error");
                        	fn.parents("tr").find("input[type='text']").val("");
                        	fn.parents("tr").find("input[type='text']").focus();
                        }
                    }
        		});
              }
        	});
        });
        
        $(document).on("click",".cancel",function(){
        	load();
        	$(".addBtn").show();
        });
        //点击休止更换成使用
       $(document).on("click",".categoryStart",function(){
    	   var trainType=$(this).attr("data");
    	   var data={
    			"trainType":trainType,
           		"useStatus":1	  
    	   }
    	   $(this).hide();
    	   $(this).parents("td").find(".categoryStop").show();
    	   $.ajax({
               type:"put",
               url:"http://49.4.109.191:8080/jtrain/v1/api/trainType/status",
               async: false,
               dataType: "json",
               contentType:"application/json",
               data:JSON.stringify(data),
	       	   beforeSend: function(request) {
	    			request.setRequestHeader("Authorization", localStorage.getItem("token"));
	    	   },
               success:function(result){
                   if(result.statusCode=="200"){
                	   return;
               }
            }
   		});
       });
       //点击使用更换成休止
       $(document).on("click",".categoryStop",function(){
    	   var trainType=$(this).attr("data");
    	   var data={
    			"trainType":trainType,
           		"useStatus":0	  
    	   }
    	   $(this).hide();
    	   $(this).parents("td").find(".categoryStart").show();
    	   $.ajax({
               type:"put",
               url:"http://49.4.109.191:8080/jtrain/v1/api/trainType/status",
               async: false,
               dataType: "json",
               contentType:"application/json",
               data:JSON.stringify(data),
	       		beforeSend: function(request) {
	    			request.setRequestHeader("Authorization", localStorage.getItem("token"));
	    		},
               success:function(result){
                   if(result.statusCode=="200"){
                	 return;
               }   
             }
   		  });
       });
    });