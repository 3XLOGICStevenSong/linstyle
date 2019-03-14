import { Component, OnInit } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Http, Headers } from '@angular/http';

import { Router } from '@angular/router';
import { ActivityService } from './activity.service';
import { PermissionsService } from '../../common/permissions.service';
import { Type } from '../../hr/status/employee-register/type';
import { Activity } from '../activity';
import { BsDatepickerConfig, BsLocaleService } from 'ngx-bootstrap/datepicker';
import { listLocales } from 'ngx-bootstrap/bs-moment';
import { defineLocale } from 'ngx-bootstrap/bs-moment';
import { zhCn } from 'ngx-bootstrap/locale';
declare var $: any;

@Component({
  selector: 'activityList',
  templateUrl: './activity.component.html',

  providers: [ActivityService, PermissionsService, BsLocaleService]
})
export class ActivityComponent implements OnInit {

  public btnFlag: Boolean;

  public permission: Set<String>;

  public startDate: Date;// 开始日期

  public endDate: Date;// 结束日期

  public startDateParam: String;// 开始日期

  public endDateParam: String;// 结束日期

  public count: number;//总页数

  public pageList: number[];

  public pageNum: number;//页码数

  public selectNum: number;//当前选中的页码

  //活动类型列表
  public activityTypeList?: Array<Type>;
  //活动列表
  public activityList?: Array<Activity>;
  //日历相关
  locale = 'zh-cn';
  colorTheme = 'theme-dark-blue';
  format: 'yyyy-mm-dd'
  bsConfig: Partial<BsDatepickerConfig>;
  //日历相关结束

  public model: Activity = new Activity();
  constructor(private router: Router, private activityService: ActivityService, private permissionsService: PermissionsService, private _localeService: BsLocaleService) {
    //设置日历信息
    defineLocale('zh-cn', zhCn);
    this._localeService.use(this.locale);
    this.bsConfig = Object.assign({ containerClass: this.colorTheme }, { locale: this.locale }, { format: this.format });
    //设置日历信息结束

  }

  public ngOnInit(): void {
    //权限信息
    this.permissionsService.getPermissionList().then(
      res => {
        this.permission = res;

        let permissArray = this.permission;

        this.btnFlag = false;
        //  this.passwordFlag = false;
        //遍历Set 查找权限
        for (let temp in permissArray) {

          if (permissArray[temp] == "activity:create") {
            this.btnFlag = true;
            break;
          }
        }
      })
    //初始化活动一览
    this.activityService.getActivityInitList().then(
      res => {
        if (res.code == '0') {
          this.activityTypeList = res.activityTypeList;
          this.activityList = res.activityList;
          this.count = res.count;
          this.endDate = new Date();
          this.startDate = new Date();
          this.startDate.setDate(this.startDate.getDate() - 7);
          this.pageList = [];
          for (var i = 1; i <= this.count; i++) {
            this.pageList.push(i);
          }
          this.selectNum = 1;
          //console.log(this.pageList);
        } else {
          $.MsgAlert.AlertTure("活动一览", res.msg);
        }
      })
  }
  //检索按钮
  searchActivity(model): void {

    var paramTypeCode = '';
    var paramActiveStatus = '';
    if (model.typeCode != undefined) {
      paramTypeCode = model.typeCode;
    }
    if (model.activeStatus != undefined) {
      paramActiveStatus = model.activeStatus;
    }
    this.startDateParam = this.dateFormat(this.startDate);
    //  alert(this.startDateParam);
    this.endDateParam = this.dateFormat(this.endDate);
    //  alert(this.endDateParam);
    this.activityService.getActivitySearchList(this.startDateParam, this.endDateParam, 0, paramActiveStatus, paramTypeCode).then(
      res => {
        if (res.code == '0') {
          this.activityTypeList = res.activityTypeList;
          this.activityList = res.activityList;
          this.count = res.count;
          // this.endDate = res.endDate;
          // this.startDate = res.startDate;
          this.pageList = [];
          for (var i = 1; i <= this.count; i++) {
            this.pageList.push(i);
          }
          // console.log(this.pageList);
        } else {
          $.MsgAlert.AlertTure("活动一览", res.msg);
        }
      })
  }
  dateFormat(dateParam: Date): any {

    var ayear = dateParam.getFullYear();
    var amonth = (dateParam.getMonth() + 1) < 10 ? "0" + (dateParam.getMonth() + 1) : (dateParam.getMonth() + 1);
    var aday = dateParam.getDate() < 10 ? "0" + dateParam.getDate() : dateParam.getDate()
    return ayear + "-" + amonth + "-" + aday;
  }
  prePage(model): void {
    if ((this.selectNum - 1) <= 1) {
      this.selectNum = 1;
    } else {
      this.selectNum = this.selectNum - 1;
    }
    var paramTypeCode = '';
    var paramActiveStatus = '';
    if (model.typeCode != undefined) {
      paramTypeCode = model.typeCode;
    }
    if (model.activeStatus != undefined) {
      paramActiveStatus = model.activeStatus;
    }
    var pageSelect = 0;
    pageSelect = (this.selectNum - 1) * 10;
    this.startDateParam = this.dateFormat(this.startDate);
    this.endDateParam = this.dateFormat(this.endDate);
    this.activityService.getActivitySearchList(this.startDateParam, this.endDateParam, pageSelect, paramActiveStatus, paramTypeCode).then(
      res => {
        if (res.code == '0') {
          this.activityTypeList = res.activityTypeList;
          this.activityList = res.activityList;
          this.count = res.count;
          //alert(this.count );
          // this.endDate = res.endDate;
          // this.startDate = res.startDate;
          this.pageList = [];
          for (var i = 1; i <= this.count; i++) {
            this.pageList.push(i);
          }
          // console.log(this.pageList);
        } else {
          $.MsgAlert.AlertTure("活动一览", res.msg);
        }
      })
  }
 nextPage(model): void {
   //alert("nextpage"+this.selectNum);
   
    if ((this.selectNum + 1) >=this.count) {
      this.selectNum = this.count;
     // alert(this.count+"count");
    } else {
      this.selectNum = this.selectNum + 1;}
   //alert(this.selectNum+"selectNum")
    var paramTypeCode = '';
    var paramActiveStatus = '';
    if (model.typeCode != undefined) {
      paramTypeCode = model.typeCode;
    }
    if (model.activeStatus != undefined) {
      paramActiveStatus = model.activeStatus;
    }
    var pageSelect = 0;
    pageSelect = (this.selectNum - 1) * 10;
   // alert(">>>>>>"+pageSelect);
    this.startDateParam = this.dateFormat(this.startDate);
    this.endDateParam = this.dateFormat(this.endDate);
    this.activityService.getActivitySearchList(this.startDateParam, this.endDateParam, pageSelect, paramActiveStatus, paramTypeCode).then(
      res => {
        if (res.code == '0') {
          this.activityTypeList = res.activityTypeList;
          this.activityList = res.activityList;
          this.count = res.count;
          //alert(this.count );
          // this.endDate = res.endDate;
          // this.startDate = res.startDate;
          this.pageList = [];
          for (var i = 1; i <= this.count; i++) {
            this.pageList.push(i);
          }
          // console.log(this.pageList);
        } else {
          $.MsgAlert.AlertTure("活动一览", res.msg);
        }
      })
  }
  //点击页码数
  pageSearch(count, model): void {
    var paramTypeCode = '';
    var paramActiveStatus = '';
    if (model.typeCode != undefined) {
      paramTypeCode = model.typeCode;
    }
    if (model.activeStatus != undefined) {
      paramActiveStatus = model.activeStatus;
    }
    var nextPage = 0;
    this.selectNum = count;
    nextPage = (count - 1) * 10;
    this.startDateParam = this.dateFormat(this.startDate);
    this.endDateParam = this.dateFormat(this.endDate);
    this.activityService.getActivitySearchList(this.startDateParam, this.endDateParam, nextPage, paramActiveStatus, paramTypeCode).then(
      res => {
        if (res.code == '0') {
          this.activityTypeList = res.activityTypeList;
          this.activityList = res.activityList;
          this.count = res.count;
          // this.endDate = res.endDate;
          // this.startDate = res.startDate;
          this.pageList = [];
          for (var i = 1; i <= this.count; i++) {
            this.pageList.push(i);
          }
          // console.log(this.pageList);
        } else {
          $.MsgAlert.AlertTure("活动一览", res.msg);
        }
      })

  }
  //跳转按钮
  pageForward(model): void {
    if (this.pageNum > this.count) {
      document.getElementById("comPass").style.display = "block";
    } else {
      document.getElementById("comPass").style.display = "none";
      var paramTypeCode = '';
      var paramActiveStatus = '';
      if (model.typeCode != undefined) {
        paramTypeCode = model.typeCode;
      }
      if (model.activeStatus != undefined) {
        paramActiveStatus = model.activeStatus;
      }
      var pageSelect = 0;
      this.selectNum = this.pageNum;
      pageSelect = (this.pageNum - 1) * 10;
      this.startDateParam = this.dateFormat(this.startDate);
      this.endDateParam = this.dateFormat(this.endDate);
      this.activityService.getActivitySearchList(this.startDateParam, this.endDateParam, pageSelect, paramActiveStatus, paramTypeCode).then(
        res => {
          if (res.code == '0') {
            this.activityTypeList = res.activityTypeList;
            this.activityList = res.activityList;
            this.count = res.count;
           // alert(this.count);
            // this.endDate = res.endDate;
            // this.startDate = res.startDate;
            this.pageList = [];
            for (var i = 1; i <= this.count; i++) {
              this.pageList.push(i);
            }
            // console.log(this.pageList);
          } else {
            $.MsgAlert.AlertTure("活动一览", res.msg);
          }
        })
    }
  }
  //页码不合理
  pageNumError(): void {
    console.log(this.pageNum);
    if (this.pageNum > this.count) {
      document.getElementById("comPass").style.display = "block";
    } else {
      document.getElementById("comPass").style.display = "none";
    }
  }
  //发布活动
  publishActivity(): void {
    this.router.navigate(['activity/release']);
  }
  editType(): void {
    this.router.navigate(['activity/type']);
  }
}