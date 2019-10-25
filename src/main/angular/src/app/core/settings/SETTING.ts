export class SETTING {
  public static HTTP_PREFIX = '';
  public static CLIENT_ID = 'clientapp';
  public static SECRET_KEY = '123456';

  public static ENDPOINTS = {
    // COMMON
    userLogin: {
      url: SETTING.HTTP_PREFIX + '/api/oauth/token',
      type: 'POST'
    },
    getLoggedInUser: {
      url: SETTING.HTTP_PREFIX + '/api/user/get-privilege',
      type: 'GET'
    },
    sites: {
      url: SETTING.HTTP_PREFIX + '/api/construction-site/sites',
      type: 'GET'
    },
    searchClient: {
      url: SETTING.HTTP_PREFIX + '/api/construction-site/searchClient',
      type: 'GET'
    },
    saveSites: {
      url: SETTING.HTTP_PREFIX + '/api/construction-site/saveSite',
      type: 'POST'
    },
    checkClientExistsByPhoneNumber: {
      url: SETTING.HTTP_PREFIX + '/api/construction-site/checkClientExistsByPhoneNumber',
      type: 'POST'
    },

    // HR
    saveEmployee: {
      url: SETTING.HTTP_PREFIX + '/api/employee/create',
      type: 'POST'
    },
    loadEmployees: {
      url: SETTING.HTTP_PREFIX + '/api/employee/searchBySite',
      type: 'GET'
    },
    searchEmployeesByNIC: {
      url: SETTING.HTTP_PREFIX + '/api/employee/searchByNic',
      type: 'GET'
    },
    searchEmployeeByNameOrNIC: {
      url: SETTING.HTTP_PREFIX + '/api/employee/searchByNameOrNic',
      type: 'GET'
    },
    searchEmployees: {
      url: SETTING.HTTP_PREFIX + '/api/employee/searchByNameAndSite',
      type: 'GET'
    },
    searchMonthlyPayment: {
      url: SETTING.HTTP_PREFIX + '/api/employee/searchMonthlyPayment',
      type: 'POST'
    },
    saveMonthlyPayment: {
      url: SETTING.HTTP_PREFIX + '/api/employee/saveMonthlyPayment',
      type: 'POST'
    },
    jobTypes: {
      url: SETTING.HTTP_PREFIX + '/api/employee/jobTypes',
      type: 'GET'
    },
    jobTypesSalaries: {
      url: SETTING.HTTP_PREFIX + '/api/employee/jobTypePayments',
      type: 'GET'
    },
    leaveTypes: {
      url: SETTING.HTTP_PREFIX + '/api/employee/LeaveTypes',
      type: 'GET'
    },
    jobTypeLeaves: {
      url: SETTING.HTTP_PREFIX + '/api/employee/JobTypeLeave',
      type: 'GET'
    },
    searchDailyAttendanceBySiteId: {
      url: SETTING.HTTP_PREFIX + '/api/employee/searchDailyAttendanceBySiteId',
      type: 'GET'
    },
    searchByIdAndDate: {
      url: SETTING.HTTP_PREFIX + '/api/employee/searchByIdAndDate',
      type: 'GET'
    },
    searchById: {
      url: SETTING.HTTP_PREFIX + '/api/employee/searchById',
      type: 'GET'
    },
    saveAttendanceInData: {
      url: SETTING.HTTP_PREFIX + '/api/employee/saveAttendanceInData',
      type: 'POST'
    },
    saveAttendanceOutData: {
      url: SETTING.HTTP_PREFIX + '/api/employee/saveAttendanceOutData',
      type: 'POST'
    },
    searchDailyAttendanceByAllSiteIdAndDate: {
      url: SETTING.HTTP_PREFIX + '/api/employee/searchDailyAttendanceByAllSiteIdAndDate',
      type: 'POST'
    },
    checkLeave: {
      url: SETTING.HTTP_PREFIX + '/api/employee/checkLeave',
      type: 'GET'
    },

    getUnsavedAttendance: {
      url: SETTING.HTTP_PREFIX + '/api/employee/getUnsavedAttendance',
      type: 'GET'
    },
    getPayrolls: {
      url: SETTING.HTTP_PREFIX + '/api/employee/payrolls',
      type: 'GET'
    },
    getPayrollByMonthAndYear: {
      url: SETTING.HTTP_PREFIX + '/api/employee/payrollsByMonthAndYear',
      type: 'GET'
    },
    loadBanks: {
      url: SETTING.HTTP_PREFIX + '/api/accounting/loadBanks',
      type: 'GET'
    },
    loadAccountTypes: {
      url: SETTING.HTTP_PREFIX + '/api/accounting/loadAccountTypes',
      type: 'GET'
    },
    saveAccount: {
      url: SETTING.HTTP_PREFIX + '/api/accounting/saveAccount',
      type: 'POST'
    },
    loadAccounts: {
      url: SETTING.HTTP_PREFIX + '/api/accounting/loadAccounts',
      type: 'GET'
    }, accountSearchByBankId: {
      url: SETTING.HTTP_PREFIX + '/api/accounting/accountSearchByBankId',
      type: 'GET'
    },
    findAccountExits:{
      url: SETTING.HTTP_PREFIX + '/api/accounting/findAccountExits',
      type: 'GET'
    }


  }
}
