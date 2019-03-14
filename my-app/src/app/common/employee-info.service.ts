import { Injectable } from '@angular/core';

import { Http, Headers, RequestOptions, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';


@Injectable()
export class EmployeeInfoService {

  private infoURL = '~resource/employeeList';
  constructor(private http: Http) { }


  public getEmployeeInfoList(searchName: String): Promise<any> {

    let url = `${this.infoURL}/?&searchName=${searchName}`;
    return this.http
      .get(url)
      .toPromise()
      .then(res => {
        return res.json();
      }).catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
        if (error.status == '401') {
            var URL = window.location.host + "/personnel-presentation/login";
            window.location.href = URL;
        }
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}