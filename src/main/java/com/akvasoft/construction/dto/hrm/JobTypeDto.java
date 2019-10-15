package com.akvasoft.construction.dto.hrm;

import com.akvasoft.construction.entity.hrm.JobType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JobTypeDto {
    private Integer jobTypeId;
    private String insentive;
    private String jobType;
    private String status;




//    public List<JobTypeDto> convertToDto(List<JobType> list) {
//        List<JobTypeDto> dtos = new ArrayList<>();
//        JobTypeDto dto = null;
//        for (JobType type : list) {
//            dto = new JobTypeDto();
//            dto.setInsentive(type.getInsentive().toString());
//            dto.setJobType(type.getJobType());
//            dto.setJobTypeId(type.getJobTypeId());
//            dto.setStatus(type.getStatus().name());
//            dtos.add(dto);
//        }
//        return dtos;
//    }

}
