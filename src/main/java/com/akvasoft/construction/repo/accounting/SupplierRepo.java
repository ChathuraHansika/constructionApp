package com.akvasoft.construction.repo.accounting;

import com.akvasoft.construction.entity.accounting.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Integer> {
}
