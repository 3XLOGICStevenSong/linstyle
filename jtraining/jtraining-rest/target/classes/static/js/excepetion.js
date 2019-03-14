(function($){
$.ajaxSetup({
	   contentType:"application/json;charset=utf-8",
	   complete:function(XMLHttpRequest,textStatus){
	   },
	   statusCode: {
	     404: function() {
	         alert('リクエストメソッドが見つかりません');
	     },
	     401: function() {
	         alert('許可エラー');
	         window.parent.location.href="http://49.4.109.191:8080/jtrain/html/login.html";
	         return;
	     },
	     511: function() {
	         alert('データ検証エラー');
	     },
	     //512: function() {
	      //   alert('服务器有误。500');
	    // }
	   }
	});
})(jQuery)