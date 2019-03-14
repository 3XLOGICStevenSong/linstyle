//左侧导航
$(function(){
	$(document).on("click",".subNav",function(){
		$(this).toggleClass("currentDd").siblings(".subNav").removeClass("currentDd")
		$(this).toggleClass("currentDt").siblings(".subNav").removeClass("currentDt")			
		// 修改数字控制速度， slideUp(500)控制卷起速度
		$(this).next(".navContent").slideToggle(500).siblings(".navContent").slideUp(500);
})	
})


//省市区三级联动
//$(function(){
//  var arrCity = [];
//  arrCity[1] = [{ t: '沈阳市', id: 1 }, { t: '大连市', id: 2 }];
//  arrCity[2] = [{ t: '南宁市', id: 3 }, { t: '桂林市', id: 4 }];
//  var arrTown = [];
//  arrTown[1] = [{ t: '东陵区', id: 1 }, { t: '浑南区', id: 2 }]
//  arrTown[2] = [{ t: '甘井子区', id: 3 }, { t: '高新园区', id: 4 }]
//  arrTown[3] = [{ t: '青秀区', id: 5 }]
//  arrTown[4] = [{ t: '七星区', id: 6 }, { t: '象山区', id: 7 }]
//  document.getElementById('pro').onchange = function changepro() {
//      addOptions(document.getElementById('city'), arrCity[this.value]);
//      document.getElementById('city').onchange();//同时加载城镇
//  }
//  document.getElementById('city').onchange = function changecity() {
//      addOptions(document.getElementById('town'), arrTown[this.value]);
//  }
//  function addOptions(s, arr, initValue) {
//      if (!arr || arr.length == 0) arr = [{ t: '请选择市', id: '' }];
//      if (!s) { alert('select对象不存在！'); return false }
//      s.options.length = 0;
//      var selectedIndex = 0;
//      for (var i = 0; i < arr.length; i++) {
//          s.options.add(new Option(arr[i].t, arr[i].id));
//          if (arr[i].id == initValue) selectedIndex = i;
//      }
//  }
//})
$(function(){
  function getRowObj(obj) { 
    var i = 0; 
    while(obj.tagName.toLowerCase() != "tr"){ 
      obj = obj.parentNode; 
      if(obj.tagName.toLowerCase() == "table")
        return null; 
      } 
      return obj; 
    } 
      
    //根据得到的行对象得到所在的行数 
    function getRowNo(obj){ 
      var trObj = getRowObj(obj); 
      var trArr = trObj.parentNode.children; 
      for(var trNo= 0; trNo < trArr.length; trNo++){ 
        if(trObj == trObj.parentNode.children[trNo]){ 
          alert(trNo+1); 
        } 
      } 
    } 
      
    //删除行 
    function delRow(obj){ 
    var tr = this.getRowObj(obj); 
      if(tr != null){ 
      tr.parentNode.removeChild(tr); 
      }else{ 
      throw new Error("the given object is not contained by the table"); 
      } 
    } 
})
//弹出拥有角色一览
$(function(){
	$(".memberlist").hide();
	$(".seeBtn").click(function(){
	  $(".memberlist").show();
	});
})
//角色授予>检索
$(function(){
	$(".person-info").hide();
	$(".searchBtn").click(function(){
	  $(".person-info").show();
	});
})
//订单编辑>服务终止
$(function () {
	$("#other").click(function () {
		if($(this).is(':checked')){
		   $("#stoptxt").css("display","block")
	   }else {
		   $("#stoptxt").css("display","none")
		}
	})
})
//服务人员选择，提成发放状态维护>全选
    function checkAll(obj){
        //alert(obj.checked);
        //获取name=box的复选框
        var userids=document.getElementsByName("box");
        //alert(userids.length);
        for(var i=0;i<userids.length;i++){
            userids[i].checked=obj.checked;
        }
    }

    function selectAll(){
        //获取name=box的复选框
        var userids=document.getElementsByName("box");
        var count=0;
        //遍历所有的复选框
        for(var i=0;i<userids.length;i++){
            if(userids[i].checked){
                count++;
            }
        }
        //选中复选框的个数==获取复选框的个数 
        if(count==userids.length){
        //设置id为all复选框选中
            document.getElementById("all").checked=true;
        }else{
        //设置id为all复选框不选中
            document.getElementById("all").checked=false;
        }
        
    }
//服务编辑>上传图片
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

//角色管理，服务流程，档案一览，系统设置,数据模型管理，体检管理，提成比例设置>删除功能
var tr="";
function launchModal(nowTr){
	$("#myModal").modal('show');
	tr=$(nowTr).parents("tr");	
 }
function deleteTr(){
	$("#myModal").modal('hide');
	tr.remove();
 }


//服务人员编辑>增删
var tr="";
function deleteBtn(nowTr){
	$("#myModal").modal('show');
	tr=$(nowTr).parent().parent("tr");	
 }
function deleteTr(){
	$("#myModal").modal('hide');
	tr.remove();
 }
$(function () {
	$(".addBtn").click(function () {
		$("table").append("<tr>"+$("#module").html()+"<td class=' border-none'>"+"<button type='button' class='btn btn-default top0' onClick='deleteBtn(this)'>"+"删除"+"</button>"+"</td>"+"</tr>");
	})
})

//编辑会员>追加删除关联账户
$(document).ready(function(){
	$(document).delegate(".plusBtn","click",function(){
		var num = $(".family .family-notice").length + 1;
		$(".family").append("<div class='form-group family-notice'>"+"<label class='col-md-2 col-md-offset-2 control-label'>"+"关联账户"+"<span>"+"&nbsp;"+"&nbsp;"+num+"</span>"+"</label>"+"<div class='col-md-2'>"+"<input class='form-control' placeholder='请输入姓名'>"+"</div>"+"<div class='col-md-6 col-md-offset-4'>"+"<div class='col-md-10  top10 padding-left-none'>"+"<input class='form-control' placeholder='请输入身份证号码'>"+"</div>"+"<button type='button' class='btn btn-default top10 minusBtn'>"+"删除"+"</button>"+"</div>"+"</div>");		
	});
})

var tr="";
	$(document).delegate(".minusBtn","click",function(){
		$("#myModal").modal('show');		
		tr= $(this).parents(".family-notice");
	})
	$(document).delegate(".delTr","click",function(){
	    tr.remove();  
 	    var num = $(".family .family-notice").length;
		for(i=0;i<=num;i++){
			$(".family-notice").eq(i).find("label").find("span").text(i+1);
			}
			 $("#myModal").modal('hide');
})
//项目编辑追加>选择型
  function change(){
	var n = document.getElementById("question").value;
	if (n==1) {
	  document.getElementById("show").style.display="block";
	  document.getElementById("hidden").style.display="none";
	}else{
	  document.getElementById("show").style.display="none";
	  document.getElementById("hidden").style.display="block";
	}
  }
  
//项目编辑追加>选择>追加删除选项
var rowContent="";
function delBtn(row){
	$("#myModal").modal('show');
	rowContent=$(row).parent(".form-group");	
 }
function delRow(){
	$("#myModal").modal('hide');
	rowContent.remove();
 }
$(function () {
	$(".addRow").click(function () {
		$("form").append("<div class='form-group'>"+$(".result").html()+"<button type='button' class='btn btn-default top0 rowContent' onclick='delBtn(this)'>"+"删除"+"</button>"+"</div>");
	})
})
//组织管理
var tr="";
function orBtn(nowTr){
	$("#myModal").modal('show');
	tr=$(nowTr).parent.parent("li");	
 }
function deleteTr(){
	$("#myModal").modal('hide');
	tr.remove();
 }