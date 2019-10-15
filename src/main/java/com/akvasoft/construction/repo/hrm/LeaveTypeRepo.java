package com.akvasoft.construction.repo.hrm;

import com.akvasoft.construction.entity.hrm.LeaveType;
import com.akvasoft.construction.util.DomainConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveTypeRepo extends JpaRepository<LeaveType, Integer> {

    List<LeaveType> findAllByStatusEquals(DomainConstant.Status status);

    @Override
    LeaveType getOne(Integer integer);
}
