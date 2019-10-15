package com.akvasoft.construction.entity.hrm;

import com.akvasoft.construction.dto.hrm.JobTypeLeavesDto;
import com.akvasoft.construction.util.DomainConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "T_JOB_TYPE_LEAVE")
public class JobTypeLeave implements Serializable {
    private static final long serialVersionUID = 787593101029746466L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_TYPE_LEAVE_ID")
    private Integer jobTypeLeaveId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JOB_TYPE_ID")
    private JobType jobType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LEAVE_TYPE_ID")
    private LeaveType leaveType;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private DomainConstant.EmployeeLeaveStatus type;

    @Column(name = "REDUCT_FROM")
    private String reductFrom;

    @Column(name = "PORTION")
    private BigDecimal portion;

    @Column(name = "AMOUNT")
    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DomainConstant.Status status;

    public JobTypeLeavesDto getDTO() {
        JobTypeLeavesDto dto = new JobTypeLeavesDto();
        dto.setId(this.jobTypeLeaveId);
        dto.setJob_type(this.jobType.getJobTypeId());
        dto.setLeave_type(this.leaveType.getLeaveTypeId());
        dto.setPortion(this.portion.doubleValue());
        dto.setAmount(this.amount);
        dto.setReduct_from(this.reductFrom);
        dto.setStatus(this.status.name());
        dto.setType(this.type.toString());
        return dto;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("JobTypeLeave{");
        sb.append("jobTypeLeaveId=").append(jobTypeLeaveId);
        sb.append(", jobType=").append(jobType);
        sb.append(", leaveType=").append(leaveType);
        sb.append(", type='").append(type).append('\'');
        sb.append(", reductFrom='").append(reductFrom).append('\'');
        sb.append(", portion=").append(portion);
        sb.append(", amount=").append(amount);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
