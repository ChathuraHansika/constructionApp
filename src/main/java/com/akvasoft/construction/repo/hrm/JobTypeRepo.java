package com.akvasoft.construction.repo.hrm;

import com.akvasoft.construction.entity.hrm.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTypeRepo extends JpaRepository<JobType, Integer> {
}
