import { Component, ViewChild, OnInit } from '@angular/core';
import { RouterModule, Routes, ActivatedRoute } from '@angular/router';
import { Http, Headers } from '@angular/http';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

import { Activity } from '../activity';
import { File } from './file';

import { Type } from '../../hr/status/employee-register/type';
import { ActivityPublishService } from './activity-publish.service';
import { FileUploader } from 'ng2-file-upload';
//import { BsDatepickerConfig } from 'ngx-bootstrap';
import { BsDatepickerConfig, BsLocaleService } from 'ngx-bootstrap/datepicker';
import { listLocales } from 'ngx-bootstrap/bs-moment';
import { defineLocale } from 'ngx-bootstrap/bs-moment';
import { zhCn } from 'ngx-bootstrap/locale';

declare var $: any;

@Component({
  selector: 'activity-publish',
  templateUrl: './activity-publish.component.html',
  providers: [ActivityPublishService, BsLocaleService],

})
export class ActivityPublishComponent implements OnInit {
  // locale = 'zh-cn';

  public typeList?: Array<Type>;

  public fileName: string;

  maxDate: Date;
  
  minDate: Date;

  //日历相关
  locale = 'zh-cn';
  colorTheme = 'theme-dark-blue';
  format: 'yyyy-mm-dd'
  bsConfig: Partial<BsDatepickerConfig>;
  //日历相关结束
  public model: Activity = new Activity();
  @ViewChild('publishForm') publishForm: NgForm;

  constructor(private router: Router, private activityPublishService: ActivityPublishService, private _localeService: BsLocaleService) {
    //设置日历信息
    defineLocale('zh-cn', zhCn);
    this._localeService.use(this.locale);
    this.bsConfig = Object.assign({ containerClass: this.colorTheme }, { locale: this.locale }, { format: this.format });
    //设置日历信息结束
    this.minDate = new Date();
  }


  public ngOnInit(): void {

    this.activityPublishService.getActivityTypeInfo("ACTIVE_TYPE").then(
      res => {
        if (res.code == '0') {
          this.typeList = res.typeList;
          this.model.typeCode = this.typeList[0].typeCode;
          this.model.activeStatus = '0';
        } else {
          $.MsgAlert.AlertTure("发布活动", res.msg);
        }
      })
  }
  //清除队列内容和显示内容
  fileClear(): void {
    this.uploader.clearQueue();
    this.fileName = '';
  }
  //选择文件
  selectChange(): void {
    var files = this.uploader.queue;
    var allName = "";
    for (var i = 0; i < files.length; i++) {
      if (i == files.length - 1) {
        allName += files[i].file.name;
      } else {
        allName += files[i].file.name + "|";
      }
    }
    this.fileName = allName;

  }

  setMaxDate(): void {

    if (this.model.activeTime[0].getDate() != undefined) {
      this.maxDate = new Date();
      //  alert("dddddd"+this.maxDate);
      this.maxDate.setDate(this.model.activeTime[0].getDate() - 1);
      // this.maxDate=this.model.activeTime[0];
      // alert("nnnnnn"+this.maxDate.getDate());}
    }
  }
  //时间验证
  validateDate(): void {
    alert(this.model.signTime[0].getDate());
    if (this.model.activeTime[0].getDate() != undefined && this.model.signTime[0].getDate() != undefined) {
      var temDate: Date;
      temDate = new Date();
      temDate.setDate(this.model.activeTime[0].getDate() - 1);
      if (this.model.signTime[0].getTime() > temDate.getTime()) {
        document.getElementById("comPass").style.display = "block";
      } else {
        document.getElementById("comPass").style.display = "none";
      }
    }
  }


  paramModel: File = {
    activeId: this.model.activeId,
  }
  //发布活动
  publishActivity(): void {
    // alert(this.model.activeTime[0]);

    if (!this.publishForm.valid && this.publishForm.controls) {
      for (const name in this.publishForm.controls) {
        if (this.publishForm.controls.hasOwnProperty(name)) {
          this.publishForm.controls[name].markAsDirty();
        }
      }

    } else {
      this.model.activeStartTime = this.model.activeTime[0];
      this.model.activeEndTime = this.model.activeTime[1];
      this.model.signStartTime = this.model.signTime[0];
      this.model.signEndTime = this.model.signTime[1];
      //报名时间check
      var temDate: Date;
      temDate = new Date();
      temDate.setDate(this.model.activeStartTime.getDate() - 1);
      if (this.model.signStartTime.getTime() > temDate.getTime()) {
        document.getElementById("comPass").style.display = "block";
      } else {
        document.getElementById("comPass").style.display = "none";
        var items = "";
        //选项拼接
        if (this.model.name) {
          items += '0' + '|';
        }
        if (this.model.sex) {
          items += '1' + '|';
        }
        if (this.model.card) {
          items += '2' + '|';
        }
        if (this.model.tel) {
          items += '3' + '|';
        }
        if (this.model.tel) {
          items += '4' + '|';
        }
        this.model.optionItem = items;

        this.activityPublishService.publishActivity(this.model).then(

          res => {
            if (res.code == '0') {
              //上传文件
              if (this.uploader.queue.length > 0) {
                this.model.activeId = res.activeId;
                this.paramModel.activeId = res.activeId;
                console.log(">>>>" + this.paramModel.activeId);
                try {
                  this.uploader.queue[this.uploader.queue.length - 1]._file;
                  console.log(this.uploader.queue[this.uploader.queue.length - 1]._file)
                  // 打印文件选择名称
                  this.uploadFile();
                } catch (err) {
                  $.MsgAlert.AlertTure("发布活动", "发布活动失败");
                }
              }
              // $.MsgAlert.AlertTure("发布活动", "发布活动成功");
            } else {
              $.MsgAlert.AlertTure("发布活动", res.msg);
            }
          })
      }
    }
  }

  public uploader: FileUploader = new FileUploader({
    url: "~resource/activityFile",
    method: "POST",
    itemAlias: "file",
    additionalParameter: this.paramModel
  });
  // 定义事件，上传文件
  uploadFile() {
    // 上传
    // alert("上传前")
    this.uploader.queue[this.uploader.queue.length - 1].onSuccess = function (response, status, headers) {
      // 上传文件成功
      if (status == 200) {
        // alert("200前")
        //上传文件后获取服务器返回的数据
        let tempRes = JSON.parse(response);
        if (tempRes.succeed == false) {
          $.MsgAlert.AlertTure("发布活动", "发布活动失败");
          // $(".showMess").text("上传文件失败");
        } else {
          $.MsgAlert.AlertTure("发布活动", "发布活动成功");
          // $(".showMess").text("上传文件成功");
        }
      } else {
        // 上传文件后获取服务器返回的数据错误
        $.MsgAlert.AlertTure("发布活动", "发布活动成功");
        //$(".showMess").text("上传文件失败");
      }
    };
    this.uploader.uploadAll(); // 开始上传
  }
}