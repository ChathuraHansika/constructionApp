package com.akvasoft.construction.repo.hrm;

import com.akvasoft.construction.entity.hrm.JobType;
import com.akvasoft.construction.entity.hrm.JobTypeLeave;
import com.akvasoft.construction.util.DomainConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobTypeLeaveRepo extends JpaRepository<JobTypeLeave, Integer> {
    List<JobTypeLeave> findAllByJobTypeEqualsAndStatusEquals(JobType jobType, DomainConstant.Status status);
}
