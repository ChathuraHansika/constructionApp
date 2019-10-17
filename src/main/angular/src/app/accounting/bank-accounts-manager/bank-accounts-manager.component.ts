import {Component, OnInit} from '@angular/core';
import {AccoutingService} from "../../core/services/Accounting/accouting.service";
import {LocalStorage, LocalStorageService} from "ngx-webstorage";
import {BankDetailDto} from "../../dto/accounting/BankDetailDto";
import {validate} from "codelyzer/walkerFactory/walkerFn";
import {AlertService} from "../../core/services/alert.service";

@Component({
  selector: 'app-bank-accounts-manager',
  templateUrl: './bank-accounts-manager.component.html',
  styleUrls: ['./bank-accounts-manager.component.scss']
})
export class BankAccountsManagerComponent implements OnInit {

  constructor(private accountingService: AccoutingService, private localStorage: LocalStorageService
    , private alertService: AlertService) {
  }

  @LocalStorage('BANKS')
  banks: any;
  sites: any;
  bankDetailDto = new BankDetailDto();


  ngOnInit() {
    this.loadBanks();
    this.sites = this.localStorage.retrieve('CONSTRUCTION_SITES');
    console.log(this.sites);
  }

  loadBanks() {
    this.accountingService.loadBanks().subscribe(data => {
      this.banks = data;
    });
  }


  save() {
    if (!this.validation()) {
      this.alertService.showToaster("Fill All Inputs", "ERROR")
    } else {
      this.accountingService.saveAccount(this.bankDetailDto).subscribe(data => {

      });
      this.alertService.showToaster("Success", "INFO")
    }
  }

  private validation = () => this.bankDetailDto.branch !== undefined &&
    this.bankDetailDto.status !== undefined &&
    this.bankDetailDto.type !== undefined &&
    this.bankDetailDto.bankId !== undefined &&
    this.bankDetailDto.balance !== undefined &&
    this.bankDetailDto.accountName !== undefined &&
    this.bankDetailDto.accountNumber !== undefined &&
    this.bankDetailDto.siteId !== undefined;

  getBankId(value: any) {
    this.bankDetailDto.bankId = value !== "0" ? value : undefined;
  }

  getAccountType(value: any) {
    this.bankDetailDto.type = value !== "0" ? value : undefined;
  }

  getAccountStatus(value: any) {
    this.bankDetailDto.status = value !== "0" ? value : undefined;
  }

  getSiteID(value: any) {
    alert(value);
    this.bankDetailDto.siteId = value !== "0" ? value : undefined;
  }
}
