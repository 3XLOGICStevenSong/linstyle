import { Injectable } from '@angular/core';

import { Http, Headers, RequestOptions, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';
import { StationMessage } from './station-message';

@Injectable()
export class StationMessageService {

  private messageURL = '~resource/stationMessage';

  private header = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http) { }

  // 获取站内消息列表
  public getStationMessageList(): Promise<any> {
    return this.http
      .get(this.messageURL)
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