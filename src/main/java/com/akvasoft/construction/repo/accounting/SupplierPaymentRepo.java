package com.akvasoft.construction.repo.accounting;

import com.akvasoft.construction.entity.accounting.SupplierPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierPaymentRepo extends JpaRepository<SupplierPayment, Integer> {

}
