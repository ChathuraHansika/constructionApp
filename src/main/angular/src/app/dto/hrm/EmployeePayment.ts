import {EmployeePaymentDetails} from './EmployeePaymentDetails';

export class EmployeePayment {
  empID: number;
  payrollID: number;
  siteID: number;
  jobTypeID: number;
  salaryTypeID: number;
  req_by: number;
  approved_by: number;
  title: string;
  fullName: string;
  siteName: string;
  designation: string;
  jobType: string;
  salaryType: string;
  date: string;
  month: string;
  year: string;
  epf: string;
  etf: string;
  noPayDeduct: string;
  approval: boolean;
  details: EmployeePaymentDetails[];
}
