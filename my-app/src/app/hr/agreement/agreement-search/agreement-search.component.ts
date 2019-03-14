import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AgreementCommonService } from '../agreement-common.service';
import { AgreementType } from '../agreementType-list/agreementType-list.type';
import { AgreementDetail } from '../agreement.agreementDetail';
import { PermissionsService } from 'app/common/permissions.service';
declare var $: any;

@Component({
  selector: 'agreement-search',
  templateUrl: './agreement-search.component.html',
  providers: [AgreementCommonService,PermissionsService]
})

export class AgreementSearchComponent implements OnInit {

  // 实例化RecruitListServicez
  constructor(private route: ActivatedRoute,
    private router: Router,
    private agreementCommonService: AgreementCommonService,
    private permissionsService: PermissionsService) { }

  /*接口返回的参数*/
  typeList: Array<AgreementType>;     // 协议类型列表
  protocolList: Array<AgreementDetail>;   //协议数组
  permission: Set<String>;

  typeVal = "";
  statusVal = "";  //页面默认值
  account = 0;
  searchFlag: boolean;
  signUPFlag: boolean;
  // 页面初始化
  ngOnInit(): void {
    //权限信息
    this.permissionsService.getPermissionList().then(
      res => {
      this.permission = res;
      let permissArray = this.permission;
      this.searchFlag = false;
      this.signUPFlag = false;

      //遍历Set 查找权限
      for (let temp in permissArray) {
        if (permissArray[temp] == "agreementSign:search") {//搜索
          this.searchFlag = true;
          }
          if (permissArray[temp] == "agreementSign:create") {//报名
          this.signUPFlag = true;
          }
        }
    });
      
    this.agreementCommonService.getProtocolNames().then(result => {
      if (result.code == 0) {
        this.typeList = result.protocolList;
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("查询协议", result.msg);
      }
    });
    this.typeVal="0";     //0：未结束 （默认值）
    this.statusVal="0";   //0：全部 （默认值）
    this.searchResult();
  }

  //查询协议人员列表
  searchResult(): void {

    // 获取所有
    this.agreementCommonService.getRegAgreementList(this.typeVal, this.statusVal).then(result => {

      if (result.code == 0) {
        this.protocolList = result.personnels;
        this.account = this.protocolList.length;
        for (let j = 0; j < this.protocolList.length; j++) {
          if (this.protocolList[j].status == "0") {
            this.protocolList[j].status = "未结束";
          } else {
            this.protocolList[j].status = "已结束";
          }
        }
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("查询协议：", result.msg);
      }
    });
  }

  //页面跳转
  forwardAgreementAdd() {
    this.router.navigate(['personnel/agreement/add']);
  }

  //跳转到个人详情页面
  forwardAgreementDetail(detail: AgreementDetail) {
    var url = 'personnel/agreement/info/' + detail.codeNo;
    this.router.navigate([url]);
  }
}
