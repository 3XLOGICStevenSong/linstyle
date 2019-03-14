import { Component, ViewChild,OnInit } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Http, Headers } from '@angular/http';
import { NgForm } from '@angular/forms';
import { SettingService } from './setting-time.service';
import { Setting } from './setting-time';
import { Router } from '@angular/router';

declare var $: any;

@Component({
  selector: 'setting-time',
  templateUrl: './setting-time.component.html',

  providers: [SettingService]
})
export class SettingTimeComponent implements OnInit {

  public urlSelect: String;

  public initFlag: Boolean;

  public setting: Setting;

  public contactTime: Number;

  public tryTime: Number;

@ViewChild('timeForm') timeForm: NgForm;
  // private test  : number;
  constructor(private router: Router, private settingService: SettingService) { }


  // 页面初始化
  ngOnInit(): void {


    // 获取通知信息
    this.settingService.getSettingTimeList().then(res => {
      if (res.code == '0') {
        this.tryTime = res.tryTime;
        // alert(this.setting.tryTime);
        this.contactTime = res.contactTime;
      } else {
        //error
      }
    });
    this.initFlag = true;
  }

  updateTime(): void {
    if (!this.timeForm.valid && this.timeForm.controls) {
        for (const name in this.timeForm.controls) {
          if (this.timeForm.controls.hasOwnProperty(name)) {
            this.timeForm.controls[name].markAsDirty();
          // alert(">>>>");
          }
        }
    } else{
      this.settingService.updateTime(this.tryTime, this.contactTime).then(res => {
        if (res.code != '0') {
       $.MsgAlert.AlertTure("修改密码",res.msg);
        } 
      });
    }
  }
}
