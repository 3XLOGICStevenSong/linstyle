import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
//import { ActivityRegister } from '../activity-register';

@Injectable()
export class DepartmentService {


    private initURL = '~resource/departmentInfo';
    private searchURL = '~resource/departmentInfo/search';
  

    private header = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) { }

    //部门信息初始化

    public getDepartmentInitInfo(): Promise<any> {
        return this.http
            .get(this.initURL, { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
    //检索部门信息
        public searchDepartmentInfo(depId: Number,sortBy:String): Promise<any> {
             const url = `${this.searchURL}/?&depId=${depId}&sortBy=${sortBy}`;
        return this.http
            .get(url, { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
    public updateDepartmentInfo(cardNo: String, depId: Number, name: String): Promise<any> {
        return this.http
            .put(this.initURL, JSON.stringify({ "managerCode": cardNo, "depId": depId, "managerName": name }), { headers: this.header })
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