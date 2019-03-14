import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';


@Injectable()
export class PasswordService {



    private passwordURL = 'user/password';

    private header = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) { }

    // 修改密码
    public changePassword(oldPassword: String, password: String): Promise<any> {
        console.log(JSON.stringify({ "password": password, "oldPassword": oldPassword }));
        return this.http
            .post(this.passwordURL, JSON.stringify({ "password": password, "oldPassword": oldPassword }), { headers: this.header })
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