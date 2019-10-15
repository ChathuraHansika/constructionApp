package com.akvasoft.construction.entity.hrm;

import com.akvasoft.construction.dto.hrm.JobTypeDto;
import com.akvasoft.construction.util.DomainConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "T_JOB_TYPE")
@Getter
@Setter
public class JobType implements Serializable {
    private static final long serialVersionUID = -5206410166117433399L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_TYPE_ID")
    private Integer jobTypeId;

    @Column(name = "INSENTIVE_PERCENTAGE")
    private BigDecimal insentive;

    @Column(name = "JOB_TYPE")
    private String jobType;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DomainConstant.Status status;


    public JobTypeDto convertToDto() {
        JobTypeDto dto = new JobTypeDto();
        dto.setStatus(this.status.name());
        dto.setJobTypeId(this.jobTypeId);
        dto.setJobType(this.jobType);
        dto.setInsentive(this.insentive.toString());
        return dto;
    }

    @Override
    public String toString() {
        return "JobType{" +
                "jobTypeId=" + jobTypeId +
                ", jobType='" + jobType + '\'' +
                ", status=" + status +
                '}';
    }
}
