import {Component, OnInit} from '@angular/core';
import {AccoutingService} from "../../core/services/Accounting/accouting.service";

@Component({
  selector: 'app-bank-accounts-manager',
  templateUrl: './bank-accounts-manager.component.html',
  styleUrls: ['./bank-accounts-manager.component.scss']
})
export class BankAccountsManagerComponent implements OnInit {

  constructor(private accountingService: AccoutingService) {
  }

  banks:any;

  ngOnInit() {
    this.loadBanks();
  }

  loadBanks() {
    this.accountingService.loadBanks().subscribe(data => {
      this.banks=data;
      console.log(this.banks);
    });
  }

}
