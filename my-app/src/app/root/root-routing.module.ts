import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RootComponent } from './root.component';
import { RootService } from './root.service';

//公司活动
import { ActivityComponent } from '../activity/activity-list/activity.component';
import { ActivityPublishComponent } from '../activity/activity-publish/activity-publish.component';
import { ActivityTypeComponent } from '../activity/activity-type/activity-type.component';
import { ActivityDetailComponent } from '../activity/activity-detail/activity-detail.component';
import { ActivitySignComponent } from '../activity/activity-sign/activity-sign.component';
import { ActivityEditComponent } from '../activity/activity-edit/activity-edit.component';
//修改密码
import { PasswordComponent } from '../setting/password/password.component';

// 人事管理
import { RecruitListComponent } from '../hr/recruit/recruit-list/recruit-list.component';
import { RecruitDetailComponent } from '../hr/recruit/recruit-detail/recruit-detail.component';
import { RecruitInputComponent } from '../hr/recruit/recruit-input/recruit-input.component';
import { InterviewListComponent } from '../hr/interview/interview-list/interview-list.component';
import { InterviewDetailComponent } from '../hr/interview/interview-detail/interview-detail.component';
import { PaperListComponent } from '../hr/paper/paper-list.component';
import { ResumeListComponent } from '../hr/resume/resume-list/resume-list.component';
import { ResumeJpComponent } from '../hr/resume/resume-jp/resume-jp.component';
import { ResumeSearchComponent } from '../hr/resume/resume-search/resume-search.component';

// 权限管理
import { RoleComponent } from '../setting/role/role.component';
import { RoleInfoComponent } from '../setting/info/info.component';
import { RoleConfigComponent } from '../setting/info/config.component';

//协议管理				
import { AgreementTypeListComponent } from '../hr/agreement/agreementType-list/agreementType-list.component';
import { AgreementDetailComponent } from '../hr/agreement/agreement-detail/agreement-detail.component';
import { AgreementSearchComponent } from '../hr/agreement/agreement-search/agreement-search.component';
import { AgreementAddComponent } from '../hr/agreement/agreement-add/agreement-add.component';

//公司通知
import { NotificationListComponent } from '../message/notification/notification-list/notification-list.component';
import { NotificationPublishComponent } from '../message/notification/notification-publish/notification-publish.component';
import { NotificationSetTypeComponent } from '../message/notification/notification-setType/notification-setType.component';
import { NotificationDetailComponent } from '../message/notification/notification-detail/notification-detail.component';
//站内消息
import { StationMessageComponent } from '../message/station-message/station-message.component';
import { SettingTimeComponent } from '../message/setting-time/setting-time.component';
//员工状态管理
import { EmployeeStatusComponent } from '../hr/status/employee-status/status.component';
import { EmployeeRegisterComponent } from '../hr/status/employee-register/register.component';
import { JobStatusComponent } from '../hr/status/job-status/job-status.component';
import { StatusUpdateComponent } from '../hr/status/employee-status/status-update.component';
//部门管理
import { DepartmentComponent } from '../hr/department/department.component';
// 上传文件
// import { Upload_1Component } from '../upload_1/upload_1.component';	

const rootRoutes: Routes = [
  {
    path: '',
    component: RootComponent,
    children: [
      //员工信息管理
      { path: 'personnel/employee', component: ActivityComponent },
      //员工状态管理
      { path: 'personnel/employee/state', component: EmployeeStatusComponent },
      { path: 'personnel/employee/state/register', component: EmployeeRegisterComponent },
      { path: 'personnel/employee/state/job', component: JobStatusComponent },
      { path: 'personnel/employee/state/update/:cardNo', component: StatusUpdateComponent },

      //部门信息管理
      { path: 'personnel/department', component: DepartmentComponent },
      //我的个人信息
      { path: 'personnel/employee/info', component: ActivityComponent },
      //员工/协力简历一览
      { path: 'personnel/resume', component: ResumeListComponent },
      //我的日文简历
      { path: 'personnel/resume/info', component: ResumeJpComponent },
      //高级搜索
      { path: 'personnel/resume/search', component: ResumeSearchComponent },
      //招聘职位一览
      { path: 'personnel/recruit', component: RecruitListComponent },
      //招聘信息录入
      { path: 'personnel/recruitInput', component: RecruitInputComponent },
      //招聘信息详情
      { path: 'personnel/recruitDetail/:recruitId', component: RecruitDetailComponent },
      //招聘信息修改
      { path: 'personnel/recruitEdit/:recruitId', component: RecruitInputComponent },
      //面试记录一览
      { path: 'personnel/interviewer', component: InterviewListComponent },
      //面试记录详情
      { path: 'personnel/interviewerDetail/:interId', component: InterviewDetailComponent },
      //考卷一览
      { path: 'personnel/paper', component: PaperListComponent },

      //协议人员查询
      { path: 'personnel/agreement/employee', component: AgreementSearchComponent },
      //协议一览
      { path: 'personnel/agreement', component: AgreementTypeListComponent },
      //我的协议
      { path: 'personnel/agreement/info', component: AgreementDetailComponent },
      { path: 'personnel/agreement/info/:cardNo', component: AgreementDetailComponent },
      //协议报名
      { path: 'personnel/agreement/add', component: AgreementAddComponent },

      //通知一览
      { path: 'notification/info', component: NotificationListComponent },
      //发布通知
      { path: 'notification/release', component: NotificationPublishComponent },
      //编辑通知
      { path: 'notification/release/:noticeId', component: NotificationPublishComponent },
      //通知类型设置
      { path: 'notification/type', component: NotificationSetTypeComponent },
      //通知详情
      { path: 'notification/detail', component: NotificationDetailComponent },
      { path: 'notification/detail/:noticeId', component: NotificationDetailComponent },


      //活动一览
      { path: 'activity/info', component: ActivityComponent },
      { path: 'activity/detail/:activeId', component: ActivityDetailComponent },
      { path: 'activity/sign/:activeId', component: ActivitySignComponent },
      { path: 'activity/edit/:activeId', component: ActivityEditComponent },

      //发布活动
      { path: 'activity/release', component: ActivityPublishComponent },

      //活动类型设置
      { path: 'activity/type', component: ActivityTypeComponent },

      //授予角色
      { path: 'setting/role/config', component: RoleConfigComponent },
      //角色一览
      { path: 'setting/role/info', component: RoleInfoComponent },

      //角色添加
      { path: 'setting/role', component: RoleComponent },
      { path: 'setting/role/:roleId', component: RoleComponent },

      //修改密码
      { path: 'setting/password', component: PasswordComponent },

      //站内消息一览
      { path: 'message/info', component: StationMessageComponent },

      //消息时间设置
      { path: 'message/setting', component: SettingTimeComponent },
      //

      // 上传文件Demo
      // { path: 'message/setting', component: Upload_1Component }

    ]
  },
];
// }
//];

@NgModule({
  imports: [
    RouterModule.forChild(rootRoutes)
  ],
  exports: [
    RouterModule
  ],
  providers: [
    RootService
  ]
})
export class RootRoutingModule { }