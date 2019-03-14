import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { AgreementType } from './agreementType-list.type';

@Injectable()
export class AgreementTypeListService { 

  private header = new Headers({'Content-Type':'application/json'});
  
  constructor(private http:Http){};

  urlStr ="~resource/protocol/";

  //获取协议一览
  public getAgreementTypeList():Promise<any> {
    const url = `${"~resource/protocol"}`;
    return this.http
      .get(url, {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  } 

  // 删除,暂停，重启 协议
  public updateAgreementStatus(type:AgreementType,flag:string):Promise<any> {
    type.status=flag;
    return this.http
      .put("~resource/protocol/", JSON.stringify(type), {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  //添加协议
   public insertAgreementInfo(info:AgreementType):Promise<any> {
    return this.http
      .post("~resource/protocol/", JSON.stringify(info), {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 错误
  private handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }

}
