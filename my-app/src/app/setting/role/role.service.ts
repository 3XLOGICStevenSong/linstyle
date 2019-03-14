import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Role } from '../role';
import { Resource } from '../role';
import { ResourceIdInfo } from '../role';
import { UpdateRoleClient } from '../role';
import { AddRoleClient } from '../role';

@Injectable()
export class RoleService {
  private header = new Headers({ 'Content-Type': 'application/json' });

  private resourceList: Array<Resource>;

  constructor(private http: Http) { }

  // 获取资源列表
  public getAllResource(roleId:number): Promise<any> {
    var resourceUrl;
    if (roleId== -1) {
      resourceUrl = "~resource/resourceInfo/?";
    } else {
      resourceUrl = `${'~resource/resourceInfo/'}?roleId=${roleId}`;
    }
    return this.http
      .get(resourceUrl)
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 添加角色
  public addRole(clientInfo: AddRoleClient): Promise<any> {
    var resourceUrl = "~resource/role/";
    var body = JSON.stringify(clientInfo);
    console.log("addRole body = " + body);
    return this.http.post(resourceUrl, body, {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);

  }

  // 修改角色
  public updateRole(clientInfo: UpdateRoleClient): Promise<any> {
    var resourceUrl = "~resource/role/";
    var body = JSON.stringify(clientInfo);
    console.log("updateRole body = " + body);
    return this.http.put(resourceUrl, body, {headers: this.header})
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