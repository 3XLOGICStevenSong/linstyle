//onchange="previewImage(this)" 
$(function(){
	$("#Photofile").on("change",function(){

		var img=new Array();
		for(i=0;i<document.getElementById('Photofile').files.length;i++){
			if(window.FileReader){
				oFReader= new FileReader();
				oFReader.readAsDataURL(document.getElementById('Photofile').files[i]);
				oFReader.onload= function (oFREvent) {
					img[i]=oFREvent.target.result;
					var pic="<span><img src='"+img[i]+"'><span class='delete'>x</span></span>"
					$(".imgBox").append(pic);
				}; 
			}
		}
	})
	
})
