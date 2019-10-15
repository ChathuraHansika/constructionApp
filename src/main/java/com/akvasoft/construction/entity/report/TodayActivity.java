package com.akvasoft.construction.entity.report;

import com.akvasoft.construction.entity.User;
import com.akvasoft.construction.util.DomainConstant;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_TODAY_ACTIVITY")
public class TodayActivity implements Serializable {
    private static final long serialVersionUID = -8827871910558059700L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTIVITY_ID")
    private Integer activityId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DAILY_REPORT_ID")
    private DailyReport dailyReport;

    @Column(name = "ACTIVITY_NO")
    private String activityNo;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUPERVISED_BY")
    private User supervisedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DomainConstant.Status status;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public DailyReport getDailyReport() {
        return dailyReport;
    }

    public void setDailyReport(DailyReport dailyReport) {
        this.dailyReport = dailyReport;
    }

    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(String activityNo) {
        this.activityNo = activityNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getSupervisedBy() {
        return supervisedBy;
    }

    public void setSupervisedBy(User supervisedBy) {
        this.supervisedBy = supervisedBy;
    }

    public DomainConstant.Status getStatus() {
        return status;
    }

    public void setStatus(DomainConstant.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TodayActivity{" +
                "activityId=" + activityId +
                ", dailyReport=" + dailyReport +
                ", activityNo='" + activityNo + '\'' +
                ", description='" + description + '\'' +
                ", supervisedBy=" + supervisedBy +
                ", status=" + status +
                '}';
    }
}
