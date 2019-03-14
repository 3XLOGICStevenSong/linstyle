//角色添加>定义角色功能
var setting = {
	check : {
		enable : true,
		chkDisabledInherit : true
	},
	//树型结构设置
	data : {
		simpleData : {
			enable : true,
			idKey : "id", // 修改默认的ID为自己的id
			pIdKey : "pId", // 修改默认父级ID为自己数据的父级id
			rootPId : 0
		// 根节点的父节点id
		}
	},
};

$(document).ready(function(){
	//获取缓存token
	var token=localStorage.getItem("token"); 
	var urlstorage=localStorage.getItem("url");
	//获取参数
	var roleId=window.location.href.substring(window.location.href.lastIndexOf("=")+1,window.location.href.length);
	var selectList="";
	//初始化角色列表
	$.ajax({
		type:"get",
		url:urlstorage+"permission/allRole",
		dataType: "json", 
		contentType : "application/json",
		success:function(result){
			if (result.statusCode=="200") {
				selectList+="<option value='0'>请选择</option>"
				$.each(result.data, function(index,item){
					selectList+="<option  value="+item.roleId+">"+item.roleName+"</option>"
				});
				$("#selectRole").html("");
				$("#selectRole").append(selectList);
				//下拉列表设置默认选中值
				$("#selectRole option[value='"+roleId+"']").attr("selected", true);
		
			}
			},
	error:function(res) {
	console.error("出错了" + res);
}
		});
	//获取资源树形结构
	$.ajax({
		type:"get",
		url:urlstorage+"permission/resourceTree",
		dataType: "json", 
		contentType : "application/json",
		data:{
			roleId:roleId
		},
		
		success:function(result){
			$.fn.zTree.init($("#treeDemo"), setting, result.data.resourceList);
			}
		});
//选择角色
		$("#selectRole").on("change",function(){
	var roleId=$("#selectRole option:selected").val();
//	 if(roleId==""||roleId==0){
//			$(".error").text("请选择角色");
//			$(".error").css('display','block');
//			return false;
//		 }
	$.ajax({
		type:"get",
		url:urlstorage+"permission/resourceTree",
		dataType: "json", 
		contentType : "application/json",
		 beforeSend: function(request) {
				request.setRequestHeader("Authorization", token);
				},
		data:{
			roleId:roleId
		},
		success:function(result){
			$.fn.zTree.init($("#treeDemo"), setting, result.data.resourceList);
			$(".error").css('display','none');
			}
		});
	})
	//保存按钮
	$(".add").on("click",function(){
		// var zTreeOjb = $.fn.zTree.getZTreeObj("treeDemo"); //ztree的Id zTreeId
		// var checkedNodes = zTreeObj.getCheckedNodes();
		var roleId=$("#selectRole option:selected").val();
		 if(roleId==""||roleId==0){
				$(".error").text("请选择角色");
				$(".error").css('display','block');
				return false;
			 }
		 var zTreeObj=$.fn.zTree.getZTreeObj("treeDemo");
		 //获取选择的checkbox
		var checkedNodes=zTreeObj.getCheckedNodes();
		var ids=new Array();
		var idlist="";
		if(checkedNodes.length>0){
		$.each(checkedNodes, function(index,item){
			if(item.id!=""){ids.push(parseInt(item.id));
		 console.log(ids+"ids1");
				}
			
			　})
			 var idlist=ids.toString();
			//alert(idlist+"idlist")
			 $.ajax({
				type:"post",
				url:urlstorage+"permission/roleResource",
				data:JSON.stringify( {
					roleId:roleId,
					ids:idlist
				}),
				 beforeSend: function(request) {
		 				request.setRequestHeader("Authorization", token);
		 				},
				dataType: "json", 
				contentType : "application/json",
				success:function(result){
					if (result.statusCode=="200") {
						$(".ok").css('display','block'); 
						$(".error").css('display','none');
					}
					}
				});
			 console.log(ids+"ids");
			}
		
		});
		//点击取消按钮
		$(".cancel").on("click",function(){
			 var zTreeObj=$.fn.zTree.getZTreeObj("treeDemo");
			 zTreeObj.checkAllNodes(false);
		})
	
	
});