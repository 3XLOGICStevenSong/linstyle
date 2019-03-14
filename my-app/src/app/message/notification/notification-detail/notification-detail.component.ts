import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../notification.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { NoticeFile} from '../notification.noticeFile';
import { NoticeInfo} from '../notification.noticeInfo';
import { PermissionsService } from 'app/common/permissions.service';

declare var $:any;

@Component({
  selector: 'notification-detail',
  templateUrl: './notification-detail.component.html',
  providers:[ NotificationService,PermissionsService ]
})

export class NotificationDetailComponent implements OnInit{

  // 接口返回参数  
  noticeDetail = new NoticeInfo();
  // nFile = new NoticeFile();
  nFileList:Array<NoticeFile>;
  noticeId: string ;
  permission:Set<String>
  modifyBtn:boolean;
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
      this.modifyBtn = false;
      //遍历Set 查找权限
      for (let temp in permissArray) {
        if (permissArray[temp] == "notification:update") {//有修改权限
          this.modifyBtn = true;
          break;
          }
        }
    });

    if (this.route.snapshot.paramMap.has('noticeId')) {//从搜索页面跳转过来的
      this.noticeId = this.route.snapshot.paramMap.get('noticeId');
    } else{
      return;
    }
    // 获取通知详情
    this.notificationService.getNotificationInfo(this.noticeId).then(result => {
      if(result.code == 0){   
        this.noticeDetail = result;
        this.noticeDetail.notificationFile = result.notificationFile;
        if(this.noticeDetail.notificationFile !=null){
          this.nFileList=this.noticeDetail.notificationFile;
        }
        // else{//没有附件，赋值为空
        //   this.nFile.fileUrl = "";
        //   this.nFile.fileName = "";
        // }
      }else{
        // 请求失败
        $.MsgAlert.AlertTure("通知详情", result.msg);
      }
    });
  }

  //跳转到通知详情
  forwardNotificationDetail():void{
      var noticeIdStr = String(this.noticeDetail.noticeId);
      var url = 'notification/release/' + noticeIdStr;
      this.router.navigate([url]);
    }
}
  
