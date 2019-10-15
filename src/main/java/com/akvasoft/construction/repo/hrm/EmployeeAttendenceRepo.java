package com.akvasoft.construction.repo.hrm;

import com.akvasoft.construction.entity.ConstructionSite;
import com.akvasoft.construction.entity.hrm.Employee;
import com.akvasoft.construction.entity.hrm.EmployeeAttendence;
import net.bytebuddy.matcher.ElementMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeAttendenceRepo extends JpaRepository<EmployeeAttendence, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM T_EMPLOYEE_ATTENDENCE   JOIN T_EMPLOYEE ON\n" +
            " T_EMPLOYEE_ATTENDENCE.EMPLOYEE_ID=T_EMPLOYEE.EMPLOYEE_ID JOIN T_CONSTRUCTION_SITE ON \n" +
            "T_EMPLOYEE.SITE_ASSIGNED=T_CONSTRUCTION_SITE.CONSTRUCTION_SITE_ID WHERE CONSTRUCTION_SITE_ID=?1 AND\n" +
            "T_EMPLOYEE_ATTENDENCE.ATTENDENCE_DATE=?2 ; ")
    List<EmployeeAttendence> findByAttendenceDateandsiteId(int siteID, Date attendanceDate);

    List<EmployeeAttendence> findByAttendenceDateEquals(Date date);

    int countByAttendenceDateAndEmployee_Site_SiteId(Date attendanceDate, int siteId);

    @Query(nativeQuery = true, value = "SELECT COUNT(EMPLOYEE_ATTENDENCE_ID) FROM T_EMPLOYEE_ATTENDENCE WHERE  ATTENDENCE_DATE=?1")
    int allCount(Date date);

    @Query(nativeQuery = true, value = "SELECT * FROM T_EMPLOYEE_ATTENDENCE WHERE ATTENDENCE_DATE=?1 AND EMPLOYEE_ID=?2")
    EmployeeAttendence findAttendanceId(Date date, int integer);

    List<EmployeeAttendence> findAllByEmployeeEqualsAndMonthEqualsAndYearEquals(Employee employee, int month, int year);

    List<EmployeeAttendence> findAllByEmployeeAndAttendenceDateEquals(Employee employee, Date date);

    List<EmployeeAttendence> findAllByDepartureTimeIsNullAndAndArrivalTimeIsNotNull();

}
