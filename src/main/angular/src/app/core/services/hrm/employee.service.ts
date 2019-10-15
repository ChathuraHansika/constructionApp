import {Injectable} from '@angular/core';
import {Employee} from '../../../dto/hrm/Employee';
import {Observable, throwError} from 'rxjs';
import {SETTING} from '../../settings/SETTING';
import {DataService} from '../dataservice.service';
import {EmployeePayment} from '../../../dto/hrm/EmployeePayment';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private dataService: DataService) {
  }

  public saveEmployeeDetails(employee: Employee): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.saveEmployee, employee);
  }

  public saveEmployeePayment(payment: EmployeePayment): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.saveMonthlyPayment, payment);
  }

  public jobTypes(): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.jobTypes, null);
  }

  public jobTypePayments(data: any): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.jobTypesSalaries, data);
  }

  public leaveTypes(): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.leaveTypes, null);
  }

  public jobTypeLeaves(data: any): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.jobTypeLeaves, data);
  }

  public loadEmployeesForSite(data: any): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.loadEmployees, data);
  }

  public searchEmployees(data: any): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.searchEmployees, data);
  }

  public searchEmployeeByNIC(data: any): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.searchEmployeesByNIC, data);
  }

  public searchEmployeeByNameOrNIC(data: any): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.searchEmployeeByNameOrNIC, data);
  }

  public searchMonthlyPayment(data: any): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.searchMonthlyPayment, data);
  }

  public searchByIdAndDate(data: any): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.searchByIdAndDate, data);
  }

  public searchById(data: any): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.searchById, data);
  }

  public saveAttendanceInData(data: any): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.saveAttendanceInData, data);
  }

  public saveAttendanceOutData(data: any): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.saveAttendanceOutData, data);
  }

  public searchDailyAttendanceByAllSiteIdAndDate(data: any): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.searchDailyAttendanceByAllSiteIdAndDate, data);
  }

  public checkLeave(data: any): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.checkLeave, data);
  }

  public getUnsavedAttendance(): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.getUnsavedAttendance, null);
  }

  public allPayroll(): Observable<any> {
    return this.dataService.request(SETTING.ENDPOINTS.getPayrolls);

  }


}
