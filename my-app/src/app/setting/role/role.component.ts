// 添加角色
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { RoleService } from './role.service';

import { Role } from '../role';
import { Resource } from '../role'
import { ResourceIdInfo } from '../role';
import { UpdateRoleClient } from '../role';
import { AddRoleClient } from '../role';
import { TreeNode } from '../role';
declare function initViews(any, list: Array<TreeNode>);
declare function getSelectNodes();
//declare function getSelectNodes():(list: Array<TreeNode>);
@Component({
    selector: 'role-root',
    templateUrl: './role.component.html',
    styleUrls: [
        '../../../assets/css/file.css',
        '../../../assets/css/mystyle.css',
        '../../../assets/css/nav.css'],
    providers: [RoleService]
})

export class RoleComponent implements OnInit {
    // 标题
    title: string;
    // 当前的角色id
    curRoleId: number;
    // 当前的角色名称
    roleName: string;
    // 资源列表
    resourceList: Array<Resource>;
    // 树节点
    treeNodeList: Array<TreeNode>;
    // 选中的资源id列表
    selectedResourceList: Array<number>;

    // 初始化RootService
    constructor(private route: ActivatedRoute,
        private router: Router,
        private roleService: RoleService) { }

    // 页面初始化
    ngOnInit(): void {
        if (this.route.snapshot.paramMap.has('roleId')) {
            this.curRoleId = Number(this.route.snapshot.paramMap.get('roleId'));
            this.title = "角色修改";
        } else {
            this.curRoleId = -1;
            this.title = "角色添加";
        }
        console.log("this.curRoleId = ", this.curRoleId);

        //this.initTestData();
        this.getResourceList();
    }

    // 获取资源列表
    getResourceList(): void {
        // 获取所有
        this.roleService.getAllResource(this.curRoleId).then(result => {
            if (result.code == 0) {
                this.roleName = result.roleName;
                console.log("this.roleName = " + this.roleName );
                this.resourceList = result.sysResourceList;

                //console.log("this.resourceList = " + this.resourceList);

                // 初始化树形结构
                this.treeNodeList = new Array;
                for (var i = 0; i < this.resourceList.length; i++) {
                    // 第一层
                    var firstRes = this.resourceList[i];
                    var firstNode = new TreeNode;
                    firstNode.id = (i + 1) * 10000;
                    firstNode.pId = 0;
                    firstNode.name = firstRes.resourceName;
                    firstNode.resourceId = firstRes.resourceId;
                    firstNode.isAction = false;
                    this.treeNodeList.push(firstNode);
                    for (var j = 0; j < firstRes.sysResourceList.length; j++) {
                        // 第二层
                        var secondRes = firstRes.sysResourceList[j];
                        var secondNode = new TreeNode;
                        secondNode.id = (i + 1) * 10000 + (j + 1) * 100;
                        secondNode.pId = firstNode.id;
                        secondNode.name = secondRes.resourceName;
                        secondNode.resourceId = secondRes.resourceId;
                        secondNode.isAction = false;
                        this.treeNodeList.push(secondNode);

                        for (var n = 0; n < secondRes.sysResourceList.length; n++) {
                            // 第三层
                            var lastRes = secondRes.sysResourceList[n];
                            var lastNode = new TreeNode;
                            lastNode.id = (i + 1) * 10000 + (j + 1) * 100 + n + 1;
                            lastNode.pId = secondNode.id;
                            lastNode.name = lastRes.resourceName;
                            lastNode.resourceId = lastRes.resourceId;
                            lastNode.checked = (lastRes.checkStatus == 1);
                            lastNode.isAction = true;
                            this.treeNodeList.push(lastNode);
                            if (lastNode.checked) {
                                secondNode.open = true;
                                firstNode.open = true;
                            }
                        }
                    }
                }

                // 初始化树结构
                var setting = {
                    check: {
                        enable: true,
                        chkDisabledInherit: true,
                        //chkboxType: { "Y": "ps", "N": "ps" }
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    }
                };
                initViews(setting, this.treeNodeList);
            } else {
            }
        });
    }

    // 点击【添加】
    addOnClicked(): void {
        var selectNodes: Array<TreeNode> = getSelectNodes();
        var client = new AddRoleClient;
        client.roleName = this.roleName;
        client.sysResourceList = new Array;
        for (var i = 0; i < selectNodes.length; i++) {
            var node = selectNodes[i];
            if (node.checked && node.isAction) {
                var info = new ResourceIdInfo;
                info.resourceId = node.resourceId;
                client.sysResourceList.push(info);
                console.log("select info.resourceId = " + info.resourceId);
            }
        }
        if (client.sysResourceList.length == 0) {
            alert("请至少选择一项");
        } else {
            this.roleService.addRole(client).then(result => {
                if (result.code == 0) {
                    alert("添加角色成功");
                } else {
                    alert(result.msg);
                }
            });
        }
    }

    // 点击【修改】
    updateOnClicked(): void {
        var selectNodes: Array<TreeNode> = getSelectNodes();
        var client = new UpdateRoleClient;
        client.roleId = this.curRoleId;
        client.roleName = this.roleName;
        client.sysResourceList = new Array;
        for (var i = 0; i < selectNodes.length; i++) {
            var node = selectNodes[i];
            if (node.checked && node.isAction) {
                var info = new ResourceIdInfo;
                info.resourceId = node.resourceId;
                client.sysResourceList.push(info);
                console.log("select info.resourceId = " + info.resourceId);
            }
        }
        if (client.sysResourceList.length == 0) {
            alert("请至少选择一项");
        } else {
            this.roleService.updateRole(client).then(result => {
                if (result.code == 0) {
                    alert("修改角色成功");
                } else {
                    alert(result.msg + "code = " + result.code);
                }
            });
        }
    }

    // 点击【取消】
    cancelOnClicked(): void {
        this.router.navigate(['setting/role/info']);
    }

    initTestData(): void {
        var setting = {
            check: {
                enable: true,
                chkDisabledInherit: true,
                //chkboxType: { "Y": "ps", "N": "ps" }
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };
        // 初始化树形结构
        var firstCount = 3;
        var secondCount = 4;
        var lastCount = 5;
        this.treeNodeList = new Array;
        for (var i = 0; i < firstCount; i++) {
            // 第一层
            var firstNode = new TreeNode;
            firstNode.id = (i + 1) * 10000;
            firstNode.pId = 0;
            firstNode.name = "一级菜单" + firstNode.id;
            firstNode.resourceId = firstNode.id;
            firstNode.isAction = false;
            this.treeNodeList.push(firstNode);
            for (var j = 0; j < secondCount; j++) {
                // 第二层
                var secondNode = new TreeNode;
                secondNode.id = (i + 1) * 10000 + (j + 1) * 100;
                secondNode.pId = firstNode.id;
                secondNode.name = "二级菜单" + secondNode.id;
                secondNode.resourceId = secondNode.id;
                secondNode.isAction = false;
                this.treeNodeList.push(secondNode);

                for (var n = 0; n < lastCount; n++) {
                    // 第三层
                    var lastNode = new TreeNode;
                    lastNode.id = (i + 1) * 10000 + (j + 1) * 100 + n + 1;
                    lastNode.pId = secondNode.id;
                    lastNode.name = "三级菜单" + lastNode.id;
                    lastNode.resourceId = lastNode.id;
                    lastNode.checked = false;
                    lastNode.isAction = true;
                    this.treeNodeList.push(lastNode);
                }
            }
        }

        console.log("initViews");
        // 初始化树结构
        initViews(setting, this.treeNodeList);
    }
}


