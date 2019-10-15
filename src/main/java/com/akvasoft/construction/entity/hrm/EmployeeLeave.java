package com.akvasoft.construction.entity.hrm;

import com.akvasoft.construction.dto.hrm.JobTypeLeavesDto;
import com.akvasoft.construction.util.DomainConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "T_EMPLOYEE_LEAVE")
public class EmployeeLeave implements Serializable {

    private static final long serialVersionUID = -3704146505418086761L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_LEAVE_ID")
    private Integer employeeLeaveId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LEAVE_TYPE_ID")
    private LeaveType leaveTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "LEAVE_DATE")
    private Date leaveDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private DomainConstant.EmployeeLeaveStatus type;

    @Column(name = "COUNT")
    private Integer count;

    @Column(name = "REDUCT_FROM")
    private String reductFrom;


    @Column(name = "PORTION")
    private BigDecimal portion;

    @Column(name = "STATUS")
    private String status;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EmployeeLeave{");
        sb.append("employeeLeaveId=").append(employeeLeaveId);
        sb.append(", leaveTypeId=").append(leaveTypeId);
        sb.append(", employee=").append(employee);
        sb.append(", leaveDate=").append(leaveDate);
        sb.append(", type='").append(type).append('\'');
        sb.append(", count=").append(count);
        sb.append(", reductFrom='").append(reductFrom).append('\'');
        sb.append(", portion=").append(portion);
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }


}
