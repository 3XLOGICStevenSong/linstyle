import { Component, OnInit } from '@angular/core';
import { RecruitListService } from './recruit-list.service';
import { PositionList } from './recruit-list.positionList';
import { Filiter } from './recruit-list.filiter';
import { Router } from '@angular/router';
// 日历
import { BsDatepickerConfig, BsLocaleService } from 'ngx-bootstrap/datepicker';
import { listLocales } from 'ngx-bootstrap/bs-moment';
import { defineLocale } from 'ngx-bootstrap/bs-moment';
import { zhCn } from 'ngx-bootstrap/locale';

declare var $: any;

@Component({
  selector: 'recruitlist-root',
  templateUrl: './recruit-list.component.html',
  styleUrls: [
    './recruit-list.component.css',
    '../../../../assets/css/file.css',
    '../../../../assets/css/mystyle.css',
    '../../../../assets/css/nav.css'],
  providers: [RecruitListService, BsLocaleService]
})

export class RecruitListComponent implements OnInit {
  //日历相关
  locale = 'zh-cn';
  colorTheme = 'theme-dark-blue';
  format: 'yyyy-mm-dd'
  bsConfig: Partial<BsDatepickerConfig>;
  // 角色
  positionList: Array<PositionList>;
  // 筛选的语言
  lanVal = "全部";
  // 筛选的标题
  titleVal: string;
  // 选择框 - 语言筛选
  lans = ['全部', '日语', '中文'];
  // 选择时间
  selDate: Date[];

  startDateVal: Date;

  endDateVal: Date;

  maxDate: Date;

  minDate: Date;

  selTime: Date[];

  delRecruitId: number = 0;

  // 实例化RecruitListService
  constructor(private recruitListService: RecruitListService, private router: Router, private _localeService: BsLocaleService) {
    //设置日历信息
    defineLocale('zh-cn', zhCn);
    this._localeService.use(this.locale);
    this.bsConfig = Object.assign({ containerClass: this.colorTheme }, { locale: this.locale }, { format: this.format });
    //设置日历信息结束
    this.minDate = new Date();
  }

  // 页面初始化
  ngOnInit(): void {
    this.getAllClear("", "");
  }

  // NETWOERK

  // 获取所有1
  getAllClear(lan: string, title: string): void {
    if (lan == "中文") {
      this.lanVal = "0";
      lan = "0";
    } else if (lan == "日语") {
      this.lanVal = "1";
      lan = "1";
    } else {
      lan = "";
      this.lanVal = "";
    }

    if (title == null || title.length == 0) {
      this.titleVal = "";
      title = "";
    }
    // 获取所有
    this.recruitListService.getAllClear(lan, title).then(result => {
      if (result.code == 0) {
        // 请求成功
        this.positionList = result.positionList;

        this.showAfterNet();
      } else {

      }
    });
  }

  // 获取所有2
  getAll(): void {
    if (this.lanVal == "中文") {
      this.lanVal = "0"
    } else if (this.lanVal == "日语") {
      this.lanVal = "1";
    } else {
      this.lanVal = "";
    }

    if (this.titleVal == null || this.titleVal.length == 0) {
      this.titleVal = "";
    }
    // 网络请求
    this.recruitListService.getAll(this.lanVal, this.titleVal, this.selTime[0], this.selTime[1]).then(result => {
      if (result.code == 0) {
        // 请求成功
        this.positionList = result.positionList;

        this.showAfterNet();

      } else {

      }
    });
  }

  //  UI
  showAfterNet(): void {
    for (var i = 0; i < this.positionList.length; i++) {
      var languageVal = this.positionList[i].language;
      if (languageVal == "0") {
        this.positionList[i].language = "中文";
      } else if (languageVal == "1") {
        this.positionList[i].language = "日语";
      } else {
        this.positionList[i].language = "全部";
      }
    }

    if (this.lanVal == "0") {
      this.lanVal = "中文"
    } else if (this.lanVal == "1") {
      this.lanVal = "日语";
    } else {
      this.lanVal = "全部";
    }
  }

  // BTN

  // 删除提示框确定按钮
  deleteTr(): void {
    var that = this;
    this.recruitListService.delone(this.delRecruitId).then(result => {
      if (result.code == 0) {
        // 数据源删掉
        for (var i = 0; i < that.positionList.length; i++) {
          if (that.positionList[i].recruitId == that.delRecruitId) {
            that.positionList.splice(i, 1);
          }
        }
        $.MsgAlert.AlertTure("删除招聘职位", "删除成功");
        $("#myModal").modal('hide');
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("删除招聘职位", result.msg);
      }
    });
  }

  // 发布招聘信息的按钮的点击事件
  recruitInput(): void {
    this.router.navigate(['personnel/recruitInput']);
  }

  // 检索按钮的点击事件
  searchOnClicked() {
    // 搜索所有
    if (this.selTime == null || this.selTime[0] == null || this.selTime[1] == null) {
      this.getAllClear(this.lanVal, this.titleVal);
    } else {
      this.getAll();
    }
  }

  // 跳到详情页面
  goto(recruitId: number): void {
    this.router.navigate(['personnel/recruitDetail', recruitId]);
  }

  // 跳到编辑页面
  onEdit(recruitId: number): void {
    this.router.navigate(['personnel/recruitEdit', recruitId]);
  }

  // 删除
  onDel(recruitId: number): void {
    // 确定删除提示框
    $("#myModal").modal('show');
    this.delRecruitId = recruitId;
  }
}
