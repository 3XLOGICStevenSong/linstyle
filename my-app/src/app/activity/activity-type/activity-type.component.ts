import { Component, ViewChild, OnInit } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Http, Headers } from '@angular/http';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

import { ActivityTypeService } from './activity-type.service';

import { Type } from '../../hr/status/employee-register/type';
declare var $: any;


@Component({
  selector: 'activity-type',
  templateUrl: './activity-type.component.html',
  providers: [ActivityTypeService],

  // providers:[ MenuService ]
})
//活动类型设置
export class ActivityTypeComponent implements OnInit {

  public typeList?: Array<Type>;

  public typeName: string;

  public type: Type;

  // public addType: Type = new Type();
  addFlag: Boolean;
  @ViewChild('activityForms') activityForms: NgForm;

  constructor(private router: Router, private activityTypeService: ActivityTypeService) {

    this.addFlag = true;
  }
  public ngOnInit(): void {
    this.getTypeInfoList();
  }

  //删除信息
  deleteInfo(type: Type): void {
    var that = this;
    $("#myModal").modal("show");
    $(".deleteFlag").click(function () {
      $("#myModal").modal("hide");
      that.activityTypeService.updateTypeInfo(type.typeId, "2").then(
        res => {
          if (res.code == 0) {
            for (var i in that.typeList) {
              if (that.typeList[i].typeId == type.typeId) {
                that.typeList.splice(parseInt(i), 1);
              }
            }
          } else {
            // 请求失败
            $.MsgAlert.AlertTure("活动类型信息", res.msg);
          }
        })
      $(".deleteFlag").unbind();
    })

  }
  //保存修改
  saveInfo(typeId: String, typeName: String, index: string): void {
    var that = this;
    if (typeName != '') {
      //输入框验证
      document.getElementById(index).style.display = "none";
      //请求服务
      this.activityTypeService.saveInfo(typeId, typeName).then(
        res => {
          if (res.code == '0') {
            var i = parseInt(index);
            $.MsgAlert.AlertTure("活动类型信息", "保存成功");
          } else {
            $.MsgAlert.AlertTure("活动类型信息", res.msg);
          }
        })
    } else {
      //输入框验证
      document.getElementById(index).style.display = "block";
    }

  }
  //删除添加的项目
  deleteAddInfo( index: string, ): void {
    var that = this;
    $("#myModal").modal("show");
    $(".deleteFlag").click(function () {
      $("#myModal").modal("hide");
      // console.log(that.typeList);
      that.typeList.splice(parseInt(index), 1);
      $(".deleteFlag").unbind();

    })
  }
  //添加项目
  addItem(): void {
    const addType = new Type();
    addType.typeId = "";
    addType.status = "1";
    addType.typeName = '';
    this.typeList.push(addType);

  }
  //输入框验证
  changeInput(typeName: string, index: string): void {
    var that = this;
    if (typeName != '') {
      document.getElementById(index).style.display = "none";

    } else {
      document.getElementById(index).style.display = "block";
    }

  }

  //初始化获取活动类型列表
  getTypeInfoList(): void {
    //     //初始化活动类型列表
    this.activityTypeService.getTypeListInfo("ACTIVE_TYPE").then(
      res => {
        if (res.code == '0') {
          this.typeList = res.typeList;
        } else {
          $.MsgAlert.AlertTure("活动类型信息", res.msg);
        }
      })
  }
  createType(typeName: String, index: string): void {
    // console.log("typeName" + typeName);
    var that = this;
    if (typeName != '') {
      document.getElementById(index).style.display = "none";
      that.activityTypeService.createTypeInfo(typeName).then(
        res => {
          if (res.code == '0') {
            alert(res.typeId)
            that.typeList[parseInt(index) - 1].typeId = res.typeId;
            //alert(that.typeList[parseInt(index) - 1].typeId+"index"+);
            console.log(that.typeList)
            $.MsgAlert.AlertTure("活动类型信息", "保存成功");
          } else {
            $.MsgAlert.AlertTure("活动类型信息", res.msg);
          }
        }
      )
    } else {
      document.getElementById(index).style.display = "block";

    }
  }
}