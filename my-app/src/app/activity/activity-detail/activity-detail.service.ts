import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { ActivityRegister } from '../activity-register';

@Injectable()
export class ActivityDetailService {


    private activityDetailURL = '~resource/activityInfo';
    private signURL = '~resource/activityRegister';
     private unsignURL = '~resource/activityRegister/unsign';

    private header = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) { }

    // 活动一览初始化

    public getActivityDetail(activeId: Number): Promise<any> {

        const url = `${this.activityDetailURL}/?&activeId=${activeId}`;
        return this.http
            .get(url, { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
    //家属取消报名
    public deleteFamilyInfo(arId: String): Promise<any> {

        const url = `${this.signURL}/${arId}`;
        return this.http
            .delete(url, { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
    //不报名功能
    public unsignActivity(activityRegisterList: Array<ActivityRegister>,signFlag:String,activeId:Number): Promise<any> {
        return this.http
            .put(this.unsignURL, JSON.stringify({ 'activityRegisterList': activityRegisterList,'signFlag':signFlag,'activeId':activeId} ), { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
    //活动报名
    public signActivity(activityRegisterList: Array<ActivityRegister>,activeId:Number): Promise<any> {
        return this.http
            .post(this.signURL, JSON.stringify({ 'activityRegisterList': activityRegisterList,'activeId':activeId} ), { headers: this.header })
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