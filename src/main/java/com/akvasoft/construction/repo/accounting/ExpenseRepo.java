package com.akvasoft.construction.repo.accounting;

import com.akvasoft.construction.entity.accounting.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Integer> {
}
