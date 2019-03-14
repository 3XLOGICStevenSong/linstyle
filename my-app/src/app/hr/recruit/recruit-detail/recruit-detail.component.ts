import { Component, OnInit } from '@angular/core';
import { RecruitDetailService } from './recruit-detail.service';
import { Paper } from './recruit-detail.paper';
import { Interviewer } from './recruit-detail.interviewer';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

declare var $: any;

@Component({
  selector: 'recruitlist-detail',
  templateUrl: './recruit-detail.component.html',
  styleUrls: [
    './recruit-detail.component.css',
    '../../../../assets/css/file.css',
    '../../../../assets/css/mystyle.css',
    '../../../../assets/css/nav.css'],
  providers: [RecruitDetailService]
})

export class RecruitDetailComponent implements OnInit {

  // 接口返回参数  
  //   1.positionId(int):招聘职位ID
  recruitId: number;
  //   2.languageId(int):语种 (中文：0，日文：1)
  language: string;
  //   3.title(string):标题
  title: string;
  //   4.content(string):发布内容
  content: string;
  //   5.paperList:
  //   [
  //    1.paperId(int)：考卷Id  
  //    2.paperName(string)：考卷名称
  //    3.paperUrl(string):考卷下载链接
  //   ]
  paperList: Array<Paper>;
  //   6.interviewerList:
  //   [
  //    1.userId(int):用户Id 
  //    2.userName（string）：姓名
  //   ]
  interviewerList: Array<Interviewer>;

  // 实例化RecruitListService
  constructor(private route: ActivatedRoute,
    private router: Router,
    private recruitDetailService: RecruitDetailService) { }

  // 编辑
  recruitEdit():void {
    this.router.navigate(['personnel/recruitEdit',this.recruitId]);
  }

  // 页面初始化
  ngOnInit(): void {

    let id = this.route.snapshot.paramMap.get('recruitId');
    let idd = Number(id);
    // 获取所有
    this.recruitDetailService.getAll(idd).then(result => {
      if (result.code == 0) {

        this.recruitId = result.recruitId;

        // 通过id判断中文和日语
        if (result.language == "0") {
          this.language = "中文";
        } else if (result.language == "1") {
          this.language = "日语";
        } else { 
          this.language = "全部";
        }

        this.title = result.title;
        this.content = result.content;
        this.paperList = result.paperList;
        this.interviewerList = result.recruitEmployeeList;

      } else {
        // 请求失败
        alert(result.code + result.msg);
      }
    });
  }
}
