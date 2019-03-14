import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { Type } from '../../hr/status/employee-register/type';
@Injectable()
export class ActivityTypeService {



    private typedURL = '~resource/type';

    private header = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) { }

    // 初始化活动类型列表
    public getTypeListInfo(groupCode: String): Promise<any> {
      
        const url = `${this.typedURL}/?groupCode=${groupCode}`;
        return this.http
            .get(url,{ headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }

 // 修改类别信息状态
    public updateTypeInfo(typeId:String,status :String): Promise<any> {
        return this.http
            .put(this.typedURL,JSON.stringify({'typeId':typeId,'status':status}),{ headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
// 修改类别信息名称
    public saveInfo(typeId:String,typeName :String): Promise<any> {
        return this.http
            .put(this.typedURL,JSON.stringify({'typeId':typeId,'typeName':typeName}),{ headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
    // 添加类别信息
    public createTypeInfo(typeName :String): Promise<any> {
        return this.http
            .post(this.typedURL,JSON.stringify({'groupCode':"ACTIVE_TYPE",'typeName':typeName}),{ headers: this.header })
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
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}