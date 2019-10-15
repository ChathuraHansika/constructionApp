package com.akvasoft.construction.dto.hrm;

import com.akvasoft.construction.entity.hrm.Employee;
import com.akvasoft.construction.util.DomainConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class EmployeeDto {
    private int employeeId;
    private int siteAssigned;
    private String siteAssignedName;
    private String title;
    private String initial;
    private String firstName;
    private String surname;
    private String fullName;
    private String nicNumber;
    private String dateOfBirth;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String contactNo1;
    private String contactNo2;
    private String joinDate;
    private int jobType;
    private String jobTypeName;
    private String designation;
    private int workHour;
    private double totalSalary;
    private double totalBaseSalary;
    private double totalAllowanceSalary;
    private double noPayDeduct;
    private double etf;
    private double epf;
    private String status;
    private double otrate;
    List<JobTypeSalaryDto> salary;
    List<JobTypeLeavesDto> leaves;


    private Date convertToDate(String stringFormatDate) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(stringFormatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Employee getEntity(Employee employee) {
        if (employee == null) {
            employee = new Employee();
        }
        employee.setAddressLine1(this.addressLine1);
        employee.setAddressLine2(this.addressLine2);
        employee.setCity(this.city);
        employee.setOverTimeRatePerHour(new BigDecimal(this.otrate));
        employee.setContact1(this.contactNo1);
        employee.setContact2(this.contactNo2);
        employee.setCountry(this.country);
        employee.setDateOfBirth(convertToDate(this.dateOfBirth));
        employee.setDesignation(this.designation);
        employee.setEpf(BigDecimal.valueOf(this.epf));
        employee.setEtf(BigDecimal.valueOf(this.etf));
        employee.setFirstName(this.firstName);
        employee.setFullName(this.fullName);
        employee.setInitials(this.initial);
        employee.setJoinDate(convertToDate(this.joinDate));
        employee.setNicNumber(this.nicNumber.toUpperCase());
        employee.setNoPayDeduct(BigDecimal.valueOf(this.noPayDeduct));
        employee.setStatus(DomainConstant.EmployeeStatus.getEmployeeStatus(this.status));
        employee.setSurname(this.surname);
        employee.setTitle(DomainConstant.Title.getTitle(this.title));
        employee.setTotalAllowanceSalary(calculateAllowanceSalary());
        employee.setTotalBaseSalary(calculateBaseSalary());
        employee.setNoPayDeduct(new BigDecimal(this.noPayDeduct));
        employee.setTotalSalary(calculateTotal(employee.getTotalAllowanceSalary(), employee.getTotalBaseSalary()));
        employee.setWorkHour(this.workHour);
        return employee;
    }

    private BigDecimal calculateTotal(BigDecimal totalAllowanceSalary, BigDecimal totalBaseSalary) {
        return BigDecimal.valueOf(totalAllowanceSalary.doubleValue() + totalBaseSalary.doubleValue());
    }

    private BigDecimal calculateAllowanceSalary() {
        double total;
        for (JobTypeSalaryDto dto : salary) System.out.println(dto.getDescription());
        total = salary.stream().filter(dto -> dto.getSalaryType().equalsIgnoreCase("ALLOWANCE")).mapToDouble(dto -> Double.parseDouble(dto.getAmount())).sum();
        return BigDecimal.valueOf(total);
    }

    private BigDecimal calculateBaseSalary() {
        double total = salary.stream().filter(dto -> dto.getSalaryType().equalsIgnoreCase("BASE")).mapToDouble(dto -> Double.parseDouble(dto.getAmount())).sum();
        return BigDecimal.valueOf(total);
    }
}
