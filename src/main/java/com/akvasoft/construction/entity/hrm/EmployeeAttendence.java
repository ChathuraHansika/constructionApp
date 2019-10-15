package com.akvasoft.construction.entity.hrm;

import com.akvasoft.construction.entity.User;
import com.akvasoft.construction.util.DomainConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "T_EMPLOYEE_ATTENDENCE")
public class EmployeeAttendence implements Serializable {
    private static final long serialVersionUID = -2362579148198756184L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ATTENDENCE_ID")
    private Integer employeeAttendenceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "MONTH")
    private Integer month;

    @Column(name = "DATE_MARKED")
    private Date dateMarked;

    @Column(name = "ATTENDENCE_DATE")
    private Date attendenceDate;

    @Column(name = "ARRIVAL_TIME")
    private String arrivalTime;

    @Column(name = "DEPARTURE_TIME")
    private String departureTime;

    @Column(name = "EXTRA_HOURS")
    private Integer extraHours;

    @Column(name = "WAGE_CALCULLATED")
    private BigDecimal wageCalculated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ARRIVAL_TIME_MARKED_BY")
    private User arrivalTimeMarkedBy;

    @Column(name = "ARRIVAL_TIME_MARKED_TIME")
    private String arrivalMarkedTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTURE_TIME_MARKED_BY")
    private User departureTimeMarkedBy;

    @Column(name = "DEPARTURE_TIME_MARKED_TIME")
    private String departureMarkedTime;

    @Column(name = "TOOK_LEAVE")
    private Boolean tookLeave;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LEAVE_TYPE")
    private LeaveType leaveType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REDUCED_LEAVE_TYPE")
    private LeaveType reducedLeaveType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REDUCED_PORTION")
    private LeaveType reducedPortion;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DomainConstant.EmployeeAttendence status;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EmployeeAttendence{");
        sb.append("employeeAttendenceId=").append(employeeAttendenceId);
        sb.append(", employee=").append(employee);
        sb.append(", year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", dateMarked=").append(dateMarked);
        sb.append(", attendenceDate=").append(attendenceDate);
        sb.append(", arrivalTime='").append(arrivalTime).append('\'');
        sb.append(", departureTime='").append(departureTime).append('\'');
        sb.append(", extraHours=").append(extraHours);
        sb.append(", wageCalculated=").append(wageCalculated);
        sb.append(", arrivalTimeMarkedBy=").append(arrivalTimeMarkedBy);
        sb.append(", arrivalMarkedTime='").append(arrivalMarkedTime).append('\'');
        sb.append(", departureTimeMarkedBy=").append(departureTimeMarkedBy);
        sb.append(", departureMarkedTime='").append(departureMarkedTime).append('\'');
        sb.append(", tookLeave=").append(tookLeave);
        sb.append(", leaveType=").append(leaveType);
        sb.append(", reducedLeaveType=").append(reducedLeaveType);
        sb.append(", reducedPortion=").append(reducedPortion);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
