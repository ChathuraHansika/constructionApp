import {Injectable} from '@angular/core';
import {DataService} from './dataservice.service';
import {Observable} from 'rxjs';
import {SETTING} from '../settings/SETTING';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  constructor(private dataService: DataService) {
  }

  public search_client(name: any): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.searchClient, name);
  }

  public sites(): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.sites, null);
  }

  public saveSite(details: any): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.saveSites, details);
  }
}
