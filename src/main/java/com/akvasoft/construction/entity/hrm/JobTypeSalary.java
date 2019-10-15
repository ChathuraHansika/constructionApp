package com.akvasoft.construction.entity.hrm;

import com.akvasoft.construction.dto.hrm.JobTypeSalaryDto;
import com.akvasoft.construction.util.DomainConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_JOB_TYPE_SALARY")
@Getter
@Setter
public class JobTypeSalary implements Serializable {
    private static final long serialVersionUID = -5185909419797312092L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_TYPE_SALARY_ID")
    private Integer jobTypeSalaryId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JOB_TYPE_ID")
    private JobType jobType;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "PER_HOUR")
    private Boolean perHour;

    @Column(name = "PER_DAY")
    private Boolean perDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "SALARY_TYPE")
    private DomainConstant.SalaryType salaryType;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DomainConstant.Status status;


    public List<JobTypeSalaryDto> convertToDto(List<JobTypeSalary> salaries) {
        List<JobTypeSalaryDto> list = new ArrayList<>();
        JobTypeSalaryDto dto = null;
        for (JobTypeSalary salary : salaries) {
            dto = new JobTypeSalaryDto();
            dto.setAmount(salary.getAmount().toString());
            dto.setDescription(salary.getDescription());
            dto.setJobType(salary.getJobType().convertToDto());
            dto.setJobTypeSalaryId(salary.getJobTypeSalaryId());
            dto.setSalaryType(salary.getSalaryType().name());
            dto.setStatus(salary.getStatus().name());
            if (salary.getPerDay() == true) {
                dto.setPerDay(true);
            } else {
                dto.setPerDay(false);
            }
            if (salary.getPerHour() == true) {
                dto.setPerHour(true);
            } else {
                dto.setPerHour(false);
            }
            list.add(dto);
        }
        return list;
    }

    @Override
    public String toString() {
        return "JobTypeSalary{" +
                "jobTypeSalaryId=" + jobTypeSalaryId +
                ", jobType=" + jobType +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", perHour='" + perHour + '\'' +
                ", perDay='" + perDay + '\'' +
                ", salaryTypee=" + salaryType +
                ", status=" + status +
                '}';
    }
}
