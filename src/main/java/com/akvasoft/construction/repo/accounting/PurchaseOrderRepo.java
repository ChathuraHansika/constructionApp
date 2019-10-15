package com.akvasoft.construction.repo.accounting;

import com.akvasoft.construction.entity.accounting.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Integer> {
}
