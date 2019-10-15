package com.akvasoft.construction.entity.report;

import com.akvasoft.construction.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "T_LARGE_EQUIPMENT_USAGE")
@Entity()
public class LargeEqupmentUsage implements Serializable {
    private static final long serialVersionUID = 5476490479953659018L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LARGE_EQUIPMENT_USAGE_ID")
    private Integer largeEquipmentUsageId;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "APPLICABLE")
    private Boolean applicable;

    @Column(name = "WORK_HOUR")
    private BigDecimal workHour;

    @Column(name = "IDLE_HOUR")
    private BigDecimal idleHour;

    @Column(name = "TOTAL_HOUR")
    private BigDecimal totalHour;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MODIFIED_BY")
    private User modifiedBy;

    @Column(name = "MODIFIED_TIME")
    private Date modifiedTime;

    public Integer getLargeEquipmentUsageId() {
        return largeEquipmentUsageId;
    }

    public void setLargeEquipmentUsageId(Integer largeEquipmentUsageId) {
        this.largeEquipmentUsageId = largeEquipmentUsageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getApplicable() {
        return applicable;
    }

    public void setApplicable(Boolean applicable) {
        this.applicable = applicable;
    }

    public BigDecimal getWorkHour() {
        return workHour;
    }

    public void setWorkHour(BigDecimal workHour) {
        this.workHour = workHour;
    }

    public BigDecimal getIdleHour() {
        return idleHour;
    }

    public void setIdleHour(BigDecimal idleHour) {
        this.idleHour = idleHour;
    }

    public BigDecimal getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(BigDecimal totalHour) {
        this.totalHour = totalHour;
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

    @Override
    public String toString() {
        return "LargeEqupmentUsage{" +
                "largeEquipmentUsageId=" + largeEquipmentUsageId +
                ", type='" + type + '\'' +
                ", applicable=" + applicable +
                ", workHour=" + workHour +
                ", idleHour=" + idleHour +
                ", totalHour=" + totalHour +
                ", modifiedBy=" + modifiedBy +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
