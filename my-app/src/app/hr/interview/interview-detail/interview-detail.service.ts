import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { InterviewDetail } from './interview-detail.interviewDetail';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class InterviewDetailService {

  // recruitUrl的URL
  private interviewDetail = '~resource/interviewDetail';
  private interviewInput = '~resource/interviewInput';
  private interviewResult = '~resource/modifyEvaluation';
  private header = new Headers({ 'Content-Type': 'application/json' });
  constructor(private http: Http) { }

  // 获取详情
  public getDetail(interId: number): Promise<any> {
    const url = `${this.interviewDetail}/${interId}`;
    return this.http
      .get(url, { headers: this.header })
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 录入面试信息
  public postInterInfo(interInfo: InterviewDetail): Promise<any> {
    return this.http
      .post(this.interviewInput, JSON.stringify(interInfo), { headers: this.header })
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 提交面试结果
  public putInterResult(interInfo: InterviewDetail): Promise<any> {
    return this.http
      .put(this.interviewResult, JSON.stringify(interInfo), { headers: this.header })
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 错误
  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}