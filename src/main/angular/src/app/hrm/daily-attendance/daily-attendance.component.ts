import {Component, OnInit} from '@angular/core';
import {LocalStorageService} from 'ngx-webstorage';
import {EmployeeService} from '../../core/services/hrm/employee.service';
import {EmployeeAttendanceDto} from '../../dto/hrm/EmployeeAttendanceDto';
import {AlertService} from '../../core/services/alert.service';

declare var $: any;

@Component({
  selector: 'app-daily-attendance',
  templateUrl: './daily-attendance.component.html',
  styleUrls: ['./daily-attendance.component.scss']
})
export class DailyAttendanceComponent implements OnInit {


  constructor(private lss: LocalStorageService, private employeeService: EmployeeService, private alertService: AlertService) {
  }

  user: any;
  sites: any;
  siteId: any;
  siteAttendance: any;
  totalPresent: any;
  timeIn: any;
  attendance: any = 'Present';
  totalAbsent: any;
  siteTotal: any;
  employeeLeaveYear: any[];
  attendanceDate: any;
  timeInTypeValue: any;
  timeOutTypeValue: any;
  leaveTypeId: any = 0;
  extraHours: any;
  wage: any;
  buttonDisabled: any = false;
  disableoutbtn: any = false;
  inTimeTxtDisbale: any = false;
  dayWorkTime: any;
  workHours: any;
  empYearLeave: any[];
  empLeave: any[];
  empname: any;
  employeeJob: any;
  hasNoLeave = false;
  checkTimeOut: any;
  checkTimeIn: any;

  ngOnInit() {
    $('.ui.scrolling.dropdown')
      .dropdown({
        allowAdditions: true
      });
    this.getSites();
    this.user = this.lss.retrieve('USER');
    this.getUnmarkedAttendance();
  }


  private getSites() {
    this.sites = this.lss.retrieve('CONSTRUCTION_SITES');
  }

  getUnmarkedAttendance() {
    this.employeeService.getUnsavedAttendance().subscribe(data => {
      if (data === 'yes') {
        this.alertService.showToaster('Unmarked Attendance Exists,Please Mark', 'WARNING');
      }
    });
  }


  searchById(siteId: any) {
    if (this.attendanceDate !== undefined) {
      if (siteId !== '-1') {
        this.siteId = siteId;
        const val = {siteId: siteId, attendanceDate: this.attendanceDate};
        this.employeeService.searchByIdAndDate(val).subscribe(data => {
          this.siteAttendance = data;
          this.totalPresent = this.siteAttendance[0].totalPresent;
          this.totalAbsent = this.siteAttendance[0].totalAbsent;
          this.siteTotal = this.siteAttendance[0].siteTotal;
          this.employeeLeaveYear = data.employeeLeave;
          const systemDate = new Date().getFullYear() + '-' + String(new Date().getMonth() + 1).padStart(2, '0')
            + '-' + new Date().getDate();
        });
      } else {
        this.alertService.showToaster('Please Select A Site', 'WARNING');
      }
    } else {
      this.siteId = siteId;
      if (this.siteId === '-1') {
        this.alertService.showToaster('Please Select A Site', 'WARNING');
      } else {
        const site = {id: siteId};
        this.employeeService.searchById(site).subscribe(data => {
          this.siteAttendance = data;
          this.employeeLeaveYear = data.employeeLeave;
        });
      }
    }
  }


  searchByIdAndDate(date) {
    if (date === '' || date.length < 1) {
      this.alertService.showToaster('Please Select A Valid Date', 'WARNING');
      this.attendanceDate = undefined;
      return;
    }
    this.checkTimeIn = '';
    if (date.length > 0) {
      this.attendanceDate = date;
      if (this.siteId === '-1' || this.siteId === undefined) {
        this.alertService.showToaster('Please Select A Site', 'WARNING');
      } else {
        const val = {siteId: this.siteId, attendanceDate: date};
        this.employeeService.searchByIdAndDate(val).subscribe(data => {
          this.siteAttendance = data;
          this.totalPresent = this.siteAttendance[0].totalPresent;
          this.totalAbsent = this.siteAttendance[0].totalAbsent;
          this.siteTotal = this.siteAttendance[0].siteTotal;
          this.employeeLeaveYear = data.employeeLeave;
          const systemDate = new Date().getFullYear() + '-' + String(new Date().getMonth() + 1).padStart(2, '0')
            + '-' + new Date().getDate();
          if (date === systemDate) {
            this.buttonDisabled = false;
            this.disableoutbtn = false;
          }
        });
        console.log(this.siteAttendance);
      }
    }
  }


  getAttendanceValue(attendanceValue, leaveData) {
    this.attendance = attendanceValue;
    if (attendanceValue === 'Absent') {
      leaveData.inTimeTxtDisbale = true;
      this.leaveTypeId = 3;
      const val = {
        empId: leaveData.employeeId,
        leaveId: '3',
      };
      this.employeeService.checkLeave(val).subscribe(data => {
        if (data === '0') {
          this.alertService.showToaster('Sick Leave Over,Deduct By Salary', 'WARNING');
          this.leaveTypeId = 3;
          this.inTimeTxtDisbale = true;
        }
      });
    } else {
      leaveData.inTimeTxtDisbale = false;
      this.leaveTypeId = 0;
    }
  }

  calculateWage(value, siteattendance) {
    siteattendance.noPayDeduct = value;
    siteattendance.wage = +siteattendance.wage - +value;
    this.wage = siteattendance.wage;
  }

  getTimeInTypeValue(timeIn) {
    this.timeInTypeValue = timeIn;
    this.checkTimeIn = timeIn;
  }

  getTimeOutTypeValue(timeOut, siteattendance) {

    if (timeOut.length < 1) {
      return;
    }
    this.checkTimeOut = timeOut;
    if (timeOut === '') {
      this.alertService.showToaster('Please Insert Time Out', 'WARNING');
    } else {
      let inTime: any;
      this.timeOutTypeValue = timeOut;
      if (this.timeInTypeValue === undefined) {
        inTime = siteattendance.timeIn.split(':', 1);
      } else {
        inTime = +this.timeInTypeValue.split(':', 1);
      }
      const outTime = +this.timeOutTypeValue.split(':', 1);

      const dayWorkTime = +outTime - +inTime;
      this.dayWorkTime = dayWorkTime;
      const workHours = +siteattendance.perDayWorkHour;
      this.workHours = workHours;
      siteattendance.extraHours = +dayWorkTime - +workHours;

      if (siteattendance.extraHours <= 0) {
        if (this.dayWorkTime < this.workHours) {
          this.alertService.showToaster('Lower than usually  Day work hours,Please select Leave', 'WARNING');
          siteattendance.extraHours = 0;
        }
        this.extraHours = siteattendance.extraHours;
      } else {
        this.extraHours = siteattendance.extraHours;
      }

      const realWage = +siteattendance.perDayWage + +siteattendance.perHourWage * +(dayWorkTime - +siteattendance.extraHours);
      // console.log(realWage);
      siteattendance.wage = realWage;
      this.wage = siteattendance.wage;
      if (siteattendance.extraHours > 0) {
        siteattendance.wage = +realWage + (+siteattendance.extraHours * +siteattendance.overTimeRatePerHour);
        this.wage = siteattendance.wage;
      } else {
        this.wage = 0;
      }
    }
  }

  getLeaveValue(value: any, leavedata) {


    if (value !== 'NONE') {
      const val = {
        empId: leavedata.employeeId,
        leaveId: value
      };
      this.employeeService.checkLeave(val).subscribe(data => {
        if (data === '0') {
          this.alertService.showToaster('This Type Of Leave Over, Deduct by Salary ', 'WARNING');
          leavedata.noPayDeductTxtVisible = true;
          leavedata.nopaytxt = false;
          this.leaveTypeId = value;
          this.hasNoLeave = true;
        } else {
          leavedata.noPayDeductTxtVisible = false;
          leavedata.nopaytxt = true;
          this.leaveTypeId = value;
          this.hasNoLeave = false;
        }
      });

    }
  }

  saveAttendanceIn(data) {
    if (this.validate('in') === 0) {
    } else if (this.checkTimeIn === '' && this.attendance === 'Present') {
      this.alertService.showToaster('Please Insert Time In Value', 'WARNING');
    } else {
      if (this.attendance === 'Absent') {
        data.disableoutbtn = true;
      } else {
        data.disableoutbtn = false;
      }
      data.disable = true;

      const dto = new EmployeeAttendanceDto();
      dto.attendance = this.attendance;
      dto.attendanceDate = this.attendanceDate;
      dto.siteId = this.siteId;
      dto.timeIn = this.timeInTypeValue;
      dto.employeeId = data.employeeId;
      dto.employeeName = data.employeeName;
      dto.employeeJob = data.employeeJob;
      dto.leaveTypeId = this.leaveTypeId;
      dto.dateMarked = new Date().getFullYear() + '-' + (new Date().getMonth() + 1)
        + '-' + new Date().getDate();
      dto.user = this.user;
      dto.timeMarked = new Date().getHours() + ':' + new Date().getMinutes() + ':'
        + new Date().getSeconds();
      dto.status = this.attendance;
      this.employeeService.saveAttendanceInData(dto).subscribe(res => {
        this.checkTimeIn = '';
        if (res[0].totalPresent !== '0' && res[0].totalPresent !== '0') {
          this.totalPresent = res[0].totalPresent;
          this.siteTotal = res[0].siteTotal;
        }
        this.siteTotal = res[0].siteTotal;
        this.totalAbsent = res[0].totalAbsent;

        this.attendance = 'Present';
        this.buttonDisabled = true;
        this.leaveTypeId = 0;

      });
    }
  }


  saveAttendanceOut(data) {
    if (this.checkTimeOut === '') {
      this.alertService.showToaster('Please Insert Time Out ', 'WARNING');
    } else if (this.validate('Out') === 0) {

    } else if (this.dayWorkTime < this.workHours && (this.leaveTypeId <= 0 || (this.hasNoLeave === true && data.noPayDeduct === null))) {
      this.alertService.showToaster('Please Select Leave or Enter Deduct Value', 'WARNING');
    } else {
      data.disableoutbtn = true;
      const employeeAttendanceOut = new EmployeeAttendanceDto();
      employeeAttendanceOut.employeeId = data.employeeId;
      employeeAttendanceOut.employeeName = data.employeeName;
      employeeAttendanceOut.employeeJob = data.employeeJob;
      employeeAttendanceOut.dateMarked = new Date().getFullYear() + '-' + (new Date().getMonth() + 1)
        + '-' + new Date().getDate();
      employeeAttendanceOut.timeMarked = new Date().getHours() + ':' + new Date().getMinutes() + ':'
        + new Date().getSeconds();
      employeeAttendanceOut.timeOut = this.timeOutTypeValue;
      employeeAttendanceOut.leaveTypeId = this.leaveTypeId;
      employeeAttendanceOut.extraHours = this.extraHours;
      employeeAttendanceOut.attendanceDate = this.attendanceDate;
      employeeAttendanceOut.user = this.user;
      employeeAttendanceOut.wage = this.wage;
      employeeAttendanceOut.isTimeOutSaved = true;

      this.employeeService.saveAttendanceOutData(employeeAttendanceOut).subscribe(res => {
        if (res === 'EmployeeTimeOutSaved') {
        } else if (res === 'EmployeeLeaveAdded') {
        } else if (res === 'EmployeeOutSaved') {
        }
        this.disableoutbtn = true;
        this.leaveTypeId = 0;
        this.wage = 0;
        this.checkTimeOut = '';
      });
    }
  }

  absentModal() {
    $('.tiny.modal')
      .modal('show')
    ;
  }


  validate(data: any) {

    if (this.siteId === '0') {
      this.alertService.showToaster('Please Select  Site', 'WARNING');
      return 0;
    } else if (data === 'in') {
      if (this.attendanceDate === undefined) {
        this.alertService.showToaster('Please Select Date', 'WARNING');
        return 0;
      } else if (this.timeIn === undefined && this.timeInTypeValue === undefined && this.attendance !== 'Absent') {
        this.alertService.showToaster('Please Insert Time In ', 'WARNING');
        return 0;
      } else {
        return 1;
      }
    } else {
      if (this.timeOutTypeValue === undefined) {
        this.alertService.showToaster('Please Insert Time Out ', 'WARNING');
        return 0;
      } else {
        return 1;
      }
    }
  }

  showLeaveData(lydto, ldto, name, data) {
    this.empYearLeave = lydto;
    this.empLeave = ldto;
    this.empname = name;
    this.employeeJob = data.employeeJob;
  }
}
