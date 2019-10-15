package com.akvasoft.construction.repo.machinary;

import com.akvasoft.construction.entity.machinary.LargeEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LargeEquipmentRepo extends JpaRepository<LargeEquipment, Integer> {
}
