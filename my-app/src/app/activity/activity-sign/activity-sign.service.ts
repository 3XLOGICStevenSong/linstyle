import { Injectable } from '@angular/core';
import { Http, Headers,RequestOptions,ResponseContentType,Response} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { ActivityRegister } from '../activity-register';
declare var $: any;
@Injectable()
export class ActivitySignService {

    private signURL = '~resource/activityRegister';

    private exportURL = '~resource/activityRegister/excel';

    private header = new Headers({ 'Content-Type': 'application/json' });

 //private Option=new RequestOptions({ headers: this.header },{ responseType: 'arraybuffer'});
private options = new RequestOptions({ headers: this.header, responseType: ResponseContentType.Blob });
    constructor(private http: Http) { }

    // 活动报名一览

    public getActivitySignInfo(activeId: Number): Promise<any> {

        const url = `${this.signURL}/?activeId=${activeId}`;
        return this.http
            .get(url, { headers: this.header })
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
    // 下载Excel
 public   downloadXLS(activeId: Number) {
//const body = JSON.stringify(value);
//console.log('body', body);
const headers = new Headers({ 'Content-Type': 'application/json' });
const options = new RequestOptions({ headers: headers, responseType: ResponseContentType.Blob });

 const url = `${this.exportURL}/?activeId=${activeId}`;
return this.http.get(url,options)
.subscribe(data => {
this.downloadXLSImpl(data, 'application/xls');
});
}
downloadXLSImpl(data:Response,type:string){
    const blob=new Blob([data.blob()],{type:type});
    const url=window.URL.createObjectURL(blob);
    this.mockAClick(url, '活动报名人员一览.xls');
}
mockAClick(url:string,fileName:string){
    const link=document.createElement('a') as HTMLAnchorElement;
    link.setAttribute('type','hidden');
    link.href=url;
    link.download=fileName;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
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