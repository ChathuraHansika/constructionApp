package com.akvasoft.construction.repo.report;

import com.akvasoft.construction.entity.report.WeatherReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepo extends JpaRepository<WeatherReport, Integer> {
}
