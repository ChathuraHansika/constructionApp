import {Injectable} from '@angular/core';
import {DataService} from "../dataservice.service";
import {Observable} from "rxjs";
import {SETTING} from "../../settings/SETTING";

@Injectable({
  providedIn: 'root'
})
export class AccoutingService {

  constructor(private dataService: DataService) {
  }

  public loadBanks(): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.loadBanks);
  }

  public loadAccountTypes(data: any) {
    return this.dataService.request(SETTING.ENDPOINTS.loadAccountTypes, data);
  }

  public saveAccount(data: any) {
    return this.dataService.request(SETTING.ENDPOINTS.saveAccount, data);
  }

  public loadAccounts() {
    return this.dataService.request(SETTING.ENDPOINTS.loadAccounts);
  }


  accountSearchByBankId(data: any) {
return this.dataService.request(SETTING.ENDPOINTS.accountSearchByBankId,data);
  }

  findAccountExits(data: any) {
    return this.dataService.request(SETTING.ENDPOINTS.findAccountExits,data);
  }
}
