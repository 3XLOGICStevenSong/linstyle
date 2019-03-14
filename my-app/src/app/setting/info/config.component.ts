// 授予角色
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { RoleInfoService } from './info.service';

import { Role } from '../role';
import { RoleDescription } from '../role';
import { RoleOwner } from '../role';
import { RoleIdInfo } from '../role';
import { UpdateRoleOwnerClient } from '../role';

@Component({
    selector: 'config',
    templateUrl: './config.component.html',
    styleUrls: [
        '../../../assets/css/file.css',
        '../../../assets/css/mystyle.css',
        '../../../assets/css/nav.css'],
    providers: [RoleInfoService]
})

export class RoleConfigComponent implements OnInit {
    // 显示【描述】
    showDes: boolean;
    // 显示【拥有者】
    showOwner: boolean;
    // 当前选中的角色
    curRoleId: number;
    // 搜索的员工号
    searchCardNo: string;
    // 角色列表
    roleList: Array<Role>;
    // 资源描述列表
    desList: Array<RoleDescription>;

    // 资源拥有者列表
    ownerList: Array<RoleOwner>;

    // 初始化RootService
    constructor(private route: ActivatedRoute,
        private router: Router,
        private service: RoleInfoService) { }

    // 页面初始化
    ngOnInit(): void {
        this.showDes = false;
        this.showOwner = false;
        this.curRoleId = Number(this.route.snapshot.paramMap.get('roleId'));

        // 初始化获取所有角色
        this.searchOnClicked();
    }
    // 搜索
    searchOnClicked() {
        console.log("before this.searchCardNo = " + this.searchCardNo);
        if (this.searchCardNo == undefined || this.searchCardNo.length == 0) {
            // 获取所有
            this.service.getAllRole().then(result => {
                if (result.code == 0) {
                    this.roleList = result.sysRoleList;
                } else {
                    alert(result.msg);
                }
            });
        } else {
            // 获取某人
            this.service.getRoleByCardNo(this.searchCardNo).then(result => {
                if (result.code == 0) {
                    this.roleList = result.sysRoleList;
                } else {
                    this.searchCardNo = "";
                    for (var i = 0; i < this.roleList.length; i++) {
                        if (this.roleList[i].checkStatus == 1) {
                        this.roleList[i].checkStatus = 0;
                        }
                    }
                    alert(result.msg);
                }
            });
        }
    }

    // 点击[描述]
    describeOnClicked(roleId: number) {
        this.showOwner = false;
        this.curRoleId = roleId;
        this.service.getDesByRoleId(roleId).then(result => {
            if (result.code == 0) {
                this.desList = result.descriptionList;
                if (this.desList.length == 0) {
                    this.showDes = false;
                    alert("该角色没有关联资源");
                } else {
                    this.showDes = true;
                }
            } else {
                alert(result.msg);
            }
        });
    }
    // 点击[详情]
    ownerOnClicked(roleId: number) {
        this.showDes = false;
        this.curRoleId = roleId;
        this.service.getOwnerByRoleId(roleId).then(result => {
            if (result.code == 0) {
                this.ownerList = result.ownerList;
                if (this.ownerList.length == 0) {
                    this.showOwner = false;
                    alert("没有人拥有该角色");
                } else {
                    this.showOwner = true;
                }

            } else {
                alert(result.msg);
            }
        });
    }

    roleOnClicked(role: Role, event: Event) {
        console.log("role.checkStatus = " + role.checkStatus);
    }

    // 点击[更新角色]
    updateOnClicked() {
        var selectRoleList = new Array();
        for (var i = 0; i < this.roleList.length; i++) {
            if (this.roleList[i].checkStatus == 1) {
                var info = new RoleIdInfo();
                info.roleId = this.roleList[i].roleId.toString();
                selectRoleList.push(info);
            }
        }
        console.log("selectRoleList.length = " + selectRoleList.length);

        var clientInfo = new UpdateRoleOwnerClient();
        clientInfo.cardNo = this.searchCardNo;
        clientInfo.roleList = selectRoleList;

        this.service.updateUserRole(clientInfo).then(result => {
            if (result.code == 0) {
                this.ownerList = result.ownerList;
                alert("更新成功!");
            } else {
                alert(result.msg);
            }
        });
    }

    // 点击【关闭】描述
    closeDesOnClicked(): void {
        this.showDes = false;
    }
    // 点击【关闭】拥有者
    closeOwnerOnClicked(): void {
        this.showOwner = false;
    }
    // 点击【重置】
    resetOnClicked(): void {
        for (var i = 0; i < this.roleList.length; i++) {
            this.roleList[i].checkStatus = 0;
        }
    }
    // 点击【返回】
    returnOnClicked(): void {
        this.router.navigate(['setting/role/info']);
    }
}