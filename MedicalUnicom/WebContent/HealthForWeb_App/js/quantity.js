var $=(function (Element){ 
return function (){
if(Element.indexOf("#") != -1){
	return document.getElementById(Element.replace("#",""));
}else if (Element.indexOf(".") != -1){
	return document.getElementsByClassName(Element.replace(".",""));
}else{
	return document.getElementsByTagName(Element);
}
}(Element);
});

var quantity_vid=$(".quantity_vid")[0];
var quantity_call=$(".quantity_call")[0];

function minus_call(a){
	if(a =="vid"){
		if(parseInt(quantity_vid.value) > 1){
			quantity_vid.value=parseInt(quantity_vid.value)-1;
		}else{
			alert("次数不能小于1次")
		}
	}else if(a =="call"){
		if(parseInt(quantity_call.value) > 1){
			quantity_call.value=parseInt(quantity_call.value)-1;
		}else{
			alert("次数不能小于1次")
		}
	}
}

function plus_call(b){
	if(b =="vid"){
			quantity_vid.value=parseInt(quantity_vid.value)+1;
	}else if(b =="call"){
		quantity_call.value=parseInt(quantity_call.value)+1;
	}
}
