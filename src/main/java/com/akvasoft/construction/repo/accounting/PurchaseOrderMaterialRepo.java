package com.akvasoft.construction.repo.accounting;

import com.akvasoft.construction.entity.accounting.PurchaseOrderMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderMaterialRepo extends JpaRepository<PurchaseOrderMaterial, Integer> {
}
