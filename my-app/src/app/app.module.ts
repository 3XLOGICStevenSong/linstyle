// 系统组件
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { CommonModule } from '@angular/common';
import { FileUploadModule } from 'ng2-file-upload';
import { BsDatepickerModule } from 'ngx-bootstrap';


// 共通自定义组件
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app/app.component';
import { HomeComponent } from './home/home.component';
import { HeadComponent } from './head/head.component';
import { MenuComponent } from './menu/menu.component';
import { RootComponent } from './root/root.component';
import { RootService } from './root/root.service';
// 自定义组件
//import { ActivityComponent } from './activity/activity.component';
import { RootRoutingModule } from './root/root-routing.module';

import { PasswordComponent } from './setting/password/password.component';
import { SettingTimeComponent } from './message/setting-time/setting-time.component';
import { StationMessageComponent } from './message/station-message/station-message.component';

import { RecruitListComponent } from './hr/recruit/recruit-list/recruit-list.component';
import { RecruitInputComponent } from './hr/recruit/recruit-input/recruit-input.component';
import { RecruitDetailComponent } from './hr/recruit/recruit-detail/recruit-detail.component';
import { RecruitUpdataComponent } from './hr/recruit/recruit-updata/recruit-updata.component';
import { InterviewListComponent } from './hr/interview/interview-list/interview-list.component';
import { InterviewDetailComponent } from './hr/interview/interview-detail/interview-detail.component';
import { NoticeTypeComponent } from './notice/notice-type/notice-type.component'; 
import { PaperListComponent } from './hr/paper/paper-list.component'; 
import { ResumeListComponent } from './hr/resume/resume-list/resume-list.component';
import { ResumeJpComponent } from './hr/resume/resume-jp/resume-jp.component';
import { ResumeSearchComponent } from './hr/resume/resume-search/resume-search.component';
//部门管理
import { DepartmentComponent } from './hr/department/department.component';

// 权限管理
import { RoleComponent } from './setting/role/role.component';
import { RoleInfoComponent } from './setting/info/info.component';
import { RoleConfigComponent } from './setting/info/config.component';

//协议管理				
import { AgreementTypeListComponent } from './hr/agreement/agreementType-list/agreementType-list.component';
import { AgreementDetailComponent } from './hr/agreement/agreement-detail/agreement-detail.component';
import { AgreementSearchComponent } from './hr/agreement/agreement-search/agreement-search.component';
import { AgreementAddComponent } from './hr/agreement/agreement-add/agreement-add.component';

//公司通知
import { NotificationListComponent } from './message/notification/notification-list/notification-list.component';
import { NotificationPublishComponent } from './message/notification/notification-publish/notification-publish.component';
import { NotificationSetTypeComponent } from './message/notification/notification-setType/notification-setType.component';
import { NotificationDetailComponent } from './message/notification/notification-detail/notification-detail.component';
//员工状态管理
import { EmployeeStatusComponent } from './hr/status/employee-status/status.component';
import { EmployeeRegisterComponent } from './hr/status/employee-register/register.component';
import { JobStatusComponent } from './hr/status/job-status/job-status.component';
import { StatusUpdateComponent } from './hr/status/employee-status/status-update.component';
//公司活动
import { ActivityComponent } from './activity/activity-list/activity.component';
import { ActivityPublishComponent } from './activity/activity-publish/activity-publish.component';
import { ActivityTypeComponent } from './activity/activity-type/activity-type.component';
import { ActivityDetailComponent } from './activity/activity-detail/activity-detail.component';
import { ActivitySignComponent } from './activity/activity-sign/activity-sign.component';
import { ActivityEditComponent } from './activity/activity-edit/activity-edit.component';

@NgModule({
  declarations: [
    //根组件
    AppComponent,
    //首页
    HomeComponent,
    //头部
    HeadComponent,
    //菜单
    MenuComponent,
    //Root
    RootComponent,
    //活动相关组件
    ActivityComponent,
    ActivityPublishComponent,
    ActivityTypeComponent,
    ActivityDetailComponent,
    ActivitySignComponent,
    ActivityEditComponent,
    //密码
    PasswordComponent,
    //站内时间设置
    SettingTimeComponent,
    //站内消息
    StationMessageComponent,
    
    RecruitListComponent,
    RecruitInputComponent,
    InterviewListComponent,
    InterviewDetailComponent,
    NoticeTypeComponent,
    RecruitDetailComponent,
    RoleComponent,
    RoleInfoComponent,
    RoleConfigComponent,
    ResumeListComponent,
    PaperListComponent,
    ResumeJpComponent,
    ResumeSearchComponent,
    //协议管理
    AgreementTypeListComponent,
    AgreementDetailComponent,
    AgreementSearchComponent,
    AgreementAddComponent,
    //公司通知
    NotificationListComponent,
    NotificationPublishComponent,
    NotificationSetTypeComponent,
    NotificationDetailComponent,
    //员工状态管理
    EmployeeStatusComponent,
    EmployeeRegisterComponent,
    JobStatusComponent,
    StatusUpdateComponent,
    //部门管理
    DepartmentComponent


  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    CommonModule,
    FileUploadModule,
    HttpModule,
    RootRoutingModule,
    BsDatepickerModule.forRoot()
  ],
  providers: [{ provide: LocationStrategy, useClass: HashLocationStrategy }, RootService],
  bootstrap: [AppComponent]
})

export class AppModule { }

