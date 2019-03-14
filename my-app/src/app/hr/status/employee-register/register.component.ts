import { Component, ViewChild, OnInit } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Http, Headers } from '@angular/http';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

import { EmployeeRegisterService } from './register.service';
import { PermissionsService } from '../../../common/permissions.service';
import { Department } from './department';
import { Type } from './type';
import { Interview } from './interview';
import { RegisterInit } from './register-init';
import { BsDatepickerConfig, BsLocaleService } from 'ngx-bootstrap/datepicker';
import { listLocales } from 'ngx-bootstrap/bs-moment';
import { defineLocale } from 'ngx-bootstrap/bs-moment';
import { zhCn } from 'ngx-bootstrap/locale';
declare var $: any;
declare var sha512: any;


@Component({
  selector: 'employee-register',
  templateUrl: './register.component.html',
  providers: [EmployeeRegisterService, BsLocaleService],

  // providers:[ MenuService ]
})
//员工状态管理
export class EmployeeRegisterComponent implements OnInit {

  public depList?: Array<Department>;

  public workStatusList?: Array<Type>;

  public workTypeList?: Array<Type>;

  public interList?: Array<Interview>;

  //日历相关
  locale = 'zh-cn';
  colorTheme = 'theme-dark-blue';
  bsConfig: Partial<BsDatepickerConfig>;
  //日历相关结束

  public interId: number;

  // public password:string;
  public model: RegisterInit = new RegisterInit();

  @ViewChild('registerForm') registerForm: NgForm;

  constructor(private router: Router, private employeeRegisterService: EmployeeRegisterService, private _localeService: BsLocaleService) { 
    //设置日历信息
    defineLocale('zh-cn', zhCn);
    this._localeService.use(this.locale);
    this.bsConfig = Object.assign({ containerClass: this.colorTheme }, { locale: this.locale });
  }

  public ngOnInit(): void {
    this.employeeRegisterService.getInitInfo().then(
      res => {
        if (res.code == '0') {
          this.depList = res.depList;
          this.model.depId = this.depList[0].depId;
          this.interList = res.interList;
          this.workStatusList = res.workStatusList;
          this.model.employeeType = this.workStatusList[0].typeCode;
          this.workTypeList = res.workTypeList;
          this.model.jobStatus = this.workTypeList[0].typeCode;
        } else {
          $.MsgAlert.AlertTure("页面初始化", res.msg);
        }
      })
  }

  // onchange():void{
  //   alert($(".testLay").val());
  // }
getValue():void{
  this.model.entryDate=$(".testLay").val();
}
  registerUser(): void {
    if (!this.registerForm.valid && this.registerForm.controls) {
      for (const name in this.registerForm.controls) {
        if (this.registerForm.controls.hasOwnProperty(name)) {
          this.registerForm.controls[name].markAsDirty();
        }
      }

    } else {
      this.model.password = sha512("DJB");
      console.log(this.interId+"interId")
      this.model.interId = this.interId;
      console.log(this.model.interId+">>>>>>" );
      this.employeeRegisterService.registerUser(this.model).then(
        res => {
          if (res.code == '0') {
           $.MsgAlert.AlertTure("注册新员工","注册新员工成功");
          } else {
            $.MsgAlert.AlertTure("注册新员工", res.msg);
          }
        }
      )
    }
    // console.log(this.register.cardNo);
  }
 
}