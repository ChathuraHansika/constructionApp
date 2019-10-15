package com.akvasoft.construction.repo.hrm;

import com.akvasoft.construction.entity.ConstructionSite;
import com.akvasoft.construction.entity.hrm.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    Employee findByNicNumberEquals(String nic);

    List<Employee> findAllBySiteEquals(ConstructionSite site);

    @Query(nativeQuery = true, value = "SELECT * FROM T_EMPLOYEE WHERE SITE_ASSIGNED=?1 ")
    List<Employee> findEmplloyesBySiteId(int siteId);

    @Query(nativeQuery = true, value = "SELECT COUNT(EMPLOYEE_ID) FROM T_EMPLOYEE")
    int allCount();

    @Query
    int countBySiteSiteIdEquals(int siteId);

    List<Employee> findAllBySiteEqualsAndFullNameContains(ConstructionSite site, String name);

    Employee findFirstByFullNameContainsOrNicNumberEquals(String name, String nic);
}
