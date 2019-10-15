import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {DailyMaterialUsageComponent} from './accounting/daily-material-usage/daily-material-usage.component';
import {HomePageComponent} from './common/home-page/home-page.component';
import {DailyAttendanceComponent} from './hrm/daily-attendance/daily-attendance.component';
import {ApprovalRequestsManagerComponent} from './accounting/approval-requests-manager/approval-requests-manager.component';
import {IncomeExpenseReportComponent} from './report/income-expense-report/income-expense-report.component';
import {ExpensesManagerComponent} from './accounting/expenses-manager/expenses-manager.component';
import {IncomeManagerComponent} from './accounting/income-manager/income-manager.component';
import {SupplierPaymentsComponent} from './accounting/supplier-payments/supplier-payments.component';
import {GoodReceiveNoteComponent} from './accounting/good-receive-note/good-receive-note.component';
import {PurchaseOrderManagerComponent} from './accounting/purchase-order-manager/purchase-order-manager.component';
import {PurchaseRequestManagerComponent} from './accounting/purchase-request-manager/purchase-request-manager.component';
import {SupplierManagerComponent} from './accounting/supplier-manager/supplier-manager.component';
import {BankAccountsManagerComponent} from './accounting/bank-accounts-manager/bank-accounts-manager.component';
import {MaterialManagerComponent} from './accounting/material-manager/material-manager.component';
import {ConstructionMaterialComponent} from './accounting/construction-material/construction-material.component';
import {SmallEquipmentManagerComponent} from './machinery/small-equipment-manager/small-equipment-manager.component';
import {ConstrucionSiteDetailsComponent} from './common/construcion-site-details/construcion-site-details.component';
import {EmployeeDetailsComponent} from './hrm/employee-details/employee-details.component';
import {PayrollComponent} from './hrm/payroll/payroll.component';
import {LargeEquipmentManagerComponent} from './machinery/large-equipment-manager/large-equipment-manager.component';
import {LoginComponent} from './common/login/login.component';
import {PageControllerComponent} from './common/page-controller/page-controller';
import {PaySheetComponent} from './common/pdf/pay-sheet/pay-sheet.component';


const routes: Routes = [
  {
    path: 'app', component: PageControllerComponent, children: [
      {
        path: 'homePage',
        component: HomePageComponent
      },
      {
        path: 'dailyMaterial',
        component: DailyMaterialUsageComponent
      },
      {
        path: 'dailyAttendance',
        component: DailyAttendanceComponent
      },
      {
        path: 'approvalRequestsManager',
        component: ApprovalRequestsManagerComponent
      },
      {
        path: 'incomeExpenseReport',
        component: IncomeExpenseReportComponent
      },
      {
        path: 'expensesManager',
        component: ExpensesManagerComponent
      },
      {
        path: 'incomeManager',
        component: IncomeManagerComponent
      },
      {
        path: 'supplierPayments',
        component: SupplierPaymentsComponent
      },
      {
        path: 'goodReceiveNote',
        component: GoodReceiveNoteComponent
      },
      {
        path: 'purchaseOrderManager',
        component: PurchaseOrderManagerComponent
      },
      {
        path: 'purchaseRequestManager',
        component: PurchaseRequestManagerComponent
      },
      {
        path: 'supplierManager',
        component: SupplierManagerComponent
      },
      {
        path: 'bankAccountsManager',
        component: BankAccountsManagerComponent
      },
      {
        path: 'materialManager',
        component: MaterialManagerComponent
      },
      {
        path: 'constructionMaterial',
        component: ConstructionMaterialComponent
      },
      {
        path: 'smallEquipmentManager',
        component: SmallEquipmentManagerComponent
      },
      {
        path: 'construcionSiteDetails',
        component: ConstrucionSiteDetailsComponent
      },
      {
        path: 'employeeDetails',
        component: EmployeeDetailsComponent
      },
      {
        path: 'payroll',
        component: PayrollComponent
      },
      {
        path: 'paySheet',
        component: PaySheetComponent
      },
      {
        path: 'largeEquipmentManager',
        component: LargeEquipmentManagerComponent
      },
    ]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {path: '**', redirectTo: 'login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
