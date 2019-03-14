import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../notification.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { NoticeType } from '../notice.type';
import { NoticeInfo } from '../notification.noticeInfo';
import { NoticeFile } from 'app/message/notification/notification.noticeFile';
import { PermissionsService } from 'app/common/permissions.service';
declare var $: any;

@Component({
  selector: 'notification-list',
  templateUrl: './notification-list.component.html',
  providers: [NotificationService,PermissionsService]
})

export class NotificationListComponent implements OnInit {

  // 接口返回参数  
  noticeTypeList: Array<NoticeType>;
  noticeList: Array<NoticeInfo>; //
  acceptList: Array<NoticeInfo>;
  startDateVal: string;  //检索开始时间
  endDateVal: string;//检索结束时间
  typeVal = "0";
  permission: Set<String>;
  createFlag: boolean;//“发布通知”标识
  createTypeFlag: boolean;//“发布通知”标识
  searchFlag: boolean;//搜索标识
  // 实例化RecruitListService
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private notificationService: NotificationService,
    private permissionsService: PermissionsService) { }

  // 页面初始化
  ngOnInit(): void {

    //权限信息
    this.permissionsService.getPermissionList().then(
      res => {
      this.permission = res;
      let permissArray = this.permission;
      this.createFlag = false;
      this.searchFlag = false;
      this.createTypeFlag = false;

      //遍历Set 查找权限
      for (let temp in permissArray) {
        if (permissArray[temp] == "notification:create") {//有发布通知
          this.createFlag = true;
          }
          if (permissArray[temp] == "notification:search") {//搜索
          this.searchFlag = true;
          }
          if (permissArray[temp] == "noticeType:create") {//添加通知类型
          this.createTypeFlag = true;
          }
        }
    });

    this.notificationService.getNoticeTypeList().then(result => {
      if (result.code == 0) {
        this.noticeTypeList = result.noticeTypeList;
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("通知一览", result.msg);
      }
    });

    this.getNoticeList();
  }

  getNoticeList(){
    //拉去当月的前一个月的第一天，后一个月的最后一天（三个月的）
    this.notificationService.getInitNotificationList("0", "", "", 0).then(result => {
      if (result.code == 0) {
        this.noticeList = result.noticeList;//已通过审核的通知列表
        this.acceptList = result.acceptList;//待审核通知列表

        //处理排序的序号显示
        for (var i = 0; i < this.noticeList.length; i++) {
          var info = new NoticeInfo();
          info = this.noticeList[i];
          if (info.notificationFile != null) {
            if (info.notificationFile.length > 0) {//有附件显示
              var infoNF = new NoticeFile();
              infoNF = info.notificationFile[0];//只显示一个文件名称
              info.appendixName = infoNF.fileName;
            }
          }
        }
        //处理排序的序号显示
        for (var i = 0; i < this.acceptList.length; i++) {
          var info = new NoticeInfo();
          info = this.acceptList[i];
          if (info.notificationFile != null) {
            if (info.notificationFile.length > 0) {//有附件显示
              var infoNF = new NoticeFile();
              infoNF = info.notificationFile[0];//只显示一个文件名称
              info.appendixName = infoNF.fileName;
            }
          }
        }

        this.startDateVal = result.startDate;
        this.endDateVal = result.endDate;
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("通知一览", result.msg);
      }
    });
  }

  //检索通知
  searchNoticeList(startDate: string, endDate: string): void {

    this.notificationService.getInitNotificationList(this.typeVal, startDate, endDate, 1).then(result => {
      if (result.code == 0) {
        this.noticeList = result.noticeList;//已通过审核的通知列表
        this.acceptList = result.acceptList;//待审核通知列表

        //处理排序的序号显示
        for (var i = 0; i < this.noticeList.length; i++) {
          var info = new NoticeInfo();
          info = this.noticeList[i];
          if (info.notificationFile != null) {
            if (info.notificationFile.length > 0) {//有附件显示
              var infoNF = new NoticeFile();
              infoNF = info.notificationFile[0];//只显示一个文件名称
              info.appendixName = infoNF.fileName;
            }
          }
        }
        //处理排序的序号显示
        for (var i = 0; i < this.acceptList.length; i++) {
          var info = new NoticeInfo();
          info = this.acceptList[i];
          if (info.notificationFile != null) {
            if (info.notificationFile.length > 0) {//有附件显示
              var infoNF = new NoticeFile();
              infoNF = info.notificationFile[0];//只显示一个文件名称
              info.appendixName = infoNF.fileName;
            }
          }
        }
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("通知一览", result.msg);
      }
    });
  }

  //编辑分类
  forwardNotificationType(): void {
    this.router.navigate(["notification/type"]);
  }
  //发布通知
  forwardNotificationPublish(): void {
    this.router.navigate(["notification/release"]);
  }

  //跳转到通知详情
  forwardNotificationDetail(notice: NoticeInfo): void {
    this.router.navigate(['notification/detail']);
    var noticeIdStr = String(notice.noticeId);
    var url = 'notification/detail/' + noticeIdStr;
    this.router.navigate([url]);
  }

  //审核按钮
  agreeNotification(notice: NoticeInfo): (void) {
    this.notificationService.updateReviewState(notice).then(result => {
      if (result.code == 0) {
        $.MsgAlert.AlertTure("通知一览", "审核成功");
        // this.ngOnInit();//重新刷一下页面
        this.getNoticeList();
      }
      else {
        $.MsgAlert.AlertTure("通知一览", "审核失败");
      }
    });
  }

  //删除已发布的通知
  deleteNotice(notice: NoticeInfo,index:string): (void){
    var that=this;
    $("#myModal").modal("show");
    $(".delSuccess").click(function()
    {
      $("#myModal").modal("hide");
      that.notificationService.deleteNotificationInfo(notice).then(result => {
        if(result.code == 0){//请求成功 
          that.noticeList.splice(parseInt(index),1);
        }else {
          // 请求失败
          $.MsgAlert.AlertTure("通知一览", result.msg);
        }
      })
    })
  }
  //删除为审核的通知
  deleteAcceptNotice(notice: NoticeInfo,index:string): (void){
    var that=this;
    $("#myModal").modal("show");
    $(".delSuccess").click(function()
    {
      $("#myModal").modal("hide");
      that.notificationService.deleteNotificationInfo(notice).then(result => {
        if(result.code == 0){//请求成功 
          that.acceptList.splice(parseInt(index),1);//成功后刷新当前页面
        }else {
          // 请求失败
          $.MsgAlert.AlertTure("通知一览", result.msg);
        }
      })
    })
  }
}
