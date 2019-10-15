package com.akvasoft.construction.repo.hrm;


import com.akvasoft.construction.entity.hrm.Employee;
import com.akvasoft.construction.entity.hrm.EmployeeLeave;
import com.akvasoft.construction.util.DomainConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeLeaveRepo extends JpaRepository<EmployeeLeave, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM T_EMPLOYEE_LEAVE where EMPLOYEE_ID =?1 and" +
            " LEAVE_TYPE_ID=?2")
    EmployeeLeave getEmployeeLeave(Integer empId, Integer LeaveId);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE T_EMPLOYEE_LEAVE  SET COUNT=?1 WHERE EMPLOYEE_ID = ?2 AND LEAVE_TYPE_ID=?3")
    int updateCount(Integer integer, Integer integer1, Integer integer2);

    int countAllByEmployeeEqualsAndTypeEqualsAndStatusEquals(Employee employee,DomainConstant.EmployeeLeaveStatus type, String status);
    int countByLeaveDateEquals(Date date);

    EmployeeLeave findByTypeAndEmployeeEquals(DomainConstant.EmployeeLeaveStatus type , Employee employee);


}
