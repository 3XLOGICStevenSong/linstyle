import { Component, OnInit } from '@angular/core';
import { AgreementTypeListService } from './agreementType-list.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AgreementType } from './agreementType-list.type';
import { inflate } from 'zlib';

declare var $:any;

@Component({
  selector: 'agreementType-root',
  templateUrl: './agreementType-list.component.html',

  providers:[ AgreementTypeListService ]
})


export class AgreementTypeListComponent implements OnInit{

  // 赴日协议一览列表
  japanProList:Array<AgreementType>;
  // 其他协议一览列表
  otherProList:Array<AgreementType>;
  // 协议类型列表
  agreementList:Array<AgreementType>;
  //协议
  typeDetail:AgreementType;

  flag:Number;

  typeName:string;
  typePrice:string;
  typeYear:string;
  typeMethod="0";
  typeStatus="1";
  // 实例化 AgreementTypeListService
  constructor(private route: ActivatedRoute,
    private router: Router,
    private agreementTypeListService: AgreementTypeListService) { }

   // 页面初始化  获取协议一览
    ngOnInit(): void {
      this.agreementTypeListService.getAgreementTypeList().then(result => {
        if(result.code == 0){
          // 请求成功

          this.japanProList=result.japanProList;
          for(var i=0;i<this.japanProList.length;i++){
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

          this.otherProList = result.otherProList
          for(var i=0;i<this.otherProList.length;i++){
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

        } else {
          // 请求失败
          $.MsgAlert.AlertTure("协议一览：", result.msg);
        }
      });
    }
  
    //使用 1 
    startAgreementTypeStatus(typeDetail:AgreementType){
      this.agreementTypeListService.updateAgreementStatus(typeDetail,"1").then(result => {
        if(result.code == 0){//请求成功
          typeDetail.status="1";
        }else {
          // 请求失败
          $.MsgAlert.AlertTure("协议一览：", result.msg);
        }
      })
    }
    //暂停 2 
    stopAgreementTypeStatus(typeDetail:AgreementType){
     this.agreementTypeListService.updateAgreementStatus(typeDetail,"2").then(result => {
       if(result.code == 0){//请求成功
         typeDetail.status="2";
       }else {
          //请求失败
          $.MsgAlert.AlertTure("协议一览：", result.msg);
       }
     })
    }

    //删除协议,0:不可用（删除）
    deleteAgreementType(typeDetail:AgreementType){
      var that=this;
      $("#myModal").modal("show");
      $(".delSuccess").click(function(){
        $("#myModal").modal("hide");
        that.agreementTypeListService.updateAgreementStatus(typeDetail,"0").then(result => {
          if(result.code == 0){//请求成功
            //成功后将从数组中删除该元素
            that.ngOnInit();
             return;
          }else {
            // 请求失败
            $.MsgAlert.AlertTure("协议一览：", result.msg);
          }
        })
      })
    }

    insertAgreementHtml(title:String,flag:Number){
      this.flag=flag;//1:赴日协议，2:其他协议  
      $("#modelForm .title").text(title);
      $("#modelForm").show();
      $(".modelBg,.cancelBtn").click(function(){
      $("#modelForm").hide();
      });
    }
    //添加协议
    insertAgreementInfo(){
      var that = this;
      var info = new AgreementType();
      if(this.flag==1){//1:赴日协议
        info.aiType="0";
        info.aiName = this.typeName;
        info.aiPeriod = this.typeYear;
        info.paymentType = this.typeMethod;
        info.status = this.typeStatus;
      }else{//0:其他协议
        info.aiType="1";
        info.aiName = this.typeName;
        info.aiPeriod = this.typeYear;
        info.aiTotal = this.typePrice;
        info.paymentType = this.typeMethod;
        info.status = this.typeStatus;
      }
      this.agreementTypeListService.insertAgreementInfo(info).then(result => {
        if(result.code == 0){//请求成功
          //成功后将从数组中删除该元素
          $("#modelForm").hide();
          that.ngOnInit(); 
        }else {
          // 请求失败
          $.MsgAlert.AlertTure("协议一览：", result.msg);
        }
      })
    }
}
  
