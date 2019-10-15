package com.akvasoft.construction.repo.machinary;

import com.akvasoft.construction.entity.machinary.SmallEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmallEquipmentRepo extends JpaRepository<SmallEquipment, Integer> {
}
