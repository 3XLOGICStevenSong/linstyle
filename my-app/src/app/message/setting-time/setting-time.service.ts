import { Injectable } from '@angular/core';

import { Http, Headers, RequestOptions, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';
import { Setting } from './setting-time';


@Injectable()
export class SettingService {

  private settingURL = '~resource/messageSetting';

  private header = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http) { }

  // 获取设置时间
  public getSettingTimeList(): Promise<any> {
    return this.http
      .get(this.settingURL)
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }
  // 获取设置时间
  public updateTime(contactTime: Number, tryTime: Number): Promise<any> {
    return this.http
      .put(this.settingURL,JSON.stringify({"contactTime":contactTime,"tryTime":tryTime}), {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }


  // 捕获异常
  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}