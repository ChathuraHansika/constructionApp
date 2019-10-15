package com.akvasoft.construction.dto.hrm;


import com.akvasoft.construction.entity.User;
import com.akvasoft.construction.entity.hrm.*;
import com.akvasoft.construction.util.DomainConstant;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class EmployeeAttendanceDto {

    private Integer employeeId;
    private Integer siteId;
    private String employeeName;
    private String employeeJob;
    private Integer totalPresent;
    private Integer totalAbsent;
    private Integer siteTotal;
    private String timeIn;
    private String timeOut;
    private String user;
    private BigDecimal perDayWage;
    private BigDecimal perHourWage;
    private BigDecimal wage;
    private boolean perHour = false;
    private String jobType;
    private String attendance;
    private List<EmployeeLeaveYear> employeeLeaveYear;
    private List<EmployeeLeave> employeeLeaves;
    private String status;
    private String attendanceDate;
    private String leaveType;
    private Integer leaveTypeId;
    private String dateMarked;
    private String timeMarked;
    private Integer extraHours;
    private Integer perDayWorkHour;
    private BigDecimal noPayDeduct;
    private Integer attendanceId;
    private BigDecimal overTimeRatePerHour;
    private boolean disable = false;
    private boolean disableoutbtn = true;
    private boolean inTimeTxtDisbale = false;
    private boolean nopaytxt = true;
    private boolean disableAttendance = false;
    private boolean isTimeOutSaved = false;
    private boolean noPayDeductTxtVisible = false;


    public Date convertToDate(String stringFormatDate) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(stringFormatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BigDecimal getPerHour(List<EmployeeSalary> salaries) {

        Double perHour = 0.00;
        for (EmployeeSalary salary : salaries) {
            if (salary.getBase() == 0 & salary.getType().name().equals
                    (DomainConstant.SalaryType.getSalaryType("VARIABLE").name()) & salary.getPerHour() == true)
                perHour = perHour + salary.getAmount().doubleValue();

        }
        return new BigDecimal(perHour);
    }


    private BigDecimal getPerDay(List<EmployeeSalary> salaries) {

        Double perDay = 0.00;
        for (EmployeeSalary salary : salaries) {
            if (salary.getBase() == 0 & salary.getType().name().equals
                    (DomainConstant.SalaryType.getSalaryType("VARIABLE").name()) & salary.getPerDay() == true)
                perDay = perDay + salary.getAmount().doubleValue();

        }
        return new BigDecimal(perDay);
    }

    private boolean getSalaryType(List<EmployeeSalary> salaries) {

        for (EmployeeSalary salary : salaries) {
            if (salary.getBase() == 0 & salary.getType().name().equals
                    (DomainConstant.SalaryType.getSalaryType("VARIABLE").name())) {
                return perHour = salary.getPerHour();
            }


        }
        return perHour;
    }


    private int getYear(Date date) {
        return Integer.parseInt(String.valueOf(date).substring(24));
    }

    private int getMonth(String date) {
        return Integer.parseInt(String.valueOf(date).substring(5, 7));
    }

    public List<EmployeeAttendanceDto> convertToEADDto(List<EmployeeAttendence> list, int count, int siteTotal, int id) {
        List<EmployeeAttendanceDto> dtoList = new ArrayList<>();
        for (EmployeeAttendence attendance : list) {
            EmployeeAttendanceDto dto = new EmployeeAttendanceDto();
            dto.setTimeIn(attendance.getArrivalTime());
            if (attendance.getDepartureTime() != null) {
                dto.setTimeOut(attendance.getDepartureTime());
            }
            dto.setEmployeeJob(attendance.getEmployee().getDesignation());
            dto.setEmployeeName(attendance.getEmployee().getFullName());
            dto.setEmployeeId(attendance.getEmployee().getEmployeeId());
            dto.setEmployeeLeaveYear(attendance.getEmployee().getLeaves());
            dto.setEmployeeLeaves(attendance.getEmployee().getLeave());
            dto.setPerDayWorkHour(attendance.getEmployee().getWorkHour());
            dto.setSiteTotal(siteTotal);
            dto.setNoPayDeduct(attendance.getEmployee().getNoPayDeduct());
            dto.setTotalAbsent(siteTotal - count);
            dto.setTotalPresent(count);
            dto.setOverTimeRatePerHour(attendance.getEmployee().getOverTimeRatePerHour());
            dto.setPerHourWage(getPerHour(attendance.getEmployee().getSalarise()));
            dto.setPerDayWage(getPerDay(attendance.getEmployee().getSalarise()));
            dto.setPerHour(getSalaryType(attendance.getEmployee().getSalarise()));
            if (attendance.getArrivalTime() != null) {
                dto.setDisable(true);
                dto.setDisableoutbtn(false);
            }
            if (attendance.getDepartureTime() != null) {
                dto.setDisable(true);
                dto.setDisableoutbtn(true);
            }
            dtoList.add(dto);
        }
        return dtoList;
    }

    public EmployeeAttendanceDto convertToEmployeeDto(Employee employee) {
        EmployeeAttendanceDto dto = new EmployeeAttendanceDto();
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setEmployeeName(employee.getFullName());
        dto.setEmployeeJob(employee.getDesignation());
        dto.setPerDayWorkHour(employee.getWorkHour());
        dto.setJobType(employee.getJobType().getJobType());
        dto.setEmployeeLeaveYear(employee.getLeaves());
        dto.setEmployeeLeaves(employee.getLeave());
        dto.setNoPayDeduct(employee.getNoPayDeduct());
        dto.setOverTimeRatePerHour(employee.getOverTimeRatePerHour());
        dto.setPerHourWage(getPerHour(employee.getSalarise()));
        dto.setPerDayWage(getPerDay(employee.getSalarise()));
        dto.setPerHour(getSalaryType(employee.getSalarise()));
        return dto;
    }


    public EmployeeAttendence getTimeInEntity(EmployeeAttendanceDto detailsDto, User user) {
        EmployeeAttendence attendance = new EmployeeAttendence();
        Employee employee = new Employee();
        employee.setEmployeeId(detailsDto.getEmployeeId());
        employee.setFullName(detailsDto.getEmployeeName());
        employee.setDesignation(detailsDto.getEmployeeJob());
        attendance.setEmployee(employee);
        attendance.setStatus(DomainConstant.EmployeeAttendence.ARRIV);
        attendance.setArrivalTimeMarkedBy(user);
        attendance.setArrivalMarkedTime(detailsDto.getDateMarked() + detailsDto.getTimeMarked());
        attendance.setMonth(getMonth(detailsDto.getAttendanceDate()));
        attendance.setYear(getYear(convertToDate(detailsDto.getAttendanceDate())));
        attendance.setDateMarked(convertToDate(detailsDto.getDateMarked()));
        attendance.setAttendenceDate(convertToDate(detailsDto.getAttendanceDate()));
        attendance.setArrivalTime(detailsDto.getTimeIn());
        return attendance;
    }

    public EmployeeAttendence getTimeOutEntity(EmployeeAttendanceDto detailsDto,
                                               EmployeeAttendence empAttendance,
                                               LeaveType leave,
                                               User user) {
        empAttendance.setEmployeeAttendenceId(empAttendance.getEmployeeAttendenceId());
        empAttendance.setDepartureTime(detailsDto.getTimeOut());
        empAttendance.setDepartureMarkedTime(detailsDto.getDateMarked() + detailsDto.getTimeMarked());
        empAttendance.setWageCalculated(detailsDto.getWage());
        empAttendance.setStatus(DomainConstant.EmployeeAttendence.DEPT);
        empAttendance.setDepartureTimeMarkedBy(user);
        empAttendance.setExtraHours(detailsDto.getExtraHours());


        if (leave != null) {
            empAttendance.setLeaveType(leave);
            empAttendance.setTookLeave(true);
            empAttendance.setReducedLeaveType(leave);
            empAttendance.setReducedPortion(leave);
            empAttendance.setStatus(DomainConstant.EmployeeAttendence.INIT);

        } else {
            empAttendance.setTookLeave(false);
        }

        return empAttendance;
    }

    public List<EmployeeAttendanceDto> getLeaveDto(int leaveCount, int siteTotal, int totalPresent) {
        List<EmployeeAttendanceDto> dtos = new ArrayList<>();
        EmployeeAttendanceDto dto = new EmployeeAttendanceDto();
        dto.setTotalAbsent(leaveCount);
        dto.setTotalPresent(totalPresent);
        dto.setSiteTotal(siteTotal);
        dtos.add(dto);
        return dtos;
    }

}
