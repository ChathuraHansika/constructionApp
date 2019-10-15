import {Injectable} from '@angular/core';
import {LocalStorage, LocalStorageService} from 'ngx-webstorage';
import {BehaviorSubject, Observable} from 'rxjs/index';
import {Router} from '@angular/router';
import {DataService} from './dataservice.service';
import {SETTING} from '../settings/SETTING';


@Injectable()
export class UserService {
  private currentUserID = new BehaviorSubject<number>(0);

  @LocalStorage('USER_ROLE')
  private userRole: any;

  @LocalStorage('USER_PRIVILEGES')
  private privileges: any;

  @LocalStorage('USER')
  private currentUser: any;

  constructor(private router: Router,
              private dataService: DataService,
              private localSt: LocalStorageService) {
  }

  public setUserID(userID) {
    this.currentUserID.next(userID);
  }

  public setUserRole(userRole) {
    this.userRole = userRole;

  }

  public getUserRole() {
    return this.userRole;
  }

  public setPrivileges(privilages) {
    this.privileges = privilages;
  }

  public setUserName(user) {
    this.currentUser = user;
    console.log(user);
  }

  public getPrivileges() {
    return this.privileges;
  }

  public getUserID(): Observable<number> {
    return this.currentUserID.asObservable();
  }

  public clearUserDetails() {
    this.userRole = null;
    this.currentUserID.next(null);
    this.privileges = null;
  }

  logout() {
    this.clearUserDetails();
    this.localSt.store('ACCESS_TOKEN', null);
    this.router.navigateByUrl('/login');
  }

}
