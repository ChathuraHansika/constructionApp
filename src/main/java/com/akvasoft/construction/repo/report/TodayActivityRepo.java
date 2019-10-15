package com.akvasoft.construction.repo.report;

import com.akvasoft.construction.entity.report.TodayActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodayActivityRepo extends JpaRepository<TodayActivity,Integer> {
}
