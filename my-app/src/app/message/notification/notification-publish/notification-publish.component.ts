import { Component, ViewChild, OnInit } from '@angular/core';
import { RouterModule, Routes, ActivatedRoute, ParamMap} from '@angular/router';
import { Http, Headers } from '@angular/http';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { NotificationService } from '../notification.service';
import { NoticeType } from '../notice.type';
import { NoticeInfo } from '../notification.noticeInfo';
import { NoticeFile } from '../notification.noticeFile';
import { element } from 'protractor';
import { BsDatepickerConfig, BsLocaleService } from 'ngx-bootstrap/datepicker';
import { listLocales } from 'ngx-bootstrap/bs-moment';
import { defineLocale } from 'ngx-bootstrap/bs-moment';
import { zhCn } from 'ngx-bootstrap/locale';
import { NgModel } from '@angular/forms/src/directives/ng_model';
import { FileUploader } from 'ng2-file-upload';
import { inflate } from 'zlib';

declare var $:any;

@Component({
  selector: 'notification-publish',
  templateUrl: './notification-publish.component.html',
  providers:[ NotificationService,BsLocaleService]
})

export class NotificationPublishComponent implements OnInit{

  noticeDetail: NoticeInfo = new NoticeInfo();
  noticeId: string;
  nFile = new NoticeFile(); //通知文件
  typeVal: number;  //类型Id
  titleVal: String;  //通知标题
  detailVal: String;  //通知详情
  startDateVal:String;
  endDateVal:String;
  flag:String;  //发布通知2，保存1 两个按钮切换标志
  importantFlg ="0"; //置顶
  fileName:string;
  // 接口返回参数  
  noticeTypeList:Array<NoticeType>;

  maxDate: Date;
  minDate: Date;

  //日历相关
  locale = 'zh-cn';
  colorTheme = 'theme-dark-blue';
  format: 'yyyy-mm-dd'
  bsConfig: Partial<BsDatepickerConfig>;

  @ViewChild('publishNoticeForm') publishNoticeForm: NgForm;
  // 实例化RecruitListService
  constructor(private route: ActivatedRoute,
    private router: Router,
    private notificationService: NotificationService,
    private _localeService: BsLocaleService) { 
    //设置日历信息
    defineLocale('zh-cn', zhCn);
    this._localeService.use(this.locale);
    this.bsConfig = Object.assign({ containerClass: this.colorTheme }, { locale: this.locale }, { format: this.format });
    //设置日历信息结束
    this.minDate = new Date();
  }

  // 页面初始化
  ngOnInit(): void {
    var that=this;
      this.notificationService.getNoticeTypeList().then(result =>
        {
        if(result.code==0){
          this.noticeTypeList=result.noticeTypeList;
          if (this.route.snapshot.paramMap.has('noticeId')) 
              {//从一览页面跳转过来的
                that.flag="保存";//保存
                that.noticeId = this.route.snapshot.paramMap.get('noticeId');
                // 获取通知详情
                this.notificationService.getNotificationInfo(this.noticeId).then(result => {
                  if(result.code == 0){   
                    this.noticeDetail = result;
                    this.typeVal=this.noticeDetail.typeId;
                      //复选框初始判断
                      if(this.noticeDetail.importantFlg=="1"){
                      $(document).find("#checkboxId").attr('checked','checked');
                      this.importantFlg = "1";
                      //  this.importantFlg1 = "1";
                      }
                    this.typeVal = this.noticeDetail.typeId;
                    this.titleVal = this.noticeDetail.title;
                    this.detailVal = this.noticeDetail.content;
                    this.startDateVal = this.noticeDetail.startDate;
                    this.endDateVal = this.noticeDetail.endDate;
                    this.noticeDetail.notificationFile = result.notificationFile;
                    var allName="";
                    for (var i = 0; i < this.noticeDetail.notificationFile.length; i++) {
                      if (i == this.noticeDetail.notificationFile.length - 1) {
                        allName += this.noticeDetail.notificationFile[i].fileName;
                      } else {
                        allName += this.noticeDetail.notificationFile[i].fileName + "，";
                      }
                    }
                    this.fileName = allName;
                  } else {
                    // 请求失败
                    alert(result.code + result.msg);
                  }
                });
              } 
          else {//只发布
            that.flag="发布通知";//发布通知
            that.typeVal=Number(this.noticeTypeList[0].typeId);
          }
        }else{
          // 请求失败
          $.MsgAlert.AlertTure("", result.msg);
        }
      });
  }

  //复选框
  smallChange(obj){
    if(obj.path[0].checked){
      this.importantFlg = "1";
    }else{
      this.importantFlg = "0";
    }
  }

  //文件---双向绑定
  public uploader: FileUploader = new FileUploader({
    url: "~resource/noticeFile/",
    method: "POST",
    itemAlias: "file",
    additionalParameter: this.noticeDetail
  });

  //选择文件
  selectChange(): void {
    //画面显示
    var files = this.uploader.queue;
    var allName = "";
    for (var i = 0; i < files.length; i++) {
      if (i == files.length - 1) {
        allName += files[i].file.name;
      } else {
        allName += files[i].file.name + "，";
      }
    }
    this.fileName = allName;
  }

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
        } else {
          $.MsgAlert.AlertTure("发布活动", "上传文件成功");
        }
      } else {
        // 上传文件后获取服务器返回的数据错误
        $.MsgAlert.AlertTure("发布活动", "上传文件成功");
      }
    };
    this.uploader.uploadAll(); // 开始上传
  }

  publishNotification():void{
    var that = this;
    if (!this.publishNoticeForm.valid && this.publishNoticeForm.controls) {
      for (const name in this.publishNoticeForm.controls) {
        if (this.publishNoticeForm.controls.hasOwnProperty(name)) {
          this.publishNoticeForm.controls[name].markAsDirty();
        }
      }
    }
    else{
      if(this.flag == "发布通知"){
        this.noticeDetail = new NoticeInfo();
        //发布通知
        this.noticeDetail.typeId = this.typeVal;
        this.noticeDetail.title = this.titleVal;
        this.noticeDetail.startDate = this.startDateVal;
        this.noticeDetail.endDate = this.endDateVal;
        this.noticeDetail.content = this.detailVal;
        this.noticeDetail.importantFlg = this.importantFlg;
        this.notificationService.insertNotifiction(this.noticeDetail).then(result =>{
          if(result.code==0){
            //上传文件
            if (this.uploader.queue.length > 0) {
              // that.noticeDetail.noticeId = result.msg;//(通知上传成功之后，将noticeId放到msg返回给前台)
              that.uploader.options.additionalParameter.noticeId = result.msg;
              try {
                this.uploader.queue[this.uploader.queue.length - 1]._file;
                // 打印文件选择名称
                this.uploadFile();
              } catch (err) {
                $.MsgAlert.AlertTure("发布通知", "上传文件失败");
                return;
              }
            }
            // 发布成功，跳转到通知一览
            this.router.navigate(['notification/info']);
          }else{
            // 请求失败
            $.MsgAlert.AlertTure("发布通知", result.msg);
          }
        });
      }
      else{
          //更新通知
          this.noticeDetail.typeId = Number(this.typeVal);
          this.noticeDetail.title = this.titleVal;
          this.noticeDetail.startDate = this.startDateVal;
          this.noticeDetail.endDate = this.endDateVal;
          this.noticeDetail.content = this.detailVal;
          this.noticeDetail.importantFlg = this.importantFlg;
        
          this.notificationService.updateNotificationInfo(this.noticeDetail).then(result =>{
            if(result.code==0){
                //上传文件
              if (this.uploader.queue.length > 0) {
                try {
                  this.uploader.queue[this.uploader.queue.length - 1]._file;
                  that.uploader.options.additionalParameter.noticeId = that.noticeDetail.noticeId;
                  // 打印文件选择名称
                  this.uploadFile();
                } catch (err) {
                  $.MsgAlert.AlertTure("发布通知", "上传文件失败");
                  return;
                }
              }
                // 发布成功，跳转到通知详情
                var noticeIdStr = String(this.noticeDetail.noticeId);
                var url = 'notification/detail/' + noticeIdStr;
                this.router.navigate(['notification/info']);
            }else{
              // 请求失败
              $.MsgAlert.AlertTure("", result.msg);
            }
          });
        }
    }
  }
}
  
