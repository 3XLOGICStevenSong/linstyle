import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { StatusInit } from './status-init';

@Injectable()
export class EmployeeStatusService {



    private initStateURL = '~resource/employeeStateInit';
    private searchURL = '~resource/employeeStateSearch';
    private detailURL = '~resource/employee';
    private recruitURL = '~resource/recruitInterviewer';
    private interURL = '~resource/employeeInfo';

    private header = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) { }


    // 员工状态初始化接口
    public employeeStateInit(): Promise<any> {
        return this.http
            .get(this.initStateURL, { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
    // 检索员工信息列表
    public searchEmployeeInfo(cardNo: String, name: String, chineseAbbr: String,
        jobStatus: String, depId: Number, employeeType: String, entryDate: String): Promise<any> {

        const url = `${this.searchURL}/?cardNo=${cardNo}&chineseAbbr=${chineseAbbr}&name=${name}&jobStatus=${jobStatus}&depId=${depId}&employeeType=${employeeType}&entryDate=${entryDate}`;
        return this.http
            .get(url, { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
    // 员工状态信息详细
    public employeeInfoDetail(cardNo: String): Promise<any> {

        const url = `${this.detailURL}/?cardNo=${cardNo}`;
        return this.http
            .get(url, { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
    //更新员工状态信息
    public updateEmployeeInfo(model: StatusInit): Promise<any> {
        return this.http
            .put(this.detailURL, JSON.stringify({ "cardNo": model.cardNo, "name": model.name, "chineseAbbr": model.chineseAbbr, "jobStatus": model.jobStatus, "depId": model.depId, "employeeType": model.employeeType, "entryDate": model.entryDate, "leaveDate": model.leaveDate }), { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }

    //重新绑定面试记录信息
    public updateEmployeeInterInfo(cardNo: String,interIdOld:Number ,interId:Number): Promise<any> {
        return this.http
            .put(this.interURL, JSON.stringify({ "cardNo": cardNo, "interIdOld": interIdOld, "interId": interId}), { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }


    // 获取未绑定的面试记录一览
    public getRecruitInterviewerList(): Promise<any> {

        // const url = `${this.recruitURL}/?cardNo=${cardNo}`;
        return this.http
            .get(this.recruitURL, { headers: this.header })
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