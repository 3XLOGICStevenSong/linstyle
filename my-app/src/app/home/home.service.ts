import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';


@Injectable()
export class HomeService {

  

   private homeNoticeURL = '~resource/home/notificationInfo';

   private header = new Headers({'Content-Type':'application/json'});
  
   constructor(private http:Http){}

  // 获取当前登录用户信息
  public getHomeNoticeInfo():Promise<any> {
    return this.http
      .get(this.homeNoticeURL)
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }


  
  // 捕获异常
  private handleError(error: any): Promise<any> {
     if (error.status == '401') {
      var URL = window.location.host + "/personnel-presentation/login";
      window.location.href = URL;
    }
    console.error('An error occurred',error);
    return Promise.reject(error.message || error);
  }
}