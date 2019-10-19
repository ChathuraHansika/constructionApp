import {Component, OnInit} from '@angular/core';
import {Employee} from '../../dto/hrm/Employee';
import {EmployeeService} from '../../core/services/hrm/employee.service';
import {AlertService} from '../../core/services/alert.service';
import {LocalStorageService} from 'ngx-webstorage';
import {DatePipe} from '@angular/common';

declare var $: any;

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.scss']
})
export class EmployeeDetailsComponent implements OnInit {
  validateForm: any[];
  searchNic: any;
  searchName: any;

  employeeList: any[];
  employee: Employee;
  jobTypes: any;

  sites: any;
  siteId: any;
  siteName: any;
  workHour: number;
  jobType: any;
  jobTypePayments: any[];
  leaveTypes: any;
  jobTypeLeaves: any[];
  title: any;
  initials: any;
  firstName: any;
  surName: any;
  NICNumber: any;
  dob: any;
  addressLineOne: any;
  addressLineTwo: any;
  city: any;
  country: any;
  contactNo1: any;
  contactNo2: any;
  joinedDate: any;
  status: any;
  epf: any;
  etf: any;
  otrate: any;
  designation: any;
  perDayWorkHour: number;
  noPayDeduct: any;
  desc: any;
  i: any;

  titleTextValue: any = 1;

  constructor(private employeeService: EmployeeService, private alertService: AlertService,
              private localSt: LocalStorageService) {
  }


  ngOnInit() {
    $('.ui.selection.dropdown')
      .dropdown({
        allowAdditions: true
      });
    $('.menu .item').tab();
    this.getJobTypes();
    this.getLeaveTypes();
    this.getSites();
  }


  public getJobTypes() {
    this.employeeService.jobTypes().subscribe(data => {
      this.jobTypes = data;
    });
  }

  public getLeaveTypes() {
    this.employeeService.leaveTypes().subscribe(data => {
      this.leaveTypes = data;
      console.log(this.leaveTypes);
      for (let i = 0; i < data.length; i++) {
        console.log(data[i].reduct_from);
      }
    });
  }


  public getSites() {
    this.sites = this.localSt.retrieve('CONSTRUCTION_SITES');
  }

  public getJobTypeData(job) {
    this.jobType = job.jobTypeId;
    const selected = {
      id: this.jobType
    };
    this.employeeService.jobTypePayments(selected).subscribe(data => {
      this.jobTypePayments = data;
    });
    this.employeeService.jobTypeLeaves(selected).subscribe(data => {
      this.jobTypeLeaves = data;
    });
  }

  public loadEmployees() {
    const site = {
      id: this.siteId
    };
    if (this.siteId === undefined || this.siteId === '') {
      this.alertService.showToaster('Please select site', 'WARNING');
    } else {
      this.employeeService.loadEmployeesForSite(site).subscribe(data => {
        this.employeeList = [];
        this.employeeList = data;
      });
    }
  }

  searchEmployees() {
    if (this.siteId === undefined || this.siteId === '') {
      this.alertService.showToaster('Please select site', 'WARNING');
    } else if (this.searchName === undefined || this.searchName === '') {
      this.alertService.showToaster('Please enter employee name', 'WARNING');
    } else {
      this.searchNic = '';
      const searchParams = {
        site: this.siteId,
        name: this.searchName
      };
      this.employeeService.searchEmployees(searchParams).subscribe(data => {
        if (data.length > 0) {
          this.employeeList = [];
          this.employee = data;
        } else {
          this.alertService.showToaster('there is no employee called ' + this.searchName + ' in ' + this.siteName, 'INFO');
        }
      });
    }
  }

  searchEmployee() {
    if (this.searchNic === undefined || this.searchNic === '') {
      this.alertService.showToaster('Please enter employee nic number and try again', 'WARNING');
    } else {
      this.searchName = '';
      const searchParams = {
        nic: this.searchNic
      };
      this.employeeService.searchEmployeeByNIC(searchParams).subscribe(data => {
        this.employeeList = [];
        if (data === 'UNF') {
          this.alertService.showToaster('This nic number is not registered', 'INFO');
        } else {
          this.employeeList = [];
          this.employeeList = data;
        }
      });
    }
  }

  getTile(titles) {
    this.title = titles;
  }

  getStatusData(status) {
    this.status = status;
  }

  getSiteData(site) {
    this.siteId = site.siteId;
    this.siteName = site.projectName;
  }

  viewSelectedEmployee(employee, value) {
    this.firstName = employee.firstName;
    this.title = employee.title;
    this.initials = employee.initial;
    this.surName = employee.surname;
    this.NICNumber = employee.nicNumber;
    this.addressLineOne = employee.addressLine1;
    this.addressLineTwo = employee.addressLine2;
    this.city = employee.city;
    this.country = employee.country;
    this.perDayWorkHour = employee.perDayWorkHour;
    this.epf = employee.epf;
    this.etf = employee.etf;
    this.otrate = employee.otrate;
    //this.jobType = employee.jobType;
    this.designation = employee.designation;
    this.perDayWorkHour = employee.perDayWorkHour;
    this.noPayDeduct = employee.noPayDeduct;
    this.siteId = employee.siteAssigned;
    this.siteName = employee.siteAssignedName;
    this.workHour = employee.workHour;
    this.contactNo2 = employee.contactNo2;
    this.contactNo1 = employee.contactNo1;
    this.jobTypePayments = employee.salary;
    this.jobTypeLeaves = employee.leaves;
    this.status = employee.status;
    const datePipe = new DatePipe('en-US');
    const formattedDate = datePipe.transform(employee.joinDate, 'yyyy-MM-dd');
    this.joinedDate = formattedDate;
    const datePipe1 = new DatePipe('en-US');
    const dobDate = datePipe1.transform(employee.dateOfBirth, 'yyyy-MM-dd');
    this.dob = dobDate;

    $('#jobType').dropdown('set selected', employee.jobTypeName);
    $('#title').dropdown('set selected', this.title);
    $('#siteID').dropdown('set selected', this.siteName);
    $('#status').dropdown('set selected', this.status);
    $('#joinDate').val(employee.joinDate.split(' ')[0]);
    $('#dob').val(employee.dateOfBirth.split(' ')[0]);
    $('.menu .item').find('.item').tab('change tab', 'first');
  }

  addPaymentRow() {
    const data = {
      amount: '',
      description: '',
      jobType: {jobTypeId: 1, insentive: '5', jobType: 'QA', status: 'ACT'},
      jobTypeSalaryId: '',
      perDay: true,
      perHour: false,
      salaryType: 'ALLOWANCE',
      status: 'ACT'
    };
    this.jobTypePayments.push(data);
  }

  removePaymentRow(i) {
    if (i !== 0 && i !== 1) {
      this.jobTypePayments.splice(i, 1);
    }

  }

  addleaveRow() {
    const data = {
      amount: '',
      id: '',
      job_type: '',
      leave_type: '',
      portion: '',
      reduct_from: '',
      status: 'ACT',
      type: ''
    };
    this.jobTypeLeaves.push(data);

  }

  removeLeaveRow(i: any) {
    if (i !== 0 && i !== 1 && i !== 2) {
      this.jobTypeLeaves.splice(i, 1);
    }

  }

  changeValues(i: number | any, event, type) {
    const jobTypePayment = this.jobTypePayments[i];
    if (type === 'description') {
      const inputValue = event.target.value;
      jobTypePayment.description = inputValue;
    }
    if (type === 'Amount') {
      const amountValue = event.target.value;
      jobTypePayment.amount = amountValue;
    }
    if (type === 'perDay') {
      jobTypePayment.perDay = true;
      jobTypePayment.perHour = false;
    }
    if (type === 'perHour') {
      jobTypePayment.perHour = true;
      jobTypePayment.perDay = false;
    }
    if (type === 'baseAmount') {
      const baseAmountValue = event.target.value;
      jobTypePayment.amount = baseAmountValue;
    }
    this.jobTypePayments[i] = jobTypePayment;
  }


  public getLeaveTypeData(i: number | any, event, type: any) {
    const jobTypeLeave = this.jobTypeLeaves[i];
    if (type === 'leaveTypeValue') {
      const leaveTypeValue = event.target.value;
      switch (leaveTypeValue) {
        case 'SHORT': {
          jobTypeLeave.type = leaveTypeValue;
          jobTypeLeave.leave_type = 1;
          break;
        }
        case 'HALF': {
          jobTypeLeave.type = leaveTypeValue;
          jobTypeLeave.leave_type = 2;
          break;
        }
        case 'LEAVE': {
          jobTypeLeave.type = leaveTypeValue;
          jobTypeLeave.leave_type = 3;
          break;
        }
      }

    }
    if (type === 'portion') {
      jobTypeLeave.portion = event.target.value;
    }
    if (type === 'Amount') {
      jobTypeLeave.amount = event.target.value;
    }
    if (type === 'reductFrom') {
      const leaveTypeValue = event.target.value;
      switch (leaveTypeValue) {
        case 'SHORT': {
          jobTypeLeave.reduct_from = leaveTypeValue;
          break;
        }
        case 'HALF': {
          jobTypeLeave.reduct_from = leaveTypeValue;
          break;
        }
        case 'LEAVE': {
          jobTypeLeave.reduct_from = leaveTypeValue;
          break;
        }
      }

    }
    this.jobTypeLeaves[i] = jobTypeLeave;
  }

  // tab navs
  showPaymentDetails() {
    this.employeeValidation();
  }

  showLeaves() {
    $('.menu .item').find('.item').tab('change tab', 'third');
  }

  showEmployeeDetails() {
    $('.menu .item').find('.item').tab('change tab', 'first');
  }


  checkTitleData() {
    if (this.title === undefined || this.title === null || this.title === '') {
      this.titleTextValue = 0;
    } else {
      this.titleTextValue = 1;
    }
  }

  public employeeValidation() {
    this.validateForm = [];
    this.validateForm.push(this.title);
    this.validateForm.push(this.initials);
    this.validateForm.push(this.firstName);
    this.validateForm.push(this.surName);
    this.validateForm.push(this.addressLineOne);
    this.validateForm.push(this.addressLineTwo);
    this.validateForm.push(this.city);
    this.validateForm.push(this.country);
    this.validateForm.push(this.contactNo1);
    this.validateForm.push(this.contactNo2);
    this.validateForm.push(this.dob);
    this.validateForm.push(this.joinedDate);
    this.validateForm.push(this.NICNumber);
    this.validateForm.push(this.designation);
    this.validateForm.push(this.jobType);
    this.validateForm.push(this.status);
    this.validateForm.push(this.siteId);
    this.validateForm.push(this.epf);
    this.validateForm.push(this.etf);
    this.validateForm.push(this.noPayDeduct);
    this.validateForm.push(this.workHour);
    this.validateForm.push(this.otrate);
    if (this.checkForm()) {
      $('.menu .item').find('.item').tab('change tab', 'second');
    }
  }

  private checkForm() {
    for (const any of this.validateForm) {
      console.log(any);
      if (any === undefined || any.length < 1 || any === '') {
        this.alertService.showToaster('All fields are required', 'ERROR');
        return false;
      }
    }
    return true;
  }


  saveEmployeeData() {
    const employeeDto = new Employee();
    employeeDto.title = this.title;
    employeeDto.initial = this.initials;
    employeeDto.firstName = this.firstName;
    employeeDto.surname = this.surName;
    employeeDto.fullName = this.initials + ' ' + this.firstName + ' ' + this.surName;
    employeeDto.addressLine1 = this.addressLineOne;
    employeeDto.addressLine2 = this.addressLineTwo;
    employeeDto.city = this.city;
    employeeDto.country = this.country;
    employeeDto.contactNo1 = this.contactNo1;
    employeeDto.contactNo2 = this.contactNo2;
    employeeDto.dateOfBirth = this.dob;
    employeeDto.joinDate = this.joinedDate;
    employeeDto.nicNumber = this.NICNumber;
    employeeDto.designation = this.designation;
    employeeDto.jobType = this.jobType;
    employeeDto.status = this.status;
    employeeDto.siteAssigned = this.siteId;
    employeeDto.epf = this.epf;
    employeeDto.etf = this.etf;
    employeeDto.noPayDeduct = this.noPayDeduct;
    employeeDto.workHour = this.workHour;
    employeeDto.otrate = this.otrate;
    employeeDto.salary = this.jobTypePayments;
    employeeDto.leaves = this.jobTypeLeaves;

    if (this.jobTypePayments.length < 1 || this.jobTypeLeaves.length < 1) {
      this.alertService.showToaster('Please check employee payment and leaves', 'ERROR');
      return;
    }
    this.employeeService.saveEmployeeDetails(employeeDto).subscribe(data => {
      this.alertService.showToaster('Account ' + data + ' Successfully', 'SUCCESS');
      this.jobTypePayments = [];
      this.jobTypeLeaves = [];
    });
    this.title = null;
    this.initials = null;
    this.firstName = null;
    this.surName = null;
    this.NICNumber = null;
    this.dob = null;
    this.addressLineOne = null;
    this.addressLineTwo = null;
    this.city = null;
    this.country = null;
    this.contactNo1 = null;
    this.contactNo2 = null;
    this.joinedDate = null;
    this.status = null;
    this.epf = null;
    this.etf = null;
    this.otrate = null;
    this.designation = null;
    this.perDayWorkHour = null;
    this.noPayDeduct = null;
    this.workHour = null;
  }


  getSiteName(siteid) {
    this.sites.forEach(obj => {
      if (obj.siteId === siteid) {
        this.siteName = obj.projectName;
        return;
      }
    });
  }

}



