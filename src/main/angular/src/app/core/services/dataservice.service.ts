import {Injectable} from '@angular/core';

import {Router} from '@angular/router';

import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {CommonService} from './common.service';
import {catchError, map} from 'rxjs/internal/operators';
import {Observable, throwError} from 'rxjs/index';

import {LocalStorageService} from 'ngx-webstorage';
import {AlertService} from './alert.service';


@Injectable()
export class DataService {
  public accessToken;

  constructor(private router: Router,
              private httpClient: HttpClient,
              private commonService: CommonService,
              private alertService: AlertService,
              private localSt: LocalStorageService
  ) {
    this.accessToken = localSt.retrieve('ACCESS_TOKEN');
  }


  public login(conf: any, data: any): Observable<any> {
    return this.requestLogin(conf.url, data, conf.headerParam).pipe(
      map((t: any) => {
        this.commonService.hideLoading();
        this.accessToken = 'Bearer ' + t.access_token;
        this.localSt.store('ACCESS_TOKEN', this.accessToken);
        return t;
      })
      , catchError(error => {
        console.log(error);
        return throwError('Login Failed!');
      }));
  }

  private requestLogin(url: string, data: any, headerParams?: any): Observable<any> {
    const loginKey = 'Basic ' + 'Y2xpZW50YXBwOjEyMzQ1Ng==';
    const body = new FormData();
    body.append('username', data.user);
    body.append('password', data.password);
    body.append('grant_type', 'password');
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', loginKey);
    return this.httpClient.post(url, body, {headers: headers});
  }


  public request(conf: any, data?: any): Observable<any> {
    if (!data) {
      data = {};
    }
    if (conf.type === 'POST') {
      return this.httpClient.post(conf.url, data).pipe(
        map((t: any) => {
          return this.decodeMsg(t);
        })
        , catchError(error => {
          console.log(error);
          return throwError('Request failed');
        }));
    } else {
      return this.httpClient.get(conf.url, {
        params: Object.entries(data).reduce(
          (params, [key, value]) => params.set(key, '' + value), new HttpParams())
      }).pipe(
        map((t: any) => {
          return this.decodeMsg(t);
        })
        , catchError(error => {
          console.log(error);
          return throwError('Request failed');
        }));
    }
  }

  public async downloadFile(conf, data?: any) {
    if (!data) {
      data = {};
    }
    const file = await this.httpClient.get<Blob>(
      conf.url,
      {
        params: Object.entries(data).reduce(
          (params, [key, value]) => params.set(key, '' + value), new HttpParams()),
        responseType: 'blob' as 'json'
      }).toPromise();
    return file;
  }

  public async downloadFileAsPost(conf, data?: any) {
    if (!data) {
      data = {};
    }
    const file = await this.httpClient.post<Blob>(
      conf.url, data,
      {responseType: 'blob' as 'json'}).toPromise();
    return file;
  }

  private decodeMsg(body: any): any {
    if (body.success && body.success === true) {
      return body.result;
    } else if (body.success === false && body.errorCode === 'WARNING') {
      this.alertService.showToaster(body.errorMessage, 'WARNING');
    } else if (body.errorCode === 'INFO') {
      this.alertService.showToaster(body.result, 'INFO');
    } else if (body.success === false) {
      this.alertService.showToaster(body.errorMessage, 'ERROR');
    }
    return null;
  }


}
