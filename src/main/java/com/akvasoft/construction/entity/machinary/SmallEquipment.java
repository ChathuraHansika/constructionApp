package com.akvasoft.construction.entity.machinary;

import com.akvasoft.construction.entity.User;
import com.akvasoft.construction.util.DomainConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "T_SMALL_EQUIPMENT")
@Entity
public class SmallEquipment implements Serializable {
    private static final long serialVersionUID = 8525727789675579050L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SMALL_EQUIPMENT_ID")
    private Integer smallEquipmentId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TOTAL_QUANTITY")
    private BigDecimal totalQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DomainConstant.Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MODIFIED_BY")
    private User modifiedBy;

    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    public Integer getSmallEquipmentId() {
        return smallEquipmentId;
    }

    public void setSmallEquipmentId(Integer smallEquipmentId) {
        this.smallEquipmentId = smallEquipmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(BigDecimal totalQuantity) {
        this.totalQuantity = totalQuantity;
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
        return "SmallEquipment{" +
                "smallEquipmentId=" + smallEquipmentId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", totalQuantity='" + totalQuantity + '\'' +
                ", status=" + status +
                ", modifiedBy=" + modifiedBy +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
