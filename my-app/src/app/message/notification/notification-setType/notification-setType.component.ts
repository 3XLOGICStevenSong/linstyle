import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../notification.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { NoticeType } from '../notice.type';
declare var $: any;

//通知一览保存//类型保存（通知和活动)
function saveSelfAgreeTr(nowTr) {
  console.log("saveSelfAgreeTr");
}

@Component({
  selector: 'notification-setType',
  templateUrl: './notification-setType.component.html',
  providers: [NotificationService]
})

export class NotificationSetTypeComponent implements OnInit {

  typeNameVal = ""; //通知类型名称
  noticeType: NoticeType; //通知类型对象

  // 接口返回参数  
  noticeTypeList: Array<NoticeType>;
  // 实例化RecruitListService
  constructor(private route: ActivatedRoute,
    private router: Router,
    private notificationService: NotificationService) { }

  // 页面初始化
  ngOnInit(): void {
    // 获取可用的通知类型
    this.getUsedNoticeType();
  }

  // 获取可用的通知类型
  getUsedNoticeType(){
    this.notificationService.getNoticeTypeList().then(result => {
      if (result.code == 0) {
        this.noticeTypeList = result.noticeTypeList;
      } else {
        // 请求失败
        alert(result.code + result.msg);
      }
    });
  }

  // 追加按钮  操作 
  addNoticeType(bNoticeTypeList: Array<NoticeType>){
    var newType = new NoticeType();
    newType.typeId = "";
    newType.typeName = "";
    bNoticeTypeList.push(newType);
  }
  //更新通知类型
  updateNoticeType(type: NoticeType,nameTxt:string) {
    var that = this;
    if(type.typeId != ""){//更新
      type.status = "1";//使用
      type.typeName = nameTxt;
      this.notificationService.updateNoticeType(type).then(result => {
        if (result.code == 0) {
          $.MsgAlert.AlertTure("通知一览更新", "更新成功");
        } else {
          // 请求失败
          $.MsgAlert.AlertTure("通知一览更新", "通知类型更新失败");
          $.MsgAlert.AlertTure("", result.msg);
          this.getUsedNoticeType();
        }
      });
    }else{//添加通知类型
      type.typeName = nameTxt;
      this.notificationService.insertNoticeType(type).then(result => {
        if (result.code == 0) {
          $.MsgAlert.AlertTure("通知一览", "保存成功");
          that.getUsedNoticeType();
        } else {
          // 请求失败
          $.MsgAlert.AlertTure("", result.msg);
          this.getUsedNoticeType();
        }
      });
    }
  }

  //删除通知类型
 deleteNoticeType(index:number,type: NoticeType,nameTxt:string,event){
  var thats = $(event.target);
  var that=this;
  $("#myModal").modal("show");
  $(".delSuccess").click(function()
    {
        $("#myModal").modal("hide");
        if(type.typeId!=""){
            type.status = "0";//删除
            that.notificationService.updateNoticeType(type).then(result => {
            if(result.code == 0){//请求成功 
              // that.ngOnInit();
              // //成功后将从数组中删除该元素
              // for(var i in that.noticeTypeList){
              //   if(that.noticeTypeList[i].typeId == type.typeId){
             that.noticeTypeList.splice(index,1);
              //     }
              // }
            }else {
              // 请求失败
              $.MsgAlert.AlertTure("通知类型设置", result.msg);
            }
          })
        }else{
          $(thats).parents("td").remove();
          //成功后将从数组中删除该元素
          for(var i in that.noticeTypeList){
            if(that.noticeTypeList[i].typeName == type.typeName){
              that.noticeTypeList.splice(parseInt(i),1);
              }
          }
        }
    })
  } 
}
