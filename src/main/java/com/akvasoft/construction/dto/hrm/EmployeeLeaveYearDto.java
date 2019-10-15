package com.akvasoft.construction.dto.hrm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeLeaveYearDto {

    private String type;
    private int count;
    private int year;
    private String reductFrom;
    private String status;



}
