import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { PositionList } from './recruit-list.positionList';

@Injectable()
export class RecruitListService { 

  // recruitUrl的URL
  private recruitUrl = '~resource/positionList';

  private header = new Headers({'Content-Type':'application/json'});
  
  constructor(private http:Http){}

  // 获取所有数据
  public getAll(language:string,title:string,startTime:Date,endTime:Date):Promise<any> {
    const url = `${this.recruitUrl}/${language}&${title}&${startTime}&${endTime}`;
    return this.http
      .get(url, {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }  

  // 获取所有数据
  public getAllClear(language:string,title:string):Promise<any> {
    const url = `${this.recruitUrl}/${language}&${title}&&`;
    return this.http
      .get(url, {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }  

  // 减少数据
  public delone(positionId:number):Promise<any> {
    const url = `${this.recruitUrl}/${positionId}`;
    return this.http
      .delete(url, {headers: this.header})
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
