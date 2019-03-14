import { Component, Input, OnChanges, ViewChild, OnInit, SimpleChanges } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { InterviewDetailService } from './interview-detail.service';
import { InterviewDetail } from './interview-detail.interviewDetail';
import { RecruitListService } from '../../recruit/recruit-list/recruit-list.service';
import { PaperListService } from '../../paper/paper-list.service';
import { NgForm } from '@angular/forms';
import { Paper } from '../../recruit/recruit-detail/recruit-detail.paper';
import { RecruitDetailService } from '../../recruit/recruit-detail/recruit-detail.service';
import { ScoreInfo } from './interview-detail.scoreInfo';
import { RecruitEmployee } from './interview-detail.recruitEmployee';
import { Interviewer } from '../../recruit/recruit-detail/recruit-detail.interviewer';
import { PositionList } from '../../recruit/recruit-list/recruit-list.positionList';
import { HeadService } from '../../../head/head.service';
import { RecruitInputService } from '../../recruit/recruit-input/recruit-input.service';
// 上传文件
import { FormGroup } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FileUploader } from 'ng2-file-upload';
// 日历
import { BsDatepickerConfig, BsLocaleService } from 'ngx-bootstrap/datepicker';
import { listLocales } from 'ngx-bootstrap/bs-moment';
import { defineLocale } from 'ngx-bootstrap/bs-moment';
import { zhCn } from 'ngx-bootstrap/locale';

declare var $: any;
@Component({
  selector: 'interviewDetail-root',
  templateUrl: './interview-detail.component.html',
  styleUrls: [
    './interview-detail.component.css'],
  providers: [InterviewDetailService, RecruitListService, PaperListService, BsLocaleService, RecruitDetailService, RecruitInputService, HeadService]
})

export class InterviewDetailComponent implements OnInit {

  //日历相关
  locale = 'zh-cn';
  colorTheme = 'theme-dark-blue';
  format: 'yyyy-mm-dd'
  bsConfig: Partial<BsDatepickerConfig>;
  //日历相关结束

  // 面试id
  // interId: number = null;
  // 面试详情Model
  interDetailModel = new InterviewDetail(
    0, null, "", "", 0,
    "", "", "", "", null,
    "", "", "0", "", "0",
    0, "", "", "", "1",
    "", "", "", "", "0",
    "", "", "", "1", "0",
    "", "", 0, [], []);
  // 职位列表
  positionList: Array<PositionList>;
  // 当前用户的员工号
  selfCardNo: string = "";
  // 上传的文件名称
  fileName: string = "";
  // 搜索所有员工关键字
  searchKey: string = "";
  // 是否首次拉取
  empFirst: boolean = true;
  // 面试官信息数组
  employeeList: Array<Interviewer>;
  // 选中的面试官
  seledEmps: Array<Interviewer>;

  @ViewChild('interviewDetailForm') interviewDetailForm: NgForm;
  // 实例化RecruitListService
  constructor(private route: ActivatedRoute,
    private interviewDetailService: InterviewDetailService,
    private recruitListService: RecruitListService,
    private recruitDetailService: RecruitDetailService,
    private paperListService: PaperListService,
    private _localeService: BsLocaleService,
    private headService: HeadService,
    private recruitInputService: RecruitInputService,
    private router: Router) {
    //设置日历信息
    defineLocale('zh-cn', zhCn);
    this._localeService.use(this.locale);
    this.bsConfig = Object.assign({ containerClass: this.colorTheme }, { locale: this.locale }, { format: this.format });
  }

  // 页面初始化
  ngOnInit(): void {
    // 获取到详情id
    let id = this.route.snapshot.paramMap.get('interId');
    let idd = Number(id);
    // this.interId = idd;
    this.interDetailModel.interId = idd;
    if (idd == 0) {
      // 录入
      this.inputUI();
      // 设置默认recruitId = 1；
    } else {
      // 详情
      this.detailUI();
      // 获取到详情
      this.getDetail();
    }
    this.commonUI();
    // 获取所有招聘信息
    this.getAllRecruitInfo();
    // 获取当前用户的卡号
    this.getCurrentCardNo();
  }

  // Network

  // 获取当前用户的卡号
  getCurrentCardNo(): void {
    this.headService.getCurrentUser().then(user => {
      this.selfCardNo = user.loginCode;
    });
  }

  // 获取面试详情
  getDetail(): void {
    this.interviewDetailService.getDetail(this.interDetailModel.interId).then(result => {
      if (result.code == 0) {
        // 请求成功
        this.interDetailModel = result.interviewerDetail;
        this.fileName = result.interviewerDetail.resumeName;
        // 标题
        $("#pageTitle").text(this.interDetailModel.interName + " - " + this.interDetailModel.title);
        this.unique(this.interDetailModel.recruitEmployees);
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("面试记录", "获取面试记录失败");
      }
    });
  }

  // 获取所有招聘信息
  getAllRecruitInfo() {
    // 获取所有
    this.recruitListService.getAllClear("", "").then(result => {
      if (result.code == 0) {
        // 请求成功
        this.positionList = result.positionList;
        this.setRecruitId();
      }
    });
  }

  // 获取职位对应的考卷和面试官
  getPaperAndEmp(): void {
    this.recruitDetailService.getAll(this.interDetailModel.recruitId).then(result => {
      if (result.code == 0) {
        if (result.paperList && result.paperList.length > 0) {
          for (var i in result.paperList) {
            this.interDetailModel.scoreInfos[i] = new ScoreInfo(0, "");
            this.interDetailModel.scoreInfos[i].paperName = result.paperList[i].paperName;
          }
        } else {
          this.interDetailModel.scoreInfos = [];
        }
        if (result.recruitEmployeeList && result.recruitEmployeeList.length > 0) {
          for (var j in result.recruitEmployeeList) {
            this.interDetailModel.recruitEmployees[j] = new RecruitEmployee("", "", "");
            this.interDetailModel.recruitEmployees[j].interviewerName = result.recruitEmployeeList[j].employeeName;
            this.interDetailModel.recruitEmployees[j].cardNo = result.recruitEmployeeList[j].cardNo;
          }
        } else {
          this.interDetailModel.recruitEmployees = [];
        }
      }
    });
  }

  // 拉取面试官
  getEmpNetwork(): void {
    this.recruitInputService.getAllTeas(this.searchKey).then(result => {
      if (result.code == 0) {
        // 不再是首次选择面试官，显示【追加面试官】
        this.empFirst = false;
        // 请求成功
        this.employeeList = result.employeeList;
        this.seledEmps = [];
        this.cancelSelEmp();
        // 显示选择面试官对话框
        $(".depModal_checkbox").modal('show');
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("招聘信息编辑", result.msg);
      }
    });
  }

  // 提交面试记录
  uploadInter(): void {
    this.interviewDetailService.postInterInfo(this.interDetailModel).then(result => {
      if (result.code == 0) {
        // 请求成功
        $.MsgAlert.AlertTure("提交面试记录", "提交成功");
        // 当录入一条新面试记录，会返回面试记录id。更新操作不返回
        if (result.interviewerDetail != null && result.interviewerDetail.interId != null) {
          this.interDetailModel.interId = result.interviewerDetail.interId;
        }
        // 保存按钮的点击事件
        this.saveBtnAction();
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("提交面试记录", result.msg);
      }
    });
  }

  // 提交面试结果
  uploadResult(): void {
    this.interviewDetailService.putInterResult(this.interDetailModel).then(result => {
      if (result.code == 0) {
        // 请求成功
        $.MsgAlert.AlertTure("提交面试结果", "提交成功");
        // 保存按钮的点击事件
        this.saveBtnAction();
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("提交面试结果", result.msg);
      }
    });
  }

  // UI

  // 录入状态下
  inputUI(): void {
    $(".personDetail").hide();
    $(".personPrompt").hide();
    $(".updateBtn").hide();
    // 标题
    $("#pageTitle").text("录入面试记录");
  }
  // 详情状态下
  detailUI(): void {
    $(".personInput").hide();
    $(".personPrompt").hide();
  }
  // 共通
  commonUI(): void {
    $(".updateBtn").click(function () {
      $(this).hide();
      $(this).parents(".info").find(".personPrompt").hide();
      $(this).parents(".info").find(".personDetail").hide();
      $(this).parents(".info").find(".personInput").show();
      $(".updateBtn").attr("disabled", "disabled");
    });

    $(".cancelBtn").click(function () {
      $(this).parents(".info").find(".personDetail").show();
      // $(this).parents(".info").find(".personPrompt").show();
      $(this).parents(".info").find(".updateBtn").show();
      $(this).parents(".info").find(".personInput").hide();
      $(".updateBtn").removeAttr("disabled");
    });
  }

  // 录入后，保存和取消按钮
  saveBtnAction(): void {
    $(".saveBtn").parents(".info").find(".personDetail").show();
    $(".saveBtn").parents(".info").find(".updateBtn").show();
    $(".saveBtn").parents(".info").find(".personInput").hide();
    $(".updateBtn").removeAttr("disabled");
  }

  // 考卷得分与面试官评价
  addScoreAndEva(): void {
    $("#paperAdd").html("");
    for (var i = 0; i < this.interDetailModel.scoreInfos.length; i++) {
      var scoreInfo: string;
      var pName;
      var pScore;
      if (this.interDetailModel.scoreInfos[i].paperName == null || this.interDetailModel.scoreInfos[i].paperName == "") {
        pName = "考卷未记入";
      } else {
        pName = this.interDetailModel.scoreInfos[i].paperName;
      }
      if (this.interDetailModel.scoreInfos[i].score == null) {
        pScore = "0";
      } else {
        pScore = this.interDetailModel.scoreInfos[i].score;
      }
      scoreInfo = "<div class='space clearfix'><div class='col-12'><font class='p_text'>考卷得分：</font><font id='interPaper'>" + pName + "     " + pScore + "分</font></div></div>"
      $("#paperAdd").append(scoreInfo);
    }

    $("#interAdd").html("");
    for (var j = 0; j < this.interDetailModel.recruitEmployees.length; j++) {
      var evaInfo: string;
      var eName;
      var eContent;
      if (this.interDetailModel.recruitEmployees[j].employeeName == null || this.interDetailModel.recruitEmployees[j].employeeName == "") {
        eName = "未记入";
      } else {
        eName = this.interDetailModel.recruitEmployees[j].employeeName;
      }
      if (this.interDetailModel.recruitEmployees[j].content == null || this.interDetailModel.recruitEmployees[j].content == "") {
        eContent = "未记入";
      } else {
        eContent = this.interDetailModel.recruitEmployees[j].content;
      }
      evaInfo = "<div class='space clearfix'><div class='col-12'><font class='p_text'>面试官：</font><font id='interInterviewer'>" + eName + "</font></div><div class='col-12'><font class='p_text'>面试评价：</font><font id='interCommend'>" + eContent + "</font></div></div>"
      $("#interAdd").append(evaInfo);
    }
  }

  // common

  // 取消选择面试官，取消选中状态
  cancelSelEmp(): void {
    if (this.employeeList && this.employeeList.length > 0) {
      for (let j = 0; j < this.employeeList.length; j++) {
        this.employeeList[j].chose = false;
        this.employeeList[j].employeeName = this.employeeList[j].name;
      }

      if (this.seledEmps && this.seledEmps.length > 0) {
        for (let j = 0; j < this.seledEmps.length; j++) {
          for (let k = 0; k < this.employeeList.length; k++) {
            if (this.employeeList[k].employeeName == this.seledEmps[j].employeeName) {
              this.employeeList[k].chose = true;
            }
          }
        }
      }
    }
  }

  // 数据处理
  unique(array) {
    if (array != null && array[0] != null) {
      var n = [array[0]]; //结果数组 
      //从第二项开始遍历 
      for (var i = 1; i < array.length; i++) {
        //如果当前数组的第i项在当前数组中第一次出现的位置不是i， 
        //那么表示第i项是重复的，忽略掉。否则存入结果数组 
        if (array.indexOf(array[i]) == i) n.push(array[i]);
      }
      this.interDetailModel.recruitEmployees = n;
    }
  }

  // 更新面试信息
  actionUpdateInterInfo(): void {
    if (!this.interviewDetailForm.valid && this.interviewDetailForm.controls) {
      for (const name in this.interviewDetailForm.controls) {
        if (this.interviewDetailForm.controls.hasOwnProperty(name)) {
          this.interviewDetailForm.controls[name].markAsDirty();
        }
      }
    }
    else {
      for (var i = 0; i < this.positionList.length; i++) {
        if (this.positionList[i].title == this.interDetailModel.title) {
          this.interDetailModel.recruitId = this.positionList[i].recruitId;
        }
      }
      // 提交
      this.uploadInter();
    }
  }

  // 设置职位显示
  showPos(): void {
    for (var i in this.positionList) {
      if (this.interDetailModel.recruitId == this.positionList[i].recruitId) {
        this.interDetailModel.title = this.positionList[i].title;
        break;
      }
    }
  }

  // 如果是录入，则默认为最后一条招聘Id
  setRecruitId(): void {
    if (this.interDetailModel.interId == 0) {
      this.interDetailModel.recruitId = this.positionList[0].recruitId;
      // 获取考卷和面试官
      this.getPaperAndEmp();
      // 设置职位显示
      this.showPos();
    }
  }

  // btn action

  // 更改招聘职位下拉框
  changeRecruit(): void {
    // 设置职位显示
    this.showPos();
    // 重新拉取所选职位对应的考卷和面试官
    this.getPaperAndEmp();
  }

  // 更新面试信息按钮（领导经验，对日经验，协力）
  saveBtn_1(): void {
    this.actionUpdateInterInfo();
  }

  // 提交面试结果
  saveBtn_4(): void {
    // 取出面试考卷得分
    var psAry = [];
    for (var i = 0; i < $("#pScoreDiv").children().length; i++) {
      psAry.push($("#pScoreDiv").children().eq(i).children().eq(1).find("input").val());
    }
    for (var j = 0; j < this.interDetailModel.scoreInfos.length; j++) {
      this.interDetailModel.scoreInfos[j].score = parseInt(psAry[j]);
    }
    // 取出面试官评价
    var empAry = [];
    for (var i = 0; i < $("#eContentDiv").children().length; i++) {
      empAry.push($("#eContentDiv").children().eq(i).children().eq(1).find("input").val());
    }
    for (var j = 0; j < this.interDetailModel.recruitEmployees.length; j++) {
      this.interDetailModel.recruitEmployees[j].content = empAry[j];
    }

    // 网络请求
    this.uploadResult();
  }

  // 搜索推荐人
  selRec(): void {
    if (this.empFirst) {
      // 首次选择面试官，从后台拉取
      this.getEmpNetwork();
    } else {
      this.cancelSelEmp();
      // 显示选择面试官对话框
      $(".depModal_checkbox").modal('show');
    }
  }

  // 搜索面试官按钮
  empSearch(): void {
    this.getEmpNetwork();
  }

  // 选好推荐人后提交按钮
  empUploadBtn(): void {
    // 选中的放入选中面试官数组
    this.seledEmps = [];
    if (this.employeeList && this.employeeList.length > 0) {
      for (let j = 0; j < this.employeeList.length; j++) {
        if (this.employeeList[j].chose) {
          this.seledEmps.push(this.employeeList[j]);
        }
      }
    }
    this.interDetailModel.recommendName = "";
    var empAll: string = "";
    if (this.seledEmps && this.seledEmps.length > 0) {
      for (let j = 0; j < this.seledEmps.length; j++) {
        empAll += this.seledEmps[j].name + ",";
      }
      this.interDetailModel.recommendName = empAll.substring(0,empAll.length-1);
    }
    // 隐藏面试官列表
    $(".depModal_checkbox").modal('hide');
  }

  // 点击取消按钮
  cancelBtn(): void {
    if (this.interDetailModel.interId == 0) {
      this.interDetailModel = new InterviewDetail(
        0, null, "", "", 0,
        "", "", "", "", null,
        "", "", "0", "", "0",
        0, "", "", "", "1",
        "", "", "", "", "0",
        "", "", "", "1", "0",
        "", "", 0, [], []);
    } else {
      // 获取到详情
      this.getDetail();
    }
    this.saveBtnAction();
    this.setRecruitId();
  }

  // 下载简历
  onDown(resumeUrl: string) {
    window.open(resumeUrl);
  }

  // 更改单选框状态
  sexChange1(): void {
    this.interDetailModel.interSex = '0'
  }

  sexChange2(): void {
    this.interDetailModel.interSex = '1'
  }

  stateChange1(): void {
    this.interDetailModel.condition = '0'
  }

  stateChange2(): void {
    this.interDetailModel.condition = '1'
  }

  teamChange1(): void {
    this.interDetailModel.teamFlag = '1'
  }

  teamChange2(): void {
    this.interDetailModel.teamFlag = '0'
  }

  jpChange1(): void {
    this.interDetailModel.acceptJapan = '1'
  }

  jpChange2(): void {
    this.interDetailModel.acceptJapan = '0'
  }

  accChange1(): void {
    this.interDetailModel.assistanceFlag = '1'
  }

  accChange2(): void {
    this.interDetailModel.assistanceFlag = '0'
  }

  resultChange1(): void {
    this.interDetailModel.interResult = '1'
  }

  resultChange2(): void {
    this.interDetailModel.interResult = '0'
  }

  // 上传简历
  public uploader: FileUploader = new FileUploader({
    url: "~resource/fileUpload",
    method: "POST",
    itemAlias: "file",
    // additionalParameter: this.paper
  });

  refreshInput(): void {
    this.uploader.clearQueue();
  }

  selectChange(): void {
    var files = this.uploader.queue;
    var file_Name = "";
    if (files && files.length > 0) {
      file_Name = files[files.length - 1].file.name;
      this.fileName = file_Name;
    }
    console.log("queue = " + files + "this.fileName " + this.fileName);
  }
  // C: 面试个人基本信息上传
  selectedFileOnChanged(event: any) {
    try {
      if (this.uploader.queue && this.uploader.queue.length > 0) {
        this.uploadFile();
      } else {
        this.actionUpdateInterInfo();
      }
    } catch (err) {
    }
  }
  // D: 定义事件，上传文件
  uploadFile() {
    // 上传
    var that = this;
    this.uploader.queue[this.uploader.queue.length - 1].onSuccess = function (response, status, headers) {
      // 上传文件成功
      if (status == 200) {
        //上传文件后获取服务器返回的数据
        let result = JSON.parse(response);
        // 上传成功后，会返回所保存的简历名称和简历保存路径
        // 若含有. 则去掉
        if (that.fileName.indexOf(".") != -1) {
          that.interDetailModel.resumeName = that.fileName.split(".", 1)[0];
        } else {
          that.interDetailModel.resumeName = that.fileName;
        }
        that.interDetailModel.resumeUrl = result.msg;
        that.actionUpdateInterInfo();
      } else {
        // 上传文件后获取服务器返回的数据错误
        $.MsgAlert.AlertTure("面试记录", "上传简历失败");
      }
    };
    this.uploader.uploadAll(); // 开始上传
  }
}
