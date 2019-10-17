import {Component, OnInit} from '@angular/core';
import {AccoutingService} from "../../core/services/Accounting/accouting.service";
import {LocalStorage, LocalStorageService} from "ngx-webstorage";

@Component({
  selector: 'app-bank-accounts-manager',
  templateUrl: './bank-accounts-manager.component.html',
  styleUrls: ['./bank-accounts-manager.component.scss']
})
export class BankAccountsManagerComponent implements OnInit {

  constructor(private accountingService: AccoutingService, private localStorage: LocalStorageService) {
  }

  @LocalStorage('BANKS')
  banks: any;
  sites: any;
  accountTypes: Array<String> = [];
  accountStatus: Array<String> = [];


  ngOnInit() {
    this.loadBanks();
    this.sites = this.localStorage.retrieve('CONSTRUCTION_SITES');
  }

  loadBanks() {
    this.accountingService.loadBanks().subscribe(data => {
      this.banks = data;
    });
  }

  loadAccountTypesAndStatus(value: any) {
    const data = {bankId: value};
    this.accountTypes = [];
    this.accountStatus = [];
    this.accountingService.loadAccountTypes(data).subscribe(data => {
      console.log(data);
      for (let i = 0; i < data.length; i++) {
        this.accountTypes.push(data[i].type.toString());
        this.accountStatus.push(data[i].status.toString());
      }
    })
  }
}
