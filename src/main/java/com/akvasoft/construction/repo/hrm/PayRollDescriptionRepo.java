package com.akvasoft.construction.repo.hrm;

import com.akvasoft.construction.entity.hrm.PayRollDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRollDescriptionRepo extends JpaRepository<PayRollDescription,Integer> {
}
