package com.akvasoft.construction.repo.accounting;

import com.akvasoft.construction.entity.accounting.GoodReceiveNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodReceivedNote extends JpaRepository<GoodReceiveNote, Integer> {
}
