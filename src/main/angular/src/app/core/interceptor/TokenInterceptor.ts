import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import {Observable, throwError} from 'rxjs/index';
import {UserService} from '../services/user.service';
import {catchError} from 'rxjs/internal/operators';
import {AlertService} from '../services/alert.service';
import {DataService} from '../services/dataservice.service';


@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(public auth: UserService,
              private alertService: AlertService,
              private dataService: DataService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (request.url.indexOf('oauth') === -1) {
      // console.log(this.dataService.accessToken)
      request = request.clone({
        setHeaders: {
          Authorization: this.dataService.accessToken
        }
      });
    }

    return next.handle(request).pipe(catchError((error, caught) => {
      // intercept the respons error and displace it to the console
      // console.log("SSSSs");
      // console.log(error);
      if (error.status === 401) {
        this.auth.logout();
        this.alertService.showToaster('Authorization failed.', 'ERROR');
        // this.router.navigate(['/login', this.appLanguage, this.airlineCode]);
      } else if (error.status === 403) {
        // this.auth.logout();
        this.alertService.showToaster('You don\'t have permission to perform this operation', 'ERROR');
      } else if (error.status === 500) {
        this.alertService.showToaster('An error occurred in server. Please contact system admin', 'ERROR');
      } else if ((error.status === 504 || error.status === 404)) {
        console.log('position came');
          this.alertService.showToaster('An error occurred in server. Please contact admin', 'ERROR');
                // this.accessToken = null;
        // this.router.navigate(['/error']);
      } else if (error.status === 400) {
        this.alertService.showToaster('Request made is incorrect. Please recheck your input', 'ERROR');
      }
      const errore = error.error.message || error.statusText;
      return throwError(errore);
    }) as any);
  }
}
