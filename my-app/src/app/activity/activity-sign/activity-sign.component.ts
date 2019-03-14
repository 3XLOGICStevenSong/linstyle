import { Component, ViewChild, OnInit } from '@angular/core';
import { RouterModule, Routes, ActivatedRoute } from '@angular/router';
import { Http, Headers } from '@angular/http';

import { Router } from '@angular/router';
import { ActivitySignService } from './activity-sign.service';
import { PermissionsService } from '../../common/permissions.service';
import { Type } from '../../hr/status/employee-register/type';
import { Activity } from '../activity';
import { ActivityRegister } from '../activity-register';
import { ActivityFile } from '../activity-file';
import { NgForm } from '@angular/forms';
declare var $: any;

@Component({
  selector: 'activity-sign',
  templateUrl: './activity-sign.component.html',

  providers: [ActivitySignService]
})
export class ActivitySignComponent implements OnInit {

  //报名信息
  public activityRegisterList: Array<ActivityRegister>;

  //活动信息
  public activity: Activity = new Activity();

  public activeId: Number;




  @ViewChild('registerForm') registerForm: NgForm;
  constructor(private router: Router, private route: ActivatedRoute, private activitySignService: ActivitySignService) { }

  public ngOnInit(): void {

    if (this.route.snapshot.paramMap.has('activeId')) {//活动详情跳转进入报名一览页面
      this.activeId = parseInt(this.route.snapshot.paramMap.get('activeId'));
      // console.log("this.activeId" + this.activeId);
      this.activitySignService.getActivitySignInfo(this.activeId).then(
        res => {
          if (res.code == '0') {
            this.activityRegisterList = res.activityRegisterList;
            this.activity.activeId = res.activeId;
            this.activity.activeTitle = res.activeTitle;

          } else {
            $.MsgAlert.AlertTure("活动报名一览", res.msg);
          }
        }
      )
    } else {
      $.MsgAlert.AlertTure("活动报名一览", "获取信息失败");
    }
  }
  exportExcel(): void {
    if (this.activeId != undefined) {
      this.activitySignService.downloadXLS(this.activeId);
    } else {
      $.MsgAlert.AlertTure("活动报名一览", "获取信息失败");
    }
  }
}
