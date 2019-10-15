import {Component, OnInit} from '@angular/core';
import {ProjectDetailsDto} from '../../dto/ProjectDetailsDto';
import {AlertService} from '../../core/services/alert.service';
import {ProjectService} from '../../core/services/project.service';
import {LocalStorage, LocalStorageService} from 'ngx-webstorage';


@Component({
  selector: 'app-construcion-site-details',
  templateUrl: './construcion-site-details.component.html',
  styleUrls: ['./construcion-site-details.component.scss']
})
export class ConstrucionSiteDetailsComponent implements OnInit {
  dto = new ProjectDetailsDto();
  Project: any;
  sites: any[];
  search: String;
  newSite: any[];
  replaceSite: any;
  @LocalStorage('CONSTRUCTION_SITES')
  private constructionSites: any[];

  constructor(private alertService: AlertService, private projectService: ProjectService, private localSt: LocalStorageService) {
  }

  ngOnInit() {
  }

  searchData() {
    if (this.newSite === undefined) {
      this.getSites();
    }
    this.sites = [];
    if (this.search.length > 0) {
      this.newSite.forEach((site) => {
        if (site.projectId.includes(this.search) || site.projectName.includes(this.search)) {
          this.sites.push(site);
        }
      });
    } else {
      this.sites = this.newSite;
    }
  }


  saveSiteDetail() {
    if (this.validateInputs() === false) {
      this.alertService.showToaster('Please check all inputs', 'WARNING');
      return;
    }
    this.projectService.saveSite(this.dto).subscribe(data => {
      this.alertService.showToaster(data, 'SUCCESS');
      this.getSites();
      this.Clear();
    });

  }

  private validateInputs() {
    return !(this.checkField(this.dto.clientName) === false || this.checkField(this.dto.clientTel) === false ||
      this.checkField(this.dto.clientAddress) === false);
  }

  private checkField(field: String) {
    return !(field === null || field === undefined || field === '');
  }

  public getSites() {
    this.projectService.sites().subscribe(data => {
      this.sites = data;
      this.newSite = data;
      this.replaceSite = data;
      this.localSt.clear('CONSTRUCTION_SITES');
      this.constructionSites = data;
    });
  }

  setProjectData(data) {
    this.dto.projectId = data.projectId;
    this.dto.projectName = data.projectName;
    this.dto.description = data.description;
    this.dto.projectAddressOne = data.addressLine1;
    this.dto.projectAddressTwo = data.addressLine2;
    this.dto.city = data.city;
    this.dto.country = data.country;
    this.dto.projectType = data.projectType;
    this.dto.vatAdded = data.vatAdded;
    this.dto.clientName = data.client.name;
    this.dto.clientAddress = data.client.addressLine;
    this.dto.clientTel = data.client.tel;
    this.dto.clientCity = data.client.city;
    this.dto.clientCountry = data.client.country;
    this.dto.remark = data.client.remarks;
  }

  searchClient() {
    const client = {
      name: this.dto.clientName
    };
    this.projectService.search_client(client).subscribe(data => {
      this.dto.clientAddress = data.addressLine;
      this.dto.clientTel = data.tel;
      this.dto.clientCity = data.city;
      this.dto.clientCountry = data.country;
      this.dto.remark = data.remarks;
    });
  }

  Clear() {
    this.dto = new ProjectDetailsDto();
  }
}
