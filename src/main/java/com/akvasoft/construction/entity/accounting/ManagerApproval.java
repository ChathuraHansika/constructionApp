package com.akvasoft.construction.entity.accounting;

import com.akvasoft.construction.entity.Role;
import com.akvasoft.construction.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_MANAGER_APPROVAL")
public class ManagerApproval implements Serializable {
    private static final long serialVersionUID = -3795073149281510082L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MANAGER_APPROVAL_ID")
    private Integer managerApprovalId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REQUESTED_BY")
    private User requestedBy;

    @Column(name = "REQUESTED_DATE")
    private Date requestedDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APPROVAL_ROLE_REQUIRED")
    private Role requierdApprovalRole;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "APPROVAL_PRIMARY_ID")
    private Integer approvalPrimaryKey;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APPROVED_BY")
    private User approvedBy;

    @Column(name = "APPROVED_DATE")
    private Date approvedDate;

    public Integer getManagerApprovalId() {
        return managerApprovalId;
    }

    public void setManagerApprovalId(Integer managerApprovalId) {
        this.managerApprovalId = managerApprovalId;
    }

    public User getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(User requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getRequierdApprovalRole() {
        return requierdApprovalRole;
    }

    public void setRequierdApprovalRole(Role requierdApprovalRole) {
        this.requierdApprovalRole = requierdApprovalRole;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getApprovalPrimaryKey() {
        return approvalPrimaryKey;
    }

    public void setApprovalPrimaryKey(Integer approvalPrimaryKey) {
        this.approvalPrimaryKey = approvalPrimaryKey;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    @Override
    public String toString() {
        return "ManagerApproval{" +
                "managerApprovalId=" + managerApprovalId +
                ", requestedBy=" + requestedBy +
                ", requestedDate=" + requestedDate +
                ", description='" + description + '\'' +
                ", requierdApprovalRole=" + requierdApprovalRole +
                ", type='" + type + '\'' +
                ", approvalPrimaryKey=" + approvalPrimaryKey +
                ", approvedBy=" + approvedBy +
                ", approvedDate=" + approvedDate +
                '}';
    }
}
