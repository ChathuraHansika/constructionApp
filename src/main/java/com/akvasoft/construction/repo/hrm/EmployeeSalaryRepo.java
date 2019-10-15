package com.akvasoft.construction.repo.hrm;

import com.akvasoft.construction.entity.hrm.Employee;
import com.akvasoft.construction.entity.hrm.EmployeeSalary;
import com.akvasoft.construction.util.DomainConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeSalaryRepo extends JpaRepository<EmployeeSalary, Integer> {
    int deleteAllByEmployeeEquals(Employee e);
    List<EmployeeSalary> findAllByEmployeeEqualsAndBaseEqualsAndPerDayEqualsAndStatusEquals(Employee employee, int base, boolean day, DomainConstant.Status status);
}
