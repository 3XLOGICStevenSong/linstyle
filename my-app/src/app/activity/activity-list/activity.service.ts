import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';


@Injectable()
export class ActivityService {


    private activityInitURL = '~resource/activityInfo/init';
    private activitySearchURL = '~resource/activityInfo/search';

    private header = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) { }

    // 活动一览初始化

    public getActivityInitList(): Promise<any> {

        //const url = `${this.activitySearchURL}`;
        return this.http
            .get(this.activityInitURL, { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }

    public getActivitySearchList(startDate: String, endDate: String, pageNum: Number, activeStatus: String, typeCode: String): Promise<any> {

        const url = `${this.activitySearchURL}/?startDate=${startDate}&endDate=${endDate}&pageNum=${pageNum}&activeStatus=${activeStatus}&typeCode=${typeCode}`;
        return this.http
            .get(url, { headers: this.header })
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