$(function(){
	
	function start() {
		var mybody = document.getElementById("calen").getElementsByTagName("tbody")[0];  
	  
		for(var j = 0; j < 24; j++) {  

			var rows=document.createElement("tr");
			
			for(var i = 0; i < 8; i++) {  
			
				 var cells=document.createElement("td");
				 
				 rows.appendChild(cells);
			};
			 mybody.appendChild(rows);
			 
			 if(j<10){
					
				rows.getElementsByTagName("td")[0].innerText="0"+j+":"+"00";
			}else{
				
				rows.getElementsByTagName("td")[0].innerText=j+":"+"00";
			}
		};

	} 
	start();
	
	var Tr=document.getElementById("calen").getElementsByTagName("tr");
	var Td=document.getElementById("calen").getElementsByTagName("td");

	for (i=0;i<Tr.length;i++){

		var Td =Tr[i].getElementsByTagName("td");

		for(j=1;j<Td.length;j++){

			Td[j].onclick=function(){

				var count=prompt("请输入就诊人数","");

				if(count != "" && count != null){
					this.innerText=count+"人";
					this.className="On";
				}else{
					this.className="";
				}
				
			}
		}
	}
	
});