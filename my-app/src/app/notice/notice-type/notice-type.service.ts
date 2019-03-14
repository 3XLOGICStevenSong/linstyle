import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { TypeList } from './notice-type.typeList';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class NoticeTypeService { 

  // recruitUrl的URL
  private recruitUrl = 'noticeType';

  private header = new Headers({'Content-Type':'application/json'});
  
  constructor(private http:Http){}

  // 获取所有数据
  public getAll():Promise<any> {
    return this.http
      .get(this.recruitUrl)
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }  

  // 增加数据
  public addone(TypeModel:TypeList):Promise<any> {
    return this.http
    //'testResource':JSON.stringify({'name':test.name,'description':test.description,'test':test.test,'result':test.result}),'description':test.description,'test':test.test,'result':test.result
      .post(this.recruitUrl, JSON.stringify({}), {headers: this.header})
      .toPromise()
      .then((res => res.json()))
      .catch(this.handleError);
  }

  // 减少数据
  public delone(typeId:number):Promise<any> {
    const url = `${this.recruitUrl}/${1}`;
    return this.http
      .delete(url, {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 更新数据
  public updateone(typeModel:TypeList):Promise<any> {
    return this.http
    //'id': testid,'name':test.name, 'description':test.description,'test':test.test,'result':test.result
      .put(this.recruitUrl, JSON.stringify({}), {headers: this.header})
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