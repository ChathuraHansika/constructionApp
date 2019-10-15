package com.akvasoft.construction.dto.hrm;

import com.akvasoft.construction.entity.hrm.Employee;
import com.akvasoft.construction.entity.hrm.EmployeeLeaveYear;
import com.akvasoft.construction.entity.hrm.JobTypeLeave;
import com.akvasoft.construction.entity.hrm.LeaveType;
import com.akvasoft.construction.util.DateTimeConverter;
import com.akvasoft.construction.util.DomainConstant;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JobTypeLeavesDto {
    private int id;
    private int job_type;
    private int leave_type;
    private String type;
    private String reduct_from;
    private double portion;
    private int amount;
    private String status;

    public List<JobTypeLeavesDto> makeDTOList(List<JobTypeLeave> leaves) {
        List<JobTypeLeavesDto> list = new ArrayList<>();
        for (JobTypeLeave leave : leaves) {
            list.add(makeDTO(leave));
        }
        return list;
    }

    private JobTypeLeavesDto makeDTO(JobTypeLeave leave) {
        JobTypeLeavesDto dto = new JobTypeLeavesDto();
        dto.setId(leave.getJobTypeLeaveId());
        dto.setJob_type(leave.getJobType().getJobTypeId());
        dto.setLeave_type(leave.getJobTypeLeaveId());
        dto.setPortion(leave.getPortion().doubleValue());
        dto.setAmount(leave.getAmount());
        dto.setReduct_from(leave.getReductFrom());
        dto.setStatus(leave.getStatus().name());
        dto.setType(leave.getType().toString());
        return dto;
    }

    public EmployeeLeaveYear getEntity(Employee employee, LeaveType leaveType) {
        EmployeeLeaveYear leave = new EmployeeLeaveYear();
        leave.setCount(this.amount);
        leave.setEmployee(employee);
        leave.setLeaveTypeId(leaveType);
        leave.setReductFrom(reduct_from);
        leave.setStatus(status);
        leave.setType(DomainConstant.EmployeeLeaveStatus.getLeaveStatus(type));
        leave.setYear(new DateTimeConverter().getCurrentYear());
        return leave;
    }
}
