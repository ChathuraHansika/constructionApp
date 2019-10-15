package com.akvasoft.construction.entity.report;

import com.akvasoft.construction.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_MAN_POWER")
public class ManPower implements Serializable {
    private static final long serialVersionUID = 2553453225851589986L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAN_POWER_ID")
    private Integer manPowerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DAILY_REPORT_ID")
    private DailyReport dailyReport;

    @Column(name = "EMPLOYEE_TYPE")
    private String employeeType;

    @Column(name = "COUNT")
    private Integer count;

    @Column(name = "ATTENDENCE_COUNT")
    private Integer attendenceCount;

    @Column(name = "MAN_POWER_HOUR")
    private BigDecimal manPowerHour;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MODIFIED_BY")
    private User modifiedBy;

    @Column(name = "MODIFIED_TIME")
    private Date modifiedTime;

    public Integer getManPowerId() {
        return manPowerId;
    }

    public void setManPowerId(Integer manPowerId) {
        this.manPowerId = manPowerId;
    }

    public DailyReport getDailyReport() {
        return dailyReport;
    }

    public void setDailyReport(DailyReport dailyReport) {
        this.dailyReport = dailyReport;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAttendenceCount() {
        return attendenceCount;
    }

    public void setAttendenceCount(Integer attendenceCount) {
        this.attendenceCount = attendenceCount;
    }

    public BigDecimal getManPowerHour() {
        return manPowerHour;
    }

    public void setManPowerHour(BigDecimal manPowerHour) {
        this.manPowerHour = manPowerHour;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return "ManPower{" +
                "manPowerId=" + manPowerId +
                ", dailyReport=" + dailyReport +
                ", employeeType='" + employeeType + '\'' +
                ", count=" + count +
                ", attendenceCount=" + attendenceCount +
                ", manPowerHour=" + manPowerHour +
                ", modifiedBy=" + modifiedBy +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
