import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class InterviewListService {

  // recruitUrl的URL
  private interviewList = '~resource/interviewList';

  private header = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http) { }

  // 获取所有数据
  public getAll(title: string, interResult: string, interName: string, employeeName: string, pageNum: number, pageSize: number): Promise<any> {
    const url = `${this.interviewList}/?title=${title}&interResult=${interResult}&interName=${interName}&employeeName=${employeeName}&pageNum=${pageNum}&pageSize=${pageSize}`;
    return this.http
      .get(url, { headers: this.header })
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 减少数据
  public delone(userId: number, positionId: number): Promise<any> {
    const url = `${this.interviewList}/${positionId}`;
    return this.http
      .delete(url, { headers: this.header })
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
