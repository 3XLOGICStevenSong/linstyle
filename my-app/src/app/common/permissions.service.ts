import { Injectable } from '@angular/core';

import { Http, Headers, RequestOptions, URLSearchParams} from '@angular/http';

import 'rxjs/add/operator/toPromise';


@Injectable()
export class PermissionsService {

  constructor(private http: Http) { }


    public getPermissionList(): Promise<Set<String>> {

        let url = '/personnel-presentation/~resource/current/user/permissions';
       return this.http
              .get(url)
              .toPromise()
              .then(res => { 
                  return res.json() as Set<String>;
            }).catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}