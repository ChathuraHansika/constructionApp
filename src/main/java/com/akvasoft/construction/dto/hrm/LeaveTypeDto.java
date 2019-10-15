package com.akvasoft.construction.dto.hrm;

import com.akvasoft.construction.entity.hrm.LeaveType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LeaveTypeDto {
    private int id;
    private String type;
    private Double day_portion;
    private String reduct_from;
    private String status;

    public List<LeaveTypeDto> convertToDto(List<LeaveType> list) {
        LeaveTypeDto dto = null;
        List<LeaveTypeDto> types = new ArrayList<>();
        for (LeaveType leaveType : list) {
            types.add(makeDTO(leaveType));
        }
        return types;
    }

    public LeaveTypeDto makeDTO(LeaveType type) {
        LeaveTypeDto dto = new LeaveTypeDto();
        dto.setDay_portion(type.getDayPortion().doubleValue());
        dto.setId(type.getLeaveTypeId());
        dto.setReduct_from(type.getReductFrom());
        dto.setStatus(type.getReductFrom());
        dto.setType(type.getType());
        return dto;
    }
}
