package com.akvasoft.construction.service.hrm;

import com.akvasoft.construction.dto.common.Response;
import com.akvasoft.construction.dto.hrm.*;

import java.text.ParseException;

import com.akvasoft.construction.entity.hrm.JobTypeSalary;

import java.util.List;

public interface HRService {
    String addEmployee(EmployeeDto employee);

    List<JobTypeDto> getJobTypes();

    List<JobTypeSalary> findJobTypeSalary(int jobType);

    List<LeaveTypeDto> getLeaveTypes();

    List<JobTypeLeavesDto> getJobTypeLeaves(int jobType);

    List<EmployeeDto> getEmployeesBySite(int siteID);

    List<EmployeeDto> searchEmployeeBySiteAndName(int site, String name);

    List<EmployeeDto> searchEmployeeByNic(String nic);

    Response searchByIdAndDate(int id, String date);

    EmployeePayment searchEmployeeForPayment(String user) throws NullPointerException;

    List<EmployeeAttendanceDto> searchById(int siteId);

    Response saveAttendanceInData(EmployeeAttendanceDto attendanceDetailsDto);

    Response saveAttendanceOutData(EmployeeAttendanceDto attendanceDetailsDto);

    String checkLeave(int id, int leaveId);

    EmployeePayment PayrollDetails(EmployeePayment user) throws ParseException;

    boolean saveMonthlyPayment(EmployeePayment payment);


    String getUnsavedAttendance();

    List<EmployeePayment> getPayments(int month, int year);

    List<EmployeePayment> getAllPayments();

}
