import {Injectable} from '@angular/core';

import {Subject, Subscription} from 'rxjs/index';
import {ToastrManager} from 'ng6-toastr-notifications';



@Injectable()
export class AlertService {
  private alert = new Subject<any>();
  private alertCallBack = new Subject<any>();
  private _eventHandler: Subscription;


  constructor(public toastr: ToastrManager) {
  }

  showToaster(message: string, type: string) {
    if (type.toUpperCase() === 'SUCCESS') {
      this.toastr.successToastr(message, type, {animate: 'slideFromTop'});
    }
    if (type.toUpperCase() === 'ERROR') {
      this.toastr.errorToastr(message, type, {animate: 'slideFromTop'});
    }
    if (type.toUpperCase() === 'WARNING') {
      this.toastr.warningToastr(message, type, {animate: 'slideFromTop'});
    }
    if (type.toUpperCase() === 'INFO') {
      this.toastr.infoToastr(message, type, {animate: 'slideFromTop'});
    }


  }
}
