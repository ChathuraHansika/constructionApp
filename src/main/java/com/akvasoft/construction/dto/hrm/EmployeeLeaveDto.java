package com.akvasoft.construction.dto.hrm;

import com.akvasoft.construction.entity.hrm.Employee;
import com.akvasoft.construction.entity.hrm.EmployeeLeave;
import com.akvasoft.construction.entity.hrm.LeaveType;
import com.akvasoft.construction.util.DateTimeConverter;
import com.akvasoft.construction.util.DomainConstant;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EmployeeLeaveDto {
    private LeaveType leaveTypeId;
    private Employee employee;
    private String type;
    private Integer count;
    private String reductFrom;
    private BigDecimal portion;
    private DomainConstant.Status status;


    public EmployeeLeave getEntity(EmployeeAttendanceDto dto, LeaveType leaveType) {
        EmployeeLeave employeeLeave = new EmployeeLeave();
        Employee employee = new Employee();
        employee.setEmployeeId(dto.getEmployeeId());
        employeeLeave.setEmployee(employee);
        employeeLeave.setLeaveTypeId(leaveType);
        employeeLeave.setPortion(leaveType.getDayPortion());
        employeeLeave.setCount(1);
        employeeLeave.setLeaveDate(new DateTimeConverter().parseDate(dto.getAttendanceDate()));
        employeeLeave.setStatus("yes");
        employeeLeave.setReductFrom(leaveType.getReductFrom());
        System.out.println("employee = " + employee);
        employeeLeave.setType(DomainConstant.EmployeeLeaveStatus.getLeaveStatus(leaveType.getType()));
        return employeeLeave;
    }


}
