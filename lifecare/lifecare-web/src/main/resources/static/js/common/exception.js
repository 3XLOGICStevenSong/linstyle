//$(function(){	
//$.ajaxSetup({
//		   contentType:"application/json;charset=utf-8",
//		   complete:function(XMLHttpRequest,textStatus){
//		   },
//		   statusCode: {
//		     404: function() {
//		         alert('请求方法找不到');
//		     },
//		     401: function() {
//		         alert('权限错误');
//		     },
//		     511: function() {
//		         alert('数据校验错误');
//		     },
//		     //512: function() {
//		      //   alert('服务器有误。500');
//		    // }
//		   }
//		});	
		
//		(function($){
//		    //首先备份一个原始的jquery.ajax()函数
//		    var _ajax = $.ajax;
//		    //重写jquery.ajax()函数
//		    $.ajax = function(options){
//		       // options.type = 'POST';
//		        options.error = function(XMLHttpRequest, msg, e){
//		            //统一错误处理
//		        	   switch (XMLHttpRequest.status){  
//		                case(500):  
//		                    alert("服务器系统内部错误");  
//		                    break;  
//		                case(401):  
//		                    alert("未登录");  
//		                    break;  
//		                case(403):  
//		                    alert("无权限执行此操作");  
//		                    break;  
//		                case(404):  
//		                    alert("连接服务器失败");  
//		                    break;  
//		                default:  
//		                    alert("未知错误");  
//		            }  
//		        },
//		        complete : function(httpRequest, status){
//		            //统一的结束状态显示
//		            //可以结合一个gif做进度显示
//		        }
//		    }
//		})(JQuery);
//		})

$(function () {
    $.ajaxSetup({
        error:function(request){
            if (!request || request.statusCode == 200)
                return;
            if (request.status == 401) { //
            	window.location.href="login.html"
            }
        },
//        complete: function (request, status) {
//            try {
//                var inFefresh = request.getResponseHeader('is-refresh');
//                var inLogin = request.getResponseHeader('in-login');
//                var refreshUrl = request.getResponseHeader('refresh-url');
//                if (inFefresh == '1' || inLogin == '1') {
//                    if (refreshUrl == null || refreshUrl == '') {
//                        window.location.reload();
//                    } else {
//                        try {
//                            refreshUrl = decodeURI(refreshUrl);
//                            top.location.href = refreshUrl;
//                        } catch (e) {
//                            window.location.reload();
//                        }
//                    }
//                }
//            } catch (e) {
//                //后台没有设置responseHeader则不做处理
//            }
//        }
    });
});