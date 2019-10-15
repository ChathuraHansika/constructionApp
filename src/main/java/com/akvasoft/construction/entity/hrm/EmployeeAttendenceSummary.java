package com.akvasoft.construction.entity.hrm;

import com.akvasoft.construction.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_EMPLOYEE_ATTENDENCE_SUMMARY")
public class EmployeeAttendenceSummary implements Serializable {
    private static final long serialVersionUID = 7733726598356871561L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ATTENDENCE_SUMMARY_ID")
    private Integer employeeSummaryId;

    @Column(name = "SUMMARY_DATE")
    private Date summaryDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INITIATED_BY")
    private User user;

    @Column(name = "TOTAL_PRESENT")
    private Integer totalPresent;

    @Column(name = "TOTAL_ABSENT")
    private Integer totalAbsent;

    @Column(name = "TOTAL_WAGE")
    private BigDecimal totalWage;

    public Integer getEmployeeSummaryId() {
        return employeeSummaryId;
    }

    public void setEmployeeSummaryId(Integer employeeSummaryId) {
        this.employeeSummaryId = employeeSummaryId;
    }

    public Date getSummaryDate() {
        return summaryDate;
    }

    public void setSummaryDate(Date summaryDate) {
        this.summaryDate = summaryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTotalPresent() {
        return totalPresent;
    }

    public void setTotalPresent(Integer totalPresent) {
        this.totalPresent = totalPresent;
    }

    public Integer getTotalAbsent() {
        return totalAbsent;
    }

    public void setTotalAbsent(Integer totalAbsent) {
        this.totalAbsent = totalAbsent;
    }

    public BigDecimal getTotalWage() {
        return totalWage;
    }

    public void setTotalWage(BigDecimal totalWage) {
        this.totalWage = totalWage;
    }

    @Override
    public String toString() {
        return "EmployeeAttendenceSummary{" +
                "employeeSummaryId=" + employeeSummaryId +
                ", summaryDate=" + summaryDate +
                ", user=" + user +
                ", totalPresent=" + totalPresent +
                ", totalAbsent=" + totalAbsent +
                ", totalWage=" + totalWage +
                '}';
    }
}
