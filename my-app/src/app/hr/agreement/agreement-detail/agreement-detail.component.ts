import { Component, OnInit } from '@angular/core';
import { AgreementCommonService } from '../agreement-common.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AgreementDetail } from '../agreement.agreementDetail';
import { inflate } from 'zlib';
import { PermissionsService } from 'app/common/permissions.service';

declare var $: any;

@Component({
  selector: 'agreement-detail',
  templateUrl: './agreement-detail.component.html',
  providers: [AgreementCommonService,PermissionsService]
})

export class AgreementDetailComponent implements OnInit {

  // 接口返回参数  
  codeNo: string;
  employeeName: string;
  leaveDate: string;                      //结束日期(yyyy-MM-hh);
  aDetail: AgreementDetail;
  japanProList: Array<AgreementDetail>;   // 赴日协议详情数组
  otherProList: Array<AgreementDetail>;   // 其他协议数组
  
  reward :number =0;                      //奖金（人民币）
  payment :number = 0;                    //缴费（人民币）
  rewardStr:string;
  paymentStr:string;

  jobStatus :string ;                     //状态情况
  flag:number = 0;                        //编辑按钮，保存按钮，取消按钮点击次数的状态
  permission: Set<String>;
  userRole:boolean;                       //ture:高级用户,false普通用户

  // 实例化RecruitListService
  constructor(private route: ActivatedRoute,
    private router: Router,
    private agreementDetailService: AgreementCommonService,
    private permissionsService: PermissionsService) { }


  // 页面初始化
  ngOnInit(): void {
    if (this.route.snapshot.paramMap.has('cardNo')) {//从搜索页面跳转过来的(角色：高级)
      this.codeNo = this.route.snapshot.paramMap.get('cardNo');
    } else {//直接点击---我的协议（角色：高级，普通用户）
      this.codeNo = 'owner';
    }
    this.getPersonelAgreementDetail(this.codeNo);
  }

  getPersonelAgreementDetail(searchKey: string): void{
    var that = this;
    this.reward  = 0;                      //奖金（人民币）
    this.payment = 0;     
    // 获取所有默认用员工号拉取数据
    this.agreementDetailService.getPerAgreDetail(searchKey).then(result => {
      
      if (result.code == 0) {
        this.employeeName = result.employeeName;
        this.codeNo = result.codeNo;
        this.leaveDate = result.leaveDate;
        this.japanProList = result.japanProList;
        this.otherProList = result.otherProList;

        that.getRole();//获取登录者的权限

        for(var i=0;i<this.japanProList.length;i++){
          this.reward += Number(this.japanProList[i].cRewardTotal);
          this.payment += Number(this.japanProList[i].cPaymentTotal);

          var payType = this.japanProList[i].paymentType;

          if(payType == "0")
          {
            this.japanProList[i].paymentType = "半年折算";
          } 
          else if(payType == "1")
          {
            this.japanProList[i].paymentType = "一年折算";
          }
          else 
          {
            this.japanProList[i].paymentType = "到期核算";
          }
        }
        for(var i=0;i<this.otherProList.length;i++){
          this.payment += Number(this.otherProList[i].cPaymentTotal);
          var payType = this.otherProList[i].paymentType;

          if(payType == "0")
          {
            this.otherProList[i].paymentType = "半年折算";
          } 
          else if(payType == "1")
          {
            this.otherProList[i].paymentType = "一年折算";
          } 
          else 
          {
            this.otherProList[i].paymentType = "到期核算";
          }
        }
        if(result.jobStatus==0){
          this.jobStatus = "在职";
        }
        else if(result.jobStatus==1){
          this.jobStatus = "出差";
        }

        this.rewardStr = this.fmoney(String(this.reward),1);
        this.paymentStr = this.fmoney(String(this.payment),1);

      } else if(result.code==1) {
        // 请求失败
        $.MsgAlert.AlertTure("查询协议详情：", result.msg);
        this.japanProList.length=0;
        this.otherProList.length=0;
        this.payment=0;
        this.reward=0;
      } else{
        $.MsgAlert.AlertTure("查询协议详情：", result.msg);
      }
    });
  }

  //获取权限
  getRole(){
    var that = this;
    //权限信息
    this.permissionsService.getPermissionList().then(
      res => {
      this.permission = res;
      let permissArray = this.permission;
      this.userRole = false;//普通用户
      //遍历Set 查找权限
      for (let temp in permissArray) {
        if (permissArray[temp] == "agreementInfo:search") {//角色：高级用户
          this.userRole = true;
          break;
          }
        }

      if(!that.userRole){//普通用户
        $(".EditArea").hide();
      }else{//高级用户
        if(that.leaveDate == null || that.leaveDate=="" ){//协议未点击”结束全部协议“
          $(".EditArea").show();
        }else{//协议已点击”结束全部协议“
          $(".EditArea").hide();
        }
      }
    });
  }

  //检索
  searchDetail(searchKey: string): void {
    // 获取所有
    if(searchKey==""){
      $.MsgAlert.AlertTure("个人协议详情", "请输入查询条件");
      return;
    }
    this.codeNo = searchKey;
    this.getPersonelAgreementDetail(searchKey);
  }
  editAgreementDetail(event):void{
    var $thats = $(event.target);
    if(this.flag==0){
      this.flag++;
      $thats.parents("tr").find(".agreeEdit").hide();
    	$thats.parents("tr").find("input").removeClass("clearStyle");
    	$thats.parents("tr").find("input").removeAttr("readonly");
    	$thats.parents("tr").find(".laydate-icon").attr("onclick","laydate()")
		  $thats.parents("td").find(".agreeSave").show();
		  $thats.parents("td").find(".cancelSave").show();
    }else{
      $("#myModal").modal("show");
    }
  }

  //保存赴日协议编辑结果
  saveJPAgreementDetail(detailInfo: AgreementDetail,event,goAbroadDate:string,backAbroadDate:string,startDate:string,endDate:string): void {
    console.log(event);
    detailInfo.goAbroadDate=goAbroadDate;
    detailInfo.backAbroadDate=backAbroadDate;
    detailInfo.startDate=startDate;
    detailInfo.endDate=endDate;
    var $thats = $(event.target);
    var that = this;
    this.agreementDetailService.updateByAgreementRegister(detailInfo).then(result => {
  
        if (result.code == 0) {
          that.flag--;
          that.reward=0;
          that.payment=0;

          //修改协议结束时间时，状态相对要改变
          var nowDate = new Date();
          var endDate = new Date(detailInfo.endDate);
          if(nowDate.getTime()>endDate.getDate()){//已结束
            detailInfo.status="1";
          }else{//未结束
            detailInfo.status="0";
          }

          for(var i=0;i<that.japanProList.length;i++){
            var info = that.japanProList[i];
            that.reward += Number(info.cRewardTotal);
            that.payment += Number(info.cPaymentTotal);
          }
          for(var i=0;i<that.otherProList.length;i++){
            var info = that.otherProList[i];
            that.payment += Number(info.cPaymentTotal);
          }

          $thats.parents("td").find(".agreeSave").hide();
          $thats.parents("td").find(".cancelSave").hide();
          $thats.parents("td").find(".agreeEdit").show();
          $thats.parents("tr").find("input").addClass("clearStyle");
          $thats.parents("tr").find("input").attr("readonly","readonly");
          $thats.parents("tr").find(".laydate-icon").removeAttr("onclick")

          this.rewardStr = this.fmoney(String(this.reward),1);
          this.paymentStr = this.fmoney(String(this.payment),1);
        } else {
          // 请求失败
          $.MsgAlert.AlertTure("查询协议详情：", result.msg);
        }
      })
    }

  //保存其他协议
  saveOtherAgreementDetail(detailInfo: AgreementDetail,event,signDate:string,startDate:string,endDate:string): void {
    console.log(event);
    detailInfo.signDate=signDate;
    detailInfo.startDate=startDate;
    detailInfo.endDate=endDate;
    var $thats = $(event.target);
    var that = this;
    this.agreementDetailService.updateByAgreementRegister(detailInfo).then(result => {
  // 协议结束时间改变，状态修正
        if (result.code == 0) {
          that.flag--;
          that.reward=0;
          that.payment=0;
          
          //修改协议结束时间时，状态相对要改变
          var nowDate = new Date();
          var endDate = new Date(detailInfo.endDate);
          if(nowDate.getTime()>endDate.getDate()){//已结束
            detailInfo.status="1";
          }else{//未结束
            detailInfo.status="0";
          }
          for(var i=0;i<that.japanProList.length;i++){
            var info = that.japanProList[i];
            that.reward += Number(info.cRewardTotal);
            that.payment += Number(info.cPaymentTotal);
          }
          for(var i=0;i<that.otherProList.length;i++){
            var info = that.otherProList[i];
            that.payment += Number(info.cPaymentTotal);
          }

          $thats.parents("td").find(".agreeSave").hide();
          $thats.parents("td").find(".cancelSave").hide();
          $thats.parents("td").find(".agreeEdit").show();
          $thats.parents("tr").find("input").addClass("clearStyle");
          $thats.parents("tr").find("input").attr("readonly","readonly");
          $thats.parents("tr").find(".laydate-icon").removeAttr("onclick")

          this.rewardStr = this.fmoney(String(this.reward),1);
          this.paymentStr = this.fmoney(String(this.payment),1);
          
        } else {
          // 请求失败
          $.MsgAlert.AlertTure("查询协议详情：", result.msg);
        }
      })
    }
  
  //取消协议按钮
  cancelEditDetail(): void {
    this.flag--;
    this.getPersonelAgreementDetail(this.codeNo);
  }

  //页面跳转
  forwardAgreementAdd(): void {
    this.router.navigate(['personnel/agreement/add']);
  }

  //结束协议前弹出确认
  end1Agreement(): void {
    if(this.flag > 0){
      $("#myModal").modal("show");
    }else{
        $("#myConfriomModal").modal("show");
      }
  }

  //结束协议
  end2Agreement(): void {
      $("#myConfriomModal").modal("hide");
      this.agreementDetailService.finishTheAgreementByCardNoPut(this.codeNo).then(result => {
      if (result.code == 0) {
        this.getPersonelAgreementDetail(this.codeNo);
      } else {
        // 请求失败
        $.MsgAlert.AlertTure("查询协议详情：", result.msg);
      }
    })
  }

  hideMyModal():void{
    $("#myModal").modal("hide");
  }

  cancelMyConfriomModal():void{
    $("#myConfriomModal").modal("hide");
  }

  fmoney(s, n)  
  {  
    n = n > 0 && n <= 20 ? n : 2;  
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";  
    var l = s.split(".")[0].split("").reverse(),  
    r = s.split(".")[1];  
    var t = "";  
    for(var i = 0; i < l.length; i ++ )  
    {  
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
    }  
    return t.split("").reverse().join("") + "." + r;  
  }  
}
