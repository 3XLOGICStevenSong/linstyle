$(function(){
	var link=window.location.href;
	var param=link.split("&");
	var userTel="";
	var password="";
	for(i=0;i<param.length;i++){
		if(param[i].indexOf("userTel") != -1 ){
			userTel=param[i].substring(param[i].lastIndexOf("=")+1,param[i].length);
		}else if(param[i].indexOf("password") != -1 ){
			password=decodeURI(param[i].substring(param[i].lastIndexOf("=")+1,param[i].length));
		}
	}

	$(".Seldes").click(function(){
		$.ajax({
			type:"get",
			url:"/MedicalUnicom/DepartmentClass.do?doGetDepartmentList",// /MedicalUnicom/Department.do?doGetDepartmentSymptom
			date: {},
			aync: false,
			dataType: "json",
			success:function(result){
				//result={"departmentTypeList":[{"dcId":1,"dcName":"内科","iconPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Neike@2x.png","cellPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Cell_Neike@2x.png","departmentList":[{"depId":1,"depName":"心血管内科专业"},{"depId":2,"depName":"呼吸内科专业"},{"depId":3,"depName":"消化内科专业"},{"depId":4,"depName":"血液内科专业"},{"depId":5,"depName":"肾病学专业"},{"depId":6,"depName":"内分泌专业"},{"depId":7,"depName":"风湿免疫专业"},{"depId":8,"depName":"神经内科专业"},{"depId":9,"depName":"肿瘤内科专业"},{"depId":10,"depName":"老年病专业"}]},{"dcId":2,"dcName":"外科","iconPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Waike@2x.png","cellPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Cell_Waike@2x.png","departmentList":[{"depId":11,"depName":"普通外科专业"},{"depId":12,"depName":"肝胆外科专业"},{"depId":13,"depName":"神经外科专业"},{"depId":14,"depName":"胸外科专业"},{"depId":15,"depName":"心血管外科专业"},{"depId":16,"depName":"泌尿外科专业"},{"depId":17,"depName":"肾移植专业"},{"depId":18,"depName":"骨外科专业"}]},{"dcId":3,"dcName":"儿科","iconPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Erke@2x.png","cellPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Cell_Erke@2x.png","departmentList":[{"depId":19,"depName":"儿科专业"}]},{"dcId":4,"dcName":"眼科","iconPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Yanke@2x.png","cellPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Cell_Yanke@2x.png","departmentList":[{"depId":20,"depName":"眼科专业"}]},{"dcId":5,"dcName":"耳鼻喉科","iconPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Erbihouke@2x.png","cellPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Cell_Erbihouke@2x.png","departmentList":[{"depId":21,"depName":"耳鼻喉专业"},{"depId":22,"depName":"头颈颌面外科专业"}]},{"dcId":6,"dcName":"妇产科","iconPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Fuchanke@2x.png","cellPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Cell_Fuchanke@2x.png","departmentList":[{"depId":23,"depName":"妇科专业"},{"depId":24,"depName":"产科专业"}]},{"dcId":7,"dcName":"口腔","iconPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Kouqiangke@2x.png","cellPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Cell_Kouqiangke@2x.png","departmentList":[{"depId":25,"depName":"口腔科专业"}]},{"dcId":8,"dcName":"皮肤科","iconPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Pifuke@2x.png","cellPic":"http://192.168.11.120:8080/MedicalUnicom/image_dep/Department_Cell_Pifuke@2x.png","departmentList":[{"depId":26,"depName":"皮肤科专业"}]}],"returnCode":"0"}
				var Category=result.departmentTypeList; //symptomTypeList;
				var CateList="";
				
				for(i=0;i<Category.length;i++){
					var small_Menu="";
					var sub_menu="";
					
					for(t=0;t<Category[i].departmentList.length;t++){
						if(t<3){
							small_Menu+= Category[i].departmentList[t].depName + ", ";
						}else if(t<4){
							small_Menu+= Category[i].departmentList[t].depName;
						}
					}

					for(k=0;k<Category[i].departmentList.length;k++){
						sub_menu+="<li ><label name='"+Category[i].departmentList[k].depId+"'>"+Category[i].departmentList[k].depName+"</label></li>";
					}

					
					CateList+="<li class='tab"+(i+1)+"'><div class='menu'><p class='clearfix' id='"+Category[i].dcId+"'><span class='Ticon'><img src='"+Category[i].iconPic+"'></span><strong>"+Category[i].dcName+"</strong></p><!--p class='small_menu'>"+small_Menu+"</p--><span class='op_icon'></span></div><div class='sub_menu'><ul class='color"+(i+1)+"'>"+sub_menu+"</ul></div></li>";
				}
				
				$("#CateGory").html(CateList);

				$(".menu").click(function(){
					$(".menu .clearfix").removeClass("sbl");
					$(".menu .op_icon").removeClass("minus");
					if($(this).next().css("display") != "block"){
						$(".sub_menu").slideUp("fast")
						$(this).next().slideDown("fast")
						$(this).find(".clearfix").addClass("sbl");
						$(this).find(".op_icon").addClass("minus");
					}else{
						$(this).next().slideUp("fast")
						$(this).find(".clearfix").removeClass("sbl");
						$(this).find(".op_icon").removeClass("minus");
					}
				})

				
				$(".sub_menu li").click(function(){
					
					/*
					if($(this).attr("class") != "li"+$(this).parent().attr("class")+" check"){
						$(this).attr("class","");
						$(this).addClass("li"+$(this).parent().attr("class")+" check");
					}else{
						$(this).attr("class","");
						$(this).attr("class","li");
					}*/
					$("input[name='departmentId']").val($(this).text());
					$("input[name='departmentId']").attr("key",$(this).find("label").attr("name"));
					$("input[name='symptomId']").val($(".sbl strong").text());
					$("input[name='symptomId']").attr("key",$(".sbl").attr("id"));
					$(".SelFm").addClass("displaynone");
					$("#container").removeClass("displaynone");

				})

			}
		});

		$(".SelFm").removeClass("displaynone");
		$(".SelFm").addClass("block");
		$("#container").addClass("displaynone");
	})

	var data=new Date();
	var year="";
	for(i=0;i<=data.getFullYear();i++){
		if(i==data.getFullYear()){
			year+="<option selected='selected'>"+i+"</option>";
		}else{
			year+="<option>"+i+"</option>";
		}
		
	}
	$("#year").html(year)
	
	var month="";
	for(i=1;i<=12;i++){
		if(i==data.getMonth()+1){
			month+="<option selected='selected'>"+i+"</option>";
		}else{
			month+="<option>"+i+"</option>";
		}
		
	}
	$("#month").html(month);
	
	var day="";
	for(i=1;i<=31;i++){
		if(i==data.getDate()){
			day+="<option selected='selected'>"+i+"</option>";
		}else{
			day+="<option>"+i+"</option>";
		}
		
	}
	$("#day").html(day);
	
	$("#grade").on("change",function(){

		$("input[name='positional']").val($("#grade option:selected").text());
		
		var grade_=$("#grade option:selected").val();
		
		$.ajax({
			type:"get",
			url:"/MedicalUnicom/GradePrice.do?doGetGradePriceList",
			data:{},
			aync: false,
			dataType: "json", // json or text or xml
			success:function(result){
				var gradeVa=result.gradePriceList[grade_];

				if(grade_ == -1 ){
					$("input[name='dayTotal']").val("");
					$("input[name='nightTotal']").val("");
					$("input[name='earlyTime']").val("");
					/*
					$("input[name='vaPrice']").val("");
					$("input[name='taPrice']").val("");
					$("input[name='paPrice']").val("");
					$("input[name='effectTime']").val("");
					$("input[name='pvPrice']").val("");
					$("input[name='vCount']").val("");
					$("input[name='ptPrice']").val("");
					$("input[name='tCount']").val("");
					*/
					return false;
				}
				if(grade_ == grade_ ){
					$("input[name='dayTotal']").val(gradeVa.dayTotal);
					$("input[name='nightTotal']").val(gradeVa.nightTotal);
					$("input[name='earlyTime']").val(gradeVa.earlyTime);
					/*
					$("input[name='vaPrice']").val(gradeVa.vaPrice);
					$("input[name='taPrice']").val(gradeVa.taPrice);
					$("input[name='paPrice']").val(gradeVa.paPrice);
					$("input[name='effectTime']").val(gradeVa.effectTime);
					$("input[name='pvPrice']").val(gradeVa.pvPrice);
					$("input[name='vCount']").val(gradeVa.vCount);
					$("input[name='ptPrice']").val(gradeVa.ptPrice);
					$("input[name='tCount']").val(gradeVa.tCount);
					*/
				}
			}
		});
	});
	
	$("#register").on("click", function(){

		var _Sex="";

		if($("#man").attr("key") == "0"){
			var _Sex="0";
		}else if($("#femal").attr("key") == "1"){
			var _Sex="1";
		};

		var age=$("#year").val()+"-"+$("#month").val()+"-"+$("#day").val();
		
		$.ajax({
			type:"post",
			url:"/MedicalUnicom/UserLogin.do?doRegisterDoctor",
			data: {
				userTel: userTel,//电话
				password: password,//密码
				name: $("input[name='name']").val(),//名字
				sex: _Sex,//性别
				age: age,//出生年月
				cardNum: $("input[name='cardNum']").val(),//身份证号
				certificateNum: $("input[name='certificateNum']").val(),//行医证号
				bankOwner: $("input[name='bankOwner']").val(),//持卡人姓名
				bankNum: $("input[name='bankNum']").val(),//卡号
				bankName: $("input[name='bankName']").val(),//银行名字
				hospitalName: $("input[name='hospitalName']").val(),//医院
				healDisease: $("input[name='healDisease']").val(),//主治
				positional: $("input[name='positional']").val(),//职称名称
				grade: $("#grade option:selected").val(),//职称级别
				dayTotal:$("input[name='dayTotal']").val(),
				nightTotal:$("input[name='nightTotal']").val(),
				earlyTime:$("input[name='earlyTime']").val(),
				dcName: $("input[name='symptomId']").val(),//科室
				dcId: $("input[name='symptomId']").attr("Key"),//科室ID
				departmentName: $("input[name='departmentId']").val(),//专业：
				departmentId: $("input[name='departmentId']").attr("Key")//专业ID：
				/*
				doLogin:"",
				certificatePic: $("img[name='certificatePic']").attr("src"),//行医证照片：
				inqueryTotal: $("input[name='vaPrice']").val(),//视频问诊多少元每次
				telTotal: $("input[name='taPrice']").val(),//电话问诊多少元每次
				inqueryNum: $("input[name='vCount']").val(),//私人医生套餐，含多少次视频问诊
				telNum: $("input[name='tCount']").val(),//私人医生套餐，含多少次电话问诊
				privateTotal: $("input[name='paPrice']").val(),//私人医生套餐价格
				effectTime: $("input[name='effectTime']").val(),//私人医生套餐有效期
				*/
			},
			async: false,
			dataType: "json",
			success:function(result){
				if(result.returnCode == "0") {
					var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_complate.png'></span><font color='white'>注册成功</font></div></div>")
					$("#wrap").append(Mess);
					setTimeout(function(){
						location.href="login.html"
					},1500)
						
				}else if(result.returnCode == "-3020"){
					var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>注册失败</font></div></div>")
					$("#wrap").append(Mess);
					setTimeout(function(){
						$(".Mess").remove();
					},1500)

				}else if(result.returnCode == "-3021"){
					var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>账号已存在</font></div></div>")
					$("#wrap").append(Mess);
					setTimeout(function(){
						$(".Mess").remove();
					},1500)
					
				}else if(result.returnCode == "-3022"){
					var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>请输入账号</font></div></div>")
					$("#wrap").append(Mess);
					setTimeout(function(){
						$(".Mess").remove();
					},1500)

				}
			},
			error:function(res) {
				var Mess=$("<div class='Mess'><div class='MessWrap'><div class='bg'></div><span class='imgComplate'><img src='img/icon_cancel.png'></span><font color='white'>请正确输入</font></div></div>")
				$("#wrap").append(Mess);
				setTimeout(function(){
					$(".Mess").remove();
				},1500)
			}
		});
	})
	
	
	
});