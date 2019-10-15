package com.akvasoft.construction.repo.report;

import com.akvasoft.construction.entity.report.DailyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyReportRepo extends JpaRepository<DailyReport, Integer> {
}
