import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { ResumeService } from './resume-list.service';
import { ResumeSearchInfo } from './resume-list-search';
import { ResumeInfo } from './resume-list.resumeInfo';
declare var $: any;

@Component({
  selector: 'resume-list',
  templateUrl: './resume-list.component.html',
  providers: [ResumeService]
})

export class ResumeListComponent implements OnInit {

  resumeSearchInfo = new ResumeSearchInfo("", "", "", "", 1, 10);
  // 简历列表数据源
  resumeList = new Array<ResumeInfo>();
  // 部门下拉列表 
  depList = [];
  depVal = "";
  // 在职状态下拉列表
  statusList = [];
  statusVal = "";

  // 实例化RecruitListService
  constructor(
    private route: ActivatedRoute,
    private resumeService: ResumeService,
    private router: Router) { }

  // 页面初始化
  ngOnInit(): void {
    // 获取初始化页面
    this.getInitView();
  }

  // Network

  // 获取初始化页面
  getInitView(): void {
    this.resumeService.getInitInfo().then(result => {
      if (result.code == 0) {
        this.resumeList = result.list;
        if (result.workStatusList && result.workStatusList.length > 0) {
          for (var i in result.workStatusList) {
            this.statusList.push(result.workStatusList[i].typeName);
          }
        }
        if (result.departList && result.departList.length > 0) {
          for (var i in result.departList) {
            this.depList.push(result.departList[i].depName);
          }
        }
        console.log(this.statusList , this.depList);
      } else {
        alert(result.msg);
      }
    });
  }

  // 搜索
  getSearchAll(): void {
    this.resumeService.getAll(this.resumeSearchInfo).then(result => {
      if (result.code == 0) {
        this.resumeList = result.resumeList;
      } else {
        alert(result.msg);
      }
    });
  }

  // BTN ACTION

  // 高级搜索按钮
  search(): void {
    this.router.navigate(['personnel/resume/search']);
  }
}
