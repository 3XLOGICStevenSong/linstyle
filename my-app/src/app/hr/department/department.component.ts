import { Component, ViewChild, OnInit } from '@angular/core';
import { RouterModule, Routes, ActivatedRoute } from '@angular/router';
import { Http, Headers } from '@angular/http';

import { Router } from '@angular/router';
import { DepartmentService } from './department.service';
import { PermissionsService } from '../../common/permissions.service';
import { EmployeeInfoService } from '../../common/employee-info.service';
import { Department } from './department';
import { DepEmployee } from './department-employee';
import { EmployeeInfo } from '../../common/employee-info';

// import { ActivityFile } from '../activity-file';
import { NgForm } from '@angular/forms';
declare var $: any;

@Component({
  selector: 'department',
  templateUrl: './department.component.html',

  providers: [DepartmentService, PermissionsService, EmployeeInfoService]
})
export class DepartmentComponent implements OnInit {

  //权限
  public permission: Set<String>;
  //按钮显示
  public btnFlag: Boolean;
  //部门员工列表
  public employeeList: Array<DepEmployee>;
  //部门列表
  public departList: Array<Department>;

  //检索题目
  public searchName: String;

  // public activeId: Number;
  public model: Department = new Department();

  //部门员工列表
  public employeeAllList: Array<EmployeeInfo>;


  public cardNo: String;

  public name: String;

  //public addRegister: ActivityRegister = new ActivityRegister();

  //@ViewChild('registerForm') registerForm: NgForm;
  constructor(private router: Router, private permissionsService: PermissionsService, private departmentService: DepartmentService, private employeeInfoService: EmployeeInfoService) { }

  public ngOnInit(): void {
    this.permissionsService.getPermissionList().then(
      res => {
        this.permission = res;

        let permissArray = this.permission;

        this.btnFlag = false;
        //  this.passwordFlag = false;
        //遍历Set 查找权限
        for (let temp in permissArray) {
          if (permissArray[temp] == "department:update") {
            this.btnFlag = true;
            break;
          }
        }
      })
    //初始化部门信息
    this.departmentService.getDepartmentInitInfo().then(
      res => {
        if (res.code == '0') {
          this.employeeList = res.employeeList;
          this.departList = res.departList;
          this.model.depId = this.departList[0].depId;
          this.model.managerCode = this.departList[0].managerCode;
          this.model.managerName = this.departList[0].managerName;
          this.model.sortBy = '0';
          console.log(">>>>managerNames" + this.model.managerName);
        } else {
          $.MsgAlert.AlertTure("部门信息管理", res.msg);
        }
      }
    )
  }
  //检索部门信息
  searchDepartmentInfo(): void {
    this.departmentService.searchDepartmentInfo(this.model.depId, this.model.sortBy).then(
      res => {
        if (res.code == '0') {
          this.employeeList = res.employeeList;
          this.model.managerCode = res.managerCode;
          this.model.managerName = res.managerName;

          console.log(">>>>managerNames" + this.model.managerName);
        } else {
          $.MsgAlert.AlertTure("部门信息管理", res.msg);
        }
      }
    )
  }
  //按照职级升降序
  sortBy(sortBy: String): void {
    this.model.sortBy = sortBy;
    this.departmentService.searchDepartmentInfo(this.model.depId, this.model.sortBy).then(
      res => {
        if (res.code == '0') {
          this.employeeList = res.employeeList;
          //this.model.managerCode = res.managerCode;
          //this.model.managerName = res.managerName;

          console.log(">>>>managerNames" + this.model.managerName);
        } else {
          $.MsgAlert.AlertTure("部门信息管理", res.msg);
        }
      }
    )
  }
  //点击编辑按钮
  editManager(): void {

    this.employeeInfoService.getEmployeeInfoList('').then(
      result => {
        if (result.code == '0') {
          $("#depModal").modal('show');
          this.employeeAllList = result.employeeList;
          //  console.log(">>>>managerNames" + this.model.managerName);
        } else {
          $.MsgAlert.AlertTure("部门信息管理", result.msg);
        }
      }
    )
  }
  //搜索员工信息
  searchEmployee(searchName: String): void {
    this.employeeInfoService.getEmployeeInfoList(searchName).then(
      result => {
        if (result.code == '0') {
          $("#depModal").modal('show');
          this.employeeAllList = result.employeeList;
          //  console.log(">>>>managerNames" + this.model.managerName);
        } else {
          $.MsgAlert.AlertTure("部门信息管理", result.msg);
        }
      }
    )
  }
  selectInfo(employeeAll: EmployeeInfo): void {
    // alert(this.cardNo);
    this.cardNo = employeeAll.cardNo;
    this.name = employeeAll.name;
  }
  //提交按钮
  submitInfo(): void {
    if (this.cardNo == '' || this.cardNo == undefined) {
      document.getElementById("comPass").style.display = "block";
    } else {
      document.getElementById("comPass").style.display = "none";
      this.departmentService.updateDepartmentInfo(this.cardNo, this.model.depId, this.name).then(
        result => {
          if (result.code == '0') {
            $("#depModal").modal('hide');
            this.model.managerName = this.name;
            $.MsgAlert.AlertTure("部门信息管理", "更新部长信息成功");
          } else {
            $.MsgAlert.AlertTure("部门信息管理", result.msg);
          }
        }
      )
    }
  }
  //     if (this.route.snapshot.paramMap.has('activeId')) {//活动一览列表跳转进入详情页面
  //       this.activeId = parseInt(this.route.snapshot.paramMap.get('activeId'));
  //      // console.log("this.activeId" + this.activeId);
  //       this.activityDetailService.getActivityDetail(this.activeId).then(
  //         res => {
  //           if (res.code == '0') {
  //             this.activityFileList = res.activityFileList;
  //             this.activityRegisterList = res.activityRegisterList;
  //             this.activity.activeId = res.activeId;
  //             this.activity.activeStatus = res.activeStatus;
  //             this.activity.activeTitle = res.activeTitle;
  //             this.activity.typeName = res.typeName;
  //             this.activity.activeContent = res.activeContent;
  //             this.activity.activeEndTime = res.activeEndTime;
  //             this.activity.activeStartTime = res.activeStartTime;
  //             this.activity.signEndTime = res.signEndTime;
  //             this.activity.signStartTime = res.signStartTime;
  //             this.activity.signFlag = res.signFlag;
  //             //console.log(">>>>title" + this.activity.activeTitle);
  //           } else {
  //             $.MsgAlert.AlertTure("活动详情", res.msg);
  //           }
  //         }
  //       )
  //     } else {
  //       $.MsgAlert.AlertTure("活动详情", "获取信息失败");
  //     }
  //   }
  //   //添加报名人员
  //   addItem(activityRegisterList: Array<ActivityRegister>): void {
  //     //  console.log(activityRegister);
  //     this.addRegister.sex = "0";
  //     this.addRegister.familyFlag = "1";
  //     this.addRegister.arId = '';
  //     // this.addType.status = "1"
  //     activityRegisterList.push(this.addRegister);
  //   }
  //   //删除添加的项目
  //   deleteAddInfo(activityRegister: ActivityRegister, index: string): void {
  //     var that = this;
  //     $("#myModal").modal("show");
  //     $(".deleteFlag").click(function () {
  //       // alert("aa")
  //       $("#myModal").modal("hide");
  //       $(".deleteFlag").unbind();
  //       that.activityRegisterList.splice(parseInt(index), 1);
  //     })

  //   }
  //   //删除信息
  //   deleteInfo(activityRegister: ActivityRegister): void {

  //     var that = this;
  //     $("#myModal").modal("show");
  //     $(".deleteFlag").click(function () {
  //       // alert("bb")
  //       $("#myModal").modal("hide");
  //       $(".deleteFlag").unbind();
  //       that.activityDetailService.deleteFamilyInfo(activityRegister.arId).then(
  //         res => {
  //           if (res.code == 0) {
  //             //删除当前元素
  //             for (var i in that.activityRegisterList) {
  //               if (that.activityRegisterList[i].arId == activityRegister.arId) {
  //                 that.activityRegisterList.splice(parseInt(i), 1);
  //               }
  //             }
  //           } else {
  //             // 请求失败
  //             $.MsgAlert.AlertTure("活动详情", res.msg);
  //           }
  //         })
  //     })
  //     // $(".deleteFlag").unbind();
  //   }

  //   //不报名
  //   unsign(activityRegisterList: Array<ActivityRegister>): void {
  //    // console.log("signFlag" + this.activity.signFlag);
  //     // if(this.activity.signFlag=='0'){

  //     // $.MsgAlert.AlertTure("活动详情", "您未报名");
  //     // }else{
  //     //signFlag 为0 不报名
  //     this.activityDetailService.unsignActivity(activityRegisterList,'0',this.activity.activeId ).then(
  //       res => {
  //         if (res.code == 0) {
  //           $.MsgAlert.AlertTure("活动详情", "操作成功");
  //         } else {
  //           // 请求失败
  //           $.MsgAlert.AlertTure("活动详情", res.msg);
  //         }
  //       }
  //     )
  //     // }
  //     // }
  //   }

  //   //活动报名
  //   sign(activityRegisterList: Array<ActivityRegister>): void {
  //     //  if (!this.registerForm.valid && this.registerForm.controls) {
  //     //   for (const name in this.registerForm.controls) {
  //     //     if (this.registerForm.controls.hasOwnProperty(name)) {
  //     //       this.registerForm.controls[name].markAsDirty();
  //     //       // alert(">>>>");
  //     //     }
  //     //   }
  //     //  }
  // // for(var i=0; i<activityRegisterList.length;i++){
  // //   console.log(activityRegisterList[i].name);
  // //     console.log(i);
  // //  // alert(activityRegisterList[i].name);
  // //   if(activityRegisterList[i].name==''||activityRegisterList[i].name==undefined){
  // //    var nameId="name"+i;
  // //     document.getElementById(nameId).style.display = "block";
  // //     //document.getElementById("name0").style.display = "block";
  // //   }else{
  // //     var nameId="name"+i;
  // //    document.getElementById(nameId).style.display = "none";
  // //  }
  // // }
  // // for{}
  //         this.activityDetailService.signActivity(activityRegisterList, this.activity.activeId).then(
  //       res => {
  //         if (res.code == 0) {
  //           $.MsgAlert.AlertTure("活动详情", "操作成功");
  //         } else {
  //           // 请求失败
  //           $.MsgAlert.AlertTure("活动详情", res.msg);
  //         }
  //       }
  //     )


  //   }



  //   dateCompareNow(): boolean {
  //     if (this.activity.signEndTime = '') {
  //       $.MsgAlert.AlertTure("活动详情", '报名结束日期错误');
  //     } else {
  //       var dateString = [];

  //       dateString = this.activity.signEndTime.split("-");
  //       var myDate = new Date();
  //       myDate.setFullYear(parseInt(dateString[0]), parseInt(dateString[1]) - 1, parseInt(dateString[2]));

  //       var today = new Date();

  //       if (myDate > today) {
  //         return true;
  //       }
  //       else {
  //         return true;
  //       }
  //     }
  //   }

  // signInfoList():void{

  // }
}
