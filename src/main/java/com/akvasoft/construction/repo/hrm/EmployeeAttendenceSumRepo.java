package com.akvasoft.construction.repo.hrm;

import com.akvasoft.construction.entity.hrm.EmployeeAttendenceSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAttendenceSumRepo extends JpaRepository<EmployeeAttendenceSummary, Integer> {
}
