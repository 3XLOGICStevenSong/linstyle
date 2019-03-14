// 角色一览
// import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RoleInfoService } from './info.service';
import { Role } from '../role';
import { RoleDescription } from '../role';
import { RoleOwner } from '../role'
import { Router } from '@angular/router';

@Component({
    selector: 'roleInfo-root',
    templateUrl: './info.component.html',
    styleUrls: [
        '../../../assets/css/file.css',
        '../../../assets/css/mystyle.css',
        '../../../assets/css/nav.css'],
    providers: [RoleInfoService]
})

export class RoleInfoComponent implements OnInit {
    // 显示【描述】
    showDes: boolean;
    // 显示【拥有者】
    showOwner: boolean;
    // 当前选中的角色
    curRoleId: number;
    // 角色列表
    roleList: Array<Role>;
    // 资源描述列表
    desList: Array<RoleDescription>;

    // 资源拥有者列表
    ownerList: Array<RoleOwner>;

    // 初始化RootService
    constructor(private service: RoleInfoService, private router: Router) { }

    // 页面初始化
    ngOnInit(): void {
        this.showDes = false;
        this.showOwner = false;

        // 获取所有
        this.loadRoleList();
    }

    loadRoleList(): void {
         // 获取所有
        this.service.getAllRole().then(result => {
            if (result.code == 0) {
                console.log("getAllRole success");
                this.roleList = result.sysRoleList;
            } else {
                alert(result.msg);
            }
        });
    }

    // 点击【角色授予】
    roleOnClicked(): void {
        console.log("roleOnClicked");
        this.router.navigate(['setting/role/config']);
    }

      // 点击[描述]
    describeOnClicked(roleId: number) {
        this.showOwner = false;
        this.showDes = false;
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
    // 点击[查看]
    ownerOnClicked(roleId: number) {
        this.showDes = false;
        this.showOwner = false;
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

    // 点击[修改]
    changeOnClicked(roleId: number): void {
        console.log("changeOnClicked roleId = " + roleId);
        var url = 'setting/role/'+ roleId;
        this.router.navigate([url]);
    }

    // 点击[删除]
    deleteOnClicked(roleId: number): void {
        console.log("deleteOnClicked" + roleId);
        if (roleId != 1) {
            this.curRoleId = roleId;
            this.service.deleteRoleByRoleId(roleId).then(result => {
                if (result.code == 0) {
                    // 获取所有
                    this.loadRoleList();
                } else {
                    alert(result.msg);
                }
            });
        } else {
            alert("roleId无效");
        }
    }
    // 点击【关闭】描述
    closeDesOnClicked(): void {
        this.showDes = false;
    }
    // 点击【关闭】拥有者
    closeOwnerOnClicked(): void {
        this.showOwner = false;
    }
}
