package com.akvasoft.construction.dto.hrm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobTypeSalaryDto {
    private Integer jobTypeSalaryId;
    private JobTypeDto jobType;
    private String description;
    private String amount;
    private boolean perHour;
    private boolean perDay;
    private String salaryType;
    private String status;
}
