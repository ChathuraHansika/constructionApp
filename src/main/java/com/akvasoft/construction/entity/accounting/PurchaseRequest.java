package com.akvasoft.construction.entity.accounting;

import com.akvasoft.construction.entity.ConstructionSite;
import com.akvasoft.construction.entity.User;
import com.akvasoft.construction.util.DomainConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_PURCHASE_REQUEST")
public class PurchaseRequest implements Serializable {
    private static final long serialVersionUID = -5311476201363656193L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PURCHASE_REQUEST_ID")
    private Integer purchaseRequestId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SITE_ID")
    private ConstructionSite site;

    @Column(name = "REQUEST_DATE")
    private Date requestDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REQUESTED_BY")
    private User requestedBy;

    @Column(name = "EMERGENCY_BOUGHT")
    private Boolean emergencyBought;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DomainConstant.PurchaseStatus status;

    public Integer getPurchaseRequestId() {
        return purchaseRequestId;
    }

    public void setPurchaseRequestId(Integer purchaseRequestId) {
        this.purchaseRequestId = purchaseRequestId;
    }

    public ConstructionSite getSite() {
        return site;
    }

    public void setSite(ConstructionSite site) {
        this.site = site;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public User getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(User requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Boolean getEmergencyBought() {
        return emergencyBought;
    }

    public void setEmergencyBought(Boolean emergencyBought) {
        this.emergencyBought = emergencyBought;
    }

    public DomainConstant.PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(DomainConstant.PurchaseStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PurchaseRequest{" +
                "purchaseRequestId=" + purchaseRequestId +
                ", site=" + site +
                ", requestDate=" + requestDate +
                ", requestedBy=" + requestedBy +
                ", emergencyBought=" + emergencyBought +
                ", status=" + status +
                '}';
    }
}
