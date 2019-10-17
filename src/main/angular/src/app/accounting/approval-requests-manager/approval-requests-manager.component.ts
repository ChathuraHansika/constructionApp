import { Component, OnInit } from '@angular/core';
import {LocalStorageService} from "ngx-webstorage";

@Component({
  selector: 'app-approval-requests-manager',
  templateUrl: './approval-requests-manager.component.html',
  styleUrls: ['./approval-requests-manager.component.scss']
})
export class ApprovalRequestsManagerComponent implements OnInit {
banks:any;
  constructor(private localStorage:LocalStorageService) { }

  ngOnInit() {
    this.banks=this.localStorage.retrieve('BANKS');

  }

}
