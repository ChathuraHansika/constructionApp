package com.akvasoft.construction.repo.machinary;

import com.akvasoft.construction.entity.machinary.SmallEquipmentSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LargeEquipmentSiteRepo extends JpaRepository<SmallEquipmentSite, Integer> {
}
