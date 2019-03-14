import { Component, ViewChild, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RecruitInputService } from './recruit-input.service';
import { PositionList } from './recruit-input.positionList';
import { Interviewer } from '../recruit-detail/recruit-detail.interviewer';
import { Paper } from '../recruit-detail/recruit-detail.paper';
import { Filiter } from './recruit-input.filiter';
import { PaperListService } from '../../paper/paper-list.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { RecruitDetailService } from '../recruit-detail/recruit-detail.service';
import { Location } from '@angular/common';

declare var $: any;

@Component({
  selector: 'recruitlist-input',
  templateUrl: './recruit-input.component.html',
  providers: [RecruitInputService, PaperListService, RecruitDetailService]
})

export class RecruitInputComponent implements OnInit {

  // 角色
  positionList: Array<PositionList>;

  // 考卷首次输入
  paperFirst = true;

  // 面试官首次输入
  empFirst = true;

  // 筛选的语言
  lanVal = "全部";

  // 筛选的标题
  titleVal: string;

  // 通知内容
  contentVal: string;

  // 已选的考卷Info
  seledPpInfo: Paper;

  // 已选的面试官Info
  seledEmpInfo: Interviewer;

  // 考卷信息数组
  paperList: Array<Paper>;

  // 面试官信息数组
  employeeList: Array<Interviewer>;

  // 选中的考卷
  // seledPapers: Array<Paper>;
  seledPapers = new Array<Paper>();
  // 选中的面试官
  seledEmps: Array<Interviewer>;

  // 选择框 - 语言筛选
  lans = ['全部', '日语', '中文'];

  // 单选框 - 考卷 - 选中的考卷id
  pp_radio: number;

  // 单选框 - 面试官 - 选中的考卷id
  emp_radio: number;

  // 取出选择考卷按钮
  chosenBtn: any;

  // 取出选择面试官按钮
  chosenBtn_emp: any;

  // 招聘ID
  recruitId: number;

  // 搜索员工
  searchKey: string = "";

  // 面试官check
  empCheck: boolean = true;

  @ViewChild('recruitInputForm') recruitInputForm: NgForm;

  // 实例化RecruitListService
  constructor(private route: ActivatedRoute,
    private location: Location,
    private router: Router,
    private recruitInputService: RecruitInputService,
    private paperListService: PaperListService,
    private recruitDetailService: RecruitDetailService) { }

  // 页面初始化
  ngOnInit(): void {
    let that = this;

    let id = this.route.snapshot.paramMap.get('recruitId');
    let idd = Number(id);

    // 判断是录入还是更新 录入idd=0，更新idd!=0
    if (idd != 0) {
      that.getRecruitInfo(idd);
    }

    // 选择考卷
    $(".choseBtn").click(function () {
      let thatt = that;
      thatt.chosenBtn = this;
      if (that.paperFirst) {
        // 首次选择考卷，从后台拉取
        that.getPaperNetwork();
      } else {
        that.cancelSelPaper();
        // 显示选择考卷对话框
        $(".paperModal_checkbox").modal('show');
      }
    });

    // 选择面试官
    $(".choseBtn_emp").click(function () {
      let thatt = that;
      // 选择面试官按钮
      thatt.chosenBtn_emp = this;
      if (that.empFirst) {
        // 首次选择面试官，从后台拉取
        that.getEmpNetwork();
      } else {
        that.cancelSelEmp();
        // 显示选择面试官对话框
        $(".depModal_checkbox").modal('show');
      }
    });
  }

  // Network

  // 拉取要修改的该条招聘信息
  getRecruitInfo(recruitId: number): void {
    // 获取所有
    this.recruitDetailService.getAll(recruitId).then(result => {
      if (result.code == 0) {

        this.recruitId = result.recruitId;

        // 通过id判断中文和日语
        if (result.language == "0") {
          this.lanVal = "中文";
        } else if (result.language == "1") {
          this.lanVal = "日语";
        } else { }

        this.titleVal = result.title;
        this.contentVal = result.content;
        this.seledPapers = result.paperList;
        this.seledEmps = result.recruitEmployeeList;

        // 写入修改的考卷名称
        var paperNameAll = "";
        if (this.seledPapers && this.seledPapers.length > 0) {
          for (let j = 0; j < this.seledPapers.length; j++) {
            paperNameAll += this.seledPapers[j].paperName + "&nbsp&nbsp&nbsp&nbsp";
          }
          $("#paperback").children().eq(0).children(".col-3").find("span").remove();
          $("#paperback").children().eq(0).children(".col-3").append("<span>" + paperNameAll + "</span>");
        }

        // 写入修改的面试官名称
        var empNameAll = "";
        if (this.seledEmps && this.seledEmps.length > 0) {
          for (let j = 0; j < this.seledEmps.length; j++) {
            empNameAll += this.seledEmps[j].employeeName + "&nbsp&nbsp&nbsp&nbsp";
          }
          $("#empback").children().eq(0).children(".col-3").find("span").remove();
          $("#empback").children().eq(0).children(".col-3").append("<span>" + empNameAll + "</span>");
        }

        // 页面的title
        $("#pageTitle").text("招聘信息编辑");
        $(".submitBtn").text("完成");
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("招聘信息编辑", result.msg);
      }
    });
  }

  // 拉取考卷
  getPaperNetwork(): void {
    this.paperListService.getAllPapers("").then(result => {
      if (result.code == 0) {
        // 不再是首次选择考卷，显示【追加考卷】
        this.paperFirst = false;
        // 请求成功
        this.paperList = result.paperList;

        this.cancelSelPaper();

        // 显示选择考卷对话框
        $(".paperModal_checkbox").modal('show');
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("招聘信息编辑", result.msg);
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

        this.cancelSelEmp();
        // 显示选择面试官对话框
        $(".depModal_checkbox").modal('show');
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("招聘信息编辑", result.msg);
      }
    });
  }

  // 发布信息
  submitInfo(): void {
    var that = this;
    if (!this.recruitInputForm.valid && this.recruitInputForm.controls) {
      for (const name in this.recruitInputForm.controls) {
        if (this.recruitInputForm.controls.hasOwnProperty(name)) {
          this.recruitInputForm.controls[name].markAsDirty();
        }
      }
    }
    else {
      let sendLan: string = "";
      if (this.lanVal == "中文") {
        sendLan = "0";
      } else if (this.lanVal == "日语") {
        sendLan = "1";
      } else {
        sendLan = "2";
      }

      if ($("#pageTitle").text() == "招聘信息编辑") {
        this.recruitInputService.updataRecruitment(this.recruitId, sendLan, this.titleVal, this.contentVal, this.seledPapers, this.seledEmps).then(result => {
          if (result.code == 0) {
            // 请求成功
            var that = this;
            $.MsgAlert.AlertTure("招聘信息编辑", "编辑成功");
            setTimeout(function(){
              that.router.navigate(['personnel/recruit']);
            },2000);
          } else {
            // 请求失败
            $.MsgAlert.AlertTure("招聘信息编辑", result.msg);
          }
        });
      } else {
        // 上传考卷和关联考官
        this.recruitInputService.postRecruitment(sendLan, this.titleVal, this.contentVal, this.seledPapers, this.seledEmps).then(result => {
          if (result.code == 0) {
            // 请求成功
            var that = this;
            $.MsgAlert.AlertTure("招聘信息编辑", "发布成功");
            setTimeout(function(){
              that.router.navigate(['personnel/recruit']);
            },2000);
          } else {
            // 请求失败
            $.MsgAlert.AlertTure("招聘信息编辑", result.msg);
          }
        });
      }
    }
  }

  // COMMON

  // 取消选择考卷，取消选中状态
  cancelSelPaper():void{
    if (this.paperList && this.paperList.length > 0) {
      for (let i = 0; i < this.paperList.length; i++) {
        // 设置数据源，选中状态全部为未选中
        this.paperList[i].chose = false;
      }

      if (this.seledPapers && this.seledPapers.length > 0) {
        for (let j = 0; j < this.seledPapers.length; j++) {
          for (let k = 0; k < this.paperList.length; k++) {
            if (this.paperList[k].paperName == this.seledPapers[j].paperName) {
              this.paperList[k].chose = true;
            }
          }
        }
      }
    }
  }

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

  // BTN Action

  // 考卷提交按钮
  paperUploadBtn(): void {
    // 选中的放入 选中考卷数组
    this.seledPapers = [];
    if (this.paperList && this.paperList.length > 0) {
      for (let j = 0; j < this.paperList.length; j++) {
        // 存入选择的考卷
        if (this.paperList[j].chose) {
          this.seledPapers.push(this.paperList[j]);
        }
      }
    }
    $("#paperback").children().eq(0).children(".col-3").find("span").remove();
    // 写入添加的考卷名称
    var paperNameAll = "";
    if (this.seledPapers && this.seledPapers.length > 0) {
      for (let j = 0; j < this.seledPapers.length; j++) {
        paperNameAll += this.seledPapers[j].paperName + "&nbsp&nbsp&nbsp&nbsp";
      }
      $("#paperback").children().eq(0).children(".col-3").append("<span>" + paperNameAll + "</span>");
    }
    // 隐藏考卷列表
    $(".paperModal_checkbox").modal('hide');
  }

  // 面试官提交按钮
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
    $("#empback").children().eq(0).children(".col-3").find("span").remove();
    // 写入添加的考卷名称
    var empAll: string = "";
    if (this.seledEmps && this.seledEmps.length > 0) {
      this.empCheck = true;
      for (let j = 0; j < this.seledEmps.length; j++) {
        empAll += this.seledEmps[j].name + "&nbsp&nbsp&nbsp&nbsp";
      }
      $("#empback").children().eq(0).children(".col-3").append("<span>" + empAll + "</span>");
    } else {
      this.empCheck = false;
      $("#empback").children().eq(0).children(".col-3").append("<span class='validate'>*</span>");
    }
    // 隐藏面试官列表
    $(".depModal_checkbox").modal('hide');
  }
  // 搜索面试官按钮
  empSearch(): void {
    this.getEmpNetwork();
  }
}
