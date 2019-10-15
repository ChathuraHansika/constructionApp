package com.akvasoft.construction.repo.accounting;

import com.akvasoft.construction.entity.accounting.MaterialDailyUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialDailyUsageRepo extends JpaRepository<MaterialDailyUsage,Integer> {
}
