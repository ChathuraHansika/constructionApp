package com.akvasoft.construction.entity.report;

import com.akvasoft.construction.entity.ConstructionSite;
import com.akvasoft.construction.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_DAILY_REPORT")
public class DailyReport implements Serializable {
    private static final long serialVersionUID = -8578491592538287319L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DAILY_REPORT_ID")
    private Integer dailyReportId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SITE_ID")
    private ConstructionSite site;

    @Column(name = "REPORT_DATE")
    private Date reportDate;

    @Column(name = "TOTAL_COUN")
    private Integer totalCount;

    @Column(name = "TOTAL_MAN_POWER")
    private BigDecimal totalManPower;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "SITE_REQUIRMENT")
    private String siteEquipment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MODIFIED_BY")
    private User modifiedBy;

    @Column(name = "MODIFIED_TIME")
    private Date modifiedTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APPROVED_BY_SITE_CONSULTANT")
    private User siteConsultantApproved;

    @Column(name = "APPROVED_BY_SITE_CONSULTANT_TIME")
    private Date siteConsultantApprovedTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APPROVED_BY_COMPANY_MANAGER")
    private User companyManagerApproved;

    @Column(name = "APPROVED_BY_COMPANY_MANAGER_TIME")
    private Date companyManagerApprovedTime;

    public Integer getDailyReportId() {
        return dailyReportId;
    }

    public void setDailyReportId(Integer dailyReportId) {
        this.dailyReportId = dailyReportId;
    }

    public ConstructionSite getSite() {
        return site;
    }

    public void setSite(ConstructionSite site) {
        this.site = site;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalManPower() {
        return totalManPower;
    }

    public void setTotalManPower(BigDecimal totalManPower) {
        this.totalManPower = totalManPower;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSiteEquipment() {
        return siteEquipment;
    }

    public void setSiteEquipment(String siteEquipment) {
        this.siteEquipment = siteEquipment;
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

    public User getSiteConsultantApproved() {
        return siteConsultantApproved;
    }

    public void setSiteConsultantApproved(User siteConsultantApproved) {
        this.siteConsultantApproved = siteConsultantApproved;
    }

    public Date getSiteConsultantApprovedTime() {
        return siteConsultantApprovedTime;
    }

    public void setSiteConsultantApprovedTime(Date siteConsultantApprovedTime) {
        this.siteConsultantApprovedTime = siteConsultantApprovedTime;
    }

    public User getCompanyManagerApproved() {
        return companyManagerApproved;
    }

    public void setCompanyManagerApproved(User companyManagerApproved) {
        this.companyManagerApproved = companyManagerApproved;
    }

    public Date getCompanyManagerApprovedTime() {
        return companyManagerApprovedTime;
    }

    public void setCompanyManagerApprovedTime(Date companyManagerApprovedTime) {
        this.companyManagerApprovedTime = companyManagerApprovedTime;
    }

    @Override
    public String toString() {
        return "DailyReport{" +
                "dailyReportId=" + dailyReportId +
                ", site=" + site +
                ", reportDate=" + reportDate +
                ", totalCount=" + totalCount +
                ", totalManPower=" + totalManPower +
                ", remark='" + remark + '\'' +
                ", siteEquipment='" + siteEquipment + '\'' +
                ", modifiedBy=" + modifiedBy +
                ", modifiedTime=" + modifiedTime +
                ", siteConsultantApproved=" + siteConsultantApproved +
                ", siteConsultantApprovedTime=" + siteConsultantApprovedTime +
                ", companyManagerApproved=" + companyManagerApproved +
                ", companyManagerApprovedTime=" + companyManagerApprovedTime +
                '}';
    }
}
