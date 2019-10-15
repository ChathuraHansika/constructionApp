package com.akvasoft.construction.entity.machinary;

import com.akvasoft.construction.entity.ConstructionSite;
import com.akvasoft.construction.util.DomainConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_LARGE_EQUIPMENT")
public class LargeEquipment implements Serializable {
    private static final long serialVersionUID = -2844352523946973419L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LARGE_EQUIPMENT_ID")
    private Integer equipmentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SITE_ID")
    private ConstructionSite site;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "REG_NO")
    private String regNo;


    @Column(name = "PURCHASE_DATE")
    private Date purchaseDate;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "ANUAL_DEPRICIATION")
    private BigDecimal annualDepriciation;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DomainConstant.EquipmentStatus status;

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public ConstructionSite getSite() {
        return site;
    }

    public void setSite(ConstructionSite site) {
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAnnualDepriciation() {
        return annualDepriciation;
    }

    public void setAnnualDepriciation(BigDecimal annualDepriciation) {
        this.annualDepriciation = annualDepriciation;
    }

    public DomainConstant.EquipmentStatus getStatus() {
        return status;
    }

    public void setStatus(DomainConstant.EquipmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LargeEquipment{" +
                "equipmentId=" + equipmentId +
                ", site=" + site +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", regNo='" + regNo + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", price=" + price +
                ", annualDepriciation=" + annualDepriciation +
                ", status=" + status +
                '}';
    }
}
