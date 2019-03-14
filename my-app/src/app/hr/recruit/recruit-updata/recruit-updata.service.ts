import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { PositionList } from '../recruit-input/recruit-input.positionList';
import { Interviewer } from '../recruit-detail/recruit-detail.interviewer';
import { Paper } from '../recruit-detail/recruit-detail.paper';

@Injectable()
export class RecruitUpDataService { 

  // recruitUrl的URL
  private recruitUrl = 'positionList';          
  private getAllPapersUrl = 'testPaperList';    // 获取所有试卷
  private getAllTeasUrl = 'InterviewerList';    // 获取所有面试官
  private subOrRefUrl = 'publishRecruitment';   // 发布或更新通知

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
  public getAllPapers():Promise<any> {
    return this.http
      .get(this.getAllPapersUrl)
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  } 

  // 获取所有面试官
  public getAllTeas():Promise<any> {
    return this.http
      .get(this.getAllTeasUrl)
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  } 

  // 更新招聘信息并发布
  // 网络请求传入参数
  // 1.positionId(int): 招聘职位ID
  // 2.languageId(int):语种 (中文：0，日文：1)
  // 3.title(string):标题
  // 4.content(string):发布内容
  // 5.paperList: 考卷
  // [
  //     1.paperId(int)：考卷Id
  // 称
  // ]
  // 6.面试官
  // interviewerList:
  // [
  //     1.userId(int):用户Id
  // ]
  public updataRecruitment(recruitId:number,language:string,title:string,content:string,paperList:Array<Paper>,interviewerList:Array<Interviewer>):Promise<any> {
    return this.http
      .put(this.subOrRefUrl, JSON.stringify({'recruitId': recruitId,'language':language, 'title':title,'content':content,'paperList':paperList,'recruitEmployeeList':interviewerList}), {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 发布招聘信息
  // 网络请求传入参数
  // 2.languageId(int):语种 (中文：0，日文：1)
  // 3.title(string):标题
  // 4.content(string):发布内容
  // 5.paperList: 考卷
  // [
  //     1.paperId(int)：考卷Id
  // 称
  // ]
  // 6.面试官
  // interviewerList:
  // [
  //     1.userId(int):用户Id
  // ]

  // 
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