package com.akvasoft.construction.repo;

import com.akvasoft.construction.entity.ConstructionSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructionSiteRepo extends JpaRepository<ConstructionSite, Integer> {

    ConstructionSite findByProjectIdEquals(String id);
}
