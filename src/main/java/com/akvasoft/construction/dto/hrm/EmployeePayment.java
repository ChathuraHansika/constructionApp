package com.akvasoft.construction.dto.hrm;

import com.akvasoft.construction.entity.hrm.Employee;
import com.akvasoft.construction.entity.hrm.EmployeeSalary;
import com.akvasoft.construction.entity.hrm.PayRoll;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EmployeePayment {
    private int empID;
    private int payrollID;
    private int siteID;
    private int jobTypeID;
    private int salaryTypeID;
    private int req_by;
    private int approved_by;
    private String title;
    private String fullName;
    private String siteName;
    private String designation;
    private String jobType;
    private String salaryType;
    private String date;
    private String month;
    private String year;
    private String epf;
    private String etf;
    private String noPayDeduct;
    private boolean approval = false;
    private List<EmployeePaymentDetails> details;

    public void setEmployeeData(Employee employee) {
        this.empID = employee.getEmployeeId();
        this.siteID = employee.getSite().getSiteId();
        this.jobTypeID = employee.getJobType().getJobTypeId();
        this.salaryTypeID = 0;
        this.title = employee.getTitle().name();
        this.fullName = employee.getFullName();
        this.siteName = employee.getSite().getProjectName();
        this.designation = employee.getDesignation();
        this.jobType = employee.getJobType().getJobType();
        this.epf = employee.getEpf().toString();
        this.etf = employee.getEtf().toString();
        this.noPayDeduct = employee.getNoPayDeduct().toString();
        this.salaryType = "temp rec";
    }

    public void setPayrollData(PayRoll payRoll) {
        if (payRoll == null) return;
        this.payrollID = payRoll.getPayRollId();
        this.date = payRoll.getDate().toString();
        this.month = payRoll.getMonth().toString();
        this.year = payRoll.getYear().toString();
        this.approval = payRoll.getApprovalStatus().name().equalsIgnoreCase("APPROVED");
        this.details = payRoll.getDetailsList();
        this.req_by = payRoll.getRequestedBy().getUserId();
        try {
            this.approved_by = payRoll.getApprovedBy().getUserId();
        } catch (NullPointerException ignored) {

        }
    }

    public void setPayment(EmployeePaymentDetails paymentDetails) {
        if (details == null) details = new ArrayList<>();
        details.add(paymentDetails);
    }

    public void setPayments(List<EmployeePaymentDetails> payments) {
        payments.forEach(this::setPayment);
    }
}
