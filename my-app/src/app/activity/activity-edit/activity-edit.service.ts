import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { Activity } from '../activity';
@Injectable()
export class ActivityEditService {


    private initURL = '~resource/activityDetail';

    private activityURL = '~resource/activityInfo';

    private header = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) { }

    // 初始化获取活动详情
    public getActivityInfo(activeId: String): Promise<any> {

        const url = `${this.initURL}/?activeId=${activeId}`;
        return this.http
            .get(url, { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
    // 编辑活动
    public updateActivity(activity: Activity): Promise<any> {
        // alert(activity.activeContent) 
        // if()
         console.log(JSON.stringify(activity));
        return this.http
            .put(this.activityURL, JSON.stringify(activity), { headers: this.header })
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