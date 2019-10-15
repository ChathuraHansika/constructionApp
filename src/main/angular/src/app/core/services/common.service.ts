import {Injectable, OnInit} from '@angular/core';
import {Observable, Subject, throwError} from 'rxjs/index';

@Injectable({
  providedIn: 'root'
})

export class CommonService {
  constructor() {

  }

  private loading = new Subject<any>();

  public showLoading() {
    this.loading.next({status: true});
  }

  public getLoadingStatus(): Observable<any> {
    return this.loading.asObservable();
  }

  public hideLoading() {
    this.loading.next({status: false});
  }


}
