import { Component, ViewChild, OnInit } from '@angular/core';
import { AgreementCommonService } from '../agreement-common.service';
import { EmployeeInfoService } from 'app/common/employee-info.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AgreementType } from '../agreementType-list/agreementType-list.type';
import { AgreementDetail } from 'app/hr/agreement/agreement.agreementDetail';
import { AddAgreement } from 'app/hr/agreement/agreement.addAgreement';
import { AgreeEmployeeInfo } from '../AgreeEmployee-info';
import { element } from 'protractor';
import { inflate } from 'zlib';
import { NgForm } from '@angular/forms';
import { BsDatepickerConfig, BsLocaleService } from 'ngx-bootstrap/datepicker';
import { listLocales } from 'ngx-bootstrap/bs-moment';
import { defineLocale } from 'ngx-bootstrap/bs-moment';
import { zhCn } from 'ngx-bootstrap/locale';
import { NgModel } from '@angular/forms/src/directives/ng_model';
import { tr } from 'ngx-bootstrap/bs-moment/i18n/tr';
import { PermissionsService } from 'app/common/permissions.service';

declare var $: any;

@Component({
  selector: 'agreement-add',
  templateUrl: './agreement-add.component.html',
  providers: [AgreementCommonService, EmployeeInfoService, BsLocaleService]
})

export class AgreementAddComponent implements OnInit {
  // 接口返回参数  
  employeeName: string;
  typeList: Array<AgreementType>;
  detail: AgreementDetail;
  employeeList: Array<AgreeEmployeeInfo>;
  agreementList: Array<AgreementDetail>;
  aiPeriodVal: string;
  cAgreementTotalVal: string;
  typeVal: AgreementType;
  array: string[];
  signDate: string;
  goDate: string;
  backDate: string;
  startDate: string;
  endDate: string;

  flag: string;
  midFlag: string;
  list: any;

  midSelectEmployee: AgreeEmployeeInfo;//赴日协议人员选择
  midSelectOtherEmployeeList: Array<AgreeEmployeeInfo>;

  maxDate: Date;
  minDate: Date;

  //日历相关
  locale = 'zh-cn';
  colorTheme = 'theme-dark-blue';
  format: 'yyyy-mm-dd'
  bsConfig: Partial<BsDatepickerConfig>;

  @ViewChild('agreeAddForm') agreeAddForm: NgForm;
  // 实例化RecruitListService
  constructor(private route: ActivatedRoute,
    private router: Router,
    private agreementCommonService: AgreementCommonService,
    private employeeInfoService: EmployeeInfoService,
    private _localeService: BsLocaleService) { 
    //设置日历信息
    defineLocale('zh-cn', zhCn);
    this._localeService.use(this.locale);
    this.bsConfig = Object.assign({ containerClass: this.colorTheme }, { locale: this.locale }, { format: this.format });
    //设置日历信息结束
    this.minDate = new Date();
  }
  
  // 页面初始化
  ngOnInit(): void {

    this.employeeList = new Array<AgreeEmployeeInfo>();
    this.midSelectEmployee = new AgreeEmployeeInfo();
    this.midSelectOtherEmployeeList = new Array<AgreeEmployeeInfo>();

    $(".addUserList").hide();
    $(".abroadName").val("");
    $(".abroadHistory").hide();
    $(".empty").hide();

    this.agreementCommonService.getProtocolNames().then(result => {
      if (result.code == 0) {
        this.typeList = result.protocolList;
        this.selectAgreementType(this.typeList[0].aiId);
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("获取公司在职员工列表：", result.msg);
      }
    });
  }

  selectAgreementType(typeId: string) {
    for (var i in this.typeList) {
      if (this.typeList[i].aiId == typeId) {
        this.typeVal = this.typeList[i];
        this.aiPeriodVal = this.typeVal.aiPeriod;
        this.cAgreementTotalVal = this.typeVal.aiTotal;
        break;
      }
    }
    if (this.typeVal.aiType == "0") {//赴日
      //其他协议显示数据重置
      this.midSelectOtherEmployeeList.length = 0;
      this.list = "";
      $(".abroad").show();
      $(".abroadSubmit .btn-success").show();
      $(".abroadSubmit .btn-primary").hide();
      $(".other").hide();
      $(".addUserList").hide();
    } else {//其他
      this.goDate = "2017/01/02";
      this.backDate = "2017/01/02";
      //赴日显示数据重置
      this.midSelectEmployee = null;
      this.flag = "-1";
      this.employeeName = null;
      $(".abroad").hide();
      $(".abroadSubmit .btn-success").hide();
      $(".abroadSubmit .btn-primary").show();
      $(".other").show();
      $(".abroadHistory").hide();
      $("#remaind").hide();
    }
  }
  /***************赴日协议    start******************** */
  //显示赴日协议，单选
  agrAbroadModalShow(): void {
    $("#agrAbroadModal").modal("show");
    if (this.employeeList.length == 0) {//获取数据
      this.employeeInfoService.getEmployeeInfoList("").then(result => {
        if (result.code == 0) {
          this.employeeList = result.employeeList;
          $("#agrAbroadModal").modal("show");
          return;
        } else {
          $.MsgAlert.AlertTure("获取公司在职员工列表：", result.msg);
        }
      });
    }
    else {
      $("#agrAbroadModal").modal("show");
    }
  }
  //查询赴日协议人员（单选）
  secarchEmployee(search: string): void {
    this.employeeInfoService.getEmployeeInfoList(search).then(result => {
      if (result.code == 0) {
        this.employeeList = result.employeeList;
        $("#agrAbroadModal").modal("show");
      } else {
        $.MsgAlert.AlertTure("搜索公司在职员：", result.msg);
      }
    });
  }

  //确认添加赴日协议人员未结束协议列表（单选）
  confirmJPEmployee(): void {
    this.midFlag = this.flag;
    for (var j in this.employeeList) {
      if (this.flag == this.employeeList[j].employeeId) {
        this.employeeName = this.employeeList[j].cardNo + this.employeeList[j].name;
        this.midSelectEmployee = this.employeeList[j];
        break;
      }
    }
    var that = this;
    //查询员工协议列表
    this.agreementCommonService.getNotFinishedAgreDetail(this.midSelectEmployee.cardNo).then(result => {
      $("#agrAbroadModal").modal("hide");
      if (result.code == 0) {//有参加的协议
        that.agreementList = result.japanProList;
        for (var i = 0; i < result.otherProList.length; i++) {
          that.agreementList.push(result.otherProList[i]);
        }
        if (that.agreementList.length > 0) {
          $(".abroadHistory").show();
          $("#remaind").hide();
        }
      } else if (result.code == 1) {//没有参加协议
        $(".abroadHistory").hide();
        $("#remaind").show();
      } else {
        $.MsgAlert.AlertTure("搜索公司在职员：", result.msg);
      }
    });
  }

  onCloseJP(): void {
    this.flag = this.midFlag;
  }
  /***************赴日协议    end************************/
  /***************其他协议    start**********************/
  //显示其他协议，多选
  agreementModalShow(): void {
    if (this.employeeList.length == 0) {//获取数据
      this.employeeInfoService.getEmployeeInfoList("").then(result => {
        if (result.code == 0) {
          this.employeeList = result.employeeList;
          for (var i in this.employeeList) {//处理显示
            this.employeeList[i].status = false;//默认未选择
          }
          $("#agreementModal").modal("show");
          return;
        } else {
          $.MsgAlert.AlertTure("获取公司在职员工列表：", result.msg);
        }
      });
    } else {

      for (var k in this.employeeList) {//处理显示
        this.employeeList[k].status = false;//默认未选择
      }
      for (var i in this.employeeList) {//处理显示
        for (var j in this.midSelectOtherEmployeeList) {
          if (this.employeeList[i].employeeId == this.midSelectOtherEmployeeList[j].employeeId) {
            this.employeeList[i].status = true;
          }
        }
      }
      $("#agreementModal").modal("show");
    }
  }

  //查询其他协议人员（多选）
  secarchEmployeeForOther(search: string): void {
    this.employeeInfoService.getEmployeeInfoList(search).then(result => {
      if (result.code == 0) {
        this.employeeList = result.employeeList;
        $("#agreementModal").modal("show");
      } else {
        $.MsgAlert.AlertTure("搜索公司在职员：", result.msg);
      }
    });
  }

  //确认其他协议人员（多选）
  confirmEmployeeForOther(): void {
    this.list = "";
    this.midSelectOtherEmployeeList.length=0;
    
    for (var j in this.employeeList) {
      if (this.employeeList[j].status) {
        this.midSelectOtherEmployeeList.push(this.employeeList[j]);
      }
    }

    if (this.midSelectOtherEmployeeList.length == 0) {
      $("#agreementModal").modal("hide");
      $(".addUserList").hide();
      return;
    };

    for (var i = 0; i < this.midSelectOtherEmployeeList.length; i++) {
      var name = this.midSelectOtherEmployeeList[i].name;
      var code = this.midSelectOtherEmployeeList[i].cardNo;
      this.list += "<li><p date='" + i + "' class='userName'>" + name + "</p><p class='usercode'>(<span>" + code + "</span>)</p><span class='del_icon'>X</span></li>";
    }

    $("#agreementModal").modal("hide");
    $(".addUserList").show();
    $(".addUserList .total").find("span").text(this.midSelectOtherEmployeeList.length);
    $(".addUserList .users").html("");
    $(".addUserList .users").html(this.list);

    var that = this;
    $(document).delegate(".del_icon", "click", function () {
      var j = $(this).parents("li").find(".userName").attr("date");
      that.midSelectOtherEmployeeList.splice(parseInt(j), 1);
      $(this).parents("li").remove();
      $(".addUserList .total").find("span").text(that.midSelectOtherEmployeeList.length);
    })
  }

  /***************其他协议    end******************** */
  //提交协议报名人员
  commitAgreement(subsidy: string): void {
    if (!this.agreeAddForm.valid && this.agreeAddForm.controls) {
      for (const name in this.agreeAddForm.controls) {
        if (this.agreeAddForm.controls.hasOwnProperty(name)) {
          this.agreeAddForm.controls[name].markAsDirty();
        }
      }
    } else {
      var that = this;
      var info = new AddAgreement();
      info.aiId = Number(this.typeVal.aiId);
      info.aiType = this.typeVal.aiType;
      info.aiName = this.typeVal.aiName;
      info.aiPeriod = this.typeVal.aiPeriod;
      info.signDate = this.signDate;

      this.array = new Array;

      if (this.typeVal.aiType == "0") {//赴日
        info.goAbroadDate = this.goDate;
        info.backAbroadDate = this.backDate;
        info.subTotal = subsidy;//补助金额
        if (this.midSelectEmployee == null) {
          $.MsgAlert.AlertTure("添加协议报名人员：", "报名人员未选择");
          return;
        };
        this.array.push(this.midSelectEmployee.employeeId + "|" + this.midSelectEmployee.cardNo + "|" + this.midSelectEmployee.name);
      }
      else {//其他
        info.cAgreementTotal = this.typeVal.aiTotal;//协议金额
        if (this.midSelectOtherEmployeeList.length == 0) {
          $.MsgAlert.AlertTure("添加协议报名人员：", "报名人员未选择");
          return;
        };
        for (var i in this.midSelectOtherEmployeeList) {
          this.array.push(this.midSelectOtherEmployeeList[i].employeeId + "|" + this.midSelectOtherEmployeeList[i].cardNo + "|" + this.midSelectOtherEmployeeList[i].name);
        }
      }

      info.startDate = this.startDate;
      info.endDate = this.endDate;
      info.employeeList = this.array;

      this.agreementCommonService.insertAgreementRegisterInfo(info).then(result => {
        if (result.code == 0) {
          $.MsgAlert.AlertTure("添加协议人员：", result.msg);
          if (that.typeVal.aiType == "0") {//赴日----跳转到协议详情
            var url = 'personnel/agreement/info/' + that.midSelectEmployee.cardNo;
            this.router.navigate([url]);
          } else {//其他---跳转到协议查询
            var url = 'personnel/agreement/employee';
            this.router.navigate([url]);
          }
        } else {
          $.MsgAlert.AlertTure("添加协议人员：", result.msg);
        }
      });
    }
  }
}
