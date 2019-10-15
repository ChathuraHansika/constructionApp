import {Payment} from './Payment';
import {Leaves} from './Leaves';

export class Employee {
  public employeeId: number;
  public siteAssigned: number;
  public siteAssignedName: number;
  public title: string;
  public initial: string;
  public firstName: string;
  public surname: string;
  public fullName: string;
  public nicNumber: string;
  public dateOfBirth: string;
  public addressLine1: string;
  public addressLine2: string;
  public city: string;
  public country: string;
  public contactNo1: string;
  public contactNo2: string;
  public joinDate: string;
  public jobType: number;
  public jobTypeName: string;
  public designation: string;
  public workHour: number;
  public totalSalary: number;
  public totalBaseSalary: number;
  public totalAllowanceSalary: number;
  public noPayDeduct: number;
  public etf: number;
  public epf: number;
  public otrate: number;
  public status: string;
  public salary: Payment[];
  public leaves: Leaves[];


}
