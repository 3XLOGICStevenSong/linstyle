import { Injectable } from '@angular/core';

import { Http, Headers, RequestOptions, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';
import { Menu } from './menu'
import { Router } from '@angular/router';
@Injectable()
export class MenuService {

  constructor(private http: Http, private router: Router) { }


  public getMenuList(): Promise<Array<Menu>> {

    let url = '/personnel-presentation/~resource/current/user/menus';
    return this.http
      .get(url)
      .toPromise()
      .then(res => {
        return res.json() as Array<Menu>;
      }).catch(this.handleError);
  }

  public handleError(error: any): Promise<any> {
    // console.error('An error occurred', error); // for demo purposes only
    if (error.status == '401') {
      var URL = window.location.host + "/personnel-presentation/login";
      window.location.href = URL;
    }
    return Promise.reject(error.message || error);
  }

}