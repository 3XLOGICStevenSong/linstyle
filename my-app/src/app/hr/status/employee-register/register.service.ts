import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { RegisterInit } from './register-init';

@Injectable()
export class EmployeeRegisterService {



    private initURL = 'employeeRegisterInit';

private registerURL = 'employee';
    private header = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) { }

    // 注册页面初始化
    public getInitInfo(): Promise<any> {
        return this.http
            .get(this.initURL, { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
    // 注册用户
    public registerUser(registerInit: RegisterInit): Promise<any> {
        return this.http
            .post(this.registerURL, JSON.stringify(registerInit), { headers: this.header })
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