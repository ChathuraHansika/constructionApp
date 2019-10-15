package com.akvasoft.construction.repo.accounting;

import com.akvasoft.construction.entity.accounting.PurchaseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseMaterialRepo extends JpaRepository<PurchaseMaterial, Integer> {
}
