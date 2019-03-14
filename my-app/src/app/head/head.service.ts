import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { User } from './User';

@Injectable()
export class HeadService {

   private currentUserURL = '~resource/current/user';

   private loginOutURL = 'loginout';

   private header = new Headers({'Content-Type':'application/json'});
  
   constructor(private http:Http){}

  // 获取当前登录用户信息
  public getCurrentUser():Promise<any> {
    return this.http
      .get(this.currentUserURL)
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  //退出登录
  
   public loginOut():Promise<any> {
    return this.http
      .get(this.loginOutURL)
      .toPromise()
      .then()
      .catch(this.handleError);
  }
  // 捕获异常
  private handleError(error: any): Promise<any> {
   // console.error('An error occurred',error);
    if (error.status == '401') {
      var URL = window.location.host + "/personnel-presentation/login";
      window.location.href = URL;
    }
    return Promise.reject(error.message || error);
  }
}