import { Component, ViewChild, OnInit } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Http, Headers } from '@angular/http';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

import { EmployeeStatusService } from './status.service';
import { PermissionsService } from '../../../common/permissions.service';
import { Department } from '../employee-register/department';
import { Type } from '../employee-register/type';
import { StatusInit } from './status-init';
import { BsDatepickerConfig, BsLocaleService } from 'ngx-bootstrap/datepicker';
import { listLocales } from 'ngx-bootstrap/bs-moment';
import { defineLocale } from 'ngx-bootstrap/bs-moment';
import { zhCn } from 'ngx-bootstrap/locale';
declare var $: any;


@Component({
  selector: 'employee-status',
  templateUrl: './status.component.html',
  providers: [EmployeeStatusService, PermissionsService,BsLocaleService],

  // providers:[ MenuService ]
})
//员工状态管理
export class EmployeeStatusComponent implements OnInit {
  //部门列表
  public depList?: Array<Department>;
  //在职状态
  public workStatusList?: Array<Type>;
  //员工类型
  public workTypeList?: Array<Type>;
  //画面数据项
  public model: StatusInit = new StatusInit();
  //员工信息列表
  public employeeInfoList?: Array<StatusInit>;
 //日历相关
  locale = 'zh-cn';
  colorTheme = 'theme-dark-blue';
  bsConfig: Partial<BsDatepickerConfig>;
  constructor(private router: Router, private employeeStatusService: EmployeeStatusService, private permissionsService: PermissionsService, private _localeService: BsLocaleService) {
     //设置日历信息
    defineLocale('zh-cn', zhCn);
    this._localeService.use(this.locale);
    this.bsConfig = Object.assign({ containerClass: this.colorTheme }, { locale: this.locale });
   }

  //menuComponent=new MenuComponent(this.router , null);

  public ngOnInit(): void {
    //初始化员工状态管理页面
    this.employeeStatusService.employeeStateInit().then(
      res => {
        if (res.code == '0') {
          this.depList = res.depList;
          // this.model.depId = this.depList[0].depId;
          this.workStatusList = res.workStatusList;
          // this.model.employeeType = this.workStatusList[0].typeCode;
          this.workTypeList = res.workTypeList;
          // this.model.jobStatus = this.workTypeList[0].typeCode;
          console.log(this.employeeInfoList);
        } else {
          $.MsgAlert.AlertTure("页面初始化", res.msg);
        }
      })
  }
  //注册新员工
  registerUser(): void {
    this.router.navigate(['personnel/employee/state/register']);
  }
  //在职状态维护
  jobStatusInfo(): void {
    this.router.navigate(['personnel/employee/state/job']);
  }
  //检索员工信息
  searchEmployeeInfo(model): void {

    this.employeeStatusService.searchEmployeeInfo(model.cardNo, model.name, model.chineseAbbr, model.jobStatus, model.depId, model.employeeType, model.entryDate).then(
      res => {
        if (res.code == '0') {
          this.employeeInfoList = res.employeeInfoList;
        } else {
          $.MsgAlert.AlertTure("检索员工信息", res.msg);
        }
      }
    )
  }
}