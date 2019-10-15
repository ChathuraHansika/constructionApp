package com.akvasoft.construction.repo.accounting;

import com.akvasoft.construction.entity.accounting.PurchaseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRequestRepo extends JpaRepository<PurchaseRequest, Integer> {
}
