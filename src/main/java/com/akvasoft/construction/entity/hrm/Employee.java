package com.akvasoft.construction.entity.hrm;

import com.akvasoft.construction.dto.hrm.EmployeeAttendanceDto;
import com.akvasoft.construction.dto.hrm.EmployeeDto;
import com.akvasoft.construction.dto.hrm.JobTypeLeavesDto;
import com.akvasoft.construction.dto.hrm.JobTypeSalaryDto;
import com.akvasoft.construction.entity.ConstructionSite;
import com.akvasoft.construction.util.DomainConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "T_EMPLOYEE")
public class Employee implements Serializable {
    private static final long serialVersionUID = 2932396677456286612L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Integer employeeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SITE_ASSIGNED")
    private ConstructionSite site;

    @Enumerated(EnumType.STRING)
    @Column(name = "TITLE")
    private DomainConstant.Title title;

    @Column(name = "INITIALS")
    private String initials;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "NIC_NUMBER")
    private String nicNumber;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CONTACT_1")
    private String contact1;

    @Column(name = "CONTACT_2")
    private String contact2;

    @Column(name = "JOIN_DATE")
    private Date joinDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JOB_TYPE_ID")
    private JobType jobType;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "PER_DAY_WORK_HOUR")
    private Integer workHour;

    @Column(name = " OVER_TIME_RATE_PER_HOUR")
    private BigDecimal overTimeRatePerHour;

    @Column(name = "TOTAL_SALARY")
    private BigDecimal totalSalary;

    @Column(name = "TOTAL_BASE_SALARY")
    private BigDecimal totalBaseSalary;

    @Column(name = "TOTAL_ALLOWANCE_SALARY")
    private BigDecimal totalAllowanceSalary;

    @Column(name = "NO_PAY_DEDUCT")
    private BigDecimal noPayDeduct;

    @Column(name = "ETF")
    private BigDecimal etf;

    @Column(name = "EPF")
    private BigDecimal epf;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeAttendence> employeeAttendence;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DomainConstant.EmployeeStatus status;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeLeaveYear> leaves = new ArrayList<>();


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeLeave> leave = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeSalary> salarise = new ArrayList<>();

    public EmployeeDto getDTO() {
        EmployeeDto dto = new EmployeeDto();
        dto.setEmployeeId(this.employeeId);
        dto.setAddressLine1(this.addressLine1);
        dto.setAddressLine2(this.addressLine2);
        dto.setCity(this.city);
        dto.setContactNo1(this.contact1);
        dto.setContactNo2(this.contact2);
        dto.setCountry(this.country);
        dto.setDateOfBirth(this.dateOfBirth.toString());
        dto.setDesignation(this.designation);
        dto.setEpf(this.epf.doubleValue());
        dto.setEtf(this.etf.doubleValue());
        dto.setFirstName(this.firstName);
        dto.setFullName(this.fullName);
        dto.setInitial(this.initials);
        dto.setJobType(this.jobType.getJobTypeId());
        dto.setJobTypeName(this.jobType.getJobType());
        dto.setJoinDate(this.joinDate.toString());
        dto.setLeaves(convertLeavesToDtos());
        dto.setNicNumber(this.nicNumber);
        dto.setNoPayDeduct(this.noPayDeduct.doubleValue());
        dto.setSalary(convertSalariseToDtos());
        dto.setSiteAssigned(this.site.getSiteId());
        dto.setSiteAssignedName(this.site.getProjectName());
        dto.setSurname(this.surname);
        dto.setTitle(this.title.name());
        dto.setTotalAllowanceSalary(this.totalAllowanceSalary.doubleValue());
        dto.setTotalBaseSalary(this.totalBaseSalary.doubleValue());
        dto.setTotalSalary(this.totalSalary.doubleValue());
        dto.setWorkHour(this.workHour);
        dto.setStatus(this.status.toString());
        return dto;
    }

    private List<JobTypeLeavesDto> convertLeavesToDtos() {
        List<JobTypeLeavesDto> list = new ArrayList<>();
        JobTypeLeavesDto dto = null;
        for (EmployeeLeaveYear leaveYear : leaves) {
            dto = new JobTypeLeavesDto();
            dto.setAmount(leaveYear.getCount());
            dto.setType(leaveYear.getType().toString());
            dto.setStatus(leaveYear.getStatus());
            dto.setReduct_from(leaveYear.getReductFrom());
            dto.setPortion(leaveYear.getLeaveTypeId().getDayPortion().doubleValue());
            dto.setLeave_type(leaveYear.getLeaveTypeId().getLeaveTypeId());
            dto.setJob_type(leaveYear.getEmployee().getJobType().getJobTypeId());
            dto.setId(leaveYear.getEmployeeLeaveYearId());
            list.add(dto);
        }
        return list;
    }

    private List<JobTypeSalaryDto> convertSalariseToDtos() {
        List<JobTypeSalaryDto> list = new ArrayList<>();
        JobTypeSalaryDto dto = null;
        for (EmployeeSalary salary : salarise) {
            dto = new JobTypeSalaryDto();
            dto.setStatus(salary.getStatus().name());
            dto.setDescription(salary.getDescription());
            dto.setAmount(salary.getAmount().toString());
            dto.setJobType(salary.getEmployee().getJobType().convertToDto());
            dto.setJobTypeSalaryId(salary.getSalaryType());
            dto.setPerHour(false);
            dto.setPerDay(false);
            dto.setSalaryType(salary.getType().name());
            if (salary.getPerDay()) {
                dto.setPerDay(true);
            } else if (salary.getPerHour()) {
                dto.setPerHour(true);
            }
            list.add(dto);
        }
        return list;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Employee{");
        sb.append("employeeId=").append(employeeId);
        sb.append(", site=").append(site);
        sb.append(", title=").append(title);
        sb.append(", initials='").append(initials).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", nicNumber='").append(nicNumber).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", addressLine1='").append(addressLine1).append('\'');
        sb.append(", addressLine2='").append(addressLine2).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", contact1='").append(contact1).append('\'');
        sb.append(", contact2='").append(contact2).append('\'');
        sb.append(", joinDate=").append(joinDate);
        sb.append(", jobType=").append(jobType);
        sb.append(", designation='").append(designation).append('\'');
        sb.append(", workHour=").append(workHour);
        sb.append(", totalSalary=").append(totalSalary);
        sb.append(", totalBaseSalary=").append(totalBaseSalary);
        sb.append(", totalAllowanceSalary=").append(totalAllowanceSalary);
        sb.append(", noPayDeduct=").append(noPayDeduct);
        sb.append(", etf=").append(etf);
        sb.append(", epf=").append(epf);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
