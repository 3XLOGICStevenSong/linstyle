import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { ResumeSearchInfo } from './resume-list-search';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class ResumeService {

    // 简历的url
    private resumeListUrl = '~resource/resumeList';
    private resumeListView = '~resource/resumeListView';
    private header = new Headers({ 'Content-Type': 'application/json' });
    constructor(private http: Http) { }

    // 获取初始化数据
    public getInitInfo(): Promise<any> {
        return this.http
            .get(this.resumeListView, { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }

    // 获取所有数据
    public getAll(rsInfo: ResumeSearchInfo): Promise<any> {
        const url = `${this.resumeListUrl}/?depName=${rsInfo.depName}&jobStatus=${rsInfo.jobStatus}&cardNo=${rsInfo.cardNo}&name=${rsInfo.name}&pageNum=${rsInfo.pageNum}&pageSize=${rsInfo.pageSize}`;
        return this.http
            .get(url, { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }

    // 获取所有部门列表


    //   // 增加数据
    //   public addone(TypeModel:TypeList):Promise<any> {
    //     return this.http
    //     //'testResource':JSON.stringify({'name':test.name,'description':test.description,'test':test.test,'result':test.result}),'description':test.description,'test':test.test,'result':test.result
    //       .post(this.recruitUrl, JSON.stringify({}), {headers: this.header})
    //       .toPromise()
    //       .then((res => res.json()))
    //       .catch(this.handleError);
    //   }

    //   // 减少数据
    //   public delone(typeId:number):Promise<any> {
    //     const url = `${this.recruitUrl}/${1}`;
    //     return this.http
    //       .delete(url, {headers: this.header})
    //       .toPromise()
    //       .then(res => res.json())
    //       .catch(this.handleError);
    //   }

    // 错误
    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}