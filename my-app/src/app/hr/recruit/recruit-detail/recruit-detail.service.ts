import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class RecruitDetailService { 

  // recruitUrl的URL
  private recruitUrl = '~resource/recruitInfoDetai';

  private header = new Headers({'Content-Type':'application/json'});
  
  constructor(private http:Http){}

  // 获取所有数据
  public getAll(positionId:number):Promise<any> {
    const url = `${this.recruitUrl}/${positionId}`;
    return this.http
      .get(url, {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }  

  // 错误
  private handleError(error: any): Promise<any> {
    console.error('An error occurred',error);
    return Promise.reject(error.message || error);
  }
}