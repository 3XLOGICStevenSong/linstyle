//角色添加>定义角色功能
var setting = {
	check: {
		enable: true,
		chkDisabledInherit: true
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};

var zNodes =[
	{ id:1, pId:0, name:"人事管理系统", open:true, checked:true},
	{ id:11, pId:1, name:"员工信息一览", open:true,checked:true},
	{ id:111, pId:11, name:"员工信息添加功能",checked:true},
	{ id:112, pId:11, name:"员工信息删除功能"},
	{ id:113, pId:11, name:"员工信息查看功能"},
	{ id:12, pId:1, name:"面试管理"},
	{ id:13, pId:1, name:"招聘管理"},
	{ id:2, pId:0, name:"日报管理系统"},
	{ id:21, pId:2, name:"日报查询"},
	{ id:22, pId:2, name:"日报添加" },
	{ id:22, pId:2, name:"日报修改"},
	{ id:3, pId:0, name:"权限管理系统"},
];

function disabledNode(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	disabled = e.data.disabled,
	nodes = zTree.getSelectedNodes(),
	inheritParent = false, inheritChildren = false;
	if (nodes.length == 0) {
		alert("请先选择一个节点");
	}
	if (disabled) {
		inheritParent = $("#py").attr("checked");
		inheritChildren = $("#sy").attr("checked");
	} else {
		inheritParent = $("#pn").attr("checked");
		inheritChildren = $("#sn").attr("checked");
	}

	for (var i=0, l=nodes.length; i<l; i++) {
		zTree.setChkDisabled(nodes[i], disabled, inheritParent, inheritChildren);
	}
}

$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	$("#disabledTrue").bind("click", {disabled: true}, disabledNode);
	$("#disabledFalse").bind("click", {disabled: false}, disabledNode);
});