import { Component, ViewChild, OnInit } from '@angular/core';
import { RouterModule, Routes, ActivatedRoute } from '@angular/router';
import { Http, Headers } from '@angular/http';

import { Router } from '@angular/router';
import { ActivityDetailService } from './activity-detail.service';
import { PermissionsService } from '../../common/permissions.service';
import { Type } from '../../hr/status/employee-register/type';
import { Activity } from '../activity';
import { ActivityRegister } from '../activity-register';
import { ActivityFile } from '../activity-file';
import { NgForm } from '@angular/forms';
declare var $: any;

@Component({
  selector: 'activity-detail',
  templateUrl: './activity-detail.component.html',

  providers: [ActivityDetailService, PermissionsService]
})
export class ActivityDetailComponent implements OnInit {
  //按钮标识
  public btnFlag: Boolean;
  //权限
  public permission: Set<String>;
  //报名信息
  public activityRegisterList: Array<ActivityRegister>;
  //附件信息
  public activityFileList: Array<ActivityFile>;

  //活动信息
  public activity: Activity = new Activity();

  public activeId: Number;
  public model: Activity = new Activity();

  public addRegister: ActivityRegister = new ActivityRegister();

  @ViewChild('registerForm') registerForm: NgForm;
  constructor(private router: Router, private route: ActivatedRoute, private permissionsService: PermissionsService, private activityDetailService: ActivityDetailService) { }


  public ngOnInit(): void {
    this.permissionsService.getPermissionList().then(
      res => {
        this.permission = res;

        let permissArray = this.permission;

        this.btnFlag = false;
        //  this.passwordFlag = false;
        //遍历Set 查找权限
        for (let temp in permissArray) {
          if (permissArray[temp] == "activitySign:view") {
            this.btnFlag = true;
            break;
          }
        }
      })
    if (this.route.snapshot.paramMap.has('activeId')) {//活动一览列表跳转进入详情页面
      this.activeId = parseInt(this.route.snapshot.paramMap.get('activeId'));
      // console.log("this.activeId" + this.activeId);
      this.activityDetailService.getActivityDetail(this.activeId).then(
        res => {
          if (res.code == '0') {
            this.activityFileList = res.activityFileList;
            this.activityRegisterList = res.activityRegisterList;
            this.activity.activeId = res.activeId;
            this.activity.activeStatus = res.activeStatus;
            this.activity.activeTitle = res.activeTitle;
            this.activity.typeName = res.typeName;
            this.activity.activeContent = res.activeContent;
            this.activity.activeEndTime = res.activeEndTime;
            this.activity.activeStartTime = res.activeStartTime;
            this.activity.signEndTime = res.signEndTime;
            this.activity.signStartTime = res.signStartTime;
            this.activity.signFlag = res.signFlag;
            //console.log(">>>>title" + this.activity.activeTitle);
          } else {
            $.MsgAlert.AlertTure("活动详情", res.msg);
          }
        }
      )
    } else {
      $.MsgAlert.AlertTure("活动详情", "获取信息失败");
    }
  }

  downloadFile(fileUrl:string):void{
    window.open(fileUrl);
  }
  //添加报名人员
  addItem(activityRegisterList: Array<ActivityRegister>): void {
    //  console.log(activityRegister);
    this.addRegister.sex = "0";
    this.addRegister.familyFlag = "1";
    this.addRegister.arId = '';
    // this.addType.status = "1"
    activityRegisterList.push(this.addRegister);
  }
  //删除添加的项目
  deleteAddInfo(activityRegister: ActivityRegister, index: string): void {
    var that = this;
    $("#myModal").modal("show");
    $(".deleteFlag").click(function () {
      // alert("aa")
      $("#myModal").modal("hide");
      $(".deleteFlag").unbind();
      that.activityRegisterList.splice(parseInt(index), 1);
    })

  }
  //删除信息
  deleteInfo(activityRegister: ActivityRegister): void {

    var that = this;
    $("#myModal").modal("show");
    $(".deleteFlag").click(function () {
      // alert("bb")
      $("#myModal").modal("hide");
      $(".deleteFlag").unbind();
      that.activityDetailService.deleteFamilyInfo(activityRegister.arId).then(
        res => {
          if (res.code == 0) {
            //删除当前元素
            for (var i in that.activityRegisterList) {
              if (that.activityRegisterList[i].arId == activityRegister.arId) {
                that.activityRegisterList.splice(parseInt(i), 1);
              }
            }
          } else {
            // 请求失败
            $.MsgAlert.AlertTure("活动详情", res.msg);
          }
        })
    })
    // $(".deleteFlag").unbind();
  }

  //不报名
  unsign(activityRegisterList: Array<ActivityRegister>): void {
    // console.log("signFlag" + this.activity.signFlag);
    // if(this.activity.signFlag=='0'){

    // $.MsgAlert.AlertTure("活动详情", "您未报名");
    // }else{
    //signFlag 为0 不报名
    this.activityDetailService.unsignActivity(activityRegisterList, '0', this.activity.activeId).then(
      res => {
        if (res.code == 0) {
          $.MsgAlert.AlertTure("活动详情", "操作成功");
        } else {
          // 请求失败
          $.MsgAlert.AlertTure("活动详情", res.msg);
        }
      }
    )
  }

  //活动报名
  sign(activityRegisterList: Array<ActivityRegister>): void {
    var checkFlag = '0';

    for (var i = 0; i < activityRegisterList.length; i++) {
      console.log(activityRegisterList[i].name);
      console.log(i);
      // alert(activityRegisterList[i].name);
      //姓名验证
      if (activityRegisterList[i].name == '' || activityRegisterList[i].name == undefined) {
        var nameId = "name" + i;
        document.getElementById(nameId).style.display = "block";
        checkFlag = '1'
        //document.getElementById("name0").style.display = "block";
      } else {
        var nameId = "name" + i;
        document.getElementById(nameId).style.display = "none";
      }
      //联系方式
      if (activityRegisterList[i].tel == '' || activityRegisterList[i].tel == undefined) {
        var telId = "tel" + i;
        document.getElementById(telId).style.display = "block";
        checkFlag = '1'
        //document.getElementById("name0").style.display = "block";
      } else {
        var telId = "tel" + i;
        document.getElementById(telId).style.display = "none";
      }
      //身份证号
      if (activityRegisterList[i].idNum == '' || activityRegisterList[i].idNum == undefined) {
        var idNumId = "idNum" + i;
        document.getElementById(idNumId).style.display = "block";
        checkFlag = '1'
        //document.getElementById("name0").style.display = "block";
      } else {
        var nameId = "idNum" + i;
        document.getElementById(nameId).style.display = "none";
      }
      //身份证号
      if (activityRegisterList[i].familyFlag == '0') {
        if (activityRegisterList[i].address == '' || activityRegisterList[i].address == undefined) {
          var addressId = "address" + i;
          document.getElementById(addressId).style.display = "block";
          checkFlag = '1'
          //document.getElementById("name0").style.display = "block";
        } else {
          var addressId = "address" + i;
          document.getElementById(addressId).style.display = "none";
        }
      }
    }
    if (checkFlag == '0') {
      this.activityDetailService.signActivity(activityRegisterList, this.activity.activeId).then(
        res => {
          if (res.code == 0) {
            $.MsgAlert.AlertTure("活动详情", "报名成功");
          } else {
            // 请求失败
            $.MsgAlert.AlertTure("活动详情", res.msg);
          }
        }
      )
    }
  }

  // dateCompareNow(): boolean {
  //   if (this.activity.signEndTime = '') {
  //     $.MsgAlert.AlertTure("活动详情", '报名结束日期错误');
  //   } else {
  //     var dateString = [];

  //     dateString = this.activity.signEndTime.split("-");
  //     var myDate = new Date();
  //     myDate.setFullYear(parseInt(dateString[0]), parseInt(dateString[1]) - 1, parseInt(dateString[2]));

  //     var today = new Date();

  //     if (myDate > today) {
  //       return true;
  //     }
  //     else {
  //       return true;
  //     }
  //   }
  // }

  // signInfoList(): void {

  // }
}
