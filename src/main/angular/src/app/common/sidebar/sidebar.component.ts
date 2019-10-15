import {Component, OnInit} from '@angular/core';
import {UserService} from '../../core/services/user.service';

declare var $: any;

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  image = require('../../../assets/rachel.png');

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    $('.ui.accordion')
      .accordion({
        selector: {
          trigger: '.title'
        }
      })
    ;
  }

  logout() {
    this.userService.logout();
  }

  logoutModal() {
    $('.ui.basic.modal')
      .modal('show')
    ;
  }

  openUserModal() {
    $('.ui.modal.user')
      .modal({
        blurring: true
      })
      .modal('show')
    ;
  }
}
