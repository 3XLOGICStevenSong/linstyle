import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { NoticeInfo} from './notification.noticeInfo';
import { NoticeType } from './notice.type';

@Injectable()
export class NotificationService {

   //所有通知类型
   private allNoticeTypeListUrl = '~resource/allTypeList';

  //通知类型
  private noticeTypeUrl = '~resource/noticeType';

  //通知一览 初始化画面
  private noticeListInitUrl = '~resource/noticeListInit/';

  //审核通知（通过审核）
  private noticeStateUrl = '~resource/noticeState/';

  //删除通知
  private noticeUrl = '~resource/notice/';

  private header = new Headers({ 'Content-Type': 'application/json' });
  constructor(private http: Http) { };

  //可用的通知类型
  public getNoticeTypeList(): Promise<any> {
    const url = `${this.noticeTypeUrl}`;
    return this.http
      .get(url, { headers: this.header })
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }
  //所有的通知类型
  public getAllNoticeTypeList(): Promise<any> {
    const url = `${this.allNoticeTypeListUrl}`;
    return this.http
      .get(url, { headers: this.header })
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }
  
  //添加通知类型
  public insertNoticeType(type: NoticeType): Promise<any> {
    var url = this.noticeTypeUrl+"/";
    return this.http
      .post(url, JSON.stringify(type), { headers: this.header })
        .toPromise()
        .then(res => res.json())
        .catch(this.handleError);
  }

  //更新,删除通知类型
   public updateNoticeType(type: NoticeType): Promise<any> {
    const url = "~resource/noticeType/";
    return this.http.put(url, JSON.stringify(type), {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }


  //审核通知（通过审核）
  public updateReviewState(notice:NoticeInfo): Promise<any> {
    return this.http
      .put(`${this.noticeStateUrl}`, JSON.stringify(notice), { headers: this.header })
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  //删除通知
  public deleteNotificationInfo(notice:NoticeInfo): Promise<any> {
    return this.http
    .delete(`${this.noticeUrl}/?noticeId=${notice.noticeId}`,{ headers: this.header })
    .toPromise()
    .then(res => res.json())
    .catch(this.handleError);
  }

  //通知一览 初始化画面
  public getInitNotificationList(typeId: string, startDate: string, endDate: string, flag:number): Promise<any> {
      const url = `${this.noticeListInitUrl}/?typeId=${typeId}&startDate=${startDate}&endDate=${endDate}&flag=${flag}`;
      return this.http
      .get(url, { headers: this.header })
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  //发布通知
  public insertNotifiction(info: NoticeInfo): Promise<any> {
    return this.http
      .post(this.noticeUrl, JSON.stringify(info), { headers: this.header })
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  //更新通知
  public updateNotificationInfo(detail: NoticeInfo): Promise<any> {
    return this.http
      .put(this.noticeUrl, JSON.stringify(detail), { headers: this.header })
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  //获取通知详情
  public getNotificationInfo(noticeId:string): Promise<any> {
    const url = `${this.noticeUrl}/?noticeId=${noticeId}`;
    return this.http
      .get(url, { headers: this.header })
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}