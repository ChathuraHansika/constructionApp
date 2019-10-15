package com.akvasoft.construction.entity.machinary;

import com.akvasoft.construction.entity.User;
import com.akvasoft.construction.util.DomainConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_SMALL_EQUIPMENT_SITE")
public class SmallEquipmentSite implements Serializable {
    private static final long serialVersionUID = -3488625716801715073L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SMALL_EQUIPMENT_SITE_ID")
    private Integer smallEquipmentSiteId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SMALL_EQUIPMENT_ID")
    private SmallEquipment smallEquipment;

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DomainConstant.Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MODIFIED_By")
    private User modifiedBy;

    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    public Integer getSmallEquipmentSiteId() {
        return smallEquipmentSiteId;
    }

    public void setSmallEquipmentSiteId(Integer smallEquipmentSiteId) {
        this.smallEquipmentSiteId = smallEquipmentSiteId;
    }

    public SmallEquipment getSmallEquipment() {
        return smallEquipment;
    }

    public void setSmallEquipment(SmallEquipment smallEquipment) {
        this.smallEquipment = smallEquipment;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public DomainConstant.Status getStatus() {
        return status;
    }

    public void setStatus(DomainConstant.Status status) {
        this.status = status;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "SmallEquipmentSite{" +
                "smallEquipmentSiteId=" + smallEquipmentSiteId +
                ", smallEquipment=" + smallEquipment +
                ", quantity=" + quantity +
                ", status=" + status +
                ", modifiedBy=" + modifiedBy +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
