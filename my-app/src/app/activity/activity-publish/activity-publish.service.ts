import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { Activity } from '../activity';
@Injectable()
export class ActivityPublishService {


    private typedURL = '~resource/type';

    private activityURL = '~resource/activityInfo';

    private header = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) { }

    // 初始化获取活动类型列表
    public getActivityTypeInfo(groupCode: String): Promise<any> {

        const url = `${this.typedURL}/?groupCode=${groupCode}`;
        return this.http
            .get(url, { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
    // 发布活动
    public publishActivity(activity: Activity): Promise<any> {
        // alert(activity.activeContent) 
        // if()
         //console.log(JSON.stringify(activity));
        return this.http
            .post(this.activityURL, JSON.stringify(activity), { headers: this.header })
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