//左侧导航
$(function(){
  $(".subNav").click(function(){
	$(this).toggleClass("currentDd").siblings(".subNav").removeClass("currentDd")
	$(this).toggleClass("currentDt").siblings(".subNav").removeClass("currentDt")			
	// 修改数字控制速度， slideUp(500)控制卷起速度
	$(this).next(".navContent").slideToggle(500).siblings(".navContent").slideUp(500);
  });	
});

//上传图片1
    function imgPreview(fileDom){
        //判断是否支持FileReader
        if (window.FileReader) {
            var reader = new FileReader();
        } else {
            alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
        }

        //获取文件
        var file = fileDom.files[0];
        var imageType = /^image\//;
        //是否是图片
        if (!imageType.test(file.type)) {
            alert("请选择图片！");
            return;
        }
        //读取完成
        reader.onload = function(e) {
            //获取图片dom
            var img = document.getElementById("preview");
            //图片路径设置为读取的图片
            img.src = e.target.result;
        };
        reader.readAsDataURL(file);
    }
//上传图片2
    function imgPreview1(fileDom){
        //判断是否支持FileReader
        if (window.FileReader) {
            var reader = new FileReader();
        } else {
            alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
        }

        //获取文件
        var file = fileDom.files[0];
        var imageType = /^image\//;
        //是否是图片
        if (!imageType.test(file.type)) {
             alert("请选择图片！");
             return;
         }
        //读取完成
        reader.onload = function(e) {
            //获取图片dom
            var img = document.getElementById("preview1");
            //图片路径设置为读取的图片
            img.src = e.target.result;
        };
        reader.readAsDataURL(file);
    }
//上传文件
$(document).ready(function()
    {
        $(".myFile").change(function()
        {
            var arrs=$(this).val().split('\\');
            var filename=arrs[arrs.length-1];
            $(".showfile").html(filename);
        });
    });

