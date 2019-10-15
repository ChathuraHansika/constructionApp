package com.akvasoft.construction.repo.accounting;

import com.akvasoft.construction.entity.accounting.GoodReceived;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodReceivedRepo extends JpaRepository<GoodReceived, Integer> {
}
