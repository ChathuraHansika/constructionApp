import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {FormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {SidebarComponent} from './common/sidebar/sidebar.component';
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
import {DailyProgressReportComponent} from './report/daily-progress-report/daily-progress-report.component';
import {TodayReportComponent} from './report/today-report/today-report.component';
import {ManPowerMachineryReportComponent} from './report/man-power-machinery-report/man-power-machinery-report.component';
import {WeatherReportComponent} from './report/weather-report/weather-report.component';
import {ChartsModule} from 'ng2-charts/ng2-charts';
import {AlertService} from './core/services/alert.service';
import {CommonService} from './core/services/common.service';
import {DataService} from './core/services/dataservice.service';
import {UserService} from './core/services/user.service';
import {LoginGuard} from './core/services/login.guard';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {TokenInterceptor} from './core/interceptor/TokenInterceptor';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgxWebstorageModule} from 'ngx-webstorage';
import {ToastrModule} from 'ng6-toastr-notifications';
import {PageControllerComponent} from './common/page-controller/page-controller';
import {EmployeeService} from './core/services/hrm/employee.service';
import {ReactiveFormsModule} from '@angular/forms';
import {PaySheetComponent} from './common/pdf/pay-sheet/pay-sheet.component';
import {PDFExportModule} from '@progress/kendo-angular-pdf-export';
import {PaySheetPdfComponent} from './common/pdf/pay-sheet-pdf/pay-sheet-pdf.component';
import {ProjectService} from "./core/services/project.service";

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    DailyMaterialUsageComponent,
    HomePageComponent,
    DailyAttendanceComponent,
    ApprovalRequestsManagerComponent,
    IncomeExpenseReportComponent,
    ExpensesManagerComponent,
    IncomeManagerComponent,
    SupplierPaymentsComponent,
    GoodReceiveNoteComponent,
    PurchaseOrderManagerComponent,
    PurchaseRequestManagerComponent,
    SupplierManagerComponent,
    BankAccountsManagerComponent,
    MaterialManagerComponent,
    ConstructionMaterialComponent,
    SmallEquipmentManagerComponent,
    ConstrucionSiteDetailsComponent,
    EmployeeDetailsComponent,
    PayrollComponent,
    LargeEquipmentManagerComponent,
    LoginComponent,
    DailyProgressReportComponent,
    TodayReportComponent,
    ManPowerMachineryReportComponent,
    WeatherReportComponent,
    PageControllerComponent,
    PaySheetComponent,
    PaySheetPdfComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    ChartsModule,
    FormsModule,
    ReactiveFormsModule,
    PDFExportModule
  ],
  providers: [AlertService, CommonService, DataService, UserService, EmployeeService, ProjectService,
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true}, LoginGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
}
