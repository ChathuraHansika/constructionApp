package com.akvasoft.construction.entity.report;

import com.akvasoft.construction.entity.ConstructionSite;
import com.akvasoft.construction.entity.User;
import com.akvasoft.construction.util.DomainConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_WEATHER_REPORT")
public class WeatherReport implements Serializable {
    private static final long serialVersionUID = 697028897331660815L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WEATHER_REPORT_ID")
    private Integer weatherReportId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DAILY_REPORT_ID")
    private DailyReport dailyReport;

    @Column(name = "HOUR")
    private Integer hour;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MODIFIED_BY")
    private User modifiedBy;

    @Column(name = "MODIFIED_TIME")
    private Date modifiedTime;


    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private DomainConstant.WeatherStatus status;

    public Integer getWeatherReportId() {
        return weatherReportId;
    }

    public void setWeatherReportId(Integer weatherReportId) {
        this.weatherReportId = weatherReportId;
    }

    public DailyReport getDailyReport() {
        return dailyReport;
    }

    public void setDailyReport(DailyReport dailyReport) {
        this.dailyReport = dailyReport;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public DomainConstant.WeatherStatus getStatus() {
        return status;
    }

    public void setStatus(DomainConstant.WeatherStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WeatherReport{" +
                "weatherReportId=" + weatherReportId +
                ", dailyReport=" + dailyReport +
                ", hour=" + hour +
                ", description='" + description + '\'' +
                ", modifiedBy=" + modifiedBy +
                ", modifiedTime=" + modifiedTime +
                ", status=" + status +
                '}';
    }
}
