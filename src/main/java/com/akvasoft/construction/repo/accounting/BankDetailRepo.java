package com.akvasoft.construction.repo.accounting;

import com.akvasoft.construction.entity.accounting.BankDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDetailRepo extends JpaRepository<BankDetail, Integer> {
}
