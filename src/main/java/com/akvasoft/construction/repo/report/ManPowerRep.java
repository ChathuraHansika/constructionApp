package com.akvasoft.construction.repo.report;

import com.akvasoft.construction.entity.report.ManPower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManPowerRep extends JpaRepository<ManPower, Integer> {
}
