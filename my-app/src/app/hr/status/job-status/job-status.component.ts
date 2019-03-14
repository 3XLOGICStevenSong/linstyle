import { Component, ViewChild, OnInit } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Http, Headers } from '@angular/http';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

import { JobStatusService } from './job-status.service';
import { PermissionsService } from '../../../common/permissions.service';
import { Type } from '../employee-register/type';
declare var $: any;


@Component({
  selector: 'employee-status',
  templateUrl: './job-status.component.html',
  providers: [JobStatusService, PermissionsService],

  // providers:[ MenuService ]
})
//员工状态管理
export class JobStatusComponent implements OnInit {

  public typeList?: Array<Type>;

  public typeName: string;

  public type: Type;

  
  @ViewChild('typeForm') typeForm: NgForm;

  constructor(private router: Router, private jobStatusService: JobStatusService) { }


  public ngOnInit(): void {
    this.getTypeInfoList();
  }
  //开始使用
  startUse(type: Type): void {
    type.status = "1";
    this.jobStatusService.updateTypeInfo(type.typeId, "1").then(
      res => {
        if (res.code == '0') {
        } else {
          type.status = "0";
          $.MsgAlert.AlertTure("在职状态信息", res.msg);
        }
      })
  }
  //暂停使用
  stopUse(type: Type): void {
    type.status = "0";
    this.jobStatusService.updateTypeInfo(type.typeId, "0").then(
      res => {
        if (res.code == '0') {
        } else {
          type.status = "1";
          $.MsgAlert.AlertTure("在职状态信息", res.msg);

        }
      })
  }
  //删除信息
  deleteInfo(type: Type): void {

    var that = this;
    $("#myModal").modal("show");
    $(".deleteFlag").click(function () {

      $("#myModal").modal("hide");
      that.jobStatusService.updateTypeInfo(type.typeId, "2").then(
        res => {
          if (res.code == 0) {
            // alert(res.code);
            //从数组中删除当前元素
            // console.log(that.typeList);
            for (var i in that.typeList) {
              if (that.typeList[i].typeId == type.typeId) {
                that.typeList.splice(parseInt(i), 1);
              }
            }
          } else {
            // 请求失败
            $.MsgAlert.AlertTure("在职状态信息", res.msg);
          }
        })
      $(".deleteFlag").unbind();
    })
  }
//删除添加的项目
  deleteAddInfo(index:string): void {
    var that = this;
    $("#myModal").modal("show");
    $(".deleteFlag").click(function () {
      $("#myModal").modal("hide");
      that.typeList.splice(parseInt(index), 1);
      $(".deleteFlag").unbind();

   })
  }
  //保存修改
  saveInfo(typeId: String, typeName: String, index: string): void {
    //console.log("typeId" + typeId);
    // console.log("typeName" + typeName);
    if (typeName != '') {
      //输入框验证
      document.getElementById(index).style.display = "none";
      //请求服务
      this.jobStatusService.saveInfo(typeId, typeName).then(
        res => {
          if (res.code == '0') {
            $.MsgAlert.AlertTure("在职状态信息", "保存成功");
          } else {
            $.MsgAlert.AlertTure("在职状态信息", res.msg);
          }
        })
    } else {
      //输入框验证
      document.getElementById(index).style.display = "block";
    }

  }
  //输入框验证
  changeInput(typeName: string, index: string): void {
    if (typeName != '') {
      document.getElementById(index).style.display = "none";
    } else {
      document.getElementById(index).style.display = "block";
    }

  }
 //添加项目
 addItem(): void {
  const addType = new Type();
  addType.typeId = "";
  addType.status = "1";
 // addType.typeName = '';
  addType.typeCode='';
  this.typeList.push(addType);
}
  //初始化获取在职状态列表
  getTypeInfoList(): void {
    //初始化获取在职状态列表
    this.jobStatusService.getJobStatusInfo("JOB_STATUS").then(
      res => {
        if (res.code == '0') {
          this.typeList = res.typeList;
        } else {
          $.MsgAlert.AlertTure("在职状态信息", res.msg);
        }
      })
  }
  createType(typeName: String , index: string): void {
    var that = this;
   // alert(typeName)
    if (typeName != '') {
      document.getElementById(index).style.display = "none";
      this.jobStatusService.createTypeInfo(typeName).then(
        res => {
          if (res.code == '0') {
            that.typeList[parseInt(index) - 1].typeId = res.typeId;
            that.typeList[parseInt(index) - 1].typeName = typeName;
            $.MsgAlert.AlertTure("在职状态信息", '添加成功');
          } else {
            $.MsgAlert.AlertTure("在职状态信息", res.msg);
          }
        }
      )
    } else {
      document.getElementById(index).style.display = "block";

    }
  }
   
}