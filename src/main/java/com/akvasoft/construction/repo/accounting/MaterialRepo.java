package com.akvasoft.construction.repo.accounting;

import com.akvasoft.construction.entity.accounting.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepo extends JpaRepository<Material, Integer> {
}
