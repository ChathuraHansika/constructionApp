package com.akvasoft.construction.repo.accounting;

import com.akvasoft.construction.entity.accounting.ManagerApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerApprovalRepo extends JpaRepository<ManagerApproval, Integer> {
}
