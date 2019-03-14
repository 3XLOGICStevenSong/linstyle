/**
 * 
 */
document.onkeydown = function(e){
    if(e.keyCode == 116){
        e.preventDefault(); //组织默认刷新
        var iframeWindow = document.getElementById("iframe").contentWindow;
        var currentHref = iframeWindow.document.location.href;
        $("#iframe").prop("src",currentHref);
    }
}
/*document.oncontextmenu=function(){return false};//禁止右键刷新
*/