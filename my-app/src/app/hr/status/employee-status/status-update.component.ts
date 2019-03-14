import { Component, ViewChild, OnInit } from '@angular/core';
import { RouterModule, Routes, ActivatedRoute } from '@angular/router';
import { Http, Headers } from '@angular/http';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

import { EmployeeStatusService } from './status.service';
import { PermissionsService } from '../../../common/permissions.service';
import { Department } from '../employee-register/department';
import { Type } from '../employee-register/type';
import { StatusInit } from './status-init';
import { Interview } from '../employee-register/interview';
import { BsDatepickerConfig, BsLocaleService } from 'ngx-bootstrap/datepicker';
import { listLocales } from 'ngx-bootstrap/bs-moment';
import { defineLocale } from 'ngx-bootstrap/bs-moment';
import { zhCn } from 'ngx-bootstrap/locale';
declare var $: any;


@Component({
  selector: 'status-update',
  templateUrl: './status-update.component.html',
  providers: [EmployeeStatusService,BsLocaleService],

  // providers:[ MenuService ]
})
//员工状态更新组件
export class StatusUpdateComponent implements OnInit {
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

  public interList?: Array<Interview>;

  public interId: number;
    //日历相关
  locale = 'zh-cn';
  colorTheme = 'theme-dark-blue';
  bsConfig: Partial<BsDatepickerConfig>;
  //日历相关结束

  @ViewChild('statusForm') statusForm: NgForm;

  constructor(private router: Router, private route: ActivatedRoute, private employeeStatusService: EmployeeStatusService, private _localeService: BsLocaleService) {
    //设置日历信息
    defineLocale('zh-cn', zhCn);
    this._localeService.use(this.locale);
    this.bsConfig = Object.assign({ containerClass: this.colorTheme }, { locale: this.locale });
  }



  public ngOnInit(): void {

    if (this.route.snapshot.paramMap.has('cardNo')) {//从员工状态管理页面跳转到该页面
      var cardNo = this.route.snapshot.paramMap.get('cardNo');
      //  console.log(cardNo + ">>>>>>cardNo");
      if (cardNo != '') {
        this.employeeStatusService.employeeInfoDetail(cardNo).then(
          res => {
            if (res.code == '0') {
              this.depList = res.depList;
              // this.model.depId = this.depList[0].depId;
              this.workStatusList = res.workStatusList;
              // this.model.employeeType = this.workStatusList[0].typeCode;
              this.workTypeList = res.workTypeList;
              // this.model.jobStatus = this.workTypeList[0].typeCode;
              this.model = res.employeeInfo;
              //  this.model.jobStatus=res.employeeInfo.jobStatus;
              // this.model.employeeType=res.employeeInfo.employeeType;
              // console.log(this.model.cardNo);
            } else {
              $.MsgAlert.AlertTure("页面初始化", res.msg);
            }
          }
        )
      } else { $.MsgAlert.AlertTure("页面初始化", "页面发生错误"); }
    } else {
      $.MsgAlert.AlertTure("页面初始化", "页面发生错误");
    }
  }
  registerUser(): void {
    this.router.navigate(['personnel/employee/state/register']);
  }
  jobStatusInfo(): void {
    this.router.navigate(['personnel/employee/state/job']);
  }
  //更新员工状态信息
  updateEmployeeInfo(model): void {
    if (!this.statusForm.valid && this.statusForm.controls) {
      for (const name in this.statusForm.controls) {
        if (this.statusForm.controls.hasOwnProperty(name)) {
          this.statusForm.controls[name].markAsDirty();
        }
      }
    } else {
      this.employeeStatusService.updateEmployeeInfo(model).then(
        res => {
          if (res.code == '0') {
            $.MsgAlert.AlertTure("修改员工状态", "修改员工状态成功");
          } else {
            $.MsgAlert.AlertTure("修改员工状态", res.msg);
          }
        }
      )
    }
  }
//获取未关联的面试记录
  getRecruitInterviewInfo(): void {
    this.employeeStatusService.getRecruitInterviewerList().then(
      res => {
        if (res.code == '0') {
          //$(".Interviewlist").show();
          this.interList = res.interList;
         // console.log(">>>>>" + this.interList.length)
        } else {
          $.MsgAlert.AlertTure("面试信息", res.msg);
        }
      }
    )

  }
  //重新关联面试记录
  relateRecruit(cardNo: String, interIdOld: Number): void {
    console.log(">>>>>>>InterId " + this.interId);
    this.employeeStatusService.updateEmployeeInterInfo(cardNo, interIdOld, this.interId).then(
      res => {
        if (res.code == '0') {
          $.MsgAlert.AlertTure("面试记录关联", "面试记录关联成功");
        } else {
          $.MsgAlert.AlertTure("面试记录关联", res.msg);
        }

      })
  }
}
