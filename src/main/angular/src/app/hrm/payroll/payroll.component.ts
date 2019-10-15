import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../../core/services/hrm/employee.service';
import {AlertService} from '../../core/services/alert.service';
import {EmployeePayment} from '../../dto/hrm/EmployeePayment';
import {EmployeePaymentDetails} from '../../dto/hrm/EmployeePaymentDetails';
import {LocalStorage} from 'ngx-webstorage';
import {DatePipe} from '@angular/common';

declare var $: any;

@Component({
  selector: 'app-payroll',
  templateUrl: './payroll.component.html',
  styleUrls: ['./payroll.component.scss']
})
export class PayrollComponent implements OnInit {
  searchName: any = '';
  employee: EmployeePayment = new EmployeePayment();
  sal_details: EmployeePaymentDetails[] = [];
  tableData: EmployeePayment[] = [];
  toBePaid = 0;
  sal = 0;
  finalAmount: any;
  deduct = 0;
  message = '';
  currentYear: any;
  approval: boolean;
  @LocalStorage('PAYMENT_DETAILS')
  private paymentDetails: EmployeePayment;

  @LocalStorage('NET_SALARY')
  private netSalary: EmployeePayment;

  constructor(private employeeService: EmployeeService, private alert: AlertService) {
  }

  ngOnInit() {
    this.currentYear = new Date().getFullYear();
    this.employee.year = this.currentYear;
    this.findAllPayments();
  }

  private findAllPayments() {
    this.employeeService.allPayroll().subscribe(data => {
      this.tableData = data;
    });
  }


  searchEmployee() {
    this.employee = new EmployeePayment();
    this.sal_details = [];
    const user = {
      user: this.searchName
    };
    this.employeeService.searchEmployeeByNameOrNIC(user).subscribe(data => {
      if (data === 'UNF') {
        this.message = 'Invalid employee name or nic! Try again.';
        this.alert.showToaster('Your input did not match with any employee.\n Please try Again.', 'INFO');
      } else {
        this.searchName = '';
        this.employee = data;
        this.getSalaryDetails(this.employee.details);
        this.totalToBePaid();
        this.message = 'Please select payment month and year.';
        if (data.date !== null) {
          $('#p_date').val(this.employee.date.split(' ')[0]);
        }
      }
    });
  }

  private getSalaryDetails(details: EmployeePaymentDetails[]) {
    this.sal_details = [];
    if (details !== null && details.length > 0) {
      for (const detail of details) {
        // @ts-ignore
        detail.amount = detail.amount.toFixed(2);
        this.sal_details.push(detail);
      }
    }
  }

  public removePayment(payment) {
    if (payment.status === 'ACT') {
      payment.status = 'INA';
      this.alert.showToaster(payment.desc.toUpperCase() + ' REMOVED!', 'WARNING');
    } else {
      payment.status = 'ACT';
      this.alert.showToaster(payment.desc.toUpperCase() + ' ADDED!', 'SUCCESS');
    }

    this.sal_details = [];
    for (const detail of this.employee.details) {
      if (detail.desc.toUpperCase() === payment.desc.toUpperCase()) {
        detail.status = payment.status;
      }
      this.sal_details.push(detail);
    }
    this.totalToBePaid();
  }

  totalToBePaid() {
    this.deduct = 0;
    this.sal = 0;
    this.toBePaid = 0;
    this.finalAmount = 0;
    if (this.employee.details === null || this.employee.details === undefined || this.employee.details.length < 1) {
      return;
    }

    for (const payment of this.employee.details) {
      if (payment.status === 'INA') {
        continue;
      }
      if (payment.desc === 'NO PAY DEDUCT') {
        // tslint:disable-next-line:radix
        this.deduct = parseInt(String(payment.amount));
      } else {
        this.sal = parseInt(String(payment.amount), 0);
        this.toBePaid = this.toBePaid + this.sal;
      }
    }
    // tslint:disable-next-line:radix
    this.toBePaid = this.toBePaid - parseInt(this.employee.epf);
    this.toBePaid = this.toBePaid - this.deduct;
    this.finalAmount = parseFloat(this.toBePaid + '').toFixed(2);
    this.netSalary = this.finalAmount;
  }

  setMonth(month) {
    this.employee.month = month;
    if (this.allParamSet()) {
      this.employee.details = [];
      this.employeeService.searchMonthlyPayment(this.employee).subscribe(data => {
        this.employee = new EmployeePayment();
        this.sal_details = [];
        this.employee = data;
        this.getSalaryDetails(this.employee.details);

        if (!(this.employee.date === null || this.employee.date === undefined)) {
          this.employee.date = this.employee.date.split(' ')[0].replace('-', '/');
        }
        this.approval = data.approval;
        if (data.approval === true) {
          this.setPrintData();
        }

        this.message = this.employee.details.length < 1 ? 'This employee has no attendances for selected month' : '';
        if (this.employee.details.length <= 0) {
          return;
        }
        this.totalToBePaid();
      });
    }
  }

  private setPrintData() {
    this.paymentDetails = this.employee;
  }

  setYear(year) {
    this.employee.year = year;
    if (this.allParamSet()) {
      this.employee.details = [];
      this.employeeService.searchMonthlyPayment(this.employee).subscribe(data => {
        this.employee = new EmployeePayment();
        this.sal_details = [];
        this.employee = data;
        this.getSalaryDetails(this.employee.details);

        if (!(this.employee.date === null || this.employee.date === undefined)) {
          this.employee.date = this.employee.date.split(' ')[0].replace('-', '/');
        }
        this.approval = data.approval;
        if (data.approval === true) {
          this.setPrintData();
        }

        this.message = this.employee.details.length < 1 ? 'This employee has no attendances for selected month' : '';
        if (this.employee.details.length <= 0) {
          return;
        }
        this.totalToBePaid();
      });
    }
  }

  private allParamSet() {
    if ((new Date()).getFullYear() < Number(this.employee.year)) {
      this.alert.showToaster('Please make sure that selected year is correct', 'WARNING');
    }

    if (this.employee.empID === undefined || this.employee.empID < 1) {
      this.alert.showToaster('No employee specified! \n Use nic number or name for find employee.', 'WARNING');
    }
    // tslint:disable-next-line:max-line-length
    return this.employee.year !== null && this.employee.year !== undefined && this.employee.month !== null && this.employee.month !== undefined;
  }

  savePayment() {
    let status = 'waiting for approval';
    if (this.employee.approval) {
      status = 'approved';
    }
    if (this.validateForm(this.employee)) {
      this.employeeService.saveEmployeePayment(this.employee).subscribe(data => {
        if (data === true) {
          this.alert.showToaster('Payment is ' + status, 'SUCCESS');
          this.employee = new EmployeePayment();
          this.sal_details = [];
        }
      });
    }
    this.findAllPayments();
  }

  private validateForm(employee: EmployeePayment) {
    if (this.hasError(employee.empID)) {
      this.alert.showToaster('Can not identify employee', 'WARNING');
      return false;
    } else if (this.hasError(employee.month)) {
      this.alert.showToaster('Please select payment month', 'WARNING');
      return false;
    } else if (this.hasError(employee.year)) {
      this.alert.showToaster('Please select payment year', 'WARNING');
      return false;
    } else if (this.hasError(employee.date)) {
      this.alert.showToaster('Please select payment date', 'WARNING');
      return false;
    } else if (employee.details.length < 1) {
      this.alert.showToaster('There is no payments for this employee', 'WARNING');
      return false;
    } else {
      return true;
    }
  }

  private hasError(param: any) {
    return param === null || param === undefined;
  }

  viewEmployee(payroll: EmployeePayment) {
    this.employee = payroll;
    const datePipe = new DatePipe('en-US');
    const formattedDate = datePipe.transform(this.employee.date, 'yyyy-MM-dd');
    this.employee.date = formattedDate;
    this.sal_details = payroll.details;
    this.approval = payroll.approval;
    $('#yeadDrop').dropdown('set selected', payroll.year);
    $('#monthDrop').dropdown('set selected', payroll.month);
    let tot = 0.00;
    for (const detail of payroll.details) {
      tot += detail.amount;
    }
    tot = (tot - parseFloat(this.employee.epf));
    this.finalAmount = tot.toFixed(2);
  }
}
