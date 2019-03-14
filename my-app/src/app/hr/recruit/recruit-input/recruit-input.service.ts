import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { PositionList } from './recruit-input.positionList';
import { Interviewer } from '../recruit-detail/recruit-detail.interviewer';
import { Paper } from '../recruit-detail/recruit-detail.paper';

@Injectable()
export class RecruitInputService { 

  // recruitUrl的URL
  private recruitUrl = '~resource/positionList';          
  private getAllPapersUrl = '~resource/examPaperList';      // 获取所有试卷
  private getAllTeasUrl = '~resource/employeeList';         // 获取所有面试官
  private subOrRefUrl = '~resource/publishRecruitment';               // 发布或更新通知

  private header = new Headers({'Content-Type':'application/json'});
  
  constructor(private http:Http){}

  // 获取所有数据
  public getAll(language:number,title:string,userId:number):Promise<any> {
    return this.http
      .get(this.recruitUrl)
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }  

  // 获取所有考卷
  public getAllPapers(searchKey:String):Promise<any> {
    const url = `${this.getAllPapersUrl}/?searchKey=${searchKey}`;
    return this.http
      .get(url, {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  } 

  // 获取所有面试官
  public getAllTeas(searchKey:string):Promise<any> {
    const url = `${this.getAllTeasUrl}/?searchName=${searchKey}`;
    return this.http
      .get(url, {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  } 

  // 更新招聘信息并发布
  public updataRecruitment(recruitId:number,languageId:string,title:string,content:string,paperList:Array<any>,interviewerList:Array<any>):Promise<any> {
    return this.http
      .put(this.subOrRefUrl, JSON.stringify({'recruitId': recruitId,'language':languageId, 'title':title,'content':content,'paperList':paperList,'recruitEmployeeList':interviewerList}), {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 发布招聘信息
  public postRecruitment(language:string,title:string,content:string,paperList:Array<Paper>,interviewerList:Array<Interviewer>):Promise<any> {
    return this.http
      .post(this.subOrRefUrl, JSON.stringify({title:title,content:content,language:language,paperList:paperList,recruitEmployeeList:interviewerList}), {headers: this.header})
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