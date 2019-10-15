import {Component, OnInit} from '@angular/core';
import {EmployeePayment} from '../../../dto/hrm/EmployeePayment';
import {LocalStorageService} from 'ngx-webstorage';
import {PaysheetData} from '../../../dto/hrm/PaysheetData';

@Component({
  selector: 'app-pay-sheet',
  templateUrl: './pay-sheet.component.html',
  styleUrls: ['./pay-sheet.component.scss'],
})
export class PaySheetComponent implements OnInit {
  employee: EmployeePayment = new EmployeePayment();
  netSalary: any;
  data: PaysheetData = new PaysheetData();

  constructor(private localSt: LocalStorageService) {
  }

  ngOnInit() {
    this.employee = this.localSt.retrieve('PAYMENT_DETAILS');
    this.netSalary = this.localSt.retrieve('NET_SALARY');
    this.data.data = this.employee;
    this.data.netSalary = this.netSalary;
    console.log(this.employee);
  }
}
