import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class RootService {

  public secItem: String;

  // private rolesUrl = '~resource/current/user/roles';

  // private permissionsUrl = '~resource/current/user/permissions';

  // private menusUrl = '~resource/current/user/menus';

  // private header = new Headers({'Content-Type':'application/json'});

  constructor(private http: Http) { }
  public getSelectItem(secItem: String) {
    return this.secItem = secItem;
  }
  // // 获取角色
  // public getRoles():Promise<any> {
  //   return this.http
  //     .get(this.rolesUrl)
  //     .toPromise()
  //     .then(res => res.json())
  //     .catch(this.handleError);
  // }

  // // 获取权限
  // public getPermissions():Promise<any> {
  //   return this.http
  //     .get(this.permissionsUrl)
  //     .toPromise()
  //     .then(res => res.json())
  //     .catch(this.handleError);
  // }

  // // 获取菜单
  // public getMenu():Promise<any> {
  //   return this.http
  //     .get(this.menusUrl)
  //     .toPromise()
  //     .then(res => res.json())
  //     .catch(this.handleError);
  // }

  // 捕获异常
  private handleError(error: any): Promise<any> {
     if (error.status == '401') {
      var URL = window.location.host + "/personnel-presentation/login";
      window.location.href = URL;
    }
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}