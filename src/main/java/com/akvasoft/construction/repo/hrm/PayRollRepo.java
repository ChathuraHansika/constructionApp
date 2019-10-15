package com.akvasoft.construction.repo.hrm;

import com.akvasoft.construction.dto.hrm.EmployeePayment;
import com.akvasoft.construction.entity.hrm.Employee;
import com.akvasoft.construction.entity.hrm.PayRoll;
import com.akvasoft.construction.util.DomainConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayRollRepo extends JpaRepository<PayRoll, Integer> {
    PayRoll getTopByEmployeeEqualsAndYearEqualsAndMonthEquals(Employee employee, int year, int month);

    List<PayRoll> findAllByMonthEqualsAndYearEquals(int month, int year);
}
