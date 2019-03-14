$.ajaxSetup({cache:false});
var trainId=null;
$(function(){
	document.onkeydown = function(e){
	    if(e.keyCode == 116){
	        e.preventDefault();
	        location.reload();
	    }
	}
	   getTrainType();
	   getTeacher();
	   $("#trainTitle").val("");
	   $("#trainDesc").val("");
	   $(document).on("change","#sourceType",function(){
		   upload();
	   });
	   var flagCheck=0;
	var getData= function(){
		$.ajax({
			type:"get",
			url:"http://49.4.109.191:8080/jtrain/v1/api/trainManage/select/"+trainId,
			async: false,
			dataType: "json",
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
			success:function(result){
				if(result.statusCode=="200"){
					var trainItemVO=result.data;
					var list="";
					$.each(trainItemVO.trainMaterialVOList,function(i,item){
						if(item.sourceType==1){
							list+="<tr><td width='10%'>ビデオ</td>";
						}
						if(item.sourceType==2){
							list+="<tr><td width='10%'>テキスト</td>"
						}
						list+="<td width='35%'>"+item.sourceTitle+"</td><td width='35%'>"+item.sourceDesc+"</td><td width='20%'><button type='button' class='btn btn-training btn-size upDateBtn' data='"+item.sourceNo+"' >編集</button><button type='button' class='btn btn-default col-md-offset-1 btn-size launchModal' data='"+item.sourceNo+"'>削除</button></td></tr>"	
					})
					$("table tr").eq(1).nextAll().remove();
					$("table").append(list);
				}
			}
	  });
	}
	   $("#trainType").change(function(){
		   getTeacher1();
	   });
	   var trainDraw=""; 
	   $("#trainDraw").on("change",function(){
		    var file = $(this)[0].files[0];
		    var formdat=file.name.substring(file.name.lastIndexOf("."));
		    if((formdat == ".png")||(formdat == ".jpg")||(formdat == ".jpeg")){
		        var formData = new FormData();
		        formData.append("file",file);
		        $.ajax({
		        	type:"post",
		        	url:"http://49.4.109.191:8080/jtrain/file/upload",
		        	dataType:"json",
		        	async: false,
		        	cache:false,
		            data:formData,
		            contentType:false,
		            processData:false,
					beforeSend: function(request) {
						request.setRequestHeader("Authorization", localStorage.getItem("token"));
					},
		        	success:function(result){
		        		if(result.statusCode=="200"){
		        		   $("#message").html("アップロード成功");
	  			           $("#message").removeClass("error");
	  		    		   $("#message").addClass("ok");
		        		   trainDraw=result.data.url;
		        		}
		        	}
		        });
		    }else{
		    	alert("正しい画像フォーマットをアップロードしてください");
		    }
	   });
	   //添加培训信息
       $(".saveBtn").click(function(){
	    	var trainType=$("#trainType").val();
	    	if(trainType==""){
	    		$("#message").html("トレーニングカテゴリの情報は空にできません");
	    		$("#message").removeClass("ok");
	    		$("#message").addClass("error");
	    		return;
	    	}
	        var teacher=$("#teacher").val();
	        if(teacher==""){
	        	$("#message").html("教師の情報は空ではありません");
	        	$("#message").removeClass("ok");
	    		$("#message").addClass("error");
	    		return;
	        }
	        var trainTitle=$.trim($("#trainTitle").val());
	        if(trainTitle==""){
	        	$("#message").html("トレーニングのタイトルは空ではありません");
	        	$("#message").removeClass("ok");
	    		$("#message").addClass("error");
	    		$("#trainTitle").val("");
	        	$("#trainTitle").focus();
	        	return;
	        }
	        var trainDesc=$.trim($("#trainDesc").val());
	        if(trainDesc==""){
	        	$("#message").html("トレーニングの紹介情報を空にすることはできません");
	        	$("#message").removeClass("ok");
	    		$("#message").addClass("error");
	    		$("#trainDesc").val("");
	        	$("#trainDesc").focus();
	        	return;	
	        }
	        if(trainDraw==""){
	        	$("#message").html("アップロード画像は空ではありません");
	        	$("#message").removeClass("ok");
	    		$("#message").addClass("error");
	        	return;
	        }
	        var data={
   			     "trainType":trainType,
   			     "trainTeacher":teacher,
   			     "trainTitle":trainTitle,
   			     "trainDesc":trainDesc,
   			     "trainDraw":trainDraw
   			  };
   			  $.ajax({
   				  type:"post",
   				  url:"http://49.4.109.191:8080/jtrain/v1/api/trainManage/insert",
   				  dataType:"json",   
   				  async: false,
   				  contentType:"application/json",
   			      data:JSON.stringify(data),
	   				beforeSend: function(request) {
	   					request.setRequestHeader("Authorization", localStorage.getItem("token"));
	   				},
   			      success:function(result){
   			         if(result.statusCode=="200"){
   			             trainId=result.data;
   			             $(".appendBtn").css("display","block");
   			             $(".saveBtn").hide();
   			             $(".updateBtn").show();
   			             $("#message").html("追加されました");
   			             $("#message").removeClass("error");
   		    		     $("#message").addClass("ok");
   			         }else{
   			             $("#message").html("追加に失敗しました");
   			             $("#message").removeClass("ok");
			             $("#message").addClass("error");
   			             $("#trainTitle").val("");
   			             $("#trainTitle").focus();
   			         }
   			    }  
   		   });
       });
       //变更培训信息
       $(document).on("click",".updateBtn",function(){
    	   var trainType=$("#trainType").val();
    	   if(trainType==""){
    		    $("#message").html("トレーニングカテゴリの情報は空にできません");
    		    $("#message").removeClass("ok");
	    	    $("#message").addClass("error");
	    		return;
	    	}
           var teacher=$("#teacher").val();
           if(teacher==""){
        	    $("#message").html("教師の情報は空ではありません");
        	    $("#message").removeClass("ok");
	    		$("#message").addClass("error");
	    		return;
	        }
           var trainTitle=$.trim($("#trainTitle").val());
           if(trainTitle==""){
        	    $("#message").html("トレーニングのタイトルは空ではありません");
        	    $("#message").removeClass("ok");
	    		$("#message").addClass("error");
	    		$("#trainTitle").val("");
	        	$("#trainTitle").focus();
	        	return;
	        }
           var trainDesc=$.trim($("#trainDesc").val());
           if(trainDesc==""){
        	    $("#message").html("トレーニングの紹介情報を空にすることはできません");
        	    $("#message").removeClass("ok");
	    		$("#message").addClass("error");
	    		$("#trainDesc").val("");
	        	$("#trainDesc").focus();
	        	return;	
	       }
           var data={
           	"trainId":trainId,
           	"trainType":trainType,
           	"trainTeacher":teacher,
           	"trainTitle":trainTitle,
           	"trainDesc":trainDesc,
           	"trainDraw":trainDraw
           };
   		$.ajax({
   			type:"put",
   			url:"http://49.4.109.191:8080/jtrain/v1/api/trainManage/update",
   			dataType : "json",   
   			contentType:"application/json",
   			data:JSON.stringify(data),
			beforeSend: function(request) {
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
   			success:function(result){
   				if(result.statusCode=="200"){
   				    $("#message").html("更新されました");
		            $("#message").removeClass("error");
	    		    $("#message").addClass("ok");
   				}else{
   				    $("#message").html("更新に失敗しました");
   				    $("#message").removeClass("ok");
		            $("#message").addClass("error");
   					$("#trainTitle").val("");
   					$("#trainTitle").focus();
   				}
   			}	
   		});
      });
       //弹出窗口
       $(document).on("click",".appendBtn",function(){
    	   $(".showMessage").css("display","none");
    	   $(".message").css("display","block");
    	   $("#sourceType option[value='"+1+"']").prop("selected",true);
    	   $("#sourceType").removeAttr("disabled");
       	   $("#sourceTitle").val("");
       	   $("#sourceDesc").val("");
       	   $(".showfile").html("");
       	   $("#preview1").attr("src","http://49.4.109.191:8080/jtrain/images/video-demo.jpg");
    	   $("#upDateModal").modal('show');
    	   flagCheck=1;
       })
       //关闭窗口
       $(document).on("click",".dlTrainBtn",function(){
    	    $("#upDateModal").modal('hide');  
       });
       $('#upDateModal').on('hidden.bs.modal',function(){
    	   $("#thelist").html("");
    	   $(".showMessage").css("display","none");
    	   $(".message").css("display","none");
    	   $("#message1").html("");
    	   $("#message1").removeClass("error");
    	   $("#message1").removeClass("ok");
    	   
       });
    	var tr="";
    	var sourceNo="";
    	//弹出窗口并获取删除信息
    	$(document).on("click",".launchModal",function(){
    		$("#myModal").modal('show');
    		tr=$(this).parents("tr");
    		sourceNo=$(this).attr("data");
    	})
    	//确认删除
    	$(".deleteTr").click(function(){
    		$("#myModal").modal('hide');
    		$.ajax({
    			type:"delete",
    			url:"http://49.4.109.191:8080/jtrain/v1/api/resource/delete/"+sourceNo,
    			async: false,
    	        dataType: "json",
    			beforeSend: function(request) {
    				request.setRequestHeader("Authorization", localStorage.getItem("token"));
    			},
    	        success:function(result){
    	        	if(result.statusCode=="200"){
    					tr.remove();
    				}else{
    					$("#message").html("情報が使用されています");
    		            $("#message").removeClass("ok");
    	    		    $("#message").addClass("error");
    				}
    	        }
    		});
    	});
    	var sourceDraw="";
    	$("#sourceDraw").on("change",function(){
    	   var file = $(this)[0].files[0];
		    var formdat=file.name.substring(file.name.lastIndexOf("."));
		    if((formdat == ".png")||(formdat == ".jpg")||(formdat == ".jpeg")){
		       var formData = new FormData();
		       formData.append("file",file);
		       $.ajax({
		        	type:"post",
		        	url:"http://49.4.109.191:8080/jtrain/file/upload",
		        	dataType:"json",
		        	async: false,
		        	cache:false,
		            data:formData,
		            contentType:false,
		            processData:false,
					beforeSend: function(request) {
						request.setRequestHeader("Authorization", localStorage.getItem("token"));
					},
		        	success:function(result){
		        		if(result.statusCode=="200"){
		        			$("#message1").html("アップロード成功");
		  			        $("#message1").removeClass("error");
		  		    		$("#message1").addClass("ok");
		        			sourceDraw=result.data.url;
		        		}else if(result.statusCode=="401"){
							alert("あなたのアカウント接続は異常です,もう一度ログインしてください");
							window.location.href="http://49.4.109.191:8080/jtrain/html/login.html";
						}
		        	 }
		          });
		    }else{
		    	alert("正しい画像フォーマットをアップロードしてください");
		    }
	       });
    	    var sourcePath="";
            var sourceLength=null;
		    var $thelist = $('#thelist');
		    var chunkSize = 50 * 1024 * 1024;
		    WebUploader.Uploader.register({
		        'before-send-file': 'beforeSendFile',
		        'before-send': 'beforeSend'
		    }, {
		        beforeSendFile: function (file) {
		            console.log("beforeSendFile");
		            var task = new $.Deferred();
		            uploader.md5File(file).progress(function (percentage) {
		                console.log('md5の進捗状況を計算する:', percentage);
		                getProgressBar(file, percentage, "MD5", "MD5");
		            }).then(function (val) { 
		                console.log('md5 result:', val);
		                file.md5 = val;
		                file.uid = WebUploader.Base.guid();
		                $.post("http://49.4.109.191:8080/jtrain/upload/checkFileMd5", {uid: file.uid, md5: file.md5},
		                    function (data) {
		                       console.log(data.status);
		                       var status = data.status.value;
		                       task.resolve();
		                       if (status == 101) {
		                       } else if (status == 100) {
		                           uploader.skipFile(file);
		                           sourcePath=data.data.name;
		                           var format=file.name.substring(file.name.lastIndexOf("."));
		       			        	if(format==".pdf"){
		       			        		sourceLength=data.data.page;
		       			        	}else{
		       			        		sourceLength=data.data.sencond;
		       			        	}
		                           file.pass = true;
		                       } else if (status == 102) {
		                           file.missChunks = data.data;
		                       }
		                 });
		            });
		            return $.when(task);
		        },
		        beforeSend: function (block) {
		            console.log("block")
		            var task = new $.Deferred();
		            var file = block.file;
		            var missChunks = file.missChunks;
		            var blockChunk = block.chunk;
		            console.log("現在のブロック：" + blockChunk);
		            console.log("missChunks:" + missChunks);
		            if (missChunks !== null && missChunks !== undefined && missChunks !== '') {
		                var flag = true;
		                for (var i = 0; i < missChunks.length; i++) {
		                    if (blockChunk == missChunks[i]) {
		                        console.log(file.name + ":" + blockChunk + ":今すぐアップロードを開始。");
		                        flag = false;
		                        break;
		                    }
		                }
		                if (flag) {
		                    task.reject();
		                } else {
		                    task.resolve();
		                }
		            } else {
		                task.resolve();
		            }
		            return $.when(task);
		        }
		    });
	var uploader="";
	$('#upDateModal').on('shown.bs.modal',function(){
		upload();
    });
	function upload(){
		 if($("#sourceType").val()==1){
				  uploader = WebUploader.create({
			        pick: {
			            id: '#picker',
			            label: '選択'
			        },
			        formData: {
			            uid: 0,
			            md5: '',
			            chunkSize: chunkSize
			        },
			        swf: 'js/Uploader.swf',
			        chunked: true,
			        chunkSize: chunkSize, 
			        threads: 3,
			        server: 'http://49.4.109.191:8080/jtrain/upload/fileUpload',
			        auto: true,
			        accept:{
			             extensions: 'mp4',
			             mimeTypes: '.mp4'
			        },
			        disableGlobalDnd: true,
			        fileNumLimit: 1,
			        fileSizeLimit: 1024 * 1024 * 1024,
			        fileSingleSizeLimit: 1024 * 1024 * 1024
			    });
			  }else{
				  uploader = WebUploader.create({
				      pick: {
				          id: '#picker',
				          label: '選択'
				      },
				      formData: {
				          uid: 0,
				          md5: '',
				          chunkSize: chunkSize
				      },
				      swf: 'js/Uploader.swf',
				      chunked: true,
				      chunkSize: chunkSize,
				      threads: 3,
				      server: 'http://49.4.109.191:8080/jtrain/upload/fileUpload',
				      auto: true,
				      accept:{
			  		     title: 'Application',
			             extensions: 'pdf',
			             mimeTypes: 'application/pdf'
				      },
				      disableGlobalDnd: true,
				      fileNumLimit: 1,
				      fileSizeLimit: 1024 * 1024 * 1024,
				      fileSingleSizeLimit: 1024 * 1024 * 1024
				  });
			 }
		    uploader.on('fileQueued', function (file) {
		        console.log("fileQueued");
		        $(".showMessage").css("display","block");
		        $thelist.append('<div id="' + file.id + '" class="item">' +
		                '<h4 class="info" style="margin-top:5px">' + file.name + '</h4>' +
		                '<p class="state">アップロード待ち...</p>' +
		                '</div>');
		        $(".message").css("display","none");
		    });
		    uploader.onUploadBeforeSend = function (obj, data) {
		        console.log("onUploadBeforeSend");
		        var file = obj.file;
		        data.md5 = file.md5 || '';
		        data.uid = file.uid;
		    };
		    uploader.on('uploadProgress', function (file, percentage) {
		    	fadeOutProgress(file, 'MD5');
		        getProgressBar(file, percentage, "FILE", "アップロードの進行状況");
		    });
		    uploader.on('uploadSuccess', function (file,response){
		        var text = "アップロード済み";
		        var check=0;
		        if (file.pass) {
		            text = "ファイル秒経過"
		            check=1; 
		        }
		        $('#' + file.id).find('p.state').text(text);
		        if(check==0){
			        sourcePath=response.data.name;
			        var format=file.name.substring(file.name.lastIndexOf("."));
			        if(format==".pdf"){
			        	sourceLength=response.data.page;
			        }else{
			        	sourceLength=response.data.sencond;
			        }
		        }
		        $("#sourceType").attr("disabled","disabled");
		        
		    });
		    uploader.on('uploadError', function (file) {
		        $('#' + file.id).find('p.state').text('アップロードエラー');
		    });
		    uploader.on('uploadComplete', function (file) {
		    	fadeOutProgress(file, 'MD5');
		        fadeOutProgress(file, 'FILE');
		    });
	    }
	
		    function getProgressBar(file, percentage, id_Prefix, titleName) {
		        var $li = $('#' + file.id), $percent = $li.find('#' + id_Prefix + '-progress-bar');
		        if (!$percent.length) {
		            $percent = $('<div id="' + id_Prefix + '-progress" class="progress progress-striped active ">' +	
		                    '<div id="' + id_Prefix + '-progress-bar" class="progress-bar" role="progressbar" style="width: 0%">' +
		                    '</div>' +
		                    '</div>'
		            ).appendTo($li).find('#' + id_Prefix + '-progress-bar');
		        }
		        var progressPercentage = percentage * 100 + '%';
		        $percent.css('width', progressPercentage);
		        $percent.html(titleName + ':' + progressPercentage);
		    }

		    function fadeOutProgress(file, id_Prefix) {
		        $('#' + file.id).find('#' + id_Prefix + '-progress').fadeOut();
		    }
		    $(document).on("change","#sourceType",function(){
				var sourceType=$("#sourceType").val();
				  if(sourceType==1){
					  $("#preview1").attr("src","http://49.4.109.191:8080/jtrain/images/video-demo.jpg");
				  }else{
					  $("#preview1").attr("src","http://49.4.109.191:8080/jtrain/images/txt-demo.jpg");
				}
			});		
    	//保存资料信息
    	$(".save").on("click",function(){
    		if(flagCheck==1){
    		var sourceType =$("#sourceType").val();
    		var sourceTitle=$.trim($("#sourceTitle").val());
    		if(sourceTitle==""){
    			$("#message1").html("データタイトルは空白にすることはできません");
    			$("#message1").removeClass("ok");
    		    $("#message1").addClass("error");
    		    $("#sourceTitle").val("");
    			$("#sourceTitle").focus();
    			return;
    		}
    		var sourceDesc=$.trim($("#sourceDesc").val());
    		if(sourceDesc==""){
    			$("#message1").html("情報を空にすることはできません");
    			$("#message1").removeClass("ok");
    		    $("#message1").addClass("error");
    		    $("#sourceDesc").val("");
    			$("#sourceDesc").focus();
    			return;
    		}
    		if(sourcePath==""){
    			$("#message1").html("プロフィール情報をアップロードすることはできません");
    			$("#message1").removeClass("ok");
    		    $("#message1").addClass("error");
    			return;
    		}
    		if(sourceDraw==""){
    			sourceDraw=$("#preview1").attr("src");
    		}
    		var data={
        	    "trainId":trainId,
        	    "sourceType":sourceType,
        	    "sourcePath":sourcePath,
        	    "sourceTitle":sourceTitle,
        	    "sourceDesc":sourceDesc,
        	    "sourceDraw":sourceDraw,
        	    "sourceLength":sourceLength
        	 };
        	 $.ajax({
        	  	 type:"post",
        	  	 url:"http://49.4.109.191:8080/jtrain/v1/api/resource/insert",
        	  	 dataType:"json",   
        	  	 async: false,
        	  	 contentType:"application/json",
        	  	 data:JSON.stringify(data),
        	  	beforeSend: function(request) {
    				request.setRequestHeader("Authorization", localStorage.getItem("token"));
    			},
        	  	 success:function(result){
        	  	 if(result.statusCode=="200"){
        	  	      $("#upDateModal").modal('hide');
        	  	      getData();
        	  	 }else{
        	  		 $("#message1").html("追加に失敗しました");
      			     $("#message1").removeClass("ok");
      		         $("#message1").addClass("error");
        		  	 $("#sourceTitle").val("");
        		  	 $("#sourceTitle").focus();
        	  	  }
        	  	}
        	});
    		}else{
    			var sourceType=$("#sourceType").val();
        		var sourceTitle=$.trim($("#sourceTitle").val());
        		if(sourceTitle==""){
        			$("#message1").html("データタイトルは空白にすることはできません");
      			    $("#message1").removeClass("ok");
      		        $("#message1").addClass("error");
      		        $("#sourceTitle").val("");
        			$("#sourceTitle").focus();
        			return;
        		}
        		var sourceDesc=$.trim($("#sourceDesc").val());
        		if(sourceDesc==""){
        			$("#message1").html("情報を空にすることはできません");
      			    $("#message1").removeClass("ok");
      		        $("#message1").addClass("error");
      		        $("#sourceDesc").val("");
        			$("#sourceDesc").focus();
        			return;
        		}
    			  var data={
                		"sourceNo":sourceNo,
                		"sourceType":sourceType,
                		"sourceTitle":sourceTitle,
                		"sourceDesc":sourceDesc,
                		"sourceDraw":sourceDraw
                    };
                	$.ajax({
                		type:"put",
                		url:"http://49.4.109.191:8080/jtrain/v1/api/resource/update",
                		async: false,
                	    dataType: "json",
                	    contentType:"application/json",
                	    data:JSON.stringify(data),
        				beforeSend: function(request) {
        					request.setRequestHeader("Authorization", localStorage.getItem("token"));
        				},
                	    success:function(result){
                	        if(result.statusCode=="200"){
                	        	$("#upDateModal").modal('hide');
                	        	getData();
                	        }else{
                	        	$("#message1").html("更新に失敗しました");
                  			    $("#message1").removeClass("ok");
                  		        $("#message1").addClass("error");
            	  	            $("#sourceTitle").val("");
            	  	            $("#sourceTitle").focus();
                	        }
                	  }
                });
    		}
    	});
    	//编辑回显
    	$(document).on("click",".upDateBtn",function(){
    		$(".showMessage").css("display","block");
     	    $(".message").css("display","none");
    		$("#upDateModal").modal('show');
    		sourceNo=$(this).attr("data");
    		$.ajax({
    			type:"get",
    			url:"http://49.4.109.191:8080/jtrain/v1/api/resource/select/"+sourceNo,
    			async: false,
    	        dataType: "json",
    			beforeSend: function(request) {
    				request.setRequestHeader("Authorization", localStorage.getItem("token"));
    			},
    	        success:function(result){
    	        	if(result.statusCode=="200"){
    	        		var trainVO=result.data;
    	        		$("#sourceType option[value='"+trainVO.sourceType+"']").prop("selected",true);
    	        		$("#sourceType").attr("disabled","disabled");
    	        		$("#preview1").attr("src",trainVO.sourceDraw);
    	        		$("#sourceTitle").val(trainVO.sourceTitle);
    	        		$("#sourceDesc").val(trainVO.sourceDesc);
    	        		$("#thelist").html(trainVO.sourcePath);
    	        	}
    	        }
    		});
    	   flagCheck=0;
    	});
   });
	//查询全部种别信息
	function getTrainType(){
		$("#trainType").empty();
		$("#trainType").append("<option value=''>選択してください</option>");
		$.ajax({
			type:"get",
			url:"http://49.4.109.191:8080/jtrain/v1/api/trainType/load",
			async: false,
            dataType: "json",
    		beforeSend: function(request) {
    			request.setRequestHeader("Authorization", localStorage.getItem("token"));
    		},
            success:function(result){
                if(result.statusCode=="200"){
                	var ItemVOList=result.data;
                	$.each(ItemVOList.trainCategoryInformationVOList,function(i,item){
                		$("#trainType").append("<option value='"+item.trainType+"'>"+item.typeName+"</option>");
                	})
                }
            }
		});
	};
	//初始化时老师信息
	function getTeacher(){
		$("#teacher").empty();
		$("#teacher").append("<option value=''>先生を選んでください</option>");
	}
	//种别与老师联动
	function getTeacher1(){
		var trainType =$("#trainType").val();
		var data={
				"trainType":trainType
		};
		$.ajax({
			type:"post",
			url:"http://49.4.109.191:8080/jtrain/v1/api/trainTeacher/load",
			async: false,
            dataType: "json",
            contentType:"application/json",
            data:JSON.stringify(data),
    		beforeSend: function(request) {
    			request.setRequestHeader("Authorization", localStorage.getItem("token"));
    		},
            success:function(result){
                if(result.statusCode=="200"){
                	var TeacherVOList=result.data;
                	$("#teacher").html("");
                	$.each(TeacherVOList.teacherInformationVOList,function(i,item){
                		$("#teacher").append("<option value='"+item.no+"'>"+item.name+"</option>");
                	})
                	if(TeacherVOList.teacherInformationVOList==""){
                		$("#teacher").append("<option value=''>先生を選んでください</option>");
                	}
                }
            }
		});	
	};