$(function(){
	var link=window.location.href;
	var param=link.split("&");
	var patientId="";
	for(i=0;i<param.length;i++){
		if(param[i].indexOf("patientId") != -1 ){
			patientId=param[i].substring(param[i].lastIndexOf("=")+1,param[i].length);
		}else{
			patientId=getCookie("pid");
		}
	}

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

	var Url="/MedicalUnicom/SymptomType.do?doGetSymptomList";

	$(".ChangeTap span").click(function(){
		if($(this).attr("class") == "menu1"){
			Url="/MedicalUnicom/SymptomType.do?doGetSymptomList";
			$(".checkForm").removeClass("dep");
			$(".MenuChangeTap").attr("id","off");
			$(".ChangeTap").attr("id","changeOff")
			getSymptomType("sym");
		}else if($(this).attr("class") == "menu2"){
			Url="/MedicalUnicom/DepartmentClass.do?doGetDepartmentList";
			$(".checkForm").addClass("dep")
			$(".MenuChangeTap").attr("id","off");
			$(".ChangeTap").attr("id","changeOff")
			getSymptomType("Dep");
		}else if($(this).attr("class") == "menu3"){
			window.location.href="Pat_Index.html";
		}else if($(this).attr("class") == "menu4"){
			window.location.href="Pat_Information.html";
		}
	})


	function getSymptomType(SymDep){
		
		$.ajax({
			type:"get",
			url:Url,
			date: {
				patientId:patientId
			},
			aync: false,
			dataType: "json",
			success:function(result){
				
				var Category="";
				var CateList="";
				
				if(SymDep=="sym"){
					
					Category=result.symptomTypeList;
					
					for(i=0;i<Category.length;i++){
						var small_Menu="";
						var sub_menu="";

						for(s=0;s<Category[i].symptomList.length;s++){
							if(s<4){
								small_Menu+=Category[i].symptomList[s].symptomName+", ";
							}
						}
						
						for(k=0;k<Category[i].symptomList.length;k++){
							sub_menu+="<li><label name='"+Category[i].symptomList[k].symptomId+"'>"+Category[i].symptomList[k].symptomName;+"</label></li>";
						}

						
						CateList+="<li class='tab"+(i+1)+"'><div class='menu'><p class='clearfix' id='"+Category[i].stId+"'><span class='Ticon'></span><strong>"+Category[i].stName+"</strong></p><p class='small_menu'>"+small_Menu+"</p><span class='op_icon'></span></div><div class='sub_menu'><ul class='color"+(i+1)+"'>"+sub_menu+"</ul></div></li>";
					}
				}else if(SymDep=="Dep"){
					
					Category=result.departmentTypeList;
					
					for(i=0;i<Category.length;i++){
						var small_Menu="";
						var sub_menu="";

						for(s=0;s<Category[i].departmentList.length;s++){
							if(s<4){
								small_Menu+=Category[i].departmentList[s].depName+", ";
							}
						}
						
						for(k=0;k<Category[i].departmentList.length;k++){
							sub_menu+="<li><label name='"+Category[i].departmentList[k].depId+"'>"+Category[i].departmentList[k].depName;+"</label></li>";
						}

						CateList+="<li class='tab"+(i+1)+"'><div class='menu'><p class='clearfix' id='"+Category[i].dcId+"'><span class='Ticon'></span><strong>"+Category[i].dcName+"</strong></p><p class='small_menu'>"+small_Menu+"</p><span class='op_icon'></span></div><div class='sub_menu'><ul class='color"+(i+1)+"'>"+sub_menu+"</ul></div></li>";
					}
				}
				
				$("#CateGory").html(CateList);

				$(".menu").click(function(){
					$(".menu .clearfix").removeClass("sbl");
					$(".menu .op_icon").removeClass("minus");
					$(".sub_menu").css({"height":"0","padding":"0"});
					
					if($(this).next().height() == 0){
						$(".sub_menu").css({"height":"0"});
						$(this).next().css({"height":$(this).next().find("ul").height(),"padding":"10px 0"});
						$(this).find(".clearfix").addClass("sbl");
						$(this).find(".op_icon").addClass("minus");
					}else{
						$(this).next().css({"height":"0","padding":"0",});
						$(this).find(".clearfix").removeClass("sbl");
						$(this).find(".op_icon").removeClass("minus");
					}
					
					/*
					if($(this).next().css("display") != "block"){
						$(".sub_menu").slideUp("fast")
						$(this).next().slideDown("fast")
						$(this).find(".clearfix").addClass("sbl");
						$(this).find(".op_icon").addClass("minus");
					}else{
						$(this).next().slideUp("fast")
						$(this).find(".clearfix").removeClass("sbl");
						$(this).find(".op_icon").removeClass("minus");
					}*/

				})
				
				$(".sub_menu label").click(function(){
					_methodFlg=0;
					_sortType=1;
					//AddDoc="0";
					
					//window.location.href="title="+$(".sbl").children("strong").text();
					
					if(SymDep=="sym"){
						_symptomId=$(this).attr("name");
						Symp="sym";
						$.ajax({
							type:"get",
							url:"/MedicalUnicom/InqueryView.do?doGetInqueryList",
							data:{
								patientId:patientId,
								symptomId: _symptomId,
								methodFlg: _methodFlg,
								sortType: _sortType,
								pageNum: 0,
								pageSize: 10
							},
							async:false,
							dataType:"json",
							success:function(result){
								var Doclist=result.doctorList;
								if(result.returnCode == "0" && Doclist != null){
									window.location.href="Pat_ResList.html?_symptomId="+_symptomId+"&title="+$(".sbl").children("strong").text()+"&patientId="+patientId;
								}else{
									var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>该科室缺少医生，请谅解</font></div></div>")
									$("body").append(Mess);
									setTimeout(function(){
										$(".Mess").remove();
										return false;
									},1500)
								}
							}
						})
						
						//getDocList(_symptomId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc);
					}else if(SymDep=="Dep"){
						_departmentId=$(".sbl").attr("id");
						Symp="Dep";
						$.ajax({
							type:"get",
							url:"/MedicalUnicom/Doctor.do?doGetDoctorListByDepId",
							data:{
								patientId:patientId,
								departmentId:_departmentId,
								methodFlg: _methodFlg,
								sortType: _sortType,
								pageNum: 0,
								pageSize: 10
							},
							async:false,
							dataType:"json",
							success:function(result){
								var Doclist=result.doctorList;
								if(result.returnCode == "0" && Doclist != null){
									window.location.href="Pat_ResList.html?_departmentId="+_departmentId+"&title="+$(".sbl").children("strong").text()+"&patientId="+patientId;
								}else{
									var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>该科室缺少医生，请谅解</font></div></div>")
									$("body").append(Mess);
									setTimeout(function(){
										$(".Mess").remove();
										return false;
									},1500)
								}
							}
						})
						
						//getDocListDep(_departmentId,_methodFlg,_sortType,_pageNum,_pageSize,AddDoc);
					}
				});
			}
		})
	}
	
	getSymptomType("sym");

})