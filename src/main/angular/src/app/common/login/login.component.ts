import {Component, OnInit} from '@angular/core';
import {AlertService} from '../../core/services/alert.service';
import {DataService} from '../../core/services/dataservice.service';
import {Subject} from 'rxjs';
import {SETTING} from '../../core/settings/SETTING';
import {Router} from '@angular/router';
import {UserService} from '../../core/services/user.service';
import {LocalStorage, LocalStorageService} from 'ngx-webstorage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  @LocalStorage('CONSTRUCTION_SITES')
  private constructionSites: any[];
  user = '';
  pass = '';

  constructor(private dataService: DataService, private alert: AlertService, private router: Router,
              private userService: UserService, private localSt: LocalStorageService) {
  }

  ngOnInit() {
  }

  checkLogin(userDetails) {
    const sub: Subject<Boolean> = new Subject();
    const user = {user: userDetails.user, password: userDetails.password, grant_type: 'password'};
    this.dataService.login(SETTING.ENDPOINTS.userLogin, user).subscribe(data => {
      sub.next(true);
      this.dataService.request(SETTING.ENDPOINTS.getLoggedInUser).subscribe(privilege => {
        this.userService.setUserID(privilege.userID);
        this.userService.setPrivileges(privilege.privileges);
        this.userService.setUserRole(privilege.userRole);
        this.userService.setUserName(privilege.userName);
        this.dataService.request(SETTING.ENDPOINTS.sites).subscribe(sites => {
          this.localSt.clear('CONSTRUCTION_SITES');
          this.constructionSites = sites;
        });
      });
    });
    sub.subscribe(data => {
      if (data != false) {
        this.router.navigate(['/app/homePage']);
      }
    });
  }
}
