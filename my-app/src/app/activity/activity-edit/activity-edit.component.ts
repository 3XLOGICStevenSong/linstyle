import { Component, ViewChild, OnInit } from '@angular/core';
import { RouterModule, Routes, ActivatedRoute } from '@angular/router';
import { Http, Headers } from '@angular/http';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

import { Activity } from '../activity';
import { File } from '../activity-publish/file';
import { Type } from '../../hr/status/employee-register/type';
import { ActivityEditService } from '../activity-edit/activity-edit.service';
import { FileUploader } from 'ng2-file-upload';
//import { BsDatepickerConfig } from 'ngx-bootstrap';
import { BsDatepickerConfig, BsLocaleService } from 'ngx-bootstrap/datepicker';
import { listLocales } from 'ngx-bootstrap/bs-moment';
import { defineLocale } from 'ngx-bootstrap/bs-moment';
import { zhCn } from 'ngx-bootstrap/locale';

declare var $: any;

@Component({
  selector: 'activity-edit',
  templateUrl: './activity-edit.component.html',
  providers: [ActivityEditService, BsLocaleService],

})
export class ActivityEditComponent implements OnInit {
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

  constructor(private router: Router, private route: ActivatedRoute, private activityEditService: ActivityEditService, private _localeService: BsLocaleService) {
    //设置日历信息
    defineLocale('zh-cn', zhCn);
    this._localeService.use(this.locale);
    this.bsConfig = Object.assign({ containerClass: this.colorTheme }, { locale: this.locale }, { format: this.format });
    //设置日历信息结束
    this.minDate = new Date();
  }
  paramModel: File = {
    activeId: this.model.activeId,
  }

  public ngOnInit(): void {
    if (this.route.snapshot.paramMap.has('activeId')) {//活动详情页面跳转
      var activeId = this.route.snapshot.paramMap.get('activeId');
      //  console.log(cardNo + ">>>>>>cardNo");
      if (activeId != '') {
        this.paramModel.activeId=parseInt(activeId);

        this.activityEditService.getActivityInfo(activeId).then(
          res => {
            if (res.code == '0') {
              this.typeList = res.typeList;
              this.model.typeCode = res.typeCode;
              this.model.activeStatus = res.typeCode;
              this.model = res;
              this.model.activeTime = [];
              this.model.activeTime[0] = (new Date(res.activeStartTime));
              this.model.activeTime[1] = (new Date(res.activeEndTime));
              this.model.signTime = [];
              this.model.signTime[0] = (new Date(res.signStartTime));
              this.model.signTime[1] = (new Date(res.signEndTime));
              //报名信息绑定
              if (res.optionItem != null) {
                var temp = [];
                temp = res.optionItem.split("|");
                if (temp.length > 0) {
                  for (var i = 0; i < temp.length; i++) {
                    if (temp[i] == '0') {
                      this.model.name = true;
                    } else if (temp[i] == '1') {
                      this.model.sex = true;
                    } else if (temp[i] == '2') {
                      this.model.card = true;
                    } else if (temp[i] == '3') {
                      this.model.tel = true;
                    } else if (temp[i] == '4') {
                      this.model.address = true;
                    }
                  }
                }
              }
              //文件信息
              if (res.activityFileList != null) {
                // console.log(this.fileName);
                var allName = "";
                for (var i = 0; i < res.activityFileList.length; i++) {
                  if (i == res.activityFileList.length - 1) {
                    allName += res.activityFileList[i].fileName;
                  } else {
                    allName += res.activityFileList[i].fileName + "|";
                  }
                }
                this.fileName = allName;
              }
            } else {
              $.MsgAlert.AlertTure("活动编辑", res.msg);
            }
          })
      }
    }
  }
 
  //清除队列内容和显示内容
  fileClear(): void {
    this.uploader.clearQueue();
    this.fileName = '';
  }
  //选择文件
  selectChange(): void {
    var files = this.uploader.queue;
    console.log(this.fileName);
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
  //发布活动
  editActivity(): void {
    // alert(this.model.activeTime[0]);

    if (!this.publishForm.valid && this.publishForm.controls) {
      for (const name in this.publishForm.controls) {
        if (this.publishForm.controls.hasOwnProperty(name)) {
          this.publishForm.controls[name].markAsDirty();
        }
      }

    } else {
      this.model.activeStartTime = this.model.activeTime[0];
     // alert(this.model.activeStartTime);
      this.model.activeEndTime = this.model.activeTime[1];
     // alert(this.model.activeEndTime);
      this.model.signStartTime = this.model.signTime[0];
     // alert(this.model.signStartTime);
      this.model.signEndTime = this.model.signTime[1];
     // alert(this.model.signEndTime);
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
      //  alert()
        this.activityEditService.updateActivity(this.model).then(

          res => {
            if (res.code == '0') {
              //上传文件
              if (this.uploader.queue.length > 0) {
                this.model.activeId = res.activeId;
                console.log(">>>>" + this.model.activeId);
                try {
                  this.uploader.queue[this.uploader.queue.length - 1]._file;
                  //console.log(this.uploader.queue[this.uploader.queue.length - 1]._file)
                  // 打印文件选择名称
                  this.uploadFile();
                } catch (err) {
                  $.MsgAlert.AlertTure("发布活动", "上传文件失败");
                }
              }
              $.MsgAlert.AlertTure("发布活动", "发布活动成功");
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
    this.uploader.queue[this.uploader.queue.length - 1].onSuccess = function (response, status, headers) {
      // 上传文件成功
      if (status == 200) {
        //上传文件后获取服务器返回的数据
        let tempRes = JSON.parse(response);
        if (tempRes.succeed == false) {
          $.MsgAlert.AlertTure("发布活动", "上传文件失败");
          // $(".showMess").text("上传文件失败");
        } else {
          $.MsgAlert.AlertTure("发布活动", "上传文件成功");
          // $(".showMess").text("上传文件成功");
        }
      } else {
        // 上传文件后获取服务器返回的数据错误
        $.MsgAlert.AlertTure("发布活动", "上传文件成功");
        //$(".showMess").text("上传文件失败");
      }
    };
    this.uploader.uploadAll(); // 开始上传
  }
}