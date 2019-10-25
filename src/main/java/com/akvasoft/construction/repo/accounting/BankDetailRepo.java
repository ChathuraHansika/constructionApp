package com.akvasoft.construction.repo.accounting;

import com.akvasoft.construction.entity.accounting.Bank;
import com.akvasoft.construction.entity.accounting.BankDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankDetailRepo extends JpaRepository<BankDetail, Integer> {

    List<BankDetail> findByBank_BankId(Integer bankId);

    BankDetail findByAccountNumber(String accNumber);

}
