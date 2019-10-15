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
}