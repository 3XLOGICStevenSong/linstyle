import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { AgreementDetail} from './agreement.agreementDetail';
import { AddAgreement } from 'app/hr/agreement/agreement.addAgreement';

@Injectable()
export class AgreementCommonService { 

  //查询员工赴日协议列表
  private japanProListUrl = '~resource/japanProList/';
  
  //添加报名人员
  private applicationUrl = '~resource/application/';
  
  //获取协议列表（报名，查询使用）
  private protocolNameListUrl = '~resource/protocolNames';

  //查询员工协议详情
  private protocolDetailUrl = '~resource/protocolDetail/';

  //查询员工未结束协议列表
  private protocolNotFinishedDetailUrl = '~resource/protocolNotFinishedDetail/';

  //查询协议报名人员
  private protocolPersonnelsUrl = '~resource/protocolPersonnels/';

  //结束协议
  private overProtocolUrl = '~resource/overProtocol/';
    
  detail: AgreementDetail; //协议详情

  private header = new Headers({'Content-Type':'application/json'});
  constructor(private http:Http){};


  //获取协议列表（报名，查询使用）
  public getProtocolNames():Promise<any> {
    const url = `${this.protocolNameListUrl}`;
    return this.http
      .get(url, {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
    }  

  //添加报名人员
  public insertAgreementRegisterInfo(addAgreement:AddAgreement):Promise<any> {
    const url = `${this.applicationUrl}`;
    return this.http
      .post(url, JSON.stringify(addAgreement),{headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // //查询员工赴日协议列表
  //  public getJpArgeementRegisterList(aiName:string,aiTotal:string,aiPeriod:string,paymentType:string,aiType:string,status:string):Promise<any> {
  //   return this.http
  //     .post(this.japanProListUrl, JSON.stringify({'aiName':aiName,'aiTotal':aiTotal,'aiPeriod':aiPeriod,'paymentType':paymentType,'aiType'
  //       :aiType,'status':status}), {headers: this.header})
  //     .toPromise()
  //     .then(res => res.json())
  //     .catch(this.handleError);
  // }

  //查询协议报名人员
     public getRegAgreementList(aiId:string,status:string):Promise<any> {
      const url = `${this.protocolPersonnelsUrl}/?aiId=${aiId}&status=${status}`;
      return this.http
        .get(url, {headers: this.header})
        .toPromise()
        .then(res => res.json())
        .catch(this.handleError);
  } 

  //初期化员工协议详情
  public getPerAgreDetail(searchKey:string):Promise<any> {
    const url = `${this.protocolDetailUrl}?searchKey=${searchKey}`;
    return this.http
    .get(url, {headers: this.header})
    .toPromise()
    .then(res => res.json()).catch(this.handleError);
  }  

   //初期化员工未结束协议列表
   public getNotFinishedAgreDetail(searchKey:string):Promise<any> {
    const url = `${this.protocolNotFinishedDetailUrl}?searchKey=${searchKey}`;
    return this.http
    .get(url, {headers: this.header})
    .toPromise()
    .then(res => res.json()).catch(this.handleError);
  } 

  // 更新协议详情
  public updateByAgreementRegister(detail:AgreementDetail):Promise<any> {
    return this.http
      .put(this.protocolDetailUrl, JSON.stringify(detail), {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 结束协议
  public finishTheAgreementByCardNo(detail:AgreementDetail):Promise<any> {
    return this.http
      .put(this.overProtocolUrl, JSON.stringify(detail), {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 结束协议
  public finishTheAgreementByCardNoPut(codeNo:string):Promise<any> { 
    const url = `${this.overProtocolUrl}?codeNo=${codeNo}`;
        return this.http
          .get(url, {headers: this.header})
          .toPromise()
          .then(res => res.json())
          .catch(this.handleError);
      }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred',error);
    return Promise.reject(error.message || error);
  }

}
