package com.akvasoft.construction.repo.hrm;

import com.akvasoft.construction.entity.hrm.Employee;
import com.akvasoft.construction.entity.hrm.EmployeeLeaveYear;
import com.akvasoft.construction.entity.hrm.LeaveType;
import com.akvasoft.construction.util.DomainConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeLeaveYearRepo extends JpaRepository<EmployeeLeaveYear, Integer> {
    List<EmployeeLeaveYear> findByEmployee_EmployeeId(int empId);

    int deleteAllByEmployeeEquals(Employee e);

    EmployeeLeaveYear findTopByEmployeeEqualsAndLeaveTypeIdEquals(Employee employee, LeaveType type);

    EmployeeLeaveYear findByTypeAndEmployeeEquals(DomainConstant.EmployeeLeaveStatus type, Employee employee);
}
