package com.akvasoft.construction.repo.accounting;

import com.akvasoft.construction.entity.accounting.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Integer> {
}
