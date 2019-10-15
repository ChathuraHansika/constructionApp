package com.akvasoft.construction.entity.hrm;

import com.akvasoft.construction.dto.hrm.JobTypeLeavesDto;
import com.akvasoft.construction.util.DateTimeConverter;
import com.akvasoft.construction.util.DomainConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "T_EMPLOYEE_LEAVE_YEAR")
public class EmployeeLeaveYear implements Serializable {

    private static final long serialVersionUID = -8874731839055599125L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_LEAVE_YEAR_ID")
    private Integer employeeLeaveYearId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LEAVE_TYPE_ID")
    private LeaveType leaveTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private DomainConstant.EmployeeLeaveStatus type;

    @Column(name = "COUNT")
    private Integer count;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "REDUCT_FROM")
    private String reductFrom;

    @Column(name = "STATUS")
    private String status;

    public EmployeeLeaveYear convertToDto(Employee employee, LeaveType leaveType, JobTypeLeavesDto elve) {
        EmployeeLeaveYear leave = new EmployeeLeaveYear();
        leave.setCount(elve.getAmount());
        leave.setEmployee(employee);
        leave.setLeaveTypeId(leaveType);
        leave.setReductFrom(elve.getReduct_from());
        leave.setStatus(elve.getStatus());
        leave.setType(DomainConstant.EmployeeLeaveStatus.getLeaveStatus(elve.getType()));
        leave.setYear(new DateTimeConverter().getCurrentYear());
        return leave;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EmployeeLeaveYear{");
        sb.append("employeeLeaveYearId=").append(employeeLeaveYearId);
        sb.append(", leaveTypeId=").append(leaveTypeId);
        sb.append(", employee=").append(employee);
        sb.append(", type='").append(type).append('\'');
        sb.append(", count=").append(count);
        sb.append(", year=").append(year);
        sb.append(", reductFrom='").append(reductFrom).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
