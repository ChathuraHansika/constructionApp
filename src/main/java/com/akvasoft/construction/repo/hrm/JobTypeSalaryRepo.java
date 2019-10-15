package com.akvasoft.construction.repo.hrm;

import com.akvasoft.construction.entity.hrm.JobType;
import com.akvasoft.construction.entity.hrm.JobTypeSalary;
import com.akvasoft.construction.util.DomainConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobTypeSalaryRepo extends JpaRepository<JobTypeSalary, Integer> {
    List<JobTypeSalary> findAllByJobTypeEqualsAndStatusEquals(JobType type, DomainConstant.Status status);
}
