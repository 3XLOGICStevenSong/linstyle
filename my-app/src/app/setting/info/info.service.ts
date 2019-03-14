import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Role } from '../role';
import { Resource } from '../role';
import { UpdateRoleOwnerClient} from '../role';
@Injectable()
export class RoleInfoService {
  private header = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http) { }

  // 获取角色列表
  public getAllRole(): Promise<any> {
    return this.http
      .get('~resource/role/')
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 获取某人的角色列表
  public getRoleByCardNo(cardNo: string): Promise<any> {
    const url = `${'~resource/role/'}?cardNo=${cardNo}`;
    return this.http
      .get(url)
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 获取描述
  public getDesByRoleId(roldId: number): Promise<any> {
    const url = `${'~resource/roleDescription/'}?roleId=${roldId}`;
    console.log("getDesByRoleId url = ", url);
    return this.http.get(url)
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 获取拥有者
  public getOwnerByRoleId(roldId: number): Promise<any> {
    const url = `${'~resource/roleOwner/'}?roleId=${roldId}`;
    return this.http.get(url)
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 修改角色关联的资源
  public changeRoleByRoleId(roldId: number): Promise<any> {
    const url = `${'~resource/roleOwner/'}?roleId=${roldId}`;
    return this.http.get(url)
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }


  // 删除角色
  public deleteRoleByRoleId(roldId: number): Promise<any> {
    const url = `${'~resource/role/'}?roleId=${roldId}`;
    return this.http.delete(url)
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 修改用户角色
  public updateUserRole(clientInfo : UpdateRoleOwnerClient): Promise<any> {
    const url = "~resource/roleInfo/";
    var body = JSON.stringify(clientInfo);
    console.log("updateUserRole body = " + body);
    return this.http.post(url, body, {headers: this.header})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  // 错误
  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    alert(error);
    return Promise.reject(error.message || error);
  }
}
