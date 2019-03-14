import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { InterviewListService } from './interview-list.service';
import { InterviewList } from './interview-list.interviewList';
import { RecruitListService } from '../../recruit/recruit-list/recruit-list.service';
declare var $: any;
@Component({
  selector: 'interviewlist-root',
  templateUrl: './interview-list.component.html',
  // styleUrls: [
  // 		'../../../../assets/css/file.css',
  // 		'../../../../assets/css/mystyle.css',
  // 		'../../../../assets/css/nav.css'],
  providers: [InterviewListService, RecruitListService]
})

export class InterviewListComponent implements OnInit {

  // ************************************ Init Start ************************************

  // 面试记录
  interviewList: Array<InterviewList>;
  // 面试职位选择框
  titleVal: string = "全部";

  title: string = "全部";

  titles = ['全部'];
  // 面试结果选择框
  interResultVal: string = "全部";

  interResult: string = "全部";

  interResults = ['全部', '等待面试', '通过', '淘汰'];
  // 面试者姓名输入框
  interviewerInput: string = "";
  // 面试官姓名输入框
  employeeInput: string = "";

  //总页数
  count: number;

  pageList: number[];
  //页码数
  pageNum: number;
  //当前选中的页码
  selectNum: number = 1;

  dateFormat(dateParam: Date): any {
    var ayear = dateParam.getFullYear();
    var amonth = (dateParam.getMonth() + 1) < 10 ? "0" + (dateParam.getMonth() + 1) : (dateParam.getMonth() + 1);
    var aday = dateParam.getDate() < 10 ? "0" + dateParam.getDate() : dateParam.getDate()
    return ayear + "-" + amonth + "-" + aday;
  }

  // 实例化RecruitListService
  constructor(private interviewListService: InterviewListService,
    private recruitListService: RecruitListService,
    private route: ActivatedRoute,
    private router: Router) { }

  // ************************************ Init Over ************************************
  // ************************************ Life Circle Start ************************************  

  // 页面初始化
  ngOnInit(): void {
    this.getAllRecruitInfo();
    // 获取所有
    this.getAllInterviews(0, 10);
  }

  // ************************************ Life Circle Over ************************************  
  // ************************************ Network Start ************************************

  // 获取所有面试记录
  getAllInterviews(pageNum: number, pageSize: number): void {
    var title: string = "";
    var result: string = "";
    title = this.convertToTitleAdapt();
    result = this.convertToResultNum();
    this.interviewListService.getAll(title, result, this.interviewerInput, this.employeeInput, pageNum, pageSize).then(result => {
      if (result.code == 0) {
        // 请求成功
        this.interviewList = result.recruitInterviewerClientModelList;
        for (var i = 0; i < this.interviewList.length; i++) {
          if (this.interviewList[i].interResult == "0") {
            this.interviewList[i].interResult = "淘汰";
          } else if (this.interviewList[i].interResult == "1") {
            this.interviewList[i].interResult = "通过";
          } else if (!this.interviewList[i].interResult || this.interviewList[i].interResult == "") {
            this.interviewList[i].interResult = "等待面试";
          }
        }
        this.count = result.count;
        this.pageList = [];
        for (var i = 1; i <= this.count; i++) {
          this.pageList.push(i);
        }
      } else {
        // 请求失败
        alert(result.code + result.msg);
      }
    });
  }

  // 获取所有招聘信息
  getAllRecruitInfo() {
    // 获取所有
    this.recruitListService.getAllClear("", "").then(result => {
      if (result.code == 0) {
        // 请求成功
        for (var i = 0; i < result.positionList.length; i++) {
          this.titles.push(result.positionList[i].title)
        }
      } else {

      }
    });
  }
  // ************************************ Network Over ************************************
  // ************************************ Action Start ************************************

  // 录入面试记录
  launchPaperInput(): void {
    this.router.navigate(['personnel/interviewerDetail', ""]);
  }

  // 跳入详情页面
  goto(interId: number): void {
    this.router.navigate(['personnel/interviewerDetail', interId]);
  }

  // 跳入职位详情
  goRecruitDetail(recruitId: number): void {
    this.router.navigate(['personnel/recruitDetail', recruitId]);
  }

  // 选择筛选职位
  changeTitle(choseTitle: string): void {
    this.titleVal = choseTitle;
  }

  // 下载简历
  onDown(resumeUrl: string) {
    window.open(resumeUrl);
  }

  // 检索按钮点击事件
  searchBtnOnClicked(): void {
    this.getAllInterviews(0, 10);
  }

  // *************** 分页相关 Start ***************
  prePage(): void {
    if ((this.selectNum - 1) <= 1) {
      this.selectNum = 1;
    } else {
      this.selectNum = this.selectNum - 1;
    }
    var pageSelect = 0;
    pageSelect = (this.selectNum - 1) * 10;
    // 网络请求数据
    this.getAllInterviews(pageSelect, 10);

  }
  nextPage(): void {
    if ((this.selectNum + 1) >= this.count) {
      this.selectNum = this.count;
    } else {
      this.selectNum = this.selectNum + 1;
    }
    var pageSelect = 0;
    pageSelect = (this.selectNum - 1) * 10;

    // 网络请求数据
    this.getAllInterviews(pageSelect, 10);

  }
  //点击页码数
  pageSearch(count): void {
    var nextPage = 0;
    this.selectNum = count;
    nextPage = (count - 1) * 10;
    // 网络请求数据
    this.getAllInterviews(nextPage, 10);
  }
  //跳转按钮
  pageForward(): void {
    if (this.pageNum > this.count) {
      document.getElementById("comPass").style.display = "block";
    } else {
      document.getElementById("comPass").style.display = "none";

      var pageSelect = 0;
      this.selectNum = this.pageNum;
      pageSelect = (this.pageNum - 1) * 10;

      // 网络请求数据
      this.getAllInterviews(pageSelect, 10);
    }
  }
  // *************** 分页相关 Over ***************

  // ************************************ Action Over ************************************
  // ************************************ Common Start ************************************

  // 标题转换 
  convertToTitleAdapt(): any {
    var t = "";
    if (this.titleVal != "全部") {
      t = this.titleVal;
    }
    return t;
  }

  // 面试结果选择框 （转换：汉字 -> 序号）
  convertToResultNum(): any {
    var interR = "";
    if (this.interResultVal == "全部") {
      interR = "2";
    } else if (this.interResultVal == "通过") {
      interR = "1";
    } else if (this.interResultVal == "淘汰") {
      interR = "0";
    } else if (this.interResultVal == "等待面试") {
      interR = "";
    }
    return interR;
  }

  //页码不合理
  pageNumError(): void {
    if (this.pageNum > this.count) {
      document.getElementById("comPass").style.display = "block";
    } else {
      document.getElementById("comPass").style.display = "none";
    }
  }

  // ************************************ Common Over ************************************
}
