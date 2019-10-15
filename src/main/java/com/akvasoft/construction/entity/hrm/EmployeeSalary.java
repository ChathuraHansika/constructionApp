package com.akvasoft.construction.entity.hrm;

import com.akvasoft.construction.dto.hrm.EmployeePaymentDetails;
import com.akvasoft.construction.dto.hrm.JobTypeSalaryDto;
import com.akvasoft.construction.util.DomainConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "T_EMPLOYEE_SALARY")
public class EmployeeSalary implements Serializable {
    private static final long serialVersionUID = -8377997151092149122L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SALARY_TYPE_ID")
    private Integer salaryType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PER_HOUR")
    private Boolean perHour;

    @Column(name = "PER_DAY")
    private Boolean perDay;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "BASE")
    private Integer base;

    @Enumerated(EnumType.STRING)
    @Column(name = "SALARY_TYPE")
    private DomainConstant.SalaryType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DomainConstant.Status status;


    public EmployeeSalary setEntity(Employee employee, JobTypeSalaryDto salary) {
        EmployeeSalary employeeSalary = new EmployeeSalary();
        employeeSalary.setEmployee(employee);
        employeeSalary.setDescription(salary.getDescription());
        employeeSalary.setAmount(BigDecimal.valueOf(Double.parseDouble(salary.getAmount())));
        employeeSalary.setStatus(DomainConstant.Status.getStatus(salary.getStatus()));
        employeeSalary.setPerDay(false);
        employeeSalary.setPerHour(false);

        if (salary.isPerDay()) employeeSalary.setPerDay(true);
        else if (salary.isPerHour()) {
            employeeSalary.setPerHour(true);
        }

        if (salary.getSalaryType().equalsIgnoreCase(DomainConstant.SalaryType.BASE.name())) {
            employeeSalary.setBase(1);
            employeeSalary.setType(DomainConstant.SalaryType.DEFINITE);
        } else {
            employeeSalary.setBase(0);
            employeeSalary.setType(DomainConstant.SalaryType.VARIABLE);
        }
        return employeeSalary;
    }

    public EmployeePaymentDetails getPayrollDetails(int days) {
        EmployeePaymentDetails details = new EmployeePaymentDetails();
        details.setEditable(this.type.name().equalsIgnoreCase("VARIABLE"));
        details.setSalaryType(this.type.name());
        details.setDesc(this.description);
        details.setAmount(this.amount.doubleValue() * days);
        details.setStatus(this.status.name());
        return details;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EmployeeSalary{");
        sb.append("salaryType=").append(salaryType);
        sb.append(", employee=").append(employee);
        sb.append(", description='").append(description).append('\'');
        sb.append(", perHour=").append(perHour);
        sb.append(", perDay=").append(perDay);
        sb.append(", amount=").append(amount);
        sb.append(", base=").append(base);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
