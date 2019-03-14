import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { PositionList } from './paper-list.positionList';
import { Paper } from './paper-list.paper';

@Injectable()
export class PaperListService { 

  // 考卷列表的URL
  private paperListUrl = '~resource/examPaperList';
  // 考卷操作的URL
  private paperUrl = '~resource/examPaper';

  private header = new Headers({'Content-Type':'application/json'});
  
  constructor(private http:Http){}

  // 获取所有考卷
  public getAllPapers(searchKey:String):Promise<any> {
    const url = `${this.paperListUrl}/?searchKey=${searchKey}`;
    return this.http
      .get(url, {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }  

  // 减少数据
  public delone(paperId:number):Promise<any> {
    const url = `${this.paperUrl}/${paperId}`;
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