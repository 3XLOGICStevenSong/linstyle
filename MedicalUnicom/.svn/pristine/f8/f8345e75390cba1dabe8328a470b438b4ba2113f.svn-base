$(function(){
	var pid=getCookie("pid");
	var link=window.location.href;
	var keyVal=link.split("&");
	var doctorId="";
	var time="";
	var depId="";
	var depName="";
	var fee="";
	for(i=0;i<keyVal.length;i++){
		if(keyVal[i].indexOf("doctorId") != -1 ){
			doctorId=keyVal[i].substring(keyVal[i].lastIndexOf("=")+1,keyVal[i].length);
		}else if(keyVal[i].indexOf("time") != -1 ){
			time=keyVal[i].substring(keyVal[i].lastIndexOf("=")+1,keyVal[i].length).replace("%"," ");
		}else if(keyVal[i].indexOf("depId") != -1 ){
			depId=keyVal[i].substring(keyVal[i].lastIndexOf("=")+1,keyVal[i].length);
		}else if(keyVal[i].indexOf("depName") != -1 ){
			depName=decodeURI(keyVal[i].substring(keyVal[i].lastIndexOf("=")+1,keyVal[i].length));
		}else if(keyVal[i].indexOf("ToFee") != -1 ){
			fee=keyVal[i].substring(keyVal[i].lastIndexOf("=")+1,keyVal[i].length);
		}
	}
	
	$(".sym").text(depName);
	$("title").text($(".titleArea h2").text());
	
	function setCookie(name,value){
	    /*
	    var Days = 30;
	    var exp = new Date();
	    exp.setTime(exp.getTime() + Days*24*60*60*1000);*/
	    document.cookie = name + "="+ escape (value) ;/*+ ";expires=" + exp.toGMTString()*/
	}
	function getCookie(name){
		
	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	 
	    if(arr=document.cookie.match(reg))
	 
	        return (arr[2]);
	    else
	        return null;
	};
	
	$.ajax({
		type:"get",
		url:"/MedicalUnicom/InqueryQuestion.do?doGetInqueryQuestionList",
		data:{
			depId:depId,
			patientId:pid
		},
		aync:false,
		dataType:"json",
		success:function(result){
			var term=result.inqueryQuestionList;
			var meno=result.memo;
			
			var index="";
			for(i=0;i<term.length;i++){
				var iqType=term[i].iqType;
				var iqName=term[i].iqName;
				var iqId=term[i].iqId;
				var inqueryResultDTO=term[i].inqueryResultDTO;
				var inqueryAnswerDTO=term[i].inqueryAnswerDTO;
				
				if(iqType == 2){
					var AnserText="";
					if(inqueryAnswerDTO != null){
						AnserText=inqueryAnswerDTO[0].iaContent;
					}
					index+="<div class='Ask' data='"+iqId+"'><strong>"+iqName+"</strong>"+"<p><input type='text' id='"+iqId+"' value='"+AnserText+"' /></p></div>";
				}else if(iqType == 1){
					
					var checkbox="";

					for(k=0;k<inqueryResultDTO.length;k++){
						if(inqueryResultDTO[k].irType == 0){
							checkbox+="<label for='"+"for"+inqueryResultDTO[k].irId+"'><input type='checkbox' name='"+"chk"+inqueryResultDTO[k].irId+"' id='"+"for"+inqueryResultDTO[k].irId+"'/>"+inqueryResultDTO[k].irName+"</label>";
						}else if(inqueryResultDTO[k].irType == 1){
							checkbox+="<label for='"+"for"+inqueryResultDTO[k].irId+"'><input type='checkbox' name='"+"chk"+inqueryResultDTO[k].irId+"' id='"+"for"+inqueryResultDTO[k].irId+"'/>"+inqueryResultDTO[k].irName+"<input type='text' data='"+"chk"+inqueryResultDTO[k].irId+"' class='orther'></label>";
						}
					}
					
					if(inqueryAnswerDTO != null){
						for(j=0;j<inqueryAnswerDTO.length;j++){
							checkbox=checkbox.replace("for='for"+inqueryAnswerDTO[j].irId+"'","for='for"+inqueryAnswerDTO[j].irId+"' data='check'");
							checkbox=checkbox.replace("name='chk"+inqueryAnswerDTO[j].irId+"'","name='chk"+inqueryAnswerDTO[j].irId+"' checked='true'");
							if(inqueryAnswerDTO[j].iaMemo != null){
								checkbox=checkbox.replace("data='chk"+inqueryAnswerDTO[j].irId+"'","data='chk"+inqueryAnswerDTO[j].irId+"' value='"+inqueryAnswerDTO[j].iaMemo+"'");
							}
						}
					}

					index+="<div class='Ask' data='"+iqId+"'><strong>"+iqName+"</strong>"+"<p class='checkbox'>"+checkbox+"</p></div>";

				}else if(iqType == 0){

					var radio="";

					for(k=0;k<inqueryResultDTO.length;k++){
						radio+="<label for='"+"for"+inqueryResultDTO[k].irId+"'><input type='radio' name='"+iqId+"radio'"+" id='for"+inqueryResultDTO[k].irId+"'/>"+inqueryResultDTO[k].irName+"</label>";
					}
					
					if(inqueryAnswerDTO != null){
						for(j=0;j<inqueryAnswerDTO.length;j++){
							radio=radio.replace("for='for"+inqueryAnswerDTO[j].irId+"'","for='for"+inqueryAnswerDTO[j].irId+"' data='check'");
							radio=radio.replace("id='for"+inqueryAnswerDTO[j].irId+"'","id='for"+inqueryAnswerDTO[j].irId+"' checked='true'");
						}
					}

					index+="<div class='Ask' data='"+iqId+"'><strong>"+iqName+"</strong>"+"<p class='checkRadio'>"+radio+"</p></div>"

				}

			}
			
			$(".tiketform").html(index);

			$(".sym_text textarea").val(meno);
			
			$(".SubmitBtn").click(function(){
				var arr=[];
				var title="";
				var key="";
				for(i=0;i<$(".Ask").length;i++){
					
					title=$(".Ask")[i].getAttribute("data");
					key=$(".Ask")[i].getElementsByTagName("strong")[0].innerText;
					
					if($(".Ask")[i].getElementsByTagName("p")[0].className == "" || $(".Ask")[i].getElementsByTagName("p")[0].className == null){
						
						var val=[];
						
						if($(".Ask")[i].getElementsByTagName("input")[0].value != ""){

							for(k=0;k<$(".Ask")[i].getElementsByTagName("input").length;k++){
								var keyval=$(".Ask")[i].getElementsByTagName("input")[k].value
								val[k]={"iaContent":keyval};
							}
							arr.push({"iqId":title,"iqName":key,"inqueryAnswerDTOs":val});
							
						}
					}else if($(".Ask")[i].getElementsByTagName("p")[0].className == "checkRadio"){
						
						var val=[];

						for(j=0;j<$(".Ask")[i].getElementsByTagName("label").length;j++){
							
							if($(".Ask")[i].getElementsByTagName("label")[j].getAttribute("data")=="check"){

								var keyval=$(".Ask")[i].getElementsByTagName("label")[j].getAttribute("for").replace("for","");
								var keyval1=$(".Ask")[i].getElementsByTagName("label")[j].innerText;
								
								val.push({"irId":keyval,"irName":keyval1});

								arr.push({"iqId":title,"iqName":key,"inqueryAnswerDTOs":val});
							}	
						}
					}else if($(".Ask")[i].getElementsByTagName("p")[0].className == "checkbox"){

						var val=[];
						
						for(j=0;j<$(".Ask")[i].getElementsByTagName("label").length;j++){
							
							if($(".Ask")[i].getElementsByTagName("label")[j].getAttribute("data")=="check"){

								var keyval=$(".Ask")[i].getElementsByTagName("label")[j].getAttribute("for").replace("for","");
								var keyval1=$(".Ask")[i].getElementsByTagName("label")[j].innerText;
								
								if($(".Ask")[i].getElementsByTagName("label")[j].getElementsByClassName("orther")[0] != null){
									var keyval3=$(".Ask")[i].getElementsByTagName("label")[j].getElementsByClassName("orther")[0].value;
									val.push({"irId":keyval,"iaMemo":keyval3,"irName":keyval1});
								}else{
									val.push({"irId":keyval,"irName":keyval1});
								}
								
								arr.push({"iqId":title,"iqName":key,"inqueryAnswerDTOs":val});
								
							}
						}

					}
					
				}

				//var Ansers={inqueryQuestionList:[{"iqId":"2","iqName":"性别","inqueryAnswerDTOs":[{"irId":"2","irName":"2.女"}]},{"iqId":"3","iqName":"职业","inqueryAnswerDTOs":[{"iaContent":"按时"}]},{"iqId":"5","iqName":"体重","inqueryAnswerDTOs":[{"iaContent":"75kg"}]},{"iqId":"10","iqName":"2.是否在其它医院接受过检查?","inqueryAnswerDTOs":[{"irId":"3","irName":"1.是 "}]},{"iqId":"11","iqName":"3.医院是否是否有给开药?","inqueryAnswerDTOs":[{"irId":"6","irName":"2.否"}]},{"iqId":"12","iqName":"4.孩子曾经患过下列哪种疾病?","inqueryAnswerDTOs":[{"irId":"8","irName":"2.麻疹"},{"irId":"9","irName":"3.风疹"},{"irId":"21","irName":"15.其它（输入框）","iaMemo":"456789"}]},{"iqId":"13","iqName":"5.接受过哪种预防针?","inqueryAnswerDTOs":[{"irId":"22","irName":"1.BCG"},{"irId":"23","irName":"2.Hib"}]},{"iqId":"14","iqName":"6.孩子是否是过敏性体质?","inqueryAnswerDTOs":[{"irId":"35","irName":"1.是"}]}]};
				var Ansers={inqueryQuestionList:arr}
				var memo=$(".sym_text textarea").val();
				var recordsType=getCookie("recordsType");
				var Tel=getCookie("Tel");
				var appointId=getCookie("appointId");
				var appointTime="2017-11-04 09:00"
				
				$.ajax({
					type:"post",
					url:"/MedicalUnicom/Records.do?doAddPrivateRecords",
					data:{
						patientId:pid,
						appointId:appointId,
						memo:memo,
						appointTime:appointTime,
						recordsType:recordsType,
						telNum:Tel,
						depId:depId,
						inqueryQuestionList:JSON.stringify(Ansers.inqueryQuestionList)
					},
					aync:false,
					dataType:"json",
					success:function(result){
						if(result.returnCode == "0"){
							alert("预约成功");
							window.location.href="Pat_Index.html";
						}
					}
				})
			})
			
			$(".checkRadio label").click(function(){
				$(this).parent().children().attr("data","")
				if($(this).attr("data") != "" || $(this).attr("data") != null || $(this).attr("data") != "check"){
					$(this).attr("data","check");
				}
			})
			
			$(".checkbox input[type=checkbox]").click(function(){
				
				if($(this).parent().attr("data") != null){
					$(this).parent().removeAttr("data");
				}else{
					$(this).parent().attr("data","check");
				}
			})
		}
	})
	
	$(".imgBox").delegate(".delete","click",function(){
		$(this).parent().remove();
	})

})